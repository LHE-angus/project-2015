package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaoaFilesContentDao;
import com.ebiz.mmt.domain.KonkaoaFilesContent;
import com.ebiz.mmt.service.KonkaoaFilesContentService;

/**
 * @author Hui,Gang
 * @version Build 2010-12-13 14:49:33
 */
 @Service
public class KonkaoaFilesContentServiceImpl implements KonkaoaFilesContentService {
	
	@Resource
	private KonkaoaFilesContentDao filesContentDao;

	public Long createKonkaoaFilesContent(KonkaoaFilesContent t) {
		return this.filesContentDao.insertEntity(t);
	}

	public KonkaoaFilesContent getKonkaoaFilesContent(KonkaoaFilesContent t) {
		return this.filesContentDao.selectEntity(t);
	}

	public Long getKonkaoaFilesContentCount(KonkaoaFilesContent t) {
		return this.filesContentDao.selectEntityCount(t);
	}

	public List<KonkaoaFilesContent> getKonkaoaFilesContentList(KonkaoaFilesContent t) {
		return this.filesContentDao.selectEntityList(t);
	}

	public int modifyKonkaoaFilesContent(KonkaoaFilesContent t) {
		return this.filesContentDao.updateEntity(t);
	}

	public int removeKonkaoaFilesContent(KonkaoaFilesContent t) {
		return this.filesContentDao.deleteEntity(t);
	}

	public List<KonkaoaFilesContent> getKonkaoaFilesContentPaginatedList(KonkaoaFilesContent t) {
		return this.filesContentDao.selectEntityPaginatedList(t);
	}	

}