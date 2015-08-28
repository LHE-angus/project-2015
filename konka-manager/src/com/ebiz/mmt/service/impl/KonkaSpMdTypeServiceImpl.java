package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaSpMdTypeDao;
import com.ebiz.mmt.domain.KonkaSpMdType;
import com.ebiz.mmt.service.KonkaSpMdTypeService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-01-23 11:36:10
 */
@Service
public class KonkaSpMdTypeServiceImpl implements KonkaSpMdTypeService {

	@Resource
	private KonkaSpMdTypeDao konkaSpMdTypeDao;
	

	public Long createKonkaSpMdType(KonkaSpMdType t) {
		return this.konkaSpMdTypeDao.insertEntity(t);
	}

	public KonkaSpMdType getKonkaSpMdType(KonkaSpMdType t) {
		return this.konkaSpMdTypeDao.selectEntity(t);
	}

	public Long getKonkaSpMdTypeCount(KonkaSpMdType t) {
		return this.konkaSpMdTypeDao.selectEntityCount(t);
	}

	public List<KonkaSpMdType> getKonkaSpMdTypeList(KonkaSpMdType t) {
		return this.konkaSpMdTypeDao.selectEntityList(t);
	}

	public int modifyKonkaSpMdType(KonkaSpMdType t) {
		return this.konkaSpMdTypeDao.updateEntity(t);
	}

	public int removeKonkaSpMdType(KonkaSpMdType t) {
		return this.konkaSpMdTypeDao.deleteEntity(t);
	}

	public List<KonkaSpMdType> getKonkaSpMdTypePaginatedList(KonkaSpMdType t) {
		return this.konkaSpMdTypeDao.selectEntityPaginatedList(t);
	}

}
