package com.ebiz.mmt.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaCategoryDao;
import com.ebiz.mmt.domain.KonkaCategory;
import com.ebiz.mmt.service.KonkaCategoryService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-06-26 09:19:45
 */
@Service
public class KonkaCategoryServiceImpl implements KonkaCategoryService {

	@Resource
	private KonkaCategoryDao konkaCategoryDao;

	public Long createKonkaCategory(KonkaCategory t) {
		return this.konkaCategoryDao.insertEntity(t);
	}

	public KonkaCategory getKonkaCategory(KonkaCategory t) {
		return this.konkaCategoryDao.selectEntity(t);
	}

	public Long getKonkaCategoryCount(KonkaCategory t) {
		return this.konkaCategoryDao.selectEntityCount(t);
	}

	public List<KonkaCategory> getKonkaCategoryList(KonkaCategory t) {
		return this.konkaCategoryDao.selectEntityList(t);
	}

	public int modifyKonkaCategory(KonkaCategory t) {
		return this.konkaCategoryDao.updateEntity(t);
	}

	public int removeKonkaCategory(KonkaCategory t) {
		return this.konkaCategoryDao.deleteEntity(t);
	}

	public List<KonkaCategory> getKonkaCategoryPaginatedList(KonkaCategory t) {
		return this.konkaCategoryDao.selectEntityPaginatedList(t);
	}

	/**
	 * @author Hu,hao
	 * @version 2013-08-27
	 * @desc 客户大类
	 */
	public List<KonkaCategory> getKonkaCategoryGroupByCCommList(KonkaCategory t) {
		return this.konkaCategoryDao.selectKonkaCategoryGroupByCCommList(t);
	}

	/**
	 * 查询客户大类，返回List<HashMap>
	 */
	@Override
	public List<HashMap> getKonkaCategoryGroupByCCommNew(KonkaCategory t) {
		
		return this.konkaCategoryDao.selectKonkaCategoryGroupByCCommNew(t);
	}

	public List<HashMap> getKonkaCategoryListNew(KonkaCategory t) {
		return this.konkaCategoryDao.selectEntityListNew(t);
	}

	@Override
	public List<HashMap> getKonkaCategoryMapList(KonkaCategory t) {
		return this.konkaCategoryDao.selectKonkaCategoryMapList(t);
	}

	@Override
	public List<KonkaCategory> getKonkaCategoryAndParIndexList(KonkaCategory t) {
		return this.konkaCategoryDao.selectKonkaCategoryAndParIndexList(t);
	}

}
