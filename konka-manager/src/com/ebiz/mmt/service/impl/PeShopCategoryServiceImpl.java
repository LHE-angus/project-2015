package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.PeShopCategoryDao;
import com.ebiz.mmt.domain.PeShopCategory;
import com.ebiz.mmt.service.PeShopCategoryService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-05-11 15:59:43
 */
@Service
public class PeShopCategoryServiceImpl implements PeShopCategoryService {

	@Resource
	private PeShopCategoryDao peShopCategoryDao;

	public Long createPeShopCategory(PeShopCategory t) {
		return this.peShopCategoryDao.insertEntity(t);
	}

	public PeShopCategory getPeShopCategory(PeShopCategory t) {
		return this.peShopCategoryDao.selectEntity(t);
	}

	public Long getPeShopCategoryCount(PeShopCategory t) {
		return this.peShopCategoryDao.selectEntityCount(t);
	}

	public List<PeShopCategory> getPeShopCategoryList(PeShopCategory t) {
		return this.peShopCategoryDao.selectEntityList(t);
	}

	public int modifyPeShopCategory(PeShopCategory t) {
		return this.peShopCategoryDao.updateEntity(t);
	}

	public int removePeShopCategory(PeShopCategory t) {
		return this.peShopCategoryDao.deleteEntity(t);
	}

	public List<PeShopCategory> getPeShopCategoryPaginatedList(PeShopCategory t) {
		return this.peShopCategoryDao.selectEntityPaginatedList(t);
	}

	/**
	 * @author Li,Yuan
	 * @version 2011-05-17
	 */
	public Long getPeShopCategoryWithNameCount(PeShopCategory t) {
		return this.peShopCategoryDao.selectPeShopCategoryWithNameCount(t);
	}

	/**
	 * @author Li,Yuan
	 * @version 2011-05-17
	 */
	public List<PeShopCategory> getPeShopCategoryWithNamePaginatedList(PeShopCategory t) {
		return this.peShopCategoryDao.selectPeShopCategoryWithNamePaginatedList(t);
	}

	public List<PeShopCategory> getPeShopCategoryListWithLevel(PeShopCategory t) {
		return this.peShopCategoryDao.selectPeShopCategoryListWithLevel(t);
	}
}
