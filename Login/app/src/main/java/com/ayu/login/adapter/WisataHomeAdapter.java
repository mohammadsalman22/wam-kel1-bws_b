package com.ayu.login.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.ayu.login.DetailActivity;
import com.ayu.login.R;
import com.ayu.login.core.ApiClient;
import com.ayu.login.model.Wisata;
import com.bumptech.glide.Glide;

import java.util.List;

public class WisataHomeAdapter extends RecyclerView.Adapter<WisataHomeAdapter.MyViewHolder> {

    Context context;
    List<Wisata> listWisata;

    public WisataHomeAdapter(Context context, List<Wisata> list) {
        this.context = context;
        this.listWisata = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_wisata, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WisataHomeAdapter.MyViewHolder holder, int position) {
        String imgUrl = ApiClient.DOMAIN + listWisata.get(position).getGambar_wisata();
        Glide.with(context).load(imgUrl).into(holder.imgView);
        holder.tvNama.setText(listWisata.get(position).getNama_wisata());
        holder.tvHarga.setText("Rp. "+listWisata.get(position).getHarga_wisata());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goDetail = new Intent(context, DetailActivity.class);
                goDetail.putExtra("id", listWisata.get(position).getId_wisata());
                goDetail.putExtra("nama", listWisata.get(position).getNama_wisata());
                goDetail.putExtra("harga", listWisata.get(position).getHarga_wisata());
                goDetail.putExtra("tag", listWisata.get(position).getTag());
                goDetail.putExtra("alamat", listWisata.get(position).getAlamat_wisata());
                goDetail.putExtra("deskripsi", listWisata.get(position).getDeskripsi_wisata());
                goDetail.putExtra("gambar", listWisata.get(position).getGambar_wisata());
                goDetail.putExtra("is_wisata", true);

                context.startActivity(goDetail);

            }
        });
    }

    @Override
    public int getItemCount() {
        return listWisata.size();
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
