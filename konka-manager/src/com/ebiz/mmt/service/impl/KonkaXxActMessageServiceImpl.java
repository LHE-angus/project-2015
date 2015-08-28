package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaXxActMessageDao;
import com.ebiz.mmt.domain.KonkaXxActMessage;
import com.ebiz.mmt.service.KonkaXxActMessageService;

/**
 * @author Ren,zhong
 * @version 2012-03-19 13:48
 */
@Service
public class KonkaXxActMessageServiceImpl implements KonkaXxActMessageService {

	@Resource
	private KonkaXxActMessageDao konkaXxActMessageDao;
	

	public Long createKonkaXxActMessage(KonkaXxActMessage t) {
		return this.konkaXxActMessageDao.insertEntity(t);
	}

	public KonkaXxActMessage getKonkaXxActMessage(KonkaXxActMessage t) {
		return this.konkaXxActMessageDao.selectEntity(t);
	}

	public Long getKonkaXxActMessageCount(KonkaXxActMessage t) {
		return this.konkaXxActMessageDao.selectEntityCount(t);
	}

	public List<KonkaXxActMessage> getKonkaXxActMessageList(KonkaXxActMessage t) {
		return this.konkaXxActMessageDao.selectEntityList(t);
	}

	public int modifyKonkaXxActMessage(KonkaXxActMessage t) {
		return this.konkaXxActMessageDao.updateEntity(t);
	}

	public int removeKonkaXxActMessage(KonkaXxActMessage t) {
		return this.konkaXxActMessageDao.deleteEntity(t);
	}

	public List<KonkaXxActMessage> getKonkaXxActMessagePaginatedList(KonkaXxActMessage t) {
		return this.konkaXxActMessageDao.selectEntityPaginatedList(t);
	}

	public Long createKonkaXxActMessageForNotice(KonkaXxActMessage t) {
		return this.konkaXxActMessageDao.insertKonkaXxActMessageForNotice(t);
	}

}
