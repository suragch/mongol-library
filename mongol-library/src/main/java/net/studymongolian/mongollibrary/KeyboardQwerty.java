package net.studymongolian.mongollibrary;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


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
    protected KeyBackspace mKeyBackspace;

    // Row 4
    protected KeyKeyboardChooser mKeyKeyboard;
    protected KeyText mKeyExclamation;
    protected KeyText mKeyComma;
    protected KeyText mKeySpace;
    protected KeyText mKeyPeriod;
    protected KeyText mKeyQuestion;
    protected KeyImage mKeyReturn;

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

    private static final String NEWLINE = "\n";
    private static final String KEY_SPACE_SUB_DISPLAY = "ᠶ᠋ᠢ ᠳᠤ ᠤᠨ";

    public KeyboardQwerty(Context context) {
        super(context);
        init(context);
    }

    public KeyboardQwerty(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public KeyboardQwerty(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    // all keyboards should include this custom constructor
    // (there was no way to force it in the abstract Keyboard class)
//    public KeyboardQwerty(Context context, StyleBuilder style) {
//        super(context);
//        super.initStyle(style);
//        init(context);
//    }

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
                0.15f, 0.1f, 0.1f, 0.3f, 0.1f, 0.1f, 0.15f};                    // row 3

        // Make sure that the total keys added to this ViewGroup below equals
        // the mNumberOfKeysInRow and mKeyWeights array totals above.

        instantiateKeys(context);
        setKeyValues();
        setNonChangingKeyValues();
        setKeyImages();
        setListeners();
        addKeysToKeyboard();
        applyThemeToKeys();
        //setPreferredCandidateLocation(CandidateLocation.HORIZONTAL_TOP);
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
        mKeyNg = new KeyText(context);

        // Row 3
        mKeyZwj = new KeyText(context);
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

    private void setKeyValues() {

        rotatePrimaryText();

        // Row 1

        mKeyQ.setText(MongolCode.Uni.CHA);
        mKeyQ.setSubText(MongolCode.Uni.CHI);

        mKeyW.setText(MongolCode.Uni.WA);
        mKeyW.setSubText("");

        mKeyE.setText(MongolCode.Uni.E);
        mKeyE.setSubText(MongolCode.Uni.EE);

        mKeyR.setText(MongolCode.Uni.RA);
        mKeyR.setSubText(MongolCode.Uni.ZRA);

        mKeyT.setText(MongolCode.Uni.TA);
        mKeyT.setSubText("");

        mKeyY.setText(MongolCode.Uni.YA);
        mKeyY.setSubText("");

        mKeyU.setText(MongolCode.Uni.UE);
        mKeyU.setSubText("");

        mKeyI.setText(MongolCode.Uni.I);
        mKeyI.setSubText("");

        mKeyO.setText(MongolCode.Uni.OE);
        mKeyO.setSubText("");

        mKeyP.setText(MongolCode.Uni.PA);
        mKeyP.setSubText("");

        // Row 2
        mKeyA.setText(MongolCode.Uni.A);
        mKeyA.setSubText("");

        mKeyS.setText(MongolCode.Uni.SA);
        mKeyS.setSubText("");

        mKeyD.setText(MongolCode.Uni.DA);
        mKeyD.setSubText("");

        mKeyF.setText(MongolCode.Uni.FA);
        mKeyF.setSubText("");

        mKeyG.setText(MongolCode.Uni.GA);
        mKeyG.setSubText("");

        mKeyH.setText(MongolCode.Uni.QA);
        mKeyH.setSubText(MongolCode.Uni.HAA);

        mKeyJ.setText(MongolCode.Uni.JA);
        mKeyJ.setSubText(MongolCode.Uni.ZHI);

        mKeyK.setText(MongolCode.Uni.KA);
        mKeyK.setSubText("");

        mKeyL.setText(MongolCode.Uni.LA);
        mKeyL.setSubText(MongolCode.Uni.LHA);

        mKeyNg.setText(MongolCode.Uni.ANG);
        mKeyNg.setSubText("");

        // Row 3
        mKeyZwj.setText(MongolCode.Uni.ZWJ, '/'); // TODO should we add ZWNJ?
        mKeyZwj.setSubText("");

        mKeyZ.setText(MongolCode.Uni.ZA);
        mKeyZ.setSubText(MongolCode.Uni.TSA);

        mKeyX.setText(MongolCode.Uni.SHA);
        mKeyX.setSubText("");

        mKeyC.setText(MongolCode.Uni.O);
        mKeyC.setSubText("");

        mKeyV.setText(MongolCode.Uni.U);
        mKeyV.setSubText("");

        mKeyB.setText(MongolCode.Uni.BA);
        mKeyB.setSubText("");

        mKeyN.setText(MongolCode.Uni.NA);
        mKeyN.setSubText("");

        mKeyM.setText(MongolCode.Uni.MA);
        mKeyM.setSubText("");
    }

    private void rotatePrimaryText() {
        mKeyQ.setRotatedPrimaryText(true);
        mKeyW.setRotatedPrimaryText(true);
        mKeyE.setRotatedPrimaryText(true);
        mKeyR.setRotatedPrimaryText(true);
        mKeyT.setRotatedPrimaryText(true);
        mKeyY.setRotatedPrimaryText(true);
        mKeyU.setRotatedPrimaryText(true);
        mKeyI.setRotatedPrimaryText(true);
        mKeyO.setRotatedPrimaryText(true);
        mKeyP.setRotatedPrimaryText(true);
        mKeyH.setRotatedPrimaryText(true);
    }

    private void setPuncuationKeyValues() {

        dontRotatePrimaryTextForSelectKeys();

        // Row 1

        mKeyQ.setText("1");
        mKeyQ.setSubText(KEY_Q_PUNCT_SUB);

        mKeyW.setText("2");
        mKeyW.setSubText(KEY_W_PUNCT_SUB);

        mKeyE.setText("3");
        mKeyE.setSubText(KEY_E_PUNCT_SUB);

        mKeyR.setText("4");
        mKeyR.setSubText(KEY_R_PUNCT_SUB);

        mKeyT.setText("5");
        mKeyT.setSubText(KEY_T_PUNCT_SUB);

        mKeyY.setText("6");
        mKeyY.setSubText(KEY_Y_PUNCT_SUB);

        mKeyU.setText("7");
        mKeyU.setSubText(KEY_U_PUNCT_SUB);

        mKeyI.setText("8");
        mKeyI.setSubText(KEY_I_PUNCT_SUB);

        mKeyO.setText("9");
        mKeyO.setSubText(KEY_O_PUNCT_SUB);

        mKeyP.setText("0");
        mKeyP.setSubText(KEY_P_PUNCT_SUB);

        // Row 2

        mKeyA.setText(MongolCode.Uni.VERTICAL_LEFT_PARENTHESIS);
        mKeyA.setSubText(KEY_A_PUNCT_SUB);

        mKeyS.setText(MongolCode.Uni.VERTICAL_RIGHT_PARENTHESIS);
        mKeyS.setSubText(KEY_S_PUNCT_SUB);

        mKeyD.setText(MongolCode.Uni.VERTICAL_LEFT_DOUBLE_ANGLE_BRACKET);
        mKeyD.setSubText(KEY_D_PUNCT_SUB);

        mKeyF.setText(MongolCode.Uni.VERTICAL_RIGHT_DOUBLE_ANGLE_BRACKET);
        mKeyF.setSubText(KEY_F_PUNCT_SUB);

        mKeyG.setText("=");
        mKeyG.setSubText(KEY_G_PUNCT_SUB);

        mKeyH.setText("¥");
        mKeyH.setSubText(KEY_H_PUNCT_SUB);

        mKeyJ.setText("'");
        mKeyJ.setSubText(KEY_J_PUNCT_SUB);

        mKeyK.setText("\"");
        mKeyK.setSubText(KEY_K_PUNCT_SUB);

        mKeyL.setText("#");
        mKeyL.setSubText(KEY_L_PUNCT_SUB);

        mKeyNg.setText("|");
        mKeyNg.setSubText(KEY_NG_PUNCT_SUB);

        // Row 3

        mKeyZwj.setText("/");
        mKeyZwj.setSubText(KEY_ZWJ_PUNCT_SUB);

        mKeyZ.setText(MongolCode.Uni.REFERENCE_MARK);
        mKeyZ.setSubText(KEY_Z_PUNCT_SUB);

        mKeyX.setText(MongolCode.Uni.MONGOLIAN_BIRGA);
        mKeyX.setSubText(KEY_X_PUNCT_SUB);

        mKeyC.setText(MongolCode.Uni.MIDDLE_DOT);
        mKeyC.setSubText(KEY_C_PUNCT_SUB);

        mKeyV.setText(MongolCode.Uni.MONGOLIAN_ELLIPSIS);
        mKeyV.setSubText(KEY_V_PUNCT_SUB);

        mKeyB.setText(MongolCode.Uni.VERTICAL_EM_DASH);
        mKeyB.setSubText(KEY_B_PUNCT_SUB);

        mKeyN.setText(MongolCode.Uni.MONGOLIAN_COLON);
        mKeyN.setSubText(KEY_N_PUNCT_SUB);

        mKeyM.setText(MongolCode.Uni.QUESTION_EXCLAMATION_MARK);
        mKeyM.setSubText(KEY_M_PUNCT_SUB);
    }

    private void dontRotatePrimaryTextForSelectKeys() {
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
        mKeyH.setRotatedPrimaryText(false);
    }

    private void setNonChangingKeyValues() {
        mKeyExclamation.setText(MongolCode.Uni.VERTICAL_EXCLAMATION_MARK);
        mKeyComma.setText(MongolCode.Uni.MONGOLIAN_COMMA);
        mKeySpace.setText(" ");
        if (hasCandidatesView()) {
            mKeySpace.setSubText(KEY_SPACE_SUB_DISPLAY);
        }
        mKeyPeriod.setText(MongolCode.Uni.MONGOLIAN_FULL_STOP);
        mKeyQuestion.setText(MongolCode.Uni.VERTICAL_QUESTION_MARK);
        mKeyReturn.setText(NEWLINE);
    }

    private void setKeyImages() {
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
        mKeyNg.setKeyListener(this);

        // Row 3
        mKeyZwj.setKeyListener(this);
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
        addView(mKeyNg);

        // Row 3
        addView(mKeyZwj);
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

    public List<PopupKeyCandidate> getPopupCandidates(Key key) {
        // get the appropriate candidates based on the key pressed
        if (key == mKeyQ) {
            return getCandidatesForQ();
        } else if (key == mKeyW) {
            return getCandidatesForW();
        } else if (key == mKeyE) {
            return getCandidatesForE();
        } else if (key == mKeyR) {
            return getCandidatesForR();
        } else if (key == mKeyT) {
            return getCandidatesForT();
        } else if (key == mKeyY) {
            return getCandidatesForY();
        } else if (key == mKeyU) {
            return getCandidatesForU();
        } else if (key == mKeyI) {
            return getCandidatesForI();
        } else if (key == mKeyO) {
            return getCandidatesForO();
        } else if (key == mKeyP) {
            return getCandidatesForP();
        } else if (key == mKeyA) {
            return getCandidatesForA();
        } else if (key == mKeyS) {
            return getCandidatesForS();
        } else if (key == mKeyD) {
            return getCandidatesForD();
        } else if (key == mKeyF) {
            return getCandidatesForF();
        } else if (key == mKeyG) {
            return getCandidatesForG();
        } else if (key == mKeyH) {
            return getCandidatesForH();
        } else if (key == mKeyJ) {
            return getCandidatesForJ();
        } else if (key == mKeyK) {
            return getCandidatesForK();
        } else if (key == mKeyL) {
            return getCandidatesForL();
        } else if (key == mKeyNg) {
            return getCandidatesForNG();
        } else if (key == mKeyZwj) {
            return getCandidatesForZwj();
        } else if (key == mKeyZ) {
            return getCandidatesForZ();
        } else if (key == mKeyX) {
            return getCandidatesForX();
        } else if (key == mKeyC) {
            return getCandidatesForC();
        } else if (key == mKeyV) {
            return getCandidatesForV();
        } else if (key == mKeyB) {
            return getCandidatesForB();
        } else if (key == mKeyN) {
            return getCandidatesForN();
        } else if (key == mKeyM) {
            return getCandidatesForM();
        } else if (key == mKeyKeyboard) {
            return getCandidatesForKeyboardKey();
        } else if (key == mKeySpace) {
            return getCandidatesForSpace();
        }

        return null;
    }

    private List<PopupKeyCandidate> getCandidatesForQ() {
        List<PopupKeyCandidate> candidates = new ArrayList<>();
        if (mIsShowingPunctuation) {
            candidates.add(new PopupKeyCandidate(KEY_Q_PUNCT_SUB));
            return candidates;
        }
        candidates.add(new PopupKeyCandidate(MongolCode.Uni.CHI));
        return candidates;
    }

    private List<PopupKeyCandidate> getCandidatesForW() {
        List<PopupKeyCandidate> candidates = new ArrayList<>();
        if (mIsShowingPunctuation) {
            candidates.add(new PopupKeyCandidate(KEY_W_PUNCT_SUB));
            return candidates;
        }
        return candidates;
    }

    private List<PopupKeyCandidate> getCandidatesForE() {
        List<PopupKeyCandidate> candidates = new ArrayList<>();
        if (mIsShowingPunctuation) {
            candidates.add(new PopupKeyCandidate(KEY_E_PUNCT_SUB));
            return candidates;
        }

        candidates.add(new PopupKeyCandidate(MongolCode.Uni.EE));

        if (!isIsolateOrInitial()) {
            // MVS
            char previousChar = getPreviousChar();
            if (MongolCode.isMvsConsonant(previousChar)
                    && previousChar != MongolCode.Uni.QA && previousChar != MongolCode.Uni.GA) {
                PopupKeyCandidate mvs_E = new PopupKeyCandidate(
                        "" + MongolCode.Uni.MVS + MongolCode.Uni.E,
                        "" + MongolCode.Uni.ZWJ + previousChar + MongolCode.Uni.MVS + MongolCode.Uni.E);
                candidates.add(mvs_E);
            }
        }

        if (!hasCandidatesView()) {
            candidates.addAll(getSuffixForKeyE());
        }

        return candidates;
    }

    private List<PopupKeyCandidate> getSuffixForKeyE() {
        List<PopupKeyCandidate> suffixes = new ArrayList<>();
        if (mKeyboardListener == null) return suffixes;
        String previousWord = mKeyboardListener.getPreviousMongolWord(true);
        MongolCode.Gender gender = MongolCode.getWordGender(previousWord);
        String achaEche = MongolCode.getSuffixAchaEche(gender);
        suffixes.add(new PopupKeyCandidate(achaEche));
        return suffixes;
    }

    private List<PopupKeyCandidate> getCandidatesForR() {
        List<PopupKeyCandidate> candidates = new ArrayList<>();
        if (mIsShowingPunctuation) {
            candidates.add(new PopupKeyCandidate(KEY_R_PUNCT_SUB));
            return candidates;
        }
        candidates.add(new PopupKeyCandidate(MongolCode.Uni.ZRA));
        return candidates;
    }

    private List<PopupKeyCandidate> getCandidatesForT() {
        List<PopupKeyCandidate> candidates = new ArrayList<>();
        if (mIsShowingPunctuation) {
            candidates.add(new PopupKeyCandidate(KEY_T_PUNCT_SUB));
            return candidates;
        }

        if (!isIsolateOrInitial()) { // medial/final
            PopupKeyCandidate medial_ta_fvs1 = new PopupKeyCandidate(
                    "" + MongolCode.Uni.TA + MongolCode.Uni.FVS1,
                    "" + MongolCode.Uni.MONGOLIAN_NIRUGU +
                            MongolCode.Uni.TA + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ,
                    "" + MongolCode.Uni.TA + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ);
            candidates.add(medial_ta_fvs1);
        }

        if (!hasCandidatesView()) {
            candidates.addAll(getSuffixForKeyT());
        }

        return candidates;
    }

    private List<PopupKeyCandidate> getSuffixForKeyT() {
        List<PopupKeyCandidate> suffixes = new ArrayList<>();
        if (mKeyboardListener == null) return suffixes;

        String previousWord = mKeyboardListener.getPreviousMongolWord(true);
        MongolCode.Gender gender = MongolCode.getWordGender(previousWord);

        if (gender == MongolCode.Gender.MASCULINE) {
            suffixes.add(new PopupKeyCandidate(MongolCode.Suffix.TU));
            suffixes.add(new PopupKeyCandidate(MongolCode.Suffix.TAI));
            suffixes.add(new PopupKeyCandidate(MongolCode.Suffix.TAGAN));
        } else if (gender == MongolCode.Gender.FEMININE) {
            suffixes.add(new PopupKeyCandidate(MongolCode.Suffix.TUE));
            suffixes.add(new PopupKeyCandidate(MongolCode.Suffix.TEI));
            suffixes.add(new PopupKeyCandidate(MongolCode.Suffix.TEGEN));
        } else {
            suffixes.add(new PopupKeyCandidate(MongolCode.Suffix.TU));
            suffixes.add(new PopupKeyCandidate(MongolCode.Suffix.TAI));
            suffixes.add(new PopupKeyCandidate(MongolCode.Suffix.TAGAN));
            suffixes.add(new PopupKeyCandidate(MongolCode.Suffix.TEGEN));
        }
        return suffixes;
    }

    private List<PopupKeyCandidate> getCandidatesForY() {
        List<PopupKeyCandidate> candidates = new ArrayList<>();
        if (mIsShowingPunctuation) {
            candidates.add(new PopupKeyCandidate(KEY_Y_PUNCT_SUB));
            return candidates;
        }

        if (isIsolateOrInitial()) {
            PopupKeyCandidate initial_YA_FVS1 = new PopupKeyCandidate(
                    "" + MongolCode.Uni.YA + MongolCode.Uni.FVS1,
                    "" + MongolCode.Uni.YA + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ,
                    "" + MongolCode.Uni.YA + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ);
            candidates.add(initial_YA_FVS1);
            return candidates;
        }

        PopupKeyCandidate medial_YA_FVS1 = new PopupKeyCandidate(
                "" + MongolCode.Uni.YA + MongolCode.Uni.FVS1,
                "" + MongolCode.Uni.ZWJ + MongolCode.Uni.YA + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ,
                "" + MongolCode.Uni.YA + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ);
        candidates.add(medial_YA_FVS1);

        return candidates;
    }

    private List<PopupKeyCandidate> getCandidatesForU() {
        List<PopupKeyCandidate> candidates = new ArrayList<>();
        if (mIsShowingPunctuation) {
            candidates.add(new PopupKeyCandidate(KEY_U_PUNCT_SUB));
            return candidates;
        }

        if (isIsolateOrInitial())
            return candidates;

        PopupKeyCandidate medial_UE_FVS2 = new PopupKeyCandidate(
                "" + MongolCode.Uni.UE + MongolCode.Uni.FVS2,
                "" + MongolCode.Uni.ZWJ + MongolCode.Uni.UE + MongolCode.Uni.FVS2 + MongolCode.Uni.ZWJ,
                "" + MongolCode.Uni.UE + MongolCode.Uni.FVS2 + MongolCode.Uni.ZWJ);
        candidates.add(medial_UE_FVS2);

        PopupKeyCandidate final_UE_FVS1 = new PopupKeyCandidate(
                "" + MongolCode.Uni.UE + MongolCode.Uni.FVS1,
                "" + MongolCode.Uni.ZWJ + MongolCode.Uni.UE + MongolCode.Uni.FVS1,
                null);
        candidates.add(final_UE_FVS1);

        return candidates;
    }

    private List<PopupKeyCandidate> getCandidatesForI() {
        List<PopupKeyCandidate> candidates = new ArrayList<>();
        if (mIsShowingPunctuation) {
            candidates.add(new PopupKeyCandidate(KEY_I_PUNCT_SUB));
            return candidates;
        }

        if (!isIsolateOrInitial()) {
            PopupKeyCandidate medial_I_FVS1 = new PopupKeyCandidate(
                    "" + MongolCode.Uni.I + MongolCode.Uni.FVS1,
                    "" + MongolCode.Uni.ZWJ + MongolCode.Uni.I + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ,
                    "" + MongolCode.Uni.I + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ);
            candidates.add(medial_I_FVS1);

            char prevChar = getPreviousChar();
            if (MongolCode.isVowel(prevChar)) {
                // override double tooth I after vowel (Unicode 10.0 deviation)
                // ("" + MongolCode.Uni.ZWJ + MongolCode.Uni.I) is an alternate method to override double tooth I
                PopupKeyCandidate medial_I_FVS2 = new PopupKeyCandidate(
                        "" + MongolCode.Uni.I + MongolCode.Uni.FVS2,
                        "" + MongolCode.Uni.ZWJ + MongolCode.Uni.I + MongolCode.Uni.ZWJ,
                        "" + MongolCode.Uni.I + MongolCode.Uni.FVS2 + MongolCode.Uni.ZWJ);
                candidates.add(medial_I_FVS2);
            }
        }

        if (!hasCandidatesView()) {
            candidates.addAll(getSuffixForKeyI());
        }

        return candidates;
    }

    private List<PopupKeyCandidate> getSuffixForKeyI() {
        List<PopupKeyCandidate> suffixes = new ArrayList<>();
        if (mKeyboardListener == null) return suffixes;

        String previousWord = mKeyboardListener.getPreviousMongolWord(true);

        if (TextUtils.isEmpty(previousWord)) {
            suffixes.add(new PopupKeyCandidate(MongolCode.Suffix.I));
            suffixes.add(new PopupKeyCandidate(MongolCode.Suffix.YI));
            suffixes.add(new PopupKeyCandidate(MongolCode.Suffix.YIN));
            suffixes.add(new PopupKeyCandidate(MongolCode.Suffix.IYAR));
            suffixes.add(new PopupKeyCandidate(MongolCode.Suffix.IYAN));
            return suffixes;
        }

        // YI I
        char lastChar = previousWord.charAt(previousWord.length() - 1);
        String iYi = MongolCode.getSuffixYiI(lastChar);
        suffixes.add(new PopupKeyCandidate(iYi));

        // YIN
        suffixes.add(new PopupKeyCandidate(MongolCode.Suffix.YIN));

        // IYAR and IYAN
        MongolCode.Gender gender = MongolCode.getWordGender(previousWord);
        if (gender == MongolCode.Gender.FEMININE) {
            suffixes.add(new PopupKeyCandidate(MongolCode.Suffix.IYER));
            suffixes.add(new PopupKeyCandidate(MongolCode.Suffix.IYEN));
        } else {
            suffixes.add(new PopupKeyCandidate(MongolCode.Suffix.IYAR));
            suffixes.add(new PopupKeyCandidate(MongolCode.Suffix.IYAN));
        }

        return suffixes;
    }

    private List<PopupKeyCandidate> getCandidatesForO() {
        List<PopupKeyCandidate> candidates = new ArrayList<>();
        if (mIsShowingPunctuation) {
            candidates.add(new PopupKeyCandidate(KEY_O_PUNCT_SUB));
            return candidates;
        }

        if (isIsolateOrInitial())
            return null;

        PopupKeyCandidate medial_OE_FVS2 = new PopupKeyCandidate(
                "" + MongolCode.Uni.OE + MongolCode.Uni.FVS2,
                "" + MongolCode.Uni.ZWJ + MongolCode.Uni.OE + MongolCode.Uni.FVS2 + MongolCode.Uni.ZWJ,
                "" + MongolCode.Uni.UE + MongolCode.Uni.FVS2 + MongolCode.Uni.ZWJ);
        candidates.add(medial_OE_FVS2);

        PopupKeyCandidate final_OE_FVS1 = new PopupKeyCandidate(
                "" + MongolCode.Uni.OE + MongolCode.Uni.FVS1,
                "" + MongolCode.Uni.ZWJ + MongolCode.Uni.OE + MongolCode.Uni.FVS1);
        candidates.add(final_OE_FVS1);

        return candidates;
    }

    private List<PopupKeyCandidate> getCandidatesForP() {
        List<PopupKeyCandidate> candidates = new ArrayList<>();
        if (mIsShowingPunctuation) {
            candidates.add(new PopupKeyCandidate(KEY_P_PUNCT_SUB));
            return candidates;
        }
        return candidates;
    }

    private List<PopupKeyCandidate> getCandidatesForA() {
        List<PopupKeyCandidate> candidates = new ArrayList<>();
        if (mIsShowingPunctuation) {
            candidates.add(new PopupKeyCandidate(KEY_A_PUNCT_SUB));
            return candidates;
        }

        PopupKeyCandidate nirugu = new PopupKeyCandidate(MongolCode.Uni.MONGOLIAN_NIRUGU);

        if (isIsolateOrInitial()){
            candidates.add(new PopupKeyCandidate("" + MongolCode.Uni.A + MongolCode.Uni.FVS1));
            candidates.add(nirugu);
            return candidates;
        }

        // MVS-A
        char previousChar = getPreviousChar();
        if (MongolCode.isMvsConsonant(previousChar)) {
            PopupKeyCandidate mvs_a = new PopupKeyCandidate(
                    "" + MongolCode.Uni.MVS + MongolCode.Uni.A,
                    "" + MongolCode.Uni.ZWJ + previousChar + MongolCode.Uni.MVS + MongolCode.Uni.A,
                    null);
            candidates.add(mvs_a);
        }

        PopupKeyCandidate medial_A_FVS1 = new PopupKeyCandidate(
                "" + MongolCode.Uni.A + MongolCode.Uni.FVS1,
                "" + MongolCode.Uni.ZWJ + MongolCode.Uni.A + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ,
                "" + MongolCode.Uni.A + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ);
        candidates.add(medial_A_FVS1);

        candidates.add(nirugu);

        return candidates;
    }

    private List<PopupKeyCandidate> getCandidatesForS() {
        List<PopupKeyCandidate> candidates = new ArrayList<>();
        if (mIsShowingPunctuation) {
            candidates.add(new PopupKeyCandidate(KEY_S_PUNCT_SUB));
            return candidates;
        }
        return candidates;
    }

    private List<PopupKeyCandidate> getCandidatesForD() {
        List<PopupKeyCandidate> candidates = new ArrayList<>();
        if (mIsShowingPunctuation) {
            candidates.add(new PopupKeyCandidate(KEY_D_PUNCT_SUB));
            return candidates;
        }

        if (isIsolateOrInitial()) {
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
            candidates.add(initial_da);
            // TODO if this turns out to be an isolate then the FVS1 should be removed
        } else {
            PopupKeyCandidate final_da_fvs1 = new PopupKeyCandidate(
                    "" + MongolCode.Uni.DA + MongolCode.Uni.FVS1,
                    "" + MongolCode.Uni.ZWJ + MongolCode.Uni.DA + MongolCode.Uni.FVS1);
            candidates.add(final_da_fvs1);
        }

        if (!hasCandidatesView()) {
            candidates.addAll(getSuffixForKeyD());
        }

        return candidates;
    }

    private List<PopupKeyCandidate> getSuffixForKeyD() {
        List<PopupKeyCandidate> suffixes = new ArrayList<>();
        if (mKeyboardListener == null) return suffixes;

        String previousWord = mKeyboardListener.getPreviousMongolWord(true);
        MongolCode.Gender gender = MongolCode.getWordGender(previousWord);

        if (gender == MongolCode.Gender.MASCULINE) {
            suffixes.add(new PopupKeyCandidate(MongolCode.Suffix.DU));
            suffixes.add(new PopupKeyCandidate(MongolCode.Suffix.DAGAN));
        } else if (gender == MongolCode.Gender.FEMININE) {
            suffixes.add(new PopupKeyCandidate(MongolCode.Suffix.DUE));
            suffixes.add(new PopupKeyCandidate(MongolCode.Suffix.DEGEN));
        } else {
            suffixes.add(new PopupKeyCandidate(MongolCode.Suffix.DU));
            suffixes.add(new PopupKeyCandidate(MongolCode.Suffix.DAGAN));
            suffixes.add(new PopupKeyCandidate(MongolCode.Suffix.DEGEN));
        }
        return suffixes;
    }

    private List<PopupKeyCandidate> getCandidatesForF() {
        List<PopupKeyCandidate> candidates = new ArrayList<>();
        if (mIsShowingPunctuation) {
            candidates.add(new PopupKeyCandidate(KEY_F_PUNCT_SUB));
            return candidates;
        }
        return candidates;
    }

    private List<PopupKeyCandidate> getCandidatesForG() {
        List<PopupKeyCandidate> candidates = new ArrayList<>();
        if (mIsShowingPunctuation) {
            candidates.add(new PopupKeyCandidate("+"));
            candidates.add(new PopupKeyCandidate("-"));
            candidates.add(new PopupKeyCandidate("×"));
            candidates.add(new PopupKeyCandidate("÷"));
            candidates.add(new PopupKeyCandidate("≠"));
            candidates.add(new PopupKeyCandidate("≈"));
            return candidates;
        }

        if (isIsolateOrInitial())
            return candidates;

        // see note on MongolCode(FINA_GA_FVS1)
        PopupKeyCandidate ga_fvs1 = new PopupKeyCandidate(
                "" + MongolCode.Uni.GA + MongolCode.Uni.FVS1,
                "" + MongolCode.Uni.ZWJ + MongolCode.Uni.GA + MongolCode.Uni.FVS1);
        candidates.add(ga_fvs1);

        // see note on MongolCode(FINA_GA_FVS2)
        PopupKeyCandidate ga_fvs2 = new PopupKeyCandidate(
                "" + MongolCode.Uni.GA + MongolCode.Uni.FVS2,
                "" + MongolCode.Uni.ZWJ + MongolCode.Uni.GA + MongolCode.Uni.FVS2);
        candidates.add(ga_fvs2);

        return candidates;
    }

    private List<PopupKeyCandidate> getCandidatesForH() {
        List<PopupKeyCandidate> candidates = new ArrayList<>();
        if (mIsShowingPunctuation) {
            candidates.add(new PopupKeyCandidate("$"));
            candidates.add(new PopupKeyCandidate("₮"));
            return candidates;
        }
        candidates.add(new PopupKeyCandidate(MongolCode.Uni.HAA));
        return candidates;
    }

    private List<PopupKeyCandidate> getCandidatesForJ() {
        List<PopupKeyCandidate> candidates = new ArrayList<>();
        if (mIsShowingPunctuation) {
            candidates.add(new PopupKeyCandidate(KEY_J_PUNCT_SUB));
            return candidates;
        }
        candidates.add(new PopupKeyCandidate(MongolCode.Uni.ZHI));
        return candidates;
    }

    private List<PopupKeyCandidate> getCandidatesForK() {
        List<PopupKeyCandidate> candidates = new ArrayList<>();
        if (mIsShowingPunctuation) {
            candidates.add(new PopupKeyCandidate(KEY_K_PUNCT_SUB));
            return candidates;
        }
        return candidates;
    }

    private List<PopupKeyCandidate> getCandidatesForL() {
        List<PopupKeyCandidate> candidates = new ArrayList<>();
        if (mIsShowingPunctuation) {
            candidates.add(new PopupKeyCandidate(KEY_L_PUNCT_SUB));
            return candidates;
        }
        candidates.add(new PopupKeyCandidate(MongolCode.Uni.LHA));
        return candidates;
    }

    private List<PopupKeyCandidate> getCandidatesForNG() {
        List<PopupKeyCandidate> candidates = new ArrayList<>();
        if (mIsShowingPunctuation) {
            candidates.add(new PopupKeyCandidate(KEY_NG_PUNCT_SUB));
            return candidates;
        }
        return candidates;
    }

    private List<PopupKeyCandidate> getCandidatesForZwj() {
        List<PopupKeyCandidate> candidates = new ArrayList<>();
        if (mIsShowingPunctuation) {
            candidates.add(new PopupKeyCandidate(KEY_ZWJ_PUNCT_SUB));
            return candidates;
        }
        return candidates;
    }

    private List<PopupKeyCandidate> getCandidatesForZ() {
        List<PopupKeyCandidate> candidates = new ArrayList<>();
        if (mIsShowingPunctuation) {
            candidates.add(new PopupKeyCandidate(KEY_Z_PUNCT_SUB));
            candidates.add(new PopupKeyCandidate("*"));
            return candidates;
        }
        candidates.add(new PopupKeyCandidate(MongolCode.Uni.TSA));
        return candidates;
    }

    private List<PopupKeyCandidate> getCandidatesForX() {
        List<PopupKeyCandidate> candidates = new ArrayList<>();
        if (mIsShowingPunctuation) {
            candidates.add(new PopupKeyCandidate(KEY_X_PUNCT_SUB));
            return candidates;
        }
        return candidates;
    }

    private List<PopupKeyCandidate> getCandidatesForC() {
        List<PopupKeyCandidate> candidates = new ArrayList<>();
        if (mIsShowingPunctuation) {
            candidates.add(new PopupKeyCandidate(KEY_C_PUNCT_SUB));
            return candidates;
        }

        if (isIsolateOrInitial())
            return null;

        PopupKeyCandidate medial_O_FVS1 = new PopupKeyCandidate(
                "" + MongolCode.Uni.O + MongolCode.Uni.FVS1,
                "" + MongolCode.Uni.ZWJ + MongolCode.Uni.O + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ,
                "" + MongolCode.Uni.O + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ);
        candidates.add(medial_O_FVS1);

        PopupKeyCandidate final_O_FVS1 = new PopupKeyCandidate(
                "" + MongolCode.Uni.O + MongolCode.Uni.FVS1,
                "" + MongolCode.Uni.ZWJ + MongolCode.Uni.O + MongolCode.Uni.FVS1);
        candidates.add(final_O_FVS1);

        return candidates;
    }

    private List<PopupKeyCandidate> getCandidatesForV() {
        List<PopupKeyCandidate> candidates = new ArrayList<>();
        if (mIsShowingPunctuation) {
            candidates.add(new PopupKeyCandidate(KEY_V_PUNCT_SUB));
            return candidates;
        }

        if (!isIsolateOrInitial()) {

            PopupKeyCandidate medial_U_FVS1 = new PopupKeyCandidate(
                    "" + MongolCode.Uni.U + MongolCode.Uni.FVS1,
                    "" + MongolCode.Uni.ZWJ + MongolCode.Uni.U + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ,
                    "" + MongolCode.Uni.U + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ);
            candidates.add(medial_U_FVS1);

            PopupKeyCandidate final_U_FVS1 = new PopupKeyCandidate(
                    "" + MongolCode.Uni.U + MongolCode.Uni.FVS1,
                    "" + MongolCode.Uni.ZWJ + MongolCode.Uni.U + MongolCode.Uni.FVS1);
            candidates.add(final_U_FVS1);

        }

        if (!hasCandidatesView()) {
            candidates.addAll(getSuffixForKeyV());
        }

        return candidates;
    }

    private List<PopupKeyCandidate> getSuffixForKeyV() {
        List<PopupKeyCandidate> suffixes = new ArrayList<>();
        if (mKeyboardListener == null) return suffixes;

        String previousWord = mKeyboardListener.getPreviousMongolWord(true);
        MongolCode.Gender gender = MongolCode.getWordGender(previousWord);

        if (gender == MongolCode.Gender.FEMININE) {
            suffixes.add(new PopupKeyCandidate(MongolCode.Suffix.UEN));
            suffixes.add(new PopupKeyCandidate(MongolCode.Suffix.UED));
            suffixes.add(new PopupKeyCandidate(MongolCode.Suffix.UEUE));
        } else {
            suffixes.add(new PopupKeyCandidate(MongolCode.Suffix.UN));
            suffixes.add(new PopupKeyCandidate(MongolCode.Suffix.UD));
            suffixes.add(new PopupKeyCandidate(MongolCode.Suffix.UU));
        }
        return suffixes;
    }

    private List<PopupKeyCandidate> getCandidatesForB() {
        List<PopupKeyCandidate> candidates = new ArrayList<>();
        if (mIsShowingPunctuation) {
            candidates.add(new PopupKeyCandidate(KEY_B_PUNCT_SUB));
            return candidates;
        }

        if (!hasCandidatesView()) {
            candidates.addAll(getSuffixForKeyB());
        }

        return candidates;
    }

    private List<PopupKeyCandidate> getSuffixForKeyB() {
        List<PopupKeyCandidate> suffixes = new ArrayList<>();
        if (mKeyboardListener == null) return suffixes;

        String previousWord = mKeyboardListener.getPreviousMongolWord(true);
        MongolCode.Gender gender = MongolCode.getWordGender(previousWord);

        if (gender == MongolCode.Gender.FEMININE) {
            suffixes.add(new PopupKeyCandidate(MongolCode.Suffix.BER));
            suffixes.add(new PopupKeyCandidate(MongolCode.Suffix.BEN));
        } else {
            suffixes.add(new PopupKeyCandidate(MongolCode.Suffix.BAR));
            suffixes.add(new PopupKeyCandidate(MongolCode.Suffix.BAN));
        }
        return suffixes;
    }

    private List<PopupKeyCandidate> getCandidatesForN() {
        List<PopupKeyCandidate> candidates = new ArrayList<>();
        if (mIsShowingPunctuation) {
            candidates.add(new PopupKeyCandidate(KEY_N_PUNCT_SUB));
            return candidates;
        }

        if (!isIsolateOrInitial()) {

            // only(?) way to override dotted N before vowel in Unicode 10.0
            PopupKeyCandidate na_zwj = new PopupKeyCandidate(
                    "" + MongolCode.Uni.NA + MongolCode.Uni.ZWJ,
                    "" + MongolCode.Uni.ZWJ + MongolCode.Uni.NA + MongolCode.Uni.ZWJ);
            candidates.add(na_zwj);

            PopupKeyCandidate na_fvs1 = new PopupKeyCandidate(
                    "" + MongolCode.Uni.NA + MongolCode.Uni.FVS1,
                    "" + MongolCode.Uni.ZWJ + MongolCode.Uni.NA + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ,
                    "" + MongolCode.Uni.NA + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ);
            candidates.add(na_fvs1);

        }

        if (!hasCandidatesView()) {
            candidates.addAll(getSuffixForKeyN());
        }

        return candidates;
    }

    private List<PopupKeyCandidate> getSuffixForKeyN() {
        List<PopupKeyCandidate> suffixes = new ArrayList<>();
        if (mKeyboardListener == null) return suffixes;

        String previousWord = mKeyboardListener.getPreviousMongolWord(true);
        MongolCode.Gender gender = MongolCode.getWordGender(previousWord);

        if (gender == MongolCode.Gender.FEMININE) {
            suffixes.add(new PopupKeyCandidate(MongolCode.Suffix.UE));
            suffixes.add(new PopupKeyCandidate(MongolCode.Suffix.NUEGUED));
        } else if (gender == MongolCode.Gender.MASCULINE) {
            suffixes.add(new PopupKeyCandidate(MongolCode.Suffix.U));
            suffixes.add(new PopupKeyCandidate(MongolCode.Suffix.NUGUD));
        } else {
            suffixes.add(new PopupKeyCandidate(MongolCode.Suffix.U));
            suffixes.add(new PopupKeyCandidate(MongolCode.Suffix.NUGUD));
            suffixes.add(new PopupKeyCandidate(MongolCode.Suffix.NUEGUED));
        }
        return suffixes;
    }

    private List<PopupKeyCandidate> getCandidatesForM() {
        List<PopupKeyCandidate> candidates = new ArrayList<>();
        if (mIsShowingPunctuation) {
            candidates.add(new PopupKeyCandidate(MongolCode.Uni.DOUBLE_EXCLAMATION_MARK));
            candidates.add(new PopupKeyCandidate(MongolCode.Uni.DOUBLE_QUESTION_MARK));
            candidates.add(new PopupKeyCandidate(MongolCode.Uni.EXCLAMATION_QUESTION_MARK));
            return candidates;
        }
        return candidates;
    }

    private List<PopupKeyCandidate> getCandidatesForSpace() {
        List<PopupKeyCandidate> candidates = new ArrayList<>();
        PopupKeyCandidate nnbs = new PopupKeyCandidate(
                "" + MongolCode.Uni.NNBS,
                KEY_SPACE_SUB_DISPLAY,
                " ");
        candidates.add(nnbs);
        return candidates;
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
