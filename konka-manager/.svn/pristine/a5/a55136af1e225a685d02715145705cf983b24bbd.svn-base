package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.GcxmProjAuditNodeDao;
import com.ebiz.mmt.domain.GcxmProjAuditNode;
import com.ebiz.mmt.service.GcxmProjAuditNodeService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2015-03-23 13:53:09
 */
@Service
public class GcxmProjAuditNodeServiceImpl implements GcxmProjAuditNodeService {

	@Resource
	private GcxmProjAuditNodeDao gcxmProjAuditNodeDao;
	

	public Long createGcxmProjAuditNode(GcxmProjAuditNode t) {
		return this.gcxmProjAuditNodeDao.insertEntity(t);
	}

	public GcxmProjAuditNode getGcxmProjAuditNode(GcxmProjAuditNode t) {
		return this.gcxmProjAuditNodeDao.selectEntity(t);
	}

	public Long getGcxmProjAuditNodeCount(GcxmProjAuditNode t) {
		return this.gcxmProjAuditNodeDao.selectEntityCount(t);
	}

	public List<GcxmProjAuditNode> getGcxmProjAuditNodeList(GcxmProjAuditNode t) {
		return this.gcxmProjAuditNodeDao.selectEntityList(t);
	}

	public int modifyGcxmProjAuditNode(GcxmProjAuditNode t) {
		return this.gcxmProjAuditNodeDao.updateEntity(t);
	}

	public int removeGcxmProjAuditNode(GcxmProjAuditNode t) {
		return this.gcxmProjAuditNodeDao.deleteEntity(t);
	}

	public List<GcxmProjAuditNode> getGcxmProjAuditNodePaginatedList(GcxmProjAuditNode t) {
		return this.gcxmProjAuditNodeDao.selectEntityPaginatedList(t);
	}

}
