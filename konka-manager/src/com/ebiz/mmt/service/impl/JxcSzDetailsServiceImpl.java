package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.JxcCustomerDao;
import com.ebiz.mmt.dao.JxcSzDetailsDao;
import com.ebiz.mmt.domain.JxcCustomer;
import com.ebiz.mmt.domain.JxcSzDetails;
import com.ebiz.mmt.service.JxcSzDetailsService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-03-03 09:42:37
 */
@Service
public class JxcSzDetailsServiceImpl implements JxcSzDetailsService {

	@Resource
	private JxcSzDetailsDao jxcSzDetailsDao;

	@Resource
	private JxcCustomerDao jxcCustomerDao;

	public Long createJxcSzDetails(JxcSzDetails t) {
		Long sz_id = this.jxcSzDetailsDao.insertEntity(t);

		JxcCustomer customer = new JxcCustomer();
		customer.setId(t.getSz_obj_id());
		customer.setIs_del(0);
		customer = this.jxcCustomerDao.selectEntity(customer);
		if (null != customer) {
			if (null != customer.getCur_pay()) {
				customer.setCur_pay(customer.getCur_pay().subtract(t.getMoney()));
			}
			this.jxcCustomerDao.updateEntity(customer);
		}
		return sz_id;
	}

	public JxcSzDetails getJxcSzDetails(JxcSzDetails t) {
		return this.jxcSzDetailsDao.selectEntity(t);
	}

	public JxcSzDetails getJxcSzDetailsWithMoney(JxcSzDetails t) {
		return this.jxcSzDetailsDao.selectEntityWithMoney(t);
	}

	public Long getJxcSzDetailsCount(JxcSzDetails t) {
		return this.jxcSzDetailsDao.selectEntityCount(t);
	}

	public List<JxcSzDetails> getJxcSzDetailsList(JxcSzDetails t) {
		return this.jxcSzDetailsDao.selectEntityList(t);
	}

	public int modifyJxcSzDetails(JxcSzDetails t) {
		return this.jxcSzDetailsDao.updateEntity(t);
	}

	public int removeJxcSzDetails(JxcSzDetails t) {
		return this.jxcSzDetailsDao.deleteEntity(t);
	}

	public List<JxcSzDetails> getJxcSzDetailsPaginatedList(JxcSzDetails t) {
		return this.jxcSzDetailsDao.selectEntityPaginatedList(t);
	}

}
