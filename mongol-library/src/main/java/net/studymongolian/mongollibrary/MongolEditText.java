package net.studymongolian.mongollibrary;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.LocaleList;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.text.Editable;
import android.text.InputType;
import android.text.Selection;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.ExtractedText;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import android.widget.HorizontalScrollView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.XmlRes;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.text.BreakIterator;
import java.util.ArrayList;


// FIXME crash if setting on OnFocusChangeListener

@SuppressWarnings("SwitchStatementWithTooFewBranches")
public class MongolEditText extends MongolTextView {

    private static final int CONTEXT_MENU_TOUCH_PADDING_DP = 50;
    private Paint mCursorPaint;
    private boolean mCursorVisible = true;
    private Blink mBlink;
    private long mShowCursor;
    static final int BLINK = 500;
    static final int CURSOR_WIDTH = 2; // dp
    private static final int CURSOR_DEFAULT_COLOR = Color.parseColor("#4ac3ff"); // blue
    private Path mCursorPath;
    private GestureDetector mDetector;
    int mBatchEditNesting = 0;
    private ArrayList<TextWatcher> mListeners;
    private boolean mAllowSystemKeyboard = true;
    private ContextMenuCallback mContextMenuCallbackListener;
    private int mExtractedTextRequestToken = 0;
    InputContentType mInputContentType;
    int mInputType = EditorInfo.TYPE_CLASS_TEXT;

    private int mSelectionHandle = SCROLLING_UNKNOWN;
    private static final int SCROLLING_UNKNOWN = 0;
    private static final int SCROLLING_START = 1;
    private static final int SCROLLING_END = 2;

    private static final int DIRECTION_LEFT = -1;
    private static final int DIRECTION_RIGHT = 1;

    public interface ContextMenuCallback {
        MongolMenu getMongolEditTextContextMenu(MongolEditText met);
    }

    public void setContextMenuCallbackListener(ContextMenuCallback listener) {
        mContextMenuCallbackListener = listener;
    }


    public MongolEditText(Context context) {
        super(context);
        init(context, null, 0);
    }

    public MongolEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public MongolEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MongolEditText, defStyleAttr, 0);
        mCursorVisible = a.getBoolean(R.styleable.MongolEditText_cursorVisible, true);
        int imeOptions = a.getInt(R.styleable.MongolEditText_imeOptions, EditorInfo.TYPE_NULL);
        mInputType = a.getInt(R.styleable.MongolEditText_inputType, InputType.TYPE_CLASS_TEXT);
        a.recycle();

        setImeOptions(imeOptions);

        int textLength = super.getText().length();
        setSelection(textLength); // TODO why did I do this?

        mCursorPaint = new Paint();
        mCursorPaint.setColor(CURSOR_DEFAULT_COLOR);
        mCursorPaint.setStyle(Paint.Style.FILL);
        mCursorPaint.setAntiAlias(true);

        mCursorPath = new Path();

        // allow this view to receive input from keyboard
        setFocusable(true);
        setFocusableInTouchMode(true);

        // get notified of text changes
        mTextStorage.setOnChangeListener(new MongolTextStorage.OnChangeListener() {

            @Override
            public void beforeTextChanged(CharSequence text, int start, int count, int after) {
                // notify any listeners the user may have added
                if (mListeners != null && mListeners.size() > 0) {
                    for (TextWatcher watcher : mListeners) {
                        watcher.beforeTextChanged(text, start, count, after);
                    }
                }
            }

            @Override
            public void onTextChanged(CharSequence text, int start, int before, int count) {
                // TODO just update the layout from the start position rather than everything
                MongolEditText.super.mLayout.setText(mTextStorage.getGlyphText());
                reportExtractedText();
                invalidate();
                requestLayout();

                post(new Runnable() {
                    @Override
                    public void run() {
                        scrollToCursorPositionIfNeeded();
                    }
                });

                // notify any listeners the user may have added
                if (mListeners != null && mListeners.size() > 0) {
                    for (TextWatcher watcher : mListeners) {
                        watcher.onTextChanged(text, start, before, count);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (mListeners != null && mListeners.size() > 0) {
                    for (TextWatcher watcher : mListeners) {
                        watcher.afterTextChanged(editable);
                    }
                }
                if (isFocused()) {
                    makeBlink();
                }
            }


            @Override
            public void onSpanChanged(Spanned buf, Object what, int oldStart, int newStart, int oldEnd, int newEnd) {

                // TODO only invalidate region affected by the span
                invalidate();
                // FIXME only need to request layout for metric affecting spans
                requestLayout();


                if (isNonIntermediateSelectionSpan(buf, what)) {
                    sendUpdateSelection();
                    reportExtractedText();
                    scrollToCursorPositionIfNeeded();
                }
            }
        });

        // handle key presses not handled by the InputConnection
        setOnKeyListener(new OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {

                    int start = getSelectionStart();
                    int end = getSelectionEnd();

                    // check if there is a selection
                    if (start < 0 || end < 0) return false;

                    if (keyCode == KeyEvent.KEYCODE_DEL) {              // key backspace
                        return onDeleteText(start, end);
                    } else if (keyCode == KeyEvent.KEYCODE_DPAD_UP) {
                        onMoveCursorUp(start, end);
                        return true;
                    } else if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN) {
                        onMoveCursorDown(start, end);
                        return true;
                    } else if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
                        onMoveCursorLeft(start, end);
                        return true;
                    } else if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
                        onMoveCursorRight(start, end);
                        return true;

                        // TODO are there any other control keys that we should handle?
                    } else if (event.getUnicodeChar() != 0) {          // key enter, numbers, etc

                        String text = String.valueOf((char) event.getUnicodeChar());
                        // TODO Handle dead keys? https://stackoverflow.com/a/44982429
                        if (start != end) {
                            mTextStorage.delete(start, end);
                        }
                        mTextStorage.insert(start, text);
                        return true;
                    }
                }
                return false;
            }
        });

        // for communication to the MongolInputMethodManager
        this.mMongolImeManager = null;

        // gestures
        mDetector = new GestureDetector(getContext(), new MyListener());
    }

    private void scrollToCursorPositionIfNeeded() {
        ViewParent parent = getParent();
        if (!(parent instanceof HorizontalScrollView)) return;
        HorizontalScrollView scrollView = (HorizontalScrollView) parent;
        int start = getSelectionStart();
        int end = getSelectionEnd();
        if (start != end) return;
        MongolLayout layout = getLayout();
        int line = layout.getLineForOffset(start);
        int lineRight = layout.getLineTop(line);
        int lineLeft = layout.getLineBottom(line);
        Rect scrollBounds = new Rect();
        scrollView.getDrawingRect(scrollBounds);
        if (lineRight > scrollBounds.right) {
            int halfLineWidth = (lineRight - lineLeft) / 2;
            int scrollTo = lineRight - scrollBounds.width() + halfLineWidth;
            scrollView.smoothScrollTo(scrollTo, 0);
        } else if (lineLeft < scrollBounds.left) {
            scrollView.smoothScrollTo(lineLeft, 0);
        }
    }

    private boolean onDeleteText(int selectionStart, int selectionEnd) {
        if (selectionStart == selectionEnd) {
            if (selectionStart <= 0) return false;
            final int codepoint = Character.codePointBefore(mTextStorage, selectionStart);
            int charCount = Character.charCount(codepoint);
            mTextStorage.delete(selectionStart - charCount, selectionStart);
        } else {
            // delete highlighted text
            mTextStorage.delete(selectionStart, selectionEnd);
        }
        return true;
    }

    private void onMoveCursorUp(int selectionStart, int selectionEnd) {
        if (selectionStart == selectionEnd) {
            int previous = getPrecedingCharIndex(selectionStart);
            if (previous == selectionStart) return;
            setSelection(previous);
        } else {
            int start = Math.min(selectionStart, selectionEnd);
            setSelection(start);
        }
    }

    private int getPrecedingCharIndex(int currentOffset) {
        BreakIterator boundary = BreakIterator.getCharacterInstance();
        boundary.setText(mTextStorage.toString());
        int preceding = boundary.preceding(currentOffset);
        return (preceding == BreakIterator.DONE) ? currentOffset : preceding;
    }

    private void onMoveCursorDown(int selectionStart, int selectionEnd) {
        if (selectionStart == selectionEnd) {
            int next = getFollowingCharIndex(selectionStart);
            if (next == selectionStart) return;
            setSelection(next);
        } else {
            int end = Math.max(selectionStart, selectionEnd);
            setSelection(end);
        }
    }

    private int getFollowingCharIndex(int currentOffset) {
        BreakIterator boundary = BreakIterator.getCharacterInstance();
        boundary.setText(mTextStorage.toString());
        int following = boundary.following(currentOffset);
        return (following == BreakIterator.DONE) ? currentOffset : following;
    }

    private void onMoveCursorLeft(int selectionStart, int selectionEnd) {
        if (selectionStart == selectionEnd) {
            moveCursorHorizontally(selectionStart, DIRECTION_LEFT);
        } else {
            int start = Math.min(selectionStart, selectionEnd);
            setSelection(start);
        }
    }

    private void moveCursorHorizontally(int startOffset, int lineDirection) {
        // get current line
        MongolLayout layout = getLayout();
        int currentLine = layout.getLineForOffset(startOffset);

        // go to start if trying to go left on first line
        if (lineDirection == DIRECTION_LEFT && currentLine <= 0) {
            setSelection(0);
            return;
        }

        // go to end if trying to go right on last line
        int indexOfLastLine = layout.getLineCount() - 1;
        if (lineDirection == DIRECTION_RIGHT && currentLine >= indexOfLastLine) {
            setSelection(mTextStorage.length());
            return;
        }

        // get vertical y position of cursor
        float y = layout.getVertical(startOffset);

        // get closest offset for same position on adjacent line
        int newOffset = layout.getOffsetForVertical(currentLine + lineDirection, y);
        setSelection(newOffset);

        // FIXME when moving at bottom of line the cursor will be set at the top
        // This bug also happens when tapping below the last character on a line
    }

    private void onMoveCursorRight(int selectionStart, int selectionEnd) {
        if (selectionStart == selectionEnd) {
            moveCursorHorizontally(selectionStart, DIRECTION_RIGHT);
        } else {
            int end = Math.max(selectionStart, selectionEnd);
            setSelection(end);
        }
    }

    @Override
    public InputConnection onCreateInputConnection(EditorInfo outAttrs) {

        if (onCheckIsTextEditor() && isEnabled()) {
            outAttrs.inputType = getInputType();
            if (mInputContentType != null) {
                outAttrs.imeOptions = mInputContentType.imeOptions;
                outAttrs.privateImeOptions = mInputContentType.privateImeOptions;
                outAttrs.actionLabel = mInputContentType.imeActionLabel;
                outAttrs.actionId = mInputContentType.imeActionId;
                outAttrs.extras = mInputContentType.extras;
            } else {
                outAttrs.imeOptions = EditorInfo.TYPE_CLASS_TEXT;
            }
        }

        outAttrs.initialSelStart = getSelectionStart();
        outAttrs.initialSelEnd = getSelectionEnd();

        return new MetInputConnection(this, true);
    }

    public void showSystemKeyboard() {
        InputMethodManager imm = (InputMethodManager) getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm == null) return;
        imm.showSoftInput(this, InputMethodManager.SHOW_FORCED);
    }

    private void hideSystemKeyboard() {
        InputMethodManager imm = (InputMethodManager) getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm == null) return;
        imm.hideSoftInputFromWindow(this.getWindowToken(), 0);
    }

    /**
     * Interface for listening to IME option actions
     */
    public interface OnEditorActionListener {
        /**
         * Called when an IME performs an action
         *
         * @param view     The MongolEditText that was clicked.
         * @param actionId ID of the action. This will be either the
         *                 id you set, or EditorInfo.IME_NULL if the enter key was pressed.
         * @param event    If triggered by an enter key, this is the event;
         *                 otherwise, this is null.
         * @return Return true if you have consumed the action, else false.
         */
        boolean onEditorAction(MongolEditText view, int actionId, KeyEvent event);
    }

    public void setOnEditorActionListener(OnEditorActionListener listener) {
        createInputContentTypeIfNeeded();
        mInputContentType.onEditorActionListener = listener;
    }

    void createInputContentTypeIfNeeded() {
        if (mInputContentType == null) {
            mInputContentType = new InputContentType();
        }
    }

    static class InputContentType {
        int imeOptions = EditorInfo.IME_NULL;
        String privateImeOptions;
        CharSequence imeActionLabel;
        int imeActionId;
        Bundle extras;
        OnEditorActionListener onEditorActionListener;
        boolean enterDown;
    }

    public void setImeOptions(int imeOptions) {
        createInputContentTypeIfNeeded();
        mInputContentType.imeOptions = imeOptions;
    }

    public int getImeOptions() {
        return mInputContentType != null
                ? mInputContentType.imeOptions : EditorInfo.IME_NULL;
    }

    public void setImeActionLabel(CharSequence label, int actionId) {
        createInputContentTypeIfNeeded();
        mInputContentType.imeActionLabel = label;
        mInputContentType.imeActionId = actionId;
    }

    public CharSequence getImeActionLabel() {
        return mInputContentType != null
                ? mInputContentType.imeActionLabel : null;
    }

    public int getImeActionId() {
        return mInputContentType != null
                ? mInputContentType.imeActionId : 0;
    }

    public void onEditorAction(int actionCode) {
        final InputContentType ict = mInputContentType;
        if (ict != null) {
            if (ict.onEditorActionListener != null) {
                if (ict.onEditorActionListener.onEditorAction(this,
                        actionCode, null)) {
                    return;
                }
            }

            // modified this from TextView source code. Not sure if it still behaves correctly.
            // Used to be FOCUS_FORWARD but that was giving an error.
            if (actionCode == EditorInfo.IME_ACTION_NEXT) {
                View v = focusSearch(FOCUS_RIGHT);
                if (v != null) {
                    if (!v.requestFocus(FOCUS_RIGHT)) {
                        throw new IllegalStateException("focus search returned a view "
                                + "that wasn't able to take focus!");
                    }
                }
                return;

            } else if (actionCode == EditorInfo.IME_ACTION_PREVIOUS) {
                View v = focusSearch(FOCUS_LEFT);
                if (v != null) {
                    if (!v.requestFocus(FOCUS_LEFT)) {
                        throw new IllegalStateException("focus search returned a view "
                                + "that wasn't able to take focus!");
                    }
                }
                return;

            } else if (actionCode == EditorInfo.IME_ACTION_DONE) {
                hideSystemKeyboard();
                return;
            }
        }

        // Press the enter key
        // Changed original from TextView. It used to be viewRootImpl.dispatchKeyFromIme.
        // Don't know if this still works.
        long eventTime = SystemClock.uptimeMillis();
        dispatchKeyEvent(
                new KeyEvent(eventTime, eventTime,
                        KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER, 0, 0,
                        KeyCharacterMap.VIRTUAL_KEYBOARD, 0,
                        KeyEvent.FLAG_SOFT_KEYBOARD | KeyEvent.FLAG_KEEP_TOUCH_MODE
                                | KeyEvent.FLAG_EDITOR_ACTION));
        dispatchKeyEvent(
                new KeyEvent(SystemClock.uptimeMillis(), eventTime,
                        KeyEvent.ACTION_UP, KeyEvent.KEYCODE_ENTER, 0, 0,
                        KeyCharacterMap.VIRTUAL_KEYBOARD, 0,
                        KeyEvent.FLAG_SOFT_KEYBOARD | KeyEvent.FLAG_KEEP_TOUCH_MODE
                                | KeyEvent.FLAG_EDITOR_ACTION));

    }

    public void setPrivateImeOptions(String type) {
        createInputContentTypeIfNeeded();
        mInputContentType.privateImeOptions = type;
    }

    public String getPrivateImeOptions() {
        return mInputContentType != null
                ? mInputContentType.privateImeOptions : null;
    }

    public void setInputExtras(@XmlRes int xmlResId) throws XmlPullParserException, IOException {
        XmlResourceParser parser = getResources().getXml(xmlResId);
        createInputContentTypeIfNeeded();
        mInputContentType.extras = new Bundle();
        getResources().parseBundleExtras(parser, mInputContentType.extras);
    }

    // @param create If true, the extras will be created if they don't already exist
    public Bundle getInputExtras(boolean create) {
        if (mInputContentType == null) {
            if (!create) return null;
            createInputContentTypeIfNeeded();
        }
        if (mInputContentType.extras == null) {
            if (!create) return null;
            mInputContentType.extras = new Bundle();
        }
        return mInputContentType.extras;
    }

    public void setRawInputType(int type) {
        mInputType = type;
    }

    public int getInputType() {
        return mInputType;
    }

    //////////// custom listener to send events to the MongolInputMethodManager //////////////////

    public interface OnMongolEditTextInputEventListener {
        void updateSelection(View view, int selStart, int selEnd,
                             int candidatesStart, int candidatesEnd);
        //void updateExtractedText (View view, int token, ExtractedText text);
        // TODO everywhere that these are called also update the system InputMethodManager
    }

    private OnMongolEditTextInputEventListener mMongolImeManager;

    public void setOnMongolEditTextUpdateListener(OnMongolEditTextInputEventListener listener) {
        this.mMongolImeManager = listener;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public boolean onCheckIsTextEditor() {
        return mInputType != EditorInfo.TYPE_NULL;
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        mShowCursor = SystemClock.uptimeMillis();
        if (focused) {
            makeBlink();
        }
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
    }

    @Override
    protected boolean getDefaultEditable() {
        return true;
    }

    class MyListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onDown(MotionEvent e) {

            int x = (int) e.getX();
            int y = (int) e.getY();
            int start = getSelectionStart();
            int end = getSelectionEnd();
            int offset = getOffsetForPosition(x, y);
            if (Math.abs(start - offset) <= Math.abs(end - offset)) {
                mSelectionHandle = SCROLLING_START;
            } else {
                mSelectionHandle = SCROLLING_END;
            }

            // disable scrolling in parent scrollview in some situations
            // (1) this EditText width is <= parent width (ie, parent can't scroll anyway)
            // (2) There is a selection and the down location is close to a cursor handle
            View parent = (View) getParent();
            if (parent != null) {
                // (1)
                if (getWidth() <= parent.getWidth()) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                // (2)
                if (touchIsCloseToSelection(x, y, start, end)) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                // enabled again in ACTION_UP
            }

            requestFocus();
            return true;
        }

        // return true if touch is within 40dp of a cursor/selection handle
        private boolean touchIsCloseToSelection(int x, int y, int start, int end) {

            final int distanceAwayDp = 40; // dp
            int distanceAwayPx = (int) (distanceAwayDp * getResources().getDisplayMetrics().density);

            // test if near selection start
            Rect selectionStart = getCursorRect(start);
            Rect nearbyStart = new Rect(
                    selectionStart.left - distanceAwayPx,
                    selectionStart.top - distanceAwayPx,
                    selectionStart.right + distanceAwayPx,
                    selectionStart.bottom + distanceAwayPx);
            if (nearbyStart.contains(x, y)) return true;

            if (end == start) return false;

            // test if near selection end
            Rect selectionEnd = getCursorRect(end);
            Rect nearbyEnd = new Rect(
                    selectionEnd.left - distanceAwayPx,
                    selectionEnd.top - distanceAwayPx,
                    selectionEnd.right + distanceAwayPx,
                    selectionEnd.bottom + distanceAwayPx);
            return (nearbyEnd.contains(x, y));
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            int x = (int) e.getX();
            int y = (int) e.getY();

            // find the position
            int offset = getOffsetForPosition(x, y);

            // set the selection
            setSelection(offset);
            invalidate();

            if (mAllowSystemKeyboard) {
                showSystemKeyboard();
            }
            makeBlink();

            return super.onSingleTapConfirmed(e);
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {

            int x = (int) e.getX();
            int y = (int) e.getY();

            // find the position
            int offset = getOffsetForPosition(x, y);

            // select word
            BreakIterator iterator = BreakIterator.getWordInstance();
            iterator.setText(getText().toString());

            // start and end are the word boundaries;
            int start;
            if (iterator.isBoundary(offset)) {
                start = offset;
            } else {
                start = iterator.preceding(offset);
            }
            int end = iterator.following(offset);

            // handle tapping at the very beginning or end.
            if (end == BreakIterator.DONE) {
                end = start;
                start = iterator.preceding(offset);
                if (start == BreakIterator.DONE) start = end;
            }

            setSelection(start, end);

            return super.onDoubleTap(e);
        }

        @Override
        public void onLongPress(MotionEvent e) {
            int x = (int) e.getRawX();
            int y = (int) e.getRawY();
            MongolMenu contextMenu = getContextMenu();
            showMongolContextMenu(contextMenu, x, y);
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {

            // if there is no selection then return
            int start = getSelectionStart();
            int end = getSelectionEnd();

            int xMove = (int) e2.getX();
            int yMove = (int) e2.getY();

            int moveOffset = getOffsetForPosition(xMove, yMove);
            if (mSelectionHandle == SCROLLING_START) {
                start = moveOffset;
            } else if (mSelectionHandle == SCROLLING_END) {
                end = moveOffset;
            }

            setSelection(start, end);

            return super.onScroll(e1, e2, distanceX, distanceY);
        }
    }

    private void showMongolContextMenu(MongolMenu menu, int xTouchLocation, int yTouchLocation) {
        float paddingPx = CONTEXT_MENU_TOUCH_PADDING_DP * getResources().getDisplayMetrics().density;
        Rect menuSize = menu.getDesiredSize();
        int y = yTouchLocation - menuSize.height() - (int) paddingPx;
        menu.showAtLocation(this, Gravity.NO_GRAVITY, xTouchLocation, y);
    }

    private MongolMenu getContextMenu() {
        if (mContextMenuCallbackListener != null)
            return mContextMenuCallbackListener.getMongolEditTextContextMenu(this);
        return getDefaultContextMenu();
    }

    private MongolMenu getDefaultContextMenu() {
        final Context context = getContext();
        MongolMenu menu = new MongolMenu(context);
        CharSequence selected = getSelectedText();

        // copy, cut
        if (selected.length() > 0) {
            menu.add(new MongolMenuItem(context.getString(R.string.copy), R.drawable.ic_keyboard_copy_32dp));
            menu.add(new MongolMenuItem(context.getString(R.string.cut), R.drawable.ic_keyboard_cut_32dp));
        }

        // paste
        menu.add(new MongolMenuItem(context.getString(R.string.paste), R.drawable.ic_keyboard_paste_32dp));

        // select all
        if (selected.length() < mTextStorage.length()) {
            menu.add(new MongolMenuItem(context.getString(R.string.select_all), R.drawable.ic_keyboard_select_all_32dp));
        }

        menu.setOnMenuItemClickListener(contextMenuItemClickListener);
        return menu;
    }

    MongolMenu.OnMenuItemClickListener contextMenuItemClickListener = new MongolMenu.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MongolMenuItem item) {
            String name = item.getTitle().toString();

            Context context = getContext();
            String copy = context.getString(R.string.copy);
            String cut = context.getString(R.string.cut);
            String paste = context.getString(R.string.paste);
            String selectAll = context.getString(R.string.select_all);

            if (name.equals(copy)) {
                copySelectedText();
            } else if (name.equals(cut)) {
                cutSelectedText();
            } else if (name.equals(paste)) {
                pasteText();
            } else if (name.equals(selectAll)) {
                selectAll();
            } else {
                return false;
            }
            return true;
        }
    };

    public boolean copySelectedText() {
        CharSequence selectedText = getSelectedText();
        if (TextUtils.isEmpty(selectedText))
            return false;
        Context context = getContext();
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText(null, selectedText);
        if (clipboard == null) return false;
        clipboard.setPrimaryClip(clip);
        moveCursorToSelectionEnd();
        return true;
    }

    private void moveCursorToSelectionEnd() {
        int start = getSelectionStart();
        int end = getSelectionEnd();
        int moveTo = Math.max(start, end);
        setSelection(moveTo);
    }

    public void cutSelectedText() {
        int start = getSelectionStart();
        int end = getSelectionEnd();
        boolean copiedSuccessfully = copySelectedText();
        if (copiedSuccessfully) {
            mTextStorage.delete(start, end);
        }
    }

    public void pasteText() {
        Context context = getContext();
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        if (clipboard == null) return;
        ClipData clip = clipboard.getPrimaryClip();
        if (clip == null) return;
        ClipData.Item item = clip.getItemAt(0);
        if (item == null) return;
        CharSequence text = item.getText();
        if (text == null) return;
        int start = getSelectionStart();
        int end = getSelectionEnd();
        mTextStorage.replace(start, end, text);
    }

    @SuppressLint("ClickableViewAccessibility") // TODO make this view accessible
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            // onDown is handled in GestureDetector
            case MotionEvent.ACTION_UP:
                // re-enable scrolling for parent scrollview
                ViewParent view = this.getParent();
                view.requestDisallowInterceptTouchEvent(false);
        }

        return mDetector.onTouchEvent(event);
    }

    @Override
    public Editable getText() {
        return mTextStorage;
    }

    /**
     * Adds a TextWatcher to the list of those whose methods are called
     * whenever this MongolEditText's text changes.
     *
     * @param watcher: the TextWatcher object to add
     */
    @SuppressWarnings("unused")
    public void addTextChangedListener(TextWatcher watcher) {
        if (mListeners == null) {
            mListeners = new ArrayList<>();
        }

        mListeners.add(watcher);
    }

    /**
     * Removes the specified TextWatcher from the list of those whose
     * methods are called whenever this TextView's text changes.
     *
     * @param watcher: the TextWatcher object to remove
     */
    @SuppressWarnings("unused")
    public void removeTextChangedListener(TextWatcher watcher) {
        if (mListeners != null) {
            int i = mListeners.indexOf(watcher);

            if (i >= 0) {
                mListeners.remove(i);
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {

        int start = getSelectionStart();
        int end = getSelectionEnd();

        // no selection
        if (start < 0 || end < 0) {
            // if there is no selection then just draw text
            super.onDraw(canvas);
            return;
        }

        // draw selection highlight
        if (start != end) {
            canvas.drawPath(getSelectionPath(start, end), mCursorPaint);
        }

        // draw text layout next
        super.onDraw(canvas);

        // draw the blinking cursor on top
        if (start == end && blinkShouldBeOn()) {
            canvas.drawRect(getCursorPath(start), mCursorPaint);
        }
    }

    private boolean blinkShouldBeOn() {
        //noinspection SimplifiableIfStatement
        if (!mCursorVisible || !isFocused()) return false;
        return (SystemClock.uptimeMillis() - mShowCursor) % (2 * BLINK) < BLINK;
    }

    private Rect getCursorPath(int cursorLocation) {
        int line = super.mLayout.getLineForOffset(cursorLocation);
        int width = super.mLayout.getLineDescent(line) - super.mLayout.getLineAscent(line);
        int x = super.mLayout.getLineBottom(line) + getPaddingLeft();
        int y = (int) super.mLayout.getVertical(cursorLocation) + getPaddingTop();

        int cursorWidthPX = (int) (CURSOR_WIDTH * getResources().getDisplayMetrics().density);
        return new Rect(x, y, x + width, y + cursorWidthPX);
    }

    private Path getSelectionPath(int unicodeStart, int unicodeEnd) {

        int start;
        int end;

        if (unicodeStart <= unicodeEnd) {
            start = unicodeStart;
            end = unicodeEnd;
        } else {
            start = unicodeEnd;
            end = unicodeStart;
        }

        // start cursor
        int lineStart = super.mLayout.getLineForOffset(start);
        int widthStart = super.mLayout.getLineDescent(lineStart) - super.mLayout.getLineAscent(lineStart);
        int xStart = super.mLayout.getLineBottom(lineStart) + getPaddingLeft();
        int yStart = (int) super.mLayout.getVertical(start) + getPaddingTop();

        // end cursor
        int lineEnd = super.mLayout.getLineForOffset(end);
        int widthEnd = super.mLayout.getLineDescent(lineEnd) - super.mLayout.getLineAscent(lineEnd);
        int xEnd = super.mLayout.getLineBottom(lineEnd) + getPaddingLeft();
        int yEnd = (int) super.mLayout.getVertical(end) + getPaddingTop();

        // create the selection path
        mCursorPath.reset();
        if (xStart == xEnd) { // one rect on a single line
            mCursorPath.addRect(xStart, yStart, xStart + widthStart, yEnd, Path.Direction.CW);
        } else if (yStart >= yEnd && lineStart == lineEnd + 1) { // two rects on two adjacent lines
            mCursorPath.addRect(xStart, yStart, xStart + widthStart,
                    getPaddingTop() + mLayout.getHeight(), Path.Direction.CW);
            mCursorPath.addRect(xEnd, getPaddingTop(), xEnd + widthEnd, yEnd, Path.Direction.CW);
        } else { // large notched corner rect spanning multiple lines
            mCursorPath.moveTo(xStart, yStart);
            mCursorPath.lineTo(xStart + widthStart, yStart);
            mCursorPath.lineTo(xStart + widthStart, getPaddingTop());
            mCursorPath.lineTo(xEnd + widthEnd, getPaddingTop());
            mCursorPath.lineTo(xEnd + widthEnd, yEnd);
            mCursorPath.lineTo(xEnd, yEnd);
            mCursorPath.lineTo(xEnd, getPaddingTop() + mLayout.getHeight());
            mCursorPath.lineTo(xStart, getPaddingTop() + mLayout.getHeight());
            mCursorPath.close();
        }

        return mCursorPath;
    }

    private Rect getCursorRect(int unicodeIndex) {
        int line = super.mLayout.getLineForOffset(unicodeIndex);
        int width = super.mLayout.getLineDescent(line) - super.mLayout.getLineAscent(line);
        int x = super.mLayout.getLineBottom(line) + getPaddingLeft();
        int y = (int) super.mLayout.getVertical(unicodeIndex) + getPaddingTop();

        return new Rect(x, y, x + width, y + CURSOR_WIDTH);
    }

    private void makeBlink() {
        if (!mCursorVisible) {
            if (mBlink != null) {
                mBlink.removeCallbacks(mBlink);
            }

            return;
        }

        if (mBlink == null)
            mBlink = new Blink(this);

        mBlink.removeCallbacks(mBlink);
        mBlink.postAtTime(mBlink, mShowCursor + BLINK);
    }

    private void invalidateCursorPath() {
        int start = getSelectionStart();
        if (start < 0) return;
        Rect cursorPath = getCursorPath(start);
        invalidate(cursorPath.left, cursorPath.top, cursorPath.right, cursorPath.bottom);
    }

    public void setCursorVisible(boolean visible) {
        mCursorVisible = visible;
        invalidateCursorPath();

        if (visible) {
            makeBlink();
        } else if (mBlink != null) {
            mBlink.removeCallbacks(mBlink);
        }
    }

    public boolean isCursorVisible() {
        return mCursorVisible;
    }

    @Override
    public Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();

        // Save state if we are forced to
        final boolean freezesText = getFreezesText();
        boolean hasSelection = false;
        int start = -1;
        int end = -1;

        if (mTextStorage != null) {
            start = getSelectionStart();
            end = getSelectionEnd();
            if (start >= 0 || end >= 0) {
                hasSelection = true;
            }
        }

        if (freezesText || hasSelection) {
            SavedState ss = new SavedState(superState);

            if (freezesText) {
                if (mTextStorage instanceof Spanned) {
                    ss.text = new SpannableStringBuilder(mTextStorage);
                } else {
                    ss.text = mTextStorage.toString();
                }
            }

            if (hasSelection) {
                ss.selStart = start;
                ss.selEnd = end;
            }

            if (isFocused() && start >= 0 && end >= 0) {
                ss.frozenWithFocus = true;
            }

            return ss;
        }

        return superState;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        if (!(state instanceof SavedState)) {
            super.onRestoreInstanceState(state);
            return;
        }

        SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());

        if (ss.text != null) {
            setText(ss.text);
        }

        if (ss.selStart >= 0 && ss.selEnd >= 0) {
            if (mTextStorage != null) {
                int len = mTextStorage.length();

                if (ss.selStart > len || ss.selEnd > len) {
                    String restored = "";

                    if (ss.text != null) {
                        restored = "(restored) ";
                    }

                    Log.e("LOG_TAG", "Saved cursor position " + ss.selStart +
                            "/" + ss.selEnd + " out of range for " + restored +
                            "text " + mTextStorage.toString());
                } else {
                    Selection.setSelection(mTextStorage, ss.selStart, ss.selEnd);
                }
            }
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);

        if (hasWindowFocus) {
            if (mBlink != null) {
                mBlink.uncancel();

                if (isFocused()) {
                    mShowCursor = SystemClock.uptimeMillis();
                    makeBlink();
                }
            }
        } else {
            if (mBlink != null) {
                mBlink.cancel();
            }
            if (mInputContentType != null) {
                mInputContentType.enterDown = false;
            }
            hideSystemKeyboard();
        }
    }

    // not really using this, just following the standard EditText, could probably delete this for now
    public boolean getFreezesText() {
        return true;
    }

    public void setAllowSystemKeyboard(boolean whether) {
        mAllowSystemKeyboard = whether;
    }


    // This along with onSaveInstanceState and onRestoreInstanceState
    // is adapted from the Android TextView source. Check that for changes.
    public static class SavedState extends BaseSavedState {
        int selStart = -1;
        int selEnd = -1;
        CharSequence text;
        boolean frozenWithFocus;

        SavedState(Parcelable superState) {
            super(superState);
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeInt(selStart);
            out.writeInt(selEnd);
            out.writeInt(frozenWithFocus ? 1 : 0);
            TextUtils.writeToParcel(text, out, flags);
        }

        @NonNull
        @Override
        public String toString() {
            String str = "MongolEditText.SavedState{"
                    + Integer.toHexString(System.identityHashCode(this))
                    + " start=" + selStart + " end=" + selEnd;
            if (text != null) {
                str += " text=" + text;
            }
            return str + "}";
        }

        @SuppressWarnings("hiding")
        public static final Parcelable.Creator<SavedState> CREATOR
                = new Parcelable.Creator<SavedState>() {
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };

        private SavedState(Parcel in) {
            super(in);
            selStart = in.readInt();
            selEnd = in.readInt();
            frozenWithFocus = (in.readInt() != 0);
            text = TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(in);
        }
    }

    // batch edits from IME

    public boolean beginBatchEdit() {
        int nesting = ++mBatchEditNesting;
        return nesting > 0; // should always be true
    }

    public boolean endBatchEdit() {
        int nesting = --mBatchEditNesting;
        if (nesting == 0) {
            finishBatchEdit();
        }
        return nesting > 0;
    }

    void ensureEndedBatchEdit() {
        if (mBatchEditNesting != 0) {
            mBatchEditNesting = 0;
            finishBatchEdit();
        }
    }

    void finishBatchEdit() {
        sendUpdateSelection();
        reportExtractedText();
    }

    private void sendUpdateSelection() {
        if (mBatchEditNesting <= 0) {
            final int selectionStart = getSelectionStart();
            final int selectionEnd = getSelectionEnd();
            int candidateStart = -1;
            int candidateEnd = -1;
            if (mTextStorage != null) {
                candidateStart = MetInputConnection.getComposingSpanStart(mTextStorage);
                candidateEnd = MetInputConnection.getComposingSpanEnd(mTextStorage);
            }

            if (mMongolImeManager != null) {
                mMongolImeManager.updateSelection(this,
                        selectionStart, selectionEnd, candidateStart, candidateEnd);
            } else {
                InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null)
                    imm.updateSelection(this,
                            selectionStart, selectionEnd, candidateStart, candidateEnd);
            }

            if (selectionStart == selectionEnd)
                makeBlink();
        }
    }

    private void reportExtractedText() {

        // custom keyboards don't have an extracted view.
        if (mMongolImeManager != null) return;

        // TODO cancel this if not in extracted text mode

        ExtractedText et = new ExtractedText();
        final CharSequence content = getText();
        final int length = content.length();
        et.partialStartOffset = -1;
        et.partialEndOffset = -1;
        et.startOffset = 0;
        et.selectionStart = getSelectionStart();
        et.selectionEnd = getSelectionEnd();
        et.flags = 0;
        et.text = content.subSequence(0, length);

        InputMethodManager imm = (InputMethodManager) getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm == null) return;
        imm.updateExtractedText(this, mExtractedTextRequestToken, et);
    }

    void setExtractedTextToken(int token) {
        mExtractedTextRequestToken = token;
    }

    // from Android source Editor.SpanController#isNonIntermediateSelectionSpan
    private boolean isNonIntermediateSelectionSpan(final Spanned text, final Object span) {
        return (Selection.SELECTION_START == span || Selection.SELECTION_END == span)
                && (text.getSpanFlags(span) & Spanned.SPAN_INTERMEDIATE) == 0;
    }

    private static class Blink extends Handler implements Runnable {
        private WeakReference<MongolEditText> mView;
        private boolean mCancelled;

        Blink(MongolEditText v) {
            mView = new WeakReference<>(v);
        }

        public void run() {
            if (mCancelled) {
                return;
            }

            removeCallbacks(Blink.this);

            MongolEditText met = mView.get();

            if (met != null && met.isFocused()) {
                int st = met.getSelectionStart();
                int en = met.getSelectionEnd();

                if (st == en && st >= 0 && en >= 0) {
                    if (met.mLayout != null) {
                        met.invalidateCursorPath();
                    }

                    postAtTime(this, SystemClock.uptimeMillis() + BLINK);
                }
            }
        }

        void cancel() {
            if (!mCancelled) {
                removeCallbacks(Blink.this);
                mCancelled = true;
            }
        }

        void uncancel() {
            mCancelled = false;
        }
    }
}