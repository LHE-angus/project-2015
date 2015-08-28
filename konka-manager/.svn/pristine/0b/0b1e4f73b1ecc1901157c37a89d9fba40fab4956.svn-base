package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaOaModuleContentDao;
import com.ebiz.mmt.dao.KonkaOaModuleTypeDao;
import com.ebiz.mmt.domain.KonkaOaModuleContent;
import com.ebiz.mmt.domain.KonkaOaModuleType;
import com.ebiz.mmt.service.KonkaOaModuleTypeService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-05-16 17:46:07
 */
@Service
public class KonkaOaModuleTypeServiceImpl implements KonkaOaModuleTypeService {

	@Resource
	private KonkaOaModuleTypeDao konkaOaModuleTypeDao;

	@Resource
	private KonkaOaModuleContentDao konkaOaModuleContentDao;

	public Long createKonkaOaModuleType(KonkaOaModuleType t) {
		return this.konkaOaModuleTypeDao.insertEntity(t);
	}

	public KonkaOaModuleType getKonkaOaModuleType(KonkaOaModuleType t) {
		return this.konkaOaModuleTypeDao.selectEntity(t);
	}

	public Long getKonkaOaModuleTypeCount(KonkaOaModuleType t) {
		return this.konkaOaModuleTypeDao.selectEntityCount(t);
	}

	public List<KonkaOaModuleType> getKonkaOaModuleTypeList(KonkaOaModuleType t) {
		return this.konkaOaModuleTypeDao.selectEntityList(t);
	}

	public int modifyKonkaOaModuleType(KonkaOaModuleType t) {
		return this.konkaOaModuleTypeDao.updateEntity(t);
	}

	public int removeKonkaOaModuleType(KonkaOaModuleType t) {
		return this.konkaOaModuleTypeDao.deleteEntity(t);
	}

	public List<KonkaOaModuleType> getKonkaOaModuleTypePaginatedList(KonkaOaModuleType t) {
		return this.konkaOaModuleTypeDao.selectEntityPaginatedList(t);
	}

	/**
	 * @author Hu,Hao
	 * @version 2013-05-16
	 */
	public Long createKonkaOaModuleTypeForContent(KonkaOaModuleType t, String content) {
		Long id = this.konkaOaModuleTypeDao.insertEntity(t);

		KonkaOaModuleContent konkaOaModuleContent = new KonkaOaModuleContent();
		konkaOaModuleContent.setModule_id(id);
		konkaOaModuleContent.setId(id);
		konkaOaModuleContent.setContent(content);

		return this.konkaOaModuleContentDao.insertEntity(konkaOaModuleContent);
	}

	/**
	 * @author Hu,Hao
	 * @version 2013-05-16
	 */
	public int modifyKonkaOaModuleTypeForContent(KonkaOaModuleType t, String content) {
		this.konkaOaModuleTypeDao.updateEntity(t);

		KonkaOaModuleContent konkaOaModuleContent = new KonkaOaModuleContent();
		konkaOaModuleContent.setId(t.getModule_id());
		konkaOaModuleContent.setModule_id(t.getModule_id());
		konkaOaModuleContent.setContent(content);

		return this.konkaOaModuleContentDao.updateEntity(konkaOaModuleContent);
	}

}
