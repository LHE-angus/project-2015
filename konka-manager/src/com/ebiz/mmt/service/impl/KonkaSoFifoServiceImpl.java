package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaSoFifoDao;
import com.ebiz.mmt.domain.KonkaSoFifo;
import com.ebiz.mmt.service.KonkaSoFifoService;


@Service
public class KonkaSoFifoServiceImpl implements KonkaSoFifoService {

	@Resource
	private KonkaSoFifoDao konkaSoFifoDao;
	

	public Long createKonkaSoFifo(KonkaSoFifo t) {
		return this.konkaSoFifoDao.insertEntity(t);
	}

	public KonkaSoFifo getKonkaSoFifo(KonkaSoFifo t) {
		return this.konkaSoFifoDao.selectEntity(t);
	}

	public Long getKonkaSoFifoCount(KonkaSoFifo t) {
		return this.konkaSoFifoDao.selectEntityCount(t);
	}

	public List<KonkaSoFifo> getKonkaSoFifoList(KonkaSoFifo t) {
		return this.konkaSoFifoDao.selectEntityList(t);
	}

	public int modifyKonkaSoFifo(KonkaSoFifo t) {
		return this.konkaSoFifoDao.updateEntity(t);
	}

	public int removeKonkaSoFifo(KonkaSoFifo t) {
		return this.konkaSoFifoDao.deleteEntity(t);
	}

	public List<KonkaSoFifo> getKonkaSoFifoPaginatedList(KonkaSoFifo t) {
		return this.konkaSoFifoDao.selectEntityPaginatedList(t);
	}

}
