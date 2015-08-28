package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.StdEntpLogin;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2010-12-23 10:47:01
 */
public interface StdEntpLoginService {

	Long createStdEntpLogin(StdEntpLogin t);

	int modifyStdEntpLogin(StdEntpLogin t);

	int removeStdEntpLogin(StdEntpLogin t);

	StdEntpLogin getStdEntpLogin(StdEntpLogin t);

	List<StdEntpLogin> getStdEntpLoginList(StdEntpLogin t);

	/**
	 * @author Jiang,JiaYong
	 * @version 2010-12-23
	 */
	List<StdEntpLogin> getStdEntpLoginGroupByVersionList(StdEntpLogin t);

	Long getStdEntpLoginCount(StdEntpLogin t);

	List<StdEntpLogin> getStdEntpLoginPaginatedList(StdEntpLogin t);

	/**
	 * @author Ren,Zhong
	 * @version 2011-7-6
	 */
	List<StdEntpLogin> getEveryDayLoginShopCountForResultList(StdEntpLogin t);

	/**
	 * @author Wu,ShangLong
	 * @version 2011-07-06
	 */
	List<StdEntpLogin> getStdEntpLoginNumPerDayList(StdEntpLogin t);

}