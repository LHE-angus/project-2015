package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaR3TargetDao;
import com.ebiz.mmt.domain.KonkaR3Target;
import com.ebiz.mmt.service.KonkaR3TargetService;


@Service
public class KonkaR3TargetServiceImpl implements KonkaR3TargetService {

	@Resource
	private KonkaR3TargetDao konkaR3TargetDao;
	

	public Long createKonkaR3Target(KonkaR3Target t) {
		return this.konkaR3TargetDao.insertEntity(t);
	}

	public KonkaR3Target getKonkaR3Target(KonkaR3Target t) {
		return this.konkaR3TargetDao.selectEntity(t);
	}

	public Long getKonkaR3TargetCount(KonkaR3Target t) {
		return this.konkaR3TargetDao.selectEntityCount(t);
	}

	public List<KonkaR3Target> getKonkaR3TargetList(KonkaR3Target t) {
		return this.konkaR3TargetDao.selectEntityList(t);
	}

	public int modifyKonkaR3Target(KonkaR3Target t) {
		return this.konkaR3TargetDao.updateEntity(t);
	}

	public int removeKonkaR3Target(KonkaR3Target t) {
		return this.konkaR3TargetDao.deleteEntity(t);
	}

	public List<KonkaR3Target> getKonkaR3TargetPaginatedList(KonkaR3Target t) {
		return this.konkaR3TargetDao.selectEntityPaginatedList(t);
	}

}
