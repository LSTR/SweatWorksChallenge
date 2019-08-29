package com.lester.demo.data.model;

import java.io.Serializable;

public class UserE implements Serializable {
    String gender;
    UserTitleE name;
    UserLocationE location;
    UserLoginE login;
    String email;
    DateE dob;
    DateE registered;
    String phone;
    String cell;
    IdentityE id;
    UserPictureE picture;
    String nat;

    boolean isFavorite = false;
    private long favorite_id;

    public UserE(){}

    public String getGender() {
        return gender;
    }

    public UserTitleE getName() {
        return name;
    }

    public UserLocationE getLocation() {
        return location;
    }

    public String getEmail() {
        return email;
    }

    public DateE getDob() {
        return dob;
    }

    public DateE getRegistered() {
        return registered;
    }

    public String getPhone() {
        return phone;
    }

    public String getCell() {
        return cell;
    }

    public IdentityE getId() {
        return id;
    }

    public UserPictureE getPicture() {
        return picture;
    }

    public String getNat() {
        return nat;
    }

    public UserLoginE getLogin() {
        return login;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public long getFavorite_id() {
        return favorite_id;
    }
}