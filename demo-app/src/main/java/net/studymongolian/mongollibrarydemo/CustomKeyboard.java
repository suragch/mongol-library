package net.studymongolian.mongollibrarydemo;

import android.content.Context;
import android.util.AttributeSet;

import net.studymongolian.mongollibrary.Key;
import net.studymongolian.mongollibrary.KeyBackspace;
import net.studymongolian.mongollibrary.KeyKeyboardChooser;
import net.studymongolian.mongollibrary.KeyText;
import net.studymongolian.mongollibrary.Keyboard;
import net.studymongolian.mongollibrary.MongolCode;
import net.studymongolian.mongollibrary.PopupKeyCandidate;

import java.util.ArrayList;
import java.util.List;

public class CustomKeyboard extends Keyboard {

    // name to use in the keyboard popup chooser
    private static final String DEFAULT_DISPLAY_NAME = "ᠮᠢᠨᠤ ᠳᠠᠷᠤᠭᠤᠯ";
    private static final int DEFAULT_HEIGHT_DP = 120;

    // Row 1
    protected KeyText mKey1;
    protected KeyText mKey2;
    protected KeyText mKey3;
    protected KeyText mKeyA;
    protected KeyText mKeyE;
    protected KeyText mKeyI;

    // Row 2
    protected KeyKeyboardChooser mKeyKeyboard;
    protected KeyText mKeySpace;
    protected KeyBackspace mKeyBackspace;

    public CustomKeyboard(Context context) {
        super(context);
        init(context);
    }

    public CustomKeyboard(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomKeyboard(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    protected void init(Context context) {

        // | 1 | 2 | 3 | A | E | I |    Row 1
        // |  kbd  | space |  del  |    Row 2

        // actual layout work is done by Keyboard superclass's onLayout
        mNumberOfKeysInRow = new int[]{6, 3};
        // the key weights for each row should sum to 1
        mKeyWeights = new float[]{
                1 / 6f, 1 / 6f, 1 / 6f, 1 / 6f, 1 / 6f, 1 / 6f,   // row 1
                1 / 3f, 1 / 3f, 1 / 3f};              // row 2


        // Make sure that the total keys added to this ViewGroup below equals
        // the mNumberOfKeysInRow and mKeyWeights array totals above.

        instantiateKeys(context);
        setKeyValues();
        dontRotatePrimaryText();
        setListeners();
        addKeysToKeyboard();
        applyThemeToKeys();
    }

    private void instantiateKeys(Context context) {

        // Row 1
        mKey1 = new KeyText(context);
        mKey2 = new KeyText(context);
        mKey3 = new KeyText(context);
        mKeyA = new KeyText(context);
        mKeyE = new KeyText(context);
        mKeyI = new KeyText(context);

        // Row 4
        mKeyKeyboard = new KeyKeyboardChooser(context);
        mKeySpace = new KeyText(context);
        mKeyBackspace = new KeyBackspace(context);
    }

    private void setKeyValues() {

        mKey1.setText("1");
        mKey2.setText("2");
        mKey3.setText("3");
        mKeyA.setText(MongolCode.Uni.A);
        mKeyE.setText(MongolCode.Uni.E);
        mKeyI.setText(MongolCode.Uni.I);

        mKeyBackspace.setImage(getBackspaceImage(), getPrimaryTextColor());
        mKeySpace.setText(" ");
        mKeyKeyboard.setImage(getKeyboardImage(), getPrimaryTextColor());
        mKeyKeyboard.setSubText("");
    }

    private void dontRotatePrimaryText() {
        mKey1.setIsRotatedPrimaryText(false);
        mKey2.setIsRotatedPrimaryText(false);
        mKey3.setIsRotatedPrimaryText(false);
    }


    private void setListeners() {

        // Row 1
        mKey1.setKeyListener(this);
        mKey2.setKeyListener(this);
        mKey3.setKeyListener(this);
        mKeyA.setKeyListener(this);
        mKeyE.setKeyListener(this);
        mKeyI.setKeyListener(this);

        // Row 2
        mKeyKeyboard.setKeyListener(this);
        mKeySpace.setKeyListener(this);
        mKeyBackspace.setKeyListener(this);
    }

    private void addKeysToKeyboard() {

        // Row 1
        addView(mKey1);
        addView(mKey2);
        addView(mKey3);
        addView(mKeyA);
        addView(mKeyE);
        addView(mKeyI);

        // Row 2
        addView(mKeyKeyboard);
        addView(mKeySpace);
        addView(mKeyBackspace);
    }

    public List<PopupKeyCandidate> getPopupCandidates(Key key) {

        if (key == mKeyKeyboard) {
            return getCandidatesForKeyboardKey();
        } else if (key == mKeyA) {
            return getCandidatesForA();
        } else if (key == mKeyE) {
            return getCandidatesForE();
        } else if (key == mKeyI) {
            return getCandidatesForI();
        }

        return null;
    }

    private List<PopupKeyCandidate> getCandidatesForA() {
        List<PopupKeyCandidate> candidates = new ArrayList<>();
        candidates.add(new PopupKeyCandidate("ᠰᠠᠶᠢᠨ"));
        candidates.add(new PopupKeyCandidate("ᠪᠠᠶᠢᠨ᠎ᠠ"));
        candidates.add(new PopupKeyCandidate(" ᠤᠤ"));
        return candidates;
    }


    private List<PopupKeyCandidate> getCandidatesForE() {
        List<PopupKeyCandidate> candidates = new ArrayList<>();
        candidates.add(new PopupKeyCandidate("ᠧ"));
        return candidates;
    }

    private List<PopupKeyCandidate> getCandidatesForI() {
        List<PopupKeyCandidate> candidates = new ArrayList<>();
        candidates.add(new PopupKeyCandidate(" ᠢ"));
        candidates.add(new PopupKeyCandidate(" ᠶᠢ"));
        candidates.add(new PopupKeyCandidate(" ᠶᠢᠨ"));
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
        requestNewKeyboard();
    }

    @Override
    public int getDefaultHeight() {
        return (int) (DEFAULT_HEIGHT_DP * getResources().getDisplayMetrics().density);
    }
}
