package com.lester.demo.data.repository.datasource.database;

import com.lester.demo.data.model.UserE;

public interface UserDataDbInt {
    void getFavorites(UserDataDB.Callback callback);
    void saveFavorite(UserE user, UserDataDB.Callback callback);
    void removeFavorite(long favorite_id, UserDataDB.Callback callback);
}