package com.example.myexampleretrofitparsejson_1.model;

import com.google.gson.annotations.SerializedName;

public class Gildings{

	@SerializedName("gid_3")
	private int gid3;

	@SerializedName("gid_2")
	private int gid2;

	@SerializedName("gid_1")
	private int gid1;

	public int getGid3(){
		return gid3;
	}

	public int getGid2(){
		return gid2;
	}

	public int getGid1(){
		return gid1;
	}
}