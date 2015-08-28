package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.YwtAskLeave;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-10-20 10:01:47
 */
public interface YwtAskLeaveService {

	Long createYwtAskLeave(YwtAskLeave t);

	int modifyYwtAskLeave(YwtAskLeave t);

	int removeYwtAskLeave(YwtAskLeave t);

	YwtAskLeave getYwtAskLeave(YwtAskLeave t);

	List<YwtAskLeave> getYwtAskLeaveList(YwtAskLeave t);

	Long getYwtAskLeaveCount(YwtAskLeave t);

	List<YwtAskLeave> getYwtAskLeavePaginatedList(YwtAskLeave t);

}