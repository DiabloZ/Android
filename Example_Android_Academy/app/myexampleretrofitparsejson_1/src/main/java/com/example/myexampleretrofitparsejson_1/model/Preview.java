package com.example.myexampleretrofitparsejson_1.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Preview{

	@SerializedName("images")
	private List<ImagesItem> images;

	@SerializedName("enabled")
	private boolean enabled;

	@SerializedName("reddit_video_preview")
	private RedditVideoPreview redditVideoPreview;

	public List<ImagesItem> getImages(){
		return images;
	}

	public boolean isEnabled(){
		return enabled;
	}

	public RedditVideoPreview getRedditVideoPreview(){
		return redditVideoPreview;
	}
}