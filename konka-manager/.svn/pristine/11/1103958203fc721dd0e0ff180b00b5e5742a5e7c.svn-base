package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcMessageDao;
import com.ebiz.mmt.domain.EcMessage;
import com.ebiz.mmt.service.EcMessageService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-03-17 10:30:16
 */
@Service
public class EcMessageServiceImpl implements EcMessageService {

	@Resource
	private EcMessageDao ecMessageDao;
	

	public Long createEcMessage(EcMessage t) {
		return this.ecMessageDao.insertEntity(t);
	}

	public EcMessage getEcMessage(EcMessage t) {
		return this.ecMessageDao.selectEntity(t);
	}

	public Long getEcMessageCount(EcMessage t) {
		return this.ecMessageDao.selectEntityCount(t);
	}

	public List<EcMessage> getEcMessageList(EcMessage t) {
		return this.ecMessageDao.selectEntityList(t);
	}

	public int modifyEcMessage(EcMessage t) {
		return this.ecMessageDao.updateEntity(t);
	}

	public int removeEcMessage(EcMessage t) {
		return this.ecMessageDao.deleteEntity(t);
	}

	public List<EcMessage> getEcMessagePaginatedList(EcMessage t) {
		return this.ecMessageDao.selectEntityPaginatedList(t);
	}

}
