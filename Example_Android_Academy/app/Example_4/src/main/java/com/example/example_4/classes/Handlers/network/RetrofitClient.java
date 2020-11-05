package com.example.example_4.classes.Handlers.network;

import android.util.Log;

import com.example.example_4.JSON.Meme;
import com.example.example_4.JSON.Response;
import com.example.example_4.fragments.StartFragment.FirstModel;

import java.util.ArrayList;
import java.util.List;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String TAG = "RetrofitClient";
    private Retrofit retrofit;
    private static RetrofitClient mInstance;
    private ArrayList<Meme> memeList = new ArrayList<>();
    private final String BASE_URL_LATEST = "https://developerslife.ru/latest/";
    private static int PAGE = 0;
    private FirstModel model = new FirstModel();

    public static synchronized RetrofitClient getInstance() {
        if (mInstance == null) {
            mInstance = new RetrofitClient();
        }
        return mInstance;
    }

    public ArrayList<Meme> getMemeList() {
        return memeList;
    }

    public DevelopersLifeAPI getApiService() {
        return retrofit.create(DevelopersLifeAPI.class);
    }

    public RetrofitClient() {}

    public void loadData() {


        if (memeList.size() != 0) {
            model.setDataForView(memeList);
            Log.i(TAG, "loadData: " + memeList.size());
            return;
        }

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL_LATEST)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        DevelopersLifeAPI developersLifeAPI = getApiService();

        developersLifeAPI.getData(0)
                .toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                /*.doOnComplete(() ->  model.setDataForView(memeList))*/
                .subscribe(
                    response -> {
                        List<Meme> childrenItems = response.getResult();
                        for (int i = 0; i < childrenItems.size(); i++) {

                            int id = childrenItems.get(i).getId();
                            int votes = childrenItems.get(i).getVotes();
                            int commentsCount = childrenItems.get(i).getCommentsCount();
                            String width = childrenItems.get(i).getWidth();
                            String height = childrenItems.get(i).getHeight();
                            String description = childrenItems.get(i).getDescription();
                            String date = childrenItems.get(i).getDate();
                            String author = childrenItems.get(i).getAuthor();
                            String gifURL = childrenItems.get(i).getGifURL();
                            String previewURL = childrenItems.get(i).getPreviewURL();

                            memeList.add(new Meme(id, votes, commentsCount, width, height, description, date, author, gifURL, previewURL));
                            Log.i(TAG, "loadData: " + memeList.size());
                        }
                    }, error -> Log.i(TAG, "loadData: " + error), () -> model.setDataForView(memeList)
                );

    }

}
