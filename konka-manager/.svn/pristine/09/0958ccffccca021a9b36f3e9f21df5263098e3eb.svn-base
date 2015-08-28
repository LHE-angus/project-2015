package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.QdDrpSyncLogDao;
import com.ebiz.mmt.domain.QdDrpSyncLog;
import com.ebiz.mmt.service.QdDrpSyncLogService;


@Service
public class QdDrpSyncLogServiceImpl implements QdDrpSyncLogService {

	@Resource
	private QdDrpSyncLogDao qdDrpSyncLogDao;
	

	public Long createQdDrpSyncLog(QdDrpSyncLog t) {
		return this.qdDrpSyncLogDao.insertEntity(t);
	}

	public QdDrpSyncLog getQdDrpSyncLog(QdDrpSyncLog t) {
		return this.qdDrpSyncLogDao.selectEntity(t);
	}

	public Long getQdDrpSyncLogCount(QdDrpSyncLog t) {
		return this.qdDrpSyncLogDao.selectEntityCount(t);
	}

	public List<QdDrpSyncLog> getQdDrpSyncLogList(QdDrpSyncLog t) {
		return this.qdDrpSyncLogDao.selectEntityList(t);
	}

	public int modifyQdDrpSyncLog(QdDrpSyncLog t) {
		return this.qdDrpSyncLogDao.updateEntity(t);
	}

	public int removeQdDrpSyncLog(QdDrpSyncLog t) {
		return this.qdDrpSyncLogDao.deleteEntity(t);
	}

	public List<QdDrpSyncLog> getQdDrpSyncLogPaginatedList(QdDrpSyncLog t) {
		return this.qdDrpSyncLogDao.selectEntityPaginatedList(t);
	}
    //

}
