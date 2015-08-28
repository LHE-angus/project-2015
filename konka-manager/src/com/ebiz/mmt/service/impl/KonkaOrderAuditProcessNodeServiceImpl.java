package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaOrderAuditProcessNodeDao;
import com.ebiz.mmt.domain.KonkaOrderAuditProcessNode;
import com.ebiz.mmt.service.KonkaOrderAuditProcessNodeService;

/**
 * @author Wu,Yang
 * @version 2011-11-30 14:09
 */
@Service
public class KonkaOrderAuditProcessNodeServiceImpl implements KonkaOrderAuditProcessNodeService {

	@Resource
	private KonkaOrderAuditProcessNodeDao konkaOrderAuditProcessNodeDao;
	

	public Long createKonkaOrderAuditProcessNode(KonkaOrderAuditProcessNode t) {
		return this.konkaOrderAuditProcessNodeDao.insertEntity(t);
	}

	public KonkaOrderAuditProcessNode getKonkaOrderAuditProcessNode(KonkaOrderAuditProcessNode t) {
		return this.konkaOrderAuditProcessNodeDao.selectEntity(t);
	}

	public Long getKonkaOrderAuditProcessNodeCount(KonkaOrderAuditProcessNode t) {
		return this.konkaOrderAuditProcessNodeDao.selectEntityCount(t);
	}

	public List<KonkaOrderAuditProcessNode> getKonkaOrderAuditProcessNodeList(KonkaOrderAuditProcessNode t) {
		return this.konkaOrderAuditProcessNodeDao.selectEntityList(t);
	}

	public int modifyKonkaOrderAuditProcessNode(KonkaOrderAuditProcessNode t) {
		return this.konkaOrderAuditProcessNodeDao.updateEntity(t);
	}

	public int removeKonkaOrderAuditProcessNode(KonkaOrderAuditProcessNode t) {
		return this.konkaOrderAuditProcessNodeDao.deleteEntity(t);
	}

	public List<KonkaOrderAuditProcessNode> getKonkaOrderAuditProcessNodePaginatedList(KonkaOrderAuditProcessNode t) {
		return this.konkaOrderAuditProcessNodeDao.selectEntityPaginatedList(t);
	}
	
	public Long getKonkaOrderAuditProcessNodeMaxLevel(KonkaOrderAuditProcessNode t) {
		return this.konkaOrderAuditProcessNodeDao.selectKonkaOrderAuditProcessNodeMaxLevel(t);
	}

}
