package net.studymongolian.mongollibrary;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.View;

public class KeyImage extends View {

    private static final String DEBUG_TAG = "TAG";

    private boolean mStatePressed = false;
    private MongolCode renderer = MongolCode.INSTANCE;
    private Paint mKeyPaint;
    private Paint mKeyBorderPaint;
    private TextPaint mImagePaint;
    private RectF mSizeRect;
    private Rect mImageBounds;

    //private String mDisplayText;
    private int mKeyColor;
    private int mPressedColor;
    //    private int mTextColor;
//    private int mBorderColor;
//    private int mBorderWidth;
    private int mBorderRadius;

    private KeyImage.OnKeyClickListener mListener;
    private GestureDetector mDetector;

    public interface OnKeyClickListener {
        public void onKeyClicked(View view, String inputText);
    }

    KeyImage(Context context) {
        this(context, null, 0);
    }

    KeyImage(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    KeyImage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // currently ignoring attrs and defStyleAttr
        // if this class is made public then should handle them.
        initDefault();
        initPaints();
    }

    private void initDefault() {
        //mDisplayText = "abc";
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

        mImagePaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        //mImagePaint.setTextSize(90);
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

        // calculate position for centered image
        int keyHeight = getMeasuredHeight() - getPaddingTop() - getPaddingBottom();
        int keyWidth = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();


//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_);
//        int left = getPaddingLeft();
//        int top = getPaddingTop();
//        int right = getPaddingRight();
//        int bottom = getPaddingBottom();
//
//        Rect dst = new Rect(left, top, right, bottom);

        // draw image
//        canvas.drawBitmap(bitmap, null, dst, mImagePaint);

    }



    public void setText(String text) {
        this.mDisplayText = renderer.unicodeToMenksoft(text);
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

    public void setBorderColor(int borderColor) {
        //this.mBorderColor = borderColor;
        mKeyBorderPaint.setColor(borderColor);
        invalidate();
    }

    public void setBorderWidth(int borderWidth) {
        //this.mBorderWidth = borderWidth;
        mKeyBorderPaint.setStrokeWidth(borderWidth);
        invalidate();
    }

    public void setBorderRadius(int borderRadius) {
        this.mBorderRadius = borderRadius;
        invalidate();
    }

    public void setPressedState(boolean pressedState) {
        mStatePressed = pressedState;
        if (mStatePressed) {
            mKeyPaint.setColor(mPressedColor);
        } else {
            mKeyPaint.setColor(mKeyColor);
        }
        invalidate();
    }
}
