package com.mt.chart.buffer;

import com.mt.chart.data.BarDataSet;
import com.mt.chart.data.BarEntry;

import java.util.List;

/**
 * Created by kevin on 2/28/16.
 */
public class BarBuffer extends  AbstractBuffer<BarDataSet> {

    private int index = 0;
    protected float mBarSpace = 0f;

    public void setPhaseY(float phaseY) {
        this.phaseY = phaseY;
    }

    protected float phaseY = 1f;

    public float getBarSpace() {
        return mBarSpace;
    }

    public void setBarSpace(float mBarSpace) {
        this.mBarSpace = mBarSpace;
    }

    public BarBuffer(int capacity) {
        super(capacity);
    }

    private void addToBuffer(float left, float top, float right, float bottom) {
        buffers[index++]=left;
        buffers[index++]=top;
        buffers[index++]=right;
        buffers[index++] = bottom;
    }

    @Override
    public void fill(BarDataSet barDataSet) {
        List<BarEntry> barEntryList = barDataSet.getEntries();
        mBarSpace = barDataSet.getmBarSpace();
        float mHalfBarSpace = mBarSpace / 2;
        float barWidth = 0.5f;
        for (int i = 0; i < barEntryList.size(); i++) {
            BarEntry e = barEntryList.get(i);
            float x = e.getIndex();
            float y = e.getValue();

            float left = x - (barWidth - mHalfBarSpace);
            float right = x + (barWidth - mHalfBarSpace);
            float top = y*phaseY;
            float bottom = 0;

            addToBuffer(left, top, right, bottom);
        }

        index = 0;
    }
}
