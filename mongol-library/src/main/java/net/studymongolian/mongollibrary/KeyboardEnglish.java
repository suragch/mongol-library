//package net.studymongolian.mongollibrary;
//
//
//import android.content.Context;
//
//public class KeyboardEnglish extends Keyboard implements KeyShift.ChangeListener {
//
//    // name to use in the keyboard popup chooser
//    private static final String DISPLAY_NAME = "ᠠᠩᠭᠯᠢ";
//
//    // Row 1
//    protected KeyTextNotRotated mKeyQ;
//    protected KeyTextNotRotated mKeyW;
//    protected KeyTextNotRotated mKeyE;
//    protected KeyTextNotRotated mKeyR;
//    protected KeyTextNotRotated mKeyT;
//    protected KeyTextNotRotated mKeyY;
//    protected KeyTextNotRotated mKeyU;
//    protected KeyTextNotRotated mKeyI;
//    protected KeyTextNotRotated mKeyO;
//    protected KeyTextNotRotated mKeyP;
//
//    // Row 2
//    protected KeyTextNotRotated mKeyA;
//    protected KeyTextNotRotated mKeyS;
//    protected KeyTextNotRotated mKeyD;
//    protected KeyTextNotRotated mKeyF;
//    protected KeyTextNotRotated mKeyG;
//    protected KeyTextNotRotated mKeyH;
//    protected KeyTextNotRotated mKeyJ;
//    protected KeyTextNotRotated mKeyK;
//    protected KeyTextNotRotated mKeyL;
//    //protected KeyText mKeyNg;
//
//    // Row 3
//    protected KeyShift mKeyShift;
//    protected KeyTextNotRotated mKeyZ;
//    protected KeyTextNotRotated mKeyX;
//    protected KeyTextNotRotated mKeyC;
//    protected KeyTextNotRotated mKeyV;
//    protected KeyTextNotRotated mKeyB;
//    protected KeyTextNotRotated mKeyN;
//    protected KeyTextNotRotated mKeyM;
//    protected KeyImage mKeyBackspace;
//
//    // Row 4
//    // protected KeyImage mKeyKeyboard; // defined in super class
//    protected KeyTextNotRotated mKeyExclamation;
//    protected KeyTextNotRotated mKeyComma;
//    protected KeyTextNotRotated mKeySpace;
//    protected KeyTextNotRotated mKeyPeriod;
//    protected KeyTextNotRotated mKeyQuestion;
//    protected KeyImage mKeyReturn;
//
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
//    private static final String KEY_Q_PUNCT_SUB = "";
//    private static final String KEY_W_PUNCT_SUB = "";
//    private static final String KEY_E_PUNCT_SUB = "";
//    private static final String KEY_R_PUNCT_SUB = "";
//    private static final String KEY_T_PUNCT_SUB = "";
//    private static final String KEY_Y_PUNCT_SUB = "";
//    private static final String KEY_U_PUNCT_SUB = "";
//    private static final String KEY_I_PUNCT_SUB = "";
//    private static final String KEY_O_PUNCT_SUB = "";
//    private static final String KEY_P_PUNCT_SUB = "";
//    private static final String KEY_A_PUNCT_SUB = "";
//    private static final String KEY_S_PUNCT_SUB = "";
//    private static final String KEY_D_PUNCT_SUB = "";
//    private static final String KEY_F_PUNCT_SUB = "";
//    private static final String KEY_G_PUNCT_SUB = "";
//    private static final String KEY_H_PUNCT_SUB = "";
//    private static final String KEY_J_PUNCT_SUB = "";
//    private static final String KEY_K_PUNCT_SUB = "";
//    private static final String KEY_L_PUNCT_SUB = "";
//    private static final String KEY_Z_PUNCT_SUB = "";
//    private static final String KEY_X_PUNCT_SUB = "";
//    private static final String KEY_C_PUNCT_SUB = "";
//    private static final String KEY_V_PUNCT_SUB = "";
//    private static final String KEY_B_PUNCT_SUB = "";
//    private static final String KEY_N_PUNCT_SUB = "";
//    private static final String KEY_M_PUNCT_SUB = "";
//
//    // Keys with different display values
//    //private static final String KEY_ZWJ_DISPLAY = "/";
//    //private static final String KEY_SPACE_SUB_DISPLAY = "ᠶ᠋ᠢ ᠳᠤ ᠤᠨ";
//
//    // Use this constructor if you want the default style
//    public KeyboardEnglish(Context context) {
//        super(context);
//        init(context);
//    }
//
//    // all keyboards should include this custom constructor
//    // (there was no way to force it in the abstract Keyboard class)
//    public KeyboardEnglish(Context context, StyleBuilder style) {
//        super(context);
//        super.initStyle(style);
//        init(context);
//    }
//
//    protected void init(Context context) {
//
//        // keyboard layout
//
//        // | Q | W | E | R | T | Y | U | I | O | P |  Row 1
//        //   | A | S | D | F | G | H | J | K | L |    Row 2
//        // |shift| Z | X | C | V | B | N | M | del |  Row 3
//        // |  kb | ! | , |   space   | : | ? | ret |  Row 4
//
//        // actual layout work is done by Keyboard superclass's onLayout
//        mNumberOfKeysInRow = new int[]{10, 9, 9, 7}; // 36 keys total
//        // this is the percent to inset the row
//        mInsetWeightInRow = new float[]{0, 0.05f, 0, 0};
//        // the key weights for each row should sum to 1 (unless there is an inset)
//        mKeyWeights = new float[]{
//                0.1f, 0.1f, 0.1f, 0.1f, 0.1f, 0.1f, 0.1f, 0.1f, 0.1f, 0.1f,     // row 0
//                0.1f, 0.1f, 0.1f, 0.1f, 0.1f, 0.1f, 0.1f, 0.1f, 0.1f,           // row 1
//                0.15f, 0.1f, 0.1f, 0.1f, 0.1f, 0.1f, 0.1f, 0.1f, 0.15f,         // row 2
//                0.15f, 0.1f, 0.1f, 0.3f, 0.1f, 0.1f, 0.15f};                    // row 3
//
//        // Make sure that the total keys added to this ViewGroup below equals
//        // the mNumberOfKeysInRow and mKeyWeights array totals above.
//
//        // Row 1 (10 keys)
//
//        mKeyQ = new KeyTextNotRotated(context);
//        initTextKey(mKeyQ, key_Q, KEY_Q_PUNCT);
//
//        mKeyW = new KeyTextNotRotated(context);
//        initTextKey(mKeyW, KEY_W, KEY_W_PUNCT);
//
//        mKeyE = new KeyTextNotRotated(context);
//        initTextKey(mKeyE, KEY_E, KEY_E_PUNCT);
//
//        mKeyR = new KeyTextNotRotated(context);
//        initTextKey(mKeyR, KEY_R, KEY_R_PUNCT);
//
//        mKeyT = new KeyTextNotRotated(context);
//        initTextKey(mKeyT, KEY_T, KEY_T_PUNCT);
//
//        mKeyY = new KeyTextNotRotated(context);
//        initTextKey(mKeyY, KEY_Y, KEY_Y_PUNCT);
//
//        mKeyU = new KeyTextNotRotated(context);
//        initTextKey(mKeyU, KEY_U, KEY_U_PUNCT);
//
//        mKeyI = new KeyTextNotRotated(context);
//        initTextKey(mKeyI, KEY_I, KEY_I_PUNCT);
//
//        mKeyO = new KeyTextNotRotated(context);
//        initTextKey(mKeyO, KEY_O, KEY_O_PUNCT);
//
//        mKeyP = new KeyTextNotRotated(context);
//        initTextKey(mKeyP, KEY_P, KEY_P_PUNCT);
//
//        // Row 2 (10 keys)
//
//        mKeyA = new KeyTextNotRotated(context);
//        initTextKey(mKeyA, KEY_A, KEY_A_PUNCT);
//
//        mKeyS = new KeyTextNotRotated(context);
//        initTextKey(mKeyS, KEY_S, KEY_S_PUNCT);
//
//        mKeyD = new KeyTextNotRotated(context);
//        initTextKey(mKeyD, KEY_D, KEY_D_PUNCT);
//
//        mKeyF = new KeyTextNotRotated(context);
//        initTextKey(mKeyF, KEY_F, KEY_F_PUNCT);
//
//        mKeyG = new KeyTextNotRotated(context);
//        initTextKey(mKeyG, KEY_G, KEY_G_PUNCT);
//
//        mKeyH = new KeyTextNotRotated(context);
//        initTextKey(mKeyH, KEY_H, KEY_H_PUNCT);
//
//        mKeyJ = new KeyTextNotRotated(context);
//        initTextKey(mKeyJ, KEY_J, KEY_J_PUNCT);
//
//        mKeyK = new KeyTextNotRotated(context);
//        initTextKey(mKeyK, KEY_K, KEY_K_PUNCT);
//
//        mKeyL = new KeyTextNotRotated(context);
//        initTextKey(mKeyL, KEY_L, KEY_L_PUNCT);
//
//        //mKeyNg = new KeyText(context);
//        //initTextKey(mKeyNg, KEY_NG, KEY_NG_PUNCT);
//
//        // Row 3 (9 keys)
//
//        mKeyShift = new KeyShift(context);
//        mKeyShift.setShiftImageForTheme(mKeyImageTheme);
//        mKeyShift.setChangeListener(this);
//        //mKeyShift.setImage(getShiftImage(KeyImage.Theme.DARK)); // FIXME temp hardcoding theme
//        //mKeyShift.setOnClickListener(handleShift);
//        addView(mKeyShift);
//
//        mKeyZ = new KeyTextNotRotated(context);
//        initTextKey(mKeyZ, KEY_Z, KEY_Z_PUNCT);
//
//        mKeyX = new KeyTextNotRotated(context);
//        initTextKey(mKeyX, KEY_X, KEY_X_PUNCT);
//
//        mKeyC = new KeyTextNotRotated(context);
//        initTextKey(mKeyC, KEY_C, KEY_C_PUNCT);
//
//        mKeyV = new KeyTextNotRotated(context);
//        initTextKey(mKeyV, KEY_V, KEY_V_PUNCT);
//
//        mKeyB = new KeyTextNotRotated(context);
//        initTextKey(mKeyB, KEY_B, KEY_B_PUNCT);
//
//        mKeyN = new KeyTextNotRotated(context);
//        initTextKey(mKeyN, KEY_N, KEY_N_PUNCT);
//
//        mKeyM = new KeyTextNotRotated(context);
//        initTextKey(mKeyM, KEY_M, KEY_M_PUNCT);
//
//        // backspace
//        mKeyBackspace = new KeyImage(context);
//        mKeyBackspace.setImage(getBackspaceImage());
//        mKeyBackspace.setOnTouchListener(handleBackspace);
//        addView(mKeyBackspace);
//
//        // Row 4 (7 keys)
//
//        // keyboard
//        mKeyKeyboard = new KeyImage(context);
//        mKeyKeyboard.setImage(getKeyboardImage());
//        mKeyKeyboard.setOnTouchListener(textKeyTouchListener);
//        addView(mKeyKeyboard);
//
//        // exclamation
//        mKeyExclamation = new KeyTextNotRotated(context);
//        initTextKey(mKeyExclamation, KEY_EXCLAMATION, KEY_EXCLAMATION);
//        mKeyExclamation.setText(KEY_EXCLAMATION);
//
//        // comma
//        mKeyComma = new KeyTextNotRotated(context);
//        initTextKey(mKeyComma, KEY_COMMA, KEY_COMMA);
//        mKeyComma.setText(KEY_COMMA);
//
//        // space
//        mKeySpace = new KeyTextNotRotated(context);
//        initTextKey(mKeySpace, " ", " ");
//        mKeySpace.setText(KEY_SPACE);
//        //mKeySpace.setSubText(KEY_SPACE_SUB_DISPLAY);
//
//        // period
//        mKeyPeriod = new KeyTextNotRotated(context);
//        initTextKey(mKeyPeriod, KEY_PERIOD, KEY_PERIOD);
//        mKeyPeriod.setText(KEY_PERIOD);
//
//        // question mark
//        mKeyQuestion = new KeyTextNotRotated(context);
//        initTextKey(mKeyQuestion, KEY_QUESTION, KEY_QUESTION);
//        mKeyQuestion.setText(KEY_QUESTION);
//
//        // return
//        mKeyReturn = new KeyImage(context);
//        mKeyReturn.setImage(getReturnImage());
//        mKeyReturn.setOnTouchListener(textKeyTouchListener);
//        mKeyValues.put(mKeyReturn, "\n");
//        mKeyPunctuationValues.put(mKeyReturn, "\n");
//        addView(mKeyReturn);
//
//
//        setDisplayText(mIsShowingPunctuation);
//        applyThemeToKeys();
//    }
//
//
//    @Override
//    public void setDisplayText(boolean isShowingPunctuation) {
//        if (isShowingPunctuation) {
//            mKeyQ.setText(KEY_Q_PUNCT);
//            mKeyW.setText(KEY_W_PUNCT);
//            mKeyE.setText(KEY_E_PUNCT);
//            mKeyR.setText(KEY_R_PUNCT);
//            mKeyT.setText(KEY_T_PUNCT);
//            mKeyY.setText(KEY_Y_PUNCT);
//            mKeyU.setText(KEY_U_PUNCT);
//            mKeyI.setText(KEY_I_PUNCT);
//            mKeyO.setText(KEY_O_PUNCT);
//            mKeyP.setText(KEY_P_PUNCT);
//            mKeyA.setText(KEY_A_PUNCT);
//            mKeyS.setText(KEY_S_PUNCT);
//            mKeyD.setText(KEY_D_PUNCT);
//            mKeyF.setText(KEY_F_PUNCT);
//            mKeyG.setText(KEY_G_PUNCT);
//            mKeyH.setText(KEY_H_PUNCT);
//            mKeyJ.setText(KEY_J_PUNCT);
//            mKeyK.setText(KEY_K_PUNCT);
//            mKeyL.setText(KEY_L_PUNCT);
//            //mKeyNg.setText(KEY_NG_PUNCT);
//            //mKeyZwj.setText(KEY_ZWJ_PUNCT);
//            mKeyZ.setText(KEY_Z_PUNCT);
//            mKeyX.setText(KEY_X_PUNCT);
//            mKeyC.setText(KEY_C_PUNCT);
//            mKeyV.setText(KEY_V_PUNCT);
//            mKeyB.setText(KEY_B_PUNCT);
//            mKeyN.setText(KEY_N_PUNCT);
//            mKeyM.setText(KEY_M_PUNCT);
//
//            mKeyQ.setSubText(KEY_Q_PUNCT_SUB);
//            mKeyW.setSubText(KEY_W_PUNCT_SUB);
//            mKeyE.setSubText(KEY_E_PUNCT_SUB);
//            mKeyR.setSubText(KEY_R_PUNCT_SUB);
//            mKeyT.setSubText(KEY_T_PUNCT_SUB);
//            mKeyY.setSubText(KEY_Y_PUNCT_SUB);
//            mKeyU.setSubText(KEY_U_PUNCT_SUB);
//            mKeyI.setSubText(KEY_I_PUNCT_SUB);
//            mKeyO.setSubText(KEY_O_PUNCT_SUB);
//            mKeyP.setSubText(KEY_P_PUNCT_SUB);
//            mKeyA.setSubText(KEY_A_PUNCT_SUB);
//            mKeyS.setSubText(KEY_S_PUNCT_SUB);
//            mKeyD.setSubText(KEY_D_PUNCT_SUB);
//            mKeyF.setSubText(KEY_F_PUNCT_SUB);
//            mKeyG.setSubText(KEY_G_PUNCT_SUB);
//            mKeyH.setSubText(KEY_H_PUNCT_SUB);
//            mKeyJ.setSubText(KEY_J_PUNCT_SUB);
//            mKeyK.setSubText(KEY_K_PUNCT_SUB);
//            mKeyL.setSubText(KEY_L_PUNCT_SUB);
//            //mKeyNg.setSubText(KEY_NG_PUNCT_SUB);
//            //mKeyZwj.setSubText(KEY_ZWJ_PUNCT_SUB);
//            mKeyZ.setSubText(KEY_Z_PUNCT_SUB);
//            mKeyX.setSubText(KEY_X_PUNCT_SUB);
//            mKeyC.setSubText(KEY_C_PUNCT_SUB);
//            mKeyV.setSubText(KEY_V_PUNCT_SUB);
//            mKeyB.setSubText(KEY_B_PUNCT_SUB);
//            mKeyN.setSubText(KEY_N_PUNCT_SUB);
//            mKeyM.setSubText(KEY_M_PUNCT_SUB);
//        } else {
//            mKeyQ.setText(key_Q);
//            mKeyW.setText(KEY_W);
//            mKeyE.setText(KEY_E);
//            mKeyR.setText(KEY_R);
//            mKeyT.setText(KEY_T);
//            mKeyY.setText(KEY_Y);
//            mKeyU.setText(KEY_U);
//            mKeyI.setText(KEY_I);
//            mKeyO.setText(KEY_O);
//            mKeyP.setText(KEY_P);
//            mKeyA.setText(KEY_A);
//            mKeyS.setText(KEY_S);
//            mKeyD.setText(KEY_D);
//            mKeyF.setText(KEY_F);
//            mKeyG.setText(KEY_G);
//            mKeyH.setText(KEY_H);
//            mKeyJ.setText(KEY_J);
//            mKeyK.setText(KEY_K);
//            mKeyL.setText(KEY_L);
//            //mKeyNg.setText(KEY_NG);
//            //mKeyZwj.setText(KEY_ZWJ_DISPLAY);
//            mKeyZ.setText(KEY_Z);
//            mKeyX.setText(KEY_X);
//            mKeyC.setText(KEY_C);
//            mKeyV.setText(KEY_V);
//            mKeyB.setText(KEY_B);
//            mKeyN.setText(KEY_N);
//            mKeyM.setText(KEY_M);
//
//            mKeyQ.setSubText(KEY_Q_SUB);
//            mKeyW.setSubText(KEY_W_SUB);
//            mKeyE.setSubText(KEY_E_SUB);
//            mKeyR.setSubText(KEY_R_SUB);
//            mKeyT.setSubText(KEY_T_SUB);
//            mKeyY.setSubText(KEY_Y_SUB);
//            mKeyU.setSubText(KEY_U_SUB);
//            mKeyI.setSubText(KEY_I_SUB);
//            mKeyO.setSubText(KEY_O_SUB);
//            mKeyP.setSubText(KEY_P_SUB);
//            mKeyA.setSubText(KEY_A_SUB);
//            mKeyS.setSubText(KEY_S_SUB);
//            mKeyD.setSubText(KEY_D_SUB);
//            mKeyF.setSubText(KEY_F_SUB);
//            mKeyG.setSubText(KEY_G_SUB);
//            mKeyH.setSubText(KEY_H_SUB);
//            mKeyJ.setSubText(KEY_J_SUB);
//            mKeyK.setSubText(KEY_K_SUB);
//            mKeyL.setSubText(KEY_L_SUB);
//            //mKeyNg.setSubText(KEY_NG_SUB);
//            //mKeyZwj.setSubText(KEY_ZWJ_SUB);
//            mKeyZ.setSubText(KEY_Z_SUB);
//            mKeyX.setSubText(KEY_X_SUB);
//            mKeyC.setSubText(KEY_C_SUB);
//            mKeyV.setSubText(KEY_V_SUB);
//            mKeyB.setSubText(KEY_B_SUB);
//            mKeyN.setSubText(KEY_N_SUB);
//            mKeyM.setSubText(KEY_M_SUB);
//        }
//    }
//
//    @Override
//    public void onShiftChanged(boolean shiftIsOn) {
//
//        if (shiftIsOn) {
//            switchToUpperCase();
//        } else {
//            switchToLowerCase();
//        }
//    }
//
//    private void switchToLowerCase() {
//        mKeyQ.setText(key_Q.toLowerCase());
//        mKeyW.setText(KEY_W.toLowerCase());
//        mKeyE.setText(KEY_E.toLowerCase());
//        mKeyR.setText(KEY_R.toLowerCase());
//        mKeyT.setText(KEY_T.toLowerCase());
//        mKeyY.setText(KEY_Y.toLowerCase());
//        mKeyU.setText(KEY_U.toLowerCase());
//        mKeyI.setText(KEY_I.toLowerCase());
//        mKeyO.setText(KEY_O.toLowerCase());
//        mKeyP.setText(KEY_P.toLowerCase());
//        mKeyA.setText(KEY_A.toLowerCase());
//        mKeyS.setText(KEY_S.toLowerCase());
//        mKeyD.setText(KEY_D.toLowerCase());
//        mKeyF.setText(KEY_F.toLowerCase());
//        mKeyG.setText(KEY_G.toLowerCase());
//        mKeyH.setText(KEY_H.toLowerCase());
//        mKeyJ.setText(KEY_J.toLowerCase());
//        mKeyK.setText(KEY_K.toLowerCase());
//        mKeyL.setText(KEY_L.toLowerCase());
//        mKeyZ.setText(KEY_Z.toLowerCase());
//        mKeyX.setText(KEY_X.toLowerCase());
//        mKeyC.setText(KEY_C.toLowerCase());
//        mKeyV.setText(KEY_V.toLowerCase());
//        mKeyB.setText(KEY_B.toLowerCase());
//        mKeyN.setText(KEY_N.toLowerCase());
//        mKeyM.setText(KEY_M.toLowerCase());
//    }
//
//    private void switchToUpperCase() {
//        mKeyQ.setText(key_Q.toUpperCase());
//        mKeyW.setText(KEY_W.toUpperCase());
//        mKeyE.setText(KEY_E.toUpperCase());
//        mKeyR.setText(KEY_R.toUpperCase());
//        mKeyT.setText(KEY_T.toUpperCase());
//        mKeyY.setText(KEY_Y.toUpperCase());
//        mKeyU.setText(KEY_U.toUpperCase());
//        mKeyI.setText(KEY_I.toUpperCase());
//        mKeyO.setText(KEY_O.toUpperCase());
//        mKeyP.setText(KEY_P.toUpperCase());
//        mKeyA.setText(KEY_A.toUpperCase());
//        mKeyS.setText(KEY_S.toUpperCase());
//        mKeyD.setText(KEY_D.toUpperCase());
//        mKeyF.setText(KEY_F.toUpperCase());
//        mKeyG.setText(KEY_G.toUpperCase());
//        mKeyH.setText(KEY_H.toUpperCase());
//        mKeyJ.setText(KEY_J.toUpperCase());
//        mKeyK.setText(KEY_K.toUpperCase());
//        mKeyL.setText(KEY_L.toUpperCase());
//        mKeyZ.setText(KEY_Z.toUpperCase());
//        mKeyX.setText(KEY_X.toUpperCase());
//        mKeyC.setText(KEY_C.toUpperCase());
//        mKeyV.setText(KEY_V.toUpperCase());
//        mKeyB.setText(KEY_B.toUpperCase());
//        mKeyN.setText(KEY_N.toUpperCase());
//        mKeyM.setText(KEY_M.toUpperCase());
//    }
//
//
//
//    @Override
//    public PopupCandidates getPopupCandidates(Key key) {
//        // these are the choices to display in the popup (and corresponding unicode values)
//        PopupCandidates candidates = null;
////
////        // get the appropriate candidates based on the key pressed
////        if (key == mKeyQ) {
////            candidates = getCandidatesForQ();
////        } else if (key == mKeyW) {
////            candidates = getCandidatesForW();
////        } else if (key == mKeyE) {
////            candidates = getCandidatesForE(isIsolateOrInitial());
////        } else if (key == mKeyR) {
////            candidates = getCandidatesForR();
////        } else if (key == mKeyT) {
////            candidates = getCandidatesForT(isIsolateOrInitial());
////        } else if (key == mKeyY) {
////            candidates = getCandidatesForY(isIsolateOrInitial());
////        } else if (key == mKeyU) {
////            candidates = getCandidatesForU(isIsolateOrInitial());
////        } else if (key == mKeyI) {
////            candidates = getCandidatesForI(isIsolateOrInitial());
////        } else if (key == mKeyO) {
////            candidates = getCandidatesForO(isIsolateOrInitial());
////        } else if (key == mKeyP) {
////            candidates = getCandidatesForP();
////        } else if (key == mKeyA) {
////            candidates = getCandidatesForA(isIsolateOrInitial());
////        } else if (key == mKeyS) {
////            candidates = getCandidatesForS();
////        } else if (key == mKeyD) {
////            candidates = getCandidatesForD(isIsolateOrInitial());
////        } else if (key == mKeyF) {
////            candidates = getCandidatesForF();
////        } else if (key == mKeyG) {
////            candidates = getCandidatesForG(isIsolateOrInitial());
////        } else if (key == mKeyH) {
////            candidates = getCandidatesForH();
////        } else if (key == mKeyJ) {
////            candidates = getCandidatesForJ();
////        } else if (key == mKeyK) {
////            candidates = getCandidatesForK();
////        } else if (key == mKeyL) {
////            candidates = getCandidatesForL();
//////        } else if (key == mKeyNg) {
//////            candidates = getCandidatesForNG();
//////        } else if (key == mKeyZwj) {
//////            candidates = getCandidatesForZwj();
////        } else if (key == mKeyZ) {
////            candidates = getCandidatesForZ();
////        } else if (key == mKeyX) {
////            candidates = getCandidatesForX();
////        } else if (key == mKeyC) {
////            candidates = getCandidatesForC(isIsolateOrInitial());
////        } else if (key == mKeyV) {
////            candidates = getCandidatesForV(isIsolateOrInitial());
////        } else if (key == mKeyB) {
////            candidates = getCandidatesForB();
////        } else if (key == mKeyN) {
////            candidates = getCandidatesForN(isIsolateOrInitial());
////        } else if (key == mKeyM) {
////            candidates = getCandidatesForM();
////        } else if (key == mKeyKeyboard) {
////            candidates = getCandidatesForKeyboard();
////        } else if (key == mKeySpace) {
////            candidates = getCandidatesForSpace();
////        }
//
//        return candidates;
//    }
//
//    private PopupCandidates getCandidatesForQ() {
//        if (mIsShowingPunctuation)
//            return new PopupCandidates(KEY_Q_PUNCT_SUB);
//        return new PopupCandidates(MongolCode.Uni.CHI);
//    }
//
//    private PopupCandidates getCandidatesForW() {
//        if (mIsShowingPunctuation)
//            return new PopupCandidates(KEY_W_PUNCT_SUB);
//        return null;
//    }
//
//    private PopupCandidates getCandidatesForE(boolean isIsolateOrInitial) {
//        if (mIsShowingPunctuation)
//            return new PopupCandidates(KEY_E_PUNCT_SUB);
//
//        if (isIsolateOrInitial)
//            return new PopupCandidates(MongolCode.Uni.EE);
//
//        // medial || final
//        char previousChar = getPreviousChar();
//        if (MongolCode.isMvsConsonant(previousChar)
//                && previousChar != MongolCode.Uni.QA && previousChar != MongolCode.Uni.GA) {
//            // include MVS
//            return new PopupCandidates(
//                    new String[]{
//                            "" + MongolCode.Uni.MVS + MongolCode.Uni.E,
//                            "" + MongolCode.Uni.EE},
//                    new String[]{
//                            "" + MongolCode.Uni.ZWJ + previousChar + MongolCode.Uni.MVS + MongolCode.Uni.E,
//                            "" + MongolCode.Uni.EE});
//        } else {
//            return new PopupCandidates(MongolCode.Uni.EE);
//        }
//    }
//
//    private PopupCandidates getCandidatesForR() {
//        if (mIsShowingPunctuation)
//            return new PopupCandidates(KEY_R_PUNCT_SUB);
//        return new PopupCandidates(MongolCode.Uni.ZRA);
//    }
//
//    private PopupCandidates getCandidatesForT(boolean isIsolateOrInitial) {
//        if (mIsShowingPunctuation)
//            return new PopupCandidates(KEY_T_PUNCT_SUB);
//
//        if (!isIsolateOrInitial) { // medial/final
//            return new PopupCandidates(new String[]{
//                    "" + MongolCode.Uni.TA + MongolCode.Uni.FVS1},
//                    new String[]{
//                            "" + MongolCode.Uni.MONGOLIAN_NIRUGU +
//                                    MongolCode.Uni.TA + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ});
//        }
//        return null;
//    }
//
//    private PopupCandidates getCandidatesForY(boolean isIsolateOrInitial) {
//        if (mIsShowingPunctuation)
//            return new PopupCandidates(KEY_Y_PUNCT_SUB);
//
//        if (isIsolateOrInitial) {
//            return new PopupCandidates(new String[]{
//                    "" + MongolCode.Uni.YA + MongolCode.Uni.FVS1},
//                    new String[]{
//                            "" + MongolCode.Uni.YA + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ});
//        } else { // medial/final
//            return new PopupCandidates(new String[]{
//                    "" + MongolCode.Uni.YA + MongolCode.Uni.FVS1},
//                    new String[]{
//                            "" + MongolCode.Uni.ZWJ + MongolCode.Uni.YA + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ});
//        }
//    }
//
//    private PopupCandidates getCandidatesForU(boolean isIsolateOrInitial) {
//        if (mIsShowingPunctuation)
//            return new PopupCandidates(KEY_U_PUNCT_SUB);
//
//        if (!isIsolateOrInitial) { // medial/final
//            return new PopupCandidates(
//                    new String[]{
//                            "" + MongolCode.Uni.UE + MongolCode.Uni.FVS2,
//                            "" + MongolCode.Uni.UE + MongolCode.Uni.FVS1},
//                    new String[]{
//                            "" + MongolCode.Uni.ZWJ + MongolCode.Uni.UE + MongolCode.Uni.FVS2 + MongolCode.Uni.ZWJ,
//                            "" + MongolCode.Uni.ZWJ + MongolCode.Uni.UE + MongolCode.Uni.FVS1});
//        }
//        return null;
//    }
//
//    private PopupCandidates getCandidatesForI(boolean isIsolateOrInitial) {
//        if (mIsShowingPunctuation)
//            return new PopupCandidates(KEY_I_PUNCT_SUB);
//
//        if (!isIsolateOrInitial) { // medial/final
//            char prevChar = getPreviousChar();
//            if (MongolCode.isVowel(prevChar)) {
//                return new PopupCandidates(
//                        new String[]{
//                                "" + MongolCode.Uni.I + MongolCode.Uni.FVS2, // override double tooth I after vowel (Unicode 10.0 deviation)
//                                //"" + MongolCode.Uni.ZWJ + MongolCode.Uni.I, // alternate method to override double tooth I
//                                "" + MongolCode.Uni.I + MongolCode.Uni.FVS1},
//                        new String[]{
//                                "" + MongolCode.Uni.ZWJ + MongolCode.Uni.I + MongolCode.Uni.ZWJ,
//                                "" + MongolCode.Uni.ZWJ + MongolCode.Uni.I + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ});
//            } else {
//                return new PopupCandidates(
//                        new String[]{"" + MongolCode.Uni.I + MongolCode.Uni.FVS1},
//                        new String[]{
//                                "" + MongolCode.Uni.ZWJ + MongolCode.Uni.I + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ});
//            }
//
//        }
//        return null;
//    }
//
//    private PopupCandidates getCandidatesForO(boolean isIsolateOrInitial) {
//        if (mIsShowingPunctuation)
//            return new PopupCandidates(KEY_O_PUNCT_SUB);
//
//        if (!isIsolateOrInitial) { // medial/final
//            return new PopupCandidates(
//                    new String[]{
//                            "" + MongolCode.Uni.OE + MongolCode.Uni.FVS2,
//                            "" + MongolCode.Uni.OE + MongolCode.Uni.FVS1},
//                    new String[]{
//                            "" + MongolCode.Uni.ZWJ + MongolCode.Uni.OE + MongolCode.Uni.FVS2 + MongolCode.Uni.ZWJ,
//                            "" + MongolCode.Uni.ZWJ + MongolCode.Uni.OE + MongolCode.Uni.FVS1});
//        }
//        return null;
//    }
//
//    private PopupCandidates getCandidatesForP() {
//        if (mIsShowingPunctuation)
//            return new PopupCandidates(KEY_P_PUNCT_SUB);
//        return null;
//    }
//
//    private PopupCandidates getCandidatesForA(boolean isIsolateOrInitial) {
//        if (mIsShowingPunctuation)
//            return new PopupCandidates(KEY_A_PUNCT_SUB);
//
//        if (isIsolateOrInitial)
//            return new PopupCandidates(new String[]{
//                    "" + MongolCode.Uni.A + MongolCode.Uni.FVS1,
//                    "" + MongolCode.Uni.MONGOLIAN_NIRUGU});
//
//        // medial || final
//        char previousChar = getPreviousChar();
//        if (MongolCode.isMvsConsonant(previousChar)) {
//            // include MVS
//            return new PopupCandidates(
//                    new String[]{
//                            "" + MongolCode.Uni.MVS + MongolCode.Uni.A,
//                            "" + MongolCode.Uni.A + MongolCode.Uni.FVS1,
//                            "" + MongolCode.Uni.MONGOLIAN_NIRUGU},
//                    new String[]{
//                            "" + MongolCode.Uni.ZWJ + previousChar + MongolCode.Uni.MVS + MongolCode.Uni.A,
//                            "" + MongolCode.Uni.ZWJ + MongolCode.Uni.A + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ,
//                            "" + MongolCode.Uni.MONGOLIAN_NIRUGU});
//        } else {
//            return new PopupCandidates(
//                    new String[]{
//                            "" + MongolCode.Uni.A + MongolCode.Uni.FVS1,
//                            "" + MongolCode.Uni.MONGOLIAN_NIRUGU},
//                    new String[]{
//                            "" + MongolCode.Uni.ZWJ + MongolCode.Uni.A + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ,
//                            "" + MongolCode.Uni.MONGOLIAN_NIRUGU});
//        }
//    }
//
//    private PopupCandidates getCandidatesForS() {
//        if (mIsShowingPunctuation)
//            return new PopupCandidates(KEY_S_PUNCT_SUB);
//        return null;
//    }
//
//    private PopupCandidates getCandidatesForD(boolean isIsolateOrInitial) {
//        if (mIsShowingPunctuation)
//            return new PopupCandidates(KEY_D_PUNCT_SUB);
//
//        if (isIsolateOrInitial) {
//            char prevChar = getPreviousChar();
//            String[] unicode;
//            if (prevChar == MongolCode.Uni.NNBS) {
//                unicode = new String[]{"" + MongolCode.Uni.DA};
//            } else {
//                unicode = new String[]{"" + MongolCode.Uni.DA + MongolCode.Uni.FVS1};
//            }
//            return new PopupCandidates(unicode,
//                    new String[]{"" + MongolCode.Uni.DA + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ});
//            // TODO if this turns out to be an isolate then the FVS1 should be removed
//        }
//
//        // medial/final
//        return new PopupCandidates(
//                new String[]{"" + MongolCode.Uni.DA + MongolCode.Uni.FVS1},
//                new String[]{"" + MongolCode.Uni.ZWJ + MongolCode.Uni.DA + MongolCode.Uni.FVS1});
//    }
//
//    private PopupCandidates getCandidatesForF() {
//        if (mIsShowingPunctuation)
//            return new PopupCandidates(KEY_F_PUNCT_SUB);
//        return null;
//    }
//
//    private PopupCandidates getCandidatesForG(boolean isIsolateOrInitial) {
//        if (mIsShowingPunctuation)
//            return new PopupCandidates(new String[]{"+", "-", "×", "÷", "≠", "≈"});
//
//        if (!isIsolateOrInitial) { // medial/final
//            return new PopupCandidates(
//                    new String[]{
//                            "" + MongolCode.Uni.GA + MongolCode.Uni.FVS1, // see note on MongolCode(FINA_GA_FVS1)
//                            "" + MongolCode.Uni.GA + MongolCode.Uni.FVS2}, // see note on MongolCode(FINA_GA_FVS2)
//                    new String[]{
//                            "" + MongolCode.Uni.ZWJ + MongolCode.Uni.GA + MongolCode.Uni.FVS1,
//                            "" + MongolCode.Uni.ZWJ + MongolCode.Uni.GA + MongolCode.Uni.FVS2});
//        }
//        return null;
//    }
//
//    private PopupCandidates getCandidatesForH() {
//        if (mIsShowingPunctuation)
//            return new PopupCandidates(new String[]{"$", "₮"});
//        return new PopupCandidates(MongolCode.Uni.HAA);
//    }
//
//    private PopupCandidates getCandidatesForJ() {
//        if (mIsShowingPunctuation)
//            return new PopupCandidates(KEY_J_PUNCT_SUB);
//        return new PopupCandidates(MongolCode.Uni.ZHI);
//    }
//
//    private PopupCandidates getCandidatesForK() {
//        if (mIsShowingPunctuation)
//            return new PopupCandidates(KEY_K_PUNCT_SUB);
//        return null;
//    }
//
//    private PopupCandidates getCandidatesForL() {
//        if (mIsShowingPunctuation)
//            return new PopupCandidates(KEY_L_PUNCT_SUB);
//        return new PopupCandidates(MongolCode.Uni.LHA);
//    }
//
////    private PopupCandidates getCandidatesForNG() {
////        if (mIsShowingPunctuation)
////            return new PopupCandidates(KEY_NG_PUNCT_SUB);
////        return null;
////    }
////
////    private PopupCandidates getCandidatesForZwj() {
////        if (mIsShowingPunctuation)
////            return new PopupCandidates(KEY_ZWJ_PUNCT_SUB);
////        return null;
////    }
//
//    private PopupCandidates getCandidatesForZ() {
//        if (mIsShowingPunctuation)
//            return new PopupCandidates(new String[]{KEY_Z_PUNCT_SUB, "*"});
//        return new PopupCandidates(MongolCode.Uni.TSA);
//    }
//
//    private PopupCandidates getCandidatesForX() {
//        if (mIsShowingPunctuation)
//            return new PopupCandidates(KEY_X_PUNCT_SUB);
//        return null;
//    }
//
//    private PopupCandidates getCandidatesForC(boolean isIsolateOrInitial) {
//        if (mIsShowingPunctuation)
//            return new PopupCandidates(KEY_C_PUNCT_SUB);
//
//        if (!isIsolateOrInitial) { // medial/final
//            return new PopupCandidates(
//                    new String[]{
//                            "" + MongolCode.Uni.O + MongolCode.Uni.FVS1,
//                            "" + MongolCode.Uni.O + MongolCode.Uni.FVS1},
//                    new String[]{
//                            "" + MongolCode.Uni.ZWJ + MongolCode.Uni.O + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ,
//                            "" + MongolCode.Uni.ZWJ + MongolCode.Uni.O + MongolCode.Uni.FVS1});
//        }
//        return null;
//    }
//
//    private PopupCandidates getCandidatesForV(boolean isIsolateOrInitial) {
//        if (mIsShowingPunctuation)
//            return new PopupCandidates(KEY_V_PUNCT_SUB);
//
//        if (!isIsolateOrInitial) { // medial/final
//            return new PopupCandidates(
//                    new String[]{
//                            "" + MongolCode.Uni.U + MongolCode.Uni.FVS1,
//                            "" + MongolCode.Uni.U + MongolCode.Uni.FVS1},
//                    new String[]{
//                            "" + MongolCode.Uni.ZWJ + MongolCode.Uni.U + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ,
//                            "" + MongolCode.Uni.ZWJ + MongolCode.Uni.U + MongolCode.Uni.FVS1});
//        }
//        return null;
//    }
//
//    private PopupCandidates getCandidatesForB() {
//        if (mIsShowingPunctuation)
//            return new PopupCandidates(KEY_B_PUNCT_SUB);
//        return null;
//    }
//
//    private PopupCandidates getCandidatesForN(boolean isIsolateOrInitial) {
//        if (mIsShowingPunctuation)
//            return new PopupCandidates(KEY_N_PUNCT_SUB);
//
//        if (!isIsolateOrInitial) { // medial/final
//            return new PopupCandidates(
//                    new String[]{
//                            "" + MongolCode.Uni.NA + MongolCode.Uni.ZWJ, // only(?) way to override dotted N before vowel in Unicode 10.0
//                            "" + MongolCode.Uni.NA + MongolCode.Uni.FVS1},
//                    new String[]{
//                            "" + MongolCode.Uni.ZWJ + MongolCode.Uni.NA + MongolCode.Uni.ZWJ,
//                            "" + MongolCode.Uni.ZWJ + MongolCode.Uni.NA + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ});
//        }
//        return null;
//    }
//
//    private PopupCandidates getCandidatesForM() {
//        if (mIsShowingPunctuation)
//            return new PopupCandidates(new String[]{
//                    "" + MongolCode.Uni.DOUBLE_EXCLAMATION_MARK,
//                    "" + MongolCode.Uni.DOUBLE_QUESTION_MARK,
//                    "" + MongolCode.Uni.EXCLAMATION_QUESTION_MARK});
//        return null;
//    }
//
//    @Override
//    public String getDisplayName() {
//        return DISPLAY_NAME;
//    }
//
//    //private PopupCandidates getCandidatesForSpace() {
//      //  return getCandidatesForSuffix();
//    //}
//
//
//}
