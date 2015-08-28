package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaXxPdHistory;

/**
 * @author Xu,XiaoYuan
 * @version 2012-03-02 09:12
 */
public interface KonkaXxPdHistoryService {

	Long createKonkaXxPdHistory(KonkaXxPdHistory t);

	int modifyKonkaXxPdHistory(KonkaXxPdHistory t);

	int removeKonkaXxPdHistory(KonkaXxPdHistory t);

	KonkaXxPdHistory getKonkaXxPdHistory(KonkaXxPdHistory t);

	List<KonkaXxPdHistory> getKonkaXxPdHistoryList(KonkaXxPdHistory t);

	Long getKonkaXxPdHistoryCount(KonkaXxPdHistory t);

	List<KonkaXxPdHistory> getKonkaXxPdHistoryPaginatedList(KonkaXxPdHistory t);
	
	/**
	 * 
	 * @auther Hu,Hao
	 * @version 2012-3-31
	 */
	List<KonkaXxPdHistory> getKonkaXxPdHistoryListForHistoryId(KonkaXxPdHistory t);
}