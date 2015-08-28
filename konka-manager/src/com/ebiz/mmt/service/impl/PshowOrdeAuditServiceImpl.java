package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.PshowOrdeAuditDao;
import com.ebiz.mmt.domain.PshowOrdeAudit;
import com.ebiz.mmt.service.PshowOrdeAuditService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-08-09 17:45:40
 */
@Service
public class PshowOrdeAuditServiceImpl implements PshowOrdeAuditService {

	@Resource
	private PshowOrdeAuditDao pshowOrdeAuditDao;
	

	public Long createPshowOrdeAudit(PshowOrdeAudit t) {
		return this.pshowOrdeAuditDao.insertEntity(t);
	}

	public PshowOrdeAudit getPshowOrdeAudit(PshowOrdeAudit t) {
		return this.pshowOrdeAuditDao.selectEntity(t);
	}

	public Long getPshowOrdeAuditCount(PshowOrdeAudit t) {
		return this.pshowOrdeAuditDao.selectEntityCount(t);
	}

	public List<PshowOrdeAudit> getPshowOrdeAuditList(PshowOrdeAudit t) {
		return this.pshowOrdeAuditDao.selectEntityList(t);
	}

	public int modifyPshowOrdeAudit(PshowOrdeAudit t) {
		return this.pshowOrdeAuditDao.updateEntity(t);
	}

	public int removePshowOrdeAudit(PshowOrdeAudit t) {
		return this.pshowOrdeAuditDao.deleteEntity(t);
	}

	public List<PshowOrdeAudit> getPshowOrdeAuditPaginatedList(PshowOrdeAudit t) {
		return this.pshowOrdeAuditDao.selectEntityPaginatedList(t);
	}

}
