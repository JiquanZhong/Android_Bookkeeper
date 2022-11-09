package com.example.accountmanager.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.accountmanager.Dao.SpendDao;
import com.example.accountmanager.Entity.SpendST;
import com.example.accountmanager.R;

import java.util.Calendar;

public class AddSpend extends AppCompatActivity {

    private Button addsp_submit;
    private Button addsp_cancel;
    private Button addsp_groupbutton;
    private EditText addsp_number;
    private EditText addsp_date;
    private EditText addsp_time;
    private EditText addsp_beizhu;

    private String addsp_class = "";
    private String dateselect = "";
    private String timeselect = "";
    private Double numbergold;
    private String mark;
    private int index = 0;
    private String[] add_group = {"Eatery Expenses", "Dairy Use", "Transportation", "Loan out", "Other"};
    private int mYear;
    private int mMonth;
    private int mDay;
    private String data;
    private String time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_spend);
        Calendar ca = Calendar.getInstance();
        final int[] mYear = {ca.get(Calendar.YEAR)};
        final int[] mMonth = {ca.get(Calendar.MONTH)};
        final int[] mDay = {ca.get(Calendar.DAY_OF_MONTH)};
        data = mYear[0] + "-" + (mMonth[0] + 1) + "-" + mDay[0];
        final int[] hour = {ca.get(Calendar.HOUR_OF_DAY)};
        final int[] minute = {ca.get(Calendar.MINUTE)};
        time = hour[0] + "h:" + minute[0] + "min";
        dateselect = data;
        timeselect = time;


        addsp_submit = (Button) findViewById(R.id.addsp_submit);
        addsp_cancel = (Button) findViewById(R.id.addsp_cancel);
        addsp_groupbutton = (Button) findViewById(R.id.addsp_group);
        addsp_date = (EditText) findViewById(R.id.addsp_date);
        addsp_date.setHint(data);
        addsp_time = (EditText) findViewById(R.id.addsp_time);
        addsp_time.setHint(time);
        addsp_groupbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AddSpend.this);
                builder.setIcon(R.mipmap.ic_launcher).setTitle("Select expenditure type").setSingleChoiceItems(add_group, 0, new DialogInterface.OnClickListener() {
                    @Override
                    //setMessage sets simple text, setSingChoiceItems sets radio buttons, and setMultiChoiceItems sets multiple buttons
                    public void onClick(DialogInterface dialogInterface, int i) {
                        index = i;// Get the index of the selected option
                        addsp_class = add_group[i];
                    }
                }).setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        addsp_groupbutton.setText(addsp_class);
                        dialogInterface.dismiss();
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        addsp_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddSpend.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                mYear[0] = year;
                                mMonth[0] = month;
                                mDay[0] = dayOfMonth;
                                data = year + "-" + (month + 1) + "-" + dayOfMonth;
                                addsp_date.setText(data);
                                dateselect = data;
                            }
                        },
                        mYear[0], mMonth[0], mDay[0]);
                datePickerDialog.setTitle("Select Date");
                datePickerDialog.show();
            }
        });
        addsp_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(AddSpend.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int hourread, int minuteread) {
                                hour[0] = hourread;
                                minute[0] = minuteread;
                                time = hourread + "h:" + minuteread + "min";
                                addsp_time.setText(time);
                                timeselect = time;
                            }
                        }, hour[0], minute[0], true);
                timePickerDialog.setTitle("Select time");
                timePickerDialog.show();
            }
        });
        addsp_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addsp_number = (EditText) findViewById(R.id.addsp_number);
                addsp_beizhu = (EditText) findViewById(R.id.addsp_beizhu);
                mark = addsp_beizhu.getText().toString();
                String numberString = addsp_number.getText().toString();
                if (!numberString.equals("")) {
                    numbergold = Double.parseDouble(numberString);
                    SpendDao spendDao = new SpendDao(AddSpend.this);
                    SpendST spendST = new SpendST(spendDao.getMaxId() + 1, numbergold, dateselect, timeselect, addsp_class, mark);
                    spendDao.add(spendST);
                    Toast.makeText(AddSpend.this, "Successfully added\n", Toast.LENGTH_SHORT).show();
                    finish();
                }


            }
        });
        addsp_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}