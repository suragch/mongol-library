package net.studymongolian.mongollibrary;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class KeyboardAeiou extends ViewGroup {

    private Button mPreviousButton;
    private Button mNextButton;

    public KeyboardAeiou(Context context) {
        this(context, null, 0);
    }

    public KeyboardAeiou(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public KeyboardAeiou(Context context,
                       AttributeSet attrs,
                       int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }



    private void init(Context context) {
        KeyText key = new KeyText(context, "a", Color.BLACK, Color.LTGRAY, Color.GRAY, Color.BLACK, 5, 20);
        addView(key);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        final int totalWidth = getMeasuredWidth();
        final int totalHeight = getMeasuredHeight();

        View child = getChildAt(0);
        child.measure(MeasureSpec.makeMeasureSpec(totalWidth / 4, MeasureSpec.EXACTLY),
                MeasureSpec.makeMeasureSpec(totalHeight / 4, MeasureSpec.EXACTLY));

        child.layout(getPaddingLeft(), getPaddingTop(), getPaddingLeft() + child.getMeasuredWidth(),
                getPaddingTop() + child.getMeasuredHeight());
    }
}
