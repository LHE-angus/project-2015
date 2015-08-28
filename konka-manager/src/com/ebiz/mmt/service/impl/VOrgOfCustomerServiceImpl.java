package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.VOrgOfCustomerDao;
import com.ebiz.mmt.domain.VOrgOfCustomer;
import com.ebiz.mmt.service.VOrgOfCustomerService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-08-06 15:02:17
 */
@Service
public class VOrgOfCustomerServiceImpl implements VOrgOfCustomerService {

	@Resource
	private VOrgOfCustomerDao vOrgOfCustomerDao;
	

	public Long createVOrgOfCustomer(VOrgOfCustomer t) {
		return this.vOrgOfCustomerDao.insertEntity(t);
	}

	public VOrgOfCustomer getVOrgOfCustomer(VOrgOfCustomer t) {
		return this.vOrgOfCustomerDao.selectEntity(t);
	}

	public Long getVOrgOfCustomerCount(VOrgOfCustomer t) {
		return this.vOrgOfCustomerDao.selectEntityCount(t);
	}

	public List<VOrgOfCustomer> getVOrgOfCustomerList(VOrgOfCustomer t) {
		return this.vOrgOfCustomerDao.selectEntityList(t);
	}

	public int modifyVOrgOfCustomer(VOrgOfCustomer t) {
		return this.vOrgOfCustomerDao.updateEntity(t);
	}

	public int removeVOrgOfCustomer(VOrgOfCustomer t) {
		return this.vOrgOfCustomerDao.deleteEntity(t);
	}

	public List<VOrgOfCustomer> getVOrgOfCustomerPaginatedList(VOrgOfCustomer t) {
		return this.vOrgOfCustomerDao.selectEntityPaginatedList(t);
	}

}
