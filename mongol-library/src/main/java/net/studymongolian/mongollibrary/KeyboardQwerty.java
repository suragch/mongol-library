package net.studymongolian.mongollibrary;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import java.util.HashMap;
import java.util.Map;


public class KeyboardQwerty extends Keyboard {

    // name to use in the keyboard popup chooser
    private static final String DISPLAY_NAME = "ᠺᠣᠮᠫᠢᠦ᠋ᠲ᠋ᠧᠷ";

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
    protected KeyText mKeyNg;

    // Row 3
    protected KeyText mKeyZwj; // break text with ZWJ
    protected KeyText mKeyZ;
    protected KeyText mKeyX;
    protected KeyText mKeyC;
    protected KeyText mKeyV;
    protected KeyText mKeyB;
    protected KeyText mKeyN;
    protected KeyText mKeyM;
    protected KeyImage mKeyBackspace;

    // Row 4
    // protected KeyImage mKeyKeyboard; // defined in super class
    protected KeyText mKeyExclamation;
    protected KeyText mKeyComma;
    protected KeyText mKeySpace;
    protected KeyText mKeyPeriod;
    protected KeyText mKeyQuestion;
    protected KeyImage mKeyReturn;

    // These are all input values (some display values are different)
    private static final String KEY_Q = String.valueOf(MongolCode.Uni.CHA);
    private static final String KEY_W = String.valueOf(MongolCode.Uni.WA);
    private static final String KEY_E = String.valueOf(MongolCode.Uni.E);
    private static final String KEY_R = String.valueOf(MongolCode.Uni.RA);
    private static final String KEY_T = String.valueOf(MongolCode.Uni.TA);
    private static final String KEY_Y = String.valueOf(MongolCode.Uni.YA);
    private static final String KEY_U = String.valueOf(MongolCode.Uni.UE);
    private static final String KEY_I = String.valueOf(MongolCode.Uni.I);
    private static final String KEY_O = String.valueOf(MongolCode.Uni.OE);
    private static final String KEY_P = String.valueOf(MongolCode.Uni.PA);
    private static final String KEY_A = String.valueOf(MongolCode.Uni.A);
    private static final String KEY_S = String.valueOf(MongolCode.Uni.SA);
    private static final String KEY_D = String.valueOf(MongolCode.Uni.DA);
    private static final String KEY_F = String.valueOf(MongolCode.Uni.FA);
    private static final String KEY_G = String.valueOf(MongolCode.Uni.GA);
    private static final String KEY_H = String.valueOf(MongolCode.Uni.QA);
    private static final String KEY_J = String.valueOf(MongolCode.Uni.JA);
    private static final String KEY_K = String.valueOf(MongolCode.Uni.KA);
    private static final String KEY_L = String.valueOf(MongolCode.Uni.LA);
    private static final String KEY_NG = String.valueOf(MongolCode.Uni.ANG);
    // TODO maybe this key should be used for suffixes instead. Longpress could be ZWJ.
    private static final String KEY_ZWJ = String.valueOf(MongolCode.Uni.ZWJ);
    private static final String KEY_Z = String.valueOf(MongolCode.Uni.ZA);
    private static final String KEY_X = String.valueOf(MongolCode.Uni.SHA);
    private static final String KEY_C = String.valueOf(MongolCode.Uni.O);
    private static final String KEY_V = String.valueOf(MongolCode.Uni.U);
    private static final String KEY_B = String.valueOf(MongolCode.Uni.BA);
    private static final String KEY_N = String.valueOf(MongolCode.Uni.NA);
    private static final String KEY_M = String.valueOf(MongolCode.Uni.MA);
    private static final String KEY_EXCLAMATION = String.valueOf(MongolCode.Uni.VERTICAL_EXCLAMATION_MARK);
    private static final String KEY_COMMA = String.valueOf(MongolCode.Uni.MONGOLIAN_COMMA);
    private static final String KEY_SPACE = " ";
    private static final String KEY_PERIOD = String.valueOf(MongolCode.Uni.MONGOLIAN_FULL_STOP);
    private static final String KEY_QUESTION = String.valueOf(MongolCode.Uni.VERTICAL_QUESTION_MARK);


    private static final String KEY_Q_SUB = String.valueOf(MongolCode.Uni.CHI);
    private static final String KEY_W_SUB = "";
    private static final String KEY_E_SUB = String.valueOf(MongolCode.Uni.EE);
    private static final String KEY_R_SUB = String.valueOf(MongolCode.Uni.ZRA);
    private static final String KEY_T_SUB = "";
    private static final String KEY_Y_SUB = "";
    private static final String KEY_U_SUB = "";
    private static final String KEY_I_SUB = "";
    private static final String KEY_O_SUB = "";
    private static final String KEY_P_SUB = "";
    private static final String KEY_A_SUB = "";
    private static final String KEY_S_SUB = "";
    private static final String KEY_D_SUB = "";
    private static final String KEY_F_SUB = "";
    private static final String KEY_G_SUB = "";
    private static final String KEY_H_SUB = String.valueOf(MongolCode.Uni.HAA);
    private static final String KEY_J_SUB = String.valueOf(MongolCode.Uni.ZHI);
    private static final String KEY_K_SUB = "";
    private static final String KEY_L_SUB = String.valueOf(MongolCode.Uni.LHA);
    private static final String KEY_NG_SUB = "";
    private static final String KEY_ZWJ_SUB = ""; // TODO should we add ZWNJ?
    private static final String KEY_Z_SUB = String.valueOf(MongolCode.Uni.TSA);
    private static final String KEY_X_SUB = "";
    private static final String KEY_C_SUB = "";
    private static final String KEY_V_SUB = "";
    private static final String KEY_B_SUB = "";
    private static final String KEY_N_SUB = "";
    private static final String KEY_M_SUB = "";
    private static final String KEY_EXCLAMATION_SUB = "";
    private static final String KEY_COMMA_SUB = "";
    private static final String KEY_PERIOD_SUB = "";
    private static final String KEY_QUESTION_SUB = "";

    private static final String KEY_Q_PUNCT = "1";
    private static final String KEY_W_PUNCT = "2";
    private static final String KEY_E_PUNCT = "3";
    private static final String KEY_R_PUNCT = "4";
    private static final String KEY_T_PUNCT = "5";
    private static final String KEY_Y_PUNCT = "6";
    private static final String KEY_U_PUNCT = "7";
    private static final String KEY_I_PUNCT = "8";
    private static final String KEY_O_PUNCT = "9";
    private static final String KEY_P_PUNCT = "0";
    private static final String KEY_A_PUNCT = String.valueOf(MongolCode.Uni.VERTICAL_LEFT_PARENTHESIS);
    private static final String KEY_S_PUNCT = String.valueOf(MongolCode.Uni.VERTICAL_RIGHT_PARENTHESIS);
    private static final String KEY_D_PUNCT = String.valueOf(MongolCode.Uni.VERTICAL_LEFT_DOUBLE_ANGLE_BRACKET);
    private static final String KEY_F_PUNCT = String.valueOf(MongolCode.Uni.VERTICAL_RIGHT_DOUBLE_ANGLE_BRACKET);
    private static final String KEY_G_PUNCT = "=";
    private static final String KEY_H_PUNCT = "¥";
    private static final String KEY_J_PUNCT = "'";
    private static final String KEY_K_PUNCT = "\"";
    private static final String KEY_L_PUNCT = "#";
    private static final String KEY_NG_PUNCT = "|";
    private static final String KEY_ZWJ_PUNCT = "/";
    private static final String KEY_Z_PUNCT = String.valueOf(MongolCode.Uni.REFERENCE_MARK);
    private static final String KEY_X_PUNCT = String.valueOf(MongolCode.Uni.MONGOLIAN_BIRGA);
    private static final String KEY_C_PUNCT = String.valueOf(MongolCode.Uni.MIDDLE_DOT);
    private static final String KEY_V_PUNCT = String.valueOf(MongolCode.Uni.MONGOLIAN_ELLIPSIS);
    private static final String KEY_B_PUNCT = String.valueOf(MongolCode.Uni.VERTICAL_EM_DASH);
    private static final String KEY_N_PUNCT = String.valueOf(MongolCode.Uni.MONGOLIAN_COLON);
    private static final String KEY_M_PUNCT = String.valueOf(MongolCode.Uni.QUESTION_EXCLAMATION_MARK);

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
    private static final String KEY_ZWJ_DISPLAY = "/";
    private static final String KEY_SPACE_SUB_DISPLAY = "ᠶ᠋ᠢ ᠳᠤ ᠤᠨ";

    // Use this constructor if you want the default style
    public KeyboardQwerty(Context context) {
        super(context);
        init(context);
    }

    // all keyboards should include this custom constructor
    // (there was no way to force it in the abstract Keyboard class)
    public KeyboardQwerty(Context context, StyleBuilder style) {
        super(context);
        super.initStyle(style);
        init(context);
    }

    protected void init(Context context) {

        // keyboard layout

        // | Q | W | E | R | T | Y | U | I | O | P |  Row 1
        // | A | S | D | F | G | H | J | K | L | NG|  Row 2
        // |  /  | Z | X | C | V | B | N | M | del |  Row 3
        // |  kb | ! | , |   space   | : | ? | ret |  Row 4

        // actual layout work is done by Keyboard superclass's onLayout
        mNumberOfKeysInRow = new int[]{10, 10, 9, 7}; // 36 keys total
        // the key weights for each row should sum to 1
        mKeyWeights = new float[]{
                0.1f, 0.1f, 0.1f, 0.1f, 0.1f, 0.1f, 0.1f, 0.1f, 0.1f, 0.1f,     // row 0
                0.1f, 0.1f, 0.1f, 0.1f, 0.1f, 0.1f, 0.1f, 0.1f, 0.1f, 0.1f,     // row 1
                0.15f, 0.1f, 0.1f, 0.1f, 0.1f, 0.1f, 0.1f, 0.1f, 0.15f,         // row 2
                0.15f, 0.1f, 0.1f, 0.3f, 0.1f, 0.1f, 0.15f};                   // row 3

        // Make sure that the total keys added to this ViewGroup below equals
        // the mNumberOfKeysInRow and mKeyWeights array totals above.

        // Row 1 (10 keys)

        mKeyQ = new KeyText(context);
        initTextKey(mKeyQ, KEY_Q, KEY_Q_PUNCT);

        mKeyW = new KeyText(context);
        initTextKey(mKeyW, KEY_W, KEY_W_PUNCT);

        mKeyE = new KeyText(context);
        initTextKey(mKeyE, KEY_E, KEY_E_PUNCT);

        mKeyR = new KeyText(context);
        initTextKey(mKeyR, KEY_R, KEY_R_PUNCT);

        mKeyT = new KeyText(context);
        initTextKey(mKeyT, KEY_T, KEY_T_PUNCT);

        mKeyY = new KeyText(context);
        initTextKey(mKeyY, KEY_Y, KEY_Y_PUNCT);

        mKeyU = new KeyText(context);
        initTextKey(mKeyU, KEY_U, KEY_U_PUNCT);

        mKeyI = new KeyText(context);
        initTextKey(mKeyI, KEY_I, KEY_I_PUNCT);

        mKeyO = new KeyText(context);
        initTextKey(mKeyO, KEY_O, KEY_O_PUNCT);

        mKeyP = new KeyText(context);
        initTextKey(mKeyP, KEY_P, KEY_P_PUNCT);

        // Row 2 (10 keys)

        mKeyA = new KeyText(context);
        initTextKey(mKeyA, KEY_A, KEY_A_PUNCT);

        mKeyS = new KeyText(context);
        initTextKey(mKeyS, KEY_S, KEY_S_PUNCT);

        mKeyD = new KeyText(context);
        initTextKey(mKeyD, KEY_D, KEY_D_PUNCT);

        mKeyF = new KeyText(context);
        initTextKey(mKeyF, KEY_F, KEY_F_PUNCT);

        mKeyG = new KeyText(context);
        initTextKey(mKeyG, KEY_G, KEY_G_PUNCT);

        mKeyH = new KeyText(context);
        initTextKey(mKeyH, KEY_H, KEY_H_PUNCT);

        mKeyJ = new KeyText(context);
        initTextKey(mKeyJ, KEY_J, KEY_J_PUNCT);

        mKeyK = new KeyText(context);
        initTextKey(mKeyK, KEY_K, KEY_K_PUNCT);

        mKeyL = new KeyText(context);
        initTextKey(mKeyL, KEY_L, KEY_L_PUNCT);

        mKeyNg = new KeyText(context);
        initTextKey(mKeyNg, KEY_NG, KEY_NG_PUNCT);

        // Row 3 (9 keys)

        mKeyZwj = new KeyText(context);
        initTextKey(mKeyZwj, KEY_ZWJ, KEY_ZWJ_PUNCT);
        mKeyZwj.setText(KEY_ZWJ_DISPLAY);

        mKeyZ = new KeyText(context);
        initTextKey(mKeyZ, KEY_Z, KEY_Z_PUNCT);

        mKeyX = new KeyText(context);
        initTextKey(mKeyX, KEY_X, KEY_X_PUNCT);

        mKeyC = new KeyText(context);
        initTextKey(mKeyC, KEY_C, KEY_C_PUNCT);

        mKeyV = new KeyText(context);
        initTextKey(mKeyV, KEY_V, KEY_V_PUNCT);

        mKeyB = new KeyText(context);
        initTextKey(mKeyB, KEY_B, KEY_B_PUNCT);

        mKeyN = new KeyText(context);
        initTextKey(mKeyN, KEY_N, KEY_N_PUNCT);

        mKeyM = new KeyText(context);
        initTextKey(mKeyM, KEY_M, KEY_M_PUNCT);

        // backspace
        mKeyBackspace = new KeyImage(context);
        mKeyBackspace.setImage(getBackspaceImage());
        mKeyBackspace.setOnTouchListener(handleBackspace);
        addView(mKeyBackspace);

        // Row 4 (7 keys)

        // keyboard
        mKeyKeyboard = new KeyImage(context);
        mKeyKeyboard.setImage(getKeyboardImage());
        mKeyKeyboard.setOnTouchListener(textKeyTouchListener);
        addView(mKeyKeyboard);

        // exclamation
        mKeyExclamation = new KeyText(context);
        initTextKey(mKeyExclamation, KEY_EXCLAMATION, KEY_EXCLAMATION);
        mKeyExclamation.setText(KEY_EXCLAMATION);

        // comma
        mKeyComma = new KeyText(context);
        initTextKey(mKeyComma, KEY_COMMA, KEY_COMMA);
        mKeyComma.setText(KEY_COMMA);

        // space
        mKeySpace = new KeyText(context);
        initTextKey(mKeySpace, " ", " ");
        mKeySpace.setText(KEY_SPACE);
        mKeySpace.setSubText(KEY_SPACE_SUB_DISPLAY);

        // period
        mKeyPeriod = new KeyText(context);
        initTextKey(mKeyPeriod, KEY_PERIOD, KEY_PERIOD);
        mKeyPeriod.setText(MongolCode.Uni.MONGOLIAN_FULL_STOP);

        // question mark
        mKeyQuestion = new KeyText(context);
        initTextKey(mKeyQuestion, KEY_QUESTION, KEY_QUESTION);
        mKeyQuestion.setText(MongolCode.Uni.VERTICAL_QUESTION_MARK);

        // return
        mKeyReturn = new KeyImage(context);
        mKeyReturn.setImage(getReturnImage());
        mKeyReturn.setOnTouchListener(textKeyTouchListener);
        mKeyValues.put(mKeyReturn, "\n");
        mKeyPunctuationValues.put(mKeyReturn, "\n");
        addView(mKeyReturn);


        setDisplayText(mIsShowingPunctuation);
        applyThemeToKeys();
    }


    @Override
    public void setDisplayText(boolean isShowingPunctuation) {
        if (isShowingPunctuation) {
            mKeyQ.setText(KEY_Q_PUNCT);
            mKeyW.setText(KEY_W_PUNCT);
            mKeyE.setText(KEY_E_PUNCT);
            mKeyR.setText(KEY_R_PUNCT);
            mKeyT.setText(KEY_T_PUNCT);
            mKeyY.setText(KEY_Y_PUNCT);
            mKeyU.setText(KEY_U_PUNCT);
            mKeyI.setText(KEY_I_PUNCT);
            mKeyO.setText(KEY_O_PUNCT);
            mKeyP.setText(KEY_P_PUNCT);
            mKeyA.setText(KEY_A_PUNCT);
            mKeyS.setText(KEY_S_PUNCT);
            mKeyD.setText(KEY_D_PUNCT);
            mKeyF.setText(KEY_F_PUNCT);
            mKeyG.setText(KEY_G_PUNCT);
            mKeyH.setText(KEY_H_PUNCT);
            mKeyJ.setText(KEY_J_PUNCT);
            mKeyK.setText(KEY_K_PUNCT);
            mKeyL.setText(KEY_L_PUNCT);
            mKeyNg.setText(KEY_NG_PUNCT);
            mKeyZwj.setText(KEY_ZWJ_PUNCT);
            mKeyZ.setText(KEY_Z_PUNCT);
            mKeyX.setText(KEY_X_PUNCT);
            mKeyC.setText(KEY_C_PUNCT);
            mKeyV.setText(KEY_V_PUNCT);
            mKeyB.setText(KEY_B_PUNCT);
            mKeyN.setText(KEY_N_PUNCT);
            mKeyM.setText(KEY_M_PUNCT);

            mKeyQ.setSubText(KEY_Q_PUNCT_SUB);
            mKeyW.setSubText(KEY_W_PUNCT_SUB);
            mKeyE.setSubText(KEY_E_PUNCT_SUB);
            mKeyR.setSubText(KEY_R_PUNCT_SUB);
            mKeyT.setSubText(KEY_T_PUNCT_SUB);
            mKeyY.setSubText(KEY_Y_PUNCT_SUB);
            mKeyU.setSubText(KEY_U_PUNCT_SUB);
            mKeyI.setSubText(KEY_I_PUNCT_SUB);
            mKeyO.setSubText(KEY_O_PUNCT_SUB);
            mKeyP.setSubText(KEY_P_PUNCT_SUB);
            mKeyA.setSubText(KEY_A_PUNCT_SUB);
            mKeyS.setSubText(KEY_S_PUNCT_SUB);
            mKeyD.setSubText(KEY_D_PUNCT_SUB);
            mKeyF.setSubText(KEY_F_PUNCT_SUB);
            mKeyG.setSubText(KEY_G_PUNCT_SUB);
            mKeyH.setSubText(KEY_H_PUNCT_SUB);
            mKeyJ.setSubText(KEY_J_PUNCT_SUB);
            mKeyK.setSubText(KEY_K_PUNCT_SUB);
            mKeyL.setSubText(KEY_L_PUNCT_SUB);
            mKeyNg.setSubText(KEY_NG_PUNCT_SUB);
            mKeyZwj.setSubText(KEY_ZWJ_PUNCT_SUB);
            mKeyZ.setSubText(KEY_Z_PUNCT_SUB);
            mKeyX.setSubText(KEY_X_PUNCT_SUB);
            mKeyC.setSubText(KEY_C_PUNCT_SUB);
            mKeyV.setSubText(KEY_V_PUNCT_SUB);
            mKeyB.setSubText(KEY_B_PUNCT_SUB);
            mKeyN.setSubText(KEY_N_PUNCT_SUB);
            mKeyM.setSubText(KEY_M_PUNCT_SUB);
        } else {
            mKeyQ.setText(KEY_Q);
            mKeyW.setText(KEY_W);
            mKeyE.setText(KEY_E);
            mKeyR.setText(KEY_R);
            mKeyT.setText(KEY_T);
            mKeyY.setText(KEY_Y);
            mKeyU.setText(KEY_U);
            mKeyI.setText(KEY_I);
            mKeyO.setText(KEY_O);
            mKeyP.setText(KEY_P);
            mKeyA.setText(KEY_A);
            mKeyS.setText(KEY_S);
            mKeyD.setText(KEY_D);
            mKeyF.setText(KEY_F);
            mKeyG.setText(KEY_G);
            mKeyH.setText(KEY_H);
            mKeyJ.setText(KEY_J);
            mKeyK.setText(KEY_K);
            mKeyL.setText(KEY_L);
            mKeyNg.setText(KEY_NG);
            mKeyZwj.setText(KEY_ZWJ_DISPLAY);
            mKeyZ.setText(KEY_Z);
            mKeyX.setText(KEY_X);
            mKeyC.setText(KEY_C);
            mKeyV.setText(KEY_V);
            mKeyB.setText(KEY_B);
            mKeyN.setText(KEY_N);
            mKeyM.setText(KEY_M);

            mKeyQ.setSubText(KEY_Q_SUB);
            mKeyW.setSubText(KEY_W_SUB);
            mKeyE.setSubText(KEY_E_SUB);
            mKeyR.setSubText(KEY_R_SUB);
            mKeyT.setSubText(KEY_T_SUB);
            mKeyY.setSubText(KEY_Y_SUB);
            mKeyU.setSubText(KEY_U_SUB);
            mKeyI.setSubText(KEY_I_SUB);
            mKeyO.setSubText(KEY_O_SUB);
            mKeyP.setSubText(KEY_P_SUB);
            mKeyA.setSubText(KEY_A_SUB);
            mKeyS.setSubText(KEY_S_SUB);
            mKeyD.setSubText(KEY_D_SUB);
            mKeyF.setSubText(KEY_F_SUB);
            mKeyG.setSubText(KEY_G_SUB);
            mKeyH.setSubText(KEY_H_SUB);
            mKeyJ.setSubText(KEY_J_SUB);
            mKeyK.setSubText(KEY_K_SUB);
            mKeyL.setSubText(KEY_L_SUB);
            mKeyNg.setSubText(KEY_NG_SUB);
            mKeyZwj.setSubText(KEY_ZWJ_SUB);
            mKeyZ.setSubText(KEY_Z_SUB);
            mKeyX.setSubText(KEY_X_SUB);
            mKeyC.setSubText(KEY_C_SUB);
            mKeyV.setSubText(KEY_V_SUB);
            mKeyB.setSubText(KEY_B_SUB);
            mKeyN.setSubText(KEY_N_SUB);
            mKeyM.setSubText(KEY_M_SUB);
        }
    }

    @Override
    public PopupCandidates getPopupCandidates(Key key) {
        // these are the choices to display in the popup (and corresponding unicode values)
        PopupCandidates candidates = null;

        // get the appropriate candidates based on the key pressed
        if (key == mKeyQ) {
            candidates = getCandidatesForQ();
        } else if (key == mKeyW) {
            candidates = getCandidatesForW();
        } else if (key == mKeyE) {
            candidates = getCandidatesForE(isIsolateOrInitial());
        } else if (key == mKeyR) {
            candidates = getCandidatesForR();
        } else if (key == mKeyT) {
            candidates = getCandidatesForT(isIsolateOrInitial());
        } else if (key == mKeyY) {
            candidates = getCandidatesForY(isIsolateOrInitial());
        } else if (key == mKeyU) {
            candidates = getCandidatesForU(isIsolateOrInitial());
        } else if (key == mKeyI) {
            candidates = getCandidatesForI(isIsolateOrInitial());
        } else if (key == mKeyO) {
            candidates = getCandidatesForO(isIsolateOrInitial());
        } else if (key == mKeyP) {
            candidates = getCandidatesForP();
        } else if (key == mKeyA) {
            candidates = getCandidatesForA(isIsolateOrInitial());
        } else if (key == mKeyS) {
            candidates = getCandidatesForS();
        } else if (key == mKeyD) {
            candidates = getCandidatesForD(isIsolateOrInitial());
        } else if (key == mKeyF) {
            candidates = getCandidatesForF();
        } else if (key == mKeyG) {
            candidates = getCandidatesForG(isIsolateOrInitial());
        } else if (key == mKeyH) {
            candidates = getCandidatesForH();
        } else if (key == mKeyJ) {
            candidates = getCandidatesForJ();
        } else if (key == mKeyK) {
            candidates = getCandidatesForK();
        } else if (key == mKeyL) {
            candidates = getCandidatesForL();
        } else if (key == mKeyNg) {
            candidates = getCandidatesForNG();
        } else if (key == mKeyZwj) {
            candidates = getCandidatesForZwj();
        } else if (key == mKeyZ) {
            candidates = getCandidatesForZ();
        } else if (key == mKeyX) {
            candidates = getCandidatesForX();
        } else if (key == mKeyC) {
            candidates = getCandidatesForC(isIsolateOrInitial());
        } else if (key == mKeyV) {
            candidates = getCandidatesForV(isIsolateOrInitial());
        } else if (key == mKeyB) {
            candidates = getCandidatesForB();
        } else if (key == mKeyN) {
            candidates = getCandidatesForN(isIsolateOrInitial());
        } else if (key == mKeyM) {
            candidates = getCandidatesForM();
        } else if (key == mKeyKeyboard) {
            candidates = getCandidatesForKeyboard();
        } else if (key == mKeySpace) {
            candidates = getCandidatesForSpace();
        }

        return candidates;
    }

    private PopupCandidates getCandidatesForQ() {
        if (mIsShowingPunctuation)
            return new PopupCandidates(KEY_Q_PUNCT_SUB);
        return new PopupCandidates(MongolCode.Uni.CHI);
    }

    private PopupCandidates getCandidatesForW() {
        if (mIsShowingPunctuation)
            return new PopupCandidates(KEY_W_PUNCT_SUB);
        return null;
    }

    private PopupCandidates getCandidatesForE(boolean isIsolateOrInitial) {
        if (mIsShowingPunctuation)
            return new PopupCandidates(KEY_E_PUNCT_SUB);

        if (isIsolateOrInitial)
            return new PopupCandidates(MongolCode.Uni.EE);

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

    private PopupCandidates getCandidatesForR() {
        if (mIsShowingPunctuation)
            return new PopupCandidates(KEY_R_PUNCT_SUB);
        return new PopupCandidates(MongolCode.Uni.ZRA);
    }

    private PopupCandidates getCandidatesForT(boolean isIsolateOrInitial) {
        if (mIsShowingPunctuation)
            return new PopupCandidates(KEY_T_PUNCT_SUB);

        if (!isIsolateOrInitial) { // medial/final
            return new PopupCandidates(new String[]{
                    "" + MongolCode.Uni.TA + MongolCode.Uni.FVS1},
                    new String[]{
                            "" + MongolCode.Uni.MONGOLIAN_NIRUGU +
                                    MongolCode.Uni.TA + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ});
        }
        return null;
    }

    private PopupCandidates getCandidatesForY(boolean isIsolateOrInitial) {
        if (mIsShowingPunctuation)
            return new PopupCandidates(KEY_Y_PUNCT_SUB);

        if (isIsolateOrInitial) {
            return new PopupCandidates(new String[]{
                    "" + MongolCode.Uni.YA + MongolCode.Uni.FVS1},
                    new String[]{
                            "" + MongolCode.Uni.YA + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ});
        } else { // medial/final
            return new PopupCandidates(new String[]{
                    "" + MongolCode.Uni.YA + MongolCode.Uni.FVS1},
                    new String[]{
                            "" + MongolCode.Uni.ZWJ + MongolCode.Uni.YA + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ});
        }
    }

    private PopupCandidates getCandidatesForU(boolean isIsolateOrInitial) {
        if (mIsShowingPunctuation)
            return new PopupCandidates(KEY_U_PUNCT_SUB);

        if (!isIsolateOrInitial) { // medial/final
            return new PopupCandidates(
                    new String[]{
                            "" + MongolCode.Uni.UE + MongolCode.Uni.FVS2,
                            "" + MongolCode.Uni.UE + MongolCode.Uni.FVS1},
                    new String[]{
                            "" + MongolCode.Uni.ZWJ + MongolCode.Uni.UE + MongolCode.Uni.FVS2 + MongolCode.Uni.ZWJ,
                            "" + MongolCode.Uni.ZWJ + MongolCode.Uni.UE + MongolCode.Uni.FVS1});
        }
        return null;
    }

    private PopupCandidates getCandidatesForI(boolean isIsolateOrInitial) {
        if (mIsShowingPunctuation)
            return new PopupCandidates(KEY_I_PUNCT_SUB);

        if (!isIsolateOrInitial) { // medial/final
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
                        new String[]{"" + MongolCode.Uni.I + MongolCode.Uni.FVS1},
                        new String[]{
                                "" + MongolCode.Uni.ZWJ + MongolCode.Uni.I + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ});
            }

        }
        return null;
    }

    private PopupCandidates getCandidatesForO(boolean isIsolateOrInitial) {
        if (mIsShowingPunctuation)
            return new PopupCandidates(KEY_O_PUNCT_SUB);

        if (!isIsolateOrInitial) { // medial/final
            return new PopupCandidates(
                    new String[]{
                            "" + MongolCode.Uni.OE + MongolCode.Uni.FVS2,
                            "" + MongolCode.Uni.OE + MongolCode.Uni.FVS1},
                    new String[]{
                            "" + MongolCode.Uni.ZWJ + MongolCode.Uni.OE + MongolCode.Uni.FVS2 + MongolCode.Uni.ZWJ,
                            "" + MongolCode.Uni.ZWJ + MongolCode.Uni.OE + MongolCode.Uni.FVS1});
        }
        return null;
    }

    private PopupCandidates getCandidatesForP() {
        if (mIsShowingPunctuation)
            return new PopupCandidates(KEY_P_PUNCT_SUB);
        return null;
    }

    private PopupCandidates getCandidatesForA(boolean isIsolateOrInitial) {
        if (mIsShowingPunctuation)
            return new PopupCandidates(KEY_A_PUNCT_SUB);

        if (isIsolateOrInitial)
            return new PopupCandidates(new String[]{
                    "" + MongolCode.Uni.A + MongolCode.Uni.FVS1,
                    "" + MongolCode.Uni.MONGOLIAN_NIRUGU});

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

    private PopupCandidates getCandidatesForS() {
        if (mIsShowingPunctuation)
            return new PopupCandidates(KEY_S_PUNCT_SUB);
        return null;
    }

    private PopupCandidates getCandidatesForD(boolean isIsolateOrInitial) {
        if (mIsShowingPunctuation)
            return new PopupCandidates(KEY_D_PUNCT_SUB);

        if (isIsolateOrInitial) {
            char prevChar = getPreviousChar();
            String[] unicode;
            if (prevChar == MongolCode.Uni.NNBS) {
                unicode = new String[]{"" + MongolCode.Uni.DA};
            } else {
                unicode = new String[]{"" + MongolCode.Uni.DA + MongolCode.Uni.FVS1};
            }
            return new PopupCandidates(unicode,
                    new String[]{"" + MongolCode.Uni.DA + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ});
            // TODO if this turns out to be an isolate then the FVS1 should be removed
        }

        // medial/final
        return new PopupCandidates(
                new String[]{"" + MongolCode.Uni.DA + MongolCode.Uni.FVS1},
                new String[]{"" + MongolCode.Uni.ZWJ + MongolCode.Uni.DA + MongolCode.Uni.FVS1});
    }

    private PopupCandidates getCandidatesForF() {
        if (mIsShowingPunctuation)
            return new PopupCandidates(KEY_F_PUNCT_SUB);
        return null;
    }

    private PopupCandidates getCandidatesForG(boolean isIsolateOrInitial) {
        if (mIsShowingPunctuation)
            return new PopupCandidates(new String[]{"+", "-", "×", "÷", "≠", "≈"});

        if (!isIsolateOrInitial) { // medial/final
            return new PopupCandidates(
                    new String[]{
                            "" + MongolCode.Uni.GA + MongolCode.Uni.FVS1, // see note on MongolCode(FINA_GA_FVS1)
                            "" + MongolCode.Uni.GA + MongolCode.Uni.FVS2}, // see note on MongolCode(FINA_GA_FVS2)
                    new String[]{
                            "" + MongolCode.Uni.ZWJ + MongolCode.Uni.GA + MongolCode.Uni.FVS1,
                            "" + MongolCode.Uni.ZWJ + MongolCode.Uni.GA + MongolCode.Uni.FVS2});
        }
        return null;
    }

    private PopupCandidates getCandidatesForH() {
        if (mIsShowingPunctuation)
            return new PopupCandidates(new String[]{"$", "₮"});
        return new PopupCandidates(MongolCode.Uni.HAA);
    }

    private PopupCandidates getCandidatesForJ() {
        if (mIsShowingPunctuation)
            return new PopupCandidates(KEY_J_PUNCT_SUB);
        return new PopupCandidates(MongolCode.Uni.ZHI);
    }

    private PopupCandidates getCandidatesForK() {
        if (mIsShowingPunctuation)
            return new PopupCandidates(KEY_K_PUNCT_SUB);
        return null;
    }

    private PopupCandidates getCandidatesForL() {
        if (mIsShowingPunctuation)
            return new PopupCandidates(KEY_L_PUNCT_SUB);
        return new PopupCandidates(MongolCode.Uni.LHA);
    }

    private PopupCandidates getCandidatesForNG() {
        if (mIsShowingPunctuation)
            return new PopupCandidates(KEY_NG_PUNCT_SUB);
        return null;
    }

    private PopupCandidates getCandidatesForZwj() {
        if (mIsShowingPunctuation)
            return new PopupCandidates(KEY_ZWJ_PUNCT_SUB);
        return null;
    }

    private PopupCandidates getCandidatesForZ() {
        if (mIsShowingPunctuation)
            return new PopupCandidates(new String[]{KEY_Z_PUNCT_SUB, "*"});
        return new PopupCandidates(MongolCode.Uni.TSA);
    }

    private PopupCandidates getCandidatesForX() {
        if (mIsShowingPunctuation)
            return new PopupCandidates(KEY_X_PUNCT_SUB);
        return null;
    }

    private PopupCandidates getCandidatesForC(boolean isIsolateOrInitial) {
        if (mIsShowingPunctuation)
            return new PopupCandidates(KEY_C_PUNCT_SUB);

        if (!isIsolateOrInitial) { // medial/final
            return new PopupCandidates(
                    new String[]{
                            "" + MongolCode.Uni.O + MongolCode.Uni.FVS1,
                            "" + MongolCode.Uni.O + MongolCode.Uni.FVS1},
                    new String[]{
                            "" + MongolCode.Uni.ZWJ + MongolCode.Uni.O + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ,
                            "" + MongolCode.Uni.ZWJ + MongolCode.Uni.O + MongolCode.Uni.FVS1});
        }
        return null;
    }

    private PopupCandidates getCandidatesForV(boolean isIsolateOrInitial) {
        if (mIsShowingPunctuation)
            return new PopupCandidates(KEY_V_PUNCT_SUB);

        if (!isIsolateOrInitial) { // medial/final
            return new PopupCandidates(
                    new String[]{
                            "" + MongolCode.Uni.U + MongolCode.Uni.FVS1,
                            "" + MongolCode.Uni.U + MongolCode.Uni.FVS1},
                    new String[]{
                            "" + MongolCode.Uni.ZWJ + MongolCode.Uni.U + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ,
                            "" + MongolCode.Uni.ZWJ + MongolCode.Uni.U + MongolCode.Uni.FVS1});
        }
        return null;
    }

    private PopupCandidates getCandidatesForB() {
        if (mIsShowingPunctuation)
            return new PopupCandidates(KEY_B_PUNCT_SUB);
        return null;
    }

    private PopupCandidates getCandidatesForN(boolean isIsolateOrInitial) {
        if (mIsShowingPunctuation)
            return new PopupCandidates(KEY_N_PUNCT_SUB);

        if (!isIsolateOrInitial) { // medial/final
            return new PopupCandidates(
                    new String[]{
                            "" + MongolCode.Uni.NA + MongolCode.Uni.ZWJ, // only(?) way to override dotted N before vowel in Unicode 10.0
                            "" + MongolCode.Uni.NA + MongolCode.Uni.FVS1},
                    new String[]{
                            "" + MongolCode.Uni.ZWJ + MongolCode.Uni.NA + MongolCode.Uni.ZWJ,
                            "" + MongolCode.Uni.ZWJ + MongolCode.Uni.NA + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ});
        }
        return null;
    }

    private PopupCandidates getCandidatesForM() {
        if (mIsShowingPunctuation)
            return new PopupCandidates(new String[]{
                    "" + MongolCode.Uni.DOUBLE_EXCLAMATION_MARK,
                    "" + MongolCode.Uni.DOUBLE_QUESTION_MARK,
                    "" + MongolCode.Uni.EXCLAMATION_QUESTION_MARK});
        return null;
    }

    @Override
    public String getDisplayName() {
        return DISPLAY_NAME;
    }

    private PopupCandidates getCandidatesForSpace() {
        return getCandidatesForSuffix();
    }
}
