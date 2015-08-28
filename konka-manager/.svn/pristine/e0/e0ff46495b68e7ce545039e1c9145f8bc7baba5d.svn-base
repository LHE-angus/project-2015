package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ebiz.mmt.dao.MvPdTypeJoinBrandDao;
import com.ebiz.mmt.domain.MvPdTypeJoinBrand;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

@Repository
public class MvPdTypeJoinBrandDaoSqlMapImpl extends EntityDaoSqlMapImpl<MvPdTypeJoinBrand> implements
		MvPdTypeJoinBrandDao {

	/**
	 * @author Zhang,ShiMing
	 * @version 2011-4-11 获取大类类型及名称
	 */
	@SuppressWarnings("unchecked")
	public List<MvPdTypeJoinBrand> selectDistinctPdType(MvPdTypeJoinBrand entity) {
		return super.getSqlMapClientTemplate().queryForList("selectDistinctPdType", entity);
	}

}
