package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaUserMobileDao;
import com.ebiz.mmt.domain.KonkaUserMobile;
import com.ebiz.mmt.service.KonkaUserMobileService;

/**
 * @author Cheng,Bing
 * @version 2012-01-09
 */
@Service
public class KonkaUserMobileServiceImpl implements KonkaUserMobileService {

	@Resource
	private KonkaUserMobileDao konkaUserMobileDao;

	public long createKonkaUserMobile(KonkaUserMobile t) {
		return this.konkaUserMobileDao.insertEntity(t);
	}

	public long modifyKonkaUserMobile(KonkaUserMobile t) {
		return this.konkaUserMobileDao.updateEntity(t);
	}

	public long removeKonkaUserMobile(KonkaUserMobile t) {
		return this.konkaUserMobileDao.deleteEntity(t);
	}

	public KonkaUserMobile getKonkaUserMobile(KonkaUserMobile t) {
		return this.konkaUserMobileDao.selectEntity(t);
	}

	public long getKonkaUserMobileCount(KonkaUserMobile t) {
		return this.konkaUserMobileDao.selectEntityCount(t);
	}

	public List<KonkaUserMobile> getKonkaUserMobileList(KonkaUserMobile t) {
		return this.konkaUserMobileDao.selectEntityList(t);
	}

	public List<KonkaUserMobile> getKonkaUserMobilePaginatedList(KonkaUserMobile t) {
		return this.konkaUserMobileDao.selectEntityPaginatedList(t);
	}

	/**
	 * @author Wu,ShangLong
	 * @version 2013-06-07
	 */
	public List<KonkaUserMobile> getKonkaUserMobileAndGpsList(KonkaUserMobile t) {
		return this.konkaUserMobileDao.selectKonkaUserMobileAndGpsList(t);
	}
}
