//package net.studymongolian.mongollibrarydemo;
//
//import android.content.Context;
//import android.graphics.BitmapFactory;
//import android.util.AttributeSet;
//
//import net.studymongolian.mongollibrary.Key;
//import net.studymongolian.mongollibrary.KeyImage;
//import net.studymongolian.mongollibrary.KeyText;
//import net.studymongolian.mongollibrary.Keyboard;
//import net.studymongolian.mongollibrary.MongolCode;
//
//public class CustomKeyboard extends Keyboard {
//
//    // name to use in the keyboard popup chooser
//    private static final String DISPLAY_NAME = "ᠲᠤᠭ᠎ᠠ";
//
//    // Row 1
//    protected KeyText mKey7;
//    protected KeyText mKey8;
//    protected KeyText mKey9;
//
//    // Row 2
//    protected KeyText mKey4;
//    protected KeyText mKey5;
//    protected KeyText mKey6;
//
//    // Row 3
//    protected KeyText mKey1;
//    protected KeyText mKey2;
//    protected KeyText mKey3;
//
//    // Row 4
//    //protected KeyImage mKeyKeyboard; // defined in super class
//    protected KeyText mKey0;
//    protected KeyImage mKeyBackspace;
//
//    // These are all input values (some display values are different)
//    private static final String KEY_0 = "0";
//    private static final String KEY_1 = "1";
//    private static final String KEY_2 = "2";
//    private static final String KEY_3 = "3";
//    private static final String KEY_4 = "4";
//    private static final String KEY_5 = "5";
//    private static final String KEY_6 = "6";
//    private static final String KEY_7 = "7";
//    private static final String KEY_8 = "8";
//    private static final String KEY_9 = "9";
//
//    // Use this constructor if you want the default style
//    public CustomKeyboard(Context context) {
//        super(context);
//        init(context);
//    }
//
//    // all keyboards should include this custom constructor
//    // (there was no way to force it in the abstract Keyboard class)
//    public CustomKeyboard(Context context, StyleBuilder style) {
//        super(context);
//        super.initStyle(style);
//        init(context);
//    }
//
//    protected void init(Context context) {
//
//        // | 7 | 8 | 9 |    Row 1
//        // | 4 | 5 | 6 |    Row 2
//        // | 1 | 2 | 3 |    Row 3
//        // |kbd| 0 |del|    Row 4
//
//        // actual layout work is done by Keyboard superclass's onLayout
//        mNumberOfKeysInRow = new int[]{3, 3, 3, 3};
//        // the key weights for each row should sum to 1
//        mKeyWeights = new float[]{
//                1 / 3f, 1 / 3f, 1 / 3f,   // row 0
//                1 / 3f, 1 / 3f, 1 / 3f,   // row 1
//                1 / 3f, 1 / 3f, 1 / 3f,   // row 2
//                1 / 3f, 1 / 3f, 1 / 3f};  // row 3
//
//
//        // Row 1
//
//        mKey7 = new KeyText(context);
//        initTextKey(mKey7, KEY_7, "");
//
//        mKey8 = new KeyText(context);
//        initTextKey(mKey8, KEY_8, "");
//
//        mKey9 = new KeyText(context);
//        initTextKey(mKey9, KEY_9, "");
//
//        // Row 2
//
//        mKey4 = new KeyText(context);
//        initTextKey(mKey4, KEY_4, "");
//
//        mKey5 = new KeyText(context);
//        initTextKey(mKey5, KEY_5, "");
//
//        mKey6 = new KeyText(context);
//        initTextKey(mKey6, KEY_6, "");
//
//        // Row 3
//
//        mKey1 = new KeyText(context);
//        initTextKey(mKey1, KEY_1, "");
//
//        mKey2 = new KeyText(context);
//        initTextKey(mKey2, KEY_2, "");
//
//        mKey3 = new KeyText(context);
//        initTextKey(mKey3, KEY_3, "");
//
//        // Row 4
//
//        // keyboard
//        mKeyKeyboard = new KeyImage(context);
//        mKeyKeyboard.setImage(getKeyboardImage());
//        mKeyKeyboard.setOnTouchListener(textKeyTouchListener);
//        addView(mKeyKeyboard);
//
//        mKey0 = new KeyText(context);
//        initTextKey(mKey0, KEY_0, "");
//
//        // backspace
//        mKeyBackspace = new KeyImage(context);
//        mKeyBackspace.setImage(BitmapFactory.decodeResource(getResources(),
//                R.drawable.ic_custom_backspace)); // custom (non library) icon
//        mKeyBackspace.setOnTouchListener(handleBackspace);
//        addView(mKeyBackspace);
//
//        setDisplayText(mIsShowingPunctuation);
//        applyThemeToKeys();
//    }
//
//    public void setDisplayText(boolean isShowingPunctuation) {
//
//        mKey1.setText(KEY_1);
//        mKey2.setText(KEY_2);
//        mKey3.setText(KEY_3);
//        mKey4.setText(KEY_4);
//        mKey5.setText(KEY_5);
//        mKey6.setText(KEY_6);
//        mKey7.setText(KEY_7);
//        mKey8.setText(KEY_8);
//        mKey9.setText(KEY_9);
//        mKey0.setText(KEY_0);
//    }
//
//    public PopupCandidates getPopupCandidates(Key key) {
//        // this keyboard has no popups except for the keyboard key
//        if (key == mKeyKeyboard) {
//            return getCandidatesForKeyboard();
//        }
//        return null;
//    }
//
//    @Override
//    public String getDisplayName() {
//        return DISPLAY_NAME;
//    }
//
//
//}
