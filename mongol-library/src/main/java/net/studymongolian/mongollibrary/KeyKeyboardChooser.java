package net.studymongolian.mongollibrary;

import android.content.Context;
import android.util.AttributeSet;


public class KeyKeyboardChooser extends KeyImage {


    public KeyKeyboardChooser(Context context) {
        super(context);
    }

    public KeyKeyboardChooser(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public KeyKeyboardChooser(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onActionUp(int xPosition) {
        if (getIsShowingPopup())
            chooseAnotherKeyboard(xPosition);
        else
            clickKeyboardKey();
    }

}
