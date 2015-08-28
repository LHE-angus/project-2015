package com.ebiz.mmt.domain;

import java.util.List;

public class MobileList {

	// 初始化门店基础数据
	List<MobileSelectItem> storeList;

	// 初始化品牌基础数据
	List<MobileSelectItem> brandList;

	// 初始化意见反馈基础数据
	List<MobileSelectItem> ideaList;

	// 初始化退货原因基础数据
	List<MobileSelectItem> backList;

	// 初始化物料基础数据
	List<MobileSelectItem> goodList;

	// 初始化产品基础数据
	List<MobileSelectItem> modelList;

	// 初始化尺寸基础数据
	List<MobileSelectItem> sizeList;

	// 初始化品类基础数据
	List<MobileSelectItem> plList;

	// 初始化提成比例/金额
	List<MobileSelectItem> peList;

	// 数据版本号
	String dataPatch;
	
	String dataTimestamp;

	public List<MobileSelectItem> getStoreList() {
		return storeList;
	}

	public void setStoreList(List<MobileSelectItem> storeList) {
		this.storeList = storeList;
	}

	public List<MobileSelectItem> getBrandList() {
		return brandList;
	}

	public void setBrandList(List<MobileSelectItem> brandList) {
		this.brandList = brandList;
	}

	public List<MobileSelectItem> getIdeaList() {
		return ideaList;
	}

	public void setIdeaList(List<MobileSelectItem> ideaList) {
		this.ideaList = ideaList;
	}

	public List<MobileSelectItem> getBackList() {
		return backList;
	}

	public void setBackList(List<MobileSelectItem> backList) {
		this.backList = backList;
	}

	public List<MobileSelectItem> getGoodList() {
		return goodList;
	}

	public void setGoodList(List<MobileSelectItem> goodList) {
		this.goodList = goodList;
	}

	public List<MobileSelectItem> getModelList() {
		return modelList;
	}

	public void setModelList(List<MobileSelectItem> modelList) {
		this.modelList = modelList;
	}

	public List<MobileSelectItem> getSizeList() {
		return sizeList;
	}

	public void setSizeList(List<MobileSelectItem> sizeList) {
		this.sizeList = sizeList;
	}

	public List<MobileSelectItem> getPlList() {
		return plList;
	}

	public void setPlList(List<MobileSelectItem> plList) {
		this.plList = plList;
	}

	public List<MobileSelectItem> getPeList() {
		return peList;
	}

	public void setPeList(List<MobileSelectItem> peList) {
		this.peList = peList;
	}

	public String getDataPatch() {
		return dataPatch;
	}

	public void setDataPatch(String dataPatch) {
		this.dataPatch = dataPatch;
	}

	public String getDataTimestamp() {
		return dataTimestamp;
	}

	public void setDataTimestamp(String dataTimestamp) {
		this.dataTimestamp = dataTimestamp;
	}
	
}
