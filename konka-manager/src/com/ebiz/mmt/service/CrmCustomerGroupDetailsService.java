package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.CrmCustomerGroupDetails;


public interface CrmCustomerGroupDetailsService {

	Long createCrmCustomerGroupDetails(CrmCustomerGroupDetails t);

	int modifyCrmCustomerGroupDetails(CrmCustomerGroupDetails t);

	int removeCrmCustomerGroupDetails(CrmCustomerGroupDetails t);

	CrmCustomerGroupDetails getCrmCustomerGroupDetails(CrmCustomerGroupDetails t);

	List<CrmCustomerGroupDetails> getCrmCustomerGroupDetailsList(CrmCustomerGroupDetails t);

	Long getCrmCustomerGroupDetailsCount(CrmCustomerGroupDetails t);

	List<CrmCustomerGroupDetails> getCrmCustomerGroupDetailsPaginatedList(CrmCustomerGroupDetails t);

}