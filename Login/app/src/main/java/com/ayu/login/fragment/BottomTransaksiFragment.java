package com.ayu.login.fragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ayu.login.DetailActivity;
import com.ayu.login.LoginActivity;
import com.ayu.login.MainActivity;
import com.ayu.login.MetodeActivity;
import com.ayu.login.R;
import com.ayu.login.RegisterActivity;
import com.ayu.login.adapter.HomestayHomeAdapter;
import com.ayu.login.adapter.MetodeAdapter;
import com.ayu.login.core.ApiClient;
import com.ayu.login.core.ApiInterface;
import com.ayu.login.model.Booking;
import com.ayu.login.model.DetailBooking;
import com.ayu.login.model.GetHomestay;
import com.ayu.login.model.GetMetode;
import com.ayu.login.model.Metode;
import com.ayu.login.model.Register;
import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;

public class BottomTransaksiFragment extends BottomSheetDialogFragment {

    Button btnBook;
    EditText edtMulai, edtAkhir, edtJumlah;
    private ApiInterface apiInterface, mApiInterface;
    private List<Booking> listBooking;
    private List<DetailBooking> listDetailBooking;
    private Intent mData;
    private MetodeAdapter metodeAdapter;

    public BottomTransaksiFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mData = getActivity().getIntent();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_bottom_transaksi, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        initWidgets(view);

        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        edtMulai = view.findViewById(R.id.edt_tanggal_mulai);
        edtMulai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });

        edtAkhir = view.findViewById(R.id.edt_tanggal_akhir);
        edtAkhir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker2();
            }
        });

        btnBook = view.findViewById(R.id.btn_book);
        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtMulai.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity(), "Harap isi username terlebih dahulu", Toast.LENGTH_SHORT).show();
                }
                else if(edtAkhir.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity(), "Harap isi password terlebih dahulu", Toast.LENGTH_SHORT).show();
                }
                else if(edtJumlah.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity(), "Harap isi nama terlebih dahulu", Toast.LENGTH_SHORT).show();
                }
                else {
                    setData();
                }
            }
        });
    }

    private void setData() {
        Bundle bundle = this.getArguments();

        if (bundle != null) {
            String i1 = bundle.getString("id_wisata");
            String i4 = bundle.getString("id_travel_homestay");
            String i2 = bundle.getString("harga");
            String i3 = bundle.getString("id_user");


            Intent goDetail = new Intent(getActivity(), MetodeActivity.class);
            goDetail.putExtra("waktu_mulai", edtMulai.getText().toString());
            goDetail.putExtra("waktu_akhir", edtAkhir.getText().toString());
            goDetail.putExtra("jumlah_pesan", edtJumlah.getText().toString());
            goDetail.putExtra("id_wisata", i1);
            goDetail.putExtra("id_travel_homestay", i4);
            goDetail.putExtra("harga", i2);
            goDetail.putExtra("id_user", i3);
            getActivity().startActivity(goDetail);
            getActivity().finish();
        }


    }

    private void initWidgets(View view) {
        edtMulai = (EditText) view.findViewById(R.id.edt_tanggal_mulai);
        edtAkhir = (EditText) view.findViewById(R.id.edt_tanggal_akhir);
        edtJumlah = (EditText) view.findViewById(R.id.edt_jumlah);
    }

    private void showDatePicker() {
        DatePickerFragment date = new DatePickerFragment();
        /**
         * Set Up Current Date Into dialog
         */
        Calendar calender = Calendar.getInstance();
        Bundle args = new Bundle();
        args.putInt("year", calender.get(Calendar.YEAR));
        args.putInt("month", calender.get(Calendar.MONTH));
        args.putInt("day", calender.get(Calendar.DAY_OF_MONTH));
        date.setArguments(args);
        /**
         * Set Call back to capture selected date
         */
        date.setCallBack(ondate);
        date.show(getFragmentManager(), "Date Picker");
    }

    private void showDatePicker2() {
        DatePickerFragment date = new DatePickerFragment();
        /**
         * Set Up Current Date Into dialog
         */
        Calendar calender = Calendar.getInstance();
        Bundle args = new Bundle();
        args.putInt("year", calender.get(Calendar.YEAR));
        args.putInt("month", calender.get(Calendar.MONTH));
        args.putInt("day", calender.get(Calendar.DAY_OF_MONTH)+1);
        date.setArguments(args);
        /**
         * Set Call back to capture selected date
         */
        date.setCallBack(ondate2);
        date.show(getFragmentManager(), "Date Picker");
    }

    DatePickerDialog.OnDateSetListener ondate = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {

            edtMulai.setText(String.valueOf(year) + "-" + String.valueOf(monthOfYear+1)
                    + "-" + String.valueOf(dayOfMonth));
        }
    };

    DatePickerDialog.OnDateSetListener ondate2 = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {

            edtAkhir.setText(String.valueOf(year) + "-" + String.valueOf(monthOfYear+1)
                    + "-" + String.valueOf(dayOfMonth));
        }
    };
}
