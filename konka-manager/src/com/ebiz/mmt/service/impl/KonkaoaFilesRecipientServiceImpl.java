package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaoaFilesRecipientDao;
import com.ebiz.mmt.domain.KonkaoaFilesRecipient;
import com.ebiz.mmt.service.KonkaoaFilesRecipientService;

/**
 * @author Hui,Gang
 * @version Build 2010-12-13 14:49:33
 */
@Service
public class KonkaoaFilesRecipientServiceImpl implements KonkaoaFilesRecipientService {

	@Resource
	private KonkaoaFilesRecipientDao filesRecipientDao;

	public Long createKonkaoaFilesRecipient(KonkaoaFilesRecipient t) {
		return this.filesRecipientDao.insertEntity(t);
	}

	public KonkaoaFilesRecipient getKonkaoaFilesRecipient(KonkaoaFilesRecipient t) {
		return this.filesRecipientDao.selectEntity(t);
	}

	public Long getKonkaoaFilesRecipientCount(KonkaoaFilesRecipient t) {
		return this.filesRecipientDao.selectEntityCount(t);
	}

	public List<KonkaoaFilesRecipient> getKonkaoaFilesRecipientList(KonkaoaFilesRecipient t) {
		return this.filesRecipientDao.selectEntityList(t);
	}

	public int modifyKonkaoaFilesRecipient(KonkaoaFilesRecipient t) {
		return this.filesRecipientDao.updateEntity(t);
	}

	public int removeKonkaoaFilesRecipient(KonkaoaFilesRecipient t) {
		return this.filesRecipientDao.deleteEntity(t);
	}

	public List<KonkaoaFilesRecipient> getKonkaoaFilesRecipientPaginatedList(KonkaoaFilesRecipient t) {
		return this.filesRecipientDao.selectEntityPaginatedList(t);
	}

}