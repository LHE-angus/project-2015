package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaoaCategoryDao;
import com.ebiz.mmt.domain.KonkaoaCategory;
import com.ebiz.mmt.service.KonkaoaCategoryService;

/**
 * @author Xu,ZhengYang
 * @date 2010-11-14 10:33:45
 */

@Service
public class KonkaoaCategoryServiceImpl implements KonkaoaCategoryService {

	@Resource
	private KonkaoaCategoryDao categoryDao;

	public Long createKonkaoaCategory(KonkaoaCategory t) {
		return this.categoryDao.insertEntity(t);
	}

	public int modifyKonkaoaCategory(KonkaoaCategory t) {
		return this.categoryDao.updateEntity(t);
	}

	public int removeKonkaoaCategory(KonkaoaCategory t) {
		return this.categoryDao.deleteEntity(t);
	}

	public KonkaoaCategory getKonkaoaCategory(KonkaoaCategory t) {
		return this.categoryDao.selectEntity(t);
	}

	public Long getKonkaoaCategoryCount(KonkaoaCategory t) {
		return this.categoryDao.selectEntityCount(t);
	}

	public List<KonkaoaCategory> getKonkaoaCategoryList(KonkaoaCategory t) {
		return this.categoryDao.selectEntityList(t);
	}

	public List<KonkaoaCategory> getKonkaoaCategoryPaginatedList(KonkaoaCategory t) {
		return this.categoryDao.selectEntityPaginatedList(t);
	}

	public List<KonkaoaCategory> getKonkaoaCategoryListForFiles(KonkaoaCategory t) {
		return this.categoryDao.selectKonkaoaCategoryListForFiles(t);
	}
}
