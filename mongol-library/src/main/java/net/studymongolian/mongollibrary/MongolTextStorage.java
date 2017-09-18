package net.studymongolian.mongollibrary;

// The purpose of this class is to store the text for TextViews.
// It is a wrapper for the unicode to glyph rendering and indexing
// so that developers can use Unicode exclusively without worrying
// about the glyph rendering and indexing.
//
// glyph: this is what is displayed by the font and MongolTextView
// unicode: this is the encoding that the app user and library user
//          (app developer) work with
//
// XXX can we keep this class package private?
// Is it actually needed by app developers?

import android.text.Editable;
import android.text.InputFilter;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.CharacterStyle;

import java.util.ArrayList;


public class MongolTextStorage implements Editable {

    private CharSequence mUnicodeText;
    private CharSequence mGlyphText;
    private MongolCode mRenderer;
    private ArrayList<Integer> mGlyphIndexes; // item number is unicode index, value is glyph index
    private OnChangeListener mChangelistener;

    MongolTextStorage() {
        this("");
    }

    MongolTextStorage(CharSequence unicodeText) {
        mRenderer = MongolCode.INSTANCE;
        this.mChangelistener = null;
        setText(unicodeText);
    }

    // callback methods to let EditText (or other view) know about changes
    // to the text here
    public interface OnChangeListener {
        void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter);
        void onSpanChanged(Spanned buf, Object what, int oldStart, int newStart, int oldEnd, int newEnd);
    }

    public void setOnChangeListener(OnChangeListener listener) {
        this.mChangelistener = listener;
    }


    CharSequence getUnicodeText() {
        return mUnicodeText;
    }

    CharSequence getGlyphText() {
        return mGlyphText;
    }

    public void setText(CharSequence unicodeText) {

        if (unicodeText == null) unicodeText = "";
        if (mUnicodeText == null) mUnicodeText = "";
        if (mGlyphText == null) mGlyphText = "";

        // just using the Editable interface method in order to keep all the logic in one place
        replace(0, mUnicodeText.length(), unicodeText, 0, unicodeText.length());

    }

    private void updateGlyphTextForUnicodeRange(int start, int end) {
        // initialize glyph indexes
        final int lengthUnicode = mUnicodeText.length();
        if (mGlyphIndexes == null) {
            mGlyphIndexes = new ArrayList<>(lengthUnicode);
            for (int i = 0; i < lengthUnicode; i++) {
                mGlyphIndexes.add(i, i);
            }
        } else if (mGlyphIndexes.size() < lengthUnicode) {
            final int size = mGlyphIndexes.size();
            for (int i = size; i < lengthUnicode; i++) {
                mGlyphIndexes.add(i, i);
            }
        }

        // update glyph indexes
        boolean indexingHasStarted = false;
        int glyphIndex = 0;
        if (start > 0) {
            glyphIndex = getGlyphIndexForUnicodeIndex(start - 1);
            if (glyphIndex > 0 || MongolCode.isRenderedGlyph(0, mUnicodeText)) {
                indexingHasStarted = true;
            }
        }
        for (int i = start; i < lengthUnicode; i++) {
            if (MongolCode.isRenderedGlyph(i, mUnicodeText)) {
                if (indexingHasStarted) {
                    glyphIndex++;
                }
                indexingHasStarted = true;
            }
            mGlyphIndexes.set(i, glyphIndex);
        }

        if (!(mUnicodeText instanceof Spanned)) return;

        // add spans to glyph string
        CharacterStyle[] spans = ((Spanned) mUnicodeText).getSpans(start, end, CharacterStyle.class);
        for (CharacterStyle span : spans) {
            final int unicodeStart = ((Spanned) mUnicodeText).getSpanStart(span);
            final int unicodeEnd = ((Spanned) mUnicodeText).getSpanEnd(span);
            final int glyphStart = getGlyphIndexForUnicodeIndex(unicodeStart);
            final int glyphEnd = getGlyphIndexForUnicodeIndex(unicodeEnd);
            ((SpannableStringBuilder) mGlyphText).setSpan(span, glyphStart, glyphEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
    }

    int getGlyphIndexForUnicodeIndex(int unicodeIndex) {
        // allow an index one past the end to support cursor selection
        if (unicodeIndex == 0) {
            return 0;
        } else if (unicodeIndex == mUnicodeText.length()) {
            return mGlyphText.length();
        }
        return mGlyphIndexes.get(unicodeIndex);
    }

    int getUnicodeIndexForGlyphIndex(int glyphIndex) {
        // calculating the glyph index when needed rather than maintaining a second index
        int length = mUnicodeText.length();
        for (int i = glyphIndex; i < length; i++) {
            if (mGlyphIndexes.get(i) == glyphIndex) {
                return i;
            }
        }
        return length;
    }

    // go to the start of the Mongol word from the indicated position
    private int getMongolWordStart(int position, CharSequence source) {
        int wordStart = position;
        for (int i = position - 1; i >= 0; i--) {
            final char thisChar = source.charAt(i);
            if (MongolCode.isMongolian(thisChar) || thisChar == MongolCode.Uni.NNBS) {
                wordStart = i;
            } else {
                break;
            }
        }
        return wordStart;
    }

    // go to the end of the Mongol word from the indicated position
    private int getMongolWordEnd(int position, CharSequence source) {
        int wordEnd = position;
        final int length = source.length();
        for (int i = position; i < length; i++) {
            final char thisChar = source.charAt(i);
            if (MongolCode.isMongolian(thisChar) || thisChar == MongolCode.Uni.NNBS) {
                wordEnd = i + 1;
            } else {
                break;
            }
        }
        return wordEnd;
    }

    ////////////////////////////// Editable interface methods ///////////////////////////

    /**
     * Replaces the Unicode range (st…en) in this MongolTextStorage Editable with
     * the slice (start…end) from source.
     *
     * @param st The Unicode start index of the range in this Editable that will be replaced
     * @param en The Unicode end index of the range in this Editable that will be replaced
     * @param source The CharSequence that will replace the (st…en) range
     * @param start The Unicode start index of the range in the source that will be used to replace
     * @param end The Unicode end index of the range in the source that will be used to replace
     * @return this MongolTextStorage editable
     */
    @Override
    public Editable replace(int st, int en, CharSequence source, int start, int end) {
        if (!(mUnicodeText instanceof SpannableStringBuilder)) {
            mUnicodeText = new SpannableStringBuilder(mUnicodeText);
            mGlyphText = new SpannableStringBuilder(mGlyphText);
        }
        // swap start and end if in wrong order
        if (st > en) {
            int temp = st;
            st = en;
            en = temp;
        }
        if (start > end) {
            int temp = start;
            start = end;
            end = temp;
        }

        int oldLength = mUnicodeText.length();

        // replace glyphs (expand to the whole word preceding and following)
        int wordStart = getMongolWordStart(st, mUnicodeText);
        int wordEnd = getMongolWordEnd(en, mUnicodeText);
        int glyphStart = getGlyphIndexForUnicodeIndex(wordStart);
        int glyphEnd = getGlyphIndexForUnicodeIndex(wordEnd);
        ((SpannableStringBuilder) mUnicodeText).replace(st, en, source, start, end);
        int adjustedEnd = wordEnd + (end - start) - (en - st);
        CharSequence unicodeReplacement = mUnicodeText.subSequence(wordStart, adjustedEnd);
        String glyphReplacement = mRenderer.unicodeToMenksoft(unicodeReplacement);
        ((SpannableStringBuilder) mGlyphText).replace(glyphStart, glyphEnd, glyphReplacement);
        updateGlyphTextForUnicodeRange(wordStart, adjustedEnd);

        if (mChangelistener != null)
            mChangelistener.onTextChanged(mUnicodeText, st, oldLength, mUnicodeText.length());

        return this;
    }

    @Override
    public Editable replace(int st, int en, CharSequence text) {
        return replace(st, en, text, 0, text.length());
    }

    @Override
    public Editable insert(int where, CharSequence text, int start, int end) {
        return replace(where, where, text, start, end);
    }

    @Override
    public Editable insert(int where, CharSequence text) {
        return insert(where, text, 0, text.length());
    }

    @Override
    public Editable delete(int st, int en) {
        return replace(st, en, "", 0, 0);
    }

    @Override
    public Editable append(CharSequence text) {
        return replace(length(), length(), text, 0, text.length());
    }

    @Override
    public Editable append(CharSequence text, int start, int end) {
        return replace(length(), length(), text, start, end) ;
    }

    @Override
    public Editable append(char text) {
        return append(String.valueOf(text));
    }

    @Override
    public void clear() {
        replace(0, length(), "", 0, 0);
    }

    @Override
    public void clearSpans() {
        if (!(mUnicodeText instanceof SpannableStringBuilder)) {
            return;
        }
        ((SpannableStringBuilder) mUnicodeText).clearSpans();
        ((SpannableStringBuilder) mGlyphText).clearSpans();

        final int length = mUnicodeText.length();
        if (mChangelistener != null)
            mChangelistener.onSpanChanged((Spanned) mUnicodeText, null, 0, 0, length, length);
    }

    @Override
    public void setFilters(InputFilter[] filters) {
        if (!(mUnicodeText instanceof SpannableStringBuilder)) {
            return;
        }
        // TODO: this is untested!
        int oldLength = mUnicodeText.length();
        ((SpannableStringBuilder) mUnicodeText).setFilters(filters);
        ((SpannableStringBuilder) mGlyphText).setFilters(filters);
        if (mChangelistener != null)
            mChangelistener.onTextChanged(mUnicodeText, 0, oldLength, mUnicodeText.length());
    }

    @Override
    public InputFilter[] getFilters() {
        if (!(mUnicodeText instanceof SpannableStringBuilder)) {
            return new InputFilter[0];
        }
        return ((SpannableStringBuilder) mUnicodeText).getFilters();
    }

    @Override
    public void getChars(int start, int end, char[] dest, int destoff) {
        if (!(mUnicodeText instanceof SpannableStringBuilder)) {
            ((String) mUnicodeText).getChars(start, end, dest, destoff);
        } else {
            ((SpannableStringBuilder) mUnicodeText).getChars(start, end, dest, destoff);
        }
    }

    @Override
    public void setSpan(Object what, int start, int end, int flags) {
        if (!(mUnicodeText instanceof SpannableStringBuilder)) {
            mUnicodeText = new SpannableStringBuilder(mUnicodeText);
            mGlyphText = new SpannableStringBuilder(mGlyphText);
        }
        ((SpannableStringBuilder) mUnicodeText).setSpan(what, start, end, flags);
        int glyphStart = getGlyphIndexForUnicodeIndex(start);
        int glyphEnd = getGlyphIndexForUnicodeIndex(end);
        ((SpannableStringBuilder) mGlyphText).setSpan(what, glyphStart, glyphEnd, flags);

        if (mChangelistener != null)
            mChangelistener.onSpanChanged((Spanned) mUnicodeText, what, start, start, end, end);
    }

    @Override
    public void removeSpan(Object what) {
        if (!(mUnicodeText instanceof Spanned)) {
            return;
        }
        if (!(mUnicodeText instanceof SpannableStringBuilder)) {
            return;
        }
        ((SpannableStringBuilder) mUnicodeText).removeSpan(what);
        ((SpannableStringBuilder) mGlyphText).removeSpan(what);

        final int length = mUnicodeText.length();
        if (mChangelistener != null)
            mChangelistener.onSpanChanged((Spanned) mUnicodeText, what, 0, 0, length, length);
    }

    @Override
    public <T> T[] getSpans(int start, int end, Class<T> type) {
        if (mUnicodeText instanceof Spanned) {
            return ((Spanned) mUnicodeText).getSpans(start, end, type);
        }
        return null;
    }

    @Override
    public int getSpanStart(Object tag) {
        if (mUnicodeText instanceof Spanned) {
            return ((Spanned) mUnicodeText).getSpanStart(tag);
        }
        return 0;
    }

    @Override
    public int getSpanEnd(Object tag) {
        if (mUnicodeText instanceof Spanned) {
            return ((Spanned) mUnicodeText).getSpanEnd(tag);
        }
        return 0;
    }

    @Override
    public int getSpanFlags(Object tag) {
        if (mUnicodeText instanceof Spanned) {
            return ((Spanned) mUnicodeText).getSpanFlags(tag);
        }
        return 0;
    }

    @Override
    public int nextSpanTransition(int start, int limit, Class type) {
        if (mUnicodeText instanceof Spanned) {
            return ((Spanned) mUnicodeText).nextSpanTransition(start, limit, type);
        }
        return 0;
    }

    @Override
    public int length() {
        return mUnicodeText.length();
    }

    @Override
    public char charAt(int index) {
        return mUnicodeText.charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return mUnicodeText.subSequence(start, end);
    }

    @Override
    public String toString() {
        return (mUnicodeText != null) ? mUnicodeText.toString() : "";
    }
}
