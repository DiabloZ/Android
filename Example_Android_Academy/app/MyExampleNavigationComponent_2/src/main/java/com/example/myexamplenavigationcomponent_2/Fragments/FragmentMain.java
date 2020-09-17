package com.example.myexamplenavigationcomponent_2.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myexamplenavigationcomponent_2.R;


public class FragmentMain extends Fragment {

    private NavController navController;

    private Button firstFragmentBtn, secondFragmentBtn, thirdFragmentBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        initView(view);

    }

    private void initView(View view) {
        firstFragmentBtn = view.findViewById(R.id.firstFragmentBtn);
        secondFragmentBtn = view.findViewById(R.id.secondFragmentBtn);
        thirdFragmentBtn = view.findViewById(R.id.thirdFragmentBtn);

        firstFragmentBtn.setOnClickListener(v ->    navController.navigate(R.id.action_fragmentMain_to_fragmentPageFirst));
        secondFragmentBtn.setOnClickListener(v ->   navController.navigate(R.id.action_fragmentMain_to_fragmentPageSecond));
        thirdFragmentBtn.setOnClickListener(v ->    navController.navigate(R.id.action_fragmentMain_to_fragmentPageThird));
    }

}