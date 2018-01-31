package net.studymongolian.mongollibrary;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

public class KeyboardAeiou extends Keyboard {

    // name to use in the keyboard popup chooser
    private static final String DISPLAY_NAME = "ᠴᠠᠭᠠᠨ ᠲᠣᠯᠤᠭᠠᠢ";

    // Row 1
    protected KeyText mKeyA;
    protected KeyText mKeyE;
    protected KeyText mKeyI;
    protected KeyText mKeyO;
    protected KeyText mKeyU;

    // Row 2
    protected KeyText mKeyNA;
    protected KeyText mKeyBA;
    protected KeyText mKeyQA;
    protected KeyText mKeyGA;
    protected KeyText mKeyMA;
    protected KeyText mKeyLA;

    // Row 3
    protected KeyText mKeySA;
    protected KeyText mKeyTADA;
    protected KeyText mKeyCHA;
    protected KeyText mKeyJA;
    protected KeyText mKeyYA;
    protected KeyText mKeyRA;

    // Row 4
    protected KeyKeyboardChooser mKeyKeyboard; // defined in super class
    protected KeyText mKeyComma;
    protected KeyText mKeySpace;
    protected KeyBackspace mKeyBackspace;
    protected KeyImage mKeyReturn;

//    // These are all input values (some display values are different)
//    private static final String KEY_A = String.valueOf(MongolCode.Uni.A);
//    private static final String KEY_E = String.valueOf(MongolCode.Uni.E);
//    private static final String KEY_I = String.valueOf(MongolCode.Uni.I);
//    private static final String KEY_O = String.valueOf(MongolCode.Uni.U);
//    private static final String KEY_U = String.valueOf(MongolCode.Uni.UE);
//    private static final String KEY_NA = String.valueOf(MongolCode.Uni.NA);
//    private static final String KEY_BA = String.valueOf(MongolCode.Uni.BA);
//    private static final String KEY_QA = String.valueOf(MongolCode.Uni.QA);
//    private static final String KEY_GA = String.valueOf(MongolCode.Uni.GA);
//    private static final String KEY_MA = String.valueOf(MongolCode.Uni.MA);
//    private static final String KEY_LA = String.valueOf(MongolCode.Uni.LA);
//    private static final String KEY_SA = String.valueOf(MongolCode.Uni.SA);
//    private static final String KEY_DA = String.valueOf(MongolCode.Uni.DA);
//    private static final String KEY_CHA = String.valueOf(MongolCode.Uni.CHA);
//    private static final String KEY_JA = String.valueOf(MongolCode.Uni.JA);
//    private static final String KEY_YA = String.valueOf(MongolCode.Uni.YA);
//    private static final String KEY_RA = String.valueOf(MongolCode.Uni.RA);
//    private static final String KEY_COMMA = String.valueOf(MongolCode.Uni.MONGOLIAN_COMMA);
//    private static final String KEY_SPACE = " ";
//
//    private static final String KEY_A_SUB = "";
//    private static final String KEY_E_SUB = String.valueOf(MongolCode.Uni.EE);
//    private static final String KEY_I_SUB = "";
//    private static final String KEY_O_SUB = "";
//    private static final String KEY_U_SUB = "";
//    private static final String KEY_NA_SUB = String.valueOf(MongolCode.Uni.ANG);
//    private static final String KEY_BA_SUB = String.valueOf(MongolCode.Uni.PA);
//    private static final String KEY_QA_SUB = String.valueOf(MongolCode.Uni.HAA);
//    private static final String KEY_GA_SUB = String.valueOf(MongolCode.Uni.KA);
//    private static final String KEY_MA_SUB = "";
//    private static final String KEY_LA_SUB = String.valueOf(MongolCode.Uni.LHA);
//    private static final String KEY_SA_SUB = String.valueOf(MongolCode.Uni.SHA);
//    private static final String KEY_DA_SUB = String.valueOf(MongolCode.Uni.DA); // DA is default
//    private static final String KEY_CHA_SUB = String.valueOf(MongolCode.Uni.TSA);
//    private static final String KEY_JA_SUB = String.valueOf(MongolCode.Uni.ZA);
//    private static final String KEY_YA_SUB = String.valueOf(MongolCode.Uni.WA);
//    private static final String KEY_RA_SUB = String.valueOf(MongolCode.Uni.ZRA);
//
//    private static final String KEY_A_PUNCT = String.valueOf(MongolCode.Uni.VERTICAL_LEFT_PARENTHESIS);
//    private static final String KEY_E_PUNCT = String.valueOf(MongolCode.Uni.VERTICAL_RIGHT_PARENTHESIS);
//    private static final String KEY_I_PUNCT = String.valueOf(MongolCode.Uni.VERTICAL_LEFT_DOUBLE_ANGLE_BRACKET);
//    private static final String KEY_O_PUNCT = String.valueOf(MongolCode.Uni.VERTICAL_RIGHT_DOUBLE_ANGLE_BRACKET);
//    private static final String KEY_U_PUNCT = String.valueOf(MongolCode.Uni.MIDDLE_DOT);
//    private static final String KEY_NA_PUNCT = "1";
//    private static final String KEY_BA_PUNCT = "2";
//    private static final String KEY_QA_PUNCT = "3";
//    private static final String KEY_GA_PUNCT = "4";
//    private static final String KEY_MA_PUNCT = "5";
//    private static final String KEY_LA_PUNCT = String.valueOf(MongolCode.Uni.VERTICAL_EM_DASH);
//    private static final String KEY_SA_PUNCT = "6";
//    private static final String KEY_DA_PUNCT = "7";
//    private static final String KEY_CHA_PUNCT = "8";
//    private static final String KEY_JA_PUNCT = "9";
//    private static final String KEY_YA_PUNCT = "0";
//    private static final String KEY_RA_PUNCT = String.valueOf(MongolCode.Uni.QUESTION_EXCLAMATION_MARK);
//
    private static final String KEY_A_PUNCT_SUB = String.valueOf(MongolCode.Uni.VERTICAL_LEFT_SQUARE_BRACKET);
    private static final String KEY_E_PUNCT_SUB = String.valueOf(MongolCode.Uni.VERTICAL_RIGHT_SQUARE_BRACKET);
    private static final String KEY_I_PUNCT_SUB = String.valueOf(MongolCode.Uni.VERTICAL_LEFT_ANGLE_BRACKET);
    private static final String KEY_O_PUNCT_SUB = String.valueOf(MongolCode.Uni.VERTICAL_RIGHT_ANGLE_BRACKET);
    private static final String KEY_U_PUNCT_SUB = String.valueOf(MongolCode.Uni.MONGOLIAN_ELLIPSIS);
    private static final String KEY_NA_PUNCT_SUB = String.valueOf(MongolCode.Uni.MONGOLIAN_DIGIT_ONE);
    private static final String KEY_BA_PUNCT_SUB = String.valueOf(MongolCode.Uni.MONGOLIAN_DIGIT_TWO);
    private static final String KEY_QA_PUNCT_SUB = String.valueOf(MongolCode.Uni.MONGOLIAN_DIGIT_THREE);
    private static final String KEY_GA_PUNCT_SUB = String.valueOf(MongolCode.Uni.MONGOLIAN_DIGIT_FOUR);
    private static final String KEY_MA_PUNCT_SUB = String.valueOf(MongolCode.Uni.MONGOLIAN_DIGIT_FIVE);
    private static final String KEY_LA_PUNCT_SUB = String.valueOf(MongolCode.Uni.MONGOLIAN_BIRGA);
    private static final String KEY_SA_PUNCT_SUB = String.valueOf(MongolCode.Uni.MONGOLIAN_DIGIT_SIX);
    private static final String KEY_DA_PUNCT_SUB = String.valueOf(MongolCode.Uni.MONGOLIAN_DIGIT_SEVEN);
    private static final String KEY_CHA_PUNCT_SUB = String.valueOf(MongolCode.Uni.MONGOLIAN_DIGIT_EIGHT);
    private static final String KEY_JA_PUNCT_SUB = String.valueOf(MongolCode.Uni.MONGOLIAN_DIGIT_NINE);
    private static final String KEY_YA_PUNCT_SUB = String.valueOf(MongolCode.Uni.MONGOLIAN_DIGIT_ZERO);
    private static final String KEY_RA_PUNCT_SUB = String.valueOf(MongolCode.Uni.MONGOLIAN_FOUR_DOTS);


    private static final String NEWLINE = "\n";
    private static final String KEY_SPACE_SUB_DISPLAY = "ᠶ᠋ᠢ ᠳᠤ ᠤᠨ";

    // Use this constructor if you want the default style
    public KeyboardAeiou(Context context) {
        super(context);
        init(context);
    }

    // all keyboards should include this custom constructor
    // (there was no way to force it in the abstract Keyboard class)
    public KeyboardAeiou(Context context, StyleBuilder style) {
        super(context);
        super.initStyle(style);
        init(context);
    }

    protected void init(Context context) {

        // | A  | E  | I |  O |  U |    Row 1
        // | N | B | Q | G | M | L |    Row 2
        // | S | D | Ch| J | Y | R |    Row 3
        // |kb | , | space |ret|del|    Row 4

        // actual layout work is done by Keyboard superclass's onLayout
        mNumberOfKeysInRow = new int[]{5, 6, 6, 5};
        // the key weights for each row should sum to 1
        mKeyWeights = new float[]{
                1 / 5f, 1 / 5f, 1 / 5f, 1 / 5f, 1 / 5f,           // row 0
                1 / 6f, 1 / 6f, 1 / 6f, 1 / 6f, 1 / 6f, 1 / 6f,   // row 1
                1 / 6f, 1 / 6f, 1 / 6f, 1 / 6f, 1 / 6f, 1 / 6f,   // row 2
                1 / 6f, 1 / 6f, 2 / 6f, 1 / 6f, 1 / 6f};          // row 3


        instantiateKeys(context);
        setKeyValues();
        setNonChangingKeyValues(context);
        setListeners();
        addKeysToKeyboard();


//        // Row 1
//
//        mKeyA = new KeyText(context);
//        mKeyA.setText(KEY_A);
//
//        initTextKey(mKeyA, KEY_A, KEY_A_PUNCT);
//
//        mKeyE = new KeyText(context);
//        initTextKey(mKeyE, KEY_E, KEY_E_PUNCT);
//
//        mKeyI = new KeyText(context);
//        initTextKey(mKeyI, KEY_I, KEY_I_PUNCT);
//
//        mKeyO = new KeyText(context);
//        initTextKey(mKeyO, KEY_O, KEY_O_PUNCT);
//
//        mKeyU = new KeyText(context);
//        initTextKey(mKeyU, KEY_U, KEY_U_PUNCT);
//
//        // Row 2
//
//        mKeyNA = new KeyText(context);
//        initTextKey(mKeyNA, KEY_NA, KEY_NA_PUNCT);
//
//        mKeyBA = new KeyText(context);
//        initTextKey(mKeyBA, KEY_BA, KEY_BA_PUNCT);
//
//        mKeyQA = new KeyText(context);
//        initTextKey(mKeyQA, KEY_QA, KEY_QA_PUNCT);
//
//        mKeyGA = new KeyText(context);
//        initTextKey(mKeyGA, KEY_GA, KEY_GA_PUNCT);
//
//        mKeyMA = new KeyText(context);
//        initTextKey(mKeyMA, KEY_MA, KEY_MA_PUNCT);
//
//        mKeyLA = new KeyText(context);
//        initTextKey(mKeyLA, KEY_LA, KEY_LA_PUNCT);
//
//        // Row 3
//
//        mKeySA = new KeyText(context);
//        initTextKey(mKeySA, KEY_SA, KEY_SA_PUNCT);
//
//        mKeyTADA = new KeyText(context);
//        initTextKey(mKeyTADA, KEY_DA, KEY_DA_PUNCT);
//
//        mKeyCHA = new KeyText(context);
//        initTextKey(mKeyCHA, KEY_CHA, KEY_CHA_PUNCT);
//
//        mKeyJA = new KeyText(context);
//        initTextKey(mKeyJA, KEY_JA, KEY_JA_PUNCT);
//
//        mKeyYA = new KeyText(context);
//        initTextKey(mKeyYA, KEY_YA, KEY_YA_PUNCT);
//
//        mKeyRA = new KeyText(context);
//        initTextKey(mKeyRA, KEY_RA, KEY_RA_PUNCT);
//
//        // Row 4
//
//        // keyboard
//        mKeyKeyboard = new KeyImage(context);
//        mKeyKeyboard.setImage(getKeyboardImage());
//        mKeyKeyboard.setOnTouchListener(textKeyTouchListener);
//        addView(mKeyKeyboard);
//
//        // comma
//        mKeyComma = new KeyText(context);
//        initTextKey(mKeyComma, KEY_COMMA, KEY_COMMA);
//        mKeyComma.setText(KEY_COMMA);
//        mKeyComma.setSubText(MongolCode.Uni.VERTICAL_QUESTION_MARK);
//
//        // space
//        mKeySpace = new KeyText(context);
//        initTextKey(mKeySpace, " ", " ");
//        mKeySpace.setText(KEY_SPACE);
//        mKeySpace.setSubText(KEY_SPACE_SUB_DISPLAY);
//
//        // return
//        mKeyReturn = new KeyImage(context);
//        mKeyReturn.setImage(getReturnImage());
//        mKeyReturn.setOnTouchListener(textKeyTouchListener);
//        mKeyValues.put(mKeyReturn, "\n");
//        mKeyPunctuationValues.put(mKeyReturn, "\n");
//        addView(mKeyReturn);
//
//        // backspace
//        mKeyBackspace = new KeyImage(context);
//        mKeyBackspace.setImage(getBackspaceImage());
//        mKeyBackspace.setOnTouchListener(handleBackspace);
//        addView(mKeyBackspace);

        //setDisplayText(mIsShowingPunctuation);
        applyThemeToKeys();
    }

    private void instantiateKeys(Context context) {

        // Row 1
        mKeyA = new KeyText(context);
        mKeyE = new KeyText(context);
        mKeyI = new KeyText(context);
        mKeyO = new KeyText(context);
        mKeyU = new KeyText(context);

        // Row 2
        mKeyNA = new KeyText(context);
        mKeyBA = new KeyText(context);
        mKeyQA = new KeyText(context);
        mKeyGA = new KeyText(context);
        mKeyMA = new KeyText(context);
        mKeyLA = new KeyText(context);

        // Row 3
        mKeySA = new KeyText(context);
        mKeyTADA = new KeyText(context);
        mKeyCHA = new KeyText(context);
        mKeyJA = new KeyText(context);
        mKeyYA = new KeyText(context);
        mKeyRA = new KeyText(context);

        // Row 4
        mKeyKeyboard = new KeyKeyboardChooser(context);
        mKeyComma = new KeyText(context);
        mKeySpace = new KeyText(context);
        mKeyReturn = new KeyImage(context);
        mKeyBackspace = new KeyBackspace(context);
    }


    private void setKeyValues() {

        // Row 1

        mKeyA.setText(MongolCode.Uni.A);
        mKeyA.setSubText("");

        mKeyE.setText(MongolCode.Uni.E);
        mKeyE.setSubText(MongolCode.Uni.EE);

        mKeyI.setText(MongolCode.Uni.I);
        mKeyI.setSubText("");

        mKeyO.setText(MongolCode.Uni.O);
        mKeyO.setSubText("");

        mKeyU.setText(MongolCode.Uni.U);
        mKeyU.setSubText("");

        // Row 2

        mKeyNA.setText(MongolCode.Uni.NA);
        mKeyNA.setSubText(MongolCode.Uni.ANG);

        mKeyBA.setText(MongolCode.Uni.BA);
        mKeyBA.setSubText(MongolCode.Uni.PA);

        mKeyQA.setText(MongolCode.Uni.QA);
        mKeyQA.setSubText(MongolCode.Uni.HAA);

        mKeyGA.setText(MongolCode.Uni.GA);
        mKeyGA.setSubText(MongolCode.Uni.KA);

        mKeyMA.setText(MongolCode.Uni.MA);
        mKeyMA.setSubText("");

        mKeyLA.setText(MongolCode.Uni.LA);
        mKeyLA.setSubText(MongolCode.Uni.LHA);

        // Row 3

        mKeySA.setText(MongolCode.Uni.SA);
        mKeySA.setSubText(MongolCode.Uni.SHA);

        mKeyTADA.setText(MongolCode.Uni.DA);
        mKeyTADA.setSubText(MongolCode.Uni.DA);

        mKeyCHA.setText(MongolCode.Uni.CHA);
        mKeyCHA.setSubText(MongolCode.Uni.TSA);

        mKeyJA.setText(MongolCode.Uni.JA);
        mKeyJA.setSubText(MongolCode.Uni.ZA);

        mKeyYA.setText(MongolCode.Uni.YA);
        mKeyYA.setSubText(MongolCode.Uni.WA);

        mKeyRA.setText(MongolCode.Uni.RA);
        mKeyRA.setSubText(MongolCode.Uni.ZRA);

    }

    private void setPuncuationKeyValues() {

        // Row 1

        mKeyA.setText(MongolCode.Uni.VERTICAL_LEFT_PARENTHESIS);
        mKeyA.setSubText(MongolCode.Uni.VERTICAL_LEFT_SQUARE_BRACKET);

        mKeyE.setText(MongolCode.Uni.VERTICAL_RIGHT_PARENTHESIS);
        mKeyE.setSubText(MongolCode.Uni.VERTICAL_RIGHT_SQUARE_BRACKET);

        mKeyI.setText(MongolCode.Uni.VERTICAL_LEFT_DOUBLE_ANGLE_BRACKET);
        mKeyI.setSubText(MongolCode.Uni.VERTICAL_LEFT_ANGLE_BRACKET);

        mKeyO.setText(MongolCode.Uni.VERTICAL_RIGHT_DOUBLE_ANGLE_BRACKET);
        mKeyO.setSubText(MongolCode.Uni.VERTICAL_RIGHT_ANGLE_BRACKET);

        mKeyU.setText(MongolCode.Uni.MIDDLE_DOT);
        mKeyU.setSubText(MongolCode.Uni.MONGOLIAN_ELLIPSIS);

        // Row 2

        mKeyNA.setText("1");
        mKeyNA.setSubText(MongolCode.Uni.MONGOLIAN_DIGIT_ONE);

        mKeyBA.setText("2");
        mKeyBA.setSubText(MongolCode.Uni.MONGOLIAN_DIGIT_TWO);

        mKeyQA.setText("3");
        mKeyQA.setSubText(MongolCode.Uni.MONGOLIAN_DIGIT_THREE);

        mKeyGA.setText("4");
        mKeyGA.setSubText(MongolCode.Uni.MONGOLIAN_DIGIT_FOUR);

        mKeyMA.setText("5");
        mKeyMA.setSubText(MongolCode.Uni.MONGOLIAN_DIGIT_FIVE);

        mKeyLA.setText(MongolCode.Uni.VERTICAL_EM_DASH);
        mKeyLA.setSubText(MongolCode.Uni.MONGOLIAN_BIRGA);

        // Row 3

        mKeySA.setText("6");
        mKeySA.setSubText(MongolCode.Uni.MONGOLIAN_DIGIT_SIX);

        mKeyTADA.setText("7");
        mKeyTADA.setSubText(MongolCode.Uni.MONGOLIAN_DIGIT_SEVEN);

        mKeyCHA.setText("8");
        mKeyCHA.setSubText(MongolCode.Uni.MONGOLIAN_DIGIT_EIGHT);

        mKeyJA.setText("9");
        mKeyJA.setSubText(MongolCode.Uni.MONGOLIAN_DIGIT_NINE);

        mKeyYA.setText("0");
        mKeyYA.setSubText(MongolCode.Uni.MONGOLIAN_DIGIT_ZERO);

        mKeyRA.setText(MongolCode.Uni.QUESTION_EXCLAMATION_MARK);
        mKeyRA.setSubText(MongolCode.Uni.MONGOLIAN_FOUR_DOTS);
    }

    private void setNonChangingKeyValues(Context context) {

        // Row 4

        mKeyKeyboard.setImage(getKeyboardImage());

        mKeyComma.setText(MongolCode.Uni.MONGOLIAN_COMMA);
        mKeyComma.setSubText(MongolCode.Uni.VERTICAL_QUESTION_MARK);

        mKeySpace.setText(" ");
        mKeySpace.setSubText(KEY_SPACE_SUB_DISPLAY);

        mKeyReturn.setImage(getReturnImage());
        mKeyReturn.setText(NEWLINE);

        mKeyBackspace.setImage(getBackspaceImage());

    }

    private void setListeners() {
        // Row 1
        mKeyA.setKeyListener(this);
        mKeyE.setKeyListener(this);
        mKeyI.setKeyListener(this);
        mKeyO.setKeyListener(this);
        mKeyU.setKeyListener(this);

        // Row 2
        mKeyNA.setKeyListener(this);
        mKeyBA.setKeyListener(this);
        mKeyQA.setKeyListener(this);
        mKeyGA.setKeyListener(this);
        mKeyMA.setKeyListener(this);
        mKeyLA.setKeyListener(this);

        // Row 3
        mKeySA.setKeyListener(this);
        mKeyTADA.setKeyListener(this);
        mKeyCHA.setKeyListener(this);
        mKeyJA.setKeyListener(this);
        mKeyYA.setKeyListener(this);
        mKeyRA.setKeyListener(this);

        // Row 4
        mKeyKeyboard.setKeyListener(this);
        mKeyComma.setKeyListener(this);
        mKeySpace.setKeyListener(this);
        mKeyReturn.setKeyListener(this);
        mKeyBackspace.setKeyListener(this);
    }

    private void addKeysToKeyboard() {

        // Row 1
        addView(mKeyA);
        addView(mKeyE);
        addView(mKeyI);
        addView(mKeyO);
        addView(mKeyU);

        // Row 2
        addView(mKeyNA);
        addView(mKeyBA);
        addView(mKeyQA);
        addView(mKeyGA);
        addView(mKeyMA);
        addView(mKeyLA);

        // Row 3
        addView(mKeySA);
        addView(mKeyTADA);
        addView(mKeyCHA);
        addView(mKeyJA);
        addView(mKeyYA);
        addView(mKeyRA);

        // Row 4
        addView(mKeyKeyboard);
        addView(mKeyComma);
        addView(mKeySpace);
        addView(mKeyReturn);
        addView(mKeyBackspace);
    }

//    public void setDisplayText(boolean isShowingPunctuation) {
//
//        if (isShowingPunctuation) {
//            mKeyA.setText(KEY_A_PUNCT);
//            mKeyE.setText(KEY_E_PUNCT);
//            mKeyI.setText(KEY_I_PUNCT);
//            mKeyO.setText(KEY_O_PUNCT);
//            mKeyU.setText(KEY_U_PUNCT);
//            mKeyNA.setText(KEY_NA_PUNCT);
//            mKeyBA.setText(KEY_BA_PUNCT);
//            mKeyQA.setText(KEY_QA_PUNCT);
//            mKeyGA.setText(KEY_GA_PUNCT);
//            mKeyMA.setText(KEY_MA_PUNCT);
//            mKeyLA.setText(KEY_LA_PUNCT);
//            mKeySA.setText(KEY_SA_PUNCT);
//            mKeyTADA.setText(KEY_DA_PUNCT);
//            mKeyCHA.setText(KEY_CHA_PUNCT);
//            mKeyJA.setText(KEY_JA_PUNCT);
//            mKeyYA.setText(KEY_YA_PUNCT);
//            mKeyRA.setText(KEY_RA_PUNCT);
//
//            mKeyNA.setRotatedPrimaryText(false);
//            mKeyBA.setRotatedPrimaryText(false);
//            mKeyQA.setRotatedPrimaryText(false);
//            mKeyGA.setRotatedPrimaryText(false);
//            mKeyMA.setRotatedPrimaryText(false);
//            mKeySA.setRotatedPrimaryText(false);
//            mKeyTADA.setRotatedPrimaryText(false);
//            mKeyCHA.setRotatedPrimaryText(false);
//            mKeyJA.setRotatedPrimaryText(false);
//            mKeyYA.setRotatedPrimaryText(false);
//
//            mKeyA.setSubText(KEY_A_PUNCT_SUB);
//            mKeyE.setSubText(KEY_E_PUNCT_SUB);
//            mKeyI.setSubText(KEY_I_PUNCT_SUB);
//            mKeyO.setSubText(KEY_O_PUNCT_SUB);
//            mKeyU.setSubText(KEY_U_PUNCT_SUB);
//            mKeyNA.setSubText(KEY_NA_PUNCT_SUB);
//            mKeyBA.setSubText(KEY_BA_PUNCT_SUB);
//            mKeyQA.setSubText(KEY_QA_PUNCT_SUB);
//            mKeyGA.setSubText(KEY_GA_PUNCT_SUB);
//            mKeyMA.setSubText(KEY_MA_PUNCT_SUB);
//            mKeyLA.setSubText(KEY_LA_PUNCT_SUB);
//            mKeySA.setSubText(KEY_SA_PUNCT_SUB);
//            mKeyTADA.setSubText(KEY_DA_PUNCT_SUB);
//            mKeyCHA.setSubText(KEY_CHA_PUNCT_SUB);
//            mKeyJA.setSubText(KEY_JA_PUNCT_SUB);
//            mKeyYA.setSubText(KEY_YA_PUNCT_SUB);
//            mKeyRA.setSubText(KEY_RA_PUNCT_SUB);
//        } else {
//            mKeyA.setText(KEY_A);
//            mKeyE.setText(KEY_E);
//            mKeyI.setText(KEY_I);
//            mKeyO.setText(KEY_O);
//            mKeyU.setText("" + KEY_U + MongolCode.Uni.ZWJ); // display
//            mKeyNA.setText(KEY_NA);
//            mKeyBA.setText(KEY_BA);
//            mKeyQA.setText(KEY_QA);
//            mKeyGA.setText(KEY_GA);
//            mKeyMA.setText(KEY_MA);
//            mKeyLA.setText(KEY_LA);
//            mKeySA.setText(KEY_SA);
//            mKeyTADA.setText("" + MongolCode.Uni.TA); // display
//            mKeyCHA.setText(KEY_CHA);
//            mKeyJA.setText(KEY_JA);
//            mKeyYA.setText(KEY_YA);
//            mKeyRA.setText(KEY_RA);
//
//            mKeyNA.setRotatedPrimaryText(true);
//            mKeyBA.setRotatedPrimaryText(true);
//            mKeyQA.setRotatedPrimaryText(true);
//            mKeyGA.setRotatedPrimaryText(true);
//            mKeyMA.setRotatedPrimaryText(true);
//            mKeySA.setRotatedPrimaryText(true);
//            mKeyTADA.setRotatedPrimaryText(true);
//            mKeyCHA.setRotatedPrimaryText(true);
//            mKeyYA.setRotatedPrimaryText(true);
//            mKeyMA.setRotatedPrimaryText(true);
//
//            mKeyA.setSubText(KEY_A_SUB);
//            mKeyE.setSubText(KEY_E_SUB);
//            mKeyI.setSubText(KEY_I_SUB);
//            mKeyO.setSubText(KEY_O_SUB);
//            mKeyU.setSubText(KEY_U_SUB);
//            mKeyNA.setSubText(KEY_NA_SUB);
//            mKeyBA.setSubText(KEY_BA_SUB);
//            mKeyQA.setSubText(KEY_QA_SUB);
//            mKeyGA.setSubText(KEY_GA_SUB);
//            mKeyMA.setSubText(KEY_MA_SUB);
//            mKeyLA.setSubText(KEY_LA_SUB);
//            mKeySA.setSubText(KEY_SA_SUB);
//            mKeyTADA.setSubText(KEY_DA_SUB);
//            mKeyCHA.setSubText(KEY_CHA_SUB);
//            mKeyJA.setSubText(KEY_JA_SUB);
//            mKeyYA.setSubText(KEY_YA_SUB);
//            mKeyRA.setSubText(KEY_RA_SUB);
//        }
//    }

//    private void showPopup() {
//
//
//        //PopupWindow window = new PopupWindow()
//
//        //if (popupWindow != null) return;
//
//        // get the popup view
//        PopupKeyCandidates popupView = new PopupKeyCandidates(getContext());
//        popupView.setBackgroundColor(mPopupBackgroundColor);
//        popupView.setTextColor(mPopupTextColor);
//
//        // update the popup view with the candidate choices
//        Key.PopupCandidates popupCandidates = getPopupCandidates(mKeyE);
//        if (popupCandidates == null || popupCandidates.getUnicode() == null) return;
//        popupView.setCandidates(popupCandidates.getUnicode());
//        if (popupCandidates.getDisplay() == null) {
//            popupView.setDisplayCandidates(popupCandidates.getUnicode(), PopupKeyCandidates.DEFAULT_TEXT_SIZE);
//        } else {
//            popupView.setDisplayCandidates(popupCandidates.getDisplay(), PopupKeyCandidates.DEFAULT_TEXT_SIZE);
//        }
//
//        popupView.setHighlightColor(mPopupHighlightColor);
//
//        PopupWindow popupWindow = new PopupWindow(popupView,
//                LinearLayout.LayoutParams.WRAP_CONTENT,
//                LinearLayout.LayoutParams.WRAP_CONTENT);
//        int location[] = new int[2];
//        this.getLocationOnScreen(location);
//        int measureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
//        popupView.measure(measureSpec, measureSpec);
//        int popupWidth = popupView.getMeasuredWidth();
//        int spaceAboveKey = this.getHeight() / 4;
//        //final int xPosition = (int) lastTouchDownXY[0];
//        //int x = xPosition - popupWidth / popupView.getChildCount() / 2;
//        //int locationX = location[0]
//        //x = xPosition;
//        int y = location[1] - popupView.getMeasuredHeight() - spaceAboveKey;
//        //y = (int) lastTouchDownXY[1];
//        popupWindow.showAtLocation(this, Gravity.NO_GRAVITY,0, 0);
//
//
////        // highlight current item (after the popup window has loaded)
////        handler.post(new Runnable() {
////            @Override
////            public void run() {
////                int x = xPosition;
////                popupView.updateTouchPosition(x);
////            }
////        });
//    }


    public Key.PopupCandidates getPopupCandidates(Key key) {

        // get the appropriate candidates based on the key pressed
        if (key == mKeyA) {
            return getCandidatesForA(isIsolateOrInitial());
        } else if (key == mKeyE) {
            return getCandidatesForE(isIsolateOrInitial());
        } else if (key == mKeyI) {
            return getCandidatesForI(isIsolateOrInitial());
        } else if (key == mKeyO) {
            return getCandidatesForO(isIsolateOrInitial());
        } else if (key == mKeyU) {
            return getCandidatesForU(isIsolateOrInitial());
        } else if (key == mKeyNA) {
            return getCandidatesForNA(isIsolateOrInitial());
        } else if (key == mKeyBA) {
            return getCandidatesForBA();
        } else if (key == mKeyQA) {
            return getCandidatesForQA();
        } else if (key == mKeyGA) {
            return getCandidatesForGA(isIsolateOrInitial());
        } else if (key == mKeyMA) {
            return getCandidatesForMA();
        } else if (key == mKeyLA) {
            return getCandidatesForLA();
        } else if (key == mKeySA) {
            return getCandidatesForSA();
        } else if (key == mKeyTADA) {
            return getCandidatesForTADA(isIsolateOrInitial());
        } else if (key == mKeyCHA) {
            return getCandidatesForCHA();
        } else if (key == mKeyJA) {
            return getCandidatesForJA();
        } else if (key == mKeyYA) {
            return getCandidatesForYA(isIsolateOrInitial());
        } else if (key == mKeyRA) {
            return getCandidatesForRA();
        } else if (key == mKeyKeyboard) {

            return getCandidatesForKeyboard();
        } else if (key == mKeyComma) {
            return getCandidatesForComma();
        } else if (key == mKeySpace) {
            return getCandidatesForSpace();
        }

        return null;
    }


    private Key.PopupCandidates getCandidatesForA(boolean isIsolateOrInitial) {

        if (mIsShowingPunctuation) {
            return new Key.PopupCandidates(KEY_A_PUNCT_SUB);
        }

        if (isIsolateOrInitial) {
            return new Key.PopupCandidates(new String[]{
                    "" + MongolCode.Uni.A + MongolCode.Uni.FVS1,
                    "" + MongolCode.Uni.MONGOLIAN_NIRUGU});
        }

        // medial || final
        char previousChar = getPreviousChar();
        if (MongolCode.isMvsConsonant(previousChar)) {
            // include MVS
            return new Key.PopupCandidates(
                    new String[]{
                            "" + MongolCode.Uni.MVS + MongolCode.Uni.A,
                            "" + MongolCode.Uni.A + MongolCode.Uni.FVS1,
                            "" + MongolCode.Uni.MONGOLIAN_NIRUGU},
                    new String[]{
                            "" + MongolCode.Uni.ZWJ + previousChar + MongolCode.Uni.MVS + MongolCode.Uni.A,
                            "" + MongolCode.Uni.ZWJ + MongolCode.Uni.A + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ,
                            "" + MongolCode.Uni.MONGOLIAN_NIRUGU});
        } else {
            return new Key.PopupCandidates(
                    new String[]{
                            "" + MongolCode.Uni.A + MongolCode.Uni.FVS1,
                            "" + MongolCode.Uni.MONGOLIAN_NIRUGU},
                    new String[]{
                            "" + MongolCode.Uni.ZWJ + MongolCode.Uni.A + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ,
                            "" + MongolCode.Uni.MONGOLIAN_NIRUGU});
        }
    }

    private Key.PopupCandidates getCandidatesForE(boolean isIsolateOrInitial) {

        if (mIsShowingPunctuation) {
            return new Key.PopupCandidates(KEY_E_PUNCT_SUB);
        }

        if (isIsolateOrInitial) {
            return new Key.PopupCandidates(MongolCode.Uni.EE);
        }

        // medial || final
        char previousChar = getPreviousChar();
        if (MongolCode.isMvsConsonant(previousChar)
                && previousChar != MongolCode.Uni.QA && previousChar != MongolCode.Uni.GA) {
            // include MVS
            return new Key.PopupCandidates(
                    new String[]{
                            "" + MongolCode.Uni.MVS + MongolCode.Uni.E,
                            "" + MongolCode.Uni.EE},
                    new String[]{
                            "" + MongolCode.Uni.ZWJ + previousChar + MongolCode.Uni.MVS + MongolCode.Uni.E,
                            "" + MongolCode.Uni.EE});
        } else {
            return new Key.PopupCandidates(MongolCode.Uni.EE);
        }

    }

    private Key.PopupCandidates getCandidatesForI(boolean isIsolateOrInitial) {

        if (mIsShowingPunctuation) {
            return new Key.PopupCandidates(KEY_I_PUNCT_SUB);
        }

        if (!isIsolateOrInitial) {// medial/final
            char prevChar = getPreviousChar();
            if (MongolCode.isVowel(prevChar)) {
                return new Key.PopupCandidates(
                        new String[]{
                                "" + MongolCode.Uni.I + MongolCode.Uni.FVS2, // override double tooth I after vowel (Unicode 10.0 deviation)
                                //"" + MongolCode.Uni.ZWJ + MongolCode.Uni.I, // alternate method to override double tooth I
                                "" + MongolCode.Uni.I + MongolCode.Uni.FVS1},
                        new String[]{
                                "" + MongolCode.Uni.ZWJ + MongolCode.Uni.I + MongolCode.Uni.ZWJ,
                                "" + MongolCode.Uni.ZWJ + MongolCode.Uni.I + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ});
            } else {
                return new Key.PopupCandidates(
                        new String[]{
                                "" + MongolCode.Uni.I + MongolCode.Uni.FVS1},
                        new String[]{
                                "" + MongolCode.Uni.ZWJ + MongolCode.Uni.I + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ});
            }

        }
        return null;
    }

    private Key.PopupCandidates getCandidatesForO(boolean isIsolateOrInitial) {

        if (mIsShowingPunctuation) {
            return new Key.PopupCandidates(KEY_O_PUNCT_SUB);
        }

        if (!isIsolateOrInitial) { // medial/final
            return new Key.PopupCandidates(
                    new String[]{"" + MongolCode.Uni.U + MongolCode.Uni.FVS1,
                            "" + MongolCode.Uni.U + MongolCode.Uni.FVS1},
                    new String[]{
                            "" + MongolCode.Uni.ZWJ + MongolCode.Uni.U + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ,
                            "" + MongolCode.Uni.ZWJ + MongolCode.Uni.U + MongolCode.Uni.FVS1});
        }
        return null;
    }

    private Key.PopupCandidates getCandidatesForU(boolean isIsolateOrInitial) {

        if (mIsShowingPunctuation) {
            return new Key.PopupCandidates(KEY_U_PUNCT_SUB);
        }

        if (!isIsolateOrInitial) { // medial/final
            return new Key.PopupCandidates(
                    new String[]{"" + MongolCode.Uni.UE + MongolCode.Uni.FVS2,
                            "" + MongolCode.Uni.UE + MongolCode.Uni.FVS1},
                    new String[]{
                            "" + MongolCode.Uni.ZWJ + MongolCode.Uni.UE + MongolCode.Uni.FVS2 + MongolCode.Uni.ZWJ,
                            "" + MongolCode.Uni.ZWJ + MongolCode.Uni.UE + MongolCode.Uni.FVS1});
        }
        return null;
    }

    private Key.PopupCandidates getCandidatesForNA(boolean isIsolateOrInitial) {

        if (mIsShowingPunctuation) {
            return new Key.PopupCandidates(KEY_NA_PUNCT_SUB);
        }

        if (isIsolateOrInitial) {
            return new Key.PopupCandidates(MongolCode.Uni.ANG);
        }

        // medial/final
        return new Key.PopupCandidates(
                new String[]{"" + MongolCode.Uni.ANG,
                        "" + MongolCode.Uni.NA + MongolCode.Uni.ZWJ, // only(?) way to override dotted N before vowel in Unicode 10.0
                        "" + MongolCode.Uni.NA + MongolCode.Uni.FVS1},
                new String[]{
                        "" + MongolCode.Uni.ANG,
                        "" + MongolCode.Uni.ZWJ + MongolCode.Uni.NA + MongolCode.Uni.ZWJ,
                        "" + MongolCode.Uni.ZWJ + MongolCode.Uni.NA + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ});
    }

    private Key.PopupCandidates getCandidatesForBA() {
        if (mIsShowingPunctuation)
            return new Key.PopupCandidates(KEY_BA_PUNCT_SUB);
        return new Key.PopupCandidates(new String[]{"" + MongolCode.Uni.PA, "" + MongolCode.Uni.FA});
    }

    private Key.PopupCandidates getCandidatesForQA() {
        if (mIsShowingPunctuation)
            return new Key.PopupCandidates(KEY_QA_PUNCT_SUB);
        return new Key.PopupCandidates(MongolCode.Uni.HAA);
    }

    private Key.PopupCandidates getCandidatesForGA(boolean isIsolateOrInitial) {
        if (mIsShowingPunctuation)
            return new Key.PopupCandidates(KEY_GA_PUNCT_SUB);

        if (isIsolateOrInitial) {
            return new Key.PopupCandidates(MongolCode.Uni.KA);
        }

        // medial/final
        return new Key.PopupCandidates(
                new String[]{
                        "" + MongolCode.Uni.KA,
                        "" + MongolCode.Uni.GA + MongolCode.Uni.FVS1, // see note on MongolCode(FINA_GA_FVS1)
                        "" + MongolCode.Uni.GA + MongolCode.Uni.FVS2}, // see note on MongolCode(FINA_GA_FVS2)
                new String[]{
                        "" + MongolCode.Uni.KA,
                        "" + MongolCode.Uni.ZWJ + MongolCode.Uni.GA + MongolCode.Uni.FVS1,
                        "" + MongolCode.Uni.ZWJ + MongolCode.Uni.GA + MongolCode.Uni.FVS2});
    }

    private Key.PopupCandidates getCandidatesForMA() {
        if (mIsShowingPunctuation)
            return new Key.PopupCandidates(KEY_MA_PUNCT_SUB);
        return null;
    }

    private Key.PopupCandidates getCandidatesForLA() {
        if (mIsShowingPunctuation)
            return new Key.PopupCandidates(KEY_LA_PUNCT_SUB);
        return new Key.PopupCandidates(MongolCode.Uni.LHA);
    }

    private Key.PopupCandidates getCandidatesForSA() {
        if (mIsShowingPunctuation)
            return new Key.PopupCandidates(KEY_SA_PUNCT_SUB);
        return new Key.PopupCandidates(MongolCode.Uni.SHA);
    }

    private Key.PopupCandidates getCandidatesForTADA(boolean isIsolateOrInitial) {
        if (mIsShowingPunctuation)
            return new Key.PopupCandidates(KEY_DA_PUNCT_SUB);

        if (isIsolateOrInitial) {
            char prevChar = getPreviousChar();
            String[] unicode;
            if (prevChar == MongolCode.Uni.NNBS) {
                unicode = new String[]{"" + MongolCode.Uni.DA};
            } else {
                unicode = new String[]{"" + MongolCode.Uni.DA + MongolCode.Uni.FVS1};
            }
            return new Key.PopupCandidates(
                    unicode,
                    new String[]{"" + MongolCode.Uni.DA + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ});
            // TODO if this turns out to be an isolate then the FVS1 should be removed
        }

        // medial/final
        return new Key.PopupCandidates(
                new String[]{
                        "" + MongolCode.Uni.TA + MongolCode.Uni.FVS1,
                        "" + MongolCode.Uni.TA,
                        "" + MongolCode.Uni.DA + MongolCode.Uni.FVS1},
                new String[]{
                        "" + MongolCode.Uni.MONGOLIAN_NIRUGU +
                                MongolCode.Uni.TA + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ,
                        "" + MongolCode.Uni.ZWJ + MongolCode.Uni.TA,
                        "" + MongolCode.Uni.ZWJ + MongolCode.Uni.DA + MongolCode.Uni.FVS1});
    }

    private Key.PopupCandidates getCandidatesForCHA() {
        if (mIsShowingPunctuation)
            return new Key.PopupCandidates(KEY_CHA_PUNCT_SUB);
        return new Key.PopupCandidates(new String[]{
                "" + MongolCode.Uni.TSA,
                "" + MongolCode.Uni.CHI});
    }

    private Key.PopupCandidates getCandidatesForJA() {
        if (mIsShowingPunctuation)
            return new Key.PopupCandidates(KEY_JA_PUNCT_SUB);
        return new Key.PopupCandidates(new String[]{
                "" + MongolCode.Uni.ZA,
                "" + MongolCode.Uni.ZHI});
    }

    private Key.PopupCandidates getCandidatesForYA(boolean isIsolateOrInitial) {
        if (mIsShowingPunctuation)
            return new Key.PopupCandidates(KEY_YA_PUNCT_SUB);

        if (isIsolateOrInitial) {
            return new Key.PopupCandidates(
                    new String[]{
                            "" + MongolCode.Uni.WA,
                            "" + MongolCode.Uni.YA + MongolCode.Uni.FVS1},
                    new String[]{
                            "" + MongolCode.Uni.WA,
                            "" + MongolCode.Uni.YA + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ});
        }

        // medial/final
        return new Key.PopupCandidates(
                new String[]{
                        "" + MongolCode.Uni.WA,
                        "" + MongolCode.Uni.YA + MongolCode.Uni.FVS1},
                new String[]{
                        "" + MongolCode.Uni.WA,
                        "" + MongolCode.Uni.ZWJ + MongolCode.Uni.YA + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ});
    }

    private Key.PopupCandidates getCandidatesForRA() {
        if (mIsShowingPunctuation)
            return new Key.PopupCandidates(KEY_RA_PUNCT_SUB);
        return new Key.PopupCandidates(MongolCode.Uni.ZRA);
    }

    private Key.PopupCandidates getCandidatesForComma() {
        return new Key.PopupCandidates(new String[]{
                "" + MongolCode.Uni.VERTICAL_QUESTION_MARK,
                "" + MongolCode.Uni.MONGOLIAN_FULL_STOP,
                "" + MongolCode.Uni.VERTICAL_EXCLAMATION_MARK});
    }

    private Key.PopupCandidates getCandidatesForSpace() {
        return getCandidatesForSuffix();
    }


    @Override
    public String getDisplayName() {
        return DISPLAY_NAME;
    }


    @Override
    public void onKeyboardKeyClick() {
        mIsShowingPunctuation = !mIsShowingPunctuation;
        if (mIsShowingPunctuation) {
            setPuncuationKeyValues();
        } else {
            setKeyValues();
        }
    }
}
