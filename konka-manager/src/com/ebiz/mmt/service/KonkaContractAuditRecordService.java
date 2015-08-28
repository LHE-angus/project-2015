package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaContractAuditRecord;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-02-20 17:43:35
 */
public interface KonkaContractAuditRecordService {

	Long createKonkaContractAuditRecord(KonkaContractAuditRecord t);

	int modifyKonkaContractAuditRecord(KonkaContractAuditRecord t);

	int removeKonkaContractAuditRecord(KonkaContractAuditRecord t);

	KonkaContractAuditRecord getKonkaContractAuditRecord(KonkaContractAuditRecord t);

	List<KonkaContractAuditRecord> getKonkaContractAuditRecordList(KonkaContractAuditRecord t);

	Long getKonkaContractAuditRecordCount(KonkaContractAuditRecord t);

	List<KonkaContractAuditRecord> getKonkaContractAuditRecordPaginatedList(KonkaContractAuditRecord t);

}