package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.MmtShopCustomer;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2010-05-28 16:48:39
 */
public interface MmtShopCustomerService {

	Long createMmtShopCustomer(MmtShopCustomer t);

	int modifyMmtShopCustomer(MmtShopCustomer t);

	int removeMmtShopCustomer(MmtShopCustomer t);

	MmtShopCustomer getMmtShopCustomer(MmtShopCustomer t);

	List<MmtShopCustomer> getMmtShopCustomerList(MmtShopCustomer t);

	Long getMmtShopCustomerCount(MmtShopCustomer t);

	List<MmtShopCustomer> getMmtShopCustomerPaginatedList(MmtShopCustomer t);

}