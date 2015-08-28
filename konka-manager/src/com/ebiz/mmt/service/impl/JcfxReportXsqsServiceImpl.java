package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.JcfxReportXsqsDao;
import com.ebiz.mmt.domain.JcfxReportXsqs;
import com.ebiz.mmt.service.JcfxReportXsqsService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-12-12 09:59:51
 */
@Service
public class JcfxReportXsqsServiceImpl implements JcfxReportXsqsService {

	@Resource
	private JcfxReportXsqsDao jcfxReportXsqsDao;
	

	public Long createJcfxReportXsqs(JcfxReportXsqs t) {
		return this.jcfxReportXsqsDao.insertEntity(t);
	}

	public JcfxReportXsqs getJcfxReportXsqs(JcfxReportXsqs t) {
		return this.jcfxReportXsqsDao.selectEntity(t);
	}

	public Long getJcfxReportXsqsCount(JcfxReportXsqs t) {
		return this.jcfxReportXsqsDao.selectEntityCount(t);
	}

	public List<JcfxReportXsqs> getJcfxReportXsqsList(JcfxReportXsqs t) {
		return this.jcfxReportXsqsDao.selectEntityList(t);
	}

	public int modifyJcfxReportXsqs(JcfxReportXsqs t) {
		return this.jcfxReportXsqsDao.updateEntity(t);
	}

	public int removeJcfxReportXsqs(JcfxReportXsqs t) {
		return this.jcfxReportXsqsDao.deleteEntity(t);
	}

	public List<JcfxReportXsqs> getJcfxReportXsqsPaginatedList(JcfxReportXsqs t) {
		return this.jcfxReportXsqsDao.selectEntityPaginatedList(t);
	}

	@Override
	public List<JcfxReportXsqs> getJcfxReportXsqsForMonthList(
			JcfxReportXsqs entity) {
		
		return jcfxReportXsqsDao.selectJcfxReportXsqsForMonthList(entity);
	}

	@Override
	public List<JcfxReportXsqs> getJcfxReportXsqsForWeekList(
			JcfxReportXsqs entity) {
		
		return jcfxReportXsqsDao.selectJcfxReportXsqsForWeekList(entity);
	}

	@Override
	public List<JcfxReportXsqs> getJcfxReportXsqsForDayList(
			JcfxReportXsqs entity) {
		
		return jcfxReportXsqsDao.selectJcfxReportXsqsForDayList(entity);
	}

	@Override
	public List<JcfxReportXsqs> getKonkaMobileDateReportList(
			JcfxReportXsqs entity) {
		
		return jcfxReportXsqsDao.selectKonkaMobileDateReportList(entity);
	}

	@Override
	public List<JcfxReportXsqs> getKonkaMobileDateReportCount(
			JcfxReportXsqs entity) {
		
		return jcfxReportXsqsDao.selectKonkaMobileDateReportCount(entity);
	}

}
