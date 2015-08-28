package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcBasePayAccountDao;
import com.ebiz.mmt.domain.EcBasePayAccount;
import com.ebiz.mmt.service.EcBasePayAccountService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-09-23 19:14:55
 */
@Service
public class EcBasePayAccountServiceImpl implements EcBasePayAccountService {

	@Resource
	private EcBasePayAccountDao ecBasePayAccountDao;
	

	public Long createEcBasePayAccount(EcBasePayAccount t) {
		return this.ecBasePayAccountDao.insertEntity(t);
	}

	public EcBasePayAccount getEcBasePayAccount(EcBasePayAccount t) {
		return this.ecBasePayAccountDao.selectEntity(t);
	}

	public Long getEcBasePayAccountCount(EcBasePayAccount t) {
		return this.ecBasePayAccountDao.selectEntityCount(t);
	}

	public List<EcBasePayAccount> getEcBasePayAccountList(EcBasePayAccount t) {
		return this.ecBasePayAccountDao.selectEntityList(t);
	}

	public int modifyEcBasePayAccount(EcBasePayAccount t) {
		return this.ecBasePayAccountDao.updateEntity(t);
	}

	public int removeEcBasePayAccount(EcBasePayAccount t) {
		return this.ecBasePayAccountDao.deleteEntity(t);
	}

	public List<EcBasePayAccount> getEcBasePayAccountPaginatedList(EcBasePayAccount t) {
		return this.ecBasePayAccountDao.selectEntityPaginatedList(t);
	}

}
