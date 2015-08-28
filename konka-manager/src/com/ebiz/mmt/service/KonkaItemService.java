package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaItem;

/**
 * @author Ren,Peng
 * @version 2011-10-20 16:41
 */
public interface KonkaItemService {

	Long createKonkaItem(KonkaItem t);

	int modifyKonkaItem(KonkaItem t);

	int removeKonkaItem(KonkaItem t);

	KonkaItem getKonkaItem(KonkaItem t);

	List<KonkaItem> getKonkaItemList(KonkaItem t);

	Long getKonkaItemCount(KonkaItem t);

	List<KonkaItem> getKonkaItemPaginatedList(KonkaItem t);

}