package com.lester.demo.data.repository.datasource.rest;

import com.lester.demo.data.model.UserDataResponse;
import com.lester.demo.data.model.UserE;
import com.lester.demo.data.repository.datasource.rest.api.ApiDataService;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class UserDataRest implements UserDataRestInt {
    private ApiDataService apiDataService;

    public UserDataRest(ApiDataService apiDataService) {
        this.apiDataService = apiDataService;
    }

    @Override
    public void getUserData(int page, int cant, final Callback callback) {
        Observable<UserDataResponse> observable = apiDataService.getApi().getUserData(page, cant);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Action1<UserDataResponse>() {
                               @Override
                               public void call(UserDataResponse data) {
                                   callback.onSuccess(data.getResults());
                               }
                           },
                        new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                throwable.printStackTrace();
                                callback.onError(throwable.getMessage());
                            }
                        });
    }

    public interface Callback{
        void onSuccess(List<UserE> data);
        void onError(String error);
    }
}