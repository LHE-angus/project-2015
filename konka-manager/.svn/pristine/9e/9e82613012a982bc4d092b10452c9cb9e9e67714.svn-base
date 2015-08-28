package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaMobileCustVisitTypeDao;
import com.ebiz.mmt.domain.KonkaMobileCustVisitType;
import com.ebiz.mmt.service.KonkaMobileCustVisitTypeService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-06-23 16:32:16
 */
@Service
public class KonkaMobileCustVisitTypeServiceImpl implements KonkaMobileCustVisitTypeService {

	@Resource
	private KonkaMobileCustVisitTypeDao konkaMobileCustVisitTypeDao;
	

	public Long createKonkaMobileCustVisitType(KonkaMobileCustVisitType t) {
		return this.konkaMobileCustVisitTypeDao.insertEntity(t);
	}

	public KonkaMobileCustVisitType getKonkaMobileCustVisitType(KonkaMobileCustVisitType t) {
		return this.konkaMobileCustVisitTypeDao.selectEntity(t);
	}

	public Long getKonkaMobileCustVisitTypeCount(KonkaMobileCustVisitType t) {
		return this.konkaMobileCustVisitTypeDao.selectEntityCount(t);
	}

	public List<KonkaMobileCustVisitType> getKonkaMobileCustVisitTypeList(KonkaMobileCustVisitType t) {
		return this.konkaMobileCustVisitTypeDao.selectEntityList(t);
	}

	public int modifyKonkaMobileCustVisitType(KonkaMobileCustVisitType t) {
		return this.konkaMobileCustVisitTypeDao.updateEntity(t);
	}

	public int removeKonkaMobileCustVisitType(KonkaMobileCustVisitType t) {
		return this.konkaMobileCustVisitTypeDao.deleteEntity(t);
	}

	public List<KonkaMobileCustVisitType> getKonkaMobileCustVisitTypePaginatedList(KonkaMobileCustVisitType t) {
		return this.konkaMobileCustVisitTypeDao.selectEntityPaginatedList(t);
	}

}
