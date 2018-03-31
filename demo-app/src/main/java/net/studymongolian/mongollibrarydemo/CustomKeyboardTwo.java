package net.studymongolian.mongollibrarydemo;

import android.content.Context;
import android.util.AttributeSet;

import net.studymongolian.mongollibrary.Key;
import net.studymongolian.mongollibrary.KeyBackspace;
import net.studymongolian.mongollibrary.KeyImage;
import net.studymongolian.mongollibrary.KeyKeyboardChooser;
import net.studymongolian.mongollibrary.KeyText;
import net.studymongolian.mongollibrary.Keyboard;
import net.studymongolian.mongollibrary.PopupKeyCandidate;

import java.util.ArrayList;
import java.util.List;

public class CustomKeyboardTwo extends Keyboard {

    // name to use in the keyboard popup chooser
    private static final String DISPLAY_NAME = "ipa";

    // Row 1
    protected KeyText mKeyA;
    protected KeyText mKeyE;
    protected KeyText mKeyI;
    protected KeyText mKeyO;
    protected KeyText mKeyU;
    protected KeyText mKeyOE;
    protected KeyText mKeyUE;

    // Row 2
    protected KeyText mKeyNA;
    protected KeyText mKeyBA;
    protected KeyText mKeyQA;
    protected KeyText mKeyGA;
    protected KeyText mKeyMA;
    protected KeyText mKeyLA;

    // Row 3
    protected KeyText mKeySA;
    protected KeyText mKeyTA;
    protected KeyText mKeyDA;
    protected KeyText mKeyCHA;
    protected KeyText mKeyJA;
    protected KeyText mKeyYA;
    protected KeyText mKeyRA;

    // Row 4
    protected KeyKeyboardChooser mKeyKeyboard;
    protected KeyText mKeyColon;
    protected KeyText mKeySpace;
    protected KeyBackspace mKeyBackspace;
    protected KeyImage mKeyReturn;

    private static final String NEWLINE = "\n";

    public CustomKeyboardTwo(Context context) {
        super(context);
        init(context);
    }

    public CustomKeyboardTwo(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomKeyboardTwo(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    protected void init(Context context) {

        // | A | E | I | O | U | OE| UE|    Row 1
        // | N  | B | Q  | G | M  |  L |    Row 2
        // | S | T | D | Ch| J | Y | R |    Row 3
        // | kb | : | space | ret | del|    Row 4

        // actual layout work is done by Keyboard superclass's onLayout
        mNumberOfKeysInRow = new int[]{7, 6, 7, 5};
        // the key weights for each row should sum to 1
        mKeyWeights = new float[]{
                1/7f, 1/7f, 1/7f, 1/7f, 1/7f, 1/7f, 1/7f,         // row 0
                1/6f, 1/6f, 1/6f, 1/6f, 1/6f, 1/6f,               // row 1
                1/7f, 1/7f, 1/7f, 1/7f, 1/7f, 1/7f, 1/7f,         // row 2
                1/6f, 1/6f, 2/6f, 1/6f, 1/6f};                    // row 3

        instantiateKeys(context);
        dontRotatePrimaryText();
        dontRotateSubText();
        setKeyValues();
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
        mKeyOE = new KeyText(context);
        mKeyUE = new KeyText(context);

        // Row 2
        mKeyNA = new KeyText(context);
        mKeyBA = new KeyText(context);
        mKeyQA = new KeyText(context);
        mKeyGA = new KeyText(context);
        mKeyMA = new KeyText(context);
        mKeyLA = new KeyText(context);

        // Row 3
        mKeySA = new KeyText(context);
        mKeyTA = new KeyText(context);
        mKeyDA = new KeyText(context);
        mKeyCHA = new KeyText(context);
        mKeyJA = new KeyText(context);
        mKeyYA = new KeyText(context);
        mKeyRA = new KeyText(context);

        // Row 4
        mKeyKeyboard = new KeyKeyboardChooser(context);
        mKeyColon = new KeyText(context);
        mKeySpace = new KeyText(context);
        mKeyReturn = new KeyImage(context);
        mKeyBackspace = new KeyBackspace(context);
    }


    private void setKeyValues() {

        // Row 1

        mKeyA.setText("ɑ");
        mKeyA.setSubText("æ");

        mKeyE.setText("ə");
        mKeyE.setSubText("ə̌");

        mKeyI.setText("i");
        mKeyI.setSubText("");

        mKeyO.setText("ɔ");
        mKeyO.setSubText("œ");

        mKeyU.setText("ʊ");
        mKeyU.setSubText("");

        mKeyOE.setText("o");
        mKeyOE.setSubText("");

        mKeyUE.setText("u");
        mKeyUE.setSubText("");

        // Row 2

        mKeyNA.setText("n");
        mKeyNA.setSubText("ŋ");

        mKeyBA.setText("b");
        mKeyBA.setSubText("p");

        mKeyQA.setText("x");
        mKeyQA.setSubText("");

        mKeyGA.setText("g");
        mKeyGA.setSubText("k");

        mKeyMA.setText("m");
        mKeyMA.setSubText("");

        mKeyLA.setText("l");
        mKeyLA.setSubText("");

        // Row 3

        mKeySA.setText("s");
        mKeySA.setSubText("ʃ");

        mKeyTA.setText("t");
        mKeyTA.setSubText("");

        mKeyDA.setText("d");
        mKeyDA.setSubText("");

        mKeyCHA.setText("ʧ");
        mKeyCHA.setSubText("ʦ");

        mKeyJA.setText("ʤ");
        mKeyJA.setSubText("ʣ");

        mKeyYA.setText("j");
        mKeyYA.setSubText("w");

        mKeyRA.setText("r");
        mKeyRA.setSubText("");

        // row 4

        mKeyColon.setText("ː");
        mKeyColon.setSubText("");

        mKeySpace.setText(" ");
        mKeySpace.setSubText("");

        mKeyReturn.setText(NEWLINE);
    }

    private void dontRotatePrimaryText() {
        // Row 1
        mKeyA.setIsRotatedPrimaryText(false);
        mKeyE.setIsRotatedPrimaryText(false);
        mKeyI.setIsRotatedPrimaryText(false);
        mKeyO.setIsRotatedPrimaryText(false);
        mKeyU.setIsRotatedPrimaryText(false);
        mKeyOE.setIsRotatedPrimaryText(false);
        mKeyUE.setIsRotatedPrimaryText(false);

        // Row 2
        mKeyNA.setIsRotatedPrimaryText(false);
        mKeyBA.setIsRotatedPrimaryText(false);
        mKeyQA.setIsRotatedPrimaryText(false);
        mKeyGA.setIsRotatedPrimaryText(false);
        mKeyMA.setIsRotatedPrimaryText(false);
        mKeyLA.setIsRotatedPrimaryText(false);

        // Row 3
        mKeySA.setIsRotatedPrimaryText(false);
        mKeyTA.setIsRotatedPrimaryText(false);
        mKeyDA.setIsRotatedPrimaryText(false);
        mKeyCHA.setIsRotatedPrimaryText(false);
        mKeyJA.setIsRotatedPrimaryText(false);
        mKeyYA.setIsRotatedPrimaryText(false);
        mKeyRA.setIsRotatedPrimaryText(false);

        // Row 4
        mKeyColon.setIsRotatedPrimaryText(false);
    }

    private void dontRotateSubText() {
        // Row 1
        mKeyA.setIsRotatedSubText(false);
        mKeyE.setIsRotatedSubText(false);
        mKeyI.setIsRotatedSubText(false);
        mKeyO.setIsRotatedSubText(false);
        mKeyU.setIsRotatedSubText(false);
        mKeyOE.setIsRotatedSubText(false);
        mKeyUE.setIsRotatedSubText(false);

        // Row 2
        mKeyNA.setIsRotatedSubText(false);
        mKeyBA.setIsRotatedSubText(false);
        mKeyQA.setIsRotatedSubText(false);
        mKeyGA.setIsRotatedSubText(false);
        mKeyMA.setIsRotatedSubText(false);
        mKeyLA.setIsRotatedSubText(false);

        // Row 3
        mKeySA.setIsRotatedSubText(false);
        mKeyTA.setIsRotatedSubText(false);
        mKeyDA.setIsRotatedSubText(false);
        mKeyCHA.setIsRotatedSubText(false);
        mKeyJA.setIsRotatedSubText(false);
        mKeyYA.setIsRotatedSubText(false);
        mKeyRA.setIsRotatedSubText(false);

        // Row 4
        mKeyColon.setIsRotatedSubText(false);
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
        mKeyOE.setKeyListener(this);
        mKeyUE.setKeyListener(this);

        // Row 2
        mKeyNA.setKeyListener(this);
        mKeyBA.setKeyListener(this);
        mKeyQA.setKeyListener(this);
        mKeyGA.setKeyListener(this);
        mKeyMA.setKeyListener(this);
        mKeyLA.setKeyListener(this);

        // Row 3
        mKeySA.setKeyListener(this);
        mKeyTA.setKeyListener(this);
        mKeyDA.setKeyListener(this);
        mKeyCHA.setKeyListener(this);
        mKeyJA.setKeyListener(this);
        mKeyYA.setKeyListener(this);
        mKeyRA.setKeyListener(this);

        // Row 4
        mKeyKeyboard.setKeyListener(this);
        mKeyColon.setKeyListener(this);
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
        addView(mKeyOE);
        addView(mKeyUE);

        // Row 2
        addView(mKeyNA);
        addView(mKeyBA);
        addView(mKeyQA);
        addView(mKeyGA);
        addView(mKeyMA);
        addView(mKeyLA);

        // Row 3
        addView(mKeySA);
        addView(mKeyTA);
        addView(mKeyDA);
        addView(mKeyCHA);
        addView(mKeyJA);
        addView(mKeyYA);
        addView(mKeyRA);

        // Row 4
        addView(mKeyKeyboard);
        addView(mKeyColon);
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
        } else if (key == mKeyOE) {
            return getCandidatesForOE();
        } else if (key == mKeyUE) {
            return getCandidatesForUE();
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
        } else if (key == mKeyTA) {
            return getCandidatesForTA();
        } else if (key == mKeyDA) {
            return getCandidatesForDA();
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
        } else if (key == mKeyColon) {
            return getCandidatesForColon();
        } else if (key == mKeySpace) {
            return getCandidatesForSpace();
        }

        return null;
    }

    private List<PopupKeyCandidate> getCandidatesForA() {
        List<PopupKeyCandidate> candidates = new ArrayList<>();
        candidates.add(new PopupKeyCandidate("æ"));
        return candidates;
    }


    private List<PopupKeyCandidate> getCandidatesForE() {
        List<PopupKeyCandidate> candidates = new ArrayList<>();
        candidates.add(new PopupKeyCandidate("ə̌"));
        return candidates;
    }

    private List<PopupKeyCandidate> getCandidatesForI() {
        return null;
    }

    private List<PopupKeyCandidate> getCandidatesForO() {
        List<PopupKeyCandidate> candidates = new ArrayList<>();
        candidates.add(new PopupKeyCandidate("œ"));
        return candidates;
    }

    private List<PopupKeyCandidate> getCandidatesForU() {
        return null;
    }

    private List<PopupKeyCandidate> getCandidatesForOE() {
        List<PopupKeyCandidate> candidates = new ArrayList<>();
        candidates.add(new PopupKeyCandidate("ö"));
        candidates.add(new PopupKeyCandidate("ө"));
        return candidates;
    }

    private List<PopupKeyCandidate> getCandidatesForUE() {
        List<PopupKeyCandidate> candidates = new ArrayList<>();
        candidates.add(new PopupKeyCandidate("ü"));
        candidates.add(new PopupKeyCandidate("ʉ"));
        return candidates;
    }

    private List<PopupKeyCandidate> getCandidatesForNA() {
        List<PopupKeyCandidate> candidates = new ArrayList<>();
        candidates.add(new PopupKeyCandidate("ŋ"));
        return candidates;
    }

    private List<PopupKeyCandidate> getCandidatesForBA() {
        List<PopupKeyCandidate> candidates = new ArrayList<>();
        candidates.add(new PopupKeyCandidate("p"));
        candidates.add(new PopupKeyCandidate("f"));
        return candidates;
    }

    private List<PopupKeyCandidate> getCandidatesForQA() {
        List<PopupKeyCandidate> candidates = new ArrayList<>();
        candidates.add(new PopupKeyCandidate("h"));
        return candidates;
    }

    private List<PopupKeyCandidate> getCandidatesForGA() {
        List<PopupKeyCandidate> candidates = new ArrayList<>();
        candidates.add(new PopupKeyCandidate("k"));
        return candidates;
    }

    private List<PopupKeyCandidate> getCandidatesForMA() {
        return null;
    }

    private List<PopupKeyCandidate> getCandidatesForLA() {
        return null;
    }

    private List<PopupKeyCandidate> getCandidatesForSA() {
        List<PopupKeyCandidate> candidates = new ArrayList<>();
        candidates.add(new PopupKeyCandidate("ʃ"));
        return candidates;
    }

    private List<PopupKeyCandidate> getCandidatesForTA() {
        return null;
    }

    private List<PopupKeyCandidate> getCandidatesForDA() {
        return null;
    }

    private List<PopupKeyCandidate> getCandidatesForCHA() {
        List<PopupKeyCandidate> candidates = new ArrayList<>();
        candidates.add(new PopupKeyCandidate("ʦ"));
        return candidates;
    }

    private List<PopupKeyCandidate> getCandidatesForJA() {
        List<PopupKeyCandidate> candidates = new ArrayList<>();
        candidates.add(new PopupKeyCandidate("ʣ"));
        return candidates;
    }

    private List<PopupKeyCandidate> getCandidatesForYA() {
        List<PopupKeyCandidate> candidates = new ArrayList<>();
        candidates.add(new PopupKeyCandidate("w"));
        return candidates;
    }

    private List<PopupKeyCandidate> getCandidatesForRA() {
        return null;
    }

    private List<PopupKeyCandidate> getCandidatesForColon() {
        List<PopupKeyCandidate> candidates = new ArrayList<>();
        candidates.add(new PopupKeyCandidate(":"));
        candidates.add(new PopupKeyCandidate("."));
        candidates.add(new PopupKeyCandidate("?"));
        candidates.add(new PopupKeyCandidate(","));
        candidates.add(new PopupKeyCandidate("!"));
        return candidates;
    }

    private List<PopupKeyCandidate> getCandidatesForSpace() {
        return null;
    }

    @Override
    public String getDisplayName() {
        return DISPLAY_NAME;
    }

    @Override
    public void onKeyboardKeyClick() {
        // this keyboard doesn't have punctuation key values
    }

}
