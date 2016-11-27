package com.example.day05_multi.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 网络工具类
 * 
 * @author gjl
 * 
 */
public class MyHttpUtils {
	// 判断是否有网
	public static boolean hasNet(Context context) {
		//获取链接管理器
		ConnectivityManager manager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		//获取网络信息
		NetworkInfo networkInfo = manager.getActiveNetworkInfo();
		//返回是否有网可用
		return networkInfo.isAvailable();
	}

	// 判断网络什么类型
	public int netType(Context context) {
		//获取链接管理器
		ConnectivityManager manager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		//获取网络信息
		NetworkInfo networkInfo = manager.getActiveNetworkInfo();
		//返回网络类型
		return networkInfo.getSubtype();
	}
}
