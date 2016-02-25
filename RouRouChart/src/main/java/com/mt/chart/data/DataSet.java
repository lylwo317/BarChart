package com.mt.chart.data;

import java.util.List;

/**
 * Created by XieJiaHua on 2016/2/29.
 * Company:PeanutRun
 * Email:lylwo317@gmail.com
 */
public class DataSet<T extends Entry> {

    private List<T> entries;

    private List<String> xLabels;

    public List<String> getXLabels() {
        return xLabels;
    }

    public void setXLabels(List<String> xLabels) {
        this.xLabels = xLabels;
    }

    public void setEntries(List<T> entries) {
        this.entries = entries;
    }

    public List<T> getEntries(){
        return this.entries;
    }
}
