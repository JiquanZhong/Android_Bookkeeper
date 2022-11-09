package com.example.accountmanager.Dao;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.accountmanager.Entity.FlagST;

import java.util.ArrayList;
import java.util.List;

public class FlagDao {
    private DataHelper helper;
    private SQLiteDatabase db;

    public FlagDao(Context context) {
        helper = new DataHelper(context);
        db = helper.getWritableDatabase();
    }

    /**
     * add note message
     *
     * @param tb_flag
     */
    public void add(FlagST tb_flag) {
        db.execSQL("insert into tb_flag (_id,flag) values (?,?)", new Object[]{
                tb_flag.getid(), tb_flag.getFlag()});// Perform the add note action
    }

    /**
     * update note
     *
     * @param tb_flag
     */
    public void update(FlagST tb_flag) {
        db.execSQL("update tb_flag set flag = ? where _id = ?", new Object[]{
                tb_flag.getFlag(), tb_flag.getid()});// Perform an operation to modify the note information
    }


    /**
     * Gets the total number of records
     *
     * @return
     */
    public long getCount() {
        Cursor cursor = db.rawQuery("select count(_id) from tb_flag", null);// 获取便签信息的记录数
        if (cursor.moveToNext()) {// Determine if there is data in the Cursor
            return cursor.getLong(0);// return the total number of records
        }
        cursor.close();// close cursor
        return 0;// if no data, return 0
    }

    /**
     * Gets the maximum number of the note
     *
     * @return
     */
    public int getMaxId() {
        Cursor cursor = db.rawQuery("select max(_id) from tb_flag", null);
        while (cursor.moveToLast()) {// Access the last data in the Cursor
            return cursor.getInt(0);// Gets the data that was accessed, that is, the maximum number
        }
        cursor.close();
        return 0;
    }

    public void remake() {
        db.execSQL("delete from tb_flag");//清空数据库
    }

    public void look() {//print data info
        Cursor cursor = db.query("tb_flag", null, null, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                System.out.println(cursor.getString(1));
            }
        }
    }

    @SuppressLint("Range")
    public List<FlagST> output() {
        List<FlagST> list = new ArrayList<FlagST>();//Create a list object to hold the database elements, and the data type is an entity object
        Cursor cursor = db.query("tb_flag", null, null, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndex("_id"));
                String mark = cursor.getString(cursor.getColumnIndex("flag"));
                list.add(new FlagST(id, mark));
            }
            return list;
        }
        cursor.close();
        return null;
    }
}
