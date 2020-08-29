package com.example.example_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final String LOG_TAG = "main_logs";

    private EditText sendToEditText;
    private Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initElem();
    }


    private void initElem() {
        sendToEditText = findViewById(R.id.sendToEditText);
        btnSend = findViewById(R.id.btnSend);

        btnSend.setOnClickListener(view -> {
            String sendText = sendToEditText.getText().toString();
            startActivity(SecondActivity.startActivity(MainActivity.this, sendText));
            Log.d(LOG_TAG, "btnSend was clicked");
        });
        Log.d(LOG_TAG, "Элементы инизиализированы");
    }
}
