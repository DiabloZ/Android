package com.example.example_3.JSON;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Response{

	@SerializedName("result")
	private List<Meme> result;

	@SerializedName("totalCount")
	private int totalCount;

	public List<Meme> getResult(){
		return result;
	}

	public int getTotalCount(){
		return totalCount;
	}
}