package com.ebiz.mmt.service;

import java.util.List;
import com.ebiz.mmt.domain.KonkaMobileReportHistory;

public interface KonkaMobileReportHistoryService {

	Long createKonkaMobileReportHistory(KonkaMobileReportHistory t);

	int modifyKonkaMobileReportHistory(KonkaMobileReportHistory t);

	int removeKonkaMobileReportHistory(KonkaMobileReportHistory t);

	KonkaMobileReportHistory getKonkaMobileReportHistory(KonkaMobileReportHistory t);

	List<KonkaMobileReportHistory> getKonkaMobileReportHistoryList(KonkaMobileReportHistory t);

	Long getKonkaMobileReportHistoryCount(KonkaMobileReportHistory t);

	List<KonkaMobileReportHistory> getKonkaMobileReportHistoryPaginatedList(KonkaMobileReportHistory t);

}
