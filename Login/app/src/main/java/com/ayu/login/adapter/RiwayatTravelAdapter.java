package com.ayu.login.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ayu.login.R;
import com.ayu.login.model.Booking;

import java.util.List;

public class RiwayatTravelAdapter extends RecyclerView.Adapter<RiwayatTravelAdapter.MyViewHolder>{

    Context context;
    List<Booking> listBooking;
    SharedPreferences preferences;

    public RiwayatTravelAdapter(Context context, List<Booking> list) {
        this.context = context;
        this.listBooking = list;
    }

    @NonNull
    @Override
    public RiwayatTravelAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_riwayat, parent, false);
        preferences = context.getSharedPreferences("login_session", Context.MODE_PRIVATE);
        return new RiwayatTravelAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RiwayatTravelAdapter.MyViewHolder holder, int position) {
        holder.tvKodeBooking.setText(": " + listBooking.get(position).getKode_booking());
        holder.tvNama.setText(": " + listBooking.get(position).getNama_travel_homestay());
        holder.tvJumlahPesan.setText(": " + listBooking.get(position).getJumlah_pesan());
        holder.tvTotalHarga.setText(": Rp. " + listBooking.get(position).getTotal_harga());
        holder.tvStatus.setText(": " + listBooking.get(position).getStatus());

        String status = listBooking.get(position).getStatus();

        if (status.equals("Menunggu")){
            holder.submit.setVisibility(View.VISIBLE);
        } else {
            holder.submit.setVisibility(View.GONE);
        }

        holder.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pesan1 = preferences.getString("nama", "");
                String pesan2 = listBooking.get(position).getNama_travel_homestay();
                String pesan6 = listBooking.get(position).getWaktu_mulai();
                String pesan7 = listBooking.get(position).getWaktu_akhir();
                String pesan3 = String.valueOf(listBooking.get(position).getJumlah_pesan());
                String pesan4 = "Rp. " + listBooking.get(position).getHarga_travel_homestay();
                String pesan5 = "Rp. " + listBooking.get(position).getTotal_harga();
                String semuapesan = "*Pemesanan Travel atau Homestay*" +
                        "\n" + "Pemesan : " + pesan1 +
                        "\n" + "Travel atau Homestay : " + pesan2 +
                        "\n" + "Waktu Mulai : " + pesan6 +
                        "\n" + "Waktu Akhir : " + pesan7 +
                        "\n" + "Qty : " + pesan3 +
                        "\n" + "Harga Satuan : " + pesan4 +
                        "\n" + "SubTotal : " + pesan5 +
                        "\n" + "*Berikut Bukti Pembayaran :*";
                Intent kirimWA = new Intent(Intent.ACTION_SEND);
                kirimWA.setType("text/plain");
                kirimWA.putExtra(Intent.EXTRA_TEXT, semuapesan);
                kirimWA.putExtra("jid", "6287859537060" + "@s.whatsapp.net");
                kirimWA.setPackage("com.whatsapp");
                context.startActivity(kirimWA);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listBooking.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        Button submit;

        TextView tvNama, tvTotalHarga, tvKodeBooking, tvJumlahPesan, tvStatus;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvKodeBooking = itemView.findViewById(R.id.tv_kode_booking);
            tvNama = itemView.findViewById(R.id.tv_nama);
            tvJumlahPesan = itemView.findViewById(R.id.tv_jumlah_pesan);
            tvTotalHarga = itemView.findViewById(R.id.tv_total_harga);
            tvStatus = itemView.findViewById(R.id.tv_status);
            submit = itemView.findViewById(R.id.submit);
        }
    }
}


