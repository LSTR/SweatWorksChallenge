package com.lester.demo.data.model;

import java.util.List;

public class UserDataResponse {
    List<UserE> results;
    InfoResponse info;

    public List<UserE> getResults() {
        return results;
    }

    public InfoResponse getInfo() {
        return info;
    }
}