package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.JxcShopOrgSnDao;
import com.ebiz.mmt.domain.JxcShopOrgSn;
import com.ebiz.mmt.service.JxcShopOrgSnService;

/**
 * @author Li,Ka
 * @version 2012-08-08 09:46
 */
@Service
public class JxcShopOrgSnServiceImpl implements JxcShopOrgSnService {

	@Resource
	private JxcShopOrgSnDao jxcShopOrgSnDao;
	

	public Long createJxcShopOrgSn(JxcShopOrgSn t) {
		return this.jxcShopOrgSnDao.insertEntity(t);
	}

	public JxcShopOrgSn getJxcShopOrgSn(JxcShopOrgSn t) {
		return this.jxcShopOrgSnDao.selectEntity(t);
	}

	public Long getJxcShopOrgSnCount(JxcShopOrgSn t) {
		return this.jxcShopOrgSnDao.selectEntityCount(t);
	}

	public List<JxcShopOrgSn> getJxcShopOrgSnList(JxcShopOrgSn t) {
		return this.jxcShopOrgSnDao.selectEntityList(t);
	}

	public int modifyJxcShopOrgSn(JxcShopOrgSn t) {
		return this.jxcShopOrgSnDao.updateEntity(t);
	}

	public int removeJxcShopOrgSn(JxcShopOrgSn t) {
		return this.jxcShopOrgSnDao.deleteEntity(t);
	}

	public List<JxcShopOrgSn> getJxcShopOrgSnPaginatedList(JxcShopOrgSn t) {
		return this.jxcShopOrgSnDao.selectEntityPaginatedList(t);
	}

}
