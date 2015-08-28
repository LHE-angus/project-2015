package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaPeAttachmentsDao;
import com.ebiz.mmt.dao.KonkaVipPdManageDao;
import com.ebiz.mmt.domain.KonkaPeAttachments;
import com.ebiz.mmt.domain.KonkaVipPdManage;
import com.ebiz.mmt.service.KonkaVipPdManageService;
import com.ebiz.org.apache.commons.lang3.StringUtils;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-05-16 13:09:31
 */
@Service
public class KonkaVipPdManageServiceImpl implements KonkaVipPdManageService {

	@Resource
	private com.ebiz.mmt.dao.KonkaVipPdManageDao konkaVipPdManageDao;
	@Resource
	private KonkaPeAttachmentsDao KonkaPeAttachmentsDao;

	public Long createKonkaVipPdManage(KonkaVipPdManage t) {
		Long id =  this.konkaVipPdManageDao.insertEntity(t);
		List<KonkaPeAttachments> KonkaPeAttachmentsList = t.getPeAttachmentsList();
		if (null != KonkaPeAttachmentsList) {
			for (KonkaPeAttachments KonkaPeAttachments : KonkaPeAttachmentsList) {
				KonkaPeAttachments.setLink_id(id);
				KonkaPeAttachments.setIs_del(0l);
				this.KonkaPeAttachmentsDao.insertEntity(KonkaPeAttachments);
			}
		}
		return id;
	}

	public KonkaVipPdManage getKonkaVipPdManage(KonkaVipPdManage t) {
		return this.konkaVipPdManageDao.selectEntity(t);
	}

	public Long getKonkaVipPdManageCount(KonkaVipPdManage t) {
		return this.konkaVipPdManageDao.selectEntityCount(t);
	}

	public List<KonkaVipPdManage> getKonkaVipPdManageList(KonkaVipPdManage t) {
		return this.konkaVipPdManageDao.selectEntityList(t);
	}

	public int modifyKonkaVipPdManage(KonkaVipPdManage t) {
		List<KonkaPeAttachments> KonkaPeAttachmentsList = t.getPeAttachmentsList();
		KonkaPeAttachments kkpa=new KonkaPeAttachments();
		if (StringUtils.isNotBlank((String)t.getMap().get("pic_edit_id"))){
			kkpa.setId(Long.valueOf(Long.valueOf(((String)t.getMap().get("pic_edit_id")))));
			this.KonkaPeAttachmentsDao.deleteEntity(kkpa);
		}
		if (null != KonkaPeAttachmentsList) {
			for (KonkaPeAttachments peAttachments : KonkaPeAttachmentsList) {
				peAttachments.setLink_id(t.getId());
				peAttachments.setIs_del(0l);
				this.KonkaPeAttachmentsDao.insertEntity(peAttachments);
			}
		}
		return this.konkaVipPdManageDao.updateEntity(t);
	}

	public int removeKonkaVipPdManage(KonkaVipPdManage t) {
		String pic_id=(String)t.getMap().get("pic_id");
		if(StringUtils.isNotBlank(pic_id)){
		KonkaPeAttachments kkam=new KonkaPeAttachments();
		kkam.setId(Long.valueOf(pic_id));
		//System.out.println(pic_id);
		this.KonkaPeAttachmentsDao.deleteEntity(kkam);
		}
		return this.konkaVipPdManageDao.deleteEntity(t);
	}

	public List<KonkaVipPdManage> getKonkaVipPdManagePaginatedList(KonkaVipPdManage t) {
		return this.konkaVipPdManageDao.selectEntityPaginatedList(t);
	}

}
