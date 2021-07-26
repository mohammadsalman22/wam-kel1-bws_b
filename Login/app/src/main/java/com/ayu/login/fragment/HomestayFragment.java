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
import android.widget.HorizontalScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.ayu.login.MainActivity;
import com.ayu.login.R;
import com.ayu.login.adapter.HomestayAdapter;
import com.ayu.login.adapter.TravelAdapter;
import com.ayu.login.core.ApiClient;
import com.ayu.login.core.ApiInterface;
import com.ayu.login.model.GetHomestay;
import com.ayu.login.model.GetTravel;
import com.ayu.login.model.TravelHomestay;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomestayFragment extends Fragment {

    private HorizontalScrollView scrollView1, scrollView2;
    private Button filter_btn;
    private TextView textView1, textView2;

    boolean filterHidden = true;

    private ApiInterface apiInterface;
    private HomestayAdapter homestayAdapter;
    private List<TravelHomestay> listHomestay;
    private RecyclerView rvHomestay;

    private List<TravelHomestay> listSearch; // nampung hasil pencarian

    EditText editSearch;

    private Button button11, button12, button13;
    private Button button21, button22;

    private int white, darkGray, red;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_homestay, container, false);

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
        rvHomestay = view.findViewById(R.id.rv_homestay);
        editSearch = view.findViewById(R.id.src);

        listSearch = new ArrayList<>();

        initWidgets(view);

        hideFilter();

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
                    for(TravelHomestay item:listHomestay) {
                        String data = item.getNama_travel_homestay().toLowerCase();
                        if(data.contains(s.toString().toLowerCase())) {
                            listSearch.add(item);
                        }
                    }
                    homestayAdapter = new HomestayAdapter(getActivity(), listSearch);
                    rvHomestay.setAdapter(homestayAdapter);
                }
                else {
                    // jika kolom pencarian kosong
                    Log.d("cari", "kolom kosong");
                    getDataHomestay();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

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
        lookUnSelected(button21);
        lookUnSelected(button22);
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
        textView1.setVisibility(View.GONE);
        textView2.setVisibility(View.GONE);
        filter_btn.setText("FILTER");
    }

    private void showFilter()
    {
        scrollView1.setVisibility(View.VISIBLE);
        scrollView2.setVisibility(View.VISIBLE);
        textView1.setVisibility(View.VISIBLE);
        textView2.setVisibility(View.VISIBLE);
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

        scrollView1 = (HorizontalScrollView) view.findViewById(R.id.scroll);
        scrollView2 = (HorizontalScrollView) view.findViewById(R.id.scroll1);

        button11 = (Button) view.findViewById(R.id.btn_guesthouse);
        button12 = (Button) view.findViewById(R.id.btn_hostel);
        button13 = (Button) view.findViewById(R.id.btn_losmen);

        button21 = (Button) view.findViewById(R.id.btn_murah);
        button22 = (Button) view.findViewById(R.id.btn_mahal);
    }

    private void getDataHomestay() {
        Call<GetHomestay> callHomestay = apiInterface.listHomestay();
        callHomestay.enqueue(new Callback<GetHomestay>() {
            @Override
            public void onResponse(Call<GetHomestay> call, Response<GetHomestay> response) {
                try {
                    listHomestay = response.body().getmData();
                    homestayAdapter = new HomestayAdapter(getActivity(), listHomestay);

                    rvHomestay.setAdapter(homestayAdapter);
                } catch (Exception e){
                    Log.e("listHomestay", e.getMessage());
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
