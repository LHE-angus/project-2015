package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.MdasRegionsDao;
import com.ebiz.mmt.domain.MdasRegions;
import com.ebiz.mmt.service.MdasRegionsService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2010-08-25 10:35:27
 */
@Service
public class MdasRegionsServiceImpl implements MdasRegionsService {

	@Resource
	private MdasRegionsDao mdasRegionsDao;

	public Long createMdasRegions(MdasRegions t) {
		return this.mdasRegionsDao.insertEntity(t);
	}

	public MdasRegions getMdasRegions(MdasRegions t) {
		return this.mdasRegionsDao.selectEntity(t);
	}

	public Long getMdasRegionsCount(MdasRegions t) {
		return this.mdasRegionsDao.selectEntityCount(t);
	}

	public List<MdasRegions> getMdasRegionsList(MdasRegions t) {
		return this.mdasRegionsDao.selectEntityList(t);
	}

	public int modifyMdasRegions(MdasRegions t) {
		return this.mdasRegionsDao.updateEntity(t);
	}

	public int removeMdasRegions(MdasRegions t) {
		return this.mdasRegionsDao.deleteEntity(t);
	}

	public List<MdasRegions> getMdasRegionsPaginatedList(MdasRegions t) {
		return this.mdasRegionsDao.selectEntityPaginatedList(t);
	}

}
