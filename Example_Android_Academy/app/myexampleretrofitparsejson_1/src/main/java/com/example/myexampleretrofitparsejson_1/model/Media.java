package com.example.myexampleretrofitparsejson_1.model;

import com.google.gson.annotations.SerializedName;

public class Media{

	@SerializedName("reddit_video")
	private RedditVideo redditVideo;

	@SerializedName("oembed")
	private Oembed oembed;

	@SerializedName("type")
	private String type;

	public RedditVideo getRedditVideo(){
		return redditVideo;
	}

	public Oembed getOembed(){
		return oembed;
	}

	public String getType(){
		return type;
	}
}