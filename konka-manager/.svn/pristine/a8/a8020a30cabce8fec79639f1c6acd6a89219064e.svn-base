package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.MvShopPdtypeAndBrandDao;
import com.ebiz.mmt.domain.MvShopPdtypeAndBrand;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-05-19 08:50:53
 */
@Service
public class MvShopPdtypeAndBrandDaoSqlMapImpl extends EntityDaoSqlMapImpl<MvShopPdtypeAndBrand> implements
		MvShopPdtypeAndBrandDao {

	/**
	 * @author Wu,ShangLong
	 * @version 2011-05-19
	 */
	@SuppressWarnings("unchecked")
	public List<MvShopPdtypeAndBrand> selectDistinctClsIdByShopIdList(MvShopPdtypeAndBrand t) {
		return super.getSqlMapClientTemplate().queryForList("selectDistinctClsIdByShopIdList", t);
	}

	/**
	 * @author Wu,ShangLong
	 * @version 2011-05-19
	 */
	@SuppressWarnings("unchecked")
	public List<MvShopPdtypeAndBrand> selectDistinctBrandIdByShopIdList(MvShopPdtypeAndBrand t) {
		return super.getSqlMapClientTemplate().queryForList("selectDistinctBrandIdByShopIdList", t);
	}
}
