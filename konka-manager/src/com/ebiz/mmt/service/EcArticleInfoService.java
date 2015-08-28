package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.EcArticleInfo;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2015-01-12 16:46:53
 */
public interface EcArticleInfoService {

	Long createEcArticleInfo(EcArticleInfo t);

	int modifyEcArticleInfo(EcArticleInfo t);

	int removeEcArticleInfo(EcArticleInfo t);

	EcArticleInfo getEcArticleInfo(EcArticleInfo t);

	List<EcArticleInfo> getEcArticleInfoList(EcArticleInfo t);

	Long getEcArticleInfoCount(EcArticleInfo t);

	List<EcArticleInfo> getEcArticleInfoPaginatedList(EcArticleInfo t);

}