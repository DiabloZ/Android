package com.example.myexampleretrofitparsejson_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myexampleretrofitparsejson_1.constatns.Constants;
import com.example.myexampleretrofitparsejson_1.model.ChildrenItem;
import com.example.myexampleretrofitparsejson_1.model.Response;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private Button btnGetData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGetData = findViewById(R.id.btnGetData);
        btnGetData.setOnClickListener(view -> getRetrofit());
    }


    private void getRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RedditAPI redditAPI = retrofit.create(RedditAPI.class);
        Call<Response> responseCall = redditAPI.getData();

        responseCall.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                Log.d(Constants.LOG_TAG, "onResponse: Server response: " + response.toString());
                Log.d(Constants.LOG_TAG, "onResponse: received information: " + response.body().toString());

                List<ChildrenItem> childrenItems = response.body().getData().getChildren();
                for (int i = 0; i < childrenItems.size(); i++) {
                    Log.d(Constants.LOG_TAG,
                            "kind: " + childrenItems.get(i).getKind()+"\n" +
                                    "context_mode: " + childrenItems.get(i).getData().isContestMode()+"\n" +
                                    "kind: " + childrenItems.get(i).getData().getSubreddit()+"\n" +
                                    "kind: " + childrenItems.get(i).getData().getAuthor()+"\n" +
                                    "\n____________________________________________________\n\n");
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Log.e(Constants.LOG_TAG, "onFailure: Something went wrong: " + t.getMessage());
                Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
