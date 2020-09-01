package com.example.android_developer_fintech2020_tinkoff;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.JsonReader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.android_developer_fintech2020_tinkoff.models.ResultItem;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SectionLastFragment extends Fragment {
    ImageView imageView1,imageView2,imageView3,imageView4;
    private Context context;
    BufferedReader reader;
    StringBuffer buffer;
    ArrayList dataJson;
    JSONArray jsonArray;

    private final String JSONURL = "https://developerslife.ru/latest/0?json=true";
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    private List<ResultItem> lstMem;
    private RecyclerView recyclerView;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_section_last, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        lstMem = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recyclerView);
        jsonrequest();

    }

    private void jsonrequest() {
        Log.d("testLogs", "start?");

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, JSONURL, null, new Response.Listener<JSONObject>() {
                    JSONObject jsonObject = null;
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            jsonObject = new JSONObject(response.toString());
                            jsonArray = jsonObject.getJSONArray("result");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        for (int i = 0; i < jsonArray.length(); i++) {
                            try {

                                jsonObject = jsonArray.getJSONObject(i);

                                ResultItem resultItem = new ResultItem();
                                resultItem.setId(jsonObject.getInt("id"));
                                resultItem.setDescription(jsonObject.getString("description"));
                                Log.d("testLogs", jsonObject.getString("gifURL"));
                                resultItem.setGifURL(jsonObject.getString("gifURL"));

                                lstMem.add(resultItem);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        setupPreCyclerView(lstMem);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }

                });

// Access the RequestQueue through your singleton class.



        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(jsonObjectRequest);
    }

    private void setupPreCyclerView(List<ResultItem> resultItems) {
        RecyclerViewAdapter myadapter = new RecyclerViewAdapter(getContext(), resultItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(myadapter);
    }
/*
    private ArrayList testMethod(){
        HttpURLConnection connection = null;
        try {
            URL url = new URL(JSONURL);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            InputStream stream = connection.getInputStream();

            reader = new BufferedReader(new InputStreamReader(stream));

            buffer = new StringBuffer();

            String line = "";
            while ((line = reader.readLine()) != null){
                buffer.append(line);
            }

            try {
                JSONObject jsonObject = null;
                jsonObject = new JSONObject(buffer.toString());
                JSONArray jsonArray = jsonObject.getJSONArray("result");

                JSONObject finalObject = jsonArray.getJSONObject(0);

                String gifUrl = finalObject.getString("gifURL");
                String gifDescription = finalObject.getString("description");
                int gifID = finalObject.getInt("id");


                return new ArrayList<>(Arrays.asList(gifUrl, gifDescription, gifID));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
                try {
                    if(reader != null) reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return new ArrayList(Arrays.asList("test"));
    }*/

/*    private void getJSON() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                dataJson = testMethod();
            }
        });



        thread.start();
    }*/

    /*private ArrayList parseJson() {
        try {
            JSONObject jsonObject = new JSONObject(dataJson);
            JSONArray jsonArray = jsonObject.getJSONArray("result");

            JSONObject finalObject = jsonArray.getJSONObject(0);

            String gifUrl = finalObject.getString("gifURL");
            String gifDescription = finalObject.getString("description");
            int gifID = finalObject.getInt("id");


            return new ArrayList<>(Arrays.asList(gifUrl, gifDescription, gifID));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }*/
}