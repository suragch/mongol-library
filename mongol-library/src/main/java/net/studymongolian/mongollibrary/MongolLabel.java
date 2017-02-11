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
import android.widget.Toast;


/**
 * This is a lightweight vertical TextView for displaying Mongolian text.
 * It is single line and does not handle rotated emoji or CJK characters.
 * It cannot be styled beyond text color, size, and font. If more styling
 * or flexibility than this is needed, then please use a MongolTextView.
 */
public class MongolLabel extends View {

    private final static int DEFAULT_FONT_SIZE_SP = 17;

    private String mText;
    private int mTextColor;
    private float mTextSizePx;
    private int mGravity = Gravity.TOP;
    private TextPaint mTextPaint;

    public MongolLabel(Context context) {
        super(context);
        init();
    }

    public MongolLabel(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs, R.styleable.MongolLabel, 0, 0);

        try {
            mText = a.getString(R.styleable.MongolLabel_text);
            mTextSizePx = a.getDimensionPixelSize(R.styleable.MongolLabel_textSize, 0);
            mTextColor = a.getColor(R.styleable.MongolLabel_textColor, Color.BLACK);
            mGravity = a.getInteger(R.styleable.MongolLabel_gravity, Gravity.TOP);
        } finally {
            a.recycle();
        }


        init();
    }

    private void init() {
        mTextPaint = new TextPaint();
        mTextPaint.setAntiAlias(true);
        if (mTextSizePx <= 0) {
            mTextSizePx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                    DEFAULT_FONT_SIZE_SP, getResources().getDisplayMetrics());
        }
        mTextPaint.setTextSize(mTextSizePx);
        mTextPaint.setColor(0xFF000000);

        // TODO font

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
            Paint.FontMetrics fm = mTextPaint.getFontMetrics();
            int desiredWidth = (int) (fm.descent - fm.ascent) + getPaddingLeft() + getPaddingRight();
            if (widthMode == MeasureSpec.AT_MOST && desiredWidth > widthRequirement) {
                width = widthRequirement;
            } else {
                width = desiredWidth;
            }
        }

        // determine the height
        int height;
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightRequirement = MeasureSpec.getSize(heightMeasureSpec);
        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightRequirement;
        } else {
            int desiredHeight = (int) mTextPaint.measureText(mText) + getPaddingTop() + getPaddingBottom();
            if (heightMode == MeasureSpec.AT_MOST && desiredHeight > heightRequirement) {
                height = heightRequirement;
            } else {
                height = desiredHeight;
            }
        }

        // Required call: set width and height
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // draw the text on the canvas after adjusting for padding
        canvas.save();
        canvas.rotate(90);

        float gravityOffset = 0;
        if (mGravity != Gravity.TOP) {
            float textWidth = mTextPaint.measureText(mText);
            if (mGravity == Gravity.CENTER) {
                gravityOffset = (getMeasuredHeight() - getPaddingTop() - getPaddingBottom() - textWidth) / 2;
            } else if (mGravity == Gravity.BOTTOM) {
                gravityOffset = getMeasuredHeight() - getPaddingTop() - getPaddingBottom() - textWidth;
            }
            if (gravityOffset < 0) gravityOffset = 0;
        }

        canvas.drawText(mText,
                getPaddingTop() + gravityOffset,
                -getPaddingLeft() - mTextPaint.getFontMetrics().descent,
                mTextPaint);
        canvas.restore();
    }

    public CharSequence getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
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
     * @param gravity Choices are Gravity.TOP (default), Gravity.CENTER, and Gravity.BOTTOM
     */
    public void setGravity(int gravity) {
        if (mGravity != gravity) {
            mGravity = gravity;
            invalidate();
        }
    }


}
