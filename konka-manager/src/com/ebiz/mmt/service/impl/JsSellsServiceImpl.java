package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.JsSellsDao;
import com.ebiz.mmt.domain.JsSells;
import com.ebiz.mmt.service.JsSellsService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2014-01-09 15:57:14
 */
@Service
public class JsSellsServiceImpl implements JsSellsService {

	@Resource
	private JsSellsDao jsSellsDao;

	public Long createJsSells(JsSells t) {
		return this.jsSellsDao.insertEntity(t);
	}

	public JsSells getJsSells(JsSells t) {
		return this.jsSellsDao.selectEntity(t);
	}

	public Long getJsSellsCount(JsSells t) {
		return this.jsSellsDao.selectEntityCount(t);
	}

	public List<JsSells> getJsSellsList(JsSells t) {
		return this.jsSellsDao.selectEntityList(t);
	}

	public int modifyJsSells(JsSells t) {
		return this.jsSellsDao.updateEntity(t);
	}

	public int removeJsSells(JsSells t) {
		return this.jsSellsDao.deleteEntity(t);
	}

	public List<JsSells> getJsSellsPaginatedList(JsSells t) {
		return this.jsSellsDao.selectEntityPaginatedList(t);
	}

	public List<JsSells> getJsSellsListForML(JsSells t) {
		return this.jsSellsDao.selectJsSellsListForML(t);
	}

}
