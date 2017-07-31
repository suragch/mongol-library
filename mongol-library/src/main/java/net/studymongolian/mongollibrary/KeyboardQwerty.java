package net.studymongolian.mongollibrary;

import android.content.Context;
import android.util.AttributeSet;



public class KeyboardQwerty extends Keyboard {

    // Row 1
    protected KeyText mKeyQ;
    protected KeyText mKeyW;
    protected KeyText mKeyE;
    protected KeyText mKeyR;
    protected KeyText mKeyT;
    protected KeyText mKeyY;
    protected KeyText mKeyU;
    protected KeyText mKeyI;
    protected KeyText mKeyO;
    protected KeyText mKeyP;

    protected KeyText mKeyA;
    protected KeyText mKeyS;
    protected KeyText mKeyD;
    protected KeyText mKeyF;
    protected KeyText mKeyG;
    protected KeyText mKeyH;
    protected KeyText mKeyJ;
    protected KeyText mKeyK;
    protected KeyText mKeyL;

    protected KeyText mKeyZ;
    protected KeyText mKeyX;
    protected KeyText mKeyV;
    protected KeyText mKeyB;
    protected KeyText mKeyN;
    protected KeyText mKeyM;
    protected KeyImage mKeyBackspace;

    // Row 4
    protected KeyImage mKeyKeyboard;
    protected KeyText mKeyComma;
    protected KeyText mKeySpace;
    protected KeyImage mKeyReturn;



    public KeyboardQwerty(Context context) {
        super(context);
    }

    public KeyboardQwerty(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public KeyboardQwerty(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
