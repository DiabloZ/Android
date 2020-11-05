package com.example.example_3.JSON;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.example_3.R;
import com.example.example_3.classes.Views.RecyclerAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JSONHandler {
    protected RequestQueue ffRequestQueue;
    private ArrayList<Meme> memeList;
    Context context;
    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;
    private View view;

    public void setView(View view) {
        this.view = view;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ArrayList<Meme> getMemeList() {
        return memeList;
    }

    int page = 0;


    private void parseJSON() {

        String url = String.format("https://developerslife.ru/latest/"+"%1$s"+"?json=true", page);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                this::getDataToArray,
                Throwable::printStackTrace
        );
        ffRequestQueue.add(request);

    }

    private void getDataToArray(JSONObject response) {
        try {
            JSONArray jsonArray = response.getJSONArray("result");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject hit = jsonArray.getJSONObject(i);

                int id = hit.getInt("id");
                int votes = hit.getInt("votes");
                int commentsCount = hit.getInt("commentsCount");
                String width = hit.getString("width");
                String height = hit.getString("height");
                String description = hit.getString("description");
                String date = hit.getString("date");
                String author = hit.getString("author");
                String gifURL = hit.getString("gifURL");
                String previewURL = hit.getString("previewURL");
                memeList.add(new Meme(id, votes, commentsCount, width, height, description, date, author, gifURL, previewURL));
                setupRecyclerView(context, view);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void setArrayList() {
        memeList = new ArrayList<>();

        ffRequestQueue = Volley.newRequestQueue(context);
        parseJSON();

    }

    private void setupRecyclerView(Context context, View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerAdapter = new RecyclerAdapter(getMemeList());

        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(recyclerAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(context, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
    }
}
