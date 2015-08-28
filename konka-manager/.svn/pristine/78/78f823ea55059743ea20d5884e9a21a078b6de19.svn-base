package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaXxZmdRewardSetHdDao;
import com.ebiz.mmt.domain.KonkaXxZmdRewardSetHd;
import com.ebiz.mmt.service.KonkaXxZmdRewardSetHdService;

/**
 * @author Xu,XiaoYuan
 * @version 2012-03-02 09:12
 */
@Service
public class KonkaXxZmdRewardSetHdServiceImpl implements KonkaXxZmdRewardSetHdService {

	@Resource
	private KonkaXxZmdRewardSetHdDao konkaXxZmdRewardSetHdDao;

	public Long createKonkaXxZmdRewardSetHd(KonkaXxZmdRewardSetHd t) {
		return this.konkaXxZmdRewardSetHdDao.insertEntity(t);
	}

	public KonkaXxZmdRewardSetHd getKonkaXxZmdRewardSetHd(KonkaXxZmdRewardSetHd t) {
		return this.konkaXxZmdRewardSetHdDao.selectEntity(t);
	}

	public Long getKonkaXxZmdRewardSetHdCount(KonkaXxZmdRewardSetHd t) {
		return this.konkaXxZmdRewardSetHdDao.selectEntityCount(t);
	}

	public List<KonkaXxZmdRewardSetHd> getKonkaXxZmdRewardSetHdList(KonkaXxZmdRewardSetHd t) {
		return this.konkaXxZmdRewardSetHdDao.selectEntityList(t);
	}

	public int modifyKonkaXxZmdRewardSetHd(KonkaXxZmdRewardSetHd t) {
		return this.konkaXxZmdRewardSetHdDao.updateEntity(t);
	}

	public int removeKonkaXxZmdRewardSetHd(KonkaXxZmdRewardSetHd t) {
		return this.konkaXxZmdRewardSetHdDao.deleteEntity(t);
	}

	public List<KonkaXxZmdRewardSetHd> getKonkaXxZmdRewardSetHdPaginatedList(KonkaXxZmdRewardSetHd t) {
		return this.konkaXxZmdRewardSetHdDao.selectEntityPaginatedList(t);
	}

	/**
	 * @author Wu,ShangLong
	 * @version 2012-03-05
	 */
	public Long getKonkaXxZmdRewardSetHdWithZmdAndHdCount(KonkaXxZmdRewardSetHd t) {
		return this.konkaXxZmdRewardSetHdDao.selectKonkaXxZmdRewardSetHdWithZmdAndHdCount(t);
	}

	/**
	 * @author Wu,ShangLong
	 * @version 2012-03-05
	 */
	public List<KonkaXxZmdRewardSetHd> getKonkaXxZmdRewardSetHdWithZmdAndHdPaginatedList(KonkaXxZmdRewardSetHd t) {
		return this.konkaXxZmdRewardSetHdDao.selectKonkaXxZmdRewardSetHdWithZmdAndHdPaginatedList(t);
	}

}
