package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.MvOrgOfCustomerAll;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2015-05-25 14:26:30
 */
public interface MvOrgOfCustomerAllService {

	Long createMvOrgOfCustomerAll(MvOrgOfCustomerAll t);

	int modifyMvOrgOfCustomerAll(MvOrgOfCustomerAll t);

	int removeMvOrgOfCustomerAll(MvOrgOfCustomerAll t);

	MvOrgOfCustomerAll getMvOrgOfCustomerAll(MvOrgOfCustomerAll t);

	List<MvOrgOfCustomerAll> getMvOrgOfCustomerAllList(MvOrgOfCustomerAll t);

	Long getMvOrgOfCustomerAllCount(MvOrgOfCustomerAll t);

	List<MvOrgOfCustomerAll> getMvOrgOfCustomerAllPaginatedList(MvOrgOfCustomerAll t);

	int updateCustomerInfoAll(MvOrgOfCustomerAll t);
}