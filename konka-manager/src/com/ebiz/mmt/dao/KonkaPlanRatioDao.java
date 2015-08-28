package com.ebiz.mmt.dao;

import java.util.List;

import com.ebiz.mmt.domain.KonkaPlanRatio;
import com.ebiz.ssi.dao.EntityDao;

/**
 * @author Liu,ZhiXiang
 * @version 2013-7-9
 * @desc 任务系数管理
 */
public interface KonkaPlanRatioDao extends EntityDao<KonkaPlanRatio> {

	List<KonkaPlanRatio> selectKonkaPlanRatioListForFgs(KonkaPlanRatio t);
}
