package com.capstone.tubescapstone;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.capstone.tubescapstone.Adapter.HotelAdapter;
import com.capstone.tubescapstone.Model.HotelItem;
import com.capstone.tubescapstone.Model.RootHotelModel;
import com.capstone.tubescapstone.rest.ApiConfig;
import com.capstone.tubescapstone.rest.ApiService;
import com.capstone.tubescapstone.Room.HotelViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView hotel = null;
    private ArrayList<HotelItem> hotelItems = null;
    private HotelAdapter hotelAdapter = null;
    private HotelViewModel mHotelViewModel = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Create an instance of the tab layout from the view.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        Intent intentIntro = new Intent(this, IntroActivity.class);
        startActivity(intentIntro);

        hotelItems = new ArrayList<>();
        if (haveNetwork()) {
            getData();
        } else if (!haveNetwork()) {
            hotelAdapter = new HotelAdapter(hotelItems, getApplicationContext()); // model memasukkan ke adapter untuk ditampilkan di Recycle vIEW
            hotel.setAdapter(hotelAdapter); //recycler view menghubungkan ke adapter dengan "SET ADAPTER"
            hotel.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            mHotelViewModel.getAllData().observe(this, new Observer<List<HotelItem>>() {
                @Override
                public void onChanged(@Nullable final List<HotelItem> data) {
                    // Update the cached copy of the words in the adapter.
                    hotelAdapter.setData((ArrayList<HotelItem>) data);
                }
            });
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    private boolean haveNetwork() {
        boolean have_WIFI = false;
        boolean have_MobileData = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo[] networkInfos = connectivityManager.getAllNetworkInfo();
        for (NetworkInfo info : networkInfos) {
            if (info.getTypeName().equalsIgnoreCase("WIFI"))
                if (info.isConnected()) have_WIFI = true;

            if (info.getTypeName().equalsIgnoreCase("MOBILE"))
                if (info.isConnected()) have_MobileData = true;
        }
        final boolean b = have_WIFI || have_MobileData;
        return b;
    }

    private void initView() {
        hotel = findViewById(R.id.rvMainMenu);
        mHotelViewModel = ViewModelProviders.of(this).get(HotelViewModel.class);
    }


    private void getData() {
        ApiService apiService = ApiConfig.getApiService();
        apiService.getData()
                .enqueue(new Callback<RootHotelModel>() {
                    @Override
                    public void onResponse(@NotNull Call<RootHotelModel> call, @NotNull Response<RootHotelModel> response) {
                        if (response.isSuccessful()) {
                            assert response.body() != null;
                            hotelItems = response.body().getHotel(); //ini untuk mengambil data dari JSON lalu ditampung ke model


                            mHotelViewModel.deleteAll();

                            // Menyimpan data ke database || Save data
                            for (int i = 0; i < hotelItems.size(); i++) {
                                Integer id = hotelItems.get(i).getId();
                                Integer kode_hotel = hotelItems.get(i).getKode_hotel();
                                String nama = hotelItems.get(i).getNama();
                                String alamat = hotelItems.get(i).getAlamat();
                                String kordinat = hotelItems.get(i).getKordinat();
                                String no_telp = hotelItems.get(i).getNomorTelp();
                                String gambar_url = hotelItems.get(i).getGambarUrl();


                                HotelItem Hotels = new HotelItem();
                                Hotels.setId(id);
                                Hotels.setKode_hotel(kode_hotel);
                                Hotels.setNama(nama);
                                Hotels.setAlamat(alamat);
                                Hotels.setKordinat(kordinat);
                                Hotels.setNomorTelp(no_telp);
                                Hotels.setGambarUrl(gambar_url);
                                mHotelViewModel.insert(Hotels);
                            }

                            hotelAdapter = new HotelAdapter(hotelItems, getApplicationContext()); // model memasukkan ke adapter untuk ditampilkan di Recycle vIEW
                            hotelAdapter.notifyDataSetChanged(); //adapter akan mengetahui apabila ada data yang baru
                            hotel.setAdapter(hotelAdapter); //recycler view menghubungkan ke adapter dengan "SET ADAPTER"
                            hotel.setLayoutManager(new LinearLayoutManager(getApplicationContext())); //recycler view dapat berubah layoutnya bisa berubah grid maupun stargredd
                        }

                    }

                    @Override
                    public void onFailure(Call<RootHotelModel> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
    }
}


