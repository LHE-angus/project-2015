package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcGroupBuyMainDao;
import com.ebiz.mmt.domain.EcGroupBuyMain;
import com.ebiz.mmt.service.EcGroupBuyMainService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-11-25 15:36:05
 */
@Service
public class EcGroupBuyMainServiceImpl implements EcGroupBuyMainService {

	@Resource
	private EcGroupBuyMainDao ecGroupBuyMainDao;

	public Long createEcGroupBuyMain(EcGroupBuyMain t) {
		return this.ecGroupBuyMainDao.insertEntity(t);
	}

	public EcGroupBuyMain getEcGroupBuyMain(EcGroupBuyMain t) {
		return this.ecGroupBuyMainDao.selectEntity(t);
	}

	public Long getEcGroupBuyMainCount(EcGroupBuyMain t) {
		return this.ecGroupBuyMainDao.selectEntityCount(t);
	}

	public List<EcGroupBuyMain> getEcGroupBuyMainList(EcGroupBuyMain t) {
		return this.ecGroupBuyMainDao.selectEntityList(t);
	}

	public int modifyEcGroupBuyMain(EcGroupBuyMain t) {
		return this.ecGroupBuyMainDao.updateEntity(t);
	}

	public int removeEcGroupBuyMain(EcGroupBuyMain t) {
		return this.ecGroupBuyMainDao.deleteEntity(t);
	}

	public List<EcGroupBuyMain> getEcGroupBuyMainPaginatedList(EcGroupBuyMain t) {
		return this.ecGroupBuyMainDao.selectEntityPaginatedList(t);
	}

	@Override
	public int modifyEcGroupBuyMainViewCounts(EcGroupBuyMain t) {
		return this.ecGroupBuyMainDao.updateEcGroupBuyMainViewCounts(t);
	}

}
