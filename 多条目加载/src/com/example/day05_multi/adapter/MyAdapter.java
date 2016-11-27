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

	// ����
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
		// //�Ȳ��Ż�
		// convertView = View.inflate(context, resource, root);
		// ��ȡ���--�������ʹ�ò�ͬ����Ŀ����
		int type = getItemViewType(position);
		// ȡ������
		Data data_item = data.get(position);
		switch (type) {
		// ��ͼ
		case 0:
			convertView = View.inflate(context, R.layout.item_text, null);
			// �ҿؼ�
			TextView item_title = (TextView) convertView
					.findViewById(R.id.item_title);
			// ��ֵ
			item_title.setText(data_item.title);
			break;
		// Сͼ
		case 1:
			convertView = View.inflate(context, R.layout.item_little_image,
					null);
			TextView item_title_little = (TextView) convertView
					.findViewById(R.id.item_title);
			ImageView item_right = (ImageView) convertView
					.findViewById(R.id.item_right);
			// ʹ��IamgeLoader��������ͼƬ
			ImageLoader instance = ImageLoader.getInstance();
			instance.displayImage(
					data_item.middle_image.url_list.get(0).url, item_right);
			item_title_little.setText(data_item.title);
			break;
		// ��ͼ 3��
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
		// ��ͼ
		case 3:
			Log.d("Adapter", "��ͼ"+data_item.large_image_list.size());
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
		// Ĭ��
		default:
			break;
		}
		return convertView;
	}

	// ���

	/**
	 * ȫ��Ϊ�գ�size= 0 ��ͼ data2.image_list data2.middle_image����
	 * data2.middle_image.url_list ���� data2.large_image_list
	 * 
	 * ��ͼ
	 *  (data2.image_list==null || data2.image_list.size()==0)&&(data2.middle_image==null || data2.middle_image.url_list==null
	 * data2.middle_image.url_list.size()=0)&&(data2.large_image_list==null||data2.large_image_list.size()=0)
	 * һ��Сͼ data2.image_list!=null&&data2.image_list.size()>0 
	 * ������ͼ data2.middle_image!=null &&data2.middle_image.url_list!=null &&data2.middle_image.url_list.size()>0
	 */
	@Override
	public int getItemViewType(int position) {
		// ����
		// 1.ȡ������
		int type=0;
		Data data2 = data.get(position);
		// ���image_listΪ�գ�large , middle ûͼ
		if ((data2.image_list == null || data2.image_list.size() == 0)
				&& (data2.middle_image == null
						|| data2.middle_image.url_list == null || data2.middle_image.url_list
						.size() == 0)
				&& (data2.large_image_list == null || data2.large_image_list
						.size() == 0)) {
			Log.d("Adapter-------------", "��ͼ");
			type= NO_IMAGE;
		} else if (data2.image_list != null && data2.image_list.size() > 0) {
			type= MIDDLE_IMAGE;
			Log.d("Adapter-------------", "����");
		} else if (data2.middle_image != null
				&& data2.middle_image.url_list != null
				&& data2.middle_image.url_list.size() > 0) {
			Log.d("Adapter-------------", "Сͼ");
			type= LITTLE_IMAGE;
		} else if (data2.large_image_list != null
				&& data2.large_image_list.size() > 0) {
			Log.d("Adapter-------------", "��ͼ");
			type= LARGE_IMAGE;
		}
		//ǿ����ͼ�߼�
		if (data2.large_image_list!=null && data2.large_image_list.size()>0) {
			Log.d("Adapter-------����", "large_image_list"+data2.large_image_list);
			type=3;
		}
		return type;
	}
	
	
	

	// �������
	@Override
	public int getViewTypeCount() {
		// TODO Auto-generated method stub
		return 4;
	}

}
