package com.mt.chart.data;

/**
 * Created by XieJiaHua on 2016/2/25.
 * Company:PeanutRun
 * Email:lylwo317@gmail.com
 */
public class BarData extends ChartData<BarDataSet> {

    private BarDataSet barDataSets;

    public BarDataSet getBarDataSet() {
        return barDataSets;
    }

    public void setBarDataSets(BarDataSet barDataSets) {
        this.barDataSets = barDataSets;
    }
}
