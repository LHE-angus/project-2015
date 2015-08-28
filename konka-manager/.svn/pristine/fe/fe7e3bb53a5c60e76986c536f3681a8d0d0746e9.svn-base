package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.JxcCustomerDao;
import com.ebiz.mmt.domain.JxcCustomer;
import com.ebiz.mmt.service.JxcCustomerService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-03-03 09:42:37
 */
@Service
public class JxcCustomerServiceImpl implements JxcCustomerService {

	@Resource
	private JxcCustomerDao jxcCustomerDao;
	

	public Long createJxcCustomer(JxcCustomer t) {
		return this.jxcCustomerDao.insertEntity(t);
	}

	public JxcCustomer getJxcCustomer(JxcCustomer t) {
		return this.jxcCustomerDao.selectEntity(t);
	}

	public Long getJxcCustomerCount(JxcCustomer t) {
		return this.jxcCustomerDao.selectEntityCount(t);
	}

	public List<JxcCustomer> getJxcCustomerList(JxcCustomer t) {
		return this.jxcCustomerDao.selectEntityList(t);
	}

	public int modifyJxcCustomer(JxcCustomer t) {
		return this.jxcCustomerDao.updateEntity(t);
	}

	public int removeJxcCustomer(JxcCustomer t) {
		return this.jxcCustomerDao.deleteEntity(t);
	}

	public List<JxcCustomer> getJxcCustomerPaginatedList(JxcCustomer t) {
		return this.jxcCustomerDao.selectEntityPaginatedList(t);
	}

}
