package com.example.example_4.fragments.StartFragment;

import android.util.Log;
import android.view.View;

import com.example.example_4.JSON.Meme;

import java.util.ArrayList;

class FirstPresenter implements FirstContract.Presenter {
    private static FirstContract.View view;
    private FirstContract.Model model;
    private static FirstPresenter mInstance;
    private static final String TAG = "FirstPresenter";

    public FirstPresenter(FirstContract.View view) {
        this.view = view;
        mInstance = this;
        model = new FirstModel();
    }

    public FirstPresenter() {}

    public static synchronized FirstPresenter getInstance(FirstContract.View view) {
        Log.i(TAG, "getInstance: " + (view == null));
        if (mInstance == null) {
            mInstance = new FirstPresenter();
        }
        return mInstance;
    }

    public static synchronized FirstPresenter getInstance() {
        Log.i(TAG, "getInstance: "+ (view == null));
        if (mInstance == null) {
            mInstance = new FirstPresenter();
        }
        return mInstance;
    }

    @Override
    public void initPresenter() {
        view.initView();
        view.showListData();
        model.getDataForView();
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onDestroy() {
        this.view = null;
        Log.i(TAG, "onDestroy: "+ (view == null));
    }

    @Override
    public void sendArrayDataToView(ArrayList<Meme> memes) {
        view.showMeme(memes);
    }
}
