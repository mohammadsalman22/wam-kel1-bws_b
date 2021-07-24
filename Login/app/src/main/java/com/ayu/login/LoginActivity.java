package com.ayu.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ayu.login.core.ApiClient;
import com.ayu.login.core.ApiInterface;
import com.ayu.login.model.Login;
import com.ayu.login.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private ApiInterface mApiInterface;
    private TextView tvDaftar;
    private EditText edtUsername, edtPassword;
    private Button btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        initView();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtUsername.getText().toString().isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Harap isi username terlebih dahulu", Toast.LENGTH_SHORT).show();
                }
                else if(edtPassword.getText().toString().isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Harap isi password terlebih dahulu", Toast.LENGTH_SHORT).show();
                }
                else {
                    prosesLogin(edtUsername.getText().toString(), edtPassword.getText().toString());
                }
            }
        });

        tvDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.this.finish();
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }

    private void initView() {
        tvDaftar = findViewById(R.id.tv_belum_punya_akun);
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
    }

    private void prosesLogin(String username, String password) {
        Call<Login> callLogin = mApiInterface.Login(username, password);
        callLogin.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                try {
                    if(response.body().getStatus() == 200) {
                        if (response.body().getMessage().equals("Berhasil")) {
                            saveLoginSession(response.body().getmData());

                            LoginActivity.this.finish();

                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        } else {
                            Toast.makeText(LoginActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                    else {
                        Toast.makeText(LoginActivity.this, "Akun tidak ditemukan", Toast.LENGTH_SHORT).show();
                    }
                }
                catch (Exception e) {
                    Log.e("prosesLogin", e.getMessage());
                    Toast.makeText(LoginActivity.this, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                Log.e("prosesLogin", t.getMessage());
                Toast.makeText(LoginActivity.this, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveLoginSession(User data) {
        SharedPreferences sharedPreferences = LoginActivity.this.getSharedPreferences("login_session", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("id_user", data.getId_user());
        editor.putString("nama", data.getNama());
        editor.putString("username", data.getUsername());
        editor.putBoolean("is_login", true);
        editor.apply();
    }
}