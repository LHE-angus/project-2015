package com.ebiz.mmt.service;

import java.util.List;
import java.util.Map;

import com.ebiz.mmt.domain.KonkaR3SellImpInvalidData;

/**
 * @author Wu,Yang
 * @version 2011-11-16 17:47
 */
@SuppressWarnings("unchecked")
public interface KonkaR3SellImpInvalidDataService {

	Long createKonkaR3SellImpInvalidData(KonkaR3SellImpInvalidData t);

	int modifyKonkaR3SellImpInvalidData(KonkaR3SellImpInvalidData t);

	int removeKonkaR3SellImpInvalidData(KonkaR3SellImpInvalidData t);

	KonkaR3SellImpInvalidData getKonkaR3SellImpInvalidData(KonkaR3SellImpInvalidData t);

	List<KonkaR3SellImpInvalidData> getKonkaR3SellImpInvalidDataList(KonkaR3SellImpInvalidData t);

	Long getKonkaR3SellImpInvalidDataCount(KonkaR3SellImpInvalidData t);

	List<KonkaR3SellImpInvalidData> getKonkaR3SellImpInvalidDataPaginatedList(KonkaR3SellImpInvalidData t);
	
	/**
	 * @author Li,Ka
	 * @version 2011-11-17 根据R3_sell_date分组订单号
	 */
	List<Map> getOrderSnGroupList(KonkaR3SellImpInvalidData t);

}