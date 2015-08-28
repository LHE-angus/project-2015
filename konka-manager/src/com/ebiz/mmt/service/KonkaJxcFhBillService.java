package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaJxcFhBill;
import com.ebiz.mmt.domain.KonkaR3SellImpInvalidData;

/**
 * @author Wu,Yang
 * @version 2011-10-11 09:18
 */
public interface KonkaJxcFhBillService {

	Long createKonkaJxcFhBill(KonkaJxcFhBill t);

	int modifyKonkaJxcFhBill(KonkaJxcFhBill t);

	int removeKonkaJxcFhBill(KonkaJxcFhBill t);

	KonkaJxcFhBill getKonkaJxcFhBill(KonkaJxcFhBill t);

	List<KonkaJxcFhBill> getKonkaJxcFhBillList(KonkaJxcFhBill t);

	Long getKonkaJxcFhBillCount(KonkaJxcFhBill t);

	List<KonkaJxcFhBill> getKonkaJxcFhBillPaginatedList(KonkaJxcFhBill t);
	
	Long[] importR3SellKonkaJxcFhBill(List<KonkaJxcFhBill> t, List<KonkaR3SellImpInvalidData> invalidDataList, KonkaR3SellImpInvalidData konkaR3SellImpInvalidData);

}