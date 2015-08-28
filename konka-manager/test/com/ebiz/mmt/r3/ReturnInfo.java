package com.ebiz.mmt.r3;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 接口返回信息统一封装
 * 
 */
public class ReturnInfo<T> {

	/**
	 * 执行信息(包括成功信息,异常)
	 */
	private List<ExcuteMsg> msgList = new ArrayList<ExcuteMsg>();


	/**
	 * 是否成功调用
	 */
	private boolean success;

	/**
	 * SAP订单编号
	 */
	private String itemNO = null;

	/**
	 * List结果集
	 */
	private List<T> dataResult = new ArrayList<T>();

	public List<ExcuteMsg> getMsgList() {
		return msgList;
	}

	public void setMsgList(List<ExcuteMsg> msgList) {
		this.msgList = msgList;
	}

	public String getItemNO() {
		return itemNO;
	}

	public void setItemNO(String itemNO) {
		this.itemNO = itemNO;
	}

	public boolean getSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public List<T> getDataResult() {
		return dataResult;
	}

	public void setDataResult(List<T> dataResult) {
		this.dataResult = dataResult;
	}


}
