package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.GcxmAuditProcessNodeDao;
import com.ebiz.mmt.domain.GcxmAuditProcessNode;
import com.ebiz.mmt.service.GcxmAuditProcessNodeService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2015-03-23 13:53:09
 */
@Service
public class GcxmAuditProcessNodeServiceImpl implements GcxmAuditProcessNodeService {

	@Resource
	private GcxmAuditProcessNodeDao gcxmAuditProcessNodeDao;
	

	public Long createGcxmAuditProcessNode(GcxmAuditProcessNode t) {
		return this.gcxmAuditProcessNodeDao.insertEntity(t);
	}

	public GcxmAuditProcessNode getGcxmAuditProcessNode(GcxmAuditProcessNode t) {
		return this.gcxmAuditProcessNodeDao.selectEntity(t);
	}

	public Long getGcxmAuditProcessNodeCount(GcxmAuditProcessNode t) {
		return this.gcxmAuditProcessNodeDao.selectEntityCount(t);
	}

	public List<GcxmAuditProcessNode> getGcxmAuditProcessNodeList(GcxmAuditProcessNode t) {
		return this.gcxmAuditProcessNodeDao.selectEntityList(t);
	}

	public int modifyGcxmAuditProcessNode(GcxmAuditProcessNode t) {
		return this.gcxmAuditProcessNodeDao.updateEntity(t);
	}

	public int removeGcxmAuditProcessNode(GcxmAuditProcessNode t) {
		return this.gcxmAuditProcessNodeDao.deleteEntity(t);
	}

	public List<GcxmAuditProcessNode> getGcxmAuditProcessNodePaginatedList(GcxmAuditProcessNode t) {
		return this.gcxmAuditProcessNodeDao.selectEntityPaginatedList(t);
	}

}
