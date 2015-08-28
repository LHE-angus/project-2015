package com.ebiz.mmt.dao.ibatis;

import java.util.HashMap;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ebiz.mmt.dao.KonkaoaFilesAuditNodeDao;
import com.ebiz.mmt.domain.KonkaoaFilesAuditNode;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * @author Hui,Gang
 * @version Build 2010-12-13 14:49:33
 */
@Repository
public class KonkaoaFilesAuditNodeDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaoaFilesAuditNode> implements KonkaoaFilesAuditNodeDao {

	// selectFilesAuditNodeListForView
	@SuppressWarnings("unchecked")
	public List<KonkaoaFilesAuditNode> selectKonkaoaFilesAuditNodeListForView(KonkaoaFilesAuditNode filesAuditNode)
			throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaoaFilesAuditNodeListForView", filesAuditNode);
	}

	@Override
	public Long selectMaxLevel(KonkaoaFilesAuditNode t) {
		
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectMaxLevel",t);
	}

	@Override
	public List<HashMap> selectAuditList(KonkaoaFilesAuditNode t) {
		return super.getSqlMapClientTemplate().queryForList("selecAuditList",t);
	}

	@Override
	public List<HashMap> selectKonkaoaFilesAuditNodeListForCust(
			KonkaoaFilesAuditNode filesAuditNode) throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaoaFilesAuditNodeListForCust", filesAuditNode);
	}

	@Override
	public int updateOldNode(KonkaoaFilesAuditNode t) {
		return super.getSqlMapClientTemplate().update("updateOldNode", t);
	}

	@Override
	public List<HashMap> selectAuditInfoList(KonkaoaFilesAuditNode t) {
		return super.getSqlMapClientTemplate().queryForList("getAuditInfoList", t);
	}

}