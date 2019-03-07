package net.studymongolian.mongollibrary;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

public class RotatedTextView extends AppCompatTextView {

    public RotatedTextView(Context context) {
        super(context);
    }

    public RotatedTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RotatedTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // swap the height and width
        super.onMeasure(heightMeasureSpec, widthMeasureSpec);
        setMeasuredDimension(getMeasuredHeight(), getMeasuredWidth());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();

        // flip and rotate the canvas
        canvas.translate(getWidth(), 0);
        canvas.rotate(90);
        canvas.translate(0, getWidth());
        canvas.scale(1, -1);
        canvas.translate(getCompoundPaddingLeft(), getExtendedPaddingTop());

        getLayout().draw(canvas);

        canvas.restore();
    }


    public void setTextWithRenderedUnicode(CharSequence unicodeText) {
        MongolCode renderer = MongolCode.INSTANCE;
        String renderedText = renderer.unicodeToMenksoft(unicodeText.toString());
        this.setText(renderedText);
    }

}