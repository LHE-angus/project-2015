package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcUserLevelAuditInfoDao;
import com.ebiz.mmt.domain.EcUserLevelAuditInfo;
import com.ebiz.mmt.service.EcUserLevelAuditInfoService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-01-15 18:37:41
 */
@Service
public class EcUserLevelAuditInfoServiceImpl implements EcUserLevelAuditInfoService {

	@Resource
	private EcUserLevelAuditInfoDao ecUserLevelAuditInfoDao;
	

	public Long createEcUserLevelAuditInfo(EcUserLevelAuditInfo t) {
		return this.ecUserLevelAuditInfoDao.insertEntity(t);
	}

	public EcUserLevelAuditInfo getEcUserLevelAuditInfo(EcUserLevelAuditInfo t) {
		return this.ecUserLevelAuditInfoDao.selectEntity(t);
	}

	public Long getEcUserLevelAuditInfoCount(EcUserLevelAuditInfo t) {
		return this.ecUserLevelAuditInfoDao.selectEntityCount(t);
	}

	public List<EcUserLevelAuditInfo> getEcUserLevelAuditInfoList(EcUserLevelAuditInfo t) {
		return this.ecUserLevelAuditInfoDao.selectEntityList(t);
	}

	public int modifyEcUserLevelAuditInfo(EcUserLevelAuditInfo t) {
		return this.ecUserLevelAuditInfoDao.updateEntity(t);
	}

	public int removeEcUserLevelAuditInfo(EcUserLevelAuditInfo t) {
		return this.ecUserLevelAuditInfoDao.deleteEntity(t);
	}

	public List<EcUserLevelAuditInfo> getEcUserLevelAuditInfoPaginatedList(EcUserLevelAuditInfo t) {
		return this.ecUserLevelAuditInfoDao.selectEntityPaginatedList(t);
	}

}
