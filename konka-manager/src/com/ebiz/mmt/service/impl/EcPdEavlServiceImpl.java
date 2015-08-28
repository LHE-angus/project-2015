package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcPdEavlDao;
import com.ebiz.mmt.dao.KonkaPeAttachmentsDao;
import com.ebiz.mmt.domain.EcPdEavl;
import com.ebiz.mmt.domain.KonkaPeAttachments;
import com.ebiz.mmt.service.EcPdEavlService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-09-09 10:06:25
 */
@Service
public class EcPdEavlServiceImpl implements EcPdEavlService {

	@Resource
	private EcPdEavlDao ecPdEavlDao;
	
	@Resource
	private KonkaPeAttachmentsDao konkaPeAttachmentsDao;
	

	public Long createEcPdEavl(EcPdEavl t) {
		Long id =this.ecPdEavlDao.insertEntity(t);
		if(t.getKonkaPeAttachmentsList()!=null&&t.getKonkaPeAttachmentsList().size()>0){
			for(KonkaPeAttachments a:t.getKonkaPeAttachmentsList()){
				a.setLink_id(id);
				konkaPeAttachmentsDao.insertEntity(a);
			}
		}
		
		return id;
	}

	public EcPdEavl getEcPdEavl(EcPdEavl t) {
		return this.ecPdEavlDao.selectEntity(t);
	}

	public Long getEcPdEavlCount(EcPdEavl t) {
		return this.ecPdEavlDao.selectEntityCount(t);
	}

	public List<EcPdEavl> getEcPdEavlList(EcPdEavl t) {
		return this.ecPdEavlDao.selectEntityList(t);
	}

	public int modifyEcPdEavl(EcPdEavl t) {
		return this.ecPdEavlDao.updateEntity(t);
	}

	public int removeEcPdEavl(EcPdEavl t) {
		return this.ecPdEavlDao.deleteEntity(t);
	}

	public List<EcPdEavl> getEcPdEavlPaginatedList(EcPdEavl t) {
		return this.ecPdEavlDao.selectEntityPaginatedList(t);
	}

	/**
	 * @author tudp 
	 * @throws 2013-09-17
	 */
	public Long getEcPdEavlSumEvalScore(EcPdEavl t){
		return this.ecPdEavlDao.selectEcPdEavlSumEvalScore(t);
	}
}
