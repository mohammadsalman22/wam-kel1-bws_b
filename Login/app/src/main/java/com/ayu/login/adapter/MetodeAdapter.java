package com.ayu.login.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ayu.login.DetailActivity;
import com.ayu.login.MainActivity;
import com.ayu.login.MetodeActivity;
import com.ayu.login.R;
import com.ayu.login.core.ApiClient;
import com.ayu.login.core.ApiInterface;
import com.ayu.login.model.Booking;
import com.ayu.login.model.Metode;
import com.ayu.login.model.TravelHomestay;
import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MetodeAdapter extends RecyclerView.Adapter<MetodeAdapter.MyViewHolder> {

    Context context;
    List<Metode> listMetode;
    Intent mData;
    Activity activity;
    ApiInterface mApiInterface;


    public MetodeAdapter(Context context, List<Metode> list, Intent mData, Activity activity, ApiInterface mApiInterface) {
        this.context = context;
        this.listMetode = list;
        this.mData = mData;
        this.mApiInterface = mApiInterface;
        this.activity = activity;
    }

    @NonNull
    @Override
    public MetodeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_metode, parent, false);

        return new MetodeAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MetodeAdapter.MyViewHolder holder, int position) {
        holder.tvNama.setText(listMetode.get(position).getNama());
        holder.tvNotujuan.setText(listMetode.get(position).getNo_tujuan());

        holder.bayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");
                String datestart = mData.getStringExtra("waktu_mulai").replace('-', ' ');
                String dateend = mData.getStringExtra("waktu_akhir").replace('-', ' ');

                Date date1, date2;

                long resultDate = 0;
                try {
                    date1 = myFormat.parse(datestart);
                    date2 = myFormat.parse(dateend);

                    resultDate = date2.getDay() - date1.getDay();

                } catch (ParseException e) {
                    e.printStackTrace();
                }

                int harga = Integer.parseInt(mData.getStringExtra("harga"));
                int qty = Integer.parseInt(mData.getStringExtra("jumlah_pesan"));

                int total = (harga * qty) * (int) resultDate;

                prosesBooking(
                        mData.getStringExtra("waktu_mulai"),
                        mData.getStringExtra("waktu_akhir"),
                        total,
                        listMetode.get(position).getId_pembayaran(),
                        Integer.parseInt(mData.getStringExtra("id_user")),
                        mData.getStringExtra("id_wisata"),
                        mData.getStringExtra("id_travel_homestay"),
                        Integer.parseInt(mData.getStringExtra("jumlah_pesan"))
                        );

            }
        });
    }

    @Override
    public int getItemCount() {
        return listMetode.size();
    }

    private void prosesBooking(String waktu_mulai, String waktu_akhir, int total_harga, int id_pembayaran, int id_user, String id_wisata,
                               String id_travel_hommestay, int jumlah_pesan) {
        if(id_travel_hommestay != null) {
            Call<Booking> callBooking = mApiInterface.Booking(
                    waktu_mulai,
                    waktu_akhir,
                    String.valueOf(total_harga),
                    String.valueOf(id_pembayaran),
                    String.valueOf(id_user),
                    id_travel_hommestay,
                    String.valueOf(jumlah_pesan)
            );

            callBooking.enqueue(new Callback<Booking>() {
                @Override
                public void onResponse(Call<Booking> call, Response<Booking> response) {
                    try {
                        Toast.makeText(context, "Berhasil Booking", Toast.LENGTH_SHORT).show();
                        activity.finish();
                        context.startActivity(new Intent(context, MainActivity.class));
                    }
                    catch (Exception e) {
                        Log.e("prosesBooking", e.getMessage());
                        Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Booking> call, Throwable t) {
                    Log.e("prosesBooking", t.getMessage());
                    Toast.makeText(context, "Server tidak terjangkau", Toast.LENGTH_SHORT).show();
                }
            });
        }
        if(id_wisata != null) {
            Call<Booking> callBookingWisata = mApiInterface.BookingWisata(
                    waktu_mulai,
                    waktu_akhir,
                    String.valueOf(total_harga),
                    String.valueOf(id_pembayaran),
                    String.valueOf(id_user),
                    id_wisata,
                    String.valueOf(jumlah_pesan)
            );

            callBookingWisata.enqueue(new Callback<Booking>() {
                @Override
                public void onResponse(Call<Booking> call, Response<Booking> response) {
                    try {
                        Toast.makeText(context, "Berhasil Booking", Toast.LENGTH_SHORT).show();
                        activity.finish();
                        context.startActivity(new Intent(context, MainActivity.class));
                    }
                    catch (Exception e) {
                        Log.e("prosesBooking", e.getMessage());
                        Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Booking> call, Throwable t) {
                    Log.e("prosesBooking", t.getMessage());
                    Toast.makeText(context, "Server tidak terjangkau", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvNama, tvNotujuan;
        Button bayar;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNama = itemView.findViewById(R.id.tv_nama);
            tvNotujuan = itemView.findViewById(R.id.tv_notujuan);
            bayar = itemView.findViewById(R.id.bayar);
        }
    }
}
