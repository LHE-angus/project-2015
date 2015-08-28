package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaSpActivityManagerDao;
import com.ebiz.mmt.domain.KonkaSpActivityManager;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-01-23 11:36:10
 */
@Service
public class KonkaSpActivityManagerDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaSpActivityManager> implements
		KonkaSpActivityManagerDao {

	@SuppressWarnings("unchecked")
	public List<KonkaSpActivityManager> selectEntityListForExcel(KonkaSpActivityManager t) {
		return (List<KonkaSpActivityManager>) super.getSqlMapClientTemplate().queryForList(
				"selectKonkaSpActivityManagerListForExcel", t);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<KonkaSpActivityManager> selectKonkaSpActivityManagerListForBookReport(
			KonkaSpActivityManager entity) {
		
		return (List<KonkaSpActivityManager>) super.getSqlMapClientTemplate().queryForList(
				"selectKonkaSpActivityManagerListForBookReport", entity);
	}

}
