package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcVouchersAuditDao;
import com.ebiz.mmt.domain.EcVouchersAudit;
import com.ebiz.mmt.service.EcVouchersAuditService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-12-19 16:29:48
 */
@Service
public class EcVouchersAuditServiceImpl implements EcVouchersAuditService {

	@Resource
	private EcVouchersAuditDao ecVouchersAuditDao;
	

	public Long createEcVouchersAudit(EcVouchersAudit t) {
		return this.ecVouchersAuditDao.insertEntity(t);
	}

	public EcVouchersAudit getEcVouchersAudit(EcVouchersAudit t) {
		return this.ecVouchersAuditDao.selectEntity(t);
	}

	public Long getEcVouchersAuditCount(EcVouchersAudit t) {
		return this.ecVouchersAuditDao.selectEntityCount(t);
	}

	public List<EcVouchersAudit> getEcVouchersAuditList(EcVouchersAudit t) {
		return this.ecVouchersAuditDao.selectEntityList(t);
	}

	public int modifyEcVouchersAudit(EcVouchersAudit t) {
		return this.ecVouchersAuditDao.updateEntity(t);
	}

	public int removeEcVouchersAudit(EcVouchersAudit t) {
		return this.ecVouchersAuditDao.deleteEntity(t);
	}

	public List<EcVouchersAudit> getEcVouchersAuditPaginatedList(EcVouchersAudit t) {
		return this.ecVouchersAuditDao.selectEntityPaginatedList(t);
	}

}
