package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaR3OrderLinesDao;
import com.ebiz.mmt.domain.KonkaR3OrderLines;
import com.ebiz.mmt.service.KonkaR3OrderLinesService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by zhou
 * @date 2013-05-29 14:09:13
 */
@Service
public class KonkaR3OrderLinesServiceImpl implements KonkaR3OrderLinesService {

	@Resource
	private KonkaR3OrderLinesDao konkaR3OrderLinesDao;
	

	public Long createKonkaR3OrderLines(KonkaR3OrderLines t) {
		return this.konkaR3OrderLinesDao.insertEntity(t);
	}

	public KonkaR3OrderLines getKonkaR3OrderLines(KonkaR3OrderLines t) {
		return this.konkaR3OrderLinesDao.selectEntity(t);
	}

	public Long getKonkaR3OrderLinesCount(KonkaR3OrderLines t) {
		return this.konkaR3OrderLinesDao.selectEntityCount(t);
	}

	public List<KonkaR3OrderLines> getKonkaR3OrderLinesList(KonkaR3OrderLines t) {
		return this.konkaR3OrderLinesDao.selectEntityList(t);
	}

	public int modifyKonkaR3OrderLines(KonkaR3OrderLines t) {
		return this.konkaR3OrderLinesDao.updateEntity(t);
	}

	public int removeKonkaR3OrderLines(KonkaR3OrderLines t) {
		return this.konkaR3OrderLinesDao.deleteEntity(t);
	}

	public List<KonkaR3OrderLines> getKonkaR3OrderLinesPaginatedList(KonkaR3OrderLines t) {
		return this.konkaR3OrderLinesDao.selectEntityPaginatedList(t);
	}

}
