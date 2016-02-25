package com.mt.chart.buffer;

import com.mt.chart.data.DataSet;
import com.mt.chart.data.Entry;

import java.util.List;

/**
 * This class to boost performance while drawing.
 * @param <T>
 */
public abstract class AbstractBuffer<T> {

    protected float[] buffers;

    public AbstractBuffer(int capacity) {
        buffers = new float[capacity];
    }

    public abstract void fill(T dataSet);

    public float[] getBuffers() {
        return buffers;
    }
}
