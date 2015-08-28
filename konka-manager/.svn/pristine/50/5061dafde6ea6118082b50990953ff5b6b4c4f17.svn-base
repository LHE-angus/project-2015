package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcBaseCardChangeInfoDao;
import com.ebiz.mmt.domain.EcBaseCardChangeInfo;
import com.ebiz.mmt.service.EcBaseCardChangeInfoService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-11-14 15:05:08
 */
@Service
public class EcBaseCardChangeInfoServiceImpl implements EcBaseCardChangeInfoService {

	@Resource
	private EcBaseCardChangeInfoDao ecBaseCardChangeInfoDao;
	

	public Long createEcBaseCardChangeInfo(EcBaseCardChangeInfo t) {
		return this.ecBaseCardChangeInfoDao.insertEntity(t);
	}

	public EcBaseCardChangeInfo getEcBaseCardChangeInfo(EcBaseCardChangeInfo t) {
		return this.ecBaseCardChangeInfoDao.selectEntity(t);
	}

	public Long getEcBaseCardChangeInfoCount(EcBaseCardChangeInfo t) {
		return this.ecBaseCardChangeInfoDao.selectEntityCount(t);
	}

	public List<EcBaseCardChangeInfo> getEcBaseCardChangeInfoList(EcBaseCardChangeInfo t) {
		return this.ecBaseCardChangeInfoDao.selectEntityList(t);
	}

	public int modifyEcBaseCardChangeInfo(EcBaseCardChangeInfo t) {
		return this.ecBaseCardChangeInfoDao.updateEntity(t);
	}

	public int removeEcBaseCardChangeInfo(EcBaseCardChangeInfo t) {
		return this.ecBaseCardChangeInfoDao.deleteEntity(t);
	}

	public List<EcBaseCardChangeInfo> getEcBaseCardChangeInfoPaginatedList(EcBaseCardChangeInfo t) {
		return this.ecBaseCardChangeInfoDao.selectEntityPaginatedList(t);
	}

}
