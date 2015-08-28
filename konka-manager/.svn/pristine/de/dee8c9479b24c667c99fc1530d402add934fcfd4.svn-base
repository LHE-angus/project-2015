package com.ebiz.mmt.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaPeAttachmentsDao;
import com.ebiz.mmt.dao.KonkaSysAplicationDao;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaPeAttachments;
import com.ebiz.mmt.domain.KonkaSysAplication;
import com.ebiz.mmt.domain.KonkaoaFilesAuditNode;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.service.KonkaSysAplicationService;
import com.ebiz.mmt.web.Constants;


@Service
public class KonkaSysAplicationServiceImpl implements KonkaSysAplicationService {

	@Resource
	private KonkaSysAplicationDao konkaSysAplicationDao;
	
	@Resource
	private KonkaPeAttachmentsDao KonkaPeAttachmentsDao;

	public Long createKonkaSysAplication(KonkaSysAplication t) {
		
		Long id = this.konkaSysAplicationDao.insertEntity(t);
		
		//保存附件信息
		List<KonkaPeAttachments> KonkaPeAttachmentsList = t.getAttachmentList();
		if (null != KonkaPeAttachmentsList) {
			for (KonkaPeAttachments KonkaPeAttachments : KonkaPeAttachmentsList) {
				KonkaPeAttachments.setLink_id(id);
				KonkaPeAttachments.setIs_del(0l);
				KonkaPeAttachments.setAdd_user_id(t.getUser_id());
				this.KonkaPeAttachmentsDao.insertEntity(KonkaPeAttachments);
			}
		}
		
		return id;
	}

	public HashMap getKonkaSysAplication(KonkaSysAplication t) {
		return this.konkaSysAplicationDao.selectKonkaSysAplication(t);
	}

	public Long getKonkaSysAplicationCount(KonkaSysAplication t) {
		return this.konkaSysAplicationDao.selectEntityCount(t);
	}

	public List<HashMap> getKonkaSysAplicationList(KonkaSysAplication t) {
		return this.konkaSysAplicationDao.selectKonkaSysAplicationList(t);
	}

	public int modifyKonkaSysAplication(KonkaSysAplication t) {
		return this.konkaSysAplicationDao.updateEntity(t);
	}

	public int removeKonkaSysAplication(KonkaSysAplication t) {
		return this.konkaSysAplicationDao.deleteEntity(t);
	}

	public List<KonkaSysAplication> getKonkaSysAplicationPaginatedList(KonkaSysAplication t) {
		return this.konkaSysAplicationDao.selectEntityPaginatedList(t);
	}

	@Override
	public int modifyAplicationStatus(KonkaSysAplication t) {
		
		return this.konkaSysAplicationDao.modifyAplicationStatus(t);
	}
}
