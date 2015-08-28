package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.MmtEntpShop;

/**
 * Code by AutoGenerated Builder AutoGenerated Builder Version 2.1
 * 
 * @author Hui,Gang
 * @datetime 2011-09-29 15:54:30
 */
public interface MmtEntpShopService {

	Long createMmtEntpShop(MmtEntpShop t);

	int modifyMmtEntpShop(MmtEntpShop t);

	int removeMmtEntpShop(MmtEntpShop t);

	MmtEntpShop getMmtEntpShop(MmtEntpShop t);

	List<MmtEntpShop> getMmtEntpShopList(MmtEntpShop t);

	Long getMmtEntpShopCount(MmtEntpShop t);

	Long getMmtEntpShop2Count(MmtEntpShop t);

	List<MmtEntpShop> getMmtEntpShopPaginatedList(MmtEntpShop t);

	List<MmtEntpShop> getMmtEntpShopPaginatedListForSelect(MmtEntpShop t);

	// Long createMmtMmtEntpShop(MmtEntpShop t);

	List<MmtEntpShop> getMmtEntpShopNotRepeatPaginatedList(MmtEntpShop t);

	Long getMmtEntpShopNotRepeatCount(MmtEntpShop t);
}