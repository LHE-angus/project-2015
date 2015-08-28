package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.ebiz.mmt.dao.KonkaSellReportdataTmpDao;
import com.ebiz.mmt.domain.KonkaSellReportdataTmp;
import com.ebiz.mmt.service.KonkaSellReportdataTmpService;


@Service
public class KonkaSellReportdataTmpServiceImpl implements KonkaSellReportdataTmpService {

	@Resource
	private KonkaSellReportdataTmpDao konkaSellReportdataTmpDao;

	public Long createKonkaSellReportdataTmp(KonkaSellReportdataTmp t) {
		return this.konkaSellReportdataTmpDao.insertEntity(t);
	}

	public KonkaSellReportdataTmp getKonkaSellReportdataTmp(KonkaSellReportdataTmp t) {
		return this.konkaSellReportdataTmpDao.selectEntity(t);
	}

	public Long getKonkaSellReportdataTmpCount(KonkaSellReportdataTmp t) {
		return this.konkaSellReportdataTmpDao.selectEntityCount(t);
	}

	public List<KonkaSellReportdataTmp> getKonkaSellReportdataTmpList(KonkaSellReportdataTmp t) {
		return this.konkaSellReportdataTmpDao.selectEntityList(t);
	}

	public int modifyKonkaSellReportdataTmp(KonkaSellReportdataTmp t) {
		return this.konkaSellReportdataTmpDao.updateEntity(t);
	}

	public int removeKonkaSellReportdataTmp(KonkaSellReportdataTmp t) {
		return this.konkaSellReportdataTmpDao.deleteEntity(t);
	}

	public List<KonkaSellReportdataTmp> getKonkaSellReportdataTmpPaginatedList(KonkaSellReportdataTmp t) {
		return this.konkaSellReportdataTmpDao.selectEntityPaginatedList(t);
	}

}
