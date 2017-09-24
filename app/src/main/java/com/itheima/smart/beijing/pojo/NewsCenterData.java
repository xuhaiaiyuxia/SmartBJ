package com.itheima.smart.beijing.pojo;

import java.util.List;

/**
 * 新闻中心的数据
 * @author Administrator
 *
 */
public class NewsCenterData {
	public int retcode;
	public List<Data> data;
	public List<String> extend;
	public class Data{
		public class Children {
			public String id;//	10007
			public String title;//	北京
			public String type;//	1
			public String url;//	/10007/list_1.json
		}
		public List<Children> children;
		public String id;//	10000
		public String title;//	新闻
		public String type;//	1
		
		public String url;//	/10006/list_1.json
		public String url1;//	/10007/list1_1.json
	
		public String dayurl;//	
		public String excurl;//
		
		public String weekurl;
	}
}
