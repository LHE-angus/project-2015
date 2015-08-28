package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaActInfo;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-11-06 14:11:44
 */
public interface KonkaActInfoService {

	Long createKonkaActInfo(KonkaActInfo t);

	int modifyKonkaActInfo(KonkaActInfo t);

	int removeKonkaActInfo(KonkaActInfo t);

	KonkaActInfo getKonkaActInfo(KonkaActInfo t);

	List<KonkaActInfo> getKonkaActInfoList(KonkaActInfo t);

	Long getKonkaActInfoCount(KonkaActInfo t);

	List<KonkaActInfo> getKonkaActInfoPaginatedList(KonkaActInfo t);

	Long getKonkaActInfoNoMax(KonkaActInfo t);

	List<KonkaActInfo> getKonkaActInfoAndSaleDatasList(KonkaActInfo t);

}