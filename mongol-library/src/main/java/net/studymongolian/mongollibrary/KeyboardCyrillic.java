package net.studymongolian.mongollibrary;

import android.content.Context;
import android.util.AttributeSet;

import java.util.List;

public class KeyboardCyrillic extends Keyboard {

    // name to use in the keyboard popup chooser
    private static final String DEFAULT_DISPLAY_NAME = "ᠺᠢᠷᠢᠯ";

    // Row 1
    protected KeyText mKey_f;
    protected KeyText mKey_ts;
    protected KeyText mKey_u;
    protected KeyText mKey_j;
    protected KeyText mKey_e;
    protected KeyText mKey_n;
    protected KeyText mKey_g;
    protected KeyText mKey_sh;
    protected KeyText mKey_u7;
    protected KeyText mKey_z;
    protected KeyText mKey_k;
    protected KeyText mKey_i_xatuu;

    // Row 2
    protected KeyText mKey_i_xagas;
    protected KeyText mKey_y;
    protected KeyText mKey_b;
    protected KeyText mKey_o6;
    protected KeyText mKey_a;
    protected KeyText mKey_kh;
    protected KeyText mKey_r;
    protected KeyText mKey_o;
    protected KeyText mKey_l;
    protected KeyText mKey_d;
    protected KeyText mKey_p;
    protected KeyText mKey_ye;

    // Row 3
    protected KeyShift mKeyShift;
    protected KeyText mKey_ya;
    protected KeyText mKey_ch;
    protected KeyText mKey_yo;
    protected KeyText mKey_s;
    protected KeyText mKey_m;
    protected KeyText mKey_i;
    protected KeyText mKey_t;
    protected KeyText mKey_i_joolen;
    protected KeyText mKey_v;
    protected KeyBackspace mKeyBackspace;

    // Row 4
    protected KeyKeyboardChooser mKeyKeyboard;
    protected KeyText mKeyComma;
    protected KeyText mKeySpace;
    protected KeyText mKeyPeriod;
    protected KeyText mKey_yu;
    protected KeyImage mKeyReturn;

    // Keys with different display values
    private static final String NEWLINE = "\n";

    // Use this constructor if you want the default style
    public KeyboardCyrillic(Context context) {
        super(context);
        init(context);
    }

    public KeyboardCyrillic(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public KeyboardCyrillic(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    protected void init(Context context) {

        // keyboard layout

        // | ф | ц | у | ж | э | н | г | ш | ү | з | к | ъ |  Row 1
        // | й | ы | б | ө | а | х | р | о | л | д | п | е |  Row 2
        // |shift| я | ч | ё | с | м | и | т | ь | в | del |  Row 3
        // |  kb   | , |     space      |  .  | ю |   ret  |  Row 4

        // actual layout work is done by Keyboard superclass's onLayout
        mNumberOfKeysInRow = new int[]{12, 12, 11, 6}; // 36 keys total
        // this is the percent to inset the row
        mInsetWeightInRow = new float[]{0, 0, 0, 0};
        // the key weights for each row should sum to 1 (unless there is an inset)
        float w = 1 / 12f;
        float w15 = (float) 1.5 * w;
        float w20 = (float) 2 * w;
        float w35 = (float) 3.5 * w;
        mKeyWeights = new float[]{
                w, w, w, w, w, w, w, w, w, w, w, w,     // row 0
                w, w, w, w, w, w, w, w, w, w, w, w,     // row 1
                w15, w, w, w, w, w, w, w, w, w, w15,    // row 2
                w20, w15, w35, w15, w15, w20};          // row 3

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
        mKey_f = new KeyText(context);
        mKey_ts = new KeyText(context);
        mKey_u = new KeyText(context);
        mKey_j = new KeyText(context);
        mKey_e = new KeyText(context);
        mKey_n = new KeyText(context);
        mKey_g = new KeyText(context);
        mKey_sh = new KeyText(context);
        mKey_u7 = new KeyText(context);
        mKey_z = new KeyText(context);
        mKey_k = new KeyText(context);
        mKey_i_xatuu = new KeyText(context);

        // Row 2
        mKey_i_xagas = new KeyText(context);
        mKey_y = new KeyText(context);
        mKey_b = new KeyText(context);
        mKey_o6 = new KeyText(context);
        mKey_a = new KeyText(context);
        mKey_kh = new KeyText(context);
        mKey_r = new KeyText(context);
        mKey_o = new KeyText(context);
        mKey_l = new KeyText(context);
        mKey_d = new KeyText(context);
        mKey_p = new KeyText(context);
        mKey_ye = new KeyText(context);

        // Row 3
        mKeyShift = new KeyShift(context);
        mKey_ya = new KeyText(context);
        mKey_ch = new KeyText(context);
        mKey_yo = new KeyText(context);
        mKey_s = new KeyText(context);
        mKey_m = new KeyText(context);
        mKey_i = new KeyText(context);
        mKey_t = new KeyText(context);
        mKey_i_joolen = new KeyText(context);
        mKey_v = new KeyText(context);
        mKeyBackspace = new KeyBackspace(context);

        // Row 4
        mKeyKeyboard = new KeyKeyboardChooser(context);
        mKeyComma = new KeyText(context);
        mKeySpace = new KeyText(context);
        mKeyPeriod = new KeyText(context);
        mKey_yu = new KeyText(context);
        mKeyReturn = new KeyImage(context);
    }

    private void makeKeysLowercase() {

        // Row 1

        mKey_f.setText("ф");
        mKey_f.setSubText("");

        mKey_ts.setText("ц");
        mKey_ts.setSubText("");

        mKey_u.setText("у");
        mKey_u.setSubText("");

        mKey_j.setText("ж");
        mKey_j.setSubText("");

        mKey_e.setText("э");
        mKey_e.setSubText("");

        mKey_n.setText("н");
        mKey_n.setSubText("");

        mKey_g.setText("г");
        mKey_g.setSubText("");

        mKey_sh.setText("ш");
        mKey_sh.setSubText("");

        mKey_u7.setText("ү");
        mKey_u7.setSubText("");

        mKey_z.setText("з");
        mKey_z.setSubText("");

        mKey_k.setText("к");
        mKey_k.setSubText("");

        mKey_i_xatuu.setText("ъ");
        mKey_i_xatuu.setSubText("");


        // Row 2

        mKey_i_xagas.setText("й");
        mKey_i_xagas.setSubText("");

        mKey_y.setText("ы");
        mKey_y.setSubText("");

        mKey_b.setText("б");
        mKey_b.setSubText("");

        mKey_o6.setText("ө");
        mKey_o6.setSubText("");

        mKey_a.setText("а");
        mKey_a.setSubText("");

        mKey_kh.setText("х");
        mKey_kh.setSubText("");

        mKey_r.setText("р");
        mKey_r.setSubText("");

        mKey_o.setText("о");
        mKey_o.setSubText("");

        mKey_l.setText("л");
        mKey_l.setSubText("");

        mKey_d.setText("д");
        mKey_d.setSubText("");

        mKey_p.setText("п");
        mKey_p.setSubText("");

        mKey_ye.setText("е");
        mKey_ye.setSubText("");


        // Row 3

        mKey_ya.setText("я");
        mKey_ya.setSubText("");

        mKey_ch.setText("ч");
        mKey_ch.setSubText("");

        mKey_yo.setText("ё");
        mKey_yo.setSubText("");

        mKey_s.setText("с");
        mKey_s.setSubText("");

        mKey_m.setText("м");
        mKey_m.setSubText("");

        mKey_i.setText("и");
        mKey_i.setSubText("");

        mKey_t.setText("т");
        mKey_t.setSubText("");

        mKey_i_joolen.setText("ь");
        mKey_i_joolen.setSubText("");

        mKey_v.setText("в");
        mKey_v.setSubText("");

        // Row 4

        mKey_yu.setText("ю");
        mKey_yu.setSubText("");
    }

    private void makeKeysUppercase() {

        // Row 1

        mKey_f.setText("Ф");
        mKey_f.setSubText("");

        mKey_ts.setText("Ц");
        mKey_ts.setSubText("");

        mKey_u.setText("У");
        mKey_u.setSubText("");

        mKey_j.setText("Ж");
        mKey_j.setSubText("");

        mKey_e.setText("Э");
        mKey_e.setSubText("");

        mKey_n.setText("Н");
        mKey_n.setSubText("");

        mKey_g.setText("Г");
        mKey_g.setSubText("");

        mKey_sh.setText("Ш");
        mKey_sh.setSubText("");

        mKey_u7.setText("Ү");
        mKey_u7.setSubText("");

        mKey_z.setText("З");
        mKey_z.setSubText("");

        mKey_k.setText("К");
        mKey_k.setSubText("");

        mKey_i_xatuu.setText("Ъ");
        mKey_i_xatuu.setSubText("");


        // Row 2

        mKey_i_xagas.setText("Й");
        mKey_i_xagas.setSubText("");

        mKey_y.setText("Ы");
        mKey_y.setSubText("");

        mKey_b.setText("Б");
        mKey_b.setSubText("");

        mKey_o6.setText("Ө");
        mKey_o6.setSubText("");

        mKey_a.setText("А");
        mKey_a.setSubText("");

        mKey_kh.setText("Х");
        mKey_kh.setSubText("");

        mKey_r.setText("Р");
        mKey_r.setSubText("");

        mKey_o.setText("О");
        mKey_o.setSubText("");

        mKey_l.setText("Л");
        mKey_l.setSubText("");

        mKey_d.setText("Д");
        mKey_d.setSubText("");

        mKey_p.setText("П");
        mKey_p.setSubText("");

        mKey_ye.setText("Е");
        mKey_ye.setSubText("");


        // Row 3

        mKey_ya.setText("Я");
        mKey_ya.setSubText("");

        mKey_ch.setText("Ч");
        mKey_ch.setSubText("");

        mKey_yo.setText("Ё");
        mKey_yo.setSubText("");

        mKey_s.setText("С");
        mKey_s.setSubText("");

        mKey_m.setText("М");
        mKey_m.setSubText("");

        mKey_i.setText("И");
        mKey_i.setSubText("");

        mKey_t.setText("Т");
        mKey_t.setSubText("");

        mKey_i_joolen.setText("Ь");
        mKey_i_joolen.setSubText("");

        mKey_v.setText("В");
        mKey_v.setSubText("");

        // Row 4

        mKey_yu.setText("Ю");
        mKey_yu.setSubText("");
    }


    private void setPunctuationKeyValues() {

        // Row 1

        mKey_f.setText("1");
        mKey_f.setSubText("");

        mKey_ts.setText("2");
        mKey_ts.setSubText("");

        mKey_u.setText("3");
        mKey_u.setSubText("");

        mKey_j.setText("4");
        mKey_j.setSubText("");

        mKey_e.setText("5");
        mKey_e.setSubText("");

        mKey_n.setText("6");
        mKey_n.setSubText("");

        mKey_g.setText("7");
        mKey_g.setSubText("");

        mKey_sh.setText("8");
        mKey_sh.setSubText("");

        mKey_u7.setText("9");
        mKey_u7.setSubText("");

        mKey_z.setText("0");
        mKey_z.setSubText("");

        mKey_k.setText("-");
        mKey_k.setSubText("");

        mKey_i_xatuu.setText("=");
        mKey_i_xatuu.setSubText("");


        // Row 2

        mKey_i_xagas.setText("!");
        mKey_i_xagas.setSubText("");

        mKey_y.setText("@");
        mKey_y.setSubText("");

        mKey_b.setText("#");
        mKey_b.setSubText("");

        mKey_o6.setText("₮");
        mKey_o6.setSubText("");

        mKey_a.setText("%");
        mKey_a.setSubText("");

        mKey_kh.setText("^");
        mKey_kh.setSubText("");

        mKey_r.setText("&");
        mKey_r.setSubText("");

        mKey_o.setText("*");
        mKey_o.setSubText("");

        mKey_l.setText("(");
        mKey_l.setSubText("");

        mKey_d.setText(")");
        mKey_d.setSubText("");

        mKey_p.setText("_");
        mKey_p.setSubText("");

        mKey_ye.setText("+");
        mKey_ye.setSubText("");


        // Row 3

        mKey_ya.setText("[");
        mKey_ya.setSubText("");

        mKey_ch.setText("]");
        mKey_ch.setSubText("");

        mKey_yo.setText("{");
        mKey_yo.setSubText("");

        mKey_s.setText("}");
        mKey_s.setSubText("");

        mKey_m.setText(";");
        mKey_m.setSubText("");

        mKey_i.setText(":");
        mKey_i.setSubText("");

        mKey_t.setText("'");
        mKey_t.setSubText("");

        mKey_i_joolen.setText("\"");
        mKey_i_joolen.setSubText("");

        mKey_v.setText("/");
        mKey_v.setSubText("");

        // Row 4

        mKey_yu.setText("?");
        mKey_yu.setSubText("");
    }

    private void setNonChangingKeyValues() {
        mKeyComma.setText(",");
        mKeySpace.setText(" ");
        mKeyPeriod.setText(".");
        mKeyReturn.setText(NEWLINE);
    }

    private void dontRotatePrimaryTextForSelectKeys() {

        // Row 1
        mKey_f.setIsRotatedPrimaryText(false);
        mKey_ts.setIsRotatedPrimaryText(false);
        mKey_u.setIsRotatedPrimaryText(false);
        mKey_j.setIsRotatedPrimaryText(false);
        mKey_e.setIsRotatedPrimaryText(false);
        mKey_n.setIsRotatedPrimaryText(false);
        mKey_g.setIsRotatedPrimaryText(false);
        mKey_sh.setIsRotatedPrimaryText(false);
        mKey_u7.setIsRotatedPrimaryText(false);
        mKey_z.setIsRotatedPrimaryText(false);
        mKey_k.setIsRotatedPrimaryText(false);
        mKey_i_xatuu.setIsRotatedPrimaryText(false);

        // Row 2
        mKey_i_xagas.setIsRotatedPrimaryText(false);
        mKey_y.setIsRotatedPrimaryText(false);
        mKey_b.setIsRotatedPrimaryText(false);
        mKey_o6.setIsRotatedPrimaryText(false);
        mKey_a.setIsRotatedPrimaryText(false);
        mKey_kh.setIsRotatedPrimaryText(false);
        mKey_r.setIsRotatedPrimaryText(false);
        mKey_o.setIsRotatedPrimaryText(false);
        mKey_l.setIsRotatedPrimaryText(false);
        mKey_d.setIsRotatedPrimaryText(false);
        mKey_p.setIsRotatedPrimaryText(false);
        mKey_ye.setIsRotatedPrimaryText(false);

        // Row 3
        mKey_ya.setIsRotatedPrimaryText(false);
        mKey_ch.setIsRotatedPrimaryText(false);
        mKey_yo.setIsRotatedPrimaryText(false);
        mKey_s.setIsRotatedPrimaryText(false);
        mKey_m.setIsRotatedPrimaryText(false);
        mKey_i.setIsRotatedPrimaryText(false);
        mKey_t.setIsRotatedPrimaryText(false);
        mKey_i_joolen.setIsRotatedPrimaryText(false);
        mKey_v.setIsRotatedPrimaryText(false);

        // Row 4
        mKeyComma.setIsRotatedPrimaryText(false);
        mKeySpace.setIsRotatedPrimaryText(false);
        mKeyPeriod.setIsRotatedPrimaryText(false);
        mKey_yu.setIsRotatedPrimaryText(false);
    }

    private void setKeyImages() {
        mKeyShift.setShiftImage(getPrimaryTextColor());
        mKeyBackspace.setImage(getBackspaceImage(), getPrimaryTextColor());
        mKeyKeyboard.setImage(getKeyboardImage(), getPrimaryTextColor());
        mKeyReturn.setImage(getReturnImage(), getPrimaryTextColor());
    }

    private void setListeners() {

        // Row 1
        mKey_f.setKeyListener(this);
        mKey_ts.setKeyListener(this);
        mKey_u.setKeyListener(this);
        mKey_j.setKeyListener(this);
        mKey_e.setKeyListener(this);
        mKey_n.setKeyListener(this);
        mKey_g.setKeyListener(this);
        mKey_sh.setKeyListener(this);
        mKey_u7.setKeyListener(this);
        mKey_z.setKeyListener(this);
        mKey_k.setKeyListener(this);
        mKey_i_xatuu.setKeyListener(this);

        // Row 2
        mKey_i_xagas.setKeyListener(this);
        mKey_y.setKeyListener(this);
        mKey_b.setKeyListener(this);
        mKey_o6.setKeyListener(this);
        mKey_a.setKeyListener(this);
        mKey_kh.setKeyListener(this);
        mKey_r.setKeyListener(this);
        mKey_o.setKeyListener(this);
        mKey_l.setKeyListener(this);
        mKey_d.setKeyListener(this);
        mKey_p.setKeyListener(this);
        mKey_ye.setKeyListener(this);

        // Row 3
        mKeyShift.setKeyListener(this);
        mKey_ya.setKeyListener(this);
        mKey_ch.setKeyListener(this);
        mKey_yo.setKeyListener(this);
        mKey_s.setKeyListener(this);
        mKey_m.setKeyListener(this);
        mKey_i.setKeyListener(this);
        mKey_t.setKeyListener(this);
        mKey_i_joolen.setKeyListener(this);
        mKey_v.setKeyListener(this);
        mKeyBackspace.setKeyListener(this);

        // Row 4
        mKeyKeyboard.setKeyListener(this);
        mKeyComma.setKeyListener(this);
        mKeySpace.setKeyListener(this);
        mKeyPeriod.setKeyListener(this);
        mKey_yu.setKeyListener(this);
        mKeyReturn.setKeyListener(this);
    }

    private void addKeysToKeyboard() {

        // Row 1
        addView(mKey_f);
        addView(mKey_ts);
        addView(mKey_u);
        addView(mKey_j);
        addView(mKey_e);
        addView(mKey_n);
        addView(mKey_g);
        addView(mKey_sh);
        addView(mKey_u7);
        addView(mKey_z);
        addView(mKey_k);
        addView(mKey_i_xatuu);

        // Row 2
        addView(mKey_i_xagas);
        addView(mKey_y);
        addView(mKey_b);
        addView(mKey_o6);
        addView(mKey_a);
        addView(mKey_kh);
        addView(mKey_r);
        addView(mKey_o);
        addView(mKey_l);
        addView(mKey_d);
        addView(mKey_p);
        addView(mKey_ye);

        // Row 3
        addView(mKeyShift);
        addView(mKey_ya);
        addView(mKey_ch);
        addView(mKey_yo);
        addView(mKey_s);
        addView(mKey_m);
        addView(mKey_i);
        addView(mKey_t);
        addView(mKey_i_joolen);
        addView(mKey_v);
        addView(mKeyBackspace);

        // Row 4
        addView(mKeyKeyboard);
        addView(mKeyComma);
        addView(mKeySpace);
        addView(mKeyPeriod);
        addView(mKey_yu);
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
