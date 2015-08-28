package com.ebiz.mmt.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaPeAttachmentsDao;
import com.ebiz.mmt.dao.KonkaSpActivityManagerDao;
import com.ebiz.mmt.dao.KonkaSpMdSailDao;
import com.ebiz.mmt.dao.KonkaSpMdTypeDao;
import com.ebiz.mmt.domain.KonkaPeAttachments;
import com.ebiz.mmt.domain.KonkaSpActivityManager;
import com.ebiz.mmt.domain.KonkaSpMdSail;
import com.ebiz.mmt.domain.KonkaSpMdType;
import com.ebiz.mmt.service.KonkaSpActivityManagerService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-01-23 11:36:10
 */
@Service
public class KonkaSpActivityManagerServiceImpl implements KonkaSpActivityManagerService {

	@Resource
	private KonkaSpActivityManagerDao konkaSpActivityManagerDao;
	
	@Resource
	private KonkaPeAttachmentsDao KonkaPeAttachmentsDao;
	
	@Resource
	private KonkaSpMdTypeDao konkaSpMdTypeDao;
	
	@Resource
	private KonkaSpMdSailDao konkaSpMdSailDao;

	public Long createKonkaSpActivityManager(KonkaSpActivityManager t) {
		Long id = this.konkaSpActivityManagerDao.insertEntity(t);
		
		List<KonkaPeAttachments> KonkaPeAttachmentsList = t.getPeAttachmentsList();
		if (null != KonkaPeAttachmentsList) {
			for (KonkaPeAttachments KonkaPeAttachments : KonkaPeAttachmentsList) {
				KonkaPeAttachments.setLink_id(id);
				KonkaPeAttachments.setIs_del(0l);
				this.KonkaPeAttachmentsDao.insertEntity(KonkaPeAttachments);
			}
		}
		
		//指定机型销售
		List<KonkaSpMdSail> konkaSpMdSailList = t.getKonkaSpMdSailList();
		if(null != konkaSpMdSailList) {
			for(KonkaSpMdSail konkaSpMdSail : konkaSpMdSailList) {
				konkaSpMdSail.setLink_id(id);
				this.konkaSpMdSailDao.insertEntity(konkaSpMdSail);
			}
		}
		
		return id;
	}

	public KonkaSpActivityManager getKonkaSpActivityManager(KonkaSpActivityManager t) {
		return this.konkaSpActivityManagerDao.selectEntity(t);
	}

	public Long getKonkaSpActivityManagerCount(KonkaSpActivityManager t) {
		return this.konkaSpActivityManagerDao.selectEntityCount(t);
	}

	public List<KonkaSpActivityManager> getKonkaSpActivityManagerList(KonkaSpActivityManager t) {
		return this.konkaSpActivityManagerDao.selectEntityList(t);
	}
	
	public List<KonkaSpActivityManager> getKonkaSpActivityManagerListForExcel(KonkaSpActivityManager t) {
		return this.konkaSpActivityManagerDao.selectEntityListForExcel(t);
	}

	public int modifyKonkaSpActivityManager(KonkaSpActivityManager t) {
		List<KonkaPeAttachments> KonkaPeAttachmentsList = t.getPeAttachmentsList();
		if (null != KonkaPeAttachmentsList) {
			for (KonkaPeAttachments peAttachments : KonkaPeAttachmentsList) {
				peAttachments.setLink_id(t.getId());
				peAttachments.setIs_del(0l);
				this.KonkaPeAttachmentsDao.insertEntity(peAttachments);
			}
		}
		
		//查出按指定商品类型的旧数据
		KonkaSpMdSail konkaSpMdSail = new KonkaSpMdSail();
		konkaSpMdSail.setLink_id(t.getId());
		List<KonkaSpMdSail> konkaSpMdSailList = this.konkaSpMdSailDao.selectEntityList(konkaSpMdSail);
		if(null != konkaSpMdSailList) {
			Long[] pks = new Long[konkaSpMdSailList.size()];
			int i = 0;
			for(KonkaSpMdSail spMdSail : konkaSpMdSailList) {
				pks[i] = spMdSail.getId();
				i++;
			}
			//删除
			if(pks!=null&&pks.length>0){
				konkaSpMdSail = new KonkaSpMdSail();
				konkaSpMdSail.getMap().put("pks", pks);
				this.konkaSpMdSailDao.deleteEntity(konkaSpMdSail);
			}
		}
		
		//插入新数据
		List<KonkaSpMdSail> newKonkaSpMdSailList = t.getKonkaSpMdSailList();
		if(null != newKonkaSpMdSailList) {
			for(KonkaSpMdSail spMdSail : newKonkaSpMdSailList) {
				spMdSail.setLink_id(t.getId());
				this.konkaSpMdSailDao.insertEntity(spMdSail);
			}
		}
		//更新操作时，销售机类型需要先删除后重新循环添加---end---
		return this.konkaSpActivityManagerDao.updateEntity(t);
	}

	public int removeKonkaSpActivityManager(KonkaSpActivityManager t) {
		return this.konkaSpActivityManagerDao.deleteEntity(t);
	}

	public List<KonkaSpActivityManager> getKonkaSpActivityManagerPaginatedList(KonkaSpActivityManager t) {
		return this.konkaSpActivityManagerDao.selectEntityPaginatedList(t);
	}

	@Override
	public List<KonkaSpActivityManager> getKonkaSpActivityManagerListForBookReport(
			KonkaSpActivityManager entity) {
		
		return this.konkaSpActivityManagerDao.selectKonkaSpActivityManagerListForBookReport(entity);
	}

}
