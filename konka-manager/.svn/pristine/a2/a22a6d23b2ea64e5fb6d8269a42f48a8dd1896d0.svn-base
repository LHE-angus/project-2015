package com.ebiz.mmt.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ebiz.mmt.domain.KonkaoaFilesAuditNode;
import com.ebiz.ssi.dao.EntityDao;

/**
 * @author Hui,Gang
 * @version Build 2010-12-13 14:49:33
 */
public interface KonkaoaFilesAuditNodeDao extends EntityDao<KonkaoaFilesAuditNode> {
	
	List<KonkaoaFilesAuditNode> selectKonkaoaFilesAuditNodeListForView(KonkaoaFilesAuditNode filesAuditNode) throws DataAccessException;

	Long selectMaxLevel(KonkaoaFilesAuditNode t);
	
	List<HashMap> selectAuditList(KonkaoaFilesAuditNode t);
	
	List<HashMap> selectAuditInfoList(KonkaoaFilesAuditNode t);
	
	List<HashMap> selectKonkaoaFilesAuditNodeListForCust(KonkaoaFilesAuditNode filesAuditNode) throws DataAccessException;
	
	int updateOldNode(KonkaoaFilesAuditNode t);
}