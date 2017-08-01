package net.studymongolian.mongollibrary;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;


public class MongolButton extends MongolTextView {

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
        setClickable(true);
        setFocusable(true);

    }


    @Override
    public CharSequence getAccessibilityClassName() {
        return MongolButton.class.getName();
    }

}
