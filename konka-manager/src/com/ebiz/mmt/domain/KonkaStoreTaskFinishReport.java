package com.ebiz.mmt.domain;

public class KonkaStoreTaskFinishReport {

	private String deptId;
	private String deptSn;
	private String deptName;
	private String storeId;
	private String storeName;
	private String storeType;
	private String nums;// 实际销量
	private String prices;// 实际销售额
	private String tfp;// 完成率
	private String storerank;// 排名

	private String taskMoney;// 任务额

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptSn() {
		return deptSn;
	}

	public void setDeptSn(String deptSn) {
		this.deptSn = deptSn;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getStoreType() {
		return storeType;
	}

	public void setStoreType(String storeType) {
		this.storeType = storeType;
	}

	public String getNums() {
		return nums;
	}

	public void setNums(String nums) {
		this.nums = nums;
	}

	public String getPrices() {
		return prices;
	}

	public void setPrices(String prices) {
		this.prices = prices;
	}

	public String getTfp() {
		return tfp;
	}

	public void setTfp(String tfp) {
		this.tfp = tfp;
	}

	public String getStorerank() {
		return storerank;
	}

	public void setStorerank(String storerank) {
		this.storerank = storerank;
	}

	public String getTaskMoney() {
		return taskMoney;
	}

	public void setTaskMoney(String taskMoney) {
		this.taskMoney = taskMoney;
	}
}
