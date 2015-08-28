package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcOrderExpressInfoDao;
import com.ebiz.mmt.domain.EcOrderExpressInfo;
import com.ebiz.mmt.service.EcOrderExpressInfoService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-12-18 10:23:43
 */
@Service
public class EcOrderExpressInfoServiceImpl implements EcOrderExpressInfoService {

	@Resource
	private EcOrderExpressInfoDao ecOrderExpressInfoDao;
	

	public Long createEcOrderExpressInfo(EcOrderExpressInfo t) {
		return this.ecOrderExpressInfoDao.insertEntity(t);
	}

	public EcOrderExpressInfo getEcOrderExpressInfo(EcOrderExpressInfo t) {
		return this.ecOrderExpressInfoDao.selectEntity(t);
	}

	public Long getEcOrderExpressInfoCount(EcOrderExpressInfo t) {
		return this.ecOrderExpressInfoDao.selectEntityCount(t);
	}

	public List<EcOrderExpressInfo> getEcOrderExpressInfoList(EcOrderExpressInfo t) {
		return this.ecOrderExpressInfoDao.selectEntityList(t);
	}

	public int modifyEcOrderExpressInfo(EcOrderExpressInfo t) {
		return this.ecOrderExpressInfoDao.updateEntity(t);
	}

	public int removeEcOrderExpressInfo(EcOrderExpressInfo t) {
		return this.ecOrderExpressInfoDao.deleteEntity(t);
	}

	public List<EcOrderExpressInfo> getEcOrderExpressInfoPaginatedList(EcOrderExpressInfo t) {
		return this.ecOrderExpressInfoDao.selectEntityPaginatedList(t);
	}

}
