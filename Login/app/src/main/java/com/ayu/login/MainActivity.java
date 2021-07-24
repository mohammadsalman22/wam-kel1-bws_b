package com.ayu.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.ayu.login.adapter.WisataHomeAdapter;
import com.ayu.login.core.ApiClient;
import com.ayu.login.core.ApiInterface;
import com.ayu.login.fragment.BottomTransaksiFragment;
import com.ayu.login.fragment.HomeFragment;
import com.ayu.login.fragment.HomestayFragment;
import com.ayu.login.fragment.TravelFragment;
import com.ayu.login.fragment.WisataFragment;
import com.ayu.login.model.GetWisata;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.dimorinny.floatingtextbutton.FloatingTextButton;


public class MainActivity extends AppCompatActivity{

    private ApiInterface apiInterface;
    private SharedPreferences.Editor editor;
    private FloatingTextButton btn1, btn2, btn3, btn4, btn5;
    private boolean isBtnopen = false;
    private boolean isnotAkun = false;
    Fragment currentFragment = new HomeFragment();
    SharedPreferences preferences;

    private Animation fromBottom, toBottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);

        preferences = MainActivity.this.getSharedPreferences("login_session", Context.MODE_PRIVATE);

        checkLogin();

        fromBottom = AnimationUtils.loadAnimation(this, R.anim.from_bottom_anim);
        toBottom = AnimationUtils.loadAnimation(this, R.anim.to_bottom_anim);

//        if (preferences.getBoolean("is_login", false)) {
//            btn1.setVisibility(View.GONE);
//            btn3.setVisibility(View.GONE);
//            btn2.setVisibility(View.VISIBLE);
//            btn4.setVisibility(View.VISIBLE);
//            btn5.setVisibility(View.VISIBLE);
//        } else {
//            btn1.setVisibility(View.VISIBLE);
//            btn3.setVisibility(View.VISIBLE);
//            btn2.setVisibility(View.GONE);
//            btn4.setVisibility(View.GONE);
//            btn5.setVisibility(View.GONE);
//        }

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ProfilActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.remove("id_user");
                editor.remove("nama");
                editor.remove("username");
                editor.putBoolean("is_login", false);
                editor.apply();

                Intent goLogin = new Intent(MainActivity.this, MainActivity.class);
                goLogin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(goLogin);
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RiwayatActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Fragment home = new HomeFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, home).commit();

        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigationView);

        bottomNav.setOnNavigationItemSelectedListener(navListener);

    }

    void setVisiblebtn() {
        if (this.isBtnopen) {
            isBtnopen = false;
            btn1.setVisibility(View.GONE);
            btn2.setVisibility(View.GONE);
            btn3.setVisibility(View.GONE);
            btn4.setVisibility(View.GONE);
            btn5.setVisibility(View.GONE);
//            btn1.startAnimation(toBottom);
//            btn2.startAnimation(toBottom);
//            btn3.startAnimation(toBottom);
//            btn4.startAnimation(toBottom);
//            btn5.startAnimation(toBottom);
        }
        else {
            if (!isnotAkun) {
                // ketika yang di sentuh menu akun
                isBtnopen = true;
//                btn1.setVisibility(View.VISIBLE);
//                btn2.setVisibility(View.VISIBLE);
//                btn3.setVisibility(View.VISIBLE);
//                btn4.setVisibility(View.VISIBLE);
//                btn5.setVisibility(View.VISIBLE);
//                btn1.startAnimation(fromBottom);
//                btn2.startAnimation(fromBottom);
//                btn3.startAnimation(fromBottom);
//                btn4.startAnimation(fromBottom);
//                btn5.startAnimation(fromBottom);
                if (preferences.getBoolean("is_login", false)) {
                    btn1.setVisibility(View.GONE);
                    btn3.setVisibility(View.GONE);
                    btn1.startAnimation(toBottom);
                    btn3.startAnimation(toBottom);
                    btn2.setVisibility(View.VISIBLE);
                    btn4.setVisibility(View.VISIBLE);
                    btn5.setVisibility(View.VISIBLE);
                } else {
                    btn1.setVisibility(View.VISIBLE);
                    btn3.setVisibility(View.VISIBLE);
                    btn2.setVisibility(View.GONE);
                    btn4.setVisibility(View.GONE);
                    btn5.setVisibility(View.GONE);
                    btn2.startAnimation(toBottom);
                    btn4.startAnimation(toBottom);
                    btn5.startAnimation(toBottom);
                }
            }
            else {
                // ketika yang di sentuh bukan menu akun
                isBtnopen = false;
                btn1.setVisibility(View.GONE);
                btn2.setVisibility(View.GONE);
                btn3.setVisibility(View.GONE);
                btn4.setVisibility(View.GONE);
                btn5.setVisibility(View.GONE);
            }

        }
    }

    private void checkLogin() {
        preferences = getSharedPreferences("login_session", Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;

            switch (item.getItemId()) {
                case R.id.nav_home:
                    isnotAkun = true;
                    selectedFragment = new HomeFragment();
                    currentFragment = selectedFragment;
                    break;
                case R.id.nav_wisata:
                    isnotAkun = true;
                    selectedFragment = new WisataFragment();
                    currentFragment = selectedFragment;
                    break;
                case R.id.nav_travel:
                    isnotAkun = true;
                    selectedFragment = new TravelFragment();
                    currentFragment = selectedFragment;
                    break;
                case R.id.nav_homestay:
                    isnotAkun = true;
                    selectedFragment = new HomestayFragment();
                    currentFragment = selectedFragment;
                    break;
                case R.id.nav_akun:
                    isnotAkun = false;
                    selectedFragment = currentFragment;
                    break;

            }

            setVisiblebtn();

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();

            return true;
        }
    };

}