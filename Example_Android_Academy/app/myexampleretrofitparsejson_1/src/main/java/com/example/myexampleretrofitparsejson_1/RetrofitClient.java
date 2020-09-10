package com.example.myexampleretrofitparsejson_1;

import com.example.myexampleretrofitparsejson_1.constatns.Constants;

import retrofit2.Retrofit;

public class RetrofitClient {
    private Retrofit retrofit;
    private static RetrofitClient mInstance;


    public static synchronized RetrofitClient getInstance() {
        if (mInstance == null) {
            mInstance = new RetrofitClient();
        }
        return mInstance;
    }

    public RetrofitClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .build();
    }

}
