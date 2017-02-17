package net.studymongolian.mongollibrary;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;

import java.text.BreakIterator;


public class MongolTextView extends View {

    String mText = "g This is some 文字文字a文字文字. a\uD83D\uDE42\uD83D\uDE42\uD83D\uDE42a\uD83D\uDE42a";
    //String mText = "asdf asdff asdfasd asdfa a asdfas asdf a asdfasasdfasd a";
    //String mText = "This is a senctence that needs some text-wrapping.";
    TextPaint mTextPaint;
    Paint mPaint;
    MongolStaticLayout mStaticLayout;

    // use this constructor if creating MyView programmatically
    public MongolTextView(Context context) {
        super(context);
        initLabelView();
    }

    // this constructor is used when created from xml
    public MongolTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initLabelView();
    }

    private void initLabelView() {
        mTextPaint = new TextPaint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextSize(26 * getResources().getDisplayMetrics().density);
        mTextPaint.setColor(0xFF000000);

        mPaint = new Paint();
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);
        // default to a single line of text
        //int width = (int) mTextPaint.measureText(mText);



    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // Tell the parent layout how big this view would like to be
        // but still respect any requirements (measure specs) that are passed down.
        // determine the width
        int width;
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthRequirement = MeasureSpec.getSize(widthMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthRequirement;
        } else {
            width = (int) mTextPaint.measureText(mText) + getPaddingLeft() + getPaddingRight();
            if (widthMode == MeasureSpec.AT_MOST) {
                if (width > widthRequirement) {
                    width = widthRequirement;
                }
            }
        }

        // FIXME determine the height
        int height;
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightRequirement = MeasureSpec.getSize(heightMeasureSpec);
        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightRequirement;
        } else {
            height = 50 + getPaddingTop() + getPaddingBottom();
            if (heightMode == MeasureSpec.AT_MOST) {
                height = Math.min(height, heightRequirement);
            }
        }

        // Required call: set width and height
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        int wrapHeight = h - getPaddingTop() - getPaddingBottom();
        mStaticLayout = new MongolStaticLayout(mText, mTextPaint, wrapHeight, Gravity.TOP, 1, 0);
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