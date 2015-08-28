package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaContractAuditRecordDao;
import com.ebiz.mmt.domain.KonkaContractAuditRecord;
import com.ebiz.mmt.service.KonkaContractAuditRecordService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-02-20 17:43:35
 */
@Service
public class KonkaContractAuditRecordServiceImpl implements KonkaContractAuditRecordService {

	@Resource
	private KonkaContractAuditRecordDao konkaContractAuditRecordDao;
	

	public Long createKonkaContractAuditRecord(KonkaContractAuditRecord t) {
		return this.konkaContractAuditRecordDao.insertEntity(t);
	}

	public KonkaContractAuditRecord getKonkaContractAuditRecord(KonkaContractAuditRecord t) {
		return this.konkaContractAuditRecordDao.selectEntity(t);
	}

	public Long getKonkaContractAuditRecordCount(KonkaContractAuditRecord t) {
		return this.konkaContractAuditRecordDao.selectEntityCount(t);
	}

	public List<KonkaContractAuditRecord> getKonkaContractAuditRecordList(KonkaContractAuditRecord t) {
		return this.konkaContractAuditRecordDao.selectEntityList(t);
	}

	public int modifyKonkaContractAuditRecord(KonkaContractAuditRecord t) {
		return this.konkaContractAuditRecordDao.updateEntity(t);
	}

	public int removeKonkaContractAuditRecord(KonkaContractAuditRecord t) {
		return this.konkaContractAuditRecordDao.deleteEntity(t);
	}

	public List<KonkaContractAuditRecord> getKonkaContractAuditRecordPaginatedList(KonkaContractAuditRecord t) {
		return this.konkaContractAuditRecordDao.selectEntityPaginatedList(t);
	}

}
