package com.ebiz.mmt.service;

import java.util.List;
import com.ebiz.mmt.domain.KonkaMobileThingsUseReport;

public interface KonkaMobileThingsUseReportService {

	Long createKonkaMobileThingsUseReport(KonkaMobileThingsUseReport t);

	int modifyKonkaMobileThingsUseReport(KonkaMobileThingsUseReport t);

	int removeKonkaMobileThingsUseReport(KonkaMobileThingsUseReport t);

	KonkaMobileThingsUseReport getKonkaMobileThingsUseReport(KonkaMobileThingsUseReport t);

	List<KonkaMobileThingsUseReport> getKonkaMobileThingsUseReportList(KonkaMobileThingsUseReport t);

	Long getKonkaMobileThingsUseReportCount(KonkaMobileThingsUseReport t);

	List<KonkaMobileThingsUseReport> getKonkaMobileThingsUseReportPaginatedList(KonkaMobileThingsUseReport t);

	Long createKonkaMobileThingsUseReportForHis(KonkaMobileThingsUseReport report);
	
}
