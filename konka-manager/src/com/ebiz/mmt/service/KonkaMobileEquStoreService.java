package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaMobileEquStore;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-04-22 15:41:58
 */
public interface KonkaMobileEquStoreService {

	Long createKonkaMobileEquStore(KonkaMobileEquStore t);

	int modifyKonkaMobileEquStore(KonkaMobileEquStore t);

	int removeKonkaMobileEquStore(KonkaMobileEquStore t);

	KonkaMobileEquStore getKonkaMobileEquStore(KonkaMobileEquStore t);

	List<KonkaMobileEquStore> getKonkaMobileEquStoreList(KonkaMobileEquStore t);

	Long getKonkaMobileEquStoreCount(KonkaMobileEquStore t);

	List<KonkaMobileEquStore> getKonkaMobileEquStorePaginatedList(KonkaMobileEquStore t);

}