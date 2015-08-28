package com.ebiz.mmt.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ebiz.mmt.domain.JdxxEntpSell;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2010-05-28 16:48:39
 */
public interface JdxxEntpSellService {

	Long createJdxxEntpSell(JdxxEntpSell t);

	Long createJdxxEntpSellForMmstJxc(JdxxEntpSell t);

	int modifyJdxxEntpSell(JdxxEntpSell t);

	/**
	 * @author Chen,LiLin
	 * @vesion 2011-04-15
	 * @desc 启用进销存后销售信息更新方法
	 */
	int modifyJdxxEntpSellForMmstJxc(JdxxEntpSell t);

	int removeJdxxEntpSell(JdxxEntpSell t);

	JdxxEntpSell getJdxxEntpSell(JdxxEntpSell t);

	List<JdxxEntpSell> getJdxxEntpSellList(JdxxEntpSell t);

	Long getJdxxEntpSellCount(JdxxEntpSell t);

	/**
	 * @author Jiang,JiaYong
	 * @vesion 2010-07-07
	 */
	Long getJdxxEntpSellCountForStatistics(JdxxEntpSell t);

	List<JdxxEntpSell> getJdxxEntpSellPaginatedList(JdxxEntpSell t);

	/**
	 * @author Jiang,JiaYong
	 * @vesion 2010-05-31
	 */
	List<JdxxEntpSell> getJdxxEntpSellIncludeMdNameAndBrandNameList(JdxxEntpSell t);

	/**
	 * @author Jiang,JiaYong
	 * @vesion 2010-12-02
	 */
	List<JdxxEntpSell> getJdxxEntpSellIncludeMdNameAndBrandNameForMmmtList(JdxxEntpSell t);

	/**
	 * @author Xing,XiuDong
	 * @vesion 2010-11-17
	 */
	List<JdxxEntpSell> getJdxxEntpSellCountGroupByPdType(JdxxEntpSell t);

	/**
	 * @author Xing,XiuDong
	 * @vesion 2010-11-17
	 */
	List<JdxxEntpSell> getJdxxEntpSellCountGroupByPdTypeAndWeek(JdxxEntpSell t);

	/**
	 * @author Xing,XiuDong
	 * @vesion 2011-6-22
	 */
	List<JdxxEntpSell> getTopPdModelList(JdxxEntpSell t);

	// test
	List<Long> getJdxxEntpSellIdStrings(JdxxEntpSell t);

	/**
	 * @author Du,HongGang
	 * @version 2010-12-07
	 */
	List<JdxxEntpSell> getJdxxEntpSellForBrandXsSalesList(JdxxEntpSell t) throws DataAccessException;

	/**
	 * @author Chen,LiLin
	 * @version 2011-3-30
	 */
	List<JdxxEntpSell> getJdxxEntpSellWithMdNamePaginatedList(JdxxEntpSell t) throws DataAccessException;

	/**
	 * @author Wu,ShangLong
	 * @version 2011-3-30
	 */
	List<JdxxEntpSell> getJdxxEntpSellWithMdNameList(JdxxEntpSell t) throws DataAccessException;
}