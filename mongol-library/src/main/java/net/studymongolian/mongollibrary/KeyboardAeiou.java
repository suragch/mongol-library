package net.studymongolian.mongollibrary;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;

import java.util.ArrayList;
import java.util.List;

public class KeyboardAeiou extends Keyboard {

    // name to use in the keyboard popup chooser
    private static final String DEFAULT_DISPLAY_NAME = "ᠴᠠᠭᠠᠨ ᠲᠣᠯᠤᠭᠠᠢ";

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

    private static final String NEWLINE = "\n";
    private static final String KEY_SPACE_SUB_DISPLAY = "ᠶ᠋ᠢ ᠳᠤ ᠤᠨ";

    public KeyboardAeiou(Context context) {
        super(context);
        init(context);
    }

    public KeyboardAeiou(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public KeyboardAeiou(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
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

        rotatePrimaryTextForSelectKeys();
        rotateSecondaryTextForSelectedKeys();

        // Row 1

        mKeyA.setText(MongolCode.Uni.A);
        mKeyA.setSwipeUpText(null);
        mKeyA.setSubText("" + MongolCode.Uni.ZWJ + MongolCode.Uni.A + MongolCode.Uni.FVS1);

        mKeyE.setText(MongolCode.Uni.E);
        mKeyE.setSwipeUpText(MongolCode.Uni.EE);
        mKeyE.setSubText(MongolCode.Uni.EE);

        mKeyI.setText(MongolCode.Uni.I);
        mKeyI.setSwipeUpText(null);
        mKeyI.setSubText("");

        mKeyO.setText(MongolCode.Uni.U);
        mKeyO.setSwipeUpText(null);
        mKeyO.setSubText("");

        mKeyU.setText(MongolCode.Uni.UE);
        mKeyU.setSwipeUpText(null);
        mKeyU.setSubText("");

        // Row 2

        mKeyNA.setText(MongolCode.Uni.NA);
        mKeyNA.setSwipeUpText(MongolCode.Uni.ANG);
        mKeyNA.setSubText(MongolCode.Uni.ANG);

        mKeyBA.setText(MongolCode.Uni.BA);
        mKeyBA.setSwipeUpText(MongolCode.Uni.PA);
        mKeyBA.setSubText(MongolCode.Uni.PA);

        mKeyQA.setText(MongolCode.Uni.QA);
        mKeyQA.setSwipeUpText(MongolCode.Uni.HAA);
        mKeyQA.setSubText(MongolCode.Uni.HAA);

        mKeyGA.setText(MongolCode.Uni.GA);
        mKeyGA.setSwipeUpText(MongolCode.Uni.KA);
        mKeyGA.setSubText(MongolCode.Uni.KA);

        mKeyMA.setText(MongolCode.Uni.MA);
        mKeyMA.setSwipeUpText(null);
        mKeyMA.setSubText("");

        mKeyLA.setText(MongolCode.Uni.LA);
        mKeyLA.setSwipeUpText(MongolCode.Uni.LHA);
        mKeyLA.setSubText(MongolCode.Uni.LHA);

        // Row 3

        mKeySA.setText(MongolCode.Uni.SA);
        mKeySA.setSwipeUpText(MongolCode.Uni.SHA);
        mKeySA.setSubText(MongolCode.Uni.SHA);

        mKeyTADA.setText(MongolCode.Uni.DA, MongolCode.Uni.TA);
        mKeyTADA.setSwipeUpText(null);
        mKeyTADA.setSubText(MongolCode.Uni.DA);

        mKeyCHA.setText(MongolCode.Uni.CHA);
        mKeyCHA.setSwipeUpText(MongolCode.Uni.ZA);
        mKeyCHA.setSubText(MongolCode.Uni.ZA);

        mKeyJA.setText(MongolCode.Uni.JA);
        mKeyJA.setSwipeUpText(MongolCode.Uni.ZHI);
        mKeyJA.setSubText(MongolCode.Uni.ZHI);

        mKeyYA.setText(MongolCode.Uni.YA);
        mKeyYA.setSwipeUpText(MongolCode.Uni.WA);
        mKeyYA.setSubText(MongolCode.Uni.WA);

        mKeyRA.setText(MongolCode.Uni.RA);
        mKeyRA.setSwipeUpText(MongolCode.Uni.ZRA);
        mKeyRA.setSubText(MongolCode.Uni.ZRA);

        // Row 4

        mKeyComma.setText(MongolCode.Uni.MONGOLIAN_COMMA);
        mKeyComma.setSwipeUpText(MongolCode.Uni.VERTICAL_QUESTION_MARK);
        mKeyComma.setSubText(MongolCode.Uni.VERTICAL_QUESTION_MARK);

    }

    private void rotatePrimaryTextForSelectKeys() {
        mKeyNA.setIsRotatedPrimaryText(true);
        mKeyBA.setIsRotatedPrimaryText(true);
        mKeyQA.setIsRotatedPrimaryText(true);
        mKeyGA.setIsRotatedPrimaryText(true);
        mKeyMA.setIsRotatedPrimaryText(true);
        mKeySA.setIsRotatedPrimaryText(true);
        mKeyTADA.setIsRotatedPrimaryText(true);
        mKeyCHA.setIsRotatedPrimaryText(true);
        mKeyJA.setIsRotatedPrimaryText(true);
        mKeyYA.setIsRotatedPrimaryText(true);

        mKeyComma.setIsRotatedPrimaryText(true);
    }


    private void dontRotatePrimaryTextForSelectKeys() {
        mKeyNA.setIsRotatedPrimaryText(false);
        mKeyBA.setIsRotatedPrimaryText(false);
        mKeyQA.setIsRotatedPrimaryText(false);
        mKeyGA.setIsRotatedPrimaryText(false);
        mKeyMA.setIsRotatedPrimaryText(false);
        mKeySA.setIsRotatedPrimaryText(false);
        mKeyTADA.setIsRotatedPrimaryText(false);
        mKeyCHA.setIsRotatedPrimaryText(false);
        mKeyJA.setIsRotatedPrimaryText(false);
        mKeyYA.setIsRotatedPrimaryText(false);

        mKeyComma.setIsRotatedPrimaryText(false);
    }

    private void rotateSecondaryTextForSelectedKeys() {
        mKeyComma.setIsRotatedSubText(true);
    }

    private void dontRotateSecondaryTextForSelectedKeys() {
        mKeyComma.setIsRotatedSubText(false);
    }

    private void setPunctuationKeyValues() {

        dontRotatePrimaryTextForSelectKeys();
        dontRotateSecondaryTextForSelectedKeys();

        // Row 1

        mKeyA.setText(MongolCode.Uni.VERTICAL_LEFT_PARENTHESIS);
        mKeyA.setSwipeUpText(MongolCode.Uni.VERTICAL_LEFT_SQUARE_BRACKET);
        mKeyA.setSubText(MongolCode.Uni.VERTICAL_LEFT_SQUARE_BRACKET);

        mKeyE.setText(MongolCode.Uni.VERTICAL_RIGHT_PARENTHESIS);
        mKeyE.setSwipeUpText(MongolCode.Uni.VERTICAL_RIGHT_SQUARE_BRACKET);
        mKeyE.setSubText(MongolCode.Uni.VERTICAL_RIGHT_SQUARE_BRACKET);

        mKeyI.setText(MongolCode.Uni.VERTICAL_LEFT_DOUBLE_ANGLE_BRACKET);
        mKeyI.setSwipeUpText(MongolCode.Uni.VERTICAL_LEFT_ANGLE_BRACKET);
        mKeyI.setSubText(MongolCode.Uni.VERTICAL_LEFT_ANGLE_BRACKET);

        mKeyO.setText(MongolCode.Uni.VERTICAL_RIGHT_DOUBLE_ANGLE_BRACKET);
        mKeyO.setSwipeUpText(MongolCode.Uni.VERTICAL_RIGHT_ANGLE_BRACKET);
        mKeyO.setSubText(MongolCode.Uni.VERTICAL_RIGHT_ANGLE_BRACKET);

        mKeyU.setText(MongolCode.Uni.MIDDLE_DOT);
        mKeyU.setSwipeUpText(MongolCode.Uni.MONGOLIAN_ELLIPSIS);
        mKeyU.setSubText(MongolCode.Uni.MONGOLIAN_ELLIPSIS);

        // Row 2

        mKeyNA.setText("1");
        mKeyNA.setSwipeUpText("①");
        mKeyNA.setSubText("①");

        mKeyBA.setText("2");
        mKeyBA.setSwipeUpText("②");
        mKeyBA.setSubText("②");

        mKeyQA.setText("3");
        mKeyQA.setSwipeUpText("③");
        mKeyQA.setSubText("③");

        mKeyGA.setText("4");
        mKeyGA.setSwipeUpText("④");
        mKeyGA.setSubText("④");

        mKeyMA.setText("5");
        mKeyMA.setSwipeUpText("⑤");
        mKeyMA.setSubText("⑤");

        mKeyLA.setText(MongolCode.Uni.VERTICAL_EM_DASH);
        mKeyLA.setSwipeUpText(MongolCode.Uni.MONGOLIAN_BIRGA);
        mKeyLA.setSubText(MongolCode.Uni.MONGOLIAN_BIRGA);

        // Row 3

        mKeySA.setText("6");
        mKeySA.setSwipeUpText("⑥");
        mKeySA.setSubText("⑥");

        mKeyTADA.setText("7");
        mKeyTADA.setSwipeUpText("⑦");
        mKeyTADA.setSubText("⑦");

        mKeyCHA.setText("8");
        mKeyCHA.setSwipeUpText("⑧");
        mKeyCHA.setSubText("⑧");

        mKeyJA.setText("9");
        mKeyJA.setSwipeUpText("⑨");
        mKeyJA.setSubText("⑨");

        mKeyYA.setText("0");
        mKeyYA.setSwipeUpText("⑩");
        mKeyYA.setSubText("⑩");

        mKeyRA.setText(MongolCode.Uni.QUESTION_EXCLAMATION_MARK);
        mKeyRA.setSwipeUpText(MongolCode.Uni.DOUBLE_QUESTION_MARK);
        mKeyRA.setSubText(MongolCode.Uni.DOUBLE_QUESTION_MARK);

        // Row 4

        mKeyComma.setText(".");
        mKeyComma.setSwipeUpText("-");
        mKeyComma.setSubText("-");
    }

    private void setNonChangingKeyValues() {
        mKeySpace.setText(" ");
        mKeySpace.setSwipeUpText(MongolCode.Uni.NNBS);
        if (hasCandidatesView()) {
            mKeySpace.setSubText(KEY_SPACE_SUB_DISPLAY);
        }
        mKeyReturn.setText(NEWLINE);
    }



    private void setKeyImages() {
        mKeyBackspace.setImage(getBackspaceImage(), getPrimaryTextColor());
        mKeyKeyboard.setImage(getKeyboardImage(), getPrimaryTextColor());
        mKeyReturn.setImage(getReturnImage(), getPrimaryTextColor());
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

    public List<PopupKeyCandidate> getPopupCandidates(Key key) {

        // get the appropriate candidates based on the key pressed
        if (key == mKeyA) {
            return getCandidatesForA();
        } else if (key == mKeyE) {
            return getCandidatesForE();
        } else if (key == mKeyI) {
            return getCandidatesForI();
        } else if (key == mKeyO) {
            return getCandidatesForO();
        } else if (key == mKeyU) {
            return getCandidatesForU();
        } else if (key == mKeyNA) {
            return getCandidatesForNA();
        } else if (key == mKeyBA) {
            return getCandidatesForBA();
        } else if (key == mKeyQA) {
            return getCandidatesForQA();
        } else if (key == mKeyGA) {
            return getCandidatesForGA();
        } else if (key == mKeyMA) {
            return getCandidatesForMA();
        } else if (key == mKeyLA) {
            return getCandidatesForLA();
        } else if (key == mKeySA) {
            return getCandidatesForSA();
        } else if (key == mKeyTADA) {
            return getCandidatesForTADA();
        } else if (key == mKeyCHA) {
            return getCandidatesForCHA();
        } else if (key == mKeyJA) {
            return getCandidatesForJA();
        } else if (key == mKeyYA) {
            return getCandidatesForYA();
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

    private List<PopupKeyCandidate> getCandidatesForA() {
        List<PopupKeyCandidate> candidates = new ArrayList<>();

        if (mIsShowingPunctuation) {
            candidates.add(new PopupKeyCandidate(MongolCode.Uni.VERTICAL_LEFT_SQUARE_BRACKET));
            return candidates;
        }

        PopupKeyCandidate nirugu = new PopupKeyCandidate(MongolCode.Uni.MONGOLIAN_NIRUGU);

        if (isIsolateOrInitial()) {
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


    private List<PopupKeyCandidate> getCandidatesForE() {
        List<PopupKeyCandidate> candidates = new ArrayList<>();

        if (mIsShowingPunctuation) {
            candidates.add(new PopupKeyCandidate(MongolCode.Uni.VERTICAL_RIGHT_SQUARE_BRACKET));
            return candidates;
        }


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

        candidates.add(new PopupKeyCandidate(MongolCode.Uni.EE));

        if (shouldShowSuffixesInPopup()) {
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

    private List<PopupKeyCandidate> getCandidatesForI() {
        List<PopupKeyCandidate> candidates = new ArrayList<>();

        if (mIsShowingPunctuation) {
            candidates.add(new PopupKeyCandidate(MongolCode.Uni.VERTICAL_LEFT_ANGLE_BRACKET));
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

        if (shouldShowSuffixesInPopup()) {
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
            candidates.add(new PopupKeyCandidate(MongolCode.Uni.VERTICAL_RIGHT_ANGLE_BRACKET));
            return candidates;
        }

        if (isIsolateOrInitial()) {
            candidates.add(new PopupKeyCandidate(MongolCode.Uni.O));
        } else {

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

        if (shouldShowSuffixesInPopup()) {
            candidates.addAll(getSuffixForKeyO());
        }

        return candidates;
    }

    private List<PopupKeyCandidate> getSuffixForKeyO() {
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

    private List<PopupKeyCandidate> getCandidatesForU() {
        List<PopupKeyCandidate> candidates = new ArrayList<>();

        if (mIsShowingPunctuation) {
            candidates.add(new PopupKeyCandidate(MongolCode.Uni.MONGOLIAN_ELLIPSIS));
            candidates.add(new PopupKeyCandidate(MongolCode.Uni.REFERENCE_MARK));
            candidates.add(new PopupKeyCandidate(MongolCode.Uni.MONGOLIAN_FOUR_DOTS));
            return candidates;
        }

        if (isIsolateOrInitial()) {
            candidates.add(new PopupKeyCandidate(MongolCode.Uni.OE));
            return candidates;
        }

        PopupKeyCandidate medial_UE_FVS1 = new PopupKeyCandidate(
                "" + MongolCode.Uni.UE + MongolCode.Uni.FVS1,
                "" + MongolCode.Uni.ZWJ + MongolCode.Uni.UE + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ,
                "" + MongolCode.Uni.UE + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ);
        candidates.add(medial_UE_FVS1);

        PopupKeyCandidate medial_UE_FVS2 = new PopupKeyCandidate(
                "" + MongolCode.Uni.UE + MongolCode.Uni.FVS2,
                "" + MongolCode.Uni.ZWJ + MongolCode.Uni.UE + MongolCode.Uni.FVS2 + MongolCode.Uni.ZWJ,
                "" + MongolCode.Uni.UE + MongolCode.Uni.FVS2 + MongolCode.Uni.ZWJ);
        candidates.add(medial_UE_FVS2);

        PopupKeyCandidate final_UE_FVS1 = new PopupKeyCandidate(
                "" + MongolCode.Uni.UE + MongolCode.Uni.FVS1,
                "" + MongolCode.Uni.ZWJ + MongolCode.Uni.UE + MongolCode.Uni.FVS1);
        candidates.add(final_UE_FVS1);

        return candidates;
    }

    private List<PopupKeyCandidate> getCandidatesForNA() {
        List<PopupKeyCandidate> candidates = new ArrayList<>();

        if (mIsShowingPunctuation) {
            candidates.add(new PopupKeyCandidate("①"));
            candidates.add(new PopupKeyCandidate("⑪"));
            candidates.add(new PopupKeyCandidate(MongolCode.Uni.MONGOLIAN_DIGIT_ONE));
            return candidates;
        }

        candidates.add(new PopupKeyCandidate(MongolCode.Uni.ANG));

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

        if (shouldShowSuffixesInPopup()) {
            candidates.addAll(getSuffixForKeyNA());
        }

        return candidates;
    }

    private List<PopupKeyCandidate> getSuffixForKeyNA() {
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

    private List<PopupKeyCandidate> getCandidatesForBA() {
        List<PopupKeyCandidate> candidates = new ArrayList<>();

        if (mIsShowingPunctuation) {
            candidates.add(new PopupKeyCandidate("②"));
            candidates.add(new PopupKeyCandidate("⑫"));
            candidates.add(new PopupKeyCandidate(MongolCode.Uni.MONGOLIAN_DIGIT_TWO));
            return candidates;
        }

        candidates.add(new PopupKeyCandidate(MongolCode.Uni.PA));
        candidates.add(new PopupKeyCandidate(MongolCode.Uni.FA));

        if (shouldShowSuffixesInPopup()) {
            candidates.addAll(getSuffixForKeyBA());
        }

        return candidates;
    }

    private List<PopupKeyCandidate> getSuffixForKeyBA() {
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

    private List<PopupKeyCandidate> getCandidatesForQA() {
        List<PopupKeyCandidate> candidates = new ArrayList<>();
        if (mIsShowingPunctuation) {
            candidates.add(new PopupKeyCandidate("③"));
            candidates.add(new PopupKeyCandidate("⑬"));
            candidates.add(new PopupKeyCandidate(MongolCode.Uni.MONGOLIAN_DIGIT_THREE));
            return candidates;
        }
        candidates.add(new PopupKeyCandidate(MongolCode.Uni.HAA));
        return candidates;
    }

    private List<PopupKeyCandidate> getCandidatesForGA() {
        List<PopupKeyCandidate> candidates = new ArrayList<>();
        if (mIsShowingPunctuation) {
            candidates.add(new PopupKeyCandidate("④"));
            candidates.add(new PopupKeyCandidate("⑭"));
            candidates.add(new PopupKeyCandidate(MongolCode.Uni.MONGOLIAN_DIGIT_FOUR));
            return candidates;
        }

        candidates.add(new PopupKeyCandidate(MongolCode.Uni.KA));

        if (isIsolateOrInitial()) {
            return candidates;
        }

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

        PopupKeyCandidate medi_ga_fvs3 = new PopupKeyCandidate(
                "" + MongolCode.Uni.GA + MongolCode.Uni.FVS3,
                "" + MongolCode.Uni.ZWJ + MongolCode.Uni.GA + MongolCode.Uni.FVS3 + MongolCode.Uni.ZWJ,
                "" + MongolCode.Uni.GA + MongolCode.Uni.FVS3 + MongolCode.Uni.ZWJ);
        candidates.add(medi_ga_fvs3);

        return candidates;
    }

    private List<PopupKeyCandidate> getCandidatesForMA() {
        List<PopupKeyCandidate> candidates = new ArrayList<>();
        if (mIsShowingPunctuation) {
            candidates.add(new PopupKeyCandidate("⑤"));
            candidates.add(new PopupKeyCandidate("⑮"));
            candidates.add(new PopupKeyCandidate(MongolCode.Uni.MONGOLIAN_DIGIT_FIVE));
            return candidates;
        }
        return candidates;
    }

    private List<PopupKeyCandidate> getCandidatesForLA() {
        List<PopupKeyCandidate> candidates = new ArrayList<>();
        if (mIsShowingPunctuation) {
            candidates.add(new PopupKeyCandidate(MongolCode.Uni.MONGOLIAN_BIRGA));
            return candidates;
        }
        candidates.add(new PopupKeyCandidate(MongolCode.Uni.LHA));
        return candidates;
    }

    private List<PopupKeyCandidate> getCandidatesForSA() {
        List<PopupKeyCandidate> candidates = new ArrayList<>();
        if (mIsShowingPunctuation) {
            candidates.add(new PopupKeyCandidate("⑥"));
            candidates.add(new PopupKeyCandidate("⑯"));
            candidates.add(new PopupKeyCandidate(MongolCode.Uni.MONGOLIAN_DIGIT_SIX));
            return candidates;
        }

        candidates.add(new PopupKeyCandidate(MongolCode.Uni.SHA));
        return candidates;
    }

    private List<PopupKeyCandidate> getCandidatesForTADA() {
        List<PopupKeyCandidate> candidates = new ArrayList<>();
        if (mIsShowingPunctuation) {
            candidates.add(new PopupKeyCandidate("⑦"));
            candidates.add(new PopupKeyCandidate("⑰"));
            candidates.add(new PopupKeyCandidate(MongolCode.Uni.MONGOLIAN_DIGIT_SEVEN));
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
            PopupKeyCandidate medial_ta_fvs1 = new PopupKeyCandidate(
                    "" + MongolCode.Uni.TA + MongolCode.Uni.FVS1,
                    "" + MongolCode.Uni.MONGOLIAN_NIRUGU +
                            MongolCode.Uni.TA + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ,
                    "" + MongolCode.Uni.TA + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ);
            candidates.add(medial_ta_fvs1);

            PopupKeyCandidate final_ta = new PopupKeyCandidate(
                    "" + MongolCode.Uni.TA,
                    "" + MongolCode.Uni.ZWJ + MongolCode.Uni.TA);
            candidates.add(final_ta);

            PopupKeyCandidate final_da_fvs1 = new PopupKeyCandidate(
                    "" + MongolCode.Uni.DA + MongolCode.Uni.FVS1,
                    "" + MongolCode.Uni.ZWJ + MongolCode.Uni.DA + MongolCode.Uni.FVS1);
            candidates.add(final_da_fvs1);
        }

        if (shouldShowSuffixesInPopup()) {
            candidates.addAll(getSuffixForKeyTADA());
        }

        return candidates;
    }

    private List<PopupKeyCandidate> getSuffixForKeyTADA() {
        List<PopupKeyCandidate> suffixes = new ArrayList<>();
        if (mKeyboardListener == null) return suffixes;

        String previousWord = mKeyboardListener.getPreviousMongolWord(true);
        MongolCode.Gender gender = MongolCode.getWordGender(previousWord);

        // there were too many choices when there is no context
        // so I'm removing the ones that can be entered without
        // any special formatting characters
        if (gender == null) {
            //suffixes.add(new PopupKeyCandidate(MongolCode.Suffix.TU));
            suffixes.add(new PopupKeyCandidate(MongolCode.Suffix.DU));
            //suffixes.add(new PopupKeyCandidate(MongolCode.Suffix.TAI));
            //suffixes.add(new PopupKeyCandidate(MongolCode.Suffix.TAGAN));
            suffixes.add(new PopupKeyCandidate(MongolCode.Suffix.DAGAN));
            //suffixes.add(new PopupKeyCandidate(MongolCode.Suffix.TEGEN));
            suffixes.add(new PopupKeyCandidate(MongolCode.Suffix.DEGEN));
            suffixes.add(new PopupKeyCandidate(MongolCode.Suffix.DAQI));
            //suffixes.add(new PopupKeyCandidate(MongolCode.Suffix.TAQI));
            return suffixes;
        }

        char lastChar = previousWord.charAt(previousWord.length() - 1);
        String tuDu = MongolCode.getSuffixTuDu(gender, lastChar);
        suffixes.add(new PopupKeyCandidate(tuDu));

        String taiTei = MongolCode.getSuffixTaiTei(gender);
        suffixes.add(new PopupKeyCandidate(taiTei));

        String taganDagan = MongolCode.getSuffixTaganDagan(gender, lastChar);
        suffixes.add(new PopupKeyCandidate(taganDagan));

        String taqiDaqi = MongolCode.getSuffixTaqiDaqi(gender, lastChar);
        suffixes.add(new PopupKeyCandidate(taqiDaqi));

        return suffixes;
    }

    private List<PopupKeyCandidate> getCandidatesForCHA() {
        List<PopupKeyCandidate> candidates = new ArrayList<>();
        if (mIsShowingPunctuation) {
            candidates.add(new PopupKeyCandidate("⑧"));
            candidates.add(new PopupKeyCandidate("⑱"));
            candidates.add(new PopupKeyCandidate(MongolCode.Uni.MONGOLIAN_DIGIT_EIGHT));
            return candidates;
        }

        candidates.add(new PopupKeyCandidate(MongolCode.Uni.ZA));
        candidates.add(new PopupKeyCandidate(MongolCode.Uni.TSA));
        candidates.add(new PopupKeyCandidate(MongolCode.Uni.CHI));

        return candidates;
    }

    private List<PopupKeyCandidate> getCandidatesForJA() {
        List<PopupKeyCandidate> candidates = new ArrayList<>();
        if (mIsShowingPunctuation) {
            candidates.add(new PopupKeyCandidate("⑨"));
            candidates.add(new PopupKeyCandidate("⑲"));
            candidates.add(new PopupKeyCandidate(MongolCode.Uni.MONGOLIAN_DIGIT_NINE));
            return candidates;
        }

        candidates.add(new PopupKeyCandidate(MongolCode.Uni.ZHI));
        return candidates;
    }

    private List<PopupKeyCandidate> getCandidatesForYA() {
        List<PopupKeyCandidate> candidates = new ArrayList<>();
        if (mIsShowingPunctuation) {
            candidates.add(new PopupKeyCandidate("⓪"));
            candidates.add(new PopupKeyCandidate("⑩"));
            candidates.add(new PopupKeyCandidate("⑳"));
            candidates.add(new PopupKeyCandidate(MongolCode.Uni.MONGOLIAN_DIGIT_ZERO));
            return candidates;
        }

        candidates.add(new PopupKeyCandidate(MongolCode.Uni.WA));

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

    private List<PopupKeyCandidate> getCandidatesForRA() {
        List<PopupKeyCandidate> candidates = new ArrayList<>();
        if (mIsShowingPunctuation) {
            candidates.add(new PopupKeyCandidate(MongolCode.Uni.DOUBLE_QUESTION_MARK));
            candidates.add(new PopupKeyCandidate(MongolCode.Uni.EXCLAMATION_QUESTION_MARK));
            candidates.add(new PopupKeyCandidate(MongolCode.Uni.DOUBLE_EXCLAMATION_MARK));
            return candidates;
        }
        candidates.add(new PopupKeyCandidate(MongolCode.Uni.ZRA));
        return candidates;
    }

    private List<PopupKeyCandidate> getCandidatesForComma() {
        List<PopupKeyCandidate> candidates = new ArrayList<>();
        if (mIsShowingPunctuation) {
            candidates.add(new PopupKeyCandidate("-", false));
            candidates.add(new PopupKeyCandidate("+", false));
            candidates.add(new PopupKeyCandidate("=", false));
            return candidates;
        }
        candidates.add(new PopupKeyCandidate(MongolCode.Uni.VERTICAL_QUESTION_MARK));
        candidates.add(new PopupKeyCandidate(MongolCode.Uni.MONGOLIAN_FULL_STOP));
        candidates.add(new PopupKeyCandidate(MongolCode.Uni.VERTICAL_EXCLAMATION_MARK));
        return candidates;
    }

    private List<PopupKeyCandidate> getCandidatesForSpace() {
        List<PopupKeyCandidate> candidates = new ArrayList<>();
        PopupKeyCandidate nnbs;
        if (hasCandidatesView()) {
            nnbs = new PopupKeyCandidate(
                    "" + MongolCode.Uni.NNBS,
                    KEY_SPACE_SUB_DISPLAY);
        } else {
            nnbs = new PopupKeyCandidate(
                    "" + MongolCode.Uni.NNBS,
                    KEY_SPACE_SUB_DISPLAY,
                    " ");
        }
        candidates.add(nnbs);
        return candidates;
    }

    @Override
    public String getDisplayName() {
        if (mDisplayName == null)
            return DEFAULT_DISPLAY_NAME;
        return mDisplayName;
    }

    @Override
    public void onKeyboardKeyClick() {
        mIsShowingPunctuation = !mIsShowingPunctuation;
        if (mIsShowingPunctuation) {
            setPunctuationKeyValues();
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
