package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaOrderInfoTrans;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-05-22 15:41:39
 */
public interface KonkaOrderInfoTransService {

	Long createKonkaOrderInfoTrans(KonkaOrderInfoTrans t);

	int modifyKonkaOrderInfoTrans(KonkaOrderInfoTrans t);

	int removeKonkaOrderInfoTrans(KonkaOrderInfoTrans t);

	KonkaOrderInfoTrans getKonkaOrderInfoTrans(KonkaOrderInfoTrans t);
	
	KonkaOrderInfoTrans getKonkaOrderInfoTransForPrint(KonkaOrderInfoTrans t);

	List<KonkaOrderInfoTrans> getKonkaOrderInfoTransList(KonkaOrderInfoTrans t);

	Long getKonkaOrderInfoTransCount(KonkaOrderInfoTrans t);

	List<KonkaOrderInfoTrans> getKonkaOrderInfoTransPaginatedList(KonkaOrderInfoTrans t);
	
	Long getKonkaOrderInfoTransForFHDCount(KonkaOrderInfoTrans t);
	
	List<KonkaOrderInfoTrans> getKonkaOrderInfoTransForFHDPaginatedList(KonkaOrderInfoTrans t);

	Long createKonkaOrderInfoTransAndDetails(
			KonkaOrderInfoTrans konkaOrderInfoTrans);

	int modifyKonkaOrderInfoTransAndDetails(
			KonkaOrderInfoTrans konkaOrderInfoTrans);

}