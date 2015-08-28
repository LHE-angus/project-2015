package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaXxZmdSpPlanResDao;
import com.ebiz.mmt.domain.KonkaXxZmdSpPlanRes;
import com.ebiz.mmt.service.KonkaXxZmdSpPlanResService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-04-19 09:52:53
 */
@Service
public class KonkaXxZmdSpPlanResServiceImpl implements KonkaXxZmdSpPlanResService {

	@Resource
	private KonkaXxZmdSpPlanResDao konkaXxZmdSpPlanResDao;
	

	public Long createKonkaXxZmdSpPlanRes(KonkaXxZmdSpPlanRes t) {
		return this.konkaXxZmdSpPlanResDao.insertEntity(t);
	}

	public KonkaXxZmdSpPlanRes getKonkaXxZmdSpPlanRes(KonkaXxZmdSpPlanRes t) {
		return this.konkaXxZmdSpPlanResDao.selectEntity(t);
	}

	public Long getKonkaXxZmdSpPlanResCount(KonkaXxZmdSpPlanRes t) {
		return this.konkaXxZmdSpPlanResDao.selectEntityCount(t);
	}

	public List<KonkaXxZmdSpPlanRes> getKonkaXxZmdSpPlanResList(KonkaXxZmdSpPlanRes t) {
		return this.konkaXxZmdSpPlanResDao.selectEntityList(t);
	}

	public int modifyKonkaXxZmdSpPlanRes(KonkaXxZmdSpPlanRes t) {
		return this.konkaXxZmdSpPlanResDao.updateEntity(t);
	}

	public int removeKonkaXxZmdSpPlanRes(KonkaXxZmdSpPlanRes t) {
		return this.konkaXxZmdSpPlanResDao.deleteEntity(t);
	}

	public List<KonkaXxZmdSpPlanRes> getKonkaXxZmdSpPlanResPaginatedList(KonkaXxZmdSpPlanRes t) {
		return this.konkaXxZmdSpPlanResDao.selectEntityPaginatedList(t);
	}

}
