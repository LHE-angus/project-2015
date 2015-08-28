package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaSpMdSailDao;
import com.ebiz.mmt.domain.KonkaSpMdSail;
import com.ebiz.mmt.service.KonkaSpMdSailService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-02-10 17:06:35
 */
@Service
public class KonkaSpMdSailServiceImpl implements KonkaSpMdSailService {

	@Resource
	private KonkaSpMdSailDao konkaSpMdSailDao;

	public Long createKonkaSpMdSail(KonkaSpMdSail t) {
		return this.konkaSpMdSailDao.insertEntity(t);
	}

	public KonkaSpMdSail getKonkaSpMdSail(KonkaSpMdSail t) {
		return this.konkaSpMdSailDao.selectEntity(t);
	}

	public Long getKonkaSpMdSailCount(KonkaSpMdSail t) {
		return this.konkaSpMdSailDao.selectEntityCount(t);
	}

	public List<KonkaSpMdSail> getKonkaSpMdSailList(KonkaSpMdSail t) {
		return this.konkaSpMdSailDao.selectEntityList(t);
	}

	public int modifyKonkaSpMdSail(KonkaSpMdSail t) {
		return this.konkaSpMdSailDao.updateEntity(t);
	}

	public int removeKonkaSpMdSail(KonkaSpMdSail t) {
		return this.konkaSpMdSailDao.deleteEntity(t);
	}

	public List<KonkaSpMdSail> getKonkaSpMdSailPaginatedList(KonkaSpMdSail t) {
		return this.konkaSpMdSailDao.selectEntityPaginatedList(t);
	}

	public KonkaSpMdSail getKonkaSpMdSailForSum(KonkaSpMdSail t) {
		return this.konkaSpMdSailDao.selectKonkaSpMdSailForSum(t);
	}

}
