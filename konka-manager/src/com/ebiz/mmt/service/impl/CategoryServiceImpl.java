package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.CategoryDao;
import com.ebiz.mmt.domain.Category;
import com.ebiz.mmt.service.CategoryService;

/**
 * @author Xu,ZhengYang
 * @date 2010-11-14 10:33:45
 */

@Service
public class CategoryServiceImpl implements CategoryService {

	@Resource
	private CategoryDao categoryDao;

	public Long createCategory(Category t) {
		return this.categoryDao.insertEntity(t);
	}

	public int modifyCategory(Category t) {
		return this.categoryDao.updateEntity(t);
	}

	public int removeCategory(Category t) {
		return this.categoryDao.deleteEntity(t);
	}

	public Category getCategory(Category t) {
		return this.categoryDao.selectEntity(t);
	}

	public Long getCategoryCount(Category t) {
		return this.categoryDao.selectEntityCount(t);
	}

	public List<Category> getCategoryList(Category t) {
		return this.categoryDao.selectEntityList(t);
	}

	public List<Category> getCategoryPaginatedList(Category t) {
		return this.categoryDao.selectEntityPaginatedList(t);
	}

	public List<Category> getCategoryListForFiles(Category t) {
		return this.categoryDao.selectCategoryListForFiles(t);
	}
}
