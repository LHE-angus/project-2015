package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaJxcBindingMobileDao;
import com.ebiz.mmt.domain.KonkaJxcBindingMobile;
import com.ebiz.mmt.service.KonkaJxcBindingMobileService;

/**
 * @author Wu,Yang
 * @version 2011-12-20 16:03
 */
@Service
public class KonkaJxcBindingMobileServiceImpl implements KonkaJxcBindingMobileService {

	@Resource
	private KonkaJxcBindingMobileDao konkaJxcBindingMobileDao;
	

	public Long createKonkaJxcBindingMobile(KonkaJxcBindingMobile t) {
		return this.konkaJxcBindingMobileDao.insertEntity(t);
	}

	public KonkaJxcBindingMobile getKonkaJxcBindingMobile(KonkaJxcBindingMobile t) {
		return this.konkaJxcBindingMobileDao.selectEntity(t);
	}

	public Long getKonkaJxcBindingMobileCount(KonkaJxcBindingMobile t) {
		return this.konkaJxcBindingMobileDao.selectEntityCount(t);
	}

	public List<KonkaJxcBindingMobile> getKonkaJxcBindingMobileList(KonkaJxcBindingMobile t) {
		return this.konkaJxcBindingMobileDao.selectEntityList(t);
	}

	public int modifyKonkaJxcBindingMobile(KonkaJxcBindingMobile t) {
		return this.konkaJxcBindingMobileDao.updateEntity(t);
	}

	public int removeKonkaJxcBindingMobile(KonkaJxcBindingMobile t) {
		return this.konkaJxcBindingMobileDao.deleteEntity(t);
	}

	public List<KonkaJxcBindingMobile> getKonkaJxcBindingMobilePaginatedList(KonkaJxcBindingMobile t) {
		return this.konkaJxcBindingMobileDao.selectEntityPaginatedList(t);
	}

}
