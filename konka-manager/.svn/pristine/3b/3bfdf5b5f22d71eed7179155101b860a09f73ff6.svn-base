package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.MvOrgOfCustomerDao;
import com.ebiz.mmt.domain.MvOrgOfCustomer;
import com.ebiz.mmt.service.MvOrgOfCustomerService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2015-05-25 14:26:30
 */
@Service
public class MvOrgOfCustomerServiceImpl implements MvOrgOfCustomerService {

	@Resource
	private MvOrgOfCustomerDao mvOrgOfCustomerDao;
	

	public Long createMvOrgOfCustomer(MvOrgOfCustomer t) {
		return this.mvOrgOfCustomerDao.insertEntity(t);
	}

	public MvOrgOfCustomer getMvOrgOfCustomer(MvOrgOfCustomer t) {
		return this.mvOrgOfCustomerDao.selectEntity(t);
	}

	public Long getMvOrgOfCustomerCount(MvOrgOfCustomer t) {
		return this.mvOrgOfCustomerDao.selectEntityCount(t);
	}

	public List<MvOrgOfCustomer> getMvOrgOfCustomerList(MvOrgOfCustomer t) {
		return this.mvOrgOfCustomerDao.selectEntityList(t);
	}

	public int modifyMvOrgOfCustomer(MvOrgOfCustomer t) {
		return this.mvOrgOfCustomerDao.updateEntity(t);
	}

	public int removeMvOrgOfCustomer(MvOrgOfCustomer t) {
		return this.mvOrgOfCustomerDao.deleteEntity(t);
	}

	public List<MvOrgOfCustomer> getMvOrgOfCustomerPaginatedList(MvOrgOfCustomer t) {
		return this.mvOrgOfCustomerDao.selectEntityPaginatedList(t);
	}

	@Override
	public int updateCustomerInfo(MvOrgOfCustomer t) {
		return this.mvOrgOfCustomerDao.updateMvOrgOfCustomerByUserId(t);
	}

}
