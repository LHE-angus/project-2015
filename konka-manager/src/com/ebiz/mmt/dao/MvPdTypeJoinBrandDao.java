package com.ebiz.mmt.dao;

import java.util.List;

import com.ebiz.mmt.domain.MvPdTypeJoinBrand;
import com.ebiz.ssi.dao.EntityDao;

public interface MvPdTypeJoinBrandDao extends EntityDao<MvPdTypeJoinBrand> {
	/**
	 * @author Zhang,ShiMing
	 * @version 2011-4-11 获取大类类型及名称
	 */
	List<MvPdTypeJoinBrand> selectDistinctPdType(MvPdTypeJoinBrand entity);

}
