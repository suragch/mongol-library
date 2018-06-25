package net.studymongolian.mongollibrary;

import android.content.Context;
import android.util.AttributeSet;

import java.util.List;

public class KeyboardNavigation extends Keyboard {

    // name to use in the keyboard popup chooser
    private static final String DEFAULT_DISPLAY_NAME = "nav";
    private static final int DEFAULT_HEIGHT_DP = 120;

    private static final String MOVE_LEFT = "left";
    private static final String MOVE_RIGHT = "right";
    private static final String MOVE_UP = "up";
    private static final String MOVE_DOWN = "down";
    private static final String MOVE_START = "start";
    private static final String MOVE_END = "end";
    private static final String SELECT_ALL = "all";
    private static final String SELECT_WORD_BACK = "sel_back";
    private static final String SELECT_WORD_FORWARD = "sel_forward";
    private static final String COPY = "copy";
    private static final String CUT = "cut";
    private static final String PASTE = "paste";
    private static final String FINISHED = "finished";
    private static final String SPACE = " ";

    private OnNavigationListener mNavigationListener;

    // Row 1
    protected KeyImage mKeyStart;
    protected KeyImage mKeyUp;
    protected KeyImage mKeySelectBack;
    protected KeyImage mKeyCopy;
    protected KeyImage mKeyBackspace;

    // Row 2
    protected KeyImage mKeyLeft;
    protected KeyImage mKeySelectAll;
    protected KeyImage mKeyRight;
    protected KeyImage mKeyCut;
    protected KeyText mKeySpace;

    // Row 3
    protected KeyImage mKeySelectForward;
    protected KeyImage mKeyDown;
    protected KeyImage mKeyEnd;
    protected KeyImage mKeyPaste;
    protected KeyImage mKeyFinished;

    public interface OnNavigationListener {
        void moveCursorLeft();
        void moveCursorRight();
        void moveCursorUp();
        void moveCursorDown();
        void moveCursorStart();
        void moveCursorEnd();

        void selectAll();
        void selectWordBack();
        void selectWordForward();

        void copyText();
        void cutText();
        void pasteText();
    }

    public void setOnNavigationListener(OnNavigationListener listener) {
        this.mNavigationListener = listener;
    }


    public KeyboardNavigation(Context context) {
        super(context);
        init(context);
    }

    public KeyboardNavigation(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public KeyboardNavigation(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public KeyboardNavigation(Context context, StyleBuilder style) {
        super(context, style);
        init(context);
    }

    protected void init(Context context) {

        // | start | up    |       | copy  | del   |    Row 1
        // | left  | all   | right | cut   | space |    Row 2
        // |       | down  | end   | paste | back  |    Row 3

        // actual layout work is done by Keyboard superclass's onLayout
        mNumberOfKeysInRow = new int[]{5, 5, 5};
        // the key weights for each row should sum to 1
        mKeyWeights = new float[]{
                0.2f, 0.2f, 0.2f, 0.2f, 0.2f,   // row 1
                0.2f, 0.2f, 0.2f, 0.2f, 0.2f,   // row 2
                0.2f, 0.2f, 0.2f, 0.2f, 0.2f};  // row 3


        // Make sure that the total keys added to this ViewGroup below equals
        // the mNumberOfKeysInRow and mKeyWeights array totals above.

        instantiateKeys(context);
        setKeyValues();
        setKeyImages();
        setListeners();
        addKeysToKeyboard();
        applyThemeToKeys();
    }

    private void instantiateKeys(Context context) {

        // Row 1
        mKeyStart = new KeyImage(context);
        mKeyUp = new KeyImage(context);
        mKeySelectBack = new KeyImage(context);
        mKeyCopy = new KeyImage(context);
        mKeyBackspace = new KeyBackspace(context);

        // Row 2
        mKeyLeft = new KeyImage(context);
        mKeySelectAll = new KeyImage(context);
        mKeyRight = new KeyImage(context);
        mKeyCut = new KeyImage(context);
        mKeySpace = new KeyText(context);

        // Row 3
        mKeySelectForward = new KeyImage(context);
        mKeyDown = new KeyImage(context);
        mKeyEnd = new KeyImage(context);
        mKeyPaste = new KeyImage(context);
        mKeyFinished = new KeyImage(context);
    }

    private void setKeyValues() {

        // Row 1
        mKeyStart.setText(MOVE_START);
        mKeyUp.setText(MOVE_UP);
        mKeySelectBack.setText(SELECT_WORD_BACK);
        mKeyCopy.setText(COPY);

        // Row 2
        mKeyLeft.setText(MOVE_LEFT);
        mKeySelectAll.setText(SELECT_ALL);
        mKeyRight.setText(MOVE_RIGHT);
        mKeyCut.setText(CUT);
        mKeySpace.setText(SPACE);

        // Row 3
        mKeySelectForward.setText(SELECT_WORD_FORWARD);
        mKeyDown.setText(MOVE_DOWN);
        mKeyEnd.setText(MOVE_END);
        mKeyPaste.setText(PASTE);
        mKeyFinished.setText(FINISHED);
    }

    private void setKeyImages() {

        // Row 1
        mKeyStart.setImage(getStartImage(), getPrimaryTextColor());
        mKeyUp.setImage(getUpImage(), getPrimaryTextColor());
        mKeySelectBack.setImage(getSelectBackImage(), getPrimaryTextColor());
        mKeyCopy.setImage(getCopyImage(), getPrimaryTextColor());
        mKeyBackspace.setImage(getBackspaceImage(), getPrimaryTextColor());

        // Row 2
        mKeyLeft.setImage(getLeftImage(), getPrimaryTextColor());
        mKeySelectAll.setImage(getSelectAllImage(), getPrimaryTextColor());
        mKeyRight.setImage(getRightImage(), getPrimaryTextColor());
        mKeyCut.setImage(getCutImage(), getPrimaryTextColor());

        // Row 3
        mKeySelectForward.setImage(getSelectForwardImage(), getPrimaryTextColor());
        mKeyDown.setImage(getDownImage(), getPrimaryTextColor());
        mKeyEnd.setImage(getEndImage(), getPrimaryTextColor());
        mKeyPaste.setImage(getPasteImage(), getPrimaryTextColor());
        mKeyFinished.setImage(getBackImage(), getPrimaryTextColor());
    }

    private void setListeners() {

        // Row 1
        mKeyStart.setKeyListener(this);
        mKeyUp.setKeyListener(this);
        mKeySelectBack.setKeyListener(this);
        mKeyCopy.setKeyListener(this);
        mKeyBackspace.setKeyListener(this);

        // Row 2
        mKeyLeft.setKeyListener(this);
        mKeySelectAll.setKeyListener(this);
        mKeyRight.setKeyListener(this);
        mKeyCut.setKeyListener(this);
        mKeySpace.setKeyListener(this);

        // Row 3
        mKeySelectForward.setKeyListener(this);
        mKeyDown.setKeyListener(this);
        mKeyEnd.setKeyListener(this);
        mKeyPaste.setKeyListener(this);
        mKeyFinished.setKeyListener(this);
    }

    private void addKeysToKeyboard() {

        // Row 1
        addView(mKeyStart);
        addView(mKeyUp);
        addView(mKeySelectBack);
        addView(mKeyCopy);
        addView(mKeyBackspace);

        // Row 2
        addView(mKeyLeft);
        addView(mKeySelectAll);
        addView(mKeyRight);
        addView(mKeyCut);
        addView(mKeySpace);

        // Row 3
        addView(mKeySelectForward);
        addView(mKeyDown);
        addView(mKeyEnd);
        addView(mKeyPaste);
        addView(mKeyFinished);
    }

    public List<PopupKeyCandidate> getPopupCandidates(Key key) {
        return null;
    }

    @Override
    public void onKeyInput(String text) {
        if (mNavigationListener == null) return;
        switch (text) {
            case MOVE_LEFT:
                mNavigationListener.moveCursorLeft();
                return;
            case MOVE_RIGHT:
                mNavigationListener.moveCursorRight();
                return;
            case MOVE_UP:
                mNavigationListener.moveCursorUp();
                return;
            case MOVE_DOWN:
                mNavigationListener.moveCursorDown();
                return;
            case MOVE_START:
                mNavigationListener.moveCursorStart();
                return;
            case MOVE_END:
                mNavigationListener.moveCursorEnd();
                return;
            case SELECT_ALL:
                mNavigationListener.selectAll();
                return;
            case SELECT_WORD_BACK:
                mNavigationListener.selectWordBack();
                return;
            case SELECT_WORD_FORWARD:
                mNavigationListener.selectWordForward();
                return;
            case COPY:
                mNavigationListener.copyText();
                return;
            case CUT:
                mNavigationListener.cutText();
                return;
            case PASTE:
                mNavigationListener.pasteText();
                return;
            case FINISHED:
                finishKeyboard();
                return;
        }
        super.onKeyInput(text);
    }

    @Override
    public String getDisplayName() {
        if (mDisplayName == null)
            return DEFAULT_DISPLAY_NAME;
        return mDisplayName;
    }

    @Override
    public void onKeyboardKeyClick() {
        // no keyboard button in this keyboard
    }

    @Override
    public int getDefaultHeight() {
        return (int) (DEFAULT_HEIGHT_DP * getResources().getDisplayMetrics().density);
    }
}
