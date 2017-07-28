package net.studymongolian.mongollibrary;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;


public abstract class Keyboard extends ViewGroup {

    public Keyboard(Context context) {
        this(context, null, 0);
    }

    public Keyboard(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Keyboard(Context context,
                         AttributeSet attrs,
                         int defStyle) {
        super(context, attrs, defStyle);
    }

}
