package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.EcMessage;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-03-17 10:30:16
 */
public interface EcMessageService {

	Long createEcMessage(EcMessage t);

	int modifyEcMessage(EcMessage t);

	int removeEcMessage(EcMessage t);

	EcMessage getEcMessage(EcMessage t);

	List<EcMessage> getEcMessageList(EcMessage t);

	Long getEcMessageCount(EcMessage t);

	List<EcMessage> getEcMessagePaginatedList(EcMessage t);

}