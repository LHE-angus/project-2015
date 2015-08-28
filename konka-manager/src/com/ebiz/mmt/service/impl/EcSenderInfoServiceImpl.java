package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcSenderInfoDao;
import com.ebiz.mmt.domain.EcSenderInfo;
import com.ebiz.mmt.service.EcSenderInfoService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-07-03 16:39:53
 */
@Service
public class EcSenderInfoServiceImpl implements EcSenderInfoService {

	@Resource
	private EcSenderInfoDao ecSenderInfoDao;
	

	public Long createEcSenderInfo(EcSenderInfo t) {
		return this.ecSenderInfoDao.insertEntity(t);
	}

	public EcSenderInfo getEcSenderInfo(EcSenderInfo t) {
		return this.ecSenderInfoDao.selectEntity(t);
	}

	public Long getEcSenderInfoCount(EcSenderInfo t) {
		return this.ecSenderInfoDao.selectEntityCount(t);
	}

	public List<EcSenderInfo> getEcSenderInfoList(EcSenderInfo t) {
		return this.ecSenderInfoDao.selectEntityList(t);
	}

	public int modifyEcSenderInfo(EcSenderInfo t) {
		return this.ecSenderInfoDao.updateEntity(t);
	}

	public int removeEcSenderInfo(EcSenderInfo t) {
		return this.ecSenderInfoDao.deleteEntity(t);
	}

	public List<EcSenderInfo> getEcSenderInfoPaginatedList(EcSenderInfo t) {
		return this.ecSenderInfoDao.selectEntityPaginatedList(t);
	}

}
