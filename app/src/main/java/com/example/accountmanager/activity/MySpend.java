package com.example.accountmanager.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.accountmanager.Dao.SpendDao;
import com.example.accountmanager.Entity.SpendST;
import com.example.accountmanager.PieChartManagger;
import com.example.accountmanager.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MySpend extends AppCompatActivity {

    PieChart pie_chart2;
    private Button mysp_cancel;
    private ListView listView;
    private SpendDao spendDao;
    private List<SpendST> list;
    private SpendST spendtext;
    private Map map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_spend);
        pie_chart2 = findViewById(R.id.myspend_pie);
        listView = (ListView) findViewById(R.id.myin_record);
        spendDao = new SpendDao(MySpend.this);
        map = spendDao.groupby();
        percent(map);
        list = spendDao.output();
        String[] data = new String[list.size()];
        int[] id = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            spendtext = list.get(i);
            data[i] = "Amount: " + spendtext.getMoney() + " Date: " + spendtext.getTime() + " Time: " + spendtext.getType() + " Type: " + spendtext.getAddress() + "Comment: " + spendtext.getMark();
            id[i] = spendtext.getid();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(MySpend.this, android.R.layout.simple_list_item_1, data);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MySpend.this, Modify.class);
                Bundle bundle = new Bundle();
                bundle.putString("type", "myspend");
                bundle.putSerializable("spendST", list.get(i));
                bundle.putInt("id", id[i]);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }
        });
        mysp_cancel = (Button) findViewById(R.id.mysp_cancel);
        mysp_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void show_pie_chart_2(float cyf, float shf, float xcf, float jck, float other) {
        List<PieEntry> yvals = new ArrayList<>();
        yvals.add(new PieEntry(cyf, "Eatery Expenses"));
        yvals.add(new PieEntry(shf, "Dairy Use"));
        yvals.add(new PieEntry(xcf, "Transportation"));
        yvals.add(new PieEntry(jck, "Loan out"));
        yvals.add(new PieEntry(other, "Other"));

        List<Integer> colors = new ArrayList<>();
        colors.add(Color.parseColor("#6785f2"));
        colors.add(Color.parseColor("#675cf2"));
        colors.add(Color.parseColor("#496cef"));
        colors.add(Color.parseColor("#aa63fa"));
        colors.add(Color.parseColor("#58a9f5"));
        PieChartManagger pieChartManagger = new PieChartManagger(pie_chart2);
        pieChartManagger.showSolidPieChart(yvals, colors);
    }

    private void percent(Map map) {
        float sum = 0.0f;
        sum = (float) map.get("Eatery Expenses") + (float) map.get("Dairy Use") + (float) map.get("Transportation") + (float) map.get("Loan out") + (float) map.get("Other");
        if (sum == 0) {
            show_pie_chart_2(1.0f, 1.0f, 1.0f, 1.0f, 1.0f);
        } else {
            show_pie_chart_2((float) map.get("Eatery Expenses"), (float) map.get("Dairy Use"), (float) map.get("Transportation"), (float) map.get("Loan out"), (float) map.get("Other"));
        }
    }
}