package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.JxcUseNojdxxShopDao;
import com.ebiz.mmt.domain.JxcUseNojdxxShop;
import com.ebiz.mmt.service.JxcUseNojdxxShopService;

/**
 * @author Wu,Yang
 * @version 2011-09-26 15:03
 */
@Service
public class JxcUseNojdxxShopServiceImpl implements JxcUseNojdxxShopService {

	@Resource
	private JxcUseNojdxxShopDao jxcUseNojdxxShopDao;
	

	public Long createJxcUseNojdxxShop(JxcUseNojdxxShop t) {
		return this.jxcUseNojdxxShopDao.insertEntity(t);
	}

	public JxcUseNojdxxShop getJxcUseNojdxxShop(JxcUseNojdxxShop t) {
		return this.jxcUseNojdxxShopDao.selectEntity(t);
	}

	public Long getJxcUseNojdxxShopCount(JxcUseNojdxxShop t) {
		return this.jxcUseNojdxxShopDao.selectEntityCount(t);
	}

	public List<JxcUseNojdxxShop> getJxcUseNojdxxShopList(JxcUseNojdxxShop t) {
		return this.jxcUseNojdxxShopDao.selectEntityList(t);
	}

	public int modifyJxcUseNojdxxShop(JxcUseNojdxxShop t) {
		return this.jxcUseNojdxxShopDao.updateEntity(t);
	}

	public int removeJxcUseNojdxxShop(JxcUseNojdxxShop t) {
		return this.jxcUseNojdxxShopDao.deleteEntity(t);
	}

	public List<JxcUseNojdxxShop> getJxcUseNojdxxShopPaginatedList(JxcUseNojdxxShop t) {
		return this.jxcUseNojdxxShopDao.selectEntityPaginatedList(t);
	}

}
