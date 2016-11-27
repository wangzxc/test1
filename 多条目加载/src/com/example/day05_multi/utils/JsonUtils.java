package com.example.day05_multi.utils;

import com.example.day05_multi.bean.News;
import com.google.gson.Gson;

public class JsonUtils {
	public static News parseJson(String json){
		Gson gson = new Gson();
		return gson.fromJson(json, News.class);
	}
}
