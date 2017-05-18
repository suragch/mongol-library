package net.studymongolian.mongollibrary;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.v4.view.MotionEventCompat;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;


// TODO make class private
public class KeyText extends View {

    private static final String DEBUG_TAG = "TAG";

    private Paint mKeyPaint;
    private Paint mKeyBorderPaint;
    private TextPaint mTextPaint;
    private RectF mSizeRect;
    private Rect mTextBounds;

    private String mText;
    private int mKeyColor;
    private int mPressedColor;
    private int mTextColor;
    private int mBorderColor;
    private int mBorderWidth;
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

    public KeyText(Context context, String text, int textColor, int keyColor, int pressedColor,
                   int borderColor, int borderWidth, int borderRadius) {
        super(context);
        mText = text;
        mTextColor = textColor;
        mKeyColor = keyColor;
        mPressedColor = pressedColor;
        mBorderColor = borderColor;
        mBorderWidth = borderWidth;
        mBorderRadius = borderRadius;

        //mSizeRect = new RectF();
        initPaints();
    }

    private void initDefault() {
        mText = "abc";
        mKeyColor = Color.LTGRAY;
        mPressedColor = Color.GRAY;
        mTextColor = Color.BLACK;
        mBorderColor = Color.BLACK;
        mBorderWidth = 10;
        mBorderRadius = 30;
    }

    private void initPaints() {
        mKeyPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mKeyPaint.setStyle(Paint.Style.FILL);
        mKeyPaint.setColor(mKeyColor);

        mKeyBorderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mKeyBorderPaint.setStyle(Paint.Style.STROKE);
        mKeyBorderPaint.setStrokeWidth(mBorderWidth);
        mKeyBorderPaint.setColor(mBorderColor);

        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextSize(90);
        mTextPaint.setColor(mTextColor);

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

        mTextPaint.getTextBounds(mText, 0, mText.length(), mTextBounds);
        int textHeight = mTextBounds.height();
        int textWidth = mTextBounds.width();

        canvas.drawRoundRect(mSizeRect, mBorderRadius, mBorderRadius, mKeyPaint);
        canvas.drawRoundRect(mSizeRect, mBorderRadius, mBorderRadius, mKeyBorderPaint);
        canvas.rotate(90);
        float verticalAdjust = (getMeasuredHeight() - getPaddingTop() - getPaddingBottom() - textWidth) / 2;
        float horizontalAdjust = (getMeasuredWidth() - getPaddingLeft() - getPaddingRight() - textHeight) / 2;
        canvas.drawText(mText, getPaddingTop() + verticalAdjust,
                -getPaddingLeft() - horizontalAdjust, mTextPaint);

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

}
