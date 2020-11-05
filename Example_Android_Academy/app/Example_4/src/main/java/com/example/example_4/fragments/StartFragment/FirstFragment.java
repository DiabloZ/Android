package com.example.example_4.fragments.StartFragment;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.example_4.classes.Handlers.ConstantManager;
import com.example.example_4.fragments.StartFragment.FirstFragmentDirections;
import com.example.example_4.JSON.Meme;
import com.example.example_4.R;
import com.example.example_4.classes.Handlers.FitText.FitTextPublisher;
import com.example.example_4.classes.Handlers.Orientation.OrientationPublisher;
import com.example.example_4.classes.Views.PlaceholderClass;

import java.util.ArrayList;

public class FirstFragment extends Fragment implements FirstContract.View{

    private static final String TAG = "FirstFragment";
    
    FirstContract.Presenter presenter;

    private OrientationPublisher publisherOrientation = OrientationPublisher.getInstance();
    private FitTextPublisher publisherFitText = FitTextPublisher.getInstance();

    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;

    private Button button, buttonTest;
    private ProgressBar progresBar;

    private ArrayList<Meme> memeList;

    private boolean landscape;
    private boolean fitText = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        presenter = new FirstPresenter(this);
        landscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
        sendOrientation();
        sendFitText();

        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG, "onViewCreated: ");

        PlaceholderClass.getInstance(requireContext());
        presenter.initPresenter();
        /*setArrayList();*/

    }

    private void sendFitText() {publisherFitText.notify(fitText);}

    private void sendOrientation() {
        publisherOrientation.notify(landscape);
    }

    private void goToCreators(View view) {
        NavDirections action = FirstFragmentDirections.actionFirstFragmentToCreatorsFragment();
        Navigation.findNavController(view).navigate(action);
    }

    private void goToTest(View view) {
        NavDirections action = FirstFragmentDirections.actionFirstFragmentToTestFragment();
        Navigation.findNavController(view).navigate(action);
    }

    @Override
    public void initView() {
        button = requireView().findViewById(R.id.button);
        buttonTest = requireView().findViewById(R.id.buttonTest);
        button.setOnClickListener(this::goToCreators);
        buttonTest.setOnClickListener(this::goToTest);
        recyclerView = requireView().findViewById(R.id.recyclerView);
        progresBar = requireView().findViewById(R.id.progressBar);
    }

    @Override
    public void showListData() {
        String test = "test";

        System.out.printf(test);
        recyclerAdapter = new RecyclerAdapter();


        if (landscape) {
            recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 2));
        } else {
            recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        }

        recyclerView.setAdapter(recyclerAdapter);
    }

    @Override
    public void showMeme(ArrayList<Meme> memes) {
        progresBar.setVisibility(View.INVISIBLE);
        recyclerAdapter.update(memes);
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy: ");
        if (presenter != null) presenter.onDestroy();
        super.onDestroy();
    }
}