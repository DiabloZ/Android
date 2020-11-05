package com.example.example_4.fragments.StartFragment;

import com.example.example_4.JSON.Meme;

import java.util.ArrayList;

public interface FirstContract {
    interface View {
        void initView();
        void showListData();
        void showMeme(ArrayList<Meme> memes);
    }

    interface Presenter {
        void initPresenter();
        void onClick(android.view.View view);
        void onDestroy();
        void sendArrayDataToView(ArrayList<Meme> memes);
    }

    interface Model {
        void getDataForView();
        void setDataForView(ArrayList<Meme> memes);
    }
}
