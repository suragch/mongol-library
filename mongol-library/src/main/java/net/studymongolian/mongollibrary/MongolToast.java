package net.studymongolian.mongollibrary;


import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.TypedValue;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MongolToast {

    public static final int LENGTH_SHORT = Toast.LENGTH_SHORT;
    public static final int LENGTH_LONG = Toast.LENGTH_LONG;

    private static final int DEFAULT_BACKGROUND = Color.parseColor("#555555");
    private static final int DEFAULT_TEXT_COLOR = Color.WHITE;
    private static final int DEFAULT_TEXT_SIZE = 20;
    private static final int DEFAULT_CORNER_RADIUS = 24;
    private static final float DEFAULT_HORIZONTAL_PADDING = 11;
    private static final float DEFAULT_VERTICAL_PADDING = 25;
    private static final int DEFAULT_ALPHA = 230;

    private final Context context;
    private CharSequence text;
    private int duration;

    public MongolToast(Context context) {
        this.context = context;
    }

    public MongolToast(Context context, CharSequence text, int duration) {
        this.context = context;
        this.text = text;
        this.duration = duration;
    }

    public static MongolToast makeText(Context context, CharSequence text, int duration) {
        return new MongolToast(context, text, duration);
    }

    public void show() {
        Toast mongolToast = new Toast(context);
        mongolToast.setDuration(duration);
        mongolToast.setView(getLayout());
        mongolToast.show();
    }

    private View getLayout() {
        int horizontalPadding = (int) getTypedValueInDP(context, DEFAULT_HORIZONTAL_PADDING);
        int verticalPadding = (int) getTypedValueInDP(context, DEFAULT_VERTICAL_PADDING);
        RelativeLayout rootLayout = new RelativeLayout(context);
        rootLayout.setPadding(horizontalPadding, verticalPadding, horizontalPadding, verticalPadding);
        rootLayout.setBackgroundDrawable(getShape());
        rootLayout.addView(getTextView());
        return rootLayout;
    }

    private GradientDrawable getShape() {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(getTypedValueInDP(context, DEFAULT_CORNER_RADIUS));
        gradientDrawable.setColor(DEFAULT_BACKGROUND);
        gradientDrawable.setAlpha(DEFAULT_ALPHA);
        return gradientDrawable;
    }

    private MongolTextView getTextView() {
        MongolTextView textView = new MongolTextView(context);
        textView.setText(text);
        textView.setTextSize(DEFAULT_TEXT_SIZE);
        textView.setTextColor(DEFAULT_TEXT_COLOR);
        return textView;
    }

    private static float getTypedValueInDP(Context context, float value) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, context.getResources().getDisplayMetrics());
    }
}
