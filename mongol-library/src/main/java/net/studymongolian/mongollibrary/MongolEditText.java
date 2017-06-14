package net.studymongolian.mongollibrary;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputType;
import android.text.Selection;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;

public class MongolEditText extends MongolTextView {

    private Paint mCursorPaint;
    //private long mShowCursor;
    //private Blink mBlink;
    private boolean mCursorVisible = true;
    private boolean mIsBlinkOn = true;
    private Handler mBlinkHandler;
    static final int BLINK = 500;
    static final int CURSOR_WIDTH = 2; // CONVERT TO DP

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
            }

            @Override
            public void onSpanChanged() {
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
                    } else if (keyCode == KeyEvent.KEYCODE_ENTER) {     // key enter

                        String text = "\n";
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

    }

    @Override
    public InputConnection onCreateInputConnection(EditorInfo outAttrs) {

        //outAttrs.inputType = InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS;
        //outAttrs.imeOptions = EditorInfo.IME_ACTION_DONE; // TODO is this right?

        //outAttrs.inputType = InputType.TYPE_CLASS_NUMBER;
        outAttrs.inputType = InputType.TYPE_CLASS_TEXT;
        //outAttrs.imeOptions = EditorInfo.IME_ACTION_DONE;

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

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int x = (int) event.getX();
        int y = (int) event.getY();
        int eventaction = event.getAction();

        switch (eventaction) {
            case MotionEvent.ACTION_DOWN:
                Log.i("TAG", "ACTION_DOWN AT COORDS "+"X: "+x+" Y: "+y);

                // TODO disable scrolling for some touches
                // getParent().requestDisallowInterceptTouchEvent(true);



                break;

            case MotionEvent.ACTION_MOVE:
                Log.i("TAG", "MOVE "+"X: "+x+" Y: "+y);
                break;

            case MotionEvent.ACTION_UP:
                Log.i("TAG", "ACTION_UP "+"X: "+x+" Y: "+y);

                // find the position
                int offset = getOffsetForPosition(x, y);

                // set the selection
                setSelection(offset);
                invalidate();

                showSystemKeyboard();
                //makeBlink();
                startBlinking();

                break;
        }
        return true;
    }

    @Override
    public Editable getText() {
        return mTextStorage;
    }

//    public Editable getTextStorage() {
//        return mTextStorage;
//    }

    public void setSelection(int start, int stop) {
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
        super.onDraw(canvas);

        Log.i("TAG", "onDraw: ");

        if (!mIsBlinkOn) return;

        // XXX when selecting text add the highlight to the glyph text so that
        // any original highlight spans on the unicode text are not lost.

        // draw the cursor (if there is no selection)
        // get the padding
        // get the line for the cursor offset
        // get the x for the line left
        // get the line width
        // get the y for the positing in the line
        // get the default cursor thickness
        // draw a rectangle on the canvas

        int start = getSelectionStart();
        int end = getSelectionEnd();

        // don't draw anything if there is no selection
        if (start < 0 || end < 0) return;

        if (start == end) {

            //int glyphStart = mTextStorage.getGlyphIndexForUnicodeIndex(start);
            //int line = super.mLayout.getLineForOffset(glyphStart);
            //int width = super.mLayout.getLineDescent(line) - super.mLayout.getLineAscent(line);
            //float x = super.mLayout.getLineBottom(line) + getPaddingLeft();
            //float y = super.mLayout.getVertical(glyphStart) + getPaddingTop();

            //canvas.drawRect(x, y, x + width, y + 2, mCursorPaint);

            canvas.drawRect(getCursorPath(start), mCursorPaint);
        } else {
            // TODO draw highlight

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

    // XXX adding the Editable methods here rather than returning
    // an Editable. This makes it easier to control the layout. But
    // it deviates from the standard EditText usage so should this
    // be changed?



    public void backspace() {

        int start = getSelectionStart();
        int end = getSelectionEnd();

        // check if there is a selection
        if (start < 0 || end < 0) return;

        if (start == end) {
            if (start <= 0) return;
            super.mTextStorage.delete(start - 1, start);
        } else {
            // delete highlighted text
            super.mTextStorage.delete(start, end);
        }
        super.mLayout.setText(mTextStorage.getGlyphText());
        invalidate();
        requestLayout();
    }

    private boolean shouldBlink() {
        if (!mCursorVisible || !isFocused()) return false;

        final int start = getSelectionStart();
        if (start < 0) return false;

        final int end = getSelectionEnd();
        if (end < 0) return false;

        return start == end;
    }

    // alternate the view's background color
    Runnable mBlink = new Runnable() {
        @Override
        public void run() {

            mBlinkHandler.removeCallbacks(mBlink);

            if (shouldBlink()) {
                if (mLayout != null && !mLayout.getNeedsLineUpdate()) {
                    MongolEditText.this.invalidateCursorPath();
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

//    void makeBlink() {
//        if (shouldBlink()) {
//            mShowCursor = SystemClock.uptimeMillis();
//            if (mBlink == null) mBlink = new Blink();
//            this.removeCallbacks(mBlink);
//            this.postDelayed(mBlink, BLINK);
//        } else {
//            if (mBlink != null) this.removeCallbacks(mBlink);
//        }
//    }

//    private class Blink implements Runnable {
//        private boolean mCancelled;
//
//        public void run() {
//            if (mCancelled) {
//                return;
//            }
//
//            MongolEditText.this.removeCallbacks(this);
//
//            if (shouldBlink()) {
//                if (mLayout != null) {
//                    MongolEditText.this.invalidateCursorPath();
//                }
//
//                MongolEditText.this.postDelayed(this, BLINK);
//            }
//        }
//
//        void cancel() {
//            if (!mCancelled) {
//                MongolEditText.this.removeCallbacks(this);
//                mCancelled = true;
//            }
//        }
//
//        void uncancel() {
//            mCancelled = false;
//        }
//    }

    private void invalidateCursorPath() {
        int start = getSelectionStart();
        if (start < 0) return;
        Rect cursorPath = getCursorPath(start);
        invalidate(cursorPath.left, cursorPath.top, cursorPath.right, cursorPath.bottom);
    }

//    private void suspendBlink() {
//        if (mBlink != null) {
//            mBlink.cancel();
//        }
//    }
//
//    private void resumeBlink() {
//        if (mBlink != null) {
//            mBlink.uncancel();
//            makeBlink();
//        }
//    }

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
