package com.ebiz.mmt.service;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import com.ebiz.mmt.domain.KonkaSellReportTmp;

public interface KonkaSellReportTmpService {

	Long createKonkaSellReportTmp(KonkaSellReportTmp t);

	int modifyKonkaSellReportTmp(KonkaSellReportTmp t);

	int removeKonkaSellReportTmp(KonkaSellReportTmp t);

	KonkaSellReportTmp getKonkaSellReportTmp(KonkaSellReportTmp t);

	List<KonkaSellReportTmp> getKonkaSellReportTmpList(KonkaSellReportTmp t);

	Long getKonkaSellReportTmpCount(KonkaSellReportTmp t);

	List<KonkaSellReportTmp> getKonkaSellReportTmpPaginatedList(
			KonkaSellReportTmp t);

	boolean calculateDay(String dayDate) throws ParseException;

	List<HashMap<String, Object>> reportTMP(KonkaSellReportTmp t);

	List<HashMap<String, Object>> reportTMPAll(KonkaSellReportTmp t);

	Long reportTMPCount(KonkaSellReportTmp t);
}
