package net.studymongolian.mongollibrary;

import android.content.Context;
import android.util.AttributeSet;

public class KeyTextNotRotated extends KeyText {

    public KeyTextNotRotated(Context context) {
        super(context);
        init();
    }

    public KeyTextNotRotated(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public KeyTextNotRotated(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mIsRotatedPrimaryText = false;
        mIsRotatedSubText = false;
    }

}
