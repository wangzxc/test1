package com.example.day05_multi;

import java.util.ArrayList;

import com.example.day05_multi.adapter.MyAdapter;
import com.example.day05_multi.bean.Data;
import com.example.day05_multi.bean.News;
import com.example.day05_multi.http.Url;
import com.example.day05_multi.utils.JsonUtils;
import com.example.day05_multi.utils.MyHttpUtils;
import com.example.day05_multi.views.XListView;
import com.example.day05_multi.views.XListView.IXListViewListener;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity implements IXListViewListener{

	private ListView listView;
	private String url;
	private HttpUtils httpUtils;
	private final int ERROR = 0;
	private final int SUCESS = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// 初始化
		init();
		// 初始化界面
		initViews();
		// 第一次请求数据，
		getData(Url.tuijian1);
		// 设置数据，根据分类进行Adapter的填充 type
	}

	private void init() {
		httpUtils = new HttpUtils();
	}

	private void getData(String url) {
		// 判断是否有网
		boolean hasNet = MyHttpUtils.hasNet(MainActivity.this);
		if (hasNet) {
			// 进行网络请求
			getDataFromNet(url);
		} else {
			Toast.makeText(MainActivity.this, "请检查网络", Toast.LENGTH_SHORT)
					.show();
			// 或者调到网络设置界面
		}
	}

	private void getDataFromNet(final String url2) {
		new Thread() {
			public void run() {
				// 使用XUtils请求网络
				httpUtils.send(HttpMethod.GET, url2,
						new RequestCallBack<String>() {

							@Override
							public void onFailure(HttpException arg0,
									String arg1) {
								mHandler.sendEmptyMessage(ERROR);
							}

							@Override
							public void onSuccess(ResponseInfo<String> result1) {
								// 成功
								String json = result1.result;
								System.out.println("json-----" + json);
								News news = JsonUtils.parseJson(json);
//								System.out.println(news.data.get(0).title);
								// 刷新UI
								// 将Data发送到Handler
								Message message = mHandler.obtainMessage();
								message.what = SUCESS;
								message.obj = news.data;
								mHandler.sendMessage(message);
							}
						});
			};
		}.start();
	}

	private void initViews() {
		listView = (ListView) findViewById(R.id.ls);
//		listView = new XListView(MainActivity.this);
//		listView.setPullLoadEnable(true);
//		listView.setXListViewListener(this);
	}

	// handler 接受所有德消息
	Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			int code = msg.what;
			switch (code) {
			case ERROR:
				Toast.makeText(MainActivity.this, "网络有问题", Toast.LENGTH_SHORT)
						.show();
				break;
			case SUCESS:
				Toast.makeText(MainActivity.this, "成功！", Toast.LENGTH_SHORT)
						.show();
				ArrayList<Data> data = (ArrayList<Data>) msg.obj;
				Log.d("MainActivity000", "--"+data.size());
				for (int i = 0; i < data.size(); i++) {
					Log.d("MainActivity000", data.get(i).title);
				}
				MyAdapter adapter = new MyAdapter(data, MainActivity.this);
				listView.setAdapter(adapter);

				break;
			default:
				break;
			}
		};
	};

	@Override
	public void onRefresh() {
		getData(Url.redian);
	}

	@Override
	public void onLoadMore() {
		getData(Url.redian);
	}
}
