package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaXxZmdDemomacDao;
import com.ebiz.mmt.domain.KonkaXxZmdDemomac;
import com.ebiz.mmt.service.KonkaXxZmdDemomacService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2012-01-09 09:19:48
 */
@Service
public class KonkaXxZmdDemomacServiceImpl implements KonkaXxZmdDemomacService {

	@Resource
	private KonkaXxZmdDemomacDao konkaXxZmdDemomacDao;
	

	public Long createKonkaXxZmdDemomac(KonkaXxZmdDemomac t) {
		return this.konkaXxZmdDemomacDao.insertEntity(t);
	}

	public KonkaXxZmdDemomac getKonkaXxZmdDemomac(KonkaXxZmdDemomac t) {
		return this.konkaXxZmdDemomacDao.selectEntity(t);
	}

	public Long getKonkaXxZmdDemomacCount(KonkaXxZmdDemomac t) {
		return this.konkaXxZmdDemomacDao.selectEntityCount(t);
	}

	public List<KonkaXxZmdDemomac> getKonkaXxZmdDemomacList(KonkaXxZmdDemomac t) {
		return this.konkaXxZmdDemomacDao.selectEntityList(t);
	}

	public int modifyKonkaXxZmdDemomac(KonkaXxZmdDemomac t) {
		return this.konkaXxZmdDemomacDao.updateEntity(t);
	}

	public int removeKonkaXxZmdDemomac(KonkaXxZmdDemomac t) {
		return this.konkaXxZmdDemomacDao.deleteEntity(t);
	}

	public List<KonkaXxZmdDemomac> getKonkaXxZmdDemomacPaginatedList(KonkaXxZmdDemomac t) {
		return this.konkaXxZmdDemomacDao.selectEntityPaginatedList(t);
	}

}
