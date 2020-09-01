package com.example.android_developer_fintech2020_tinkoff.models;

public class ResultItem{
	private String date;
	private String previewURL;
	private String author;
	private String description;
	private String type;
	private int videoSize;
	private String gifURL;
	private String videoPath;
	private String videoURL;
	private int fileSize;
	private int gifSize;
	private int commentsCount;
	private String width;
	private int votes;
	private int id;
	private String height;
	private boolean canVote;

	public void setDate(String date){
		this.date = date;
	}

	public String getDate(){
		return date;
	}

	public void setPreviewURL(String previewURL){
		this.previewURL = previewURL;
	}

	public String getPreviewURL(){
		return previewURL;
	}

	public void setAuthor(String author){
		this.author = author;
	}

	public String getAuthor(){
		return author;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}

	public void setVideoSize(int videoSize){
		this.videoSize = videoSize;
	}

	public int getVideoSize(){
		return videoSize;
	}

	public void setGifURL(String gifURL){
		this.gifURL = gifURL;
	}

	public String getGifURL(){
		return gifURL;
	}

	public void setVideoPath(String videoPath){
		this.videoPath = videoPath;
	}

	public String getVideoPath(){
		return videoPath;
	}

	public void setVideoURL(String videoURL){
		this.videoURL = videoURL;
	}

	public String getVideoURL(){
		return videoURL;
	}

	public void setFileSize(int fileSize){
		this.fileSize = fileSize;
	}

	public int getFileSize(){
		return fileSize;
	}

	public void setGifSize(int gifSize){
		this.gifSize = gifSize;
	}

	public int getGifSize(){
		return gifSize;
	}

	public void setCommentsCount(int commentsCount){
		this.commentsCount = commentsCount;
	}

	public int getCommentsCount(){
		return commentsCount;
	}

	public void setWidth(String width){
		this.width = width;
	}

	public String getWidth(){
		return width;
	}

	public void setVotes(int votes){
		this.votes = votes;
	}

	public int getVotes(){
		return votes;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setHeight(String height){
		this.height = height;
	}

	public String getHeight(){
		return height;
	}

	public void setCanVote(boolean canVote){
		this.canVote = canVote;
	}

	public boolean isCanVote(){
		return canVote;
	}

	@Override
 	public String toString(){
		return 
			"ResultItem{" + 
			"date = '" + date + '\'' + 
			",previewURL = '" + previewURL + '\'' + 
			",author = '" + author + '\'' + 
			",description = '" + description + '\'' + 
			",type = '" + type + '\'' + 
			",videoSize = '" + videoSize + '\'' + 
			",gifURL = '" + gifURL + '\'' + 
			",videoPath = '" + videoPath + '\'' + 
			",videoURL = '" + videoURL + '\'' + 
			",fileSize = '" + fileSize + '\'' + 
			",gifSize = '" + gifSize + '\'' + 
			",commentsCount = '" + commentsCount + '\'' + 
			",width = '" + width + '\'' + 
			",votes = '" + votes + '\'' + 
			",id = '" + id + '\'' + 
			",height = '" + height + '\'' + 
			",canVote = '" + canVote + '\'' + 
			"}";
		}

}
