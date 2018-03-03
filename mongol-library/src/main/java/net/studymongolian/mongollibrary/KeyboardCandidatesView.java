package net.studymongolian.mongollibrary;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class KeyboardCandidatesView extends ViewGroup implements KeyboardCandidatesAdapter.ItemClickListener {

    private static final Orientation DEFAULT_ORIENTATION = Orientation.VERTICAL;

    Context mContext;
    RecyclerView recyclerView;
    KeyboardCandidatesAdapter adapter;
    List<String> mCandidates;
    Orientation mOrientation;

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

    @Override
    public void onItemClick(View view, int position) {
        String item = adapter.getItem(position);
        MongolToast.makeText(mContext, item, Toast.LENGTH_SHORT).show();
        //Toast.makeText(mContext, item, Toast.LENGTH_SHORT).show();
    }

    public void setOrientation(Orientation orientation) {
        this.mOrientation = orientation;
        LinearLayoutManager layoutManager;
        if (mOrientation == Orientation.HORIZONTAL)
            layoutManager = new LinearLayoutManager(mContext,
                    LinearLayoutManager.HORIZONTAL, false);
        else
            layoutManager = new LinearLayoutManager(mContext,
                    LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
    }

    public void setCandidates(List<String> candidates) {
        // data to populate the RecyclerView with
        //List<String> animalNames = new ArrayList<>();
        mCandidates.clear();
        mCandidates.addAll(candidates);

        if (adapter == null) {
            initAdapter();
        }
        adapter.notifyDataSetChanged();
    }


    private void initAdapter() {
        adapter = new KeyboardCandidatesAdapter(mContext, mCandidates, mOrientation);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

}
