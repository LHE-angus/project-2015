package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaMobileVisitPlanDao;
import com.ebiz.mmt.domain.KonkaMobileVisitPlan;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-07-01 15:01:19
 */
@Service
public class KonkaMobileVisitPlanDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaMobileVisitPlan> implements KonkaMobileVisitPlanDao {

	@Override
	public Long selectKonkaMobileVisitPlanLBCount(KonkaMobileVisitPlan v) {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectKonkaMobileVisitPlanLBCount", v);
	}

	@Override
	public List<KonkaMobileVisitPlan> selectKonkaMobileVisitPlanLBPaginatedList(
			KonkaMobileVisitPlan v) {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaMobileVisitPlanLBPaginatedList", v);
	}

	@Override
	public Long selectCurrentMonthVisitCount(KonkaMobileVisitPlan v) {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectCurrentMonthVisitCount", v);
	}

}
