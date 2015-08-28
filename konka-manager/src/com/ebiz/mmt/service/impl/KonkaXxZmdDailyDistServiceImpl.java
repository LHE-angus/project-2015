package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaXxZmdDailyDistDao;
import com.ebiz.mmt.domain.KonkaXxZmdDailyDist;
import com.ebiz.mmt.service.KonkaXxZmdDailyDistService;

/**
 * @author Ren,zhong
 * @version 2012-03-22 12:33
 */
@Service
public class KonkaXxZmdDailyDistServiceImpl implements KonkaXxZmdDailyDistService {

	@Resource
	private KonkaXxZmdDailyDistDao konkaXxZmdDailyDistDao;
	

	public Long createKonkaXxZmdDailyDist(KonkaXxZmdDailyDist t) {
		return this.konkaXxZmdDailyDistDao.insertEntity(t);
	}

	public KonkaXxZmdDailyDist getKonkaXxZmdDailyDist(KonkaXxZmdDailyDist t) {
		return this.konkaXxZmdDailyDistDao.selectEntity(t);
	}

	public Long getKonkaXxZmdDailyDistCount(KonkaXxZmdDailyDist t) {
		return this.konkaXxZmdDailyDistDao.selectEntityCount(t);
	}

	public List<KonkaXxZmdDailyDist> getKonkaXxZmdDailyDistList(KonkaXxZmdDailyDist t) {
		return this.konkaXxZmdDailyDistDao.selectEntityList(t);
	}

	public int modifyKonkaXxZmdDailyDist(KonkaXxZmdDailyDist t) {
		return this.konkaXxZmdDailyDistDao.updateEntity(t);
	}

	public int removeKonkaXxZmdDailyDist(KonkaXxZmdDailyDist t) {
		return this.konkaXxZmdDailyDistDao.deleteEntity(t);
	}

	public List<KonkaXxZmdDailyDist> getKonkaXxZmdDailyDistPaginatedList(KonkaXxZmdDailyDist t) {
		return this.konkaXxZmdDailyDistDao.selectEntityPaginatedList(t);
	}

}
