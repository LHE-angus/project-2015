package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaBcompPdContentDao;
import com.ebiz.mmt.domain.KonkaBcompPdContent;
import com.ebiz.mmt.service.KonkaBcompPdContentService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-08-21 12:02:11
 */
@Service
public class KonkaBcompPdContentServiceImpl implements KonkaBcompPdContentService {

	@Resource
	private KonkaBcompPdContentDao konkaBcompPdContentDao;
	

	public Long createKonkaBcompPdContent(KonkaBcompPdContent t) {
		return this.konkaBcompPdContentDao.insertEntity(t);
	}

	public KonkaBcompPdContent getKonkaBcompPdContent(KonkaBcompPdContent t) {
		return this.konkaBcompPdContentDao.selectEntity(t);
	}

	public Long getKonkaBcompPdContentCount(KonkaBcompPdContent t) {
		return this.konkaBcompPdContentDao.selectEntityCount(t);
	}

	public List<KonkaBcompPdContent> getKonkaBcompPdContentList(KonkaBcompPdContent t) {
		return this.konkaBcompPdContentDao.selectEntityList(t);
	}

	public int modifyKonkaBcompPdContent(KonkaBcompPdContent t) {
		return this.konkaBcompPdContentDao.updateEntity(t);
	}

	public int removeKonkaBcompPdContent(KonkaBcompPdContent t) {
		return this.konkaBcompPdContentDao.deleteEntity(t);
	}

	public List<KonkaBcompPdContent> getKonkaBcompPdContentPaginatedList(KonkaBcompPdContent t) {
		return this.konkaBcompPdContentDao.selectEntityPaginatedList(t);
	}

}
