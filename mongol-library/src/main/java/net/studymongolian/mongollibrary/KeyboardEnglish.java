package net.studymongolian.mongollibrary;


import android.content.Context;
import android.util.AttributeSet;

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
    protected KeyText mKeyExclamation;
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
                1/9f, 1/9f, 1/9f, 1/9f, 1/9f, 1/9f, 1/9f, 1/9f, 1/9f,           // row 1
                0.15f, 0.1f, 0.1f, 0.1f, 0.1f, 0.1f, 0.1f, 0.1f, 0.15f,         // row 2
                0.15f, 0.1f, 0.1f, 0.3f, 0.1f, 0.1f, 0.15f};                    // row 3

        // Make sure that the total keys added to this ViewGroup below equals
        // the mNumberOfKeysInRow and mKeyWeights array totals above.

        instantiateKeys(context);
        makeKeysLowercase();
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

    private void makeKeysLowercase() {

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

    private void makeKeysUppercase() {

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
        mKeyExclamation.setIsRotatedPrimaryText(false);
        mKeyComma.setIsRotatedPrimaryText(false);
        mKeySpace.setIsRotatedPrimaryText(false);
        mKeyPeriod.setIsRotatedPrimaryText(false);
        mKeyQuestion.setIsRotatedPrimaryText(false);
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

    public List<PopupKeyCandidate> getPopupCandidates(Key key) {
        // get the appropriate candidates based on the key pressed
        if (key == mKeyKeyboard) {
            return getCandidatesForKeyboardKey();
        }

        return null;
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
