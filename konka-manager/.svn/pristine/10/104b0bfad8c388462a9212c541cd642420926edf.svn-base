package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaOrderInfoMessageSendDao;
import com.ebiz.mmt.domain.KonkaOrderInfoMessageSend;
import com.ebiz.mmt.service.KonkaOrderInfoMessageSendService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-04-16 16:57:03
 */
@Service
public class KonkaOrderInfoMessageSendServiceImpl implements KonkaOrderInfoMessageSendService {

	@Resource
	private KonkaOrderInfoMessageSendDao konkaOrderInfoMessageSendDao;
	

	public Long createKonkaOrderInfoMessageSend(KonkaOrderInfoMessageSend t) {
		return this.konkaOrderInfoMessageSendDao.insertEntity(t);
	}

	public KonkaOrderInfoMessageSend getKonkaOrderInfoMessageSend(KonkaOrderInfoMessageSend t) {
		return this.konkaOrderInfoMessageSendDao.selectEntity(t);
	}

	public Long getKonkaOrderInfoMessageSendCount(KonkaOrderInfoMessageSend t) {
		return this.konkaOrderInfoMessageSendDao.selectEntityCount(t);
	}

	public List<KonkaOrderInfoMessageSend> getKonkaOrderInfoMessageSendList(KonkaOrderInfoMessageSend t) {
		return this.konkaOrderInfoMessageSendDao.selectEntityList(t);
	}

	public int modifyKonkaOrderInfoMessageSend(KonkaOrderInfoMessageSend t) {
		return this.konkaOrderInfoMessageSendDao.updateEntity(t);
	}

	public int removeKonkaOrderInfoMessageSend(KonkaOrderInfoMessageSend t) {
		return this.konkaOrderInfoMessageSendDao.deleteEntity(t);
	}

	public List<KonkaOrderInfoMessageSend> getKonkaOrderInfoMessageSendPaginatedList(KonkaOrderInfoMessageSend t) {
		return this.konkaOrderInfoMessageSendDao.selectEntityPaginatedList(t);
	}

}
