package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.StdEntpProdJoinBrandIdDao;
import com.ebiz.mmt.domain.StdEntpProdJoinBrandId;
import com.ebiz.mmt.service.StdEntpProdJoinBrandIdService;

/**
 * @author Wu,Yang
 * @version 2010-10-25 11:39:12
 */
@Service
public class StdEntpProdJoinBrandIdServiceImpl implements StdEntpProdJoinBrandIdService {

	@Resource
	private StdEntpProdJoinBrandIdDao stdEntpProdJoinBrandIdDao;

	public Long createStdEntpProdJoinBrandId(StdEntpProdJoinBrandId t) {
		return this.stdEntpProdJoinBrandIdDao.insertEntity(t);
	}

	public int modifyStdEntpProdJoinBrandId(StdEntpProdJoinBrandId t) {
		return this.stdEntpProdJoinBrandIdDao.updateEntity(t);
	}

	public int removeStdEntpProdJoinBrandId(StdEntpProdJoinBrandId t) {
		return this.stdEntpProdJoinBrandIdDao.deleteEntity(t);
	}

	public StdEntpProdJoinBrandId getStdEntpProdJoinBrandId(StdEntpProdJoinBrandId t) {
		return this.stdEntpProdJoinBrandIdDao.selectEntity(t);
	}

	public Long getStdEntpProdJoinBrandIdCount(StdEntpProdJoinBrandId t) {
		return this.stdEntpProdJoinBrandIdDao.selectEntityCount(t);
	}

	public List<StdEntpProdJoinBrandId> getStdEntpProdJoinBrandIdList(StdEntpProdJoinBrandId t) {
		return this.stdEntpProdJoinBrandIdDao.selectEntityList(t);
	}

	public List<StdEntpProdJoinBrandId> getStdEntpProdJoinBrandIdPaginatedList(StdEntpProdJoinBrandId t) {
		return this.stdEntpProdJoinBrandIdDao.selectEntityPaginatedList(t);
	}

	/**
	 * @author Du,HongGang
	 * @version 2010-10-26
	 */
	public int removeStdEntpProdJoinBrandIdByEntpIdOrBrandId(StdEntpProdJoinBrandId t) {
		return this.stdEntpProdJoinBrandIdDao.deleteStdEntpProdJoinBrandIdByEntpIdOrBrandId(t);
	}

	/**
	 * @author Wu,ShangLong
	 * @version 2011-03-10
	 */
	public List<StdEntpProdJoinBrandId> getStdEntpProdJoinBrandIdWithEntpName(StdEntpProdJoinBrandId t) {
		return this.stdEntpProdJoinBrandIdDao.selectStdEntpProdJoinBrandIdWithEntpName(t);
	}
}
