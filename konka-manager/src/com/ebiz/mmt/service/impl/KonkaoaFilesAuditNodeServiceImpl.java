package com.ebiz.mmt.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaoaFilesAuditNodeDao;
import com.ebiz.mmt.dao.KonkaoaFilesDao;
import com.ebiz.mmt.domain.KonkaoaFiles;
import com.ebiz.mmt.domain.KonkaoaFilesAuditNode;
import com.ebiz.mmt.service.KonkaoaFilesAuditNodeService;

/**
 * @author Hui,Gang
 * @version Build 2010-12-13 14:49:33
 */
@Service
public class KonkaoaFilesAuditNodeServiceImpl implements KonkaoaFilesAuditNodeService {

	@Resource
	private KonkaoaFilesAuditNodeDao filesAuditNodeDao;

	@Resource
	private KonkaoaFilesDao filesDao;

	public Long createKonkaoaFilesAuditNode(KonkaoaFilesAuditNode t) {
		List<KonkaoaFilesAuditNode> konkaoaFilesAuditNodeList = t.getKonkaoaFilesAuditNodeList();
		if (null != konkaoaFilesAuditNodeList) {
			KonkaoaFiles kf = new KonkaoaFiles();
			kf.setFile_type(0);
			kf.setSubmit_dept("null");
			Long id = this.filesDao.insertEntity(kf);

			for (KonkaoaFilesAuditNode kfan : konkaoaFilesAuditNodeList) {
				kfan.setAudit_type(t.getAudit_type());
				kfan.setAudit_node_name(t.getAudit_node_name());
                kfan.setLink_id(id);// 加上link_id
				this.filesAuditNodeDao.insertEntity(kfan);
			}

			kf.setId(id);
            this.filesDao.deleteEntity(kf);// set is_del =1 ,submit_dept_id is null 的都是流程

			return 1l;
		} else {
			return this.filesAuditNodeDao.insertEntity(t);
		}
	}

	public KonkaoaFilesAuditNode getKonkaoaFilesAuditNode(KonkaoaFilesAuditNode t) {
		return this.filesAuditNodeDao.selectEntity(t);
	}

	public Long getKonkaoaFilesAuditNodeCount(KonkaoaFilesAuditNode t) {
		return this.filesAuditNodeDao.selectEntityCount(t);
	}

	public List<KonkaoaFilesAuditNode> getKonkaoaFilesAuditNodeList(KonkaoaFilesAuditNode t) {
		return this.filesAuditNodeDao.selectEntityList(t);
	}

	public int modifyKonkaoaFilesAuditNode(KonkaoaFilesAuditNode t) {
		// 先删除原有数据
		Long link_id = t.getLink_id();
		if (null != link_id) {
            KonkaoaFilesAuditNode _entity = new KonkaoaFilesAuditNode();
            _entity.setLink_id(link_id);
            this.filesAuditNodeDao.deleteEntity(_entity);
        }
		// 重新插入数据
		List<KonkaoaFilesAuditNode> konkaoaFilesAuditNodeList = t.getKonkaoaFilesAuditNodeList();
		if (null != konkaoaFilesAuditNodeList) {
            for (KonkaoaFilesAuditNode kfan : konkaoaFilesAuditNodeList) {
                kfan.setAudit_type(t.getAudit_type());
                kfan.setAudit_node_name(t.getAudit_node_name());
                if (t.getAudit_node_name().equals("EPP退换货订单审核流程") || t.getAudit_node_name().equals("顺丰审核流程")) {
                    kfan.setLink_id(t.getLink_id());
                } else {
                    kfan.setLink_id(link_id);
                }
                this.filesAuditNodeDao.insertEntity(kfan);
            }
			return 1;
		} else {
			return this.filesAuditNodeDao.updateEntity(t);
		}
	}

	public int removeKonkaoaFilesAuditNode(KonkaoaFilesAuditNode t) {
		return this.filesAuditNodeDao.deleteEntity(t);
	}

	public List<KonkaoaFilesAuditNode> getKonkaoaFilesAuditNodePaginatedList(KonkaoaFilesAuditNode t) {
		return this.filesAuditNodeDao.selectEntityPaginatedList(t);
	}

	public List<KonkaoaFilesAuditNode> getKonkaoaFilesAuditNodeListForView(KonkaoaFilesAuditNode t) {
		return this.filesAuditNodeDao.selectKonkaoaFilesAuditNodeListForView(t);
	}

	@Override
	public Long createKonkaoaFilesAuditNodeList(KonkaoaFilesAuditNode t) {
		List<KonkaoaFilesAuditNode> konkaoaFilesAuditNodeList = t.getKonkaoaFilesAuditNodeList();
		if (null != konkaoaFilesAuditNodeList) {
			KonkaoaFiles kf = new KonkaoaFiles();
			kf.setFile_type(0);
			kf.setSubmit_dept("null");
			Long id = this.filesDao.insertEntity(kf);

			for (KonkaoaFilesAuditNode kfan : konkaoaFilesAuditNodeList) {
				kfan.setLink_id(t.getLink_id());
				kfan.setAudit_type(t.getAudit_type());
				kfan.setAudit_node_name(t.getAudit_node_name());
				kfan.setLink_id(id);
				this.filesAuditNodeDao.insertEntity(kfan);
			}

			kf.setId(id);
			this.filesDao.deleteEntity(kf);

			return id;
		} else {
			return this.filesAuditNodeDao.insertEntity(t);
		}
	}

	@Override
	public int modifySystemAplication(KonkaoaFilesAuditNode t) {
		
		return this.filesAuditNodeDao.updateEntity(t);
	}

	@Override
	public Long getMaxKonkaoaFilesAuditNode(KonkaoaFilesAuditNode t) {
		
		return this.filesAuditNodeDao.selectMaxLevel(t);
	}

	@Override
	public List<HashMap> getAplicationAuditList(KonkaoaFilesAuditNode t) {
		return this.filesAuditNodeDao.selectAuditList(t);
	}

	@Override
	public List<HashMap> getKonkaoaFilesAuditNodeListForCust(
			KonkaoaFilesAuditNode t) {
		return this.filesAuditNodeDao.selectKonkaoaFilesAuditNodeListForCust(t);
	}

	@Override
	public int updateOldNode(KonkaoaFilesAuditNode t) {
		return this.filesAuditNodeDao.updateOldNode(t);
	}

	@Override
	public List<HashMap> getAuditInfoList(KonkaoaFilesAuditNode t) {
		return this.filesAuditNodeDao.selectAuditInfoList(t);
	}

}