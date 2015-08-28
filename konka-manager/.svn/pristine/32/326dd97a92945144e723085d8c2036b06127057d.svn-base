package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaXxZmdDailyDistDetailDao;
import com.ebiz.mmt.domain.KonkaXxZmdDailyDistDetail;
import com.ebiz.mmt.service.KonkaXxZmdDailyDistDetailService;

/**
 * @author Ren,zhong
 * @version 2012-03-22 12:33
 */
@Service
public class KonkaXxZmdDailyDistDetailServiceImpl implements KonkaXxZmdDailyDistDetailService {

	@Resource
	private KonkaXxZmdDailyDistDetailDao konkaXxZmdDailyDistDetailDao;
	

	public Long createKonkaXxZmdDailyDistDetail(KonkaXxZmdDailyDistDetail t) {
		return this.konkaXxZmdDailyDistDetailDao.insertEntity(t);
	}

	public KonkaXxZmdDailyDistDetail getKonkaXxZmdDailyDistDetail(KonkaXxZmdDailyDistDetail t) {
		return this.konkaXxZmdDailyDistDetailDao.selectEntity(t);
	}

	public Long getKonkaXxZmdDailyDistDetailCount(KonkaXxZmdDailyDistDetail t) {
		return this.konkaXxZmdDailyDistDetailDao.selectEntityCount(t);
	}

	public List<KonkaXxZmdDailyDistDetail> getKonkaXxZmdDailyDistDetailList(KonkaXxZmdDailyDistDetail t) {
		return this.konkaXxZmdDailyDistDetailDao.selectEntityList(t);
	}

	public int modifyKonkaXxZmdDailyDistDetail(KonkaXxZmdDailyDistDetail t) {
		return this.konkaXxZmdDailyDistDetailDao.updateEntity(t);
	}

	public int removeKonkaXxZmdDailyDistDetail(KonkaXxZmdDailyDistDetail t) {
		return this.konkaXxZmdDailyDistDetailDao.deleteEntity(t);
	}

	public List<KonkaXxZmdDailyDistDetail> getKonkaXxZmdDailyDistDetailPaginatedList(KonkaXxZmdDailyDistDetail t) {
		return this.konkaXxZmdDailyDistDetailDao.selectEntityPaginatedList(t);
	}

}
