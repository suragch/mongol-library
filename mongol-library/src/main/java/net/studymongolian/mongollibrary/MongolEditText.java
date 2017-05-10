package net.studymongolian.mongollibrary;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.Editable;
import android.text.Selection;
import android.text.SpannableStringBuilder;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

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
        setSelection(textLength);

        mCursorPaint = new Paint();
        mCursorPaint.setColor(Color.RED);
        mCursorPaint.setStyle(Paint.Style.FILL);
        mCursorPaint.setAntiAlias(true);



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

                break;
        }
        return true;
    }

    @Override
    public SpannableStringBuilder getText() {
        return (SpannableStringBuilder) super.getText();
    }

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

        // don't draw anything is there is no selection
        if (start < 0 || end < 0) return;

        if (start == end) {

            int glyphStart = mTextStorage.getGlyphIndexForUnicodeIndex(start);
            int line = super.mLayout.getLineForOffset(glyphStart);
            int width = super.mLayout.getLineDescent(line) - super.mLayout.getLineAscent(line);
            float x = super.mLayout.getLineBottom(line) + getPaddingLeft();
            float y = super.mLayout.getVertical(glyphStart) + getPaddingTop();

            canvas.drawRect(x, y, x + width, y + 10, mCursorPaint);
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
}
