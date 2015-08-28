package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.BasePdTypeDao;
import com.ebiz.mmt.dao.PdPropertyDao;
import com.ebiz.mmt.domain.BasePdType;
import com.ebiz.mmt.domain.PdProperty;
import com.ebiz.mmt.service.BasePdTypeService;

/**
 * @author Liu,Huan
 */
@Service
public class BasePdTypeServiceImpl implements BasePdTypeService {

	@Resource
	private BasePdTypeDao basePdTypeDao;

	@Resource
	private PdPropertyDao pdPropertyDao;

	public Long createBasePdType(BasePdType t) {
		return this.basePdTypeDao.insertEntity(t);
	}

	public BasePdType getBasePdType(BasePdType t) {
		return this.basePdTypeDao.selectEntity(t);
	}

	public Long getBasePdTypeCount(BasePdType t) {
		return this.basePdTypeDao.selectEntityCount(t);
	}

	public List<BasePdType> getBasePdTypeList(BasePdType t) {
		return this.basePdTypeDao.selectEntityList(t);
	}

	public int modifyBasePdType(BasePdType t) {
		return this.basePdTypeDao.updateEntity(t);
	}

	public int removeBasePdType(BasePdType t) {
		if (null != t.getPd_type()) {
			PdProperty pp = new PdProperty();
			pp.setPd_type(t.getPd_type());
			this.pdPropertyDao.deleteEntity(pp);
		}

		return this.basePdTypeDao.deleteEntity(t);
	}

	public List<BasePdType> getBasePdTypePaginatedList(BasePdType t) {
		return this.basePdTypeDao.selectEntityPaginatedList(t);
	}

	public List<BasePdType> getBasePdTypeListByBrandName(BasePdType t) {
		return this.basePdTypeDao.selectBasePdTypeListByBrandName(t);
	}

	public List<BasePdType> getBasePdTypeListForMmt(BasePdType t) {
		return this.basePdTypeDao.selectBasePdTypeListForMmt(t);
	}

	public List<BasePdType> getPdTypeSignForMmt(BasePdType t) {
		return this.basePdTypeDao.selectPdTypeSignForMmt(t);
	}

	/**
	 * @author Jiang,JiaYong
	 * @version 2010-08-13
	 */
	public List<BasePdType> getBasePdTypeListWithBrandInfoForEntpShop(BasePdType t) {
		return this.basePdTypeDao.selectBasePdTypeListWithBrandInfoForEntpShop(t);
	}

	/**
	 * @author Du,HongGang
	 * @version 2010-11-16
	 */
	public List<BasePdType> getBasePdTypeListForPop(BasePdType t) {
		return this.basePdTypeDao.selectBasePdTypeListForPop(t);
	}
}
