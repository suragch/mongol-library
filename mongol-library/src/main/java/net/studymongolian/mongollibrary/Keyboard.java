package net.studymongolian.mongollibrary;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.Gravity;
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
    static final KeyImage.Theme DEFAULT_KEY_IMAGE_THEME = KeyImage.Theme.LIGHT;
    static final int DEFAULT_KEY_BORDER_COLOR = Color.BLACK;
    static final int DEFAULT_KEY_BORDER_WIDTH = 0;
    static final int DEFAULT_KEY_BORDER_RADIUS = 5;
    static final int DEFAULT_KEY_PADDING = 2;
    static final int DEFAULT_POPUP_COLOR = Color.WHITE;
    static final int DEFAULT_POPUP_TEXT_COLOR = Color.BLACK;
    static final int DEFAULT_POPUP_HIGHLIGHT_COLOR = Color.GRAY;
    static final CandidatesLocation DEFAULT_CANDIDATES_LOCATION = CandidatesLocation.NONE;



    private KeyImage.Theme mKeyImageTheme;
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

    // this is not set with styling
    private CandidatesLocation mCandidatesLocation;

    private PopupKeyCandidatesView popupView;
    private PopupWindow popupWindow;



    public enum CandidatesLocation {
        VERTICAL_LEFT,
        HORIZONTAL_TOP,
        NONE;

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

//    protected void initStyle(StyleBuilder style) {

    // use default values if custom constructor is not used
    private void init(Context context) {
        mTypeface = MongolFont.get(MongolFont.QAGAN, context);
        mPrimaryTextSize = DEFAULT_PRIMARY_TEXT_SIZE;
        mSecondaryTextSize = mPrimaryTextSize / 2;
        mPrimaryTextColor = DEFAULT_PRIMARY_TEXT_COLOR;
        mSecondaryTextColor = DEFAULT_SECONDARY_TEXT_COLOR;
        mKeyImageTheme = DEFAULT_KEY_IMAGE_THEME;
        mKeyColor = DEFAULT_KEY_COLOR;
        mKeyPressedColor = DEFAULT_KEY_PRESSED_COLOR;
        mKeyBorderColor = DEFAULT_KEY_BORDER_COLOR;
        mKeyBorderWidth = DEFAULT_KEY_BORDER_WIDTH;
        mKeyBorderRadius = DEFAULT_KEY_BORDER_RADIUS;
        mKeyPadding = DEFAULT_KEY_PADDING;
        mPopupBackgroundColor = DEFAULT_POPUP_COLOR;
        mPopupHighlightColor = DEFAULT_POPUP_HIGHLIGHT_COLOR;
        mPopupTextColor = DEFAULT_POPUP_TEXT_COLOR;
        mCandidatesLocation = DEFAULT_CANDIDATES_LOCATION;
    }

    public interface KeyboardListener {

        void onRequestNewKeyboard(String keyboardDisplayName);
        PopupKeyCandidate[] getKeyboardKeyCandidates();
        char getPreviousChar();
        boolean insertLocationIsIsolateOrInitial();
        void onKeyboardInput(String text);
        void onKeyPopupChosen(PopupKeyCandidate popupKeyCandidate);
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
        if (mKeyImageTheme == KeyImage.Theme.LIGHT) {
            imageResourceId = R.drawable.ic_keyboard_return_black_48dp;
        } else {
            imageResourceId = R.drawable.ic_keyboard_return_white_48dp;
        }
        return BitmapFactory.decodeResource(getResources(), imageResourceId);
    }

    // TODO change to 32dp
    protected Bitmap getBackspaceImage() {
        int imageResourceId;
        if (mKeyImageTheme == KeyImage.Theme.LIGHT) {
            imageResourceId = R.drawable.ic_keyboard_backspace_black_48dp;
        } else {
            imageResourceId = R.drawable.ic_keyboard_backspace_white_48dp;
        }
        return BitmapFactory.decodeResource(getResources(), imageResourceId);
    }

    // TODO change to 32dp
    protected Bitmap getKeyboardImage() {
        int imageResourceId;
        if (mKeyImageTheme == KeyImage.Theme.LIGHT) {
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

    public void setCandidatesLocation(CandidatesLocation location) {
        mCandidatesLocation = location;
    }



    public CandidatesLocation getCandidatesLocation() {
        return mCandidatesLocation;
    }

    public int getKeyPadding() {
        return mKeyPadding;
    }

    public int getKeyColor() {
        return mKeyColor;
    }

    public KeyImage.Theme getKeyboardTheme() {
        return mKeyImageTheme;
    }

    public void setSecondaryTextSize(float secondaryTextSize) {
        this.mSecondaryTextSize = secondaryTextSize;
        invalidate();
    }

    public void setPrimaryTextColor(int primaryTextColor) {
        this.mPrimaryTextColor = primaryTextColor;
        invalidate();
    }

    public void setPrimaryTextSize(float size) {
        this.mPrimaryTextSize = size;
        invalidate();
    }

    public void setSecondaryTextColor(int secondaryTextColor) {
        this.mSecondaryTextColor = secondaryTextColor;
        invalidate();
    }

    public void setKeyImageTheme(KeyImage.Theme keyImageTheme) {
        this.mKeyImageTheme = keyImageTheme;
        invalidate();
    }

    public void setKeyColor(int keyColor) {
        this.mKeyColor = keyColor;
        invalidate();
    }

    public void setKeyPressedColor(int keyPressedColor) {
        this.mKeyPressedColor = keyPressedColor;
        invalidate();
    }

    public void setKeyBorderColor(int keyBorderColor) {
        this.mKeyBorderColor = keyBorderColor;
        invalidate();
    }

    public void setKeyBorderWidth(int keyBorderWidth) {
        this.mKeyBorderWidth = keyBorderWidth;
        invalidate();
    }

    public void setKeyBorderRadius(int keyBorderRadius) {
        this.mKeyBorderRadius = keyBorderRadius;
        invalidate();
    }

    public void setKeyPadding(int keyPadding) {
        this.mKeyPadding = keyPadding;
        invalidate();
    }

    public void setPopupBackgroundColor(int popupBackgroundColor) {
        this.mPopupBackgroundColor = popupBackgroundColor;
        invalidate();
    }

    public void setPopupHighlightColor(int popupHighlightColor) {
        this.mPopupHighlightColor = popupHighlightColor;
        invalidate();
    }

    public void setPopupTextColor(int popupTextColor) {
        this.mPopupTextColor = popupTextColor;
        invalidate();
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
        mKeyboardListener.onKeyPopupChosen(choice);
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
