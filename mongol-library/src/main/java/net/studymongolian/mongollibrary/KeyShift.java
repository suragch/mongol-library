package net.studymongolian.mongollibrary;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;


public class KeyShift extends KeyImage {

    private boolean isShiftOn = false;
    private boolean isCapsLockOn = false;
    private ChangeListener mListener = null;

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
        this.setImage(getShiftImage());
        gestureDetector = new GestureDetector(context, new GestureListener());
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        return gestureDetector.onTouchEvent(e);
    }


    private class GestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            isCapsLockOn = false;
            isShiftOn = !isShiftOn;
            if (mListener != null)
                mListener.onShiftChanged(isShiftOn);
            return true;
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            boolean oldStateWasAlreadyOn = isShiftOn;
            isCapsLockOn = true;
            isShiftOn = true;
            if (oldStateWasAlreadyOn) return true;
            if (mListener != null)
                mListener.onShiftChanged(true);
            return true;
        }
    }

    public boolean shiftIsOn() {
        return isShiftOn;
    }

    public void turnOffCapsUnlessLocked() {
        if (isCapsLockOn) return;
        isShiftOn = false;
        if (mListener != null)
            mListener.onShiftChanged(isShiftOn);
    }

//    public boolean capsLockIsOn() {
//        return isCapsLockOn;
//    }


    private Bitmap getShiftImage() {
        int imageResourceId;
        if (mTheme == KeyImage.Theme.LIGHT) {
            imageResourceId = R.drawable.ic_keyboard_shift_black_32dp;
        } else {
            imageResourceId = R.drawable.ic_keyboard_shift_white_32dp;
        }
        return BitmapFactory.decodeResource(getResources(), imageResourceId);
    }

    public void setChangeListener(ChangeListener listener) {
        this.mListener = listener;
    }

    public interface ChangeListener {
        public void onShiftChanged(boolean shiftIsOn);
    }
}