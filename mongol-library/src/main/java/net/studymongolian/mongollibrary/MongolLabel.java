package net.studymongolian.mongollibrary;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;


/**
 * This is a lightweight vertical TextView for displaying Mongolian text.
 * It is single line and does not handle rotated emoji, CJK, or tabs.
 * It cannot be styled beyond text color, size, and font. If more styling
 * or flexibility than this is needed, then please use a MongolTextView.
 */
public class MongolLabel extends View {

    private final static int DEFAULT_FONT_SIZE_SP = 17;

    private Context mContext;
    private String mUnicodeText;
    private String mGlyphText;
    private Typeface mTypeface;
    private int mTextColor;
    private float mTextSizePx;
    //private int mGravity = Gravity.CENTER_VERTICAL;
    private TextPaint mTextPaint;
    private MongolCode mRenderer;

    public MongolLabel(Context context) {
        super(context);

        mTextSizePx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                DEFAULT_FONT_SIZE_SP, getResources().getDisplayMetrics());
        mTextColor = Color.BLACK;
        //mGravity = Gravity.TOP;
        mContext = context;
        init();
    }

    public MongolLabel(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs, R.styleable.MongolLabel, 0, 0);

        try {
            String text = a.getString(R.styleable.MongolLabel_text);
            if (text == null) text = "";
            mUnicodeText = text;
            mTextSizePx = a.getDimensionPixelSize(R.styleable.MongolLabel_textSize, 0);
            mTextColor = a.getColor(R.styleable.MongolLabel_textColor, Color.BLACK);
            //mGravity = a.getInteger(R.styleable.MongolLabel_gravity, Gravity.TOP);
        } finally {
            a.recycle();
        }

        mContext = context;
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
        mTextPaint.setColor(mTextColor);
        mTypeface = MongolFont.get(MongolFont.QAGAN, mContext);
        mTextPaint.setTypeface(mTypeface);
        mRenderer = MongolCode.INSTANCE;
        if (mUnicodeText == null) {
            mUnicodeText = "";
        }
        mGlyphText = mRenderer.unicodeToMenksoft(mUnicodeText);
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
            int desiredHeight = (int) mTextPaint.measureText(mGlyphText) + getPaddingTop() + getPaddingBottom();
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


        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        float textHeight = mTextPaint.getFontMetrics().descent - mTextPaint.getFontMetrics().ascent;
        float textWidth = mTextPaint.measureText(mGlyphText);
        float paddingLeft = getPaddingLeft();
        float paddingTop = getPaddingTop();
        float paddingRight = getPaddingRight();
        float paddingBottom = getPaddingBottom();
        float desiredWidth = paddingLeft + getPaddingRight() + textHeight;
        float desiredHeight = paddingTop + getPaddingBottom() + textWidth;

        // automatically resize text that is too large
        if (desiredWidth > getMeasuredWidth() || desiredHeight > getMeasuredHeight()) {
            float proportion;
            float widthProportion = getMeasuredWidth() / desiredWidth;
            float heightProportion = getMeasuredHeight() / desiredHeight;
            if (heightProportion < widthProportion) {
                proportion = heightProportion;
            } else {
                proportion = widthProportion;
            }
            paddingLeft *= proportion;
            paddingTop *= proportion;
            paddingRight *= proportion;
            paddingBottom *= proportion;
            mTextPaint.setTextSize(mTextPaint.getTextSize() * proportion);
            textWidth = mTextPaint.measureText(mGlyphText);
            textHeight = mTextPaint.getFontMetrics().descent - mTextPaint.getFontMetrics().ascent;
        }

        float textAreaHeight = measuredHeight - paddingTop - paddingBottom;
        float textAreaWidth = measuredWidth - paddingLeft - paddingRight;
        float x = paddingTop + (textAreaHeight - textWidth) / 2;
        float y = -mTextPaint.getFontMetrics().descent - paddingLeft - (textAreaWidth - textHeight) / 2;
        canvas.drawText(mGlyphText, x, y, mTextPaint);

        canvas.restore();
    }

    public CharSequence getText() {
        return mUnicodeText;
    }

    public void setText(String text) {
        if (text.equals(mUnicodeText)) return;

        mUnicodeText = text;
        mGlyphText = mRenderer.unicodeToMenksoft(mUnicodeText);
        invalidate();
        requestLayout();
    }

    public int getTextColor() {
        return mTextColor;
    }

    public void setTextColor(int color) {
        if (color == mTextColor) return;

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
        invalidate();
        requestLayout();
    }

    public Typeface getTypeface() {
        return mTypeface;
    }

    public void setTypeface(Typeface typeface) {
        mTypeface = typeface;
        mTextPaint.setTypeface(mTypeface);
        invalidate();
        requestLayout();
    }

}
