package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.AttachmentDao;
import com.ebiz.mmt.dao.KonkaSpActivityAddrDao;
import com.ebiz.mmt.domain.Attachment;
import com.ebiz.mmt.domain.KonkaSpActivityAddr;
import com.ebiz.mmt.service.KonkaSpActivityAddrService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-12-29 11:19:39
 */
@Service
public class KonkaSpActivityAddrServiceImpl implements KonkaSpActivityAddrService {

	@Resource
	private KonkaSpActivityAddrDao konkaSpActivityAddrDao;
	@Resource
	private AttachmentDao attachmentDao;

	public Long createKonkaSpActivityAddr(KonkaSpActivityAddr t) {
	Long id=this.konkaSpActivityAddrDao.insertEntity(t);
		List<Attachment> attlist=t.getAttachmentList();
	if(null!=attlist && attlist.size()> 0){
		for(Attachment att:attlist){
			att.setLink_id(id);
			att.setLink_tab("KONKA_SP_ACTIVITY_ADDR");
			attachmentDao.insertEntity(att);
			
		}
	}
		return id;
	}

	public KonkaSpActivityAddr getKonkaSpActivityAddr(KonkaSpActivityAddr t) {
		return this.konkaSpActivityAddrDao.selectEntity(t);
	}

	public Long getKonkaSpActivityAddrCount(KonkaSpActivityAddr t) {
		return this.konkaSpActivityAddrDao.selectEntityCount(t);
	}

	public List<KonkaSpActivityAddr> getKonkaSpActivityAddrList(KonkaSpActivityAddr t) {
		return this.konkaSpActivityAddrDao.selectEntityList(t);
	}

	public int modifyKonkaSpActivityAddr(KonkaSpActivityAddr t) {
		List<Attachment> attlist=t.getAttachmentList();
		if(null!=attlist && attlist.size()> 0){
			for(Attachment att:attlist){
				att.setLink_id(t.getId());
				att.setLink_tab("KONKA_SP_ACTIVITY_ADDR");
				attachmentDao.insertEntity(att);
				
			}
		}
		return this.konkaSpActivityAddrDao.updateEntity(t);
	}

	public int removeKonkaSpActivityAddr(KonkaSpActivityAddr t) {
		return this.konkaSpActivityAddrDao.deleteEntity(t);
	}

	public List<KonkaSpActivityAddr> getKonkaSpActivityAddrPaginatedList(KonkaSpActivityAddr t) {
		return this.konkaSpActivityAddrDao.selectEntityPaginatedList(t);
	}

}
