package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaSpListDao;
import com.ebiz.mmt.domain.KonkaSpList;
import com.ebiz.mmt.service.KonkaSpListService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-01-23 12:08:34
 */
@Service
public class KonkaSpListServiceImpl implements KonkaSpListService {

	@Resource
	private KonkaSpListDao konkaSpListDao;
	

	public Long createKonkaSpList(KonkaSpList t) {
		return this.konkaSpListDao.insertEntity(t);
	}

	public KonkaSpList getKonkaSpList(KonkaSpList t) {
		return this.konkaSpListDao.selectEntity(t);
	}

	public Long getKonkaSpListCount(KonkaSpList t) {
		return this.konkaSpListDao.selectEntityCount(t);
	}

	public List<KonkaSpList> getKonkaSpListList(KonkaSpList t) {
		return this.konkaSpListDao.selectEntityList(t);
	}

	public int modifyKonkaSpList(KonkaSpList t) {
		return this.konkaSpListDao.updateEntity(t);
	}

	public int removeKonkaSpList(KonkaSpList t) {
		return this.konkaSpListDao.deleteEntity(t);
	}

	public List<KonkaSpList> getKonkaSpListPaginatedList(KonkaSpList t) {
		return this.konkaSpListDao.selectEntityPaginatedList(t);
	}

}
