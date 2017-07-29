package net.studymongolian.mongollibrary;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;


import java.util.HashMap;
import java.util.Map;

public class KeyboardAeiou extends Keyboard {

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
    protected KeyImage mKeyKeyboard;
    protected KeyText mKeyComma;
    protected KeyText mKeySpace;
    protected KeyImage mKeyBackspace;
    protected KeyImage mKeyReturn;

    private int mPopupBackgroundColor = Color.WHITE;
    private int mPopupHighlightColor = Color.GRAY;
    private static final char MONGOLIAN_DOT = '\u00b7';
    private static final char MONGOLIAN_DASH = '\ufe31';
    private static final char PUNCTUATION_QUESTION_EXCLAMATION = '\u2048';
    private static final char PUNCTUATION_EXCLAMATION_QUESTION = '\u2049';
    private static final char PUNCTUATION_EXCLAMATION_EXCLAMATION = '\u203c';
    private static final char PUNCTUATION_DOUBLEQUOTE_TOP = '\u00ab';
    private static final char PUNCTUATION_DOUBLEQUOTE_BOTTOM = '\u00bb';

    // These are all input values (some display values are different)
    private static final String KEY_A = String.valueOf(MongolCode.Uni.A);
    private static final String KEY_E = String.valueOf(MongolCode.Uni.E);
    private static final String KEY_I = String.valueOf(MongolCode.Uni.I);
    private static final String KEY_O = String.valueOf(MongolCode.Uni.U);
    private static final String KEY_U = String.valueOf(MongolCode.Uni.UE);
    private static final String KEY_NA = String.valueOf(MongolCode.Uni.NA);
    private static final String KEY_BA = String.valueOf(MongolCode.Uni.BA);
    private static final String KEY_QA = String.valueOf(MongolCode.Uni.QA);
    private static final String KEY_GA = String.valueOf(MongolCode.Uni.GA);
    private static final String KEY_MA = String.valueOf(MongolCode.Uni.MA);
    private static final String KEY_LA = String.valueOf(MongolCode.Uni.LA);
    private static final String KEY_SA = String.valueOf(MongolCode.Uni.SA);
    private static final String KEY_TA = String.valueOf(MongolCode.Uni.TA);
    private static final String KEY_CHA = String.valueOf(MongolCode.Uni.CHA);
    private static final String KEY_JA = String.valueOf(MongolCode.Uni.JA);
    private static final String KEY_YA = String.valueOf(MongolCode.Uni.YA);
    private static final String KEY_RA = String.valueOf(MongolCode.Uni.RA);

    private static final String KEY_A_SUB = "";
    private static final String KEY_E_SUB = String.valueOf(MongolCode.Uni.EE);
    private static final String KEY_I_SUB = "";
    private static final String KEY_O_SUB = "";
    private static final String KEY_U_SUB = "";
    private static final String KEY_NA_SUB = String.valueOf(MongolCode.Uni.ANG);
    private static final String KEY_BA_SUB = String.valueOf(MongolCode.Uni.PA);
    private static final String KEY_QA_SUB = String.valueOf(MongolCode.Uni.HAA);
    private static final String KEY_GA_SUB = String.valueOf(MongolCode.Uni.KA);
    private static final String KEY_MA_SUB = "";
    private static final String KEY_LA_SUB = String.valueOf(MongolCode.Uni.LHA);
    private static final String KEY_SA_SUB = String.valueOf(MongolCode.Uni.SHA);
    private static final String KEY_TA_SUB = String.valueOf(MongolCode.Uni.DA); // DA is default
    private static final String KEY_CHA_SUB = String.valueOf(MongolCode.Uni.TSA);
    private static final String KEY_JA_SUB = String.valueOf(MongolCode.Uni.ZA);
    private static final String KEY_YA_SUB = String.valueOf(MongolCode.Uni.WA);
    private static final String KEY_RA_SUB = String.valueOf(MongolCode.Uni.ZRA);

    private static final String KEY_A_PUNCT = "(";
    private static final String KEY_E_PUNCT = ")";
    private static final String KEY_I_PUNCT = String.valueOf(PUNCTUATION_DOUBLEQUOTE_TOP);
    private static final String KEY_O_PUNCT = String.valueOf(PUNCTUATION_DOUBLEQUOTE_BOTTOM);
    private static final String KEY_U_PUNCT = String.valueOf(MONGOLIAN_DOT);
    private static final String KEY_NA_PUNCT = "1";
    private static final String KEY_BA_PUNCT = "2";
    private static final String KEY_QA_PUNCT = "3";
    private static final String KEY_GA_PUNCT = "4";
    private static final String KEY_MA_PUNCT = "5";
    private static final String KEY_LA_PUNCT = String.valueOf(MONGOLIAN_DASH);
    private static final String KEY_SA_PUNCT = "6";
    private static final String KEY_TA_PUNCT = "7";
    private static final String KEY_CHA_PUNCT = "8";
    private static final String KEY_JA_PUNCT = "9";
    private static final String KEY_YA_PUNCT = "0";
    private static final String KEY_RA_PUNCT = String.valueOf(PUNCTUATION_QUESTION_EXCLAMATION);

    private static final String KEY_A_PUNCT_SUB = "[";
    private static final String KEY_E_PUNCT_SUB = "]";
    private static final String KEY_I_PUNCT_SUB = "<";
    private static final String KEY_O_PUNCT_SUB = ">";
    private static final String KEY_U_PUNCT_SUB = String.valueOf(MongolCode.Uni.MONGOLIAN_ELLIPSIS);
    private static final String KEY_NA_PUNCT_SUB = String.valueOf(MongolCode.Uni.MONGOLIAN_DIGIT_ONE);
    private static final String KEY_BA_PUNCT_SUB = String.valueOf(MongolCode.Uni.MONGOLIAN_DIGIT_TWO);
    private static final String KEY_QA_PUNCT_SUB = String.valueOf(MongolCode.Uni.MONGOLIAN_DIGIT_THREE);
    private static final String KEY_GA_PUNCT_SUB = String.valueOf(MongolCode.Uni.MONGOLIAN_DIGIT_FOUR);
    private static final String KEY_MA_PUNCT_SUB = String.valueOf(MongolCode.Uni.MONGOLIAN_DIGIT_FIVE);
    private static final String KEY_LA_PUNCT_SUB = String.valueOf(MongolCode.Uni.MONGOLIAN_BIRGA);
    private static final String KEY_SA_PUNCT_SUB = String.valueOf(MongolCode.Uni.MONGOLIAN_DIGIT_SIX);
    private static final String KEY_TA_PUNCT_SUB = String.valueOf(MongolCode.Uni.MONGOLIAN_DIGIT_SEVEN);
    private static final String KEY_CHA_PUNCT_SUB = String.valueOf(MongolCode.Uni.MONGOLIAN_DIGIT_EIGHT);
    private static final String KEY_JA_PUNCT_SUB = String.valueOf(MongolCode.Uni.MONGOLIAN_DIGIT_NINE);
    private static final String KEY_YA_PUNCT_SUB = String.valueOf(MongolCode.Uni.MONGOLIAN_DIGIT_ZERO);
    private static final String KEY_RA_PUNCT_SUB = String.valueOf(MongolCode.Uni.MONGOLIAN_FOUR_DOTS);

    // This will map the button resource id to the String value that we want to
    // input when that key is clicked.
    protected Map<Key, String> keyValues = new HashMap<>();
    protected Map<Key, String> keyPunctuationValues = new HashMap<>();


    private boolean mIsShowingPunctuation = false;






    public KeyboardAeiou(Context context) {
        this(context, null, 0);
    }

    public KeyboardAeiou(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public KeyboardAeiou(Context context,
                         AttributeSet attrs,
                         int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }


    private void init(Context context) {

        Typeface typeface = MongolFont.get(MongolFont.QAGAN, context);
        float textSize = 24;
        float subTextSize = textSize / 2;
        int textColor = Color.BLACK;
        int subTextColor = Color.parseColor("#61000000"); // alpha black
        int keyColor = Color.LTGRAY;
        int pressedColor = Color.GRAY;
        int borderColor = Color.BLACK;
        int borderWidth = 0;
        int borderRadius = 5;
        int padding = 2;


        // Row 1

        mKeyA = new KeyText(context);
        mKeyA.setOnTouchListener(textKeyTouchListener);
        keyValues.put(mKeyA, KEY_A);
        keyPunctuationValues.put(mKeyA, KEY_A_PUNCT);
        addView(mKeyA);

        mKeyE = new KeyText(context);
        mKeyE.setOnTouchListener(textKeyTouchListener);
        keyValues.put(mKeyE, KEY_E);
        keyPunctuationValues.put(mKeyE, KEY_E_PUNCT);
        addView(mKeyE);

        mKeyI = new KeyText(context);
        mKeyI.setOnTouchListener(textKeyTouchListener);
        keyValues.put(mKeyI, KEY_I);
        keyPunctuationValues.put(mKeyI, KEY_I_PUNCT);
        addView(mKeyI);

        mKeyO = new KeyText(context);
        mKeyO.setOnTouchListener(textKeyTouchListener);
        keyValues.put(mKeyO, KEY_O);
        keyPunctuationValues.put(mKeyO, KEY_O_PUNCT);
        addView(mKeyO);

        mKeyU = new KeyText(context);
        mKeyU.setOnTouchListener(textKeyTouchListener);
        keyValues.put(mKeyU, KEY_U);
        keyPunctuationValues.put(mKeyU, KEY_U_PUNCT);
        addView(mKeyU);

        // Row 2

        mKeyNA = new KeyText(context);
        mKeyNA.setOnTouchListener(textKeyTouchListener);
        keyValues.put(mKeyNA, KEY_NA);
        keyPunctuationValues.put(mKeyNA, KEY_NA_PUNCT);
        addView(mKeyNA);

        mKeyBA = new KeyText(context);
        mKeyBA.setOnTouchListener(textKeyTouchListener);
        keyValues.put(mKeyBA, KEY_BA);
        keyPunctuationValues.put(mKeyBA, KEY_BA_PUNCT);
        addView(mKeyBA);

        mKeyQA = new KeyText(context);
        mKeyQA.setOnTouchListener(textKeyTouchListener);
        keyValues.put(mKeyQA, KEY_QA);
        keyPunctuationValues.put(mKeyQA, KEY_QA_PUNCT);
        addView(mKeyQA);

        mKeyGA = new KeyText(context);
        mKeyGA.setOnTouchListener(textKeyTouchListener);
        keyValues.put(mKeyGA, KEY_GA);
        keyPunctuationValues.put(mKeyGA, KEY_GA_PUNCT);
        addView(mKeyGA);

        mKeyMA = new KeyText(context);
        mKeyMA.setOnTouchListener(textKeyTouchListener);
        keyValues.put(mKeyMA, KEY_MA);
        keyPunctuationValues.put(mKeyMA, KEY_MA_PUNCT);
        addView(mKeyMA);

        mKeyLA = new KeyText(context);
        mKeyLA.setOnTouchListener(textKeyTouchListener);
        keyValues.put(mKeyLA, KEY_LA);
        keyPunctuationValues.put(mKeyLA, KEY_LA_PUNCT);
        addView(mKeyLA);

        // Row 3

        mKeySA = new KeyText(context);
        mKeySA.setOnTouchListener(textKeyTouchListener);
        keyValues.put(mKeySA, KEY_SA);
        keyPunctuationValues.put(mKeySA, KEY_SA_PUNCT);
        addView(mKeySA);

        mKeyTADA = new KeyText(context);
        mKeyTADA.setOnTouchListener(textKeyTouchListener);
        keyValues.put(mKeyTADA, String.valueOf(MongolCode.Uni.DA)); // make DA the default
        keyPunctuationValues.put(mKeyTADA, KEY_TA_PUNCT);
        addView(mKeyTADA);

        mKeyCHA = new KeyText(context);
        mKeyCHA.setOnTouchListener(textKeyTouchListener);
        keyValues.put(mKeyCHA, KEY_CHA);
        keyPunctuationValues.put(mKeyCHA, KEY_CHA_PUNCT);
        addView(mKeyCHA);

        mKeyJA = new KeyText(context);
        mKeyJA.setOnTouchListener(textKeyTouchListener);
        keyValues.put(mKeyJA, KEY_JA);
        keyPunctuationValues.put(mKeyJA, KEY_JA_PUNCT);
        addView(mKeyJA);

        mKeyYA = new KeyText(context);
        mKeyYA.setOnTouchListener(textKeyTouchListener);
        keyValues.put(mKeyYA, KEY_YA);
        keyPunctuationValues.put(mKeyYA, KEY_YA_PUNCT);
        addView(mKeyYA);

        mKeyRA = new KeyText(context);
        mKeyRA.setOnTouchListener(textKeyTouchListener);
        keyValues.put(mKeyRA, KEY_RA);
        keyPunctuationValues.put(mKeyRA, KEY_RA_PUNCT);
        addView(mKeyRA);

        // Row 4

        // keyboard
        mKeyKeyboard = new KeyImage(context);
        mKeyKeyboard.setImage(BitmapFactory.decodeResource(getResources(), R.drawable.ic_keyboard_black_48dp));
        mKeyKeyboard.setOnTouchListener(textKeyTouchListener);
        addView(mKeyKeyboard);

        // comma
        mKeyComma = new KeyText(context);
        mKeyComma.setText(MongolCode.Uni.MONGOLIAN_COMMA);
        mKeyComma.setSubText(MongolCode.Uni.VERTICAL_QUESTION_MARK);
        mKeyComma.setOnTouchListener(textKeyTouchListener);
        keyValues.put(mKeyComma, String.valueOf(MongolCode.Uni.MONGOLIAN_COMMA));
        keyPunctuationValues.put(mKeyComma, String.valueOf(MongolCode.Uni.MONGOLIAN_COMMA));
        addView(mKeyComma);

        // space
        mKeySpace = new KeyText(context);
        mKeySpace.setText(" ");
        String subtextDisplay = "ᠶ᠋ᠢ ᠳᠤ ᠤᠨ";
        mKeySpace.setSubText(subtextDisplay);
        mKeySpace.setOnTouchListener(textKeyTouchListener);
        keyValues.put(mKeySpace, " ");
        keyPunctuationValues.put(mKeySpace, " ");
        addView(mKeySpace);

        // return
        mKeyReturn = new KeyImage(context);
        mKeyReturn.setImage(BitmapFactory.decodeResource(getResources(), R.drawable.ic_keyboard_return_black_48dp));
        mKeyReturn.setOnTouchListener(textKeyTouchListener);
        keyValues.put(mKeyReturn, "\n");
        keyPunctuationValues.put(mKeyReturn, "\n");
        addView(mKeyReturn);

        // backspace
        mKeyBackspace = new KeyImage(context);
        mKeyBackspace.setImage(BitmapFactory.decodeResource(getResources(), R.drawable.ic_keyboard_backspace_black_48dp));
        mKeyBackspace.setOnTouchListener(handleBackspace);
        addView(mKeyBackspace);

        setDisplayText();

        for (int i = 0; i < getChildCount(); i++) {
            Key child = (Key) getChildAt(i);
            if (child instanceof KeyText) {
                ((KeyText) child).setTypeFace(typeface);
                ((KeyText) child).setTextSize(textSize);
                ((KeyText) child).setSubTextSize(subTextSize);
                ((KeyText) child).setTextColor(textColor);
                ((KeyText) child).setSubTextColor(subTextColor);
            } else if (child instanceof KeyImage) {

            }

            child.setKeyColor(keyColor);
            child.setPressedColor(pressedColor);
            child.setBorderColor(borderColor);
            child.setBorderWidth(borderWidth);
            child.setBorderRadius(borderRadius);
            child.setPadding(padding, padding, padding, padding);
        }

    }

    private void setDisplayText() {

        if (mIsShowingPunctuation) {
            mKeyA.setText(KEY_A_PUNCT);
            mKeyE.setText(KEY_E_PUNCT);
            mKeyI.setText(KEY_I_PUNCT);
            mKeyO.setText(KEY_O_PUNCT);
            mKeyU.setText(KEY_U_PUNCT);
            mKeyNA.setText(KEY_NA_PUNCT);
            mKeyBA.setText(KEY_BA_PUNCT);
            mKeyQA.setText(KEY_QA_PUNCT);
            mKeyGA.setText(KEY_GA_PUNCT);
            mKeyMA.setText(KEY_MA_PUNCT);
            mKeyLA.setText(KEY_LA_PUNCT);
            mKeySA.setText(KEY_SA_PUNCT);
            mKeyTADA.setText(KEY_TA_PUNCT);
            mKeyCHA.setText(KEY_CHA_PUNCT);
            mKeyJA.setText(KEY_JA_PUNCT);
            mKeyYA.setText(KEY_YA_PUNCT);
            mKeyRA.setText(KEY_RA_PUNCT);

            mKeyA.setSubText(KEY_A_PUNCT_SUB);
            mKeyE.setSubText(KEY_E_PUNCT_SUB);
            mKeyI.setSubText(KEY_I_PUNCT_SUB);
            mKeyO.setSubText(KEY_O_PUNCT_SUB);
            mKeyU.setSubText(KEY_U_PUNCT_SUB);
            mKeyNA.setSubText(KEY_NA_PUNCT_SUB);
            mKeyBA.setSubText(KEY_BA_PUNCT_SUB);
            mKeyQA.setSubText(KEY_QA_PUNCT_SUB);
            mKeyGA.setSubText(KEY_GA_PUNCT_SUB);
            mKeyMA.setSubText(KEY_MA_PUNCT_SUB);
            mKeyLA.setSubText(KEY_LA_PUNCT_SUB);
            mKeySA.setSubText(KEY_SA_PUNCT_SUB);
            mKeyTADA.setSubText(KEY_TA_PUNCT_SUB);
            mKeyCHA.setSubText(KEY_CHA_PUNCT_SUB);
            mKeyJA.setSubText(KEY_JA_PUNCT_SUB);
            mKeyYA.setSubText(KEY_YA_PUNCT_SUB);
            mKeyRA.setSubText(KEY_RA_PUNCT_SUB);
        } else {
            mKeyA.setText(KEY_A);
            mKeyE.setText(KEY_E);
            mKeyI.setText(KEY_I);
            mKeyO.setText(KEY_O);
            mKeyU.setText("" + KEY_U + MongolCode.Uni.ZWJ); // display
            mKeyNA.setText(KEY_NA);
            mKeyBA.setText(KEY_BA);
            mKeyQA.setText(KEY_QA);
            mKeyGA.setText(KEY_GA);
            mKeyMA.setText(KEY_MA);
            mKeyLA.setText(KEY_LA);
            mKeySA.setText(KEY_SA);
            mKeyTADA.setText("" + MongolCode.Uni.TA); // display
            mKeyCHA.setText(KEY_CHA);
            mKeyJA.setText(KEY_JA);
            mKeyYA.setText(KEY_YA);
            mKeyRA.setText(KEY_RA);

            mKeyA.setSubText(KEY_A_SUB);
            mKeyE.setSubText(KEY_E_SUB);
            mKeyI.setSubText(KEY_I_SUB);
            mKeyO.setSubText(KEY_O_SUB);
            mKeyU.setSubText(KEY_U_SUB);
            mKeyNA.setSubText(KEY_NA_SUB);
            mKeyBA.setSubText(KEY_BA_SUB);
            mKeyQA.setSubText(KEY_QA_SUB);
            mKeyGA.setSubText(KEY_GA_SUB);
            mKeyMA.setSubText(KEY_MA_SUB);
            mKeyLA.setSubText(KEY_LA_SUB);
            mKeySA.setSubText(KEY_SA_SUB);
            mKeyTADA.setSubText(KEY_TA_SUB);
            mKeyCHA.setSubText(KEY_CHA_SUB);
            mKeyJA.setSubText(KEY_JA_SUB);
            mKeyYA.setSubText(KEY_YA_SUB);
            mKeyRA.setSubText(KEY_RA_SUB);
        }
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        // | A  | E  | I |  O |  U |    Row 1
        // | N | B | Q | G | M | L |    Row 2
        // | S | D | Ch| J | Y | R |    Row 3
        // |kb | , | space |ret|del|    Row 4

        final int totalWidth = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
        final int totalHeight = getMeasuredHeight() - getPaddingTop() - getPaddingBottom();

        int[] numberOfKeysInRow = {5, 6, 6, 5};
        // the key weights for each row should sum to 1
        float[] keyWeight = {
                1 / 5f, 1 / 5f, 1 / 5f, 1 / 5f, 1 / 5f,           // row 0
                1 / 6f, 1 / 6f, 1 / 6f, 1 / 6f, 1 / 6f, 1 / 6f,     // row 1
                1 / 6f, 1 / 6f, 1 / 6f, 1 / 6f, 1 / 6f, 1 / 6f,     // row 2
                1 / 6f, 1 / 6f, 2 / 6f, 1 / 6f, 1 / 6f};          // row 3
        int numberOfRows = numberOfKeysInRow.length;

        float x = getPaddingLeft();
        float y = getPaddingTop();
        int keyIndex = 0;
        for (int rowIndex = 0; rowIndex < numberOfRows; rowIndex++) {

            int end = keyIndex + numberOfKeysInRow[rowIndex];
            for (int i = keyIndex; i < end; i++) {
                View child = getChildAt(keyIndex);

                float keyWidth = totalWidth * keyWeight[keyIndex];
                float keyHeight = totalHeight / numberOfRows;
                child.measure(MeasureSpec.makeMeasureSpec((int) keyWidth, MeasureSpec.EXACTLY),
                        MeasureSpec.makeMeasureSpec((int) keyHeight, MeasureSpec.EXACTLY));

                child.layout((int) x, (int) y, (int) (x + keyWidth), (int) (y + keyHeight));
                //x += keyWidth;
                x += keyWidth;
                keyIndex++;
            }

            x = getPaddingLeft();
            y += (float) totalHeight / numberOfRows;
        }

    }

    protected View.OnTouchListener handleBackspace = new View.OnTouchListener() {

        private Handler handler;
        final int INITIAL_DELAY = 500;
        final int REPEAT_DELAY = 50;

        @Override
        public boolean onTouch(View view, MotionEvent event) {

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    view.setPressed(true);
                    doBackspace();
                    if (handler != null)
                        return true;
                    handler = new Handler();
                    handler.postDelayed(actionBackspace, INITIAL_DELAY);
                    break;
                case MotionEvent.ACTION_MOVE:
                    break;
                default:
                    view.setPressed(false);
                    if (handler == null)
                        return true;
                    handler.removeCallbacks(actionBackspace);
                    handler = null;
                    break;
            }

            return true;
        }

        private void doBackspace(){
            if (inputConnection == null) return;

            if (mComposing.length() > 0) {
                inputConnection.commitText("", 1);
                mComposing.setLength(0);
            } else {

                inputConnection.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL));
                inputConnection.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_DEL));

                // We could also do this with inputConnection.deleteSurroundingText(1, 0)
                // but then we would need to be careful of not deleting too much
                // and not deleting half a surrogate pair.
                // see https://developer.android.com/reference/android/view/inputmethod/InputConnection.html#deleteSurroundingText(int,%20int)
                // see also https://stackoverflow.com/a/45182401
            }


        }

        Runnable actionBackspace = new Runnable() {
            @Override
            public void run() {
                doBackspace();
                handler.postDelayed(this, REPEAT_DELAY);
            }
        };

    };


    protected View.OnTouchListener textKeyTouchListener = new View.OnTouchListener() {

        Handler handler;
        final int LONGPRESS_THRESHOLD = 500; // milliseconds

        PopupKeyCandidates popupView;
        int popupWidth;
        PopupWindow popupWindow;

        @Override
        public boolean onTouch(View view, final MotionEvent event) {

            if (event.getPointerCount() > 1) return false;

            final Key key = (Key) view;
            int action = event.getActionMasked();

            switch (action) {
                case (MotionEvent.ACTION_DOWN):

                    key.setPressed(true);

                    Candidates candidates = getPopupCandidates(key);
                    if (candidates != null && candidates.unicode != null
                            && candidates.unicode.length > 0) {
                        int x = (int) event.getRawX();
                        preparePopup(key, candidates, x);
                    }


                    return true;
                case (MotionEvent.ACTION_MOVE):

                    if (popupView != null) {
                        int x = (int) event.getRawX();
                        popupView.updateTouchPosition(x);
                    }


                    return true;
                case (MotionEvent.ACTION_UP):

                    if (inputConnection == null) {
                        dismissPopup(key, event);
                        return true;
                    }


                    if (popupView != null) {                                // handle popups
                        dismissPopup(key, event);
                    } else if (key == mKeyKeyboard) {                       // keyboard key

                        mIsShowingPunctuation = !mIsShowingPunctuation;
                        setDisplayText();

                    } else {                                                // other keys

                        String inputText;
                        if (mIsShowingPunctuation) {
                            inputText = keyPunctuationValues.get(key);
                        } else {
                            inputText = keyValues.get(key);
                        }

                        // handle composing
                        if (mComposing.length() > 0) {
                            if (MongolCode.isMongolian(inputText.charAt(0))) {
                                inputConnection.commitText(mComposing, 1);
                            } else {
                                inputConnection.finishComposingText();
                            }
                            mComposing.setLength(0);
                        }

                        // TA/DA defaults to DA except in the INITIAL location
                        if (inputText.equals(String.valueOf(MongolCode.Uni.DA))) {
                            char prevChar = getPreviousChar();
                            if (!MongolCode.isMongolian(prevChar)) {
                                inputText = String.valueOf(MongolCode.Uni.TA);
                            }
                        }

                        inputConnection.commitText(inputText, 1);
                    }

                    key.setPressed(false);
                    if (handler != null) handler.removeCallbacksAndMessages(null);
                    return true;
                default:
                    dismissPopup(key, event);
                    return false;
            }
        }

        private void preparePopup(final Key key, final Candidates candidates, final int xPosition) {

            if (handler != null) {
                handler.removeCallbacksAndMessages(null);
            } else {
                handler = new Handler();
            }

            final Runnable runnableCode = new Runnable() {
                @Override
                public void run() {

                    if (popupWindow != null) return;

                    // get the popup view
                    popupView = new PopupKeyCandidates(getContext());
                    popupView.setBackgroundColor(mPopupBackgroundColor);

                    // update the popup view with the candidate choices
                    if (candidates == null || candidates.unicode == null) return;
                    popupView.setCandidates(candidates.unicode);
                    if (candidates.display == null) {
                        popupView.setDisplayCandidates(candidates.unicode, PopupKeyCandidates.DEFAULT_TEXT_SIZE);
                    } else {
                        popupView.setDisplayCandidates(candidates.display, PopupKeyCandidates.DEFAULT_TEXT_SIZE);
                    }

                    popupView.setHighlightColor(mPopupHighlightColor);

                    popupWindow = new PopupWindow(popupView,
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT);
                    int location[] = new int[2];
                    key.getLocationOnScreen(location);
                    int measureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                    popupView.measure(measureSpec, measureSpec);
                    popupWidth = popupView.getMeasuredWidth();
                    int spaceAboveKey = key.getHeight() / 4;
                    int x = xPosition - popupWidth / popupView.getChildCount() / 2;
                    //int locationX = location[0]
                    popupWindow.showAtLocation(key, Gravity.NO_GRAVITY,
                            x, location[1] - popupView.getMeasuredHeight() - spaceAboveKey);


                    // highlight current item (after the popup window has loaded)
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            int x = xPosition;
                            popupView.updateTouchPosition(x);
                        }
                    });

                }
            };

            handler.postDelayed(runnableCode, LONGPRESS_THRESHOLD);
        }

        private void dismissPopup(Key key, MotionEvent event) {

            key.setPressed(false);

            if (handler != null) {
                handler.removeCallbacksAndMessages(null);
            }
            if (popupWindow == null) return;

            int x = (int) event.getRawX();

            CharSequence selectedItem = popupView.getCurrentItem(x);
            if (!TextUtils.isEmpty(selectedItem) && inputConnection != null) {

                inputConnection.beginBatchEdit();

                if (mComposing.length() > 0) {
                    inputConnection.commitText(mComposing, 1);
                    mComposing.setLength(0);
                }

                // add composing text for certain medials to avoid confusion with finals
                if (selectedItem.equals(MEDIAL_A_FVS1)) {
                    inputConnection.setComposingText(MEDIAL_A_FVS1_COMPOSING, 1);
                    mComposing.append(MEDIAL_A_FVS1);
//                } else if (selectedItem.equals(INITIAL_I_SUFFIX)) {
//                    inputConnection.setComposingText(INITIAL_I_SUFFIX_COMPOSING, 1);
//                    mComposing.append(INITIAL_I_SUFFIX);
                } else if (selectedItem.equals(MEDIAL_I_FVS1)) {
                    inputConnection.setComposingText(MEDIAL_I_FVS1_COMPOSING, 1);
                    mComposing.append(MEDIAL_I_FVS1);
                } else if (selectedItem.equals(MEDIAL_ZWJ_I)) {
                    inputConnection.setComposingText(MEDIAL_ZWJ_I_COMPOSING, 1);
                    mComposing.append(MEDIAL_ZWJ_I);
                } else if (selectedItem.equals(MEDIAL_U_FVS1)) {
                    inputConnection.setComposingText(MEDIAL_U_FVS1_COMPOSING, 1);
                    mComposing.append(MEDIAL_U_FVS1);
                } else if (selectedItem.equals(MEDIAL_UE_FVS2)) {
                    inputConnection.setComposingText(MEDIAL_UE_FVS2_COMPOSING, 1);
                    mComposing.append(MEDIAL_UE_FVS2);
                } else if (selectedItem.equals(MEDIAL_DOTTED_NA)) {
                    inputConnection.setComposingText(MEDIAL_DOTTED_NA_COMPOSING, 1);
                    mComposing.append(MEDIAL_DOTTED_NA);
                } else if (selectedItem.equals(MEDIAL_TA_FVS1)) {
                    inputConnection.setComposingText(MEDIAL_TA_FVS1_COMPOSING, 1);
                    mComposing.append(MEDIAL_TA_FVS1);
                } else if (selectedItem.equals(YA_FVS1)) {
                    inputConnection.setComposingText(YA_FVS1_COMPOSING, 1);
                    mComposing.append(YA_FVS1);
                } else {
                    inputConnection.commitText(selectedItem, 1);
                }

                inputConnection.endBatchEdit();

            }

            popupWindow.dismiss();
            popupView = null;
            popupWindow = null;
        }
    };



    private char getPreviousChar() {
        if (inputConnection == null) return 0;
        CharSequence previous = inputConnection.getTextBeforeCursor(1, 0);
        if (TextUtils.isEmpty(previous)) return 0;
        return previous.charAt(0);
    }

    private Candidates getPopupCandidates(Key key) {
        // these are the choices to display in the popup (and corresponding unicode values)
        Candidates candidates = null;

        // get the appropriate candidates based on the key pressed
        if (key == mKeyA) {
            candidates = getCandidatesForA(isIsolateOrInitial());
        } else if (key == mKeyE) {
            candidates = getCandidatesForE(isIsolateOrInitial());
        } else if (key == mKeyI) {
            candidates = getCandidatesForI(isIsolateOrInitial());
        } else if (key == mKeyO) {
            candidates = getCandidatesForO(isIsolateOrInitial());
        } else if (key == mKeyU) {
            candidates = getCandidatesForU(isIsolateOrInitial());
        } else if (key == mKeyNA) {
            candidates = getCandidatesForNA(isIsolateOrInitial());
        } else if (key == mKeyBA) {
            candidates = getCandidatesForBA();
        } else if (key == mKeyQA) {
            candidates = getCandidatesForQA();
        } else if (key == mKeyGA) {
            candidates = getCandidatesForGA(isIsolateOrInitial());
        } else if (key == mKeyMA) {
            candidates = getCandidatesForMA();
        } else if (key == mKeyLA) {
            candidates = getCandidatesForLA();
        } else if (key == mKeySA) {
            candidates = getCandidatesForSA();
        } else if (key == mKeyTADA) {
            candidates = getCandidatesForTADA(isIsolateOrInitial());
        } else if (key == mKeyCHA) {
            candidates = getCandidatesForCHA();
        } else if (key == mKeyJA) {
            candidates = getCandidatesForJA();
        } else if (key == mKeyYA) {
            candidates = getCandidatesForYA(isIsolateOrInitial());
        } else if (key == mKeyRA) {
            candidates = getCandidatesForRA();
        } else if (key == mKeyKeyboard) {
            candidates = getCandidatesForKeyboard();
        } else if (key == mKeyComma) {
            candidates = getCandidatesForComma();
        } else if (key == mKeySpace) {
            candidates = getCandidatesForSpace();
        }

        return candidates;
    }


    private boolean isIsolateOrInitial() {
        if (inputConnection == null) return true;
        CharSequence before = inputConnection.getTextBeforeCursor(2, 0);
        CharSequence after = inputConnection.getTextAfterCursor(2, 0);
        if (before == null || after == null) return true;
        // get Mongol word location at cursor input
        MongolCode.Location location = MongolCode.getLocation(before, after);
        return location == MongolCode.Location.ISOLATE ||
                location == MongolCode.Location.INITIAL;
        //return isIsolateOrInitial;
    }

    private Candidates getCandidatesForA(boolean isIsolateOrInitial) {
        Candidates can = new Candidates();

        if (mIsShowingPunctuation) {
            can.unicode = new String[]{KEY_A_PUNCT_SUB};
            return can;
        }

        if (isIsolateOrInitial) {
            can.unicode = new String[]{
                    "" + MongolCode.Uni.A + MongolCode.Uni.FVS1,
                    "" + MongolCode.Uni.MONGOLIAN_NIRUGU};
        } else { // medial || final
            char previousChar = getPreviousChar();
            if (MongolCode.isMvsConsonant(previousChar)) {
                // include MVS
                can.unicode = new String[]{
                        "" + MongolCode.Uni.MVS + MongolCode.Uni.A,
                        "" + MongolCode.Uni.A + MongolCode.Uni.FVS1,
                        "" + MongolCode.Uni.MONGOLIAN_NIRUGU};
                can.display = new String[]{
                        "" + MongolCode.Uni.ZWJ + previousChar + MongolCode.Uni.MVS + MongolCode.Uni.A,
                        "" + MongolCode.Uni.ZWJ + MongolCode.Uni.A + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ,
                        "" + MongolCode.Uni.MONGOLIAN_NIRUGU};
            } else {
                can.unicode = new String[]{
                        "" + MongolCode.Uni.A + MongolCode.Uni.FVS1,
                        "" + MongolCode.Uni.MONGOLIAN_NIRUGU};
                can.display = new String[]{
                        "" + MongolCode.Uni.ZWJ + MongolCode.Uni.A + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ,
                        "" + MongolCode.Uni.MONGOLIAN_NIRUGU};
            }

        }
        return can;
    }

    private Candidates getCandidatesForE(boolean isIsolateOrInitial) {
        Candidates can = new Candidates();

        if (mIsShowingPunctuation) {
            can.unicode = new String[]{KEY_E_PUNCT_SUB};
            return can;
        }

        if (isIsolateOrInitial) {
            can.unicode = new String[]{"" + MongolCode.Uni.EE};
        } else { // medial || final
            char previousChar = getPreviousChar();
            if (MongolCode.isMvsConsonant(previousChar)
                    && previousChar != MongolCode.Uni.QA && previousChar != MongolCode.Uni.GA) {
                // include MVS
                can.unicode = new String[]{
                        "" + MongolCode.Uni.MVS + MongolCode.Uni.E,
                        "" + MongolCode.Uni.EE};
                can.display = new String[]{
                        "" + MongolCode.Uni.ZWJ + previousChar + MongolCode.Uni.MVS + MongolCode.Uni.E,
                        "" + MongolCode.Uni.EE};
            } else {
                can.unicode = new String[]{"" + MongolCode.Uni.EE};
            }

        }
        return can;
    }

    private Candidates getCandidatesForI(boolean isIsolateOrInitial) {
        Candidates can = new Candidates();

        if (mIsShowingPunctuation) {
            can.unicode = new String[]{KEY_I_PUNCT_SUB};
            return can;
        }

        if (isIsolateOrInitial) {
//            can.unicode = new String[]{
//                    "" + MongolCode.Uni.NNBS + MongolCode.Uni.I,
//                    "" + MongolCode.Uni.NNBS + MongolCode.Uni.I};
//            can.display = new String[]{
//                    "" + MongolCode.Uni.NNBS + MongolCode.Uni.I,
//                    "" + MongolCode.Uni.NNBS + MongolCode.Uni.I + MongolCode.Uni.ZWJ};
        } else {// medial/final
            char prevChar = getPreviousChar();
            if (MongolCode.isVowel(prevChar)) {
                can.unicode = new String[]{
                        "" + MongolCode.Uni.ZWJ + MongolCode.Uni.I, // override double tooth I after vowel
                        "" + MongolCode.Uni.I + MongolCode.Uni.FVS1};
                can.display = new String[]{
                        "" + MongolCode.Uni.ZWJ + MongolCode.Uni.I + MongolCode.Uni.ZWJ,
                        "" + MongolCode.Uni.ZWJ + MongolCode.Uni.I + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ};
            } else {
                can.unicode = new String[]{"" +
                        "" + MongolCode.Uni.I + MongolCode.Uni.FVS1};
                can.display = new String[]{
                        "" + MongolCode.Uni.ZWJ + MongolCode.Uni.I + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ};
            }

        }
        return can;
    }

    private Candidates getCandidatesForO(boolean isIsolateOrInitial) {
        Candidates can = new Candidates();

        if (mIsShowingPunctuation) {
            can.unicode = new String[]{KEY_O_PUNCT_SUB};
            return can;
        }

        if (!isIsolateOrInitial) { // medial/final
            can.unicode = new String[]{"" + MongolCode.Uni.U + MongolCode.Uni.FVS1,
                    "" + MongolCode.Uni.U + MongolCode.Uni.FVS1};
            can.display = new String[]{
                    "" + MongolCode.Uni.ZWJ + MongolCode.Uni.U + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ,
                    "" + MongolCode.Uni.ZWJ + MongolCode.Uni.U + MongolCode.Uni.FVS1};
            return can;
        }
        return null;
    }

    private Candidates getCandidatesForU(boolean isIsolateOrInitial) {
        Candidates can = new Candidates();

        if (mIsShowingPunctuation) {
            can.unicode = new String[]{KEY_U_PUNCT_SUB};
            return can;
        }

        if (!isIsolateOrInitial) { // medial/final
            can.unicode = new String[]{"" + MongolCode.Uni.UE + MongolCode.Uni.FVS2,
                    "" + MongolCode.Uni.UE + MongolCode.Uni.FVS1};
            can.display = new String[]{
                    "" + MongolCode.Uni.ZWJ + MongolCode.Uni.UE + MongolCode.Uni.FVS2 + MongolCode.Uni.ZWJ,
                    "" + MongolCode.Uni.ZWJ + MongolCode.Uni.UE + MongolCode.Uni.FVS1};
            return can;
        }
        return null;
    }

    private Candidates getCandidatesForNA(boolean isIsolateOrInitial) {
        Candidates can = new Candidates();

        if (mIsShowingPunctuation) {
            can.unicode = new String[]{KEY_NA_PUNCT_SUB};
            return can;
        }

        if (isIsolateOrInitial) {
            can.unicode = new String[]{"" + MongolCode.Uni.ANG};
        } else { // medial/final
            can.unicode = new String[]{"" + MongolCode.Uni.ANG,
                    "" + MongolCode.Uni.NA + MongolCode.Uni.ZWJ, // only(?) way to override dotted N before vowel in Unicode 9.0
                    "" + MongolCode.Uni.NA + MongolCode.Uni.FVS1};
            can.display = new String[]{
                    "" + MongolCode.Uni.ANG,
                    "" + MongolCode.Uni.ZWJ + MongolCode.Uni.NA + MongolCode.Uni.ZWJ,
                    "" + MongolCode.Uni.ZWJ + MongolCode.Uni.NA + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ};
        }
        return can;
    }

    private Candidates getCandidatesForBA() {
        Candidates can = new Candidates();

        if (mIsShowingPunctuation) {
            can.unicode = new String[]{KEY_BA_PUNCT_SUB};
            return can;
        }

        can.unicode = new String[]{"" + MongolCode.Uni.PA, "" + MongolCode.Uni.FA};
        return can;
    }

    private Candidates getCandidatesForQA() {
        Candidates can = new Candidates();

        if (mIsShowingPunctuation) {
            can.unicode = new String[]{KEY_QA_PUNCT_SUB};
            return can;
        }

        can.unicode = new String[]{"" + MongolCode.Uni.HAA};
        return can;
    }

    private Candidates getCandidatesForGA(boolean isIsolateOrInitial) {
        Candidates can = new Candidates();

        if (mIsShowingPunctuation) {
            can.unicode = new String[]{KEY_GA_PUNCT_SUB};
            return can;
        }

        if (isIsolateOrInitial) {
            can.unicode = new String[]{"" + MongolCode.Uni.KA};
        } else { // medial/final
            can.unicode = new String[]{"" + MongolCode.Uni.KA,
                    "" + MongolCode.Uni.GA + MongolCode.Uni.FVS1, // see note on MongolCode(FINA_GA_FVS1)
                    "" + MongolCode.Uni.GA + MongolCode.Uni.FVS2}; // see note on MongolCode(FINA_GA_FVS2)
            can.display = new String[]{
                    "" + MongolCode.Uni.KA,
                    "" + MongolCode.Uni.ZWJ + MongolCode.Uni.GA + MongolCode.Uni.FVS1,
                    "" + MongolCode.Uni.ZWJ + MongolCode.Uni.GA + MongolCode.Uni.FVS2};
        }
        return can;
    }

    private Candidates getCandidatesForMA() {
        if (mIsShowingPunctuation) {
            Candidates can = new Candidates();
            can.unicode = new String[]{KEY_MA_PUNCT_SUB};
            return can;
        }
        return null;
    }

    private Candidates getCandidatesForLA() {
        Candidates can = new Candidates();

        if (mIsShowingPunctuation) {
            can.unicode = new String[]{KEY_LA_PUNCT_SUB};
            return can;
        }

        can.unicode = new String[]{"" + MongolCode.Uni.LHA};
        return can;
    }

    private Candidates getCandidatesForSA() {
        Candidates can = new Candidates();

        if (mIsShowingPunctuation) {
            can.unicode = new String[]{KEY_SA_PUNCT_SUB};
            return can;
        }

        can.unicode = new String[]{"" + MongolCode.Uni.SHA};
        return can;
    }

    private Candidates getCandidatesForTADA(boolean isIsolateOrInitial) {
        Candidates can = new Candidates();

        if (mIsShowingPunctuation) {
            can.unicode = new String[]{KEY_TA_PUNCT_SUB};
            return can;
        }

        if (isIsolateOrInitial) {
            char prevChar = getPreviousChar();
            if (prevChar == MongolCode.Uni.NNBS) {
                can.unicode = new String[]{"" + MongolCode.Uni.DA};
            } else {
                can.unicode = new String[]{"" + MongolCode.Uni.DA + MongolCode.Uni.FVS1};
            }
            can.display = new String[]{"" + MongolCode.Uni.DA + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ};
            // TODO if this turns out to be an isolate then the FVS1 should be removed
        } else { // medial/final
            can.unicode = new String[]{
                    "" + MongolCode.Uni.TA + MongolCode.Uni.FVS1,
                    "" + MongolCode.Uni.TA,
                    "" + MongolCode.Uni.DA + MongolCode.Uni.FVS1};
            can.display = new String[]{
                    "" + MongolCode.Uni.MONGOLIAN_NIRUGU +
                            MongolCode.Uni.TA + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ,
                    "" + MongolCode.Uni.ZWJ + MongolCode.Uni.TA,
                    "" + MongolCode.Uni.ZWJ + MongolCode.Uni.DA + MongolCode.Uni.FVS1};
        }
        return can;
    }

    private Candidates getCandidatesForCHA() {
        Candidates can = new Candidates();

        if (mIsShowingPunctuation) {
            can.unicode = new String[]{KEY_CHA_PUNCT_SUB};
            return can;
        }

        can.unicode = new String[]{
                "" + MongolCode.Uni.TSA,
                "" + MongolCode.Uni.CHI,};
        return can;
    }

    private Candidates getCandidatesForJA() {
        Candidates can = new Candidates();

        if (mIsShowingPunctuation) {
            can.unicode = new String[]{KEY_JA_PUNCT_SUB};
            return can;
        }

        can.unicode = new String[]{
                "" + MongolCode.Uni.ZA,
                "" + MongolCode.Uni.ZHI};
        return can;
    }

    private Candidates getCandidatesForYA(boolean isIsolateOrInitial) {
        Candidates can = new Candidates();

        if (mIsShowingPunctuation) {
            can.unicode = new String[]{KEY_YA_PUNCT_SUB};
            return can;
        }

        if (isIsolateOrInitial) {
            can.unicode = new String[]{
                    "" + MongolCode.Uni.WA,
                    "" + MongolCode.Uni.YA + MongolCode.Uni.FVS1};
            can.display = new String[]{
                    "" + MongolCode.Uni.WA,
                    "" + MongolCode.Uni.YA + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ};
        } else { // medial/final
            can.unicode = new String[]{
                    "" + MongolCode.Uni.WA,
                    "" + MongolCode.Uni.YA + MongolCode.Uni.FVS1};
            can.display = new String[]{
                    "" + MongolCode.Uni.WA,
                    "" + MongolCode.Uni.ZWJ + MongolCode.Uni.YA + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ};
        }

        return can;
    }

    private Candidates getCandidatesForRA() {
        Candidates can = new Candidates();

        if (mIsShowingPunctuation) {
            can.unicode = new String[]{KEY_RA_PUNCT_SUB};
            return can;
        }

        can.unicode = new String[]{"" + MongolCode.Uni.ZRA};
        return can;
    }

    private Candidates getCandidatesForKeyboard() {
        Candidates can = new Candidates();
        can.unicode = new String[]{
                "English",
                "Computer",
                "Cyrillic"};
        return can;
    }

    private Candidates getCandidatesForComma() {
        Candidates can = new Candidates();
        can.unicode = new String[]{
                "" + MongolCode.Uni.VERTICAL_QUESTION_MARK,
                "" + MongolCode.Uni.MONGOLIAN_FULL_STOP,
                "" + MongolCode.Uni.VERTICAL_EXCLAMATION_MARK};
        return can;
    }

    private Candidates getCandidatesForSpace() {
        Candidates can = new Candidates();
        String previousWord = getPreviousMongolWord();
        if (TextUtils.isEmpty(previousWord)) {
            can.unicode = new String[]{
                    "" + MongolCode.Uni.NNBS};
            return can;

        }
        // TODO if it is a number then return the right suffix for that
        char lastChar = previousWord.charAt(previousWord.length() - 1);
        MongolCode.Gender gender = MongolCode.getWordGender(previousWord);
        if (gender == null) {
            can.unicode = new String[]{
                    "" + MongolCode.Uni.NNBS};
            return can;
        }
        String duTuSuffix = MongolCode.getSuffixTuDu(gender, lastChar);
        String iYiSuffix = MongolCode.getSuffixYiI(lastChar);
        String yinUnUSuffix = MongolCode.getSuffixYinUnU(gender, lastChar);
        String achaSuffix = MongolCode.getSuffixAchaEche(gender);
        String barIyarSuffix = MongolCode.getSuffixBarIyar(gender, lastChar);
        String taiSuffix = MongolCode.getSuffixTaiTei(gender);
        String uuSuffix = MongolCode.getSuffixUu(gender);
        String banIyanSuffix = MongolCode.getSuffixBanIyan(gender, lastChar);
        String udSuffix = MongolCode.getSuffixUd(gender);

        can.unicode = new String[]{
                "" + MongolCode.Uni.NNBS,
                uuSuffix,
                yinUnUSuffix,
                iYiSuffix,
                duTuSuffix,
                barIyarSuffix,
                banIyanSuffix,
                achaSuffix,
                udSuffix};
        return can;
    }

    // this may not actually return a whole word if the word is very long
    private String getPreviousMongolWord() {
        if (inputConnection == null) return "";
        int numberOfCharsToFetch = 20;
        CharSequence previous = inputConnection.getTextBeforeCursor(numberOfCharsToFetch, 0);
        if (TextUtils.isEmpty(previous)) return "";
        int startIndex = previous.length() - 1;
        char charAtIndex = previous.charAt(startIndex);
        if (charAtIndex == ' ' || charAtIndex == MongolCode.Uni.NNBS) startIndex--;
        StringBuilder mongolWord = new StringBuilder();
        for (int i = startIndex; i >= 0; i--) {
            charAtIndex = previous.charAt(i);
            if (MongolCode.isMongolian(charAtIndex)) {
                mongolWord.append(charAtIndex);
            } else if (charAtIndex == ' ' || charAtIndex == MongolCode.Uni.NNBS) {
                break;
            }
        }
        return mongolWord.toString();
    }

    private class Candidates {
        String[] unicode;
        String[] display;
    }




    // These are special popup chose characters. They are being converted to a
    // temporary medial composing form so that the automatic Unicode rendering
    // will not confuse users (because they would get displayed as a final otherwise).

    private static final String MEDIAL_A_FVS1 = "" + MongolCode.Uni.A + MongolCode.Uni.FVS1;
    private static final String MEDIAL_A_FVS1_COMPOSING = "" + MongolCode.Uni.A + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ;
    //    private static final String INITIAL_I_SUFFIX = "" + MongolCode.Uni.NNBS + MongolCode.Uni.I;
//    private static final String INITIAL_I_SUFFIX_COMPOSING = "" + MongolCode.Uni.NNBS + MongolCode.Uni.I + MongolCode.Uni.ZWJ;
    private static final String MEDIAL_I_FVS1 = "" + MongolCode.Uni.I + MongolCode.Uni.FVS1;
    private static final String MEDIAL_I_FVS1_COMPOSING = "" + MongolCode.Uni.I + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ;
    private static final String MEDIAL_ZWJ_I = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.I;
    private static final String MEDIAL_ZWJ_I_COMPOSING = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.I + MongolCode.Uni.ZWJ;

    private static final String MEDIAL_U_FVS1 = "" + MongolCode.Uni.U + MongolCode.Uni.FVS1;
    private static final String MEDIAL_U_FVS1_COMPOSING = "" + MongolCode.Uni.U + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ;
    private static final String MEDIAL_UE_FVS2 = "" + MongolCode.Uni.UE + MongolCode.Uni.FVS2;
    private static final String MEDIAL_UE_FVS2_COMPOSING = "" + MongolCode.Uni.UE + MongolCode.Uni.FVS2 + MongolCode.Uni.ZWJ;
    private static final String MEDIAL_DOTTED_NA = "" + MongolCode.Uni.NA + MongolCode.Uni.FVS1;
    private static final String MEDIAL_DOTTED_NA_COMPOSING = "" + MongolCode.Uni.NA + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ;
    private static final String MEDIAL_TA_FVS1 = "" + MongolCode.Uni.TA + MongolCode.Uni.FVS1;
    private static final String MEDIAL_TA_FVS1_COMPOSING = "" + MongolCode.Uni.TA + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ;
    private static final String YA_FVS1 = "" + MongolCode.Uni.YA + MongolCode.Uni.FVS1;
    private static final String YA_FVS1_COMPOSING = "" + MongolCode.Uni.YA + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ;
}
