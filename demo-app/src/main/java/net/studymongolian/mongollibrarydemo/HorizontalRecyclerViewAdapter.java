package net.studymongolian.mongollibrarydemo;


import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.studymongolian.mongollibrary.MongolTextView;

import java.util.List;

public class HorizontalRecyclerViewAdapter
        extends RecyclerView.Adapter<HorizontalRecyclerViewAdapter.ViewHolder> {

    private List<String> mNumbers;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    HorizontalRecyclerViewAdapter(Context context, List<String> numbers) {
        this.mInflater = LayoutInflater.from(context);
        this.mNumbers = numbers;
    }

    // inflates the row layout from xml when needed
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.horizontal_recyclerview_item, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the view and textview in each row
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String number = mNumbers.get(position);
        holder.mongolTextView.setText(number);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mNumbers.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        MongolTextView mongolTextView;

        ViewHolder(View itemView) {
            super(itemView);
            mongolTextView = itemView.findViewById(R.id.mongol_textview);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    public String getItem(int id) {
        return mNumbers.get(id);
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}