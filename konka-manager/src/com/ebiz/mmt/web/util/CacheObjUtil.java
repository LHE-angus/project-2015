package com.ebiz.mmt.web.util;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ebiz.mmt.domain.SysObjData;
import com.ebiz.mmt.service.Facade;

/**
 * 缓存java 对象到数据库
 * @author TUDP
 * @version 2015-1-20 下午2:25:22
 */
public class CacheObjUtil {
	
	private static Logger log = LoggerFactory.getLogger(CacheObjUtil.class);
	
	private Facade facade;
	
	private CallInterface callInterface;  
	
	private Integer day; 

	private Integer objTime;
	
	private String objName;
	
	private String objKey;
	
	private Map map;//回调函数 参数传递
	

	/**
	 * 
	 * @param facade
	 */
	public CacheObjUtil(Facade facade){
		this.facade=facade;
	}
	
	/**
	 * 
	 * @param facade
	 * @param day
	 * @param objTime
	 * @param objName
	 * @param objKey
	 */
	public CacheObjUtil(Facade facade,Integer day,Integer objTime,String objName,String objKey){
		this.facade=facade;
		this.day =day;
		this.objTime =objTime;
		this.objName = objName;
		this.objKey = objKey;
	}
 
	/**
	 * 
	 * @param obj
	 * @param objTime 对象有效时间 单位秒
	 * @param objName 对象名称
	 * @param objKey 关键字
	 * @return
	 */
	public Long saveObj(Object obj, Integer objTime, String objName, String objKey) {
		SysObjData sysdata = new SysObjData();
		sysdata.setObj_name(objName);// 对象名称
		sysdata.setObj_key(objKey);// 对象关键字
		sysdata.setObj_content(SerializeUtil.objectToByteArray(obj));// 序列化java对象转换成 byte[]
		if(objTime == null){
			objTime = 3600 * 24;
		}
		sysdata.setObj_time(objTime);
		Long id = this.getFacade().getSysObjDataService().createSysObjData(sysdata);
		return id;
	}
	
	/**
	 * 
	 * @param day 对象创建 多少天内有效，null不判断创建日期 ， 0：当天有效，超过23:59:59秒失效 
	 * @param objName
	 * @param objKey
	 * @return
	 */
	public Object getObj(Integer day, String objName, String objKey) { 
		Object obj = null;
		SysObjData sysdata = new SysObjData();
		sysdata.setObj_name(objName);// 对象名称
		sysdata.setObj_key(objKey);// 对象关键字 
		// 根据对象名称 关键字 条件查询对象
		List<SysObjData> objList = this.getFacade().getSysObjDataService().getSysObjDataList(sysdata);
		// 判断是否有对象
		if (objList != null && objList.size() > 0) {
			sysdata = new SysObjData();
			sysdata = objList.get(0);
			if(sysdata.getDay()!=null&&day!=null){
				if(sysdata.getDay().intValue()>day.intValue()){//过期 删除
					 SysObjData entity= new SysObjData();
					 entity.setId(sysdata.getId());
					 this.getFacade().getSysObjDataService().removeSysObjData(sysdata);
					 return null;
				} 
			}
			obj = SerializeUtil.byteArrayToObject(sysdata.getObj_content());			 
		}
		return obj;
	}
	
	public Object cache() {
		 Object obj =null;
		 try{
		  obj = this.getObj(day, objName, objKey);
		  if(obj == null){//缓存对象为空 ，调用对象接口方法;
		      obj = callInterface.getObj(map); 
		      if(obj !=null) { //非空对象保存到数据库
		    	this.saveObj(obj, objTime, objName, objKey);
		      }
		  }
		 }catch(Exception ex){
			 log.info("缓存失败------------>");
			 ex.printStackTrace();
		 }
		 return obj;
	 }  

	public Facade getFacade() {
		return facade;
	}

	public void setFacade(Facade facade) {
		this.facade = facade;
	}
	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	public Integer getObjTime() {
		return objTime;
	}

	public void setObjTime(Integer objTime) {
		this.objTime = objTime;
	}

	public String getObjName() {
		return objName;
	}

	public void setObjName(String objName) {
		this.objName = objName;
	}

	public String getObjKey() {
		return objKey;
	}

	public void setObjKey(String objKey) {
		this.objKey = objKey;
	}
	
	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public CallInterface getCallInterface() {
		return callInterface;
	}

	public void setCallInterface(CallInterface callInterface) {
		this.callInterface = callInterface;
	}


}
