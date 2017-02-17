package net.studymongolian.mongollibrary;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.text.TextPaint;
import android.text.TextUtils;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;


public class MongolStaticLayout {

    private CharSequence mText;
    private TextPaint mTextPaint;
    private int mHeight;
    private int mAlignment; // Use Gravity for now
    private float mSpacingMult;
    private float mSpacingAdd;
    private Paint.FontMetricsInt mFontMetricsInt;
    private List<LineInfo> mLinesInfo;

    private static final char CHAR_NEW_LINE = '\n';
    private static final char CHAR_TAB = '\t';
    private static final char CHAR_SPACE = ' ';
    private static final char CHAR_ZWSP = '\u200B';


    public MongolStaticLayout(CharSequence text, int start, int end,
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
        //mSpannedText = text instanceof Spanned;

        mLinesInfo = new ArrayList<>();
        updateLines();
    }


    /**
     * Constructor to set the display text, width, and other standard properties.
     *
     * @param text        the text to render
     * @param paint       the default paint for the layout.  Styles can override
     *                    various attributes of the paint.
     * @param height      the wrapping height for the text.
     * @param align       whether to top, bottom, or center the text.
     * @param spacingMult factor by which to scale the font size to get the
     *                    default line spacing
     * @param spacingAdd  amount to add to the default line spacing
     */
    public MongolStaticLayout(CharSequence text, TextPaint paint,
                              int height, int align,
                              float spacingMult, float spacingAdd) {

        this(text, 0, text.length(), paint, height, align, spacingMult, spacingAdd,
                false, Integer.MAX_VALUE);
    }


    /**
     * Return how wide a layout must be in order to display the
     * specified text slice with one line per paragraph.
     */
    public static float getDesiredWidth(CharSequence source,
                                        int start, int end,
                                        TextPaint paint) {
        float need = 0;

        int next;
        for (int i = start; i <= end; i = next) {
            next = TextUtils.indexOf(source, '\n', i, end);

            if (next < 0)
                next = end;

            float w = measurePara(paint, source, i, next);

            if (w > need)
                need = w;

            next++;
        }

        return need;
    }

    static float measurePara(TextPaint paint, CharSequence text, int start, int end) {
        // TODO
        return 0;
//        MongolTextLine tl = MongolTextLine.obtain();
//        try {
//
//            tl.set(paint, text, start, end, dir, directions, hasTabs, tabStops);
//            return margin + tl.metrics(null);
//        } finally {
//            MongolTextLine.recycle(tl);
//
//        }
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

        int ltop = mTextPaint.getFontMetricsInt().top;
        int lbottom = mTextPaint.getFontMetricsInt().bottom;
        int lineToLineDistance = lbottom - ltop;

        int x = 0; // start position of each vertical line
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


    public void drawText(Canvas canvas, int firstLine, int lastLine) {
//        int previousLineBottom = mLinesInfo.get(firstLine).getTop();
//        int previousLineEnd = mLinesInfo.get(firstLine).getStartOffset();
//        int spanEnd = 0;
//        TextPaint paint = mTextPaint;
//        CharSequence buf = mText;
//
//        //Alignment paraAlign = mAlignment;
//        boolean tabStopsIsInitialized = false;
//
//        MongolTextLine tl = MongolTextLine.obtain();
//
//        // Draw the lines, one at a time.
//        for (int i = firstLine; i <= lastLine; i++) {
//            int start = previousLineEnd;
//            int end = mLinesInfo.get(i + 1).getStartOffset();
//            previousLineEnd = end;
//
////            int ltop = previousLineBottom;
////            int lbottom = mLinesInfo.get(i + 1).getTop();
////            previousLineBottom = lbottom;
////            int lbaseline = lbottom - mLinesInfo.get(i).getDescent();
//
//            int ltop
//
//            int left = 0;
//            int right = mHeight;
//
//
//            boolean isSpannedText = false; // TODO
//            boolean hasSpecialChar = mLinesInfo.get(i).hasSpecialChars();
//            int x = 0;
//            if (!isSpannedText && !hasSpecialChar) {
//                canvas.drawText(buf, start, end, x, lbaseline, paint);
//            } else {
//                tl.set(paint, buf); // TODO start, end,
//                tl.draw(canvas, x, ltop, lbaseline, lbottom);
//            }
//            i++;
//        }
//
//        MongolTextLine.recycle(tl);
    }


    public void drawBackground(Canvas canvas, Path highlight, Paint highlightPaint,
                               int cursorOffsetVertical, int firstLine, int lastLine) {
        // TODO
    }


    // call this in onSizeChanged initially
    private void updateLines() {

        String tempString = mText.toString();

        BreakIterator boundary = BreakIterator.getLineInstance();
        //BreakIterator charBoundary = BreakIterator.getCharacterInstance();

        boundary.setText(tempString);
        int start = boundary.first();
        mLinesInfo.add(new LineInfo(start, 0, 0, false)); // TODO add top, descent, special chars
        float measuredSum = 0;
        float measuredLength = 0;
        for (int end = boundary.next(); end != BreakIterator.DONE; end = boundary.next()) {

            measuredLength = mTextPaint.measureText(tempString, start, end);
            measuredSum += measuredLength;
            if (measuredSum > mHeight) {
                mLinesInfo.add(new LineInfo(start, 0, 0, false));
                measuredSum = measuredLength;
                if (measuredSum > mHeight) {

                    // There were no natural line wrap boundaries shorter than the wrap height
                    // so we have to split the word unnaturally across lines.

                    // This would be a better starting point but can't be sure we wouldn't
                    // end up in the middle of an emoji:
                    //int estimatedCharsPerLine = (int) ((end - start) * mHeight / measuredSum);

                    int codepoint;
                    int previousEndChar = start;
                    for (int endChar = start; endChar < end; ) {


                        codepoint = tempString.codePointAt(endChar);
                        endChar += Character.charCount(codepoint);
                        // XXX this needs to be optimized. Measures too many times.
                        measuredLength = mTextPaint.measureText(tempString, start, endChar);
                        if (measuredLength > mHeight) {
                            start = previousEndChar;
                            mLinesInfo.add(new LineInfo(start, 0, 0, false));
                            // TODO what if mHeight is shorter than a single character
                        }

                        previousEndChar = endChar;
                    }

                    measuredSum = mTextPaint.measureText(tempString, start, end);
                }
            }

            start = end;
        }
    }


    private class LineInfo {
        private int mStartOffset;
        private boolean mHasSpecialChars;
        private int mTop;
        private int mDescent;

        LineInfo(int start, int top, int descent, boolean hasSpecialChars) {
            mStartOffset = start;
            mTop = top;
            mDescent = descent;
            mHasSpecialChars = hasSpecialChars;
        }

//        LineInfo(int start) {
//
//        }

        int getStartOffset() {
            return mStartOffset;
        }

        int getTop() {
            return mTop;
        }

        int getDescent() {
            return mDescent;
        }

        boolean hasSpecialChars() {
            return mHasSpecialChars;
        }
    }
}
