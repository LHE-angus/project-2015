package com.ebiz.mmt.web.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/** 
 * @author tudp
 * @version 2013-09-15
 */
public class ShoppingCarJsonUtil {

	public String toGoodsJson(String goods_id, String md_name,
			String buy_num, String price, String img_url,String p_index,String service_ids) {
		String row = "{\"goods_id\":\"" + goods_id + "\",\"md_name\":\""
				+ md_name + "\",\"buy_num\":\"" + buy_num + "\",\"price\":\""
				+ price + "\",\"img_url\":\"" + img_url + "\",\"p_index\":\"" + p_index + "\",\"service_ids\":\"" + service_ids + "\"}";
		return row;
	}

	public String toGoodsJsonsStr(List<HashMap<String, String>> rows) {		
		String str = "[";
		if (rows != null && rows.size() > 0) {
			HashMap<String, String> map=new HashMap<String, String>();
			for (int i = 0; i < rows.size(); i++) {
				map=new HashMap<String, String>();
				map=rows.get(i);
				String goods_id=map.get("goods_id");
				String md_name=map.get("md_name");
				String buy_num=map.get("buy_num");
				String price=map.get("price");
				String img_url=map.get("img_url");
				String p_index=map.get("p_index");
				String service_ids=map.get("service_ids");
				
				str +=toGoodsJson(goods_id,md_name,buy_num,price,img_url,p_index,service_ids);
				if (i +1 < rows.size()) {
					str += ",";
				}
			}
		}else{
			return "";
		}
		str += "]";
		return str;
	}

	public static List<HashMap<String, String>> jsonToList(String data) {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		try {
			JSONArray array;
			try {
				array = new JSONArray(data);
				HashMap<String, String> map=new HashMap<String, String>();
				for (int i = 0; i < array.length(); i++) {
					JSONObject ob = new JSONObject();
					map=new HashMap<String, String>();
					ob = (JSONObject) array.get(i);
					String goods_id=ob.getString("goods_id");
					String md_name=ob.getString("md_name");
					String buy_num=ob.getString("buy_num");
					String price=ob.getString("price");
					String img_url=ob.getString("img_url");
					String p_index=ob.getString("p_index");
					String service_ids=ob.getString("service_ids");
					map.put("goods_id", goods_id);
					map.put("md_name", md_name);
					map.put("buy_num", buy_num);
					map.put("price", price);
					map.put("img_url", img_url); 
					map.put("p_index", p_index); 
					map.put("service_ids", service_ids); 
					list.add(map);
				}
				return list;
			} catch (JSONException e) {
				e.printStackTrace();
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<HashMap<String, String>> joinShoppingCarList(List<HashMap<String, String>> cookielist, List<HashMap<String, String>>  tableList) {
		List<HashMap<String, String>> list=cookielist;
		if(tableList!=null&&tableList.size()>0){
			if(cookielist!=null&&cookielist.size()>0){
				String goods_ids=",";
				for(HashMap<String, String> map:cookielist){
					goods_ids+=map.get("goods_id")+",";
				}
				for(HashMap<String, String> tableMap:tableList){
					String goods_id = ","+tableMap.get("goods_id")+",";
					if(goods_ids.indexOf(goods_id)==-1){
					 list.add(tableMap);
					}
				}
				
			}else{
			 list = tableList;
			}			
		} 
		return list;
	}
	
	public static void main(String[] args) throws Exception {  
	}
}