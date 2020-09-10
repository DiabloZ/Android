package com.example.myexampleretrofitparsejson_1.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Obfuscated{

	@SerializedName("resolutions")
	private List<ResolutionsItem> resolutions;

	@SerializedName("source")
	private Source source;

	public List<ResolutionsItem> getResolutions(){
		return resolutions;
	}

	public Source getSource(){
		return source;
	}
}