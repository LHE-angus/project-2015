package com.ebiz.mmt.service;

import java.util.List;
import com.ebiz.mmt.domain.KonkaBranchCategory;

public interface KonkaBranchCategoryService {

	Long createKonkaBranchCategory(KonkaBranchCategory t);

	int modifyKonkaBranchCategory(KonkaBranchCategory t);

	int removeKonkaBranchCategory(KonkaBranchCategory t);

	KonkaBranchCategory getKonkaBranchCategory(KonkaBranchCategory t);

	List<KonkaBranchCategory> getKonkaBranchCategoryList(KonkaBranchCategory t);

	Long getKonkaBranchCategoryCount(KonkaBranchCategory t);

	List<KonkaBranchCategory> getKonkaBranchCategoryPaginatedList(KonkaBranchCategory t);

	List<KonkaBranchCategory> getKonkaBranchCategoryListForMsgAndArticle(KonkaBranchCategory t);

	List<KonkaBranchCategory> getKonkaBranchCategoryWithCustomerNamesList(KonkaBranchCategory t);
}
