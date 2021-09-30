package com.capstone.tubescapstone.Room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.capstone.tubescapstone.Model.HotelItem;

import java.util.List;

@Dao
public interface HotelDAO {
    @Insert
    void insert(HotelItem data);

    @Query("SELECT * from api_table ORDER BY kode_hotel ASC")
    LiveData<List<HotelItem>> getAllData();

    @Query("DELETE FROM api_table")
    void deleteAll();

}
