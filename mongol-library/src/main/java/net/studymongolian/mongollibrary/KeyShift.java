package net.studymongolian.mongollibrary;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

import static android.content.ContentValues.TAG;


public class KeyShift extends KeyImage {

    private static final int CAPS_STATE_INDICATOR_INDENT = 5; // px
    private static final int CAPS_STATE_INDICATOR_RADIUS = 10; // px
    //private int mKeyHeight;
    //private int mKeyWidth;

    private boolean shiftIsOn = false;
    private boolean isCapsLockOn = false;
    private ChangeListener mListener = null;
    private Paint mCapsStatePaint;

    GestureDetector gestureDetector;

    public KeyShift(Context context) {
        super(context);
        init(context);
    }

    public KeyShift(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public KeyShift(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mCapsStatePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCapsStatePaint.setStyle(Paint.Style.FILL);
        mCapsStatePaint.setColor(Color.RED);
        //this.setImage(getShiftImage());
        gestureDetector = new GestureDetector(context, new GestureListener());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawCircleRepresentingTheCapsState(canvas);
    }

    private void drawCircleRepresentingTheCapsState(Canvas canvas) {

        if (!shiftIsOn) return;

        // make sure a large border radius doesn't cover the indicator
        float indent = CAPS_STATE_INDICATOR_INDENT;
        float borderRadiusAdjustment = (float) (mBorderRadius * ( 1 - (1 / Math.sqrt(2))));
        indent += borderRadiusAdjustment;

        int indicatorDiameter = 2 * CAPS_STATE_INDICATOR_RADIUS;

        int dx = getPaddingLeft() + mKeyWidth - indicatorDiameter - (int) indent;
        int dy = getPaddingTop() + mKeyHeight - indicatorDiameter - (int) indent;

        // draw indicator
        canvas.save();
        canvas.translate(dx, dy);
        canvas.drawCircle(0, 0, CAPS_STATE_INDICATOR_RADIUS, mCapsStatePaint);
        canvas.restore();
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        changeBackgroundColorForClickEvent(e);
        return gestureDetector.onTouchEvent(e);
    }


    private class GestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onDown(MotionEvent e) {
            // change color to pressed color
            return true;
        }

        // TODO this could be singleTap to lessen delay since there is no long press here
        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            Log.i(TAG, "onSingleTapConfirmed: " + shiftIsOn);
            //KeyShift.this.setPressed(true);
            isCapsLockOn = false;
            shiftIsOn = !shiftIsOn;
            if (mListener != null)
                mListener.onShiftChanged(shiftIsOn);
            invalidate();
            return true;
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            Log.i(TAG, "onDoubleTap: " + shiftIsOn);
            boolean oldStateWasAlreadyOn = shiftIsOn;
            isCapsLockOn = true;
            shiftIsOn = true;
            if (oldStateWasAlreadyOn) return true;
            if (mListener != null)
                mListener.onShiftChanged(true);
            invalidate();
            return true;
        }
    }

    public boolean shiftIsOn() {
        return shiftIsOn;
    }

    public void turnOffCapsUnlessLocked() {
        if (isCapsLockOn) return;
        if (!shiftIsOn) return;
        shiftIsOn = false;
        if (mListener != null)
            mListener.onShiftChanged(shiftIsOn);
    }

    public void setShiftImage(Keyboard.Theme theme) {
        int imageResourceId;
        if (theme == Keyboard.Theme.LIGHT) {
            imageResourceId = R.drawable.ic_keyboard_shift_black_32dp;
        } else {
            imageResourceId = R.drawable.ic_keyboard_shift_white_32dp;
        }
        setImage(BitmapFactory.decodeResource(getResources(), imageResourceId));
    }

    public void setChangeListener(ChangeListener listener) {
        this.mListener = listener;
    }

    public interface ChangeListener {
        void onShiftChanged(boolean shiftIsOn);
    }
}