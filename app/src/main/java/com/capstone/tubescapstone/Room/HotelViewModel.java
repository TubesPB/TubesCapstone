package com.capstone.tubescapstone.Room;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.capstone.tubescapstone.Model.HotelItem;

import java.util.List;

public class HotelViewModel extends AndroidViewModel {
    private HotelRepository mRepository;
    private LiveData<List<HotelItem>> mHotelItems;

    public HotelViewModel(Application application) {
        super(application);
        mRepository = new HotelRepository(application);
        mHotelItems = mRepository.getAllData();
    }

    public LiveData<List<HotelItem>> getAllData() { return mHotelItems; }

    public void insert(HotelItem hotelItem) { mRepository.insert(hotelItem); }

    public void deleteAll() {mRepository.deleteAll();}
}
