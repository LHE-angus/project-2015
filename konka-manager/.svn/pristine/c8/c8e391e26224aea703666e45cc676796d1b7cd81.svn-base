package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.GlobalIpGeoLibDao;
import com.ebiz.mmt.domain.GlobalIpGeoLib;
import com.ebiz.mmt.service.GlobalIpGeoLibService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2012-07-05 14:22:07
 */
@Service
public class GlobalIpGeoLibServiceImpl implements GlobalIpGeoLibService {

	@Resource
	private GlobalIpGeoLibDao globalIpGeoLibDao;

	public Long createGlobalIpGeoLib(GlobalIpGeoLib t) {
		return this.globalIpGeoLibDao.insertEntity(t);
	}

	public GlobalIpGeoLib getGlobalIpGeoLib(GlobalIpGeoLib t) {
		return this.globalIpGeoLibDao.selectEntity(t);
	}

	public Long getGlobalIpGeoLibCount(GlobalIpGeoLib t) {
		return this.globalIpGeoLibDao.selectEntityCount(t);
	}

	public List<GlobalIpGeoLib> getGlobalIpGeoLibList(GlobalIpGeoLib t) {
		return this.globalIpGeoLibDao.selectEntityList(t);
	}

	public int modifyGlobalIpGeoLib(GlobalIpGeoLib t) {
		return this.globalIpGeoLibDao.updateEntity(t);
	}

	public int removeGlobalIpGeoLib(GlobalIpGeoLib t) {
		return this.globalIpGeoLibDao.deleteEntity(t);
	}

	public List<GlobalIpGeoLib> getGlobalIpGeoLibPaginatedList(GlobalIpGeoLib t) {
		return this.globalIpGeoLibDao.selectEntityPaginatedList(t);
	}

	/**
	 * @author Hu,Hao
	 * @version 2012-07-05
	 */
	public List<GlobalIpGeoLib> getGlobalIpGeoLibForIndexList(GlobalIpGeoLib t) {
		return this.globalIpGeoLibDao.selectGlobalIpGeoLibForIndexList(t);
	}

}
