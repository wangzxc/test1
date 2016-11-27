package com.example.day05_multi.application;

import android.app.Application;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

public class App extends Application {
	@Override
	public void onCreate() {
		super.onCreate();
		//配置ImageLoader
		
//		Runtime.getRuntime().maxMemory()/8;
		
		init();
	}

	private void init() {
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				getApplicationContext())
				.memoryCacheExtraOptions(480, 800)
				// max width, max height£¬¼´±£´æµÄÃ¿¸ö»º´æÎÄ¼þµÄ×î´ó³¤¿í
				.threadPoolSize(3)
				// Ïß³Ì³ØÄÚ¼ÓÔØµÄÊýÁ¿
				.threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024))//缓存一般会用总总内存德1/8
				// You can pass your own memory cache
				// implementation/Äã¿ÉÒÔÍ¨¹ý×Ô¼ºµÄÄÚ´æ»º´æÊµÏÖ
				.memoryCacheSize(2 * 1024 * 1024)
				.discCacheSize(50 * 1024 * 1024)
				.discCacheFileNameGenerator(new Md5FileNameGenerator())
				// ½«±£´æµÄÊ±ºòµÄURIÃû³ÆÓÃMD5 ¼ÓÃÜ
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				.discCacheFileCount(100) // »º´æµÄÎÄ¼þÊýÁ¿
				.defaultDisplayImageOptions(DisplayImageOptions.createSimple())
				.imageDownloader(
						new BaseImageDownloader(getApplicationContext(),
								5 * 1000, 30 * 1000)) // connectTimeout (5 s),
														// readTimeout (30
														// s)³¬Ê±Ê±¼ä
				.writeDebugLogs() // Remove for release app
				.build();
		ImageLoader.getInstance().init(config);// È«¾Ö
	}
}
