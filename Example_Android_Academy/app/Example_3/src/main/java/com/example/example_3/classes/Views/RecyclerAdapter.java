package com.example.example_3.classes.Views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.example.example_3.JSON.Meme;
import com.example.example_3.R;
import com.example.example_3.classes.Handlers.Date.DateHandler;
import com.example.example_3.fragments.StartFragment.FirstFragmentDirections;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    List<Meme> memeList;
    int width, height;

    public RecyclerAdapter(List<Meme> memeList) {
        this.memeList = memeList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_item_constraint, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DateHandler dateHandler = new DateHandler(memeList.get(position).getDate());
        String url = memeList.get(position).getPreviewURL();

        holder.topicTextView.setText(memeList.get(position).getAuthor());
        holder.headingTextView.setText(String.valueOf(memeList.get(position).getId()));
        holder.contentTextView.setText(memeList.get(position).getDescription());
        holder.dateTextView.setText(dateHandler.getCompletedLine());

        Glide.with(holder.imageView)
                .load(url)
                .override(width,height)
                .centerCrop()
                .apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))
                .transition(DrawableTransitionOptions.withCrossFade(500))
                .placeholder(PlaceholderClass.getInstance())
                .into(holder.imageView);
    }


    @Override
    public int getItemCount() {
        return memeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView topicTextView,headingTextView,contentTextView,dateTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);

            topicTextView = itemView.findViewById(R.id.topicTextView);
            headingTextView = itemView.findViewById(R.id.headingTextView);
            contentTextView = itemView.findViewById(R.id.contentTextView);
            dateTextView = itemView.findViewById(R.id.dateTextView);

            itemView.setOnClickListener(view -> {
                goToViewport(memeList.get(getAdapterPosition()),view);
            });
            itemView.setOnLongClickListener(view -> {
                memeList.remove(getAdapterPosition());
                notifyItemRemoved(getAdapterPosition());
                return true;
            });
        }

        @Override
        public void onClick(View view) {
        }
    }

    private void goToViewport(Meme meme, View view) {
        FirstFragmentDirections.ActionFirstFragmentToViewportFragment action = FirstFragmentDirections.actionFirstFragmentToViewportFragment(meme);
        NavController navigation = Navigation.findNavController(view);
        navigation.navigate(action);
    }
}
