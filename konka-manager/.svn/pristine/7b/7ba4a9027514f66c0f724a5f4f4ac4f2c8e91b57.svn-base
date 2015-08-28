package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.AttachmentDao;
import com.ebiz.mmt.dao.KonkaContractAuditRecordDao;
import com.ebiz.mmt.dao.KonkaContractManagerDao;
import com.ebiz.mmt.domain.Attachment;
import com.ebiz.mmt.domain.KonkaContractAuditRecord;
import com.ebiz.mmt.domain.KonkaContractManager;
import com.ebiz.mmt.service.KonkaContractManagerService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-02-20 17:43:35
 */
@Service
public class KonkaContractManagerServiceImpl implements KonkaContractManagerService {

	@Resource
	private KonkaContractManagerDao konkaContractManagerDao;

	@Resource
	private AttachmentDao attachmentDao;

	@Resource
	private KonkaContractAuditRecordDao konkaContractAuditRecordDao;

	public Long createKonkaContractManager(KonkaContractManager t) {
		Long id = this.konkaContractManagerDao.insertEntity(t);

		if (t.getAttachmentList() != null && t.getAttachmentList().size() > 0) {
			for (Attachment a : t.getAttachmentList()) {
				a.setLink_tab("KONKA_CONTRACT_MANAGER");
				a.setLink_id(id);
				this.attachmentDao.insertEntity(a);
			}
		}

		return id;
	}

	public KonkaContractManager getKonkaContractManager(KonkaContractManager t) {
		return this.konkaContractManagerDao.selectEntity(t);
	}

	public Long getKonkaContractManagerCount(KonkaContractManager t) {
		return this.konkaContractManagerDao.selectEntityCount(t);
	}

	public List<KonkaContractManager> getKonkaContractManagerList(KonkaContractManager t) {
		return this.konkaContractManagerDao.selectEntityList(t);
	}

	public int modifyKonkaContractManager(KonkaContractManager t) {
		if (t.getAttachmentList() != null && t.getAttachmentList().size() > 0) {
			for (Attachment a : t.getAttachmentList()) {
				a.setLink_tab("KONKA_CONTRACT_MANAGER");
				a.setLink_id(t.getId());
				this.attachmentDao.insertEntity(a);
			}
		}

		return this.konkaContractManagerDao.updateEntity(t);
	}

	public int removeKonkaContractManager(KonkaContractManager t) {
		return this.konkaContractManagerDao.deleteEntity(t);
	}

	public List<KonkaContractManager> getKonkaContractManagerPaginatedList(KonkaContractManager t) {
		return this.konkaContractManagerDao.selectEntityPaginatedList(t);
	}

	public int modifyKonkaContractManagerAndAudit(KonkaContractManager t, KonkaContractAuditRecord k) {
		this.konkaContractManagerDao.updateEntity(t);
		konkaContractAuditRecordDao.insertEntity(k);
		return 0;
	}

}
