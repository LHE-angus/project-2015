package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaR3DeptStore;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-09-24 11:31:51
 */
public interface KonkaR3DeptStoreService {

	Long createKonkaR3DeptStore(KonkaR3DeptStore t);

	int modifyKonkaR3DeptStore(KonkaR3DeptStore t);

	int removeKonkaR3DeptStore(KonkaR3DeptStore t);

	KonkaR3DeptStore getKonkaR3DeptStore(KonkaR3DeptStore t);

	List<KonkaR3DeptStore> getKonkaR3DeptStoreList(KonkaR3DeptStore t);

	Long getKonkaR3DeptStoreCount(KonkaR3DeptStore t);

	List<KonkaR3DeptStore> getKonkaR3DeptStorePaginatedList(KonkaR3DeptStore t);
	
	/**
	 * @author Hu,Hao
	 * @version 2013-09-24
	 * @desc 分公司查询 
	 */
	List<KonkaR3DeptStore> getKonkaR3DeptStoreForFgsNameList(KonkaR3DeptStore t);
	
	/**
	 * @author Hu,Hao
	 * @version 2013-09-24
	 * @desc 工厂查询 
	 */
	List<KonkaR3DeptStore> getKonkaR3DeptStoreForFacSnList(KonkaR3DeptStore t);

}