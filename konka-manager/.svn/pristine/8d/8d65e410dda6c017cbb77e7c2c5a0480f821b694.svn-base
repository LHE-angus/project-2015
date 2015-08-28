package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaCommentReadDao;
import com.ebiz.mmt.domain.KonkaCommentRead;
import com.ebiz.mmt.service.KonkaCommentReadService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-10-22 11:24:46
 */
@Service
public class KonkaCommentReadServiceImpl implements KonkaCommentReadService {

	@Resource
	private KonkaCommentReadDao konkaCommentReadDao;
	

	public Long createKonkaCommentRead(KonkaCommentRead t) {
		return this.konkaCommentReadDao.insertEntity(t);
	}

	public KonkaCommentRead getKonkaCommentRead(KonkaCommentRead t) {
		return this.konkaCommentReadDao.selectEntity(t);
	}

	public Long getKonkaCommentReadCount(KonkaCommentRead t) {
		return this.konkaCommentReadDao.selectEntityCount(t);
	}

	public List<KonkaCommentRead> getKonkaCommentReadList(KonkaCommentRead t) {
		return this.konkaCommentReadDao.selectEntityList(t);
	}

	public int modifyKonkaCommentRead(KonkaCommentRead t) {
		return this.konkaCommentReadDao.updateEntity(t);
	}

	public int removeKonkaCommentRead(KonkaCommentRead t) {
		return this.konkaCommentReadDao.deleteEntity(t);
	}

	public List<KonkaCommentRead> getKonkaCommentReadPaginatedList(KonkaCommentRead t) {
		return this.konkaCommentReadDao.selectEntityPaginatedList(t);
	}

}
