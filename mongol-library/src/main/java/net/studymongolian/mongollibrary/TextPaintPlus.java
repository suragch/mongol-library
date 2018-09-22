package net.studymongolian.mongollibrary;

import android.graphics.Paint;
import android.support.annotation.ColorInt;
import android.text.TextPaint;

public class TextPaintPlus extends TextPaint {

    @ColorInt
    public int strokeColor;

    public TextPaintPlus() {
        super();
    }

    public TextPaintPlus(int flags) {
        super(flags);
    }

    public TextPaintPlus(Paint p) {
        super(p);
    }

    /**
     * Copy the fields from tp into this TextPaint, including the
     * fields inherited from Paint.
     */
    public void set(TextPaintPlus tpp) {
        super.set(tpp);

        strokeColor = tpp.strokeColor;
    }

    public void setStrokeColor(@ColorInt int color) {
        this.strokeColor = color;
    }
}
