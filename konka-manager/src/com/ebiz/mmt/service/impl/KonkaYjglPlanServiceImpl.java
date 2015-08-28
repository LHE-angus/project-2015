package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaYjglPlanDao;
import com.ebiz.mmt.domain.KonkaYjglPlan;
import com.ebiz.mmt.service.KonkaYjglPlanService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-11-03 14:08:34
 */
@Service
public class KonkaYjglPlanServiceImpl implements KonkaYjglPlanService {

	@Resource
	private KonkaYjglPlanDao konkaYjglPlanDao;

	public Long createKonkaYjglPlan(KonkaYjglPlan t) {
		return this.konkaYjglPlanDao.insertEntity(t);
	}

	public KonkaYjglPlan getKonkaYjglPlan(KonkaYjglPlan t) {
		return this.konkaYjglPlanDao.selectEntity(t);
	}

	public Long getKonkaYjglPlanCount(KonkaYjglPlan t) {
		return this.konkaYjglPlanDao.selectEntityCount(t);
	}

	public List<KonkaYjglPlan> getKonkaYjglPlanList(KonkaYjglPlan t) {
		return this.konkaYjglPlanDao.selectEntityList(t);
	}

	public int modifyKonkaYjglPlan(KonkaYjglPlan t) {
		return this.konkaYjglPlanDao.updateEntity(t);
	}

	public int removeKonkaYjglPlan(KonkaYjglPlan t) {
		return this.konkaYjglPlanDao.deleteEntity(t);
	}

	public List<KonkaYjglPlan> getKonkaYjglPlanPaginatedList(KonkaYjglPlan t) {
		return this.konkaYjglPlanDao.selectEntityPaginatedList(t);
	}

	@Override
	public Long getKonkaYjglPlanAndDeptNameCount(KonkaYjglPlan t) {
		return this.konkaYjglPlanDao.selectKonkaYjglPlanAndDeptNameCount(t);
	}

	@Override
	public List<KonkaYjglPlan> getKonkaYjglPlanAndDeptNamePaginatedList(KonkaYjglPlan t) {
		return this.konkaYjglPlanDao.selectKonkaYjglPlanAndDeptNamePaginatedList(t);
	}

}
