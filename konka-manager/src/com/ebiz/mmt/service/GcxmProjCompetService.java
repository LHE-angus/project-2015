package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.GcxmProjCompet;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2015-03-23 13:53:09
 */
public interface GcxmProjCompetService {

	Long createGcxmProjCompet(GcxmProjCompet t);

	int modifyGcxmProjCompet(GcxmProjCompet t);

	int removeGcxmProjCompet(GcxmProjCompet t);

	GcxmProjCompet getGcxmProjCompet(GcxmProjCompet t);

	List<GcxmProjCompet> getGcxmProjCompetList(GcxmProjCompet t);

	Long getGcxmProjCompetCount(GcxmProjCompet t);

	List<GcxmProjCompet> getGcxmProjCompetPaginatedList(GcxmProjCompet t);

	Long getGcxmProjCompetForProjCount(GcxmProjCompet t);

	List<GcxmProjCompet> getGcxmProjCompetForProjPaginatedList(GcxmProjCompet t);

}