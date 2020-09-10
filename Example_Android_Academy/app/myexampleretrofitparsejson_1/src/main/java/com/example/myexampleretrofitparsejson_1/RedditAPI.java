package com.example.myexampleretrofitparsejson_1;

import com.example.myexampleretrofitparsejson_1.model.Response;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

interface RedditAPI {
    String REPO = ".json";

    @Headers("Content-Type: application/json")
    @GET(REPO)
    Call<Response> getData();
}
