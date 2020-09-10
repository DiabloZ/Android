package com.example.myexampleretrofitparsejson_1.model;

import com.google.gson.annotations.SerializedName;

public class Variants{

	@SerializedName("obfuscated")
	private Obfuscated obfuscated;

	public Obfuscated getObfuscated(){
		return obfuscated;
	}
}