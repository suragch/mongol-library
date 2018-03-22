package net.studymongolian.mongollibrary;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;

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
