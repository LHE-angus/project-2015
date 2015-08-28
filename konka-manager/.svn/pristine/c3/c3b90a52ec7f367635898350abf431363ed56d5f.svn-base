package com.ebiz.mmt.dao.ibatis;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ebiz.mmt.dao.KonkaoaFilesAuditAgentUserDao;
import com.ebiz.mmt.domain.KonkaoaFilesAuditAgentUser;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * @author Hui,Gang
 * @version Build 2010-12-24 10:27:40
 */
@Repository
public class KonkaoaFilesAuditAgentUserDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaoaFilesAuditAgentUser> implements
		KonkaoaFilesAuditAgentUserDao {

	public Long selectKonkaoaAgentFilesAuditPopedomCount(KonkaoaFilesAuditAgentUser filesAuditAgentUser) throws DataAccessException {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectKonkaoaAgentFilesAuditPopedomCount",
				filesAuditAgentUser);
	}

}