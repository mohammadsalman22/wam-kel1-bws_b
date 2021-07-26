package com.ayu.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.ayu.login.adapter.RiwayatAdapter;
import com.ayu.login.adapter.RiwayatTravelAdapter;
import com.ayu.login.adapter.TravelHomestayAdapter;
import com.ayu.login.core.ApiClient;
import com.ayu.login.core.ApiInterface;
import com.ayu.login.model.Booking;
import com.ayu.login.model.GetBooking;
import com.ayu.login.model.GetTravelHomestay;
import com.ayu.login.model.Wisata;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RiwayatActivity extends AppCompatActivity {

    private ApiInterface apiInterface;
    private RiwayatAdapter riwayatAdapter;
    private RiwayatTravelAdapter riwayatTravelAdapter;
    private RecyclerView rvRiwayatWisata, rvRiwayatTravel;
    private List<Booking> listBookingWisata, listBookingTravel;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        preferences = RiwayatActivity.this.getSharedPreferences("login_session", Context.MODE_PRIVATE);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        rvRiwayatWisata = findViewById(R.id.rv_riwayat_wisata);
        rvRiwayatTravel = findViewById(R.id.rv_riwayat_travel);

        getDataRiwayatWisata();

        getDataRiwayatTravel();
    }

    @Override
    public boolean onSupportNavigateUp() {
        RiwayatActivity.this.finish();
        return true;
    }

    private void getDataRiwayatWisata() {
        Call<GetBooking> getBookingWisata = apiInterface.getBookingWisata(preferences.getInt("id_user", 0));
        getBookingWisata.enqueue(new Callback<GetBooking>() {
            @Override
            public void onResponse(Call<GetBooking> call, Response<GetBooking> response) {
                try {
                    listBookingWisata = response.body().getmData();
                    riwayatAdapter = new RiwayatAdapter(RiwayatActivity.this, listBookingWisata);

                    rvRiwayatWisata.setAdapter(riwayatAdapter);
                } catch (Exception e){
                    Log.e("GetBooking", e.getMessage());
                    Toast.makeText(RiwayatActivity.this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetBooking> call, Throwable t) {
                Toast.makeText(RiwayatActivity.this, "Server tidak terjangkau", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getDataRiwayatTravel() {
        Call<GetBooking> getBookingTravel = apiInterface.getBookingTravel(preferences.getInt("id_user", 0));
        getBookingTravel.enqueue(new Callback<GetBooking>() {
            @Override
            public void onResponse(Call<GetBooking> call, Response<GetBooking> response) {
                try {
                    listBookingTravel = response.body().getmData();
                    riwayatTravelAdapter = new RiwayatTravelAdapter(RiwayatActivity.this, listBookingTravel);

                    rvRiwayatTravel.setAdapter(riwayatTravelAdapter);
                } catch (Exception e){
                    Log.e("GetBooking", e.getMessage());
                    Toast.makeText(RiwayatActivity.this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetBooking> call, Throwable t) {
                Toast.makeText(RiwayatActivity.this, "Server tidak terjangkau", Toast.LENGTH_SHORT).show();
            }
        });
    }
}