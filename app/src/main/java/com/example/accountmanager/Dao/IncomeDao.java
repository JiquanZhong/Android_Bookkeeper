package com.example.accountmanager.Dao;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.accountmanager.Entity.IncomeST;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IncomeDao {
    private DataHelper helper;
    private SQLiteDatabase db;

    public IncomeDao(Context context) {
        helper = new DataHelper(context);//
    }

    /**
     * add income record
     *
     * @param tb_inaccount
     */
    public void add(IncomeST tb_inaccount) {
        // perform income insertion
        db.execSQL(
                "insert into tb_inaccount (_id,money,time,type,handler,mark) "
                        + "values (?,?,?,?,?,?)",
                new Object[]{tb_inaccount.getid(), tb_inaccount.getMoney(),
                        tb_inaccount.getTime(), tb_inaccount.getType(),
                        tb_inaccount.getDate(), tb_inaccount.getMark()});
    }

    /**
     * update income
     *
     * @param tb_inaccount
     */
    public void update(IncomeST tb_inaccount) {
//		db = helper.getWritableDatabase();
        // modify income info
        db.execSQL(
                "update tb_inaccount set money = ?,time = ?,type = ?,handler = ?,"
                        + "mark = ? where _id = ?",
                new Object[]{tb_inaccount.getMoney(), tb_inaccount.getTime(),
                        tb_inaccount.getType(), tb_inaccount.getDate(),
                        tb_inaccount.getMark(), tb_inaccount.getid()});
    }


    public long getCount() {//get total records number
//		db = helper.getWritableDatabase();//
        Cursor cursor = db
                .rawQuery("select count(_id) from tb_inaccount", null);
        if (cursor.moveToNext()) {
            return cursor.getLong(0);
        }
        cursor.close();// close cursor
        return 0;
    }

    public int getMaxId() {
//		db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select max(_id) from tb_inaccount", null);
        while (cursor.moveToLast()) {
            return cursor.getInt(0);
        }
        cursor.close();
        return 0;
    }

    public void look() {
        Cursor cursor = db.query("tb_inaccount", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                System.out.println(cursor.getString(0));
            }
        }
    }

    public void remake() {
        db.execSQL("delete from tb_inaccount");
    }

    @SuppressLint("Range")
    public List<IncomeST> output() {
        List<IncomeST> list = new ArrayList<IncomeST>();
        Cursor cursor = db.query("tb_inaccount", null, null, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {

                int id = cursor.getInt(cursor.getColumnIndex("_id"));
                Double money = cursor.getDouble(cursor.getColumnIndex("money"));
                String date = cursor.getString(cursor.getColumnIndex("time"));
                String time = cursor.getString(cursor.getColumnIndex("type"));
                String type = cursor.getString(cursor.getColumnIndex("handler"));
                String mark = cursor.getString(cursor.getColumnIndex("mark"));
                list.add(new IncomeST(id, money, date, time, type, mark));
            }
            return list;
        }
        cursor.close();
        return null;
    }

    @SuppressLint("Range")
    public Map groupby() {
        Map percentage = new HashMap<>();//Create a map object that stores the total amount of each income
        percentage.put("Salary", 0.0f);
        percentage.put("Loan", 0.0f);
        percentage.put("Financing income", 0.0f);
        percentage.put("Other", 0.0f);
        float sum = 0;
//        Cursor cursor=db.query("tb_inaccount","sum(money)","handler",null,null,null,nu)
        Cursor cursor = db.rawQuery("select handler,sum(money) from tb_inaccount group by handler", null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String key = cursor.getString(0);
                Float value = cursor.getFloat(1);
                percentage.put(key, value);//Updating map values
            }
        }
        cursor.close();
        return percentage;

    }

    public void delete(int id) {//delete data by id
        db.execSQL("delete from tb_inaccount where _id=?", new Object[]{id});
    }


}
