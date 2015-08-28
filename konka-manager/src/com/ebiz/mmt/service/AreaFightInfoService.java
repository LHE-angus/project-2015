package com.ebiz.mmt.service;

import java.util.HashMap;
import java.util.List;

import com.ebiz.mmt.domain.AreaFightInfo;


public interface AreaFightInfoService {

	Long createAreaFightInfo(AreaFightInfo t);

	int modifyAreaFightInfo(AreaFightInfo t);

	int removeAreaFightInfo(AreaFightInfo t);

	AreaFightInfo getAreaFightInfo(AreaFightInfo t);

	List<AreaFightInfo> getAreaFightInfoList(AreaFightInfo t);

	Long getAreaFightInfoCount(AreaFightInfo t);

	List<AreaFightInfo> getAreaFightInfoPaginatedList(AreaFightInfo t);

	Long getTotalCount(AreaFightInfo t);
	
	Long getDetailCount(AreaFightInfo t);
	
	List<HashMap> getAreaFightListForMap(AreaFightInfo t);
	
	List<HashMap> getAreaFightDetailList(AreaFightInfo t);
	
	AreaFightInfo getAreaFightDetail(AreaFightInfo t);
}