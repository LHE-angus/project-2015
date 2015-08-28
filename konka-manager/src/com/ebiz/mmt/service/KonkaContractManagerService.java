package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaContractAuditRecord;
import com.ebiz.mmt.domain.KonkaContractManager;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-02-20 17:43:35
 */
public interface KonkaContractManagerService {

	Long createKonkaContractManager(KonkaContractManager t);

	int modifyKonkaContractManager(KonkaContractManager t);

	int removeKonkaContractManager(KonkaContractManager t);

	KonkaContractManager getKonkaContractManager(KonkaContractManager t);

	List<KonkaContractManager> getKonkaContractManagerList(KonkaContractManager t);

	Long getKonkaContractManagerCount(KonkaContractManager t);

	List<KonkaContractManager> getKonkaContractManagerPaginatedList(KonkaContractManager t);

	int modifyKonkaContractManagerAndAudit(KonkaContractManager t, KonkaContractAuditRecord k);

}