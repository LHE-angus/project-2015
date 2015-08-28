package com.ebiz.mmt.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaUserMobileSetPlanDao;
import com.ebiz.mmt.domain.KonkaUserMobileSetPlan;
import com.ebiz.mmt.service.KonkaUserMobileSetPlanService;


/**
 * @author Cheng,Bing
 * @version 2012-01-09
 */
@Service
public class KonkaUserMobileSetPlanServiceImpl implements KonkaUserMobileSetPlanService {

	@Resource
	private KonkaUserMobileSetPlanDao konkaUserMobileSetPlanDao;

	public long createKonkaUserMobileSetPlan(KonkaUserMobileSetPlan t) {
		return this.konkaUserMobileSetPlanDao.insertEntity(t);
	}

	public long modifyKonkaUserMobileSetPlan(KonkaUserMobileSetPlan t) {
		return this.konkaUserMobileSetPlanDao.updateEntity(t);
	}

	public long removeKonkaUserMobileSetPlan(KonkaUserMobileSetPlan t) {
		return this.konkaUserMobileSetPlanDao.deleteEntity(t);
	}

	public KonkaUserMobileSetPlan getKonkaUserMobileSetPlan(KonkaUserMobileSetPlan t) {
		return this.konkaUserMobileSetPlanDao.selectEntity(t);
	}

	public long getKonkaUserMobileSetPlanCount(KonkaUserMobileSetPlan t) {
		return this.konkaUserMobileSetPlanDao.selectEntityCount(t);
	}

	public List<KonkaUserMobileSetPlan> getKonkaUserMobileSetPlanList(KonkaUserMobileSetPlan t) {
		return this.konkaUserMobileSetPlanDao.selectEntityList(t);
	}

	public List<KonkaUserMobileSetPlan> getKonkaUserMobileSetPlanPaginatedList(KonkaUserMobileSetPlan t) {
		return this.konkaUserMobileSetPlanDao.selectEntityPaginatedList(t);
	}
}
