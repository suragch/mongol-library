package net.studymongolian.mongollibrary;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;


public class KeyShift extends KeyImage {

    private static final int CAPS_STATE_INDICATOR_INDENT = 5; // px
    private static final int CAPS_STATE_INDICATOR_RADIUS = 5; // px

    private static final long DOUBLE_CLICK_TIME_DELTA = ViewConfiguration.getDoubleTapTimeout();

    long lastClickTime = 0;

    private boolean isShiftOn = false;
    private boolean isCapsLockOn = false;
    private Paint mCapsStatePaint;
    private static final int DEFAULT_CAPS_STATE_INDICATOR_COLOR = Color.RED;

    public KeyShift(Context context) {
        super(context);
        init();
    }

    public KeyShift(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public KeyShift(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mCapsStatePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCapsStatePaint.setStyle(Paint.Style.FILL);
        mCapsStatePaint.setColor(DEFAULT_CAPS_STATE_INDICATOR_COLOR);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawCircleRepresentingTheShiftState(canvas);
        // TODO: draw something to represent the caps lock state
    }

    private void drawCircleRepresentingTheShiftState(Canvas canvas) {

        if (!isShiftOn) return;

        // make sure a large border radius doesn't cover the indicator
        float indent = CAPS_STATE_INDICATOR_INDENT;
        float borderRadiusAdjustment = (float) (mBorderRadius * ( 1 - (1 / Math.sqrt(2))));
        indent += borderRadiusAdjustment;

        int indicatorDiameter = 2 * CAPS_STATE_INDICATOR_RADIUS;

        int dx = getPaddingLeft() + mKeyWidth - indicatorDiameter - (int) indent;
        int dy = getPaddingTop() + indicatorDiameter + (int) indent;

        // draw indicator
        canvas.save();
        canvas.translate(dx, dy);
        canvas.drawCircle(0, 0, CAPS_STATE_INDICATOR_RADIUS, mCapsStatePaint);
        canvas.restore();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        changeBackgroundColorForClickEvent(event);
        int action = event.getActionMasked();
        switch(action) {
            case (MotionEvent.ACTION_DOWN) :
                return true;
            case (MotionEvent.ACTION_UP) :
                differentiateSingleDoubleTap();
                return true;
            default :
                return super.onTouchEvent(event);
        }
    }

    private void differentiateSingleDoubleTap() {
        long clickTime = System.currentTimeMillis();
        if (clickTime - lastClickTime < DOUBLE_CLICK_TIME_DELTA){
            onSecondTapOfDoubleTap();
        } else {
            onSingleTapUp();
        }
        lastClickTime = clickTime;
    }

    private void onSingleTapUp() {
        isCapsLockOn = false;
        isShiftOn = !isShiftOn;
        setShift(isShiftOn);
        invalidate();
    }

    private void onSecondTapOfDoubleTap() {
        if (isShiftOn) {
            isCapsLockOn = true;
            invalidate();
        }
    }

    public void turnOffCapsUnlessLocked() {
        if (isCapsLockOn) return;
        if (!isShiftOn) return;
        isShiftOn = false;
        setShift(false);
        invalidate();
    }

    public void setShiftImage(KeyImage.Theme theme) {
        int imageResourceId;
        if (theme == KeyImage.Theme.LIGHT) {
            imageResourceId = R.drawable.ic_keyboard_shift_black_32dp;
        } else {
            imageResourceId = R.drawable.ic_keyboard_shift_white_32dp;
        }
        setImage(BitmapFactory.decodeResource(getResources(), imageResourceId));
    }

    public void setCapsStateIndicatorColor(int color) {
        mCapsStatePaint.setColor(color);
        invalidate();
    }
}