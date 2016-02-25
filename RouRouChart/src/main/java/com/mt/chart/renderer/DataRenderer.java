package com.mt.chart.renderer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.mt.chart.utils.Utils;
import com.mt.chart.utils.ViewportHandler;

/**
 * Created by kevin on 2/28/16.
 */
public abstract class DataRenderer {

    protected ViewportHandler viewportHandler;

    protected Paint mDataPaint;
    protected Paint mValuePaint;
    protected Paint mLabelPaint;

    public DataRenderer(ViewportHandler viewportHandler) {
        this.viewportHandler = viewportHandler;

        mDataPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mDataPaint.setStyle(Paint.Style.FILL);
        //mDataPaint.setColor(Color.rgb(65, 65, 65));

        mValuePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mValuePaint.setTextAlign(Paint.Align.CENTER);
        mValuePaint.setTextSize(Utils.convertDpToPixel(20f));
        mValuePaint.setColor(Color.parseColor("#FFFFCAD5"));

        mLabelPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mLabelPaint.setTextAlign(Paint.Align.CENTER);
        mLabelPaint.setTextSize(Utils.convertDpToPixel(10f));
        mLabelPaint.setColor(Color.parseColor("#FFFFFFFF"));
    }

    public abstract void drawData(Canvas canvas);

    public abstract void drawValue(Canvas canvas);

    public abstract void drawLabels(Canvas canvas);
}
