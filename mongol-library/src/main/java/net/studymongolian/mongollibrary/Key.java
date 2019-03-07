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


public abstract class Key extends View {

    public static final float MAX_CONTENT_PROPORTION = 0.8f;
    private static final float SUBTEXT_INDENT_DP = 3;
    private static final int LONG_PRESS_TIMEOUT = 300; // ViewConfiguration.getLongPressTimeout() too slow
    private static final float MIN_SWIPE_DISTANCE_DP = 10;

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
    protected String mKeySwipeUpText;
    private String mSubTextDisplay;
    private boolean mIsRotatedSubText;
    private Rect mSubTextBounds;
    private TextPaint mSubTextPaint;
    private float mSubtextIndent;

    private Handler mHandler = new Handler();
    private boolean mIsLongPress = false;
    private int lastTouchDownX;
    private int lastTouchDownY;
    private float mMinSwipeDistance;

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
        mSubtextIndent = SUBTEXT_INDENT_DP * getResources().getDisplayMetrics().density;
        mMinSwipeDistance = MIN_SWIPE_DISTANCE_DP * getResources().getDisplayMetrics().density;
    }

    private void initPaints() {
        mKeyPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mKeyPaint.setStyle(Paint.Style.FILL);

        mKeyBorderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mKeyBorderPaint.setStyle(Paint.Style.STROKE);

        mSubTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
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
        int y = (int) event.getRawY();

        switch(action) {
            case (MotionEvent.ACTION_DOWN) :
                onActionDown(x, y);
                return true;
            case (MotionEvent.ACTION_MOVE) :
                onActionScroll(x);
                return true;
            case (MotionEvent.ACTION_UP) :
                mIsLongPress = false;
                mHandler.removeCallbacks(longPress);
                onActionUp(x, y);
                return true;
            default :
                mIsLongPress = false;
                mHandler.removeCallbacks(longPress);
                return super.onTouchEvent(event);
        }
    }

    protected void onActionDown(int xPosition, int yPosition) {
        lastTouchDownX = xPosition;
        lastTouchDownY = yPosition;
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

    protected void onActionUp(int xPosition, int yPosition) {
        if (getIsShowingPopup()) {
            finishPopup(xPosition);
            return;
        }
        if (mKeyInputText == null) return;

        if (didSwipeUp(yPosition) && mKeySwipeUpText != null) {
            sendTextToKeyboard(mKeySwipeUpText);
        } else {
            sendTextToKeyboard(mKeyInputText);
        }
    }

    private boolean didSwipeUp(int yPosition) {
        int deltaY = lastTouchDownY - yPosition;
        return deltaY > mMinSwipeDistance;
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
        } else if (action == MotionEvent.ACTION_UP
                || action == MotionEvent.ACTION_CANCEL) {
            this.setPressed(false);
        }
    }

    /**
     *
     * @param color of the background for the key
     */
    public void setKeyColor(int color) {
        mKeyPaint.setColor(color);
        this.mKeyColor = color;
        invalidate();
    }

    /**
     *
     * @param color of the background for the key when pressed
     */
    public void setPressedColor(int color) {
        this.mPressedColor = color;
        invalidate();
    }

    /**
     *
     * @param color of the border around the key
     */
    public void setBorderColor(int color) {
        mKeyBorderPaint.setColor(color);
        invalidate();
    }

    /**
     *
     * @param width of the border around the key
     */
    public void setBorderWidth(int width) {
        mKeyBorderPaint.setStrokeWidth(width);
        invalidate();
    }

    /**
     *
     * @param radius of the corners of the border around the key
     */
    public void setBorderRadius(int radius) {
        this.mBorderRadius = radius;
        invalidate();
    }

    /**
     *
     * @param text primary centered text in the key
     */
    public void setText(char text) {
        setText(String.valueOf(text));
    }

    /**
     *
     * @param text primary centered text in the key
     */
    public void setText(String text) {
        this.mKeyInputText = text;
    }

    /**
     * Setting this creates a swipe up shortcut to enter secondary text (for example,
     * as a shortcut for the first popup value or the value shown on the subtext label)
     * If this is not set then the default key text is used.
     *
     * @param text the text to enter if the user swipes up on the key
     */
    public void setSwipeUpText(char text) {
        setSwipeUpText(String.valueOf(text));
    }

    /**
     * Setting this creates a swipe up shortcut to enter secondary text (for example,
     * as a shortcut for the first popup value or the value shown on the subtext label)
     * If this is not set then the default key text is used.
     *
     * @param text the text to enter if the user swipes up on the key
     */
    public void setSwipeUpText(String text) {
        this.mKeySwipeUpText = text;
    }

    /**
     *
     * @param isRotated whether the subtext should be rotated 90 degrees
     */
    public void setIsRotatedSubText(boolean isRotated) {
        this.mIsRotatedSubText = isRotated;
        invalidate();
    }

    /**
     * This text is the secondary text in the key. It is used for display purposes.
     * The actual input values must be set by adding popup candidates.
     * @param text secondary display text
     */
    public void setSubText(String text) {
        this.mSubTextDisplay = renderer.unicodeToMenksoft(text);
        invalidate();
    }

    /**
     * This text is the secondary text in the key. It is used for display purposes.
     * The actual input values must be set by adding popup candidates.
     * @param text secondary display text
     */
    public void setSubText(char text) {
        setSubText(String.valueOf(text));
    }

    /**
     * Keys can override this method to allow their own text to be updated.
     * @param typeface of the font to display the key text
     */
    public void setTypeFace(Typeface typeface) {
        mSubTextPaint.setTypeface(typeface);
        invalidate();
    }

    /**
     *
     * @param size of the subtext
     */
    public void setSubTextSize(float size) {
        mSubTextPaint.setTextSize(size);
        invalidate();
    }

    /**
     *
     * @param color of the subtext
     */
    public void setSubTextColor(int color) {
        mSubTextPaint.setColor(color);
        invalidate();
    }

    /**
     * Keyboards should implement this interface to receive input from keys
     */
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

    /**
     *
     * @param listener the keyboard class that will listen for key events
     */
    public void setKeyListener(KeyListener listener) {
        this.mKeyListener = listener;
    }

    // Convenience methods for key subclasses to communicate with keyboard

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
