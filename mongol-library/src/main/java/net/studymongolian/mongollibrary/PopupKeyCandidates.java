package net.studymongolian.mongollibrary;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

class PopupKeyCandidates extends ViewGroup {

    private static final int LABEL_PADDING = 5; // dp
    private final Context mContext;

    private int mHeight = 0;
    private int mHighlightColor;

    // this popup view will only be created programmatically
    public PopupKeyCandidates(Context context) {
        super(context);
        this.mContext = context;
    }

    public void init(String[] candidates, int textSize, int height, int highlightColor) {
        int paddingPX = (int) (LABEL_PADDING * getResources().getDisplayMetrics().density);
        this.mHeight = height;
        this.mHighlightColor = highlightColor;
        for (String candidate : candidates) {
            MongolLabel label = new MongolLabel(mContext);
            label.setText(candidate);
            label.setTextSize(textSize);
            label.setPadding(paddingPX, paddingPX, paddingPX, paddingPX);
            label.setGravity(Gravity.CENTER);
            addView(label);
        }
    }

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
            int height = child.getMeasuredHeight();
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

    private int getHighlightedCandidateIndex(int x) {
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            int location[] = new int[2];
            child.getLocationOnScreen(location);
            int leftSide = location[0];
            if (x < leftSide) return i - 1;
            int rightSide = leftSide + child.getMeasuredWidth();
            if (x < rightSide) return i;
        }
        return count;
    }

}
