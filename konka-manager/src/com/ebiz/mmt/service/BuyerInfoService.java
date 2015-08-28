package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.BuyerInfo;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2010-06-02 12:48:04
 */
public interface BuyerInfoService {

	Long createBuyerInfo(BuyerInfo t);

	int modifyBuyerInfo(BuyerInfo t);

	int removeBuyerInfo(BuyerInfo t);

	BuyerInfo getBuyerInfo(BuyerInfo t);

	List<BuyerInfo> getBuyerInfoList(BuyerInfo t);

	Long getBuyerInfoCount(BuyerInfo t);

	/**
	 * @author Jiang,JiaYong
	 * @version 2010-06-11
	 */
	Long getBuyerInfoCountWithShopId(BuyerInfo t);

	List<BuyerInfo> getBuyerInfoPaginatedList(BuyerInfo t);

	/**
	 * @author Jiang,JiaYong
	 * @version 2010-06-11
	 */
	List<BuyerInfo> getBuyerInfoPaginatedListWithShopId(BuyerInfo t);
	
	Long createBuyerInfoAndMMTShopCustomer(BuyerInfo t);

}