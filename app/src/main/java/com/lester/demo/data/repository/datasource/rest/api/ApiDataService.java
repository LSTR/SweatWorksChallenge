package com.lester.demo.data.repository.datasource.rest.api;

import com.lester.demo.data.model.UserDataResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public class ApiDataService extends ApiClient {

    private UserSessionInterface apiService;

    public ApiDataService() {
        apiService = retrofit.create(UserSessionInterface.class);
    }

    public UserSessionInterface getApi() {
        return apiService;
    }

    public interface UserSessionInterface{
        @GET("/api/")
        Observable<UserDataResponse> getUserData(@Query("page") int page, @Query("results") int results);
    }
}