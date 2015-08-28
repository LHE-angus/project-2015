package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaXxZmdRewardSetPdDao;
import com.ebiz.mmt.domain.KonkaXxZmdRewardSetPd;
import com.ebiz.mmt.service.KonkaXxZmdRewardSetPdService;

/**
 * @author Xu,XiaoYuan
 * @version 2012-03-02 09:12
 */
@Service
public class KonkaXxZmdRewardSetPdServiceImpl implements KonkaXxZmdRewardSetPdService {

	@Resource
	private KonkaXxZmdRewardSetPdDao konkaXxZmdRewardSetPdDao;

	public Long createKonkaXxZmdRewardSetPd(KonkaXxZmdRewardSetPd t) {
		return this.konkaXxZmdRewardSetPdDao.insertEntity(t);
	}

	public KonkaXxZmdRewardSetPd getKonkaXxZmdRewardSetPd(KonkaXxZmdRewardSetPd t) {
		return this.konkaXxZmdRewardSetPdDao.selectEntity(t);
	}

	public Long getKonkaXxZmdRewardSetPdCount(KonkaXxZmdRewardSetPd t) {
		return this.konkaXxZmdRewardSetPdDao.selectEntityCount(t);
	}

	public List<KonkaXxZmdRewardSetPd> getKonkaXxZmdRewardSetPdList(KonkaXxZmdRewardSetPd t) {
		return this.konkaXxZmdRewardSetPdDao.selectEntityList(t);
	}

	public int modifyKonkaXxZmdRewardSetPd(KonkaXxZmdRewardSetPd t) {
		return this.konkaXxZmdRewardSetPdDao.updateEntity(t);
	}

	public int removeKonkaXxZmdRewardSetPd(KonkaXxZmdRewardSetPd t) {
		return this.konkaXxZmdRewardSetPdDao.deleteEntity(t);
	}

	public List<KonkaXxZmdRewardSetPd> getKonkaXxZmdRewardSetPdPaginatedList(KonkaXxZmdRewardSetPd t) {
		return this.konkaXxZmdRewardSetPdDao.selectEntityPaginatedList(t);
	}

	/**
	 * @author Wu,ShangLong
	 * @version 2012-03-02
	 */
	public Long getKonkaXxZmdRewardSetPdWithZmdSnCount(KonkaXxZmdRewardSetPd t) {
		return this.konkaXxZmdRewardSetPdDao.selectKonkaXxZmdRewardSetPdWithZmdSnCount(t);
	}

	/**
	 * @author Wu,ShangLong
	 * @version 2012-03-02
	 */
	public List<KonkaXxZmdRewardSetPd> getKonkaXxZmdRewardSetPdWithZmdSnPaginatedList(KonkaXxZmdRewardSetPd t) {
		return this.konkaXxZmdRewardSetPdDao.selectKonkaXxZmdRewardSetPdWithZmdSnPaginatedList(t);
	}

}
