package com.example.android_developer_fintech2020_tinkoff.models;

import java.util.List;

public class Response{
	private List<ResultItem> result;
	private int totalCount;

	public void setResult(List<ResultItem> result){
		this.result = result;
	}

	public List<ResultItem> getResult(){
		return result;
	}

	public void setTotalCount(int totalCount){
		this.totalCount = totalCount;
	}

	public int getTotalCount(){
		return totalCount;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"result = '" + result + '\'' + 
			",totalCount = '" + totalCount + '\'' + 
			"}";
		}
}