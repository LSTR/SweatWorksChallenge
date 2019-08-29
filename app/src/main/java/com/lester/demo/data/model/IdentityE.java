package com.lester.demo.data.model;

import java.io.Serializable;

public class IdentityE implements Serializable {
    String name;
    String value;

    public String getName() {
        if(name == null) name = "";
        if(!name.isEmpty()) name += " ";
        return name;
    }

    public String getValue() {
        if(value == null) value = "";
        return value;
    }
}