package net.studymongolian.mongollibrary;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Handler;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.PopupWindow;

import static android.content.ContentValues.TAG;

public class KeyText extends Key {

    private static final int SUBTEXT_INDENT = 5; // px

    private String mPrimaryText;
    private String mPrimaryTextDisplay;
    //private StringForKey mSubText;
    private String mSubTextDisplay;

    private MongolCode renderer = MongolCode.INSTANCE;
    private TextPaint mTextPaint;
    private Rect mTextBounds;
    private Rect mSubTextBounds;
    //private String mDisplayText;
    //private String mDisplaySubText;
    private TextPaint mSubTextPaint;



    private final int LONG_PRESS_TIMEOUT = ViewConfiguration.getLongPressTimeout();
    private Handler mHandler = new Handler();
    private boolean mIsLongPress = false;
    //private float[] lastTouchDownXY = new float[2];
    private int lastTouchDownX;


    protected boolean mIsRotatedPrimaryText;
    protected boolean mIsRotatedSubText;

    public KeyText(Context context) {
        super(context);
        init(context);
    }

    public KeyText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public KeyText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextSize(90);
        mTextBounds = new Rect();

        mSubTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mSubTextPaint.setTextSize(90);
        mSubTextBounds = new Rect();

        mSubTextDisplay = "";
        mIsRotatedPrimaryText = true;
        mIsRotatedSubText = true;

        //gestureDetector = new GestureDetector(context, new GestureListener());
        //gestureDetector.setIsLongpressEnabled(false);
    }

//    @Override
//    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
//        super.onSizeChanged(w, h, oldw, oldh);
//        mKeyHeight = getMeasuredHeight() - getPaddingTop() - getPaddingBottom();
//        mKeyWidth = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
//    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mIsRotatedSubText) {
            drawRotatedSubText(canvas);
        } else {
            drawNonRotatedSubText(canvas);
        }

        if (mIsRotatedPrimaryText) {
            drawRotatedPrimaryText(canvas);
        } else {
            drawNonRotatedPrimaryText(canvas);
        }
    }

    private void drawRotatedSubText(Canvas canvas) {
        String text = mSubTextDisplay;
        if (TextUtils.isEmpty(text)) return;

        // get text size
        mSubTextPaint.getTextBounds(text, 0, text.length(), mSubTextBounds);

        // automatically resize text that is too large
        final float widthProportion = MAX_CONTENT_PROPORTION * mKeyWidth / mSubTextBounds.height();
        final float heightProportion = MAX_CONTENT_PROPORTION * mKeyHeight / mSubTextBounds.width();
        float proportion = Math.min(heightProportion, widthProportion);
        if (proportion < 1) {
            mSubTextPaint.setTextSize(mSubTextPaint.getTextSize() * proportion);
            mSubTextPaint.getTextBounds(text, 0, text.length(), mSubTextBounds);
        }

        // make sure a large border radius doesn't overlap the subtext
        float indent = SUBTEXT_INDENT;
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
        canvas.drawText(text, 0, 0, mSubTextPaint);
        canvas.restore();
    }

    private void drawRotatedPrimaryText(Canvas canvas) {
        String text = mPrimaryTextDisplay;
        if (TextUtils.isEmpty(text)) return;

        // metrics
        mTextPaint.getTextBounds(text, 0, text.length(), mTextBounds);

        // automatically resize text that is too large
        final float widthProportion = MAX_CONTENT_PROPORTION * mKeyWidth / mTextBounds.height();
        final float heightProportion = MAX_CONTENT_PROPORTION * mKeyHeight / mTextBounds.width();
        float proportion = Math.min(heightProportion, widthProportion);
        if (proportion < 1) {
            mTextPaint.setTextSize(mTextPaint.getTextSize() * proportion);
            mTextPaint.getTextBounds(text, 0, text.length(), mTextBounds);
        }

        // location
        int dx = getPaddingTop() + (mKeyHeight - mTextBounds.right) / 2;
        int dy = -getPaddingLeft() - mTextBounds.bottom - (mKeyWidth - mTextBounds.height()) / 2;

        // draw
        canvas.save();
        canvas.rotate(90);
        canvas.translate(dx, dy);
        canvas.drawText(text, 0, 0, mTextPaint);
        canvas.restore();
    }

    private void drawNonRotatedSubText(Canvas canvas) {
        String text = mSubTextDisplay;
        if (TextUtils.isEmpty(text)) return;

        // get text size
        mSubTextPaint.getTextBounds(text, 0, text.length(), mSubTextBounds);

        // resize text if too big
        final float widthProportion = MAX_CONTENT_PROPORTION * mKeyWidth / mSubTextBounds.width();
        final float heightProportion = MAX_CONTENT_PROPORTION * mKeyHeight / mSubTextBounds.height();
        float proportion = Math.min(heightProportion, widthProportion);
        if (proportion < 1) {
            mSubTextPaint.setTextSize(mSubTextPaint.getTextSize() * proportion);
            mSubTextPaint.getTextBounds(text, 0, text.length(), mSubTextBounds);
        }

        // make sure a large border radius doesn't overlap the subtext
        float indent = SUBTEXT_INDENT;
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
        canvas.drawText(text, 0, 0, mSubTextPaint);
        canvas.restore();
    }

    private void drawNonRotatedPrimaryText(Canvas canvas) {
        String text = mPrimaryTextDisplay;
        if (TextUtils.isEmpty(text)) return;

        // metrics
        mTextPaint.getTextBounds(text, 0, text.length(), mTextBounds);
        Paint.FontMetricsInt fm = mTextPaint.getFontMetricsInt();
        int textHeight = fm.descent - fm.ascent;

        // resize text if too big
        final float widthProportion = MAX_CONTENT_PROPORTION * mKeyWidth / mTextBounds.width();
        final float heightProportion = MAX_CONTENT_PROPORTION * mKeyHeight / textHeight;
        float proportion = Math.min(heightProportion, widthProportion);
        if (proportion < 1) {
            mTextPaint.setTextSize(mTextPaint.getTextSize() * proportion);
            mTextPaint.getTextBounds(text, 0, text.length(), mTextBounds);
            fm = mTextPaint.getFontMetricsInt();
            textHeight = fm.descent - fm.ascent;
        }

        // location
        int dx = getPaddingLeft() + (mKeyWidth - mTextBounds.width()) / 2;
        int dy = getPaddingTop() + (mKeyHeight + textHeight) / 2 - fm.descent;

        // draw text
        canvas.save();
        canvas.translate(dx, dy);
        canvas.drawText(text, 0, 0, mTextPaint);
        canvas.restore();
    }

    @Override
    protected void onActionUp(int xPosition) {
        if (getIsShowingPopup())
            finishPopup(xPosition);
        else if (mPrimaryText != null)
            sendTextToKeyboard(mPrimaryText);
    }


//        private void onActionUp(int xPosition) {
//        Log.i(TAG, "onActionUp: " + mPrimaryText);
//        //int x = (int) event.getRawX();
//        String popupChoice = getPopupChoice(xPosition);
//        if (popupChoice != null)
//            sendTextToKeyboard(popupChoice);
//        else
//            sendTextToKeyboard(mPrimaryText);
//    }

//    PopupWindow popupWindow;
//    PopupKeyCandidatesView popupView;
//    //int popupWidth;

//
//
//
//    private void updatePopup(MotionEvent event) {
//        if (popupView == null) return;
//        int x = (int) event.getRawX();
//        popupView.updateTouchPosition(x);
//    }



//    private void handlePopupChoice(Key key, MotionEvent event) {
//
//        //key.setPressed(false);
//
////        if (handler != null) {
////            handler.removeCallbacksAndMessages(null);
////        }
//        if (popupWindow == null) return;
//
//        int x = (int) event.getRawX();
//        CharSequence selectedItem = popupView.getCurrentItem(x);
//
//        if (TextUtils.isEmpty(selectedItem)) {
//            dismissPopup();
//            return;
//        }
//
//        sendTextToKeyboard(selectedItem.toString());
//
//        if (key == mKeyKeyboard) {
//            CharSequence name = popupView.getCurrentItem(x);
//            mKeyboardListener.onRequestNewKeyboard(name.toString());
//            dismissPopup();
//            return;
//        }
//
//        if (inputConnection == null) {
//            dismissPopup();
//            return;
//        }
//
//        inputConnection.beginBatchEdit();
//
//        if (mComposing.length() > 0) {
//            inputConnection.commitText(mComposing, 1);
//            mComposing.setLength(0);
//        }
//
//        // add composing text for certain medials to avoid confusion with finals
//        if (selectedItem.equals(MEDIAL_A_FVS1)) {
//            inputConnection.setComposingText(MEDIAL_A_FVS1_COMPOSING, 1);
//            mComposing.append(MEDIAL_A_FVS1);
//        } else if (selectedItem.equals(MEDIAL_I_FVS1)) {
//            inputConnection.setComposingText(MEDIAL_I_FVS1_COMPOSING, 1);
//            mComposing.append(MEDIAL_I_FVS1);
//        } else if (selectedItem.equals(MEDIAL_I_FVS2)) {
//            inputConnection.setComposingText(MEDIAL_I_FVS2_COMPOSING, 1);
//            mComposing.append(MEDIAL_I_FVS2);
//            //} else if (selectedItem.equals(MEDIAL_ZWJ_I)) {
//            //    inputConnection.setComposingText(MEDIAL_ZWJ_I_COMPOSING, 1);
//            //    mComposing.append(MEDIAL_ZWJ_I);
//        } else if (selectedItem.equals(MEDIAL_U_FVS1)) {
//            inputConnection.setComposingText(MEDIAL_U_FVS1_COMPOSING, 1);
//            mComposing.append(MEDIAL_U_FVS1);
//        } else if (selectedItem.equals(MEDIAL_UE_FVS2)) {
//            inputConnection.setComposingText(MEDIAL_UE_FVS2_COMPOSING, 1);
//            mComposing.append(MEDIAL_UE_FVS2);
//        } else if (selectedItem.equals(MEDIAL_DOTTED_NA)) {
//            inputConnection.setComposingText(MEDIAL_DOTTED_NA_COMPOSING, 1);
//            mComposing.append(MEDIAL_DOTTED_NA);
//        } else if (selectedItem.equals(MEDIAL_TA_FVS1)) {
//            inputConnection.setComposingText(MEDIAL_TA_FVS1_COMPOSING, 1);
//            mComposing.append(MEDIAL_TA_FVS1);
//        } else if (selectedItem.equals(YA_FVS1)) {
//            inputConnection.setComposingText(YA_FVS1_COMPOSING, 1);
//            mComposing.append(YA_FVS1);
//        } else {
//            inputConnection.commitText(selectedItem, 1);
//        }
//
//        inputConnection.endBatchEdit();
//
//        dismissPopup();
//    }
//
//    private void dismissPopup() {
//        if (popupWindow != null)
//            popupWindow.dismiss();
//        popupView = null;
//        popupWindow = null;
//    }



//    private class GestureListener extends GestureDetector.SimpleOnGestureListener {
//
//        @Override
//        public boolean onDown(MotionEvent e) {
//            return true;
//        }
//
//        @Override
//        public boolean onSingleTapUp(MotionEvent e) {
//            Log.i(TAG, "onSingleTapUp: " + mPrimaryText);
//            return true;
//        }
//
//        @Override
//        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
//            //handlePopupScrolling();
//            return true;
//        }
//
//        @Override
//        public void onLongPress(MotionEvent e) {
//            //showPopupCandidates();
//
//        }
//    }


    public void setText(char text) {
        setText(String.valueOf(text));
    }

    public void setText(String text) {
        setText(text, text);
    }

    public void setText(char inputValue, char displayText) {
        setText(String.valueOf(inputValue), String.valueOf(displayText));
    }

    /**
     * @param inputValue the unicode value that will be  used as an input value when
     *                the key is pressed.
     * @param displayText the value to display if different than the unicode value
     */
    public void setText(String inputValue, String displayText) {
        this.mPrimaryText = inputValue;
        this.mPrimaryTextDisplay = renderer.unicodeToMenksoft(displayText);
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
        mTextPaint.setTypeface(typeface);
        mSubTextPaint.setTypeface(typeface);
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

    public void setSubTextSize(float subTextSize) {
        float sizePx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                subTextSize, getResources().getDisplayMetrics());
        mSubTextPaint.setTextSize(sizePx);
        invalidate();
    }

    public void setSubTextColor(int subTextColor) {
        mSubTextPaint.setColor(subTextColor);
        invalidate();
    }

    public void setRotatedPrimaryText(boolean isRotated) {
        this.mIsRotatedPrimaryText = isRotated;
        invalidate();
    }

    public void setRotatedSubText(boolean isRotated) {
        this.mIsRotatedSubText = isRotated;
        invalidate();
    }


//    /**
//     * these are the choices for a popup key
//     */
//    public static class PopupCandidates {
//
//        private String[] unicode;
//        private String[] display;
//
//        /**
//         * Convenience constructor for PopupCandidates(String[] unicode)
//         *
//         * @param unicode the unicode values for the popup items
//         */
//        public PopupCandidates(char unicode) {
//            this(new String[]{String.valueOf(unicode)});
//        }
//
//        /**
//         * Convenience constructor for PopupCandidates(String[] unicode)
//         *
//         * @param unicode the unicode values for the popup items
//         */
//        public PopupCandidates(String unicode) {
//            this(new String[]{unicode});
//        }
//
//        /**
//         * @param unicode the unicode values for the popup items
//         */
//        public PopupCandidates(String[] unicode) {
//            this(unicode, null);
//        }
//
//        /**
//         * @param unicode the unicode values for the popup items
//         * @param display the value to display if different than the unicode values
//         */
//        public PopupCandidates(String[] unicode, String[] display) {
//            if (display != null) {
//                if (display.length != unicode.length)
//                    throw new IllegalArgumentException(
//                            "The number of display items must " +
//                                    "be the same as the number of unicode items.");
//            }
//            this.unicode = unicode;
//            this.display = display;
//        }
//
//        public boolean isEmpty() {
//            if (unicode == null) return true;
//            if (unicode.length == 0) return true;
//            if (unicode.length == 1 && TextUtils.isEmpty(unicode[0])) return true;
//            return false;
//        }
//
//        public String[] getUnicode() {
//            return unicode;
//        }
//
//        public String[] getDisplay() {
//            return display;
//        }
//    }

//    /**
//     * this is the text in the center of the key
//     */
//    public static class StringForKey {
//
//        private String unicodeInput;
//        private String display;
//
//
//        /**
//         * @param unicodeInput the unicode value that will be  used as an input value when
//         *                the key is pressed.
//         */
//        public StringForKey(String unicodeInput) {
//            this(unicodeInput, null);
//        }
//
//        /**
//         * @param unicodeInput the unicode value that will be  used as an input value when
//         *                the key is pressed.
//         * @param display the value to display if different than the unicode value
//         */
//        public StringForKey(String unicodeInput, String display) {
//            this.unicodeInput = unicodeInput;
//            this.display = display;
//        }
//
//        public String getInputValue() {
//            return unicodeInput;
//        }
//
//        public String getDisplayValue() {
//            return display;
//        }
//    }
}
