package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcNavInfoDao;
import com.ebiz.mmt.domain.EcNavInfo;
import com.ebiz.mmt.service.EcNavInfoService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-12-11 15:10:42
 */
@Service
public class EcNavInfoServiceImpl implements EcNavInfoService {

	@Resource
	private EcNavInfoDao ecNavInfoDao;
	

	public Long createEcNavInfo(EcNavInfo t) {
		return this.ecNavInfoDao.insertEntity(t);
	}

	public EcNavInfo getEcNavInfo(EcNavInfo t) {
		return this.ecNavInfoDao.selectEntity(t);
	}

	public Long getEcNavInfoCount(EcNavInfo t) {
		return this.ecNavInfoDao.selectEntityCount(t);
	}

	public List<EcNavInfo> getEcNavInfoList(EcNavInfo t) {
		return this.ecNavInfoDao.selectEntityList(t);
	}

	public int modifyEcNavInfo(EcNavInfo t) {
		return this.ecNavInfoDao.updateEntity(t);
	}

	public int removeEcNavInfo(EcNavInfo t) {
		return this.ecNavInfoDao.deleteEntity(t);
	}

	public List<EcNavInfo> getEcNavInfoPaginatedList(EcNavInfo t) {
		return this.ecNavInfoDao.selectEntityPaginatedList(t);
	}

}
