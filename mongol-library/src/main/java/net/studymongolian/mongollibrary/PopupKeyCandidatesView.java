package net.studymongolian.mongollibrary;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

class PopupKeyCandidatesView extends ViewGroup {

    private static final int LABEL_PADDING_DP = 5;
    private static final int BORDER_WIDTH_DP = 1;
    private static final int DEFAULT_KEY_HEIGHT_DP = 60;
    public static final int DEFAULT_TEXT_SIZE = 30;

    private final Context mContext;

    private Paint mBorderPaint;
    private int mHeight = 0;
    private int mHighlightColor = Color.DKGRAY;
    private int mTextColor = Color.BLACK;
    private int mTextSize = DEFAULT_TEXT_SIZE;
    private Typeface mTypeface;
    private List<PopupKeyCandidate> mCandidates;

    public PopupKeyCandidatesView(Context context) {
        super(context);
        this.mContext = context;
        this.mBorderPaint = new Paint();
        initPaint();
    }

    private void initPaint() {
        mBorderPaint.setColor(Color.BLACK);
        int strokeWidthPx = (int) (BORDER_WIDTH_DP * getResources().getDisplayMetrics().density);
        mBorderPaint.setStrokeWidth(strokeWidthPx);
        mBorderPaint.setStyle(Paint.Style.STROKE);
    }

    public void setCandidates(List<PopupKeyCandidate> candidates) {
        this.mCandidates = candidates;
        initDisplay();
    }

    private void initDisplay() {
        int paddingPX = (int) (LABEL_PADDING_DP * getResources().getDisplayMetrics().density);
        for (PopupKeyCandidate candidate : mCandidates) {
            MongolLabel label = new MongolLabel(mContext);
            String text = (candidate.getDisplay() != null) ? candidate.getDisplay() : candidate.getUnicode();
            label.setText(text);
            label.setTextSize(mTextSize);
            label.setTextColor(mTextColor);
            if (mTypeface != null)
                label.setTypeface(mTypeface);
            label.setPadding(paddingPX, paddingPX, paddingPX, paddingPX);
            addView(label);
        }
    }

    public void setHeight(int height) {
        this.mHeight = height;
    }

    public void setHighlightColor(int highlightColor) {
        this.mHighlightColor = highlightColor;
    }

    public void setTextColor(int textColor) {
        this.mTextColor = textColor;
    }

    public void setTextSize(int textSize) {
        this.mTextSize = textSize;
    }

    public void setTypeface(Typeface typeface) {
        this.mTypeface = typeface;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int summedWidth = 0;
        int maxHeight = 0;
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            child.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                    View.MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
            summedWidth += child.getMeasuredWidth();
            maxHeight = Math.max(maxHeight, child.getMeasuredHeight());
        }

        mHeight = (int) (DEFAULT_KEY_HEIGHT_DP * getResources().getDisplayMetrics().density);
        if (mHeight < maxHeight) {
            mHeight = maxHeight;
        }

        int desiredWidth = summedWidth + getPaddingLeft() + getPaddingRight();
        int desiredHeight = mHeight + getPaddingTop() + getPaddingBottom();

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height;

        //Measure Width
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            width = Math.min(desiredWidth, widthSize);
        } else {
            width = desiredWidth;
        }

        //Measure Height
        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            height = Math.min(desiredHeight, heightSize);
        } else {
            height = desiredHeight;
        }

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {

        int leftOffset = this.getPaddingLeft();
        int topOffset = this.getPaddingTop();

        int widthSize = getMeasuredWidth() / getChildCount();
        int widthMode = MeasureSpec.EXACTLY;

        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            child.measure(View.MeasureSpec.makeMeasureSpec(widthSize, widthMode),
                    View.MeasureSpec.makeMeasureSpec(mHeight, MeasureSpec.EXACTLY));

            int rightOffset = leftOffset + child.getMeasuredWidth();
            int bottomOffset = topOffset + child.getMeasuredHeight();
            child.layout(leftOffset, topOffset, rightOffset, bottomOffset);
            leftOffset = rightOffset;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // draw border
        canvas.drawRect(0, 0, getWidth(), getHeight(), mBorderPaint);
    }

    public void updateTouchPosition(int x) {
        int index = getSelectedCandidateIndex(x);
        highlightCandidate(index);
    }

    private void highlightCandidate(int index) {
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            if (i == index) {
                child.setBackgroundColor(mHighlightColor);
            } else {
                child.setBackgroundColor(Color.TRANSPARENT);
            }
        }
    }

    public int getSelectedCandidateIndex(int x) {
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            int location[] = new int[2];
            child.getLocationOnScreen(location);

            int leftSide = location[0];
            int rightSide = leftSide + child.getMeasuredWidth();

            if (leftSide < x && x < rightSide) return i;
        }
        return -1;
    }

    public PopupKeyCandidate getCurrentItem(int touchPositionX) {
        int highlightedIndex = getSelectedCandidateIndex(touchPositionX);
        if (highlightedIndex >= 0 && mCandidates != null) {
            return mCandidates.get(highlightedIndex);
        }
        return null;
    }
}
