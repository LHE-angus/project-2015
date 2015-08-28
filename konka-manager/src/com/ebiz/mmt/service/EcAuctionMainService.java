package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.EcAuctionMain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-08-28 15:50:21
 */
public interface EcAuctionMainService {

	Long createEcAuctionMain(EcAuctionMain t);

	int modifyEcAuctionMain(EcAuctionMain t);

	int removeEcAuctionMain(EcAuctionMain t);

	EcAuctionMain getEcAuctionMain(EcAuctionMain t);

	List<EcAuctionMain> getEcAuctionMainList(EcAuctionMain t);

	Long getEcAuctionMainCount(EcAuctionMain t);

	List<EcAuctionMain> getEcAuctionMainPaginatedList(EcAuctionMain t);
	
	int modifyEcAuctionMainViewCounts(EcAuctionMain t);
	
	int modifyEcAuctionMainDelayNum(EcAuctionMain t);

}