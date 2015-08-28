package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaMobileVisitPlanDetailDao;
import com.ebiz.mmt.domain.KonkaMobileVisitPlanDetail;
import com.ebiz.mmt.service.KonkaMobileVisitPlanDetailService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-07-01 15:01:19
 */
@Service
public class KonkaMobileVisitPlanDetailServiceImpl implements KonkaMobileVisitPlanDetailService {

	@Resource
	private KonkaMobileVisitPlanDetailDao konkaMobileVisitPlanDetailDao;
	

	public Long createKonkaMobileVisitPlanDetail(KonkaMobileVisitPlanDetail t) {
		return this.konkaMobileVisitPlanDetailDao.insertEntity(t);
	}

	public KonkaMobileVisitPlanDetail getKonkaMobileVisitPlanDetail(KonkaMobileVisitPlanDetail t) {
		return this.konkaMobileVisitPlanDetailDao.selectEntity(t);
	}

	public Long getKonkaMobileVisitPlanDetailCount(KonkaMobileVisitPlanDetail t) {
		return this.konkaMobileVisitPlanDetailDao.selectEntityCount(t);
	}

	public List<KonkaMobileVisitPlanDetail> getKonkaMobileVisitPlanDetailList(KonkaMobileVisitPlanDetail t) {
		return this.konkaMobileVisitPlanDetailDao.selectEntityList(t);
	}

	public int modifyKonkaMobileVisitPlanDetail(KonkaMobileVisitPlanDetail t) {
		return this.konkaMobileVisitPlanDetailDao.updateEntity(t);
	}

	public int removeKonkaMobileVisitPlanDetail(KonkaMobileVisitPlanDetail t) {
		return this.konkaMobileVisitPlanDetailDao.deleteEntity(t);
	}

	public List<KonkaMobileVisitPlanDetail> getKonkaMobileVisitPlanDetailPaginatedList(KonkaMobileVisitPlanDetail t) {
		return this.konkaMobileVisitPlanDetailDao.selectEntityPaginatedList(t);
	}

}
