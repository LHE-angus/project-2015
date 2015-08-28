package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.StdEntpKeysKeys;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-09-20 15:43:53
 */
public interface StdEntpKeysKeysService {

	Long createStdEntpKeysKeys(StdEntpKeysKeys t);

	int modifyStdEntpKeysKeys(StdEntpKeysKeys t);

	int removeStdEntpKeysKeys(StdEntpKeysKeys t);

	StdEntpKeysKeys getStdEntpKeysKeys(StdEntpKeysKeys t);

	List<StdEntpKeysKeys> getStdEntpKeysKeysList(StdEntpKeysKeys t);

	Long getStdEntpKeysKeysCount(StdEntpKeysKeys t);

	List<StdEntpKeysKeys> getStdEntpKeysKeysPaginatedList(StdEntpKeysKeys t);

}