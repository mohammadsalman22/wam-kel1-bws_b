package com.ayu.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ayu.login.adapter.HomestayHomeAdapter;
import com.ayu.login.adapter.TravelHomestayAdapter;
import com.ayu.login.core.ApiClient;
import com.ayu.login.core.ApiInterface;
import com.ayu.login.fragment.BottomTransaksiFragment;
import com.ayu.login.model.GetHomestay;
import com.ayu.login.model.GetTravelHomestay;
import com.ayu.login.model.TravelHomestay;
import com.ayu.login.model.Wisata;
import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    private Button button;

    private ApiInterface mApiInterface, apiInterface;
    private TravelHomestayAdapter travelHomestayAdapter;
    private RecyclerView rvTravelHomestay;
    //private SharedPreferences preferences;
    private Intent mData;
    private ImageView imgView;
    private TextView tvId, tvNama, tvDeskripsi, tvTag, tvHarga, tvJudul;
    private List<TravelHomestay> listTravelHomestay;
    private List<Wisata> listWisata;

    SharedPreferences preferences;
    //private Button btnTambahFavorit, btnHapusFavorit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        preferences = DetailActivity.this.getSharedPreferences("login_session", Context.MODE_PRIVATE);
        mData = DetailActivity.this.getIntent();

        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        rvTravelHomestay = findViewById(R.id.rv_travelhomestay);
        tvJudul = findViewById(R.id.tvJudul);

        initView();

        setData();

        if (mData.getBooleanExtra("is_wisata", false)){
            getDataTravelHomestay();
        } else {
            tvJudul.setVisibility(View.GONE);
            rvTravelHomestay.setVisibility(View.GONE);
        }



        button = findViewById(R.id.alamat);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("google.navigation:q="+mData.getStringExtra("alamat")+"&mode=d"));
                intent.setPackage("com.google.android.apps.maps");
                startActivity(intent);
            }
        });

        Button buttonShow = findViewById(R.id.btn_pesan);
        buttonShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (preferences.getBoolean("is_login", false)) {
                    int id_user = preferences.getInt("id_user", 0);

                    BottomTransaksiFragment bottomTransaksiFragment = new BottomTransaksiFragment();
                    Bundle bundle = new Bundle();

                    if(mData.getBooleanExtra("is_wisata", false)) {
                        // wisata
                        bundle.putString("id_wisata", String.valueOf(mData.getIntExtra("id", 0)));
                        bundle.putString("id_travel_homestay", null);
                        bundle.putString("id_user", String.valueOf(id_user));
                        bundle.putString("harga", String.valueOf(mData.getIntExtra("harga", 0)));
                    }
                    else {
                        // travel
                        bundle.putString("id_wisata", null);
                        bundle.putString("id_travel_homestay", String.valueOf(mData.getIntExtra("id", 0)));
                        bundle.putString("harga", String.valueOf(mData.getIntExtra("harga", 0)));
                        bundle.putString("id_user", String.valueOf(id_user));
                    }

                    bottomTransaksiFragment.setArguments(bundle);
                    bottomTransaksiFragment.show(getSupportFragmentManager(), bottomTransaksiFragment.getTag());
                } else {
                    Toast.makeText(DetailActivity.this, "Maaf Anda Harus Login Terlebih Dahulu", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(DetailActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }


    @Override
    public boolean onSupportNavigateUp() {
        DetailActivity.this.finish();
        return true;
    }

    private void initView() {
        imgView = findViewById(R.id.imgView);
        tvNama = findViewById(R.id.tvNama);
        tvDeskripsi = findViewById(R.id.tvDeskripsi);
        tvHarga = findViewById(R.id.tvHarga);
        tvTag = findViewById(R.id.tvTag);
    }

    private void setData() {
        String imgUrl = ApiClient.DOMAIN + mData.getStringExtra("gambar");
        Glide.with(DetailActivity.this).load(imgUrl).into(imgView);

        tvNama.setText(mData.getStringExtra("nama"));
        tvTag.setText(mData.getStringExtra("tag"));
        tvHarga.setText("Rp "+ mData.getIntExtra("harga", 0));
        tvDeskripsi.setText(mData.getStringExtra("deskripsi"));

    }
    private void getDataTravelHomestay() {
        Call<GetTravelHomestay> callTravelHomestay = apiInterface.getTravelHomestay(mData.getStringExtra("nama"));
        callTravelHomestay.enqueue(new Callback<GetTravelHomestay>() {
            @Override
            public void onResponse(Call<GetTravelHomestay> call, Response<GetTravelHomestay> response) {
                try {
                    listTravelHomestay = response.body().getmData();
                    travelHomestayAdapter = new TravelHomestayAdapter(DetailActivity.this, listTravelHomestay);

                    rvTravelHomestay.setAdapter(travelHomestayAdapter);
                } catch (Exception e){
                    Log.e("GetHomestay", e.getMessage());
                    Toast.makeText(DetailActivity.this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetTravelHomestay> call, Throwable t) {
                Toast.makeText(DetailActivity.this, "Server tidak terjangkau", Toast.LENGTH_SHORT).show();
            }
        });
    }
}