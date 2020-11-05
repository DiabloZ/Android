package com.example.example_4.classes.Views;

import android.content.Context;

import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

public class PlaceholderClass {
    private static CircularProgressDrawable circularProgressDrawable;

    public static CircularProgressDrawable getInstance(Context context) {
        if (circularProgressDrawable == null) {
            circularProgressDrawable = new CircularProgressDrawable(context);
            circularProgressDrawable.setStrokeWidth(5f);
            circularProgressDrawable.setCenterRadius(30f);
            circularProgressDrawable.start();
            return circularProgressDrawable;
        }
        return circularProgressDrawable;
    }

    public static CircularProgressDrawable getInstance() {
        return circularProgressDrawable;
    }
}
