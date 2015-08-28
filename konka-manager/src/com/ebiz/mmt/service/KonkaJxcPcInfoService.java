package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaJxcPcInfo;

/**
 * @author Wu,Yang
 * @version 2011-10-11 09:18
 */
public interface KonkaJxcPcInfoService {

	Long createKonkaJxcPcInfo(KonkaJxcPcInfo t);

	int modifyKonkaJxcPcInfo(KonkaJxcPcInfo t);

	int removeKonkaJxcPcInfo(KonkaJxcPcInfo t);

	KonkaJxcPcInfo getKonkaJxcPcInfo(KonkaJxcPcInfo t);

	List<KonkaJxcPcInfo> getKonkaJxcPcInfoList(KonkaJxcPcInfo t);

	Long getKonkaJxcPcInfoCount(KonkaJxcPcInfo t);

	List<KonkaJxcPcInfo> getKonkaJxcPcInfoPaginatedList(KonkaJxcPcInfo t);

}