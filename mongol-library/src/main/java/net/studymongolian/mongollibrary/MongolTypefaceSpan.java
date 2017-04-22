package net.studymongolian.mongollibrary;

import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;



public class MongolTypefaceSpan extends MetricAffectingSpan {

    private final Typeface typeface;

    public  MongolTypefaceSpan(Typeface typeface) {

        this.typeface = typeface;

    }

    @Override
    public void updateMeasureState(TextPaint paint) {
        apply(paint);
    }

    @Override
    public void updateDrawState(TextPaint paint) {
        apply(paint);
    }

    private void apply(TextPaint paint) {
        int oldStyle;
        Typeface old = paint.getTypeface();
        if (old == null) {
            oldStyle = 0;
        } else {
            oldStyle = old.getStyle();
        }
        final int fakeStyle = oldStyle & ~typeface.getStyle();

        if ((fakeStyle & Typeface.BOLD) != 0) {
            paint.setFakeBoldText(true);
        }

        if ((fakeStyle & Typeface.ITALIC) != 0) {
            paint.setTextSkewX(-0.25f);
        }

        paint.setTypeface(typeface);
    }
}
