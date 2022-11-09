package com.example.accountmanager;

import android.graphics.Color;
import android.graphics.Typeface;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.List;

public class PieChartManagger {

    public PieChart pieChart;

    public PieChartManagger(PieChart pieChart) {
        this.pieChart = pieChart;
        initPieChart();
    }

    //Initialization
    private void initPieChart() {
        //  Whether to show the hole in the middle
        pieChart.setDrawHoleEnabled(false);
        pieChart.setHoleRadius(40f);//Set the size of the middle hole

        // Circle of translucent
        pieChart.setTransparentCircleRadius(30f);
        pieChart.setTransparentCircleColor(Color.WHITE); //Set the color of the semi-transparent circle
        pieChart.setTransparentCircleAlpha(125); //Set the transparency of the semi-transparent circle

        //You can add text in the middle of a pie chart
        pieChart.setDrawCenterText(false);
        pieChart.setCenterText("nation"); //Set the middle text
        pieChart.setCenterTextColor(Color.parseColor("#a1a1a1")); //Set the middle question color
        pieChart.setCenterTextSizePixels(36);
        pieChart.setCenterTextRadiusPercent(1f);
        pieChart.setCenterTextTypeface(Typeface.DEFAULT); //Styles for middle text
        pieChart.setCenterTextOffset(0, 0);


        pieChart.setRotationAngle(0);
        pieChart.setRotationEnabled(true);// You can rotate it manually
        pieChart.setUsePercentValues(true);//Displayed as a percentage
        pieChart.getDescription().setEnabled(false); // undescribe the bottom right corner

        //Whether to display a text description of each section
        pieChart.setDrawEntryLabels(false);
        pieChart.setEntryLabelColor(Color.RED); //Describes the text color
        pieChart.setEntryLabelTextSize(14);//Describes the text size
        pieChart.setEntryLabelTypeface(Typeface.DEFAULT_BOLD); //Describes the style of the text

        //The plot is offset with respect to up, down, left and right
        pieChart.setExtraOffsets(20, 8, 75, 8);
        //The icon's background color
        pieChart.setBackgroundColor(Color.TRANSPARENT);
        //Setting pieChart chart rotational resistance friction coefficient [0,1]
        pieChart.setDragDecelerationFrictionCoef(0.75f);

        //Getting the legend
        Legend legend = pieChart.getLegend();
        legend.setOrientation(Legend.LegendOrientation.VERTICAL);  //Sets the legend to display horizontally
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP); //Top
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT); //Right-justifies

        legend.setXEntrySpace(7f);//Spacing on the X-axis
        legend.setYEntrySpace(10f); //Spacing on the Y-axis
        legend.setYOffset(10f);  //The y offset of the legend
        legend.setXOffset(10f);  //The x offset of the legend
        legend.setTextColor(Color.parseColor("#a1a1a1")); //Legend text color
        legend.setTextSize(13);  //Legend text size

    }


    /**
     * Displays a solid circle
     *
     * @param yvals
     * @param colors
     */
    public void showSolidPieChart(List<PieEntry> yvals, List<Integer> colors) {
        //Data Collection
        PieDataSet dataset = new PieDataSet(yvals, "");
        //Fill the color of each area
        dataset.setColors(colors);
        //Whether to display values on the plot
        dataset.setDrawValues(true);
//        Text size
        dataset.setValueTextSize(14);
//        text color
        dataset.setValueTextColor(Color.RED);
//        text style
        dataset.setValueTypeface(Typeface.DEFAULT_BOLD);

//      When the value position is the outer edge line, it represents the first half length of the line.
        dataset.setValueLinePart1Length(0.4f);
//      When the value position is the outer edge line, it represents the length of the second half of the line.
        dataset.setValueLinePart2Length(0.4f);
//      When ValuePosits is OutsiDice, the indication offset is a percentage of the slice size
        dataset.setValueLinePart1OffsetPercentage(80f);
        //When the value position is the outer edge line, it indicates the color of the line.
        dataset.setValueLineColor(Color.parseColor("#a1a1a1"));
        //Sets the position of the Y value inside or outside the circle
        dataset.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
//        Set the gap before each strip
        dataset.setSliceSpace(0);

        //Sets the distance to vary when the pie Item is selected
        dataset.setSelectionShift(5f);
        //fill data
        PieData pieData = new PieData(dataset);
//        The data displayed in the format are percentages
        pieData.setValueFormatter(new PercentFormatter());
//        display data
        pieChart.setData(pieData);
    }


    /**
     * Display ring
     *
     * @param yvals
     * @param colors
     */
    public void showRingPieChart(List<PieEntry> yvals, List<Integer> colors) {
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleRadius(85f);//

        PieDataSet dataset = new PieDataSet(yvals, "");
        dataset.setColors(colors);
        dataset.setDrawValues(true);
        dataset.setValueTextSize(14);
        dataset.setValueTextColor(Color.RED);
        dataset.setValueTypeface(Typeface.DEFAULT_BOLD);

        dataset.setValueLinePart1Length(0.4f);
        dataset.setValueLinePart2Length(0.4f);
        dataset.setValueLinePart1OffsetPercentage(80f);
        dataset.setValueLineColor(Color.parseColor("#a1a1a1"));
        dataset.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        dataset.setSliceSpace(0);

        dataset.setSelectionShift(5f);
        PieData pieData = new PieData(dataset);
        pieData.setValueFormatter(new PercentFormatter());
        pieChart.setData(pieData);
    }
}
