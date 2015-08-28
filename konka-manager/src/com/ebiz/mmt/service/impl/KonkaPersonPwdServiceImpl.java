package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaPersonPwdDao;
import com.ebiz.mmt.domain.KonkaPersonPwd;
import com.ebiz.mmt.service.KonkaPersonPwdService;

/**
 * @author Ren,zhong
 * @version 2013-07-26 11:06
 */
@Service
public class KonkaPersonPwdServiceImpl implements KonkaPersonPwdService {

	@Resource
	private KonkaPersonPwdDao konkaPersonPwdDao;
	

	public Long createKonkaPersonPwd(KonkaPersonPwd t) {
		return this.konkaPersonPwdDao.insertEntity(t);
	}

	public KonkaPersonPwd getKonkaPersonPwd(KonkaPersonPwd t) {
		return this.konkaPersonPwdDao.selectEntity(t);
	}

	public Long getKonkaPersonPwdCount(KonkaPersonPwd t) {
		return this.konkaPersonPwdDao.selectEntityCount(t);
	}

	public List<KonkaPersonPwd> getKonkaPersonPwdList(KonkaPersonPwd t) {
		return this.konkaPersonPwdDao.selectEntityList(t);
	}

	public int modifyKonkaPersonPwd(KonkaPersonPwd t) {
		return this.konkaPersonPwdDao.updateEntity(t);
	}

	public int removeKonkaPersonPwd(KonkaPersonPwd t) {
		return this.konkaPersonPwdDao.deleteEntity(t);
	}

	public List<KonkaPersonPwd> getKonkaPersonPwdPaginatedList(KonkaPersonPwd t) {
		return this.konkaPersonPwdDao.selectEntityPaginatedList(t);
	}

	/**
	 * @author Ren,zhong
	 * @date 2013-07-30
	 */
	public int modifyKonkaPersonPwdLoginCount(KonkaPersonPwd t) {
		return this.konkaPersonPwdDao.updateKonkaPersonPwdLoginCount(t);
	}

}
