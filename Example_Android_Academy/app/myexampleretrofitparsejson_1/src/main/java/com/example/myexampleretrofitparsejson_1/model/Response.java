package com.example.myexampleretrofitparsejson_1.model;

import com.google.gson.annotations.SerializedName;

public class Response{

	@SerializedName("data")
	private Data data;

	@SerializedName("kind")
	private String kind;

	public Data getData(){
		return data;
	}

	public String getKind(){
		return kind;
	}

	@Override
	public String toString() {
		return "Response{" +
				"data=" + data +
				", kind='" + kind + '\'' +
				'}';
	}
}