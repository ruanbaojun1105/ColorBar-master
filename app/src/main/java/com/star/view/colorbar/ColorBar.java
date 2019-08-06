package com.star.view.colorbar;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;

/**
 * Detail：颜色条
 * Author：Stars
 * Create Time：2019/4/4 6:31
 */
public class ColorBar extends ColorSeekView {

    private int[] colors;

    private ColorValueBar valueBar;

    public ColorBar(Context context) {
        this(context, null);
    }

    public ColorBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        setColor(colors[0]);
    }

    @Override
    protected int[] getLinearColors() {
        if (colors == null) {
            colors = new int[]{0xFFFF0000, 0xFFFF00FF, 0xFF0000FF, 0xFF00FFFF, 0xFF00FF00, 0xFFFFFF00, 0xFFFF0000};
        }
        return colors;
    }

    @Override
    protected float getColorPosition(int color, float startX, float startY) {
        float[] colors = new float[3];
        Color.colorToHSV(color, colors);
        float p = (360f - colors[0]) / 360f;
        return (getWidth() - startX * 2) * p + startX;
    }

    @Override
    protected int getPositionColor(float position, float startX, float startY) {
        float unit = (position - startX) / (getWidth() - startX * 2);
        if (unit <= 0) {
            unit = 0.001f;
        }
        if (unit >= 1) {
            return colors[colors.length - 1];
        }

        float p = unit * (colors.length - 1);
        int i = (int) p;
        p -= i;

        int c0 = colors[i];
        int c1 = colors[i + 1];
        int a = ave(Color.alpha(c0), Color.alpha(c1), p);
        int r = ave(Color.red(c0), Color.red(c1), p);
        int g = ave(Color.green(c0), Color.green(c1), p);
        int b = ave(Color.blue(c0), Color.blue(c1), p);
        return Color.argb(a, r, g, b);
    }

    public void setValueBar(ColorValueBar bar) {
        this.valueBar = bar;
        addOnColorChangedListener(bar);
        bar.setColor(getColor());
    }

    @Override
    public void setColor(int color) {
        super.setColor(color);
        if (valueBar != null) {
            valueBar.setColor(color);
        }
    }

    /**
     * 获取最终色值
     * @return
     */
    public int getValueColor() {
        if (valueBar != null) {
            return valueBar.getColor();
        } else {
            return getColor();
        }
    }

    private int ave(int s, int d, float p) {
        return s + Math.round(p * (d - s));
    }

}
