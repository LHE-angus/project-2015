package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaMobileVisitPlanDao;
import com.ebiz.mmt.dao.KonkaMobileVisitPlanDetailDao;
import com.ebiz.mmt.domain.KonkaMobileVisitPlan;
import com.ebiz.mmt.domain.KonkaMobileVisitPlanDetail;
import com.ebiz.mmt.service.KonkaMobileVisitPlanService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-07-01 15:01:19
 */
@Service
public class KonkaMobileVisitPlanServiceImpl implements KonkaMobileVisitPlanService {

	@Resource
	private KonkaMobileVisitPlanDao konkaMobileVisitPlanDao;
	@Resource
	private KonkaMobileVisitPlanDetailDao konkaMobileVisitPlanDetailDao;

	@Override
	public Long createKonkaMobileVisitPlan(KonkaMobileVisitPlan t) {
		return this.konkaMobileVisitPlanDao.insertEntity(t);
	}

	@Override
	public KonkaMobileVisitPlan getKonkaMobileVisitPlan(KonkaMobileVisitPlan t) {
		return this.konkaMobileVisitPlanDao.selectEntity(t);
	}

	@Override
	public Long getKonkaMobileVisitPlanCount(KonkaMobileVisitPlan t) {
		return this.konkaMobileVisitPlanDao.selectEntityCount(t);
	}

	@Override
	public List<KonkaMobileVisitPlan> getKonkaMobileVisitPlanList(KonkaMobileVisitPlan t) {
		return this.konkaMobileVisitPlanDao.selectEntityList(t);
	}

	@Override
	public int modifyKonkaMobileVisitPlan(KonkaMobileVisitPlan t) {
		return this.konkaMobileVisitPlanDao.updateEntity(t);
	}

	@Override
	public int removeKonkaMobileVisitPlan(KonkaMobileVisitPlan t) {
		return this.konkaMobileVisitPlanDao.deleteEntity(t);
	}

	@Override
	public List<KonkaMobileVisitPlan> getKonkaMobileVisitPlanPaginatedList(KonkaMobileVisitPlan t) {
		return this.konkaMobileVisitPlanDao.selectEntityPaginatedList(t);
	}

	@Override
	public Long getKonkaMobileVisitPlanLBCount(KonkaMobileVisitPlan v) {
		return this.konkaMobileVisitPlanDao.selectKonkaMobileVisitPlanLBCount(v);
	}

	@Override
	public List<KonkaMobileVisitPlan> getKonkaMobileVisitPlanLBPaginatedList(
			KonkaMobileVisitPlan v) {
		
		return this.konkaMobileVisitPlanDao.selectKonkaMobileVisitPlanLBPaginatedList(v);
	}

	@Override
	public Long createKonkaMobileVisitPlanJL(KonkaMobileVisitPlan t) {
		Long plan_id=this.konkaMobileVisitPlanDao.insertEntity(t);
		List<KonkaMobileVisitPlanDetail> listdetail=t.getDetail();
		if (null!=listdetail) {
			for (KonkaMobileVisitPlanDetail konkaMobileVisitPlanDetail : listdetail) {
				konkaMobileVisitPlanDetail.setPlan_id(plan_id);
			    this.konkaMobileVisitPlanDetailDao.insertEntity(konkaMobileVisitPlanDetail);
			}
		}
		return plan_id;
	}

	@Override
	public Long modifyKonkaMobileVisitPlanJL(KonkaMobileVisitPlan t) {
		this.konkaMobileVisitPlanDao.updateEntity(t);
		KonkaMobileVisitPlanDetail detail=new KonkaMobileVisitPlanDetail();
		detail.setPlan_id(t.getPlan_id());
		//删除之前关联的的详情数据
		this.konkaMobileVisitPlanDetailDao.deleteEntity(detail);
		//添加最新的的详情数据
		List<KonkaMobileVisitPlanDetail> listdetail=t.getDetail();
		if (null!=listdetail) {
			for (KonkaMobileVisitPlanDetail konkaMobileVisitPlanDetail : listdetail) {
				konkaMobileVisitPlanDetail.setPlan_id(t.getPlan_id());
				this.konkaMobileVisitPlanDetailDao.insertEntity(konkaMobileVisitPlanDetail);
			}
		}
		return t.getPlan_id();
	}

	@Override
	public int removeKonkaMobileVisitPlanLB(KonkaMobileVisitPlan t) {
		/*KonkaMobileVisitPlanDetail entity=new KonkaMobileVisitPlanDetail();
		entity.setPlan_id(t.getPlan_id());
		this.konkaMobileVisitPlanDetailDao.deleteEntity(entity);*/
		t.setIs_del(1);
		int num=this.konkaMobileVisitPlanDao.updateEntity(t);
		return num;
	}
    /**
     * 查询当前年月是否做过拜访计划
     */
	@Override
	public Long getCurrentMonthVisitCount(KonkaMobileVisitPlan v) {
		return this.konkaMobileVisitPlanDao.selectCurrentMonthVisitCount(v);
	}

}
