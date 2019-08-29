package com.lester.demo.data.repository.datasource.database.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.lester.demo.data.mapper.Converters;

@Database(entities = {FavoriteE.class, FavoriteE.UserTitleE.class, FavoriteE.UserLocationE.class, FavoriteE.UserLoginE.class, FavoriteE.DateE.class,
        FavoriteE.IdentityE.class, FavoriteE.UserPictureE.class, FavoriteE.UserLocationE.CoordinateE.class, FavoriteE.UserLocationE.TimeZoneE.class},
        version = 1, exportSchema = false)
@TypeConverters(Converters.class)
public abstract class UserDatabase extends RoomDatabase {

    public abstract UserFavoriteDAO itemNoteDAO();

    private static UserDatabase INSTANCE;

    public static UserDatabase getDatabase(Context context){
        if(INSTANCE == null){
            synchronized (UserDatabase.class){
                if (INSTANCE == null)
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), UserDatabase.class, "user_db")
                            .build();
            }
        }
        return INSTANCE;
    }
}