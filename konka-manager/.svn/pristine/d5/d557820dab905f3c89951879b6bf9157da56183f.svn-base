package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaSalesmanInfoLogDao;
import com.ebiz.mmt.domain.KonkaSalesmanInfoLog;
import com.ebiz.mmt.service.KonkaSalesmanInfoLogService;


@Service
public class KonkaSalesmanInfoLogServiceImpl implements KonkaSalesmanInfoLogService {

	@Resource
	private KonkaSalesmanInfoLogDao konkaSalesmanInfoLogDao;
	

	public Long createKonkaSalesmanInfoLog(KonkaSalesmanInfoLog t) {
		return this.konkaSalesmanInfoLogDao.insertEntity(t);
	}

	public KonkaSalesmanInfoLog getKonkaSalesmanInfoLog(KonkaSalesmanInfoLog t) {
		return this.konkaSalesmanInfoLogDao.selectEntity(t);
	}

	public Long getKonkaSalesmanInfoLogCount(KonkaSalesmanInfoLog t) {
		return this.konkaSalesmanInfoLogDao.selectEntityCount(t);
	}

	public List<KonkaSalesmanInfoLog> getKonkaSalesmanInfoLogList(KonkaSalesmanInfoLog t) {
		return this.konkaSalesmanInfoLogDao.selectEntityList(t);
	}

	public int modifyKonkaSalesmanInfoLog(KonkaSalesmanInfoLog t) {
		return this.konkaSalesmanInfoLogDao.updateEntity(t);
	}

	public int removeKonkaSalesmanInfoLog(KonkaSalesmanInfoLog t) {
		return this.konkaSalesmanInfoLogDao.deleteEntity(t);
	}

	public List<KonkaSalesmanInfoLog> getKonkaSalesmanInfoLogPaginatedList(KonkaSalesmanInfoLog t) {
		return this.konkaSalesmanInfoLogDao.selectEntityPaginatedList(t);
	}

}
