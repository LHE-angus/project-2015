package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.GcxmProj;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2015-03-23 13:53:09
 */
public interface GcxmProjService {

	Long createGcxmProj(GcxmProj t);

	int modifyGcxmProjForFj(GcxmProj t);

	int modifyGcxmProjForCh(GcxmProj t);

	int modifyGcxmProj(GcxmProj t);

	int removeGcxmProj(GcxmProj t);

	GcxmProj getGcxmProj(GcxmProj t);

	List<GcxmProj> getGcxmProjList(GcxmProj t);

	Long getGcxmProjCount(GcxmProj t);

	List<GcxmProj> getGcxmProjPaginatedList(GcxmProj t);

	Long getGcxmProjForUnionCount(GcxmProj t);

	List<GcxmProj> getGcxmProjForUnionPaginatedList(GcxmProj t);

}