package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaOaModuleContentDao;
import com.ebiz.mmt.domain.KonkaOaModuleContent;
import com.ebiz.mmt.service.KonkaOaModuleContentService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-05-16 17:46:07
 */
@Service
public class KonkaOaModuleContentServiceImpl implements KonkaOaModuleContentService {

	@Resource
	private KonkaOaModuleContentDao konkaOaModuleContentDao;
	

	public Long createKonkaOaModuleContent(KonkaOaModuleContent t) {
		return this.konkaOaModuleContentDao.insertEntity(t);
	}

	public KonkaOaModuleContent getKonkaOaModuleContent(KonkaOaModuleContent t) {
		return this.konkaOaModuleContentDao.selectEntity(t);
	}

	public Long getKonkaOaModuleContentCount(KonkaOaModuleContent t) {
		return this.konkaOaModuleContentDao.selectEntityCount(t);
	}

	public List<KonkaOaModuleContent> getKonkaOaModuleContentList(KonkaOaModuleContent t) {
		return this.konkaOaModuleContentDao.selectEntityList(t);
	}

	public int modifyKonkaOaModuleContent(KonkaOaModuleContent t) {
		return this.konkaOaModuleContentDao.updateEntity(t);
	}

	public int removeKonkaOaModuleContent(KonkaOaModuleContent t) {
		return this.konkaOaModuleContentDao.deleteEntity(t);
	}

	public List<KonkaOaModuleContent> getKonkaOaModuleContentPaginatedList(KonkaOaModuleContent t) {
		return this.konkaOaModuleContentDao.selectEntityPaginatedList(t);
	}

}
