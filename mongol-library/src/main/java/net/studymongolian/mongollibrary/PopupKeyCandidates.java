package net.studymongolian.mongollibrary;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

class PopupKeyCandidates extends ViewGroup {

    private static final int LABEL_PADDING = 5; // dp
    private static final int DEFAULT_KEY_HEIGHT = 60; // dp
    public static final int DEFAULT_TEXT_SIZE = 30; // sp

    private final Context mContext;

    private int mHeight = (int) (DEFAULT_KEY_HEIGHT * getResources().getDisplayMetrics().density);
    private int mHighlightColor = Color.DKGRAY;
    private String[] mCandidates;

    // this popup view will only be created programmatically
    public PopupKeyCandidates(Context context) {
        super(context);
        this.mContext = context;
    }

    public void setCandidates(String[] candidates) {
        mCandidates = candidates;
    }

    public void setDisplayCandidates(String[] displayCandidates, int textSize) {
        int paddingPX = (int) (LABEL_PADDING * getResources().getDisplayMetrics().density);
        for (String candidate : displayCandidates) {
            MongolLabel label = new MongolLabel(mContext);
            label.setText(candidate);
            label.setTextSize(textSize);
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


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int summedWidth = 0;
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            child.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                    View.MeasureSpec.makeMeasureSpec(mHeight, MeasureSpec.EXACTLY));
            summedWidth += child.getMeasuredWidth();
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

    public void updateTouchPosition(int x) {
        // see which candidate is selected
        int highlightedIndex = getHighlightedCandidateIndex(x);
        // highlight that candidate
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            if (i == highlightedIndex) {
                child.setBackgroundColor(mHighlightColor);
            } else {
                child.setBackgroundColor(Color.TRANSPARENT);
            }
        }
    }

    public int getHighlightedCandidateIndex(int x) {
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

    public CharSequence getCurrentItem(int touchPositionX) {
        int highlightedIndex = getHighlightedCandidateIndex(touchPositionX);
        if (highlightedIndex >= 0 && mCandidates != null) {
            return mCandidates[highlightedIndex];
        }
        return "";
    }
}
