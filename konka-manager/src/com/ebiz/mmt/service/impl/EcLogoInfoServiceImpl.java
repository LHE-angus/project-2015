package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcLogoInfoDao;
import com.ebiz.mmt.domain.EcLogoInfo;
import com.ebiz.mmt.service.EcLogoInfoService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-12-11 15:10:42
 */
@Service
public class EcLogoInfoServiceImpl implements EcLogoInfoService {

	@Resource
	private EcLogoInfoDao ecLogoInfoDao;
	

	public Long createEcLogoInfo(EcLogoInfo t) {
		return this.ecLogoInfoDao.insertEntity(t);
	}

	public EcLogoInfo getEcLogoInfo(EcLogoInfo t) {
		return this.ecLogoInfoDao.selectEntity(t);
	}

	public Long getEcLogoInfoCount(EcLogoInfo t) {
		return this.ecLogoInfoDao.selectEntityCount(t);
	}

	public List<EcLogoInfo> getEcLogoInfoList(EcLogoInfo t) {
		return this.ecLogoInfoDao.selectEntityList(t);
	}

	public int modifyEcLogoInfo(EcLogoInfo t) {
		return this.ecLogoInfoDao.updateEntity(t);
	}

	public int removeEcLogoInfo(EcLogoInfo t) {
		return this.ecLogoInfoDao.deleteEntity(t);
	}

	public List<EcLogoInfo> getEcLogoInfoPaginatedList(EcLogoInfo t) {
		return this.ecLogoInfoDao.selectEntityPaginatedList(t);
	}

}
