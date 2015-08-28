package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.GcxmAuditProcess;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2015-03-23 13:53:09
 */
public interface GcxmAuditProcessService {

	Long createGcxmAuditProcess(GcxmAuditProcess t);

	int modifyGcxmAuditProcess(GcxmAuditProcess t);

	int removeGcxmAuditProcess(GcxmAuditProcess t);

	GcxmAuditProcess getGcxmAuditProcess(GcxmAuditProcess t);

	List<GcxmAuditProcess> getGcxmAuditProcessList(GcxmAuditProcess t);

	Long getGcxmAuditProcessCount(GcxmAuditProcess t);

	List<GcxmAuditProcess> getGcxmAuditProcessPaginatedList(GcxmAuditProcess t);

}