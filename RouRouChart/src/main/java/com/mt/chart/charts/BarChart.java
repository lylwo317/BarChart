package com.mt.chart.charts;

import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.util.AttributeSet;
import android.view.ViewGroup;

import com.mt.chart.animation.ChartAnimator;
import com.mt.chart.data.BarData;
import com.mt.chart.data.BarEntry;
import com.mt.chart.renderer.BarDataRenderer;
import com.mt.chart.utils.Converter;
import com.mt.chart.utils.ViewportHandler;
import com.mt.chart.utils.Utils;

import java.util.List;

/**
 * Created by XieJiaHua on 2016/2/25.
 * Company:PeanutRun
 * Email:lylwo317@gmail.com
 */
public class BarChart extends ViewGroup {

    private BarData mBarData;

    private BarDataRenderer mRenderer;

    private ViewportHandler viewportHandler;

    private float mXChartMin = -0.5f;

    private Converter converter;

    private float mXAxisMin=0;
    private float mXAxisMax = 1;
    private float mYAxisMin=0;
    private float mYAxisMax = 1;

    private float mDeltaX;
    private float mDeltaY;

    private float mValueTextHighPersence=0.225f;

    public ChartAnimator getAnimator() {
        return mAnimator;
    }

    private ChartAnimator mAnimator;

    public BarChart(Context context) {
        super(context);
        init();
    }

    public BarChart(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BarChart(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public BarChart(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public Converter getConverter() {
        return converter;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mBarData == null || mBarData.getBarDataSet() == null) {
            return;
        }

        List<BarEntry> barEntryListView = mBarData.getBarDataSet().getEntries();
        if (barEntryListView != null && barEntryListView.size() != 0) {
            mRenderer.drawData(canvas);

            mRenderer.drawValue(canvas);
        }

        mRenderer.drawLabels(canvas);
    }

    public void notifyDataSetChanged() {
        if (mBarData!=null && mBarData.getBarDataSet() != null) {
            mRenderer.initBuffer(mBarData);
            calMaxMin();
            calOffset();
            postInvalidate();
        }
    }

    public void calMaxMin(){
        List<BarEntry> barEntryList = mBarData.getBarDataSet().getEntries();
        if (barEntryList != null && barEntryList.size() != 0) {
            resetMinMax();
            for (BarEntry entry : barEntryList) {
                mXAxisMin = Math.min(entry.getIndex(), mXAxisMin);
                mXAxisMax = Math.max(entry.getIndex(), mXAxisMax);
                mYAxisMin = Math.min(entry.getValue(), mYAxisMin);
                mYAxisMax = Math.max(entry.getValue(), mYAxisMax);
            }
        }
    }

    private void resetMinMax(){
        mXAxisMin=0;
        mXAxisMax = 1;
        mYAxisMin=0;
        mYAxisMax = 1;
    }

    private void calOffset() {

        List<BarEntry> barEntryList = mBarData.getBarDataSet().getEntries();
        List<String> xAxisLabels = mBarData.getBarDataSet().getXLabels();

        mDeltaX = xAxisLabels.size()-1;

        mDeltaX += 1;

        mDeltaY = mYAxisMax;

        if (barEntryList != null && barEntryList.size() != 0) {
            mDeltaY = mDeltaY*(mValueTextHighPersence+1);
        }

        converter.prepareMatrixValueToPx(mXChartMin, viewportHandler, mDeltaX, mDeltaY);
        converter.prepareMatrixOffset(viewportHandler);

    }

    public void init() {

        if (isInEditMode()) { return; }

        setWillNotDraw(false);

        mAnimator = new ChartAnimator(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                postInvalidate();
            }
        });

        Utils.init(getContext());

        viewportHandler = new ViewportHandler();

        mRenderer = new BarDataRenderer(this,viewportHandler);

        converter = new Converter();
    }

    public void setData(BarData data) {
        mBarData = data;
        notifyDataSetChanged();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int size = (int) Utils.convertDpToPixel(50f);
        setMeasuredDimension(
                Math.max(getSuggestedMinimumWidth(),resolveSize(size,widthMeasureSpec)),
                Math.max(getSuggestedMinimumHeight(),resolveSize(size,heightMeasureSpec)));
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).layout(l, t, r, b);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (isInEditMode()) {
            return;
        }
        viewportHandler.setChartDimension(w,h);
        viewportHandler.setContentBoundOffset(0,Utils.convertDpToPixel(30f),0,Utils.convertDpToPixel(25f));
        notifyDataSetChanged();
    }
}
