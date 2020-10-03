package com.example.example_3.fragments.StartFragment;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.example_3.JSON.Meme;
import com.example.example_3.R;
import com.example.example_3.classes.Handlers.FitText.FitTextPublisher;
import com.example.example_3.classes.Handlers.Orientation.OrientationPublisher;
import com.example.example_3.classes.Views.RecyclerAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FirstFragment extends Fragment{

    private OrientationPublisher publisherOrientation = OrientationPublisher.getInstance();
    private FitTextPublisher publisherFitText = FitTextPublisher.getInstance();
    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;

    private Button button;

    private ArrayList<Meme> memeList;
    private RequestQueue ffRequestQueue;
    private boolean landscape;
    private boolean fitText = false;
    private static final String TAG = "FirstFragment";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        landscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
        sendOrientation();
        sendFitText();
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        button = view.findViewById(R.id.button);
        button.setOnClickListener(this::goToCreators);
        setArrayList();
        parseJSON();

    }

    private void sendFitText() {publisherFitText.notify(fitText);}

    private void sendOrientation() {
        publisherOrientation.notify(landscape);
    }

    private void goToCreators(View view) {
        NavDirections action = FirstFragmentDirections.actionFirstFragmentToCreatorsFragment();
        Navigation.findNavController(view).navigate(action);
    }

    private void setArrayList() {
        memeList = new ArrayList<>();

        ffRequestQueue = Volley.newRequestQueue(requireContext());


    }

    private void parseJSON() {
        int page = 0;
        String url = String.format("https://developerslife.ru/latest/" + "%1$s" + "?json=true", page);
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
            }
            setupRecyclerView(getContext(), requireView());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void setupRecyclerView(Context context, View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerAdapter = new RecyclerAdapter(memeList);


        if (landscape) {

            recyclerView.setLayoutManager(new GridLayoutManager(context, 2));


        } else {

            recyclerView.setLayoutManager(new LinearLayoutManager(context));
        }

        recyclerView.setAdapter(recyclerAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(context, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
    }
}