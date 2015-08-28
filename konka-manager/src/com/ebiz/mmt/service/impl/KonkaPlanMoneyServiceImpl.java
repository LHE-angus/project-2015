package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaPlanMoneyDao;
import com.ebiz.mmt.domain.KonkaPlanMoney;
import com.ebiz.mmt.service.KonkaPlanMoneyService;

/**
 * @author Liu,ZhiXiang
 * @version 2013-7-9
 * @desc 任务系数管理
 */
@Service
public class KonkaPlanMoneyServiceImpl implements KonkaPlanMoneyService {

	@Resource
	private KonkaPlanMoneyDao konkaPlanMoneyDao;

	public Long createKonkaPlanMoney(KonkaPlanMoney t) {
		return this.konkaPlanMoneyDao.insertEntity(t);
	}

	public KonkaPlanMoney getKonkaPlanMoney(KonkaPlanMoney t) {
		return this.konkaPlanMoneyDao.selectEntity(t);
	}

	public Long getKonkaPlanMoneyCount(KonkaPlanMoney t) {
		return this.konkaPlanMoneyDao.selectEntityCount(t);
	}

	public List<KonkaPlanMoney> getKonkaPlanMoneyList(KonkaPlanMoney t) {
		return this.konkaPlanMoneyDao.selectEntityList(t);
	}

	public int modifyKonkaPlanMoney(KonkaPlanMoney t) {
		return this.konkaPlanMoneyDao.updateEntity(t);
	}

	public int removeKonkaPlanMoney(KonkaPlanMoney t) {
		return this.konkaPlanMoneyDao.deleteEntity(t);
	}

	public List<KonkaPlanMoney> getKonkaPlanMoneyPaginatedList(KonkaPlanMoney t) {
		return this.konkaPlanMoneyDao.selectEntityPaginatedList(t);
	}

	public List<KonkaPlanMoney> getKonkaPlanMoneyListForYear(KonkaPlanMoney t) {
		return this.konkaPlanMoneyDao.selectKonkaPlanMoneyListForYear(t);
	}

	public Long createKonkaPlanMoneyForTotal(KonkaPlanMoney t, List<KonkaPlanMoney> list) {
		KonkaPlanMoney entity = new KonkaPlanMoney();
		entity.setP(1);
		entity.getMap().put("year", t.getY());
		this.konkaPlanMoneyDao.deleteEntity(entity);
		if (null != list && list.size() > 0) {
			for (KonkaPlanMoney kpm : list) {
				this.konkaPlanMoneyDao.insertEntity(kpm);
			}
		}
		return this.konkaPlanMoneyDao.insertEntity(t);
	}

	public int modifyKonkaPlanMoneyForTotal(KonkaPlanMoney t, List<KonkaPlanMoney> list) {
		KonkaPlanMoney entity = new KonkaPlanMoney();
		entity.setP(1);
		entity.getMap().put("year", t.getY());
		this.konkaPlanMoneyDao.deleteEntity(entity);
		if (null != list && list.size() > 0) {
			for (KonkaPlanMoney kpm : list) {
				this.konkaPlanMoneyDao.insertEntity(kpm);
			}
		}

		return this.konkaPlanMoneyDao.updateEntity(t);
	}

}
