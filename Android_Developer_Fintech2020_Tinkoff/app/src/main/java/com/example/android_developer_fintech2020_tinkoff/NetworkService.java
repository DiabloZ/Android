package com.example.android_developer_fintech2020_tinkoff;

import com.example.android_developer_fintech2020_tinkoff.models.ResultItem;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

class NetworkService {
    private static NetworkService mInstance;
    private static final String BASE_URL = "https://developerslife.ru/";
    private Retrofit retrofit;


    private NetworkService(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static NetworkService getInstance() {
        if (mInstance == null) {
            mInstance = new NetworkService();
        }
        return mInstance;
    }

    public JSONPlaceHolderApi getJSONApi() {
        return retrofit.create(JSONPlaceHolderApi.class);
    }

    public interface JSONPlaceHolderApi {
        @GET("latest/{id}?json=true")
        public Call<ResultItem> getPostWithID(@Path("id") int id);
    }
}
