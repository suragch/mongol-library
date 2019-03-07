package net.studymongolian.mongollibrary;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;


public class MongolButton extends MongolTextView {

    private static final int RESTING_ELEVATION_DP = 2;
    private static final int PRESSED_ELEVATION_DP = 4;


    public MongolButton(Context context) {
        this(context, null);
    }

    public MongolButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        // TODO it would probably be better to do something like this (rather than init()):
        //this(context, attrs, R.attr.MongolButtonStyle);
    }

    public MongolButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        Drawable background = ContextCompat.getDrawable(context, R.drawable.btn_default);
        setBackgroundDrawable(background);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setElevation(convertDpToPx(RESTING_ELEVATION_DP));
        }
        setClickable(true);
        setFocusable(true);

    }

    private int convertDpToPx(int dp) {
        return (int) (dp * getContext().getResources().getDisplayMetrics().density);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getActionMasked();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    setElevation(convertDpToPx(PRESSED_ELEVATION_DP));
                }
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            default:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    setElevation(convertDpToPx(RESTING_ELEVATION_DP));
                }
        }
        return super.onTouchEvent(event);
    }

    @Override
    public CharSequence getAccessibilityClassName() {
        return MongolButton.class.getName();
    }

}
