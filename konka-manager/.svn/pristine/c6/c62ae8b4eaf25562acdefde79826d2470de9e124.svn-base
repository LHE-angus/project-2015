package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaInterfaceBindsUserDao;
import com.ebiz.mmt.domain.KonkaInterfaceBindsUser;
import com.ebiz.mmt.service.KonkaInterfaceBindsUserService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-09-22 14:12:24
 */
@Service
public class KonkaInterfaceBindsUserServiceImpl implements KonkaInterfaceBindsUserService {

	@Resource
	private KonkaInterfaceBindsUserDao konkaInterfaceBindsUserDao;
	

	public Long createKonkaInterfaceBindsUser(KonkaInterfaceBindsUser t) {
		return this.konkaInterfaceBindsUserDao.insertEntity(t);
	}

	public KonkaInterfaceBindsUser getKonkaInterfaceBindsUser(KonkaInterfaceBindsUser t) {
		return this.konkaInterfaceBindsUserDao.selectEntity(t);
	}

	public Long getKonkaInterfaceBindsUserCount(KonkaInterfaceBindsUser t) {
		return this.konkaInterfaceBindsUserDao.selectEntityCount(t);
	}

	public List<KonkaInterfaceBindsUser> getKonkaInterfaceBindsUserList(KonkaInterfaceBindsUser t) {
		return this.konkaInterfaceBindsUserDao.selectEntityList(t);
	}

	public int modifyKonkaInterfaceBindsUser(KonkaInterfaceBindsUser t) {
		return this.konkaInterfaceBindsUserDao.updateEntity(t);
	}

	public int removeKonkaInterfaceBindsUser(KonkaInterfaceBindsUser t) {
		return this.konkaInterfaceBindsUserDao.deleteEntity(t);
	}

	public List<KonkaInterfaceBindsUser> getKonkaInterfaceBindsUserPaginatedList(KonkaInterfaceBindsUser t) {
		return this.konkaInterfaceBindsUserDao.selectEntityPaginatedList(t);
	}

}
