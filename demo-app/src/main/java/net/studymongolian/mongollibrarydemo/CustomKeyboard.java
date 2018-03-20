package net.studymongolian.mongollibrarydemo;

import android.content.Context;
import android.util.AttributeSet;

import net.studymongolian.mongollibrary.Key;
import net.studymongolian.mongollibrary.KeyBackspace;
import net.studymongolian.mongollibrary.KeyKeyboardChooser;
import net.studymongolian.mongollibrary.KeyText;
import net.studymongolian.mongollibrary.Keyboard;
import net.studymongolian.mongollibrary.PopupKeyCandidate;

import java.util.List;

public class CustomKeyboard extends Keyboard {

    // name to use in the keyboard popup chooser
    private static final String DISPLAY_NAME = "ᠲᠤᠭ᠎ᠠ";

    // Row 1
    protected KeyText mKey1;
    protected KeyText mKey2;
    protected KeyText mKey3;

    // Row 2
    protected KeyText mKey4;
    protected KeyText mKey5;
    protected KeyText mKey6;

    // Row 3
    protected KeyText mKey7;
    protected KeyText mKey8;
    protected KeyText mKey9;

    // Row 4
    protected KeyKeyboardChooser mKeyKeyboard;
    protected KeyText mKey0;
    protected KeyBackspace mKeyBackspace;

    public CustomKeyboard(Context context) {
        super(context);
        init(context);
    }

    public CustomKeyboard(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomKeyboard(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    // all keyboards should include this custom constructor
    // (there was no way to force it in the abstract Keyboard class)
//    public CustomKeyboard(Context context, StyleBuilder style) {
//        super(context);
//        super.initStyle(style);
//        init(context);
//    }

    protected void init(Context context) {

        // | 7 | 8 | 9 |    Row 1
        // | 4 | 5 | 6 |    Row 2
        // | 1 | 2 | 3 |    Row 3
        // |kbd| 0 |del|    Row 4

        // actual layout work is done by Keyboard superclass's onLayout
        mNumberOfKeysInRow = new int[]{3, 3, 3, 3};
        // the key weights for each row should sum to 1
        mKeyWeights = new float[]{
                1 / 3f, 1 / 3f, 1 / 3f,   // row 0
                1 / 3f, 1 / 3f, 1 / 3f,   // row 1
                1 / 3f, 1 / 3f, 1 / 3f,   // row 2
                1 / 3f, 1 / 3f, 1 / 3f};  // row 3


        // Make sure that the total keys added to this ViewGroup below equals
        // the mNumberOfKeysInRow and mKeyWeights array totals above.

        instantiateKeys(context);
        setKeyValues();
        dontRotatePrimaryText();
        setListeners();
        addKeysToKeyboard();
        applyThemeToKeys();
    }

    private void instantiateKeys(Context context) {

        // Row 1
        mKey1 = new KeyText(context);
        mKey2 = new KeyText(context);
        mKey3 = new KeyText(context);

        // Row 2
        mKey4 = new KeyText(context);
        mKey5 = new KeyText(context);
        mKey6 = new KeyText(context);

        // Row 3
        mKey7 = new KeyText(context);
        mKey8 = new KeyText(context);
        mKey9 = new KeyText(context);

        // Row 4
        mKeyKeyboard = new KeyKeyboardChooser(context);
        mKey0 = new KeyText(context);
        mKeyBackspace = new KeyBackspace(context);
    }

    private void setKeyValues() {

        mKey1.setText("1");
        mKey2.setText("2");
        mKey3.setText("3");
        mKey4.setText("4");
        mKey5.setText("5");
        mKey6.setText("6");
        mKey7.setText("7");
        mKey8.setText("8");
        mKey9.setText("9");
        mKey0.setText("0");

        mKeyBackspace.setImage(getBackspaceImage());
        mKeyKeyboard.setImage(getKeyboardImage());
    }

    private void dontRotatePrimaryText() {
        mKey1.setRotatedPrimaryText(false);
        mKey2.setRotatedPrimaryText(false);
        mKey3.setRotatedPrimaryText(false);
        mKey4.setRotatedPrimaryText(false);
        mKey5.setRotatedPrimaryText(false);
        mKey6.setRotatedPrimaryText(false);
        mKey7.setRotatedPrimaryText(false);
        mKey8.setRotatedPrimaryText(false);
        mKey9.setRotatedPrimaryText(false);
        mKey0.setRotatedPrimaryText(false);
    }


    private void setListeners() {

        // Row 1
        mKey1.setKeyListener(this);
        mKey2.setKeyListener(this);
        mKey3.setKeyListener(this);

        // Row 2
        mKey4.setKeyListener(this);
        mKey5.setKeyListener(this);
        mKey6.setKeyListener(this);

        // Row 3
        mKey7.setKeyListener(this);
        mKey8.setKeyListener(this);
        mKey9.setKeyListener(this);

        // Row 4
        mKeyKeyboard.setKeyListener(this);
        mKey0.setKeyListener(this);
        mKeyBackspace.setKeyListener(this);
    }

    private void addKeysToKeyboard() {

        // Row 1
        addView(mKey1);
        addView(mKey2);
        addView(mKey3);

        // Row 2
        addView(mKey4);
        addView(mKey5);
        addView(mKey6);

        // Row 3
        addView(mKey7);
        addView(mKey8);
        addView(mKey9);

        // Row 4
        addView(mKeyKeyboard);
        addView(mKey0);
        addView(mKeyBackspace);
    }

    public List<PopupKeyCandidate> getPopupCandidates(Key key) {
        // this keyboard has no popups except for the keyboard key
        if (key == mKeyKeyboard) {
            return getCandidatesForKeyboardKey();
        }
        return null;
    }

    @Override
    public String getDisplayName() {
        return DISPLAY_NAME;
    }

    @Override
    public void onKeyboardKeyClick() {
        // this keyboard doesn't have punctuation key values
    }
}
