package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaCategoryTypeDao;
import com.ebiz.mmt.domain.KonkaCategoryType;
import com.ebiz.mmt.service.KonkaCategoryTypeService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-07-12 16:57:52
 */
@Service
public class KonkaCategoryTypeServiceImpl implements KonkaCategoryTypeService {

	@Resource
	private KonkaCategoryTypeDao konkaCategoryTypeDao;
	

	public Long createKonkaCategoryType(KonkaCategoryType t) {
		return this.konkaCategoryTypeDao.insertEntity(t);
	}

	public KonkaCategoryType getKonkaCategoryType(KonkaCategoryType t) {
		return this.konkaCategoryTypeDao.selectEntity(t);
	}

	public Long getKonkaCategoryTypeCount(KonkaCategoryType t) {
		return this.konkaCategoryTypeDao.selectEntityCount(t);
	}

	public List<KonkaCategoryType> getKonkaCategoryTypeList(KonkaCategoryType t) {
		return this.konkaCategoryTypeDao.selectEntityList(t);
	}

	public int modifyKonkaCategoryType(KonkaCategoryType t) {
		return this.konkaCategoryTypeDao.updateEntity(t);
	}

	public int removeKonkaCategoryType(KonkaCategoryType t) {
		return this.konkaCategoryTypeDao.deleteEntity(t);
	}

	public List<KonkaCategoryType> getKonkaCategoryTypePaginatedList(KonkaCategoryType t) {
		return this.konkaCategoryTypeDao.selectEntityPaginatedList(t);
	}

}
