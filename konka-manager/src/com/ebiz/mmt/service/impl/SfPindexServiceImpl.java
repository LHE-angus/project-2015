package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.SfPindexDao;
import com.ebiz.mmt.domain.SfPindex;
import com.ebiz.mmt.service.SfPindexService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-04-22 17:20:16
 */
@Service
public class SfPindexServiceImpl implements SfPindexService {

	@Resource
	private SfPindexDao sfPindexDao;
	

	public Long createSfPindex(SfPindex t) {
		return this.sfPindexDao.insertEntity(t);
	}

	public SfPindex getSfPindex(SfPindex t) {
		return this.sfPindexDao.selectEntity(t);
	}

	public Long getSfPindexCount(SfPindex t) {
		return this.sfPindexDao.selectEntityCount(t);
	}

	public List<SfPindex> getSfPindexList(SfPindex t) {
		return this.sfPindexDao.selectEntityList(t);
	}

	public int modifySfPindex(SfPindex t) {
		return this.sfPindexDao.updateEntity(t);
	}

	public int removeSfPindex(SfPindex t) {
		return this.sfPindexDao.deleteEntity(t);
	}

	public List<SfPindex> getSfPindexPaginatedList(SfPindex t) {
		return this.sfPindexDao.selectEntityPaginatedList(t);
	}

}
