package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaPartershipDao;
import com.ebiz.mmt.domain.KonkaPartership;
import com.ebiz.mmt.service.KonkaPartershipService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-04-16 14:48:10
 */
@Service
public class KonkaPartershipServiceImpl implements KonkaPartershipService {

	@Resource
	private KonkaPartershipDao konkaPartershipDao;
	

	public Long createKonkaPartership(KonkaPartership t) {
		return this.konkaPartershipDao.insertEntity(t);
	}

	public KonkaPartership getKonkaPartership(KonkaPartership t) {
		return this.konkaPartershipDao.selectEntity(t);
	}

	public Long getKonkaPartershipCount(KonkaPartership t) {
		return this.konkaPartershipDao.selectEntityCount(t);
	}

	public List<KonkaPartership> getKonkaPartershipList(KonkaPartership t) {
		return this.konkaPartershipDao.selectEntityList(t);
	}

	public int modifyKonkaPartership(KonkaPartership t) {
		return this.konkaPartershipDao.updateEntity(t);
	}

	public int removeKonkaPartership(KonkaPartership t) {
		return this.konkaPartershipDao.deleteEntity(t);
	}

	public List<KonkaPartership> getKonkaPartershipPaginatedList(KonkaPartership t) {
		return this.konkaPartershipDao.selectEntityPaginatedList(t);
	}

}
