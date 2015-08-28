package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.JfScorts;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-06-25 15:25:02
 */
public interface JfScortsService {

	Long createJfScorts(JfScorts t);

	int modifyJfScorts(JfScorts t);

	int removeJfScorts(JfScorts t);

	JfScorts getJfScorts(JfScorts t);

	List<JfScorts> getJfScortsList(JfScorts t);

	Long getJfScortsCount(JfScorts t);

	List<JfScorts> getJfScortsPaginatedList(JfScorts t);

	// 根据会员卡获取总积分
	List<JfScorts> getJfScortsForScortsList(JfScorts t);

}