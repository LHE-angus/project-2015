package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.EcAuctionBuy;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-08-28 15:50:21
 */
public interface EcAuctionBuyService {

	Long createEcAuctionBuy(EcAuctionBuy t);

	int modifyEcAuctionBuy(EcAuctionBuy t);

	int removeEcAuctionBuy(EcAuctionBuy t);

	EcAuctionBuy getEcAuctionBuy(EcAuctionBuy t);

	List<EcAuctionBuy> getEcAuctionBuyList(EcAuctionBuy t);

	Long getEcAuctionBuyCount(EcAuctionBuy t);

	List<EcAuctionBuy> getEcAuctionBuyPaginatedList(EcAuctionBuy t);
	
	EcAuctionBuy getEcAuctionBuyForMaxPrice(Long auction_id);
	
	int createEcAuctionBuyForAuction(EcAuctionBuy t);

}