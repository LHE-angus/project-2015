package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaoaFilesAuditAgentUser;

/**
 * @author Hui,Gang
 * @version Build 2010-12-24 10:27:40
 */
public interface KonkaoaFilesAuditAgentUserService {

	Long createKonkaoaFilesAuditAgentUser(KonkaoaFilesAuditAgentUser t);

	int modifyKonkaoaFilesAuditAgentUser(KonkaoaFilesAuditAgentUser t);

	int removeKonkaoaFilesAuditAgentUser(KonkaoaFilesAuditAgentUser t);

	KonkaoaFilesAuditAgentUser getKonkaoaFilesAuditAgentUser(KonkaoaFilesAuditAgentUser t);

	List<KonkaoaFilesAuditAgentUser> getKonkaoaFilesAuditAgentUserList(KonkaoaFilesAuditAgentUser t);

	Long getKonkaoaFilesAuditAgentUserCount(KonkaoaFilesAuditAgentUser t);

	List<KonkaoaFilesAuditAgentUser> getKonkaoaFilesAuditAgentUserPaginatedList(KonkaoaFilesAuditAgentUser t);
	
	Long getKonkaoaAgentFilesAuditPopedomCount(KonkaoaFilesAuditAgentUser t);

}