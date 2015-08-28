package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.EcBaseStore;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-09-09 10:06:25
 */
public interface EcBaseStoreService {

	Long createEcBaseStore(EcBaseStore t);

	int modifyEcBaseStore(EcBaseStore t);

	int removeEcBaseStore(EcBaseStore t);

	EcBaseStore getEcBaseStore(EcBaseStore t);

	List<EcBaseStore> getEcBaseStoreList(EcBaseStore t);

	Long getEcBaseStoreCount(EcBaseStore t);

	List<EcBaseStore> getEcBaseStorePaginatedList(EcBaseStore t);

	List<EcBaseStore> getEcBaseStoreForPdList(EcBaseStore t);

	Long createEcBaseStoreWithPindex(EcBaseStore t);

	Long createEcBaseStoreWithPindexArea(EcBaseStore t, String e_id);

	Long getEcBaseStoreForAreaCount(EcBaseStore t);

	/**
	 * 
	 * @author Pan,Gang
	 * @date 2013-09-15
	 */
	List<EcBaseStore> getEcBaseStoreForDeptNamePaginatedList(EcBaseStore t);

	Long getEcBaseStoreForDeptCount(EcBaseStore t);

	int modifyEcBaseStoreAndArea(EcBaseStore t, String store_id, String e_id);

	List<EcBaseStore> getEcBaseStoreForAreaForList(EcBaseStore t);

	List<EcBaseStore> getEcBaseStoreForPindexIdForList(EcBaseStore t);

	List<EcBaseStore> getEcBaseStoreForPindexAndPnameList(EcBaseStore t);
}