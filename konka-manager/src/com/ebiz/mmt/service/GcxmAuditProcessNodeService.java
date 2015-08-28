package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.GcxmAuditProcessNode;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2015-03-23 13:53:09
 */
public interface GcxmAuditProcessNodeService {

	Long createGcxmAuditProcessNode(GcxmAuditProcessNode t);

	int modifyGcxmAuditProcessNode(GcxmAuditProcessNode t);

	int removeGcxmAuditProcessNode(GcxmAuditProcessNode t);

	GcxmAuditProcessNode getGcxmAuditProcessNode(GcxmAuditProcessNode t);

	List<GcxmAuditProcessNode> getGcxmAuditProcessNodeList(GcxmAuditProcessNode t);

	Long getGcxmAuditProcessNodeCount(GcxmAuditProcessNode t);

	List<GcxmAuditProcessNode> getGcxmAuditProcessNodePaginatedList(GcxmAuditProcessNode t);

}