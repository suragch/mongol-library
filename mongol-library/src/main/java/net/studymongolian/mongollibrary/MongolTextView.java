package net.studymongolian.mongollibrary;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;

// TODO how to speed this up
// use array instead of mLinesInfo list
// only rerender changed words
// only redraw changed lines

public class MongolTextView extends View  implements ViewTreeObserver.OnPreDrawListener {

    private final static int DEFAULT_FONT_SIZE_SP = 17;
    private static final int STICKY_WIDTH_UNDEFINED = -1;
    private static final String TAG = "MongolTextView";


    private Context mContext;
    private int mTextColor;
    private float mTextSizePx;
    private Typeface mTypeface;
    private int mGravity = Gravity.TOP;
    private TextPaint mTextPaint;
    //private Paint mPaint;
    protected MongolLayout mLayout;
    protected MongolTextStorage mTextStorage;

    private int mStickyWidth = STICKY_WIDTH_UNDEFINED;
    private int[] mOnMeasureData = new int[6];


    public MongolTextView(Context context) {
        this(context, null);
    }

    public MongolTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MongolTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

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
        mGravity = a.getInteger(R.styleable.MongolTextView_gravity, Gravity.TOP);
        a.recycle();

        mContext = context;
        init();

    }


    private void init() {
        mTextPaint = new TextPaint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setColor(mTextColor);
        if (mTextSizePx <= 0) {
            mTextSizePx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                    DEFAULT_FONT_SIZE_SP, getResources().getDisplayMetrics());
        }
        mTextPaint.setTextSize(mTextSizePx);
        mTypeface = MongolFont.get(MongolFont.QAGAN, mContext);
        mTextPaint.setTypeface(mTypeface);
        mTextPaint.linkColor = Color.BLUE;

//        mPaint = new Paint();
//        mPaint.setColor(Color.WHITE);
//        mPaint.setStyle(Paint.Style.FILL);

        // initialize the layout, but the height still needs to be set
        final CharSequence text = mTextStorage.getGlyphText();
        mLayout = new MongolLayout(text, 0, text.length(), mTextPaint, 0, Gravity.TOP, 1, 0, false, Integer.MAX_VALUE);

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

    private static final int OLD_WIDTH_SPEC_INDEX = 0;
    private static final int OLD_CHOSEN_HEIGHT_INDEX = 1;
    private static final int OLD_DESIRED_WIDTH_INDEX = 2;
    private static final int NEW_WIDTH_SPEC_INDEX = 3;
    private static final int NEW_CHOSEN_HEIGHT_INDEX = 4;
    private static final int NEW_DESIRED_WIDTH_INDEX = 5;
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

        mOnMeasureData = new int[6];
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


    public CharSequence getText() {
        return mTextStorage.getUnicodeText();
    }

    public void setText(CharSequence text) {
        mTextStorage.setText(text);
        mLayout.setText(mTextStorage.getGlyphText());
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

    public void setPadding (int left, int top, int right, int bottom) {
        super.setPadding(left, top, right, bottom);
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
        final int offset = getOffsetAtCoordinate(line, y);
        return offset;
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
        int glyphOffset = getLayout().getOffsetForVertical(line, y);
        //int unicodeOffset = mTextStorage.getUnicodeIndexForGlyphIndex(glyphOffset);
        return mTextStorage.getUnicodeIndexForGlyphIndex(glyphOffset);
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
}