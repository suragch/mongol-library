package net.studymongolian.mongollibrary;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
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
    //private int mLineWidth; // distance from line to line

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
        //mLineWidth = mTextPaint.getFontMetricsInt().bottom - mTextPaint.getFontMetricsInt().top;
        //mSpannedText = text instanceof Spanned;

        //updateBreaks();
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

            float height = MongolTextLine.measure(paint, source, i, next).right; // unrotated line

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
        //int lineToLineDistance = lbottom - ltop; // XXX this ignores leading

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

            float gravityOffset = 0;
            if (mAlignment != Gravity.TOP) {
                float textWidth = mLinesInfo.get(i).getMeasuredWidth();
                int verticalGravity = mAlignment & Gravity.VERTICAL_GRAVITY_MASK;
                if (verticalGravity == Gravity.CENTER_VERTICAL) {
                    gravityOffset = (mHeight - textWidth) / 2;
                } else if (verticalGravity == Gravity.BOTTOM) {
                    gravityOffset = mHeight - textWidth;
                }
                if (gravityOffset < 0) gravityOffset = 0;
            }

            if (!isSpannedText && !hasSpecialChar) {
                // TODO add gravityOffset
                canvas.drawText(mText, start, end, x, y, mTextPaint);
            } else {
                tl.set(mTextPaint, mText, start, end);
                tl.draw(canvas, x, ltop, y + gravityOffset, lbottom);

            }
            x += mLinesInfo.get(i).getMeasuredHeight();
        }

        MongolTextLine.recycle(tl);
    }


    public void drawBackground(Canvas canvas, Path highlight, Paint highlightPaint,
                               int cursorOffsetVertical, int firstLine, int lastLine) {
        // TODO
    }

    private void updateLines() {

        if (mHeight <= 0)
            return;

        if (mLinesInfo == null || mLinesInfo.size() > 0)
            mLinesInfo = new ArrayList<>();

        // XXX can we just use a char sequence? BreakIterator is the only thing that needs it.
        String tempString = mText.toString();

        BreakIterator boundary = BreakIterator.getLineInstance();
        boundary.setText(tempString);
        int start = boundary.first();
        int lineStart = start;
        float measuredSum = 0;
        RectF measuredSize;
        float previousLineHeight = 0;
        for (int end = boundary.next(); end != BreakIterator.DONE; end = boundary.next()) {

            measuredSize = MongolTextLine.measure(mTextPaint, tempString, start, end);
            //adjustedHeight = Math.max(adjustedHeight, measuredSize.bottom);

            if (Math.floor(measuredSize.right) > mHeight) {

                // add previously measured text as a new line
                if (measuredSum > 0) {
                    mLinesInfo.add(new LineInfo(lineStart, measuredSum, previousLineHeight));
                }

                // There were no natural line wrap boundaries shorter than the wrap height
                // so we have to split the word unnaturally across lines.
                lineStart = start;
                float[] measuredWidth = new float[1];
                int charactersMeasured = mTextPaint.breakText(tempString, lineStart, end, true, mHeight - measuredSum, measuredWidth);
                if (charactersMeasured > 0) {
                    //float height = mTextPaint.getFontMetrics().bottom - mTextPaint.getFontMetrics().top;
                    mLinesInfo.add(new LineInfo(lineStart, measuredWidth[0], measuredSize.bottom));
                    lineStart += charactersMeasured;
                } else {
                    // if mHeight is shorter than a single character then just add that char to the line
                    mLinesInfo.add(new LineInfo(lineStart, mHeight, measuredSize.bottom));
                    lineStart++;
                }

                measuredSum = 0;
                //adjustedHeight = 0;
            } else  if (Math.floor(measuredSum + measuredSize.right) > mHeight) {

                mLinesInfo.add(new LineInfo(lineStart, measuredSum, previousLineHeight));
                lineStart = start;
                measuredSum = measuredSize.right;
                //adjustedHeight = measuredSize.bottom;
            } else {
                measuredSum += measuredSize.right;
            }

            previousLineHeight = measuredSize.bottom;
            start = end;

        }

        // add any last line info
        if (measuredSum > 0) {
            mLinesInfo.add(new LineInfo(lineStart, measuredSum, previousLineHeight));
        }
    }

    /**
     * Call this if the height has not changed but something else like the font size has.
     */
    public void reflowLines() {
        //updateBreaks();
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
        if (mLinesInfo == null || mLinesInfo.size() == 0) return 0;
        float width = 0;
        for (LineInfo line : mLinesInfo) {
            width += line.mMeasuredHeight;
        }
        return (int) width;
    }

//    public int getLineWidth() {
//        return mTextPaint.getFontMetricsInt().bottom - mTextPaint.getFontMetricsInt().top;
//    }

    public int getAlignment() {
        return mAlignment;
    }

    public void setAlignment(int alignment) {
        mAlignment = alignment;
    }

    private class LineInfo {
        private int mStartOffset;
        private int mTop;
        private float mMeasuredWidth;
        private float mMeasuredHeight;
        private RectF mSize;

        LineInfo(int start, float measuredWidth, float measuredHeight) {
            mStartOffset = start;
            mMeasuredWidth = measuredWidth;
            mMeasuredHeight = measuredHeight;
            //mTop = top;
            //mSize = size;
        }

        int getStartOffset() {
            return mStartOffset;
        }

        //int getTop() {
        //    return mTop;
        //}

        float getMeasuredWidth() {
            return mMeasuredWidth;
        }
        float getMeasuredHeight() {
            return mMeasuredHeight;
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
