package net.studymongolian.mongollibrary;


import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.BackgroundColorSpan;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;
import android.text.style.MetricAffectingSpan;
import android.text.style.RelativeSizeSpan;

import java.util.ArrayList;
import java.util.List;

class MongolTextLine {

    private static final int UNICODE_HANGUL_JAMO_START = 0x1100;
    private static final int UNICODE_HANGUL_JAMO_END = 0x11FF;
    private static final int UNICODE_CJK_START = 0x11FF;
    private static final int UNICODE_EMOJI_START = 0x1F000;
    private static final int MENKSOFT_START = 0xE234;
    private static final int MENKSOFT_END = 0xE34F;


    private TextPaint mPaint;
    private Paint mHighlightPaint;
    private CharSequence mText;
    private List<TextRun> mTextRuns;

    // XXX is having a static variable a bad idea here?
    // The purpose of the work paint is to avoid modifying paint
    // variables being passed in while measuring spanned text.
    private static final TextPaint mWorkPaint = new TextPaint();

    // A text run is a substring of text within the text line. The substring is made up of
    //     (1) a single emoji or CJK character,
    //     (2) a span of styled text, or
    //     (3) normal Mongolian/Latin/etc text.
    // A run may contain multiple types of spans covering the whole run but it should never
    // contain a span transition. It should also never contain multiple emoji or CJK characters.
    private class TextRun {
        int offset;             // the start position of the run in the text
        int length;             // number of chars in the run
        boolean isRotated;      // whether run is emoji or CJK (and thus should be rotated)
        float measuredWidth;    // horizontal line orientation (but height of emoji/CJK)
        float measuredHeight;   // horizontal line orientation (but width of emoji/CJK)

        TextRun(int offset, int length, boolean isRotated, boolean isSpanned) {

            this.offset = offset;
            this.length = length;
            this.isRotated = isRotated;

            TextPaint wp;
            if (isSpanned) {
                wp = mWorkPaint;
                wp.set(mPaint);
                MetricAffectingSpan[] spans = ((Spanned) mText).getSpans(offset, offset + length, MetricAffectingSpan.class);
                for(MetricAffectingSpan span : spans) {
                    span.updateDrawState(wp);
                }
            } else {
                wp = mPaint;
            }

            if (isRotated) {
                measuredWidth = wp.getFontMetrics().bottom - wp.getFontMetrics().top;
                measuredHeight = wp.measureText(mText, offset, offset + length);
            } else {
                measuredWidth = wp.measureText(mText, offset, offset + length);
                measuredHeight = wp.getFontMetrics().bottom - wp.getFontMetrics().top;
            }
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
        tl.mHighlightPaint = null;
        tl.mTextRuns = null;
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

        int nextSpanTransition = 0;
        boolean isSpanned = text instanceof Spanned;
        mPaint = paint;
        mHighlightPaint = new Paint();
        mText = text;
        mTextRuns = new ArrayList<>(); // TODO recycle and reuse this for multiple lines
        int charCount;
        int currentRunStart = start;
        int currentRunLength = 0;

        if (isSpanned) {
            nextSpanTransition = ((Spanned) mText).nextSpanTransition(start, end, CharacterStyle.class);
        }

        for (int offset = start; offset < end; ) {
            final int codepoint = Character.codePointAt(mText, offset);
            charCount = Character.charCount(codepoint);

            // Rotate Chinese, emoji, etc
            Character.UnicodeBlock block = Character.UnicodeBlock.of(codepoint);
            if (isCJK(block) ||
                    isJapaneseKana(block) ||
                    isKoreanHangul(block) ||
                    isEmoji(codepoint)) {
                // save any old normal (nonrotated) runs
                if (currentRunLength > 0) {
                    mTextRuns.add(new TextRun(currentRunStart, currentRunLength, false, isSpanned));
                }
                // save this rotated character
                mTextRuns.add(new TextRun(offset, charCount, true, isSpanned));
                // reset normal run
                currentRunStart = offset + charCount;
                currentRunLength = 0;
            } else {
                // Mongolian, Latin, etc. Don't rotate.
                if (isSpanned && nextSpanTransition == offset) {
                    if (currentRunLength > 0) {
                        mTextRuns.add(new TextRun(currentRunStart, currentRunLength, false, isSpanned));
                    }
                    // reset normal run
                    currentRunStart = offset;
                    currentRunLength = charCount;
                    nextSpanTransition = ((Spanned) mText).nextSpanTransition(offset, end, CharacterStyle.class);
                } else {
                    currentRunLength += charCount;
                }
            }
            offset += charCount;
        }

        if (currentRunLength > 0) {
            mTextRuns.add(new TextRun(currentRunStart, currentRunLength, false, isSpanned));
        }
    }

    private boolean isMenksoft(int codepoint) {
        return codepoint >= MENKSOFT_START && codepoint <= MENKSOFT_END;
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
    void draw(Canvas c, float x, float top, float y, int bottom) {
        // FIXME top parameter is not being used

        // (x, y) are the start coordinates of each vertical line
        // where x is the top of the line and y is the baseline running down.
        // Don't confuse these with Paint.drawText coordinates.

        // top and bottom are the font metrics values in the normal
        // horizontal orientation of a text line.

        boolean hasSpan = mText instanceof Spanned;
        int start;
        int end;

        c.save();
        c.translate(x, y);
        c.rotate(90);

        for (TextRun run : mTextRuns) {

            start = run.offset;
            end = run.offset + run.length;

            TextPaint wp;
            if (hasSpan) {
                wp = mWorkPaint;
                wp.set(mPaint);

                // gets character style spans
                CharacterStyle[] csSpans = ((Spanned) mText).getSpans(start, end, CharacterStyle.class);
                for (CharacterStyle span : csSpans) {
                    span.updateDrawState(wp);
                }
            } else {
                wp = mPaint;
            }

            if (run.isRotated) {

                // background color
                if (wp.bgColor != 0) {
                    int previousColor = wp.getColor();
                    Paint.Style previousStyle = wp.getStyle();
                    wp.setColor(wp.bgColor);
                    wp.setStyle(Paint.Style.FILL);
                    c.drawRect(0, top, run.measuredHeight, bottom, wp);
                    wp.setStyle(previousStyle);
                    wp.setColor(previousColor);
                }

                // TODO draw "underline" (on the right side)

                // move down
                c.translate(run.measuredHeight, 0);

                // then rotate and draw
                c.save();
                c.rotate(-90);
                c.translate(-bottom, -bottom);
                c.drawText(mText, start, end, -wp.baselineShift, 0, wp);
                c.restore();

            } else {

                float width = wp.measureText(mText, start, end);

                // background color
                if (wp.bgColor != 0) {
                    int previousColor = wp.getColor();
                    Paint.Style previousStyle = wp.getStyle();
                    wp.setColor(wp.bgColor);
                    wp.setStyle(Paint.Style.FILL);
                    c.drawRect(0, top, width, bottom, wp);
                    wp.setStyle(previousStyle);
                    wp.setColor(previousColor);
                }


                // TODO underline
                // Underline works partially but it draws the line on the left side of the
                // characters. It should be the right.
                // More importantly, rotated characters are underlined below. They should
                // be "underlined" on the side.
                // See https://android.googlesource.com/platform/frameworks/base/+/master/core/java/android/text/TextLine.java#741
                // for how TextLine does it. Unfortunately TextPaint.underlineColor is hidden.
                // TODO wp.isUnderlineText() to check if underlined
                // Could also make a MongolTextPaint version of TextPaint

                c.drawText(mText, start, end, 0, wp.baselineShift, wp);
                c.translate(width, 0);
            }

        }

        c.restore();
    }


    RectF measure() {

        float widthSum = 0;
        float maxHeight = 0;

        for (TextRun run : mTextRuns) {
            widthSum += run.measuredWidth;
            maxHeight = Math.max(maxHeight, run.measuredHeight);
        }

        // left, top, right, bottom (for horizontal line orientation)
        return new RectF(0, 0, widthSum, maxHeight);
    }

}
