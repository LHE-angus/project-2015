package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaXxZmdDailyStmsDao;
import com.ebiz.mmt.domain.KonkaXxZmdDailyStms;
import com.ebiz.mmt.service.KonkaXxZmdDailyStmsService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2012-03-21 09:35:55
 */
@Service
public class KonkaXxZmdDailyStmsServiceImpl implements KonkaXxZmdDailyStmsService {

	@Resource
	private KonkaXxZmdDailyStmsDao konkaXxZmdDailyStmsDao;
	

	public Long createKonkaXxZmdDailyStms(KonkaXxZmdDailyStms t) {
		return this.konkaXxZmdDailyStmsDao.insertEntity(t);
	}

	public KonkaXxZmdDailyStms getKonkaXxZmdDailyStms(KonkaXxZmdDailyStms t) {
		return this.konkaXxZmdDailyStmsDao.selectEntity(t);
	}

	public Long getKonkaXxZmdDailyStmsCount(KonkaXxZmdDailyStms t) {
		return this.konkaXxZmdDailyStmsDao.selectEntityCount(t);
	}

	public List<KonkaXxZmdDailyStms> getKonkaXxZmdDailyStmsList(KonkaXxZmdDailyStms t) {
		return this.konkaXxZmdDailyStmsDao.selectEntityList(t);
	}

	public int modifyKonkaXxZmdDailyStms(KonkaXxZmdDailyStms t) {
		return this.konkaXxZmdDailyStmsDao.updateEntity(t);
	}

	public int removeKonkaXxZmdDailyStms(KonkaXxZmdDailyStms t) {
		return this.konkaXxZmdDailyStmsDao.deleteEntity(t);
	}

	public List<KonkaXxZmdDailyStms> getKonkaXxZmdDailyStmsPaginatedList(KonkaXxZmdDailyStms t) {
		return this.konkaXxZmdDailyStmsDao.selectEntityPaginatedList(t);
	}

}
