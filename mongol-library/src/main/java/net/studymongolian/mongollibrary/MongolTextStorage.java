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
// TODO: render the unicode to glyphs using MongolCode
// TODO: maintain Unicode<->Glyph index maps with lazy instantiation
// TODO: handle spanned text
// TODO: update changes without needing to render everything again
//
// XXX can we keep this class package private?
// Is it actually needed by app developers?

import android.text.Editable;
import android.text.InputFilter;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.CharacterStyle;
import android.view.inputmethod.BaseInputConnection;


public class MongolTextStorage implements Editable {

    private CharSequence mUnicodeText;
    private CharSequence mGlyphText;
    private MongolCode mRenderer;
    //private int[] mGlyphArrayWithUnicodeIndexes;
    private int[] mUnicodeArrayWithGlyphIndexes;
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
        void onSpanChanged();
        // TODO pass more data into onSpanChanged
        // void onSpanChanged(Spanned buf, Object what, int oldStart, int newStart, int oldEnd, int newEnd);
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
        if (unicodeText == null) {
            unicodeText = "";
        }

        int oldLength;
        if (mUnicodeText == null) {
            oldLength = 0;
        } else {
            oldLength = mUnicodeText.length();
        }

        mUnicodeText = unicodeText;
        if (unicodeText instanceof Spanned) {
            updateGlyphInfoForSpannedText();
        } else {
            mGlyphText = mRenderer.unicodeToMenksoft(unicodeText.toString());
        }

        if (mChangelistener != null)
            mChangelistener.onTextChanged(mUnicodeText, 0, oldLength, mUnicodeText.length());
    }

    // FIXME this is hugely inefficient because it recalculates everything rather than a range
    private void updateGlyphInfoForSpannedText() {
        mUnicodeArrayWithGlyphIndexes = new int[mUnicodeText.length()];
        mGlyphText = mRenderer.unicodeToMenksoft(mUnicodeText.toString(), mUnicodeArrayWithGlyphIndexes);
        //setSpanOnRenderedText();

        if (!(mUnicodeText instanceof Spanned)) return;

        SpannableString spannable = new SpannableString(mGlyphText);
        CharacterStyle[] spans = ((Spanned) mUnicodeText).getSpans(0, mUnicodeText.length(), CharacterStyle.class);
        for (CharacterStyle span : spans) {
            int unicodeStart = ((Spanned) mUnicodeText).getSpanStart(span);
            int unicodeEnd = ((Spanned) mUnicodeText).getSpanEnd(span);
            int glyphStart = mUnicodeArrayWithGlyphIndexes[unicodeStart];
            int glyphEnd = mUnicodeArrayWithGlyphIndexes[unicodeEnd - 1] + 1;
            spannable.setSpan(span, glyphStart, glyphEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        mGlyphText = spannable;
    }

    int getGlyphIndexForUnicodeIndex(int unicodeIndex) {
        // allow an index one past the end to support cursor selection
        if (unicodeIndex == mUnicodeText.length()) return mGlyphText.length();
        return mUnicodeArrayWithGlyphIndexes[unicodeIndex];
    }

    int getUnicodeIndexForGlyphIndex(int glyphIndex) {
        // calculating the glyph index when needed rather than maintaining a second index
        int index = glyphIndex;
        int length = mUnicodeArrayWithGlyphIndexes.length;
        for (int i = glyphIndex; i < length; i++) {
            if (mUnicodeArrayWithGlyphIndexes[i] > glyphIndex) break;
            index = i;
        }
        return index;
    }

    // Editable interface methods

    @Override
    public Editable replace(int st, int en, CharSequence source, int start, int end) {
        if (!(mUnicodeText instanceof SpannableStringBuilder)) {
            mUnicodeText = new SpannableStringBuilder(mUnicodeText);
        }

        int oldLength;
        if (mUnicodeText == null) {
            oldLength = 0;
        } else {
            oldLength = mUnicodeText.length();
        }

        ((SpannableStringBuilder) mUnicodeText).replace(st, en, source, start, end);

        updateGlyphInfoForSpannedText();

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
        if (!(mUnicodeText instanceof SpannableStringBuilder)) {
            mUnicodeText = new SpannableStringBuilder(mUnicodeText);
        }

        int oldLength;
        if (mUnicodeText == null) {
            oldLength = 0;
        } else {
            oldLength = mUnicodeText.length();
        }

        ((SpannableStringBuilder) mUnicodeText).insert(where, text, start, end);
        updateGlyphInfoForSpannedText();

        if (mChangelistener != null)
            mChangelistener.onTextChanged(mUnicodeText, where, oldLength, mUnicodeText.length());

        return this;
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
        updateGlyphInfoForSpannedText();

        if (mChangelistener != null)
            mChangelistener.onSpanChanged();
    }

    @Override
    public void setFilters(InputFilter[] filters) {
        if (!(mUnicodeText instanceof SpannableStringBuilder)) {
            return;
        }
        ((SpannableStringBuilder) mUnicodeText).setFilters(filters);
        // TODO does mGlyphText need to be updated in any way?
        // TODO does mChangelistener need to be notified?
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
        }
        ((SpannableStringBuilder) mUnicodeText).setSpan(what, start, end, flags);
        updateGlyphInfoForSpannedText();

        if (mChangelistener != null)
            mChangelistener.onSpanChanged();
    }

    @Override
    public void removeSpan(Object what) {
        if (!(mUnicodeText instanceof Spanned)) {
            return;
        }
        if (!(mUnicodeText instanceof SpannableStringBuilder)) {
            mUnicodeText = new SpannableStringBuilder(mUnicodeText);
        }
        ((SpannableStringBuilder) mUnicodeText).removeSpan(what);
        updateGlyphInfoForSpannedText();

        if (mChangelistener != null)
            mChangelistener.onSpanChanged();
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
        return mUnicodeText.toString();
    }
}
