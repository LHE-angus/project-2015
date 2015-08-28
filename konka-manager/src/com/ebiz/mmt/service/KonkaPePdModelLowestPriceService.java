package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaPePdModelLowestPrice;

/**
 * @author Wu,Yang
 * @version 2011-11-30 11:11
 */
public interface KonkaPePdModelLowestPriceService {

	Long createKonkaPePdModelLowestPrice(KonkaPePdModelLowestPrice t);

	int modifyKonkaPePdModelLowestPrice(KonkaPePdModelLowestPrice t);

	int removeKonkaPePdModelLowestPrice(KonkaPePdModelLowestPrice t);

	KonkaPePdModelLowestPrice getKonkaPePdModelLowestPrice(KonkaPePdModelLowestPrice t);

	List<KonkaPePdModelLowestPrice> getKonkaPePdModelLowestPriceList(KonkaPePdModelLowestPrice t);

	Long getKonkaPePdModelLowestPriceCount(KonkaPePdModelLowestPrice t);

	List<KonkaPePdModelLowestPrice> getKonkaPePdModelLowestPricePaginatedList(KonkaPePdModelLowestPrice t);

}