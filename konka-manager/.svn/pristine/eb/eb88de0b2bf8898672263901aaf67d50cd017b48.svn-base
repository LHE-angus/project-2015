package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaMobileCategory;

/**
 * @author Xu,ZhengYang
 * @date 2010-11-14 10:33:45
 */
public interface KonkaMobileCategoryService {

	String getKonkaMobileDept(KonkaMobileCategory t);

	Long createKonkaMobileCategory(KonkaMobileCategory t);

	int modifyKonkaMobileCategory(KonkaMobileCategory t);

	int removeKonkaMobileCategory(KonkaMobileCategory t);

	KonkaMobileCategory getKonkaMobileCategory(KonkaMobileCategory t);

	List<KonkaMobileCategory> getKonkaMobileCategoryList(KonkaMobileCategory t);

	Long getKonkaMobileCategoryCount(KonkaMobileCategory t);

	List<KonkaMobileCategory> getKonkaMobileCategoryPaginatedList(
			KonkaMobileCategory t);

	List<KonkaMobileCategory> getKonkaMobileCategoryListForFiles(
			KonkaMobileCategory t);

	List<KonkaMobileCategory> getKonkaMobileCategoryListForSelect(
			KonkaMobileCategory t);

	// 查找表KONKA_MOBILE_CATEGORY取得当前操作表中使用的类别枚举信息
	KonkaMobileCategory getCategory(KonkaMobileCategory t, Integer type);

	// 查找表KONKA_MOBILE_CATEGORY的所有类别
	List<KonkaMobileCategory> getCategoryForList();

	// 查找表KONKA_MOBILE_CATEGORY的样机枚举
	List<KonkaMobileCategory> getCategoryList();

	// 查找表KONKA_MOBILE_CATEGORY的终端物料枚举
	List<KonkaMobileCategory> getWuliaoCategoryList();

	// 查找表KONKA_MOBILE_CATEGORY的竞品品牌枚举
	List<KonkaMobileCategory> getJingpinCategoryList();

	Long createKonkaMobileCategoryForAddType(KonkaMobileCategory t);
}