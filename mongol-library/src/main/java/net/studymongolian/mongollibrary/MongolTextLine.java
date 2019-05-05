package net.studymongolian.mongollibrary;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.Spanned;
import android.text.style.CharacterStyle;
import android.text.style.MetricAffectingSpan;

import java.util.ArrayList;
import java.util.List;

class MongolTextLine {

    private static final float UNDERLINE_THICKNESS_PROPORTION = 1 / 16f;

    private final static int MONGOL_QUICKCHECK_START = 0x1800;
    private final static int MONGOL_QUICKCHECK_END = 0x2060;
    private final static int KOREAN_JAMO_START = 0x1100;
    private final static int KOREAN_JAMO_END = 0x11FF;
    private final static int CJK_RADICAL_SUPPLEMENT_START = 0x2E80;
    private final static int CJK_SYMBOLS_AND_PUNCTUATION_START = 0x3000;
    private final static int CJK_SYMBOLS_AND_PUNCTUATION_MENKSOFT_END = 0x301C;
    private final static int CIRCLE_NUMBER_21 = 0x3251;
    private final static int CIRCLE_NUMBER_35 = 0x325F;
    private final static int CIRCLE_NUMBER_36 = 0x32B1;
    private final static int CIRCLE_NUMBER_50 = 0x32BF;
    private final static int CJK_UNIFIED_IDEOGRAPHS_END = 0x9FFF;
    private final static int HANGUL_SYLLABLES_START = 0xAC00;
    private final static int HANGUL_JAMO_EXTENDED_B_END = 0xD7FF;
    private final static int CJK_COMPATIBILITY_IDEOGRAPHS_START = 0xF900;
    private final static int CJK_COMPATIBILITY_IDEOGRAPHS_END = 0xFAFF;
    private static final int UNICODE_EMOJI_START = 0x1F000;

    private TextPaintPlus mPaint;
    private CharSequence mText;
    private List<TextRun> mTextRuns;

    // XXX is having a static variable a bad idea here?
    // The purpose of the work paint is to avoid modifying paint
    // variables being passed in while measuring spanned text.
    private static final TextPaintPlus sWorkPaint = new TextPaintPlus();

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

            TextPaintPlus wp;
            if (isSpanned) {
                wp = sWorkPaint;
                wp.set(mPaint);
                MetricAffectingSpan[] spans = ((Spanned) mText).getSpans(offset, offset + length, MetricAffectingSpan.class);
                for (MetricAffectingSpan span : spans) {
                    span.updateDrawState(wp);
                }
            } else {
                wp = mPaint;
            }

            // just record the normal non-rotated values here
            // measure and draw will take rotation into account
            measuredWidth = wp.measureText(mText, offset, offset + length);
            measuredHeight = wp.getFontMetrics().bottom - wp.getFontMetrics().top;
        }
    }

    private static final MongolTextLine[] sCached = new MongolTextLine[3];

    static MongolTextLine obtain() {
        MongolTextLine tl;
        synchronized (sCached) {
            for (int i = sCached.length; --i >= 0; ) {
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

    static void recycle(MongolTextLine tl) {
        tl.mText = null;
        tl.mPaint = null;
        tl.mTextRuns = null;
        synchronized (sCached) {
            for (int i = 0; i < sCached.length; ++i) {
                if (sCached[i] == null) {
                    sCached[i] = tl;
                    break;
                }
            }
        }
        // XXX do we need to do something with sWorkPaint here?
    }


    void set(TextPaintPlus paint, CharSequence text, int start, int end) {

        int nextSpanTransition = 0;
        boolean isSpanned = text instanceof Spanned;
        mPaint = paint;
        mText = text;
        mTextRuns = new ArrayList<>(); // TODO recycle and reuse this for multiple lines?
        int charCount;
        int currentRunStart = start;
        int currentRunLength = 0;

        if (isSpanned) {
            nextSpanTransition = ((Spanned) mText).nextSpanTransition(start, end, CharacterStyle.class);
        }

        for (int offset = start; offset < end; ) {
            final int codePoint = Character.codePointAt(mText, offset);
            charCount = Character.charCount(codePoint);

            // Rotate Chinese, emoji, etc
            if (isRotated(codePoint)) {
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
                        mTextRuns.add(new TextRun(currentRunStart, currentRunLength,
                                false, true));
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

    @SuppressWarnings("RedundantIfStatement")
    private static boolean isRotated(int codePoint) {

        // TODO rotate single numbers
        //if (codePoint >= 0x0030 && codePoint < 0x0039) return true;


        // Quick return: most Mongol chars should be in this range
        if (codePoint >= MONGOL_QUICKCHECK_START && codePoint < MONGOL_QUICKCHECK_END) return false;

        // Korean Jamo
        if (codePoint < KOREAN_JAMO_START) return false; // latin, etc
        if (codePoint <= KOREAN_JAMO_END) return true;

        // Chinese and Japanese
        if (codePoint >= CJK_RADICAL_SUPPLEMENT_START && codePoint <= CJK_UNIFIED_IDEOGRAPHS_END) {
            // exceptions for font handled punctuation
            if (codePoint >= CJK_SYMBOLS_AND_PUNCTUATION_START
                    && codePoint <= CJK_SYMBOLS_AND_PUNCTUATION_MENKSOFT_END) return false;
            if (codePoint >= CIRCLE_NUMBER_21 && codePoint <= CIRCLE_NUMBER_35) return false;
            if (codePoint >= CIRCLE_NUMBER_36 && codePoint <= CIRCLE_NUMBER_50) return false;
            return true;
        }

        // Korean Hangul
        if (codePoint >= HANGUL_SYLLABLES_START && codePoint <= HANGUL_JAMO_EXTENDED_B_END)
            return true;

        // More Chinese
        if (codePoint >= CJK_COMPATIBILITY_IDEOGRAPHS_START
                && codePoint <= CJK_COMPATIBILITY_IDEOGRAPHS_END)
            return true;

        // TODO the Half-width and Full-width Forms (0xFF00--0xFFEF) might be worth rotating
        // The problem is the Menksoft font already rotated a few (but not all) of them.

        // Emoji
        if (isEmoji(codePoint)) return true;

        // all other code points
        return false;
    }

    private static boolean isEmoji(int codePoint) {
        // FIXME this will rotate some things that maybe shouldn't be rotated
        // TODO there are a few other random emoji in other places as well
        return codePoint > UNICODE_EMOJI_START;
    }


    /**
     * Renders the TextLine.
     *
     * @param c      the canvas to render on
     * @param x      the leading margin position
     * @param top    the top of the line
     * @param y      the baseline
     * @param bottom the bottom of the line
     */
    void draw(Canvas c, float x, float top, float y, int bottom) {

        // (x, y) are the start coordinates of each vertical line
        // where x is the top of the line and y is the baseline running down.
        // Don't confuse these with Paint.drawText coordinates.

        // top and bottom are the font metrics values in the normal
        // horizontal orientation of a text line.

        boolean hasSpan = mText instanceof Spanned;

        c.save();
        c.translate(x, y);
        c.rotate(90);

        for (TextRun run : mTextRuns) {

            int start = run.offset;
            int end = run.offset + run.length;

            TextPaintPlus wp;
            if (hasSpan) {
                wp = sWorkPaint;
                wp.set(mPaint);
                CharacterStyle[] csSpans = ((Spanned) mText).getSpans(start, end, CharacterStyle.class);
                for (CharacterStyle span : csSpans) {
                    span.updateDrawState(wp);
                }
            } else {
                wp = mPaint;
            }

            float width = (run.isRotated) ? run.measuredHeight : wp.measureText(mText, start, end);

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

            // shadow for rotated text
            if (run.isRotated && wp.hasShadowLayer()) {
                wp.setShadowLayer(
                        wp.getShadowLayerRadius(),
                        -wp.getShadowLayerDx(),  // mirror x coordinate
                        wp.getShadowLayerDy(),
                        wp.getShadowLayerColor());
            }

            // text stroke
            if (wp.hasStroke()) {
                drawTextStroke(c, run, bottom, width, start, end, wp);
            }

            // "underline" (to the right of vertical text)
            if (wp.isUnderlineText()) {
                wp.setUnderlineText(false);

                Paint.Style previousStyle = wp.getStyle();
                wp.setStyle(Paint.Style.FILL);
                float underlineThickness = (top - bottom) * UNDERLINE_THICKNESS_PROPORTION;
                c.drawRect(0, top, width, top - underlineThickness, wp);
                wp.setStyle(previousStyle);
            }

            // text
            drawTextRun(c, run, bottom, width, start, end, wp);

            // move into position for next text run
            c.translate(width, 0);
        }

        c.restore();
    }

    private void drawTextStroke(Canvas c, TextRun run,
                                int bottom, float width, int start, int end, TextPaintPlus wp) {
        int previousColor = wp.getColor();
        Paint.Style previousStyle = wp.getStyle();

        wp.setColor(wp.getStrokeColor());

        if (previousColor != Color.TRANSPARENT && wp.hasShadowLayer())
            wp.setStyle(Paint.Style.FILL_AND_STROKE);
        else
            wp.setStyle(Paint.Style.STROKE);

        drawTextRun(c, run, bottom, width, start, end, wp);

        wp.setStyle(previousStyle);
        wp.setColor(previousColor);

        // shadow no longer needed for text since it was already drawn for the stroke
        // unless it is an emoji
        if (!wp.hasShadowLayer()) return;
        if (run.isRotated && isEmoji(Character.codePointAt(mText, run.offset))) {
            return;
        }
        wp.clearShadowLayer();
    }

    private void drawTextRun(Canvas c, TextRun run,
                             int bottom, float width, int start, int end, TextPaintPlus wp) {
        if (run.isRotated) {
            c.save();
            c.rotate(-90);
            c.translate(-bottom, width - bottom);
            c.drawText(mText, start, end, -wp.baselineShift, 0, wp);
            c.restore();
        } else {
            c.drawText(mText, start, end, 0, wp.baselineShift, wp);
        }
    }

    RectF measure() {

        float widthSum = 0;
        float maxHeight = 0;

        for (TextRun run : mTextRuns) {
            if (run.isRotated) {
                //noinspection SuspiciousNameCombination
                widthSum += run.measuredHeight;
                maxHeight = Math.max(maxHeight, run.measuredWidth);
            } else {
                widthSum += run.measuredWidth;
                maxHeight = Math.max(maxHeight, run.measuredHeight);
            }
        }

        // left, top, right, bottom (for horizontal line orientation)
        return new RectF(0, 0, widthSum, maxHeight);
    }


    int getOffsetForAdvance(float advance) {
        boolean hasSpan = mText instanceof Spanned;
        int offset = 0;
        float oldWidth = 0;
        float newWidth = 0;
        // measure each run and compare sum to advance
        for (TextRun run : mTextRuns) {
            final int start = run.offset;
            final int length = run.length;
            newWidth += run.measuredWidth;
            if (advance >= newWidth) {
                oldWidth = newWidth;
                offset += length;
            } else { // overshot so break up the run to the nearest offset
                if (run.isRotated) {
                    // choose the closer offset
                    if (advance - oldWidth > newWidth - advance) {
                        offset += length;
                    }
                    break;
                }

                TextPaintPlus wp = sWorkPaint;
                wp.set(mPaint);
                if (hasSpan) {
                    MetricAffectingSpan[] maSpans = ((Spanned) mText).getSpans(start, start + length, MetricAffectingSpan.class);
                    for (MetricAffectingSpan span : maSpans) {
                        span.updateDrawState(wp);
                    }
                }

                float[] measuredWidth = new float[1];
                float maxWidth = advance - oldWidth;
                int charactersMeasured = wp.breakText(mText, start, start + length, true, maxWidth, measuredWidth);
                offset += charactersMeasured;
                newWidth = oldWidth + measuredWidth[0];
                int nextCharIndex = start + charactersMeasured;
                float widthOfNextChar = wp.measureText(mText, nextCharIndex, nextCharIndex + 1);
                // choose the closer offset
                if (advance - newWidth > newWidth + widthOfNextChar - advance) {
                    offset++;
                }
                break;
            }
        }
        return offset;
    }
}
