package com.ebiz.mmt.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaMobileCategoryDao;
import com.ebiz.mmt.dao.KonkaMobileReportHistoryDao;
import com.ebiz.mmt.dao.KonkaMobileThingsUseReportDao;
import com.ebiz.mmt.domain.KonkaMobileCategory;
import com.ebiz.mmt.domain.KonkaMobileReportHistory;
import com.ebiz.mmt.domain.KonkaMobileThingsUseReport;
import com.ebiz.mmt.service.KonkaMobileThingsUseReportService;

@Service
public class KonkaMobileThingsUseReportServiceImpl implements
		KonkaMobileThingsUseReportService {

	@Resource
	private KonkaMobileThingsUseReportDao konkaMobileThingsUseReportDao;

	@Resource
	private KonkaMobileCategoryDao konkaMobileCategoryDao;

	@Resource
	private KonkaMobileReportHistoryDao konkaMobileReportHistoryDao;

	public Long createKonkaMobileThingsUseReport(KonkaMobileThingsUseReport t) {
		return this.konkaMobileThingsUseReportDao.insertEntity(t);
	}

	public KonkaMobileThingsUseReport getKonkaMobileThingsUseReport(
			KonkaMobileThingsUseReport t) {
		return this.konkaMobileThingsUseReportDao.selectEntity(t);
	}

	public Long getKonkaMobileThingsUseReportCount(KonkaMobileThingsUseReport t) {
		return this.konkaMobileThingsUseReportDao.selectEntityCount(t);
	}

	public List<KonkaMobileThingsUseReport> getKonkaMobileThingsUseReportList(
			KonkaMobileThingsUseReport t) {
		return this.konkaMobileThingsUseReportDao.selectEntityList(t);
	}

	public int modifyKonkaMobileThingsUseReport(KonkaMobileThingsUseReport t) {
		return this.konkaMobileThingsUseReportDao.updateEntity(t);
	}

	public int removeKonkaMobileThingsUseReport(KonkaMobileThingsUseReport t) {
		return this.konkaMobileThingsUseReportDao.deleteEntity(t);
	}

	public List<KonkaMobileThingsUseReport> getKonkaMobileThingsUseReportPaginatedList(
			KonkaMobileThingsUseReport t) {
		return this.konkaMobileThingsUseReportDao.selectEntityPaginatedList(t);
	}

	public Long createKonkaMobileThingsUseReportForHis(
			KonkaMobileThingsUseReport t) {
		Long result = this.konkaMobileThingsUseReportDao.insertEntity(t);

		KonkaMobileCategory category = new KonkaMobileCategory();
		category.setC_index(t.getThing_id());
		category = konkaMobileCategoryDao.selectEntity(category);

		// 添加历史记录
		KonkaMobileReportHistory konkaMobileReportHistory = new KonkaMobileReportHistory();
		konkaMobileReportHistory.setContent("物料名称：" + category.getC_name()
				+ " 使用量：" + t.getThing_num());
		konkaMobileReportHistory.setType_id(3l);
		konkaMobileReportHistory.setReport_man(t.getReprot_man());
		konkaMobileReportHistory.setReport_time(new Date());
		konkaMobileReportHistoryDao.insertEntity(konkaMobileReportHistory);

		return result;
	}

}
