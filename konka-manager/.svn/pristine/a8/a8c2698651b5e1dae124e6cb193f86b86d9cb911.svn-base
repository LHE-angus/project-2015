package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaoaFilesPropertyDao;
import com.ebiz.mmt.domain.KonkaoaFilesProperty;
import com.ebiz.mmt.service.KonkaoaFilesPropertyService;

/**
 * @author Hui,Gang
 * @version Build 2010-12-13 14:49:33
 */
@Service
public class KonkaoaFilesPropertyServiceImpl implements KonkaoaFilesPropertyService {

	@Resource
	private KonkaoaFilesPropertyDao filesPropertyDao;

	public Long createKonkaoaFilesProperty(KonkaoaFilesProperty t) {
		return this.filesPropertyDao.insertEntity(t);
	}

	public KonkaoaFilesProperty getKonkaoaFilesProperty(KonkaoaFilesProperty t) {
		return this.filesPropertyDao.selectEntity(t);
	}

	public Long getKonkaoaFilesPropertyCount(KonkaoaFilesProperty t) {
		return this.filesPropertyDao.selectEntityCount(t);
	}

	public List<KonkaoaFilesProperty> getKonkaoaFilesPropertyList(KonkaoaFilesProperty t) {
		return this.filesPropertyDao.selectEntityList(t);
	}

	public int modifyKonkaoaFilesProperty(KonkaoaFilesProperty t) {
		return this.filesPropertyDao.updateEntity(t);
	}

	public int removeKonkaoaFilesProperty(KonkaoaFilesProperty t) {
		return this.filesPropertyDao.deleteEntity(t);
	}

	public List<KonkaoaFilesProperty> getKonkaoaFilesPropertyPaginatedList(KonkaoaFilesProperty t) {
		return this.filesPropertyDao.selectEntityPaginatedList(t);
	}
	
}