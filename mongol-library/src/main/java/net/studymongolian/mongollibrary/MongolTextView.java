package net.studymongolian.mongollibrary;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;


public class MongolTextView extends View {

    private final static int DEFAULT_FONT_SIZE_SP = 17;

    private String mText;
    private int mTextColor;
    private float mTextSizePx;
    private int mGravity = Gravity.TOP;
    private TextPaint mTextPaint;
    private Paint mPaint;
    private MongolLayout mLayout;
    private boolean mNeedsRelayout = false;

    // programmatic constructor
    public MongolTextView(Context context) {
        super(context);

        mText = "";
        mTextSizePx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                DEFAULT_FONT_SIZE_SP, getResources().getDisplayMetrics());
        mTextColor = Color.BLACK;
        mGravity = Gravity.TOP;

        init();
    }

    // xml constructor
    public MongolTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs, R.styleable.MongolTextView, 0, 0);

        try {
            mText = a.getString(R.styleable.MongolTextView_text);
            if (mText == null) {
                mText = ""; // FIXME still crashes if text is not set in xml
            }
            mTextSizePx = a.getDimensionPixelSize(R.styleable.MongolTextView_textSize, 0);
            mTextColor = a.getColor(R.styleable.MongolTextView_textColor, Color.BLACK);
            mGravity = a.getInteger(R.styleable.MongolTextView_gravity, Gravity.TOP);
        } finally {
            a.recycle();
        }

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


        mPaint = new Paint();
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);

        // initialize the layout, but the height still needs to be set
        //mLayout = new MongolLayout(mText, mTextPaint);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {


        int desiredHeight = (int) MongolLayout.getDesiredHeight(mText, 0, mText.length(), mTextPaint);

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
        MongolLayout layout = new MongolLayout(mText, 0, mText.length(), mTextPaint, height, Gravity.TOP, 1, 0, false, Integer.MAX_VALUE);
        int desiredWidth = layout.getWidth();

        //Measure Width
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            width = Math.min(desiredWidth, widthSize);
        } else {
            width = desiredWidth;
        }

        setMeasuredDimension(width, height);
    }

//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        // Tell the parent layout how big this view would like to be
//        // but still respect any requirements (measure specs) that are passed down.
//
//        // determine the height
//        int height;
//        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
//        int heightRequirement = MeasureSpec.getSize(heightMeasureSpec);
//        if (heightMode == MeasureSpec.EXACTLY) {
//            height = heightRequirement;
//        } else {
//            int desiredHeight = (int) MongolLayout.getDesiredHeight(mText, 0, mText.length(), mTextPaint)
//                    + getPaddingTop() + getPaddingBottom();
//            if (heightMode == MeasureSpec.AT_MOST && desiredHeight > heightRequirement) {
//                height = heightRequirement;
//            } else {
//                height = desiredHeight;
//            }
//        }
//
//        // determine the width
//        int width;
//        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
//        int widthRequirement = MeasureSpec.getSize(widthMeasureSpec);
//        if (widthMode == MeasureSpec.EXACTLY) {
//            width = widthRequirement;
//        } else {
////            if (mStaticLayout == null || mStaticLayout.getHeight() != height || mStaticLayoutNeedsRedraw) {
////                int wrapHeight = height - getPaddingTop() - getPaddingBottom();
////                mStaticLayout = new MongolStaticLayout(mText, mTextPaint, wrapHeight, Gravity.TOP, 1, 0);
////                mStaticLayoutNeedsRedraw = false;
////            }
//            mLayout.setHeight(height);
//            int desiredWidth = mLayout.getWidth() + getPaddingLeft() + getPaddingRight();
//            if (widthMode == MeasureSpec.AT_MOST && desiredWidth > widthRequirement) {
//                width = widthRequirement;
//            } else {
//                width = desiredWidth;
//            }
//        }
//
//        // Required call: set width and height
//        setMeasuredDimension(width, height);
//    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        //if (h != oldh) {
//            mLayout.setHeight(h);
//            mNeedsRelayout = false;
        //}

        mLayout = new MongolLayout(mText, 0, mText.length(), mTextPaint, h, Gravity.TOP, 1, 0, false, Integer.MAX_VALUE);

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        // TODO Make it a MongolLayout where parameters can be adjusted rather than creating a new
        // layout every time.

//        int h = bottom - top;
//        mLayout.setHeight(h);
        if (mNeedsRelayout) {
            mLayout.reflowLines();
            mNeedsRelayout = false;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mLayout.draw(canvas);
    }


    public CharSequence getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
        mNeedsRelayout = true;
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
    public void setTextSize(int size) {
        mTextSizePx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                size, getResources().getDisplayMetrics());
        mTextPaint.setTextSize(mTextSizePx);
        //mStaticLayoutNeedsRedraw = true;
        mNeedsRelayout = true;
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
            invalidate();
        }
    }
}