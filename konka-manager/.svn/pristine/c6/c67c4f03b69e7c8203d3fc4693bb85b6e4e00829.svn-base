package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcGiftCommDao;
import com.ebiz.mmt.dao.EcGiftContentDao;
import com.ebiz.mmt.dao.EcGiftDao;
import com.ebiz.mmt.domain.EcGift;
import com.ebiz.mmt.domain.EcGiftComm;
import com.ebiz.mmt.domain.EcGiftContent;
import com.ebiz.mmt.service.EcGiftService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-09-09 10:06:25
 */
@Service
public class EcGiftServiceImpl implements EcGiftService {

	@Resource
	private EcGiftDao ecGiftDao;

	@Resource
	private EcGiftContentDao ecGiftContentDao;
	
	@Resource
	private EcGiftCommDao ecGiftCommDao;

	public Long createEcGift(EcGift t) {
		return this.ecGiftDao.insertEntity(t);
	}

	public EcGift getEcGift(EcGift t) {
		return this.ecGiftDao.selectEntity(t);
	}

	public Long getEcGiftCount(EcGift t) {
		return this.ecGiftDao.selectEntityCount(t);
	}

	public List<EcGift> getEcGiftList(EcGift t) {
		return this.ecGiftDao.selectEntityList(t);
	}

	public int modifyEcGift(EcGift t) {
		return this.ecGiftDao.updateEntity(t);
	}

	public int removeEcGift(EcGift t) {
		return this.ecGiftDao.deleteEntity(t);
	}

	public List<EcGift> getEcGiftPaginatedList(EcGift t) {
		return this.ecGiftDao.selectEntityPaginatedList(t);
	}

	public Long createEcGiftIncludeContent(EcGift t) {
		Long id = this.ecGiftDao.insertEntity(t);
		if (t.getEcGiftContentList() != null && t.getEcGiftContentList().size() > 0) {
			for (int i = 0; i < t.getEcGiftContentList().size(); i++) {
				EcGiftContent e = t.getEcGiftContentList().get(i);
				e.setKbp_id(id);
				this.ecGiftContentDao.insertEntity(e);
			}
		}
		EcGiftComm ecGiftComm=t.getEcGiftComm();
		//关联商品表
		if(ecGiftComm!=null){
			ecGiftComm.setGift_id(id);
			ecGiftComm.setOwn_sys(t.getOwn_sys());
			if(ecGiftComm.getId()==null){
				this.ecGiftCommDao.insertEntity(ecGiftComm);
			}else{
				this.ecGiftCommDao.updateEntity(ecGiftComm);
			}
		}
		return id;
	}

	public int modifyEcGiftIncludeContent(EcGift t) {

		EcGiftContent entity = new EcGiftContent();
		entity.setKbp_id(t.getId());
		this.ecGiftContentDao.deleteEcGiftContentForKbpId(entity);

		if (t.getEcGiftContentList() != null && t.getEcGiftContentList().size() > 0) {
			for (int i = 0; i < t.getEcGiftContentList().size(); i++) {
				EcGiftContent e = t.getEcGiftContentList().get(i);
				e.setKbp_id(t.getId());
				this.ecGiftContentDao.insertEntity(e);
			}
		}
		EcGiftComm ecGiftComm=t.getEcGiftComm();
		//关联商品表
		if(ecGiftComm!=null){
			ecGiftComm.setGift_id(t.getId());
			ecGiftComm.setOwn_sys(t.getOwn_sys());
			if(ecGiftComm.getId()==null){
				this.ecGiftCommDao.insertEntity(ecGiftComm);
			}else{
				this.ecGiftCommDao.updateEntity(ecGiftComm);
			}
		}
		return this.ecGiftDao.updateEntity(t);
	}
}
