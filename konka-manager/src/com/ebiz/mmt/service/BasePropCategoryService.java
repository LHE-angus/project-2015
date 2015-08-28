package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.BasePropCategory;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Chen,ShunHua
 * @date 2011-09-22 09:45:12
 */
public interface BasePropCategoryService {

	Long createBasePropCategory(BasePropCategory t);

	int modifyBasePropCategory(BasePropCategory t);

	int removeBasePropCategory(BasePropCategory t);

	BasePropCategory getBasePropCategory(BasePropCategory t);

	List<BasePropCategory> getBasePropCategoryList(BasePropCategory t);

	Long getBasePropCategoryCount(BasePropCategory t);

	List<BasePropCategory> getBasePropCategoryPaginatedList(BasePropCategory t);

	/**
	 * @author Chen,ShunHua
	 * @version 2011.09.21
	 */
	List<BasePropCategory> getBasePropCategoryByNameList(BasePropCategory t);

}