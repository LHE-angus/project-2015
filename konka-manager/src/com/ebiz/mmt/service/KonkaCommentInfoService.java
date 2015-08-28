package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaCommentInfo;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-10-22 11:24:46
 */
public interface KonkaCommentInfoService {

	Long createKonkaCommentInfo(KonkaCommentInfo t);

	int modifyKonkaCommentInfo(KonkaCommentInfo t);

	int removeKonkaCommentInfo(KonkaCommentInfo t);

	KonkaCommentInfo getKonkaCommentInfo(KonkaCommentInfo t);

	List<KonkaCommentInfo> getKonkaCommentInfoList(KonkaCommentInfo t);

	Long getKonkaCommentInfoCount(KonkaCommentInfo t);

	List<KonkaCommentInfo> getKonkaCommentInfoPaginatedList(KonkaCommentInfo t);

	/**
	 * 用递归获取评论的父和子<br/>
	 * 2014-10-23
	 * 
	 * @author Xiao,GuoJian
	 * @param entity
	 * @return
	 */
	List<KonkaCommentInfo> getKonkaCommentInfoListFatherAndChildren(KonkaCommentInfo entity);

}