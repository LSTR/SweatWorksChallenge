package com.lester.demo.presentation.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.lester.demo.data.model.UserE;
import com.lester.demo.data.repository.UserDataRepositoryInt;
import com.lester.demo.data.repository.datasource.database.UserDataDB;
import com.lester.demo.data.repository.datasource.rest.UserDataRest;

import java.util.List;

public class UserDataViewModel extends ViewModel {
    private UserDataRepositoryInt userDataRepository;
    private MutableLiveData<List<UserE>> userListLive = new MutableLiveData<>();
    private MutableLiveData<String> messageLive = new MutableLiveData<>();

    public UserDataViewModel(UserDataRepositoryInt userDataRepository){
        this.userDataRepository = userDataRepository;
    }

    public MutableLiveData<List<UserE>> getDataListLive() {
        return userListLive;
    }

    public MutableLiveData<String> getMessageLive() {
        return messageLive;
    }

    public void loadUserData(int page, int cant){
        userDataRepository.getUserData(page, cant, new UserDataRest.Callback() {
            @Override
            public void onSuccess(List<UserE> data) {
                userListLive.postValue(data);
            }
            @Override
            public void onError(String error) {
                messageLive.postValue(error);
            }
        });
    }

    public void loadFavorites() {
        userDataRepository.loadFavorites(new UserDataDB.Callback() {
            @Override
            public void onSuccess(List<UserE> data) {
                userListLive.postValue(data);
            }
            @Override
            public void onError(String error) {}
        });
    }

    public void addFavorite(UserE user) {
        userDataRepository.addFavorite(user, new UserDataDB.Callback() {
            @Override
            public void onSuccess(List<UserE> data) {
                messageLive.postValue("Added to favorites!");
            }
            @Override
            public void onError(String error) {
                messageLive.postValue(error);
            }
        });
    }

    public void removeFavorite(long favorite_id) {
        userDataRepository.removeFavorite(favorite_id, new UserDataDB.Callback() {
            @Override
            public void onSuccess(List<UserE> data) {
                messageLive.postValue("Removed from favorites!");
            }
            @Override
            public void onError(String error) {
                messageLive.postValue(error);
            }
        });
    }
}