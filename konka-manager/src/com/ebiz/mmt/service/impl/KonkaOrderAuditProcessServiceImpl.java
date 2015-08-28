package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaOrderAuditProcessDao;
import com.ebiz.mmt.dao.KonkaOrderAuditProcessNodeDao;
import com.ebiz.mmt.domain.KonkaOrderAuditProcess;
import com.ebiz.mmt.domain.KonkaOrderAuditProcessNode;
import com.ebiz.mmt.service.KonkaOrderAuditProcessService;

/**
 * @author Wu,Yang
 * @version 2011-11-30 14:09
 */
@Service
public class KonkaOrderAuditProcessServiceImpl implements KonkaOrderAuditProcessService {

	@Resource
	private KonkaOrderAuditProcessDao konkaOrderAuditProcessDao;
	
	@Resource
	private KonkaOrderAuditProcessNodeDao konkaOrderAuditProcessNodeDao;
	

	public Long createKonkaOrderAuditProcess(KonkaOrderAuditProcess t) {
		Long  id = this.konkaOrderAuditProcessDao.insertEntity(t);
		List<KonkaOrderAuditProcessNode> konkaOrderAuditProcessNodeList = t.getKonkaOrderAuditProcessNodeList();
		if( konkaOrderAuditProcessNodeList != null && konkaOrderAuditProcessNodeList.size() > 0){
			for(KonkaOrderAuditProcessNode kkoapn :konkaOrderAuditProcessNodeList ){
				kkoapn.setAudit_proces_id(id);
				konkaOrderAuditProcessNodeDao.insertEntity(kkoapn);
			}
			
		}
		return  id;
	}

	public KonkaOrderAuditProcess getKonkaOrderAuditProcess(KonkaOrderAuditProcess t) {
		return this.konkaOrderAuditProcessDao.selectEntity(t);
	}

	public Long getKonkaOrderAuditProcessCount(KonkaOrderAuditProcess t) {
		return this.konkaOrderAuditProcessDao.selectEntityCount(t);
	}

	public List<KonkaOrderAuditProcess> getKonkaOrderAuditProcessList(KonkaOrderAuditProcess t) {
		return this.konkaOrderAuditProcessDao.selectEntityList(t);
	}

	public int modifyKonkaOrderAuditProcess(KonkaOrderAuditProcess t) {
		int id =  this.konkaOrderAuditProcessDao.updateEntity(t);
//		List<KonkaOrderAuditProcessNode> konkaOrderAuditProcessNodeList = t.getKonkaOrderAuditProcessNodeList();
//		if( konkaOrderAuditProcessNodeList != null && konkaOrderAuditProcessNodeList.size() > 0){
//			for(KonkaOrderAuditProcessNode kkoapn :konkaOrderAuditProcessNodeList ){
//				kkoapn.setIs_del(1);
//				konkaOrderAuditProcessNodeDao.updateEntity(kkoapn);
//			}
//			
//		}
		return id ;
	}

	public int removeKonkaOrderAuditProcess(KonkaOrderAuditProcess t) {
		return this.konkaOrderAuditProcessDao.deleteEntity(t);
	}

	public List<KonkaOrderAuditProcess> getKonkaOrderAuditProcessPaginatedList(KonkaOrderAuditProcess t) {
		return this.konkaOrderAuditProcessDao.selectEntityPaginatedList(t);
	}

}
