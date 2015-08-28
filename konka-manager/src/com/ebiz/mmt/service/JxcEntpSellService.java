package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.JxcEntpSell;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-02-10 11:05:59
 */
public interface JxcEntpSellService {

	Long createJxcEntpSell(JxcEntpSell t);

	int modifyJxcEntpSell(JxcEntpSell t);

	int removeJxcEntpSell(JxcEntpSell t);

	JxcEntpSell getJxcEntpSell(JxcEntpSell t);

	List<JxcEntpSell> getJxcEntpSellList(JxcEntpSell t);

	Long getJxcEntpSellCount(JxcEntpSell t);

	List<JxcEntpSell> getJxcEntpSellPaginatedList(JxcEntpSell t);

	/**
	 * @author Wu,ShangLong
	 * @version 2011-02-10
	 */
	List<JxcEntpSell> getJxcEntpSellListForPart(JxcEntpSell t);

	/**
	 * @author Wu,ShangLong
	 * @version 2011-02-10
	 */
	Long getJxcEntpSellforDistinctCount(JxcEntpSell t);

}