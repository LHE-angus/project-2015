package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.MvOrgOfCustomer;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2015-05-25 14:26:30
 */
public interface MvOrgOfCustomerService {

	Long createMvOrgOfCustomer(MvOrgOfCustomer t);

	int modifyMvOrgOfCustomer(MvOrgOfCustomer t);

	int removeMvOrgOfCustomer(MvOrgOfCustomer t);

	MvOrgOfCustomer getMvOrgOfCustomer(MvOrgOfCustomer t);

	List<MvOrgOfCustomer> getMvOrgOfCustomerList(MvOrgOfCustomer t);

	Long getMvOrgOfCustomerCount(MvOrgOfCustomer t);

	List<MvOrgOfCustomer> getMvOrgOfCustomerPaginatedList(MvOrgOfCustomer t);

	int updateCustomerInfo(MvOrgOfCustomer t);
}