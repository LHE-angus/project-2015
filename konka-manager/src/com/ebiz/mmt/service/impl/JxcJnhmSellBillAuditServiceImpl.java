package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.JxcJnhmSellBillAuditDao;
import com.ebiz.mmt.domain.JxcJnhmSellBillAudit;
import com.ebiz.mmt.service.JxcJnhmSellBillAuditService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2012-08-17 08:47:29
 */
@Service
public class JxcJnhmSellBillAuditServiceImpl implements JxcJnhmSellBillAuditService {

	@Resource
	private JxcJnhmSellBillAuditDao jxcJnhmSellBillAuditDao;
	

	public Long createJxcJnhmSellBillAudit(JxcJnhmSellBillAudit t) {
		return this.jxcJnhmSellBillAuditDao.insertEntity(t);
	}

	public JxcJnhmSellBillAudit getJxcJnhmSellBillAudit(JxcJnhmSellBillAudit t) {
		return this.jxcJnhmSellBillAuditDao.selectEntity(t);
	}

	public Long getJxcJnhmSellBillAuditCount(JxcJnhmSellBillAudit t) {
		return this.jxcJnhmSellBillAuditDao.selectEntityCount(t);
	}

	public List<JxcJnhmSellBillAudit> getJxcJnhmSellBillAuditList(JxcJnhmSellBillAudit t) {
		return this.jxcJnhmSellBillAuditDao.selectEntityList(t);
	}

	public int modifyJxcJnhmSellBillAudit(JxcJnhmSellBillAudit t) {
		return this.jxcJnhmSellBillAuditDao.updateEntity(t);
	}

	public int removeJxcJnhmSellBillAudit(JxcJnhmSellBillAudit t) {
		return this.jxcJnhmSellBillAuditDao.deleteEntity(t);
	}

	public List<JxcJnhmSellBillAudit> getJxcJnhmSellBillAuditPaginatedList(JxcJnhmSellBillAudit t) {
		return this.jxcJnhmSellBillAuditDao.selectEntityPaginatedList(t);
	}

}
