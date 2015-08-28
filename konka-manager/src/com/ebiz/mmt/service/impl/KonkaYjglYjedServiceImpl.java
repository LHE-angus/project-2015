package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaYjglYjedDao;
import com.ebiz.mmt.domain.KonkaYjglYjed;
import com.ebiz.mmt.service.KonkaYjglYjedService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-11-03 14:08:34
 */
@Service
public class KonkaYjglYjedServiceImpl implements KonkaYjglYjedService {

	@Resource
	private KonkaYjglYjedDao konkaYjglYjedDao;

	public Long createKonkaYjglYjed(KonkaYjglYjed t) {
		return this.konkaYjglYjedDao.insertEntity(t);
	}

	public KonkaYjglYjed getKonkaYjglYjed(KonkaYjglYjed t) {
		return this.konkaYjglYjedDao.selectEntity(t);
	}

	public Long getKonkaYjglYjedCount(KonkaYjglYjed t) {
		return this.konkaYjglYjedDao.selectEntityCount(t);
	}

	public List<KonkaYjglYjed> getKonkaYjglYjedList(KonkaYjglYjed t) {
		return this.konkaYjglYjedDao.selectEntityList(t);
	}

	public int modifyKonkaYjglYjed(KonkaYjglYjed t) {
		return this.konkaYjglYjedDao.updateEntity(t);
	}

	public int removeKonkaYjglYjed(KonkaYjglYjed t) {
		return this.konkaYjglYjedDao.deleteEntity(t);
	}

	public List<KonkaYjglYjed> getKonkaYjglYjedPaginatedList(KonkaYjglYjed t) {
		return this.konkaYjglYjedDao.selectEntityPaginatedList(t);
	}

	@Override
	public Long getKonkaYjglYjedAndDeptNameCount(KonkaYjglYjed t) {
		return this.konkaYjglYjedDao.selectKonkaYjglYjedAndDeptNameCount(t);
	}

	@Override
	public List<KonkaYjglYjed> getKonkaYjglYjedAndDeptNamePaginatedList(KonkaYjglYjed t) {
		return this.konkaYjglYjedDao.selectKonkaYjglYjedAndDeptNamePaginatedList(t);
	}

}
