package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaParagonShowimgDao;
import com.ebiz.mmt.domain.KonkaParagonShowimg;
import com.ebiz.mmt.service.KonkaParagonShowimgService;


@Service
public class KonkaParagonShowimgServiceImpl implements KonkaParagonShowimgService {

	@Resource
	private KonkaParagonShowimgDao konkaParagonShowimgDao;

	public Long createKonkaParagonShowimg(KonkaParagonShowimg t) {
		return this.konkaParagonShowimgDao.insertEntity(t);
	}

	public KonkaParagonShowimg getKonkaParagonShowimg(KonkaParagonShowimg t) {
		return this.konkaParagonShowimgDao.selectEntity(t);
	}

	public Long getKonkaParagonShowimgCount(KonkaParagonShowimg t) {
		return this.konkaParagonShowimgDao.selectEntityCount(t);
	}

	public List<KonkaParagonShowimg> getKonkaParagonShowimgList(KonkaParagonShowimg t) {
		return this.konkaParagonShowimgDao.selectEntityList(t);
	}

	public int modifyKonkaParagonShowimg(KonkaParagonShowimg t) {
		return this.konkaParagonShowimgDao.updateEntity(t);
	}

	public int removeKonkaParagonShowimg(KonkaParagonShowimg t) {
		return this.konkaParagonShowimgDao.deleteEntity(t);
	}

	public List<KonkaParagonShowimg> getKonkaParagonShowimgPaginatedList(KonkaParagonShowimg t) {
		return this.konkaParagonShowimgDao.selectEntityPaginatedList(t);
	}

}
