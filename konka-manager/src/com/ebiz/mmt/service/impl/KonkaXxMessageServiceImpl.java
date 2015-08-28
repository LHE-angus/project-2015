package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaXxMessageDao;
import com.ebiz.mmt.domain.KonkaXxMessage;
import com.ebiz.mmt.service.KonkaXxMessageService;

/**
 * @author Ren,zhong
 * @version 2012-03-19 13:48
 */
@Service
public class KonkaXxMessageServiceImpl implements KonkaXxMessageService {

	@Resource
	private KonkaXxMessageDao konkaXxMessageDao;
	

	public Long createKonkaXxMessage(KonkaXxMessage t) {
		return this.konkaXxMessageDao.insertEntity(t);
	}

	public KonkaXxMessage getKonkaXxMessage(KonkaXxMessage t) {
		return this.konkaXxMessageDao.selectEntity(t);
	}

	public Long getKonkaXxMessageCount(KonkaXxMessage t) {
		return this.konkaXxMessageDao.selectEntityCount(t);
	}

	public List<KonkaXxMessage> getKonkaXxMessageList(KonkaXxMessage t) {
		return this.konkaXxMessageDao.selectEntityList(t);
	}

	public int modifyKonkaXxMessage(KonkaXxMessage t) {
		return this.konkaXxMessageDao.updateEntity(t);
	}

	public int removeKonkaXxMessage(KonkaXxMessage t) {
		return this.konkaXxMessageDao.deleteEntity(t);
	}

	public List<KonkaXxMessage> getKonkaXxMessagePaginatedList(KonkaXxMessage t) {
		return this.konkaXxMessageDao.selectEntityPaginatedList(t);
	}

}
