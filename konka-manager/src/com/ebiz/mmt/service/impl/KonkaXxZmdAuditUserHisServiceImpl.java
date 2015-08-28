package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaXxZmdAuditUserHisDao;
import com.ebiz.mmt.domain.KonkaXxZmdAuditUserHis;
import com.ebiz.mmt.service.KonkaXxZmdAuditUserHisService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-03-19 11:53:08
 */
@Service
public class KonkaXxZmdAuditUserHisServiceImpl implements KonkaXxZmdAuditUserHisService {

	@Resource
	private KonkaXxZmdAuditUserHisDao konkaXxZmdAuditUserHisDao;
	

	public Long createKonkaXxZmdAuditUserHis(KonkaXxZmdAuditUserHis t) {
		return this.konkaXxZmdAuditUserHisDao.insertEntity(t);
	}

	public KonkaXxZmdAuditUserHis getKonkaXxZmdAuditUserHis(KonkaXxZmdAuditUserHis t) {
		return this.konkaXxZmdAuditUserHisDao.selectEntity(t);
	}

	public Long getKonkaXxZmdAuditUserHisCount(KonkaXxZmdAuditUserHis t) {
		return this.konkaXxZmdAuditUserHisDao.selectEntityCount(t);
	}

	public List<KonkaXxZmdAuditUserHis> getKonkaXxZmdAuditUserHisList(KonkaXxZmdAuditUserHis t) {
		return this.konkaXxZmdAuditUserHisDao.selectEntityList(t);
	}

	public int modifyKonkaXxZmdAuditUserHis(KonkaXxZmdAuditUserHis t) {
		return this.konkaXxZmdAuditUserHisDao.updateEntity(t);
	}

	public int removeKonkaXxZmdAuditUserHis(KonkaXxZmdAuditUserHis t) {
		return this.konkaXxZmdAuditUserHisDao.deleteEntity(t);
	}

	public List<KonkaXxZmdAuditUserHis> getKonkaXxZmdAuditUserHisPaginatedList(KonkaXxZmdAuditUserHis t) {
		return this.konkaXxZmdAuditUserHisDao.selectEntityPaginatedList(t);
	}

	/**
	 * @author Hu,hao
	 * @version 2013
	 */
	public List<KonkaXxZmdAuditUserHis> getKonkaXxZmdAuditUserHisForZmdList(KonkaXxZmdAuditUserHis t){
		return this.konkaXxZmdAuditUserHisDao.selectKonkaXxZmdAuditUserHisForZmdList(t);
	}
	/**
	 * @author lideyu
	 * @version 2014
	 */
	public KonkaXxZmdAuditUserHis getKonkaXxZmdAuditUserHisMAX(KonkaXxZmdAuditUserHis t)
	{
		return this.konkaXxZmdAuditUserHisDao.selectKonkaXxZmdAuditUserHisMAX(t);
	}
}
