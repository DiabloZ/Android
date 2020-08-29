package com.example.example_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    private final String LOG_TAG = "second_logs";
    private final String SUBJECT = "me?";
    private final String ATTACHMENT = "testAttachment";

    private final String[] EMAIL = {"test@test.com", "1@2.ra"};

    private TextView secondTextView;
    private Button secondButtonSendEmail;

    private String textFromFirstActivity;

    private static final String KEY_SECOND = "KEY_SECOND";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initElem();
        textFromFirstActivity =  getIntent().getStringExtra(KEY_SECOND);
        secondTextView.setText(textFromFirstActivity);
    }


    public static Intent startActivity(Context context, String message) {
        return new Intent(context, SecondActivity.class)
                .putExtra(KEY_SECOND, message);
    }




    private void initElem() {
        secondTextView = findViewById(R.id.tvSecondGetMessage);
        secondButtonSendEmail = findViewById(R.id.btnSecondSendEmail);

        secondButtonSendEmail.setOnClickListener(v -> sendMailFromIntent());
        Log.d(LOG_TAG, "Элементы инизиализированы");
    }


    private void sendMailFromIntent() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
            intent.putExtra(Intent.EXTRA_EMAIL, EMAIL);
            intent.putExtra(Intent.EXTRA_SUBJECT, SUBJECT);
            intent.putExtra(Intent.EXTRA_STREAM, ATTACHMENT);
            intent.putExtra(Intent.EXTRA_TEXT, secondTextView.getText().toString());
        Log.d(LOG_TAG, EMAIL[0]);
        if(intent.resolveActivity(getPackageManager()) != null) startActivity(intent);
    }
}