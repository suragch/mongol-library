package net.studymongolian.mongollibrary;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import java.util.ArrayList;
import java.util.List;

public class MongolMenu extends PopupWindow {

    private static int DEFAULT_DIVIDER_COLOR = Color.GRAY;


    private List<MongolMenuItem> menuItems;
    private OnMenuItemClickListener mMenuItemClickListener;
    private Context mContext;

    private int mDividerColor;

    public MongolMenu(Context context) {
        super(context);
        init(context);
    }

    private MongolMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private MongolMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private MongolMenu(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private MongolMenu() {
    }

    private MongolMenu(View contentView) {
        super(contentView);
    }

    private MongolMenu(int width, int height) {
        super(width, height);
    }

    private MongolMenu(View contentView, int width, int height) {
        super(contentView, width, height);
    }

    private MongolMenu(View contentView, int width, int height, boolean focusable) {
        super(contentView, width, height, focusable);
    }

    private void init(Context context) {
        mContext = context;
        menuItems = new ArrayList<>();
        mDividerColor = DEFAULT_DIVIDER_COLOR;
        setBackgroundDrawable(null);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setElevation(20);
        }
    }

    public interface OnMenuItemClickListener {
        boolean onMenuItemClick(MongolMenuItem item);
    }

    public void setOnMenuItemClickListener(OnMenuItemClickListener listener) {
        this.mMenuItemClickListener = listener;
    }

    public void add(MongolMenuItem item) {
        menuItems.add(item);
    }

    @Override
    public void showAsDropDown(View anchor) {
        setCustomContentView();
        super.showAsDropDown(anchor);
    }

    @Override
    public void showAsDropDown(View anchor, int xoff, int yoff) {
        setCustomContentView();
        super.showAsDropDown(anchor, xoff, yoff);
    }

    @Override
    public void showAsDropDown(View anchor, int xoff, int yoff, int gravity) {
        setCustomContentView();
        super.showAsDropDown(anchor, xoff, yoff, gravity);
    }

    @Override
    public void showAtLocation(View parent, int gravity, int x, int y) {
        setCustomContentView();
        super.showAtLocation(parent, gravity, x, y);
    }

    private void setCustomContentView() {
        View contentView = createContentView();
        setContentView(contentView);
        setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
        setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        setFocusable(true);
    }

    private View createContentView() {
        RecyclerView recyclerView = new RecyclerView(mContext);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext,
                LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        MenuItemAdapter adapter = new MenuItemAdapter(mContext);
        recyclerView.setAdapter(adapter);
        recyclerView.setBackgroundColor(Color.WHITE);
        return recyclerView;
    }

    class MenuItemAdapter extends RecyclerView.Adapter<MenuItemAdapter.ViewHolder> {

        private LayoutInflater inflater;

        MenuItemAdapter(Context context) {
            this.inflater = LayoutInflater.from(context);
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = getInflatedView(parent);
            return new ViewHolder(view);
        }

        private View getInflatedView(ViewGroup parent) {
                return inflater.inflate(R.layout.mongol_menu_item_layout,
                        parent, false);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            MongolMenuItem menuItem = menuItems.get(position);
            holder.icon.setImageResource(menuItem.getIconResId());
            holder.titleLabel.setText(menuItem.getTitle().toString());
        }

        @Override
        public int getItemCount() {
            return menuItems.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder
                implements View.OnClickListener {

            MongolLabel titleLabel;
            ImageView icon;

            ViewHolder(View itemView) {
                super(itemView);
                titleLabel = itemView.findViewById(R.id.menuItemTitle);
                icon = itemView.findViewById(R.id.menuItemIcon);
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View view) {
                if (mMenuItemClickListener != null) {
                    int position = getAdapterPosition();
                    MongolMenuItem item = getItem(position);
                    mMenuItemClickListener.onMenuItemClick(item);
                }
                dismiss();
            }

        }

        MongolMenuItem getItem(int id) {
            return menuItems.get(id);
        }
    }
}
