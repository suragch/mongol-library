package net.studymongolian.mongollibrary;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;


public abstract class Keyboard extends LinearLayout {

    private Button mPreviousButton;
    private Button mNextButton;

    public Keyboard(Context context) {
        this(context, null);
    }

    public Keyboard(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


}
