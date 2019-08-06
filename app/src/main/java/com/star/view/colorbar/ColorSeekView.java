package com.star.view.colorbar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Detail：
 * Author：Stars
 * Create Time：2019/8/6 9:05
 */
public abstract class ColorSeekView extends View {

    private Paint paint;
    private Paint thumbPaint;

    private float radius;
    private float startX;
    private float startY;
    private float borderWidth;

    private RectF rectF;
    private RectF rectThumb;

    private int color;
    private OnColorChangedListener listeners;

    public ColorSeekView(Context context) {
        this(context, null);
    }

    public ColorSeekView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ColorSeekView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        rectF = new RectF();
        rectThumb=new RectF();
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        thumbPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        setThumbColor(Color.WHITE);
        invalidateLinearGradient();
    }

    public void setThumbColor(int color) {
        thumbPaint.setColor(color);
        invalidate();
    }


    /**
     * 获取渐变色
     * @return
     */
    protected abstract int[] getLinearColors();

    /**
     * 根据颜色获取进度位置
     * @param color
     * @param startX
     * @param startY
     * @return
     */
    protected abstract float getColorPosition(int color, float startX, float startY);

    /**
     * 根据进度位置获取颜色
     * @param position
     * @param startX
     * @param startY
     * @return
     */
    protected abstract int getPositionColor(float position, float startX, float startY);

    public void setColor(int color) {
        this.color = color;
        if (listeners != null) {
            listeners.onChanged(color);
        }
        invalidateLinearGradient();
        invalidate();
    }

    public int getColor() {
        return color;
    }

    public void addOnColorChangedListener(OnColorChangedListener listener) {
        listeners=listener;
    }

    private void invalidateLinearGradient() {
        LinearGradient linearGradient = new LinearGradient(startX, 0, getWidth() - startX,
                getHeight(), getLinearColors(), null, Shader.TileMode.CLAMP);
        paint.setShader(linearGradient);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        radius = h / 6;
        startX = radius;
        startY = radius*2;
        invalidateLinearGradient();
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //DrawBar
        rectF.set(startX, getHeight()-startY, getWidth() - startX, getHeight()-radius);
        canvas.drawRoundRect(rectF, radius, radius, paint);
        //DrawThumb
        float position = getColorPosition(color, startX, startY);
        rectThumb.set(position-radius, radius, position+ radius, radius*3);
        float r = radius / 3;
        thumbPaint.setColor(color);
        canvas.drawCircle(position, radius*3,r, thumbPaint);
        //canvas.drawCircle(position, radius*3,r - borderWidth * 2, thumbPaint);
        canvas.drawRoundRect(rectThumb, 5, 5, thumbPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() != MotionEvent.ACTION_UP) {
            color = getPositionColor(event.getX(), startX, startY);
            if (listeners != null) {
                listeners.onChanged(color);
            }
            invalidateLinearGradient();
            invalidate();
        }
        return true;
    }
}
