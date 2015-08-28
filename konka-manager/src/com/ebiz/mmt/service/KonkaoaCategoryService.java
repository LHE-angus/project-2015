package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaoaCategory;

/**
 * @author Xu,ZhengYang
 * @date 2010-11-14 10:33:45
 */
public interface KonkaoaCategoryService {

	Long createKonkaoaCategory(KonkaoaCategory t);

	int modifyKonkaoaCategory(KonkaoaCategory t);

	int removeKonkaoaCategory(KonkaoaCategory t);

	KonkaoaCategory getKonkaoaCategory(KonkaoaCategory t);

	List<KonkaoaCategory> getKonkaoaCategoryList(KonkaoaCategory t);

	Long getKonkaoaCategoryCount(KonkaoaCategory t);

	List<KonkaoaCategory> getKonkaoaCategoryPaginatedList(KonkaoaCategory t);

	List<KonkaoaCategory> getKonkaoaCategoryListForFiles(KonkaoaCategory t);

}