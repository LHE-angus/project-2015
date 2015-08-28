package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaBranchCategoryDao;
import com.ebiz.mmt.domain.KonkaBranchCategory;
import com.ebiz.mmt.service.KonkaBranchCategoryService;


@Service
public class KonkaBranchCategoryServiceImpl implements KonkaBranchCategoryService {

	@Resource
	private KonkaBranchCategoryDao konkaBranchCategoryDao;

	public Long createKonkaBranchCategory(KonkaBranchCategory t) {
		return this.konkaBranchCategoryDao.insertEntity(t);
	}

	public KonkaBranchCategory getKonkaBranchCategory(KonkaBranchCategory t) {
		return this.konkaBranchCategoryDao.selectEntity(t);
	}

	public Long getKonkaBranchCategoryCount(KonkaBranchCategory t) {
		return this.konkaBranchCategoryDao.selectEntityCount(t);
	}

	public List<KonkaBranchCategory> getKonkaBranchCategoryList(KonkaBranchCategory t) {
		return this.konkaBranchCategoryDao.selectEntityList(t);
	}

	public int modifyKonkaBranchCategory(KonkaBranchCategory t) {
		return this.konkaBranchCategoryDao.updateEntity(t);
	}

	public int removeKonkaBranchCategory(KonkaBranchCategory t) {
		return this.konkaBranchCategoryDao.deleteEntity(t);
	}

	public List<KonkaBranchCategory> getKonkaBranchCategoryPaginatedList(KonkaBranchCategory t) {
		return this.konkaBranchCategoryDao.selectEntityPaginatedList(t);
	}

	public List<KonkaBranchCategory> getKonkaBranchCategoryListForMsgAndArticle(KonkaBranchCategory t) {
		return this.konkaBranchCategoryDao.selectKonkaBranchCategoryListForMsgAndArticle(t);
	}

	@Override
	public List<KonkaBranchCategory> getKonkaBranchCategoryWithCustomerNamesList(KonkaBranchCategory t) {
		return this.konkaBranchCategoryDao.selectKonkaBranchCategoryWithCustomerNamesList(t);
	}

}
