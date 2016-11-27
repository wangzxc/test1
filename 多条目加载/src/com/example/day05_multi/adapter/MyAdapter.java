package com.example.day05_multi.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.day05_multi.R;
import com.example.day05_multi.bean.Data;
import com.nostra13.universalimageloader.core.ImageLoader;

public class MyAdapter extends BaseAdapter {

	private final int NO_IMAGE = 0;
	private final int LITTLE_IMAGE = 1;
	private final int MIDDLE_IMAGE = 2;
	private final int LARGE_IMAGE = 3;
	private ArrayList<Data> data;
	private Context context;

	// 构造
	public MyAdapter(ArrayList<Data> data, Context context) {
		this.data = data;
		this.context = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// //先不优化
		// convertView = View.inflate(context, resource, root);
		// 获取类别--根据类别使用不同的条目布局
		int type = getItemViewType(position);
		// 取出数据
		Data data_item = data.get(position);
		switch (type) {
		// 无图
		case 0:
			convertView = View.inflate(context, R.layout.item_text, null);
			// 找控件
			TextView item_title = (TextView) convertView
					.findViewById(R.id.item_title);
			// 赋值
			item_title.setText(data_item.title);
			break;
		// 小图
		case 1:
			convertView = View.inflate(context, R.layout.item_little_image,
					null);
			TextView item_title_little = (TextView) convertView
					.findViewById(R.id.item_title);
			ImageView item_right = (ImageView) convertView
					.findViewById(R.id.item_right);
			// 使用IamgeLoader加载网络图片
			ImageLoader instance = ImageLoader.getInstance();
			instance.displayImage(
					data_item.middle_image.url_list.get(0).url, item_right);
			item_title_little.setText(data_item.title);
			break;
		// 中图 3张
		case 2:
			convertView = View.inflate(context, R.layout.item_middle_image,
					null);
			TextView item_title_middle = (TextView) convertView
					.findViewById(R.id.item_title);
			ImageView item_middle1 = (ImageView) convertView
					.findViewById(R.id.item_middle1);
			ImageView item_middle2 = (ImageView) convertView
					.findViewById(R.id.item_middle2);
			ImageView item_middle3 = (ImageView) convertView
					.findViewById(R.id.item_middle3);
			ImageLoader instance1 = ImageLoader.getInstance();
			instance1.displayImage(data_item.image_list.get(0).url_list.get(0).url,
					item_middle1);
			instance1.displayImage(data_item.image_list.get(1).url_list.get(1).url,
					item_middle2);
			instance1.displayImage(data_item.image_list.get(2).url_list.get(2).url,
					item_middle3);
			item_title_middle.setText(data_item.title);
			break;
		// 大图
		case 3:
			Log.d("Adapter", "大图"+data_item.large_image_list.size());
			if (data_item.large_image_list.size()>0) {
				convertView = View
						.inflate(context, R.layout.item_large_image, null);
				ImageView item_large = (ImageView) convertView
						.findViewById(R.id.item_large);
				ImageLoader instance2 = ImageLoader.getInstance();
				instance2.displayImage(data_item.large_image_list.get(0).url,
						item_large);
			}
			break;
		// 默认
		default:
			break;
		}
		return convertView;
	}

	// 类别

	/**
	 * 全部为空，size= 0 无图 data2.image_list data2.middle_image对象
	 * data2.middle_image.url_list 集合 data2.large_image_list
	 * 
	 * 无图
	 *  (data2.image_list==null || data2.image_list.size()==0)&&(data2.middle_image==null || data2.middle_image.url_list==null
	 * data2.middle_image.url_list.size()=0)&&(data2.large_image_list==null||data2.large_image_list.size()=0)
	 * 一张小图 data2.image_list!=null&&data2.image_list.size()>0 
	 * 三张中图 data2.middle_image!=null &&data2.middle_image.url_list!=null &&data2.middle_image.url_list.size()>0
	 */
	@Override
	public int getItemViewType(int position) {
		// 分类
		// 1.取出数据
		int type=0;
		Data data2 = data.get(position);
		// 如果image_list为空，large , middle 没图
		if ((data2.image_list == null || data2.image_list.size() == 0)
				&& (data2.middle_image == null
						|| data2.middle_image.url_list == null || data2.middle_image.url_list
						.size() == 0)
				&& (data2.large_image_list == null || data2.large_image_list
						.size() == 0)) {
			Log.d("Adapter-------------", "无图");
			type= NO_IMAGE;
		} else if (data2.image_list != null && data2.image_list.size() > 0) {
			type= MIDDLE_IMAGE;
			Log.d("Adapter-------------", "三张");
		} else if (data2.middle_image != null
				&& data2.middle_image.url_list != null
				&& data2.middle_image.url_list.size() > 0) {
			Log.d("Adapter-------------", "小图");
			type= LITTLE_IMAGE;
		} else if (data2.large_image_list != null
				&& data2.large_image_list.size() > 0) {
			Log.d("Adapter-------------", "大图");
			type= LARGE_IMAGE;
		}
		//强化大图逻辑
		if (data2.large_image_list!=null && data2.large_image_list.size()>0) {
			Log.d("Adapter-------数据", "large_image_list"+data2.large_image_list);
			type=3;
		}
		return type;
	}
	
	
	

	// 类别数量
	@Override
	public int getViewTypeCount() {
		// TODO Auto-generated method stub
		return 4;
	}

}
