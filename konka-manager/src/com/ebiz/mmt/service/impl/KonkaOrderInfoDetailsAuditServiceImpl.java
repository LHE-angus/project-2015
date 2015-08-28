package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaOrderInfoDetailsAuditDao;
import com.ebiz.mmt.domain.KonkaOrderInfoDetailsAudit;
import com.ebiz.mmt.service.KonkaOrderInfoDetailsAuditService;

/**
 * @author Xu,XiaoYuan
 * @version 2012-02-15 10:24
 */
@Service
public class KonkaOrderInfoDetailsAuditServiceImpl implements KonkaOrderInfoDetailsAuditService {

	@Resource
	private KonkaOrderInfoDetailsAuditDao konkaOrderInfoDetailsAuditDao;
	

	public Long createKonkaOrderInfoDetailsAudit(KonkaOrderInfoDetailsAudit t) {
		return this.konkaOrderInfoDetailsAuditDao.insertEntity(t);
	}

	public KonkaOrderInfoDetailsAudit getKonkaOrderInfoDetailsAudit(KonkaOrderInfoDetailsAudit t) {
		return this.konkaOrderInfoDetailsAuditDao.selectEntity(t);
	}

	public Long getKonkaOrderInfoDetailsAuditCount(KonkaOrderInfoDetailsAudit t) {
		return this.konkaOrderInfoDetailsAuditDao.selectEntityCount(t);
	}

	public List<KonkaOrderInfoDetailsAudit> getKonkaOrderInfoDetailsAuditList(KonkaOrderInfoDetailsAudit t) {
		return this.konkaOrderInfoDetailsAuditDao.selectEntityList(t);
	}

	public int modifyKonkaOrderInfoDetailsAudit(KonkaOrderInfoDetailsAudit t) {
		return this.konkaOrderInfoDetailsAuditDao.updateEntity(t);
	}

	public int removeKonkaOrderInfoDetailsAudit(KonkaOrderInfoDetailsAudit t) {
		return this.konkaOrderInfoDetailsAuditDao.deleteEntity(t);
	}

	public List<KonkaOrderInfoDetailsAudit> getKonkaOrderInfoDetailsAuditPaginatedList(KonkaOrderInfoDetailsAudit t) {
		return this.konkaOrderInfoDetailsAuditDao.selectEntityPaginatedList(t);
	}

}
