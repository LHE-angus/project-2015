package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaXxNotice;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2012-03-19 09:15:51
 */
public interface KonkaXxNoticeService {

	Long createKonkaXxNotice(KonkaXxNotice t);

	int modifyKonkaXxNotice(KonkaXxNotice t);

	int removeKonkaXxNotice(KonkaXxNotice t);

	KonkaXxNotice getKonkaXxNotice(KonkaXxNotice t);

	List<KonkaXxNotice> getKonkaXxNoticeList(KonkaXxNotice t);

	Long getKonkaXxNoticeCount(KonkaXxNotice t);

	List<KonkaXxNotice> getKonkaXxNoticePaginatedList(KonkaXxNotice t);

	/**
	 * @author Wu,ShangLong
	 * @version 2012-03-19
	 */
	Long createKonkaXxNoticeAndContent(KonkaXxNotice t);

	int modifyKonkaXxNoticeAndContent(KonkaXxNotice t);

	void publishKonkaXxNotice(KonkaXxNotice t);

	Long getKonkaXxNoticeAndContentCount(KonkaXxNotice t);

	List<KonkaXxNotice> getKonkaXxNoticeAndContentPaginatedList(KonkaXxNotice t);

}