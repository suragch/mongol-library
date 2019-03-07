package net.studymongolian.mongollibrary;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.Gravity;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;

// lines use width/height in horizontal orientation
// layout uses width/height in vertical orientation

@SuppressWarnings({"WeakerAccess", "unused"})
public class MongolLayout {

    private CharSequence mText;
    private TextPaintPlus mTextPaint;
    private int mHeight;
    private int mAlignment; // Use Gravity for now
    private float mSpacingMult;
    private float mSpacingAdd;
    private List<LineInfo> mLinesInfo; // = new ArrayList<>();
    private boolean needsLineUpdate;
    private static final double EXTRA_ROUNDING = 0.5;

    private static final char CHAR_SPACE = ' ';

    /*
     * Line spacing multiplier for default line spacing.
     */
    public static final float DEFAULT_LINESPACING_MULTIPLIER = 1.0f;

    /*
     * Line spacing addition for default line spacing.
     */
    public static final float DEFAULT_LINESPACING_ADDITION = 0.0f;


    @SuppressWarnings("unused") // TODO add the unused parameters
    public MongolLayout(CharSequence text, int start, int end,
                        TextPaintPlus paint, int height,
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

        needsLineUpdate = true;
    }


    /**
     * @param source the text to measure
     * @param start index in source to start measuring from
     * @param end index in source to stop measuring
     * @param paint the paint object used to know the text size
     * @return how wide a layout must be in order to display the
     * specified text slice with one line per paragraph.
     */
    public static Rect getDesiredSize(CharSequence source,
                                      int start, int end,
                                      TextPaintPlus paint) {

        MongolTextLine tl = MongolTextLine.obtain();

        float longestWidth = 0;
        float heightSum = 0;

        int next;
        for (int i = start; i <= end; i = next) {
            next = TextUtils.indexOf(source, '\n', i, end);

            if (next < 0)
                next = end;

            tl.set(paint, source, i, next);
            RectF size = tl.measure();
            float width = size.width(); // horizontal line orientation
            heightSum += size.height(); // horizontal line orientation

            if (width > longestWidth)
                longestWidth = width;

            next++;
        }

        MongolTextLine.recycle(tl);

        if (heightSum == 0) {
            heightSum = paint.getFontMetrics().bottom - paint.getFontMetrics().top;
        }

        // returning the size as a vertical line orientation (swapping width and height)
        return new Rect(0, 0, (int) heightSum, (int) longestWidth);
    }

    /**
     * @param canvas the canvas to draw the layout on
     */
    public void draw(Canvas canvas) {
        if (mHeight <= 0) return;

        if (needsLineUpdate) updateLines();

        // TODO for now draw all the lines. Should we only draw the visible lines?
        // (see Layout source code)
        int lastLine = mLinesInfo.size() - 1;
        if (lastLine < 0) return;

        drawText(canvas);
    }

    @SuppressWarnings("SuspiciousNameCombination")
    public void drawText(Canvas canvas) {

        if (mHeight <= 0) return;

        if (needsLineUpdate) updateLines();

        int metricsBottom = mTextPaint.getFontMetricsInt().bottom;

        int x = metricsBottom; // start position of each vertical line
        int y = 0; // baseline
        MongolTextLine tl = MongolTextLine.obtain();

        boolean needMultiply = (mSpacingMult != 1 || mSpacingAdd != 0);

        // draw the lines one at a time
        int lastLine = mLinesInfo.size() - 1;
        for (int i = 0; i <= lastLine; i++) {
            int start = mLinesInfo.get(i).startOffset;
            int end;
            if (i < lastLine) {
                end = mLinesInfo.get(i + 1).startOffset;
            } else {
                end = mText.length();
            }

            float gravityOffset = 0;
            if (mAlignment != Gravity.TOP) {
                float textWidth = mLinesInfo.get(i).measuredWidth;
                int verticalGravity = mAlignment & Gravity.VERTICAL_GRAVITY_MASK;
                if (verticalGravity == Gravity.CENTER_VERTICAL) {
                    gravityOffset = (mHeight - textWidth) / 2;
                } else if (verticalGravity == Gravity.BOTTOM) {
                    gravityOffset = mHeight - textWidth;
                }
                if (gravityOffset < 0) gravityOffset = 0;
            }

            tl.set(mTextPaint, mText, start, end);
            int lineHeight;
            if (i > 0) {
                lineHeight = mLinesInfo.get(i).top - mLinesInfo.get(i - 1).top;
            } else {
                lineHeight = mLinesInfo.get(i).top;
            }
            int extraSpacing = (int) (mLinesInfo.get(i).extraSpacing);
            float metricsTop = metricsBottom - lineHeight + extraSpacing;
            tl.draw(canvas, x, metricsTop, y + gravityOffset, metricsBottom);

            x += lineHeight;
        }

        MongolTextLine.recycle(tl);
    }

    // TODO refactor this method. It's messy and hard to read.
    @SuppressWarnings("SuspiciousNameCombination")
    private void updateLines() {

        needsLineUpdate = false;

        if (mLinesInfo == null || mLinesInfo.size() > 0)
            mLinesInfo = new ArrayList<>();

        if (mText.length() == 0) {
            int defaultHeight = mTextPaint.getFontMetricsInt().bottom - mTextPaint.getFontMetricsInt().top;
            mLinesInfo.add(new LineInfo(0, defaultHeight, 0, 0));
            return;
        }

        // XXX can we just use a char sequence? BreakIterator is the only thing that needs a String.

        BreakIterator boundary = BreakIterator.getLineInstance();
        boundary.setText(mText.toString());
        float extraSpacing;
        int start = boundary.first();
        int lineStart = start;
        float measuredSum = 0;
        RectF measuredSize;
        int top = 0; // cumulative sum of line heights
        float lineHeightMax = 0;
        boolean hadToSplitWord = false;
        MongolTextLine tl = MongolTextLine.obtain();
        for (int end = boundary.next(); end != BreakIterator.DONE; ) {

            boolean forceNewLine = false;
            if (mText.charAt(end - 1) == '\n') {
                forceNewLine = true;
                tl.set(mTextPaint, mText, start, end - 1);
            } else {
                tl.set(mTextPaint, mText, start, end);
            }
            measuredSize = tl.measure();

            if (Math.floor(measuredSize.width()) > mHeight) {

                // add previously measured text as a new line
                if (measuredSum > 0) {
                    extraSpacing = getExtraSpacing(lineHeightMax);
                    top += lineHeightMax + extraSpacing;
                    mLinesInfo.add(new LineInfo(lineStart, top, measuredSum, extraSpacing));
                    lineHeightMax = 0;
                    measuredSum = 0;
                }

                // There were no natural line wrap boundaries shorter than the wrap height
                // so we have to split the word unnaturally across lines.
                lineStart = start;
                float[] measuredWidth = new float[1];
                // FIXME this doesn't handle spanned text, does it? Should add a breakText method to TextLine.
                int charactersMeasured = mTextPaint.breakText(mText, lineStart, end, true, mHeight, measuredWidth);
                if (charactersMeasured > 0) {
                    extraSpacing = getExtraSpacing(measuredSize.height());
                    top += measuredSize.height() + extraSpacing;
                    mLinesInfo.add(new LineInfo(lineStart, top, measuredWidth[0], extraSpacing));
                    lineStart += charactersMeasured;
                } else {
                    // if mHeight is shorter than a single character then just add that char to the line
                    extraSpacing = getExtraSpacing(measuredSize.height());
                    mLinesInfo.add(new LineInfo(lineStart, mHeight, measuredSize.height(), extraSpacing));
                    lineStart++;
                }
                hadToSplitWord = true;

            } else if (Math.floor(measuredSum + measuredSize.width()) > mHeight) {

                extraSpacing = getExtraSpacing(lineHeightMax);
                top += lineHeightMax + extraSpacing;
                mLinesInfo.add(new LineInfo(lineStart, top, measuredSum, extraSpacing));
                lineHeightMax = measuredSize.height();
                lineStart = start;
                measuredSum = measuredSize.width();

            } else {
                measuredSum += measuredSize.width();
                lineHeightMax = Math.max(lineHeightMax, measuredSize.height());
            }

            // handle spaces at the end of split lines
            // TODO still need to handle spaces at the end of normal lines. They shouldn't even be measured
            if (hadToSplitWord) {
                if (lineStart < mText.length() && mText.charAt(lineStart) == CHAR_SPACE) {
                    // don't let a single trailing space make an empty blank next line
                    lineStart++;
                }
                start = lineStart;
                if (start == end) {
                    end = boundary.next();
                }
                hadToSplitWord = false;
                forceNewLine = false;
            } else {
                start = end;
                end = boundary.next();
            }

            // handle new line characters
            if (forceNewLine) {
                if (lineHeightMax == 0) {
                    // using the standard line height
                    // TODO should be using a different height if there is a span
                    lineHeightMax = mTextPaint.getFontMetrics().bottom - mTextPaint.getFontMetrics().top;
                }
                extraSpacing = getExtraSpacing(lineHeightMax);
                top += lineHeightMax + extraSpacing;
                mLinesInfo.add(new LineInfo(lineStart, top, measuredSum, extraSpacing));
                lineHeightMax = 0;
                measuredSum = 0;
                lineStart = start;
            }
        }

        // add any last line info
        if (measuredSum > 0 || (mText.length() > 0 && mText.charAt(mText.length() - 1) == '\n')) {
            if (lineHeightMax == 0) {
                // using the standard line height
                // TODO should be using a different height if there is a span
                lineHeightMax = mTextPaint.getFontMetrics().bottom - mTextPaint.getFontMetrics().top;
            }
            top += lineHeightMax;
            mLinesInfo.add(new LineInfo(lineStart, top, measuredSum, 0));
        }
    }

    private float getExtraSpacing(float lineHeight) {
        if (mSpacingAdd == 0 && mSpacingMult == 1)
            return 0;
        double extra = lineHeight * (mSpacingMult - 1) + mSpacingAdd;
        if (extra >= 0) {
            return (int)(extra + EXTRA_ROUNDING);
        } else {
            return -(int)(-extra + EXTRA_ROUNDING);
        }
    }

    /**
     * Call this if the height has not changed but something else like the font size has.
     */
    void reflowLines() {
        needsLineUpdate = true;
    }

    public void setText(CharSequence text) {
        mText = text;
        needsLineUpdate = true;
    }

    public int getHeight() {
        return mHeight;
    }

    public void setHeight(int height) {
        if (height == mHeight)
            return;
        if (height < 0) {
            mHeight = 0;
        } else {
            mHeight = height;
            needsLineUpdate = true;
        }
    }

    public int getWidth() {
        if (needsLineUpdate) updateLines();
        if (mLinesInfo == null || mLinesInfo.size() == 0) return 0;
        int lastLine = mLinesInfo.size() - 1;
        //noinspection SuspiciousNameCombination
        return mLinesInfo.get(lastLine).top;
    }


    public void setAlignment(int alignment) {
        mAlignment = alignment;
    }


    // TODO make alignment be an enum like in Android Layout?
//    public int getAlignment() {
//        return mAlignment;
//    }

    // negative value
    public final int getLineAscent(int line) {
        return getLineBottom(line) - getLineTop(line) + getLineDescent(line);
    }

    public final int getLineBaseline(int line) {
        return getLineBottom(line) + getLineDescent(line);
    }

    public final int getLineBottom(int line) {
        if (line <= 0) return 0;
        return mLinesInfo.get(line - 1).top;
    }

    public int getLineDescent(int line) {
        // TODO this should probably be based on the actual line
        // see http://stackoverflow.com/a/43691403
        return mTextPaint.getFontMetricsInt().descent;
    }

    public int getLineTop(int line) {
        if (mLinesInfo == null || mLinesInfo.size() == 0) {
            return mTextPaint.getFontMetricsInt().bottom - mTextPaint.getFontMetricsInt().top;
        }
        return mLinesInfo.get(line).top;
    }

    public int getLineCount() {
        return mLinesInfo != null ? mLinesInfo.size() : 0;
        //return mLinesInfo.size();
    }

    public final int getLineStart(int line) {
        if (mLinesInfo == null || mLinesInfo.size() == 0) return 0;
        return mLinesInfo.get(line).startOffset;
    }

    public final int getLineEnd(int line) {
        if (mLinesInfo == null || mLinesInfo.size() == 0) return 0;
        if (line == mLinesInfo.size() - 1) {
            return mText.length();
        } else {
            return mLinesInfo.get(line + 1).startOffset;
        }
    }

    public int getLineForOffset(int offset) {
        int high = getLineCount();
        int low = -1;
        int guess;

        while (high - low > 1) {
            guess = (high + low) / 2;

            if (getLineStart(guess) > offset)
                high = guess;
            else
                low = guess;
        }

        if (low < 0)
            return 0;
        else
            return low;
    }

    // Get the line number corresponding to the specified horizontal position.
    // If you ask for a position before 0, you get 0; if you ask for a position
    // to the right of the last line of the text, you get the last line.
    public int getLineForHorizontal(int horizontal) {
        if (horizontal <= 0) return 0;
        if (mLinesInfo == null || mLinesInfo.size() == 0) return 0;
        final int lineCount = mLinesInfo.size();
        int high = lineCount;
        int low = -1;
        int guess;
        while (high - low > 1) {
            guess = (high + low) >> 1;
            if (mLinesInfo.get(guess).top < horizontal) {
                low = guess;
            } else {
                high = guess;
            }
        }
        if (high >= lineCount) {
            return lineCount - 1;
        } else {
            return high;
        }
    }

    public int getOffsetForVertical(int line, float vertical) {

        final int lineStartOffset = getLineStart(line);
        final int lineEndOffset = getLineEnd(line);

        MongolTextLine tl = MongolTextLine.obtain();
        tl.set(mTextPaint, mText, lineStartOffset, lineEndOffset);
        int offset = tl.getOffsetForAdvance(vertical);
        MongolTextLine.recycle(tl);

        return lineStartOffset + offset;
    }

//    int getOffsetToLeftOf (int offset) {
//
//    }
//
//    int getOffsetToRightOf (int offset) {
//
//    }

    float getVertical(int offset) {
        if (offset < 0) return 0;

        int line = getLineForOffset(offset);
        int start = getLineStart(line);

        MongolTextLine tl = MongolTextLine.obtain();
        tl.set(mTextPaint, mText, start, offset);
        float verticalLineHeight = tl.measure().width();
        MongolTextLine.recycle(tl);

        return verticalLineHeight;
    }

    public final TextPaint getPaint() {
        return mTextPaint;
    }

    /**
     * Return the text that is displayed by this Layout.
     */
    public final CharSequence getText() {
        return mText;
    }

    void setLineSpacing(float add, float mult) {
        mSpacingAdd = add;
        mSpacingMult = mult;
    }


    private class LineInfo {
        int startOffset;

        // top refers to the top of a non-rotated line. Since the line gets rotated
        // it is the x distance from the left side of the layout to the right side
        // of the rotated line. The top of each succeeding line increases as a sum
        // of the previous (rotated) line widths.
        int top;

        float measuredWidth;
        float extraSpacing;

        LineInfo(int start, int top, float measuredWidth, float extraSpacing) {
            this.startOffset = start;
            this.top = top;
            this.measuredWidth = measuredWidth;
            this.extraSpacing = extraSpacing;
        }

    }
}
