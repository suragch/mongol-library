package net.studymongolian.mongollibrary;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.v4.view.MotionEventCompat;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;


// TODO make class private
public class KeyText extends Key {

    private static final String DEBUG_TAG = "TAG";

    private MongolCode renderer = MongolCode.INSTANCE;
    private TextPaint mTextPaint;
    private Rect mTextBounds;
    private String mDisplayText;

    KeyText(Context context) {
        this(context, null);
    }

    KeyText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    KeyText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextSize(90);
        mTextBounds = new Rect();
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // calculate position for centered text
        canvas.rotate(90);
        mTextPaint.getTextBounds(mDisplayText, 0, mDisplayText.length(), mTextBounds);
        int keyHeight = getMeasuredHeight() - getPaddingTop() - getPaddingBottom();
        int keyWidth = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
        float x = getPaddingTop() + (keyHeight - mTextBounds.right) / 2;
        float y = -getPaddingLeft() - mTextBounds.bottom - (keyWidth - mTextBounds.height()) / 2;

        // automatically resize text that is too large
        int threshold = keyHeight * 8 / 10;
        if (mTextBounds.width() > threshold) {
            float proportion = 0.8f * keyHeight / mTextBounds.width();
            mTextPaint.setTextSize(mTextPaint.getTextSize() * proportion);
            x += mTextBounds.width() * (1 - proportion) / 2;
            y -= mTextBounds.height() * (1 - proportion) / 2;
        }

        // draw text
        canvas.drawText(mDisplayText, x, y, mTextPaint);

    }



    public void setText(String text) {
        this.mDisplayText = renderer.unicodeToMenksoft(text);
        invalidate();
    }

    public void setText(char text) {
        setText(String.valueOf(text));
    }

    public void setTypeFace(Typeface typeface) {
        mTextPaint.setTypeface(typeface);
        invalidate();
    }

    public void setTextSize(float sizeSP) {
        float sizePx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                sizeSP, getResources().getDisplayMetrics());
        mTextPaint.setTextSize(sizePx);
        invalidate();
    }

    public void setTextColor(int textColor) {
        mTextPaint.setColor(textColor);
        invalidate();
    }

}
