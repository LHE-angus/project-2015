package com.ebiz.mmt.service;

import java.util.HashMap;
import java.util.List;

import com.ebiz.mmt.domain.KonkaCategory;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-06-26 09:19:45
 */
public interface KonkaCategoryService {

	Long createKonkaCategory(KonkaCategory t);

	int modifyKonkaCategory(KonkaCategory t);

	int removeKonkaCategory(KonkaCategory t);

	KonkaCategory getKonkaCategory(KonkaCategory t);

	List<KonkaCategory> getKonkaCategoryList(KonkaCategory t);

	Long getKonkaCategoryCount(KonkaCategory t);

	List<KonkaCategory> getKonkaCategoryPaginatedList(KonkaCategory t);

	/**
	 * @author Hu,hao
	 * @version 2013-08-27
	 * @desc 客户大类
	 */
	List<KonkaCategory> getKonkaCategoryGroupByCCommList(KonkaCategory t);

	/**
	 * 查询客户大类，返回List<HashMap>
	 * 
	 * @author Angus
	 * @date 2014-7-24
	 * @param t
	 * @return
	 */
	List<HashMap> getKonkaCategoryGroupByCCommNew(KonkaCategory t);

	/**
	 * 客户细类
	 * 
	 * @author Angus
	 * @date 2014-7-24
	 * @param t
	 * @return
	 */
	List<HashMap> getKonkaCategoryListNew(KonkaCategory t);

	/**
	 * 客户管理中的客户类型列表
	 * 
	 * @author Liang,HouEn 2014-9-17
	 * @param t
	 * @return
	 */
	List<HashMap> getKonkaCategoryMapList(KonkaCategory t);

	List<KonkaCategory> getKonkaCategoryAndParIndexList(KonkaCategory t);
}