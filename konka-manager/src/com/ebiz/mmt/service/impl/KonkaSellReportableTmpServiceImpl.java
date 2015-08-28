package com.ebiz.mmt.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.ebiz.mmt.dao.KonkaSellReportableTmpDao;
import com.ebiz.mmt.domain.KonkaSellReportableTmp;
import com.ebiz.mmt.service.KonkaSellReportableTmpService;

@Service
public class KonkaSellReportableTmpServiceImpl implements
		KonkaSellReportableTmpService {

	@Resource
	private KonkaSellReportableTmpDao konkaSellReportableTmpDao;

	public boolean checkData(KonkaSellReportableTmp t) {
		boolean i = true;

		return i;
	}

	public Long createKonkaSellReportableTmp(KonkaSellReportableTmp t) {
		return this.konkaSellReportableTmpDao.insertEntity(t);
	}

	public KonkaSellReportableTmp getKonkaSellReportableTmp(
			KonkaSellReportableTmp t) {
		return this.konkaSellReportableTmpDao.selectEntity(t);
	}

	public Long getKonkaSellReportableTmpCount(KonkaSellReportableTmp t) {
		return this.konkaSellReportableTmpDao.selectEntityCount(t);
	}

	public List<KonkaSellReportableTmp> getKonkaSellReportableTmpList(
			KonkaSellReportableTmp t) {
		return this.konkaSellReportableTmpDao.selectEntityList(t);
	}

	public int modifyKonkaSellReportableTmp(KonkaSellReportableTmp t) {
		return this.konkaSellReportableTmpDao.updateEntity(t);
	}

	public int removeKonkaSellReportableTmp(KonkaSellReportableTmp t) {
		return this.konkaSellReportableTmpDao.deleteEntity(t);
	}

	public List<KonkaSellReportableTmp> getKonkaSellReportableTmpPaginatedList(
			KonkaSellReportableTmp t) {
		return this.konkaSellReportableTmpDao.selectEntityPaginatedList(t);
	}

}
