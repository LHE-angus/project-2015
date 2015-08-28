package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.JxcJnhmSellBillAudit;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2012-08-17 08:47:28
 */
public interface JxcJnhmSellBillAuditService {

	Long createJxcJnhmSellBillAudit(JxcJnhmSellBillAudit t);

	int modifyJxcJnhmSellBillAudit(JxcJnhmSellBillAudit t);

	int removeJxcJnhmSellBillAudit(JxcJnhmSellBillAudit t);

	JxcJnhmSellBillAudit getJxcJnhmSellBillAudit(JxcJnhmSellBillAudit t);

	List<JxcJnhmSellBillAudit> getJxcJnhmSellBillAuditList(JxcJnhmSellBillAudit t);

	Long getJxcJnhmSellBillAuditCount(JxcJnhmSellBillAudit t);

	List<JxcJnhmSellBillAudit> getJxcJnhmSellBillAuditPaginatedList(JxcJnhmSellBillAudit t);

}