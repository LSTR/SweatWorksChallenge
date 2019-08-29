package com.lester.demo.presentation.viewmodel;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

import com.lester.demo.data.repository.UserDataRepository;
import com.lester.demo.data.repository.datasource.database.UserDataDB;
import com.lester.demo.data.repository.datasource.database.UserDataDbInt;
import com.lester.demo.data.repository.datasource.database.room.UserDatabase;
import com.lester.demo.data.repository.datasource.rest.UserDataRest;
import com.lester.demo.data.repository.datasource.rest.UserDataRestInt;
import com.lester.demo.data.repository.datasource.rest.api.ApiDataService;

public class UserDataViewModelFactory implements ViewModelProvider.Factory{

    private final UserDataRestInt userDataRest;
    private final UserDataDbInt userDataDB;
    public UserDataViewModelFactory(Context context){
        userDataRest = new UserDataRest(new ApiDataService());
        userDataDB = new UserDataDB(UserDatabase.getDatabase(context));
    }

    @NonNull
    @Override
    public UserDataViewModel create(@NonNull Class modelClass) {
        return new UserDataViewModel(
                new UserDataRepository(userDataRest, userDataDB));
    }
}