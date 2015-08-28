package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaOrderMeetingCustomerDao;
import com.ebiz.mmt.dao.KonkaOrderMeetingManagerDao;
import com.ebiz.mmt.dao.KonkaPeAttachmentsDao;
import com.ebiz.mmt.dao.KonkaSpMdSailDao;
import com.ebiz.mmt.domain.KonkaOrderMeetingCustomer;
import com.ebiz.mmt.domain.KonkaOrderMeetingManager;
import com.ebiz.mmt.domain.KonkaPeAttachments;
import com.ebiz.mmt.domain.KonkaSpMdSail;
import com.ebiz.mmt.service.KonkaOrderMeetingManagerService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-01-23 11:36:10
 */
@Service
public class KonkaOrderMeetingManagerServiceImpl implements KonkaOrderMeetingManagerService {

	@Resource
	private KonkaOrderMeetingManagerDao konkaOrderMeetingManagerDao;

	@Resource
	private KonkaPeAttachmentsDao KonkaPeAttachmentsDao;

	@Resource
	private KonkaOrderMeetingCustomerDao konkaOrderMeetingCustomerDao;
	
	@Resource
	private KonkaSpMdSailDao konkaSpMdSailDao;

	public Long createKonkaOrderMeetingManager(KonkaOrderMeetingManager t) {
		Long id = this.konkaOrderMeetingManagerDao.insertEntity(t);

		List<KonkaPeAttachments> KonkaPeAttachmentsList = t.getPeAttachmentsList();
		if (null != KonkaPeAttachmentsList) {
			for (KonkaPeAttachments KonkaPeAttachments : KonkaPeAttachmentsList) {
				KonkaPeAttachments.setLink_id(id);
				KonkaPeAttachments.setIs_del(0l);
				this.KonkaPeAttachmentsDao.insertEntity(KonkaPeAttachments);
			}
		}

		List<KonkaOrderMeetingCustomer> orderMeetingCustomerList = t.getOrderMeetingCustomerList();
		if (null != orderMeetingCustomerList) {
			for (KonkaOrderMeetingCustomer konkaOrderMeetingCustomer : orderMeetingCustomerList) {
				konkaOrderMeetingCustomer.setLink_id(id);
				this.konkaOrderMeetingCustomerDao.insertEntity(konkaOrderMeetingCustomer);
			}
		}
		
		List<KonkaSpMdSail> konkaSpMdSailList = t.getKonkaSpMdSailList();
		if(null != konkaSpMdSailList) {
			for(KonkaSpMdSail konkaSpMdSail : konkaSpMdSailList) {
				konkaSpMdSail.setLink_id(id);
				this.konkaSpMdSailDao.insertEntity(konkaSpMdSail);
			}
		}

		return id;
	}

	public KonkaOrderMeetingManager getKonkaOrderMeetingManager(KonkaOrderMeetingManager t) {
		return this.konkaOrderMeetingManagerDao.selectEntity(t);
	}

	public Long getKonkaOrderMeetingManagerCount(KonkaOrderMeetingManager t) {
		return this.konkaOrderMeetingManagerDao.selectEntityCount(t);
	}

	public List<KonkaOrderMeetingManager> getKonkaOrderMeetingManagerList(KonkaOrderMeetingManager t) {
		return this.konkaOrderMeetingManagerDao.selectEntityList(t);
	}

	public int modifyKonkaOrderMeetingManager(KonkaOrderMeetingManager t) {
		// 查出附件表中的旧数据，删除
//		KonkaPeAttachments attachment = new KonkaPeAttachments();
//		attachment.setLink_id(t.getId());
//		attachment.setIs_del(0l);
//		attachment.setLink_tab("KONKA_ORDER_MEETING_MANAGER");
//		List<KonkaPeAttachments> konkaPeAttachmentsList = this.KonkaPeAttachmentsDao.selectEntityList(attachment);
//		if (null != konkaPeAttachmentsList && 0 != konkaPeAttachmentsList.size()) {
//			Long[] pks = new Long[konkaPeAttachmentsList.size()];
//			int i = 0;
//			for (KonkaPeAttachments attch : konkaPeAttachmentsList) {
//				pks[i] = attch.getId();
//				i++;
//			}
//			// 删除
//			attachment = new KonkaPeAttachments();
//			attachment.setIs_del(1l);
//			attachment.getMap().put("pks", pks);
//			this.KonkaPeAttachmentsDao.updateEntity(attachment);
//		}

		// 向附件表中插入新数据
		List<KonkaPeAttachments> newKonkaPeAttachmentsList = t.getPeAttachmentsList();
		if (null != newKonkaPeAttachmentsList) {
			for (KonkaPeAttachments KonkaPeAttachments : newKonkaPeAttachmentsList) {
				KonkaPeAttachments.setLink_id(t.getId());
				KonkaPeAttachments.setIs_del(0l);
				this.KonkaPeAttachmentsDao.insertEntity(KonkaPeAttachments);
			}
		}
		
		if(null == t.getIs_del() || (0 == t.getIs_del() && null == t.getMap().get("enable"))) {
			// 查出订货会客户表中的旧数据，如果有的话，先删除
			KonkaOrderMeetingCustomer konkaOrderMeetingCustomer = new KonkaOrderMeetingCustomer();
			konkaOrderMeetingCustomer.setLink_id(t.getId());
			List<KonkaOrderMeetingCustomer> orderMeetingCustomerList = this.konkaOrderMeetingCustomerDao
					.selectEntityList(konkaOrderMeetingCustomer);
			if (null != orderMeetingCustomerList && 0 != orderMeetingCustomerList.size()) {
				Long[] pks = new Long[orderMeetingCustomerList.size()];
				int i = 0;
				for (KonkaOrderMeetingCustomer customer : orderMeetingCustomerList) {
					pks[i] = customer.getId();
					i++;
				}
				//删除
				konkaOrderMeetingCustomer = new KonkaOrderMeetingCustomer();
				konkaOrderMeetingCustomer.getMap().put("pks", pks);
				this.konkaOrderMeetingCustomerDao.deleteEntity(konkaOrderMeetingCustomer);
			}
			// 向订货会客户表中插入新数据
			List<KonkaOrderMeetingCustomer> newOrderMeetingCustomerList = t.getOrderMeetingCustomerList();
			if (null != orderMeetingCustomerList) {
				for (KonkaOrderMeetingCustomer customer : newOrderMeetingCustomerList) {
					customer.setLink_id(t.getId());
					this.konkaOrderMeetingCustomerDao.insertEntity(customer);
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
		}

		return this.konkaOrderMeetingManagerDao.updateEntity(t);
	}

	public int removeKonkaOrderMeetingManager(KonkaOrderMeetingManager t) {
		return this.konkaOrderMeetingManagerDao.deleteEntity(t);
	}

	public List<KonkaOrderMeetingManager> getKonkaOrderMeetingManagerPaginatedList(KonkaOrderMeetingManager t) {
		return this.konkaOrderMeetingManagerDao.selectEntityPaginatedList(t);
	}

	public Long getSequenceResult(KonkaOrderMeetingManager t) {
		return this.konkaOrderMeetingManagerDao.selectSequenceResult(t);
	}
}
