package com.mt.chart.renderer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.mt.chart.buffer.BarBuffer;
import com.mt.chart.charts.BarChart;
import com.mt.chart.data.BarData;
import com.mt.chart.data.BarDataSet;
import com.mt.chart.data.BarEntry;
import com.mt.chart.utils.Converter;
import com.mt.chart.utils.Utils;
import com.mt.chart.utils.ViewportHandler;

import java.util.List;

/**
 * Created by XieJiaHua on 2016/2/29.
 * Company:PeanutRun
 * Email:lylwo317@gmail.com
 */
public class BarDataRenderer extends DataRenderer {

    private BarBuffer barBuffer;

    private BarChart barChart;

    private BarData barData;

    private int barColor=0xFF888888;

    private int valueColor=Color.BLACK;

    private int xLabelColor=Color.BLACK;

    private float[] valuesPx;

    public BarDataRenderer(BarChart barChart, ViewportHandler viewportHandler) {
        super(viewportHandler);
        this.barChart = barChart;
    }

    public void initBuffer(BarData barData) {
        this.barData = barData;
        if (barData.getBarDataSet() != null) {
            BarDataSet barDataSet = barData.getBarDataSet();
            if (barDataSet.getEntries() != null && barDataSet.getEntries().size() != 0) {
                barBuffer = new BarBuffer(barDataSet.getEntries().size() * 4);
            }
        }
    }

    @Override
    public void drawData(Canvas canvas) {
        Converter converter = barChart.getConverter();
        barBuffer.setPhaseY(barChart.getAnimator().getPhaseY());
        barBuffer.fill(barData.getBarDataSet());
        converter.valueToPx(barBuffer.getBuffers());

        float[] buffer = barBuffer.getBuffers();

        for (int i = 0; i < buffer.length; i+=4) {
            LinearGradient linearGradient = new LinearGradient(buffer[i], buffer[i + 3], buffer[i], buffer[i + 1], Color.parseColor("#00FFFFFF"),Color.parseColor("#A6FFFFFF"), Shader.TileMode.CLAMP);
            mDataPaint.setShader(linearGradient);

            float radius = (buffer[i+2] - buffer[i]) / 2;

            Path path = RoundedRect(buffer[i], buffer[i + 1], buffer[i + 2], buffer[i + 3], radius, radius, true, true, false, false);
            canvas.drawPath(path, mDataPaint);
            //
            // canvas.drawRect(buffer[i], buffer[i + 1], buffer[i + 2], buffer[i + 3], mDataPaint);
        }
    }

    @Override
    public void drawValue(Canvas canvas) {
        valuesPx = new float[barData.getBarDataSet().getEntries().size()*2];

        //List<String> xLabels = barData.getBarDataSet().getXLabels();

        float[] buffer = barBuffer.getBuffers();
        List<BarEntry> barEntryList = barData.getBarDataSet().getEntries();


        for (int i = 0; i < buffer.length; i+=4) {

            float x = (buffer[i]+buffer[i+2])/2;
            float y = buffer[i+1];

            Paint.FontMetricsInt fontMetrics = mValuePaint.getFontMetricsInt();
            // 转载请注明出处：http://blog.csdn.net/hursing
            int baseline = (int) (y  - (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.top);

            canvas.drawText(String.valueOf((int)(barEntryList.get(i / 4).getValue()*barChart.getAnimator().getPhaseY())), x, baseline - Utils.convertDpToPixel(19f), mValuePaint);
        }
    }

    @Override
    public void drawLabels(Canvas canvas) {
        List<String> xLabels = barData.getBarDataSet().getXLabels();

        float[] buffer = new float[xLabels.size()*2];

        int index = 0;

        for (int i = 0; i < xLabels.size(); i++) {
            buffer[index++]=i;
            buffer[index++]=0;
        }

        Converter converter = barChart.getConverter();
        converter.valueToPx(buffer);

        for (int i = 0; i < buffer.length; i+=2) {
            float x = buffer[i];
            float y = buffer[i+1];
            Paint.FontMetricsInt fontMetrics = mValuePaint.getFontMetricsInt();
            // 转载请注明出处：http://blog.csdn.net/hursing
            int baseline = (int) (y - (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.top);
            canvas.drawText(xLabels.get(i/2),x,baseline + Utils.convertDpToPixel(10f),mLabelPaint);
        }

    }

    public static Path RoundedRect(
            float left, float top, float right, float bottom, float rx, float ry,
            boolean tl, boolean tr, boolean br, boolean bl
    ){
        Path path = new Path();
        if (rx < 0) rx = 0;
        if (ry < 0) ry = 0;
        float width = right - left;
        float height = bottom - top;
        if (rx > width / 2) rx = width / 2;
        if (ry > height / 2) ry = height / 2;
        float widthMinusCorners = (width - (2 * rx));
        float heightMinusCorners = (height - (2 * ry));

        path.moveTo(right, top + ry);
        if (tr)
            path.rQuadTo(0, -ry, -rx, -ry);//top-right corner
        else{
            path.rLineTo(0, -ry);
            path.rLineTo(-rx,0);
        }
        path.rLineTo(-widthMinusCorners, 0);
        if (tl)
            path.rQuadTo(-rx, 0, -rx, ry); //top-left corner
        else{
            path.rLineTo(-rx, 0);
            path.rLineTo(0,ry);
        }
        path.rLineTo(0, heightMinusCorners);

        if (bl)
            path.rQuadTo(0, ry, rx, ry);//bottom-left corner
        else{
            path.rLineTo(0, ry);
            path.rLineTo(rx,0);
        }

        path.rLineTo(widthMinusCorners, 0);
        if (br)
            path.rQuadTo(rx, 0, rx, -ry); //bottom-right corner
        else{
            path.rLineTo(rx,0);
            path.rLineTo(0, -ry);
        }

        path.rLineTo(0, -heightMinusCorners);

        path.close();//Given close, last lineto can be removed.

        return path;
    }
}
