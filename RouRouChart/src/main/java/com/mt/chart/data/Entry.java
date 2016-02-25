package com.mt.chart.data;

import android.os.Parcel;
import android.os.ParcelFormatException;
import android.os.Parcelable;
import android.view.inputmethod.ExtractedTextRequest;

/**
 * Created by XieJiaHua on 2016/2/25.
 * Company:PeanutRun
 * Email:lylwo317@gmail.com
 */
public class Entry implements Parcelable {

    private float mValue = 0f;

    private int mXIndex = 0;

    private Object mData = null;

    public float getValue() {
        return mValue;
    }

    public void setValue(float mValue) {
        this.mValue = mValue;
    }

    public int getIndex() {
        return mXIndex;
    }

    public void setXIndex(int mXIndex) {
        this.mXIndex = mXIndex;
    }

    public Object getData() {
        return mData;
    }

    public void setData(Object mData) {
        this.mData = mData;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Entry)) return false;

        Entry entry = (Entry) o;

        if (Float.compare(entry.getValue(), getValue()) != 0) return false;
        if (getIndex() != entry.getIndex()) return false;
        return !(getData() != null ? !getData().equals(entry.getData()) : entry.getData() != null);

    }

    @Override
    public int hashCode() {
        int result = (getValue() != +0.0f ? Float.floatToIntBits(getValue()) : 0);
        result = 31 * result + getIndex();
        result = 31 * result + (getData() != null ? getData().hashCode() : 0);
        return result;
    }
    public static final Creator<Entry> CREATOR = new Creator<Entry>() {
        public Entry createFromParcel(Parcel source) {
            return new Entry(source);
        }

        public Entry[] newArray(int size) {
            return new Entry[size];
        }
    };

    @Override
    public String toString() {
        return "Entry{" +
                "mValue=" + mValue +
                ", mXIndex=" + mXIndex +
                ", mData=" + mData +
                '}';
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(this.mValue);
        dest.writeInt(this.mXIndex);
        if (mData != null) {
            if (mData instanceof Parcelable) {
                dest.writeInt(1);
                dest.writeParcelable((Parcelable) mData, flags);
            } else {
                throw new ParcelFormatException("Cannot parcel an Entry with non-parcelable data");
            }
        } else {
            dest.writeInt(0);
        }

    }

    public Entry(float value, int xIndex) {
        mValue = value;
        mXIndex = xIndex;
    }

    public Entry(float value, int xIndex, Object data) {
        this(value, xIndex);
        mData = data;
    }

    protected Entry(Parcel in) {
        this.mValue = in.readFloat();
        this.mXIndex = in.readInt();
        if (in.readInt() == 1) {
            this.mData = in.readParcelable(Object.class.getClassLoader());
        }
    }

}
