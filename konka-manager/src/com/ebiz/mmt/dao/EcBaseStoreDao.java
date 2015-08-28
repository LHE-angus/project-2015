package com.ebiz.mmt.dao;

import java.util.List;

import com.ebiz.mmt.domain.EcBaseStore;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-09-09 10:06:25
 */
public interface EcBaseStoreDao extends EntityDao<EcBaseStore> {

	List<EcBaseStore> selectEcBaseStoreForPdList(EcBaseStore t);

	Long selectEcBaseStoreForAreaCount(EcBaseStore t);

	/**
	 * 
	 * @author Pan,Gang
	 * @date 2013-09-15
	 */
	List<EcBaseStore> selectEcBaseStoreForDeptNamePaginatedList(EcBaseStore t);

	Long selectEcBaseStoreForDeptCount(EcBaseStore t);

	List<EcBaseStore> selectEcBaseStoreForAreaForList(EcBaseStore t);

	List<EcBaseStore> selectEcBaseStoreForPindexIdForList(EcBaseStore t);

	List<EcBaseStore> selectEcBaseStoreForPindexAndPnameList(EcBaseStore t);
}
