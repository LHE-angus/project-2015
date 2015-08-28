package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.EcVoteMain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-10-29 14:02:24
 */
public interface EcVoteMainService {

	Long createEcVoteMain(EcVoteMain t);

	int modifyEcVoteMain(EcVoteMain t);

	int removeEcVoteMain(EcVoteMain t);

	EcVoteMain getEcVoteMain(EcVoteMain t);

	List<EcVoteMain> getEcVoteMainList(EcVoteMain t);

	Long getEcVoteMainCount(EcVoteMain t);

	List<EcVoteMain> getEcVoteMainPaginatedList(EcVoteMain t);
	
	int modifyEcVoteMainViewCounts(EcVoteMain t);

}