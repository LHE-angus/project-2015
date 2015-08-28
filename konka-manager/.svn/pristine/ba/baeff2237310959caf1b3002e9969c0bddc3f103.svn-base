package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaParagonManinfoDao;
import com.ebiz.mmt.domain.KonkaParagonManinfo;
import com.ebiz.mmt.service.KonkaParagonManinfoService;


@Service
public class KonkaParagonManinfoServiceImpl implements KonkaParagonManinfoService {

	@Resource
	private KonkaParagonManinfoDao konkaParagonManinfoDao;

	public Long createKonkaParagonManinfo(KonkaParagonManinfo t) {
		return this.konkaParagonManinfoDao.insertEntity(t);
	}

	public KonkaParagonManinfo getKonkaParagonManinfo(KonkaParagonManinfo t) {
		return this.konkaParagonManinfoDao.selectEntity(t);
	}

	public Long getKonkaParagonManinfoCount(KonkaParagonManinfo t) {
		return this.konkaParagonManinfoDao.selectEntityCount(t);
	}

	public List<KonkaParagonManinfo> getKonkaParagonManinfoList(KonkaParagonManinfo t) {
		return this.konkaParagonManinfoDao.selectEntityList(t);
	}

	public int modifyKonkaParagonManinfo(KonkaParagonManinfo t) {
		return this.konkaParagonManinfoDao.updateEntity(t);
	}

	public int removeKonkaParagonManinfo(KonkaParagonManinfo t) {
		return this.konkaParagonManinfoDao.deleteEntity(t);
	}

	public List<KonkaParagonManinfo> getKonkaParagonManinfoPaginatedList(KonkaParagonManinfo t) {
		return this.konkaParagonManinfoDao.selectEntityPaginatedList(t);
	}

}
