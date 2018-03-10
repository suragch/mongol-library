package net.studymongolian.mongollibrary;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

class KeyboardCandidatesAdapter extends RecyclerView.Adapter<KeyboardCandidatesAdapter.ViewHolder> {

    private List<String> mCandidates;
    private KeyboardCandidatesView.Orientation orientation;
    private LayoutInflater inflater;
    private CandidateClickListener mCandidateClickListener;

    KeyboardCandidatesAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
        //this.orientation = orientation;
        this.mCandidates = new ArrayList<>();
    }

    interface CandidateClickListener {

        void onCandidateClick(int position, String text);
        void onCandidateLongClick(int position, String text);
    }
    public void setCandidates(List<String> candidates) {
        mCandidates.clear();
        mCandidates.addAll(candidates);
        notifyDataSetChanged();
    }

    public void clearCandidates() {
        mCandidates.clear();
        notifyDataSetChanged();
    }

    public boolean hasCandidates() {
        return mCandidates != null && mCandidates.size() > 0;
    }

    public void setOrientation(KeyboardCandidatesView.Orientation orientation) {
        this.orientation = orientation;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = getInflatedView(parent);
        return new ViewHolder(view);
    }

    private View getInflatedView(ViewGroup parent) {
        if (orientation == KeyboardCandidatesView.Orientation.VERTICAL)
            return inflater.inflate(R.layout.vertical_keyboard_candidates_item,
                    parent, false);
        else
            return inflater.inflate(R.layout.horizontal_keyboard_candidates_item,
                    parent, false);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String word = mCandidates.get(position);
        holder.setText(word);
    }

    @Override
    public int getItemCount() {
        return mCandidates.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnLongClickListener {

        View textView;
        View divider;

        ViewHolder(View itemView) {
            super(itemView);
            textView = getTextView(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        private View getTextView(View itemView) {
            if (orientation == KeyboardCandidatesView.Orientation.VERTICAL)
                return itemView.findViewById(R.id.mongolLabel);
            else
                return itemView.findViewById(R.id.textView);
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
            if (orientation == KeyboardCandidatesView.Orientation.VERTICAL) {
                ((MongolLabel) textView).setText(text);
                ((MongolLabel) textView).setTextSize(26); // FIXME put this somewhere else
            } else
                ((TextView) textView).setText(text);
        }

    }

    String getItem(int id) {
        return mCandidates.get(id);
    }

    void setCandidateClickListener(CandidateClickListener listener) {
        this.mCandidateClickListener = listener;
    }
}