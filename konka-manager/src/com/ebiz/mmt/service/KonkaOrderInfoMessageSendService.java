package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaOrderInfoMessageSend;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-04-16 16:57:03
 */
public interface KonkaOrderInfoMessageSendService {

	Long createKonkaOrderInfoMessageSend(KonkaOrderInfoMessageSend t);

	int modifyKonkaOrderInfoMessageSend(KonkaOrderInfoMessageSend t);

	int removeKonkaOrderInfoMessageSend(KonkaOrderInfoMessageSend t);

	KonkaOrderInfoMessageSend getKonkaOrderInfoMessageSend(KonkaOrderInfoMessageSend t);

	List<KonkaOrderInfoMessageSend> getKonkaOrderInfoMessageSendList(KonkaOrderInfoMessageSend t);

	Long getKonkaOrderInfoMessageSendCount(KonkaOrderInfoMessageSend t);

	List<KonkaOrderInfoMessageSend> getKonkaOrderInfoMessageSendPaginatedList(KonkaOrderInfoMessageSend t);

}