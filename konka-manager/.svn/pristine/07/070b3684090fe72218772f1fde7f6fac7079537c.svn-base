package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaR3OrderOplogDao;
import com.ebiz.mmt.domain.KonkaR3OrderOplog;
import com.ebiz.mmt.service.KonkaR3OrderOplogService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by zhou
 * @date 2013-05-29 14:09:13
 */
@Service
public class KonkaR3OrderOplogServiceImpl implements KonkaR3OrderOplogService {

	@Resource
	private KonkaR3OrderOplogDao konkaR3OrderOplogDao;
	

	public Long createKonkaR3OrderOplog(KonkaR3OrderOplog t) {
		return this.konkaR3OrderOplogDao.insertEntity(t);
	}

	public KonkaR3OrderOplog getKonkaR3OrderOplog(KonkaR3OrderOplog t) {
		return this.konkaR3OrderOplogDao.selectEntity(t);
	}

	public Long getKonkaR3OrderOplogCount(KonkaR3OrderOplog t) {
		return this.konkaR3OrderOplogDao.selectEntityCount(t);
	}

	public List<KonkaR3OrderOplog> getKonkaR3OrderOplogList(KonkaR3OrderOplog t) {
		return this.konkaR3OrderOplogDao.selectEntityList(t);
	}

	public int modifyKonkaR3OrderOplog(KonkaR3OrderOplog t) {
		return this.konkaR3OrderOplogDao.updateEntity(t);
	}

	public int removeKonkaR3OrderOplog(KonkaR3OrderOplog t) {
		return this.konkaR3OrderOplogDao.deleteEntity(t);
	}

	public List<KonkaR3OrderOplog> getKonkaR3OrderOplogPaginatedList(KonkaR3OrderOplog t) {
		return this.konkaR3OrderOplogDao.selectEntityPaginatedList(t);
	}

}
