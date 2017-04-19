package net.studymongolian.mongollibrary;


class MongolBreakIterator {

    // TODO
    // If such a class is to be implemented then it needs to follow the Unicode
    // specs found at http://www.unicode.org/reports/tr14/


    public static final int DONE = -1;
    public static final char SPACE = ' ';
    public static final char NNBSP = '\u202F';

    private CharSequence mText;
    private boolean mBreakAtNnbsp = false;
    private int mCurrentPosition = 0;

    private MongolBreakIterator() {

    }

    /**
     * This will find all places were line breaks are allowed
     *
     * @param breakAtNnbsp whether breaking at NNBSP (Narrow Non-Breaking Space) is allowed.
     * @return a new MongolBreakIterator set up to iterate over line break locations.
     */
    MongolBreakIterator getLineInstance(boolean breakAtNnbsp) {
        mBreakAtNnbsp = breakAtNnbsp;
        return new MongolBreakIterator();
    }

    // TODO add an iterator for characters?

    void setText(CharSequence text) {
        mText = text;
    }

    /**
     * Returns the first boundary. The iterator's current position is set
     * to the first text boundary.
     *
     * @return The character index of the first text boundary.
     */
    int first() {
        mCurrentPosition = 0;
        return 0;
    }

    int next() {
        for (int i = mCurrentPosition; i < mText.length(); i++) {
            if (isLineBreakChar(mText.charAt(i))) {

            }
        }
        return 0; // TODO
    }

    private boolean isLineBreakChar(char c) {
        return false; // TODO
    }

    int last() {
        return 0; // TODO
    }
}
