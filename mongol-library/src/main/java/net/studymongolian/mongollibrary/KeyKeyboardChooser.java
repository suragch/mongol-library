package net.studymongolian.mongollibrary;

import android.content.Context;
import android.util.AttributeSet;


public class KeyKeyboardChooser extends KeyImage {

    private static final char ELLIPSIS = '…';
    private static final float ELLIPSIS_SIZE_MULTIPLIER = 1.5f;

    public KeyKeyboardChooser(Context context) {
        super(context);
        init();
    }

    public KeyKeyboardChooser(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public KeyKeyboardChooser(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setSubText(ELLIPSIS);
        setIsRotatedSubText(false);
    }

    @Override
    protected void onActionUp(int xPosition, int yPosition) {
        if (getIsShowingPopup())
            chooseAnotherKeyboard(xPosition);
        else
            clickKeyboardKey();
    }

    @Override
    public void setSubTextSize(float subTextSize) {
        float scaledSize = subTextSize * ELLIPSIS_SIZE_MULTIPLIER;
        super.setSubTextSize(scaledSize);
    }
}
