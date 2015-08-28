package com.ebiz.mmt.dao;

import java.util.List;

import com.ebiz.mmt.domain.JxcBrandApply;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-03-03 09:42:37
 */
public interface JxcBrandApplyDao extends EntityDao<JxcBrandApply> {

	/**
	 * @author Zhang,ShiMing
	 */
	List<JxcBrandApply> selectEntityListWithShopName(JxcBrandApply entity);

	/**
	 * @author Zhang,ShiMing
	 */
	Long selectEntityWithShopNameCount(JxcBrandApply entity);

	/**
	 * @author Xing,XiuDong
	 * @date 2011-05-17
	 */
	List<JxcBrandApply> selectJxcBrandApplyXPaginatedList(JxcBrandApply entity);

	/**
	 * @author Xing,XiuDong
	 * @date 2011-05-17
	 */
	Long selectJxcBrandApplyXCount(JxcBrandApply entity);

}
