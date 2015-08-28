package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaSpActivityBookReportDao;
import com.ebiz.mmt.domain.KonkaSpActivityBookReport;
import com.ebiz.mmt.service.KonkaSpActivityBookReportService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-12-29 11:19:39
 */
@Service
public class KonkaSpActivityBookReportServiceImpl implements KonkaSpActivityBookReportService {

	@Resource
	private KonkaSpActivityBookReportDao konkaSpActivityBookReportDao;
	

	public Long createKonkaSpActivityBookReport(KonkaSpActivityBookReport t) {
		return this.konkaSpActivityBookReportDao.insertEntity(t);
	}

	public KonkaSpActivityBookReport getKonkaSpActivityBookReport(KonkaSpActivityBookReport t) {
		return this.konkaSpActivityBookReportDao.selectEntity(t);
	}

	public Long getKonkaSpActivityBookReportCount(KonkaSpActivityBookReport t) {
		return this.konkaSpActivityBookReportDao.selectEntityCount(t);
	}

	public List<KonkaSpActivityBookReport> getKonkaSpActivityBookReportList(KonkaSpActivityBookReport t) {
		return this.konkaSpActivityBookReportDao.selectEntityList(t);
	}

	public int modifyKonkaSpActivityBookReport(KonkaSpActivityBookReport t) {
		return this.konkaSpActivityBookReportDao.updateEntity(t);
	}

	public int removeKonkaSpActivityBookReport(KonkaSpActivityBookReport t) {
		return this.konkaSpActivityBookReportDao.deleteEntity(t);
	}

	public List<KonkaSpActivityBookReport> getKonkaSpActivityBookReportPaginatedList(KonkaSpActivityBookReport t) {
		return this.konkaSpActivityBookReportDao.selectEntityPaginatedList(t);
	}

	@Override
	public Long getKonkaSpActivityBookReportYytjCount(
			KonkaSpActivityBookReport entity) {
		
		return this.konkaSpActivityBookReportDao.selectKonkaSpActivityBookReportYytjCount(entity);
	}

	@Override
	public List<KonkaSpActivityBookReport> getKonkaSpActivityBookReportYytjPaginatedList(
			KonkaSpActivityBookReport entity) {
		
		return this.konkaSpActivityBookReportDao.selectKonkaSpActivityBookReportYytjPaginatedList(entity);
	}

	@Override
	public Long getKonkaSpActivityBookReportYyLstjCount(KonkaSpActivityBookReport entity) {
		
		return this.konkaSpActivityBookReportDao.selectKonkaSpActivityBookReportYyLstjCount(entity);
	}

	@Override
	public List<KonkaSpActivityBookReport> getKonkaSpActivityBookReportYyLstjPaginatedList(
			KonkaSpActivityBookReport entity) {
		
		return this.konkaSpActivityBookReportDao.selectKonkaSpActivityBookReportYyLstjPaginatedList(entity);
	}

}
