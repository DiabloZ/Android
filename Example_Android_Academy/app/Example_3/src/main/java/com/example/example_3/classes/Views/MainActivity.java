package com.example.example_3.classes.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.example_3.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PlaceholderClass.getInstance(this);
    }
}
