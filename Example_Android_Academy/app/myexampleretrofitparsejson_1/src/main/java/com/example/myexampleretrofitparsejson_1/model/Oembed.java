package com.example.myexampleretrofitparsejson_1.model;

import com.google.gson.annotations.SerializedName;

public class Oembed{

	@SerializedName("author_name")
	private String authorName;

	@SerializedName("provider_url")
	private String providerUrl;

	@SerializedName("title")
	private String title;

	@SerializedName("thumbnail_url")
	private String thumbnailUrl;

	@SerializedName("type")
	private String type;

	@SerializedName("version")
	private String version;

	@SerializedName("thumbnail_height")
	private int thumbnailHeight;

	@SerializedName("author_url")
	private String authorUrl;

	@SerializedName("thumbnail_width")
	private int thumbnailWidth;

	@SerializedName("width")
	private int width;

	@SerializedName("html")
	private String html;

	@SerializedName("provider_name")
	private String providerName;

	@SerializedName("height")
	private int height;

	@SerializedName("description")
	private String description;

	@SerializedName("cache_age")
	private long cacheAge;

	@SerializedName("url")
	private String url;

	public String getAuthorName(){
		return authorName;
	}

	public String getProviderUrl(){
		return providerUrl;
	}

	public String getTitle(){
		return title;
	}

	public String getThumbnailUrl(){
		return thumbnailUrl;
	}

	public String getType(){
		return type;
	}

	public String getVersion(){
		return version;
	}

	public int getThumbnailHeight(){
		return thumbnailHeight;
	}

	public String getAuthorUrl(){
		return authorUrl;
	}

	public int getThumbnailWidth(){
		return thumbnailWidth;
	}

	public int getWidth(){
		return width;
	}

	public String getHtml(){
		return html;
	}

	public String getProviderName(){
		return providerName;
	}

	public int getHeight(){
		return height;
	}

	public String getDescription(){
		return description;
	}

	public long getCacheAge(){
		return cacheAge;
	}

	public String getUrl(){
		return url;
	}
}