package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ebiz.mmt.dao.ShopPdDao;
import com.ebiz.mmt.domain.ShopPd;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * @author Liu,Huan
 */
@Repository
@SuppressWarnings("unchecked")
public class ShopPdDaoSqlMapImpl extends EntityDaoSqlMapImpl<ShopPd> implements ShopPdDao {

	public List<ShopPd> selectAllPdType(ShopPd t) {
		return super.getSqlMapClientTemplate().queryForList("selectAllPdType", t);
	}

	public List<ShopPd> selectAllBrandId(ShopPd t) {
		return super.getSqlMapClientTemplate().queryForList("selectAllBrandId", t);
	}

	public Long selectProductCenterShowCount(ShopPd t) throws DataAccessException {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectProductCenterShowCount", t);
	}

	public List<ShopPd> selectProductCenterShowPaginatedList(ShopPd t) throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectProductCenterShowPaginatedList", t);
	}

	public int updateShopPdDown(ShopPd t) throws DataAccessException {
		return super.getSqlMapClientTemplate().update("updateShopPdDown", t);
	}

	public List<ShopPd> selectShopPdIndexList(ShopPd t) throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectShopPdIndexList", t);
	}

	public List<ShopPd> selectShopPdListByBrandName(ShopPd shopPd) {
		return super.getSqlMapClientTemplate().queryForList("selectShopPdListByBrandName", shopPd);
	}

	public List<ShopPd> selectMmtProductList(ShopPd t) {
		return super.getSqlMapClientTemplate().queryForList("selectMmtProductList", t);
	}

	public Long selectMmtProductCount(ShopPd t) {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectMmtProductCount", t);
	}

	public List<ShopPd> selectMmtProductPaginatedList(ShopPd t) {
		return super.getSqlMapClientTemplate().queryForList("selectMmtProductPaginatedList", t);
	}

	/**
	 * @author Wu,Yang
	 * @version 2009-11-11 09:13
	 */
	public List<ShopPd> selectShopPdWithBrandNameList(ShopPd t) throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectShopPdWithBrandNameList", t);
	}

	/**
	 * @author Wu,Yang
	 * @version 2009-11-11 09:13
	 */
	public Long selectShopPdWithBrandNameCount(ShopPd t) throws DataAccessException {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectShopPdWithBrandNameCount", t);
	}

	/**
	 * @author Wu,Yang
	 * @version 2009-11-11 09:13
	 */
	public List<ShopPd> selectShopPdWithBrandNamePaginatedList(ShopPd t) throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectShopPdWithBrandNamePaginatedList", t);
	}

	/**
	 * @author Chen,Lilin
	 * @version 2010-04-20 14:30
	 */
	public List<ShopPd> selectShopPdForContrastPaginatedList(ShopPd t) throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectShopPdForContrastPaginatedList", t);
	}

	/**
	 * @author Jiang,Jiayong
	 * @version 2010-06-11
	 */
	public List<ShopPd> selectShopPdForMmtongPaginatedList(ShopPd t) throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectShopPdForMmtongPaginatedList", t);
	}

	@Override
	public List<ShopPd> selectProductRank(ShopPd t) throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectProductRank", t);
	}

	/**
	 * @author Jiang,JiaYong
	 * @version 2010-10-31
	 */
	public List<ShopPd> selectShopPdFoIndexv3List(ShopPd t) throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectShopPdFoIndexv3List", t);
	}

	/**
	 * @author Wu,ShangLong
	 * @version 2010-10-31
	 */
	public List<ShopPd> selectProductRankForMMT_Index(ShopPd t) throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectProductRankForMMT_Index", t);
	}

	/**
	 * @author Jiang,JiaYong
	 * @version 2010-11-05
	 */
	public Long selectShopPdOnlyCount(ShopPd t) throws DataAccessException {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectShopPdOnlyCount", t);
	}

	/**
	 * @author Wu,ShangLong
	 * @version 2010-11-17
	 */
	public List<ShopPd> selectShopPdFoIndexv4List(ShopPd t) throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectShopPdFoIndexv4List", t);
	}

}
