package com.example.myexamplenavigationcomponent_2.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavControllerKt;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myexamplenavigationcomponent_2.Money;
import com.example.myexamplenavigationcomponent_2.R;

import java.math.BigDecimal;


public class FragmentPageThirdOne extends Fragment {

    NavController navController;

    String recipient;

    Button backBtnPageThirdOne, nextBtnPageThirdOne;

    EditText etTextPageThirdOne;
    TextView tvPageThirdOne;

    Money money;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            FragmentPageThirdArgs args = FragmentPageThirdArgs.fromBundle(getArguments());
            recipient = args.getRecipient();
            money = args.getAmount();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_page_third_one, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);

        initView(view);
        setOnClick();
        String message = "Send money to " + recipient;
        tvPageThirdOne.setText(message);

    }

    private void initView(View view) {
        backBtnPageThirdOne = view.findViewById(R.id.backBtnPageThirdOne);
        nextBtnPageThirdOne = view.findViewById(R.id.nextBtnPageThirdOne);

        etTextPageThirdOne = view.findViewById(R.id.etTextPageThirdOne);

        tvPageThirdOne = view.findViewById(R.id.tvPageThirdOne);
    }

    private void setOnClick() {
        backBtnPageThirdOne.setOnClickListener(v -> getActivity().onBackPressed());
        nextBtnPageThirdOne.setOnClickListener(v -> goNextFragment());
    }

    private void goNextFragment() {
        if (!TextUtils.isEmpty(etTextPageThirdOne.getText().toString())) {

            money.setAmount(new BigDecimal(etTextPageThirdOne.getText().toString()));

            //navController.navigate(R.id.action_fragmentPageThirdOne_to_fragmentPageThirdOneOne);
            FragmentPageThirdOneDirections.ActionFragmentPageThirdOneToFragmentPageThirdOneOne action =
                    FragmentPageThirdOneDirections.actionFragmentPageThirdOneToFragmentPageThirdOneOne(money);
            navController.navigate(action);

        } else {
            Toast.makeText(getActivity(), "blet", Toast.LENGTH_SHORT).show();
        }
    }
}