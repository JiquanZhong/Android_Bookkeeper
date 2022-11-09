package com.example.accountmanager.Entity;

import java.io.Serializable;

public class IncomeST implements Serializable {
    private int _id;
    private double money;
    private String date;
    private String time;
    private String type;
    private String mark;

    public IncomeST() {// 默认构造函数
        super();
    }

    public IncomeST(int id, double money, String time, String type,
                    String date, String mark) {
        super();
        this._id = id;
        this.money = money;
        this.time = time;
        this.type = type;
        this.date = date;
        this.mark = mark;
    }

    public int getid() {// 设置收入编号的可读属性
        return _id;
    }

    public void setid(int id) {// 设置收入编号的可写属性
        this._id = id;
    }

    public double getMoney() {// 设置收入金额的可读属性
        return money;
    }

    public void setMoney(double money) {// 设置收入金额的可写属性
        this.money = money;
    }

    public String getTime() {// 设置收入时间的可读属性
        return time;
    }

    public void setTime(String time) {// 设置收入时间的可写属性
        this.time = time;
    }

    public String getType() {// 设置收入类别的可读属性
        return type;
    }

    public void setType(String type) {// 设置收入类别的可写属性
        this.type = type;
    }

    public String getDate() {// 设置收入付款方的可读属性
        return date;
    }

    public void setDate(String date) {// 设置收入付款方的可写属性
        this.date = date;
    }

    public String getMark() {// 设置收入备注的可读属性
        return mark;
    }

    public void setMark(String mark) {// 设置收入备注的可写属性
        this.mark = mark;
    }
}
