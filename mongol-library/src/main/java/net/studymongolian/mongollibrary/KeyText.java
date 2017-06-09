package net.studymongolian.mongollibrary;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.v4.view.MotionEventCompat;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;


// TODO make class private
public class KeyText extends View {

    private static final String DEBUG_TAG = "TAG";

    private MongolCode renderer = MongolCode.INSTANCE;
    private Paint mKeyPaint;
    private Paint mKeyBorderPaint;
    private TextPaint mTextPaint;
    private RectF mSizeRect;
    private Rect mTextBounds;

    private String mText;
    private int mKeyColor;
    private int mPressedColor;
//    private int mTextColor;
//    private int mBorderColor;
//    private int mBorderWidth;
    private int mBorderRadius;

    KeyText(Context context) {
        this(context, null);
    }

    KeyText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    KeyText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // currently ignoring attrs and defStyleAttr
        // if this class is made public then should handle them.
        initDefault();
        initPaints();
    }

//    public KeyText(Context context, String text, int textColor, int keyColor, int pressedColor,
//                   int borderColor, int borderWidth, int borderRadius) {
//        super(context);
//        mText = text;
//        mTextColor = textColor;
//        mKeyColor = keyColor;
//        mPressedColor = pressedColor;
//        mBorderColor = borderColor;
//        mBorderWidth = borderWidth;
//        mBorderRadius = borderRadius;
//
//        //mSizeRect = new RectF();
//        initPaints();
//    }

    private void initDefault() {
        mText = "abc";
        //mKeyColor = Color.LTGRAY;
        mPressedColor = Color.GRAY;
        //mTextColor = Color.BLACK;
        //mBorderColor = Color.BLACK;
        //mBorderWidth = 10;
        //mBorderRadius = 30;
    }

    private void initPaints() {
        mKeyPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mKeyPaint.setStyle(Paint.Style.FILL);
        //mKeyPaint.setColor(mKeyColor);

        mKeyBorderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mKeyBorderPaint.setStyle(Paint.Style.STROKE);
        //mKeyBorderPaint.setStrokeWidth(mBorderWidth);
        //mKeyBorderPaint.setColor(mBorderColor);

        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextSize(90);
        //mTextPaint.setColor(mTextColor);

        mTextBounds = new Rect();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mSizeRect = new RectF(getPaddingLeft(), getPaddingTop(),
                w - getPaddingRight(), h - getPaddingBottom());
    }

    @Override
    protected void onDraw(Canvas canvas) {


        // draw background and border
        canvas.drawRoundRect(mSizeRect, mBorderRadius, mBorderRadius, mKeyPaint);
        if (mKeyBorderPaint.getStrokeWidth() > 0) {
            canvas.drawRoundRect(mSizeRect, mBorderRadius, mBorderRadius, mKeyBorderPaint);
        }

        // calculate position for centered text
        canvas.rotate(90);
        mTextPaint.getTextBounds(mText, 0, mText.length(), mTextBounds);
        int keyHeight = getMeasuredHeight() - getPaddingTop() - getPaddingBottom();
        int keyWidth = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
        float x = getPaddingTop() + (keyHeight - mTextBounds.right)/2;
        float y = -getPaddingLeft() - mTextBounds.bottom - (keyWidth - mTextBounds.height())/2;

        // automatically resize text that is too large
        int threshold = keyHeight * 8 / 10;
        if (mTextBounds.width() > threshold) {
            float proportion = 0.8f * keyHeight / mTextBounds.width();
            mTextPaint.setTextSize(mTextPaint.getTextSize() * proportion);
            x += mTextBounds.width() * (1 - proportion) / 2;
            y -= mTextBounds.height() * (1 - proportion) / 2;
        }

        // draw text
        canvas.drawText(mText, x, y, mTextPaint);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event){

        int action = MotionEventCompat.getActionMasked(event);

        switch(action) {
            case (MotionEvent.ACTION_DOWN) :
                mKeyPaint.setColor(mPressedColor);
                invalidate();
                return true;
            case (MotionEvent.ACTION_CANCEL) :
            case (MotionEvent.ACTION_OUTSIDE) :
            case (MotionEvent.ACTION_UP) :
                Log.d(DEBUG_TAG,"Action was UP");
                mKeyPaint.setColor(mKeyColor);
                invalidate();
                return true;
            default :
                return super.onTouchEvent(event);
        }
    }

    public void setText(String text) {
        this.mText = renderer.unicodeToMenksoft(text);
        invalidate();
    }

    public void setText(char text) {
        setText(String.valueOf(text));
    }

    public void setTypeFace(Typeface typeface) {
        mTextPaint.setTypeface(typeface);
        invalidate();
    }

    public void setTextSize(float sizeSP) {
        float sizePx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                sizeSP, getResources().getDisplayMetrics());
        mTextPaint.setTextSize(sizePx);
        invalidate();
    }

    public void setTextColor(int textColor) {
        //this.mTextColor = textColor;
        mTextPaint.setColor(textColor);
        invalidate();
    }

    public void setKeyColor(int keyColor) {
        mKeyPaint.setColor(keyColor);
        this.mKeyColor = keyColor;
        invalidate();
    }

    public void setPressedColor(int pressedColor) {
        this.mPressedColor = pressedColor;
        invalidate();
    }

    public void setBorderColor (int borderColor) {
        //this.mBorderColor = borderColor;
        mKeyBorderPaint.setColor(borderColor);
        invalidate();
    }

    public void setBorderWidth (int borderWidth) {
        //this.mBorderWidth = borderWidth;
        mKeyBorderPaint.setStrokeWidth(borderWidth);
        invalidate();
    }

    public void setBorderRadius (int borderRadius) {
        this.mBorderRadius = borderRadius;
        invalidate();
    }
}
