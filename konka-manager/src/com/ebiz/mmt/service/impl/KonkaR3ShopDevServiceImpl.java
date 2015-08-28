package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaPeAttachmentsDao;
import com.ebiz.mmt.dao.KonkaR3ShopDevDao;
import com.ebiz.mmt.domain.KonkaPeAttachments;
import com.ebiz.mmt.domain.KonkaR3ShopDev;
import com.ebiz.mmt.service.KonkaR3ShopDevService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-06-30 11:05:44
 */
@Service
public class KonkaR3ShopDevServiceImpl implements KonkaR3ShopDevService {

	@Resource
	private KonkaR3ShopDevDao konkaR3ShopDevDao;
	@Resource
	private KonkaPeAttachmentsDao konkaPeAttachmentsDao;

	public Long createKonkaR3ShopDev(KonkaR3ShopDev t) {
		Long cust_id=this.konkaR3ShopDevDao.insertEntity(t);
		List<KonkaPeAttachments> attachmentList=t.getKonkaPeAttachmentsList();
		if(null!=attachmentList && attachmentList.size()>0){//插入附件
			for(KonkaPeAttachments attachment:attachmentList){
				attachment.setLink_id(cust_id);
				this.konkaPeAttachmentsDao.insertEntity(attachment);
			}
		}
		
		return cust_id;
	}

	public KonkaR3ShopDev getKonkaR3ShopDev(KonkaR3ShopDev t) {
		return this.konkaR3ShopDevDao.selectEntity(t);
	}

	public Long getKonkaR3ShopDevCount(KonkaR3ShopDev t) {
		return this.konkaR3ShopDevDao.selectEntityCount(t);
	}

	public List<KonkaR3ShopDev> getKonkaR3ShopDevList(KonkaR3ShopDev t) {
		return this.konkaR3ShopDevDao.selectEntityList(t);
	}

	public int modifyKonkaR3ShopDev(KonkaR3ShopDev t) {
		Long cust_id=t.getCust_id();
	
		List<KonkaPeAttachments> attachmentList=t.getKonkaPeAttachmentsList();
		if(null!=attachmentList && attachmentList.size()>0){
			/*KonkaPeAttachments attachmentdel =new KonkaPeAttachments();
			attachmentdel.setLink_id(cust_id);
			attachmentdel.setLink_tab("KONKA_R3_SHOP_DEV");
			List<KonkaPeAttachments> attachmentListdel=this.konkaPeAttachmentsDao.selectEntityList(attachmentdel);
			if(null!=attachmentListdel && attachmentListdel.size()>0){//删除原有附件
				for(KonkaPeAttachments attachment:attachmentListdel){
					attachment.setLink_id(cust_id);
					this.konkaPeAttachmentsDao.deleteEntity(attachment);
				}
			}*/
			for(KonkaPeAttachments attachment:attachmentList){//插入附件
				attachment.setLink_id(cust_id);
				this.konkaPeAttachmentsDao.insertEntity(attachment);
			}
		
	     }
		return this.konkaR3ShopDevDao.updateEntity(t);
		}

	public int removeKonkaR3ShopDev(KonkaR3ShopDev t) {
		return this.konkaR3ShopDevDao.deleteEntity(t);
	}

	public List<KonkaR3ShopDev> getKonkaR3ShopDevPaginatedList(KonkaR3ShopDev t) {
		return this.konkaR3ShopDevDao.selectEntityPaginatedList(t);
	}

	@Override
	public List<KonkaR3ShopDev> getKtUserByUserIdList(KonkaR3ShopDev v) {
		return this.konkaR3ShopDevDao.selectKtUserByUserIdList(v);
	}

	/**
	 * 连表查询 带附件
	 */
	@Override
	public List<KonkaR3ShopDev> getKonkaR3ShopDevLBPaginatedList(
			KonkaR3ShopDev v) {
		return this.konkaR3ShopDevDao.selectKonkaR3ShopDevLBPaginatedList(v);
	}
	/**
	 * 连表查询 带附件记录数
	 */
	@Override
	public Long selectKonkaR3ShopDevLBCount(KonkaR3ShopDev v) {
		return this.konkaR3ShopDevDao.selectKonkaR3ShopDevLBCount(v);
	}

}
