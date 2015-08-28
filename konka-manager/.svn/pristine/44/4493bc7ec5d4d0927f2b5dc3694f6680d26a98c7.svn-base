package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcArticleInfoDao;
import com.ebiz.mmt.domain.EcArticleInfo;
import com.ebiz.mmt.service.EcArticleInfoService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2015-01-12 16:46:53
 */
@Service
public class EcArticleInfoServiceImpl implements EcArticleInfoService {

	@Resource
	private EcArticleInfoDao ecArticleInfoDao;
	

	public Long createEcArticleInfo(EcArticleInfo t) {
		return this.ecArticleInfoDao.insertEntity(t);
	}

	public EcArticleInfo getEcArticleInfo(EcArticleInfo t) {
		return this.ecArticleInfoDao.selectEntity(t);
	}

	public Long getEcArticleInfoCount(EcArticleInfo t) {
		return this.ecArticleInfoDao.selectEntityCount(t);
	}

	public List<EcArticleInfo> getEcArticleInfoList(EcArticleInfo t) {
		return this.ecArticleInfoDao.selectEntityList(t);
	}

	public int modifyEcArticleInfo(EcArticleInfo t) {
		return this.ecArticleInfoDao.updateEntity(t);
	}

	public int removeEcArticleInfo(EcArticleInfo t) {
		return this.ecArticleInfoDao.deleteEntity(t);
	}

	public List<EcArticleInfo> getEcArticleInfoPaginatedList(EcArticleInfo t) {
		return this.ecArticleInfoDao.selectEntityPaginatedList(t);
	}

}
