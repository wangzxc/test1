package com.example.day05_multi.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * ���繤����
 * 
 * @author gjl
 * 
 */
public class MyHttpUtils {
	// �ж��Ƿ�����
	public static boolean hasNet(Context context) {
		//��ȡ���ӹ�����
		ConnectivityManager manager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		//��ȡ������Ϣ
		NetworkInfo networkInfo = manager.getActiveNetworkInfo();
		//�����Ƿ���������
		return networkInfo.isAvailable();
	}

	// �ж�����ʲô����
	public int netType(Context context) {
		//��ȡ���ӹ�����
		ConnectivityManager manager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		//��ȡ������Ϣ
		NetworkInfo networkInfo = manager.getActiveNetworkInfo();
		//������������
		return networkInfo.getSubtype();
	}
}
