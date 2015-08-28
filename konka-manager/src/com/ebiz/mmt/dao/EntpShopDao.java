package com.ebiz.mmt.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ebiz.mmt.domain.EntpShop;
import com.ebiz.ssi.dao.EntityDao;

/**
 * @author Jin,QingHua
 */
public interface EntpShopDao extends EntityDao<EntpShop> {

	/**
	 * @author Jiang,JiaYong
	 */
	public List<EntpShop> selectEntpShopListOrderByMark(EntpShop t);

	/**
	 * @author Jiang,JiaYong
	 * @version 2010-07-01
	 */
	public Long selectEntpShopCountForJDZD(EntpShop t) throws DataAccessException;

	/**
	 * @author Wang,XuLi
	 * @version 2010-08-07
	 */
	public Long selectEntpShopCountForMmstAdmin(EntpShop t) throws DataAccessException;

	/**
	 * @author Jiang,JiaYong
	 * @version 2010-07-01
	 */
	public List<EntpShop> selectEntpShopPaginatedListForJDZD(EntpShop t) throws DataAccessException;

	/**
	 * @author Wang,XuLi
	 * @version 2010-08-07
	 */
	public List<EntpShop> selectEntpShopPaginatedListForMmstAdmin(EntpShop t) throws DataAccessException;

	/**
	 * @author Wang,Zhaocai
	 * @version 2010-08-07
	 */
	public Long selectEntpShopCountForMMST(EntpShop t) throws DataAccessException;

	/**
	 * @author Wang,Zhaocai
	 * @version 2010-08-07
	 */
	public List<EntpShop> selectEntpShopPaginatedListForMMST(EntpShop t) throws DataAccessException;

	/**
	 * @author Xu,XiaoYuan
	 * @version 2010-09-03
	 */
	public List<EntpShop> selectEntpShopListForMap(EntpShop t) throws DataAccessException;

	/**
	 * @author Xu,XiaoYuan
	 * @version 2010-09-03
	 */
	public List<EntpShop> selectEntpShopPaginatedListForMap(EntpShop t) throws DataAccessException;

	/**
	 * @author Du,HongGang
	 * @version 2010-09-04
	 */
	public Long selectEntpShopCountForMap(EntpShop t) throws DataAccessException;

	/**
	 * @author Du,HongGang
	 * @version 2010-09-07
	 */
	public List<EntpShop> selectEntpShopListForXsCountTable(EntpShop t) throws DataAccessException;

	/**
	 * @author Du,HongGang
	 * @version 2010-09-07
	 */
	public List<EntpShop> selectEntpShopGMapAuditStaticsList(EntpShop t) throws DataAccessException;

	/**
	 * @author Kun,Zheng
	 * @version 2011-06-16
	 */
	public List<EntpShop> selectStdEntpMainListForNet(EntpShop t) throws DataAccessException;

	/**
	 * @author Du,HongGang
	 * @version 2010-09-20
	 */
	public Long selectEntpShopCountForMapUpdated(EntpShop t) throws DataAccessException;

	/**
	 * @author Du,HongGang
	 * @version 2010-09-20
	 */
	public List<EntpShop> selectEntpShopPaginatedListForMapUpdated(EntpShop t) throws DataAccessException;

	/**
	 * @author Du,HongGang
	 * @version 2010-09-20
	 */
	public List<EntpShop> selectEntpShopListForMapUpdated(EntpShop t) throws DataAccessException;

	public List<EntpShop> selectEntpShopListWithPdType(EntpShop t) throws DataAccessException;

	/**
	 * @author Du,HongGang
	 * @version 2010-10-23
	 */
	public List<EntpShop> selectEntpShopListForMyInfoOrder(EntpShop t) throws DataAccessException;

	/**
	 * @author Wu,ShangLong
	 * @version 2011-05-16
	 */
	Long selectEntpShopInFindShopCount(EntpShop t) throws DataAccessException;

	/**
	 * @author Wu,ShangLong
	 * @version 2011-05-16
	 */
	List<EntpShop> selectEntpShopInFindShopPaginatedList(EntpShop t) throws DataAccessException;

	/**
	 * @author Wu,ShangLong
	 * @version 2011-05-27
	 */
	List<EntpShop> selectEntpInfoScores(EntpShop t) throws DataAccessException;

	/**
	 * @author Wu,ShangLong
	 * @version 2011-05-27
	 */
	List<EntpShop> selectEntpShopSellPerMonth(EntpShop t) throws DataAccessException;

	/**
	 * @author Wu,Yang
	 * @version 2011-09-01
	 */
	List<EntpShop> selectEntpShopForJxcList(EntpShop t) throws DataAccessException;

}
