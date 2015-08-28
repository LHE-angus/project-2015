package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.QdDrpSyncLog;


public interface QdDrpSyncLogService {

	Long createQdDrpSyncLog(QdDrpSyncLog t);

	int modifyQdDrpSyncLog(QdDrpSyncLog t);

	int removeQdDrpSyncLog(QdDrpSyncLog t);

	QdDrpSyncLog getQdDrpSyncLog(QdDrpSyncLog t);

	List<QdDrpSyncLog> getQdDrpSyncLogList(QdDrpSyncLog t);

	Long getQdDrpSyncLogCount(QdDrpSyncLog t);

	List<QdDrpSyncLog> getQdDrpSyncLogPaginatedList(QdDrpSyncLog t);


    //
}