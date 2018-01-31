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

    //private static final String DEBUG_TAG = "TAG";

    private String mPrimaryText;

    //protected Theme mTheme = Theme.LIGHT;
    private TextPaint mImagePaint;
    private Bitmap mImage;
    private Bitmap mImageScaled;
    private boolean mNeedToScaleImage = false;

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



        //mImagePaint.setColor(Color.YELLOW);
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



        //int left = getPaddingLeft();
        //int top = getPaddingTop();
        //int right = left + keyWidth;
        //int bottom = top + keyHeight;

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

    //public void setTheme(Theme theme) {
    //    mTheme = theme;
    //}


//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        changeBackgroundColorForClickEvent(event);
//
//        int action = event.getActionMasked();
//        int x = (int) event.getRawX();
//
//        switch(action) {
//            case (MotionEvent.ACTION_DOWN) :
//                lastTouchDownX = x;
//                mIsLongPress = true;
//                mHandler.postDelayed(longPress, LONG_PRESS_TIMEOUT);
//                return true;
//            case (MotionEvent.ACTION_MOVE) :
//                onActionScroll(x);
//                return true;
//            case (MotionEvent.ACTION_CANCEL) :
//            case (MotionEvent.ACTION_OUTSIDE) :
//            case (MotionEvent.ACTION_UP) :
//                mIsLongPress = false;
//                mHandler.removeCallbacks(longPress);
//                onActionUp(x);
//                return true;
//            default :
//                return super.onTouchEvent(event);
//        }
//    }
//
//    private Runnable longPress = new Runnable() {
//
//        @Override
//        public void run() {
//            if (mIsLongPress) {
//                onLongPressThresholdReached();
//                mIsLongPress = false;
//            }
//        }
//
//    };
//
//    private void onLongPressThresholdReached() {
//
//        Log.i(TAG, "onLongPressThresholdReached: ");
//        showPopup(this, lastTouchDownX);
//    }
//
//    private void onActionScroll(int xPosition) {
//        if (mIsLongPress) return;
//        Log.i(TAG, "onActionScroll: ");
//        updatePopup(xPosition);
//    }


    @Override
    protected void onActionUp(int xPosition) {
        super.onActionUp(xPosition);
        String popupChoice = getFinalPopupChoice(xPosition);
        if (popupChoice != null)
            sendTextToKeyboard(popupChoice);
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
