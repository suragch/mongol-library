package net.studymongolian.mongollibrary;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.view.inputmethod.InputConnection;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Input Method Container
 * <p>
 * Currently it is a container/controller for Keyboards and suggested word candidates. In the future
 * it could also be a container for other IME views (like handwriting recognition or speech-to-text).
 * <p>
 * The word candidates may be arranged vertically on the left of horizontally at the top.
 * <p>
 * ImeContainer manages switching keyboards and handling communication between the keyboard and the
 * word suggestion candidates list.
 */
public class ImeContainer extends ViewGroup
        implements Keyboard.KeyboardListener, ImeCandidatesView.CandidateClickListener {

    //static final Theme DEFAULT_THEME = Theme.LIGHT;

    private static final float DEFAULT_VERTICAL_CANDIDATE_VIEW_PROPORTION = 1 / 10f;
    private static final float DEFAULT_HORIZONTAL_CANDIDATE_VIEW_PROPORTION = 1 / 5f;


    private Context mContext;
    private List<Keyboard> mKeyboards;
    private Keyboard mCurrentKeyboard;
    private ImeCandidatesView mCandidatesView;
    //private KeyboardCandidatesAdapter mCandidatesAdapter;
    private WeakReference<MongolInputMethodManager> mimm;
    private DataSource mDataSource = null;
    private CharSequence mComposing = "";
    private InputConnection mInputConnection;

    public ImeContainer(Context context) {
        super(context, null, 0);
        init(context);
    }

    public ImeContainer(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        init(context);
    }

    public ImeContainer(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
    }

    public interface DataSource {

        public List<String> onRequestWordsStartingWith(String text);

        public List<String> onRequestWordsFollowing(String word);
    }

    // provide a way for another class to set the listener
    public void setDataSource(DataSource dataSource) {
        this.mDataSource = dataSource;
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {

        //  Keyboard.CandidateLocation
        //  VERTICAL_LEFT                  HORIZONTAL_TOP                 NONE
        //  ___________________________    ___________________________    ___________________________
        //  | C |                     |    |       Candidates        |    |                         |
        //  | a |                     |    |_________________________|    |                         |
        //  | n |                     |    |                         |    |                         |
        //  | d |                     |    |                         |    |                         |
        //  | i |                     |    |                         |    |                         |
        //  | d |     Keyboard        | or |        Keyboard         | or |        Keyboard         |
        //  | a |                     |    |                         |    |                         |
        //  | t |                     |    |                         |    |                         |
        //  | e |                     |    |                         |    |                         |
        //  | s |                     |    |                         |    |                         |
        //  ---------------------------    ---------------------------    ---------------------------

        if (getChildCount() == 0) return;

        Keyboard.CandidatesLocation candidateLocation = getCandidateViewLocation();
        switch (candidateLocation) {
            case VERTICAL_LEFT:
                layoutWithCandidateViewAtVerticalLeft();
                break;
            case HORIZONTAL_TOP:
                layoutWithCandidateViewAtHorizontalTop();
                break;
            case NONE:
                layoutWithNoCandidateView();
                break;
        }
    }

    private Keyboard.CandidatesLocation getCandidateViewLocation() {
        if (mCandidatesView == null) {
            return Keyboard.CandidatesLocation.NONE;
        }
        return mCurrentKeyboard.getCandidatesLocation();
    }

    private void layoutWithNoCandidateView() {
        final int keyboardLeft = getPaddingLeft();
        final int keyboardTop = getPaddingTop();
        final int keyboardRight = getMeasuredWidth() - getPaddingRight();
        final int keyboardBottom = getMeasuredHeight() - getPaddingBottom();
        layoutKeyboard(keyboardLeft, keyboardTop, keyboardRight, keyboardBottom);
    }

    private void layoutWithCandidateViewAtVerticalLeft() {
        // candidate view
        final int availableWidth = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
        final int candidateViewWidth = (int) (availableWidth * DEFAULT_VERTICAL_CANDIDATE_VIEW_PROPORTION);
        final int candidateLeft = getPaddingLeft();
        final int candidateTop = getPaddingTop();
        final int candidateRight = candidateLeft + candidateViewWidth;
        final int candidateBottom = getMeasuredHeight() - getPaddingBottom();
        layoutCandidateView(candidateLeft, candidateTop, candidateRight, candidateBottom);

        // keyboard
        final int keyboardLeft = candidateRight + mCurrentKeyboard.getKeyPadding();
        final int keyboardTop = getPaddingTop();
        final int keyboardRight = getMeasuredWidth() - getPaddingRight();
        final int keyboardBottom = getMeasuredHeight() - getPaddingBottom();
        layoutKeyboard(keyboardLeft, keyboardTop, keyboardRight, keyboardBottom);
    }

    private void layoutWithCandidateViewAtHorizontalTop() {
        // candidate view
        final int availableHeight = getMeasuredHeight() - getPaddingTop() - getPaddingBottom();
        final int candidateViewHeight = (int) (availableHeight * DEFAULT_HORIZONTAL_CANDIDATE_VIEW_PROPORTION);

        final int candidateLeft = getPaddingLeft();
        final int candidateTop = getPaddingTop();
        final int candidateRight = getMeasuredWidth() - getPaddingRight();
        final int candidateBottom = getPaddingTop() + candidateViewHeight;
        layoutCandidateView(candidateLeft, candidateTop, candidateRight, candidateBottom);

        // keyboard
        final int keyboardLeft = getPaddingLeft();
        final int keyboardTop = candidateBottom + mCurrentKeyboard.getKeyPadding();
        final int keyboardRight = getMeasuredWidth() - getPaddingRight();
        final int keyboardBottom = getMeasuredHeight() - getPaddingBottom();
        layoutKeyboard(keyboardLeft, keyboardTop, keyboardRight, keyboardBottom);
    }

    private void layoutCandidateView(int left, int top, int right, int bottom) {
        int width = right - left;
        int height = bottom - top;
        mCandidatesView.measure(MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY),
                MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY));
        mCandidatesView.layout(left, top, right, bottom);
    }

    private void layoutKeyboard(int left, int top, int right, int bottom) {
        int width = right - left;
        int height = bottom - top;
        mCurrentKeyboard.measure(MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY),
                MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY));
        mCurrentKeyboard.layout(left, top, right, bottom);
    }

    public void setInputConnection(InputConnection inputConnection) {
        this.mInputConnection = inputConnection;
    }

    InputConnection getInputConnection() {
        if (mimm == null) return null;
        MongolInputMethodManager imm = mimm.get();
        if (imm == null) return null;
        return imm.getCurrentInputConnection();
    }

    public void onUpdateSelection(int oldSelStart,
                                  int oldSelEnd,
                                  int newSelStart,
                                  int newSelEnd,
                                  int candidatesStart,
                                  int candidatesEnd) {

        // TODO in the Android source InputMethodService also handles Extracted Text here

        // currently we are only using composing for popup glyph selection.
        // If we want to be more like the standard keyboards we could do
        // composing on the whole word.
        if (!TextUtils.isEmpty(mComposing) &&
                (newSelStart != candidatesEnd
                        || newSelEnd != candidatesEnd)) {
            mComposing = "";
            // TODO updateCandidates();
            if (mInputConnection != null) {
                mInputConnection.finishComposingText();
            }
        }
        if (mCandidatesView != null && mCandidatesView.hasCandidates()) {
            mCandidatesView.clearCandidates();
        }

    }

    @Override
    public void onRequestNewKeyboard(String keyboardDisplayName) {

        Keyboard newKeyboard = getKeyboardFromDisplayName(keyboardDisplayName);
        if (newKeyboard == null) return;
        setCurrentKeyboard(newKeyboard);
        setCandidatesView();
    }

    private Keyboard getKeyboardFromDisplayName(String keyboardDisplayName) {
        for (Keyboard keyboard : mKeyboards) {
            if (keyboard.getDisplayName().equals(keyboardDisplayName))
                return keyboard;
        }
        return null;
    }

    private void setCurrentKeyboard(Keyboard keyboard) {
        removeOldCurrentKeyboard();
        addNewCurrentKeyboard(keyboard);
    }

    private void removeOldCurrentKeyboard() {
        if (mCurrentKeyboard == null) return;
        this.removeView(mCurrentKeyboard);
    }

    private void addNewCurrentKeyboard(Keyboard keyboard) {
        mCurrentKeyboard = keyboard;
        keyboard.setKeyboardListener(this);
        this.addView(keyboard);
    }

    private void setCandidatesView() {
        Keyboard.CandidatesLocation location = mCurrentKeyboard.getCandidatesLocation();
        if (location == Keyboard.CandidatesLocation.NONE) return;
        setCandidatesOrientation(location);
        //applyKeyboardThemeToCandidatesView();
    }

    private void setCandidatesOrientation(Keyboard.CandidatesLocation location) {
        if (mCandidatesView == null) {
            initCandidatesView();
        }
        ImeCandidatesView.Orientation orientation;
        switch (location) {
            case HORIZONTAL_TOP:
                orientation = ImeCandidatesView.Orientation.HORIZONTAL;
                break;
            default:
                orientation = ImeCandidatesView.Orientation.VERTICAL;
                break;
        }
        mCandidatesView.setOrientation(orientation);
    }

    private void initCandidatesView() {
        mCandidatesView = new ImeCandidatesView(mContext);
        mCandidatesView.setCandidateClickListener(this);
        styleCandidatesView();
        this.addView(mCandidatesView);
    }

    private void styleCandidatesView() {
//        mCandidatesView.setCandidateBackgroundColor(mC);
//        mCandidatesView.setBorderColor(mCurrentKeyboard.getBorderColor());
    }

//    private void applyKeyboardThemeToCandidatesView() {
//        int keyColor = mCurrentKeyboard.getKeyColor();
//        mCandidatesView.setBackgroundColor(keyColor);
//    }

    @Override
    public PopupKeyCandidate[] getKeyboardKeyCandidates() {
        int numberOfOtherKeyboards = mKeyboards.size() - 1;
        if (numberOfOtherKeyboards < 1) return null;
        String[] names = new String[numberOfOtherKeyboards];
        int nameIndex = 0;
        for (int i = 0; i < mKeyboards.size(); i++) {
            Keyboard keyboard = mKeyboards.get(i);
            if (keyboard == mCurrentKeyboard) {
                continue;
            }
            names[nameIndex] = keyboard.getDisplayName();
            nameIndex++;
        }
        return PopupKeyCandidate.createArray(names);
    }

    @Override
    public char getPreviousChar() {
        if (mInputConnection == null) return 0;
        CharSequence previous = mInputConnection.getTextBeforeCursor(1, 0);
        if (TextUtils.isEmpty(previous)) return 0;
        return previous.charAt(0);
    }

    @Override
    public boolean insertLocationIsIsolateOrInitial() {
        if (mInputConnection == null) return true;
        CharSequence before = mInputConnection.getTextBeforeCursor(2, 0);
        CharSequence after = mInputConnection.getTextAfterCursor(2, 0);
        if (before == null || after == null) return true;
        // get Mongol word location at cursor input
        MongolCode.Location location = MongolCode.getLocation(before, after);
        return location == MongolCode.Location.ISOLATE ||
                location == MongolCode.Location.INITIAL;
    }

    @Override
    public void onKeyboardInput(String text) {
        if (mInputConnection == null) return;
        if (hasCandidatesView()) {
            if (isMongol(text)) {
                mInputConnection.beginBatchEdit();
                mComposing = getComposingForPreviousMongolWord();
                mComposing = mComposing + text;
                mInputConnection.setComposingText(mComposing, 1);
                mInputConnection.endBatchEdit();
                if (mDataSource != null) {
                    List<String> candidates = mDataSource.onRequestWordsStartingWith(mComposing.toString());
                    if (mCandidatesView != null)
                        mCandidatesView.setCandidates(candidates);
                }
            } else {
                handleOldComposingText(text);
                mInputConnection.commitText(text, 1);
                if (mCandidatesView != null)
                    mCandidatesView.clearCandidates();
            }
        } else {
            handleOldComposingText(text);
            mInputConnection.commitText(text, 1);
        }
    }

    private boolean isMongol(String text) {
        if (TextUtils.isEmpty(text)) return false;
        for (char character : text.toCharArray()) {
            if (!MongolCode.isMongolian(character))
                return false;
        }
        return true;
    }

    private boolean hasCandidatesView() {
        // FIXME get the actual value
        return true;
    }

    protected CharSequence getComposingForPreviousMongolWord() {
        if (mInputConnection == null) return "";
        int numberOfCharsToGet = Integer.MAX_VALUE;
        CharSequence previous = mInputConnection.getTextBeforeCursor(numberOfCharsToGet, 0);
        if (TextUtils.isEmpty(previous)) return "";
        int endIndex = previous.length();
        int startIndex = endIndex;
        for (int i = endIndex - 1; i >= 0; i--) {
            if (MongolCode.isMongolian(previous.charAt(i))) {
                startIndex = i;
            } else {
                break;
            }
        }
        mInputConnection.setComposingRegion(startIndex, endIndex);
        if (startIndex < endIndex) {
            return previous.subSequence(startIndex, endIndex);
        }
        return "";
    }

    @Override
    public void onKeyPopupChosen(PopupKeyCandidate choice) {
        if (mInputConnection == null) return;
        if (choice == null) return;
        String composingText = choice.getComposing();
        String unicode = choice.getUnicode();
        if (TextUtils.isEmpty(composingText)) {
            onKeyboardInput(unicode);
        } else {
            handleOldComposingText(unicode);
            mInputConnection.setComposingText(composingText, 1);
            mComposing = unicode;
        }
    }

    private void handleOldComposingText(String inputText) {
        if (TextUtils.isEmpty(mComposing)) return;
        if (MongolCode.isMongolian(inputText.charAt(0))) {
            mInputConnection.commitText(mComposing, 1);
        } else {
            mInputConnection.finishComposingText();
        }
        mComposing = "";
    }

    @Override
    public void onBackspace() {
        if (mInputConnection == null) return;

        if (!TextUtils.isEmpty(mComposing)) {
            mInputConnection.commitText(mComposing, 1);
            mComposing = "";
        }

        if (hasSelection()) {
            doBackspace();
            return;
        }

        String previousFourChars = getPreviousFourChars();
        backspaceFromEndOf(previousFourChars);
    }

    private boolean hasSelection() {
        CharSequence selection = mInputConnection.getSelectedText(0);
        return selection != null && selection.length() > 0;
    }

    private String getPreviousFourChars() {
        if (mInputConnection == null) return "";
        CharSequence previous = mInputConnection.getTextBeforeCursor(4, 0);
        return previous.toString();
    }

    private void backspaceFromEndOf(String previousChars) {
        if (TextUtils.isEmpty(previousChars)) return;
        int deleteIndex = previousChars.length() - 1;

        // delete any invisible character directly in front of cursor
        char currentChar = previousChars.charAt(deleteIndex);
        if (isInvisibleChar(currentChar)) {
            doBackspace();
            deleteIndex--;
        }
        if (deleteIndex < 0) return;

        // always delete at least one visible character
        doBackspace();
        deleteIndex--;
        if (deleteIndex < 0) return;

        // also delete certain invisible characters before the just deleted character
        currentChar = previousChars.charAt(deleteIndex);
        if (currentChar == MongolCode.Uni.MVS) {
            doBackspace();
        } else if (currentChar == MongolCode.Uni.ZWJ || currentChar == MongolCode.Uni.ZWNJ) {
            if (deleteIndex == 0) {
                doBackspace();
                return;
            }
            char previousChar = previousChars.charAt(deleteIndex - 1);
            if (!MongolCode.isMongolian(previousChar)) {
                doBackspace();
            }
        }
    }

    private boolean isInvisibleChar(char character) {
        return character == MongolCode.Uni.MVS ||
                MongolCode.isFVS(character) ||
                character == MongolCode.Uni.ZWJ ||
                character == MongolCode.Uni.ZWNJ;
    }

    private void doBackspace() {
        mInputConnection.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL));
        mInputConnection.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_DEL));

        // We could also do this with inputConnection.deleteSurroundingText(1, 0)
        // but then we would need to be careful of not deleting too much
        // and not deleting half a surrogate pair.
        // see https://developer.android.com/reference/android/view/inputmethod/InputConnection.html#deleteSurroundingText(int,%20int)
        // see also https://stackoverflow.com/a/45182401
    }

    public void setInputMethodManager(MongolInputMethodManager inputMethodManager) {
        this.mimm = new WeakReference<>(inputMethodManager);
    }

    public void addKeyboard(Keyboard keyboard) {
        if (mKeyboards == null)
            mKeyboards = new ArrayList<>();

        mKeyboards.add(keyboard);

        // make the first keyboard added be the one that shows
        if (mKeyboards.size() == 1) {
            setCurrentKeyboard(keyboard);
            setCandidatesView();
        }
    }

    public Keyboard getCurrentKeyboard() {
        return mCurrentKeyboard;
    }

    //    }
//        return (candidatesPreference == Keyboard.CandidatesLocation.NONE);
//        Keyboard.CandidatesLocation candidatesPreference = mCurrentKeyboard.getCandidatesPreference();
//        if (mCurrentKeyboard == null) return true;
//        if (candidatesView == null) return true;
//    private boolean thereIsNoCandidateViewToSendWordsTo() {
//
//    }
//        candidatesView.setCandidates(wordList);
//        if (thereIsNoCandidateViewToSendWordsTo()) return;
//    }
//        return PopupKeyCandidate.createArray(unicode);
//                udSuffix};
//                achaSuffix,
//                banIyanSuffix,
//                barIyarSuffix,
//                duTuSuffix,
//                iYiSuffix,
//                yinUnUSuffix,
//                uuSuffix,
//                "" + MongolCode.Uni.NNBS,
//        String[] unicode = new String[]{
//
//        String udSuffix = MongolCode.getSuffixUd(gender);
//        String banIyanSuffix = MongolCode.getSuffixBanIyan(gender, lastChar);
//        String uuSuffix = MongolCode.getSuffixUu(gender);
//        String taiSuffix = MongolCode.getSuffixTaiTei(gender);
//        String barIyarSuffix = MongolCode.getSuffixBarIyar(gender, lastChar);
//        String achaSuffix = MongolCode.getSuffixAchaEche(gender);
//        String yinUnUSuffix = MongolCode.getSuffixYinUnU(gender, lastChar);
//        String iYiSuffix = MongolCode.getSuffixYiI(lastChar);
//        String duTuSuffix = MongolCode.getSuffixTuDu(gender, lastChar);
//        }
//            return PopupKeyCandidate.createArray(MongolCode.Uni.NNBS);
//        if (gender == null) {
//        MongolCode.Gender gender = MongolCode.getWordGender(previousWord);
//        char lastChar = previousWord.charAt(previousWord.length() - 1);
//        // TODO if it is a number then return the right suffix for that
//        }
//            return new PopupKeyCandidate[] {new PopupKeyCandidate(MongolCode.Uni.NNBS)};
//        if (TextUtils.isEmpty(previousWord)) {
//        String previousWord = getPreviousMongolWord();
//    protected PopupKeyCandidate[] getCandidatesForSuffix() {
//
    @Override
    public void onCandidateClick(int position, String text) {
        MongolToast.makeText(mContext, text, Toast.LENGTH_SHORT).show();

        replaceComposingWithCandidate(text);
        suggestFollowingWords(text);
    }

    private void replaceComposingWithCandidate(String candidate) {
        if (mInputConnection == null) return;
        mInputConnection.commitText(candidate + " ", 1);
        mComposing = "";
    }

    private void suggestFollowingWords(String text) {
        if (mCandidatesView == null) return;
        if (mDataSource != null) {
            List<String> followingWords = mDataSource.onRequestWordsFollowing(text);
            mCandidatesView.setCandidates(followingWords);
        } else {
            mCandidatesView.clearCandidates();
        }
    }

    @Override
    public void onCandidateLongClick(int position, String text) {
        MongolToast.makeText(mContext, position + "", Toast.LENGTH_SHORT).show();
    }

    public void applyStyle(StyleBuilder style) {
        for (Keyboard keyboard : mKeyboards) {
            keyboard.setPrimaryTextSize(style.keyPrimaryTextSize);
            keyboard.setSecondaryTextSize(style.keyPrimaryTextSize / 2);
            keyboard.setPrimaryTextColor(style.keyPrimaryTextColor);
            keyboard.setSecondaryTextColor(style.keySecondaryTextColor);
            keyboard.setKeyImageTheme(style.keyImageTheme);
            keyboard.setKeyColor(style.keyBackgroundColor);
            keyboard.setKeyPressedColor(style.keyPressedColor);
            keyboard.setKeyBorderColor(style.keyBorderColor);
            keyboard.setKeyBorderWidth(style.keyBorderWidth);
            keyboard.setKeyBorderRadius(style.keyBorderRadius);
            keyboard.setKeyPadding(style.keySpacing);
            keyboard.setPopupBackgroundColor(style.popupBackgroundColor);
            keyboard.setPopupHighlightColor(style.popupHighlightColor);
            keyboard.setPopupTextColor(style.popupTextColor);
            keyboard.setCandidatesLocation(style.candidatesLocation);
        }
        if (mCandidatesView != null) {
            mCandidatesView.setCandidateBackgroundColor(style.candidateItemBackgroundColor);
            mCandidatesView.setCandidateBackgroundPressedColor(style.candidateItemBackgroundPressedColor);
            mCandidatesView.setCandidateTextColor(style.candidateItemTextColor);
            mCandidatesView.setCandidateDividerColor(style.candidateDividerColor);
            mCandidatesView.setBorderColor(style.keyBorderColor);
            mCandidatesView.setBorderRadius(style.keyBorderRadius);
            mCandidatesView.setBorderWidth(style.keyBorderWidth);
        }
    }

//    public void updateCandidateWordList(List<String> wordList) {

    public static class StyleBuilder {

        private int keyBackgroundColor = Keyboard.DEFAULT_KEY_COLOR;
        private int keyPressedColor = Keyboard.DEFAULT_KEY_PRESSED_COLOR;
        private int keyBorderColor = Keyboard.DEFAULT_KEY_BORDER_COLOR;
        private int keyBorderRadius = Keyboard.DEFAULT_KEY_BORDER_RADIUS;
        private int keyBorderWidth = Keyboard.DEFAULT_KEY_BORDER_WIDTH;
        private int popupBackgroundColor = Keyboard.DEFAULT_POPUP_COLOR;
        private int popupTextColor = Keyboard.DEFAULT_POPUP_TEXT_COLOR;
        private int popupHighlightColor = Keyboard.DEFAULT_POPUP_HIGHLIGHT_COLOR;
        private int keyPrimaryTextColor = Keyboard.DEFAULT_PRIMARY_TEXT_COLOR;
        private int keySecondaryTextColor = Keyboard.DEFAULT_SECONDARY_TEXT_COLOR;
        private float keyPrimaryTextSize = Keyboard.DEFAULT_PRIMARY_TEXT_SIZE;
        private int keySpacing = Keyboard.DEFAULT_KEY_PADDING;
        private KeyImage.Theme keyImageTheme = Keyboard.DEFAULT_KEY_IMAGE_THEME;
        private Keyboard.CandidatesLocation candidatesLocation = Keyboard.DEFAULT_CANDIDATES_LOCATION;
        private int candidateItemBackgroundColor = ImeCandidatesView.DEFAULT_CANDIDATE_ITEM_BACKGROUND_COLOR;
        private int candidateItemBackgroundPressedColor = ImeCandidatesView.DEFAULT_CANDIDATE_ITEM_BACKGROUND_PRESSED_COLOR;
        private int candidateItemTextColor = ImeCandidatesView.DEFAULT_CANDIDATE_ITEM_TEXT_COLOR;
        private int candidateDividerColor = ImeCandidatesView.DEFAULT_CANDIDATE_DIVIDER_COLOR;


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
         * @param theme Theme.DARK for a light image
         *              or Theme.LIGHT for a dark image.
         * @return StyleBuilder
         */
        public StyleBuilder setKeyImageTheme(KeyImage.Theme theme) {
            this.keyImageTheme = theme;
            return this;
        }

        public StyleBuilder setCandidatesLocation(Keyboard.CandidatesLocation location) {
            this.candidatesLocation = location;
            return this;
        }

        public StyleBuilder setCandidateItemBackgroundColor(int color) {
            this.candidateItemBackgroundColor = color;
            return this;
        }

        public StyleBuilder setCandidateItemBackgroundPressedColor(int color) {
            this.candidateItemBackgroundPressedColor = color;
            return this;
        }

        public StyleBuilder setCandidateItemTextColor(int color) {
            this.candidateItemTextColor = color;
            return this;
        }

        public StyleBuilder setCandidateDividerColor(int color) {
            this.candidateDividerColor = color;
            return this;
        }
    }
}

