package com.example.example_4.classes.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.example_4.R;
import com.example.example_4.classes.Handlers.DB.MemeDatabase;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PlaceholderClass.getInstance(this);
        MemeDatabase memeDatabase = MemeDatabase.getInstance(this);

    }
}
