package net.studymongolian.mongollibrary;

// The purpose of this class is to store the text for TextViews.
// It is a wrapper for the unicode to glyph rendering and indexing
// so that developers can use Unicode exclusively without worrying
// about the glyph rendering and indexing.
//
// glyph: this is what is displayed by the font and MongolTextView
// unicode: this is the encoding that the app user and library user works with
//
// TODO: render the unicode to glyphs using MongolCode
// TODO: maintain Unicode<->Glyph index maps with lazy instantiation
// TODO: handle spanned text
// TODO: update changes without needing to render everything again


import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.CharacterStyle;

import java.util.Map;

public class MongolTextStorage {

    private CharSequence mUnicodeText;
    private CharSequence mGlyphText;
    private MongolCode mRenderer;
    private int[] mGlyphArrayWithUnicodeIndexes;
    private int[] mUnicodeArrayWithGlyphIndexes;

    public MongolTextStorage() {
        mRenderer = MongolCode.INSTANCE;
        mUnicodeText = "";
        mGlyphText = "";
    }

    public MongolTextStorage(CharSequence unicodeText) {
        mRenderer = MongolCode.INSTANCE;
        setText(unicodeText);
    }

    public CharSequence getUnicodeText() {
        return mUnicodeText;
    }

    public CharSequence getGlyphText() {
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
            mUnicodeArrayWithGlyphIndexes = new int[unicodeText.length()];
            mGlyphText = mRenderer.unicodeToMenksoft(unicodeText.toString(), mUnicodeArrayWithGlyphIndexes);
            setSpanOnRenderedText();
        } else {
            mGlyphText = mRenderer.unicodeToMenksoft(unicodeText.toString());
        }
    }

    private void setSpanOnRenderedText() {
        if (!(mUnicodeText instanceof Spanned)) return;

        SpannableString spannable = new SpannableString(mGlyphText);
        CharacterStyle[] spans = ((Spanned) mUnicodeText).getSpans(0, mUnicodeText.length(), CharacterStyle.class);
        for (CharacterStyle span : spans) {
            int unicodeStart = ((Spanned) mUnicodeText).getSpanStart(span);
            int unicodeEnd = ((Spanned) mUnicodeText).getSpanEnd(span);
            int glyphStart = getGlyphIndexForUnicodeIndex(unicodeStart);
            int glyphEnd = getGlyphIndexForUnicodeIndex(unicodeEnd);
            spannable.setSpan(span, glyphStart, glyphEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        mGlyphText = spannable;
    }

    public int getGlyphIndexForUnicodeIndex(int unicodeIndex) {
//        if (mUnicodeArrayWithGlyphIndexes == null) {
//
//            populateIndexArrays();
//        }
        return mUnicodeArrayWithGlyphIndexes[unicodeIndex];
    }

//    private void populateIndexArrays() {
//        mUnicodeArrayWithGlyphIndexes = new int[mUnicodeText.length()];
//        int glyphIndex = 0;
//        for (int i = 0; i < mUnicodeText.length(); i++) {
//            if (mUnicodeText.charAt(i) == nonPrintingChar) {
//                glyphIndex++;
//                continue;
//            } else {
//
//            }
//            mUnicodeArrayWithGlyphIndexes[i] = glyphIndex;
//        }
//    }

    public int getUnicodeIndexForGlyphIndex(int glyphIndex) {
        // TODO
        return -1;
    }

    public char getUnicodeCharAt(int unicodeIndex) {
        // TODO
        return 0;
    }

    public char getGlyphCharAt(int glyphIndex) {
        // TODO
        return 0;
    }

}
