package com.ebiz.mmt.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.CrmCustomerGroupDao;
import com.ebiz.mmt.domain.CrmCustomerGroup;
import com.ebiz.mmt.service.CrmCustomerGroupService;

@Service
public class CrmCustomerGroupServiceImpl implements CrmCustomerGroupService {

	@Resource
	private CrmCustomerGroupDao crmCustomerGroupDao;

	@Override
	public Long createCrmCustomerGroup(CrmCustomerGroup t) {
		return this.crmCustomerGroupDao.insertEntity(t);
	}

	@Override
	public CrmCustomerGroup getCrmCustomerGroup(CrmCustomerGroup t) {
		return this.crmCustomerGroupDao.selectEntity(t);
	}

	@Override
	public Long getCrmCustomerGroupCount(CrmCustomerGroup t) {
		return this.crmCustomerGroupDao.selectEntityCount(t);
	}

	@Override
	public List<CrmCustomerGroup> getCrmCustomerGroupList(CrmCustomerGroup t) {
		return this.crmCustomerGroupDao.selectEntityList(t);
	}

	@Override
	public int modifyCrmCustomerGroup(CrmCustomerGroup t) {
		return this.crmCustomerGroupDao.updateEntity(t);
	}

	@Override
	public int removeCrmCustomerGroup(CrmCustomerGroup t) {
		return this.crmCustomerGroupDao.deleteEntity(t);
	}

	@Override
	public List<CrmCustomerGroup> getCrmCustomerGroupPaginatedList(CrmCustomerGroup t) {
		return this.crmCustomerGroupDao.selectEntityPaginatedList(t);
	}

	@Override
	public String getGroupCode() {

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return "KG" + sdf.format(date) + (new Random().nextInt(1000));
	}

	@Override
	public boolean isExitsGroupName(String deptId, String groupName) {
		CrmCustomerGroup t = new CrmCustomerGroup();
		t.setDeptid(Long.valueOf(deptId));
		// t.setGroupname(groupName);
		t.setIsdel(0);
		t.getMap().put("groupname_eq", groupName);
		return this.crmCustomerGroupDao.selectEntityCount(t) > 0;
	}

	@Override
	public CrmCustomerGroup getCrmCustomerGroupByCustomerIdAndDeptId(String customerId, Long deptId) {
		CrmCustomerGroup t = new CrmCustomerGroup();
		t.getMap().put("in_dept_id", deptId);
		t.getMap().put("customer_id", customerId);
		return this.crmCustomerGroupDao.selectCrmCustomerGroupByCustomerIdAndDeptId(t);
	}

}
