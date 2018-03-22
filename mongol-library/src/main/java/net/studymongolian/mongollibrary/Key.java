package net.studymongolian.mongollibrary;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Handler;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;


public abstract class Key extends View {

    public static final float MAX_CONTENT_PROPORTION = 0.8f;
    private static final float SUBTEXT_INDENT = 3; // dp

    protected boolean mStatePressed = false;
    protected Paint mKeyPaint;
    protected int mKeyColor;
    protected Paint mKeyBorderPaint;
    protected int mBorderRadius;
    protected int mPressedColor;
    protected RectF mSizeRect;
    protected int mKeyHeight;
    protected int mKeyWidth;

    protected MongolCode renderer = MongolCode.INSTANCE;
    protected String mKeyInputText;
    private String mSubTextDisplay;
    private boolean mIsRotatedSubText;
    private Rect mSubTextBounds;
    private TextPaint mSubTextPaint;
    private float mSubtextIndent;

    private final int LONG_PRESS_TIMEOUT = ViewConfiguration.getLongPressTimeout();
    private Handler mHandler = new Handler();
    private boolean mIsLongPress = false;
    private int lastTouchDownX;

    private KeyListener mKeyListener = null;

    public Key(Context context) {
        super(context);
        initDefault();
        initPaints();
    }

    public Key(Context context, AttributeSet attrs) {
        super(context, attrs);
        initDefault();
        initPaints();
    }

    Key(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initDefault();
        initPaints();
    }

    private void initDefault() {
        mPressedColor = Color.GRAY;
        mIsRotatedSubText = true;
        mSubTextBounds = new Rect();
        //mSubTextDisplay = "";
        mSubtextIndent = SUBTEXT_INDENT * getResources().getDisplayMetrics().density;
    }

    private void initPaints() {
        mKeyPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mKeyPaint.setStyle(Paint.Style.FILL);

        mKeyBorderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mKeyBorderPaint.setStyle(Paint.Style.STROKE);

        mSubTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mSubTextPaint.setTextSize(90);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mSizeRect = new RectF(getPaddingLeft(), getPaddingTop(),
                w - getPaddingRight(), h - getPaddingBottom());
        mKeyHeight = getMeasuredHeight() - getPaddingTop() - getPaddingBottom();
        mKeyWidth = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBackgroundAndBorder(canvas);
        drawSubText(canvas);
    }

    private void drawBackgroundAndBorder(Canvas canvas) {
        canvas.drawRoundRect(mSizeRect, mBorderRadius, mBorderRadius, mKeyPaint);
        if (mKeyBorderPaint.getStrokeWidth() > 0) {
            canvas.drawRoundRect(mSizeRect, mBorderRadius, mBorderRadius, mKeyBorderPaint);
        }
    }

    private void drawSubText(Canvas canvas) {
        if (TextUtils.isEmpty(mSubTextDisplay)) return;
        if (mIsRotatedSubText) {
            drawRotatedSubText(canvas);
        } else {
            drawNonRotatedSubText(canvas);
        }
    }

    private void drawRotatedSubText(Canvas canvas) {
        // get text size
        mSubTextPaint.getTextBounds(mSubTextDisplay, 0, mSubTextDisplay.length(), mSubTextBounds);

        // automatically resize text that is too large
        final float widthProportion = MAX_CONTENT_PROPORTION * mKeyWidth / mSubTextBounds.height();
        final float heightProportion = MAX_CONTENT_PROPORTION * mKeyHeight / mSubTextBounds.width();
        float proportion = Math.min(heightProportion, widthProportion);
        if (proportion < 1) {
            mSubTextPaint.setTextSize(mSubTextPaint.getTextSize() * proportion);
            mSubTextPaint.getTextBounds(mSubTextDisplay, 0, mSubTextDisplay.length(), mSubTextBounds);
        }

        // make sure a large border radius doesn't overlap the subtext
        float indent = mSubtextIndent;
        float radiusAdjustment = (float) (mBorderRadius * ( 1 - (1 / Math.sqrt(2))));
        indent += radiusAdjustment;

        int dx = getPaddingTop() - mSubTextBounds.left        // align top edge
                + mKeyHeight - mSubTextBounds.width()         // align bottom edge
                - (int) indent;                               // move a little up
        int dy = - getPaddingLeft() - mSubTextBounds.bottom   // align left edge
                - mKeyWidth + mSubTextBounds.height()         // align right edge
                + (int) indent;                               // move a little left

        // draw text
        canvas.save();
        canvas.rotate(90);
        canvas.translate(dx, dy);
        canvas.drawText(mSubTextDisplay, 0, 0, mSubTextPaint);
        canvas.restore();
    }

    private void drawNonRotatedSubText(Canvas canvas) {
        // get text size
        mSubTextPaint.getTextBounds(mSubTextDisplay, 0, mSubTextDisplay.length(), mSubTextBounds);

        // resize text if too big
        final float widthProportion = MAX_CONTENT_PROPORTION * mKeyWidth / mSubTextBounds.width();
        final float heightProportion = MAX_CONTENT_PROPORTION * mKeyHeight / mSubTextBounds.height();
        float proportion = Math.min(heightProportion, widthProportion);
        if (proportion < 1) {
            mSubTextPaint.setTextSize(mSubTextPaint.getTextSize() * proportion);
            mSubTextPaint.getTextBounds(mSubTextDisplay, 0, mSubTextDisplay.length(), mSubTextBounds);
        }

        // make sure a large border radius doesn't overlap the subtext
        float indent = mSubtextIndent;
        float radiusAdjustment = (float) (mBorderRadius * ( 1 - (1 / Math.sqrt(2))));
        indent += radiusAdjustment;

        int dx = getPaddingLeft() - mSubTextBounds.left      // align left edge
                + mKeyWidth - mSubTextBounds.width()         // align right edge
                - (int) indent;                              // move a little left
        int dy = getPaddingTop() - mSubTextBounds.top        // align top edge
                + mKeyHeight - mSubTextBounds.height()       // align bottom edge
                - (int) indent;                              // move a little up

        // draw text
        canvas.save();
        canvas.translate(dx, dy);
        canvas.drawText(mSubTextDisplay, 0, 0, mSubTextPaint);
        canvas.restore();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        changeBackgroundColorForClickEvent(event);

        int action = event.getActionMasked();
        int x = (int) event.getRawX();

        switch(action) {
            case (MotionEvent.ACTION_DOWN) :
                onActionDown(x);
                return true;
            case (MotionEvent.ACTION_MOVE) :
                onActionScroll(x);
                return true;
            case (MotionEvent.ACTION_UP) :
                mIsLongPress = false;
                mHandler.removeCallbacks(longPress);
                onActionUp(x);
                return true;
            default :
                mIsLongPress = false;
                mHandler.removeCallbacks(longPress);
                return super.onTouchEvent(event);
        }
    }

    protected void onActionDown(int xPosition) {
        lastTouchDownX = xPosition;
        mIsLongPress = true;
        mHandler.postDelayed(longPress, LONG_PRESS_TIMEOUT);
    }

    private Runnable longPress = new Runnable() {
        @Override
        public void run() {
            if (mIsLongPress) {
                onLongPressThresholdReached();
                mIsLongPress = false;
            }
        }
    };

    private void onLongPressThresholdReached() {
        showPopup(this, lastTouchDownX);
    }

    protected void onActionScroll(int xPosition) {
        if (mIsLongPress) return;
        updatePopup(xPosition);
    }


    protected void onActionUp(int xPosition) {
        if (getIsShowingPopup())
            finishPopup(xPosition);
        else if (mKeyInputText != null)
            sendTextToKeyboard(mKeyInputText);
    }

    @Override
    public void setPressed(boolean pressed) {
        super.setPressed(pressed);
        mStatePressed = pressed;
        if (pressed) {
            mKeyPaint.setColor(mPressedColor);
        } else {
            mKeyPaint.setColor(mKeyColor);
        }
        invalidate();
    }

    protected void changeBackgroundColorForClickEvent(MotionEvent event) {
        int action = event.getActionMasked();
        if (action == MotionEvent.ACTION_DOWN) {
            this.setPressed(true);
        } else if (action == MotionEvent.ACTION_UP) {
            this.setPressed(false);
        }
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
        mKeyBorderPaint.setColor(borderColor);
        invalidate();
    }

    public void setBorderWidth(int borderWidth) {
        mKeyBorderPaint.setStrokeWidth(borderWidth);
        invalidate();
    }

    public void setBorderRadius(int borderRadius) {
        this.mBorderRadius = borderRadius;
        invalidate();
    }

    public void setText(char text) {
        setText(String.valueOf(text));
    }

    public void setText(String text) {
        this.mKeyInputText = text;
    }

    public void setIsRotatedSubText(boolean isRotated) {
        this.mIsRotatedSubText = isRotated;
        invalidate();
    }

    public void setSubText(String text) {
        this.mSubTextDisplay = renderer.unicodeToMenksoft(text);
        invalidate();
    }

    public void setSubText(char text) {
        setSubText(String.valueOf(text));
    }

    public void setTypeFace(Typeface typeface) {
        mSubTextPaint.setTypeface(typeface);
        invalidate();
    }

    public void setSubTextSize(float subTextSize) {
        mSubTextPaint.setTextSize(subTextSize);
        invalidate();
    }

    public void setSubTextColor(int subTextColor) {
        mSubTextPaint.setColor(subTextColor);
        invalidate();
    }

    // Keyboards should implement this interface to receive input from keys
    public interface KeyListener {
        void onKeyInput(String text);
        void onBackspace();

        boolean getIsShowingPopup();
        void showPopup(Key key, int xPosition);
        void updatePopup(int xPosition);
        void finishPopup(int xPosition);

        void onKeyboardKeyClick();
        void onNewKeyboardChosen(int xPositionOnPopup);
        void onShiftChanged(boolean isShiftOn);
    }
    public void setKeyListener(KeyListener listener) {
        this.mKeyListener = listener;
    }
    protected void sendTextToKeyboard(String text) {
        if (mKeyListener == null) return;
        mKeyListener.onKeyInput(text);
    }
    protected boolean getIsShowingPopup() {
        return (mKeyListener != null)
                && mKeyListener.getIsShowingPopup();
    }
    protected void showPopup(Key key, int xPosition) {
        if (mKeyListener == null) return;
        mKeyListener.showPopup(key, xPosition);
    }
    protected void updatePopup(int xPosition) {
        if (mKeyListener == null) return;
        mKeyListener.updatePopup(xPosition);
    }
    protected void finishPopup(int xPosition) {
        if (mKeyListener == null) return;
        mKeyListener.finishPopup(xPosition);
    }
    protected void backspace() {
        if (mKeyListener == null) return;
        mKeyListener.onBackspace();
    }
    protected void clickKeyboardKey() {
        if (mKeyListener == null) return;
        mKeyListener.onKeyboardKeyClick();
    }
    protected void chooseAnotherKeyboard(int xPositionOnPopup) {
        if (mKeyListener == null) return;
        mKeyListener.onNewKeyboardChosen(xPositionOnPopup);
    }
    protected void setShift(boolean isShiftOn) {
        if (mKeyListener == null) return;
        mKeyListener.onShiftChanged(isShiftOn);
    }
}
