package com.example.example_2.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.example_2.R;
import com.example.example_2.constants.Constants;

public class ContentFragment extends Fragment {
    protected EditText fieldMessage;
    protected Button sendMessageBtn;
    protected ImageView telegramBtn, whatsappBtn;

    protected Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_content, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        context = getContext();
    }

    private void initViews(View view) {
        fieldMessage = view.findViewById(R.id.fieldMessage);

        sendMessageBtn = view.findViewById(R.id.sendMessageBtn);
        telegramBtn = view.findViewById(R.id.telegramBtn);
        whatsappBtn = view.findViewById(R.id.whatsappBtn);

        telegramBtn.setOnClickListener(this::sendMessenger);
        whatsappBtn.setOnClickListener(this::sendMessenger);
        sendMessageBtn.setOnClickListener(v -> sendMailFromIntent());
    }

    private void sendMessenger(View view) {
        Uri webPage = Uri.parse("http://uncnownButton.wtf");
        switch (view.getId()){
            case R.id.telegramBtn:
                webPage = Uri.parse(Constants.URI_telegram);
                break;
            case R.id.whatsappBtn:
                webPage = Uri.parse(Constants.URI_whatsapp);
                break;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW, webPage);
        if (intent.resolveActivity(context.getPackageManager()) != null)startActivity(intent);
    }

    private void sendMailFromIntent() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_EMAIL, Constants.EMAIL);
        intent.putExtra(Intent.EXTRA_SUBJECT, Constants.SUBJECT);
        intent.putExtra(Intent.EXTRA_STREAM, Constants.ATTACHMENT);
        intent.putExtra(Intent.EXTRA_TEXT, fieldMessage.getText().toString());
        if(intent.resolveActivity(context.getPackageManager()) != null) startActivity(intent);
    }


}