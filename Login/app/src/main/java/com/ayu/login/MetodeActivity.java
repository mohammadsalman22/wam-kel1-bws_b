package com.ayu.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ayu.login.adapter.MetodeAdapter;
import com.ayu.login.adapter.TravelHomestayAdapter;
import com.ayu.login.core.ApiClient;
import com.ayu.login.core.ApiInterface;
import com.ayu.login.fragment.BottomTransaksiFragment;
import com.ayu.login.model.Booking;
import com.ayu.login.model.GetMetode;
import com.ayu.login.model.GetTravelHomestay;
import com.ayu.login.model.Metode;
import com.ayu.login.model.TravelHomestay;
import com.bumptech.glide.Glide;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MetodeActivity extends AppCompatActivity {

    private ApiInterface mApiInterface, apiInterface;
    private MetodeAdapter metodeAdapter;
    private RecyclerView rvMetode;
    private Intent mData;
    private TextView tvNama, tvNotujuan, idObjek, idObjek2, waktuMulai, waktuAkhir, totalHarga, idPembayaran, idUser, jumlah;
    private List<Metode> listMetode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metode);
        getSupportActionBar();
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        mData = MetodeActivity.this.getIntent();

        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        rvMetode = findViewById(R.id.rv_metode);
        tvNama = findViewById(R.id.tv_nama);

        initView();

        getDataMetode();

//        setData();
    }
    @Override
    public boolean onSupportNavigateUp() {
        MetodeActivity.this.finish();
        return true;
    }
    private void initView() {
//        tvNama = findViewById(R.id.tvNama);
//        tvNotujuan = findViewById(R.id.tv_notujuan);
//        idObjek = findViewById(R.id.id_objek);
//        idObjek2 = findViewById(R.id.id_objek2);
//        waktuMulai = findViewById(R.id.waktu_mulai);
//        waktuAkhir = findViewById(R.id.waktu_akhir);
//        totalHarga = findViewById(R.id.total_harga);
//        idPembayaran = findViewById(R.id.id_pembayaran);
//        idUser = findViewById(R.id.id_user);
//        jumlah = findViewById(R.id.jumlah);

    }

//    private void setData() {
////        tvNama.setText(mData.getStringExtra("nama"));
////        tvNotujuan.setText(mData.getStringExtra("no_tujuan"));
//        idObjek.setText(mData.getStringExtra("id_wisata"));
//        idObjek2.setText(mData.getStringExtra("id_travel_homestay"));
//        waktuMulai.setText(mData.getStringExtra("waktu_mulai"));
//        waktuAkhir.setText(mData.getStringExtra("waktu_akhir"));
//        totalHarga.setText(mData.getStringExtra("harga"));
////        idPembayaran.setText(mData.getIntExtra("id_pembayaran", 0));
//        idUser.setText(mData.getStringExtra("id_user"));
//        jumlah.setText(mData.getStringExtra("jumlah_pesan"));
//
//    }

    private void getDataMetode() {
        Call<GetMetode> callMetode = apiInterface.getMetode();
        callMetode.enqueue(new Callback<GetMetode>() {
            @Override
            public void onResponse(Call<GetMetode> call, Response<GetMetode> response) {
                try {
                    listMetode = response.body().getmData();
                    metodeAdapter = new MetodeAdapter(MetodeActivity.this, listMetode, mData, MetodeActivity.this, mApiInterface);

                    rvMetode.setAdapter(metodeAdapter);
                } catch (Exception e){
                    Log.e("GetMetode", e.getMessage());
                    Toast.makeText(MetodeActivity.this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetMetode> call, Throwable t) {
                Toast.makeText(MetodeActivity.this, "Server tidak terjangkau", Toast.LENGTH_SHORT).show();
            }
        });
    }
}