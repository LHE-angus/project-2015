package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaPlanRatio;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-07-09 15:32:09
 */
public interface KonkaPlanRatioService {

	Long createKonkaPlanRatio(KonkaPlanRatio t);

	int modifyKonkaPlanRatio(KonkaPlanRatio t);

	int removeKonkaPlanRatio(KonkaPlanRatio t);

	KonkaPlanRatio getKonkaPlanRatio(KonkaPlanRatio t);

	List<KonkaPlanRatio> getKonkaPlanRatioList(KonkaPlanRatio t);

	Long getKonkaPlanRatioCount(KonkaPlanRatio t);

	List<KonkaPlanRatio> getKonkaPlanRatioPaginatedList(KonkaPlanRatio t);

	List<KonkaPlanRatio> selectKonkaPlanRatioListForFgs(KonkaPlanRatio t);

	Long createKonkaPlanRatioForExcel(List<KonkaPlanRatio> entityList);

}