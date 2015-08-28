package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaOrderInfoDetailsAudit;

/**
 * @author Xu,XiaoYuan
 * @version 2012-02-15 10:24
 */
public interface KonkaOrderInfoDetailsAuditService {

	Long createKonkaOrderInfoDetailsAudit(KonkaOrderInfoDetailsAudit t);

	int modifyKonkaOrderInfoDetailsAudit(KonkaOrderInfoDetailsAudit t);

	int removeKonkaOrderInfoDetailsAudit(KonkaOrderInfoDetailsAudit t);

	KonkaOrderInfoDetailsAudit getKonkaOrderInfoDetailsAudit(KonkaOrderInfoDetailsAudit t);

	List<KonkaOrderInfoDetailsAudit> getKonkaOrderInfoDetailsAuditList(KonkaOrderInfoDetailsAudit t);

	Long getKonkaOrderInfoDetailsAuditCount(KonkaOrderInfoDetailsAudit t);

	List<KonkaOrderInfoDetailsAudit> getKonkaOrderInfoDetailsAuditPaginatedList(KonkaOrderInfoDetailsAudit t);

}