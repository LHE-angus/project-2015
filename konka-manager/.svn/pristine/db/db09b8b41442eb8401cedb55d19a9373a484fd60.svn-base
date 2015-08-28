package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcVoteMainDao;
import com.ebiz.mmt.domain.EcVoteMain;
import com.ebiz.mmt.domain.EcVoteMain;
import com.ebiz.mmt.service.EcVoteMainService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-10-29 14:02:24
 */
@Service
public class EcVoteMainServiceImpl implements EcVoteMainService {

	@Resource
	private EcVoteMainDao ecVoteMainDao;
	

	public Long createEcVoteMain(EcVoteMain t) {
		return this.ecVoteMainDao.insertEntity(t);
	}

	public EcVoteMain getEcVoteMain(EcVoteMain t) {
		return this.ecVoteMainDao.selectEntity(t);
	}

	public Long getEcVoteMainCount(EcVoteMain t) {
		return this.ecVoteMainDao.selectEntityCount(t);
	}

	public List<EcVoteMain> getEcVoteMainList(EcVoteMain t) {
		return this.ecVoteMainDao.selectEntityList(t);
	}

	public int modifyEcVoteMain(EcVoteMain t) {
		return this.ecVoteMainDao.updateEntity(t);
	}

	public int removeEcVoteMain(EcVoteMain t) {
		return this.ecVoteMainDao.deleteEntity(t);
	}

	public List<EcVoteMain> getEcVoteMainPaginatedList(EcVoteMain t) {
		return this.ecVoteMainDao.selectEntityPaginatedList(t);
	}

	public int modifyEcVoteMainViewCounts(EcVoteMain t) {
		return this.ecVoteMainDao.updateEcVoteMainViewCounts(t);
	}

}
