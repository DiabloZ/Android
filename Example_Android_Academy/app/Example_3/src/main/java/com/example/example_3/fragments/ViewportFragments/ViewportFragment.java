package com.example.example_3.fragments.ViewportFragments;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.example.example_3.JSON.Meme;
import com.example.example_3.R;
import com.example.example_3.classes.Handlers.Date.DateHandler;
import com.example.example_3.classes.Handlers.FitText.FitTextPublisher;
import com.example.example_3.classes.Handlers.Orientation.OrientationPublisher;
import com.example.example_3.classes.Views.PlaceholderClass;

public class ViewportFragment extends Fragment {
    private ImageView imageView;
    private TextView topicTextView, headingTextView, contentTextView, dateTextView;
    private Meme meme;
    private OrientationPublisher orientationPublisher = OrientationPublisher.getInstance();
    private FitTextPublisher fitTextPublisher = FitTextPublisher.getInstance();
    private boolean fitText = true;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewportFragmentArgs args = ViewportFragmentArgs.fromBundle(getArguments());
        meme = args.getMeme();
        sendOrientation();
        sendFitText();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_viewport, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initElems(view);
        setContentFromElems();
    }

    private void sendFitText() {
        fitTextPublisher.notify(fitText);
    }

    private void sendOrientation() {
        orientationPublisher.notify(getResources()
                .getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE);
    }

    private void initElems(View view) {
        imageView = view.findViewById(R.id.imageView);
        topicTextView = view.findViewById(R.id.topicTextView);
        headingTextView = view.findViewById(R.id.headingTextView);
        contentTextView = view.findViewById(R.id.contentTextView);
        dateTextView = view.findViewById(R.id.dateTextView);
    }

    private void setContentFromElems() {
        DateHandler dateHandler = new DateHandler(meme.getDate());

        topicTextView.setText(meme.getAuthor());
        headingTextView.setText(String.valueOf(meme.getId()));
        contentTextView.setText(meme.getDescription());
        dateTextView.setText(dateHandler.getCompletedLine());
        String url = meme.getGifURL();

        Glide.with(imageView)
                .load(url)
                .fitCenter()
                .apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))
                .placeholder(PlaceholderClass.getInstance())
                .transition(DrawableTransitionOptions.withCrossFade(500))
                .override(Resources.getSystem().getDisplayMetrics().widthPixels, (int) (Resources.getSystem().getDisplayMetrics().heightPixels))
                .into(imageView);

    }
}
