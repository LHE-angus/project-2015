package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaR3ExOrderLinesDao;
import com.ebiz.mmt.domain.KonkaR3ExOrderLines;
import com.ebiz.mmt.service.KonkaR3ExOrderLinesService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-05-29 14:09:13
 */
@Service
public class KonkaR3ExOrderLinesServiceImpl implements KonkaR3ExOrderLinesService {

	@Resource
	private KonkaR3ExOrderLinesDao konkaR3ExOrderLinesDao;
	

	public Long createKonkaR3ExOrderLines(KonkaR3ExOrderLines t) {
		return this.konkaR3ExOrderLinesDao.insertEntity(t);
	}

	public KonkaR3ExOrderLines getKonkaR3ExOrderLines(KonkaR3ExOrderLines t) {
		return this.konkaR3ExOrderLinesDao.selectEntity(t);
	}

	public Long getKonkaR3ExOrderLinesCount(KonkaR3ExOrderLines t) {
		return this.konkaR3ExOrderLinesDao.selectEntityCount(t);
	}

	public List<KonkaR3ExOrderLines> getKonkaR3ExOrderLinesList(KonkaR3ExOrderLines t) {
		return this.konkaR3ExOrderLinesDao.selectEntityList(t);
	}

	public int modifyKonkaR3ExOrderLines(KonkaR3ExOrderLines t) {
		return this.konkaR3ExOrderLinesDao.updateEntity(t);
	}

	public int removeKonkaR3ExOrderLines(KonkaR3ExOrderLines t) {
		return this.konkaR3ExOrderLinesDao.deleteEntity(t);
	}

	public List<KonkaR3ExOrderLines> getKonkaR3ExOrderLinesPaginatedList(KonkaR3ExOrderLines t) {
		return this.konkaR3ExOrderLinesDao.selectEntityPaginatedList(t);
	}

}
