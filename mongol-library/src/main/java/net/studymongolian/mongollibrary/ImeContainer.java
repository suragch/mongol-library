package net.studymongolian.mongollibrary;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Input Method Container
 *
 * Currently it is a container/controller for Keyboards and suggested word candidates. In the future
 * it could also be a container for other IME views (like handwriting recognition or speech-to-text).
 *
 * The word candidates may be arranged vertically on the left of horizontally at the top.
 *
 * ImeContainer manages switching keyboards and handling communication between the keyboard and the
 * word suggestion candidates list.
 */
public class ImeContainer extends ViewGroup {



    public ImeContainer(Context context) {
        this(context, null, 0);
        //this.mContext = context;
    }

    public ImeContainer(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ImeContainer(Context context,
                         AttributeSet attrs,
                         int defStyle) {
        super(context, attrs, defStyle);
        //init(context);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {

        //   ___________________________         ___________________________
        //   | C |                     |         |       Candidates        |
        //   | a |                     |         |_________________________|
        //   | n |                     |         |                         |
        //   | d |                     |         |                         |
        //   | i |                     |         |                         |
        //   | d |     Keyboard        |    or   |        Keyboard         |
        //   | a |                     |         |                         |
        //   | t |                     |         |                         |
        //   | e |                     |         |                         |
        //   | s |                     |         |                         |
        //   ---------------------------         ---------------------------

        // TODO need to add a Candidates view (currently Keyboard is filling the entire layout)




        // make sure there are children
        int count = getChildCount();
        if (count < 1) {
            throw new RuntimeException("You must add at least one keyboard to the ImeContainer");
        }
        // make sure that all subviews are Keyboards TODO: will need to update this for Candidate views
        for (int i = 0; i < count; i++) {
            View view = getChildAt(i);
            if (!(view instanceof Keyboard)) {
                throw new RuntimeException("The child views of ImeContainer must be Keyboards.");
            }
        }


        final int totalAvailableWidth = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
        final int totalAvailableHeight = getMeasuredHeight() - getPaddingTop() - getPaddingBottom();

        float x = getPaddingLeft();
        float y = getPaddingTop();
        int keyboardWidth = totalAvailableWidth;
        int keyboardHeight = totalAvailableHeight;

        // just choose the first keyboard for now
        View child = getChildAt(0);
        child.measure(MeasureSpec.makeMeasureSpec(keyboardWidth, MeasureSpec.EXACTLY),
                MeasureSpec.makeMeasureSpec(keyboardHeight, MeasureSpec.EXACTLY));
        child.layout((int) x, (int) y, (int) (x + keyboardWidth), (int) (y + keyboardHeight));

    }
}
