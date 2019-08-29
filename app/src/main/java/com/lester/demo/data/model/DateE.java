package com.lester.demo.data.model;

import java.io.Serializable;

public class DateE implements Serializable {
    String date;
    int age;

    public String getDate() {
        return date;
    }

    public int getAge() {
        return age;
    }

    public String getFullAge() {
        return String.valueOf(age);
    }
}