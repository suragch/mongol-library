package net.studymongolian.mongollibrary;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;


import java.util.HashMap;
import java.util.Map;

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
    //protected KeyImage mKeyKeyboard; // defined in super class
    protected KeyText mKeyComma;
    protected KeyText mKeySpace;
    protected KeyImage mKeyBackspace;
    protected KeyImage mKeyReturn;

    // These are all input values (some display values are different)
    private static final String KEY_A = String.valueOf(MongolCode.Uni.A);
    private static final String KEY_E = String.valueOf(MongolCode.Uni.E);
    private static final String KEY_I = String.valueOf(MongolCode.Uni.I);
    private static final String KEY_O = String.valueOf(MongolCode.Uni.U);
    private static final String KEY_U = String.valueOf(MongolCode.Uni.UE);
    private static final String KEY_NA = String.valueOf(MongolCode.Uni.NA);
    private static final String KEY_BA = String.valueOf(MongolCode.Uni.BA);
    private static final String KEY_QA = String.valueOf(MongolCode.Uni.QA);
    private static final String KEY_GA = String.valueOf(MongolCode.Uni.GA);
    private static final String KEY_MA = String.valueOf(MongolCode.Uni.MA);
    private static final String KEY_LA = String.valueOf(MongolCode.Uni.LA);
    private static final String KEY_SA = String.valueOf(MongolCode.Uni.SA);
    private static final String KEY_DA = String.valueOf(MongolCode.Uni.DA);
    private static final String KEY_CHA = String.valueOf(MongolCode.Uni.CHA);
    private static final String KEY_JA = String.valueOf(MongolCode.Uni.JA);
    private static final String KEY_YA = String.valueOf(MongolCode.Uni.YA);
    private static final String KEY_RA = String.valueOf(MongolCode.Uni.RA);
    private static final String KEY_COMMA = String.valueOf(MongolCode.Uni.MONGOLIAN_COMMA);
    private static final String KEY_SPACE = " ";

    private static final String KEY_A_SUB = "";
    private static final String KEY_E_SUB = String.valueOf(MongolCode.Uni.EE);
    private static final String KEY_I_SUB = "";
    private static final String KEY_O_SUB = "";
    private static final String KEY_U_SUB = "";
    private static final String KEY_NA_SUB = String.valueOf(MongolCode.Uni.ANG);
    private static final String KEY_BA_SUB = String.valueOf(MongolCode.Uni.PA);
    private static final String KEY_QA_SUB = String.valueOf(MongolCode.Uni.HAA);
    private static final String KEY_GA_SUB = String.valueOf(MongolCode.Uni.KA);
    private static final String KEY_MA_SUB = "";
    private static final String KEY_LA_SUB = String.valueOf(MongolCode.Uni.LHA);
    private static final String KEY_SA_SUB = String.valueOf(MongolCode.Uni.SHA);
    private static final String KEY_DA_SUB = String.valueOf(MongolCode.Uni.DA); // DA is default
    private static final String KEY_CHA_SUB = String.valueOf(MongolCode.Uni.TSA);
    private static final String KEY_JA_SUB = String.valueOf(MongolCode.Uni.ZA);
    private static final String KEY_YA_SUB = String.valueOf(MongolCode.Uni.WA);
    private static final String KEY_RA_SUB = String.valueOf(MongolCode.Uni.ZRA);

    private static final String KEY_A_PUNCT = String.valueOf(MongolCode.Uni.VERTICAL_LEFT_PARENTHESIS);
    private static final String KEY_E_PUNCT = String.valueOf(MongolCode.Uni.VERTICAL_RIGHT_PARENTHESIS);
    private static final String KEY_I_PUNCT = String.valueOf(MongolCode.Uni.VERTICAL_LEFT_DOUBLE_ANGLE_BRACKET);
    private static final String KEY_O_PUNCT = String.valueOf(MongolCode.Uni.VERTICAL_RIGHT_DOUBLE_ANGLE_BRACKET);
    private static final String KEY_U_PUNCT = String.valueOf(MongolCode.Uni.MIDDLE_DOT);
    private static final String KEY_NA_PUNCT = "1";
    private static final String KEY_BA_PUNCT = "2";
    private static final String KEY_QA_PUNCT = "3";
    private static final String KEY_GA_PUNCT = "4";
    private static final String KEY_MA_PUNCT = "5";
    private static final String KEY_LA_PUNCT = String.valueOf(MongolCode.Uni.VERTICAL_EM_DASH);
    private static final String KEY_SA_PUNCT = "6";
    private static final String KEY_DA_PUNCT = "7";
    private static final String KEY_CHA_PUNCT = "8";
    private static final String KEY_JA_PUNCT = "9";
    private static final String KEY_YA_PUNCT = "0";
    private static final String KEY_RA_PUNCT = String.valueOf(MongolCode.Uni.QUESTION_EXCLAMATION_MARK);

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

    // Keys with different display values
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


        // Row 1

        mKeyA = new KeyText(context);
        initTextKey(mKeyA, KEY_A, KEY_A_PUNCT);

        mKeyE = new KeyText(context);
        initTextKey(mKeyE, KEY_E, KEY_E_PUNCT);

        mKeyI = new KeyText(context);
        initTextKey(mKeyI, KEY_I, KEY_I_PUNCT);

        mKeyO = new KeyText(context);
        initTextKey(mKeyO, KEY_O, KEY_O_PUNCT);

        mKeyU = new KeyText(context);
        initTextKey(mKeyU, KEY_U, KEY_U_PUNCT);

        // Row 2

        mKeyNA = new KeyText(context);
        initTextKey(mKeyNA, KEY_NA, KEY_NA_PUNCT);

        mKeyBA = new KeyText(context);
        initTextKey(mKeyBA, KEY_BA, KEY_BA_PUNCT);

        mKeyQA = new KeyText(context);
        initTextKey(mKeyQA, KEY_QA, KEY_QA_PUNCT);

        mKeyGA = new KeyText(context);
        initTextKey(mKeyGA, KEY_GA, KEY_GA_PUNCT);

        mKeyMA = new KeyText(context);
        initTextKey(mKeyMA, KEY_MA, KEY_MA_PUNCT);

        mKeyLA = new KeyText(context);
        initTextKey(mKeyLA, KEY_LA, KEY_LA_PUNCT);

        // Row 3

        mKeySA = new KeyText(context);
        initTextKey(mKeySA, KEY_SA, KEY_SA_PUNCT);

        mKeyTADA = new KeyText(context);
        initTextKey(mKeyTADA, KEY_DA, KEY_DA_PUNCT);

        mKeyCHA = new KeyText(context);
        initTextKey(mKeyCHA, KEY_CHA, KEY_CHA_PUNCT);

        mKeyJA = new KeyText(context);
        initTextKey(mKeyJA, KEY_JA, KEY_JA_PUNCT);

        mKeyYA = new KeyText(context);
        initTextKey(mKeyYA, KEY_YA, KEY_YA_PUNCT);

        mKeyRA = new KeyText(context);
        initTextKey(mKeyRA, KEY_RA, KEY_RA_PUNCT);

        // Row 4

        // keyboard
        mKeyKeyboard = new KeyImage(context);
        mKeyKeyboard.setImage(getKeyboardImage());
        mKeyKeyboard.setOnTouchListener(textKeyTouchListener);
        addView(mKeyKeyboard);

        // comma
        mKeyComma = new KeyText(context);
        initTextKey(mKeyComma, KEY_COMMA, KEY_COMMA);
        mKeyComma.setText(KEY_COMMA);
        mKeyComma.setSubText(MongolCode.Uni.VERTICAL_QUESTION_MARK);

        // space
        mKeySpace = new KeyText(context);
        initTextKey(mKeySpace, " ", " ");
        mKeySpace.setText(KEY_SPACE);
        mKeySpace.setSubText(KEY_SPACE_SUB_DISPLAY);

        // return
        mKeyReturn = new KeyImage(context);
        mKeyReturn.setImage(getReturnImage());
        mKeyReturn.setOnTouchListener(textKeyTouchListener);
        mKeyValues.put(mKeyReturn, "\n");
        mKeyPunctuationValues.put(mKeyReturn, "\n");
        addView(mKeyReturn);

        // backspace
        mKeyBackspace = new KeyImage(context);
        mKeyBackspace.setImage(getBackspaceImage());
        mKeyBackspace.setOnTouchListener(handleBackspace);
        addView(mKeyBackspace);

        setDisplayText(mIsShowingPunctuation);
        applyThemeToKeys();
    }

    public void setDisplayText(boolean isShowingPunctuation) {

        if (isShowingPunctuation) {
            mKeyA.setText(KEY_A_PUNCT);
            mKeyE.setText(KEY_E_PUNCT);
            mKeyI.setText(KEY_I_PUNCT);
            mKeyO.setText(KEY_O_PUNCT);
            mKeyU.setText(KEY_U_PUNCT);
            mKeyNA.setText(KEY_NA_PUNCT);
            mKeyBA.setText(KEY_BA_PUNCT);
            mKeyQA.setText(KEY_QA_PUNCT);
            mKeyGA.setText(KEY_GA_PUNCT);
            mKeyMA.setText(KEY_MA_PUNCT);
            mKeyLA.setText(KEY_LA_PUNCT);
            mKeySA.setText(KEY_SA_PUNCT);
            mKeyTADA.setText(KEY_DA_PUNCT);
            mKeyCHA.setText(KEY_CHA_PUNCT);
            mKeyJA.setText(KEY_JA_PUNCT);
            mKeyYA.setText(KEY_YA_PUNCT);
            mKeyRA.setText(KEY_RA_PUNCT);

            mKeyNA.setRotatedPrimaryText(false);
            mKeyBA.setRotatedPrimaryText(false);
            mKeyQA.setRotatedPrimaryText(false);
            mKeyGA.setRotatedPrimaryText(false);
            mKeyMA.setRotatedPrimaryText(false);
            mKeySA.setRotatedPrimaryText(false);
            mKeyTADA.setRotatedPrimaryText(false);
            mKeyCHA.setRotatedPrimaryText(false);
            mKeyJA.setRotatedPrimaryText(false);
            mKeyYA.setRotatedPrimaryText(false);

            mKeyA.setSubText(KEY_A_PUNCT_SUB);
            mKeyE.setSubText(KEY_E_PUNCT_SUB);
            mKeyI.setSubText(KEY_I_PUNCT_SUB);
            mKeyO.setSubText(KEY_O_PUNCT_SUB);
            mKeyU.setSubText(KEY_U_PUNCT_SUB);
            mKeyNA.setSubText(KEY_NA_PUNCT_SUB);
            mKeyBA.setSubText(KEY_BA_PUNCT_SUB);
            mKeyQA.setSubText(KEY_QA_PUNCT_SUB);
            mKeyGA.setSubText(KEY_GA_PUNCT_SUB);
            mKeyMA.setSubText(KEY_MA_PUNCT_SUB);
            mKeyLA.setSubText(KEY_LA_PUNCT_SUB);
            mKeySA.setSubText(KEY_SA_PUNCT_SUB);
            mKeyTADA.setSubText(KEY_DA_PUNCT_SUB);
            mKeyCHA.setSubText(KEY_CHA_PUNCT_SUB);
            mKeyJA.setSubText(KEY_JA_PUNCT_SUB);
            mKeyYA.setSubText(KEY_YA_PUNCT_SUB);
            mKeyRA.setSubText(KEY_RA_PUNCT_SUB);
        } else {
            mKeyA.setText(KEY_A);
            mKeyE.setText(KEY_E);
            mKeyI.setText(KEY_I);
            mKeyO.setText(KEY_O);
            mKeyU.setText("" + KEY_U + MongolCode.Uni.ZWJ); // display
            mKeyNA.setText(KEY_NA);
            mKeyBA.setText(KEY_BA);
            mKeyQA.setText(KEY_QA);
            mKeyGA.setText(KEY_GA);
            mKeyMA.setText(KEY_MA);
            mKeyLA.setText(KEY_LA);
            mKeySA.setText(KEY_SA);
            mKeyTADA.setText("" + MongolCode.Uni.TA); // display
            mKeyCHA.setText(KEY_CHA);
            mKeyJA.setText(KEY_JA);
            mKeyYA.setText(KEY_YA);
            mKeyRA.setText(KEY_RA);

            mKeyNA.setRotatedPrimaryText(true);
            mKeyBA.setRotatedPrimaryText(true);
            mKeyQA.setRotatedPrimaryText(true);
            mKeyGA.setRotatedPrimaryText(true);
            mKeyMA.setRotatedPrimaryText(true);
            mKeySA.setRotatedPrimaryText(true);
            mKeyTADA.setRotatedPrimaryText(true);
            mKeyCHA.setRotatedPrimaryText(true);
            mKeyYA.setRotatedPrimaryText(true);
            mKeyMA.setRotatedPrimaryText(true);

            mKeyA.setSubText(KEY_A_SUB);
            mKeyE.setSubText(KEY_E_SUB);
            mKeyI.setSubText(KEY_I_SUB);
            mKeyO.setSubText(KEY_O_SUB);
            mKeyU.setSubText(KEY_U_SUB);
            mKeyNA.setSubText(KEY_NA_SUB);
            mKeyBA.setSubText(KEY_BA_SUB);
            mKeyQA.setSubText(KEY_QA_SUB);
            mKeyGA.setSubText(KEY_GA_SUB);
            mKeyMA.setSubText(KEY_MA_SUB);
            mKeyLA.setSubText(KEY_LA_SUB);
            mKeySA.setSubText(KEY_SA_SUB);
            mKeyTADA.setSubText(KEY_DA_SUB);
            mKeyCHA.setSubText(KEY_CHA_SUB);
            mKeyJA.setSubText(KEY_JA_SUB);
            mKeyYA.setSubText(KEY_YA_SUB);
            mKeyRA.setSubText(KEY_RA_SUB);
        }
    }

    public PopupCandidates getPopupCandidates(Key key) {
        // these are the choices to display in the popup (and corresponding unicode values)
        PopupCandidates candidates = null;

        // get the appropriate candidates based on the key pressed
        if (key == mKeyA) {
            candidates = getCandidatesForA(isIsolateOrInitial());
        } else if (key == mKeyE) {
            candidates = getCandidatesForE(isIsolateOrInitial());
        } else if (key == mKeyI) {
            candidates = getCandidatesForI(isIsolateOrInitial());
        } else if (key == mKeyO) {
            candidates = getCandidatesForO(isIsolateOrInitial());
        } else if (key == mKeyU) {
            candidates = getCandidatesForU(isIsolateOrInitial());
        } else if (key == mKeyNA) {
            candidates = getCandidatesForNA(isIsolateOrInitial());
        } else if (key == mKeyBA) {
            candidates = getCandidatesForBA();
        } else if (key == mKeyQA) {
            candidates = getCandidatesForQA();
        } else if (key == mKeyGA) {
            candidates = getCandidatesForGA(isIsolateOrInitial());
        } else if (key == mKeyMA) {
            candidates = getCandidatesForMA();
        } else if (key == mKeyLA) {
            candidates = getCandidatesForLA();
        } else if (key == mKeySA) {
            candidates = getCandidatesForSA();
        } else if (key == mKeyTADA) {
            candidates = getCandidatesForTADA(isIsolateOrInitial());
        } else if (key == mKeyCHA) {
            candidates = getCandidatesForCHA();
        } else if (key == mKeyJA) {
            candidates = getCandidatesForJA();
        } else if (key == mKeyYA) {
            candidates = getCandidatesForYA(isIsolateOrInitial());
        } else if (key == mKeyRA) {
            candidates = getCandidatesForRA();
        } else if (key == mKeyKeyboard) {
            candidates = getCandidatesForKeyboard();
        } else if (key == mKeyComma) {
            candidates = getCandidatesForComma();
        } else if (key == mKeySpace) {
            candidates = getCandidatesForSpace();
        }

        return candidates;
    }


    private PopupCandidates getCandidatesForA(boolean isIsolateOrInitial) {

        if (mIsShowingPunctuation) {
            return new PopupCandidates(KEY_A_PUNCT_SUB);
        }

        if (isIsolateOrInitial) {
            return new PopupCandidates(new String[]{
                    "" + MongolCode.Uni.A + MongolCode.Uni.FVS1,
                    "" + MongolCode.Uni.MONGOLIAN_NIRUGU});
        }

        // medial || final
        char previousChar = getPreviousChar();
        if (MongolCode.isMvsConsonant(previousChar)) {
            // include MVS
            return new PopupCandidates(
                    new String[]{
                            "" + MongolCode.Uni.MVS + MongolCode.Uni.A,
                            "" + MongolCode.Uni.A + MongolCode.Uni.FVS1,
                            "" + MongolCode.Uni.MONGOLIAN_NIRUGU},
                    new String[]{
                            "" + MongolCode.Uni.ZWJ + previousChar + MongolCode.Uni.MVS + MongolCode.Uni.A,
                            "" + MongolCode.Uni.ZWJ + MongolCode.Uni.A + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ,
                            "" + MongolCode.Uni.MONGOLIAN_NIRUGU});
        } else {
            return new PopupCandidates(
                    new String[]{
                            "" + MongolCode.Uni.A + MongolCode.Uni.FVS1,
                            "" + MongolCode.Uni.MONGOLIAN_NIRUGU},
                    new String[]{
                            "" + MongolCode.Uni.ZWJ + MongolCode.Uni.A + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ,
                            "" + MongolCode.Uni.MONGOLIAN_NIRUGU});
        }
    }

    private PopupCandidates getCandidatesForE(boolean isIsolateOrInitial) {

        if (mIsShowingPunctuation) {
            return new PopupCandidates(KEY_E_PUNCT_SUB);
        }

        if (isIsolateOrInitial) {
            return new PopupCandidates(MongolCode.Uni.EE);
        }

        // medial || final
        char previousChar = getPreviousChar();
        if (MongolCode.isMvsConsonant(previousChar)
                && previousChar != MongolCode.Uni.QA && previousChar != MongolCode.Uni.GA) {
            // include MVS
            return new PopupCandidates(
                    new String[]{
                            "" + MongolCode.Uni.MVS + MongolCode.Uni.E,
                            "" + MongolCode.Uni.EE},
                    new String[]{
                            "" + MongolCode.Uni.ZWJ + previousChar + MongolCode.Uni.MVS + MongolCode.Uni.E,
                            "" + MongolCode.Uni.EE});
        } else {
            return new PopupCandidates(MongolCode.Uni.EE);
        }

    }

    private PopupCandidates getCandidatesForI(boolean isIsolateOrInitial) {

        if (mIsShowingPunctuation) {
            return new PopupCandidates(KEY_I_PUNCT_SUB);
        }

        if (!isIsolateOrInitial) {// medial/final
            char prevChar = getPreviousChar();
            if (MongolCode.isVowel(prevChar)) {
                return new PopupCandidates(
                        new String[]{
                                "" + MongolCode.Uni.I + MongolCode.Uni.FVS2, // override double tooth I after vowel (Unicode 10.0 deviation)
                                //"" + MongolCode.Uni.ZWJ + MongolCode.Uni.I, // alternate method to override double tooth I
                                "" + MongolCode.Uni.I + MongolCode.Uni.FVS1},
                        new String[]{
                                "" + MongolCode.Uni.ZWJ + MongolCode.Uni.I + MongolCode.Uni.ZWJ,
                                "" + MongolCode.Uni.ZWJ + MongolCode.Uni.I + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ});
            } else {
                return new PopupCandidates(
                        new String[]{
                                "" + MongolCode.Uni.I + MongolCode.Uni.FVS1},
                        new String[]{
                                "" + MongolCode.Uni.ZWJ + MongolCode.Uni.I + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ});
            }

        }
        return null;
    }

    private PopupCandidates getCandidatesForO(boolean isIsolateOrInitial) {

        if (mIsShowingPunctuation) {
            return new PopupCandidates(KEY_O_PUNCT_SUB);
        }

        if (!isIsolateOrInitial) { // medial/final
            return new PopupCandidates(
                    new String[]{"" + MongolCode.Uni.U + MongolCode.Uni.FVS1,
                            "" + MongolCode.Uni.U + MongolCode.Uni.FVS1},
                    new String[]{
                            "" + MongolCode.Uni.ZWJ + MongolCode.Uni.U + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ,
                            "" + MongolCode.Uni.ZWJ + MongolCode.Uni.U + MongolCode.Uni.FVS1});
        }
        return null;
    }

    private PopupCandidates getCandidatesForU(boolean isIsolateOrInitial) {

        if (mIsShowingPunctuation) {
            return new PopupCandidates(KEY_U_PUNCT_SUB);
        }

        if (!isIsolateOrInitial) { // medial/final
            return new PopupCandidates(
                    new String[]{"" + MongolCode.Uni.UE + MongolCode.Uni.FVS2,
                            "" + MongolCode.Uni.UE + MongolCode.Uni.FVS1},
                    new String[]{
                            "" + MongolCode.Uni.ZWJ + MongolCode.Uni.UE + MongolCode.Uni.FVS2 + MongolCode.Uni.ZWJ,
                            "" + MongolCode.Uni.ZWJ + MongolCode.Uni.UE + MongolCode.Uni.FVS1});
        }
        return null;
    }

    private PopupCandidates getCandidatesForNA(boolean isIsolateOrInitial) {

        if (mIsShowingPunctuation) {
            return new PopupCandidates(KEY_NA_PUNCT_SUB);
        }

        if (isIsolateOrInitial) {
            return new PopupCandidates(MongolCode.Uni.ANG);
        }

        // medial/final
        return new PopupCandidates(
                new String[]{"" + MongolCode.Uni.ANG,
                        "" + MongolCode.Uni.NA + MongolCode.Uni.ZWJ, // only(?) way to override dotted N before vowel in Unicode 10.0
                        "" + MongolCode.Uni.NA + MongolCode.Uni.FVS1},
                new String[]{
                        "" + MongolCode.Uni.ANG,
                        "" + MongolCode.Uni.ZWJ + MongolCode.Uni.NA + MongolCode.Uni.ZWJ,
                        "" + MongolCode.Uni.ZWJ + MongolCode.Uni.NA + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ});
    }

    private PopupCandidates getCandidatesForBA() {
        if (mIsShowingPunctuation)
            return new PopupCandidates(KEY_BA_PUNCT_SUB);
        return new PopupCandidates(new String[]{"" + MongolCode.Uni.PA, "" + MongolCode.Uni.FA});
    }

    private PopupCandidates getCandidatesForQA() {
        if (mIsShowingPunctuation)
            return new PopupCandidates(KEY_QA_PUNCT_SUB);
        return new PopupCandidates(MongolCode.Uni.HAA);
    }

    private PopupCandidates getCandidatesForGA(boolean isIsolateOrInitial) {
        if (mIsShowingPunctuation)
            return new PopupCandidates(KEY_GA_PUNCT_SUB);

        if (isIsolateOrInitial) {
            return new PopupCandidates(MongolCode.Uni.KA);
        }

        // medial/final
        return new PopupCandidates(
                new String[]{
                        "" + MongolCode.Uni.KA,
                        "" + MongolCode.Uni.GA + MongolCode.Uni.FVS1, // see note on MongolCode(FINA_GA_FVS1)
                        "" + MongolCode.Uni.GA + MongolCode.Uni.FVS2}, // see note on MongolCode(FINA_GA_FVS2)
                new String[]{
                        "" + MongolCode.Uni.KA,
                        "" + MongolCode.Uni.ZWJ + MongolCode.Uni.GA + MongolCode.Uni.FVS1,
                        "" + MongolCode.Uni.ZWJ + MongolCode.Uni.GA + MongolCode.Uni.FVS2});
    }

    private PopupCandidates getCandidatesForMA() {
        if (mIsShowingPunctuation)
            return new PopupCandidates(KEY_MA_PUNCT_SUB);
        return null;
    }

    private PopupCandidates getCandidatesForLA() {
        if (mIsShowingPunctuation)
            return new PopupCandidates(KEY_LA_PUNCT_SUB);
        return new PopupCandidates(MongolCode.Uni.LHA);
    }

    private PopupCandidates getCandidatesForSA() {
        if (mIsShowingPunctuation)
            return new PopupCandidates(KEY_SA_PUNCT_SUB);
        return new PopupCandidates(MongolCode.Uni.SHA);
    }

    private PopupCandidates getCandidatesForTADA(boolean isIsolateOrInitial) {
        if (mIsShowingPunctuation)
            return new PopupCandidates(KEY_DA_PUNCT_SUB);

        if (isIsolateOrInitial) {
            char prevChar = getPreviousChar();
            String[] unicode;
            if (prevChar == MongolCode.Uni.NNBS) {
                unicode = new String[]{"" + MongolCode.Uni.DA};
            } else {
                unicode = new String[]{"" + MongolCode.Uni.DA + MongolCode.Uni.FVS1};
            }
            return new PopupCandidates(
                    unicode,
                    new String[]{"" + MongolCode.Uni.DA + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ});
            // TODO if this turns out to be an isolate then the FVS1 should be removed
        }

        // medial/final
        return new PopupCandidates(
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

    private PopupCandidates getCandidatesForCHA() {
        if (mIsShowingPunctuation)
            return new PopupCandidates(KEY_CHA_PUNCT_SUB);
        return new PopupCandidates(new String[]{
                "" + MongolCode.Uni.TSA,
                "" + MongolCode.Uni.CHI});
    }

    private PopupCandidates getCandidatesForJA() {
        if (mIsShowingPunctuation)
            return new PopupCandidates(KEY_JA_PUNCT_SUB);
        return new PopupCandidates(new String[]{
                "" + MongolCode.Uni.ZA,
                "" + MongolCode.Uni.ZHI});
    }

    private PopupCandidates getCandidatesForYA(boolean isIsolateOrInitial) {
        if (mIsShowingPunctuation)
            return new PopupCandidates(KEY_YA_PUNCT_SUB);

        if (isIsolateOrInitial) {
            return new PopupCandidates(
                    new String[]{
                            "" + MongolCode.Uni.WA,
                            "" + MongolCode.Uni.YA + MongolCode.Uni.FVS1},
                    new String[]{
                            "" + MongolCode.Uni.WA,
                            "" + MongolCode.Uni.YA + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ});
        }

        // medial/final
        return new PopupCandidates(
                new String[]{
                        "" + MongolCode.Uni.WA,
                        "" + MongolCode.Uni.YA + MongolCode.Uni.FVS1},
                new String[]{
                        "" + MongolCode.Uni.WA,
                        "" + MongolCode.Uni.ZWJ + MongolCode.Uni.YA + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ});
    }

    private PopupCandidates getCandidatesForRA() {
        if (mIsShowingPunctuation)
            return new PopupCandidates(KEY_RA_PUNCT_SUB);
        return new PopupCandidates(MongolCode.Uni.ZRA);
    }

    private PopupCandidates getCandidatesForComma() {
        return new PopupCandidates(new String[]{
                "" + MongolCode.Uni.VERTICAL_QUESTION_MARK,
                "" + MongolCode.Uni.MONGOLIAN_FULL_STOP,
                "" + MongolCode.Uni.VERTICAL_EXCLAMATION_MARK});
    }

    private PopupCandidates getCandidatesForSpace() {
        return getCandidatesForSuffix();
    }


    @Override
    public String getDisplayName() {
        return DISPLAY_NAME;
    }


}
