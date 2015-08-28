package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.MvShopPdtypeAndBrandDao;
import com.ebiz.mmt.domain.MvShopPdtypeAndBrand;
import com.ebiz.mmt.service.MvShopPdtypeAndBrandService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-05-19 08:50:53
 */
@Service
public class MvShopPdtypeAndBrandServiceImpl implements MvShopPdtypeAndBrandService {

	@Resource
	private MvShopPdtypeAndBrandDao mvShopPdtypeAndBrandDao;

	public Long createMvShopPdtypeAndBrand(MvShopPdtypeAndBrand t) {
		return this.mvShopPdtypeAndBrandDao.insertEntity(t);
	}

	public MvShopPdtypeAndBrand getMvShopPdtypeAndBrand(MvShopPdtypeAndBrand t) {
		return this.mvShopPdtypeAndBrandDao.selectEntity(t);
	}

	public Long getMvShopPdtypeAndBrandCount(MvShopPdtypeAndBrand t) {
		return this.mvShopPdtypeAndBrandDao.selectEntityCount(t);
	}

	public List<MvShopPdtypeAndBrand> getMvShopPdtypeAndBrandList(MvShopPdtypeAndBrand t) {
		return this.mvShopPdtypeAndBrandDao.selectEntityList(t);
	}

	public int modifyMvShopPdtypeAndBrand(MvShopPdtypeAndBrand t) {
		return this.mvShopPdtypeAndBrandDao.updateEntity(t);
	}

	public int removeMvShopPdtypeAndBrand(MvShopPdtypeAndBrand t) {
		return this.mvShopPdtypeAndBrandDao.deleteEntity(t);
	}

	public List<MvShopPdtypeAndBrand> getMvShopPdtypeAndBrandPaginatedList(MvShopPdtypeAndBrand t) {
		return this.mvShopPdtypeAndBrandDao.selectEntityPaginatedList(t);
	}

	/**
	 * @author Wu,ShangLong
	 * @version 2011-05-19
	 */
	public List<MvShopPdtypeAndBrand> getDistinctClsIdByShopIdList(MvShopPdtypeAndBrand t) {
		return this.mvShopPdtypeAndBrandDao.selectDistinctClsIdByShopIdList(t);
	}

	/**
	 * @author Wu,ShangLong
	 * @version 2011-05-19
	 */
	public List<MvShopPdtypeAndBrand> getDistinctBrandIdByShopIdList(MvShopPdtypeAndBrand t) {
		return this.mvShopPdtypeAndBrandDao.selectDistinctBrandIdByShopIdList(t);
	}

}
