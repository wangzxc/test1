package com.example.day05_multi.bean;

import java.util.ArrayList;

public class Data {
/**
 *  title
 * label  
 * source
 * comment_count,������
 * publish_time  ʱ���
 * share_url     �����ַ
 * 
 * large_image_list  ��ͼ�б�
 * middle_image      ��ͼ�б�
 * image_list		   Сͼ�б�
 * 					��ͼ���б�
 */
	public String title;
	public String label;
	public String source;
	public String comment_count;
	
	public String publish_time;
	public String share_url;
//	
	public ArrayList<Large> large_image_list;
//	public ArrayList<Middle> middle_image;
	public MiddleImage middle_image;
	public ArrayList<Little> image_list;
}
