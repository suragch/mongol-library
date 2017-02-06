package net.studymongolian.mongollibrary;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.text.Layout;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;

import java.util.List;


public class MongolStaticLayout {

    private CharSequence mText;
    private TextPaint mPaint;
    private int mWidth;
    private Alignment mAlignment;
    private float mSpacingMult;
    private float mSpacingAdd;
    private int mLineCount;
    private Paint.FontMetricsInt mFontMetricsInt;
    private List<LineInfo> mLinesInfo;

    private static final char CHAR_NEW_LINE = '\n';
    private static final char CHAR_TAB = '\t';
    private static final char CHAR_SPACE = ' ';
    private static final char CHAR_ZWSP = '\u200B';


    public MongolStaticLayout(CharSequence text, int start, int end,
                              TextPaint paint, int width,
                              Alignment align, float spacingMult, float spacingAdd,
                              boolean includepad, int maxLines) {

        if (width < 0)
            throw new IllegalArgumentException("Layout: " + width + " < 0");

        mText = text;
        mPaint = paint;
        mWidth = width;
        mAlignment = align;
        mSpacingMult = spacingMult;
        mSpacingAdd = spacingAdd;
        //mSpannedText = text instanceof Spanned;
    }




    /**
     * Constructor to set the display text, width, and other standard properties.
     * @param text the text to render
     * @param paint the default paint for the layout.  Styles can override
     * various attributes of the paint.
     * @param width the wrapping width for the text.
     * @param align whether to top, bottom, or center the text.
     * @param spacingMult factor by which to scale the font size to get the
     * default line spacing
     * @param spacingAdd amount to add to the default line spacing
     */
    public MongolStaticLayout(CharSequence text, TextPaint paint,
                     int width, Alignment align,
                     float spacingMult, float spacingAdd) {

        this(text, 0, text.length(), paint, width, align, spacingMult, spacingAdd,
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
     * @param canvas the canvas
     * @param highlight the path of the highlight or cursor; can be null
     * @param highlightPaint the paint for the highlight
     * @param cursorOffsetVertical the amount to temporarily translate the
     *        canvas while rendering the highlight
     */
    public void draw(Canvas canvas, Path highlight, Paint highlightPaint,
                     int cursorOffsetVertical) {

        // TODO for now draw all the lines. Should we only draw the visible lines?
        // (see Layout source code)
        //final long lineRange = getLineRangeForDraw(canvas);
        int firstLine = 0;
        int lastLine = mLineCount - 1;
        if (lastLine < 0) return;

        drawBackground(canvas, highlight, highlightPaint, cursorOffsetVertical,
                firstLine, lastLine);
        drawText(canvas, firstLine, lastLine);
    }


    public void drawText(Canvas canvas, int firstLine, int lastLine) {
        int previousLineBottom = mLinesInfo.get(firstLine).getTop();
        int previousLineEnd = mLinesInfo.get(firstLine).getStartOffset();
        int spanEnd = 0;
        TextPaint paint = mPaint;
        CharSequence buf = mText;

        Alignment paraAlign = mAlignment;
        boolean tabStopsIsInitialized = false;

        MongolTextLine tl = MongolTextLine.obtain();

        // Draw the lines, one at a time.
        for (int i = firstLine; i <= lastLine; i++) {
            int start = previousLineEnd;
            int end = mLinesInfo.get(i + 1).getStartOffset();
            previousLineEnd = end;

            int ltop = previousLineBottom;
            int lbottom = mLinesInfo.get(i + 1).getTop();
            previousLineBottom = lbottom;
            int lbaseline = lbottom - mLinesInfo.get(i).getDescent();

            int left = 0;
            int right = mWidth;


            boolean isSpannedText = false; // TODO
            boolean hasSpecialChar = mLinesInfo.get(i).hasSpecialChars();
            int x = 0;
            if (!isSpannedText && !hasSpecialChar) {
                canvas.drawText(buf, start, end, x, lbaseline, paint);
            } else {
                tl.set(paint, buf); // TODO start, end,
                tl.draw(canvas, x, ltop, lbaseline, lbottom);
            }
        }

        MongolTextLine.recycle(tl);
    }


    public void drawBackground(Canvas canvas, Path highlight, Paint highlightPaint,
                               int cursorOffsetVertical, int firstLine, int lastLine) {
        // TODO
    }



    public enum Alignment {
        ALIGN_TOP,
        ALIGN_BOTTOM,
        ALIGN_CENTER,
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
