package com.ayu.login.core;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
//http://192.168.43.209:8000/
//    public static final String DOMAIN = "https://wsjti.id/bookWisata/public/";
    public static final String DOMAIN = "http://192.168.1.16:8000/";
    public static final String BASE_URL = DOMAIN + "api/";
    private static Retrofit retrofit = null;
    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
