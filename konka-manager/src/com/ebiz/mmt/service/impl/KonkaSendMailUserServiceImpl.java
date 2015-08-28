package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaSendMailUserDao;
import com.ebiz.mmt.domain.KonkaSendMailUser;
import com.ebiz.mmt.service.KonkaSendMailUserService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-11-02 13:27:08
 */
@Service
public class KonkaSendMailUserServiceImpl implements KonkaSendMailUserService {

	@Resource
	private KonkaSendMailUserDao konkaSendMailUserDao;
	

	public Long createKonkaSendMailUser(KonkaSendMailUser t) {
		return this.konkaSendMailUserDao.insertEntity(t);
	}

	public KonkaSendMailUser getKonkaSendMailUser(KonkaSendMailUser t) {
		return this.konkaSendMailUserDao.selectEntity(t);
	}

	public Long getKonkaSendMailUserCount(KonkaSendMailUser t) {
		return this.konkaSendMailUserDao.selectEntityCount(t);
	}

	public List<KonkaSendMailUser> getKonkaSendMailUserList(KonkaSendMailUser t) {
		return this.konkaSendMailUserDao.selectEntityList(t);
	}

	public int modifyKonkaSendMailUser(KonkaSendMailUser t) {
		return this.konkaSendMailUserDao.updateEntity(t);
	}

	public int removeKonkaSendMailUser(KonkaSendMailUser t) {
		return this.konkaSendMailUserDao.deleteEntity(t);
	}

	public List<KonkaSendMailUser> getKonkaSendMailUserPaginatedList(KonkaSendMailUser t) {
		return this.konkaSendMailUserDao.selectEntityPaginatedList(t);
	}

}
