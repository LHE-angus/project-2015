package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcBuyInfoDao;
import com.ebiz.mmt.domain.EcBuyInfo;
import com.ebiz.mmt.service.EcBuyInfoService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-12-18 10:23:43
 */
@Service
public class EcBuyInfoServiceImpl implements EcBuyInfoService {

	@Resource
	private EcBuyInfoDao ecBuyInfoDao;
	

	public Long createEcBuyInfo(EcBuyInfo t) {
		return this.ecBuyInfoDao.insertEntity(t);
	}

	public EcBuyInfo getEcBuyInfo(EcBuyInfo t) {
		return this.ecBuyInfoDao.selectEntity(t);
	}

	public Long getEcBuyInfoCount(EcBuyInfo t) {
		return this.ecBuyInfoDao.selectEntityCount(t);
	}

	public List<EcBuyInfo> getEcBuyInfoList(EcBuyInfo t) {
		return this.ecBuyInfoDao.selectEntityList(t);
	}

	public int modifyEcBuyInfo(EcBuyInfo t) {
		return this.ecBuyInfoDao.updateEntity(t);
	}

	public int removeEcBuyInfo(EcBuyInfo t) {
		return this.ecBuyInfoDao.deleteEntity(t);
	}

	public List<EcBuyInfo> getEcBuyInfoPaginatedList(EcBuyInfo t) {
		return this.ecBuyInfoDao.selectEntityPaginatedList(t);
	}

}
