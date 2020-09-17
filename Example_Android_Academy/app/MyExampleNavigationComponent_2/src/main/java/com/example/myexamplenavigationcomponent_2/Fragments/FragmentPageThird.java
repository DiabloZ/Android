package com.example.myexamplenavigationcomponent_2.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myexamplenavigationcomponent_2.Money;
import com.example.myexamplenavigationcomponent_2.R;


public class FragmentPageThird extends Fragment {

    NavController navController;

    Button backBtnPageThird, nextBtnPageThird;
    EditText etTextPageThird;

    Money money;
    String recipient;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_page_third, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        initView(view);
    }

    private void initView(View view) {
        etTextPageThird = view.findViewById(R.id.etTextPageThirdOne);

        backBtnPageThird = view.findViewById(R.id.backBtnPageThirdOne);
        nextBtnPageThird = view.findViewById(R.id.nextBtnPageThirdOne);

        backBtnPageThird.setOnClickListener(v -> getActivity().onBackPressed());
        nextBtnPageThird.setOnClickListener(v -> {
            if (!TextUtils.isEmpty(etTextPageThird.getText().toString())) {
                recipient = etTextPageThird.getText().toString();
                money = new Money(recipient, null);
                FragmentPageThirdDirections.ActionFragmentPageThirdToFragmentPageThirdOne action = FragmentPageThirdDirections.actionFragmentPageThirdToFragmentPageThirdOne(money);
                action.setRecipient(recipient);

                navController.navigate(action);
            }
        });

    }
}