package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaPlanMoneyDao;
import com.ebiz.mmt.domain.KonkaPlanMoney;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * @author Liu,ZhiXiang
 * @version 2013-7-9
 * @desc 任务系数管理
 */
@Service
public class KonkaPlanMoneyDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaPlanMoney> implements KonkaPlanMoneyDao {

	@SuppressWarnings("unchecked")
	public List<KonkaPlanMoney> selectKonkaPlanMoneyListForYear(KonkaPlanMoney entity) {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaPlanMoneyListForYear", entity);
	}
}
