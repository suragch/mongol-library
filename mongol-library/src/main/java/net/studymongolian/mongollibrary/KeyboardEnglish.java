package net.studymongolian.mongollibrary;


import android.content.Context;
import android.util.AttributeSet;

import java.util.ArrayList;
import java.util.List;

public class KeyboardEnglish extends Keyboard {

    // name to use in the keyboard popup chooser
    private static final String DEFAULT_DISPLAY_NAME = "ᠠᠩᠭᠯᠢ";

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
    protected KeyText mKeyQuote;
    protected KeyText mKeyComma;
    protected KeyText mKeySpace;
    protected KeyText mKeyPeriod;
    protected KeyText mKeyQuestion;
    protected KeyImage mKeyReturn;

    private static final String NEWLINE = "\n";

    // Use this constructor if you want the default style
    public KeyboardEnglish(Context context) {
        super(context);
        init(context);
    }

    public KeyboardEnglish(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public KeyboardEnglish(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    protected void init(Context context) {

        // keyboard layout

        // | Q | W | E | R | T | Y | U | I | O | P |  Row 1
        // | A  |  S | D | F | G | H | J | K  | L  |    Row 2
        // |shift| Z | X | C | V | B | N | M | del |  Row 3
        // |  kb | ! | , |   space   | : | ? | ret |  Row 4

        // actual layout work is done by Keyboard superclass's onLayout
        mNumberOfKeysInRow = new int[]{10, 9, 9, 7}; // 36 keys total
        // this is the percent to inset the row
        mInsetWeightInRow = new float[]{0, 0, 0, 0};
        // the key weights for each row should sum to 1 (unless there is an inset)
        mKeyWeights = new float[]{
                0.1f, 0.1f, 0.1f, 0.1f, 0.1f, 0.1f, 0.1f, 0.1f, 0.1f, 0.1f,     // row 0
                1 / 9f, 1 / 9f, 1 / 9f, 1 / 9f, 1 / 9f, 1 / 9f, 1 / 9f, 1 / 9f, 1 / 9f,           // row 1
                0.15f, 0.1f, 0.1f, 0.1f, 0.1f, 0.1f, 0.1f, 0.1f, 0.15f,         // row 2
                0.15f, 0.1f, 0.1f, 0.3f, 0.1f, 0.1f, 0.15f};                    // row 3

        // Make sure that the total keys added to this ViewGroup below equals
        // the mNumberOfKeysInRow and mKeyWeights array totals above.

        instantiateKeys(context);
        makeKeysLowercase();
        setNonChangingKeyValues();
        dontRotatePrimaryTextForSelectKeys();
        dontRotateSecondaryText();
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
        mKeyQuote = new KeyText(context);
        mKeyComma = new KeyText(context);
        mKeySpace = new KeyText(context);
        mKeyPeriod = new KeyText(context);
        mKeyQuestion = new KeyText(context);
        mKeyReturn = new KeyImage(context);
    }

    private void makeKeysLowercase() {

        // Row 1

        mKeyQ.setText("q");
        mKeyQ.setSwipeUpText("Q");
        mKeyQ.setSubText("1");

        mKeyW.setText("w");
        mKeyW.setSwipeUpText("W");
        mKeyW.setSubText("2");

        mKeyE.setText("e");
        mKeyE.setSwipeUpText("E");
        mKeyE.setSubText("3");

        mKeyR.setText("r");
        mKeyR.setSwipeUpText("R");
        mKeyR.setSubText("4");

        mKeyT.setText("t");
        mKeyT.setSwipeUpText("T");
        mKeyT.setSubText("5");

        mKeyY.setText("y");
        mKeyY.setSwipeUpText("Y");
        mKeyY.setSubText("6");

        mKeyU.setText("u");
        mKeyU.setSwipeUpText("U");
        mKeyU.setSubText("7");

        mKeyI.setText("i");
        mKeyI.setSwipeUpText("I");
        mKeyI.setSubText("8");

        mKeyO.setText("o");
        mKeyO.setSwipeUpText("O");
        mKeyO.setSubText("9");

        mKeyP.setText("p");
        mKeyP.setSwipeUpText("P");
        mKeyP.setSubText("0");

        // Row 2
        mKeyA.setText("a");
        mKeyA.setSwipeUpText("A");
        mKeyA.setSubText("ɑ");

        mKeyS.setText("s");
        mKeyS.setSwipeUpText("S");
        mKeyS.setSubText("");

        mKeyD.setText("d");
        mKeyD.setSwipeUpText("D");
        mKeyD.setSubText("ː");

        mKeyF.setText("f");
        mKeyF.setSwipeUpText("F");
        mKeyF.setSubText("̌");

        mKeyG.setText("g");
        mKeyG.setSwipeUpText("G");
        mKeyG.setSubText("");

        mKeyH.setText("h");
        mKeyH.setSwipeUpText("H");
        mKeyH.setSubText("");

        mKeyJ.setText("j");
        mKeyJ.setSwipeUpText("J");
        mKeyJ.setSubText("ʤ");

        mKeyK.setText("k");
        mKeyK.setSwipeUpText("K");
        mKeyK.setSubText("");

        mKeyL.setText("l");
        mKeyL.setSwipeUpText("L");
        mKeyL.setSubText("ɬ");

        // Row 3

        mKeyZ.setText("z");
        mKeyZ.setSwipeUpText("Z");
        mKeyZ.setSubText("");

        mKeyX.setText("x");
        mKeyX.setSwipeUpText("X");
        mKeyX.setSubText("ʃ");

        mKeyC.setText("c");
        mKeyC.setSwipeUpText("C");
        mKeyC.setSubText("ɔ");

        mKeyV.setText("v");
        mKeyV.setSwipeUpText("V");
        mKeyV.setSubText("ʊ");

        mKeyB.setText("b");
        mKeyB.setSwipeUpText("B");
        mKeyB.setSubText("");

        mKeyN.setText("n");
        mKeyN.setSwipeUpText("N");
        mKeyN.setSubText("ŋ");

        mKeyM.setText("m");
        mKeyM.setSwipeUpText("M");
        mKeyM.setSubText("");
    }

    private void makeKeysUppercase() {

        // Row 1

        mKeyQ.setText("Q");
        mKeyQ.setSwipeUpText("");
        mKeyQ.setSubText("");

        mKeyW.setText("W");
        mKeyW.setSwipeUpText("");
        mKeyW.setSubText("");

        mKeyE.setText("E");
        mKeyE.setSwipeUpText("");
        mKeyE.setSubText("");

        mKeyR.setText("R");
        mKeyR.setSwipeUpText("");
        mKeyR.setSubText("");

        mKeyT.setText("T");
        mKeyT.setSwipeUpText("");
        mKeyT.setSubText("");

        mKeyY.setText("Y");
        mKeyY.setSwipeUpText("");
        mKeyY.setSubText("");

        mKeyU.setText("U");
        mKeyU.setSwipeUpText("");
        mKeyU.setSubText("");

        mKeyI.setText("I");
        mKeyI.setSwipeUpText("");
        mKeyI.setSubText("");

        mKeyO.setText("O");
        mKeyO.setSwipeUpText("");
        mKeyO.setSubText("");

        mKeyP.setText("P");
        mKeyP.setSwipeUpText("");
        mKeyP.setSubText("");

        // Row 2
        mKeyA.setText("A");
        mKeyA.setSwipeUpText("");
        mKeyA.setSubText("");

        mKeyS.setText("S");
        mKeyS.setSwipeUpText("");
        mKeyS.setSubText("");

        mKeyD.setText("D");
        mKeyD.setSwipeUpText("");
        mKeyD.setSubText("");

        mKeyF.setText("F");
        mKeyF.setSwipeUpText("");
        mKeyF.setSubText("");

        mKeyG.setText("G");
        mKeyG.setSwipeUpText("");
        mKeyG.setSubText("");

        mKeyH.setText("H");
        mKeyH.setSwipeUpText("");
        mKeyH.setSubText("");

        mKeyJ.setText("J");
        mKeyJ.setSwipeUpText("");
        mKeyJ.setSubText("");

        mKeyK.setText("K");
        mKeyK.setSwipeUpText("");
        mKeyK.setSubText("");

        mKeyL.setText("L");
        mKeyL.setSwipeUpText("");
        mKeyL.setSubText("");

        // Row 3

        mKeyZ.setText("Z");
        mKeyZ.setSwipeUpText("");
        mKeyZ.setSubText("");

        mKeyX.setText("X");
        mKeyX.setSwipeUpText("");
        mKeyX.setSubText("");

        mKeyC.setText("C");
        mKeyC.setSwipeUpText("");
        mKeyC.setSubText("");

        mKeyV.setText("V");
        mKeyV.setSwipeUpText("");
        mKeyV.setSubText("");

        mKeyB.setText("B");
        mKeyB.setSwipeUpText("");
        mKeyB.setSubText("");

        mKeyN.setText("N");
        mKeyN.setSwipeUpText("");
        mKeyN.setSubText("");

        mKeyM.setText("M");
        mKeyM.setSwipeUpText("");
        mKeyM.setSubText("");
    }


    private void setPunctuationKeyValues() {

        // Row 1

        mKeyQ.setText("1");
        mKeyQ.setSwipeUpText("");
        mKeyQ.setSubText("");

        mKeyW.setText("2");
        mKeyW.setSwipeUpText("");
        mKeyW.setSubText("");

        mKeyE.setText("3");
        mKeyE.setSwipeUpText("");
        mKeyE.setSubText("");

        mKeyR.setText("4");
        mKeyR.setSwipeUpText("");
        mKeyR.setSubText("");

        mKeyT.setText("5");
        mKeyT.setSwipeUpText("");
        mKeyT.setSubText("");

        mKeyY.setText("6");
        mKeyY.setSwipeUpText("");
        mKeyY.setSubText("");

        mKeyU.setText("7");
        mKeyU.setSwipeUpText("");
        mKeyU.setSubText("");

        mKeyI.setText("8");
        mKeyI.setSwipeUpText("");
        mKeyI.setSubText("");

        mKeyO.setText("9");
        mKeyO.setSwipeUpText("");
        mKeyO.setSubText("");

        mKeyP.setText("0");
        mKeyP.setSwipeUpText("");
        mKeyP.setSubText("");

        // Row 2

        mKeyA.setText("@");
        mKeyA.setSwipeUpText("~");
        mKeyA.setSubText("~");

        mKeyS.setText("#");
        mKeyS.setSwipeUpText("");
        mKeyS.setSubText("");

        mKeyD.setText("$");
        mKeyD.setSwipeUpText("¥");
        mKeyD.setSubText("¥");

        mKeyF.setText("%");
        mKeyF.setSwipeUpText("");
        mKeyF.setSubText("");

        mKeyG.setText("^");
        mKeyG.setSwipeUpText("");
        mKeyG.setSubText("");

        mKeyH.setText("&");
        mKeyH.setSwipeUpText("");
        mKeyH.setSubText("");

        mKeyJ.setText("*");
        mKeyJ.setSwipeUpText("");
        mKeyJ.setSubText("");

        mKeyK.setText("(");
        mKeyK.setSwipeUpText("[");
        mKeyK.setSubText("[");

        mKeyL.setText(")");
        mKeyL.setSwipeUpText("]");
        mKeyL.setSubText("]");

        // Row 3

        mKeyZ.setText("°");
        mKeyZ.setSwipeUpText("℃");
        mKeyZ.setSubText("℃");

        mKeyX.setText("+");
        mKeyX.setSwipeUpText("");
        mKeyX.setSubText("");

        mKeyC.setText("-");
        mKeyC.setSwipeUpText("_");
        mKeyC.setSubText("_");

        mKeyV.setText("×");
        mKeyV.setSwipeUpText("");
        mKeyV.setSubText("");

        mKeyB.setText("÷");
        mKeyB.setSwipeUpText("√");
        mKeyB.setSubText("√");

        mKeyN.setText("=");
        mKeyN.setSwipeUpText("");
        mKeyN.setSubText("");

        mKeyM.setText("/");
        mKeyM.setSwipeUpText("\\");
        mKeyM.setSubText("\\");
    }

    private void setNonChangingKeyValues() {
        mKeyQuote.setText("\'");
        mKeyQuote.setSwipeUpText("\"");
        mKeyQuote.setSubText("\"");

        mKeyComma.setText(",");
        mKeyComma.setSwipeUpText(";");
        mKeyComma.setSubText(";");

        mKeySpace.setText(" ");

        mKeyPeriod.setText(".");
        mKeyPeriod.setSwipeUpText(":");
        mKeyPeriod.setSubText(":");

        mKeyQuestion.setText("?");
        mKeyQuestion.setSwipeUpText("!");
        mKeyQuestion.setSubText("!");

        mKeyReturn.setText(NEWLINE);
    }

    private void dontRotatePrimaryTextForSelectKeys() {

        // Row 1
        mKeyQ.setIsRotatedPrimaryText(false);
        mKeyW.setIsRotatedPrimaryText(false);
        mKeyE.setIsRotatedPrimaryText(false);
        mKeyR.setIsRotatedPrimaryText(false);
        mKeyT.setIsRotatedPrimaryText(false);
        mKeyY.setIsRotatedPrimaryText(false);
        mKeyU.setIsRotatedPrimaryText(false);
        mKeyI.setIsRotatedPrimaryText(false);
        mKeyO.setIsRotatedPrimaryText(false);
        mKeyP.setIsRotatedPrimaryText(false);

        // Row 2
        mKeyA.setIsRotatedPrimaryText(false);
        mKeyS.setIsRotatedPrimaryText(false);
        mKeyD.setIsRotatedPrimaryText(false);
        mKeyF.setIsRotatedPrimaryText(false);
        mKeyG.setIsRotatedPrimaryText(false);
        mKeyH.setIsRotatedPrimaryText(false);
        mKeyJ.setIsRotatedPrimaryText(false);
        mKeyK.setIsRotatedPrimaryText(false);
        mKeyL.setIsRotatedPrimaryText(false);

        // Row 3
        mKeyZ.setIsRotatedPrimaryText(false);
        mKeyX.setIsRotatedPrimaryText(false);
        mKeyC.setIsRotatedPrimaryText(false);
        mKeyV.setIsRotatedPrimaryText(false);
        mKeyB.setIsRotatedPrimaryText(false);
        mKeyN.setIsRotatedPrimaryText(false);
        mKeyM.setIsRotatedPrimaryText(false);

        // Row 4
        mKeyQuote.setIsRotatedPrimaryText(false);
        mKeyComma.setIsRotatedPrimaryText(false);
        mKeySpace.setIsRotatedPrimaryText(false);
        mKeyPeriod.setIsRotatedPrimaryText(false);
        mKeyQuestion.setIsRotatedPrimaryText(false);
    }

    private void dontRotateSecondaryText() {
        // Row 1
        mKeyQ.setIsRotatedSubText(false);
        mKeyW.setIsRotatedSubText(false);
        mKeyE.setIsRotatedSubText(false);
        mKeyR.setIsRotatedSubText(false);
        mKeyT.setIsRotatedSubText(false);
        mKeyY.setIsRotatedSubText(false);
        mKeyU.setIsRotatedSubText(false);
        mKeyI.setIsRotatedSubText(false);
        mKeyO.setIsRotatedSubText(false);
        mKeyP.setIsRotatedSubText(false);

        // Row 2
        mKeyA.setIsRotatedSubText(false);
        mKeyS.setIsRotatedSubText(false);
        mKeyD.setIsRotatedSubText(false);
        mKeyF.setIsRotatedSubText(false);
        mKeyG.setIsRotatedSubText(false);
        mKeyH.setIsRotatedSubText(false);
        mKeyJ.setIsRotatedSubText(false);
        mKeyK.setIsRotatedSubText(false);
        mKeyL.setIsRotatedSubText(false);

        // Row 3
        mKeyZ.setIsRotatedSubText(false);
        mKeyX.setIsRotatedSubText(false);
        mKeyC.setIsRotatedSubText(false);
        mKeyV.setIsRotatedSubText(false);
        mKeyB.setIsRotatedSubText(false);
        mKeyN.setIsRotatedSubText(false);
        mKeyM.setIsRotatedSubText(false);

        // Row 4
        mKeyQuote.setIsRotatedSubText(false);
        mKeyComma.setIsRotatedSubText(false);
        mKeyPeriod.setIsRotatedSubText(false);
        mKeyQuestion.setIsRotatedSubText(false);
    }

    private void setKeyImages() {
        mKeyShift.setShiftImage(getPrimaryTextColor());
        mKeyBackspace.setImage(getBackspaceImage(), getPrimaryTextColor());
        mKeyKeyboard.setImage(getKeyboardImage(), getPrimaryTextColor());
        mKeyReturn.setImage(getReturnImage(), getPrimaryTextColor());
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
        mKeyQuote.setKeyListener(this);
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
        addView(mKeyQuote);
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
        } else if (key == mKeyQuote) {
            return getCandidatesForQuote();
        } else if (key == mKeyComma) {
            return getCandidatesForComma();
        } else if (key == mKeySpace) {
            return getCandidatesForSpace();
        } else if (key == mKeyPeriod) {
            return getCandidatesForPeriod();
        } else if (key == mKeyQuestion) {
            return getCandidatesForQuestion();
        }

        return null;
    }

    private List<PopupKeyCandidate> getCandidatesForQ() {
        List<PopupKeyCandidate> candidates = new ArrayList<>();
        if (mIsShowingPunctuation) {
            candidates.add(new PopupKeyCandidate("¹", false));
            return candidates;
        }
        candidates.add(new PopupKeyCandidate("1", false));
        candidates.add(new PopupKeyCandidate("ʧ", false));
        candidates.add(new PopupKeyCandidate("č", false));
        candidates.add(new PopupKeyCandidate("tʂ", false));
        return candidates;
    }

    private List<PopupKeyCandidate> getCandidatesForW() {
        List<PopupKeyCandidate> candidates = new ArrayList<>();
        if (mIsShowingPunctuation) {
            candidates.add(new PopupKeyCandidate("²", false));
            candidates.add(new PopupKeyCandidate("½", false));
            return candidates;
        }
        candidates.add(new PopupKeyCandidate("2", false));
        //candidates.add(new PopupKeyCandidate("ʦ", false));
        return candidates;
    }

    private List<PopupKeyCandidate> getCandidatesForE() {
        List<PopupKeyCandidate> candidates = new ArrayList<>();
        if (mIsShowingPunctuation) {
            candidates.add(new PopupKeyCandidate("³", false));
            candidates.add(new PopupKeyCandidate("⅓", false));
            return candidates;
        }

        candidates.add(new PopupKeyCandidate("3", false));
        candidates.add(new PopupKeyCandidate("ə", false));
        candidates.add(new PopupKeyCandidate("ᴇ", false));
        candidates.add(new PopupKeyCandidate("ē", false));
        candidates.add(new PopupKeyCandidate("é", false));
        candidates.add(new PopupKeyCandidate("ě", false));
        candidates.add(new PopupKeyCandidate("è", false));
        return candidates;
    }

    private List<PopupKeyCandidate> getCandidatesForR() {
        List<PopupKeyCandidate> candidates = new ArrayList<>();
        if (mIsShowingPunctuation) {
            candidates.add(new PopupKeyCandidate("¼", false));
            candidates.add(new PopupKeyCandidate("¾", false));
            return candidates;
        }
        candidates.add(new PopupKeyCandidate("4", false));
        return candidates;
    }

    private List<PopupKeyCandidate> getCandidatesForT() {
        List<PopupKeyCandidate> candidates = new ArrayList<>();
        if (mIsShowingPunctuation) {
            return candidates;
        }

        candidates.add(new PopupKeyCandidate("5", false));

        return candidates;
    }

    private List<PopupKeyCandidate> getCandidatesForY() {
        List<PopupKeyCandidate> candidates = new ArrayList<>();
        if (mIsShowingPunctuation) {
            return candidates;
        }
        candidates.add(new PopupKeyCandidate("6", false));
        candidates.add(new PopupKeyCandidate("ʏ", false));
        return candidates;
    }

    private List<PopupKeyCandidate> getCandidatesForU() {
        List<PopupKeyCandidate> candidates = new ArrayList<>();
        if (mIsShowingPunctuation) {
            return candidates;
        }
        candidates.add(new PopupKeyCandidate("ʉ", false));
        candidates.add(new PopupKeyCandidate("ü", false));
        candidates.add(new PopupKeyCandidate("ū", false));
        candidates.add(new PopupKeyCandidate("ú", false));
        candidates.add(new PopupKeyCandidate("ǔ", false));
        candidates.add(new PopupKeyCandidate("ù", false));
        candidates.add(new PopupKeyCandidate("7", false));
        return candidates;
    }

    private List<PopupKeyCandidate> getCandidatesForI() {
        List<PopupKeyCandidate> candidates = new ArrayList<>();
        if (mIsShowingPunctuation) {
            return candidates;
        }


        candidates.add(new PopupKeyCandidate("ɪ", false));
        candidates.add(new PopupKeyCandidate("ī", false));
        candidates.add(new PopupKeyCandidate("í", false));
        candidates.add(new PopupKeyCandidate("ǐ", false));
        candidates.add(new PopupKeyCandidate("ì", false));
        candidates.add(new PopupKeyCandidate("8", false));

        return candidates;
    }

    private List<PopupKeyCandidate> getCandidatesForO() {
        List<PopupKeyCandidate> candidates = new ArrayList<>();
        if (mIsShowingPunctuation) {
            return candidates;
        }

        candidates.add(new PopupKeyCandidate("ө", false));
        candidates.add(new PopupKeyCandidate("ö", false));
        candidates.add(new PopupKeyCandidate("ø", false));
        candidates.add(new PopupKeyCandidate("ō", false));
        candidates.add(new PopupKeyCandidate("ó", false));
        candidates.add(new PopupKeyCandidate("ǒ", false));
        candidates.add(new PopupKeyCandidate("ò", false));
        candidates.add(new PopupKeyCandidate("9", false));

        return candidates;
    }

    private List<PopupKeyCandidate> getCandidatesForP() {
        List<PopupKeyCandidate> candidates = new ArrayList<>();
        if (mIsShowingPunctuation) {
            return candidates;
        }
        candidates.add(new PopupKeyCandidate("0", false));
        return candidates;
    }

    private List<PopupKeyCandidate> getCandidatesForA() {
        List<PopupKeyCandidate> candidates = new ArrayList<>();
        if (mIsShowingPunctuation) {
            candidates.add(new PopupKeyCandidate("~", false));
            candidates.add(new PopupKeyCandidate("`", false));
            candidates.add(new PopupKeyCandidate("©", false));
            candidates.add(new PopupKeyCandidate("®", false));
            return candidates;
        }

        candidates.add(new PopupKeyCandidate("ɑ", false));
        candidates.add(new PopupKeyCandidate("æ", false));
        candidates.add(new PopupKeyCandidate("ā", false));
        candidates.add(new PopupKeyCandidate("á", false));
        candidates.add(new PopupKeyCandidate("ǎ", false));
        candidates.add(new PopupKeyCandidate("à", false));

        return candidates;
    }

    private List<PopupKeyCandidate> getCandidatesForS() {
        List<PopupKeyCandidate> candidates = new ArrayList<>();
        if (mIsShowingPunctuation) {
            return candidates;
        }
        candidates.add(new PopupKeyCandidate("ʂ", false));

        return candidates;
    }

    private List<PopupKeyCandidate> getCandidatesForD() {
        List<PopupKeyCandidate> candidates = new ArrayList<>();
        if (mIsShowingPunctuation) {
            candidates.add(new PopupKeyCandidate("¥", false));
            candidates.add(new PopupKeyCandidate("₮", false));
            candidates.add(new PopupKeyCandidate("€", false));
            candidates.add(new PopupKeyCandidate("£", false));
            candidates.add(new PopupKeyCandidate("¢", false));
            return candidates;
        }

        candidates.add(new PopupKeyCandidate("ː", false));
        return candidates;
    }

    private List<PopupKeyCandidate> getCandidatesForF() {
        List<PopupKeyCandidate> candidates = new ArrayList<>();
        if (mIsShowingPunctuation) {
            return candidates;
        }
        candidates.add(new PopupKeyCandidate("̌", "x̌", null, false));
        return candidates;
    }

    private List<PopupKeyCandidate> getCandidatesForG() {
        List<PopupKeyCandidate> candidates = new ArrayList<>();
        if (mIsShowingPunctuation) {
            return candidates;
        }

        candidates.add(new PopupKeyCandidate("ɣ", false));
        candidates.add(new PopupKeyCandidate("ɡ", false));
        candidates.add(new PopupKeyCandidate("ɢ", false));

        return candidates;
    }

    private List<PopupKeyCandidate> getCandidatesForH() {
        List<PopupKeyCandidate> candidates = new ArrayList<>();
        if (mIsShowingPunctuation) {
            return candidates;
        }

        return candidates;
    }

    private List<PopupKeyCandidate> getCandidatesForJ() {
        List<PopupKeyCandidate> candidates = new ArrayList<>();
        if (mIsShowingPunctuation) {
            return candidates;
        }

        candidates.add(new PopupKeyCandidate("ʤ", false));
        candidates.add(new PopupKeyCandidate("ǰ", false));
        candidates.add(new PopupKeyCandidate("dʐ", false));
        return candidates;
    }

    private List<PopupKeyCandidate> getCandidatesForK() {
        List<PopupKeyCandidate> candidates = new ArrayList<>();
        if (mIsShowingPunctuation) {
            candidates.add(new PopupKeyCandidate("[", false));
            candidates.add(new PopupKeyCandidate("{", false));
            return candidates;
        }
        return candidates;
    }

    private List<PopupKeyCandidate> getCandidatesForL() {
        List<PopupKeyCandidate> candidates = new ArrayList<>();
        if (mIsShowingPunctuation) {
            candidates.add(new PopupKeyCandidate("]", false));
            candidates.add(new PopupKeyCandidate("}", false));
            return candidates;
        }

        candidates.add(new PopupKeyCandidate("ɬ", false));
        candidates.add(new PopupKeyCandidate("ɮ", false));
        return candidates;
    }

    private List<PopupKeyCandidate> getCandidatesForZ() {
        List<PopupKeyCandidate> candidates = new ArrayList<>();
        if (mIsShowingPunctuation) {
            candidates.add(new PopupKeyCandidate("℃", false));
            return candidates;
        }
        candidates.add(new PopupKeyCandidate("ʣ", false));
        candidates.add(new PopupKeyCandidate("ʦ", false));
        candidates.add(new PopupKeyCandidate("ʐ", false));
        return candidates;
    }

    private List<PopupKeyCandidate> getCandidatesForX() {
        List<PopupKeyCandidate> candidates = new ArrayList<>();
        if (mIsShowingPunctuation) {
            candidates.add(new PopupKeyCandidate("±", false));
            candidates.add(new PopupKeyCandidate("∑", false));
            return candidates;
        }

        candidates.add(new PopupKeyCandidate("ʃ", false));
        candidates.add(new PopupKeyCandidate("š", false));
        return candidates;
    }

    private List<PopupKeyCandidate> getCandidatesForC() {
        List<PopupKeyCandidate> candidates = new ArrayList<>();
        if (mIsShowingPunctuation) {
            candidates.add(new PopupKeyCandidate("_", false));
            return candidates;
        }

        candidates.add(new PopupKeyCandidate("ɔ", false));
        candidates.add(new PopupKeyCandidate("œ", false));

        return candidates;
    }

    private List<PopupKeyCandidate> getCandidatesForV() {
        List<PopupKeyCandidate> candidates = new ArrayList<>();
        if (mIsShowingPunctuation) {
            return candidates;
        }
        candidates.add(new PopupKeyCandidate("ʊ", false));
        candidates.add(new PopupKeyCandidate("ǖ", false));
        candidates.add(new PopupKeyCandidate("ǘ", false));
        candidates.add(new PopupKeyCandidate("ǚ", false));
        candidates.add(new PopupKeyCandidate("ǜ", false));
        return candidates;
    }

    private List<PopupKeyCandidate> getCandidatesForB() {
        List<PopupKeyCandidate> candidates = new ArrayList<>();
        if (mIsShowingPunctuation) {
            candidates.add(new PopupKeyCandidate("√", false));
            return candidates;
        }

        return candidates;
    }

    private List<PopupKeyCandidate> getCandidatesForN() {
        List<PopupKeyCandidate> candidates = new ArrayList<>();
        if (mIsShowingPunctuation) {
            candidates.add(new PopupKeyCandidate("<", false));
            candidates.add(new PopupKeyCandidate(">", false));
            candidates.add(new PopupKeyCandidate("≤", false));
            candidates.add(new PopupKeyCandidate("≥", false));
            candidates.add(new PopupKeyCandidate("≠", false));
            candidates.add(new PopupKeyCandidate("≈", false));
            return candidates;
        }

        candidates.add(new PopupKeyCandidate("ŋ", false));
        return candidates;
    }

    private List<PopupKeyCandidate> getCandidatesForM() {
        List<PopupKeyCandidate> candidates = new ArrayList<>();
        if (mIsShowingPunctuation) {
            candidates.add(new PopupKeyCandidate("\\", false));
            candidates.add(new PopupKeyCandidate("|", false));
            return candidates;
        }
        return candidates;
    }

    private List<PopupKeyCandidate> getCandidatesForQuote() {
        List<PopupKeyCandidate> candidates = new ArrayList<>();

        candidates.add(new PopupKeyCandidate("\"", false));
        return candidates;
    }

    private List<PopupKeyCandidate> getCandidatesForComma() {
        List<PopupKeyCandidate> candidates = new ArrayList<>();

        candidates.add(new PopupKeyCandidate(";", false));
        return candidates;
    }

    private List<PopupKeyCandidate> getCandidatesForSpace() {
        return null;
    }

    private List<PopupKeyCandidate> getCandidatesForPeriod() {
        List<PopupKeyCandidate> candidates = new ArrayList<>();

        candidates.add(new PopupKeyCandidate(":", false));
        return candidates;
    }

    private List<PopupKeyCandidate> getCandidatesForQuestion() {
        List<PopupKeyCandidate> candidates = new ArrayList<>();

        candidates.add(new PopupKeyCandidate("!", false));
        return candidates;
    }

    @Override
    public String getDisplayName() {
        if (mDisplayName == null)
            return DEFAULT_DISPLAY_NAME;
        return mDisplayName;
    }

    @Override
    public void onKeyInput(String text) {
        super.onKeyInput(text);
        if (mKeyShift != null)
            mKeyShift.turnOffCapsUnlessLocked();
    }

    @Override
    public void onKeyboardKeyClick() {
        mIsShowingPunctuation = !mIsShowingPunctuation;
        if (mIsShowingPunctuation) {
            setPunctuationKeyValues();
        } else {
            makeKeysLowercase();
        }
    }

    @Override
    public void onShiftChanged(boolean shiftIsOn) {
        if (shiftIsOn) {
            makeKeysUppercase();
        } else {
            makeKeysLowercase();
        }
    }
}
