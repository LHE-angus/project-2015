package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.OaUserGroupHDao;
import com.ebiz.mmt.domain.OaUserGroupH;
import com.ebiz.mmt.service.OaUserGroupHService;


@Service
public class OaUserGroupHServiceImpl implements OaUserGroupHService {

	@Resource
	private OaUserGroupHDao oaUserGroupHDao;
	

	public Long createOaUserGroupH(OaUserGroupH t) {
		return this.oaUserGroupHDao.insertEntity(t);
	}

	public OaUserGroupH getOaUserGroupH(OaUserGroupH t) {
		return this.oaUserGroupHDao.selectEntity(t);
	}

	public Long getOaUserGroupHCount(OaUserGroupH t) {
		return this.oaUserGroupHDao.selectEntityCount(t);
	}

	public List<OaUserGroupH> getOaUserGroupHList(OaUserGroupH t) {
		return this.oaUserGroupHDao.selectEntityList(t);
	}

	public int modifyOaUserGroupH(OaUserGroupH t) {
		return this.oaUserGroupHDao.updateEntity(t);
	}

	public int removeOaUserGroupH(OaUserGroupH t) {
		return this.oaUserGroupHDao.deleteEntity(t);
	}

	public List<OaUserGroupH> getOaUserGroupHPaginatedList(OaUserGroupH t) {
		return this.oaUserGroupHDao.selectEntityPaginatedList(t);
	}

}
