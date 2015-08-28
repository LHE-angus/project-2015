package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaSendMailInfoDao;
import com.ebiz.mmt.domain.KonkaSendMailInfo;
import com.ebiz.mmt.service.KonkaSendMailInfoService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-11-02 13:27:08
 */
@Service
public class KonkaSendMailInfoServiceImpl implements KonkaSendMailInfoService {

	@Resource
	private KonkaSendMailInfoDao konkaSendMailInfoDao;
	

	public Long createKonkaSendMailInfo(KonkaSendMailInfo t) {
		return this.konkaSendMailInfoDao.insertEntity(t);
	}

	public KonkaSendMailInfo getKonkaSendMailInfo(KonkaSendMailInfo t) {
		return this.konkaSendMailInfoDao.selectEntity(t);
	}

	public Long getKonkaSendMailInfoCount(KonkaSendMailInfo t) {
		return this.konkaSendMailInfoDao.selectEntityCount(t);
	}

	public List<KonkaSendMailInfo> getKonkaSendMailInfoList(KonkaSendMailInfo t) {
		return this.konkaSendMailInfoDao.selectEntityList(t);
	}

	public int modifyKonkaSendMailInfo(KonkaSendMailInfo t) {
		return this.konkaSendMailInfoDao.updateEntity(t);
	}

	public int removeKonkaSendMailInfo(KonkaSendMailInfo t) {
		return this.konkaSendMailInfoDao.deleteEntity(t);
	}

	public List<KonkaSendMailInfo> getKonkaSendMailInfoPaginatedList(KonkaSendMailInfo t) {
		return this.konkaSendMailInfoDao.selectEntityPaginatedList(t);
	}

}
