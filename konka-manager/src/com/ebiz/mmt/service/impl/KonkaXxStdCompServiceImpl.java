package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaXxStdCompDao;
import com.ebiz.mmt.domain.KonkaXxStdComp;
import com.ebiz.mmt.service.KonkaXxStdCompService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2012-01-11 10:56:03
 */
@Service
public class KonkaXxStdCompServiceImpl implements KonkaXxStdCompService {

	@Resource
	private KonkaXxStdCompDao konkaXxStdCompDao;
	

	public Long createKonkaXxStdComp(KonkaXxStdComp t) {
		return this.konkaXxStdCompDao.insertEntity(t);
	}

	public KonkaXxStdComp getKonkaXxStdComp(KonkaXxStdComp t) {
		return this.konkaXxStdCompDao.selectEntity(t);
	}

	public Long getKonkaXxStdCompCount(KonkaXxStdComp t) {
		return this.konkaXxStdCompDao.selectEntityCount(t);
	}

	public List<KonkaXxStdComp> getKonkaXxStdCompList(KonkaXxStdComp t) {
		return this.konkaXxStdCompDao.selectEntityList(t);
	}

	public int modifyKonkaXxStdComp(KonkaXxStdComp t) {
		return this.konkaXxStdCompDao.updateEntity(t);
	}

	public int removeKonkaXxStdComp(KonkaXxStdComp t) {
		return this.konkaXxStdCompDao.deleteEntity(t);
	}

	public List<KonkaXxStdComp> getKonkaXxStdCompPaginatedList(KonkaXxStdComp t) {
		return this.konkaXxStdCompDao.selectEntityPaginatedList(t);
	}

}
