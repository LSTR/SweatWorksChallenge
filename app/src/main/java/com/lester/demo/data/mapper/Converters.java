package com.lester.demo.data.mapper;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lester.demo.data.model.UserE;
import com.lester.demo.data.repository.datasource.database.room.FavoriteE;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Converters {
    @TypeConverter
    public static FavoriteE.UserTitleE fromString1(String value) {
        Gson gson = new Gson();
        return gson.fromJson(value, FavoriteE.UserTitleE.class);
    }
    @TypeConverter
    public static String toString(FavoriteE.UserTitleE value) {
        Gson gson = new Gson();
        return gson.toJson(value);
    }

    @TypeConverter
    public static FavoriteE.UserLocationE fromString2(String value) {
        Gson gson = new Gson();
        return gson.fromJson(value, FavoriteE.UserLocationE.class);
    }
    @TypeConverter
    public static String toString(FavoriteE.UserLocationE value) {
        Gson gson = new Gson();
        return gson.toJson(value);
    }
    @TypeConverter
    public static FavoriteE.UserLocationE.CoordinateE fromString21(String value) {
        Gson gson = new Gson();
        return gson.fromJson(value, FavoriteE.UserLocationE.CoordinateE.class);
    }
    @TypeConverter
    public static String toString(FavoriteE.UserLocationE.CoordinateE value) {
        Gson gson = new Gson();
        return gson.toJson(value);
    }
    @TypeConverter
    public static FavoriteE.UserLocationE.TimeZoneE fromString22(String value) {
        Gson gson = new Gson();
        return gson.fromJson(value, FavoriteE.UserLocationE.TimeZoneE.class);
    }
    @TypeConverter
    public static String toString(FavoriteE.UserLocationE.TimeZoneE value) {
        Gson gson = new Gson();
        return gson.toJson(value);
    }

    @TypeConverter
    public static FavoriteE.UserLoginE fromString3(String value) {
        Gson gson = new Gson();
        return gson.fromJson(value, FavoriteE.UserLoginE.class);
    }
    @TypeConverter
    public static String toString(FavoriteE.UserLoginE value) {
        Gson gson = new Gson();
        return gson.toJson(value);
    }

    @TypeConverter
    public static FavoriteE.DateE fromString4(String value) {
        Gson gson = new Gson();
        return gson.fromJson(value, FavoriteE.DateE.class);
    }
    @TypeConverter
    public static String toString(FavoriteE.DateE value) {
        Gson gson = new Gson();
        return gson.toJson(value);
    }

    @TypeConverter
    public static FavoriteE.IdentityE fromString5(String value) {
        Gson gson = new Gson();
        return gson.fromJson(value, FavoriteE.IdentityE.class);
    }
    @TypeConverter
    public static String toString(FavoriteE.IdentityE value) {
        Gson gson = new Gson();
        return gson.toJson(value);
    }

    @TypeConverter
    public static FavoriteE.UserPictureE fromString6(String value) {
        Gson gson = new Gson();
        return gson.fromJson(value, FavoriteE.UserPictureE.class);
    }
    @TypeConverter
    public static String toString(FavoriteE.UserPictureE value) {
        Gson gson = new Gson();
        return gson.toJson(value);
    }


    @TypeConverter
    public static FavoriteE fromUserToFavorite(UserE user) {
        Gson gson = new Gson();
        String a = gson.toJson(user);
        FavoriteE f = gson.fromJson(a, FavoriteE.class);
        return f;
    }

    @TypeConverter
    public static UserE fromFavoriteToUser(FavoriteE user) {
        Gson gson = new Gson();
        String a = gson.toJson(user);
        UserE u = gson.fromJson(a, UserE.class);
        return u;
    }

    @TypeConverter
    public static List<UserE> fromFavoritesToUsers(List<FavoriteE> favs) {
        Gson gson = new Gson();
        String favStr = gson.toJson(favs);
        Type listType = new TypeToken<ArrayList<UserE>>() {}.getType();
        return gson.fromJson(favStr, listType);
    }
}