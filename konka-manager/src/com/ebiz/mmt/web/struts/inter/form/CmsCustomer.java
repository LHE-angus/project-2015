package com.ebiz.mmt.web.struts.inter.form;

public class CmsCustomer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 大区编码
	 */
	private Integer areaCode;
	/**
	 * 大区名称
	 */
	private String areaName;
	/**
	 * 地区编码
	 */
	private Integer branchAreaCode;
	/**
	 * 地区名称
	 */
	private String branchAreaName;
	/**
	 * 客户r3编码
	 */
	private String r3Code;
	/**
	 * 客户类型
	 */
	private Integer customerType;

	/**
	 * 客户大类
	 */
	private Integer customerBigType;

	/**
	 * 客户大类名称
	 */
	private String customerBigTypeName;

	/**
	 * 客户分公司编码
	 */
	private String customerFgsCode;
	/**
	 * 客户名称
	 */
	private String customerName;
	/**
	 * 客户销售组织
	 */
	private String r3SalesOrgCode;
	/**
	 * 渠道系统停用标志
	 */
	private Integer isdel;
	/**
	 * 渠道系统客户状态
	 */
	private Integer status;
	/**
	 * 客户创建时间
	 */
	private String addDate;
	/**
	 * 冻结标志r3
	 */
	private Integer isCassd;
	/**
	 * 删除标志r3
	 */
	private Integer isLoevm;
	/**
	 * 事业部 2多媒体1白电
	 */
	private String branchName;
	/**
	 * 市场级别 1:一线城市 2:二线城市 3:三线城市 4:四线城市
	 */
	private String entpplevel;
	/**
	 * 主营产品
	 */
	private String entpmainpds;
	/**
	 * 销售区域
	 */
	private String entpsalearea;
	/**
	 * 客户简介
	 */
	private String entpinro;

	/**
	 * 一级部门
	 */
	private Integer oneDeptId;
	/**
	 * 一级部门名称
	 */
	private String oneDeptName;

	/**
	 * 二级部门
	 */
	private Integer twoDeptId;
	/**
	 * 二级部门名称
	 */
	private String twoDeptName;
	/**
	 * 三级部门
	 */
	private Integer threeDeptId;
	/**
	 * 三级部门名称
	 */
	private String threeDeptName;

	@Override
	public String toString() {
		return "CmsCustomer [r3Code=" + r3Code + ", customerName=" + customerName + ", isdel=" + isdel + ", isCassd="
				+ isCassd + ", isLoevm=" + isLoevm + "]";
	}

	public Integer getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(Integer areaCode) {
		this.areaCode = areaCode;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public Integer getBranchAreaCode() {
		return branchAreaCode;
	}

	public void setBranchAreaCode(Integer branchAreaCode) {
		this.branchAreaCode = branchAreaCode;
	}

	public String getBranchAreaName() {
		return branchAreaName;
	}

	public void setBranchAreaName(String branchAreaName) {
		this.branchAreaName = branchAreaName;
	}

	public String getR3Code() {
		return r3Code;
	}

	public void setR3Code(String r3Code) {
		this.r3Code = r3Code;
	}

	public Integer getCustomerType() {
		return customerType;
	}

	public void setCustomerType(Integer customerType) {
		this.customerType = customerType;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getR3SalesOrgCode() {
		return r3SalesOrgCode;
	}

	public void setR3SalesOrgCode(String r3SalesOrgCode) {
		this.r3SalesOrgCode = r3SalesOrgCode;
	}

	public Integer getIsdel() {
		return isdel;
	}

	public void setIsdel(Integer isdel) {
		this.isdel = isdel;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getAddDate() {
		return addDate;
	}

	public void setAddDate(String addDate) {
		this.addDate = addDate;
	}

	public Integer getIsCassd() {
		return isCassd;
	}

	public void setIsCassd(Integer isCassd) {
		this.isCassd = isCassd;
	}

	public Integer getIsLoevm() {
		return isLoevm;
	}

	public void setIsLoevm(Integer isLoevm) {
		this.isLoevm = isLoevm;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getEntpplevel() {
		return entpplevel;
	}

	public void setEntpplevel(String entpplevel) {
		this.entpplevel = entpplevel;
	}

	public String getEntpmainpds() {
		return entpmainpds;
	}

	public void setEntpmainpds(String entpmainpds) {
		this.entpmainpds = entpmainpds;
	}

	public String getEntpsalearea() {
		return entpsalearea;
	}

	public void setEntpsalearea(String entpsalearea) {
		this.entpsalearea = entpsalearea;
	}

	public String getEntpinro() {
		return entpinro;
	}

	public void setEntpinro(String entpinro) {
		this.entpinro = entpinro;
	}

	public Integer getCustomerBigType() {
		return customerBigType;
	}

	public void setCustomerBigType(Integer customerBigType) {
		this.customerBigType = customerBigType;
	}

	public String getCustomerBigTypeName() {
		return customerBigTypeName;
	}

	public void setCustomerBigTypeName(String customerBigTypeName) {
		this.customerBigTypeName = customerBigTypeName;
	}

	public String getCustomerFgsCode() {
		return customerFgsCode;
	}

	public void setCustomerFgsCode(String customerFgsCode) {
		this.customerFgsCode = customerFgsCode;
	}

	public Integer getOneDeptId() {
		return oneDeptId;
	}

	public void setOneDeptId(Integer oneDeptId) {
		this.oneDeptId = oneDeptId;
	}

	public String getOneDeptName() {
		return oneDeptName;
	}

	public void setOneDeptName(String oneDeptName) {
		this.oneDeptName = oneDeptName;
	}

	public Integer getTwoDeptId() {
		return twoDeptId;
	}

	public void setTwoDeptId(Integer twoDeptId) {
		this.twoDeptId = twoDeptId;
	}

	public String getTwoDeptName() {
		return twoDeptName;
	}

	public void setTwoDeptName(String twoDeptName) {
		this.twoDeptName = twoDeptName;
	}

	public Integer getThreeDeptId() {
		return threeDeptId;
	}

	public void setThreeDeptId(Integer threeDeptId) {
		this.threeDeptId = threeDeptId;
	}

	public String getThreeDeptName() {
		return threeDeptName;
	}

	public void setThreeDeptName(String threeDeptName) {
		this.threeDeptName = threeDeptName;
	}

}
