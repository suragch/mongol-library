package net.studymongolian.mongollibrary;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.Gravity;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;


public class MongolLayout {

    private CharSequence mText;
    private TextPaint mTextPaint;
    private int mHeight;
    private int mAlignment; // Use Gravity for now
    private float mSpacingMult;
    private float mSpacingAdd;
    private List<LineInfo> mLinesInfo;
    private List<BreakInfo> mBreaks;
    private int mLineWidth; // distance from line to line

    private static final char CHAR_NEW_LINE = '\n';
    private static final char CHAR_TAB = '\t';
    private static final char CHAR_SPACE = ' ';
    private static final char CHAR_ZWSP = '\u200B';


    public MongolLayout(CharSequence text, int start, int end,
                              TextPaint paint, int height,
                              int align, float spacingMult, float spacingAdd,
                              boolean includepad, int maxLines) {

        if (height < 0)
            throw new IllegalArgumentException("Layout: " + height + " < 0");

        mText = text;
        mTextPaint = paint;
        mHeight = height;
        mAlignment = align;
        mSpacingMult = spacingMult;
        mSpacingAdd = spacingAdd;
        mLineWidth = mTextPaint.getFontMetricsInt().bottom - mTextPaint.getFontMetricsInt().top;
        //mSpannedText = text instanceof Spanned;

        updateBreaks();
        if (height > 0) {
            updateLines();
        }
    }


    public MongolLayout(CharSequence text, TextPaint paint) {
        this(text, 0, text.length(), paint, 0, Gravity.TOP, 1, 0, false, Integer.MAX_VALUE);
    }


    /**
     * Return how wide a layout must be in order to display the
     * specified text slice with one line per paragraph.
     */
    public static float getDesiredHeight(CharSequence source,
                                         int start, int end,
                                         TextPaint paint) {
        float need = 0;

        int next;
        for (int i = start; i <= end; i = next) {
            next = TextUtils.indexOf(source, '\n', i, end);

            if (next < 0)
                next = end;

            float height = MongolTextLine.measure(paint, source, i, next);

            if (height > need)
                need = height;

            next++;
        }

        return need;
    }


    /**
     * Draw this Layout on the specified Canvas.
     */
    public void draw(Canvas c) {
        draw(c, null, null, 0);
    }

    /**
     * Draw this Layout on the specified canvas, with the highlight path drawn
     * between the background and the text.
     *
     * @param canvas               the canvas
     * @param highlight            the path of the highlight or cursor; can be null
     * @param highlightPaint       the paint for the highlight
     * @param cursorOffsetVertical the amount to temporarily translate the
     *                             canvas while rendering the highlight
     */
    public void draw(Canvas canvas, Path highlight, Paint highlightPaint,
                     int cursorOffsetVertical) {

        if (mHeight <= 0) return;

        // TODO for now draw all the lines. Should we only draw the visible lines?
        // (see Layout source code)
        //final long lineRange = getLineRangeForDraw(canvas);
        int firstLine = 0;
        int lastLine = mLinesInfo.size() - 1;
        if (lastLine < 0) return;

        drawBackground(canvas, highlight, highlightPaint, cursorOffsetVertical,
                firstLine, lastLine);
        //drawText(canvas, firstLine, lastLine);
        drawText(canvas);
    }

    public void drawText(Canvas canvas) {

        if (mHeight <= 0) return;

        int ltop = mTextPaint.getFontMetricsInt().top;
        int lbottom = mTextPaint.getFontMetricsInt().bottom;
        int lineToLineDistance = lbottom - ltop; // XXX this ignores leading

        int x = lbottom; // start position of each vertical line
        int y = 0; // baseline
        MongolTextLine tl = MongolTextLine.obtain();

        // draw the lines one at a time
        int lastLine = mLinesInfo.size() - 1;
        for (int i = 0; i <= lastLine; i++) {
            boolean isSpannedText = false; // TODO
            boolean hasSpecialChar = true; // TODO mLinesInfo.get(i).hasSpecialChars();
            int start = mLinesInfo.get(i).getStartOffset();
            int end;
            if (i<lastLine) {
                end = mLinesInfo.get(i + 1).getStartOffset();
            } else {
                end = mText.length();
            }

            if (!isSpannedText && !hasSpecialChar) {
                canvas.drawText(mText, start, end, x, y, mTextPaint);
            } else {
                tl.set(mTextPaint, mText, start, end);
                tl.draw(canvas, x, ltop, y, lbottom);
            }
            x += lineToLineDistance;
        }

        MongolTextLine.recycle(tl);
    }


    public void drawBackground(Canvas canvas, Path highlight, Paint highlightPaint,
                               int cursorOffsetVertical, int firstLine, int lastLine) {
        // TODO
    }

    /**
     * This method is meant to find the lengths of all the places a line break could occur.
     * The purpose is to cache the measure info so that the layout can be resized quickly
     * when there is a change in size. This way an entire new layout does not need to be
     * created.
     */
    private void updateBreaks() {

        if (mBreaks == null || mBreaks.size() > 0)
            mBreaks = new ArrayList<>();

        // XXX can we just use a char sequence? BreakIterator is the only thing that needs it.
        String tempString = mText.toString();

        BreakIterator boundary = BreakIterator.getLineInstance();
        boundary.setText(tempString);
        int start = boundary.first();
        for (int end = boundary.next(); end != BreakIterator.DONE; end = boundary.next()) {
            final float measuredLength = MongolTextLine.measure(mTextPaint, tempString, start, end);
            mBreaks.add(new BreakInfo(start, measuredLength));
            start = end;
        }
    }

    /**
     * Reflow the line-wrap locations based on the height and line breaks.
     */
    private void updateLines() {

        if (mHeight <= 0)
            return;

        if (mLinesInfo == null || mLinesInfo.size() > 0)
            mLinesInfo = new ArrayList<>();

        if (mBreaks == null)
            updateBreaks();

        // XXX can we just use a char sequence? BreakIterator is the only thing that needs it.
        String tempString = mText.toString();

        int start = mBreaks.get(0).getStartOffset();
        mLinesInfo.add(new LineInfo(start, 0)); // TODO add top, descent, special chars
        float  measuredSum = 0;
        int size = mBreaks.size();
        for (int i = 0; i < size; i++) {

            final int end;
            if (i < size - 1) {
                end = mBreaks.get(i+1).getStartOffset();
            } else {
                end = tempString.length();
            }

            float measuredLength = mBreaks.get(i).getMeasuredLength();
            measuredSum += measuredLength;
            if (Math.floor(measuredSum) > mHeight) {
                mLinesInfo.add(new LineInfo(start, 0));
                measuredSum = measuredLength;
                if (measuredSum > mHeight) {

                    // There were no natural line wrap boundaries shorter than the wrap height
                    // so we have to split the word unnaturally across lines.

                    // This would be a better starting point but can't be sure we wouldn't
                    // end up in the middle of a surrogate pair:
                    //int estimatedCharsPerLine = (int) ((end - start) * mHeight / measuredSum);


                    int previousEndChar = start;
                    for (int endChar = start; endChar < end; ) {


                        final int codepoint = tempString.codePointAt(endChar);
                        endChar += Character.charCount(codepoint);
                        // XXX this needs to be optimized. Measures too many times.
                        // TODO use Paint.breakText
                        measuredLength = (int) MongolTextLine.measure(mTextPaint, tempString, start, endChar);
                        if (measuredLength > mHeight) {
                            start = previousEndChar;
                            mLinesInfo.add(new LineInfo(start, 0));
                            // TODO what if mHeight is shorter than a single character
                        }

                        previousEndChar = endChar;
                    }

                    measuredSum = (int) MongolTextLine.measure(mTextPaint, tempString, start, end);
                }
            }

            start = end;
        }
    }

    /**
     * Call this if the height has not changed but something else like the font size has.
     */
    public void reflowLines() {
        updateBreaks();
        updateLines();
    }

    public int getHeight() {
        return mHeight;
    }

    public void setHeight(int height) {
        if (height == mHeight)
            return;
        if (height < 0)
            throw new IllegalArgumentException("height cannot be less than 0");

        mHeight = height;
        //updateBreaks();
        updateLines();
    }

    public int getWidth() {
        return mLinesInfo.size() * mLineWidth;
    }

    public int getLineWidth() {
        return mLineWidth;
    }


    private class LineInfo {
        private int mStartOffset;
        private int mTop;

        LineInfo(int start, int top) {
            mStartOffset = start;
            mTop = top;
        }

        int getStartOffset() {
            return mStartOffset;
        }

        int getTop() {
            return mTop;
        }
    }

    private class BreakInfo {
        private int mStartOffset;
        private float mLength;

        BreakInfo(int start, float measuredLength) {
            mStartOffset = start;
            mLength = measuredLength;
        }

        int getStartOffset() {
            return mStartOffset;
        }

        float getMeasuredLength() {
            return mLength;
        }
    }
}
