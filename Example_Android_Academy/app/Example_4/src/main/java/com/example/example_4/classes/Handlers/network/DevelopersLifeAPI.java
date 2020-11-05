package com.example.example_4.classes.Handlers.network;

import com.example.example_4.JSON.Response;

import io.reactivex.rxjava3.core.Flowable;
import retrofit2.Call;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

interface DevelopersLifeAPI {
    String REPO = "{page}?json=true";

    @Headers({"Content-Type: application/json"})
    @GET(REPO)
    Flowable<Response> getData(
            @Path("page")
            int page
    );
}
