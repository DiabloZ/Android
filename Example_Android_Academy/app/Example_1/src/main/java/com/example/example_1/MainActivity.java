package com.example.example_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final String LOG_TAG = "main_logs";

    private EditText sendToEditText;
    private Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initElem();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSend:
                String sendText = sendToEditText.getText().toString();
                startActivity(SecondActivity.startActivity(this, sendText));
                Log.d(LOG_TAG, "btnSend was clicked");
                return;
            default:
                return;
        }
    }

    private void initElem() {
        sendToEditText = findViewById(R.id.sendToEditText);
        btnSend = findViewById(R.id.btnSend);

        btnSend.setOnClickListener(this);
        Log.d(LOG_TAG, "Элементы инизиализированы");
    }
}
