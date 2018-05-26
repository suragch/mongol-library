package net.studymongolian.mongollibrary;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ImeCandidatesView extends ViewGroup {

    private static final Orientation DEFAULT_ORIENTATION = Orientation.VERTICAL;
    public static int DEFAULT_CANDIDATE_ITEM_BACKGROUND_COLOR = Color.LTGRAY;
    public static int DEFAULT_CANDIDATE_ITEM_BACKGROUND_PRESSED_COLOR = Color.GRAY;
    public static int DEFAULT_CANDIDATE_ITEM_TEXT_COLOR = Color.BLACK;
    public static float DEFAULT_CANDIDATE_ITEM_TEXT_SIZE = Keyboard.DEFAULT_PRIMARY_TEXT_SIZE_SP;
    public static int DEFAULT_CANDIDATE_DIVIDER_COLOR = Color.GRAY;
    static final int DEFAULT_BORDER_COLOR = Keyboard.DEFAULT_KEY_BORDER_COLOR;
    static final int DEFAULT_BORDER_WIDTH = Keyboard.DEFAULT_KEY_BORDER_WIDTH;
    static final int DEFAULT_BORDER_RADIUS = Keyboard.DEFAULT_KEY_BORDER_RADIUS;
    static final int DEFAULT_SPACING = Keyboard.DEFAULT_KEY_SPACING;

    private int mTextColor;
    private float mTextSize;
    private int mPressedBackgroundColor;
    private int mDividerColor;

    private RectF mSizeRect;
    private int mBorderRadius;
    private Paint mKeyBorderPaint;
    private Paint mPaint;

    private Context mContext;
    private RecyclerView recyclerView;
    private CandidatesAdapter adapter;
    private List<String> mCandidates;
    private Orientation mOrientation;
    private CandidateClickListener mCandidateClickListener;

    public boolean hasCandidates() {
        return mCandidates != null && mCandidates.size() > 0;
    }

    public void clearCandidates() {
        if (adapter == null)
            return;
        mCandidates.clear();
        adapter.notifyDataSetChanged();
    }

    public void removeCandidate(int index) {
        mCandidates.remove(index);
        adapter.notifyItemRemoved(index);
    }

    public void setCandidates(List<String> candidates) {
        mCandidates.clear();
        if (candidates != null)
            mCandidates.addAll(candidates);
        if (adapter != null)
            adapter.notifyDataSetChanged();
    }

    public List<String> getCandidates() {
        return mCandidates;
    }

    public enum Orientation {
        VERTICAL,
        HORIZONTAL
    }

    public ImeCandidatesView(Context context) {
        super(context);
        init(context);
    }

    public ImeCandidatesView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ImeCandidatesView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {

        mOrientation = DEFAULT_ORIENTATION;
        mContext = context;
        mCandidates = new ArrayList<>();
        mTextColor = DEFAULT_CANDIDATE_ITEM_TEXT_COLOR;
        mTextSize = DEFAULT_CANDIDATE_ITEM_TEXT_SIZE;
        mPressedBackgroundColor = DEFAULT_CANDIDATE_ITEM_BACKGROUND_PRESSED_COLOR;
        mDividerColor = DEFAULT_CANDIDATE_DIVIDER_COLOR;
        initPaints();
        mBorderRadius = DEFAULT_BORDER_RADIUS;
        setPadding(DEFAULT_SPACING, DEFAULT_SPACING, DEFAULT_SPACING, DEFAULT_SPACING);

        // set up the RecyclerView
        recyclerView = new RecyclerView(context);
        addView(recyclerView);
        setWillNotDraw(false);
    }

    private void initPaints() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(DEFAULT_CANDIDATE_ITEM_BACKGROUND_COLOR);


        mKeyBorderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mKeyBorderPaint.setStyle(Paint.Style.STROKE);
        mKeyBorderPaint.setColor(DEFAULT_BORDER_COLOR);
        mKeyBorderPaint.setStrokeWidth(DEFAULT_BORDER_WIDTH);
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
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mSizeRect = new RectF(getPaddingLeft(), getPaddingTop(),
                w - getPaddingRight(), h - getPaddingBottom());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // draw background and border
        canvas.drawRoundRect(mSizeRect, mBorderRadius, mBorderRadius, mPaint);
        if (mKeyBorderPaint.getStrokeWidth() > 0) {
            canvas.drawRoundRect(mSizeRect, mBorderRadius, mBorderRadius, mKeyBorderPaint);
        }
    }

    public void setOrientation(Orientation orientation) {
        this.mOrientation = orientation;
        LinearLayoutManager layoutManager;
        if (mOrientation == Orientation.HORIZONTAL) {
            layoutManager = new LinearLayoutManager(mContext,
                    LinearLayoutManager.HORIZONTAL, false);
        } else {
            layoutManager = new LinearLayoutManager(mContext,
                    LinearLayoutManager.VERTICAL, false);
        }
        recyclerView.setLayoutManager(layoutManager);
        adapter = new CandidatesAdapter(mContext);
        recyclerView.setAdapter(adapter);
    }

    public void setCandidateBackgroundColor(int color) {
        mPaint.setColor(color);
        invalidate();
    }

    public void setBackgroundPressedColor(int color) {
        this.mPressedBackgroundColor = color;
    }

    public void setTextColor(int color) {
        this.mTextColor = color;
        invalidate();
    }

    public void setDividerColor(int color) {
        this.mDividerColor = color;
        invalidate();
    }

    public void setBorderColor(int borderColor) {
        mKeyBorderPaint.setColor(borderColor);
        invalidate();
    }

    public void setBorderWidth(int borderWidth) {
        mKeyBorderPaint.setStrokeWidth(borderWidth);
        invalidate();
    }

    public void setBorderRadius(int borderRadius) {
        this.mBorderRadius = borderRadius;
        invalidate();
    }


    interface CandidateClickListener {

        void onCandidateClick(int position, String text);

        void onCandidateLongClick(int position, String text);
    }

    void setCandidateClickListener(CandidateClickListener listener) {
        this.mCandidateClickListener = listener;
    }

    class CandidatesAdapter extends RecyclerView.Adapter<CandidatesAdapter.ViewHolder> {

        private LayoutInflater inflater;

        CandidatesAdapter(Context context) {
            this.inflater = LayoutInflater.from(context);
        }

        @Override
        @NonNull
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getInflatedView(parent);
            return new ViewHolder(view);
        }

        private View getInflatedView(ViewGroup parent) {
            if (mOrientation == ImeCandidatesView.Orientation.VERTICAL)
                return inflater.inflate(R.layout.vertical_keyboard_candidates_item,
                        parent, false);
            else
                return inflater.inflate(R.layout.horizontal_keyboard_candidates_item,
                        parent, false);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            String word = mCandidates.get(position);
            holder.setText(word);
            holder.divider.setBackgroundColor(mDividerColor);
        }

        @Override
        public int getItemCount() {
            return mCandidates.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder
                implements View.OnClickListener, View.OnLongClickListener, View.OnTouchListener {

            View textView;
            View divider;

            ViewHolder(View itemView) {
                super(itemView);
                textView = getTextView(itemView);
                divider = itemView.findViewById(R.id.divider);
                itemView.setOnClickListener(this);
                itemView.setOnLongClickListener(this);
                itemView.setOnTouchListener(this);
            }

            private View getTextView(View itemView) {
                if (mOrientation == ImeCandidatesView.Orientation.VERTICAL) {
                    MongolLabel label = itemView.findViewById(R.id.mongolLabel);
                    label.setTextColor(mTextColor);
                    label.setTextSize(mTextSize);
                    return label;
                } else {
                    TextView tv = itemView.findViewById(R.id.textView);
                    tv.setTextSize(mTextSize);
                    tv.setTextColor(mTextColor);
                    tv.setTypeface(MongolFont.get(MongolFont.QAGAN, mContext));
                    return tv;
                }
            }

            @Override
            public void onClick(View view) {
                if (mCandidateClickListener != null) {
                    int position = getAdapterPosition();
                    String text = getItem(position);
                    mCandidateClickListener.onCandidateClick(position, text);
                }
            }

            @Override
            public boolean onLongClick(View v) {
                if (mCandidateClickListener != null) {
                    int position = getAdapterPosition();
                    String text = getItem(position);
                    mCandidateClickListener.onCandidateLongClick(position, text);
                    return true;
                }
                return false;
            }

            void setText(String text) {
                if (mOrientation == ImeCandidatesView.Orientation.VERTICAL) {
                    ((MongolLabel) textView).setText(text);
                } else {
                    String renderedText = MongolCode.INSTANCE.unicodeToMenksoft(text);
                    ((TextView) textView).setText(renderedText);
                    // On Android 5.0 the TextView is not changing width for new text
                    // so we are manually calling a relayout here.
                    textView.requestLayout();
                }
            }

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        view.setBackgroundColor(mPressedBackgroundColor);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        break;
                    case MotionEvent.ACTION_UP:
                        view.performClick();
                        view.setBackgroundColor(Color.TRANSPARENT);
                        break;
                    default:
                        view.setBackgroundColor(Color.TRANSPARENT);
                }
                return false;
            }

        }

        String getItem(int id) {
            return mCandidates.get(id);
        }
    }
}
