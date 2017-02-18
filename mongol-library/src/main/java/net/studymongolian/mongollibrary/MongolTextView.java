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

import java.text.BreakIterator;


public class MongolTextView extends View {

    private final static int DEFAULT_FONT_SIZE_SP = 17;

    private String mText;
    //String mText = "asdf asdff asdfasd asdfa a asdfas asdf a asdfasasdfasd a";
    //String mText = "This is a senctence that needs some text-wrapping.";

    private int mTextColor;
    private float mTextSizePx;
    private int mGravity = Gravity.TOP;
    private TextPaint mTextPaint;
    private Paint mPaint;
    private MongolStaticLayout mStaticLayout;

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
                mText = "";
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
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // Tell the parent layout how big this view would like to be
        // but still respect any requirements (measure specs) that are passed down.

        // determine the height
        int height;
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightRequirement = MeasureSpec.getSize(heightMeasureSpec);
        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightRequirement;
        } else {
            int desiredHeight = (int) MongolStaticLayout.getDesiredHeight(mText, 0, mText.length(), mTextPaint)
                    + getPaddingTop() + getPaddingBottom();
            if (heightMode == MeasureSpec.AT_MOST && desiredHeight > heightRequirement) {
                height = heightRequirement;
            } else {
                height = desiredHeight;
            }
        }

        // determine the width
        int width;
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthRequirement = MeasureSpec.getSize(widthMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthRequirement;
        } else {
            if (mStaticLayout == null || mStaticLayout.getHeight() != height) {
                int wrapHeight = height - getPaddingTop() - getPaddingBottom();
                mStaticLayout = new MongolStaticLayout(mText, mTextPaint, wrapHeight, Gravity.TOP, 1, 0);
            }
            int desiredWidth = mStaticLayout.getWidth() + getPaddingLeft() + getPaddingRight();
            if (widthMode == MeasureSpec.AT_MOST && desiredWidth > widthRequirement) {
                width = widthRequirement;
            } else {
                width = desiredWidth;
            }
        }

        // Required call: set width and height
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        if (mStaticLayout == null || mStaticLayout.getHeight() != h) {
            int wrapHeight = h - getPaddingTop() - getPaddingBottom();
            mStaticLayout = new MongolStaticLayout(mText, mTextPaint, wrapHeight, Gravity.TOP, 1, 0);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mStaticLayout.draw(canvas);


        // FIXME don't instantiate here
//        MongolTextLine textLine = MongolTextLine.obtain();
//        textLine.set(mTextPaint, mText);
//
//        textLine.draw(canvas, 0, 0, 0, 0);
//        MongolTextLine.recycle(textLine);

    }


}