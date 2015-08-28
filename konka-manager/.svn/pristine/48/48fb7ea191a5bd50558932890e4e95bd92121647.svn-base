package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaoaFilesAuditAgentUserDao;
import com.ebiz.mmt.dao.KonkaoaFilesAuditPopedomDao;
import com.ebiz.mmt.domain.KonkaoaFilesAuditAgentUser;
import com.ebiz.mmt.domain.KonkaoaFilesAuditPopedom;
import com.ebiz.mmt.service.KonkaoaFilesAuditAgentUserService;

/**
 * @author Hui,Gang
 * @version Build 2010-12-24 10:27:40
 */
@Service
public class KonkaoaFilesAuditAgentUserServiceImpl implements KonkaoaFilesAuditAgentUserService {

	@Resource
	private KonkaoaFilesAuditAgentUserDao filesAuditAgentUserDao;

	@Resource
	private KonkaoaFilesAuditPopedomDao filesAuditPopedomDao;

	public Long createKonkaoaFilesAuditAgentUser(KonkaoaFilesAuditAgentUser t) {
		Long id = this.filesAuditAgentUserDao.insertEntity(t);

		List<KonkaoaFilesAuditPopedom> filesAuditPopedomList = t.getFilesAuditPopedom();
		if (null != filesAuditPopedomList) {
			for (KonkaoaFilesAuditPopedom fap : filesAuditPopedomList) {
				fap.setLink_id(id);
				this.filesAuditPopedomDao.insertEntity(fap);
			}
		}

		return id;
	}

	public KonkaoaFilesAuditAgentUser getKonkaoaFilesAuditAgentUser(KonkaoaFilesAuditAgentUser t) {
		return this.filesAuditAgentUserDao.selectEntity(t);
	}

	public Long getKonkaoaFilesAuditAgentUserCount(KonkaoaFilesAuditAgentUser t) {
		return this.filesAuditAgentUserDao.selectEntityCount(t);
	}

	public List<KonkaoaFilesAuditAgentUser> getKonkaoaFilesAuditAgentUserList(KonkaoaFilesAuditAgentUser t) {
		return this.filesAuditAgentUserDao.selectEntityList(t);
	}

	public int modifyKonkaoaFilesAuditAgentUser(KonkaoaFilesAuditAgentUser t) {
		KonkaoaFilesAuditPopedom _fap = new KonkaoaFilesAuditPopedom();
		_fap.setLink_id(t.getId());
		this.filesAuditPopedomDao.deleteEntity(_fap);

		List<KonkaoaFilesAuditPopedom> filesAuditPopedomList = t.getFilesAuditPopedom();
		if (null != filesAuditPopedomList) {
			for (KonkaoaFilesAuditPopedom fap : filesAuditPopedomList) {
				fap.setLink_id(t.getId());
				this.filesAuditPopedomDao.insertEntity(fap);
			}
		}

		return 1;
	}

	public int removeKonkaoaFilesAuditAgentUser(KonkaoaFilesAuditAgentUser t) {
		return this.filesAuditAgentUserDao.deleteEntity(t);
	}

	public List<KonkaoaFilesAuditAgentUser> getKonkaoaFilesAuditAgentUserPaginatedList(KonkaoaFilesAuditAgentUser t) {
		return this.filesAuditAgentUserDao.selectEntityPaginatedList(t);
	}

	public Long getKonkaoaAgentFilesAuditPopedomCount(KonkaoaFilesAuditAgentUser t) {
		return this.filesAuditAgentUserDao.selectKonkaoaAgentFilesAuditPopedomCount(t);
	}

}