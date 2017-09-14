package net.studymongolian.mongollibrary;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;


public class RotatedViewGroup extends ViewGroup {

    private int angle = 90;
    private final Matrix rotateMatrix = new Matrix();
    private final Rect viewRectRotated = new Rect();
    private final RectF tempRectF1 = new RectF();
    private final RectF tempRectF2 = new RectF();
    private final float[] viewTouchPoint = new float[2];
    private final float[] childTouchPoint = new float[2];

    public RotatedViewGroup(Context context) {
        this(context, null);
    }

    public RotatedViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
    }

    public View getView() {
        return getChildAt(0);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final View view = getView();
        if (view != null) {
            measureChild(view, heightMeasureSpec, widthMeasureSpec);
            setMeasuredDimension(resolveSize(view.getMeasuredHeight(), widthMeasureSpec),
                    resolveSize(view.getMeasuredWidth(), heightMeasureSpec));
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        final RectF layoutRect = tempRectF1;
        final RectF layoutRectRotated = tempRectF2;
        layoutRect.set(0, 0, right - left, bottom - top);
        rotateMatrix.setRotate(angle, layoutRect.centerX(), layoutRect.centerY());
        rotateMatrix.postScale(-1, 1);
        rotateMatrix.mapRect(layoutRectRotated, layoutRect);
        layoutRectRotated.round(viewRectRotated);
        final View view = getView();
        if (view != null) {
            view.layout(viewRectRotated.left, viewRectRotated.top, viewRectRotated.right,
                    viewRectRotated.bottom);
        }
    }


    @Override
    protected void dispatchDraw(Canvas canvas) {
        canvas.save();
        canvas.rotate(-angle, getWidth() / 2f, getHeight() / 2f);
        canvas.scale(-1, 1);
        super.dispatchDraw(canvas);
        canvas.restore();
    }

    @Override
    public ViewParent invalidateChildInParent(int[] location, Rect dirty) {
        invalidate();
        return super.invalidateChildInParent(location, dirty);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        viewTouchPoint[0] = event.getX();
        viewTouchPoint[1] = event.getY();
        rotateMatrix.mapPoints(childTouchPoint, viewTouchPoint);
        event.setLocation(childTouchPoint[0], childTouchPoint[1]);
        boolean result = super.dispatchTouchEvent(event);
        event.setLocation(viewTouchPoint[0], viewTouchPoint[1]);
        return result;
    }

}