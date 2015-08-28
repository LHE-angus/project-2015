package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.JcfxReportXsqsDao;
import com.ebiz.mmt.domain.JcfxReportXsqs;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-12-12 09:59:51
 */
@Service
public class JcfxReportXsqsDaoSqlMapImpl extends EntityDaoSqlMapImpl<JcfxReportXsqs> implements JcfxReportXsqsDao {

	@Override
	public List<JcfxReportXsqs> selectJcfxReportXsqsForMonthList(
			JcfxReportXsqs entity) {
		
		return this.getSqlMapClientTemplate().queryForList("selectJcfxReportXsqsForMonthList", entity);
	}

	@Override
	public List<JcfxReportXsqs> selectJcfxReportXsqsForWeekList(
			JcfxReportXsqs entity) {
		
		return this.getSqlMapClientTemplate().queryForList("selectJcfxReportXsqsForWeekList", entity);
	}

	@Override
	public List<JcfxReportXsqs> selectJcfxReportXsqsForDayList(
			JcfxReportXsqs entity) {
		
		return this.getSqlMapClientTemplate().queryForList("selectJcfxReportXsqsForDayList", entity);
	}

	@Override
	public List<JcfxReportXsqs> selectKonkaMobileDateReportList(
			JcfxReportXsqs entity) {
		
		return this.getSqlMapClientTemplate().queryForList("selectKonkaMobileDateReportList", entity);
	}
	@Override
	public List<JcfxReportXsqs> selectKonkaMobileDateReportCount(
			JcfxReportXsqs entity) {
		
		return this.getSqlMapClientTemplate().queryForList("selectKonkaMobileDateReportCount", entity);
	}

}
