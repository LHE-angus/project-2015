package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcLuckyTimeDao;
import com.ebiz.mmt.domain.EcLuckyTime;
import com.ebiz.mmt.service.EcLuckyTimeService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-07-11 16:03:36
 */
@Service
public class EcLuckyTimeServiceImpl implements EcLuckyTimeService {

	@Resource
	private EcLuckyTimeDao ecLuckyTimeDao;
	

	public Long createEcLuckyTime(EcLuckyTime t) {
		return this.ecLuckyTimeDao.insertEntity(t);
	}

	public EcLuckyTime getEcLuckyTime(EcLuckyTime t) {
		return this.ecLuckyTimeDao.selectEntity(t);
	}

	public Long getEcLuckyTimeCount(EcLuckyTime t) {
		return this.ecLuckyTimeDao.selectEntityCount(t);
	}

	public List<EcLuckyTime> getEcLuckyTimeList(EcLuckyTime t) {
		return this.ecLuckyTimeDao.selectEntityList(t);
	}

	public int modifyEcLuckyTime(EcLuckyTime t) {
		return this.ecLuckyTimeDao.updateEntity(t);
	}

	public int removeEcLuckyTime(EcLuckyTime t) {
		return this.ecLuckyTimeDao.deleteEntity(t);
	}

	public List<EcLuckyTime> getEcLuckyTimePaginatedList(EcLuckyTime t) {
		return this.ecLuckyTimeDao.selectEntityPaginatedList(t);
	}

}
