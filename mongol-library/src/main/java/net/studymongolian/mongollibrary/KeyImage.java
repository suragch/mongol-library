package net.studymongolian.mongollibrary;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import static android.content.ContentValues.TAG;

public class KeyImage extends Key {

    private String mPrimaryText;

    private TextPaint mImagePaint;
    private Bitmap mImage;
    private Bitmap mImageScaled;
    private boolean mNeedToScaleImage = false;

    // use a light image for a DARK theme and vice-versa
    public enum Theme {
        DARK,
        LIGHT;
    }

    public KeyImage(Context context) {
        super(context);
        initPaints();
    }

    public KeyImage(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaints();
    }

    public KeyImage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaints();
    }

    private void initPaints() {

        mImagePaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mImagePaint.setFilterBitmap(true);
        mImagePaint.setDither(true);

    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        int keyWidth = w - getPaddingLeft() - getPaddingRight();
        int keyHeight = h - getPaddingTop() - getPaddingBottom();
        float adjustedMinWidth = keyWidth * MAX_CONTENT_PROPORTION;
        float adjustedMinHeight = keyHeight * MAX_CONTENT_PROPORTION;

        float wDiff =  adjustedMinWidth - mImage.getWidth();
        float hDiff =  adjustedMinHeight - mImage.getHeight();

        mNeedToScaleImage = wDiff < 0 || hDiff < 0;
        if (mNeedToScaleImage) {

            //float scale;
            if (wDiff < hDiff) {
                int newHeight = (int) (mImage.getHeight() * adjustedMinWidth / mImage.getWidth());
                mImageScaled = Bitmap.createScaledBitmap(mImage, (int) adjustedMinWidth, newHeight, true);
            } else {
                int newWidth = (int) (mImage.getWidth() * adjustedMinHeight / mImage.getHeight());
                mImageScaled = Bitmap.createScaledBitmap(mImage, newWidth, (int) (adjustedMinHeight), true);
            }
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // calculate position for centered image
        int keyHeight = getMeasuredHeight() - getPaddingTop() - getPaddingBottom();
        int keyWidth = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();

        // automatically resize text that is too large
        if (mNeedToScaleImage) {
            float x = getPaddingLeft() + (keyWidth - mImageScaled.getWidth()) / 2;
            float y = getPaddingTop() + (keyHeight - mImageScaled.getHeight()) / 2;
            canvas.drawBitmap(mImageScaled, x, y, mImagePaint);
        } else {
            float x = getPaddingLeft() + (keyWidth - mImage.getWidth()) / 2;
            float y = getPaddingTop() + (keyHeight - mImage.getHeight()) / 2;
            canvas.drawBitmap(mImage, x, y, mImagePaint);
        }

    }

    public void setImage (Bitmap bitmap) {
        mImage = bitmap;
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
        mPrimaryText = text;
    }
}
