package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.MvOrgOfCxyAll;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2015-05-25 14:26:30
 */
public interface MvOrgOfCxyAllService {

	Long createMvOrgOfCxyAll(MvOrgOfCxyAll t);

	int modifyMvOrgOfCxyAll(MvOrgOfCxyAll t);

	int removeMvOrgOfCxyAll(MvOrgOfCxyAll t);

	MvOrgOfCxyAll getMvOrgOfCxyAll(MvOrgOfCxyAll t);

	List<MvOrgOfCxyAll> getMvOrgOfCxyAllList(MvOrgOfCxyAll t);

	Long getMvOrgOfCxyAllCount(MvOrgOfCxyAll t);

	List<MvOrgOfCxyAll> getMvOrgOfCxyAllPaginatedList(MvOrgOfCxyAll t);

}