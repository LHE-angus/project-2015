package com.ebiz.mmt.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.JBaseTypeDao;
import com.ebiz.mmt.domain.JBaseType;
import com.ebiz.mmt.service.JBaseTypeService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-06-08 17:03:35
 */
@Service
public class JBaseTypeServiceImpl implements JBaseTypeService {

	@Resource
	private JBaseTypeDao jBaseTypeDao;
	

	public Long createJBaseType(JBaseType t) {
		return this.jBaseTypeDao.insertEntity(t);
	}

	public JBaseType getJBaseType(JBaseType t) {
		return this.jBaseTypeDao.selectEntity(t);
	}

	public Long getJBaseTypeCount(JBaseType t) {
		return this.jBaseTypeDao.selectEntityCount(t);
	}

	public List<JBaseType> getJBaseTypeList(JBaseType t) {
		return this.jBaseTypeDao.selectEntityList(t);
	}

	public int modifyJBaseType(JBaseType t) {
		return this.jBaseTypeDao.updateEntity(t);
	}

	public int removeJBaseType(JBaseType t) {
		return this.jBaseTypeDao.deleteEntity(t);
	}

	public List<JBaseType> getJBaseTypePaginatedList(JBaseType t) {
		return this.jBaseTypeDao.selectEntityPaginatedList(t);
	}

	@Override
	public List<HashMap> getJBaseTypeMap(JBaseType t) {
		return this.jBaseTypeDao.selectJBaseTypeMap(t);
	}

}
