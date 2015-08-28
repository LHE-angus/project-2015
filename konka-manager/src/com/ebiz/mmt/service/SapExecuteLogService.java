package com.ebiz.mmt.service;

import java.util.HashMap;
import java.util.List;

import com.ebiz.mmt.domain.SapExecuteLog;


public interface SapExecuteLogService {

	Long createSapExecuteLog(SapExecuteLog t);

	int modifySapExecuteLog(SapExecuteLog t);

	int removeSapExecuteLog(SapExecuteLog t);

	SapExecuteLog getSapExecuteLog(SapExecuteLog t);

	List<SapExecuteLog> getSapExecuteLogList(SapExecuteLog t);

	Long getSapExecuteLogCount(SapExecuteLog t);

	List<SapExecuteLog> getSapExecuteLogPaginatedList(SapExecuteLog t);

	List<HashMap> getSapExecuteLogForReport();

}