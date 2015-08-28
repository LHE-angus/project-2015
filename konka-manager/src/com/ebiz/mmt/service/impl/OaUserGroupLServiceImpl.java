package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.OaUserGroupLDao;
import com.ebiz.mmt.domain.OaUserGroupL;
import com.ebiz.mmt.service.OaUserGroupLService;


@Service
public class OaUserGroupLServiceImpl implements OaUserGroupLService {

	@Resource
	private OaUserGroupLDao oaUserGroupLDao;
	

	public Long createOaUserGroupL(OaUserGroupL t) {
		return this.oaUserGroupLDao.insertEntity(t);
	}

	public OaUserGroupL getOaUserGroupL(OaUserGroupL t) {
		return this.oaUserGroupLDao.selectEntity(t);
	}

	public Long getOaUserGroupLCount(OaUserGroupL t) {
		return this.oaUserGroupLDao.selectEntityCount(t);
	}

	public List<OaUserGroupL> getOaUserGroupLList(OaUserGroupL t) {
		return this.oaUserGroupLDao.selectEntityList(t);
	}

	public int modifyOaUserGroupL(OaUserGroupL t) {
		return this.oaUserGroupLDao.updateEntity(t);
	}

	public int removeOaUserGroupL(OaUserGroupL t) {
		return this.oaUserGroupLDao.deleteEntity(t);
	}

	public List<OaUserGroupL> getOaUserGroupLPaginatedList(OaUserGroupL t) {
		return this.oaUserGroupLDao.selectEntityPaginatedList(t);
	}

}
