package com.ebiz.mmt.domain;

import java.util.List;

public class MobileListNew {

	// 初始化门店基础数据
	List<MobileSelectItemNew> storeList;

	// 初始化品牌基础数据
	List<MobileSelectItemNew> brandList;

	// 初始化意见反馈基础数据
	List<MobileSelectItemNew> ideaList;

	// 初始化退货原因基础数据
	List<MobileSelectItemNew> backList;

	// 初始化物料基础数据
	List<MobileSelectItemNew> goodList;

	// 初始化产品基础数据
	List<MobileSelectItemNew> modelList;

	// 初始化尺寸基础数据
	List<MobileSelectItemNew> sizeList;

	// 初始化品类基础数据
	List<MobileSelectItemNew> plList;

	// 初始化提成比例/金额
	List<MobileSelectItemNew> peList;

	// 数据版本号
	String dataPatch;
	
	String dataTimestamp;



	public List<MobileSelectItemNew> getStoreList() {
		return storeList;
	}

	public void setStoreList(List<MobileSelectItemNew> storeList) {
		this.storeList = storeList;
	}

	public List<MobileSelectItemNew> getBrandList() {
		return brandList;
	}

	public void setBrandList(List<MobileSelectItemNew> brandList) {
		this.brandList = brandList;
	}

	public List<MobileSelectItemNew> getIdeaList() {
		return ideaList;
	}

	public void setIdeaList(List<MobileSelectItemNew> ideaList) {
		this.ideaList = ideaList;
	}

	public List<MobileSelectItemNew> getBackList() {
		return backList;
	}

	public void setBackList(List<MobileSelectItemNew> backList) {
		this.backList = backList;
	}

	public List<MobileSelectItemNew> getGoodList() {
		return goodList;
	}

	public void setGoodList(List<MobileSelectItemNew> goodList) {
		this.goodList = goodList;
	}

	public List<MobileSelectItemNew> getModelList() {
		return modelList;
	}

	public void setModelList(List<MobileSelectItemNew> modelList) {
		this.modelList = modelList;
	}

	public List<MobileSelectItemNew> getSizeList() {
		return sizeList;
	}

	public void setSizeList(List<MobileSelectItemNew> sizeList) {
		this.sizeList = sizeList;
	}

	public List<MobileSelectItemNew> getPlList() {
		return plList;
	}

	public void setPlList(List<MobileSelectItemNew> plList) {
		this.plList = plList;
	}

	public List<MobileSelectItemNew> getPeList() {
		return peList;
	}

	public void setPeList(List<MobileSelectItemNew> peList) {
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
