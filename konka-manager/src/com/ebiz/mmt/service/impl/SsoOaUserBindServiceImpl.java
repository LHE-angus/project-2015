package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.SsoOaUserBindDao;
import com.ebiz.mmt.domain.SsoOaUserBind;
import com.ebiz.mmt.service.SsoOaUserBindService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-11-24 15:46:32
 */
@Service
public class SsoOaUserBindServiceImpl implements SsoOaUserBindService {

	@Resource
	private SsoOaUserBindDao ssoOaUserBindDao;
	

	public Long createSsoOaUserBind(SsoOaUserBind t) {
		return this.ssoOaUserBindDao.insertEntity(t);
	}

	public SsoOaUserBind getSsoOaUserBind(SsoOaUserBind t) {
		return this.ssoOaUserBindDao.selectEntity(t);
	}

	public Long getSsoOaUserBindCount(SsoOaUserBind t) {
		return this.ssoOaUserBindDao.selectEntityCount(t);
	}

	public List<SsoOaUserBind> getSsoOaUserBindList(SsoOaUserBind t) {
		return this.ssoOaUserBindDao.selectEntityList(t);
	}

	public int modifySsoOaUserBind(SsoOaUserBind t) {
		return this.ssoOaUserBindDao.updateEntity(t);
	}

	public int removeSsoOaUserBind(SsoOaUserBind t) {
		return this.ssoOaUserBindDao.deleteEntity(t);
	}

	public List<SsoOaUserBind> getSsoOaUserBindPaginatedList(SsoOaUserBind t) {
		return this.ssoOaUserBindDao.selectEntityPaginatedList(t);
	}

}
