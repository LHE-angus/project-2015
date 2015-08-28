package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaXxZmdDailyStmsDetailDao;
import com.ebiz.mmt.domain.KonkaXxZmdDailyStmsDetail;
import com.ebiz.mmt.service.KonkaXxZmdDailyStmsDetailService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2012-03-21 09:35:55
 */
@Service
public class KonkaXxZmdDailyStmsDetailServiceImpl implements KonkaXxZmdDailyStmsDetailService {

	@Resource
	private KonkaXxZmdDailyStmsDetailDao konkaXxZmdDailyStmsDetailDao;
	

	public Long createKonkaXxZmdDailyStmsDetail(KonkaXxZmdDailyStmsDetail t) {
		return this.konkaXxZmdDailyStmsDetailDao.insertEntity(t);
	}

	public KonkaXxZmdDailyStmsDetail getKonkaXxZmdDailyStmsDetail(KonkaXxZmdDailyStmsDetail t) {
		return this.konkaXxZmdDailyStmsDetailDao.selectEntity(t);
	}

	public Long getKonkaXxZmdDailyStmsDetailCount(KonkaXxZmdDailyStmsDetail t) {
		return this.konkaXxZmdDailyStmsDetailDao.selectEntityCount(t);
	}

	public List<KonkaXxZmdDailyStmsDetail> getKonkaXxZmdDailyStmsDetailList(KonkaXxZmdDailyStmsDetail t) {
		return this.konkaXxZmdDailyStmsDetailDao.selectEntityList(t);
	}

	public int modifyKonkaXxZmdDailyStmsDetail(KonkaXxZmdDailyStmsDetail t) {
		return this.konkaXxZmdDailyStmsDetailDao.updateEntity(t);
	}

	public int removeKonkaXxZmdDailyStmsDetail(KonkaXxZmdDailyStmsDetail t) {
		return this.konkaXxZmdDailyStmsDetailDao.deleteEntity(t);
	}

	public List<KonkaXxZmdDailyStmsDetail> getKonkaXxZmdDailyStmsDetailPaginatedList(KonkaXxZmdDailyStmsDetail t) {
		return this.konkaXxZmdDailyStmsDetailDao.selectEntityPaginatedList(t);
	}

}
