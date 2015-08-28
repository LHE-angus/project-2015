package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaCommentScore;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-10-22 11:24:46
 */
public interface KonkaCommentScoreService {

	Long createKonkaCommentScore(KonkaCommentScore t);

	int modifyKonkaCommentScore(KonkaCommentScore t);

	int removeKonkaCommentScore(KonkaCommentScore t);

	KonkaCommentScore getKonkaCommentScore(KonkaCommentScore t);

	List<KonkaCommentScore> getKonkaCommentScoreList(KonkaCommentScore t);

	Long getKonkaCommentScoreCount(KonkaCommentScore t);

	List<KonkaCommentScore> getKonkaCommentScorePaginatedList(KonkaCommentScore t);

}