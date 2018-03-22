package net.studymongolian.mongollibrary;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;

public class KeyText extends Key {

    // Sometimes the display value may be different than the key input value
    private String mPrimaryTextDisplay;

    private TextPaint mTextPaint;
    private Rect mTextBounds;

    protected boolean mIsRotatedPrimaryText;


    public KeyText(Context context) {
        super(context);
        init();
    }

    public KeyText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public KeyText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextSize(90);
        mTextBounds = new Rect();
        mIsRotatedPrimaryText = true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawPrimaryText(canvas);
    }

    private void drawPrimaryText(Canvas canvas) {
        if (TextUtils.isEmpty(mPrimaryTextDisplay)) return;

        if (mIsRotatedPrimaryText) {
            drawRotatedPrimaryText(canvas);
        } else {
            drawNonRotatedPrimaryText(canvas);
        }
    }

    private void drawRotatedPrimaryText(Canvas canvas) {

        // metrics
        mTextPaint.getTextBounds(mPrimaryTextDisplay, 0, mPrimaryTextDisplay.length(), mTextBounds);

        // automatically resize text that is too large
        final float widthProportion = MAX_CONTENT_PROPORTION * mKeyWidth / mTextBounds.height();
        final float heightProportion = MAX_CONTENT_PROPORTION * mKeyHeight / mTextBounds.width();
        float proportion = Math.min(heightProportion, widthProportion);
        if (proportion < 1) {
            mTextPaint.setTextSize(mTextPaint.getTextSize() * proportion);
            mTextPaint.getTextBounds(mPrimaryTextDisplay, 0, mPrimaryTextDisplay.length(), mTextBounds);
        }

        // location
        int dx = getPaddingTop() + (mKeyHeight - mTextBounds.right) / 2;
        int dy = -getPaddingLeft() - mTextBounds.bottom - (mKeyWidth - mTextBounds.height()) / 2;

        // draw
        canvas.save();
        canvas.rotate(90);
        canvas.translate(dx, dy);
        canvas.drawText(mPrimaryTextDisplay, 0, 0, mTextPaint);
        canvas.restore();
    }

    private void drawNonRotatedPrimaryText(Canvas canvas) {

        // metrics
        mTextPaint.getTextBounds(mPrimaryTextDisplay, 0, mPrimaryTextDisplay.length(), mTextBounds);
        Paint.FontMetricsInt fm = mTextPaint.getFontMetricsInt();
        int textHeight = fm.descent - fm.ascent;

        // resize text if too big
        final float widthProportion = MAX_CONTENT_PROPORTION * mKeyWidth / mTextBounds.width();
        final float heightProportion = MAX_CONTENT_PROPORTION * mKeyHeight / textHeight;
        float proportion = Math.min(heightProportion, widthProportion);
        if (proportion < 1) {
            mTextPaint.setTextSize(mTextPaint.getTextSize() * proportion);
            mTextPaint.getTextBounds(mPrimaryTextDisplay, 0, mPrimaryTextDisplay.length(), mTextBounds);
            fm = mTextPaint.getFontMetricsInt();
            textHeight = fm.descent - fm.ascent;
        }

        // location
        int dx = getPaddingLeft() + (mKeyWidth - mTextBounds.width()) / 2;
        int dy = getPaddingTop() + (mKeyHeight + textHeight) / 2 - fm.descent;

        // draw text
        canvas.save();
        canvas.translate(dx, dy);
        canvas.drawText(mPrimaryTextDisplay, 0, 0, mTextPaint);
        canvas.restore();
    }

    @Override
    public void setText(char text) {
        setText(String.valueOf(text));
    }

    @Override
    public void setText(String text) {
        setText(text, text);
    }

    public void setText(char inputValue, char displayText) {
        setText(String.valueOf(inputValue), String.valueOf(displayText));
    }

    /**
     * @param inputValue the unicode value that will be  used as an input value when
     *                the key is pressed.
     * @param displayText the value to display if different than the unicode value
     */
    public void setText(String inputValue, String displayText) {
        this.mKeyInputText = inputValue;
        this.mPrimaryTextDisplay = renderer.unicodeToMenksoft(displayText);
        invalidate();
    }

    @Override
    public void setTypeFace(Typeface typeface) {
        super.setTypeFace(typeface);
        mTextPaint.setTypeface(typeface);
        invalidate();
    }

    public void setTextSize(float sizePx) {
        mTextPaint.setTextSize(sizePx);
        invalidate();
    }

    public void setTextColor(int textColor) {
        mTextPaint.setColor(textColor);
        invalidate();
    }

    public void setIsRotatedPrimaryText(boolean isRotated) {
        this.mIsRotatedPrimaryText = isRotated;
        invalidate();
    }
}
