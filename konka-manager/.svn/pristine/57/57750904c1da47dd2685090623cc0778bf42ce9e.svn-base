package com.ebiz.mmt.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaOrderInfoTransEnsuDao;
import com.ebiz.mmt.domain.KonkaOrderInfoTransEnsu;
import com.ebiz.mmt.service.KonkaOrderInfoTransEnsuService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-05-24 17:30:54
 */
@Service
public class KonkaOrderInfoTransEnsuServiceImpl implements KonkaOrderInfoTransEnsuService {

	@Resource
	private KonkaOrderInfoTransEnsuDao konkaOrderInfoTransEnsuDao;
	

	public Long createKonkaOrderInfoTransEnsu(KonkaOrderInfoTransEnsu t) {
		return this.konkaOrderInfoTransEnsuDao.insertEntity(t);
	}

	public KonkaOrderInfoTransEnsu getKonkaOrderInfoTransEnsu(KonkaOrderInfoTransEnsu t) {
		return this.konkaOrderInfoTransEnsuDao.selectEntity(t);
	}

	public Long getKonkaOrderInfoTransEnsuCount(KonkaOrderInfoTransEnsu t) {
		return this.konkaOrderInfoTransEnsuDao.selectEntityCount(t);
	}

	public List<KonkaOrderInfoTransEnsu> getKonkaOrderInfoTransEnsuList(KonkaOrderInfoTransEnsu t) {
		return this.konkaOrderInfoTransEnsuDao.selectEntityList(t);
	}

	public int modifyKonkaOrderInfoTransEnsu(KonkaOrderInfoTransEnsu t) {
		return this.konkaOrderInfoTransEnsuDao.updateEntity(t);
	}

	public int removeKonkaOrderInfoTransEnsu(KonkaOrderInfoTransEnsu t) {
		return this.konkaOrderInfoTransEnsuDao.deleteEntity(t);
	}

	public List<KonkaOrderInfoTransEnsu> getKonkaOrderInfoTransEnsuPaginatedList(KonkaOrderInfoTransEnsu t) {
		return this.konkaOrderInfoTransEnsuDao.selectEntityPaginatedList(t);
	}

	public Long getKonkaOrderInfoTransEnsurSumEnsured(KonkaOrderInfoTransEnsu t) {
		return this.konkaOrderInfoTransEnsuDao.selectKonkaOrderInfoTransEnsurSumEnsured(t);
	}

	@Override
	public Long getKonkaOrderInfoTransEnsuAndDetailsCount(KonkaOrderInfoTransEnsu konkaOrderInfoTransEnsu) {
		
		return this.konkaOrderInfoTransEnsuDao.selectKonkaOrderInfoTransEnsuAndDetailsCount(konkaOrderInfoTransEnsu);
	}

	@Override
	public List<KonkaOrderInfoTransEnsu> getKonkaOrderInfoTransEnsuAndDetailsPaginatedList(
			KonkaOrderInfoTransEnsu konkaOrderInfoTransEnsu) {
		
		return this.konkaOrderInfoTransEnsuDao.selectKonkaOrderInfoTransEnsuAndDetailsPaginatedList(konkaOrderInfoTransEnsu);
	}

}
