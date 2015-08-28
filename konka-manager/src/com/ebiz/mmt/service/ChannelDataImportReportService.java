package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KhjxQueryResult;

public interface ChannelDataImportReportService {

	List<KhjxQueryResult> getKhjxDataFromChannelDataImport(String sql, Object[] args);
	int getKhjxDataFromChannelDataImportCount(String sql, Object[] args);

}
