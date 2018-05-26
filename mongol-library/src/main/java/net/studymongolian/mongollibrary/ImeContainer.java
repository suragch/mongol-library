package net.studymongolian.mongollibrary;

import android.content.Context;
import android.support.v4.graphics.ColorUtils;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;

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
        implements Keyboard.OnKeyboardListener, ImeCandidatesView.CandidateClickListener {

    private static final float DEFAULT_VERTICAL_CANDIDATE_VIEW_PROPORTION = 1 / 8f;
    private static final float DEFAULT_HORIZONTAL_CANDIDATE_VIEW_PROPORTION = 1 / 5f;
    private static final int DIVIDER_ALPHA = 0x40; // 25%
    private static final int MAX_CHARS_BEFORE_CURSOR = 128;
    private static final int DEFAULT_HEIGHT_DP = 240;

    private Context mContext;
    private List<Keyboard> mKeyboards;
    private Keyboard mCurrentKeyboard;
    private ImeCandidatesView mCandidatesView;
    private DataSource mDataSource = null;
    private OnSystemImeListener mSystemImeListener = null;
    private CharSequence mComposing = "";
    private InputConnection mInputConnection;
    private PopupKeyCandidate mShowSystemKeyboardsOption;

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

        void onRequestWordsStartingWith(String text);

        void onWordFinished(String word, String previousWord);

        void onCandidateClick(int position, String word, String previousWordInEditor);

        void onCandidateLongClick(int position, String text);
    }

    // provide a way for another class to set the listener
    public void setDataSource(DataSource dataSource) {
        this.mDataSource = dataSource;
    }

    public interface OnSystemImeListener {
        InputConnection getInputConnection();
        void onChooseNewSystemKeyboard();
    }

    public void setOnSystemImeListener(OnSystemImeListener listener) {
        this.mSystemImeListener = listener;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int desiredWidth = Integer.MAX_VALUE;
        int desiredHeight = getDefaultHeight();

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height;

        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            width = Math.min(desiredWidth, widthSize);
        } else {
            width = desiredWidth;
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            height = Math.min(desiredHeight, heightSize);
        } else {
            height = desiredHeight;
        }

        setMeasuredDimension(width, height);
    }

    private int getDefaultHeight() {
        if (mCurrentKeyboard != null)
            return mCurrentKeyboard.getDefaultHeight();
        return (int) (DEFAULT_HEIGHT_DP * getResources().getDisplayMetrics().density);
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
        // candidate view
        if (mCandidatesView != null) {
            layoutCandidateView(0, 0, 0, 0);
        }

        // keyboard
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
        final int padding = mCurrentKeyboard.getKeySpacing();
        mCandidatesView.setPadding(padding, padding, 0, padding);
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
        final int padding = mCurrentKeyboard.getKeySpacing();
        mCandidatesView.setPadding(padding, padding, padding, 0);
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

    public InputConnection getInputConnection() {
        if (mSystemImeListener != null)
            return mSystemImeListener.getInputConnection();
        return mInputConnection;
    }

    public void setInputConnection(InputConnection inputConnection) {
        this.mInputConnection = inputConnection;
        mComposing = "";
    }

    @SuppressWarnings("unused")
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
            InputConnection ic = getInputConnection();
            if (ic != null) {
                ic.finishComposingText();
            }
        }
        if (mCandidatesView != null && mCandidatesView.hasCandidates()) {
            mCandidatesView.clearCandidates();
        }

    }

    /**
     * subclasses can override this method to choose a specific keyboard based on
     * the InputType of the current editor
     *
     * @param attribute information passed in by the current EditText or MongolEditText
     * @param restarting this parameter is currently not implemented
     */
    @SuppressWarnings("unused")
    public void onStartInput(EditorInfo attribute, boolean restarting) {
        // subclasses can override this method to choose a specific keyboard
        // based on the InputType of the current editor

        //switch (attribute.inputType) {
        //    case InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PHONETIC:
        //        // keyboard indexes are in the order added in xml or programmatically
        //        requestNewKeyboard(1);
        //        break;
        //    default:
        //        requestNewKeyboard(0);
        //}
    }

    @Override
    public void onRequestNewKeyboard(String keyboardDisplayName) {
        if (isSystemKeyboardRequest(keyboardDisplayName)) {
            chooseSystemKeyboard();
        }
        int newKeyboardIndex = getKeyboardIndexFromDisplayName(keyboardDisplayName);
        requestNewKeyboard(newKeyboardIndex);
    }

    public void requestNewKeyboard(int index) {
        if (index < 0 || index >= mKeyboards.size())
            return;
        Keyboard keyboard = mKeyboards.get(index);
        setCurrentKeyboard(keyboard);
        setCandidatesView();
    }

    private boolean isSystemKeyboardRequest(String requestedName) {
        if (mShowSystemKeyboardsOption == null) return false;
        String system = mShowSystemKeyboardsOption.getUnicode();
        return system != null && system.equals(requestedName);
    }

    private void chooseSystemKeyboard() {
        if (mSystemImeListener != null) {
            mSystemImeListener.onChooseNewSystemKeyboard();
        }
        // TODO else who can we request a system keyboard from? MongolInputMethodManager?
    }

    private int getKeyboardIndexFromDisplayName(String keyboardDisplayName) {
        for (int i = 0; i < mKeyboards.size(); i++) {
            if (mKeyboards.get(i).getDisplayName().equals(keyboardDisplayName))
                return i;
        }
        return -1;
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
        keyboard.setOnKeyboardListener(this);
        this.addView(keyboard);
    }

    @SuppressWarnings("unused")
    public int getKeyboardCount() {
        return mKeyboards.size();
    }

    @SuppressWarnings("unused")
    public Keyboard getKeyboardAt(int index) {
        return mKeyboards.get(index);
    }

    public void showSystemKeyboardsOption(String title) {
        mShowSystemKeyboardsOption = new PopupKeyCandidate(title);
    }

    private void setCandidatesView() {
        Keyboard.CandidatesLocation location = mCurrentKeyboard.getCandidatesLocation();
        if (location == Keyboard.CandidatesLocation.NONE) return;
        setCandidatesOrientation(location);
        styleCandidatesView();
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
        this.addView(mCandidatesView);
    }

    private void styleCandidatesView() {
        mCandidatesView.setCandidateBackgroundColor(mCurrentKeyboard.getKeyColor());
        mCandidatesView.setBackgroundPressedColor(mCurrentKeyboard.getKeyPressedColor());
        mCandidatesView.setBorderColor(mCurrentKeyboard.getBorderColor());
        mCandidatesView.setBorderWidth(mCurrentKeyboard.getBorderWidth());
        mCandidatesView.setBorderRadius(mCurrentKeyboard.getBorderRadius());

        int textColor = mCurrentKeyboard.getPrimaryTextColor();
        mCandidatesView.setTextColor(textColor);
        int dividerColor = ColorUtils.setAlphaComponent(textColor, DIVIDER_ALPHA);
        mCandidatesView.setDividerColor(dividerColor);
    }

    @Override
    public List<PopupKeyCandidate> getAllKeyboardNames() {
        int numberOfOtherKeyboards = mKeyboards.size() - 1;
        List<PopupKeyCandidate> candidates = new ArrayList<>();
        if (numberOfOtherKeyboards >= 1) {
            for (Keyboard keyboard : mKeyboards) {
                if (keyboard == mCurrentKeyboard) {
                    continue;
                }
                PopupKeyCandidate item = new PopupKeyCandidate(keyboard.getDisplayName());
                candidates.add(item);
            }
        }
        if (mShowSystemKeyboardsOption != null) {
            candidates.add(mShowSystemKeyboardsOption);
        }
        return candidates;
    }

    private char getPreviousChar() {
        InputConnection ic = getInputConnection();
        if (ic == null) return 0;
        CharSequence previous = ic.getTextBeforeCursor(1, 0);
        if (TextUtils.isEmpty(previous)) return 0;
        return previous.charAt(0);
    }

    @Override
    public String getPreviousMongolWord(boolean allowSingleSpaceBeforeCursor) {
        List<String> words = getPreviousMongolWords(1, allowSingleSpaceBeforeCursor);
        if (words.size() == 0) return "";
        return words.get(0);
    }

    private List<String> getPreviousMongolWords(int numberOfWords, boolean allowSingleSpaceBeforeCursor) {
        InputConnection ic = getInputConnection();
        List<String> words = new ArrayList<>();
        if (ic == null) return words;
        CharSequence previous = ic.getTextBeforeCursor(MAX_CHARS_BEFORE_CURSOR, 0);
        if (TextUtils.isEmpty(previous)) return words;

        int endIndex = previous.length();
        if (allowSingleSpaceBeforeCursor && isQualifiedSpaceAt(previous, endIndex - 1)) {
            endIndex--;
        }

        for (int i = 0; i < numberOfWords; i++) {
            int startIndex = getStartIndex(endIndex, previous);
            String word = previous.subSequence(startIndex, endIndex).toString();
            words.add(word);
            endIndex = startIndex;
            if (isQualifiedSpaceAt(previous, endIndex - 1)) {
                endIndex--;
            }
        }
        return words;
    }

    private boolean isQualifiedSpaceAt(CharSequence text, int index) {
        int length = text.length();
        if (index < 0 || index >= length) return false;
        char character = text.charAt(index);
        return (character == ' ' || character == MongolCode.Uni.NNBS)
                && index != 0
                && MongolCode.isMongolian(text.charAt(index - 1));
    }

    private int getStartIndex(int endIndex, CharSequence previous) {
        int startIndex = endIndex;
        for (int i = endIndex - 1; i >= 0; i--) {
            char previousChar = previous.charAt(i);
            if (MongolCode.isMongolian(previousChar)) {
                startIndex = i;
            } else if (previousChar == MongolCode.Uni.NNBS) {
                startIndex = i;
                break;
            } else {
                break;
            }
        }
        return startIndex;
    }

    @Override
    public void onKeyboardInput(String text) {
        if (TextUtils.isEmpty(text)) return;
        checkForFinishedWord(text);
        boolean isMongol = MongolCode.isMongolian(text.charAt(0));
        handleOldComposingText(isMongol);
        commitText(text);
        updateCandidatesView();
    }

    private void checkForFinishedWord(String textToBeAdded) {
        if (mCandidatesView == null || mDataSource == null) return;
        boolean isThisCharMongol = MongolCode.isMongolian(textToBeAdded.charAt(0));
        boolean isPrevCharMongol = MongolCode.isMongolian(getPreviousChar());
        if (!isThisCharMongol && isPrevCharMongol) {
            List<String> previousWords = getPreviousMongolWords(2, false);
            String previousWord = previousWords.get(0);
            String wordBeforeThat = previousWords.get(1);
            mDataSource.onWordFinished(previousWord, wordBeforeThat);
        }
    }



    private void commitText(String text) {
        InputConnection ic = getInputConnection();
        if (ic == null) return;
        ic.beginBatchEdit();
        char previousChar = getPreviousChar();
        if (previousChar == ' ' || previousChar == MongolCode.Uni.NNBS) {
            char initialChar = text.charAt(0);
            if (initialChar == MongolCode.Uni.NNBS) {
                ic.deleteSurroundingText(1, 0);
            }
        }
        ic.commitText(text, 1);
        ic.endBatchEdit();
    }

    private void updateCandidatesView() {
        if (mCandidatesView == null || mDataSource == null) return;
        String mongolWord = getPreviousMongolWord(false);
        if (TextUtils.isEmpty(mongolWord)) {
            mCandidatesView.clearCandidates();
            return;
        }
        mDataSource.onRequestWordsStartingWith(mongolWord);
    }

    @Override
    public void onKeyPopupChosen(PopupKeyCandidate choice) {
        InputConnection ic = getInputConnection();
        if (ic == null) return;
        if (choice == null) return;
        String composingText = choice.getComposing();
        String unicode = choice.getUnicode();
        if (TextUtils.isEmpty(composingText)) {
            onKeyboardInput(unicode);
        } else {
            checkForFinishedWord(unicode);
            boolean isMongol = MongolCode.isMongolian(unicode.charAt(0));
            handleOldComposingText(isMongol);
            ic.setComposingText(composingText, 1);
            mComposing = unicode;
        }
    }

    private void handleOldComposingText(boolean newInputIsMongol) {
        InputConnection ic = getInputConnection();
        if (TextUtils.isEmpty(mComposing)) return;
        if (newInputIsMongol) {
            ic.commitText(mComposing, 1);
        } else {
            ic.finishComposingText();
        }
        mComposing = "";
    }

    @Override
    public void onBackspace() {
        InputConnection ic = getInputConnection();
        if (ic == null) return;

        if (!TextUtils.isEmpty(mComposing)) {
            ic.commitText(mComposing, 1);
            mComposing = "";
        }

        if (hasSelection()) {
            doBackspace();
            return;
        }

        CharSequence previousFourChars = getTextBeforeCursor(4);
        backspaceFromEndOf(previousFourChars);

        clearCandidates();
    }

    @Override
    public CharSequence getTextBeforeCursor(int numberOfChars) {
        InputConnection ic = getInputConnection();
        if (ic == null) return "";
        return ic.getTextBeforeCursor(numberOfChars, 0);
    }

    @Override
    public CharSequence getTextAfterCursor(int numberOfChars) {
        InputConnection ic = getInputConnection();
        if (ic == null) return "";
        return ic.getTextAfterCursor(numberOfChars, 0);
    }

    private boolean hasSelection() {
        InputConnection ic = getInputConnection();
        CharSequence selection = ic.getSelectedText(0);
        return selection != null && selection.length() > 0;
    }

    private void backspaceFromEndOf(CharSequence previousChars) {
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
        InputConnection ic = getInputConnection();
        ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL));
        ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_DEL));

        // We could also do this with inputConnection.deleteSurroundingText(1, 0)
        // but then we would need to be careful of not deleting too much
        // and not deleting half a surrogate pair.
        // see https://developer.android.com/reference/android/view/inputmethod/InputConnection.html#deleteSurroundingText(int,%20int)
        // see also https://stackoverflow.com/a/45182401
    }

    public void clearCandidates() {
        if (mCandidatesView == null) return;
        mCandidatesView.clearCandidates();
    }

    public void removeCandidate(int index) {
        if (mCandidatesView == null) return;
        mCandidatesView.removeCandidate(index);
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

    // This method is called when subviews are added from XML
    // XXX currently ignoring LayoutParams. Should we use them?
    @Override
    public void addView(View child, LayoutParams params) {
        if (child instanceof Keyboard) {
            addKeyboard((Keyboard) child);
        }
    }

    public Keyboard getCurrentKeyboard() {
        return mCurrentKeyboard;
    }

    @Override
    public void onCandidateClick(int position, String text) {
        if (currentWordIsPrefixedWith(text)) {
            replaceMongolWordBeforeCursor(text);
        } else {
            insertFollowingWord(text);
        }
        if (mCandidatesView == null || mDataSource == null) return;
        List<String> words = getPreviousMongolWords(2, true);
        String previousWord = words.get(1);
        mDataSource.onCandidateClick(position, text, previousWord);
    }

    private boolean currentWordIsPrefixedWith(String text) {
        String currentWord = getPreviousMongolWord(false);
        return text.startsWith(currentWord);
    }

    private void insertFollowingWord(String text) {
        if (TextUtils.isEmpty(text)) return;
        InputConnection ic = getInputConnection();
        if (ic == null) return;
        char previousChar = getPreviousChar();
        String insertWord = text;
        ic.beginBatchEdit();
        if (text.charAt(0) == MongolCode.Uni.NNBS) {
            if (isSpace(previousChar)) {
                ic.deleteSurroundingText(1, 0);
            }
        } else if (!isSpace(previousChar)) {
            insertWord = " " + insertWord;
        }
        ic.commitText(insertWord, 1);
        ic.endBatchEdit();
    }

    private boolean isSpace(char character) {
        return character == ' ' || character == MongolCode.Uni.NNBS;
    }

    private void replaceMongolWordBeforeCursor(String text) {
        InputConnection ic = getInputConnection();
        if (ic == null) return;
        CharSequence previous = ic.getTextBeforeCursor(MAX_CHARS_BEFORE_CURSOR, 0);
        if (previous == null) return;
        int endIndex = previous.length();
        int startIndex = getStartIndex(endIndex, previous);
        int length = endIndex - startIndex;
        ic.beginBatchEdit();
        ic.deleteSurroundingText(length, 0);
        ic.commitText(text, 1);
        ic.endBatchEdit();
    }

    @Override
    public void onCandidateLongClick(int position, String text) {
        if (mDataSource == null) return;
        mDataSource.onCandidateLongClick(position, text);
    }

    public void setCandidates(List<String> candidateWords) {
        if (mCandidatesView == null) return;
        mCandidatesView.setCandidates(candidateWords);
    }

}

