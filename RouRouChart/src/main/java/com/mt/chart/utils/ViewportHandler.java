package com.mt.chart.utils;

import android.graphics.RectF;

/**
 * Control the range of content in the chart.
 */
public class ViewportHandler {

    private float mChartHigh;
    private float mChartWidth;
    private RectF mContentRect = new RectF();

    public void setChartDimension(float chartWidth,float chartHigh){
        mChartWidth = chartWidth;
        mChartHigh = chartHigh;
    }

    public void setContentBoundOffset(float offsetLeft, float offsetTop, float offsetRight, float offsetBottom) {
        mContentRect.set(offsetLeft, offsetTop, mChartWidth - offsetRight, mChartHigh - offsetBottom);
    }

    public float getOffsetLeft() {
        return mContentRect.left;
    }

    public float getOffsetTop() {
        return mContentRect.top;
    }

    public float getOffsetRight() {
        return mChartWidth - mContentRect.right;
    }

    public float getOffsetBottom() {
        return mChartHigh - mContentRect.bottom;
    }

    public float contentLeft() {
        return mContentRect.left;
    }

    public float contentRight() {
        return mContentRect.right;
    }

    public float contentTop() {
        return mContentRect.top;
    }

    public float contentBottom() {
        return mContentRect.bottom;
    }

    public float contentWidth() {
        return mContentRect.width();
    }

    public float contentHigh() {
        return mContentRect.height();
    }

    public float getChartHigh() {
        return mChartHigh;
    }

    public float getChartWidth() {
        return mChartWidth;
    }
}
