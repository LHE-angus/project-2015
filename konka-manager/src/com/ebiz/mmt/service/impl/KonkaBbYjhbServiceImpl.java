package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaBbYjhbDao;
import com.ebiz.mmt.domain.KonkaBbYjhb;
import com.ebiz.mmt.service.KonkaBbYjhbService;


@Service
public class KonkaBbYjhbServiceImpl implements KonkaBbYjhbService {

	@Resource
	private KonkaBbYjhbDao konkaBbYjhbDao;
	

	@Override
	public Long createKonkaBbYjhb(KonkaBbYjhb t) {
		return this.konkaBbYjhbDao.insertEntity(t);
	}

	@Override
	public KonkaBbYjhb getKonkaBbYjhb(KonkaBbYjhb t) {
		return this.konkaBbYjhbDao.selectEntity(t);
	}

	@Override
	public Long getKonkaBbYjhbCount(KonkaBbYjhb t) {
		return this.konkaBbYjhbDao.selectEntityCount(t);
	}

	@Override
	public List<KonkaBbYjhb> getKonkaBbYjhbList(KonkaBbYjhb t) {
		return this.konkaBbYjhbDao.selectEntityList(t);
	}

	@Override
	public int modifyKonkaBbYjhb(KonkaBbYjhb t) {
		return this.konkaBbYjhbDao.updateEntity(t);
	}

	@Override
	public int removeKonkaBbYjhb(KonkaBbYjhb t) {
		return this.konkaBbYjhbDao.deleteEntity(t);
	}

	@Override
	public List<KonkaBbYjhb> getKonkaBbYjhbPaginatedList(KonkaBbYjhb t) {
		return this.konkaBbYjhbDao.selectEntityPaginatedList(t);
	}

	@Override
	public void createKonkaBbYjhbBatch(List<KonkaBbYjhb> konkaBbYjhbList) {
		this.konkaBbYjhbDao.insertEntityBatch(konkaBbYjhbList);
	}
}
