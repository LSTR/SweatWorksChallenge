package com.lester.demo.data.repository.datasource.database;

import android.os.AsyncTask;

import com.lester.demo.data.mapper.Converters;
import com.lester.demo.data.model.UserE;
import com.lester.demo.data.repository.datasource.database.room.FavoriteE;
import com.lester.demo.data.repository.datasource.database.room.UserDatabase;
import com.lester.demo.data.repository.datasource.database.room.UserFavoriteDAO;

import java.util.List;

public class UserDataDB implements UserDataDbInt{
    UserFavoriteDAO userFavoriteDAO;
    public UserDataDB(UserDatabase db){
        userFavoriteDAO = db.itemNoteDAO();
    }

    @Override
    public void getFavorites(Callback callback){
        new selectAsyncTask(userFavoriteDAO, callback).execute();
    }

    @Override
    public void saveFavorite(UserE user, Callback callback) {
        user.setFavorite(true);
        FavoriteE favoriteE = Converters.fromUserToFavorite(user);
        new insertAsyncTask(userFavoriteDAO, callback).execute(favoriteE);
    }

    @Override
    public void removeFavorite(long favoriteId, Callback callback) {
        new removeAsyncTask(userFavoriteDAO, callback).execute(favoriteId);
    }

    private static class insertAsyncTask extends AsyncTask<FavoriteE, Void, Void> {
        private UserFavoriteDAO userFavoriteDAO;
        private Callback callback;

        public insertAsyncTask(UserFavoriteDAO userFavoriteDAO, Callback callback) {
            this.userFavoriteDAO = userFavoriteDAO;
            this.callback = callback;
        }

        @Override
        protected Void doInBackground(FavoriteE... obj) {
            if(obj == null || obj.length == 0)return null;
            FavoriteE curr_fav = obj[0];
            userFavoriteDAO.insert(curr_fav);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            callback.onSuccess(null);
        }
    }

    private static class selectAsyncTask extends AsyncTask<Void, Void, List<UserE>> {
        private UserFavoriteDAO userFavoriteDAO;
        private Callback callback;

        public selectAsyncTask(UserFavoriteDAO userFavoriteDAO, Callback callback) {
            this.userFavoriteDAO = userFavoriteDAO;
            this.callback = callback;
        }

        @Override
        protected List<UserE> doInBackground(Void... obj) {
            List<FavoriteE> allFavorites = userFavoriteDAO.getFavorites();
            return Converters.fromFavoritesToUsers(allFavorites);
        }

        @Override
        protected void onPostExecute(List<UserE> data) {
            callback.onSuccess(data);
        }
    }

    private static class removeAsyncTask extends AsyncTask<Long, Void, Void> {
        private UserFavoriteDAO userFavoriteDAO;
        private Callback callback;

        public removeAsyncTask(UserFavoriteDAO userFavoriteDAO, Callback callback) {
            this.userFavoriteDAO = userFavoriteDAO;
            this.callback = callback;
        }

        @Override
        protected Void doInBackground(Long... obj) {
            if(obj == null || obj.length == 0)return null;
            userFavoriteDAO.remove(obj[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            callback.onSuccess(null);
        }
    }

    public interface Callback{
        void onSuccess(List<UserE> data);
        void onError(String error);
    }
}