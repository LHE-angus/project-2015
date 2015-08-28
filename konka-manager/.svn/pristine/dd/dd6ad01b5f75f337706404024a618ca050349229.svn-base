package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.CrmCustomerGroupDetailsDao;
import com.ebiz.mmt.domain.CrmCustomerGroupDetails;
import com.ebiz.mmt.service.CrmCustomerGroupDetailsService;


@Service
public class CrmCustomerGroupDetailsServiceImpl implements CrmCustomerGroupDetailsService {

	@Resource
	private CrmCustomerGroupDetailsDao crmCustomerGroupDetailsDao;
	

	public Long createCrmCustomerGroupDetails(CrmCustomerGroupDetails t) {
		return this.crmCustomerGroupDetailsDao.insertEntity(t);
	}

	public CrmCustomerGroupDetails getCrmCustomerGroupDetails(CrmCustomerGroupDetails t) {
		return this.crmCustomerGroupDetailsDao.selectEntity(t);
	}

	public Long getCrmCustomerGroupDetailsCount(CrmCustomerGroupDetails t) {
		return this.crmCustomerGroupDetailsDao.selectEntityCount(t);
	}

	public List<CrmCustomerGroupDetails> getCrmCustomerGroupDetailsList(CrmCustomerGroupDetails t) {
		return this.crmCustomerGroupDetailsDao.selectEntityList(t);
	}

	public int modifyCrmCustomerGroupDetails(CrmCustomerGroupDetails t) {
		return this.crmCustomerGroupDetailsDao.updateEntity(t);
	}

	public int removeCrmCustomerGroupDetails(CrmCustomerGroupDetails t) {
		return this.crmCustomerGroupDetailsDao.deleteEntity(t);
	}

	public List<CrmCustomerGroupDetails> getCrmCustomerGroupDetailsPaginatedList(CrmCustomerGroupDetails t) {
		return this.crmCustomerGroupDetailsDao.selectEntityPaginatedList(t);
	}

}
