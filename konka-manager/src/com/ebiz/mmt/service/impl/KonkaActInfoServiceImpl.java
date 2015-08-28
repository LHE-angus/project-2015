package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.AttachmentDao;
import com.ebiz.mmt.dao.KonkaActInfoDao;
import com.ebiz.mmt.domain.Attachment;
import com.ebiz.mmt.domain.KonkaActInfo;
import com.ebiz.mmt.service.KonkaActInfoService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-11-06 14:11:44
 */
@Service
public class KonkaActInfoServiceImpl implements KonkaActInfoService {

	@Resource
	private KonkaActInfoDao konkaActInfoDao;

	@Resource
	private AttachmentDao attachmentDao;

	public Long createKonkaActInfo(KonkaActInfo t) {
		Long id = this.konkaActInfoDao.insertEntity(t);
		// 附件insert
		if (t.getAttachmentList() != null && t.getAttachmentList().size() > 0) {
			for (Attachment a : t.getAttachmentList()) {
				a.setLink_tab("KONKA_ACT_INFO");
				a.setLink_id(id);
				this.attachmentDao.insertEntity(a);
			}
		}
		return id;
	}

	public KonkaActInfo getKonkaActInfo(KonkaActInfo t) {
		return this.konkaActInfoDao.selectEntity(t);
	}

	public Long getKonkaActInfoCount(KonkaActInfo t) {
		return this.konkaActInfoDao.selectEntityCount(t);
	}

	public List<KonkaActInfo> getKonkaActInfoList(KonkaActInfo t) {
		return this.konkaActInfoDao.selectEntityList(t);
	}

	public int modifyKonkaActInfo(KonkaActInfo t) {
		if (t.getAttachmentList() != null && t.getAttachmentList().size() > 0) {
			for (Attachment a : t.getAttachmentList()) {
				a.setLink_tab("KONKA_ACT_INFO");
				a.setLink_id(t.getAct_id());
				this.attachmentDao.insertEntity(a);
			}
		}
		return this.konkaActInfoDao.updateEntity(t);
	}

	public int removeKonkaActInfo(KonkaActInfo t) {
		return this.konkaActInfoDao.deleteEntity(t);
	}

	public List<KonkaActInfo> getKonkaActInfoPaginatedList(KonkaActInfo t) {
		return this.konkaActInfoDao.selectEntityPaginatedList(t);
	}

	public Long getKonkaActInfoNoMax(KonkaActInfo t) {
		return this.konkaActInfoDao.selectKonkaActInfoNoMax(t);
	}

	public List<KonkaActInfo> getKonkaActInfoAndSaleDatasList(KonkaActInfo t) {
		return this.konkaActInfoDao.selectKonkaActInfoAndSaleDatasList(t);
	}

}
