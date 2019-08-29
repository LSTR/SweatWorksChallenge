package com.lester.demo.data.repository.datasource.rest;

public interface UserDataRestInt {
    void getUserData(int page, int results, final UserDataRest.Callback callback);
}