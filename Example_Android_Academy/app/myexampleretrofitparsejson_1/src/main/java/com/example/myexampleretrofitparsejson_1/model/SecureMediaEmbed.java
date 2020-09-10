package com.example.myexampleretrofitparsejson_1.model;

import com.google.gson.annotations.SerializedName;

public class SecureMediaEmbed{

	@SerializedName("scrolling")
	private boolean scrolling;

	@SerializedName("media_domain_url")
	private String mediaDomainUrl;

	@SerializedName("width")
	private int width;

	@SerializedName("content")
	private String content;

	@SerializedName("height")
	private int height;

	public boolean isScrolling(){
		return scrolling;
	}

	public String getMediaDomainUrl(){
		return mediaDomainUrl;
	}

	public int getWidth(){
		return width;
	}

	public String getContent(){
		return content;
	}

	public int getHeight(){
		return height;
	}
}