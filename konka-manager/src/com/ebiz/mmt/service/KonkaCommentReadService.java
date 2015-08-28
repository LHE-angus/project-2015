package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaCommentRead;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-10-22 11:24:46
 */
public interface KonkaCommentReadService {

	Long createKonkaCommentRead(KonkaCommentRead t);

	int modifyKonkaCommentRead(KonkaCommentRead t);

	int removeKonkaCommentRead(KonkaCommentRead t);

	KonkaCommentRead getKonkaCommentRead(KonkaCommentRead t);

	List<KonkaCommentRead> getKonkaCommentReadList(KonkaCommentRead t);

	Long getKonkaCommentReadCount(KonkaCommentRead t);

	List<KonkaCommentRead> getKonkaCommentReadPaginatedList(KonkaCommentRead t);

}