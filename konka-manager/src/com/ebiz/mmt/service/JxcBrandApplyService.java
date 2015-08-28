package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.JxcBrandApply;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-03-03 09:42:37
 */
public interface JxcBrandApplyService {

	Long createJxcBrandApply(JxcBrandApply t);

	int modifyJxcBrandApply(JxcBrandApply t);

	int removeJxcBrandApply(JxcBrandApply t);

	JxcBrandApply getJxcBrandApply(JxcBrandApply t);

	List<JxcBrandApply> getJxcBrandApplyList(JxcBrandApply t);

	Long getJxcBrandApplyCount(JxcBrandApply t);

	List<JxcBrandApply> getJxcBrandApplyPaginatedList(JxcBrandApply t);

	/**
	 * @author Zhang,ShiMing
	 */
	List<JxcBrandApply> getJxcBrandApplyListWithShopName(JxcBrandApply entity);

	/**
	 * @author Zhang,ShiMing
	 */
	Long getJxcBrandApplyWithShopNameCount(JxcBrandApply entity);

	/**
	 * @author Xing,XiuDong
	 * @date 2011-05-17
	 */
	List<JxcBrandApply> getJxcBrandApplyXPaginatedList(JxcBrandApply entity);

	/**
	 * @author Xing,XiuDong
	 * @date 2011-05-17
	 */
	Long getJxcBrandApplyXCount(JxcBrandApply entity);

}