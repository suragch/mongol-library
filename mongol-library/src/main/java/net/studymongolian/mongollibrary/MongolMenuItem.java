package net.studymongolian.mongollibrary;


public class MongolMenuItem {

    public static final int NO_ICON = 0;
    private CharSequence title;
    private int iconResId;

    public MongolMenuItem(CharSequence title, int iconResId) {
        this.title = title;
        this.iconResId = iconResId;
    }

    public MongolMenuItem setTitle(CharSequence title) {
        this.title = title;
        return this;
    }

    public CharSequence getTitle() {
        return title;
    }

    public MongolMenuItem setIcon(int iconResId) {
        this.iconResId = iconResId;
        return this;
    }

    public int getIconResId() {
        return iconResId;
    }
}
