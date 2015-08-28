package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaEmFileReceiveUserDao;
import com.ebiz.mmt.domain.KonkaEmFileReceiveUser;
import com.ebiz.mmt.service.KonkaEmFileReceiveUserService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-09-27 17:08:18
 */
@Service
public class KonkaEmFileReceiveUserServiceImpl implements KonkaEmFileReceiveUserService {

	@Resource
	private KonkaEmFileReceiveUserDao konkaEmFileReceiveUserDao;
	

	public Long createKonkaEmFileReceiveUser(KonkaEmFileReceiveUser t) {
		return this.konkaEmFileReceiveUserDao.insertEntity(t);
	}

	public KonkaEmFileReceiveUser getKonkaEmFileReceiveUser(KonkaEmFileReceiveUser t) {
		return this.konkaEmFileReceiveUserDao.selectEntity(t);
	}

	public Long getKonkaEmFileReceiveUserCount(KonkaEmFileReceiveUser t) {
		return this.konkaEmFileReceiveUserDao.selectEntityCount(t);
	}

	public List<KonkaEmFileReceiveUser> getKonkaEmFileReceiveUserList(KonkaEmFileReceiveUser t) {
		return this.konkaEmFileReceiveUserDao.selectEntityList(t);
	}

	public int modifyKonkaEmFileReceiveUser(KonkaEmFileReceiveUser t) {
		return this.konkaEmFileReceiveUserDao.updateEntity(t);
	}

	public int removeKonkaEmFileReceiveUser(KonkaEmFileReceiveUser t) {
		return this.konkaEmFileReceiveUserDao.deleteEntity(t);
	}

	public List<KonkaEmFileReceiveUser> getKonkaEmFileReceiveUserPaginatedList(KonkaEmFileReceiveUser t) {
		return this.konkaEmFileReceiveUserDao.selectEntityPaginatedList(t);
	}

}
