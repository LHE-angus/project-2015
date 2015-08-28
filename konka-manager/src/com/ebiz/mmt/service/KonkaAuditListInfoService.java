package com.ebiz.mmt.service;

import java.util.HashMap;
import java.util.List;

import com.ebiz.mmt.domain.KonkaAuditListInfo;


public interface KonkaAuditListInfoService {

	Long createKonkaAuditListInfo(KonkaAuditListInfo t);

	int modifyKonkaAuditListInfo(KonkaAuditListInfo t);

	int removeKonkaAuditListInfo(KonkaAuditListInfo t);

	KonkaAuditListInfo getKonkaAuditListInfo(KonkaAuditListInfo t);

	List<KonkaAuditListInfo> getKonkaAuditListInfoList(KonkaAuditListInfo t);

	Long getKonkaAuditListInfoCount(KonkaAuditListInfo t);

	List<KonkaAuditListInfo> getKonkaAuditListInfoPaginatedList(KonkaAuditListInfo t);

	List<HashMap> getKonkaAuditListInfoListForMap(KonkaAuditListInfo t);
	
	HashMap getKonkaAuditListInfoForMap(KonkaAuditListInfo t);
	
	/**
	 * 根据类型，ID更新数据
	 * @param t
	 * @return
	 */
	int modifyKonkaAuditListInfoForNew(KonkaAuditListInfo t);
}