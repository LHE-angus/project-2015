package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaoaFilesAuditPopedomDao;
import com.ebiz.mmt.domain.KonkaoaFilesAuditPopedom;
import com.ebiz.mmt.service.KonkaoaFilesAuditPopedomService;

/**
 * @author Hui,Gang
 * @version Build 2010-12-24 10:27:40
 */
@Service
public class KonkaoaFilesAuditPopedomServiceImpl implements KonkaoaFilesAuditPopedomService {

	@Resource
	private KonkaoaFilesAuditPopedomDao filesAuditPopedomDao;

	public Long createKonkaoaFilesAuditPopedom(KonkaoaFilesAuditPopedom t) {
		return this.filesAuditPopedomDao.insertEntity(t);
	}

	public KonkaoaFilesAuditPopedom getKonkaoaFilesAuditPopedom(KonkaoaFilesAuditPopedom t) {
		return this.filesAuditPopedomDao.selectEntity(t);
	}

	public Long getKonkaoaFilesAuditPopedomCount(KonkaoaFilesAuditPopedom t) {
		return this.filesAuditPopedomDao.selectEntityCount(t);
	}

	public List<KonkaoaFilesAuditPopedom> getKonkaoaFilesAuditPopedomList(KonkaoaFilesAuditPopedom t) {
		return this.filesAuditPopedomDao.selectEntityList(t);
	}

	public int modifyKonkaoaFilesAuditPopedom(KonkaoaFilesAuditPopedom t) {
		return this.filesAuditPopedomDao.updateEntity(t);
	}

	public int removeKonkaoaFilesAuditPopedom(KonkaoaFilesAuditPopedom t) {
		return this.filesAuditPopedomDao.deleteEntity(t);
	}

	public List<KonkaoaFilesAuditPopedom> getKonkaoaFilesAuditPopedomPaginatedList(KonkaoaFilesAuditPopedom t) {
		return this.filesAuditPopedomDao.selectEntityPaginatedList(t);
	}

}