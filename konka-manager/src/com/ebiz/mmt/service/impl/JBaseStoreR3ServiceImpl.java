package com.ebiz.mmt.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.JBaseStoreR3Dao;
import com.ebiz.mmt.domain.JBaseStoreR3;
import com.ebiz.mmt.service.JBaseStoreR3Service;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-06-13 14:54:36
 */
@Service
public class JBaseStoreR3ServiceImpl implements JBaseStoreR3Service {

	@Resource
	private JBaseStoreR3Dao jBaseStoreR3Dao;
	

	public Long createJBaseStoreR3(JBaseStoreR3 t) {
		return this.jBaseStoreR3Dao.insertEntity(t);
	}

	public JBaseStoreR3 getJBaseStoreR3(JBaseStoreR3 t) {
		return this.jBaseStoreR3Dao.selectEntity(t);
	}

	public Long getJBaseStoreR3Count(JBaseStoreR3 t) {
		return this.jBaseStoreR3Dao.selectEntityCount(t);
	}

	public List<JBaseStoreR3> getJBaseStoreR3List(JBaseStoreR3 t) {
		return this.jBaseStoreR3Dao.selectEntityList(t);
	}

	public int modifyJBaseStoreR3(JBaseStoreR3 t) {
		return this.jBaseStoreR3Dao.updateEntity(t);
	}

	public int removeJBaseStoreR3(JBaseStoreR3 t) {
		return this.jBaseStoreR3Dao.deleteEntity(t);
	}

	public List<JBaseStoreR3> getJBaseStoreR3PaginatedList(JBaseStoreR3 t) {
		return this.jBaseStoreR3Dao.selectEntityPaginatedList(t);
	}

	@Override
	public HashMap getSDFStore(JBaseStoreR3 t) {
		return this.jBaseStoreR3Dao.selectSDFStore(t);
	}

}
