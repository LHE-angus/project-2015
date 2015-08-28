package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.StdEntpLoginDao;
import com.ebiz.mmt.domain.StdEntpLogin;
import com.ebiz.mmt.service.StdEntpLoginService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2010-12-23 10:47:02
 */
@Service
public class StdEntpLoginServiceImpl implements StdEntpLoginService {

	@Resource
	private StdEntpLoginDao stdEntpLoginDao;

	public Long createStdEntpLogin(StdEntpLogin t) {
		return this.stdEntpLoginDao.insertEntity(t);
	}

	public StdEntpLogin getStdEntpLogin(StdEntpLogin t) {
		return this.stdEntpLoginDao.selectEntity(t);
	}

	public Long getStdEntpLoginCount(StdEntpLogin t) {
		return this.stdEntpLoginDao.selectEntityCount(t);
	}

	public List<StdEntpLogin> getStdEntpLoginList(StdEntpLogin t) {
		return this.stdEntpLoginDao.selectEntityList(t);
	}

	public int modifyStdEntpLogin(StdEntpLogin t) {
		return this.stdEntpLoginDao.updateEntity(t);
	}

	public int removeStdEntpLogin(StdEntpLogin t) {
		return this.stdEntpLoginDao.deleteEntity(t);
	}

	public List<StdEntpLogin> getStdEntpLoginPaginatedList(StdEntpLogin t) {
		return this.stdEntpLoginDao.selectEntityPaginatedList(t);
	}

	public List<StdEntpLogin> getStdEntpLoginGroupByVersionList(StdEntpLogin t) {
		return this.stdEntpLoginDao.selectStdEntpLoginGroupByVersionList(t);
	}

	public List<StdEntpLogin> getEveryDayLoginShopCountForResultList(StdEntpLogin t) {
		return this.stdEntpLoginDao.selectEveryDayLoginShopCountForResultList(t);
	}

	/**
	 * @author Wu,ShangLong
	 * @version 2011-07-06
	 */
	public List<StdEntpLogin> getStdEntpLoginNumPerDayList(StdEntpLogin t) {
		return this.stdEntpLoginDao.selectStdEntpLoginNumPerDayList(t);
	}

}
