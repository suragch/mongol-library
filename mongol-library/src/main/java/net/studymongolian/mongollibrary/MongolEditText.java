package net.studymongolian.mongollibrary;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputType;
import android.text.Selection;
import android.text.SpannableStringBuilder;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;

public class MongolEditText extends MongolTextView {

    Paint mCursorPaint;

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

        // allow system keyboard to show on touch
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
                    } else if (keyCode == KeyEvent.KEYCODE_ENTER) {     // key enter

                        String text = "\n";
                        if (start != end) {
                            mTextStorage.delete(start, end);
                        }
                        mTextStorage.insert(start, text);
                    }
                    return true;
                }
                return false;
            }
        });

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

//    @Override
//    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
//        super.onFocusChanged(focused, direction, previouslyFocusedRect);
//        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
//        if (focused) {
//            imm.showSoftInput(this, 0);
//        } else {
//            imm.hideSoftInputFromWindow(getWindowToken(), 0);
//        }
//    }

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

            int glyphStart = mTextStorage.getGlyphIndexForUnicodeIndex(start);
            int line = super.mLayout.getLineForOffset(glyphStart);
            int width = super.mLayout.getLineDescent(line) - super.mLayout.getLineAscent(line);
            float x = super.mLayout.getLineBottom(line) + getPaddingLeft();
            float y = super.mLayout.getVertical(glyphStart) + getPaddingTop();

            canvas.drawRect(x, y, x + width, y + 2, mCursorPaint);
        } else {
            // TODO draw highlight

        }



    }

    // XXX adding the Editable methods here rather than returning
    // an Editable. This makes it easier to control the layout. But
    // it deviates from the standard EditText usage so should this
    // be changed?

    public void insertText(CharSequence text) {

        int start = getSelectionStart();
        int end = getSelectionEnd();

        // check if there is a selection
        if (start < 0 || end < 0) return;

        if (start != end) {
            mTextStorage.delete(start, end);
        }
        mTextStorage.insert(start, text);
        super.mLayout.setText(mTextStorage.getGlyphText());
        invalidate();
        requestLayout();
    }

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

    // allow the input connection to notify of text commits
    void textChanged() {
        invalidate();
        requestLayout();
    }

//    @Override
//    public void onFocusChange(View v, boolean hasFocus) {
//        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
//        if (hasFocus) {
//            imm.showSoftInput(v, 0);
//        } else {
//            imm.hideSoftInputFromWindow(getWindowToken(), 0);
//        }
//    }
}
