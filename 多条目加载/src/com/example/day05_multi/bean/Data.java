package com.example.day05_multi.bean;

import java.util.ArrayList;

public class Data {
/**
 *  title
 * label  
 * source
 * comment_count,评论数
 * publish_time  时间戳
 * share_url     详情地址
 * 
 * large_image_list  大图列表
 * middle_image      中图列表
 * image_list		   小图列表
 * 					无图的列表
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
