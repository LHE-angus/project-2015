package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.StdEntpProdJoinBrandId;

/**
 * @author Wu,Yang
 * @version 2010-10-25 11:39:12
 */
public interface StdEntpProdJoinBrandIdService {

	Long createStdEntpProdJoinBrandId(StdEntpProdJoinBrandId t);

	int modifyStdEntpProdJoinBrandId(StdEntpProdJoinBrandId t);

	int removeStdEntpProdJoinBrandId(StdEntpProdJoinBrandId t);

	StdEntpProdJoinBrandId getStdEntpProdJoinBrandId(StdEntpProdJoinBrandId t);

	List<StdEntpProdJoinBrandId> getStdEntpProdJoinBrandIdList(StdEntpProdJoinBrandId t);

	Long getStdEntpProdJoinBrandIdCount(StdEntpProdJoinBrandId t);

	List<StdEntpProdJoinBrandId> getStdEntpProdJoinBrandIdPaginatedList(StdEntpProdJoinBrandId t);

	/**
	 * @author Du,HongGang
	 * @version 2010-10-26
	 */
	public int removeStdEntpProdJoinBrandIdByEntpIdOrBrandId(StdEntpProdJoinBrandId t);

	/**
	 * @author Wu,ShangLong
	 * @version 2011-03-10
	 */
	List<StdEntpProdJoinBrandId> getStdEntpProdJoinBrandIdWithEntpName(StdEntpProdJoinBrandId t);
}
