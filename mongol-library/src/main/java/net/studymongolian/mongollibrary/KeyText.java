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

public class KeyText extends Key {

    private static final String DEBUG_TAG = "TAG";
    private static final int SUBTEXT_INDENT = 5; // px

    private MongolCode renderer = MongolCode.INSTANCE;
    private TextPaint mTextPaint;
    private Rect mTextBounds;
    private Rect mSubTextBounds;
    private String mDisplayText;
    private String mDisplaySubText;
    private TextPaint mSubTextPaint;

    public KeyText(Context context) {
        this(context, null);
    }

    public KeyText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public KeyText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextSize(90);
        mTextBounds = new Rect();

        mSubTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mSubTextPaint.setTextSize(90);
        mSubTextBounds = new Rect();

        mDisplaySubText = "";
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        final int keyHeight = getMeasuredHeight() - getPaddingTop() - getPaddingBottom();
        final int keyWidth = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
        final float threshold = 0.8f * keyHeight;
        float x;
        float y;

        canvas.save();
        canvas.rotate(90);

        // draw the subtext on the bottom right
        if (mDisplaySubText.length() > 0) {
            float indent = SUBTEXT_INDENT;
            mSubTextPaint.getTextBounds(mDisplaySubText, 0, mDisplaySubText.length(), mSubTextBounds);
            if (mSubTextBounds.width() > threshold) {
                // automatically resize text that is too large
                float proportion = threshold / mSubTextBounds.width();
                mSubTextPaint.setTextSize(mSubTextPaint.getTextSize() * proportion);
                mSubTextPaint.getTextBounds(mDisplaySubText, 0, mDisplaySubText.length(), mSubTextBounds);
                indent = 0;
            }
            x = keyHeight - mSubTextBounds.width();
            y = - keyWidth - getPaddingRight() + mSubTextBounds.height();
            // make sure a large border radius doesn't overlap the subtext
            float radiusAdjustment = (float) (mBorderRadius * ( 1 - (1 / Math.sqrt(2))));
            indent += radiusAdjustment;
            canvas.drawText(mDisplaySubText, x - indent, y + indent, mSubTextPaint);
        }

        // draw the main text in the center
        mTextPaint.getTextBounds(mDisplayText, 0, mDisplayText.length(), mTextBounds);
        if (mTextBounds.width() > threshold) {
            // automatically resize text that is too large
            float proportion = threshold / mTextBounds.width();
            mTextPaint.setTextSize(mTextPaint.getTextSize() * proportion);
            mTextPaint.getTextBounds(mDisplayText, 0, mDisplayText.length(), mTextBounds);
        }
        x = getPaddingTop() + (keyHeight - mTextBounds.right) / 2;
        y = -getPaddingLeft() - mTextBounds.bottom - (keyWidth - mTextBounds.height()) / 2;
        canvas.drawText(mDisplayText, x, y, mTextPaint);

        canvas.restore();

    }



    public void setText(String text) {
        this.mDisplayText = renderer.unicodeToMenksoft(text);
        invalidate();
    }

    public void setText(char text) {
        setText(String.valueOf(text));
    }

    public void setSubText(String text) {
        this.mDisplaySubText = renderer.unicodeToMenksoft(text);
        invalidate();
    }

    public void setSubText(char text) {
        setSubText(String.valueOf(text));
    }

    public void setTypeFace(Typeface typeface) {
        mTextPaint.setTypeface(typeface);
        mSubTextPaint.setTypeface(typeface);
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

    public void setSubTextSize(float subTextSize) {
        float sizePx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                subTextSize, getResources().getDisplayMetrics());
        mSubTextPaint.setTextSize(sizePx);
        invalidate();
    }

    public void setSubTextColor(int subTextColor) {
        mSubTextPaint.setColor(subTextColor);
        invalidate();
    }
}
