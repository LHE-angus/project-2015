package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.ToDrpOrderDetail;


public interface ToDrpOrderDetailService {

	Long createToDrpOrderDetail(ToDrpOrderDetail t);

	int modifyToDrpOrderDetail(ToDrpOrderDetail t);

	int removeToDrpOrderDetail(ToDrpOrderDetail t);

	ToDrpOrderDetail getToDrpOrderDetail(ToDrpOrderDetail t);

	List<ToDrpOrderDetail> getToDrpOrderDetailList(ToDrpOrderDetail t);

	Long getToDrpOrderDetailCount(ToDrpOrderDetail t);

	List<ToDrpOrderDetail> getToDrpOrderDetailPaginatedList(ToDrpOrderDetail t);


    // /
}