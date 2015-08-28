package com.ebiz.mmt.service;

import java.util.HashMap;
import java.util.List;

import com.ebiz.mmt.domain.KonkaoaFilesAuditNode;

/**
 * @author Hui,Gang
 * @version Build 2010-12-13 14:49:33
 */
public interface KonkaoaFilesAuditNodeService {

	Long createKonkaoaFilesAuditNode(KonkaoaFilesAuditNode t);
	
	Long createKonkaoaFilesAuditNodeList(KonkaoaFilesAuditNode t);

	int modifyKonkaoaFilesAuditNode(KonkaoaFilesAuditNode t);

	int removeKonkaoaFilesAuditNode(KonkaoaFilesAuditNode t);

	KonkaoaFilesAuditNode getKonkaoaFilesAuditNode(KonkaoaFilesAuditNode t);

	List<KonkaoaFilesAuditNode> getKonkaoaFilesAuditNodeList(KonkaoaFilesAuditNode t);

	Long getKonkaoaFilesAuditNodeCount(KonkaoaFilesAuditNode t);

	List<KonkaoaFilesAuditNode> getKonkaoaFilesAuditNodePaginatedList(KonkaoaFilesAuditNode t);

	List<KonkaoaFilesAuditNode> getKonkaoaFilesAuditNodeListForView(KonkaoaFilesAuditNode t);
	
	/**
	 * 待审客户中查询审批记录
	 * @author Liang,HouEn
	 * 2014-10-14
	 * @param t
	 * @return
	 */
	List<HashMap> getKonkaoaFilesAuditNodeListForCust(KonkaoaFilesAuditNode t);

	/**
	 * 需求申请审批
	 * @author Angus
	 * @date 2014-8-7
	 * @param t
	 * @return
	 */
	int modifySystemAplication(KonkaoaFilesAuditNode t);
	
	/**
	 * 获取最后一个审批人ID
	 * @author Angus
	 * @date 2014-8-7
	 * @param t
	 * @return
	 */
	Long getMaxKonkaoaFilesAuditNode(KonkaoaFilesAuditNode t);
	
	List<HashMap> getAplicationAuditList(KonkaoaFilesAuditNode t);
	
	List<HashMap> getAuditInfoList(KonkaoaFilesAuditNode t);
	
	/**
	 * 更新审批记录设置audit_level设置成0，表示非最新审批记录
	 * @author Liang,HouEn
	 * Oct 17, 2014
	 * @param t
	 * @return
	 */
	int updateOldNode(KonkaoaFilesAuditNode t);
}