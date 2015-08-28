package com.ebiz.mmt.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.ebiz.mmt.dao.KonkaSellReportTmpResDao;
import com.ebiz.mmt.domain.KonkaSellReportTmpRes;
import com.ebiz.mmt.service.KonkaSellReportTmpResService;


@Service
public class KonkaSellReportTmpResServiceImpl implements KonkaSellReportTmpResService {

	@Resource
	private KonkaSellReportTmpResDao konkaSellReportTmpResDao;

	public Long createKonkaSellReportTmpRes(KonkaSellReportTmpRes t) {
		return this.konkaSellReportTmpResDao.insertEntity(t);
	}

	public KonkaSellReportTmpRes getKonkaSellReportTmpRes(KonkaSellReportTmpRes t) {
		return this.konkaSellReportTmpResDao.selectEntity(t);
	}

	public Long getKonkaSellReportTmpResCount(KonkaSellReportTmpRes t) {
		return this.konkaSellReportTmpResDao.selectEntityCount(t);
	}

	public List<KonkaSellReportTmpRes> getKonkaSellReportTmpResList(KonkaSellReportTmpRes t) {
		return this.konkaSellReportTmpResDao.selectEntityList(t);
	}

	public int modifyKonkaSellReportTmpRes(KonkaSellReportTmpRes t) {
		return this.konkaSellReportTmpResDao.updateEntity(t);
	}

	public int removeKonkaSellReportTmpRes(KonkaSellReportTmpRes t) {
		return this.konkaSellReportTmpResDao.deleteEntity(t);
	}

	public List<KonkaSellReportTmpRes> getKonkaSellReportTmpResPaginatedList(KonkaSellReportTmpRes t) {
		return this.konkaSellReportTmpResDao.selectEntityPaginatedList(t);
	}

}
