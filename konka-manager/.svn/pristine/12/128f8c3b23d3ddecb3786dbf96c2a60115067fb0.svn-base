package com.ebiz.mmt.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ebiz.mmt.domain.ShopPd;
import com.ebiz.ssi.dao.EntityDao;

/**
 * @author Liu,Huan
 */
public interface ShopPdDao extends EntityDao<ShopPd> {

	List<ShopPd> selectAllPdType(ShopPd t) throws DataAccessException;

	List<ShopPd> selectAllBrandId(ShopPd t) throws DataAccessException;

	Long selectProductCenterShowCount(ShopPd t) throws DataAccessException;

	List<ShopPd> selectProductCenterShowPaginatedList(ShopPd t) throws DataAccessException;

	List<ShopPd> selectShopPdIndexList(ShopPd t) throws DataAccessException;

	int updateShopPdDown(ShopPd t) throws DataAccessException;

	List<ShopPd> selectShopPdListByBrandName(ShopPd shopPd) throws DataAccessException;

	List<ShopPd> selectMmtProductList(ShopPd t) throws DataAccessException;

	Long selectMmtProductCount(ShopPd t) throws DataAccessException;

	List<ShopPd> selectMmtProductPaginatedList(ShopPd t) throws DataAccessException;

	/**
	 * @author Wu,Yang
	 * @version 2009-11-11 09:13
	 */
	List<ShopPd> selectShopPdWithBrandNameList(ShopPd t) throws DataAccessException;

	/**
	 * @author Jiang,JiaYong
	 * @version 2010-10-31
	 */
	List<ShopPd> selectShopPdFoIndexv3List(ShopPd t) throws DataAccessException;

	/**
	 * @author Wu,Yang
	 * @version 2009-11-11 09:13
	 */
	Long selectShopPdWithBrandNameCount(ShopPd t) throws DataAccessException;

	/**
	 * @author Jiang,JiaYong
	 * @version 2010-11-05
	 */
	Long selectShopPdOnlyCount(ShopPd t) throws DataAccessException;

	/**
	 * @author Wu,Yang
	 * @version 2009-11-11 09:13
	 */
	List<ShopPd> selectShopPdWithBrandNamePaginatedList(ShopPd t) throws DataAccessException;

	/**
	 * @author Jiang,Jiayong
	 * @version 2010-06-11
	 */
	List<ShopPd> selectShopPdForMmtongPaginatedList(ShopPd t) throws DataAccessException;

	/**
	 * @author Chen,Lilin
	 * @version 2010-04-20 14:30
	 */
	List<ShopPd> selectShopPdForContrastPaginatedList(ShopPd t) throws DataAccessException;

	/**
	 * @author Zhang,DaPeng
	 * @version 2010-10-13 14:30
	 */
	List<ShopPd> selectProductRank(ShopPd t) throws DataAccessException;

	/**
	 * @author Wu,ShangLong
	 * @version 2010-10-31
	 */
	List<ShopPd> selectProductRankForMMT_Index(ShopPd t) throws DataAccessException;

	/**
	 * @author Wu,ShangLong
	 * @version 2010-11-17
	 */
	List<ShopPd> selectShopPdFoIndexv4List(ShopPd t) throws DataAccessException;
}
