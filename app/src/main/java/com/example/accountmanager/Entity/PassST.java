package com.example.accountmanager.Entity;

public class PassST {
    private String password;

    public PassST() {// 默认构造函数
        super();
    }

    public PassST(String password) {
        super();
        this.password = password;
    }

    public String getPassword() {// 定义密码的可读属性
        return password;
    }

    public void setPassword(String password) {// 定义密码的可写属性
        this.password = password;
    }
}
