package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcAuctionMainDao;
import com.ebiz.mmt.domain.EcAuctionMain;
import com.ebiz.mmt.service.EcAuctionMainService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-08-28 15:50:21
 */
@Service
public class EcAuctionMainServiceImpl implements EcAuctionMainService {

	@Resource
	private EcAuctionMainDao ecAuctionMainDao;
	

	public Long createEcAuctionMain(EcAuctionMain t) {
		return this.ecAuctionMainDao.insertEntity(t);
	}

	public EcAuctionMain getEcAuctionMain(EcAuctionMain t) {
		return this.ecAuctionMainDao.selectEntity(t);
	}

	public Long getEcAuctionMainCount(EcAuctionMain t) {
		return this.ecAuctionMainDao.selectEntityCount(t);
	}

	public List<EcAuctionMain> getEcAuctionMainList(EcAuctionMain t) {
		return this.ecAuctionMainDao.selectEntityList(t);
	}

	public int modifyEcAuctionMain(EcAuctionMain t) {
		return this.ecAuctionMainDao.updateEntity(t);
	}

	public int removeEcAuctionMain(EcAuctionMain t) {
		return this.ecAuctionMainDao.deleteEntity(t);
	}

	public List<EcAuctionMain> getEcAuctionMainPaginatedList(EcAuctionMain t) {
		return this.ecAuctionMainDao.selectEntityPaginatedList(t);
	}

	public int modifyEcAuctionMainViewCounts(EcAuctionMain t) {
		return this.ecAuctionMainDao.updateEcAuctionMainViewCounts(t);
	}
	
	public int modifyEcAuctionMainDelayNum(EcAuctionMain t) {
		return this.ecAuctionMainDao.updateEcAuctionMainDelayNum(t);
	}
}
