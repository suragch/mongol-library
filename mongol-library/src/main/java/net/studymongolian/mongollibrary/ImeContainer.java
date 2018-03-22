package net.studymongolian.mongollibrary;

import android.content.Context;
import android.support.v4.graphics.ColorUtils;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
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
        implements Keyboard.KeyboardListener, ImeCandidatesView.CandidateClickListener {

    private static final float DEFAULT_VERTICAL_CANDIDATE_VIEW_PROPORTION = 1 / 8f;
    private static final float DEFAULT_HORIZONTAL_CANDIDATE_VIEW_PROPORTION = 1 / 5f;
    private static final int DIVIDER_ALPHA = 0x40; // 25%
    private static final int MAX_CHARS_BEFORE_CURSOR = 128;

    private Context mContext;
    private List<Keyboard> mKeyboards;
    private Keyboard mCurrentKeyboard;
    private ImeCandidatesView mCandidatesView;
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

        List<String> onRequestWordsStartingWith(String text);

        List<String> onRequestWordsFollowing(String word);

        void onCandidateLongClick(int position, String text);
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
    public List<PopupKeyCandidate> getKeyboardKeyCandidates() {
        int numberOfOtherKeyboards = mKeyboards.size() - 1;
        List<PopupKeyCandidate> candidates = new ArrayList<>();
        if (numberOfOtherKeyboards < 1) return candidates;
        for (Keyboard keyboard : mKeyboards) {
            if (keyboard == mCurrentKeyboard) {
                continue;
            }
            PopupKeyCandidate item = new PopupKeyCandidate(keyboard.getDisplayName());
            candidates.add(item);
        }
        return candidates;
    }

    @Override
    public char getPreviousChar() {
        if (mInputConnection == null) return 0;
        CharSequence previous = mInputConnection.getTextBeforeCursor(1, 0);
        if (TextUtils.isEmpty(previous)) return 0;
        return previous.charAt(0);
    }

    @Override
    public String getPreviousMongolWord(boolean allowSingleSpace) {
        if (mInputConnection == null) return "";
        CharSequence previous = mInputConnection.getTextBeforeCursor(MAX_CHARS_BEFORE_CURSOR, 0);
        if (TextUtils.isEmpty(previous)) return "";
        int endIndex = previous.length();
        if (allowSingleSpace && endsWithSpace(previous)) {
            endIndex--;
        }
        int startIndex = getStartIndex(endIndex, previous);
        return previous.subSequence(startIndex, endIndex).toString();
    }

    private boolean endsWithSpace(CharSequence text) {
        int length = text.length();
        if (length < 1) return false;
        char lastChar = text.charAt(length - 1);
        return (lastChar == ' ' || lastChar == MongolCode.Uni.NNBS);
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
        if (TextUtils.isEmpty(text)) return;
        boolean isMongol = MongolCode.isMongolian(text.charAt(0));
        handleOldComposingText(isMongol);
        commitText(text);
        updateCandidatesView();
    }

    private void commitText(String text) {
        if (mInputConnection == null) return;
        mInputConnection.beginBatchEdit();
        char previousChar = getPreviousChar();
        if (previousChar == ' ' || previousChar == MongolCode.Uni.NNBS) {
            char initialChar = text.charAt(0);
            if (initialChar == MongolCode.Uni.NNBS) {
                mInputConnection.deleteSurroundingText(1, 0);
            }
        }
        mInputConnection.commitText(text, 1);
        mInputConnection.endBatchEdit();
    }

    private void updateCandidatesView() {
        if (mCandidatesView == null || mDataSource == null) return;
        String mongolWord = getPreviousMongolWord(false);
        if (TextUtils.isEmpty(mongolWord)) {
            mCandidatesView.clearCandidates();
            return;
        }
        List<String> candidates = mDataSource.onRequestWordsStartingWith(mongolWord);
        mCandidatesView.setCandidates(candidates);
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
            boolean isMongol = MongolCode.isMongolian(unicode.charAt(0));
            handleOldComposingText(isMongol);
            mInputConnection.setComposingText(composingText, 1);
            mComposing = unicode;
        }
    }

    private void handleOldComposingText(boolean newInputIsMongol) {
        if (TextUtils.isEmpty(mComposing)) return;
        if (newInputIsMongol) {
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

        clearCandidates();
    }

    private boolean hasSelection() {
        CharSequence selection = mInputConnection.getSelectedText(0);
        return selection != null && selection.length() > 0;
    }

    private String getPreviousFourChars() {
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

    private void clearCandidates() {
        if (mCandidatesView == null) return;
        mCandidatesView.clearCandidates();
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
        suggestFollowingWords(text);
    }

    private boolean currentWordIsPrefixedWith(String text) {
        String currentWord = getPreviousMongolWord(false);
        return text.startsWith(currentWord);
    }

    private void insertFollowingWord(String text) {
        if (mInputConnection == null) return;
        char previousChar = getPreviousChar();
        String insertWord = text;
        if (previousChar != ' ') {
            insertWord = " " + insertWord;
        }
        mInputConnection.commitText(insertWord, 1);
    }


    private void replaceMongolWordBeforeCursor(String text) {
        if (mInputConnection == null) return;
        CharSequence previous = mInputConnection.getTextBeforeCursor(MAX_CHARS_BEFORE_CURSOR, 0);
        if (previous == null) return;
        int endIndex = previous.length();
        int startIndex = getStartIndex(endIndex, previous);
        int length = endIndex - startIndex;
        mInputConnection.beginBatchEdit();
        mInputConnection.deleteSurroundingText(length, 0);
        mInputConnection.commitText(text, 1);
        mInputConnection.endBatchEdit();
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
        if (mDataSource == null) return;
        mDataSource.onCandidateLongClick(position, text);
    }

}

