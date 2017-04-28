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


class MongolTextStorage implements Editable {

    private CharSequence mUnicodeText;
    private CharSequence mGlyphText;
    private MongolCode mRenderer;
    private int[] mGlyphArrayWithUnicodeIndexes;
    private int[] mUnicodeArrayWithGlyphIndexes;

    MongolTextStorage() {
        mRenderer = MongolCode.INSTANCE;
        mUnicodeText = "";
        mGlyphText = "";
    }

    MongolTextStorage(CharSequence unicodeText) {
        mRenderer = MongolCode.INSTANCE;
        setText(unicodeText);
    }

    CharSequence getUnicodeText() {
        return mUnicodeText;
    }

    CharSequence getGlyphText() {
        return mGlyphText;
    }

    public void setText(CharSequence unicodeText) {
        if (unicodeText == null) {
            mUnicodeText = "";
            mGlyphText = "";
            return;
        }

        mUnicodeText = unicodeText;
        if (unicodeText instanceof Spanned) {
            updateGlyphInfoForSpannedText();
//            mUnicodeArrayWithGlyphIndexes = new int[unicodeText.length()];
//            mGlyphText = mRenderer.unicodeToMenksoft(unicodeText.toString(), mUnicodeArrayWithGlyphIndexes);
//            setSpanOnRenderedText();
        } else {
            mGlyphText = mRenderer.unicodeToMenksoft(unicodeText.toString());
        }
    }

    // FIXME this is hugely inefitient because it recalculates everything rather than a range
    private void updateGlyphInfoForSpannedText() {
        mUnicodeArrayWithGlyphIndexes = new int[mUnicodeText.length()];
        mGlyphText = mRenderer.unicodeToMenksoft(mUnicodeText.toString(), mUnicodeArrayWithGlyphIndexes);
        setSpanOnRenderedText();
    }

    private void setSpanOnRenderedText() {
        if (!(mUnicodeText instanceof Spanned)) return;

        SpannableString spannable = new SpannableString(mGlyphText);
        CharacterStyle[] spans = ((Spanned) mUnicodeText).getSpans(0, mUnicodeText.length(), CharacterStyle.class);
        for (CharacterStyle span : spans) {
            int unicodeStart = ((Spanned) mUnicodeText).getSpanStart(span);
            int unicodeEnd = ((Spanned) mUnicodeText).getSpanEnd(span);
            int glyphStart = mUnicodeArrayWithGlyphIndexes[unicodeStart];
            int glyphEnd = mUnicodeArrayWithGlyphIndexes[unicodeEnd];
            spannable.setSpan(span, glyphStart, glyphEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        mGlyphText = spannable;
    }

    private int getGlyphIndexForUnicodeIndex(int unicodeIndex) {
        return mUnicodeArrayWithGlyphIndexes[unicodeIndex];
    }

    private int getUnicodeIndexForGlyphIndex(int glyphIndex) {
        // TODO
        return -1;
    }

    private char getUnicodeCharAt(int unicodeIndex) {
        // TODO
        return 0;
    }

    private char getGlyphCharAt(int glyphIndex) {
        // TODO
        return 0;
    }

    // Editable interface methods

    @Override
    public Editable replace(int st, int en, CharSequence source, int start, int end) {
        return null;
    }

    @Override
    public Editable replace(int st, int en, CharSequence text) {
        return null;
    }

    @Override
    public Editable insert(int where, CharSequence text, int start, int end) {
        return null;
    }

    @Override
    public Editable insert(int where, CharSequence text) {
        if (!(mUnicodeText instanceof SpannableStringBuilder)) {
            mUnicodeText = new SpannableStringBuilder(mUnicodeText);
        }
        ((SpannableStringBuilder) mUnicodeText).insert(where, text);
        updateGlyphInfoForSpannedText();
        return this;
    }

    @Override
    public Editable delete(int st, int en) {
        return null;
    }

    @Override
    public Editable append(CharSequence text) {
        return null;
    }

    @Override
    public Editable append(CharSequence text, int start, int end) {
        return null;
    }

    @Override
    public Editable append(char text) {
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public void clearSpans() {

    }

    @Override
    public void setFilters(InputFilter[] filters) {

    }

    @Override
    public InputFilter[] getFilters() {
        return new InputFilter[0];
    }

    @Override
    public void getChars(int start, int end, char[] dest, int destoff) {

    }

    @Override
    public void setSpan(Object what, int start, int end, int flags) {

    }

    @Override
    public void removeSpan(Object what) {

    }

    @Override
    public <T> T[] getSpans(int start, int end, Class<T> type) {
        return null;
    }

    @Override
    public int getSpanStart(Object tag) {
        return 0;
    }

    @Override
    public int getSpanEnd(Object tag) {
        return 0;
    }

    @Override
    public int getSpanFlags(Object tag) {
        return 0;
    }

    @Override
    public int nextSpanTransition(int start, int limit, Class type) {
        return 0;
    }

    @Override
    public int length() {
        return 0;
    }

    @Override
    public char charAt(int index) {
        return 0;
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return null;
    }
}
