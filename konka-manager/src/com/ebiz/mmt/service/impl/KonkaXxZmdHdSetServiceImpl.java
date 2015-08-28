package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaXxZmdHdSetDao;
import com.ebiz.mmt.domain.KonkaXxZmdHdSet;
import com.ebiz.mmt.service.KonkaXxZmdHdSetService;

/**
 * @author Xu,XiaoYuan
 * @version 2012-03-02 09:12
 */
@Service
public class KonkaXxZmdHdSetServiceImpl implements KonkaXxZmdHdSetService {

	@Resource
	private KonkaXxZmdHdSetDao konkaXxZmdHdSetDao;
	

	public Long createKonkaXxZmdHdSet(KonkaXxZmdHdSet t) {
		return this.konkaXxZmdHdSetDao.insertEntity(t);
	}

	public KonkaXxZmdHdSet getKonkaXxZmdHdSet(KonkaXxZmdHdSet t) {
		return this.konkaXxZmdHdSetDao.selectEntity(t);
	}

	public Long getKonkaXxZmdHdSetCount(KonkaXxZmdHdSet t) {
		return this.konkaXxZmdHdSetDao.selectEntityCount(t);
	}

	public List<KonkaXxZmdHdSet> getKonkaXxZmdHdSetList(KonkaXxZmdHdSet t) {
		return this.konkaXxZmdHdSetDao.selectEntityList(t);
	}

	public int modifyKonkaXxZmdHdSet(KonkaXxZmdHdSet t) {
		return this.konkaXxZmdHdSetDao.updateEntity(t);
	}

	public int removeKonkaXxZmdHdSet(KonkaXxZmdHdSet t) {
		return this.konkaXxZmdHdSetDao.deleteEntity(t);
	}

	public List<KonkaXxZmdHdSet> getKonkaXxZmdHdSetPaginatedList(KonkaXxZmdHdSet t) {
		return this.konkaXxZmdHdSetDao.selectEntityPaginatedList(t);
	}

}
