package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.FromDrpOrder;


public interface FromDrpOrderService {

	Long createFromDrpOrder(FromDrpOrder t);

	int modifyFromDrpOrder(FromDrpOrder t);

	int removeFromDrpOrder(FromDrpOrder t);

	FromDrpOrder getFromDrpOrder(FromDrpOrder t);

	List<FromDrpOrder> getFromDrpOrderList(FromDrpOrder t);

	Long getFromDrpOrderCount(FromDrpOrder t);

	List<FromDrpOrder> getFromDrpOrderPaginatedList(FromDrpOrder t);

    /**
     * 获取从drp系统过来的订单,并且在渠道系统已经审批完结的订单数据 (只有头表数据)
     * 
     * @param t
     * @return
     */
    List<FromDrpOrder> getFDrpAndQdOrderList(FromDrpOrder t);

    //FromDrpOrderDetailService#getFDrpOrderDetailAndQddata 可以取得明细数据
    // from_drp_order + konka_order_info --> to_drp_order
    // from_drp_order_detail + konka_order_info_detail --> to_drp_order_detail

    /**
     * 把已审批完的订单数据和from_drp_order原始数据做关联,然后同步到to_drp_order中,等待同步至drp系统
     */
    void createAllFromData2ToData();

    /**
     * 把已审批完的订单数据和from_drp_order原始数据做关联,然后同步到to_drp_order中,等待同步至drp系统
     * @param index (渠道系统的订单流水号)
     * @return 0 成功, -1失败
     */
    int createFromData2ToData(String index);
}