package com.example.accountmanager.Entity;

import java.io.Serializable;

public class SpendST implements Serializable {
    private int _id;
    private double money;
    private String time;
    private String type;
    private String address;
    private String mark;

    public SpendST() {// 默认构造函数
        super();
    }

    public SpendST(int id, double money, String time, String type,
                   String address, String mark) {
        super();
        this._id = id;
        this.money = money;
        this.time = time;
        this.type = type;
        this.address = address;
        this.mark = mark;
    }

    public int getid() {// 设置支出编号的可读属性
        return _id;
    }

    public void setid(int id) {// 设置支出编号的可写属性
        this._id = id;
    }

    public double getMoney() {// 设置支出金额的可读属性
        return money;
    }

    public void setMoney(double money) {// 设置支出金额的可写属性
        this.money = money;
    }

    public String getTime() {// 设置支出时间的可读属性
        return time;
    }

    public void setTime(String time) {// 设置支出时间的可写属性
        this.time = time;
    }

    public String getType() {// 设置支出类别的可读属性
        return type;
    }

    public void setType(String type) {// 设置支出类别的可写属性
        this.type = type;
    }

    public String getAddress() {// 设置支出地点的可读属性
        return address;
    }

    public void setAddress(String address) {// 设置支出地点的可写属性
        this.address = address;
    }

    public String getMark() {// 设置支出备注的可读属性
        return mark;
    }

    public void setMark(String mark) {// 设置支出备注的可写属性
        this.mark = mark;
    }
}
