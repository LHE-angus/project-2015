package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcSenderOrderInfoDao;
import com.ebiz.mmt.domain.EcSenderOrderInfo;
import com.ebiz.mmt.service.EcSenderOrderInfoService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-07-03 16:39:53
 */
@Service
public class EcSenderOrderInfoServiceImpl implements EcSenderOrderInfoService {

	@Resource
	private EcSenderOrderInfoDao ecSenderOrderInfoDao;
	

	public Long createEcSenderOrderInfo(EcSenderOrderInfo t) {
		return this.ecSenderOrderInfoDao.insertEntity(t);
	}

	public EcSenderOrderInfo getEcSenderOrderInfo(EcSenderOrderInfo t) {
		return this.ecSenderOrderInfoDao.selectEntity(t);
	}

	public Long getEcSenderOrderInfoCount(EcSenderOrderInfo t) {
		return this.ecSenderOrderInfoDao.selectEntityCount(t);
	}

	public List<EcSenderOrderInfo> getEcSenderOrderInfoList(EcSenderOrderInfo t) {
		return this.ecSenderOrderInfoDao.selectEntityList(t);
	}

	public int modifyEcSenderOrderInfo(EcSenderOrderInfo t) {
		return this.ecSenderOrderInfoDao.updateEntity(t);
	}

	public int removeEcSenderOrderInfo(EcSenderOrderInfo t) {
		return this.ecSenderOrderInfoDao.deleteEntity(t);
	}

	public List<EcSenderOrderInfo> getEcSenderOrderInfoPaginatedList(EcSenderOrderInfo t) {
		return this.ecSenderOrderInfoDao.selectEntityPaginatedList(t);
	}

}
