package com.mt.chart.data;

/**
 * Created by XieJiaHua on 2016/2/25.
 * Company:PeanutRun
 * Email:lylwo317@gmail.com
 */
public class BarDataSet extends DataSet<BarEntry> {

    public float getmBarSpace() {
        return mBarSpace;
    }

    public void setmBarSpace(float mBarSpace) {
        this.mBarSpace = mBarSpace;
    }

    /**
     * BarSpace range : (0,0.5)
     */
    private float mBarSpace = 0.65f;

    private float mValueTextHeigh = 20f;

    public float getValueTextHeigh() {
        return mValueTextHeigh;
    }

    public void setValueTextHeigh(float mValueTextHeigh) {
        this.mValueTextHeigh = mValueTextHeigh;
    }
}
