package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaXxPropValItem;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-04-01 16:53:38
 */
public interface KonkaXxPropValItemService {

	Long createKonkaXxPropValItem(KonkaXxPropValItem t);

	int modifyKonkaXxPropValItem(KonkaXxPropValItem t);

	int removeKonkaXxPropValItem(KonkaXxPropValItem t);

	KonkaXxPropValItem getKonkaXxPropValItem(KonkaXxPropValItem t);

	List<KonkaXxPropValItem> getKonkaXxPropValItemList(KonkaXxPropValItem t);

	Long getKonkaXxPropValItemCount(KonkaXxPropValItem t);

	List<KonkaXxPropValItem> getKonkaXxPropValItemPaginatedList(KonkaXxPropValItem t);

}