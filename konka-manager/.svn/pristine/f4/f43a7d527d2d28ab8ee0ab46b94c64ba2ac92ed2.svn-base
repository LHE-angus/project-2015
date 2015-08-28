package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcQaInfoDao;
import com.ebiz.mmt.domain.EcQaInfo;
import com.ebiz.mmt.service.EcQaInfoService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-09-09 10:06:25
 */
@Service
public class EcQaInfoServiceImpl implements EcQaInfoService {

	@Resource
	private EcQaInfoDao ecQaInfoDao;
	

	public Long createEcQaInfo(EcQaInfo t) {
		return this.ecQaInfoDao.insertEntity(t);
	}

	public EcQaInfo getEcQaInfo(EcQaInfo t) {
		return this.ecQaInfoDao.selectEntity(t);
	}

	public Long getEcQaInfoCount(EcQaInfo t) {
		return this.ecQaInfoDao.selectEntityCount(t);
	}

	public List<EcQaInfo> getEcQaInfoList(EcQaInfo t) {
		return this.ecQaInfoDao.selectEntityList(t);
	}

	public int modifyEcQaInfo(EcQaInfo t) {
		return this.ecQaInfoDao.updateEntity(t);
	}

	public int removeEcQaInfo(EcQaInfo t) {
		return this.ecQaInfoDao.deleteEntity(t);
	}

	public List<EcQaInfo> getEcQaInfoPaginatedList(EcQaInfo t) {
		return this.ecQaInfoDao.selectEntityPaginatedList(t);
	}

}
