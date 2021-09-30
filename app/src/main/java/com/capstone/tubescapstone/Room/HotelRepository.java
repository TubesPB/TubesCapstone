package com.capstone.tubescapstone.Room;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.capstone.tubescapstone.Model.HotelItem;

import java.util.List;

public class HotelRepository {
    private HotelDAO mHotelDAO;
    private LiveData<List<HotelItem>> mHotelItems;

    HotelRepository(Application application){
        HotelRoomDatabase db = HotelRoomDatabase.getDatabase(application);
        mHotelDAO = db.hotelDAO();
        mHotelItems = mHotelDAO.getAllData();
    }

    LiveData<List<HotelItem>> getAllData() {

        return mHotelItems;
    }

    public void insert (HotelItem data) {
        new insertAsyncTask(mHotelDAO).execute(data);
    }

    private static class insertAsyncTask extends AsyncTask<HotelItem, Void, Void> {

        private HotelDAO mAsyncTaskDao;

        insertAsyncTask(HotelDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final HotelItem... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    public void deleteAll()  {
        new deleteAllWordsAsyncTask(mHotelDAO).execute();
    }

    private static class deleteAllWordsAsyncTask extends AsyncTask<Void, Void, Void> {
        private HotelDAO mAsyncTaskDao;

        deleteAllWordsAsyncTask(HotelDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }
}
