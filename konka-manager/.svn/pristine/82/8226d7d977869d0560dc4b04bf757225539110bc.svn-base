package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.CrmCustomerGroup;


public interface CrmCustomerGroupService {

	Long createCrmCustomerGroup(CrmCustomerGroup t);

	int modifyCrmCustomerGroup(CrmCustomerGroup t);

	int removeCrmCustomerGroup(CrmCustomerGroup t);

	CrmCustomerGroup getCrmCustomerGroup(CrmCustomerGroup t);

	CrmCustomerGroup getCrmCustomerGroupByCustomerIdAndDeptId(String customerid, Long deptId);

	List<CrmCustomerGroup> getCrmCustomerGroupList(CrmCustomerGroup t);

	Long getCrmCustomerGroupCount(CrmCustomerGroup t);

	List<CrmCustomerGroup> getCrmCustomerGroupPaginatedList(CrmCustomerGroup t);

	String getGroupCode();

	/**
	 * true 重复 false 不重复
	 * 
	 * @param deptId
	 * @param groupName
	 * @return
	 */
	boolean isExitsGroupName(String deptId, String groupName);

}