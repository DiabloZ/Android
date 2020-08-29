package com.example.example_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    final String LOG_TAG = "Main_logs";

    private EditText etSendToSecondActivity;
    private Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initElem();
    }

    private void initElem() {
        etSendToSecondActivity = findViewById(R.id.etSendToSecondActivity);
        btnSend = findViewById(R.id.btnSend);

        btnSend.setOnClickListener(this);
        Log.d(LOG_TAG, "Элементы инизиализированы");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSend:
                String sendText = etSendToSecondActivity.getText().toString();
                SecondActivity.startActivity(this, sendText);
                Log.d(LOG_TAG, "btnSend was clicked");
                return;
            default:
                return;
        }
    }
}
