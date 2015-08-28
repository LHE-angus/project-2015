package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.GcxmProjAudit;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2015-03-23 13:53:09
 */
public interface GcxmProjAuditService {

	Long createGcxmProjAudit(GcxmProjAudit t);

	Long createGcxmProjOfferAudit(GcxmProjAudit t);

	int modifyGcxmProjAudit(GcxmProjAudit t);

	int removeGcxmProjAudit(GcxmProjAudit t);

	GcxmProjAudit getGcxmProjAudit(GcxmProjAudit t);

	List<GcxmProjAudit> getGcxmProjAuditList(GcxmProjAudit t);

	Long getGcxmProjAuditCount(GcxmProjAudit t);

	List<GcxmProjAudit> getGcxmProjAuditPaginatedList(GcxmProjAudit t);

}