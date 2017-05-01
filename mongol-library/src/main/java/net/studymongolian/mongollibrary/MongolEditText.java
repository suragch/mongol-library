package net.studymongolian.mongollibrary;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.Editable;
import android.util.AttributeSet;

public class MongolEditText extends MongolTextView {

    // This is a text offset based on the unicode (not glyph) position
    int mCursorOffset = 0;
    Paint mCursorPaint;

    public MongolEditText(Context context) {
        this(context, null);
        init();
    }

    public MongolEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mCursorOffset = super.getText().length();

        mCursorPaint = new Paint();
        mCursorPaint.setColor(Color.RED);
        mCursorPaint.setStyle(Paint.Style.FILL);
        mCursorPaint.setAntiAlias(true);

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

        int line = super.mLayout.getLineForOffset(mCursorOffset);
        int width = super.mLayout.getLineDescent(line) - super.mLayout.getLineAscent(line);
        float x = super.mLayout.getLineBottom(line) + getPaddingLeft();
        float y = super.mLayout.getVertical(mCursorOffset) + getPaddingTop();

        canvas.drawRect(x, y, x + width, y + 10, mCursorPaint);


    }

    // XXX adding the Editable methods here rather than returning
    // an Editable. This makes it easier to control the layout. But
    // it deviates from the standard EditText usage so should this
    // be changed?

    public void insertText(CharSequence text) {
        // TODO handle selection
        super.mTextStorage.insert(mCursorOffset, text);
        super.mLayout.setText(mTextStorage.getGlyphText());
        mCursorOffset += text.length();
        invalidate();
        requestLayout();
    }

    public void backspace() {
        // TODO handle selection
        if (mCursorOffset <= 0) return;
        super.mTextStorage.delete(mCursorOffset - 1, mCursorOffset);
        super.mLayout.setText(mTextStorage.getGlyphText());
        mCursorOffset--;
        invalidate();
        requestLayout();
    }
}
