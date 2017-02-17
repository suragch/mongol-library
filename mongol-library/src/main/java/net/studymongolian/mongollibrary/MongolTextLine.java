package net.studymongolian.mongollibrary;


import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MongolTextLine {
    private static final boolean DEBUG = true;
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
        if (DEBUG) {
            Log.v("TLINE", "new: " + tl);
        }
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

    /**
     * Initializes a MongolTextLine and prepares it for use.
     *
     * @param paint the base paint for the line
     * @param text the text, can be Styled
     */
    void set(TextPaint paint, CharSequence text, int start, int end) {

        // 0 g 1    2 T  3 h  4 i   5s   6    7i   8s  9    10s  11o   12m  13e  14   15   16   17   18.  19   20  21
        //0067 0020 0054 0068 0069 0073 0020 0069 0073 0020 0073 006F 006D 0065 0020 6587 5B57 0020 002E 0020 D83D DE0A

        //SpannableStringBuilder currentRun = new SpannableStringBuilder();
        mPaint = paint;
        mText = text;
        mTextRunOffsets = new ArrayList<>(); // TODO recycle and reuse this for multiple lines
        //mOffsetsOfCharsToRotate = new ArrayList<Integer>();
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
                    // some other obsure character -> don't rotate it.
                    currentRunLength += charCount;
                }
            }
            offset += charCount;
        }

        if (currentRunLength > 0) {
            mTextRunOffsets.add(new TextRunOffset(currentRunStart, currentRunLength, false));
        }
    }

    private boolean isCJK(Character.UnicodeBlock block) {
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


    private boolean isJapaneseKana(Character.UnicodeBlock block) {
        return (
                Character.UnicodeBlock.HIRAGANA.equals(block) ||
                Character.UnicodeBlock.KATAKANA.equals(block) ||
                Character.UnicodeBlock.KATAKANA_PHONETIC_EXTENSIONS.equals(block));
    }



    private boolean isKoreanHangul(Character.UnicodeBlock block) {
        // TODO add hardcoded ranges for api 19
        return (Character.UnicodeBlock.HANGUL_JAMO.equals(block) ||
                //Character.UnicodeBlock.HANGUL_JAMO_EXTENDED_A.equals(block) ||    // api 19
                //Character.UnicodeBlock.HANGUL_JAMO_EXTENDED_B.equals(block) ||    // api 19
                Character.UnicodeBlock.HANGUL_COMPATIBILITY_JAMO.equals(block) ||
                Character.UnicodeBlock.HANGUL_SYLLABLES.equals(block));
    }




    private boolean isEmoji(int codepoint) {
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
        // FIXME top/bottom parameters are not being used

        // (x, y) are the start coordinates of each vertical line
        // where x is the top of the line and y is the baseline running down.
        // Don't confuse these with Paint.drawText coordinates.

        // top and bottom are the font metrics values in the normal
        // horizontal orientation of a text line.

        float width = 0;
        int start = 0;
        int end = 0;
        //float tempBottom = mPaint.getFontMetrics().bottom; // get this from bottom parameter?

        c.save();
        c.translate(x, y);
        c.rotate(90);

        for (TextRunOffset run : mTextRunOffsets) {

            start = run.getOffset();
            end = run.getOffset() + run.getLength();
            width = mPaint.measureText(mText, start, end);

            if (run.isRotated()) {

                // measure how far to move down and then move down
                c.translate(width, 0);

                // then rotate and draw
                c.save();

                c.rotate(-90);
                c.translate(-bottom, 0);
                c.drawText(mText, start, end, 0, 0, mPaint);

                c.restore();

            } else {

                c.drawText(mText, start, end, 0, 0, mPaint);
                c.translate(width, 0);
            }
        }




//        if (mOffsetsOfCharsToRotate.size() == 0) {
//            c.drawText(mText, 0, mText.length(), x, y, mPaint);
//        } else {
//            Log.i("TAG", "size: " + mOffsetsOfCharsToRotate.size());
//            Rect textBounds = new Rect();
//            int lastDrawnOffset = -1;
//            for (int offset : mOffsetsOfCharsToRotate) {
//                // first draw any normal text
//                float width = mPaint.measureText(mText, lastDrawnOffset + 1, offset + 1);
//                c.drawText(mText, lastDrawnOffset + 1, offset, x, y, mPaint);
//                // rotate and draw the current char
//                //c.rotate(-90);
//                c.translate(width, 0);
//                c.rotate(-90);
//                c.drawText(mText, offset, offset + 1, x, y, mPaint);
//
//                break;
//            }
//
//        }

        //c.drawText(mText, 0, mText.length(), x, y, mPaint);
        //c.drawCircle(0, 0, 10, mPaint);

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

    /**
     * Returns information about a position on the line.
     *
     * @param offset the line-relative character offset, between 0 and the
     * line length, inclusive
     * @param trailing true to measure the trailing edge of the character
     * before offset, false to measure the leading edge of the character
     * at offset.
     * @param fmi receives metrics information about the requested
     * character, can be null.
     * @return the signed offset from the leading margin to the requested
     * character edge.
     */
    float measure(int offset, boolean trailing, Paint.FontMetricsInt fmi) {

        return 0;
    }

    /**
     * Draws a unidirectional (but possibly multi-styled) run of text.
     *
     *
     * @param c the canvas to draw on
     * @param start the line-relative start
     * @param limit the line-relative limit
     * @param x the position of the run that is closest to the leading margin
     * @param top the top of the line
     * @param y the baseline
     * @param bottom the bottom of the line
     * @param needWidth true if the width value is required.
     * @return the signed width of the run, based on the paragraph direction.
     * Only valid if needWidth is true.
     */
    private float drawRun(Canvas c, int start,
                          int limit, float x, int top, int y, int bottom,
                          boolean needWidth) {

        return 0;
    }

    /**
     * Measures a unidirectional (but possibly multi-styled) run of text.
     *
     *
     * @param start the line-relative start of the run
     * @param offset the offset to measure to, between start and limit inclusive
     * @param limit the line-relative limit of the run
     * @param fmi receives metrics information about the requested
     * run, can be null.
     * @return the signed width from the start of the run to the leading edge
     * of the character at offset, based on the run (not paragraph) direction
     */
    private float measureRun(int start, int offset, int limit, Paint.FontMetricsInt fmi) {
        return 0;
    }



}
