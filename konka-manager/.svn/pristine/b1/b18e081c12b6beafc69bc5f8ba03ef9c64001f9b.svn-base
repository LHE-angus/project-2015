package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaOrderInfo;
import com.ebiz.mmt.domain.KonkaOrderInfoAudit;
import com.ebiz.mmt.domain.KonkaOrderInfoDetails;

/**
 * @author Wu,Yang
 * @version 2011-11-26 11:39
 */
public interface KonkaOrderInfoAuditService {

	Long createKonkaOrderInfoAudit(KonkaOrderInfoAudit t);

	int modifyKonkaOrderInfoAudit(KonkaOrderInfoAudit t);

	int removeKonkaOrderInfoAudit(KonkaOrderInfoAudit t);

	KonkaOrderInfoAudit getKonkaOrderInfoAudit(KonkaOrderInfoAudit t);

	List<KonkaOrderInfoAudit> getKonkaOrderInfoAuditList(KonkaOrderInfoAudit t);

	Long getKonkaOrderInfoAuditCount(KonkaOrderInfoAudit t);

	List<KonkaOrderInfoAudit> getKonkaOrderInfoAuditPaginatedList(KonkaOrderInfoAudit t);

	/** 订单对应的审核记录包含角色名称和ID */
	List<KonkaOrderInfoAudit> getKonkaOrderInfoAuditWithRoleInfoList(KonkaOrderInfoAudit t);

	/** 订单对应的审核记录中最高的审核级别 */
	Long getKonkaOrderInfoAuditWithMaxAuditLevel(KonkaOrderInfoAudit t);

	/**
	 * @author Wu,ShangLong
	 * @version 2013-08-05
	 */
	Long createKonkaOrderInfoAuditNew(KonkaOrderInfoAudit t);

	/**
	 * @author Wu,ShangLong
	 * @version 2013-08-05
	 */
	Long createKonkaOrderInfoAuditNewForNewProcess(KonkaOrderInfoAudit t);

	List<KonkaOrderInfoAudit> getKonkaOrderInfoAuditAndRoleList(KonkaOrderInfoAudit t);

	Boolean is_Four_Week(KonkaOrderInfo order, List<KonkaOrderInfoDetails> orderDetailsList);

	Boolean is_Four_Week(KonkaOrderInfo order, List<KonkaOrderInfoDetails> orderDetailsList,int audit_proc_cond);
	
	Long createKonkaOrderInfoAuditNewForChange(KonkaOrderInfoAudit entity);

}