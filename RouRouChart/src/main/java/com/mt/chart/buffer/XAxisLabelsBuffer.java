package com.mt.chart.buffer;

import java.util.List;

/**
 * Created by XieJiaHua on 2016/3/1.
 * Company:PeanutRun
 * Email:lylwo317@gmail.com
 */
public class XAxisLabelsBuffer extends AbstractBuffer<List<String>> {


    private int index=0;

    public XAxisLabelsBuffer(int capacity) {
        super(capacity);
    }


    private void addToBuffer(float x, float y) {
        buffers[index++]=x;
        buffers[index++]=y;
    }


    @Override
    public void fill(List<String> dataSet) {

        for (int i = 0; i < dataSet.size(); i++) {
            //addToBuffer(i,);
        }
    }
}
