//package com.ebiz.mmt.web.struts.manager.admin;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.net.MalformedURLException;
//import java.net.URL;
//
//public class IpXYUtils {
//
//
//
//		/**
//		* 获取指定IP对应的经纬度（为空返回当前机器经纬度）
//		*
//		* @param ip
//		* @return
//		*/
//		public static String[] getIPXY(String ip) {
//		 
//		String ak = "4ea8e13a34f8a075af75475fa94a2de1";
//		if (null == ip) {
//		ip = "";
//		}
//		 
//		try {
//		 
//		URL url = new URL("http://api.map.baidu.com/location/ip?ak=" + ak
//		+ "&ip=" + ip + "&coor=bd09ll");
//		InputStream inputStream = url.openStream();
//		InputStreamReader inputReader = new InputStreamReader(inputStream);
//		BufferedReader reader = new BufferedReader(inputReader);
//		StringBuffer sb = new StringBuffer();
//		String str;
//		do {
//		str = reader.readLine();
//		sb.append(str);
//		} while (null != str);
//		 
//		 
//		str = sb.toString();
//		if (null == str || str.isEmpty()) {
//		return null;
//		}
//		 
//		 
//		// 获取坐标位子
//		int index = str.indexOf("point");
//		int end = str.indexOf("}}", index);
//		 
//		 
//		if (index == -1 || end == -1) {
//		return null;
//		}
//		 
//		 
//		str = str.substring(index - 1, end + 1);
//		if (null == str || str.isEmpty()) {
//		return null;
//		}
//		 
//		 
//		String[] ss = str.split(":");
//		if (ss.length != 4) {
//		return null;
//		}
//		 
//		 
//		String x = ss[2].split(",")[0];
//		String y = ss[3];
//		 
//		 
//		x = x.substring(x.indexOf("\"") + 1, x.indexOf("\"", 1));
//		y = y.substring(y.indexOf("\"") + 1, y.indexOf("\"", 1));
//		 
//		 
//		return new String[] { x, y };
//		 
//		 
//		} catch (MalformedURLException e) {
//		e.printStackTrace();
//		} catch (IOException e) {
//		e.printStackTrace();
//		}
//		 
//		 
//		return null;
//		}
//		/**
//		* 获取指定经纬度的地址
//		*
//		* @param xy
//		* @return
//		*/
//		public static String getAdrees(String xy) {
//		 
//		String ak = "4ea8e13a34f8a075af75475fa94a2de1";
//		if (null == xy) {
//		xy = "";
//		return null;
//		}
//		 
//		try {
//		 
//		URL url = new URL("http://api.map.baidu.com/geocoder/v2/?" +
//				"ak=4ea8e13a34f8a075af75475fa94a2de1&callback=renderReverse&output=json&pois=0&location="+xy);
//		InputStream inputStream = url.openStream();
//		InputStreamReader inputReader = new InputStreamReader(inputStream);
//		BufferedReader reader = new BufferedReader(inputReader);
//		StringBuffer sb = new StringBuffer();
//		String str;
//		do {
//		str = reader.readLine();
//		sb.append(str);
//		} while (null != str);
//		 
//		 
//		str = sb.toString();
//		if (null == str || str.isEmpty()) {
//		return null;
//		}
//		 
//		 
//		// 获取坐标位子
//		int index = str.indexOf("formatted_address");
//		int end = str.indexOf("business", index);
//		 
//		 
//		if (index == -1 || end == -1) {
//		return null;
//		}
//		 
//		 
//		str = str.substring(index + 20, end - 3);
//		return str;
//		 
//		 
//		} catch (MalformedURLException e) {
//		e.printStackTrace();
//		} catch (IOException e) {
//		e.printStackTrace();
//		}
//		 
//		 
//		return null;
//		}
//		
//		
//		public static void main(String[] args) {
//			 String m=	getAdrees("31.86694226,117.28269909");
//			//System.out.println(m);
//			String[] mm=getIPXY("36.40.230.47");
//			//System.out.println(mm[0]);
//			//System.out.println(mm[1]);
//		    //System.out.println(getAdrees(mm[1]+","+mm[0]));
//			}
//	}

