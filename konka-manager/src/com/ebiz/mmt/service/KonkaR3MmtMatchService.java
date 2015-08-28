package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaR3MmtMatch;

/**
 * Code by AutoGenerated Builder AutoGenerated Builder Version 2.1
 * 
 * @author Hui,Gang
 * @datetime 2011-09-24 14:50:49
 */
public interface KonkaR3MmtMatchService {

	Long createKonkaR3MmtMatch(KonkaR3MmtMatch t);

	int modifyKonkaR3MmtMatch(KonkaR3MmtMatch t);

	int removeKonkaR3MmtMatch(KonkaR3MmtMatch t);

	KonkaR3MmtMatch getKonkaR3MmtMatch(KonkaR3MmtMatch t);

	KonkaR3MmtMatch getKonkaR3MmtMatchBySelf(KonkaR3MmtMatch t);

	List<KonkaR3MmtMatch> getKonkaR3MmtMatchList(KonkaR3MmtMatch t);

	Long getKonkaR3MmtMatchCount(KonkaR3MmtMatch t);

	List<KonkaR3MmtMatch> getKonkaR3MmtMatchPaginatedList(KonkaR3MmtMatch t);

	void match(String r3_shop_id, String mmt_shop_id, String mmt_shop_name);


	KonkaR3MmtMatch getKonkaR3MmtMatchByR3ShopId(KonkaR3MmtMatch t);
}