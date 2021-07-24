package com.ayu.login.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.ayu.login.MainActivity;
import com.ayu.login.R;
import com.ayu.login.adapter.TravelAdapter;
import com.ayu.login.adapter.WisataAdapter;
import com.ayu.login.core.ApiClient;
import com.ayu.login.core.ApiInterface;
import com.ayu.login.model.GetTravel;
import com.ayu.login.model.GetWisata;
import com.ayu.login.model.TravelHomestay;
import com.ayu.login.model.Wisata;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WisataFragment extends Fragment {

    private HorizontalScrollView scrollView1, scrollView2, scrollView3;
    private Button filter_btn;
    private TextView textView1, textView2, textView3;

    boolean filterHidden = true;

    private ApiInterface apiInterface;
    private WisataAdapter wisataAdapter;
    private List<Wisata> listWisata;
    private RecyclerView rvWisata;

    private Button button11, button12, button13, button14;
    private Button button21, button22, button23, button24, button25, button26, button27;
    private Button button31, button32;

    private int white, darkGray, red;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wisata, container, false);

        Button button = (Button) view.findViewById(R.id.btn_filter);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFilterTapped(v);
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        rvWisata = view.findViewById(R.id.rv_wisata);

        initWidgets(view);

        hideFilter();

        getDataWisata();

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //initSearchWidgets();
//        setupData();
//        setUpList();
//        setUpOnclickListener();
//        hideFilter();
//        hideSort();
        initColors();

//        lookSelected(idAscButton);
//        lookSelected(allButton);
//        selectedFilters.add("all");
    }

    private void initColors() {
        white = getActivity().getResources().getColor(R.color.white);
        red = getActivity().getResources().getColor(R.color.colorlogin);
        darkGray = getActivity().getResources().getColor(R.color.darkerGrey);
    }

    private void unSelectFilterButton() {
        lookUnSelected(button11);
        lookUnSelected(button12);
        lookUnSelected(button13);
        lookUnSelected(button14);
        lookUnSelected(button21);
        lookUnSelected(button22);
        lookUnSelected(button23);
        lookUnSelected(button24);
        lookUnSelected(button25);
        lookUnSelected(button26);
        lookUnSelected(button27);
        lookUnSelected(button31);
        lookUnSelected(button32);

    }

    public void showFilterTapped(View view)
    {
        if(filterHidden == true)
        {
            filterHidden = false;
            showFilter();
        }
        else
        {
            filterHidden = true;
            hideFilter();
        }
    }

    private void hideFilter()
    {
        scrollView1.setVisibility(View.GONE);
        scrollView2.setVisibility(View.GONE);
        scrollView3.setVisibility(View.GONE);
        textView1.setVisibility(View.GONE);
        textView2.setVisibility(View.GONE);
        textView3.setVisibility(View.GONE);
        filter_btn.setText("FILTER");
    }

    private void showFilter()
    {
        scrollView1.setVisibility(View.VISIBLE);
        scrollView2.setVisibility(View.VISIBLE);
        scrollView3.setVisibility(View.VISIBLE);
        textView1.setVisibility(View.VISIBLE);
        textView2.setVisibility(View.VISIBLE);
        textView3.setVisibility(View.VISIBLE);
        filter_btn.setText("HIDE");
    }

    private void lookSelected(Button parsedButton) {
        parsedButton.setTextColor(white);
        parsedButton.setBackgroundColor(red);
    }

    private void lookUnSelected(Button parsedButton) {
        parsedButton.setTextColor(red);
        parsedButton.setBackgroundColor(darkGray);
    }

    private void initWidgets(View view) {
        filter_btn = (Button) view.findViewById(R.id.btn_filter);

        textView1 = (TextView) view.findViewById(R.id.tv1);
        textView2 = (TextView) view.findViewById(R.id.tv);
        textView3 = (TextView) view.findViewById(R.id.t);

        scrollView1 = (HorizontalScrollView) view.findViewById(R.id.scroll);
        scrollView2 = (HorizontalScrollView) view.findViewById(R.id.scroll1);
        scrollView3 = (HorizontalScrollView) view.findViewById(R.id.scroll3);

        button11 = (Button) view.findViewById(R.id.btn_ijenraung);
        button12 = (Button) view.findViewById(R.id.btn_argopuro);
        button13 = (Button) view.findViewById(R.id.btn_solor);
        button14 = (Button) view.findViewById(R.id.btn_sejarah);

        button21 = (Button) view.findViewById(R.id.btn_bahari);
        button22 = (Button) view.findViewById(R.id.btn_budaya);
        button23 = (Button) view.findViewById(R.id.btn_pertanian);
        button24 = (Button) view.findViewById(R.id.btn_buru);
        button25 = (Button) view.findViewById(R.id.btn_ziarah);
        button26 = (Button) view.findViewById(R.id.btn_cagaralam);
        button27 = (Button) view.findViewById(R.id.btn_konversi);

        button31 = (Button) view.findViewById(R.id.btn_murah);
        button32 = (Button) view.findViewById(R.id.btn_mahal);
    }

    private void getDataWisata() {
        Call<GetWisata> callWisata = apiInterface.listWisata();
        callWisata.enqueue(new Callback<GetWisata>() {
            @Override
            public void onResponse(Call<GetWisata> call, Response<GetWisata> response) {
                try {
                    listWisata = response.body().getmData();
                    wisataAdapter = new WisataAdapter(getActivity(), listWisata);

                    rvWisata.setAdapter(wisataAdapter);
                } catch (Exception e){
                    Log.e("listWisata", e.getMessage());
                    Toast.makeText(getActivity(), "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetWisata> call, Throwable t) {
                Toast.makeText(getActivity(), "Server tidak terjangkau", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
