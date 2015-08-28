package com.ebiz.mmt.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.LogOfJobDao;
import com.ebiz.mmt.domain.LogOfJob;
import com.ebiz.mmt.service.LogOfJobService;


@Service
public class LogOfJobServiceImpl implements LogOfJobService {

	@Resource
	private LogOfJobDao logOfJobDao;
	

	public Long createLogOfJob(LogOfJob t) {
		return this.logOfJobDao.insertEntity(t);
	}

	public LogOfJob getLogOfJob(LogOfJob t) {
		return this.logOfJobDao.selectEntity(t);
	}

	public Long getLogOfJobCount(LogOfJob t) {
		return this.logOfJobDao.selectEntityCount(t);
	}

	public List<LogOfJob> getLogOfJobList(LogOfJob t) {
		return this.logOfJobDao.selectEntityList(t);
	}

	public int modifyLogOfJob(LogOfJob t) {
		return this.logOfJobDao.updateEntity(t);
	}

	public int removeLogOfJob(LogOfJob t) {
		return this.logOfJobDao.deleteEntity(t);
	}

	public List<LogOfJob> getLogOfJobPaginatedList(LogOfJob t) {
		return this.logOfJobDao.selectEntityPaginatedList(t);
	}

	@Override
	public List<LogOfJob> getSynTypeList() {
		return this.logOfJobDao.selectSynTypeList();
	}

	@Override
	public List<HashMap> getLogOfJobListForMap(LogOfJob t) {
		return this.logOfJobDao.selectLogOfJobListForMap(t);
	}

}
