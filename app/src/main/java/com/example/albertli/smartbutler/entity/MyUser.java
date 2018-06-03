package com.example.albertli.smartbutler.entity;

import cn.bmob.v3.BmobUser;

/**
 * Project Name: SmartButler
 * Details: User
 * Created by albert.li on 2018/5/26.*/



public class MyUser extends BmobUser {
    private int age;
    private Boolean sex;
    private String desc;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
