package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.EcGift;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-09-09 10:06:25
 */
public interface EcGiftService {

	Long createEcGift(EcGift t);

	int modifyEcGift(EcGift t);

	int removeEcGift(EcGift t);

	EcGift getEcGift(EcGift t);

	List<EcGift> getEcGiftList(EcGift t);

	Long getEcGiftCount(EcGift t);

	List<EcGift> getEcGiftPaginatedList(EcGift t);

	Long createEcGiftIncludeContent(EcGift t);

	int modifyEcGiftIncludeContent(EcGift t);

}