package net.studymongolian.mongollibrary;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.view.GestureDetectorCompat;
import android.text.Editable;
import android.text.InputType;
import android.text.Selection;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;

import java.text.BreakIterator;

public class MongolEditText extends MongolTextView {

    private Paint mCursorPaint;
    //private long mShowCursor;
    //private Blink mBlink;
    private boolean mCursorVisible = true;
    private boolean mIsBlinkOn = true;
    private Handler mBlinkHandler;
    static final int BLINK = 500;
    static final int CURSOR_WIDTH = 2; // CONVERT TO DP
    private Path mCursorPath;
    private GestureDetector mDetector;
    private boolean mAllowSystemKeyboard = true;

    private int mSelectionHandle = SCROLLING_UNKNOWN;
    private static final int SCROLLING_UNKNOWN = 0;
    private static final int SCROLLING_START = 1;
    private static final int SCROLLING_END = 2;


    public MongolEditText(Context context) {
        this(context, null);
    }

    public MongolEditText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MongolEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        int textLength = super.getText().length();
        setSelection(textLength); // TODO why did I do this?

        mCursorPaint = new Paint();
        mCursorPaint.setColor(Color.RED);
        mCursorPaint.setStyle(Paint.Style.FILL);
        mCursorPaint.setAntiAlias(true);

        mCursorPath = new Path();

        // allow this view to receive input from keyboard
        setFocusable(true);
        setFocusableInTouchMode(true);

        // get notified of text changes
        mTextStorage.setOnChangeListener(new MongolTextStorage.OnChangeListener() {

            @Override
            public void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
                // TODO just update the layout from the start position rather than everything
                MongolEditText.super.mLayout.setText(mTextStorage.getGlyphText());
                invalidate();
                requestLayout();

                Log.i("TAG", "onTextChanged: ");
                startBlinking();
            }

            @Override
            public void onSpanChanged(Spanned buf, Object what, int oldStart, int newStart, int oldEnd, int newEnd) {
                Log.i("TAG", "onSpanChanged: ");
                // TODO only invalidate region affected by the span
                invalidate();
                // FIXME only need to request layout for metric affecting spans
                requestLayout();
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
                        if (start == end) {
                            if (start <= 0) return false;
                            mTextStorage.delete(start - 1, start);
                        } else {
                            // delete highlighted text
                            mTextStorage.delete(start, end);
                        }
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

        // init the handler for the blinking cursor
        mBlinkHandler = new Handler();

        // gestures
        mDetector = new GestureDetector(getContext(), new mListener());
    }

    @Override
    public InputConnection onCreateInputConnection(EditorInfo outAttrs) {

        //outAttrs.inputType = InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS; // TODO what are flags?
        //outAttrs.imeOptions = EditorInfo.IME_ACTION_DONE; // TODO is this right?

        //outAttrs.inputType = InputType.TYPE_CLASS_NUMBER;
        outAttrs.inputType = InputType.TYPE_CLASS_TEXT; // TODO allow user to set type class

        return new MetInputConnection(this, true);
    }


    public void showSystemKeyboard() {
        InputMethodManager imm = (InputMethodManager) getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(this, InputMethodManager.SHOW_FORCED);
    }

    @Override
    public boolean onCheckIsTextEditor() {
        return true;
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        if (focused) {
            startBlinking();
        } else {
            stopBlinking();
        }
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
    }

    @Override
    protected boolean getDefaultEditable() {
        return true;
    }

    class mListener extends GestureDetector.SimpleOnGestureListener {
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
            requestFocus();
            return true;
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
            startBlinking();

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
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {

            // if there is no selection then return
            int start = getSelectionStart();
            int end = getSelectionEnd();


            //int xDown = (int) e1.getX();
            //int yDown = (int) e1.getY();

            int xMove = (int) e2.getX();
            int yMove = (int) e2.getY();

            int moveOffset = getOffsetForPosition(xMove, yMove);
            if (mSelectionHandle == SCROLLING_START) {
                start = moveOffset;
            } else if (mSelectionHandle == SCROLLING_END) {
                end = moveOffset;
            }

            setSelection(start, end);

            // todo should end be forbidden from going before start?

            return super.onScroll(e1, e2, distanceX, distanceY);
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean result = mDetector.onTouchEvent(event);
        // <-- if result is false (event not detected) then add custom detection code here
        return result;
    }

    @Override
    public Editable getText() {
        return mTextStorage;
    }

    public void setSelection(int start, int stop) {
        Log.i("TAG", "setSelection: ");
        Selection.setSelection(getText(), start, stop);
    }

    public void setSelection(int index) {
        Selection.setSelection(getText(), index);
    }

    public void selectAll() {
        Selection.selectAll(getText());
    }

    public void extendSelection(int index) {
        Selection.extendSelection(getText(), index);
    }

    public int getSelectionStart() {
        // returns -1 if no selection
        return Selection.getSelectionStart(getText());
    }

    public int getSelectionEnd() {
        // returns -1 if no selection
        return Selection.getSelectionEnd(getText());
    }

    public boolean hasSelection() {
        final int selectionStart = getSelectionStart();
        final int selectionEnd = getSelectionEnd();

        return selectionStart >= 0 && selectionStart != selectionEnd;
    }

    String getSelectedText() {
        if (!hasSelection()) {
            return null;
        }

        final int start = getSelectionStart();
        final int end = getSelectionEnd();
        return String.valueOf(
                start > end ? mTextStorage.subSequence(end, start) : mTextStorage.subSequence(start, end));
    }


    @Override
    protected void onDraw(Canvas canvas) {
        Log.i("TAG", "onDraw: ");

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
        if (!mIsBlinkOn && start == end) {
            canvas.drawRect(getCursorPath(start), mCursorPaint);
        }
    }

    private Rect getCursorPath(int cursorLocation) {

        int glyphStart = mTextStorage.getGlyphIndexForUnicodeIndex(cursorLocation);
        int line = super.mLayout.getLineForOffset(glyphStart);
        int width = super.mLayout.getLineDescent(line) - super.mLayout.getLineAscent(line);
        int x = super.mLayout.getLineBottom(line) + getPaddingLeft();
        int y = (int) super.mLayout.getVertical(glyphStart) + getPaddingTop();

        return new Rect(x, y, x + width, y + CURSOR_WIDTH);
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
        int glyphStart = mTextStorage.getGlyphIndexForUnicodeIndex(start);
        int lineStart = super.mLayout.getLineForOffset(glyphStart);
        int widthStart = super.mLayout.getLineDescent(lineStart) - super.mLayout.getLineAscent(lineStart);
        int xStart = super.mLayout.getLineBottom(lineStart) + getPaddingLeft();
        int yStart = (int) super.mLayout.getVertical(glyphStart) + getPaddingTop();

        // end cursor
        int glyphEnd = mTextStorage.getGlyphIndexForUnicodeIndex(end);
        int lineEnd = super.mLayout.getLineForOffset(glyphEnd);
        int widthEnd = super.mLayout.getLineDescent(lineEnd) - super.mLayout.getLineAscent(lineEnd);
        int xEnd = super.mLayout.getLineBottom(lineEnd) + getPaddingLeft();
        int yEnd = (int) super.mLayout.getVertical(glyphEnd) + getPaddingTop();

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



    private boolean shouldBlink() {
        //Log.i("TAG", "shouldBlink: ");
        if (!mCursorVisible || !isFocused()) return false;

        final int start = getSelectionStart();
        if (start < 0) return false;

        final int end = getSelectionEnd();
        if (end < 0) return false;

        return start == end;
    }

    // FIXME do I need to cancel this when user leaves app? Check logging
    // alternate the view's background color
    Runnable mBlink = new Runnable() {
        @Override
        public void run() {

            mBlinkHandler.removeCallbacks(mBlink);

            if (shouldBlink()) {
                if (mLayout != null && !mLayout.getNeedsLineUpdate()) {
                    Log.i("TAG", "run: ");
                    MongolEditText.this.invalidateCursorPath();
                    //MongolEditText.this.invalidate();
                    mIsBlinkOn = !mIsBlinkOn;
                }

                mBlinkHandler.postDelayed(mBlink, BLINK);
            }
        }
    };

    // start the task
    void startBlinking() {
        mBlink.run();
    }

    // stop running the task, cancel any current code that is waiting to run
    void stopBlinking() {
        mBlinkHandler.removeCallbacks(mBlink);
    }


    private void invalidateCursorPath() {
        int start = getSelectionStart();
        if (start < 0) return;
        Rect cursorPath = getCursorPath(start);
        invalidate(cursorPath.left, cursorPath.top, cursorPath.right, cursorPath.bottom);
    }


    @Override
    public void onScreenStateChanged(int screenState) {
        switch (screenState) {
            case View.SCREEN_STATE_ON:
                startBlinking();
                break;
            case View.SCREEN_STATE_OFF:
                stopBlinking();
                break;
        }
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        startBlinking();
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopBlinking();
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
                    final Spannable sp = new SpannableStringBuilder(mTextStorage);

                    ss.text = sp;
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

        SavedState ss = (SavedState)state;
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

}
