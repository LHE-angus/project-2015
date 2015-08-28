package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.FromDrpOrderDetail;

public interface FromDrpOrderDetailService {


	Long createFromDrpOrderDetail(FromDrpOrderDetail t);

	int modifyFromDrpOrderDetail(FromDrpOrderDetail t);

	int removeFromDrpOrderDetail(FromDrpOrderDetail t);

	FromDrpOrderDetail getFromDrpOrderDetail(FromDrpOrderDetail t);

	List<FromDrpOrderDetail> getFromDrpOrderDetailList(FromDrpOrderDetail t);

	Long getFromDrpOrderDetailCount(FromDrpOrderDetail t);

	List<FromDrpOrderDetail> getFromDrpOrderDetailPaginatedList(FromDrpOrderDetail t);


    /**
     * 根据外键(订单头id),再获取渠道流水号,再根据流水号获取渠道系统已审批的订单项明细
     * 
     * @param ds_order_id drp订单头id
     * @return
     */
    List<FromDrpOrderDetail> getFDrpOrderDetailAndQddata(String ds_order_id);

}