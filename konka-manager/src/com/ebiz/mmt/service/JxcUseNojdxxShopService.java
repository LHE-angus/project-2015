package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.JxcUseNojdxxShop;

/**
 * @author Wu,Yang
 * @version 2011-09-26 15:03
 */
public interface JxcUseNojdxxShopService {

	Long createJxcUseNojdxxShop(JxcUseNojdxxShop t);

	int modifyJxcUseNojdxxShop(JxcUseNojdxxShop t);

	int removeJxcUseNojdxxShop(JxcUseNojdxxShop t);

	JxcUseNojdxxShop getJxcUseNojdxxShop(JxcUseNojdxxShop t);

	List<JxcUseNojdxxShop> getJxcUseNojdxxShopList(JxcUseNojdxxShop t);

	Long getJxcUseNojdxxShopCount(JxcUseNojdxxShop t);

	List<JxcUseNojdxxShop> getJxcUseNojdxxShopPaginatedList(JxcUseNojdxxShop t);

}