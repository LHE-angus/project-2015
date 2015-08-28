package com.ebiz.mmt.web.struts.inter.form;

import java.util.List;

public class CmsCustomerForm extends BaseInterForm {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 每次请求返回的数据
	private List<CmsCustomer> cmsCustomerlist;
	// 根据查询条件得出条数 total_count >= cmsCustomerlist.lenght
	private int totalCount;
	// 每次查询的结果的长度 cmsCustomerlist.lenght = currentCount
	private int currentCount;


	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public long getCurrentCount() {
		return currentCount;
	}

	public void setCurrentCount(int currentCount) {
		this.currentCount = currentCount;
	}

	public List<CmsCustomer> getCmsCustomerlist() {
		return cmsCustomerlist;
	}

	public void setCmsCustomerlist(List<CmsCustomer> cmsCustomerlist) {
		this.cmsCustomerlist = cmsCustomerlist;
	}

}
