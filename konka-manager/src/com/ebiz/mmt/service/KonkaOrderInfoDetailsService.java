package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaOrderInfoDetails;

/**
 * @author Wu,Yang
 * @version 2011-11-25 09:08
 */
public interface KonkaOrderInfoDetailsService {

	Long createKonkaOrderInfoDetails(KonkaOrderInfoDetails t);

	int modifyKonkaOrderInfoDetails(KonkaOrderInfoDetails t);

	int removeKonkaOrderInfoDetails(KonkaOrderInfoDetails t);

	KonkaOrderInfoDetails getKonkaOrderInfoDetails(KonkaOrderInfoDetails t);

	List<KonkaOrderInfoDetails> getKonkaOrderInfoDetailsList(KonkaOrderInfoDetails t);

	Long getKonkaOrderInfoDetailsCount(KonkaOrderInfoDetails t);

	List<KonkaOrderInfoDetails> getKonkaOrderInfoDetailsPaginatedList(KonkaOrderInfoDetails t);
	
	List<KonkaOrderInfoDetails> getKonkaOrderInfoDetailsForTrans(KonkaOrderInfoDetails t);
}