package com.ebiz.mmt.dao;

import java.util.List;

import com.ebiz.mmt.domain.BasePdType;
import com.ebiz.ssi.dao.EntityDao;

/**
 * @author Liu,Huan
 */
public interface BasePdTypeDao extends EntityDao<BasePdType> {

	List<BasePdType> selectBasePdTypeListByBrandName(BasePdType basePdType);

	List<BasePdType> selectPdTypeSignForMmt(BasePdType basePdType);

	List<BasePdType> selectBasePdTypeListForMmt(BasePdType basePdType);

	/**
	 * @author Jiang,JiaYong
	 * @version 2010-08-13
	 */
	List<BasePdType> selectBasePdTypeListWithBrandInfoForEntpShop(BasePdType t);

	/**
	 * @author Du,HongGang
	 * @version 2010-11-16
	 */
	public List<BasePdType> selectBasePdTypeListForPop(BasePdType t);
}
