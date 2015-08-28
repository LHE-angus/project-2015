package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaParagonShowtDao;
import com.ebiz.mmt.domain.KonkaParagonShowt;
import com.ebiz.mmt.service.KonkaParagonShowtService;


@Service
public class KonkaParagonShowtServiceImpl implements KonkaParagonShowtService {

	@Resource
	private KonkaParagonShowtDao konkaParagonShowtDao;

	public Long createKonkaParagonShowt(KonkaParagonShowt t) {
		return this.konkaParagonShowtDao.insertEntity(t);
	}

	public KonkaParagonShowt getKonkaParagonShowt(KonkaParagonShowt t) {
		return this.konkaParagonShowtDao.selectEntity(t);
	}

	public Long getKonkaParagonShowtCount(KonkaParagonShowt t) {
		return this.konkaParagonShowtDao.selectEntityCount(t);
	}

	public List<KonkaParagonShowt> getKonkaParagonShowtList(KonkaParagonShowt t) {
		return this.konkaParagonShowtDao.selectEntityList(t);
	}

	public int modifyKonkaParagonShowt(KonkaParagonShowt t) {
		return this.konkaParagonShowtDao.updateEntity(t);
	}

	public int removeKonkaParagonShowt(KonkaParagonShowt t) {
		return this.konkaParagonShowtDao.deleteEntity(t);
	}

	public List<KonkaParagonShowt> getKonkaParagonShowtPaginatedList(KonkaParagonShowt t) {
		return this.konkaParagonShowtDao.selectEntityPaginatedList(t);
	}

}
