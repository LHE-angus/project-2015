package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.OperLog;

public interface OperLogService {

	Long createOperLog(OperLog t);

	int modifyOperLog(OperLog t);

	int removeOperLog(OperLog t);

	OperLog getOperLog(OperLog t);

	List<OperLog> getOperLogList(OperLog t);

	Long getOperLogCount(OperLog t);

	List<OperLog> getOperLogPaginatedList(OperLog t);

}
