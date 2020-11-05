package com.example.example_4.JSON;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Meme implements Parcelable {

	@SerializedName("date")
	private String date;

	@SerializedName("previewURL")
	private String previewURL;

	@SerializedName("author")
	private String author;

	@SerializedName("description")
	private String description;

	@SerializedName("type")
	private String type;

	@SerializedName("videoSize")
	private int videoSize;

	@SerializedName("gifURL")
	private String gifURL;

	@SerializedName("videoPath")
	private String videoPath;

	@SerializedName("videoURL")
	private String videoURL;

	@SerializedName("fileSize")
	private int fileSize;

	@SerializedName("gifSize")
	private int gifSize;

	@SerializedName("commentsCount")
	private int commentsCount;

	@SerializedName("width")
	private String width;

	@SerializedName("votes")
	private int votes;

	@SerializedName("id")
	private int id;

	@SerializedName("height")
	private String height;

	@SerializedName("canVote")
	private boolean canVote;

	public String getDate(){
		return date;
	}

	public String getPreviewURL(){
		return previewURL;
	}

	public String getAuthor(){
		return author;
	}

	public String getDescription(){
		return description;
	}

	public String getType(){
		return type;
	}

	public int getVideoSize(){
		return videoSize;
	}

	public String getGifURL(){
		return gifURL;
	}

	public String getVideoPath(){
		return videoPath;
	}

	public String getVideoURL(){
		return videoURL;
	}

	public int getFileSize(){
		return fileSize;
	}

	public int getGifSize(){
		return gifSize;
	}

	public int getCommentsCount(){
		return commentsCount;
	}

	public String getWidth(){
		return width;
	}

	public int getVotes(){
		return votes;
	}

	public int getId(){
		return id;
	}

	public String getHeight(){
		return height;
	}

	public boolean isCanVote(){
		return canVote;
	}

	public Meme(int id, int votes, int commentsCount, String width, String height, String description, String date, String author, String gifURL, String previewURL) {
		this.id = id;
		this.votes = votes;
		this.commentsCount = commentsCount;
		this.width = width;
		this.height = height;
		this.description = description;
		this.date = date;
		this.author = author;
		this.gifURL = gifURL;
		this.previewURL = previewURL;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int i) {

	}
}