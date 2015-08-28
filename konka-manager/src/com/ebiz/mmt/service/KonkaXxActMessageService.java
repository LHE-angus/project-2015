package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaXxActMessage;

/**
 * @author Ren,zhong
 * @version 2012-03-19 13:48
 */
public interface KonkaXxActMessageService {

	Long createKonkaXxActMessage(KonkaXxActMessage t);

	int modifyKonkaXxActMessage(KonkaXxActMessage t);

	int removeKonkaXxActMessage(KonkaXxActMessage t);

	KonkaXxActMessage getKonkaXxActMessage(KonkaXxActMessage t);

	List<KonkaXxActMessage> getKonkaXxActMessageList(KonkaXxActMessage t);

	Long getKonkaXxActMessageCount(KonkaXxActMessage t);

	List<KonkaXxActMessage> getKonkaXxActMessagePaginatedList(KonkaXxActMessage t);
	
	Long createKonkaXxActMessageForNotice (KonkaXxActMessage t);

}