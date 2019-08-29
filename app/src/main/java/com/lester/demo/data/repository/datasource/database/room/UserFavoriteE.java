package com.lester.demo.data.repository.datasource.database.room;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "favorite_table")
public class UserFavoriteE {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "favorite_id")
    private long favorite_id;

    @ColumnInfo(name = "favorite_name")
    private String favorite_name;
    @ColumnInfo(name = "favorite_email")
    private String favorite_email;
    @ColumnInfo(name = "favorite_phone")
    private String favorite_phone;
    @ColumnInfo(name = "favorite_cell")
    private String favorite_cell;

    @ColumnInfo(name = "location_address")
    private String location_address;
    @ColumnInfo(name = "location_latitude")
    private double location_latitude;
    @ColumnInfo(name = "location_longitude")
    private double location_longitude;

    @ColumnInfo(name = "favorite_age")
    private int favorite_age;
    @ColumnInfo(name = "identity_name")
    private String identity_name;
    @ColumnInfo(name = "identity_value")
    private String identity_value;

    @ColumnInfo(name = "image_large")
    private String image_large;
    @ColumnInfo(name = "image_medium")
    private String image_medium;
    @ColumnInfo(name = "image_thumbnail")
    private String image_thumbnail;

    public UserFavoriteE(String favorite_name, String favorite_email, String favorite_phone, String favorite_cell, String location_address, double location_latitude, double location_longitude, int favorite_age, String identity_name, String identity_value, String image_large, String image_medium, String image_thumbnail) {
        this.favorite_name = favorite_name;
        this.favorite_email = favorite_email;
        this.favorite_phone = favorite_phone;
        this.favorite_cell = favorite_cell;
        this.location_address = location_address;
        this.location_latitude = location_latitude;
        this.location_longitude = location_longitude;
        this.favorite_age = favorite_age;
        this.identity_name = identity_name;
        this.identity_value = identity_value;
        this.image_large = image_large;
        this.image_medium = image_medium;
        this.image_thumbnail = image_thumbnail;
    }

    public void setFavorite_id(long favorite_id) {
        this.favorite_id = favorite_id;
    }

    public long getFavorite_id() {
        return favorite_id;
    }

    public String getFavorite_name() {
        return favorite_name;
    }

    public String getFavorite_email() {
        return favorite_email;
    }

    public String getFavorite_phone() {
        return favorite_phone;
    }

    public String getFavorite_cell() {
        return favorite_cell;
    }

    public String getLocation_address() {
        return location_address;
    }

    public double getLocation_latitude() {
        return location_latitude;
    }

    public double getLocation_longitude() {
        return location_longitude;
    }

    public int getFavorite_age() {
        return favorite_age;
    }

    public String getIdentity_name() {
        return identity_name;
    }

    public String getIdentity_value() {
        return identity_value;
    }

    public String getImage_large() {
        return image_large;
    }

    public String getImage_medium() {
        return image_medium;
    }

    public String getImage_thumbnail() {
        return image_thumbnail;
    }
}