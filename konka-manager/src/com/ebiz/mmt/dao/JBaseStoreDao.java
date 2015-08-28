package com.ebiz.mmt.dao;

import java.util.HashMap;
import java.util.List;

import com.ebiz.mmt.domain.JBaseStore;
import com.ebiz.mmt.domain.JBaseStoreR3;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-06-08 17:03:35
 */
public interface JBaseStoreDao extends EntityDao<JBaseStore> {

	List<JBaseStoreR3> selectStoreSnWeForList(JBaseStoreR3 jBaseStoreR3);

	JBaseStore selectJBaseStoreAndDetails(JBaseStore entity);

	/**
	 * 获取仓库列表，包含对应的售达方和送达方
	 * 
	 * @author Xiao,GuoJian
	 * @param jBaseStore
	 * @return jBaseStore
	 */
	List<JBaseStore> selectJBaseStoreForR3PaginatedList(JBaseStore jBaseStore);

	/**
	 * 获取仓库列表，包含对应的售达方和送达方
	 * 
	 * @author Xiao,GuoJian
	 * @param jBaseStore
	 * @return jBaseStore
	 */
	List<JBaseStore> selectJBaseStoreForR3List(JBaseStore jBaseStore);

	List<HashMap> selectJBaseStoreMapList(JBaseStore jBaseStore);

	void clearStockUseR3code(HashMap map);

}
