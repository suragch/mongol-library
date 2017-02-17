package net.studymongolian.mongollibrary;


import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextPaint;

import java.util.ArrayList;
import java.util.List;

public class MongolTextLine {

    private static final int UNICODE_HANGUL_JAMO_START = 0x1100;
    private static final int UNICODE_HANGUL_JAMO_END = 0x11FF;
    private static final int UNICODE_CJK_START = 0x11FF;
    private static final int UNICODE_EMOJI_START = 0x1F000;

    private TextPaint mPaint;
    private CharSequence mText;
    private List<TextRunOffset> mTextRunOffsets;



    private class TextRunOffset {
        private int mOffset;
        private int mLength;
        private boolean mIsRotated;

        public TextRunOffset(int offset, int length, boolean isRotated) {
            mOffset = offset;
            mLength = length;
            mIsRotated = isRotated;
        }

        public int getOffset() {
            return mOffset;
        }
        public int getLength() {
            return mLength;
        }
        public boolean isRotated() {
            return mIsRotated;
        }
    }



    private static final MongolTextLine[] sCached = new MongolTextLine[3];

    static MongolTextLine obtain() {
        MongolTextLine tl;
        synchronized (sCached) {
            for (int i = sCached.length; --i >= 0;) {
                if (sCached[i] != null) {
                    tl = sCached[i];
                    sCached[i] = null;
                    return tl;
                }
            }
        }
        tl = new MongolTextLine();

        return tl;
    }

    static MongolTextLine recycle(MongolTextLine tl) {
        tl.mText = null;
        tl.mPaint = null;
        tl.mTextRunOffsets = null;
        synchronized(sCached) {
            for (int i = 0; i < sCached.length; ++i) {
                if (sCached[i] == null) {
                    sCached[i] = tl;
                    break;
                }
            }
        }
        return null;
    }



    void set(TextPaint paint, CharSequence text, int start, int end) {

        mPaint = paint;
        mText = text;
        mTextRunOffsets = new ArrayList<>(); // TODO recycle and reuse this for multiple lines
        int charCount;
        int currentRunStart = start;
        int currentRunLength = 0;
        final int length = end - start;
        for (int offset = start; offset < end; ) {
            final int codepoint = Character.codePointAt(mText, offset);
            charCount = Character.charCount(codepoint);

            // first check for Mongolian/latin/etc. (less than CJK and not Korean Jamo)
            // Most chars are expected to be here so this is an early exit to avoid
            // checking every single character for CJK and Emoji
            if ((codepoint > UNICODE_HANGUL_JAMO_END && codepoint < UNICODE_CJK_START) || // Mongolian, etc
                    codepoint < UNICODE_HANGUL_JAMO_START) { // English, etc
                currentRunLength += charCount;
            } else {
                // Check for Chinese and emoji
                Character.UnicodeBlock block = Character.UnicodeBlock.of(codepoint);
                if (isCJK(block) ||
                        isJapaneseKana(block) ||
                        isKoreanHangul(block) ||
                        isEmoji(codepoint)) {
                    // save any old normal (nonrotated) runs
                    if (currentRunLength > 0) {
                        mTextRunOffsets.add(new TextRunOffset(currentRunStart, currentRunLength, false));
                    }
                    // save this rotated character
                    mTextRunOffsets.add(new TextRunOffset(offset, charCount, true));
                    // reset normal run
                    currentRunStart = offset + charCount;
                    currentRunLength = 0;
                } else {
                    // some other obscure character -> don't rotate it.
                    currentRunLength += charCount;
                }
            }
            offset += charCount;
        }

        if (currentRunLength > 0) {
            mTextRunOffsets.add(new TextRunOffset(currentRunStart, currentRunLength, false));
        }
    }

    private static boolean isCJK(Character.UnicodeBlock block) {
        // TODO add hardcoded ranges for api 19 (and EXTENSION_E?)
        return (
                Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS.equals(block)||
                Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A.equals(block) ||
                Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B.equals(block) ||
                //Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_C.equals(block) || // api 19
                //Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_D.equals(block) || // api 19
                Character.UnicodeBlock.CJK_COMPATIBILITY.equals(block) ||
                Character.UnicodeBlock.CJK_COMPATIBILITY_FORMS.equals(block) ||
                Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS.equals(block) ||
                Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS_SUPPLEMENT.equals(block) ||
                Character.UnicodeBlock.CJK_RADICALS_SUPPLEMENT.equals(block) ||
                //Character.UnicodeBlock.CJK_STROKES.equals(block) ||                        // api 19
                Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION.equals(block) ||
                Character.UnicodeBlock.ENCLOSED_CJK_LETTERS_AND_MONTHS.equals(block) ||
                //Character.UnicodeBlock.ENCLOSED_IDEOGRAPHIC_SUPPLEMENT.equals(block) ||    // api 19
                Character.UnicodeBlock.KANGXI_RADICALS.equals(block) ||
                Character.UnicodeBlock.IDEOGRAPHIC_DESCRIPTION_CHARACTERS.equals(block));
    }


    private static boolean isJapaneseKana(Character.UnicodeBlock block) {
        return (
                Character.UnicodeBlock.HIRAGANA.equals(block) ||
                Character.UnicodeBlock.KATAKANA.equals(block) ||
                Character.UnicodeBlock.KATAKANA_PHONETIC_EXTENSIONS.equals(block));
    }



    private static boolean isKoreanHangul(Character.UnicodeBlock block) {
        // TODO add hardcoded ranges for api 19
        return (Character.UnicodeBlock.HANGUL_JAMO.equals(block) ||
                //Character.UnicodeBlock.HANGUL_JAMO_EXTENDED_A.equals(block) ||    // api 19
                //Character.UnicodeBlock.HANGUL_JAMO_EXTENDED_B.equals(block) ||    // api 19
                Character.UnicodeBlock.HANGUL_COMPATIBILITY_JAMO.equals(block) ||
                Character.UnicodeBlock.HANGUL_SYLLABLES.equals(block));
    }




    private static boolean isEmoji(int codepoint) {
        // XXX later may want to check specific code blocks, for now this is ok.
        return (codepoint >= UNICODE_EMOJI_START);
    }

    /**
     * Renders the TextLine.
     *
     * @param c the canvas to render on
     * @param x the leading margin position
     * @param top the top of the line
     * @param y the baseline
     * @param bottom the bottom of the line
     */
    void draw(Canvas c, float x, int top, int y, int bottom) {
        // FIXME top parameter is not being used

        // (x, y) are the start coordinates of each vertical line
        // where x is the top of the line and y is the baseline running down.
        // Don't confuse these with Paint.drawText coordinates.

        // top and bottom are the font metrics values in the normal
        // horizontal orientation of a text line.

        float fontHeight = mPaint.getFontMetrics().descent - mPaint.getFontMetrics().ascent;
        int start = 0;
        int end = 0;
        //float tempBottom = mPaint.getFontMetrics().bottom; // get this from bottom parameter?

        c.save();
        c.translate(x, y);
        c.rotate(90);

        for (TextRunOffset run : mTextRunOffsets) {

            start = run.getOffset();
            end = run.getOffset() + run.getLength();
            //width = mPaint.measureText(mText, start, end);

            if (run.isRotated()) {
            //if (false) {

                // move down
                c.translate(fontHeight, 0);

                // then rotate and draw
                c.save();

                c.rotate(-90);
                c.translate(-bottom, -bottom);
                c.drawText(mText, start, end, 0, 0, mPaint);

                c.restore();

            } else {

                c.drawText(mText, start, end, 0, 0, mPaint);
                float width = mPaint.measureText(mText, start, end);
                c.translate(width, 0);
            }
        }

        c.restore();
    }

    /**
     * Returns metrics information for the entire line.
     *
     * @param fmi receives font metrics information, can be null
     * @return the signed width of the line
     */
    float metrics(Paint.FontMetricsInt fmi) {
        return 0;
    }


    public static float measure(TextPaint paint, CharSequence text, int start, int end) {
        float measuredSum = 0;
        float lineHeight = paint.getFontMetrics().descent - paint.getFontMetrics().ascent;
        int charCount;
        int currentRunStart = start;
        int currentRunLength = 0;
        for (int offset = start; offset < end; ) {
            final int codepoint = Character.codePointAt(text, offset);
            charCount = Character.charCount(codepoint);

            // first check for Mongolian/latin/etc. (less than CJK and not Korean Jamo)
            // Most chars are expected to be here so this is an early exit to avoid
            // checking every single character for CJK and Emoji
            if ((codepoint > UNICODE_HANGUL_JAMO_END && codepoint < UNICODE_CJK_START) || // Mongolian, etc
                    codepoint < UNICODE_HANGUL_JAMO_START) { // English, etc
                currentRunLength += charCount;
            } else {
                // Check for Chinese and emoji
                Character.UnicodeBlock block = Character.UnicodeBlock.of(codepoint);
                if (isCJK(block) ||
                        isJapaneseKana(block) ||
                        isKoreanHangul(block) ||
                        isEmoji(codepoint)) {
                    // save any old normal (nonrotated) runs
                    if (currentRunLength > 0) {
                        measuredSum += paint.measureText(text, currentRunStart, currentRunStart + currentRunLength);
                        //mTextRunOffsets.add(new TextRunOffset(currentRunStart, currentRunLength, false));
                    }
                    // save this rotated character
                    measuredSum += lineHeight;
                    //mTextRunOffsets.add(new TextRunOffset(offset, charCount, true));
                    // reset normal run
                    currentRunStart = offset + charCount;
                    currentRunLength = 0;
                } else {
                    // some other obscure character -> don't rotate it.
                    currentRunLength += charCount;
                }
            }
            offset += charCount;
        }

        if (currentRunLength > 0) {
            measuredSum += paint.measureText(text, currentRunStart, currentRunStart + currentRunLength);
            //mTextRunOffsets.add(new TextRunOffset(currentRunStart, currentRunLength, false));
        }

        return measuredSum;
    }



}
