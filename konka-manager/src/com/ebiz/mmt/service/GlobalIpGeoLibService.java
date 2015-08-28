package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.GlobalIpGeoLib;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2012-07-05 14:22:07
 */
public interface GlobalIpGeoLibService {

	Long createGlobalIpGeoLib(GlobalIpGeoLib t);

	int modifyGlobalIpGeoLib(GlobalIpGeoLib t);

	int removeGlobalIpGeoLib(GlobalIpGeoLib t);

	GlobalIpGeoLib getGlobalIpGeoLib(GlobalIpGeoLib t);

	List<GlobalIpGeoLib> getGlobalIpGeoLibList(GlobalIpGeoLib t);

	Long getGlobalIpGeoLibCount(GlobalIpGeoLib t);

	List<GlobalIpGeoLib> getGlobalIpGeoLibPaginatedList(GlobalIpGeoLib t);

	/**
	 * @author Hu,Hao
	 * @version 2012-07-05
	 */
	List<GlobalIpGeoLib> getGlobalIpGeoLibForIndexList(GlobalIpGeoLib t);

}