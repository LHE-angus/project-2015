package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.MmtShopCustomerDao;
import com.ebiz.mmt.domain.MmtShopCustomer;
import com.ebiz.mmt.service.MmtShopCustomerService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2010-05-28 16:48:39
 */
@Service
public class MmtShopCustomerServiceImpl implements MmtShopCustomerService {

	@Resource
	private MmtShopCustomerDao mmtShopCustomerDao;

	public Long createMmtShopCustomer(MmtShopCustomer t) {
		return this.mmtShopCustomerDao.insertEntity(t);
	}

	public MmtShopCustomer getMmtShopCustomer(MmtShopCustomer t) {
		return this.mmtShopCustomerDao.selectEntity(t);
	}

	public Long getMmtShopCustomerCount(MmtShopCustomer t) {
		return this.mmtShopCustomerDao.selectEntityCount(t);
	}

	public List<MmtShopCustomer> getMmtShopCustomerList(MmtShopCustomer t) {
		return this.mmtShopCustomerDao.selectEntityList(t);
	}

	public int modifyMmtShopCustomer(MmtShopCustomer t) {
		return this.mmtShopCustomerDao.updateEntity(t);
	}

	public int removeMmtShopCustomer(MmtShopCustomer t) {
		return this.mmtShopCustomerDao.deleteEntity(t);
	}

	public List<MmtShopCustomer> getMmtShopCustomerPaginatedList(MmtShopCustomer t) {
		return this.mmtShopCustomerDao.selectEntityPaginatedList(t);
	}

}
