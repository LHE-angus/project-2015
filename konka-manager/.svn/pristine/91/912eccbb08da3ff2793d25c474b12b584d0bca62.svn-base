package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.MdasRegionsProvinceDao;
import com.ebiz.mmt.domain.MdasRegionsProvince;
import com.ebiz.mmt.service.MdasRegionsProvinceService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2010-08-25 10:35:27
 */
@Service
public class MdasRegionsProvinceServiceImpl implements MdasRegionsProvinceService {

	@Resource
	private MdasRegionsProvinceDao mdasRegionsProvinceDao;

	public Long createMdasRegionsProvince(MdasRegionsProvince t) {
		return this.mdasRegionsProvinceDao.insertEntity(t);
	}

	public MdasRegionsProvince getMdasRegionsProvince(MdasRegionsProvince t) {
		return this.mdasRegionsProvinceDao.selectEntity(t);
	}

	public Long getMdasRegionsProvinceCount(MdasRegionsProvince t) {
		return this.mdasRegionsProvinceDao.selectEntityCount(t);
	}

	public List<MdasRegionsProvince> getMdasRegionsProvinceList(MdasRegionsProvince t) {
		return this.mdasRegionsProvinceDao.selectEntityList(t);
	}

	public int modifyMdasRegionsProvince(MdasRegionsProvince t) {
		return this.mdasRegionsProvinceDao.updateEntity(t);
	}

	public int removeMdasRegionsProvince(MdasRegionsProvince t) {
		return this.mdasRegionsProvinceDao.deleteEntity(t);
	}

	public List<MdasRegionsProvince> getMdasRegionsProvincePaginatedList(MdasRegionsProvince t) {
		return this.mdasRegionsProvinceDao.selectEntityPaginatedList(t);
	}

}
