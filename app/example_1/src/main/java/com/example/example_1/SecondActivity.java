package com.example.example_1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {
    final String LOG_TAG = "Second_logs";
    final String[] EMAIL = {"test@test.com", "1@2.ra"};
    final String SUBJECT = "me?";
    final String ATTACHMENT = "testAttachment";

    private TextView tvSecondGetMessage;
    private Button   btnSecondSendEmail;

    private String textFromFirstActivity;

    private static final String KEY_SECOND = "KEY_SECOND";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initElem();
        getDataFromIntent();
        useDataFromIntent();

    }

    private void useDataFromIntent() {
        tvSecondGetMessage.setText(textFromFirstActivity);
    }

    private void getDataFromIntent() {
        Intent intent = getIntent();
        textFromFirstActivity = intent.getStringExtra(KEY_SECOND);
    }


    private void initElem() {
        tvSecondGetMessage = findViewById(R.id.tvSecondGetMessage);
        btnSecondSendEmail = findViewById(R.id.btnSecondSendEmail);

        btnSecondSendEmail.setOnClickListener(this);
        Log.d(LOG_TAG, "Элементы инизиализированы");
    }

    public static void startActivity(Activity activity, String message) {
        Intent intent = new Intent(activity, SecondActivity.class);
        intent.putExtra(KEY_SECOND, message);
        activity.startActivity(intent);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSecondSendEmail:
                sendMailFromIntent();
                return;
            default:
                return;
        }
    }

    private void sendMailFromIntent() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_EMAIL, EMAIL);
        intent.putExtra(Intent.EXTRA_SUBJECT, SUBJECT);
        intent.putExtra(Intent.EXTRA_STREAM, ATTACHMENT);
        intent.putExtra(Intent.EXTRA_TEXT, tvSecondGetMessage.getText().toString());
        Log.d(LOG_TAG, EMAIL[0]);
        if(intent.resolveActivity(getPackageManager()) != null) startActivity(intent);
    }
}