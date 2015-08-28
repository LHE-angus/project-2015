package com.ebiz.mmt.service;

import java.util.HashMap;
import java.util.List;

import com.ebiz.mmt.domain.JBaseStore;
import com.ebiz.mmt.domain.JBaseStoreR3;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-06-08 17:03:35
 */
public interface JBaseStoreService {

	Long createJBaseStore(JBaseStore t);

	int modifyJBaseStore(JBaseStore t);

	int removeJBaseStore(JBaseStore t);

	JBaseStore getJBaseStore(JBaseStore t);

	List<JBaseStore> getJBaseStoreList(JBaseStore t);

	Long getJBaseStoreCount(JBaseStore t);

	List<JBaseStore> getJBaseStorePaginatedList(JBaseStore t);

	List<JBaseStoreR3> getStoreSnWeForList(JBaseStoreR3 jBaseStoreR3);

	JBaseStore getJBaseStoreAndDetails(JBaseStore entity);

	/**
	 * 获取仓库列表，包含对应的售达方和送达方
	 * 
	 * @author Xiao,GuoJian
	 * @param jBaseStore
	 * @return jBaseStore
	 */
	List<JBaseStore> getJBaseStoreForR3PaginatedList(JBaseStore jBaseStore);

	/**
	 * 获取仓库列表，包含对应的售达方和送达方
	 * 
	 * @author Xiao,GuoJian
	 * @param jBaseStore
	 * @return jBaseStore
	 */
	List<JBaseStore> getJBaseStoreForR3List(JBaseStore jBaseStore);

	/**
	 * 获取仓库列表，包含对应的售达方和送达方
	 * 
	 * @author Xiao,GuoJian
	 * @param jBaseStore
	 * @return jBaseStore flag true送达方为空，false 送达方不能为空
	 */
	List<JBaseStore> getJBaseStoreForR3WithSdfNullOrNotList(JBaseStore jBaseStore, boolean flag);

	/**
	 * 订单明细获取仓库列表
	 * 
	 * @param jBaseStore
	 * @param flag
	 * @return
	 */
	List<HashMap> getJBaseStoreMapList(JBaseStore jBaseStore);

	void clearStockUseR3code(HashMap map);
}