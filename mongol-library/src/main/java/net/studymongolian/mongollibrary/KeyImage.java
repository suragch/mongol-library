package net.studymongolian.mongollibrary;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.View;

public class KeyImage extends Key {

    private static final String DEBUG_TAG = "TAG";

    // use a light image for a DARK theme and vice-versa
    public enum Theme {
        DARK,
        LIGHT
    }

    private TextPaint mImagePaint;
    private Bitmap mImage;
    private Bitmap mImageScaled;
    private boolean mNeedToScaleImage = false;

    public KeyImage(Context context) {
        this(context, null, 0);
    }

    public KeyImage(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public KeyImage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaints();
    }

    private void initPaints() {

        mImagePaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mImagePaint.setFilterBitmap(true);
        mImagePaint.setDither(true);



        mImagePaint.setColor(Color.YELLOW);
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



        int left = getPaddingLeft();
        int top = getPaddingTop();
        int right = left + keyWidth;
        int bottom = top + keyHeight;

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

}
