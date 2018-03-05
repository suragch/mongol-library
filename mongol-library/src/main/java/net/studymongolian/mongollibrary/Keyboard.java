package net.studymongolian.mongollibrary;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
//import android.view.inputmethod.InputConnection;
import android.widget.LinearLayout;
import android.widget.PopupWindow;


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
    static final CandidatesPreference DEFAULT_CANDIDATES_PREFERENCE = CandidatesPreference.NONE;


    private Theme mKeyboardTheme;
    private int mPopupBackgroundColor;
    private int mPopupHighlightColor;
    private int mPopupTextColor;
    private Typeface mTypeface;
    private float mPrimaryTextSize;
    private float mSecondaryTextSize;
    private int mPrimaryTextColor;
    private int mSecondaryTextColor;
    private int mKeyColor;
    private int mKeyPressedColor;
    private int mKeyBorderColor;
    private int mKeyBorderWidth;
    private int mKeyBorderRadius;
    private int mKeyPadding;
    private CandidatesPreference mCandidatesPreference;

    private PopupKeyCandidatesView popupView;
    private PopupWindow popupWindow;

    // use a light image for a DARK theme and vice-versa
    public enum Theme {
        DARK,
        LIGHT
    }

    public enum CandidatesPreference {
        VERTICAL_LEFT,
        HORIZONTAL_TOP,
        NONE
    }

    protected boolean mIsShowingPunctuation = false;

    // number of keys and weights are initialized by keyboard subclass
    protected int[] mNumberOfKeysInRow;
    protected float[] mInsetWeightInRow;
    protected float[] mKeyWeights;

    protected KeyboardListener mKeyboardListener = null;

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
        mCandidatesPreference = style.candidatesPreference;
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
        mCandidatesPreference = DEFAULT_CANDIDATES_PREFERENCE;
    }

    public interface KeyboardListener {
        void onRequestNewKeyboard(String keyboardDisplayName);
        PopupKeyCandidate[] getKeyboardKeyCandidates();
        char getPreviousChar();
        boolean insertLocationIsIsolateOrInitial();
        void onKeyboardInput(String text);
        void onKeyPopupInput(PopupKeyCandidate popupKeyCandidate);
        void onBackspace();
    }

    public void setKeyboardListener(KeyboardListener listener) {
        this.mKeyboardListener = listener;
    }

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

                if (child instanceof KeyShift) {
                    ((KeyShift) child).setCapsStateIndicatorColor(mPrimaryTextColor);
                }
            }

            child.setKeyColor(mKeyColor);
            child.setPressedColor(mKeyPressedColor);
            child.setBorderColor(mKeyBorderColor);
            child.setBorderWidth(mKeyBorderWidth);
            child.setBorderRadius(mKeyBorderRadius);
            child.setPadding(mKeyPadding, mKeyPadding, mKeyPadding, mKeyPadding);
        }
    }

    // TODO change to 32dp
    protected Bitmap getReturnImage() {
        int imageResourceId;
        if (mKeyboardTheme == Theme.LIGHT) {
            imageResourceId = R.drawable.ic_keyboard_return_black_48dp;
        } else {
            imageResourceId = R.drawable.ic_keyboard_return_white_48dp;
        }
        return BitmapFactory.decodeResource(getResources(), imageResourceId);
    }

    // TODO change to 32dp
    protected Bitmap getBackspaceImage() {
        int imageResourceId;
        if (mKeyboardTheme == Theme.LIGHT) {
            imageResourceId = R.drawable.ic_keyboard_backspace_black_48dp;
        } else {
            imageResourceId = R.drawable.ic_keyboard_backspace_white_48dp;
        }
        return BitmapFactory.decodeResource(getResources(), imageResourceId);
    }

    // TODO change to 32dp
    protected Bitmap getKeyboardImage() {
        int imageResourceId;
        if (mKeyboardTheme == Theme.LIGHT) {
            imageResourceId = R.drawable.ic_keyboard_black_48dp;
        } else {
            imageResourceId = R.drawable.ic_keyboard_white_48dp;
        }
        return BitmapFactory.decodeResource(getResources(), imageResourceId);
    }

    protected char getPreviousChar() {
        if (mKeyboardListener == null) return 0;
        return mKeyboardListener.getPreviousChar();
    }

//    // this may not actually return a whole word if the word is very long
//    // TODO remove from here. This is only used for getting suffix candidates
//    protected String getPreviousMongolWord() {
//        if (inputConnection == null) return "";
//        int numberOfCharsToGet = 20;
//        CharSequence previous = inputConnection.getTextBeforeCursor(numberOfCharsToGet, 0);
//        if (TextUtils.isEmpty(previous)) return "";
//        int startIndex = previous.length() - 1;
//        char charAtIndex = previous.charAt(startIndex);
//        if (charAtIndex == ' ' || charAtIndex == MongolCode.Uni.NNBS) startIndex--;
//        StringBuilder mongolWord = new StringBuilder();
//        for (int i = startIndex; i >= 0; i--) {
//            charAtIndex = previous.charAt(i);
//            if (MongolCode.isMongolian(charAtIndex)) {
//                mongolWord.insert(0, charAtIndex);
//            } else if (charAtIndex == ' ' || charAtIndex == MongolCode.Uni.NNBS) {
//                break;
//            }
//        }
//        return mongolWord.toString();
//    }
//
//    protected PopupKeyCandidate[] getCandidatesForSuffix() {
//        String previousWord = getPreviousMongolWord();
//        if (TextUtils.isEmpty(previousWord)) {
//            return new PopupKeyCandidate[] {new PopupKeyCandidate(MongolCode.Uni.NNBS)};
//        }
//        // TODO if it is a number then return the right suffix for that
//        char lastChar = previousWord.charAt(previousWord.length() - 1);
//        MongolCode.Gender gender = MongolCode.getWordGender(previousWord);
//        if (gender == null) {
//            return PopupKeyCandidate.createArray(MongolCode.Uni.NNBS);
//        }
//        String duTuSuffix = MongolCode.getSuffixTuDu(gender, lastChar);
//        String iYiSuffix = MongolCode.getSuffixYiI(lastChar);
//        String yinUnUSuffix = MongolCode.getSuffixYinUnU(gender, lastChar);
//        String achaSuffix = MongolCode.getSuffixAchaEche(gender);
//        String barIyarSuffix = MongolCode.getSuffixBarIyar(gender, lastChar);
//        String taiSuffix = MongolCode.getSuffixTaiTei(gender);
//        String uuSuffix = MongolCode.getSuffixUu(gender);
//        String banIyanSuffix = MongolCode.getSuffixBanIyan(gender, lastChar);
//        String udSuffix = MongolCode.getSuffixUd(gender);
//
//        String[] unicode = new String[]{
//                "" + MongolCode.Uni.NNBS,
//                uuSuffix,
//                yinUnUSuffix,
//                iYiSuffix,
//                duTuSuffix,
//                barIyarSuffix,
//                banIyanSuffix,
//                achaSuffix,
//                udSuffix};
//        return PopupKeyCandidate.createArray(unicode);
//    }

    protected boolean isIsolateOrInitial() {
        if (mKeyboardListener == null) return true;
        return mKeyboardListener.insertLocationIsIsolateOrInitial();
    }

    public PopupKeyCandidate[] getCandidatesForKeyboardKey() {
        if (mKeyboardListener == null) return null;
        return mKeyboardListener.getKeyboardKeyCandidates();
    }

    abstract public PopupKeyCandidate[] getPopupCandidates(Key key);

    // subclasses should return the name of the keyboard to display in the
    // keyboard chooser popup
    abstract public String getDisplayName();

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
        private CandidatesPreference candidatesPreference = DEFAULT_CANDIDATES_PREFERENCE;

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

        public StyleBuilder setCandidatesPreference(CandidatesPreference preference) {
            this.candidatesPreference = preference;
            return this;
        }
    }

    public void setCandidatesPreference(CandidatesPreference preference) {
        mCandidatesPreference = preference;
    }

    public CandidatesPreference getCandidatesPreference() {
        return mCandidatesPreference;
    }

    public int getKeyPadding() {
        return mKeyPadding;
    }

    public int getKeyColor() {
        return mKeyColor;
    }

    public Theme getKeyboardTheme() {
        return mKeyboardTheme;
    }

    // KeyListener methods

    @Override
    public void onKeyInput(String text) {
        if (mKeyboardListener == null) return;
        mKeyboardListener.onKeyboardInput(text);
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
        if (mKeyboardListener == null) return;
        mKeyboardListener.onKeyPopupInput(choice);
    }

    private void dismissPopup() {
        if (popupWindow != null)
            popupWindow.dismiss();
        popupView = null;
        popupWindow = null;
    }

    @Override
    public void onBackspace() {
        if (mKeyboardListener == null) return;
        mKeyboardListener.onBackspace();
    }

    @Override
    public void onNewKeyboardChosen(int xPosition) {
        if (mKeyboardListener == null) return;
        PopupKeyCandidate selectedKeyboard = popupView.getCurrentItem(xPosition);
        dismissPopup();
        if (selectedKeyboard == null) return;
        mKeyboardListener.onRequestNewKeyboard(selectedKeyboard.getUnicode());
    }

    @Override
    public void onShiftChanged(boolean isShiftOn) {
        // Keyboard subclasses can override this if they have a shift key
    }
}
