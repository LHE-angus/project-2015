package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.SsoOaUserGroupDao;
import com.ebiz.mmt.domain.SsoOaUserGroup;
import com.ebiz.mmt.service.SsoOaUserGroupService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-11-24 15:46:32
 */
@Service
public class SsoOaUserGroupServiceImpl implements SsoOaUserGroupService {

	@Resource
	private SsoOaUserGroupDao ssoOaUserGroupDao;
	

	public Long createSsoOaUserGroup(SsoOaUserGroup t) {
		return this.ssoOaUserGroupDao.insertEntity(t);
	}

	public SsoOaUserGroup getSsoOaUserGroup(SsoOaUserGroup t) {
		return this.ssoOaUserGroupDao.selectEntity(t);
	}

	public Long getSsoOaUserGroupCount(SsoOaUserGroup t) {
		return this.ssoOaUserGroupDao.selectEntityCount(t);
	}

	public List<SsoOaUserGroup> getSsoOaUserGroupList(SsoOaUserGroup t) {
		return this.ssoOaUserGroupDao.selectEntityList(t);
	}

	public int modifySsoOaUserGroup(SsoOaUserGroup t) {
		return this.ssoOaUserGroupDao.updateEntity(t);
	}

	public int removeSsoOaUserGroup(SsoOaUserGroup t) {
		return this.ssoOaUserGroupDao.deleteEntity(t);
	}

	public List<SsoOaUserGroup> getSsoOaUserGroupPaginatedList(SsoOaUserGroup t) {
		return this.ssoOaUserGroupDao.selectEntityPaginatedList(t);
	}

}
