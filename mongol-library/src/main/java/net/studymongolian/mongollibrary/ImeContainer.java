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
        implements Keyboard.KeyboardListener, KeyboardCandidatesAdapter.CandidateClickListener {

    private static final float DEFAULT_VERTICAL_CANDIDATE_VIEW_PROPORTION = 1 / 10f;
    private static final float DEFAULT_HORIZONTAL_CANDIDATE_VIEW_PROPORTION = 1 / 5f;


    private Context mContext;
    private List<Keyboard> mKeyboards;
    private Keyboard mCurrentKeyboard;
    private KeyboardCandidatesView candidatesView;
    private KeyboardCandidatesAdapter candidatesAdapter;
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

        //   Keyboard.CandidateLocation
        //   VERTICAL_LEFT                    HORIZONTAL_TOP                   NONE
        //   ___________________________      ___________________________      ___________________________
        //   | C |                     |      |       Candidates        |      |                         |
        //   | a |                     |      |_________________________|      |                         |
        //   | n |                     |      |                         |      |                         |
        //   | d |                     |      |                         |      |                         |
        //   | i |                     |      |                         |      |                         |
        //   | d |     Keyboard        |  or  |        Keyboard         |  or  |        Keyboard         |
        //   | a |                     |      |                         |      |                         |
        //   | t |                     |      |                         |      |                         |
        //   | e |                     |      |                         |      |                         |
        //   | s |                     |      |                         |      |                         |
        //   ---------------------------      ---------------------------      ---------------------------

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
        if (candidatesView == null) {
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
        candidatesView.measure(MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY),
                MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY));
        candidatesView.layout(left, top, right, bottom);
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
        if (candidatesAdapter != null && candidatesAdapter.hasCandidates()) {
            candidatesAdapter.clearCandidates();
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
        applyKeyboardThemeToCandidatesView();
    }

    private void setCandidatesOrientation(Keyboard.CandidatesLocation location) {
        if (candidatesView == null) {
            candidatesView = new KeyboardCandidatesView(mContext);
            candidatesAdapter = new KeyboardCandidatesAdapter(mContext);
            candidatesView.setAdapter(candidatesAdapter);
            candidatesAdapter.setCandidateClickListener(this);
            this.addView(candidatesView);
        }
        KeyboardCandidatesView.Orientation orientation;
        switch (location) {
            case HORIZONTAL_TOP:
                orientation = KeyboardCandidatesView.Orientation.HORIZONTAL;
                break;
            default:
                orientation = KeyboardCandidatesView.Orientation.VERTICAL;
                break;
        }
        candidatesView.setOrientation(orientation);
        candidatesAdapter.setOrientation(orientation);
    }

    private void applyKeyboardThemeToCandidatesView() {
        int keyColor = mCurrentKeyboard.getKeyColor();
        candidatesView.setBackgroundColor(keyColor);
    }

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
                    candidatesAdapter.setCandidates(candidates);
                }
            } else {
                handleOldComposingText(text);
                mInputConnection.commitText(text, 1);
                candidatesAdapter.clearCandidates();
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
        }
        else {
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
        if (isInvisibleChar(currentChar)){
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
        if (candidatesAdapter == null) return;
        if (mDataSource != null) {
            List<String> followingWords = mDataSource.onRequestWordsFollowing(text);
            candidatesAdapter.setCandidates(followingWords);
        } else {
            candidatesAdapter.clearCandidates();
        }
    }

    @Override
    public void onCandidateLongClick(int position, String text) {
        MongolToast.makeText(mContext, position + "", Toast.LENGTH_SHORT).show();
    }

//    public void updateCandidateWordList(List<String> wordList) {
}

