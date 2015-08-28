package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaParagonShowversionDao;
import com.ebiz.mmt.domain.KonkaParagonShowversion;
import com.ebiz.mmt.service.KonkaParagonShowversionService;


@Service
public class KonkaParagonShowversionServiceImpl implements KonkaParagonShowversionService {

	@Resource
	private KonkaParagonShowversionDao konkaParagonShowversionDao;

	public Long createKonkaParagonShowversion(KonkaParagonShowversion t) {
		return this.konkaParagonShowversionDao.insertEntity(t);
	}

	public KonkaParagonShowversion getKonkaParagonShowversion(KonkaParagonShowversion t) {
		return this.konkaParagonShowversionDao.selectEntity(t);
	}

	public Long getKonkaParagonShowversionCount(KonkaParagonShowversion t) {
		return this.konkaParagonShowversionDao.selectEntityCount(t);
	}

	public List<KonkaParagonShowversion> getKonkaParagonShowversionList(KonkaParagonShowversion t) {
		return this.konkaParagonShowversionDao.selectEntityList(t);
	}

	public int modifyKonkaParagonShowversion(KonkaParagonShowversion t) {
		return this.konkaParagonShowversionDao.updateEntity(t);
	}

	public int removeKonkaParagonShowversion(KonkaParagonShowversion t) {
		return this.konkaParagonShowversionDao.deleteEntity(t);
	}

	public List<KonkaParagonShowversion> getKonkaParagonShowversionPaginatedList(KonkaParagonShowversion t) {
		return this.konkaParagonShowversionDao.selectEntityPaginatedList(t);
	}

}
