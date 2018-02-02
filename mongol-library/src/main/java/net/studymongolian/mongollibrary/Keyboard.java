package net.studymongolian.mongollibrary;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
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
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.inputmethod.InputConnection;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public abstract class Keyboard extends ViewGroup implements Key.KeyListener {



    static final float DEFAULT_PRIMARY_TEXT_SIZE = 24;
    static final int DEFAULT_PRIMARY_TEXT_COLOR = Color.BLACK;
    static final int DEFAULT_SECONDARY_TEXT_COLOR = Color.parseColor("#61000000"); // alpha black
    static final int DEFAULT_KEY_COLOR = Color.LTGRAY;
    static final int DEFAULT_KEY_PRESSED_COLOR = Color.GRAY;
    static final int DEFAULT_KEY_BORDER_COLOR = Color.BLACK;
    static final int DEFAULT_KEY_BORDER_WIDTH = 0;
    static final int DEFAULT_KEY_BORDER_RADIUS = 5;
    static final int DEFAULT_KEY_PADDING = 2;
    static final int DEFAULT_POPUP_COLOR = Color.WHITE;
    static final int DEFAULT_POPUP_TEXT_COLOR = Color.BLACK;
    static final int DEFAULT_POPUP_HIGHLIGHT_COLOR = Color.GRAY;
    static final Theme DEFAULT_THEME = Theme.LIGHT;


    protected Theme mKeyboardTheme;
    protected int mPopupBackgroundColor;
    protected int mPopupHighlightColor;
    protected int mPopupTextColor;
    protected Typeface mTypeface;
    protected float mPrimaryTextSize;
    protected float mSecondaryTextSize;
    protected int mPrimaryTextColor;
    protected int mSecondaryTextColor;
    protected int mKeyColor;
    protected int mKeyPressedColor;
    protected int mKeyBorderColor;
    protected int mKeyBorderWidth;
    protected int mKeyBorderRadius;
    protected int mKeyPadding;

    private PopupKeyCandidatesView popupView;
    private PopupWindow popupWindow;


    // use a light image for a DARK theme and vice-versa
    public enum Theme {
        DARK,
        LIGHT
    }

    // This will map the button resource id to the String value that we want to
    // input when that key is clicked.
    //protected Map<Key, String> mKeyValues = new HashMap<>();
    protected Map<Key, String> mKeyPunctuationValues = new HashMap<>();

    protected boolean mIsShowingPunctuation = false;

    // Our communication link to the EditText/MongolEditText
    protected InputConnection inputConnection;
    protected String mComposing = null;

    protected KeyboardListener mKeyboardListener = null;

//    // These are special popup choice characters. They are being converted to a
//    // temporary medial composing form so that the automatic Unicode rendering
//    // will not confuse users (because they would get displayed as a final otherwise).
//
//    private static final String MEDIAL_A_FVS1 = "" + MongolCode.Uni.A + MongolCode.Uni.FVS1;
//    private static final String MEDIAL_A_FVS1_COMPOSING = "" + MongolCode.Uni.A + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ;
//    //    private static final String INITIAL_I_SUFFIX = "" + MongolCode.Uni.NNBS + MongolCode.Uni.I;
//    //    private static final String INITIAL_I_SUFFIX_COMPOSING = "" + MongolCode.Uni.NNBS + MongolCode.Uni.I + MongolCode.Uni.ZWJ;
//    private static final String MEDIAL_I_FVS1 = "" + MongolCode.Uni.I + MongolCode.Uni.FVS1;
//    private static final String MEDIAL_I_FVS1_COMPOSING = "" + MongolCode.Uni.I + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ;
//    private static final String MEDIAL_I_FVS2 = "" + MongolCode.Uni.I + MongolCode.Uni.FVS2;
//    private static final String MEDIAL_I_FVS2_COMPOSING = "" + MongolCode.Uni.I + MongolCode.Uni.FVS2 + MongolCode.Uni.ZWJ;
//    //private static final String MEDIAL_ZWJ_I = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.I;
//    //private static final String MEDIAL_ZWJ_I_COMPOSING = "" + MongolCode.Uni.ZWJ + MongolCode.Uni.I + MongolCode.Uni.ZWJ;
//
//    private static final String MEDIAL_U_FVS1 = "" + MongolCode.Uni.U + MongolCode.Uni.FVS1;
//    private static final String MEDIAL_U_FVS1_COMPOSING = "" + MongolCode.Uni.U + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ;
//    private static final String MEDIAL_UE_FVS2 = "" + MongolCode.Uni.UE + MongolCode.Uni.FVS2;
//    private static final String MEDIAL_UE_FVS2_COMPOSING = "" + MongolCode.Uni.UE + MongolCode.Uni.FVS2 + MongolCode.Uni.ZWJ;
//    private static final String MEDIAL_DOTTED_NA = "" + MongolCode.Uni.NA + MongolCode.Uni.FVS1;
//    private static final String MEDIAL_DOTTED_NA_COMPOSING = "" + MongolCode.Uni.NA + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ;
//    private static final String MEDIAL_TA_FVS1 = "" + MongolCode.Uni.TA + MongolCode.Uni.FVS1;
//    private static final String MEDIAL_TA_FVS1_COMPOSING = "" + MongolCode.Uni.TA + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ;
//    private static final String YA_FVS1 = "" + MongolCode.Uni.YA + MongolCode.Uni.FVS1;
//    private static final String YA_FVS1_COMPOSING = "" + MongolCode.Uni.YA + MongolCode.Uni.FVS1 + MongolCode.Uni.ZWJ;


    public Keyboard(Context context) {
        super(context);
        init(context);
    }

    public Keyboard(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public Keyboard(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }



    protected void initStyle(StyleBuilder style) {
        mPrimaryTextSize = style.keyPrimaryTextSize;
        mSecondaryTextSize = mPrimaryTextSize / 2;
        mPrimaryTextColor = style.keyPrimaryTextColor;
        mSecondaryTextColor = style.keySecondaryTextColor;
        mKeyboardTheme = style.keyboardTheme;
        mKeyColor = style.keyBackgroundColor;
        mKeyPressedColor = style.keyPressedColor;
        mKeyBorderColor = style.keyBorderColor;
        mKeyBorderWidth = style.keyBorderWidth;
        mKeyBorderRadius = style.keyBorderRadius;
        mKeyPadding = style.keySpacing;
        mPopupBackgroundColor = style.popupBackgroundColor;
        mPopupHighlightColor = style.popupHighlightColor;
        mPopupTextColor = style.popupTextColor;
    }

    // use default values if custom constructor is not used
    private void init(Context context) {
        mTypeface = MongolFont.get(MongolFont.QAGAN, context);
        mPrimaryTextSize = DEFAULT_PRIMARY_TEXT_SIZE;
        mSecondaryTextSize = mPrimaryTextSize / 2;
        mPrimaryTextColor = DEFAULT_PRIMARY_TEXT_COLOR;
        mSecondaryTextColor = DEFAULT_SECONDARY_TEXT_COLOR;
        mKeyboardTheme = DEFAULT_THEME;
        mKeyColor = DEFAULT_KEY_COLOR;
        mKeyPressedColor = DEFAULT_KEY_PRESSED_COLOR;
        mKeyBorderColor = DEFAULT_KEY_BORDER_COLOR;
        mKeyBorderWidth = DEFAULT_KEY_BORDER_WIDTH;
        mKeyBorderRadius = DEFAULT_KEY_BORDER_RADIUS;
        mKeyPadding = DEFAULT_KEY_PADDING;
        mPopupBackgroundColor = DEFAULT_POPUP_COLOR;
        mPopupHighlightColor = DEFAULT_POPUP_HIGHLIGHT_COLOR;
        mPopupTextColor = DEFAULT_POPUP_TEXT_COLOR;
    }

//    // FIXME this shouldn't be here
//    protected void initTextKey(KeyText textKey, String primary, String punctuation) {
//        textKey.setOnTouchListener(textKeyTouchListener);
//        //mKeyValues.put(textKey, primary);
//        mKeyPunctuationValues.put(textKey, punctuation);
//        addView(textKey);
//    }

    // number of keys and weights are initialized by keyboard subclass
    protected int[] mNumberOfKeysInRow;
    protected float[] mInsetWeightInRow;
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

            if (mInsetWeightInRow != null) {
                x += (totalWidth * mInsetWeightInRow[rowIndex]);
            }
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
                ((KeyText) child).setTextSize(mPrimaryTextSize);
                ((KeyText) child).setSubTextSize(mSecondaryTextSize);
                ((KeyText) child).setTextColor(mPrimaryTextColor);
                ((KeyText) child).setSubTextColor(mSecondaryTextColor);
            } else if (child instanceof KeyImage) {
                // TODO apply theme to key image
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
        void onRequestNewKeyboard(String keyboardDisplayName);
        PopupKeyCandidate[] getKeyboardCandidates();
    }

    public void setKeyboardListener(KeyboardListener listener) {
        this.mKeyboardListener = listener;
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
        if (mComposing != null && (newSelStart != candidatesEnd
                || newSelEnd != candidatesEnd)) {
            mComposing = null;
            // TODO updateCandidates();
            if (inputConnection != null) {
                inputConnection.finishComposingText();
            }
        }
    }


//    // TODO: move all this logic to the key
//    protected View.OnTouchListener textKeyTouchListener = new View.OnTouchListener() {
//
//        Handler handler;
//        final int LONGPRESS_THRESHOLD = 500; // milliseconds
//
//        PopupKeyCandidate popupView;
//        int popupWidth;
//        PopupWindow popupWindow;
//
//        @Override
//        public boolean onTouch(View view, final MotionEvent event) {
//
//            if (event.getPointerCount() > 1) return false;
//
//            final Key key = (Key) view;
//            int action = event.getActionMasked();
//
//            switch (action) {
//                case (MotionEvent.ACTION_DOWN):
//
//                    key.setPressed(true);
//
//                    Key.PopupCandidates candidates = getPopupCandidates(key);
//                    if (candidates != null && !candidates.isEmpty()) {
//                        int x = (int) event.getRawX();
//                        preparePopup(key, candidates, x);
//                    }
//                    return true;
//                case (MotionEvent.ACTION_MOVE):
//
//                    if (popupView != null) {
//                        int x = (int) event.getRawX();
//                        popupView.updateTouchPosition(x);
//                    }
//
//
//                    return true;
//                case (MotionEvent.ACTION_UP):
//
//                    if (inputConnection == null) {
//                        handlePopupChoice(key, event);
//                        return true;
//                    }
//
//
//                    if (popupView != null) {                                // handle popups
//                        handlePopupChoice(key, event);
//                    } else if (key == mKeyKeyboard) {                       // keyboard key
//
//                        mIsShowingPunctuation = !mIsShowingPunctuation;
//                        //setDisplayText(mIsShowingPunctuation);
//
//                    } else {                                                // other keys
//
//                        String inputText;
//                        if (mIsShowingPunctuation) {
//                            inputText = mKeyPunctuationValues.get(key);
//                        } else {
//                            inputText = "aaa";//mKeyValues.get(key);
//                        }
//
//                        // handle composing
//                        if (mComposing.length() > 0) {
//                            if (MongolCode.isMongolian(inputText.charAt(0))) {
//                                inputConnection.commitText(mComposing, 1);
//                            } else {
//                                inputConnection.finishComposingText();
//                            }
//                            mComposing.setLength(0);
//                        }
//
//                        // TODO add composing on initial DA
//                        // FIXME not necessarily wanted on all keyboards
////                        // TA/DA defaults to DA except in the INITIAL location
////                        if (inputText.equals(String.valueOf(MongolCode.Uni.DA))) {
////                            char prevChar = getPreviousChar();
////                            if (!MongolCode.isMongolian(prevChar)) {
////                                inputText = String.valueOf(MongolCode.Uni.TA);
////                            }
////                        }
//
//                        inputConnection.commitText(inputText, 1);
//                    }
//
//                    key.setPressed(false);
//                    if (handler != null) handler.removeCallbacksAndMessages(null);
//                    return true;
//                default:
//                    handlePopupChoice(key, event);
//                    return false;
//            }
//        }

//        private void preparePopup(final Key key, final Key.PopupCandidates candidates, final int xPosition) {
//
//            if (handler != null) {
//                handler.removeCallbacksAndMessages(null);
//            } else {
//                handler = new Handler();
//            }
//
//            final Runnable runnableCode = new Runnable() {
//                @Override
//                public void run() {
//
//                    if (popupWindow != null) return;
//
//                    // get the popup view
//                    popupView = new PopupKeyCandidate(getContext());
//                    popupView.setBackgroundColor(mPopupBackgroundColor);
//                    popupView.setTextColor(mPopupTextColor);
//
//                    // update the popup view with the candidate choices
//                    if (candidates == null || candidates.getUnicode() == null) return;
//                    popupView.setCandidates(candidates.getUnicode());
//                    if (candidates.getDisplay() == null) {
//                        popupView.setDisplayCandidates(candidates.getUnicode(), PopupKeyCandidate.DEFAULT_TEXT_SIZE);
//                    } else {
//                        popupView.setDisplayCandidates(candidates.getDisplay(), PopupKeyCandidate.DEFAULT_TEXT_SIZE);
//                    }
//
//                    popupView.setHighlightColor(mPopupHighlightColor);
//
//                    popupWindow = new PopupWindow(popupView,
//                            LinearLayout.LayoutParams.WRAP_CONTENT,
//                            LinearLayout.LayoutParams.WRAP_CONTENT);
//                    int location[] = new int[2];
//                    key.getLocationOnScreen(location);
//                    int measureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
//                    popupView.measure(measureSpec, measureSpec);
//                    popupWidth = popupView.getMeasuredWidth();
//                    int spaceAboveKey = key.getHeight() / 4;
//                    int x = xPosition - popupWidth / popupView.getChildCount() / 2;
//                    //int locationX = location[0]
//                    popupWindow.showAtLocation(key, Gravity.NO_GRAVITY,
//                            x, location[1] - popupView.getMeasuredHeight() - spaceAboveKey);
//
//
//                    // highlight current item (after the popup window has loaded)
//                    handler.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            int x = xPosition;
//                            popupView.updateTouchPosition(x);
//                        }
//                    });
//
//                }
//            };
//
//            handler.postDelayed(runnableCode, LONGPRESS_THRESHOLD);
//        }
//
//        private void handlePopupChoice(Key key, MotionEvent event) {
//
//            key.setPressed(false);
//
//            if (handler != null) {
//                handler.removeCallbacksAndMessages(null);
//            }
//            if (popupWindow == null) return;
//
//            int x = (int) event.getRawX();
//            CharSequence selectedItem = popupView.getCurrentItem(x);
//
//            if (TextUtils.isEmpty(selectedItem)) {
//                dismissPopup();
//                return;
//            }
//
//            if (key == mKeyKeyboard) {
//                CharSequence name = popupView.getCurrentItem(x);
//                mKeyboardListener.onRequestNewKeyboard(name.toString());
//                dismissPopup();
//                return;
//            }
//
//            if (inputConnection == null) {
//                dismissPopup();
//                return;
//            }
//
//            inputConnection.beginBatchEdit();
//
//            if (mComposing.length() > 0) {
//                inputConnection.commitText(mComposing, 1);
//                mComposing.setLength(0);
//            }
//
//            // add composing text for certain medials to avoid confusion with finals
//            if (selectedItem.equals(MEDIAL_A_FVS1)) {
//                inputConnection.setComposingText(MEDIAL_A_FVS1_COMPOSING, 1);
//                mComposing.append(MEDIAL_A_FVS1);
//            } else if (selectedItem.equals(MEDIAL_I_FVS1)) {
//                inputConnection.setComposingText(MEDIAL_I_FVS1_COMPOSING, 1);
//                mComposing.append(MEDIAL_I_FVS1);
//            } else if (selectedItem.equals(MEDIAL_I_FVS2)) {
//                inputConnection.setComposingText(MEDIAL_I_FVS2_COMPOSING, 1);
//                mComposing.append(MEDIAL_I_FVS2);
//            //} else if (selectedItem.equals(MEDIAL_ZWJ_I)) {
//            //    inputConnection.setComposingText(MEDIAL_ZWJ_I_COMPOSING, 1);
//            //    mComposing.append(MEDIAL_ZWJ_I);
//            } else if (selectedItem.equals(MEDIAL_U_FVS1)) {
//                inputConnection.setComposingText(MEDIAL_U_FVS1_COMPOSING, 1);
//                mComposing.append(MEDIAL_U_FVS1);
//            } else if (selectedItem.equals(MEDIAL_UE_FVS2)) {
//                inputConnection.setComposingText(MEDIAL_UE_FVS2_COMPOSING, 1);
//                mComposing.append(MEDIAL_UE_FVS2);
//            } else if (selectedItem.equals(MEDIAL_DOTTED_NA)) {
//                inputConnection.setComposingText(MEDIAL_DOTTED_NA_COMPOSING, 1);
//                mComposing.append(MEDIAL_DOTTED_NA);
//            } else if (selectedItem.equals(MEDIAL_TA_FVS1)) {
//                inputConnection.setComposingText(MEDIAL_TA_FVS1_COMPOSING, 1);
//                mComposing.append(MEDIAL_TA_FVS1);
//            } else if (selectedItem.equals(YA_FVS1)) {
//                inputConnection.setComposingText(YA_FVS1_COMPOSING, 1);
//                mComposing.append(YA_FVS1);
//            } else {
//                inputConnection.commitText(selectedItem, 1);
//            }
//
//            inputConnection.endBatchEdit();
//
//            dismissPopup();
//        }
//
//        private void dismissPopup() {
//            if (popupWindow != null)
//                popupWindow.dismiss();
//            popupView = null;
//            popupWindow = null;
//        }
//    };



//    protected View.OnTouchListener handleBackspace = new View.OnTouchListener() {
//
//        private Handler handler;
//        final int INITIAL_DELAY = 500;
//        final int REPEAT_DELAY = 50;
//
//        @Override
//        public boolean onTouch(View view, MotionEvent event) {
//
//            switch (event.getAction()) {
//                case MotionEvent.ACTION_DOWN:
//                    view.setPressed(true);
//                    doBackspace();
//                    if (handler != null)
//                        return true;
//                    handler = new Handler();
//                    handler.postDelayed(actionBackspace, INITIAL_DELAY);
//                    break;
//                case MotionEvent.ACTION_MOVE:
//                    break;
//                default:
//                    view.setPressed(false);
//                    if (handler == null)
//                        return true;
//                    handler.removeCallbacks(actionBackspace);
//                    handler = null;
//                    break;
//            }
//
//            return true;
//        }
//
//        private void doBackspace() {
//            if (inputConnection == null) return;
//
//            if (mComposing.length() > 0) {
//                inputConnection.commitText("", 1);
//                mComposing.setLength(0);
//            } else {
//
//                inputConnection.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL));
//                inputConnection.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_DEL));
//
//                // We could also do this with inputConnection.deleteSurroundingText(1, 0)
//                // but then we would need to be careful of not deleting too much
//                // and not deleting half a surrogate pair.
//                // see https://developer.android.com/reference/android/view/inputmethod/InputConnection.html#deleteSurroundingText(int,%20int)
//                // see also https://stackoverflow.com/a/45182401
//            }
//
//
//        }
//
//        Runnable actionBackspace = new Runnable() {
//            @Override
//            public void run() {
//                doBackspace();
//                handler.postDelayed(this, REPEAT_DELAY);
//            }
//        };
//
//    };

//    protected View.OnClickListener handleShift = new OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            onShiftClick();
//        }
//    };

//    // subclasses can override this if using a shift key
//    protected boolean shiftIsOn = false;
//    protected void onShiftClick() { }

    protected Bitmap getReturnImage() {
        int imageResourceId;
        if (mKeyboardTheme == Theme.LIGHT) {
            imageResourceId = R.drawable.ic_keyboard_return_black_48dp;
        } else {
            imageResourceId = R.drawable.ic_keyboard_return_white_48dp;
        }
        return BitmapFactory.decodeResource(getResources(), imageResourceId);
    }

    protected Bitmap getBackspaceImage() {
        int imageResourceId;
        if (mKeyboardTheme == Theme.LIGHT) {
            imageResourceId = R.drawable.ic_keyboard_backspace_black_48dp;
        } else {
            imageResourceId = R.drawable.ic_keyboard_backspace_white_48dp;
        }
        return BitmapFactory.decodeResource(getResources(), imageResourceId);
    }

    protected Bitmap getKeyboardImage() {
        int imageResourceId;
        if (mKeyboardTheme == Theme.LIGHT) {
            imageResourceId = R.drawable.ic_keyboard_black_48dp;
        } else {
            imageResourceId = R.drawable.ic_keyboard_white_48dp;
        }
        return BitmapFactory.decodeResource(getResources(), imageResourceId);
    }

//    // TODO either change them all to 32dp or change this to 48dp
//    protected Bitmap getShiftImage(KeyImage.Theme theme) {
//        int imageResourceId;
//        if (theme == KeyImage.Theme.LIGHT) {
//            imageResourceId = R.drawable.ic_keyboard_shift_black_32dp;
//        } else {
//            imageResourceId = R.drawable.ic_keyboard_shift_white_32dp;
//        }
//        return BitmapFactory.decodeResource(getResources(), imageResourceId);
//    }

    protected char getPreviousChar() {
        if (inputConnection == null) return 0;
        CharSequence previous = inputConnection.getTextBeforeCursor(1, 0);
        if (TextUtils.isEmpty(previous)) return 0;
        return previous.charAt(0);
    }

    // this may not actually return a whole word if the word is very long
    protected String getPreviousMongolWord() {
        if (inputConnection == null) return "";
        int numberOfCharsToGet = 20;
        CharSequence previous = inputConnection.getTextBeforeCursor(numberOfCharsToGet, 0);
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

    protected PopupKeyCandidate[] getCandidatesForSuffix() {
        String previousWord = getPreviousMongolWord();
        if (TextUtils.isEmpty(previousWord)) {
            return new PopupKeyCandidate[] {new PopupKeyCandidate(MongolCode.Uni.NNBS)};
        }
        // TODO if it is a number then return the right suffix for that
        char lastChar = previousWord.charAt(previousWord.length() - 1);
        MongolCode.Gender gender = MongolCode.getWordGender(previousWord);
        if (gender == null) {
            return PopupKeyCandidate.createArray(MongolCode.Uni.NNBS);
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

        String[] unicode = new String[]{
                "" + MongolCode.Uni.NNBS,
                uuSuffix,
                yinUnUSuffix,
                iYiSuffix,
                duTuSuffix,
                barIyarSuffix,
                banIyanSuffix,
                achaSuffix,
                udSuffix};
        return PopupKeyCandidate.createArray(unicode);
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

    public PopupKeyCandidate[] getCandidatesForKeyboard() {
        if (mKeyboardListener == null) return null;
        return mKeyboardListener.getKeyboardCandidates();
    }

    abstract public PopupKeyCandidate[] getPopupCandidates(Key key);

    // TODO do we need this?
    // in this method you should switch the display on the keys for normal or punctuation mode
    //abstract public void setDisplayText(boolean isShowingPunctuation);

    // subclasses should return the name of the keyboard to display in the keyboard chooser popup
    abstract public String getDisplayName();


    // TODO move these to KeyText or Key
//    /**
//     * these are the choices for a popup key
//     */
//    public static class PopupCandidates {
//
//        private String[] unicode;
//        private String[] display;
//
//        /**
//         * Convenience constructor for PopupCandidates(String[] unicode)
//         *
//         * @param unicode the unicode values for the popup items
//         */
//        public PopupCandidates(char unicode) {
//            this(new String[]{String.valueOf(unicode)});
//        }
//
//        /**
//         * Convenience constructor for PopupCandidates(String[] unicode)
//         *
//         * @param unicode the unicode values for the popup items
//         */
//        public PopupCandidates(String unicode) {
//            this(new String[]{unicode});
//        }
//
//        /**
//         * @param unicode the unicode values for the popup items
//         */
//        public PopupCandidates(String[] unicode) {
//            this(unicode, null);
//        }
//
//        /**
//         * @param unicode the unicode values for the popup items
//         * @param display the value to display if different than the unicode values
//         */
//        public PopupCandidates(String[] unicode, String[] display) {
//            if (display != null) {
//                if (display.length != unicode.length)
//                    throw new IllegalArgumentException(
//                            "The number of display items must " +
//                                    "be the same as the number of unicode items.");
//            }
//            this.unicode = unicode;
//            this.display = display;
//        }
//
//        public boolean isEmpty() {
//            if (unicode == null) return true;
//            if (unicode.length == 0) return true;
//            if (unicode.length == 1 && TextUtils.isEmpty(unicode[0])) return true;
//            return false;
//        }
//
//        public String[] getUnicode() {
//            return unicode;
//        }
//
//        public String[] getDisplay() {
//            return display;
//        }
//    }



    public static class StyleBuilder {

        private int keyBackgroundColor = DEFAULT_KEY_COLOR;
        private int keyPressedColor = DEFAULT_KEY_PRESSED_COLOR;
        private int keyBorderColor = DEFAULT_KEY_BORDER_COLOR;
        private int keyBorderRadius = DEFAULT_KEY_BORDER_RADIUS;
        private int keyBorderWidth = DEFAULT_KEY_BORDER_WIDTH;
        private int popupBackgroundColor = DEFAULT_POPUP_COLOR;
        private int popupTextColor = DEFAULT_POPUP_TEXT_COLOR;
        private int popupHighlightColor = DEFAULT_POPUP_HIGHLIGHT_COLOR;
        private int keyPrimaryTextColor = DEFAULT_PRIMARY_TEXT_COLOR;
        private int keySecondaryTextColor = DEFAULT_SECONDARY_TEXT_COLOR;
        private float keyPrimaryTextSize = DEFAULT_PRIMARY_TEXT_SIZE;
        private int keySpacing = DEFAULT_KEY_PADDING;
        private Theme keyboardTheme = DEFAULT_THEME;

        public StyleBuilder setKeyTextSize(float keyTextSize) {
            this.keyPrimaryTextSize = keyTextSize;
            return this;
        }

        public StyleBuilder setKeyBackgroundColor(int keyBackgroundColor) {
            this.keyBackgroundColor = keyBackgroundColor;
            return this;
        }

        public StyleBuilder setKeyBorderColor(int keyBorderColor) {
            this.keyBorderColor = keyBorderColor;
            return this;
        }

        public StyleBuilder setKeyBorderRadius(int keyBorderRadius) {
            this.keyBorderRadius = keyBorderRadius;
            return this;
        }

        public StyleBuilder setKeyBorderWidth(int keyBorderWidth) {
            this.keyBorderWidth = keyBorderWidth;
            return this;
        }

        public StyleBuilder setPopupBackgroundColor(int popupBackgroundColor) {
            this.popupBackgroundColor = popupBackgroundColor;
            return this;
        }

        public StyleBuilder setPopupTextColor(int popupTextColor) {
            this.popupTextColor = popupTextColor;
            return this;
        }

        public StyleBuilder setKeyPrimaryTextColor(int keyPrimaryTextColor) {
            this.keyPrimaryTextColor = keyPrimaryTextColor;
            return this;
        }

        public StyleBuilder setKeySecondaryTextColor(int keySecondaryTextColor) {
            this.keySecondaryTextColor = keySecondaryTextColor;
            return this;
        }

        public StyleBuilder setKeySpacing(int keySpacing) {
            this.keySpacing = keySpacing;
            return this;
        }

        public StyleBuilder setPopupHighlightColor(int popupHighlightColor) {
            this.popupHighlightColor = popupHighlightColor;
            return this;
        }

        public StyleBuilder setKeyPressedColor(int keyPressedColor) {
            this.keyPressedColor = keyPressedColor;
            return this;
        }

        /**
         *
         * @param theme Theme.DARK for a light image
         *                  or Theme.LIGHT for a dark image.
         * @return StyleBuilder
         */
        public StyleBuilder setKeyboardTheme(Theme theme) {
            this.keyboardTheme = theme;
            return this;
        }
    }



    // KeyListener methods

    @Override
    public void onKeyInput(String text) {
        if (inputConnection == null) return;
        handleOldComposingText(text);
        inputConnection.commitText(text, 1);
    }

    private void handleOldComposingText(String inputText) {
        //if (inputConnection == null) return;
        if (mComposing == null) return;
        if (MongolCode.isMongolian(inputText.charAt(0))) {
            inputConnection.commitText(mComposing, 1);
        } else {
            inputConnection.finishComposingText();
        }
        mComposing = null;
    }


    @Override
    public boolean getIsShowingPopup() {
        return popupView != null;
    }

    @Override
    public void showPopup(Key key, final int xPosition) {
        PopupKeyCandidate[] popupCandidates = getPopupCandidates(key);
        if (popupCandidates == null || popupCandidates.length == 0) return;
        popupView = getPopupView();
        popupView.setCandidates(popupCandidates);
        layoutAndShowPopupWindow(key, xPosition);
        highlightCurrentItemAfterPopupWindowHasLoaded(key, xPosition);

    }

    private PopupKeyCandidatesView getPopupView() {
        PopupKeyCandidatesView popupView = new PopupKeyCandidatesView(getContext());
        popupView.setBackgroundColor(mPopupBackgroundColor);
        popupView.setTextColor(mPopupTextColor);
        popupView.setHighlightColor(mPopupHighlightColor);
        return popupView;
    }

    private void layoutAndShowPopupWindow(Key key, int xPosition) {
        popupWindow = new PopupWindow(popupView,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        int location[] = new int[2];
        key.getLocationOnScreen(location);
        int measureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        popupView.measure(measureSpec, measureSpec);
        int popupWidth = popupView.getMeasuredWidth();
        int spaceAboveKey = key.getHeight() / 4;
        int x = xPosition - popupWidth / popupView.getChildCount() / 2;
        int y = location[1] - popupView.getMeasuredHeight() - spaceAboveKey;
        popupWindow.showAtLocation(key, Gravity.NO_GRAVITY, x, y);
    }

    private void highlightCurrentItemAfterPopupWindowHasLoaded(Key key, final int xPosition) {
        key.post(new Runnable() {
            @Override
            public void run() {
                popupView.updateTouchPosition(xPosition);
            }
        });
    }

    @Override
    public void updatePopup(int xPosition) {
        if (popupView == null) return;
        popupView.updateTouchPosition(xPosition);
    }

    @Override
    public void finishPopup(int xPosition) {
        if (popupWindow == null) return;
        PopupKeyCandidate selectedItem = popupView.getCurrentItem(xPosition);
        inputPopupChoice(selectedItem);
        dismissPopup();
    }

    private void inputPopupChoice(PopupKeyCandidate choice) {
        if (inputConnection == null) return;
        if (choice == null) return;
        String composingText = choice.getComposing();
        if (TextUtils.isEmpty(composingText)) {
            String text = choice.getUnicode();
            handleOldComposingText(text);
            inputConnection.commitText(text, 1);
        }
        else
            setComposing(choice);
    }

    private void setComposing(PopupKeyCandidate popupChoice) {
        handleOldComposingText(popupChoice.getUnicode());
        inputConnection.setComposingText(popupChoice.getComposing(), 1);
        mComposing = popupChoice.getUnicode();
    }

//    @Override
//    public String getPopupChoiceOnFinish(int xPosition) {
//        if (popupWindow == null) return null;
//
//        CharSequence selectedItem = popupView.getCurrentItem(xPosition);
//        dismissPopup();
//        return selectedItem.toString();
//    }

    private void dismissPopup() {
        if (popupWindow != null)
            popupWindow.dismiss();
        popupView = null;
        popupWindow = null;
    }

    @Override
    public void onBackspace() {
        if (inputConnection == null) return;

        if (mComposing != null) {
            inputConnection.commitText("", 1);
            mComposing = null;
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


    @Override
    public void onNewKeyboardChosen(int xPosition) {
        PopupKeyCandidate selectedKeyboard = popupView.getCurrentItem(xPosition);
        mKeyboardListener.onRequestNewKeyboard(selectedKeyboard.getUnicode());
    }
}
