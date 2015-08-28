package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcBcompTypeDao;
import com.ebiz.mmt.domain.EcBcompType;
import com.ebiz.mmt.service.EcBcompTypeService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-09-09 10:06:25
 */
@Service
public class EcBcompTypeServiceImpl implements EcBcompTypeService {

	@Resource
	private EcBcompTypeDao ecBcompTypeDao;
	

	public Long createEcBcompType(EcBcompType t) {
		return this.ecBcompTypeDao.insertEntity(t);
	}

	public EcBcompType getEcBcompType(EcBcompType t) {
		return this.ecBcompTypeDao.selectEntity(t);
	}

	public Long getEcBcompTypeCount(EcBcompType t) {
		return this.ecBcompTypeDao.selectEntityCount(t);
	}

	public List<EcBcompType> getEcBcompTypeList(EcBcompType t) {
		return this.ecBcompTypeDao.selectEntityList(t);
	}

	public int modifyEcBcompType(EcBcompType t) {
		return this.ecBcompTypeDao.updateEntity(t);
	}

	public int removeEcBcompType(EcBcompType t) {
		return this.ecBcompTypeDao.deleteEntity(t);
	}

	public List<EcBcompType> getEcBcompTypePaginatedList(EcBcompType t) {
		return this.ecBcompTypeDao.selectEntityPaginatedList(t);
	}

}
