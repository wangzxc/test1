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
		// ��ʼ��
		init();
		// ��ʼ������
		initViews();
		// ��һ���������ݣ�
		getData(Url.tuijian1);
		// �������ݣ����ݷ������Adapter����� type
	}

	private void init() {
		httpUtils = new HttpUtils();
	}

	private void getData(String url) {
		// �ж��Ƿ�����
		boolean hasNet = MyHttpUtils.hasNet(MainActivity.this);
		if (hasNet) {
			// ������������
			getDataFromNet(url);
		} else {
			Toast.makeText(MainActivity.this, "��������", Toast.LENGTH_SHORT)
					.show();
			// ���ߵ����������ý���
		}
	}

	private void getDataFromNet(final String url2) {
		new Thread() {
			public void run() {
				// ʹ��XUtils��������
				httpUtils.send(HttpMethod.GET, url2,
						new RequestCallBack<String>() {

							@Override
							public void onFailure(HttpException arg0,
									String arg1) {
								mHandler.sendEmptyMessage(ERROR);
							}

							@Override
							public void onSuccess(ResponseInfo<String> result1) {
								// �ɹ�
								String json = result1.result;
								System.out.println("json-----" + json);
								News news = JsonUtils.parseJson(json);
//								System.out.println(news.data.get(0).title);
								// ˢ��UI
								// ��Data���͵�Handler
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

	// handler �������е���Ϣ
	Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			int code = msg.what;
			switch (code) {
			case ERROR:
				Toast.makeText(MainActivity.this, "����������", Toast.LENGTH_SHORT)
						.show();
				break;
			case SUCESS:
				Toast.makeText(MainActivity.this, "�ɹ���", Toast.LENGTH_SHORT)
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
