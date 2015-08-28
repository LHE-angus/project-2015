package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaSpActivityManager;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-01-23 11:36:10
 */
public interface KonkaSpActivityManagerService {

	Long createKonkaSpActivityManager(KonkaSpActivityManager t);

	int modifyKonkaSpActivityManager(KonkaSpActivityManager t);

	int removeKonkaSpActivityManager(KonkaSpActivityManager t);

	KonkaSpActivityManager getKonkaSpActivityManager(KonkaSpActivityManager t);

	List<KonkaSpActivityManager> getKonkaSpActivityManagerList(KonkaSpActivityManager t);

	List<KonkaSpActivityManager> getKonkaSpActivityManagerListForExcel(KonkaSpActivityManager t);

	Long getKonkaSpActivityManagerCount(KonkaSpActivityManager t);

	List<KonkaSpActivityManager> getKonkaSpActivityManagerPaginatedList(KonkaSpActivityManager t);

	List<KonkaSpActivityManager> getKonkaSpActivityManagerListForBookReport(
			KonkaSpActivityManager entity);

}