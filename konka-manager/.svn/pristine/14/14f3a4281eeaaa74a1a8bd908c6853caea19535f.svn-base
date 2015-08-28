package com.ebiz.mmt.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaAuditListInfoDao;
import com.ebiz.mmt.domain.KonkaAuditListInfo;
import com.ebiz.mmt.service.KonkaAuditListInfoService;


@Service
public class KonkaAuditListInfoServiceImpl implements KonkaAuditListInfoService {

	@Resource
	private KonkaAuditListInfoDao konkaAuditListInfoDao;
	

	public Long createKonkaAuditListInfo(KonkaAuditListInfo t) {
		return this.konkaAuditListInfoDao.insertEntity(t);
	}

	public KonkaAuditListInfo getKonkaAuditListInfo(KonkaAuditListInfo t) {
		return this.konkaAuditListInfoDao.selectEntity(t);
	}

	public Long getKonkaAuditListInfoCount(KonkaAuditListInfo t) {
		return this.konkaAuditListInfoDao.selectEntityCount(t);
	}

	public List<KonkaAuditListInfo> getKonkaAuditListInfoList(KonkaAuditListInfo t) {
		return this.konkaAuditListInfoDao.selectEntityList(t);
	}

	public int modifyKonkaAuditListInfo(KonkaAuditListInfo t) {
		return this.konkaAuditListInfoDao.updateEntity(t);
	}

	public int removeKonkaAuditListInfo(KonkaAuditListInfo t) {
		return this.konkaAuditListInfoDao.deleteEntity(t);
	}

	public List<KonkaAuditListInfo> getKonkaAuditListInfoPaginatedList(KonkaAuditListInfo t) {
		return this.konkaAuditListInfoDao.selectEntityPaginatedList(t);
	}

	@Override
	public List<HashMap> getKonkaAuditListInfoListForMap(KonkaAuditListInfo t) {
		return this.konkaAuditListInfoDao.selectKonkaAuditListInfoListForMap(t);
	}

	@Override
	public HashMap getKonkaAuditListInfoForMap(KonkaAuditListInfo t) {
		return this.konkaAuditListInfoDao.selectKonkaAuditListInfoForMap(t);
	}

	@Override
	public int modifyKonkaAuditListInfoForNew(KonkaAuditListInfo t) {
		return this.konkaAuditListInfoDao.updateKonkaAuditListInfoForNew(t);
	}

}
