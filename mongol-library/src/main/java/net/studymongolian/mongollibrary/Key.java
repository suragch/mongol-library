package net.studymongolian.mongollibrary;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;


public abstract class Key extends View {

    public static final float MAX_CONTENT_PROPORTION = 0.8f;

    protected boolean mStatePressed = false;
    protected Paint mKeyPaint;
    protected int mKeyColor;
    protected Paint mKeyBorderPaint;
    protected int mBorderRadius;
    protected int mPressedColor;
    protected RectF mSizeRect;
    protected int mKeyHeight;
    protected int mKeyWidth;

    private final int LONG_PRESS_TIMEOUT = ViewConfiguration.getLongPressTimeout();
    private Handler mHandler = new Handler();
    private boolean mIsLongPress = false;
    private int lastTouchDownX;

    private KeyListener mKeyListener = null;

    public Key(Context context) {
        super(context);
        initDefault();
        initPaints();
    }

    public Key(Context context, AttributeSet attrs) {
        super(context, attrs);
        initDefault();
        initPaints();
    }

    Key(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initDefault();
        initPaints();
    }

    private void initDefault() {
        mPressedColor = Color.GRAY;
    }

    private void initPaints() {
        mKeyPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mKeyPaint.setStyle(Paint.Style.FILL);

        mKeyBorderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mKeyBorderPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mSizeRect = new RectF(getPaddingLeft(), getPaddingTop(),
                w - getPaddingRight(), h - getPaddingBottom());
        mKeyHeight = getMeasuredHeight() - getPaddingTop() - getPaddingBottom();
        mKeyWidth = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // draw background and border
        canvas.drawRoundRect(mSizeRect, mBorderRadius, mBorderRadius, mKeyPaint);
        if (mKeyBorderPaint.getStrokeWidth() > 0) {
            canvas.drawRoundRect(mSizeRect, mBorderRadius, mBorderRadius, mKeyBorderPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        changeBackgroundColorForClickEvent(event);

        int action = event.getActionMasked();
        int x = (int) event.getRawX();

        switch(action) {
            case (MotionEvent.ACTION_DOWN) :
                onActionDown(x);
                return true;
            case (MotionEvent.ACTION_MOVE) :
                onActionScroll(x);
                return true;
            case (MotionEvent.ACTION_UP) :
                mIsLongPress = false;
                mHandler.removeCallbacks(longPress);
                onActionUp(x);
                return true;
            default :
                mIsLongPress = false;
                mHandler.removeCallbacks(longPress);
                return super.onTouchEvent(event);
        }
    }

    protected void onActionDown(int xPosition) {
        lastTouchDownX = xPosition;
        mIsLongPress = true;
        mHandler.postDelayed(longPress, LONG_PRESS_TIMEOUT);
    }

    private Runnable longPress = new Runnable() {
        @Override
        public void run() {
            if (mIsLongPress) {
                onLongPressThresholdReached();
                mIsLongPress = false;
            }
        }
    };

    private void onLongPressThresholdReached() {
        showPopup(this, lastTouchDownX);
    }

    protected void onActionScroll(int xPosition) {
        if (mIsLongPress) return;
        updatePopup(xPosition);
    }


    protected void onActionUp(int xPosition) {
        finishPopup(xPosition);
    }

    @Override
    public void setPressed(boolean pressed) {
        super.setPressed(pressed);
        mStatePressed = pressed;
        if (pressed) {
            mKeyPaint.setColor(mPressedColor);
        } else {
            mKeyPaint.setColor(mKeyColor);
        }
        invalidate();
    }

    protected void changeBackgroundColorForClickEvent(MotionEvent event) {
        int action = event.getActionMasked();
        if (action == MotionEvent.ACTION_DOWN) {
            this.setPressed(true);
        } else if (action == MotionEvent.ACTION_UP) {
            this.setPressed(false);
        }
    }

    public void setKeyColor(int keyColor) {
        mKeyPaint.setColor(keyColor);
        this.mKeyColor = keyColor;
        invalidate();
    }

    public void setPressedColor(int pressedColor) {
        this.mPressedColor = pressedColor;
        invalidate();
    }

    public void setBorderColor(int borderColor) {
        mKeyBorderPaint.setColor(borderColor);
        invalidate();
    }

    public void setBorderWidth(int borderWidth) {
        mKeyBorderPaint.setStrokeWidth(borderWidth);
        invalidate();
    }

    public void setBorderRadius(int borderRadius) {
        this.mBorderRadius = borderRadius;
        invalidate();
    }

    // Keyboards should implement this interface to receive input from keys
    public interface KeyListener {
        void onKeyInput(String text);
        void onBackspace();

        boolean getIsShowingPopup();
        void showPopup(Key key, int xPosition);
        void updatePopup(int xPosition);
        void finishPopup(int xPosition);

        void onKeyboardKeyClick();
        void onNewKeyboardChosen(int xPositionOnPopup);
        void onShiftChanged(boolean isShiftOn);
    }
    public void setKeyListener(KeyListener listener) {
        this.mKeyListener = listener;
    }
    protected void sendTextToKeyboard(String text) {
        if (mKeyListener == null) return;
        mKeyListener.onKeyInput(text);
    }
    protected boolean getIsShowingPopup() {
        return (mKeyListener != null)
                && mKeyListener.getIsShowingPopup();
    }
    protected void showPopup(Key key, int xPosition) {
        if (mKeyListener == null) return;
        mKeyListener.showPopup(key, xPosition);
    }
    protected void updatePopup(int xPosition) {
        if (mKeyListener == null) return;
        mKeyListener.updatePopup(xPosition);
    }
    protected void finishPopup(int xPosition) {
        if (mKeyListener == null) return;
        mKeyListener.finishPopup(xPosition);
    }
    protected void backspace() {
        if (mKeyListener == null) return;
        mKeyListener.onBackspace();
    }
    protected void clickKeyboardKey() {
        if (mKeyListener == null) return;
        mKeyListener.onKeyboardKeyClick();
    }
    protected void chooseAnotherKeyboard(int xPositionOnPopup) {
        if (mKeyListener == null) return;
        mKeyListener.onNewKeyboardChosen(xPositionOnPopup);
    }
    protected void setShift(boolean isShiftOn) {
        if (mKeyListener == null) return;
        mKeyListener.onShiftChanged(isShiftOn);
    }

}
