package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaSalesmanInfoDao;
import com.ebiz.mmt.domain.KonkaSalesmanInfo;
import com.ebiz.mmt.service.KonkaSalesmanInfoService;


@Service
public class KonkaSalesmanInfoServiceImpl implements KonkaSalesmanInfoService {

	@Resource
	private KonkaSalesmanInfoDao konkaSalesmanInfoDao;
	

	public Long createKonkaSalesmanInfo(KonkaSalesmanInfo t) {
		return this.konkaSalesmanInfoDao.insertEntity(t);
	}

	public KonkaSalesmanInfo getKonkaSalesmanInfo(KonkaSalesmanInfo t) {
		return this.konkaSalesmanInfoDao.selectEntity(t);
	}

	public Long getKonkaSalesmanInfoCount(KonkaSalesmanInfo t) {
		return this.konkaSalesmanInfoDao.selectEntityCount(t);
	}

	public List<KonkaSalesmanInfo> getKonkaSalesmanInfoList(KonkaSalesmanInfo t) {
		return this.konkaSalesmanInfoDao.selectEntityList(t);
	}

	public int modifyKonkaSalesmanInfo(KonkaSalesmanInfo t) {
		return this.konkaSalesmanInfoDao.updateEntity(t);
	}

	public int removeKonkaSalesmanInfo(KonkaSalesmanInfo t) {
		return this.konkaSalesmanInfoDao.deleteEntity(t);
	}

	public List<KonkaSalesmanInfo> getKonkaSalesmanInfoPaginatedList(KonkaSalesmanInfo t) {
		return this.konkaSalesmanInfoDao.selectEntityPaginatedList(t);
	}

}
