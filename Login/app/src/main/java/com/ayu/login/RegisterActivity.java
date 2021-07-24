package com.ayu.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ayu.login.core.ApiClient;
import com.ayu.login.core.ApiInterface;
import com.ayu.login.model.Register;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    ApiInterface mApiInterface;
    private EditText edtUsername, edtPassword, edtNama, edtAlamat, edtNo_hp, edtEmail;
    private TextView tvLogin, sudah_punya_akun;
    private Button btnDaftar;
    private RadioGroup radioJk;
    private RadioButton radioBtn;
    private int selectedId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();

        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedId = radioJk.getCheckedRadioButtonId();
                radioBtn = findViewById(selectedId);
                if(edtUsername.getText().toString().isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Harap isi username terlebih dahulu", Toast.LENGTH_SHORT).show();
                }
                else if(edtPassword.getText().toString().isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Harap isi password terlebih dahulu", Toast.LENGTH_SHORT).show();
                }
                else if(edtNama.getText().toString().isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Harap isi nama terlebih dahulu", Toast.LENGTH_SHORT).show();
                }
                else if (radioJk == null) {
                    Toast.makeText(RegisterActivity.this, "Harap pilih jenis kelamin terlebih dahulu", Toast.LENGTH_SHORT).show();
                }
                else if(edtAlamat.getText().toString().isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Harap isi alamat terlebih dahulu", Toast.LENGTH_SHORT).show();
                }
                else if(edtNo_hp.getText().toString().isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Harap isi nomer hp terlebih dahulu", Toast.LENGTH_SHORT).show();
                }
                else if(edtEmail.getText().toString().isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Harap isi email terlebih dahulu", Toast.LENGTH_SHORT).show();
                }
                else {
                    prosesRegister(edtUsername.getText().toString(),
                            edtPassword.getText().toString(),
                            edtNama.getText().toString(),
                            radioBtn.getText().toString(),
                            edtAlamat.getText().toString(),
                            edtNo_hp.getText().toString(),
                            edtEmail.getText().toString());
                }
            }
        });

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterActivity.this.finish();
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

    }

    private void initView() {
        edtNama = findViewById(R.id.edtNama);
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        edtAlamat = findViewById(R.id.edtAlamat);
        edtEmail = findViewById(R.id.edtEmail);
        edtNo_hp = findViewById(R.id.edtNo_hp);
        tvLogin = findViewById(R.id.tv_sudah_punya_akun);
        btnDaftar = findViewById(R.id.btnDaftar);
        radioJk = findViewById(R.id.radioJk);
    }

    private void prosesRegister(String username, String password, String nama, String jk, String alamat, String no_hp, String email) {
        Call<Register> callRegister = mApiInterface.Register(username, password, nama, jk, alamat, no_hp, email);
        callRegister.enqueue(new Callback<Register>() {
            @Override
            public void onResponse(Call<Register> call, Response<Register> response) {
                try {
                    if(response.body().getStatus() == 200) {
                        RegisterActivity.this.finish();

                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                        Toast.makeText(RegisterActivity.this, "Berhasil mendaftar", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(RegisterActivity.this, "Gagal mendaftarkan akun", Toast.LENGTH_SHORT).show();
                    }
                }
                catch (Exception e) {
                    Log.e("prosesLogin", e.getMessage());
                    Toast.makeText(RegisterActivity.this, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Register> call, Throwable t) {
                Log.e("prosesLogin", t.getMessage());
                Toast.makeText(RegisterActivity.this, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
            }
        });
    }
}