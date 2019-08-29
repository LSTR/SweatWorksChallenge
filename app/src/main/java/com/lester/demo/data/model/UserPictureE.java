package com.lester.demo.data.model;

import java.io.Serializable;

public class UserPictureE implements Serializable {
    String large;
    String medium;
    String thumbnail;

    public String getLarge() {
        return large;
    }

    public String getMedium() {
        return medium;
    }

    public String getThumbnail() {
        return thumbnail;
    }
}