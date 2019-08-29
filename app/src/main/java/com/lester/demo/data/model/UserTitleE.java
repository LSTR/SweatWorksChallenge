package com.lester.demo.data.model;

import com.lester.demo.presentation.util.AppUtil;

import java.io.Serializable;

public class UserTitleE implements Serializable {
    String title;
    String first;
    String last;

    public String getTitle() {
        return title;
    }

    public String getFirst() {
        return first;
    }

    public String getLast() {
        return last;
    }

    public String getFullName() {
        return AppUtil.capitalize(title) + " " + AppUtil.capitalize(first) + " " + AppUtil.capitalize(last);
    }
}