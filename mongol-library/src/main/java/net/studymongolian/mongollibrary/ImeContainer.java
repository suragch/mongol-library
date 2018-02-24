package net.studymongolian.mongollibrary;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.inputmethod.InputConnection;

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
public class ImeContainer extends ViewGroup implements Keyboard.KeyboardListener {

    private static final float DEFAULT_VERTICAL_CANDIDATE_VIEW_PROPORTION = 1 / 10f;
    private static final float DEFAULT_HORIZONTAL_CANDIDATE_VIEW_PROPORTION = 1 / 5f;


    private Context mContext;
    private List<Keyboard> mKeyboards;
    private Keyboard mCurrentKeyboard;
    private KeyboardCandidatesView candidatesView;
    private WeakReference<MongolInputMethodManager> mimm;

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

        Keyboard.CandidatesPreference candidateLocation = getCandidateViewLocation();
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

    private Keyboard.CandidatesPreference getCandidateViewLocation() {
        if (candidatesView == null) {
            return Keyboard.CandidatesPreference.NONE;
        }
        return mCurrentKeyboard.getCandidatesPreference();
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

    // forward this on to the current keyboard
    public void setInputConnection(InputConnection inputConnection) {
        if (mCurrentKeyboard != null) {
            mCurrentKeyboard.setInputConnection(inputConnection);
        }
    }

    InputConnection getInputConnection() {
        if (mimm == null) return null;
        MongolInputMethodManager imm = mimm.get();
        if (imm == null) return null;
        return imm.getCurrentInputConnection();
    }

    // forward this on to the current keyboard
    public void onUpdateSelection(int oldSelStart, int oldSelEnd, int selStart, int selEnd, int candidatesStart, int candidatesEnd) {
        if (mCurrentKeyboard != null) {
            mCurrentKeyboard.onUpdateSelection(oldSelStart, oldSelEnd, selStart, selEnd, candidatesStart, candidatesEnd);
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
        setInputConnectionToCurrentKeyboard();
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

    private void setInputConnectionToCurrentKeyboard() {
        InputConnection ic = getInputConnection();
        mCurrentKeyboard.setInputConnection(ic);
    }

    private void setCandidatesView() {
        //if (!mCurrentKeyboard.isRequestingCandidatesView()) return;
        Keyboard.CandidatesPreference location = mCurrentKeyboard.getCandidatesPreference();
        if (location == Keyboard.CandidatesPreference.NONE) return;
        if (candidatesView == null) {
            candidatesView = new KeyboardCandidatesView(mContext);
        }
        switch (location) {
            case VERTICAL_LEFT:
                candidatesView.setOrientation(KeyboardCandidatesView.Orientation.VERTICAL);
                break;
            case HORIZONTAL_TOP:
                candidatesView.setOrientation(KeyboardCandidatesView.Orientation.HORIZONTAL);
                break;
            case NONE:
                break;
        }
        applyKeyboardThemeToCandidatesView();
    }

    private void applyKeyboardThemeToCandidatesView() {
        int keyColor = mCurrentKeyboard.getKeyColor();
        candidatesView.setBackgroundColor(keyColor);
    }

    @Override
    public PopupKeyCandidate[] getKeyboardCandidates() {
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

    public void updateCandidateWordList(List<String> wordList) {
        if (thereIsNoCandidateViewToSendWordsTo()) return;
        candidatesView.setCandidates(wordList);
    }

    private boolean thereIsNoCandidateViewToSendWordsTo() {
        if (candidatesView == null) return true;
        if (mCurrentKeyboard == null) return true;
        Keyboard.CandidatesPreference candidatesPreference = mCurrentKeyboard.getCandidatesPreference();
        return (candidatesPreference == Keyboard.CandidatesPreference.NONE);
    }
}
