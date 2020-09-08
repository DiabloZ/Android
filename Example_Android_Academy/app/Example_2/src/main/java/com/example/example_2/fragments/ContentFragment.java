package com.example.example_2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class ContentFragment extends Fragment {
    protected EditText fieldMessage;
    protected Button sendMessageBtn;
    protected ImageView telegramBtn;

    protected Context context;

    protected final String URI = "https://t.me/DiabloZ";
    protected final String LOG_TAG = "second_logs";
    protected final String SUBJECT = "me?";
    protected final String ATTACHMENT = "testAttachment";

    protected final String[] EMAIL = {"test@test.com", "1@2.ra"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void initViews(View view) {
        fieldMessage = view.findViewById(R.id.fieldMessage);

        sendMessageBtn = view.findViewById(R.id.sendMessageBtn);
        telegramBtn = view.findViewById(R.id.telegramBtn);

        telegramBtn.setOnClickListener(v -> sendToHTML());
        sendMessageBtn.setOnClickListener(v -> sendMailFromIntent());
    }

    private void sendToHTML() {
        Uri webpage = Uri.parse(URI);
        context = getContext();
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(context.getPackageManager()) != null)startActivity(intent);
    }

    private void sendMailFromIntent() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_EMAIL, EMAIL);
        intent.putExtra(Intent.EXTRA_SUBJECT, SUBJECT);
        intent.putExtra(Intent.EXTRA_STREAM, ATTACHMENT);
        intent.putExtra(Intent.EXTRA_TEXT, fieldMessage.getText().toString());
        Log.d(LOG_TAG, EMAIL[0]);
        if(intent.resolveActivity(context.getPackageManager()) != null) startActivity(intent);
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

}