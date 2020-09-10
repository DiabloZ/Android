package com.example.myexampleretrofitparsejson_1.model;

import com.google.gson.annotations.SerializedName;

public class LinkFlairRichtextItem{

	@SerializedName("t")
	private String T;

	@SerializedName("e")
	private String E;

	public String getT(){
		return T;
	}

	public String getE(){
		return E;
	}
}