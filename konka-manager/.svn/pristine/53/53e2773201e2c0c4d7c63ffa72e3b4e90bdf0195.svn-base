package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.MvPdTypeJoinBrandDao;
import com.ebiz.mmt.domain.MvPdTypeJoinBrand;
import com.ebiz.mmt.service.MvPdTypeJoinBrandService;

@Service
public class MvPdTypeJoinBrandServiceImpl implements MvPdTypeJoinBrandService {

	@Resource
	private MvPdTypeJoinBrandDao mvPdTypeJoinBrandDao;

	public MvPdTypeJoinBrand getMvPdTypeJoinBrand(MvPdTypeJoinBrand t) {
		return this.mvPdTypeJoinBrandDao.selectEntity(t);
	}

	public Long getMvPdTypeJoinBrandCount(MvPdTypeJoinBrand t) {
		return this.mvPdTypeJoinBrandDao.selectEntityCount(t);
	}

	public List<MvPdTypeJoinBrand> getMvPdTypeJoinBrandList(MvPdTypeJoinBrand t) {
		return this.mvPdTypeJoinBrandDao.selectEntityList(t);
	}

	public List<MvPdTypeJoinBrand> getMvPdTypeJoinBrandPaginatedList(MvPdTypeJoinBrand t) {
		return this.mvPdTypeJoinBrandDao.selectEntityPaginatedList(t);
	}

	/**
	 * @author Zhang,ShiMing
	 * @version 2011-4-11 获取大类类型及名称
	 */
	public List<MvPdTypeJoinBrand> getDistinctPdType(MvPdTypeJoinBrand entity) {

		return this.mvPdTypeJoinBrandDao.selectDistinctPdType(entity);
	}

}
