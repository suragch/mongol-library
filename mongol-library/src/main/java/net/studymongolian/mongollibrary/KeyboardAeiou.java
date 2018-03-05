package net.studymongolian.mongollibrary;

import android.content.Context;

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
    protected KeyKeyboardChooser mKeyKeyboard;
    protected KeyText mKeyComma;
    protected KeyText mKeySpace;
    protected KeyBackspace mKeyBackspace;
    protected KeyImage mKeyReturn;


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
        setNonChangingKeyValues();
        setKeyImages();
        setListeners();
        addKeysToKeyboard();
        applyThemeToKeys();
        //setPreferredCandidateLocation(CandidateLocation.VERTICAL_LEFT);
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

        rotatePrimaryText();

        // Row 1

        mKeyA.setText(MongolCode.Uni.A);
        mKeyA.setSubText("");

        mKeyE.setText(MongolCode.Uni.E);
        mKeyE.setSubText(MongolCode.Uni.EE);

        mKeyI.setText(MongolCode.Uni.I);
        mKeyI.setSubText("");

        mKeyO.setText(MongolCode.Uni.U);
        mKeyO.setSubText("");

        mKeyU.setText(MongolCode.Uni.UE);
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

        mKeyTADA.setText(MongolCode.Uni.DA, MongolCode.Uni.TA);
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

    private void rotatePrimaryText() {
        mKeyNA.setRotatedPrimaryText(true);
        mKeyBA.setRotatedPrimaryText(true);
        mKeyQA.setRotatedPrimaryText(true);
        mKeyGA.setRotatedPrimaryText(true);
        mKeyMA.setRotatedPrimaryText(true);
        mKeySA.setRotatedPrimaryText(true);
        mKeyTADA.setRotatedPrimaryText(true);
        mKeyCHA.setRotatedPrimaryText(true);
        mKeyJA.setRotatedPrimaryText(true);
        mKeyYA.setRotatedPrimaryText(true);
    }

    private void setPuncuationKeyValues() {

        dontRotatePrimaryTextForSelectKeys();

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

    private void dontRotatePrimaryTextForSelectKeys() {
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
    }

    private void setNonChangingKeyValues() {
        mKeyComma.setText(MongolCode.Uni.MONGOLIAN_COMMA);
        mKeyComma.setSubText(MongolCode.Uni.VERTICAL_QUESTION_MARK);
        mKeySpace.setText(" ");
        mKeySpace.setSubText(KEY_SPACE_SUB_DISPLAY);
        mKeyReturn.setText(NEWLINE);
    }

    private void setKeyImages() {
        mKeyBackspace.setImage(getBackspaceImage());
        mKeyKeyboard.setImage(getKeyboardImage());
        mKeyReturn.setImage(getReturnImage());
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

    public PopupKeyCandidate[] getPopupCandidates(Key key) {

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
            return getCandidatesForKeyboardKey();
        } else if (key == mKeyComma) {
            return getCandidatesForComma();
        } else if (key == mKeySpace) {
            return getCandidatesForSpace();
        }

        return null;
    }

    private PopupKeyCandidate[] getCandidatesForA(boolean isIsolateOrInitial) {

        if (mIsShowingPunctuation) {
            return PopupKeyCandidate.createArray(KEY_A_PUNCT_SUB);
        }

        if (isIsolateOrInitial) {
            return PopupKeyCandidate.createArray(new String[]{
                    "" + MongolCode.Uni.A + MongolCode.Uni.FVS1,
                    "" + MongolCode.Uni.MONGOLIAN_NIRUGU});
        }

        // medial || final
        PopupKeyCandidate medial_A_FVS1 = new PopupKeyCandidate(
                "" + MongolCode.Uni.A + MongolCode.Uni.FVS1,
                "" + MongolCode.Uni.ZWJ + MongolCode.Uni.A + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ,
                "" + MongolCode.Uni.A + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ);
        PopupKeyCandidate nirugu = new PopupKeyCandidate(MongolCode.Uni.MONGOLIAN_NIRUGU);
        char previousChar = getPreviousChar();
        if (MongolCode.isMvsConsonant(previousChar)) {
            PopupKeyCandidate mvs_a = new PopupKeyCandidate(
                    "" + MongolCode.Uni.MVS + MongolCode.Uni.A,
                    "" + MongolCode.Uni.ZWJ + previousChar + MongolCode.Uni.MVS + MongolCode.Uni.A,
                    null);
            // include MVS
            return new PopupKeyCandidate[]{mvs_a, medial_A_FVS1, nirugu};
        } else {
            return new PopupKeyCandidate[]{medial_A_FVS1, nirugu};
        }
    }


    private PopupKeyCandidate[] getCandidatesForE(boolean isIsolateOrInitial) {

        if (mIsShowingPunctuation) {
            return PopupKeyCandidate.createArray(KEY_E_PUNCT_SUB);
        }

        if (isIsolateOrInitial) {
            return PopupKeyCandidate.createArray(MongolCode.Uni.EE);
        }

        // medial || final
        char previousChar = getPreviousChar();
        PopupKeyCandidate ee = new PopupKeyCandidate(MongolCode.Uni.EE);
        if (MongolCode.isMvsConsonant(previousChar)
                && previousChar != MongolCode.Uni.QA && previousChar != MongolCode.Uni.GA) {
            PopupKeyCandidate mvs_E = new PopupKeyCandidate(
                    "" + MongolCode.Uni.MVS + MongolCode.Uni.E,
                    "" + MongolCode.Uni.ZWJ + previousChar + MongolCode.Uni.MVS + MongolCode.Uni.E);
            return new PopupKeyCandidate[]{mvs_E, ee};
        } else {
            return new PopupKeyCandidate[]{ee};
        }

    }

    private PopupKeyCandidate[] getCandidatesForI(boolean isIsolateOrInitial) {

        if (mIsShowingPunctuation) {
            return PopupKeyCandidate.createArray(KEY_I_PUNCT_SUB);
        }

        if (isIsolateOrInitial)
            return null;

        // medial/final
        PopupKeyCandidate medial_I_FVS1 = new PopupKeyCandidate(
                "" + MongolCode.Uni.I + MongolCode.Uni.FVS1,
                "" + MongolCode.Uni.ZWJ + MongolCode.Uni.I + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ,
                "" + MongolCode.Uni.I + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ);
        char prevChar = getPreviousChar();
        if (MongolCode.isVowel(prevChar)) {
            // override double tooth I after vowel (Unicode 10.0 deviation)
            // ("" + MongolCode.Uni.ZWJ + MongolCode.Uni.I) is an alternate method to override double tooth I
            PopupKeyCandidate medial_I_FVS2 = new PopupKeyCandidate(
                    "" + MongolCode.Uni.I + MongolCode.Uni.FVS2,
                    "" + MongolCode.Uni.ZWJ + MongolCode.Uni.I + MongolCode.Uni.ZWJ,
                    "" + MongolCode.Uni.I + MongolCode.Uni.FVS2 + MongolCode.Uni.ZWJ);
            return new PopupKeyCandidate[]{medial_I_FVS2, medial_I_FVS1};
        } else {
            return new PopupKeyCandidate[]{medial_I_FVS1};
        }

    }

    private PopupKeyCandidate[] getCandidatesForO(boolean isIsolateOrInitial) {

        if (mIsShowingPunctuation) {
            return PopupKeyCandidate.createArray(KEY_O_PUNCT_SUB);
        }

        if (isIsolateOrInitial) {
            return PopupKeyCandidate.createArray(MongolCode.Uni.O);
        }

        // medial/final
        PopupKeyCandidate medial_U_FVS1 = new PopupKeyCandidate(
                "" + MongolCode.Uni.U + MongolCode.Uni.FVS1,
                "" + MongolCode.Uni.ZWJ + MongolCode.Uni.U + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ,
                "" + MongolCode.Uni.U + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ);
        PopupKeyCandidate final_U_FVS1 = new PopupKeyCandidate(
                "" + MongolCode.Uni.U + MongolCode.Uni.FVS1,
                "" + MongolCode.Uni.ZWJ + MongolCode.Uni.U + MongolCode.Uni.FVS1);
        return new PopupKeyCandidate[]{medial_U_FVS1, final_U_FVS1};
    }

    private PopupKeyCandidate[] getCandidatesForU(boolean isIsolateOrInitial) {

        if (mIsShowingPunctuation) {
            return PopupKeyCandidate.createArray(KEY_U_PUNCT_SUB);
        }

        if (isIsolateOrInitial) {
            return PopupKeyCandidate.createArray(MongolCode.Uni.OE);
        }

        // medial/final
        PopupKeyCandidate medial_UE_FVS2 = new PopupKeyCandidate(
                "" + MongolCode.Uni.UE + MongolCode.Uni.FVS2,
                "" + MongolCode.Uni.ZWJ + MongolCode.Uni.UE + MongolCode.Uni.FVS2 + MongolCode.Uni.ZWJ,
                "" + MongolCode.Uni.UE + MongolCode.Uni.FVS2 + MongolCode.Uni.ZWJ);
        PopupKeyCandidate final_UE_FVS1 = new PopupKeyCandidate(
                "" + MongolCode.Uni.UE + MongolCode.Uni.FVS1,
                "" + MongolCode.Uni.ZWJ + MongolCode.Uni.UE + MongolCode.Uni.FVS1);
        return new PopupKeyCandidate[]{medial_UE_FVS2, final_UE_FVS1};
    }

    private PopupKeyCandidate[] getCandidatesForNA(boolean isIsolateOrInitial) {

        if (mIsShowingPunctuation) {
            return PopupKeyCandidate.createArray(KEY_NA_PUNCT_SUB);
        }

        if (isIsolateOrInitial) {
            return PopupKeyCandidate.createArray(MongolCode.Uni.ANG);
        }

        // medial/final
        PopupKeyCandidate ang = new PopupKeyCandidate(MongolCode.Uni.ANG);
        // only(?) way to override dotted N before vowel in Unicode 10.0
        PopupKeyCandidate na_zwj = new PopupKeyCandidate(
                "" + MongolCode.Uni.NA + MongolCode.Uni.ZWJ,
                "" + MongolCode.Uni.ZWJ + MongolCode.Uni.NA + MongolCode.Uni.ZWJ);
        PopupKeyCandidate na_fvs1 = new PopupKeyCandidate(
                "" + MongolCode.Uni.NA + MongolCode.Uni.FVS1,
                "" + MongolCode.Uni.ZWJ + MongolCode.Uni.NA + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ,
                "" + MongolCode.Uni.NA + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ);
        return new PopupKeyCandidate[]{ang, na_zwj, na_fvs1};
    }

    private PopupKeyCandidate[] getCandidatesForBA() {
        if (mIsShowingPunctuation)
            return PopupKeyCandidate.createArray(KEY_BA_PUNCT_SUB);
        return PopupKeyCandidate.createArray(new String[]{"" + MongolCode.Uni.PA, "" + MongolCode.Uni.FA});
    }

    private PopupKeyCandidate[] getCandidatesForQA() {
        if (mIsShowingPunctuation)
            return PopupKeyCandidate.createArray(KEY_QA_PUNCT_SUB);
        return PopupKeyCandidate.createArray(MongolCode.Uni.HAA);
    }

    private PopupKeyCandidate[] getCandidatesForGA(boolean isIsolateOrInitial) {
        if (mIsShowingPunctuation)
            return PopupKeyCandidate.createArray(KEY_GA_PUNCT_SUB);

        if (isIsolateOrInitial) {
            return PopupKeyCandidate.createArray(MongolCode.Uni.KA);
        }

        // medial/final
        PopupKeyCandidate ka = new PopupKeyCandidate(MongolCode.Uni.KA);
        // see note on MongolCode(FINA_GA_FVS1)
        PopupKeyCandidate ga_fvs1 = new PopupKeyCandidate(
                "" + MongolCode.Uni.GA + MongolCode.Uni.FVS1,
                "" + MongolCode.Uni.ZWJ + MongolCode.Uni.GA + MongolCode.Uni.FVS1);
        // see note on MongolCode(FINA_GA_FVS2)
        PopupKeyCandidate ga_fvs2 = new PopupKeyCandidate(
                "" + MongolCode.Uni.GA + MongolCode.Uni.FVS2,
                "" + MongolCode.Uni.ZWJ + MongolCode.Uni.GA + MongolCode.Uni.FVS2);
        return new PopupKeyCandidate[]{ka, ga_fvs1, ga_fvs2};
    }

    private PopupKeyCandidate[] getCandidatesForMA() {
        if (mIsShowingPunctuation)
            return PopupKeyCandidate.createArray(KEY_MA_PUNCT_SUB);
        return null;
    }

    private PopupKeyCandidate[] getCandidatesForLA() {
        if (mIsShowingPunctuation)
            return PopupKeyCandidate.createArray(KEY_LA_PUNCT_SUB);
        return PopupKeyCandidate.createArray(MongolCode.Uni.LHA);
    }

    private PopupKeyCandidate[] getCandidatesForSA() {
        if (mIsShowingPunctuation)
            return PopupKeyCandidate.createArray(KEY_SA_PUNCT_SUB);
        return PopupKeyCandidate.createArray(MongolCode.Uni.SHA);
    }

    private PopupKeyCandidate[] getCandidatesForTADA(boolean isIsolateOrInitial) {
        if (mIsShowingPunctuation)
            return PopupKeyCandidate.createArray(KEY_DA_PUNCT_SUB);

        if (isIsolateOrInitial) {
            char prevChar = getPreviousChar();
            String unicode;
            if (prevChar == MongolCode.Uni.NNBS) {
                unicode = "" + MongolCode.Uni.DA;
            } else {
                unicode = "" + MongolCode.Uni.DA + MongolCode.Uni.FVS1;
            }
            PopupKeyCandidate initial_da = new PopupKeyCandidate(
                    unicode,
                    "" + MongolCode.Uni.DA + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ);
            return new PopupKeyCandidate[]{initial_da};
            // TODO if this turns out to be an isolate then the FVS1 should be removed
        }

        // medial/final
        PopupKeyCandidate medial_ta_fvs1 = new PopupKeyCandidate(
                "" + MongolCode.Uni.TA + MongolCode.Uni.FVS1,
                "" + MongolCode.Uni.MONGOLIAN_NIRUGU +
                        MongolCode.Uni.TA + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ,
                "" + MongolCode.Uni.TA + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ);
        PopupKeyCandidate final_ta = new PopupKeyCandidate(
                "" + MongolCode.Uni.TA,
                "" + MongolCode.Uni.ZWJ + MongolCode.Uni.TA);
        PopupKeyCandidate final_da_fvs1 = new PopupKeyCandidate(
                "" + MongolCode.Uni.DA + MongolCode.Uni.FVS1,
                "" + MongolCode.Uni.ZWJ + MongolCode.Uni.DA + MongolCode.Uni.FVS1);
        return new PopupKeyCandidate[]{medial_ta_fvs1, final_ta, final_da_fvs1};
    }

    private PopupKeyCandidate[] getCandidatesForCHA() {
        if (mIsShowingPunctuation)
            return PopupKeyCandidate.createArray(KEY_CHA_PUNCT_SUB);
        return PopupKeyCandidate.createArray(new String[]{
                "" + MongolCode.Uni.TSA,
                "" + MongolCode.Uni.CHI});
    }

    private PopupKeyCandidate[] getCandidatesForJA() {
        if (mIsShowingPunctuation)
            return PopupKeyCandidate.createArray(KEY_JA_PUNCT_SUB);
        return PopupKeyCandidate.createArray(new String[]{
                "" + MongolCode.Uni.ZA,
                "" + MongolCode.Uni.ZHI});
    }

    private PopupKeyCandidate[] getCandidatesForYA(boolean isIsolateOrInitial) {
        if (mIsShowingPunctuation)
            return PopupKeyCandidate.createArray(KEY_YA_PUNCT_SUB);

        PopupKeyCandidate wa = new PopupKeyCandidate(MongolCode.Uni.WA);
        if (isIsolateOrInitial) {
            PopupKeyCandidate initial_YA_FVS1 = new PopupKeyCandidate(
                    "" + MongolCode.Uni.YA + MongolCode.Uni.FVS1,
                    "" + MongolCode.Uni.YA + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ,
                    "" + MongolCode.Uni.YA + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ);
            return new PopupKeyCandidate[]{wa, initial_YA_FVS1};
        }

        // medial/final
        PopupKeyCandidate medial_YA_FVS1 = new PopupKeyCandidate(
                "" + MongolCode.Uni.YA + MongolCode.Uni.FVS1,
                "" + MongolCode.Uni.ZWJ + MongolCode.Uni.YA + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ,
                "" + MongolCode.Uni.YA + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ);
        return new PopupKeyCandidate[]{wa, medial_YA_FVS1};
    }

    private PopupKeyCandidate[] getCandidatesForRA() {
        if (mIsShowingPunctuation)
            return PopupKeyCandidate.createArray(KEY_RA_PUNCT_SUB);
        return PopupKeyCandidate.createArray(MongolCode.Uni.ZRA);
    }

    private PopupKeyCandidate[] getCandidatesForComma() {
        return PopupKeyCandidate.createArray(new String[]{
                "" + MongolCode.Uni.VERTICAL_QUESTION_MARK,
                "" + MongolCode.Uni.MONGOLIAN_FULL_STOP,
                "" + MongolCode.Uni.VERTICAL_EXCLAMATION_MARK});
    }

    private PopupKeyCandidate[] getCandidatesForSpace() {
        PopupKeyCandidate nnbs = new PopupKeyCandidate(
                "" + MongolCode.Uni.NNBS,
                KEY_SPACE_SUB_DISPLAY,
                " ");
        return new PopupKeyCandidate[]{nnbs};
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

    @Override
    public void onKeyInput(String text) {
        // TA/DA defaults to DA except in the INITIAL location
        if (text.equals(String.valueOf(MongolCode.Uni.DA))) {
            char prevChar = getPreviousChar();
            if (!MongolCode.isMongolian(prevChar)) {
                text = String.valueOf(MongolCode.Uni.TA);
            }
        }
        super.onKeyInput(text);
    }
}
