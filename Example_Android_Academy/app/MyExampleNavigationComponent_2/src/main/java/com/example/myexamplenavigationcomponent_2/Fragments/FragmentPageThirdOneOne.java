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
import android.widget.TextView;

import com.example.myexamplenavigationcomponent_2.Money;
import com.example.myexamplenavigationcomponent_2.R;


public class FragmentPageThirdOneOne extends Fragment {

    NavController navController;
    TextView textView;
    Button button;

//    String recipient;
    Money money;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //recipient = getArguments().getString("recipient"); BUNDLE
        FragmentPageThirdOneOneArgs args = FragmentPageThirdOneOneArgs.fromBundle(getArguments());

        money = args.getAmount();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_page_third_one_one, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        initView(view);
    }


    private void initView(View view) {
        textView = view.findViewById(R.id.TextView);
        button = view.findViewById(R.id.button);

        String recipient = money.getRecipient();
        String amount = money.getAmount().toString();

        String confirmationMsg = "You have sent $" + amount +   " to " + recipient
                + money.toString();
        textView.setText(confirmationMsg);
        button.setOnClickListener(v -> navController.navigate(R.id.action_fragmentPageThirdOneOne_to_fragmentMain));
    }
}