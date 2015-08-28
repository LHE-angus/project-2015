package com.ebiz.mmt.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.SapExecuteLogDao;
import com.ebiz.mmt.domain.SapExecuteLog;
import com.ebiz.mmt.service.SapExecuteLogService;


@Service
public class SapExecuteLogServiceImpl implements SapExecuteLogService {

	@Resource
	private SapExecuteLogDao sapExecuteLogDao;
	

	@Override
	public Long createSapExecuteLog(SapExecuteLog t) {
		return this.sapExecuteLogDao.insertEntity(t);
	}

	@Override
	public SapExecuteLog getSapExecuteLog(SapExecuteLog t) {
		return this.sapExecuteLogDao.selectEntity(t);
	}

	@Override
	public Long getSapExecuteLogCount(SapExecuteLog t) {
		return this.sapExecuteLogDao.selectEntityCount(t);
	}

	@Override
	public List<SapExecuteLog> getSapExecuteLogList(SapExecuteLog t) {
		return this.sapExecuteLogDao.selectEntityList(t);
	}

	@Override
	public int modifySapExecuteLog(SapExecuteLog t) {
		return this.sapExecuteLogDao.updateEntity(t);
	}

	@Override
	public int removeSapExecuteLog(SapExecuteLog t) {
		return this.sapExecuteLogDao.deleteEntity(t);
	}

	@Override
	public List<SapExecuteLog> getSapExecuteLogPaginatedList(SapExecuteLog t) {
		return this.sapExecuteLogDao.selectEntityPaginatedList(t);
	}

	@Override
	public List<HashMap> getSapExecuteLogForReport() {
		return this.sapExecuteLogDao.selectSapExecuteLogReport();
	}


}
