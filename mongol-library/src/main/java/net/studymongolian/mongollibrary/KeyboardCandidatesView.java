package net.studymongolian.mongollibrary;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KeyboardCandidatesView extends ViewGroup {

    private static final Orientation DEFAULT_ORIENTATION = Orientation.VERTICAL;

    private Context mContext;
    private RecyclerView recyclerView;
    //private KeyboardCandidatesAdapter adapter;
    private List<String> mCandidates;
    private Orientation mOrientation;
    //private ItemListener mListener;



    public enum Orientation {
        VERTICAL,
        HORIZONTAL
    }

    public KeyboardCandidatesView(Context context) {
        super(context);
        init(context);
    }

    public KeyboardCandidatesView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public KeyboardCandidatesView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {

        mOrientation = DEFAULT_ORIENTATION;
        mContext = context;
        mCandidates = new ArrayList<>();

        //this.setBackgroundColor(Color.WHITE);

        // set up the RecyclerView
        recyclerView = new RecyclerView(context);
        addView(recyclerView);
    }

//    public interface ItemListener {
//        public void onCandidateClick(int position, String text);
//        public void onCandidateLongClick(int position, String text);
//    }
//
//    public void setItemListener(ItemListener listener) {
//        this.mListener = listener;
//    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {

        int left = getPaddingLeft();
        int top = getPaddingTop();
        int right = getMeasuredWidth() - getPaddingRight();
        int bottom = getMeasuredHeight() - getPaddingBottom();

        final int availableWidth = right - left;
        final int availableHeight = bottom - top;

        recyclerView.measure(MeasureSpec.makeMeasureSpec(availableWidth, MeasureSpec.EXACTLY),
                MeasureSpec.makeMeasureSpec(availableHeight, MeasureSpec.EXACTLY));
        recyclerView.layout(left, top, right, bottom);
    }

//    @Override
//    public void onCandidateClick(View view, int position) {
//        String text = adapter.getItem(position);
//        MongolToast.makeText(mContext, text, Toast.LENGTH_SHORT).show();
//        //if (mListener != null)
//        //    mListener.onCandidateClick(position, text);
//        //Toast.makeText(mContext, item, Toast.LENGTH_SHORT).show();
//    }

    public void setOrientation(Orientation orientation) {
        this.mOrientation = orientation;
        LinearLayoutManager layoutManager;
        if (mOrientation == Orientation.HORIZONTAL) {
            layoutManager = new LinearLayoutManager(mContext,
                    LinearLayoutManager.HORIZONTAL, false);
        }
        else {
            layoutManager = new LinearLayoutManager(mContext,
                    LinearLayoutManager.VERTICAL, false);
        }
        recyclerView.setLayoutManager(layoutManager);
        //DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
        //        layoutManager.getOrientation());
        //recyclerView.addItemDecoration(dividerItemDecoration);
    }

//    public void setCandidates(List<String> candidates, KeyboardCandidatesAdapter adapter) {
//        // data to populate the RecyclerView with
//        //List<String> animalNames = new ArrayList<>();
//        mCandidates.clear();
//        mCandidates.addAll(candidates);
//
////        if (adapter == null) {
////            initAdapter();
////        }
//        adapter.notifyDataSetChanged();
//    }


//    public void clearCandidates() {
//        setCandidates(Collections.<String>emptyList());
//    }

//    private void initAdapter() {
//        adapter = new KeyboardCandidatesAdapter(mContext, mCandidates, mOrientation);
//        adapter.setClickListener(this);
//        recyclerView.setAdapter(adapter);
//    }

    public void setAdapter(KeyboardCandidatesAdapter adapter) {
        recyclerView.setAdapter(adapter);
    }
}
