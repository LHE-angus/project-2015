package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaXxZmdStore;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2012-01-11 10:56:03
 */
public interface KonkaXxZmdStoreService {

	Long createKonkaXxZmdStore(KonkaXxZmdStore t);

	int modifyKonkaXxZmdStore(KonkaXxZmdStore t);

	int removeKonkaXxZmdStore(KonkaXxZmdStore t);

	KonkaXxZmdStore getKonkaXxZmdStore(KonkaXxZmdStore t);

	List<KonkaXxZmdStore> getKonkaXxZmdStoreList(KonkaXxZmdStore t);

	Long getKonkaXxZmdStoreCount(KonkaXxZmdStore t);

	List<KonkaXxZmdStore> getKonkaXxZmdStorePaginatedList(KonkaXxZmdStore t);
	
	/**
	 * @author Ren,zhong
	 * @version 2011-01-11
	 */
	List<KonkaXxZmdStore> getSalesOrderDeliveryForStocksResultList(KonkaXxZmdStore t);

}