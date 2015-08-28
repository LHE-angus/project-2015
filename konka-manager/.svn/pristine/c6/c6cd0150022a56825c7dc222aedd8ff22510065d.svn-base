package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcCustRelUserDao;
import com.ebiz.mmt.domain.EcCustRelUser;
import com.ebiz.mmt.service.EcCustRelUserService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2015-01-26 17:26:50
 */
@Service
public class EcCustRelUserServiceImpl implements EcCustRelUserService {

	@Resource
	private EcCustRelUserDao ecCustRelUserDao;
	

	public Long createEcCustRelUser(EcCustRelUser t) {
		return this.ecCustRelUserDao.insertEntity(t);
	}

	public EcCustRelUser getEcCustRelUser(EcCustRelUser t) {
		return this.ecCustRelUserDao.selectEntity(t);
	}

	public Long getEcCustRelUserCount(EcCustRelUser t) {
		return this.ecCustRelUserDao.selectEntityCount(t);
	}

	public List<EcCustRelUser> getEcCustRelUserList(EcCustRelUser t) {
		return this.ecCustRelUserDao.selectEntityList(t);
	}

	public int modifyEcCustRelUser(EcCustRelUser t) {
		return this.ecCustRelUserDao.updateEntity(t);
	}

	public int removeEcCustRelUser(EcCustRelUser t) {
		return this.ecCustRelUserDao.deleteEntity(t);
	}

	public List<EcCustRelUser> getEcCustRelUserPaginatedList(EcCustRelUser t) {
		return this.ecCustRelUserDao.selectEntityPaginatedList(t);
	}

}
