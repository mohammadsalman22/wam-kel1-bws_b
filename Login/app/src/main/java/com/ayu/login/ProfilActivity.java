package com.ayu.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.ayu.login.adapter.TravelAdapter;
import com.ayu.login.core.ApiClient;
import com.ayu.login.core.ApiInterface;
import com.ayu.login.model.GetTravel;
import com.ayu.login.model.Login;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilActivity extends AppCompatActivity {

    ApiInterface mApiInterface;
    SharedPreferences preferences;
    
    TextView tvUsername, tvPassword, tvNama, tvJk, tvAlamat, tvNo_hp, tvEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        preferences = ProfilActivity.this.getSharedPreferences("login_session", Context.MODE_PRIVATE);

        initView();

        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        getDataProfil();
    }

    @Override
    public boolean onSupportNavigateUp() {
        ProfilActivity.this.finish();
        return true;
    }

    private void initView() {
        tvNama = findViewById(R.id.tvNama);
        tvUsername = findViewById(R.id.tvUsername);
        tvJk = findViewById(R.id.tvJk);
        tvAlamat = findViewById(R.id.tvAlamat);
        tvEmail = findViewById(R.id.tvEmail);
        tvNo_hp = findViewById(R.id.tvNo_hp);
    }

    private void getDataProfil() {
        Call<Login> getProfilUser = mApiInterface.getProfilUser(preferences.getInt("id_user", 0));
        getProfilUser.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                try {
                    tvUsername.setText(response.body().getmData().username);
                    tvNama.setText(response.body().getmData().nama);
                    tvJk.setText(response.body().getmData().jk);
                    tvAlamat.setText(response.body().getmData().alamat);
                    tvNo_hp.setText(response.body().getmData().no_hp);
                    tvEmail.setText(response.body().getmData().email);

                } catch (Exception e){
                    Log.e("getProfilUser", e.getMessage());
                    Toast.makeText(ProfilActivity.this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                Toast.makeText(ProfilActivity.this, "Server tidak terjangkau", Toast.LENGTH_SHORT).show();
            }
        });
    }
}