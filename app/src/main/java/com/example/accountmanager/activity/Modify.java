package com.example.accountmanager.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.accountmanager.Dao.IncomeDao;
import com.example.accountmanager.Dao.SpendDao;
import com.example.accountmanager.Entity.IncomeST;
import com.example.accountmanager.Entity.SpendST;
import com.example.accountmanager.R;

import java.util.Calendar;

public class Modify extends AppCompatActivity {
    private Button modify_submit;
    private Button modify_delete;
    private Button modify_cancel;
    private Button modify_groupbutton;
    private EditText modify_number;
    private EditText modify_date;
    private EditText modify_time;
    private EditText modify_beizhu;

    private String modify_class = "";
    private String dateselect = "";
    private String timeselect = "";
    private Double numbergold;
    private String mark;

    private IncomeDao incomeDao;
    private SpendDao spendDao;
    private IncomeST incomeST;
    private SpendST spendST;

    private int index = 0;
    private String[] out_group = {"Eatery Expenses", "Dairy Use", "Transportation", "Loan out", "Other"};
    private String[] add_group = {"Salary", "Loan", "Financing income", "Other"};
    private int mYear;
    private int mMonth;
    private int mDay;
    private String data;
    private String time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);
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
        incomeDao = new IncomeDao(Modify.this);
        spendDao = new SpendDao(Modify.this);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        modify_date = (EditText) findViewById(R.id.modify_date);
        modify_time = (EditText) findViewById(R.id.modify_time);
        modify_number = (EditText) findViewById(R.id.modify_number);
        modify_beizhu = (EditText) findViewById(R.id.modify_beizhu);
        modify_groupbutton = (Button) findViewById(R.id.modify_group);

        if (bundle.getString("type").equals("income")) {
            incomeST = (IncomeST) bundle.getSerializable("incomeST");
            modify_date.setText(incomeST.getTime());
            modify_time.setText(incomeST.getType());
            modify_number.setText(Double.toString(incomeST.getMoney()));
            modify_beizhu.setText(incomeST.getMark());
            modify_groupbutton.setText(incomeST.getDate());
        } else if (bundle.getString("type").equals("myspend")) {
            spendST = (SpendST) bundle.getSerializable("spendST");
            modify_date.setText(spendST.getTime());
            modify_time.setText(spendST.getType());
            modify_number.setText(Double.toString(spendST.getMoney()));
            modify_beizhu.setText(spendST.getMark());
            modify_groupbutton.setText(spendST.getAddress());
        }

        modify_submit = (Button) findViewById(R.id.modify_submit);
        modify_delete = (Button) findViewById(R.id.modify_delete);
        modify_cancel = (Button) findViewById(R.id.modify_cancel);
        modify_groupbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Modify.this);
                if (bundle.getString("type").equals("income")) {
                    builder.setIcon(R.mipmap.ic_launcher).setTitle("Select type").setSingleChoiceItems(add_group, 0, new DialogInterface.OnClickListener() {
                        @Override
                        //setMessage sets simple text, setSingChoiceItems sets radio buttons, and setMultiChoiceItems sets multiple buttons
                        public void onClick(DialogInterface dialogInterface, int i) {
                            index = i;
                            modify_class = add_group[i];
                        }
                    }).setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            modify_groupbutton.setText(modify_class);
                            dialogInterface.dismiss();
                        }
                    }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                } else if (bundle.getString("type").equals("myspend")) {
                    builder.setIcon(R.mipmap.ic_launcher).setTitle("Select type").setSingleChoiceItems(out_group, 0, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            index = i;
                            modify_class = out_group[i];
                        }
                    }).setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            modify_groupbutton.setText(modify_class);
                            dialogInterface.dismiss();
                        }
                    }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                }
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        modify_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(Modify.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                mYear[0] = year;
                                mMonth[0] = month;
                                mDay[0] = dayOfMonth;
                                data = year + "-" + (month + 1) + "-" + dayOfMonth;
                                modify_date.setText(data);
                                dateselect = data;
                            }
                        },
                        mYear[0], mMonth[0], mDay[0]);
                datePickerDialog.setTitle("Select Date");
                datePickerDialog.show();
            }
        });
        modify_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(Modify.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int hourread, int minuteread) {
                                hour[0] = hourread;
                                minute[0] = minuteread;
                                time = hourread + "h:" + minuteread + "min";
                                modify_time.setText(time);
                                timeselect = time;
                            }
                        }, hour[0], minute[0], true);
                timePickerDialog.setTitle("Select time");
                timePickerDialog.show();
            }
        });
        modify_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mark = modify_beizhu.getText().toString();
                String numberString = modify_number.getText().toString();
                numbergold = Double.parseDouble(numberString);
                if (bundle.getString("type").equals("income")) {
                    incomeST.setMark(mark);
                    incomeST.setDate(modify_class);
                    incomeST.setMoney(numbergold);
                    incomeST.setType(timeselect);
                    incomeST.setTime(dateselect);
                    incomeDao.update(incomeST);
                    Toast.makeText(Modify.this, "Successfully modified", Toast.LENGTH_SHORT).show();
                    finish();
                } else if (bundle.getString("type").equals("myspend")) {
                    spendST.setMark(mark);
                    spendST.setTime(dateselect);
                    spendST.setMoney(numbergold);
                    spendST.setType(timeselect);
                    spendST.setAddress(modify_class);
                    spendDao.update(spendST);
                    Toast.makeText(Modify.this, "Successfully modified", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
        modify_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bundle.getString("type").equals("income")) {

                    incomeDao.delete(bundle.getInt("id"));
                    Toast.makeText(Modify.this, "Successfully deleted", Toast.LENGTH_SHORT).show();
                    finish();
                } else if (bundle.getString("type").equals("myspend")) {

                    spendDao.delete(bundle.getInt("id"));
                    Toast.makeText(Modify.this, "Successfully deleted", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
        modify_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}