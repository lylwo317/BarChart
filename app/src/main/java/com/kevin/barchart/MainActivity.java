package com.kevin.barchart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.kevin.barchart.utils.StatusBarUtils;
import com.mt.chart.charts.BarChart;
import com.mt.chart.data.BarData;
import com.mt.chart.data.BarDataSet;
import com.mt.chart.data.BarEntry;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private BarChart barChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StatusBarUtils.setColor(this,getResources().getColor(R.color.colorPrimaryDark));
        barChart = (BarChart) findViewById(R.id.barchart);
        setData();

    }

    @Override
    protected void onResume() {
        super.onResume();
        barChart.getAnimator().animateY(1500);
    }

    private void setData() {

        List<String> xlabel = new ArrayList<>();
        xlabel.add("周一");
        xlabel.add("周二");
        xlabel.add("周三");
        xlabel.add("周四");
        xlabel.add("周五");
        xlabel.add("周六");
        xlabel.add("周日");

        List<BarEntry> entryList = new ArrayList<>();
        BarEntry entry = new BarEntry(1, 0);
        BarEntry entry1 = new BarEntry(0, 1);
        BarEntry entry2 = new BarEntry(3, 2);
        BarEntry entry3 = new BarEntry(4, 3);
        BarEntry entry4 = new BarEntry(5, 4);
        BarEntry entry5 = new BarEntry(6, 5);
        BarEntry entry6 = new BarEntry(7, 6);
        entryList.add(entry);
        entryList.add(entry1);
        entryList.add(entry2);
        //entryList.add(entry3);
        entryList.add(entry4);
        entryList.add(entry5);
        entryList.add(entry6);

        BarDataSet barDataSet = new BarDataSet();
        barDataSet.setEntries(entryList);
        barDataSet.setXLabels(xlabel);

        BarData barData = new BarData();
        barData.setBarDataSets(barDataSet);
        barChart.setData(barData);
    }
}
