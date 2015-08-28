package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.Category;

/**
 * @author Xu,ZhengYang
 * @date 2010-11-14 10:33:45
 */
public interface CategoryService {

	Long createCategory(Category t);

	int modifyCategory(Category t);

	int removeCategory(Category t);

	Category getCategory(Category t);

	List<Category> getCategoryList(Category t);

	Long getCategoryCount(Category t);

	List<Category> getCategoryPaginatedList(Category t);

	List<Category> getCategoryListForFiles(Category t);

}