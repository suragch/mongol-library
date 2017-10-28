package net.studymongolian.mongollibrary;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputConnection;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import java.util.HashMap;
import java.util.Map;


public abstract class Keyboard extends ViewGroup {

    // Standard keyboard types
//    public static final int AEIOU = 0;
//    public static final int AEIOU_PUNCTUATION = 1;
//    public static final int QWERTY = 2;
//    public static final int QWERTY_PUNCTUATION = 3;
//    public static final int LATIN = 4;
//    public static final int LATIN_PUNCTUATION = 5;
//    public static final int CYRILLIC = 6;
//    public static final int CYRILLIC_PUNCTUATION = 7;

    protected KeyImage mKeyKeyboard;

    protected int mPopupBackgroundColor = Color.WHITE;
    protected int mPopupHighlightColor = Color.GRAY;

    protected Typeface mTypeface;
    protected float mTextSize;
    protected float mSubTextSize;
    protected int mTextColor;
    protected int mSubTextColor;
    protected int mKeyColor;
    protected int mKeyPressedColor;
    protected int mKeyBorderColor;
    protected int mKeyBorderWidth;
    protected int mKeyBorderRadius;
    protected int mKeyPadding;

    // This will map the button resource id to the String value that we want to
    // input when that key is clicked.
    protected Map<Key, String> mKeyValues = new HashMap<>();
    protected Map<Key, String> mKeyPunctuationValues = new HashMap<>();

    protected boolean mIsShowingPunctuation = false;

    // Our communication link to the EditText/MongolEditText
    protected InputConnection inputConnection;
    protected StringBuilder mComposing = new StringBuilder();

    protected KeyboardListener mKeyboardController = null;

    public Keyboard(Context context) {
        this(context, null, 0);
    }

    public Keyboard(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Keyboard(Context context,
                         AttributeSet attrs,
                         int defStyle) {
        super(context, attrs, defStyle);
    }

    protected void init(Context context) {
        mTypeface = MongolFont.get(MongolFont.QAGAN, context);
        mTextSize = 24;
        mSubTextSize = mTextSize / 2;
        mTextColor = Color.BLACK;
        mSubTextColor = Color.parseColor("#61000000"); // alpha black
        mKeyColor = Color.LTGRAY;
        mKeyPressedColor = Color.GRAY;
        mKeyBorderColor = Color.BLACK;
        mKeyBorderWidth = 0;
        mKeyBorderRadius = 5;
        mKeyPadding = 2;
    }

    protected void initTextKey(KeyText textKey, String primary, String punctuation) {
        textKey.setOnTouchListener(textKeyTouchListener);
        mKeyValues.put(textKey, primary);
        mKeyPunctuationValues.put(textKey, punctuation);
        addView(textKey);
    }

    // number of keys and wieghts are initialized by keyboard subclass
    protected int[] mNumberOfKeysInRow;
    protected float[] mKeyWeights;
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        // this must be set by the subclass
        int numberOfRows = mNumberOfKeysInRow.length;

        final int totalWidth = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
        final int totalHeight = getMeasuredHeight() - getPaddingTop() - getPaddingBottom();

        float x = getPaddingLeft();
        float y = getPaddingTop();
        int keyIndex = 0;
        for (int rowIndex = 0; rowIndex < numberOfRows; rowIndex++) {

            int end = keyIndex + mNumberOfKeysInRow[rowIndex];
            for (int i = keyIndex; i < end; i++) {
                View child = getChildAt(keyIndex);

                float keyWidth = totalWidth * mKeyWeights[keyIndex];
                float keyHeight = totalHeight / numberOfRows;
                child.measure(MeasureSpec.makeMeasureSpec((int) keyWidth, MeasureSpec.EXACTLY),
                        MeasureSpec.makeMeasureSpec((int) keyHeight, MeasureSpec.EXACTLY));

                child.layout((int) x, (int) y, (int) (x + keyWidth), (int) (y + keyHeight));
                x += keyWidth;
                keyIndex++;
            }

            x = getPaddingLeft();
            y += (float) totalHeight / numberOfRows;
        }
    }

    protected void applyThemeToKeys() {
        for (int i = 0; i < getChildCount(); i++) {
            Key child = (Key) getChildAt(i);
            if (child instanceof KeyText) {
                ((KeyText) child).setTypeFace(mTypeface);
                ((KeyText) child).setTextSize(mTextSize);
                ((KeyText) child).setSubTextSize(mSubTextSize);
                ((KeyText) child).setTextColor(mTextColor);
                ((KeyText) child).setSubTextColor(mSubTextColor);
            } else if (child instanceof KeyImage) {

            }

            child.setKeyColor(mKeyColor);
            child.setPressedColor(mKeyPressedColor);
            child.setBorderColor(mKeyBorderColor);
            child.setBorderWidth(mKeyBorderWidth);
            child.setBorderRadius(mKeyBorderRadius);
            child.setPadding(mKeyPadding, mKeyPadding, mKeyPadding, mKeyPadding);
        }
    }

    public interface KeyboardListener {
        public void punctuationSwitch();

    }

    public void setKeyboardListener(KeyboardListener listener) {
        this.mKeyboardController = listener;
    }

    // The activity (or some parent or controller) must give us
    // a reference to the current EditText's InputConnection
    public void setInputConnection(InputConnection ic) {
        this.inputConnection = ic;
    }

    public void onUpdateSelection(int oldSelStart,
                                  int oldSelEnd,
                                  int newSelStart,
                                  int newSelEnd,
                                  int candidatesStart,
                                  int candidatesEnd) {

        // TODO in the Android source InputMethodService also handles Extracted Text here

        // currently we are only using composing for popup glyph selection. If we want to be more
        // like the standard keyboards we could do composing on the whole word.
        if (mComposing.length() > 0 && (newSelStart != candidatesEnd
                || newSelEnd != candidatesEnd)) {
            mComposing.setLength(0);
            // TODO updateCandidates();
            if (inputConnection != null) {
                inputConnection.finishComposingText();
            }
        }
    }

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

                    Keyboard.Candidates candidates = getPopupCandidates(key);
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
                        setDisplayText(mIsShowingPunctuation);

                    } else {                                                // other keys

                        String inputText;
                        if (mIsShowingPunctuation) {
                            inputText = mKeyPunctuationValues.get(key);
                        } else {
                            inputText = mKeyValues.get(key);
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

                        // FIXME not necessarily wanted on all keyboards
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

        private void preparePopup(final Key key, final KeyboardAeiou.Candidates candidates, final int xPosition) {

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

    protected char getPreviousChar() {
        if (inputConnection == null) return 0;
        CharSequence previous = inputConnection.getTextBeforeCursor(1, 0);
        if (TextUtils.isEmpty(previous)) return 0;
        return previous.charAt(0);
    }

    // this may not actually return a whole word if the word is very long
    protected String getPreviousMongolWord() {
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
                mongolWord.insert(0, charAtIndex);
            } else if (charAtIndex == ' ' || charAtIndex == MongolCode.Uni.NNBS) {
                break;
            }
        }
        return mongolWord.toString();
    }

    protected Candidates getCandidatesForSuffix() {
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

    protected boolean isIsolateOrInitial() {
        if (inputConnection == null) return true;
        CharSequence before = inputConnection.getTextBeforeCursor(2, 0);
        CharSequence after = inputConnection.getTextAfterCursor(2, 0);
        if (before == null || after == null) return true;
        // get Mongol word location at cursor input
        MongolCode.Location location = MongolCode.getLocation(before, after);
        return location == MongolCode.Location.ISOLATE ||
                location == MongolCode.Location.INITIAL;
    }

    abstract public Candidates getPopupCandidates(Key key);

    // in this method you should switch the display on the keys for normal or punctuation mode
    abstract public void setDisplayText(boolean isShowingPunctuation);

    public class Candidates {
        String[] unicode;
        String[] display;
    }

    // These are special popup choice characters. They are being converted to a
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
