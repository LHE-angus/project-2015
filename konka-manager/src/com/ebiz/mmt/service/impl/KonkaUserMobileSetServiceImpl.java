package com.ebiz.mmt.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaUserMobileSetDao;
import com.ebiz.mmt.domain.KonkaUserMobileSet;
import com.ebiz.mmt.service.KonkaUserMobileSetService;


/**
 * @author Cheng,Bing
 * @version 2012-01-09
 */
@Service
public class KonkaUserMobileSetServiceImpl implements KonkaUserMobileSetService {

	@Resource
	private KonkaUserMobileSetDao konkaUserMobileSetDao;

	public long createKonkaUserMobileSet(KonkaUserMobileSet t) {
		return this.konkaUserMobileSetDao.insertEntity(t);
	}

	public long modifyKonkaUserMobileSet(KonkaUserMobileSet t) {
		return this.konkaUserMobileSetDao.updateEntity(t);
	}

	public long removeKonkaUserMobileSet(KonkaUserMobileSet t) {
		return this.konkaUserMobileSetDao.deleteEntity(t);
	}

	public KonkaUserMobileSet getKonkaUserMobileSet(KonkaUserMobileSet t) {
		return this.konkaUserMobileSetDao.selectEntity(t);
	}

	public long getKonkaUserMobileSetCount(KonkaUserMobileSet t) {
		return this.konkaUserMobileSetDao.selectEntityCount(t);
	}

	public List<KonkaUserMobileSet> getKonkaUserMobileSetList(KonkaUserMobileSet t) {
		return this.konkaUserMobileSetDao.selectEntityList(t);
	}

	public List<KonkaUserMobileSet> getKonkaUserMobileSetPaginatedList(KonkaUserMobileSet t) {
		return this.konkaUserMobileSetDao.selectEntityPaginatedList(t);
	}
}
