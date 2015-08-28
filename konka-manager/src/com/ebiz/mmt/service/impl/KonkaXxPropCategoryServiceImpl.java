package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaXxPropCategoryDao;
import com.ebiz.mmt.domain.KonkaXxPropCategory;
import com.ebiz.mmt.service.KonkaXxPropCategoryService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-04-01 16:53:38
 */
@Service
public class KonkaXxPropCategoryServiceImpl implements KonkaXxPropCategoryService {

	@Resource
	private KonkaXxPropCategoryDao konkaXxPropCategoryDao;
	

	public Long createKonkaXxPropCategory(KonkaXxPropCategory t) {
		return this.konkaXxPropCategoryDao.insertEntity(t);
	}

	public KonkaXxPropCategory getKonkaXxPropCategory(KonkaXxPropCategory t) {
		return this.konkaXxPropCategoryDao.selectEntity(t);
	}

	public Long getKonkaXxPropCategoryCount(KonkaXxPropCategory t) {
		return this.konkaXxPropCategoryDao.selectEntityCount(t);
	}

	public List<KonkaXxPropCategory> getKonkaXxPropCategoryList(KonkaXxPropCategory t) {
		return this.konkaXxPropCategoryDao.selectEntityList(t);
	}

	public int modifyKonkaXxPropCategory(KonkaXxPropCategory t) {
		return this.konkaXxPropCategoryDao.updateEntity(t);
	}

	public int removeKonkaXxPropCategory(KonkaXxPropCategory t) {
		return this.konkaXxPropCategoryDao.deleteEntity(t);
	}

	public List<KonkaXxPropCategory> getKonkaXxPropCategoryPaginatedList(KonkaXxPropCategory t) {
		return this.konkaXxPropCategoryDao.selectEntityPaginatedList(t);
	}

	/**
	 * @author Hu,Hao
	 * @version 2013-04-03
	 */
	public List<KonkaXxPropCategory> getKonkaXxPropCategoryForPdNamePaginatedList(KonkaXxPropCategory t){
		return this.konkaXxPropCategoryDao.selectKonkaXxPropCategoryForPdNamePaginatedList(t);
	}
}
