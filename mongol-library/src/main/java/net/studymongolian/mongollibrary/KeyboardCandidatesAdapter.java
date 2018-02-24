package net.studymongolian.mongollibrary;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

class KeyboardCandidatesAdapter extends RecyclerView.Adapter<KeyboardCandidatesAdapter.ViewHolder> {

    private List<String> candidates;
    private KeyboardCandidatesView.Orientation orientation;
    private LayoutInflater inflater;
    private ItemClickListener mClickListener;

    KeyboardCandidatesAdapter(Context context, List<String> words, KeyboardCandidatesView.Orientation orientation) {
        this.inflater = LayoutInflater.from(context);
        this.candidates = words;
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
        String word = candidates.get(position);
        holder.setText(word);
    }

    @Override
    public int getItemCount() {
        return candidates.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        View textView;

        ViewHolder(View itemView) {
            super(itemView);
            textView = getTextView(itemView);
            itemView.setOnClickListener(this);
        }

        private View getTextView(View itemView) {
            if (orientation == KeyboardCandidatesView.Orientation.VERTICAL)
                return itemView.findViewById(R.id.mongolLabel);
            else
                return itemView.findViewById(R.id.textView);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }

        void setText(String text) {
            if (orientation == KeyboardCandidatesView.Orientation.VERTICAL)
                ((MongolLabel) textView).setText(text);
            else
                ((TextView) textView).setText(text);
        }
    }

    String getItem(int id) {
        return candidates.get(id);
    }

    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}