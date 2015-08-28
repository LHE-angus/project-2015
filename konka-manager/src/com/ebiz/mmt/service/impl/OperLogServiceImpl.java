package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.OperLogDao;
import com.ebiz.mmt.domain.OperLog;
import com.ebiz.mmt.service.OperLogService;

@Service
public class OperLogServiceImpl implements OperLogService {

	@Resource
	private OperLogDao OperLogDao;

	public Long createOperLog(OperLog t) {
		return this.OperLogDao.insertEntity(t);
	}

	public OperLog getOperLog(OperLog t) {
		return this.OperLogDao.selectEntity(t);
	}

	public Long getOperLogCount(OperLog t) {
		return this.OperLogDao.selectEntityCount(t);
	}

	public List<OperLog> getOperLogList(OperLog t) {
		return this.OperLogDao.selectEntityList(t);
	}

	public int modifyOperLog(OperLog t) {
		return this.OperLogDao.updateEntity(t);
	}

	public int removeOperLog(OperLog t) {
		return this.OperLogDao.deleteEntity(t);
	}

	public List<OperLog> getOperLogPaginatedList(OperLog t) {
		return this.OperLogDao.selectEntityPaginatedList(t);
	}

}
