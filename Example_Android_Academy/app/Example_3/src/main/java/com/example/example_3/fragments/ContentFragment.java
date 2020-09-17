package com.example.example_3.fragments;

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

import com.example.example_3.R;

public class ContentFragment extends Fragment {
    private EditText fieldMessage;
    private Button sendMessageBtn;
    private ImageView telegramBtn, whatsappBtn;

    private static final String URI_telegram = "https://t.me/DiabloZ";
    private static final String URI_whatsapp = "https://wa.me/79680082921";
    private static final String SUBJECT = "me?";
    private static final String ATTACHMENT = "testAttachment";

    private static final String[] EMAIL = {"test@test.com", "1@2.ra"};

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
    }

    private void initViews(View view) {
        fieldMessage = view.findViewById(R.id.fieldMessage);

        sendMessageBtn = view.findViewById(R.id.sendMessageBtn);
        telegramBtn = view.findViewById(R.id.telegramBtn);
        whatsappBtn = view.findViewById(R.id.whatsappBtn);

        telegramBtn.setOnClickListener(v -> sendMessenger(URI_telegram));
        whatsappBtn.setOnClickListener(v -> sendMessenger(URI_whatsapp));
        sendMessageBtn.setOnClickListener(v -> sendMailFromIntent());
    }

    private void sendMessenger(String URL) {
        Uri webPage = Uri.parse(URL);
        Intent intent = new Intent(Intent.ACTION_VIEW, webPage);
        if (intent.resolveActivity(requireContext().getPackageManager()) != null)startActivity(intent);
    }

    private void sendMailFromIntent() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*")
                .putExtra(Intent.EXTRA_EMAIL, EMAIL)
                .putExtra(Intent.EXTRA_SUBJECT, SUBJECT)
                .putExtra(Intent.EXTRA_STREAM, ATTACHMENT)
                .putExtra(Intent.EXTRA_TEXT, fieldMessage.getText().toString());
        if(intent.resolveActivity(requireContext().getPackageManager()) != null) startActivity(intent);
        requireContext();
        requireActivity();
    }


}