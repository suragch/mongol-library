package net.studymongolian.mongollibrary;

import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.ColorInt;
import android.text.TextPaint;

public class TextPaintPlus extends TextPaint {

    @ColorInt
    private int strokeColor;

    private float mShadowLayerRadius;
    private float mShadowLayerDx;
    private float mShadowLayerDy;
    private int mShadowLayerColor;

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

        strokeColor = tpp.getStrokeColor();
        mShadowLayerRadius = tpp.getShadowLayerRadius();
        mShadowLayerDx = tpp.getShadowLayerDx();
        mShadowLayerDy = tpp.getShadowLayerDy();
        mShadowLayerColor = tpp.getShadowLayerColor();
    }

    public void setStrokeColor(@ColorInt int color) {
        this.strokeColor = color;
        //clearShadowLayer();
        //super.setShadowLayer(mShadowLayerRadius, mShadowLayerDx, mShadowLayerDy, mShadowLayerColor);
    }

    public int getStrokeColor() {
        return this.strokeColor;
    }

    @Override
    public void setShadowLayer(float radius, float dx, float dy, int shadowColor) {
        mShadowLayerRadius = radius;
        mShadowLayerDx = dx;
        mShadowLayerDy = dy;
        mShadowLayerColor = shadowColor;
        super.setShadowLayer(radius, dx, dy, shadowColor);
    }

    @Override
    public void clearShadowLayer() {
        setShadowLayer(0, 0, 0, 0);
    }

    @Override
    public void reset() {

        strokeColor = 0;

        mShadowLayerRadius = 0.0f;
        mShadowLayerDx = 0.0f;
        mShadowLayerDy = 0.0f;
        mShadowLayerColor = 0;

        super.reset();
    }

    public boolean hasStroke() {
        return getStrokeWidth() > 0 && strokeColor != Color.TRANSPARENT;
    }

    // This is a replacement since Paint.hasShadowLayer() is hidden
    public boolean hasShadowLayer() {
        return mShadowLayerRadius > 0
                && mShadowLayerColor != Color.TRANSPARENT;
    }

    public float getShadowLayerRadius() {
        return mShadowLayerRadius;
    }

    public float getShadowLayerDx() {
        return mShadowLayerDx;
    }

    public float getShadowLayerDy() {
        return mShadowLayerDy;
    }

    public int getShadowLayerColor() {
        return mShadowLayerColor;
    }


}
