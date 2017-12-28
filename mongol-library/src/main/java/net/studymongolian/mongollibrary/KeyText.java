package net.studymongolian.mongollibrary;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;

public class KeyText extends Key {

    private static final int SUBTEXT_INDENT = 5; // px

    private MongolCode renderer = MongolCode.INSTANCE;
    private TextPaint mTextPaint;
    private Rect mTextBounds;
    private Rect mSubTextBounds;
    private String mDisplayText;
    private String mDisplaySubText;
    private TextPaint mSubTextPaint;
    private int mKeyHeight;
    private int mKeyWidth;

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

        mDisplaySubText = "";
        mIsRotatedPrimaryText = true;
        mIsRotatedSubText = true;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mKeyHeight = getMeasuredHeight() - getPaddingTop() - getPaddingBottom();
        mKeyWidth = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
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
        if (TextUtils.isEmpty(mDisplaySubText)) return;

        // get text size
        mSubTextPaint.getTextBounds(mDisplaySubText, 0, mDisplaySubText.length(), mSubTextBounds);

        // automatically resize text that is too large
        final float widthProportion = MAX_CONTENT_PROPORTION * mKeyWidth / mSubTextBounds.height();
        final float heightProportion = MAX_CONTENT_PROPORTION * mKeyHeight / mSubTextBounds.width();
        float proportion = Math.min(heightProportion, widthProportion);
        if (proportion < 1) {
            mSubTextPaint.setTextSize(mSubTextPaint.getTextSize() * proportion);
            mSubTextPaint.getTextBounds(mDisplaySubText, 0, mDisplaySubText.length(), mSubTextBounds);
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
        canvas.drawText(mDisplaySubText, 0, 0, mSubTextPaint);
        canvas.restore();
    }

    private void drawRotatedPrimaryText(Canvas canvas) {
        if (TextUtils.isEmpty(mDisplayText)) return;

        // metrics
        mTextPaint.getTextBounds(mDisplayText, 0, mDisplayText.length(), mTextBounds);

        // automatically resize text that is too large
        final float widthProportion = MAX_CONTENT_PROPORTION * mKeyWidth / mTextBounds.height();
        final float heightProportion = MAX_CONTENT_PROPORTION * mKeyHeight / mTextBounds.width();
        float proportion = Math.min(heightProportion, widthProportion);
        if (proportion < 1) {
            mTextPaint.setTextSize(mTextPaint.getTextSize() * proportion);
            mTextPaint.getTextBounds(mDisplayText, 0, mDisplayText.length(), mTextBounds);
        }

        // location
        int dx = getPaddingTop() + (mKeyHeight - mTextBounds.right) / 2;
        int dy = -getPaddingLeft() - mTextBounds.bottom - (mKeyWidth - mTextBounds.height()) / 2;

        // draw
        canvas.save();
        canvas.rotate(90);
        canvas.translate(dx, dy);
        canvas.drawText(mDisplayText, 0, 0, mTextPaint);
        canvas.restore();
    }

    private void drawNonRotatedSubText(Canvas canvas) {
        if (TextUtils.isEmpty(mDisplaySubText)) return;

        // get text size
        mSubTextPaint.getTextBounds(mDisplaySubText, 0, mDisplaySubText.length(), mSubTextBounds);

        // resize text if too big
        final float widthProportion = MAX_CONTENT_PROPORTION * mKeyWidth / mSubTextBounds.width();
        final float heightProportion = MAX_CONTENT_PROPORTION * mKeyHeight / mSubTextBounds.height();
        float proportion = Math.min(heightProportion, widthProportion);
        if (proportion < 1) {
            mSubTextPaint.setTextSize(mSubTextPaint.getTextSize() * proportion);
            mSubTextPaint.getTextBounds(mDisplaySubText, 0, mDisplaySubText.length(), mSubTextBounds);
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
        canvas.drawText(mDisplaySubText, 0, 0, mSubTextPaint);
        canvas.restore();
    }

    private void drawNonRotatedPrimaryText(Canvas canvas) {
        if (TextUtils.isEmpty(mDisplayText)) return;

        // metrics
        mTextPaint.getTextBounds(mDisplayText, 0, mDisplayText.length(), mTextBounds);
        Paint.FontMetricsInt fm = mTextPaint.getFontMetricsInt();
        int textHeight = fm.descent - fm.ascent;

        // resize text if too big
        final float widthProportion = MAX_CONTENT_PROPORTION * mKeyWidth / mTextBounds.width();
        final float heightProportion = MAX_CONTENT_PROPORTION * mKeyHeight / textHeight;
        float proportion = Math.min(heightProportion, widthProportion);
        if (proportion < 1) {
            mTextPaint.setTextSize(mTextPaint.getTextSize() * proportion);
            mTextPaint.getTextBounds(mDisplayText, 0, mDisplayText.length(), mTextBounds);
            fm = mTextPaint.getFontMetricsInt();
            textHeight = fm.descent - fm.ascent;
        }

        // location
        int dx = getPaddingLeft() + (mKeyWidth - mTextBounds.width()) / 2;
        int dy = getPaddingTop() + (mKeyHeight + textHeight) / 2 - fm.descent;

        // draw text
        canvas.save();
        canvas.translate(dx, dy);
        canvas.drawText(mDisplayText, 0, 0, mTextPaint);
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

    public void setRotatedPrimaryText(boolean isRotated) {
        this.mIsRotatedPrimaryText = isRotated;
        invalidate();
    }

    public void setRotatedSubText(boolean isRotated) {
        this.mIsRotatedSubText = isRotated;
        invalidate();
    }
}
