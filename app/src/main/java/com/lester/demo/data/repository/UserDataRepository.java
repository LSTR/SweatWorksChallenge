package com.lester.demo.data.repository;

import com.lester.demo.data.model.UserE;
import com.lester.demo.data.repository.datasource.database.UserDataDB;
import com.lester.demo.data.repository.datasource.database.UserDataDbInt;
import com.lester.demo.data.repository.datasource.rest.UserDataRest;
import com.lester.demo.data.repository.datasource.rest.UserDataRestInt;

public class UserDataRepository implements UserDataRepositoryInt {

    private final UserDataRestInt resDataSource;
    private final UserDataDbInt dbDdataSource;

    public UserDataRepository(UserDataRestInt resDataSource, UserDataDbInt dbDdataSource){
        this.resDataSource = resDataSource;
        this.dbDdataSource = dbDdataSource;
    }

    @Override
    public void getUserData(int page, int cant, final UserDataRest.Callback callback) {
        resDataSource.getUserData(page, cant, callback);
    }

    @Override
    public void loadFavorites(UserDataDB.Callback callback) {
        dbDdataSource.getFavorites(callback);
    }

    @Override
    public void addFavorite(UserE user, UserDataDB.Callback callback) {
        dbDdataSource.saveFavorite(user, callback);
    }

    @Override
    public void removeFavorite(long favorite_id, UserDataDB.Callback callback) {
        dbDdataSource.removeFavorite(favorite_id, callback);
    }
}