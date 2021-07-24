package com.ayu.login.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.ayu.login.R;
import com.ayu.login.adapter.HomestayHomeAdapter;
import com.ayu.login.adapter.TravelHomeAdapter;
import com.ayu.login.adapter.WisataHomeAdapter;
import com.ayu.login.core.ApiClient;
import com.ayu.login.core.ApiInterface;
import com.ayu.login.model.GetHomestay;
import com.ayu.login.model.GetTravel;
import com.ayu.login.model.GetWisata;
import com.ayu.login.model.TravelHomestay;
import com.ayu.login.model.Wisata;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment implements View.OnClickListener{

    private ApiInterface apiInterface;

    private WisataHomeAdapter wisataHomeAdapter;
    private TravelHomeAdapter travelHomeAdapter;
    private HomestayHomeAdapter homestayHomeAdapter;

    private RecyclerView rvWisata, rvTravel, rvHomestay;
    private List<Wisata> listWisata;
    private List<TravelHomestay> listTravel, listHomestay; // total data
    private List<Wisata> listSearch; // nampung hasil pencarian

    EditText editSearch;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        Button button1 = (Button) view.findViewById(R.id.btn_view_wisata);
        Button button2 = (Button) view.findViewById(R.id.btn_view_travel);
        Button button3 = (Button) view.findViewById(R.id.btn_view_homestay);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        Fragment fragment = null;

        switch (view.getId()) {
            case R.id.btn_view_wisata:
                fragment = new WisataFragment();
                replaceFragment(fragment);
                break;
            case R.id.btn_view_travel:
                fragment = new TravelFragment();
                replaceFragment(fragment);
                break;
            case R.id.btn_view_homestay:
                fragment = new HomestayFragment();
                replaceFragment(fragment);
                break;
        }
    }

    public void replaceFragment(Fragment someFragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        rvWisata = view.findViewById(R.id.rv_wisata);
        rvTravel = view.findViewById(R.id.rv_travel);
        rvHomestay = view.findViewById(R.id.rv_homestay);
        editSearch = view.findViewById(R.id.src);

        listSearch = new ArrayList<>();

        getDataWisata();
        getDataTravel();
        getDataHomestay();

        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() > 0) {
                    if(listSearch.size() > 0) {
                        Log.d("cari", "kosongkan list pencarian");
                        listSearch.clear();
                    }
                    // melakukan pencarian
                    Log.d("cari", s.toString());
                    for(Wisata item:listWisata) {
                        String data = item.getNama_wisata().toLowerCase();
                        if(data.contains(s.toString().toLowerCase())) {
                            listSearch.add(item);
                        }
                    }
                    wisataHomeAdapter = new WisataHomeAdapter(getActivity(), listSearch);
                    rvWisata.setAdapter(wisataHomeAdapter);
                }
                else {
                    // jika kolom pencarian kosong
                    Log.d("cari", "kolom kosong");
                    getDataWisata();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void getDataWisata() {
        Call<GetWisata> callWisata = apiInterface.getWisata();
        callWisata.enqueue(new Callback<GetWisata>() {
            @Override
            public void onResponse(Call<GetWisata> call, Response<GetWisata> response) {
                try {
                    listWisata = response.body().getmData();
                    wisataHomeAdapter = new WisataHomeAdapter(getActivity(), listWisata);

                    rvWisata.setAdapter(wisataHomeAdapter);
                } catch (Exception e){
                    Log.e("getWisata", e.getMessage());
                    Toast.makeText(getActivity(), "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetWisata> call, Throwable t) {
                Toast.makeText(getActivity(), "Server tidak terjangkau", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getDataTravel() {
        Call<GetTravel> callTravel = apiInterface.getTravel();
        callTravel.enqueue(new Callback<GetTravel>() {
            @Override
            public void onResponse(Call<GetTravel> call, Response<GetTravel> response) {
                try {
                    listTravel = response.body().getmData();
                    travelHomeAdapter = new TravelHomeAdapter(getActivity(), listTravel);

                    rvTravel.setAdapter(travelHomeAdapter);
                } catch (Exception e){
                    Log.e("getTravel", e.getMessage());
                    Toast.makeText(getActivity(), "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetTravel> call, Throwable t) {
                Toast.makeText(getActivity(), "Server tidak terjangkau", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getDataHomestay() {
        Call<GetHomestay> callHomestay = apiInterface.getHomestay();
        callHomestay.enqueue(new Callback<GetHomestay>() {
            @Override
            public void onResponse(Call<GetHomestay> call, Response<GetHomestay> response) {
                try {
                    listHomestay = response.body().getmData();
                    homestayHomeAdapter = new HomestayHomeAdapter(getActivity(), listHomestay);

                    rvHomestay.setAdapter(homestayHomeAdapter);
                } catch (Exception e){
                    Log.e("GetHomestay", e.getMessage());
                    Toast.makeText(getActivity(), "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetHomestay> call, Throwable t) {
                Toast.makeText(getActivity(), "Server tidak terjangkau", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
