package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaMobileReportHistoryDao;
import com.ebiz.mmt.domain.KonkaMobileReportHistory;
import com.ebiz.mmt.service.KonkaMobileReportHistoryService;


@Service
public class KonkaMobileReportHistoryServiceImpl implements KonkaMobileReportHistoryService {

	@Resource
	private KonkaMobileReportHistoryDao konkaMobileReportHistoryDao;

	public Long createKonkaMobileReportHistory(KonkaMobileReportHistory t) {
		return this.konkaMobileReportHistoryDao.insertEntity(t);
	}

	public KonkaMobileReportHistory getKonkaMobileReportHistory(KonkaMobileReportHistory t) {
		return this.konkaMobileReportHistoryDao.selectEntity(t);
	}

	public Long getKonkaMobileReportHistoryCount(KonkaMobileReportHistory t) {
		return this.konkaMobileReportHistoryDao.selectEntityCount(t);
	}

	public List<KonkaMobileReportHistory> getKonkaMobileReportHistoryList(KonkaMobileReportHistory t) {
		return this.konkaMobileReportHistoryDao.selectEntityList(t);
	}

	public int modifyKonkaMobileReportHistory(KonkaMobileReportHistory t) {
		return this.konkaMobileReportHistoryDao.updateEntity(t);
	}

	public int removeKonkaMobileReportHistory(KonkaMobileReportHistory t) {
		return this.konkaMobileReportHistoryDao.deleteEntity(t);
	}

	public List<KonkaMobileReportHistory> getKonkaMobileReportHistoryPaginatedList(KonkaMobileReportHistory t) {
		return this.konkaMobileReportHistoryDao.selectEntityPaginatedList(t);
	}

}
