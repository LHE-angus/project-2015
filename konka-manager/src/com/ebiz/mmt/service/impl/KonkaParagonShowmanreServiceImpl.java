package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaParagonShowmanreDao;
import com.ebiz.mmt.domain.KonkaParagonShowmanre;
import com.ebiz.mmt.service.KonkaParagonShowmanreService;


@Service
public class KonkaParagonShowmanreServiceImpl implements KonkaParagonShowmanreService {

	@Resource
	private KonkaParagonShowmanreDao konkaParagonShowmanreDao;

	public Long createKonkaParagonShowmanre(KonkaParagonShowmanre t) {
		return this.konkaParagonShowmanreDao.insertEntity(t);
	}

	public KonkaParagonShowmanre getKonkaParagonShowmanre(KonkaParagonShowmanre t) {
		return this.konkaParagonShowmanreDao.selectEntity(t);
	}

	public Long getKonkaParagonShowmanreCount(KonkaParagonShowmanre t) {
		return this.konkaParagonShowmanreDao.selectEntityCount(t);
	}

	public List<KonkaParagonShowmanre> getKonkaParagonShowmanreList(KonkaParagonShowmanre t) {
		return this.konkaParagonShowmanreDao.selectEntityList(t);
	}

	public int modifyKonkaParagonShowmanre(KonkaParagonShowmanre t) {
		return this.konkaParagonShowmanreDao.updateEntity(t);
	}

	public int removeKonkaParagonShowmanre(KonkaParagonShowmanre t) {
		return this.konkaParagonShowmanreDao.deleteEntity(t);
	}

	public List<KonkaParagonShowmanre> getKonkaParagonShowmanrePaginatedList(KonkaParagonShowmanre t) {
		return this.konkaParagonShowmanreDao.selectEntityPaginatedList(t);
	}
	
	public KonkaParagonShowmanre getKonkaParagonShowmanreForEdit(KonkaParagonShowmanre t) {
		return this.konkaParagonShowmanreDao.selectEntityForEdit(t);
	}
	
	public Long getKonkaParagonShowmanreCountForShowOut(KonkaParagonShowmanre t) {
		return this.konkaParagonShowmanreDao.selectEntityCountForShowOut(t);
	}

	public List<KonkaParagonShowmanre> getKonkaParagonShowmanrePaginatedListForShowOut(KonkaParagonShowmanre t) {
		return this.konkaParagonShowmanreDao.selectEntityPaginatedListForShowOut(t);
	}
	
}
