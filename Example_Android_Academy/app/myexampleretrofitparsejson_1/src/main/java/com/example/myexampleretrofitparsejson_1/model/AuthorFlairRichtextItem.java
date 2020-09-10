package com.example.myexampleretrofitparsejson_1.model;

import com.google.gson.annotations.SerializedName;

public class AuthorFlairRichtextItem{

	@SerializedName("a")
	private String A;

	@SerializedName("e")
	private String E;

	@SerializedName("u")
	private String U;

	public String getA(){
		return A;
	}

	public String getE(){
		return E;
	}

	public String getU(){
		return U;
	}
}