package net.studymongolian.mongollibrary;

import android.content.Context;
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
    //private String[] mDisplayCandidates;

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
            label.setGravity(Gravity.CENTER);
            addView(label);
        }
    }

    public void setHeight(int height) {
        this.mHeight = height;
    }

    public void setHighlightColor(int highlightColor) {
        this.mHighlightColor = highlightColor;
    }

//    public void isnit(String[] candidates,
//                     String[] displayCandidates,
//                     int textSize,
//                     int height,
//                     int highlightColor) {
//
//        if (candidates.length != displayCandidates.length)
//            throw new RuntimeException(
//                    "Popup key candidates must have the same number as the displayed items.");
//
//
//
//        this.mCandidates = candidates;
//
//    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        // ignoring measure specs
        // (assuming that keyboard will give us as much room as needed)

        int summedWidth = 0;
        //int maxHeight = 0;

        //int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            child.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                    View.MeasureSpec.makeMeasureSpec(mHeight, MeasureSpec.EXACTLY));
            //int height = child.getMeasuredHeight();
            summedWidth += child.getMeasuredWidth();
            //maxHeight = Math.max(maxHeight, child.getMeasuredHeight());
        }

        setMeasuredDimension(
                summedWidth + getPaddingLeft() + getPaddingRight(),
                mHeight + getPaddingTop() + getPaddingBottom());
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {

        int leftOffset = this.getPaddingLeft();
        int topOffset = this.getPaddingTop();

        int count = getChildCount();
        //int measureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            child.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
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
