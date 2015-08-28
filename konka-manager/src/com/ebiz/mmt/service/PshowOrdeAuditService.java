package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.PshowOrdeAudit;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-08-09 17:45:40
 */
public interface PshowOrdeAuditService {

	Long createPshowOrdeAudit(PshowOrdeAudit t);

	int modifyPshowOrdeAudit(PshowOrdeAudit t);

	int removePshowOrdeAudit(PshowOrdeAudit t);

	PshowOrdeAudit getPshowOrdeAudit(PshowOrdeAudit t);

	List<PshowOrdeAudit> getPshowOrdeAuditList(PshowOrdeAudit t);

	Long getPshowOrdeAuditCount(PshowOrdeAudit t);

	List<PshowOrdeAudit> getPshowOrdeAuditPaginatedList(PshowOrdeAudit t);

}