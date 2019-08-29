package com.lester.demo.data.repository.datasource.database.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserFavoriteDAO {
    @Insert
    long insert(FavoriteE obj);

    @Query("SELECT * from tb_favorite")
    List<FavoriteE> getFavorites();

    @Query("DELETE FROM tb_favorite WHERE favorite_id = :favoriteId")
    void remove(long favoriteId);
}