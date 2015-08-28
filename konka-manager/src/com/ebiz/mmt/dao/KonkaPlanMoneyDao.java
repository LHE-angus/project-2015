package com.ebiz.mmt.dao;

import java.util.List;

import com.ebiz.mmt.domain.KonkaPlanMoney;
import com.ebiz.ssi.dao.EntityDao;

/**
 * @author Liu,ZhiXiang
 * @version 2013-7-9
 * @desc 任务系数管理
 */
public interface KonkaPlanMoneyDao extends EntityDao<KonkaPlanMoney> {

	List<KonkaPlanMoney> selectKonkaPlanMoneyListForYear(KonkaPlanMoney entity);
}
