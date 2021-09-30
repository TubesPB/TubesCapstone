package com.capstone.tubescapstone.Room;



import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.capstone.tubescapstone.Model.HotelItem;

@Database(entities = {HotelItem.class}, version = 1, exportSchema = false)
public abstract class HotelRoomDatabase extends RoomDatabase {

    public abstract HotelDAO hotelDAO();
    private static HotelRoomDatabase INSTANCE;

    public static HotelRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (HotelRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            HotelRoomDatabase.class, "database")
                            // Wipes and rebuilds instead of migrating
                            // if no Migration object.
                            // Migration is not part of this practical.
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
