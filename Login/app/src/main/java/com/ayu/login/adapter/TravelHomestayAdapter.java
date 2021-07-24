package com.ayu.login.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ayu.login.DetailActivity;
import com.ayu.login.R;
import com.ayu.login.core.ApiClient;
import com.ayu.login.model.TravelHomestay;
import com.bumptech.glide.Glide;

import java.util.List;

public class TravelHomestayAdapter extends RecyclerView.Adapter<TravelHomestayAdapter.MyViewHolder>{

    Context context;
    List<TravelHomestay> listTravelHomestay;

    public TravelHomestayAdapter(Context context, List<TravelHomestay> list) {
        this.context = context;
        this.listTravelHomestay = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_homestay, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TravelHomestayAdapter.MyViewHolder holder, int position) {
        String imgUrl = ApiClient.DOMAIN + listTravelHomestay.get(position).getGambar_travel_homestay();
        Glide.with(context).load(imgUrl).into(holder.imgView);
        holder.tvNama.setText(listTravelHomestay.get(position).getNama_travel_homestay());
        holder.tvHarga.setText("Rp. "+listTravelHomestay.get(position).getHarga_travel_homestay());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goDetail = new Intent(context, DetailActivity.class);
                goDetail.putExtra("id", listTravelHomestay.get(position).getId_travel_homestay());
                goDetail.putExtra("nama", listTravelHomestay.get(position).getNama_travel_homestay());
                goDetail.putExtra("harga", listTravelHomestay.get(position).getHarga_travel_homestay());
                goDetail.putExtra("tag", listTravelHomestay.get(position).getTag());
                goDetail.putExtra("alamat", listTravelHomestay.get(position).getAlamat_travel_homestay());
                goDetail.putExtra("deskripsi", listTravelHomestay.get(position).getDeskripsi_travel_homestay());
                goDetail.putExtra("gambar", listTravelHomestay.get(position).getGambar_travel_homestay());
                goDetail.putExtra("is_wisata", false);

                context.startActivity(goDetail);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listTravelHomestay.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imgView;
        TextView tvNama, tvHarga;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgView = itemView.findViewById(R.id.img_view);
            tvNama = itemView.findViewById(R.id.tv_nama);
            tvHarga = itemView.findViewById(R.id.tv_harga);
        }
    }
}
