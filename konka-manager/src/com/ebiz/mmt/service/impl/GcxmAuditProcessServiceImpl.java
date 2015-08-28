package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.GcxmAuditProcessDao;
import com.ebiz.mmt.dao.GcxmAuditProcessNodeDao;
import com.ebiz.mmt.domain.GcxmAuditProcess;
import com.ebiz.mmt.domain.GcxmAuditProcessNode;
import com.ebiz.mmt.service.GcxmAuditProcessService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2015-03-23 13:53:09
 */
@Service
public class GcxmAuditProcessServiceImpl implements GcxmAuditProcessService {

	@Resource
	private GcxmAuditProcessDao gcxmAuditProcessDao;

	@Resource
	private GcxmAuditProcessNodeDao gcxmAuditProcessNodeDao;

	public Long createGcxmAuditProcess(GcxmAuditProcess t) {
		Long id = this.gcxmAuditProcessDao.insertEntity(t);
		if (t.getGcxmAuditProcessNodeList() != null && t.getGcxmAuditProcessNodeList().size() > 0) {
			for (GcxmAuditProcessNode pnode : t.getGcxmAuditProcessNodeList()) {
				pnode.setProcess_id(id);
				this.gcxmAuditProcessNodeDao.insertEntity(pnode);
			}
		}
		return id;
	}

	public GcxmAuditProcess getGcxmAuditProcess(GcxmAuditProcess t) {
		return this.gcxmAuditProcessDao.selectEntity(t);
	}

	public Long getGcxmAuditProcessCount(GcxmAuditProcess t) {
		return this.gcxmAuditProcessDao.selectEntityCount(t);
	}

	public List<GcxmAuditProcess> getGcxmAuditProcessList(GcxmAuditProcess t) {
		return this.gcxmAuditProcessDao.selectEntityList(t);
	}

	public int modifyGcxmAuditProcess(GcxmAuditProcess t) {
		return this.gcxmAuditProcessDao.updateEntity(t);
	}

	public int removeGcxmAuditProcess(GcxmAuditProcess t) {
		return this.gcxmAuditProcessDao.deleteEntity(t);
	}

	public List<GcxmAuditProcess> getGcxmAuditProcessPaginatedList(GcxmAuditProcess t) {
		return this.gcxmAuditProcessDao.selectEntityPaginatedList(t);
	}

}
