package com.lester.demo.data.model;

import java.io.Serializable;

public class UserLoginE implements Serializable {
    String uuid;
    String username;

    public String getUsername() {
        return username;
    }

    public String getUuid() {
        return uuid;
    }
}