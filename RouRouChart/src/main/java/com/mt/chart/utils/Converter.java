package com.mt.chart.utils;

import android.graphics.Matrix;

/**
 * Convert the values from Data to the pixel pointer on screen.
 * Created by kevin on 2/28/16.
 */
public class Converter {

    private Matrix mMatrixValue = new Matrix();

    private Matrix mMatrixOffset = new Matrix();


    public void prepareMatrixValueToPx(float mXChartMin,ViewportHandler viewportHandler,float mDeltaX, float mDeltaY) {

        float scaleX = viewportHandler.contentWidth() / mDeltaX;
        float scaleY = viewportHandler.contentHigh() / mDeltaY;

        mMatrixValue.reset();
        mMatrixValue.postTranslate(-mXChartMin, 0);
        mMatrixValue.postScale(scaleX, -scaleY);
    }

    public void prepareMatrixOffset(ViewportHandler viewportHandler) {

        mMatrixOffset.reset();
        mMatrixOffset.postTranslate(viewportHandler.getOffsetLeft(), viewportHandler.getChartHigh()-viewportHandler.getOffsetBottom());
    }

    public float[] valueToPx(float[] buffer) {
        mMatrixValue.mapPoints(buffer);
        mMatrixOffset.mapPoints(buffer);
        return buffer;
    }


}
