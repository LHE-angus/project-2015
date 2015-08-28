package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaPdTypeJoinPdClassDao;
import com.ebiz.mmt.domain.KonkaPdTypeJoinPdClass;
import com.ebiz.mmt.service.KonkaPdTypeJoinPdClassService;

/**
 * @author Wu,Yang
 * @version 2011-10-28 17:35
 */
@Service
public class KonkaPdTypeJoinPdClassServiceImpl implements KonkaPdTypeJoinPdClassService {

	@Resource
	private KonkaPdTypeJoinPdClassDao konkaPdTypeJoinPdClassDao;
	

	public Long createKonkaPdTypeJoinPdClass(KonkaPdTypeJoinPdClass t) {
		return this.konkaPdTypeJoinPdClassDao.insertEntity(t);
	}

	public KonkaPdTypeJoinPdClass getKonkaPdTypeJoinPdClass(KonkaPdTypeJoinPdClass t) {
		return this.konkaPdTypeJoinPdClassDao.selectEntity(t);
	}

	public Long getKonkaPdTypeJoinPdClassCount(KonkaPdTypeJoinPdClass t) {
		return this.konkaPdTypeJoinPdClassDao.selectEntityCount(t);
	}

	public List<KonkaPdTypeJoinPdClass> getKonkaPdTypeJoinPdClassList(KonkaPdTypeJoinPdClass t) {
		return this.konkaPdTypeJoinPdClassDao.selectEntityList(t);
	}

	public int modifyKonkaPdTypeJoinPdClass(KonkaPdTypeJoinPdClass t) {
		return this.konkaPdTypeJoinPdClassDao.updateEntity(t);
	}

	public int removeKonkaPdTypeJoinPdClass(KonkaPdTypeJoinPdClass t) {
		return this.konkaPdTypeJoinPdClassDao.deleteEntity(t);
	}

	public List<KonkaPdTypeJoinPdClass> getKonkaPdTypeJoinPdClassPaginatedList(KonkaPdTypeJoinPdClass t) {
		return this.konkaPdTypeJoinPdClassDao.selectEntityPaginatedList(t);
	}

}
