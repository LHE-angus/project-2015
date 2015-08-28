package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaPeArticleContentDao;
import com.ebiz.mmt.domain.KonkaPeArticleContent;
import com.ebiz.mmt.service.KonkaPeArticleContentService;

/**
 * Code by AutoGenerated Builder
 * AutoGenerated Builder Version 2.1
 * @author Hui,Gang
 * @datetime 2011-09-29 16:08:29
 */
 @Service
public class KonkaPeArticleContentServiceImpl implements KonkaPeArticleContentService {
	
	@Resource
	private KonkaPeArticleContentDao konkaPeArticleContentDao;

	public Long createKonkaPeArticleContent(KonkaPeArticleContent t) {
		return this.konkaPeArticleContentDao.insertEntity(t);
	}

	public KonkaPeArticleContent getKonkaPeArticleContent(KonkaPeArticleContent t) {
		return this.konkaPeArticleContentDao.selectEntity(t);
	}

	public Long getKonkaPeArticleContentCount(KonkaPeArticleContent t) {
		return this.konkaPeArticleContentDao.selectEntityCount(t);
	}

	public List<KonkaPeArticleContent> getKonkaPeArticleContentList(KonkaPeArticleContent t) {
		return this.konkaPeArticleContentDao.selectEntityList(t);
	}

	public int modifyKonkaPeArticleContent(KonkaPeArticleContent t) {
		return this.konkaPeArticleContentDao.updateEntity(t);
	}

	public int removeKonkaPeArticleContent(KonkaPeArticleContent t) {
		return this.konkaPeArticleContentDao.deleteEntity(t);
	}

	public List<KonkaPeArticleContent> getKonkaPeArticleContentPaginatedList(KonkaPeArticleContent t) {
		return this.konkaPeArticleContentDao.selectEntityPaginatedList(t);
	}	

}