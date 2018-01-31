package net.studymongolian.mongollibrary;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;

import static android.content.ContentValues.TAG;


public class KeyBackspace extends KeyImage {

    private Handler mHandler = new Handler();
    final int INITIAL_DELAY = 500;
    final int REPEAT_DELAY = 50;

    public KeyBackspace(Context context) {
        super(context);
    }

    public KeyBackspace(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public KeyBackspace(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onActionDown(int xPosition) {
        backspace();
        mHandler.postDelayed(actionBackspace, INITIAL_DELAY);
    }

    Runnable actionBackspace = new Runnable() {
        @Override
        public void run() {
            backspace();
            mHandler.postDelayed(this, REPEAT_DELAY);
        }
    };

    @Override
    protected void onActionScroll(int xPosition) {}

    @Override
    protected void onActionUp(int xPosition) {
        mHandler.removeCallbacks(actionBackspace);
    }

}
