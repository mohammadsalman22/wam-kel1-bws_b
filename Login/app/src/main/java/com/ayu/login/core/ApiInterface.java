package com.ayu.login.core;

import com.ayu.login.model.Booking;
import com.ayu.login.model.DetailBooking;
import com.ayu.login.model.GetBooking;
import com.ayu.login.model.GetHomestay;
import com.ayu.login.model.GetMetode;
import com.ayu.login.model.GetTravel;
import com.ayu.login.model.GetTravelHomestay;
import com.ayu.login.model.GetWisata;
import com.ayu.login.model.Login;
import com.ayu.login.model.Register;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("get-wisata")
    Call<GetWisata> getWisata();
    @GET("get-travel")
    Call<GetTravel> getTravel();
    @GET("get-homestay")
    Call<GetHomestay> getHomestay();

    @GET("list-wisata")
    Call<GetWisata> listWisata();
    @GET("list-travel")
    Call<GetTravel> listTravel();
    @GET("list-homestay")
    Call<GetHomestay> listHomestay();

    @GET("filter-wisata")
    Call<GetWisata> filterWisata();
    @GET("filter-travel")
    Call<GetTravel> filterTravel();
    @GET("filter-homestay")
    Call<GetHomestay> filterHomestay();

    @GET("get-travelhomestay")
    Call<GetTravelHomestay> getTravelHomestay(@Query("nama_wisata") String nama_wisata);

    @FormUrlEncoded
    @POST("post-user")
    Call<Register> Register(@Field("username") String username,
                            @Field("password") String password,
                            @Field("nama") String nama,
                            @Field("jk") String jk,
                            @Field("alamat") String alamat,
                            @Field("no_hp") String no_hp,
                            @Field("email") String email) ;

    @FormUrlEncoded
    @POST("get-user")
    Call<Login> Login (@Field("username") String username,
                       @Field("password") String password);

    @FormUrlEncoded
    @POST("get-profiluser")
    Call<Login> getProfilUser(@Field("id_user") int id_user);

    @GET("get-metode")
    Call<GetMetode> getMetode();

    @FormUrlEncoded
    @POST("post-booking")
    Call<Booking> Booking(@Field("waktu_mulai") String waktu_mulai,
                          @Field("waktu_akhir") String waktu_akhir,
                          @Field("total_harga") String total_harga,
                          @Field("id_pembayaran") String id_pembayaran,
                          @Field("id_user") String id_user,
                          @Field("id_travel_homestay") String id_travel_hommestay,
                          @Field("jumlah_pesan") String jumlah_pesan);

    @FormUrlEncoded
    @POST("post-booking")
    Call<Booking> BookingWisata(@Field("waktu_mulai") String waktu_mulai,
                          @Field("waktu_akhir") String waktu_akhir,
                          @Field("total_harga") String total_harga,
                          @Field("id_pembayaran") String id_pembayaran,
                          @Field("id_user") String id_user,
                          @Field("id_wisata") String id_wisata,
                          @Field("jumlah_pesan") String jumlah_pesan);

    @GET("get-booking-wisata")
    Call<GetBooking> getBookingWisata(@Query("id_user") int id_user);

    @GET("get-booking-travel")
    Call<GetBooking> getBookingTravel(@Query("id_user") int id_user);

}