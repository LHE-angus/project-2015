package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaYjglPlanDao;
import com.ebiz.mmt.domain.KonkaYjglPlan;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-11-03 14:08:34
 */
@Service
public class KonkaYjglPlanDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaYjglPlan> implements KonkaYjglPlanDao {

	@Override
	public Long selectKonkaYjglPlanAndDeptNameCount(KonkaYjglPlan t) {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectKonkaYjglPlanAndDeptNameCount", t);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<KonkaYjglPlan> selectKonkaYjglPlanAndDeptNamePaginatedList(KonkaYjglPlan t) {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaYjglPlanAndDeptNamePaginatedList", t);
	}

}
