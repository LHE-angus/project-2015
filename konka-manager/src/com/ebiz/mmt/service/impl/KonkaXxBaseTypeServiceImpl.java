package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaXxBaseTypeDao;
import com.ebiz.mmt.domain.KonkaXxBaseType;
import com.ebiz.mmt.service.KonkaXxBaseTypeService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2012-01-09 09:19:48
 */
@Service
public class KonkaXxBaseTypeServiceImpl implements KonkaXxBaseTypeService {

	@Resource
	private KonkaXxBaseTypeDao konkaXxBaseTypeDao;
	

	public Long createKonkaXxBaseType(KonkaXxBaseType t) {
		return this.konkaXxBaseTypeDao.insertEntity(t);
	}

	public KonkaXxBaseType getKonkaXxBaseType(KonkaXxBaseType t) {
		return this.konkaXxBaseTypeDao.selectEntity(t);
	}

	public Long getKonkaXxBaseTypeCount(KonkaXxBaseType t) {
		return this.konkaXxBaseTypeDao.selectEntityCount(t);
	}

	public List<KonkaXxBaseType> getKonkaXxBaseTypeList(KonkaXxBaseType t) {
		return this.konkaXxBaseTypeDao.selectEntityList(t);
	}

	public int modifyKonkaXxBaseType(KonkaXxBaseType t) {
		return this.konkaXxBaseTypeDao.updateEntity(t);
	}

	public int removeKonkaXxBaseType(KonkaXxBaseType t) {
		return this.konkaXxBaseTypeDao.deleteEntity(t);
	}

	public List<KonkaXxBaseType> getKonkaXxBaseTypePaginatedList(KonkaXxBaseType t) {
		return this.konkaXxBaseTypeDao.selectEntityPaginatedList(t);
	}

}
