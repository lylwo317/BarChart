package com.mt.chart.data;

/**
 * Created by XieJiaHua on 2016/2/29.
 * Company:PeanutRun
 * Email:lylwo317@gmail.com
 */
public class ChartData<T extends DataSet> {

    private T mDataSet;

    public void setDataSet(T dataSet) {
        mDataSet = dataSet;
    }

}
