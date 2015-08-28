package com.ebiz.mmt.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ebiz.mmt.domain.KonkaXxSellBillDetails;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2012-01-10 08:56:05
 */
public interface KonkaXxSellBillDetailsDao extends EntityDao<KonkaXxSellBillDetails> {

	/**
	 * @author Wu,ShangLong
	 * @version 2012-1-10
	 */
	List<KonkaXxSellBillDetails> selectKonkaXxSellBillDetailsWithOrderPaginatedList(KonkaXxSellBillDetails t)
			throws DataAccessException;

	/**
	 * @author Wu,ShangLong
	 * @version 2012-1-10
	 */
	Long selectKonkaXxSellBillDetailsWithOrderCount(KonkaXxSellBillDetails t) throws DataAccessException;

	/**
	 * @author Ren,zhong
	 * @version 2012-01-11
	 */
	Long selectkonkaXxSellBillDetailsForSalesOrdersCount(KonkaXxSellBillDetails t);

	/**
	 * @author Ren,zhong
	 * @version 2012-01-11
	 */
	public List<KonkaXxSellBillDetails> selectKonkaXxSellBillDetailsForSalesOrdersForResultList(KonkaXxSellBillDetails t);

	/**
	 * @author Li,Yuan
	 * @version 2012-01-11
	 */
	Long selectKonkaXxSellBillDetailsForKcCount(KonkaXxSellBillDetails t);

	/**
	 * @author Wu,ShangLong
	 * @version 2012-1-12
	 */
	List<KonkaXxSellBillDetails> selectKonkaXxSellBillDetailsWithSpecList(KonkaXxSellBillDetails t)
			throws DataAccessException;

	/**
	 * @author Wu,ShangLong
	 * @version 2012-1-12
	 */
	List<KonkaXxSellBillDetails> selectKonkaXxSellBillDetailsWithPdTypeList(KonkaXxSellBillDetails t)
			throws DataAccessException;

	/**
	 * @author Wu,ShangLong
	 * @version 2012-04-05
	 */
	Long selectKonkaXxSellBillDetailsInRoadCount(KonkaXxSellBillDetails t);

	/**
	 * @author Wu,ShangLong
	 * @version 2012-04-05
	 */
	List<KonkaXxSellBillDetails> selectKonkaXxSellBillDetailsInRoadPaginatedList(KonkaXxSellBillDetails t);

	/**
	 * @author Hu,Hao
	 * @version 2012-04-20
	 */
	List<KonkaXxSellBillDetails> selectKonkaXxSellBillDetailsForJJSPaginatedList(KonkaXxSellBillDetails t);
	
	/**
	 * @author Hu,Hao
	 * @version 2012-04-20
	 */
	Long selectKonkaXxSellBillDetailsForJJSCount(KonkaXxSellBillDetails t);
	
	/**
	 * @author Hu,Hao
	 * @version 2013-03-23
	 */
	List<KonkaXxSellBillDetails> selectKonkaXxSellBillDetailsForInRoadList(KonkaXxSellBillDetails t);
}
