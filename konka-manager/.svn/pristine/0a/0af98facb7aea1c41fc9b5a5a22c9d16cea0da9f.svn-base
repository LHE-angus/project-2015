package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaSpActivityBookReportDao;
import com.ebiz.mmt.domain.KonkaSpActivityBookReport;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-12-29 11:19:39
 */
@Service
public class KonkaSpActivityBookReportDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaSpActivityBookReport> implements KonkaSpActivityBookReportDao {

	@Override
	public Long selectKonkaSpActivityBookReportYytjCount(
			KonkaSpActivityBookReport entity) {
		
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectKonkaSpActivityBookReportYytjCount", entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<KonkaSpActivityBookReport> selectKonkaSpActivityBookReportYytjPaginatedList(
			KonkaSpActivityBookReport entity) {
		
		return super.getSqlMapClientTemplate().queryForList("selectKonkaSpActivityBookReportYytjPaginatedList", entity);
	}

	@Override
	public Long selectKonkaSpActivityBookReportYyLstjCount(KonkaSpActivityBookReport entity) {
		
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectKonkaSpActivityBookReportYyLstjCount", entity);
	}

	@Override
	public List<KonkaSpActivityBookReport> selectKonkaSpActivityBookReportYyLstjPaginatedList(
			KonkaSpActivityBookReport entity) {
		
		return super.getSqlMapClientTemplate().queryForList("selectKonkaSpActivityBookReportYyLstjPaginatedList", entity);
	}

}
