package com.example.accountmanager.Entity;

public class FlagST {
    private int _id;// note Id
    private String flag;// note message

    public FlagST() {// 默认构造函数
        super();
    }

    // A constructor with parameter is defined to initialize the fields of the note information entity class
    public FlagST(int id, String flag) {
        super();
        this._id = id;
        this.flag = flag;
    }

    public int getid() {// 设置便签编号的可读属性
        return _id;
    }

    public void setid(int id) {// 设置便签编号的可写属性
        this._id = id;
    }

    public String getFlag() {// 设置便签信息的可读属性
        return flag;
    }

    public void setFlag(String flag) {// 设置便签信息的可写属性
        this.flag = flag;
    }
}
