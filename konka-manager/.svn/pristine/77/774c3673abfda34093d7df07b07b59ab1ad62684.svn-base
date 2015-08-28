package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaBoothsDao;
import com.ebiz.mmt.domain.KonkaBooths;
import com.ebiz.mmt.service.KonkaBoothsService;


@Service
public class KonkaBoothsServiceImpl implements KonkaBoothsService {

	@Resource
	private KonkaBoothsDao konkaBoothsDao;

	public Long createKonkaBooths(KonkaBooths t) {
		return this.konkaBoothsDao.insertEntity(t);
	}

	public KonkaBooths getKonkaBooths(KonkaBooths t) {
		return this.konkaBoothsDao.selectEntity(t);
	}

	public Long getKonkaBoothsCount(KonkaBooths t) {
		return this.konkaBoothsDao.selectEntityCount(t);
	}

	public List<KonkaBooths> getKonkaBoothsList(KonkaBooths t) {
		return this.konkaBoothsDao.selectEntityList(t);
	}

	public int modifyKonkaBooths(KonkaBooths t) {
		return this.konkaBoothsDao.updateEntity(t);
	}

	public int removeKonkaBooths(KonkaBooths t) {
		return this.konkaBoothsDao.deleteEntity(t);
	}

	public List<KonkaBooths> getKonkaBoothsPaginatedList(KonkaBooths t) {
		return this.konkaBoothsDao.selectEntityPaginatedList(t);
	}

}
