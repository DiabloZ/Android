package com.example.example_4.fragments.StartFragment;

import com.example.example_4.JSON.Meme;
import com.example.example_4.classes.Handlers.network.RetrofitClient;

import java.util.ArrayList;

public class FirstModel implements FirstContract.Model{
    private static final String TAG = "me";
    FirstPresenter firstPresenter = FirstPresenter.getInstance();

    @Override
    public void getDataForView() {
        /*RetrofitClient.getInstance().loadData();*/
    }

    @Override
    public void setDataForView(ArrayList<Meme> memes) {
        firstPresenter.sendArrayDataToView(memes);
    }


}