package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaOrderMeetingCustomerDao;
import com.ebiz.mmt.domain.KonkaOrderMeetingCustomer;
import com.ebiz.mmt.service.KonkaOrderMeetingCustomerService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-01-23 11:36:10
 */
@Service
public class KonkaOrderMeetingCustomerServiceImpl implements KonkaOrderMeetingCustomerService {

	@Resource
	private KonkaOrderMeetingCustomerDao konkaOrderMeetingCustomerDao;
	

	public Long createKonkaOrderMeetingCustomer(KonkaOrderMeetingCustomer t) {
		return this.konkaOrderMeetingCustomerDao.insertEntity(t);
	}

	public KonkaOrderMeetingCustomer getKonkaOrderMeetingCustomer(KonkaOrderMeetingCustomer t) {
		return this.konkaOrderMeetingCustomerDao.selectEntity(t);
	}

	public Long getKonkaOrderMeetingCustomerCount(KonkaOrderMeetingCustomer t) {
		return this.konkaOrderMeetingCustomerDao.selectEntityCount(t);
	}

	public List<KonkaOrderMeetingCustomer> getKonkaOrderMeetingCustomerList(KonkaOrderMeetingCustomer t) {
		return this.konkaOrderMeetingCustomerDao.selectEntityList(t);
	}

	public int modifyKonkaOrderMeetingCustomer(KonkaOrderMeetingCustomer t) {
		return this.konkaOrderMeetingCustomerDao.updateEntity(t);
	}

	public int removeKonkaOrderMeetingCustomer(KonkaOrderMeetingCustomer t) {
		return this.konkaOrderMeetingCustomerDao.deleteEntity(t);
	}

	public List<KonkaOrderMeetingCustomer> getKonkaOrderMeetingCustomerPaginatedList(KonkaOrderMeetingCustomer t) {
		return this.konkaOrderMeetingCustomerDao.selectEntityPaginatedList(t);
	}

}
