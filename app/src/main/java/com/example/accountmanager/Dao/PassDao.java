package com.example.accountmanager.Dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.accountmanager.Entity.PassST;

public class PassDao {
    private DataHelper da;
    private SQLiteDatabase db;

    public PassDao(Context context) {
        da = new DataHelper(context);
        db = da.getWritableDatabase();
    }

    public void add(PassST passST) {
//        ContentValues value=new ContentValues();
//        value.put("password",passST.getPassword());
//        db.insert("tb_pwd",null,value);
        db.execSQL("insert into" + " tb_pwd (password)" + " values ('" + passST.getPassword() + "')");
    }

    public void update(PassST passST) {
        db.execSQL("update tb_pwd set password = " + "'" + passST.getPassword() + "'");
    }

    public String find() {
        Cursor cursor = db.query("tb_pwd", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                return cursor.getString(0);
            }
        }
        return null;
    }

    public void delete() {
        db.execSQL("delete from tb_pwd");
    }


    public long getCount() {
        Cursor cursor = db.rawQuery("select count(*) from tb_pwd", null);
        if (cursor.moveToNext()) {
            return cursor.getLong(0);
        }
        cursor.close();
        return 0;
    }

}
