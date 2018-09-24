package net.studymongolian.mongollibrary;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.annotation.ColorInt;
import android.text.Selection;
import android.text.SpannableStringBuilder;
import android.text.method.MovementMethod;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;

// TODO how to speed this up
// use array instead of mLinesInfo list
// only re-render changed words
// only redraw changed lines

public class MongolTextView extends View  implements ViewTreeObserver.OnPreDrawListener {

    private final static int DEFAULT_FONT_SIZE_SP = 20;
    private static final int STICKY_WIDTH_UNDEFINED = -1;

    private static final int OLD_WIDTH_SPEC_INDEX = 0;
    private static final int OLD_CHOSEN_HEIGHT_INDEX = 1;
    private static final int OLD_DESIRED_WIDTH_INDEX = 2;
    private static final int NEW_WIDTH_SPEC_INDEX = 3;
    private static final int NEW_CHOSEN_HEIGHT_INDEX = 4;
    private static final int NEW_DESIRED_WIDTH_INDEX = 5;

    private int mTextColor;
    private float mTextSizePx;
    private Typeface mTypeface;
    private int mGravity = Gravity.TOP;
    private float mTextStrokeWidthPx;
    private TextPaintPlus mTextPaint;
    private int mTextStrokeColor;
    protected MongolLayout mLayout;
    protected MongolTextStorage mTextStorage;

    private int mStickyWidth = STICKY_WIDTH_UNDEFINED;
    private int[] mOnMeasureData = new int[6];
    private MovementMethod mMovementMethod;
    private float mShadowRadius;
    private float mShadowDx;
    private float mShadowDy;
    private int mShadowColor;


    public MongolTextView(Context context) {
        super(context);
        init(context, null, 0);
    }

    public MongolTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public MongolTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MongolTextView, defStyleAttr, 0);

        boolean isEditText = getDefaultEditable();
        String text = a.getString(R.styleable.MongolTextView_text);
        if (text == null) text = "";
        if (isEditText) {
            SpannableStringBuilder ssb = new SpannableStringBuilder(text);
            mTextStorage = new MongolTextStorage(ssb);
        } else {
            mTextStorage = new MongolTextStorage(text);
        }
        mTextSizePx = a.getDimensionPixelSize(R.styleable.MongolTextView_textSize, 0);
        mTextColor = a.getColor(R.styleable.MongolTextView_textColor, Color.BLACK);
        mTextStrokeWidthPx = a.getDimensionPixelSize(R.styleable.MongolTextView_textStrokeWidth, 0);
        mTextStrokeColor = a.getColor(R.styleable.MongolTextView_textStrokeColor, 0);
        mGravity = a.getInteger(R.styleable.MongolTextView_gravity, Gravity.TOP);
        a.recycle();

        mTextPaint = new TextPaintPlus();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setColor(mTextColor);
        if (mTextSizePx <= 0) {
            mTextSizePx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                    DEFAULT_FONT_SIZE_SP, getResources().getDisplayMetrics());
        }
        mTextPaint.setTextSize(mTextSizePx);
        mTypeface = MongolFont.get(MongolFont.QAGAN, context);
        mTextPaint.setTypeface(mTypeface);
        mTextPaint.linkColor = Color.BLUE;
        mTextPaint.setStrokeWidth(mTextStrokeWidthPx);
        mTextPaint.setStrokeColor(mTextStrokeColor);


        // initialize the layout, but the height still needs to be set
        final CharSequence glyphText = mTextStorage.getGlyphText();
        mLayout = new MongolLayout(
                glyphText,
                0,
                glyphText.length(),
                mTextPaint,
                0,
                Gravity.TOP,
                1,
                0,
                false,
                Integer.MAX_VALUE);

    }

    // MongolEditText overrides this to return true
    protected boolean getDefaultEditable() {
        return false;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        // TODO don't need to calculate this if using sticky width?
        // TODO pass in a limit where we can stop measuring?
        final CharSequence text = mTextStorage.getGlyphText();
        Rect desiredSizeNoPadding = MongolLayout.getDesiredSize(text, 0, text.length(), mTextPaint);
        int desiredHeight = desiredSizeNoPadding.height() + getPaddingTop() + getPaddingBottom();

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height;

        //Measure Height
        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            height = Math.min(desiredHeight, heightSize);
        } else {
            height = desiredHeight;
        }

        // add padding calculations
        int desiredWidth;

        // desired width
        if (mStickyWidth != STICKY_WIDTH_UNDEFINED) {
            // used if the first layout got the wrong size
            desiredWidth = mStickyWidth;
        } else {
            mLayout.setHeight(height - getPaddingTop() - getPaddingBottom());
            desiredWidth = mLayout.getWidth() + getPaddingLeft() + getPaddingRight();
        }

        //Measure Width
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            width = Math.min(desiredWidth, widthSize);
        } else {
            width = desiredWidth;
        }

        // record results
        recordMeasureResults(widthMeasureSpec, height, desiredWidth);

        setMeasuredDimension(width, height);
    }

    private void recordMeasureResults(int widthMeasureSpec, int chosenHeight, int desiredWidth) {
        // this keeps track of the last two onMeasure passes
        mOnMeasureData[OLD_WIDTH_SPEC_INDEX] = mOnMeasureData[NEW_WIDTH_SPEC_INDEX];
        mOnMeasureData[OLD_CHOSEN_HEIGHT_INDEX] = mOnMeasureData[NEW_CHOSEN_HEIGHT_INDEX];
        mOnMeasureData[OLD_DESIRED_WIDTH_INDEX] = mOnMeasureData[NEW_DESIRED_WIDTH_INDEX];
        mOnMeasureData[NEW_WIDTH_SPEC_INDEX] = widthMeasureSpec;
        mOnMeasureData[NEW_CHOSEN_HEIGHT_INDEX] = chosenHeight;
        mOnMeasureData[NEW_DESIRED_WIDTH_INDEX] = desiredWidth;
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mLayout.setHeight(h - getPaddingTop() - getPaddingBottom());
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int w = right - left;
        int h = bottom - top;
        super.onLayout(changed, left, top, right, bottom);

        // This code is to solve the problem described here:
        // http://stackoverflow.com/questions/42390378/custom-views-onmeasure-how-to-get-width-based-on-height
        // Sometimes false widths are gotten in onMeasure so a remeasure has to occur.
        // mStickyWidth holds that value.

        // old width mode was AT_MOST
        // old desiredWidth did not exceed the old width size
        // the new height was less than the old height
        // the old desired width equals the new width
        // the new desired width was greater than the old desired width
        int oldWidthMode = MeasureSpec.getMode(mOnMeasureData[OLD_WIDTH_SPEC_INDEX]);
        int oldWidthSize = MeasureSpec.getSize(mOnMeasureData[OLD_WIDTH_SPEC_INDEX]);

        if (oldWidthMode == MeasureSpec.AT_MOST &&
                mOnMeasureData[OLD_DESIRED_WIDTH_INDEX] <= oldWidthSize &&
                h < mOnMeasureData[OLD_CHOSEN_HEIGHT_INDEX] &&
                w < mOnMeasureData[NEW_DESIRED_WIDTH_INDEX] &&
                mStickyWidth == STICKY_WIDTH_UNDEFINED) {

            mStickyWidth = mOnMeasureData[NEW_DESIRED_WIDTH_INDEX];
            getViewTreeObserver().addOnPreDrawListener(this);
        } else {
            mStickyWidth = STICKY_WIDTH_UNDEFINED;
        }

        clearOnMeasureData();
    }

    private void clearOnMeasureData() {
        for (int i=0; i<mOnMeasureData.length; i++)
            mOnMeasureData[i] = 0;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // draw the text on the canvas after adjusting for padding
        canvas.save();
        canvas.translate(getPaddingLeft(), getPaddingTop());
        mLayout.draw(canvas);
        canvas.restore();
    }

    @SuppressLint("ClickableViewAccessibility") // todo support accessibility
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        // this method is currently only for detecting a ClickableSpan
        if (mMovementMethod != null && event.getAction() == MotionEvent.ACTION_UP) {
            int x = (int) event.getX();
            int y = (int) event.getY();
            int offset = getOffsetForPosition(x, y);

            final ClickableSpan[] links = mTextStorage.getSpans(offset, offset, ClickableSpan.class);

            if (links.length > 0) {
                links[0].onClick(this);
                return true;
            }
        }

        return super.onTouchEvent(event);
    }

    public CharSequence getText() {
        return mTextStorage.getUnicodeText();
    }

    public void setText(CharSequence text) {
        mTextStorage.setText(text);
        mLayout.setText(mTextStorage.getGlyphText());
        setSelection(mTextStorage.length());
        invalidate();
        requestLayout();
    }

    public int getTextColor() {
        return mTextColor;
    }

    public void setTextColor(int color) {
        mTextColor = color;
        mTextPaint.setColor(mTextColor);
        invalidate();
    }

    /**
     * @return text size in pixels
     */
    public float getTextSize() {
        return mTextSizePx;
    }

    /**
     * @param size in SP units
     */
    public void setTextSize(float size) {
        mTextSizePx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                size, getResources().getDisplayMetrics());
        mTextPaint.setTextSize(mTextSizePx);
        mLayout.reflowLines();
        invalidate();
        requestLayout();
    }

    public Typeface getTypeface() {
        return mTypeface;
    }

    public void setTypeface(Typeface typeface) {
        mTypeface = typeface;
        mTextPaint.setTypeface(mTypeface);
        mLayout.reflowLines();
        invalidate();
        requestLayout();
    }

    /**
     * Draw a border outline around the text. May also need to set the stroke color.
     *
     * @param widthSp in SP units
     */
    public void setStrokeWidth(float widthSp) {
        mTextStrokeWidthPx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                widthSp, getResources().getDisplayMetrics());
        mTextPaint.setStrokeWidth(mTextStrokeWidthPx);
        invalidate();
    }

    /**
     *
     * @return the text stroke width in px units
     */
    @SuppressWarnings("unused")
    public float getStrokeWidth() {
        return mTextStrokeWidthPx;
    }

    /**
     * sets the color of the border outline around the characters.
     * Also need to set the stroke width greater than 0.
     *
     * @param color to set the stoke
     */
    public void setStrokeColor(int color) {
        mTextStrokeColor = color;
        mTextPaint.setStrokeColor(mTextStrokeColor);
        invalidate();
    }

    /**
     *
     * @return the color of the stroke border outline around the text characters
     */
    @SuppressWarnings("unused")
    public int getStrokeColor() {
        return mTextStrokeColor;
    }

    /**
     * Sets a shadow on the text.
     * Set the color to Color.TRANSPARENT to disable shadow
     *
     * @param radius the blur radius of the shadow
     * @param dx the x offset
     * @param dy the y offset
     * @param color of the shadow
     */
    public void setShadowLayer(float radius, float dx, float dy, int color) {
        mTextPaint.setShadowLayer(radius, dx, dy, color);

        mShadowRadius = radius;
        mShadowDx = dx;
        mShadowDy = dy;
        mShadowColor = color;

        invalidate();
    }

    /**
     *
     * @return the blur radius of the text shadow
     */
    public float getShadowRadius() {
        return mShadowRadius;
    }

    /**
     *
     * @return the x offset of the text shadow
     */
    public float getShadowDx() {
        return mShadowDx;
    }

    /**
     *
     * @return the y offset of the text shadow
     */
    public float getShadowDy() {
        return mShadowDy;
    }

    /**
     *
     * @return the color of the text shadow
     */
    @ColorInt
    public int getShadowColor() {
        return mShadowColor;
    }


    public void setPadding (int left, int top, int right, int bottom) {
        super.setPadding(left, top, right, bottom);
        if (mLayout == null) return;
        mLayout.reflowLines();
        invalidate();
        requestLayout();
    }


    public int getGravity() {
        return mGravity;
    }

    /**
     *  This sets a custom gravity attribute but uses the same values as Android gravity.
     *  The gravity values are used as integers and not flags. Thus, combining two
     *  flags with | will not work.
     *
     * @param gravity Choices are Gravity.TOP (default), Gravity.CENTER_HORIZONTAL, and Gravity.BOTTOM
     */
    public void setGravity(int gravity) {
        if (mGravity != gravity) {
            mGravity = gravity;
            mLayout.setAlignment(gravity);
            invalidate();
        }
    }

    public int getOffsetForPosition (float x, float y) {
        if (getLayout() == null) return -1;
        final int line = getLineAtCoordinate(x); // vertical line
        return getOffsetAtCoordinate(line, y);
    }

    /**
     * Returns the number of lines of text from the layout (this includes line-wrapping)
     */
    @SuppressWarnings("unused")
    public int getLineCount() {
        return mLayout != null ? mLayout.getLineCount() : 0;
    }

    int getLineAtCoordinate(float x) {
        x -= getTotalPaddingLeft();
        // Clamp the position to inside of the view.
        x = Math.max(0.0f, x);
        x = Math.min(getWidth() - getTotalPaddingRight() - 1, x);
        x += getScrollX();
        return getLayout().getLineForHorizontal((int) x);
    }

    int getOffsetAtCoordinate(int line, float y) {
        // the layout uses glyphs while the TextView uses Unicode
        // so the index conversion happens here
        y = convertToLocalVerticalCoordinate(y);
        return getLayout().getOffsetForVertical(line, y);
    }

    float convertToLocalVerticalCoordinate(float y) {
        y -= getTotalPaddingTop();
        // Clamp the position to inside of the view.
        y = Math.max(0.0f, y);
        y = Math.min(getHeight() - getTotalPaddingBottom() - 1, y);
        y += getScrollY();
        return y;
    }

    @Override
    public boolean onPreDraw() {
        // this method helps solve the problem of incorrect width measuring
        // http://stackoverflow.com/questions/42390378/custom-views-onmeasure-how-to-get-width-based-on-height
        getViewTreeObserver().removeOnPreDrawListener(this);
        if (mStickyWidth == STICKY_WIDTH_UNDEFINED) {
            return true;
        }
        requestLayout();
        return false;
    }

    public MongolLayout getLayout() {
        return mLayout;
    }

    // currently these only return the normal padding
    // no gravity is taken into account
    private int getTotalPaddingLeft() {
        return getPaddingLeft();
    }

    private int getTotalPaddingTop() {
        return getPaddingTop();
    }

    private int getTotalPaddingRight() {
        return getPaddingRight();
    }

    private int getTotalPaddingBottom() {
        return getPaddingBottom();
    }

    public void setMovementMethod(MovementMethod movementMethod) {
        this.mMovementMethod = movementMethod;
    }

    public void setSelection(int start, int stop) {
        Selection.setSelection(mTextStorage, start, stop);
    }

    public void setSelection(int index) {
        Selection.setSelection(mTextStorage, index);
    }

    public void selectAll() {
        Selection.selectAll(mTextStorage);
    }

    @SuppressWarnings("unused")
    public void extendSelection(int index) {
        Selection.extendSelection(mTextStorage, index);
    }

    public int getSelectionStart() {
        // returns -1 if no selection
        return Selection.getSelectionStart(mTextStorage);
    }

    public int getSelectionEnd() {
        // returns -1 if no selection
        return Selection.getSelectionEnd(mTextStorage);
    }

    public boolean hasSelection() {
        final int selectionStart = getSelectionStart();
        final int selectionEnd = getSelectionEnd();

        return selectionStart >= 0 && selectionStart != selectionEnd;
    }

    public CharSequence getSelectedText() {
        if (!hasSelection()) return "";

        final int start = getSelectionStart();
        final int end = getSelectionEnd();
        return start > end
                ? mTextStorage.subSequence(end, start)
                : mTextStorage.subSequence(start, end);
    }
}