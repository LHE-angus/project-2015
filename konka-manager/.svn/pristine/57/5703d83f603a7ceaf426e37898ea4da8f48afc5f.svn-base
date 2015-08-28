package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaPlanRatioDao;
import com.ebiz.mmt.domain.KonkaPlanRatio;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * @author Liu,ZhiXiang
 * @version 2013-7-9
 * @desc 任务系数管理
 */
@Service
public class KonkaPlanRatioDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaPlanRatio> implements KonkaPlanRatioDao {
	@SuppressWarnings("unchecked")
	public List<KonkaPlanRatio> selectKonkaPlanRatioListForFgs(KonkaPlanRatio t){
		return super.getSqlMapClientTemplate().queryForList("selectKonkaPlanRatioListForFgs", t);
	}
}
