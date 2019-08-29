package com.lester.demo.data.repository;

import com.lester.demo.data.model.UserE;
import com.lester.demo.data.repository.datasource.database.UserDataDB;
import com.lester.demo.data.repository.datasource.rest.UserDataRest;

public interface UserDataRepositoryInt {
    void getUserData(int page, int cant, final UserDataRest.Callback callback);
    void addFavorite(UserE user, UserDataDB.Callback callback);
    void loadFavorites(UserDataDB.Callback callback);
    void removeFavorite(long favorite_id, UserDataDB.Callback callback);
}