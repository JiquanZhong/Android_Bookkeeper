package com.example.accountmanager.Dao;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.accountmanager.Entity.SpendST;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpendDao {
    private DataHelper helper;
    private SQLiteDatabase db;

    public SpendDao(Context context) {
        helper = new DataHelper(context);
        db = helper.getWritableDatabase();
    }

    /**
     * add expenditure record
     *
     * @param tb_outaccount
     */
    public void add(SpendST tb_outaccount) {
        db.execSQL(
                "insert into tb_outaccount (_id,money,time,type,address,mark) values (?,?,?,?,?,?)",
                new Object[]{tb_outaccount.getid(), tb_outaccount.getMoney(),
                        tb_outaccount.getTime(), tb_outaccount.getType(),
                        tb_outaccount.getAddress(), tb_outaccount.getMark()});
    }

    /**
     * update expenditure record
     *
     * @param tb_outaccount
     */
    public void update(SpendST tb_outaccount) {
//		db = helper.getWritableDatabase();
        db.execSQL(
                "update tb_outaccount set money = ?,time = ?,type = ?,address = ?,mark = ? where _id = ?",
                new Object[]{tb_outaccount.getMoney(),
                        tb_outaccount.getTime(), tb_outaccount.getType(),
                        tb_outaccount.getAddress(), tb_outaccount.getMark(),
                        tb_outaccount.getid()});
    }


    /**
     * get records number
     *
     * @return
     */
    public long getCount() {
//		db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select count(_id) from tb_outaccount",
                null);
        if (cursor.moveToNext()) {
            return cursor.getLong(0);
        }
        cursor.close();
        return 0;
    }


    public int getMaxId() {
        Cursor cursor = db.rawQuery("select max(_id) from tb_outaccount", null);
        while (cursor.moveToLast()) {
            return cursor.getInt(0);
        }
        cursor.close();
        return 0;
    }

    public void look() {
        Cursor cursor = db.query("tb_outaccount", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                System.out.println(cursor.getString(0));
            }
        }
    }

    public void remake() {
        db.execSQL("delete from tb_outaccount");
    }

    @SuppressLint("Range")
    public List<SpendST> output() {
        List<SpendST> list = new ArrayList<SpendST>();
        Cursor cursor = db.query("tb_outaccount", null, null, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {

                int id = cursor.getInt(cursor.getColumnIndex("_id"));
                Double money = cursor.getDouble(cursor.getColumnIndex("money"));
                String date = cursor.getString(cursor.getColumnIndex("time"));
                String time = cursor.getString(cursor.getColumnIndex("type"));
                String type = cursor.getString(cursor.getColumnIndex("address"));
                String mark = cursor.getString(cursor.getColumnIndex("mark"));
                list.add(new SpendST(id, money, date, time, type, mark));
            }
            return list;
        }
        cursor.close();
        return null;
    }

    @SuppressLint("Range")
    public Map groupby() {
        Map percentage = new HashMap<>();
        percentage.put("Eatery Expenses", 0.0f);
        percentage.put("Dairy Use", 0.0f);
        percentage.put("Transportation", 0.0f);
        percentage.put("Loan Out", 0.0f);
        percentage.put("Other", 0.0f);
        float sum = 0;
        Cursor cursor = db.rawQuery("select address,sum(money) from tb_outaccount group by address", null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String key = cursor.getString(0);
                Float value = cursor.getFloat(1);
                percentage.put(key, value);
            }
        }
        cursor.close();
        return percentage;

    }

    public void delete(int id) {
        db.execSQL("delete from tb_outaccount where _id=?", new Object[]{id});
    }
}
