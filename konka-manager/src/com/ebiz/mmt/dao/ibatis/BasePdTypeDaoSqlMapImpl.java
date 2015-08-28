package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ebiz.mmt.dao.BasePdTypeDao;
import com.ebiz.mmt.domain.BasePdType;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * @author Liu,AiZhou
 */
@Repository
public class BasePdTypeDaoSqlMapImpl extends EntityDaoSqlMapImpl<BasePdType> implements BasePdTypeDao {

	@SuppressWarnings("unchecked")
	public List<BasePdType> selectBasePdTypeListByBrandName(BasePdType basePdType) {
		return super.getSqlMapClientTemplate().queryForList("selectBasePdTypeListByBrandName", basePdType);
	}

	@SuppressWarnings("unchecked")
	public List<BasePdType> selectPdTypeSignForMmt(BasePdType basePdType) {
		return super.getSqlMapClientTemplate().queryForList("selectPdTypeSignForMmt", basePdType);
	}

	@SuppressWarnings("unchecked")
	public List<BasePdType> selectBasePdTypeListForMmt(BasePdType basePdType) {
		return super.getSqlMapClientTemplate().queryForList("selectBasePdTypeListForMmt", basePdType);
	}

	/**
	 * @author Jiang,JiaYong
	 * @version 2010-08-13
	 */
	@SuppressWarnings("unchecked")
	public List<BasePdType> selectBasePdTypeListWithBrandInfoForEntpShop(BasePdType t) {
		return super.getSqlMapClientTemplate().queryForList("selectBasePdTypeListWithBrandInfoForEntpShop", t);
	}

	/**
	 * @author Du,HongGang
	 * @version 2010-11-16
	 */
	@SuppressWarnings("unchecked")
	public List<BasePdType> selectBasePdTypeListForPop(BasePdType t) {
		return super.getSqlMapClientTemplate().queryForList("selectBasePdTypeListForPop", t);
	}
}
