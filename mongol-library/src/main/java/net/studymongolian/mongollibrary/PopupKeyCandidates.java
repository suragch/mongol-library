package net.studymongolian.mongollibrary;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

class PopupKeyCandidates extends ViewGroup {

    private static final int LABEL_PADDING = 20;

    private final Context mContext;

    // this popup view will only be created programmatically
    public PopupKeyCandidates(Context context) {
        super(context);
        this.mContext = context;
    }

    public void init(String[] candidates, int textSize) {
        for (String candidate : candidates) {
            MongolLabel label = new MongolLabel(mContext);
            label.setText(candidate);
            label.setTextSize(textSize);
            label.setPadding(LABEL_PADDING, LABEL_PADDING, LABEL_PADDING, LABEL_PADDING);
            addView(label);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        // ignoring measure specs
        // (assuming that keyboard will give us as much room as needed)

        int summedWidth = 0;
        int maxHeight = 0;

        int measureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            child.measure(measureSpec, measureSpec);
            summedWidth += child.getMeasuredWidth();
            maxHeight = Math.max(maxHeight, child.getMeasuredHeight());
        }

        setMeasuredDimension(
                summedWidth + getPaddingLeft() + getPaddingRight(),
                maxHeight + getPaddingTop() + getPaddingBottom());
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {

        int leftOffset = this.getPaddingLeft();
        int topOffset = this.getPaddingTop();

        int count = getChildCount();
        int measureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            child.measure(measureSpec, measureSpec);

            int rightOffset = leftOffset + child.getMeasuredWidth();
            int bottomOffset = topOffset + child.getMeasuredHeight();
            child.layout(leftOffset, topOffset, rightOffset, bottomOffset);
            leftOffset = rightOffset;
        }
    }

}
