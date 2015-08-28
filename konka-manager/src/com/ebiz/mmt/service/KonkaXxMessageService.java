package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaXxMessage;

/**
 * @author Ren,zhong
 * @version 2012-03-19 13:48
 */
public interface KonkaXxMessageService {

	Long createKonkaXxMessage(KonkaXxMessage t);

	int modifyKonkaXxMessage(KonkaXxMessage t);

	int removeKonkaXxMessage(KonkaXxMessage t);

	KonkaXxMessage getKonkaXxMessage(KonkaXxMessage t);

	List<KonkaXxMessage> getKonkaXxMessageList(KonkaXxMessage t);

	Long getKonkaXxMessageCount(KonkaXxMessage t);

	List<KonkaXxMessage> getKonkaXxMessagePaginatedList(KonkaXxMessage t);

}