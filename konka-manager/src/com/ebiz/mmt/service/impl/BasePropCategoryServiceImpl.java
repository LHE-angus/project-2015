package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.BasePropCategoryDao;
import com.ebiz.mmt.domain.BasePropCategory;
import com.ebiz.mmt.service.BasePropCategoryService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Chen,ShunHua
 * @date 2011-09-21 15:45:12
 */
@Service
public class BasePropCategoryServiceImpl implements BasePropCategoryService {

	@Resource
	private BasePropCategoryDao basePropCategoryDao;

	public Long createBasePropCategory(BasePropCategory t) {
		return this.basePropCategoryDao.insertEntity(t);
	}

	public BasePropCategory getBasePropCategory(BasePropCategory t) {
		return this.basePropCategoryDao.selectEntity(t);
	}

	public Long getBasePropCategoryCount(BasePropCategory t) {
		return this.basePropCategoryDao.selectEntityCount(t);
	}

	public List<BasePropCategory> getBasePropCategoryList(BasePropCategory t) {
		return this.basePropCategoryDao.selectEntityList(t);
	}

	public int modifyBasePropCategory(BasePropCategory t) {
		return this.basePropCategoryDao.updateEntity(t);
	}

	public int removeBasePropCategory(BasePropCategory t) {
		return this.basePropCategoryDao.deleteEntity(t);
	}

	public List<BasePropCategory> getBasePropCategoryPaginatedList(BasePropCategory t) {
		return this.basePropCategoryDao.selectEntityPaginatedList(t);
	}

	/**
	 * @author Chen,ShunHua
	 * @version 2011.09.21
	 */
	public List<BasePropCategory> getBasePropCategoryByNameList(BasePropCategory t) {
		return this.basePropCategoryDao.selectBasePropCategoryByNameList(t);
	}
}
