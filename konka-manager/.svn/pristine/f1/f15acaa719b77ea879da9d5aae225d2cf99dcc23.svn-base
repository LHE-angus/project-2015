package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.StdEntpProdDao;
import com.ebiz.mmt.domain.StdEntpProd;
import com.ebiz.mmt.service.StdEntpProdService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2010-10-11 11:51:17
 */
@Service
public class StdEntpProdServiceImpl implements StdEntpProdService {

	@Resource
	private StdEntpProdDao stdEntpProdDao;

	public Long createStdEntpProd(StdEntpProd t) {
		return this.stdEntpProdDao.insertEntity(t);
	}

	public StdEntpProd getStdEntpProd(StdEntpProd t) {
		return this.stdEntpProdDao.selectEntity(t);
	}

	public Long getStdEntpProdCount(StdEntpProd t) {
		return this.stdEntpProdDao.selectEntityCount(t);
	}

	public List<StdEntpProd> getStdEntpProdList(StdEntpProd t) {
		return this.stdEntpProdDao.selectEntityList(t);
	}

	public int modifyStdEntpProd(StdEntpProd t) {
		return this.stdEntpProdDao.updateEntity(t);
	}

	public int removeStdEntpProd(StdEntpProd t) {
		return this.stdEntpProdDao.deleteEntity(t);
	}

	public List<StdEntpProd> getStdEntpProdPaginatedList(StdEntpProd t) {
		return this.stdEntpProdDao.selectEntityPaginatedList(t);
	}

	/**
	 * @author Du,HongGang
	 * @version 2010-10-25
	 */
	public List<StdEntpProd> getEntpProdForjoinBrandInfoList(StdEntpProd t) {
		return this.stdEntpProdDao.selectEntpProdForjoinBrandInfoList(t);
	}

	/**
	 * @author Du,HongGang
	 * @version 2010-10-26
	 */
	public StdEntpProd getEntpProdForjoinBrandInfo(StdEntpProd t) {
		return this.stdEntpProdDao.selectEntpProdForjoinBrandInfo(t);
	}

	/**
	 * @author Li,Yuan
	 * @version 2010-11-18
	 */
	public List<StdEntpProd> getStdEntpProdForLogoList(StdEntpProd t) {
		return this.stdEntpProdDao.selectStdEntpProdForLogoList(t);
	}

	/**
	 * @author Wu,ShangLong
	 * @version 2011-03-10
	 */
	public Long getStdEntpProdForjoinBrandAndEntpProdCount(StdEntpProd t) {
		return this.stdEntpProdDao.selectStdEntpProdForjoinBrandAndEntpProdCount(t);
	}

	/**
	 * @author Wu,ShangLong
	 * @version 2011-03-10
	 */
	public List<StdEntpProd> getStdEntpProdForjoinBrandAndEntpProdPaginatedList(StdEntpProd t) {
		return this.stdEntpProdDao.selectStdEntpProdForjoinBrandAndEntpProdPaginatedList(t);
	}

	/**
	 * @author Wu,ShangLong
	 * @version 2011-03-10
	 */
	public Long getStdEntpProdNotBrandIdCount(StdEntpProd t) {
		return this.stdEntpProdDao.selectStdEntpProdNotBrandIdCount(t);
	}

	/**
	 * @author Wu,ShangLong
	 * @version 2011-03-10
	 */
	public List<StdEntpProd> getStdEntpProdNotBrandIdList(StdEntpProd t) {
		return this.stdEntpProdDao.selectStdEntpProdNotBrandIdList(t);
	}
}
