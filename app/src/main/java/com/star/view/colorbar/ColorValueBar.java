package com.star.view.colorbar;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;

/**
 * Detail：明度条
 * Author：Stars
 * Create Time：2019/4/4 6:31
 */
public class ColorValueBar extends ColorSeekView implements OnColorChangedListener {

    private float v = 1;    //明度

    public ColorValueBar(Context context) {
        super(context);
    }

    public ColorValueBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ColorValueBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected int[] getLinearColors() {
        float[] hsv = getHSV(getColor());
        hsv[2] = 1;
        int endColor = Color.HSVToColor(hsv);
        return new int[]{Color.BLACK, endColor};
    }

    @Override
    protected float getColorPosition(int color, float startX, float startY) {
        float[] colors = new float[3];
        Color.colorToHSV(color, colors);
        float p = colors[2] / 1f;
        return (getWidth() - startX * 2) * p + startX;
    }

    @Override
    protected int getPositionColor(float position, float startX, float startY) {
        float[] colors = new float[3];
        Color.colorToHSV(getColor(), colors);
        float unit = (position - startX) / (getWidth() - startX * 2);
        if (unit <= 0) {
            unit = 0;
        }
        if (unit >= 1) {
            unit = 1;
        }
        colors[2] = unit;
        v = unit;
        return Color.HSVToColor(colors);
    }

    /**
     * 设置颜色，但不改变明度
     * @param color
     */
    public void setColorStayV(int color) {
        float[] hsv = getHSV(color);
        hsv[2] = v;
        setColor(Color.HSVToColor(hsv));
    }

    /**
     * 设置颜色，同时会改变明度
     * @param color
     */
    @Override
    public void setColor(int color) {
        v = getHSV(color)[2];
        super.setColor(color);
    }

    private float[] getHSV(int color) {
        float[] colors = new float[3];
        Color.colorToHSV(color, colors);
        return colors;
    }


    @Override
    public void onChanged(int color) {
        setColorStayV(color);
    }
}
