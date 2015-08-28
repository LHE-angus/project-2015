package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaCommentScoreDao;
import com.ebiz.mmt.domain.KonkaCommentScore;
import com.ebiz.mmt.service.KonkaCommentScoreService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-10-22 11:24:46
 */
@Service
public class KonkaCommentScoreServiceImpl implements KonkaCommentScoreService {

	@Resource
	private KonkaCommentScoreDao konkaCommentScoreDao;
	

	public Long createKonkaCommentScore(KonkaCommentScore t) {
		return this.konkaCommentScoreDao.insertEntity(t);
	}

	public KonkaCommentScore getKonkaCommentScore(KonkaCommentScore t) {
		return this.konkaCommentScoreDao.selectEntity(t);
	}

	public Long getKonkaCommentScoreCount(KonkaCommentScore t) {
		return this.konkaCommentScoreDao.selectEntityCount(t);
	}

	public List<KonkaCommentScore> getKonkaCommentScoreList(KonkaCommentScore t) {
		return this.konkaCommentScoreDao.selectEntityList(t);
	}

	public int modifyKonkaCommentScore(KonkaCommentScore t) {
		return this.konkaCommentScoreDao.updateEntity(t);
	}

	public int removeKonkaCommentScore(KonkaCommentScore t) {
		return this.konkaCommentScoreDao.deleteEntity(t);
	}

	public List<KonkaCommentScore> getKonkaCommentScorePaginatedList(KonkaCommentScore t) {
		return this.konkaCommentScoreDao.selectEntityPaginatedList(t);
	}

}
