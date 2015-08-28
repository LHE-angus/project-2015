package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.EcLuckyMain;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-07-11 16:03:36
 */
public interface EcLuckyMainService {

	Long createEcLuckyMain(EcLuckyMain t);

	int modifyEcLuckyMain(EcLuckyMain t);

	int removeEcLuckyMain(EcLuckyMain t);

	EcLuckyMain getEcLuckyMain(EcLuckyMain t);

	List<EcLuckyMain> getEcLuckyMainList(EcLuckyMain t);

	Long getEcLuckyMainCount(EcLuckyMain t);

	List<EcLuckyMain> getEcLuckyMainPaginatedList(EcLuckyMain t);

	Long createEcLuckyMain(EcLuckyMain t, String e_id);

	int modifyEcLuckyMain(EcLuckyMain t, String[] had_ids, String e_id);
	
	int modifyEcLuckyMainViewCounts(EcLuckyMain t);

}