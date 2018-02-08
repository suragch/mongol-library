package net.studymongolian.mongollibrary;


import android.content.Context;

public class KeyboardEnglish extends Keyboard {

    // name to use in the keyboard popup chooser
    private static final String DISPLAY_NAME = "ᠠᠩᠭᠯᠢ";

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

    // Row 2
    protected KeyText mKeyA;
    protected KeyText mKeyS;
    protected KeyText mKeyD;
    protected KeyText mKeyF;
    protected KeyText mKeyG;
    protected KeyText mKeyH;
    protected KeyText mKeyJ;
    protected KeyText mKeyK;
    protected KeyText mKeyL;

    // Row 3
    protected KeyShift mKeyShift;
    protected KeyText mKeyZ;
    protected KeyText mKeyX;
    protected KeyText mKeyC;
    protected KeyText mKeyV;
    protected KeyText mKeyB;
    protected KeyText mKeyN;
    protected KeyText mKeyM;
    protected KeyBackspace mKeyBackspace;

    // Row 4
    protected KeyKeyboardChooser mKeyKeyboard;
    protected KeyText mKeyExclamation;
    protected KeyText mKeyComma;
    protected KeyText mKeySpace;
    protected KeyText mKeyPeriod;
    protected KeyText mKeyQuestion;
    protected KeyImage mKeyReturn;

//    // These are all input values (some display values are different)
//    private String key_Q = "q";
//    private static final String KEY_W = "w";
//    private static final String KEY_E = "e";
//    private static final String KEY_R = "r";
//    private static final String KEY_T = "t";
//    private static final String KEY_Y = "y";
//    private static final String KEY_U = "u";
//    private static final String KEY_I = "i";
//    private static final String KEY_O = "o";
//    private static final String KEY_P = "p";
//    private static final String KEY_A = "a";
//    private static final String KEY_S = "s";
//    private static final String KEY_D = "d";
//    private static final String KEY_F = "f";
//    private static final String KEY_G = "g";
//    private static final String KEY_H = "h";
//    private static final String KEY_J = "j";
//    private static final String KEY_K = "k";
//    private static final String KEY_L = "l";
//    private static final String KEY_Z = "z";
//    private static final String KEY_X = "x";
//    private static final String KEY_C = "c";
//    private static final String KEY_V = "v";
//    private static final String KEY_B = "b";
//    private static final String KEY_N = "n";
//    private static final String KEY_M = "m";
//    private static final String KEY_EXCLAMATION = "!";
//    private static final String KEY_COMMA = ",";
//    private static final String KEY_SPACE = " ";
//    private static final String KEY_PERIOD = ".";
//    private static final String KEY_QUESTION = "?";
//
//
//    private static final String KEY_Q_SUB = "";
//    private static final String KEY_W_SUB = "";
//    private static final String KEY_E_SUB = "";
//    private static final String KEY_R_SUB = "";
//    private static final String KEY_T_SUB = "";
//    private static final String KEY_Y_SUB = "";
//    private static final String KEY_U_SUB = "";
//    private static final String KEY_I_SUB = "";
//    private static final String KEY_O_SUB = "";
//    private static final String KEY_P_SUB = "";
//    private static final String KEY_A_SUB = "";
//    private static final String KEY_S_SUB = "";
//    private static final String KEY_D_SUB = "";
//    private static final String KEY_F_SUB = "";
//    private static final String KEY_G_SUB = "";
//    private static final String KEY_H_SUB = "";
//    private static final String KEY_J_SUB = "";
//    private static final String KEY_K_SUB = "";
//    private static final String KEY_L_SUB = "";
//    private static final String KEY_Z_SUB = "";
//    private static final String KEY_X_SUB = "";
//    private static final String KEY_C_SUB = "";
//    private static final String KEY_V_SUB = "";
//    private static final String KEY_B_SUB = "";
//    private static final String KEY_N_SUB = "";
//    private static final String KEY_M_SUB = "";
//    private static final String KEY_EXCLAMATION_SUB = "";
//    private static final String KEY_COMMA_SUB = "";
//    private static final String KEY_PERIOD_SUB = "";
//    private static final String KEY_QUESTION_SUB = "";
//
//    private static final String KEY_Q_PUNCT = "1";
//    private static final String KEY_W_PUNCT = "2";
//    private static final String KEY_E_PUNCT = "3";
//    private static final String KEY_R_PUNCT = "4";
//    private static final String KEY_T_PUNCT = "5";
//    private static final String KEY_Y_PUNCT = "6";
//    private static final String KEY_U_PUNCT = "7";
//    private static final String KEY_I_PUNCT = "8";
//    private static final String KEY_O_PUNCT = "9";
//    private static final String KEY_P_PUNCT = "0";
//    private static final String KEY_A_PUNCT = "\\";
//    private static final String KEY_S_PUNCT = "_";
//    private static final String KEY_D_PUNCT = "(";
//    private static final String KEY_F_PUNCT = ":";
//    private static final String KEY_G_PUNCT = ")";
//    private static final String KEY_H_PUNCT = "&";
//    private static final String KEY_J_PUNCT = "#";
//    private static final String KEY_K_PUNCT = "*";
//    private static final String KEY_L_PUNCT = "\"";
//    private static final String KEY_Z_PUNCT = "@";
//    private static final String KEY_X_PUNCT = "/";
//    private static final String KEY_C_PUNCT = "-";
//    private static final String KEY_V_PUNCT = "\'";
//    private static final String KEY_B_PUNCT = "!";
//    private static final String KEY_N_PUNCT = "?";
//    private static final String KEY_M_PUNCT = ";";
//
private static final String KEY_Q_PUNCT_SUB = String.valueOf(MongolCode.Uni.MONGOLIAN_DIGIT_ONE);
    private static final String KEY_W_PUNCT_SUB = String.valueOf(MongolCode.Uni.MONGOLIAN_DIGIT_TWO);
    private static final String KEY_E_PUNCT_SUB = String.valueOf(MongolCode.Uni.MONGOLIAN_DIGIT_THREE);
    private static final String KEY_R_PUNCT_SUB = String.valueOf(MongolCode.Uni.MONGOLIAN_DIGIT_FOUR);
    private static final String KEY_T_PUNCT_SUB = String.valueOf(MongolCode.Uni.MONGOLIAN_DIGIT_FIVE);
    private static final String KEY_Y_PUNCT_SUB = String.valueOf(MongolCode.Uni.MONGOLIAN_DIGIT_SIX);
    private static final String KEY_U_PUNCT_SUB = String.valueOf(MongolCode.Uni.MONGOLIAN_DIGIT_SEVEN);
    private static final String KEY_I_PUNCT_SUB = String.valueOf(MongolCode.Uni.MONGOLIAN_DIGIT_EIGHT);
    private static final String KEY_O_PUNCT_SUB = String.valueOf(MongolCode.Uni.MONGOLIAN_DIGIT_NINE);
    private static final String KEY_P_PUNCT_SUB = String.valueOf(MongolCode.Uni.MONGOLIAN_DIGIT_ZERO);
    private static final String KEY_A_PUNCT_SUB = String.valueOf(MongolCode.Uni.VERTICAL_LEFT_SQUARE_BRACKET);
    private static final String KEY_S_PUNCT_SUB = String.valueOf(MongolCode.Uni.VERTICAL_RIGHT_SQUARE_BRACKET);
    private static final String KEY_D_PUNCT_SUB = String.valueOf(MongolCode.Uni.VERTICAL_LEFT_ANGLE_BRACKET);
    private static final String KEY_F_PUNCT_SUB = String.valueOf(MongolCode.Uni.VERTICAL_RIGHT_ANGLE_BRACKET);
    private static final String KEY_G_PUNCT_SUB = "+";
    private static final String KEY_H_PUNCT_SUB = "$";
    private static final String KEY_J_PUNCT_SUB = "";
    private static final String KEY_K_PUNCT_SUB = "";
    private static final String KEY_L_PUNCT_SUB = "";
    private static final String KEY_NG_PUNCT_SUB = "";
    private static final String KEY_ZWJ_PUNCT_SUB = "\\";
    private static final String KEY_Z_PUNCT_SUB = String.valueOf(MongolCode.Uni.MONGOLIAN_FOUR_DOTS);
    private static final String KEY_X_PUNCT_SUB = "";
    private static final String KEY_C_PUNCT_SUB = ".";
    private static final String KEY_V_PUNCT_SUB = "";
    private static final String KEY_B_PUNCT_SUB = "~";
    private static final String KEY_N_PUNCT_SUB = String.valueOf(MongolCode.Uni.VERTICAL_COMMA);
    private static final String KEY_M_PUNCT_SUB = String.valueOf(MongolCode.Uni.DOUBLE_EXCLAMATION_MARK);

    // Keys with different display values
    //private static final String KEY_ZWJ_DISPLAY = "/";
    //private static final String KEY_SPACE_SUB_DISPLAY = "ᠶ᠋ᠢ ᠳᠤ ᠤᠨ";
    private static final String NEWLINE = "\n";

    // Use this constructor if you want the default style
    public KeyboardEnglish(Context context) {
        super(context);
        init(context);
    }

    // all keyboards should include this custom constructor
    // (there was no way to force it in the abstract Keyboard class)
    public KeyboardEnglish(Context context, StyleBuilder style) {
        super(context);
        super.initStyle(style);
        init(context);
    }

    protected void init(Context context) {

        // keyboard layout

        // | Q | W | E | R | T | Y | U | I | O | P |  Row 1
        //   | A | S | D | F | G | H | J | K | L |    Row 2
        // |shift| Z | X | C | V | B | N | M | del |  Row 3
        // |  kb | ! | , |   space   | : | ? | ret |  Row 4

        // actual layout work is done by Keyboard superclass's onLayout
        mNumberOfKeysInRow = new int[]{10, 9, 9, 7}; // 36 keys total
        // this is the percent to inset the row
        mInsetWeightInRow = new float[]{0, 0.05f, 0, 0};
        // the key weights for each row should sum to 1 (unless there is an inset)
        mKeyWeights = new float[]{
                0.1f, 0.1f, 0.1f, 0.1f, 0.1f, 0.1f, 0.1f, 0.1f, 0.1f, 0.1f,     // row 0
                0.1f, 0.1f, 0.1f, 0.1f, 0.1f, 0.1f, 0.1f, 0.1f, 0.1f,           // row 1
                0.15f, 0.1f, 0.1f, 0.1f, 0.1f, 0.1f, 0.1f, 0.1f, 0.15f,         // row 2
                0.15f, 0.1f, 0.1f, 0.3f, 0.1f, 0.1f, 0.15f};                    // row 3

        // Make sure that the total keys added to this ViewGroup below equals
        // the mNumberOfKeysInRow and mKeyWeights array totals above.

        instantiateKeys(context);
        setLowerCaseKeyValues();
        setNonChangingKeyValues();
        dontRotatePrimaryTextForSelectKeys();
        setKeyImages();
        setListeners();
        addKeysToKeyboard();
        applyThemeToKeys();
    }

    private void instantiateKeys(Context context) {

        // Row 1
        mKeyQ = new KeyText(context);
        mKeyW = new KeyText(context);
        mKeyE = new KeyText(context);
        mKeyR = new KeyText(context);
        mKeyT = new KeyText(context);
        mKeyY = new KeyText(context);
        mKeyU = new KeyText(context);
        mKeyI = new KeyText(context);
        mKeyO = new KeyText(context);
        mKeyP = new KeyText(context);

        // Row 2
        mKeyA = new KeyText(context);
        mKeyS = new KeyText(context);
        mKeyD = new KeyText(context);
        mKeyF = new KeyText(context);
        mKeyG = new KeyText(context);
        mKeyH = new KeyText(context);
        mKeyJ = new KeyText(context);
        mKeyK = new KeyText(context);
        mKeyL = new KeyText(context);

        // Row 3
        mKeyShift = new KeyShift(context);
        mKeyZ = new KeyText(context);
        mKeyX = new KeyText(context);
        mKeyC = new KeyText(context);
        mKeyV = new KeyText(context);
        mKeyB = new KeyText(context);
        mKeyN = new KeyText(context);
        mKeyM = new KeyText(context);
        mKeyBackspace = new KeyBackspace(context);

        // Row 4
        mKeyKeyboard = new KeyKeyboardChooser(context);
        mKeyExclamation = new KeyText(context);
        mKeyComma = new KeyText(context);
        mKeySpace = new KeyText(context);
        mKeyPeriod = new KeyText(context);
        mKeyQuestion = new KeyText(context);
        mKeyReturn = new KeyImage(context);
    }

    private void setLowerCaseKeyValues() {

        // Row 1

        mKeyQ.setText("q");
        mKeyQ.setSubText("");

        mKeyW.setText("w");
        mKeyW.setSubText("");

        mKeyE.setText("e");
        mKeyE.setSubText("");

        mKeyR.setText("r");
        mKeyR.setSubText("");

        mKeyT.setText("t");
        mKeyT.setSubText("");

        mKeyY.setText("y");
        mKeyY.setSubText("");

        mKeyU.setText("u");
        mKeyU.setSubText("");

        mKeyI.setText("i");
        mKeyI.setSubText("");

        mKeyO.setText("o");
        mKeyO.setSubText("");

        mKeyP.setText("p");
        mKeyP.setSubText("");

        // Row 2
        mKeyA.setText("a");
        mKeyA.setSubText("");

        mKeyS.setText("s");
        mKeyS.setSubText("");

        mKeyD.setText("d");
        mKeyD.setSubText("");

        mKeyF.setText("f");
        mKeyF.setSubText("");

        mKeyG.setText("g");
        mKeyG.setSubText("");

        mKeyH.setText("h");
        mKeyH.setSubText("");

        mKeyJ.setText("j");
        mKeyJ.setSubText("");

        mKeyK.setText("k");
        mKeyK.setSubText("");

        mKeyL.setText("l");
        mKeyL.setSubText("");

        // Row 3

        mKeyZ.setText("z");
        mKeyZ.setSubText("");

        mKeyX.setText("x");
        mKeyX.setSubText("");

        mKeyC.setText("c");
        mKeyC.setSubText("");

        mKeyV.setText("v");
        mKeyV.setSubText("");

        mKeyB.setText("b");
        mKeyB.setSubText("");

        mKeyN.setText("n");
        mKeyN.setSubText("");

        mKeyM.setText("m");
        mKeyM.setSubText("");
    }

    private void setUpperCaseKeyValues() {

        // Row 1

        mKeyQ.setText("Q");
        mKeyQ.setSubText("");

        mKeyW.setText("W");
        mKeyW.setSubText("");

        mKeyE.setText("E");
        mKeyE.setSubText("");

        mKeyR.setText("R");
        mKeyR.setSubText("");

        mKeyT.setText("T");
        mKeyT.setSubText("");

        mKeyY.setText("Y");
        mKeyY.setSubText("");

        mKeyU.setText("U");
        mKeyU.setSubText("");

        mKeyI.setText("I");
        mKeyI.setSubText("");

        mKeyO.setText("O");
        mKeyO.setSubText("");

        mKeyP.setText("P");
        mKeyP.setSubText("");

        // Row 2
        mKeyA.setText("A");
        mKeyA.setSubText("");

        mKeyS.setText("S");
        mKeyS.setSubText("");

        mKeyD.setText("D");
        mKeyD.setSubText("");

        mKeyF.setText("F");
        mKeyF.setSubText("");

        mKeyG.setText("G");
        mKeyG.setSubText("");

        mKeyH.setText("H");
        mKeyH.setSubText("");

        mKeyJ.setText("J");
        mKeyJ.setSubText("");

        mKeyK.setText("K");
        mKeyK.setSubText("");

        mKeyL.setText("L");
        mKeyL.setSubText("");

        // Row 3

        mKeyZ.setText("Z");
        mKeyZ.setSubText("");

        mKeyX.setText("X");
        mKeyX.setSubText("");

        mKeyC.setText("C");
        mKeyC.setSubText("");

        mKeyV.setText("V");
        mKeyV.setSubText("");

        mKeyB.setText("B");
        mKeyB.setSubText("");

        mKeyN.setText("N");
        mKeyN.setSubText("");

        mKeyM.setText("M");
        mKeyM.setSubText("");
    }


    private void setPunctuationKeyValues() {

        // Row 1

        mKeyQ.setText("1");
        mKeyQ.setSubText("");

        mKeyW.setText("2");
        mKeyW.setSubText("");

        mKeyE.setText("3");
        mKeyE.setSubText("");

        mKeyR.setText("4");
        mKeyR.setSubText("");

        mKeyT.setText("5");
        mKeyT.setSubText("");

        mKeyY.setText("6");
        mKeyY.setSubText("");

        mKeyU.setText("7");
        mKeyU.setSubText("");

        mKeyI.setText("8");
        mKeyI.setSubText("");

        mKeyO.setText("9");
        mKeyO.setSubText("");

        mKeyP.setText("0");
        mKeyP.setSubText("");

        // Row 2

        mKeyA.setText("\\");
        mKeyA.setSubText("");

        mKeyS.setText("_");
        mKeyS.setSubText("");

        mKeyD.setText("(");
        mKeyD.setSubText("");

        mKeyF.setText(":");
        mKeyF.setSubText("");

        mKeyG.setText(")");
        mKeyG.setSubText("");

        mKeyH.setText("&");
        mKeyH.setSubText("");

        mKeyJ.setText("#");
        mKeyJ.setSubText("");

        mKeyK.setText("*");
        mKeyK.setSubText("");

        mKeyL.setText("\"");
        mKeyL.setSubText("");

        // Row 3

        mKeyZ.setText("@");
        mKeyZ.setSubText("");

        mKeyX.setText("/");
        mKeyX.setSubText("");

        mKeyC.setText("-");
        mKeyC.setSubText("");

        mKeyV.setText("\'");
        mKeyV.setSubText("");

        mKeyB.setText("!");
        mKeyB.setSubText("");

        mKeyN.setText("?");
        mKeyN.setSubText("");

        mKeyM.setText(";");
        mKeyM.setSubText("");
    }

    private void setNonChangingKeyValues() {
        mKeyExclamation.setText("!");
        mKeyComma.setText(",");
        mKeySpace.setText(" ");
        mKeyPeriod.setText(".");
        mKeyQuestion.setText("?");
        mKeyReturn.setText(NEWLINE);
    }

    private void dontRotatePrimaryTextForSelectKeys() {

        // Row 1
        mKeyQ.setRotatedPrimaryText(false);
        mKeyW.setRotatedPrimaryText(false);
        mKeyE.setRotatedPrimaryText(false);
        mKeyR.setRotatedPrimaryText(false);
        mKeyT.setRotatedPrimaryText(false);
        mKeyY.setRotatedPrimaryText(false);
        mKeyU.setRotatedPrimaryText(false);
        mKeyI.setRotatedPrimaryText(false);
        mKeyO.setRotatedPrimaryText(false);
        mKeyP.setRotatedPrimaryText(false);

        // Row 2
        mKeyA.setRotatedPrimaryText(false);
        mKeyS.setRotatedPrimaryText(false);
        mKeyD.setRotatedPrimaryText(false);
        mKeyF.setRotatedPrimaryText(false);
        mKeyG.setRotatedPrimaryText(false);
        mKeyH.setRotatedPrimaryText(false);
        mKeyJ.setRotatedPrimaryText(false);
        mKeyK.setRotatedPrimaryText(false);
        mKeyL.setRotatedPrimaryText(false);

        // Row 3
        mKeyZ.setRotatedPrimaryText(false);
        mKeyX.setRotatedPrimaryText(false);
        mKeyC.setRotatedPrimaryText(false);
        mKeyV.setRotatedPrimaryText(false);
        mKeyB.setRotatedPrimaryText(false);
        mKeyN.setRotatedPrimaryText(false);
        mKeyM.setRotatedPrimaryText(false);

        // Row 4
        mKeyExclamation.setRotatedPrimaryText(false);
        mKeyComma.setRotatedPrimaryText(false);
        mKeySpace.setRotatedPrimaryText(false);
        mKeyPeriod.setRotatedPrimaryText(false);
        mKeyQuestion.setRotatedPrimaryText(false);
    }

    private void setKeyImages() {
        mKeyShift.setShiftImage(mKeyboardTheme);
        mKeyBackspace.setImage(getBackspaceImage());
        mKeyKeyboard.setImage(getKeyboardImage());
        mKeyReturn.setImage(getReturnImage());
    }

    private void setListeners() {

        // Row 1
        mKeyQ.setKeyListener(this);
        mKeyW.setKeyListener(this);
        mKeyE.setKeyListener(this);
        mKeyR.setKeyListener(this);
        mKeyT.setKeyListener(this);
        mKeyY.setKeyListener(this);
        mKeyU.setKeyListener(this);
        mKeyI.setKeyListener(this);
        mKeyO.setKeyListener(this);
        mKeyP.setKeyListener(this);

        // Row 2
        mKeyA.setKeyListener(this);
        mKeyS.setKeyListener(this);
        mKeyD.setKeyListener(this);
        mKeyF.setKeyListener(this);
        mKeyG.setKeyListener(this);
        mKeyH.setKeyListener(this);
        mKeyJ.setKeyListener(this);
        mKeyK.setKeyListener(this);
        mKeyL.setKeyListener(this);

        // Row 3
        mKeyShift.setKeyListener(this);
        mKeyZ.setKeyListener(this);
        mKeyX.setKeyListener(this);
        mKeyC.setKeyListener(this);
        mKeyV.setKeyListener(this);
        mKeyB.setKeyListener(this);
        mKeyN.setKeyListener(this);
        mKeyM.setKeyListener(this);
        mKeyBackspace.setKeyListener(this);

        // Row 4
        mKeyKeyboard.setKeyListener(this);
        mKeyExclamation.setKeyListener(this);
        mKeyComma.setKeyListener(this);
        mKeySpace.setKeyListener(this);
        mKeyPeriod.setKeyListener(this);
        mKeyQuestion.setKeyListener(this);
        mKeyReturn.setKeyListener(this);
    }

    private void addKeysToKeyboard() {

        // Row 1
        addView(mKeyQ);
        addView(mKeyW);
        addView(mKeyE);
        addView(mKeyR);
        addView(mKeyT);
        addView(mKeyY);
        addView(mKeyU);
        addView(mKeyI);
        addView(mKeyO);
        addView(mKeyP);

        // Row 2
        addView(mKeyA);
        addView(mKeyS);
        addView(mKeyD);
        addView(mKeyF);
        addView(mKeyG);
        addView(mKeyH);
        addView(mKeyJ);
        addView(mKeyK);
        addView(mKeyL);

        // Row 3
        addView(mKeyShift);
        addView(mKeyZ);
        addView(mKeyX);
        addView(mKeyC);
        addView(mKeyV);
        addView(mKeyB);
        addView(mKeyN);
        addView(mKeyM);
        addView(mKeyBackspace);

        // Row 4
        addView(mKeyKeyboard);
        addView(mKeyExclamation);
        addView(mKeyComma);
        addView(mKeySpace);
        addView(mKeyPeriod);
        addView(mKeyQuestion);
        addView(mKeyReturn);
    }

    //@Override
    public void onShiftChanged(boolean shiftIsOn) {

        if (shiftIsOn) {
            setUpperCaseKeyValues();
        } else {
            setLowerCaseKeyValues();
        }
    }

    public PopupKeyCandidate[] getPopupCandidates(Key key) {
        // get the appropriate candidates based on the key pressed
        if (key == mKeyQ) {
            return getCandidatesForQ();
        } else if (key == mKeyW) {
            return getCandidatesForW();
        } else if (key == mKeyE) {
            return getCandidatesForE(isIsolateOrInitial());
        } else if (key == mKeyR) {
            return getCandidatesForR();
        } else if (key == mKeyT) {
            return getCandidatesForT(isIsolateOrInitial());
        } else if (key == mKeyY) {
            return getCandidatesForY(isIsolateOrInitial());
        } else if (key == mKeyU) {
            return getCandidatesForU(isIsolateOrInitial());
        } else if (key == mKeyI) {
            return getCandidatesForI(isIsolateOrInitial());
        } else if (key == mKeyO) {
            return getCandidatesForO(isIsolateOrInitial());
        } else if (key == mKeyP) {
            return getCandidatesForP();
        } else if (key == mKeyA) {
            return getCandidatesForA(isIsolateOrInitial());
        } else if (key == mKeyS) {
            return getCandidatesForS();
        } else if (key == mKeyD) {
            return getCandidatesForD(isIsolateOrInitial());
        } else if (key == mKeyF) {
            return getCandidatesForF();
        } else if (key == mKeyG) {
            return getCandidatesForG(isIsolateOrInitial());
        } else if (key == mKeyH) {
            return getCandidatesForH();
        } else if (key == mKeyJ) {
            return getCandidatesForJ();
        } else if (key == mKeyK) {
            return getCandidatesForK();
        } else if (key == mKeyL) {
            return getCandidatesForL();
        } else if (key == mKeyZ) {
            return getCandidatesForZ();
        } else if (key == mKeyX) {
            return getCandidatesForX();
        } else if (key == mKeyC) {
            return getCandidatesForC(isIsolateOrInitial());
        } else if (key == mKeyV) {
            return getCandidatesForV(isIsolateOrInitial());
        } else if (key == mKeyB) {
            return getCandidatesForB();
        } else if (key == mKeyN) {
            return getCandidatesForN(isIsolateOrInitial());
        } else if (key == mKeyM) {
            return getCandidatesForM();
        } else if (key == mKeyKeyboard) {
            return getCandidatesForKeyboard();
        } else if (key == mKeySpace) {
            return getCandidatesForSpace();
        }

        return null;
    }

    private PopupKeyCandidate[] getCandidatesForQ() {
        if (mIsShowingPunctuation)
            return PopupKeyCandidate.createArray(KEY_Q_PUNCT_SUB);
        return PopupKeyCandidate.createArray(MongolCode.Uni.CHI);
    }

    private PopupKeyCandidate[] getCandidatesForW() {
        if (mIsShowingPunctuation)
            return PopupKeyCandidate.createArray(KEY_W_PUNCT_SUB);
        return null;
    }

    private PopupKeyCandidate[] getCandidatesForE(boolean isIsolateOrInitial) {
        if (mIsShowingPunctuation)
            return PopupKeyCandidate.createArray(KEY_E_PUNCT_SUB);

        if (isIsolateOrInitial)
            return PopupKeyCandidate.createArray(MongolCode.Uni.EE);

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

    private PopupKeyCandidate[] getCandidatesForR() {
        if (mIsShowingPunctuation)
            return PopupKeyCandidate.createArray(KEY_R_PUNCT_SUB);
        return PopupKeyCandidate.createArray(MongolCode.Uni.ZRA);
    }

    private PopupKeyCandidate[] getCandidatesForT(boolean isIsolateOrInitial) {
        if (mIsShowingPunctuation)
            return PopupKeyCandidate.createArray(KEY_T_PUNCT_SUB);

        if (!isIsolateOrInitial) { // medial/final
            PopupKeyCandidate medial_ta_fvs1 = new PopupKeyCandidate(
                    "" + MongolCode.Uni.TA + MongolCode.Uni.FVS1,
                    "" + MongolCode.Uni.MONGOLIAN_NIRUGU +
                            MongolCode.Uni.TA + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ,
                    "" + MongolCode.Uni.TA + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ);
            return new PopupKeyCandidate[]{medial_ta_fvs1};
        }
        return null;
    }

    private PopupKeyCandidate[] getCandidatesForY(boolean isIsolateOrInitial) {
        if (mIsShowingPunctuation)
            return PopupKeyCandidate.createArray(KEY_Y_PUNCT_SUB);

        if (isIsolateOrInitial) {
            PopupKeyCandidate initial_YA_FVS1 = new PopupKeyCandidate(
                    "" + MongolCode.Uni.YA + MongolCode.Uni.FVS1,
                    "" + MongolCode.Uni.YA + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ,
                    "" + MongolCode.Uni.YA + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ);
            return new PopupKeyCandidate[]{initial_YA_FVS1};
        }

        // medial/final
        PopupKeyCandidate medial_YA_FVS1 = new PopupKeyCandidate(
                "" + MongolCode.Uni.YA + MongolCode.Uni.FVS1,
                "" + MongolCode.Uni.ZWJ + MongolCode.Uni.YA + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ,
                "" + MongolCode.Uni.YA + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ);
        return new PopupKeyCandidate[]{medial_YA_FVS1};
    }

    private PopupKeyCandidate[] getCandidatesForU(boolean isIsolateOrInitial) {
        if (mIsShowingPunctuation)
            return PopupKeyCandidate.createArray(KEY_U_PUNCT_SUB);

        if (isIsolateOrInitial)
            return null;

        // medial/final
        PopupKeyCandidate medial_UE_FVS2 = new PopupKeyCandidate(
                "" + MongolCode.Uni.UE + MongolCode.Uni.FVS2,
                "" + MongolCode.Uni.ZWJ + MongolCode.Uni.UE + MongolCode.Uni.FVS2 + MongolCode.Uni.ZWJ,
                "" + MongolCode.Uni.UE + MongolCode.Uni.FVS2 + MongolCode.Uni.ZWJ);
        PopupKeyCandidate final_UE_FVS1 = new PopupKeyCandidate(
                "" + MongolCode.Uni.UE + MongolCode.Uni.FVS1,
                "" + MongolCode.Uni.ZWJ + MongolCode.Uni.UE + MongolCode.Uni.FVS1,
                null);
        return new PopupKeyCandidate[]{medial_UE_FVS2, final_UE_FVS1};
    }

    private PopupKeyCandidate[] getCandidatesForI(boolean isIsolateOrInitial) {
        if (mIsShowingPunctuation)
            return PopupKeyCandidate.createArray(KEY_I_PUNCT_SUB);

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
        if (mIsShowingPunctuation)
            return PopupKeyCandidate.createArray(KEY_O_PUNCT_SUB);

        if (isIsolateOrInitial)
            return null;

        // medial/final
        PopupKeyCandidate medial_OE_FVS2 = new PopupKeyCandidate(
                "" + MongolCode.Uni.OE + MongolCode.Uni.FVS2,
                "" + MongolCode.Uni.ZWJ + MongolCode.Uni.OE + MongolCode.Uni.FVS2 + MongolCode.Uni.ZWJ,
                "" + MongolCode.Uni.UE + MongolCode.Uni.FVS2 + MongolCode.Uni.ZWJ);
        PopupKeyCandidate final_OE_FVS1 = new PopupKeyCandidate(
                "" + MongolCode.Uni.OE + MongolCode.Uni.FVS1,
                "" + MongolCode.Uni.ZWJ + MongolCode.Uni.OE + MongolCode.Uni.FVS1);
        return new PopupKeyCandidate[]{medial_OE_FVS2, final_OE_FVS1};
    }

    private PopupKeyCandidate[] getCandidatesForP() {
        if (mIsShowingPunctuation)
            return PopupKeyCandidate.createArray(KEY_P_PUNCT_SUB);
        return null;
    }

    private PopupKeyCandidate[] getCandidatesForA(boolean isIsolateOrInitial) {
        if (mIsShowingPunctuation)
            return PopupKeyCandidate.createArray(KEY_A_PUNCT_SUB);

        if (isIsolateOrInitial)
            return PopupKeyCandidate.createArray(new String[]{
                    "" + MongolCode.Uni.A + MongolCode.Uni.FVS1,
                    "" + MongolCode.Uni.MONGOLIAN_NIRUGU});

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

    private PopupKeyCandidate[] getCandidatesForS() {
        if (mIsShowingPunctuation)
            return PopupKeyCandidate.createArray(KEY_S_PUNCT_SUB);
        return null;
    }

    private PopupKeyCandidate[] getCandidatesForD(boolean isIsolateOrInitial) {
        if (mIsShowingPunctuation)
            return PopupKeyCandidate.createArray(KEY_D_PUNCT_SUB);

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
        PopupKeyCandidate final_da_fvs1 = new PopupKeyCandidate(
                "" + MongolCode.Uni.DA + MongolCode.Uni.FVS1,
                "" + MongolCode.Uni.ZWJ + MongolCode.Uni.DA + MongolCode.Uni.FVS1);
        return new PopupKeyCandidate[]{final_da_fvs1};
    }

    private PopupKeyCandidate[] getCandidatesForF() {
        if (mIsShowingPunctuation)
            return PopupKeyCandidate.createArray(KEY_F_PUNCT_SUB);
        return null;
    }

    private PopupKeyCandidate[] getCandidatesForG(boolean isIsolateOrInitial) {
        if (mIsShowingPunctuation)
            return PopupKeyCandidate.createArray(new String[]{"+", "-", "×", "÷", "≠", "≈"});

        if (isIsolateOrInitial)
            return null;

        // medial/final
        // see note on MongolCode(FINA_GA_FVS1)
        PopupKeyCandidate ga_fvs1 = new PopupKeyCandidate(
                "" + MongolCode.Uni.GA + MongolCode.Uni.FVS1,
                "" + MongolCode.Uni.ZWJ + MongolCode.Uni.GA + MongolCode.Uni.FVS1);
        // see note on MongolCode(FINA_GA_FVS2)
        PopupKeyCandidate ga_fvs2 = new PopupKeyCandidate(
                "" + MongolCode.Uni.GA + MongolCode.Uni.FVS2,
                "" + MongolCode.Uni.ZWJ + MongolCode.Uni.GA + MongolCode.Uni.FVS2);
        return new PopupKeyCandidate[]{ga_fvs1, ga_fvs2};
    }

    private PopupKeyCandidate[] getCandidatesForH() {
        if (mIsShowingPunctuation)
            return PopupKeyCandidate.createArray(new String[]{"$", "₮"});
        return PopupKeyCandidate.createArray(MongolCode.Uni.HAA);
    }

    private PopupKeyCandidate[] getCandidatesForJ() {
        if (mIsShowingPunctuation)
            return PopupKeyCandidate.createArray(KEY_J_PUNCT_SUB);
        return PopupKeyCandidate.createArray(MongolCode.Uni.ZHI);
    }

    private PopupKeyCandidate[] getCandidatesForK() {
        if (mIsShowingPunctuation)
            return PopupKeyCandidate.createArray(KEY_K_PUNCT_SUB);
        return null;
    }

    private PopupKeyCandidate[] getCandidatesForL() {
        if (mIsShowingPunctuation)
            return PopupKeyCandidate.createArray(KEY_L_PUNCT_SUB);
        return PopupKeyCandidate.createArray(MongolCode.Uni.LHA);
    }

    private PopupKeyCandidate[] getCandidatesForNG() {
        if (mIsShowingPunctuation)
            return PopupKeyCandidate.createArray(KEY_NG_PUNCT_SUB);
        return null;
    }

    private PopupKeyCandidate[] getCandidatesForZwj() {
        if (mIsShowingPunctuation)
            return PopupKeyCandidate.createArray(KEY_ZWJ_PUNCT_SUB);
        return null;
    }

    private PopupKeyCandidate[] getCandidatesForZ() {
        if (mIsShowingPunctuation)
            return PopupKeyCandidate.createArray(new String[]{KEY_Z_PUNCT_SUB, "*"});
        return PopupKeyCandidate.createArray(MongolCode.Uni.TSA);
    }

    private PopupKeyCandidate[] getCandidatesForX() {
        if (mIsShowingPunctuation)
            return PopupKeyCandidate.createArray(KEY_X_PUNCT_SUB);
        return null;
    }

    private PopupKeyCandidate[] getCandidatesForC(boolean isIsolateOrInitial) {
        if (mIsShowingPunctuation)
            return PopupKeyCandidate.createArray(KEY_C_PUNCT_SUB);

        if (isIsolateOrInitial)
            return null;

        // medial/final
        PopupKeyCandidate medial_O_FVS1 = new PopupKeyCandidate(
                "" + MongolCode.Uni.O + MongolCode.Uni.FVS1,
                "" + MongolCode.Uni.ZWJ + MongolCode.Uni.O + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ,
                "" + MongolCode.Uni.O + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ);
        PopupKeyCandidate final_O_FVS1 = new PopupKeyCandidate(
                "" + MongolCode.Uni.O + MongolCode.Uni.FVS1,
                "" + MongolCode.Uni.ZWJ + MongolCode.Uni.O + MongolCode.Uni.FVS1);
        return new PopupKeyCandidate[]{medial_O_FVS1, final_O_FVS1};
    }

    private PopupKeyCandidate[] getCandidatesForV(boolean isIsolateOrInitial) {
        if (mIsShowingPunctuation)
            return PopupKeyCandidate.createArray(KEY_V_PUNCT_SUB);

        if (isIsolateOrInitial)
            return null;

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

    private PopupKeyCandidate[] getCandidatesForB() {
        if (mIsShowingPunctuation)
            return PopupKeyCandidate.createArray(KEY_B_PUNCT_SUB);
        return null;
    }

    private PopupKeyCandidate[] getCandidatesForN(boolean isIsolateOrInitial) {
        if (mIsShowingPunctuation)
            return PopupKeyCandidate.createArray(KEY_N_PUNCT_SUB);

        if (isIsolateOrInitial)
            return null;

        // medial/final
        // only(?) way to override dotted N before vowel in Unicode 10.0
        PopupKeyCandidate na_zwj = new PopupKeyCandidate(
                "" + MongolCode.Uni.NA + MongolCode.Uni.ZWJ,
                "" + MongolCode.Uni.ZWJ + MongolCode.Uni.NA + MongolCode.Uni.ZWJ);
        PopupKeyCandidate na_fvs1 = new PopupKeyCandidate(
                "" + MongolCode.Uni.NA + MongolCode.Uni.FVS1,
                "" + MongolCode.Uni.ZWJ + MongolCode.Uni.NA + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ,
                "" + MongolCode.Uni.NA + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ);
        return new PopupKeyCandidate[]{na_zwj, na_fvs1};
    }

    private PopupKeyCandidate[] getCandidatesForM() {
        if (mIsShowingPunctuation)
            return PopupKeyCandidate.createArray(new String[]{
                    "" + MongolCode.Uni.DOUBLE_EXCLAMATION_MARK,
                    "" + MongolCode.Uni.DOUBLE_QUESTION_MARK,
                    "" + MongolCode.Uni.EXCLAMATION_QUESTION_MARK});
        return null;
    }

    private PopupKeyCandidate[] getCandidatesForSpace() {
        PopupKeyCandidate nnbs = new PopupKeyCandidate(
                "" + MongolCode.Uni.NNBS,
                "space",
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
            setPunctuationKeyValues();
        } else {
            setLowerCaseKeyValues();
        }
    }
}
