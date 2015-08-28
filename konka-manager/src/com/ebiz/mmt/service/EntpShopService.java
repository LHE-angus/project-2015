package com.ebiz.mmt.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ebiz.mmt.domain.EntpShop;

/**
 * @author Jin,QingHua
 */
public interface EntpShopService {

	Long createEntpShop(EntpShop t);

	int modifyEntpShop(EntpShop t);

	int removeEntpShop(EntpShop t);

	EntpShop getEntpShop(EntpShop t);

	List<EntpShop> getEntpShopList(EntpShop t);

	Long getEntpShopCount(EntpShop t);

	/**
	 * @author Jiang,JiaYong
	 * @version 2010-07-01
	 */
	Long getEntpShopCountForJDZD(EntpShop t);

	/**
	 * @author Wang,XuLi
	 * @version 2010-08-07
	 */
	Long getEntpShopCountForMmstAdmin(EntpShop t);

	List<EntpShop> getEntpShopPaginatedList(EntpShop t);

	/**
	 * @author Jiang,JiaYong
	 * @version 2010-07-01
	 */
	List<EntpShop> getEntpShopPaginatedListForJDZD(EntpShop t);

	/**
	 * @author Wang,XuLi
	 * @version 2010-08-07
	 */
	List<EntpShop> getEntpShopPaginatedListForMmstAdmin(EntpShop t);

	List<EntpShop> getEntpShopListOrderByMark(EntpShop t);

	/**
	 * @author Wang,Zhaocai
	 * @version 2010-08-07
	 */
	Long getEntpShopCountForMMST(EntpShop t);

	/**
	 * @author Wang,Zhaocai
	 * @version 2010-08-07
	 */
	List<EntpShop> getEntpShopPaginatedListForMMST(EntpShop t);

	/**
	 * @author Xu,XiaoYuan
	 * @version 2010-09-03
	 */
	public List<EntpShop> getEntpShopListForMap(EntpShop t);

	/**
	 * @author Xu,XiaoYuan
	 * @version 2010-09-03
	 */
	public List<EntpShop> getEntpShopPaginatedListForMap(EntpShop t);

	/**
	 * @author Du,HongGang
	 * @version 2010-09-04
	 */
	Long getEntpShopCountForMap(EntpShop t);

	/**
	 * @author Du,HongGang
	 * @version 2010-09-07
	 */
	public List<EntpShop> getEntpShopListForXsCountTable(EntpShop t);

	/**
	 * @author Xing,XiuDong
	 * @version 2010-09-07
	 */
	public List<EntpShop> getEntpShopGMapAuditStaticsList(EntpShop t);

	/**
	 * @author Kun,Zheng
	 * @version 2011-06-16
	 */
	public List<EntpShop> getStdEntpMainListForNet(EntpShop t) throws DataAccessException;

	/**
	 * @author Du,HongGang
	 * @version 2010-09-20
	 */
	public Long getEntpShopCountForMapUpdated(EntpShop t) throws DataAccessException;

	/**
	 * @author Du,HongGang
	 * @version 2010-09-20
	 */
	public List<EntpShop> getEntpShopPaginatedListForMapUpdated(EntpShop t) throws DataAccessException;

	/**
	 * @author Du,HongGang
	 * @version 2010-09-20
	 */
	public List<EntpShop> getEntpShopListForMapUpdated(EntpShop t) throws DataAccessException;

	public List<EntpShop> getEntShopListWithPdType(EntpShop t) throws DataAccessException;

	/**
	 * @author Du,HongGang
	 * @version 2010-10-23
	 */
	public List<EntpShop> getEntpShopListForMyInfoOrder(EntpShop t) throws DataAccessException;

	/**
	 * @author Wu,ShangLong
	 * @version 2011-05-16
	 */
	Long getEntpShopInFindShopCount(EntpShop t);

	/**
	 * @author Wu,ShangLong
	 * @version 2011-05-16
	 */
	List<EntpShop> getEntpShopInFindShopPaginatedList(EntpShop t);

	/**
	 * @author Wu,ShangLong
	 * @version 2011-05-27
	 */
	List<EntpShop> getEntpInfoScores(EntpShop t);

	/**
	 * @author Wu,ShangLong
	 * @version 2011-05-27
	 */
	List<EntpShop> getEntpShopSellPerMonth(EntpShop t);

	/**
	 * @author Wu,Yang
	 * @version 2011-09-01
	 */
	List<EntpShop> getEntpShopForJxcList(EntpShop t);
}
