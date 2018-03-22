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

    private static final int SUBTEXT_INDENT = 5; // px

    private String mPrimaryText;
    private String mPrimaryTextDisplay;
    private String mSubTextDisplay;

    private MongolCode renderer = MongolCode.INSTANCE;
    private TextPaint mTextPaint;
    private Rect mTextBounds;
    private Rect mSubTextBounds;
    private TextPaint mSubTextPaint;

    protected boolean mIsRotatedPrimaryText;
    protected boolean mIsRotatedSubText;

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

        mSubTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mSubTextPaint.setTextSize(90);
        mSubTextBounds = new Rect();

        mSubTextDisplay = "";
        mIsRotatedPrimaryText = true;
        mIsRotatedSubText = true;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mIsRotatedSubText) {
            drawRotatedSubText(canvas);
        } else {
            drawNonRotatedSubText(canvas);
        }

        if (mIsRotatedPrimaryText) {
            drawRotatedPrimaryText(canvas);
        } else {
            drawNonRotatedPrimaryText(canvas);
        }
    }

    private void drawRotatedSubText(Canvas canvas) {
        String text = mSubTextDisplay;
        if (TextUtils.isEmpty(text)) return;

        // get text size
        mSubTextPaint.getTextBounds(text, 0, text.length(), mSubTextBounds);

        // automatically resize text that is too large
        final float widthProportion = MAX_CONTENT_PROPORTION * mKeyWidth / mSubTextBounds.height();
        final float heightProportion = MAX_CONTENT_PROPORTION * mKeyHeight / mSubTextBounds.width();
        float proportion = Math.min(heightProportion, widthProportion);
        if (proportion < 1) {
            mSubTextPaint.setTextSize(mSubTextPaint.getTextSize() * proportion);
            mSubTextPaint.getTextBounds(text, 0, text.length(), mSubTextBounds);
        }

        // make sure a large border radius doesn't overlap the subtext
        float indent = SUBTEXT_INDENT;
        float radiusAdjustment = (float) (mBorderRadius * ( 1 - (1 / Math.sqrt(2))));
        indent += radiusAdjustment;

        int dx = getPaddingTop() - mSubTextBounds.left        // align top edge
                + mKeyHeight - mSubTextBounds.width()         // align bottom edge
                - (int) indent;                               // move a little up
        int dy = - getPaddingLeft() - mSubTextBounds.bottom   // align left edge
                - mKeyWidth + mSubTextBounds.height()         // align right edge
                + (int) indent;                               // move a little left

        // draw text
        canvas.save();
        canvas.rotate(90);
        canvas.translate(dx, dy);
        canvas.drawText(text, 0, 0, mSubTextPaint);
        canvas.restore();
    }

    private void drawRotatedPrimaryText(Canvas canvas) {
        String text = mPrimaryTextDisplay;
        if (TextUtils.isEmpty(text)) return;

        // metrics
        mTextPaint.getTextBounds(text, 0, text.length(), mTextBounds);

        // automatically resize text that is too large
        final float widthProportion = MAX_CONTENT_PROPORTION * mKeyWidth / mTextBounds.height();
        final float heightProportion = MAX_CONTENT_PROPORTION * mKeyHeight / mTextBounds.width();
        float proportion = Math.min(heightProportion, widthProportion);
        if (proportion < 1) {
            mTextPaint.setTextSize(mTextPaint.getTextSize() * proportion);
            mTextPaint.getTextBounds(text, 0, text.length(), mTextBounds);
        }

        // location
        int dx = getPaddingTop() + (mKeyHeight - mTextBounds.right) / 2;
        int dy = -getPaddingLeft() - mTextBounds.bottom - (mKeyWidth - mTextBounds.height()) / 2;

        // draw
        canvas.save();
        canvas.rotate(90);
        canvas.translate(dx, dy);
        canvas.drawText(text, 0, 0, mTextPaint);
        canvas.restore();
    }

    private void drawNonRotatedSubText(Canvas canvas) {
        String text = mSubTextDisplay;
        if (TextUtils.isEmpty(text)) return;

        // get text size
        mSubTextPaint.getTextBounds(text, 0, text.length(), mSubTextBounds);

        // resize text if too big
        final float widthProportion = MAX_CONTENT_PROPORTION * mKeyWidth / mSubTextBounds.width();
        final float heightProportion = MAX_CONTENT_PROPORTION * mKeyHeight / mSubTextBounds.height();
        float proportion = Math.min(heightProportion, widthProportion);
        if (proportion < 1) {
            mSubTextPaint.setTextSize(mSubTextPaint.getTextSize() * proportion);
            mSubTextPaint.getTextBounds(text, 0, text.length(), mSubTextBounds);
        }

        // make sure a large border radius doesn't overlap the subtext
        float indent = SUBTEXT_INDENT;
        float radiusAdjustment = (float) (mBorderRadius * ( 1 - (1 / Math.sqrt(2))));
        indent += radiusAdjustment;

        int dx = getPaddingLeft() - mSubTextBounds.left      // align left edge
                + mKeyWidth - mSubTextBounds.width()         // align right edge
                - (int) indent;                              // move a little left
        int dy = getPaddingTop() - mSubTextBounds.top        // align top edge
                + mKeyHeight - mSubTextBounds.height()       // align bottom edge
                - (int) indent;                              // move a little up

        // draw text
        canvas.save();
        canvas.translate(dx, dy);
        canvas.drawText(text, 0, 0, mSubTextPaint);
        canvas.restore();
    }

    private void drawNonRotatedPrimaryText(Canvas canvas) {
        String text = mPrimaryTextDisplay;
        if (TextUtils.isEmpty(text)) return;

        // metrics
        mTextPaint.getTextBounds(text, 0, text.length(), mTextBounds);
        Paint.FontMetricsInt fm = mTextPaint.getFontMetricsInt();
        int textHeight = fm.descent - fm.ascent;

        // resize text if too big
        final float widthProportion = MAX_CONTENT_PROPORTION * mKeyWidth / mTextBounds.width();
        final float heightProportion = MAX_CONTENT_PROPORTION * mKeyHeight / textHeight;
        float proportion = Math.min(heightProportion, widthProportion);
        if (proportion < 1) {
            mTextPaint.setTextSize(mTextPaint.getTextSize() * proportion);
            mTextPaint.getTextBounds(text, 0, text.length(), mTextBounds);
            fm = mTextPaint.getFontMetricsInt();
            textHeight = fm.descent - fm.ascent;
        }

        // location
        int dx = getPaddingLeft() + (mKeyWidth - mTextBounds.width()) / 2;
        int dy = getPaddingTop() + (mKeyHeight + textHeight) / 2 - fm.descent;

        // draw text
        canvas.save();
        canvas.translate(dx, dy);
        canvas.drawText(text, 0, 0, mTextPaint);
        canvas.restore();
    }

    @Override
    protected void onActionUp(int xPosition) {
        if (getIsShowingPopup())
            finishPopup(xPosition);
        else if (mPrimaryText != null)
            sendTextToKeyboard(mPrimaryText);
    }

    public void setText(char text) {
        setText(String.valueOf(text));
    }

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
        this.mPrimaryText = inputValue;
        this.mPrimaryTextDisplay = renderer.unicodeToMenksoft(displayText);
        invalidate();
    }

    public void setSubText(String text) {
        this.mSubTextDisplay = renderer.unicodeToMenksoft(text);
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

    public void setTextSize(float sizePx) {
        mTextPaint.setTextSize(sizePx);
        invalidate();
    }

    public void setTextColor(int textColor) {
        mTextPaint.setColor(textColor);
        invalidate();
    }

    public void setSubTextSize(float subTextSize) {
        mSubTextPaint.setTextSize(subTextSize);
        invalidate();
    }

    public void setSubTextColor(int subTextColor) {
        mSubTextPaint.setColor(subTextColor);
        invalidate();
    }

    public void setRotatedPrimaryText(boolean isRotated) {
        this.mIsRotatedPrimaryText = isRotated;
        invalidate();
    }

    public void setRotatedSubText(boolean isRotated) {
        this.mIsRotatedSubText = isRotated;
        invalidate();
    }

}
