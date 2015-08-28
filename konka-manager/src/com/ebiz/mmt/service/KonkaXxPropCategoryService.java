package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaXxPropCategory;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-04-01 16:53:38
 */
public interface KonkaXxPropCategoryService {

	Long createKonkaXxPropCategory(KonkaXxPropCategory t);

	int modifyKonkaXxPropCategory(KonkaXxPropCategory t);

	int removeKonkaXxPropCategory(KonkaXxPropCategory t);

	KonkaXxPropCategory getKonkaXxPropCategory(KonkaXxPropCategory t);

	List<KonkaXxPropCategory> getKonkaXxPropCategoryList(KonkaXxPropCategory t);

	Long getKonkaXxPropCategoryCount(KonkaXxPropCategory t);

	List<KonkaXxPropCategory> getKonkaXxPropCategoryPaginatedList(KonkaXxPropCategory t);
	
	/**
	 * @author Hu,Hao
	 * @version 2013-04-03
	 */
	List<KonkaXxPropCategory> getKonkaXxPropCategoryForPdNamePaginatedList(KonkaXxPropCategory t);

}