package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaEmFileContentDao;
import com.ebiz.mmt.domain.KonkaEmFileContent;
import com.ebiz.mmt.service.KonkaEmFileContentService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-09-27 17:08:18
 */
@Service
public class KonkaEmFileContentServiceImpl implements KonkaEmFileContentService {

	@Resource
	private KonkaEmFileContentDao konkaEmFileContentDao;
	

	public Long createKonkaEmFileContent(KonkaEmFileContent t) {
		return this.konkaEmFileContentDao.insertEntity(t);
	}

	public KonkaEmFileContent getKonkaEmFileContent(KonkaEmFileContent t) {
		return this.konkaEmFileContentDao.selectEntity(t);
	}

	public Long getKonkaEmFileContentCount(KonkaEmFileContent t) {
		return this.konkaEmFileContentDao.selectEntityCount(t);
	}

	public List<KonkaEmFileContent> getKonkaEmFileContentList(KonkaEmFileContent t) {
		return this.konkaEmFileContentDao.selectEntityList(t);
	}

	public int modifyKonkaEmFileContent(KonkaEmFileContent t) {
		return this.konkaEmFileContentDao.updateEntity(t);
	}

	public int removeKonkaEmFileContent(KonkaEmFileContent t) {
		return this.konkaEmFileContentDao.deleteEntity(t);
	}

	public List<KonkaEmFileContent> getKonkaEmFileContentPaginatedList(KonkaEmFileContent t) {
		return this.konkaEmFileContentDao.selectEntityPaginatedList(t);
	}

}
