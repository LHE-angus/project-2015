package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.ToDrpOrder;


public interface ToDrpOrderService {

    Long createToDrpOrder(ToDrpOrder t);

    int modifyToDrpOrder(ToDrpOrder t);

    int removeToDrpOrder(ToDrpOrder t);

    ToDrpOrder getToDrpOrder(ToDrpOrder t);

    List<ToDrpOrder> getToDrpOrderList(ToDrpOrder t);

    Long getToDrpOrderCount(ToDrpOrder t);

    List<ToDrpOrder> getToDrpOrderPaginatedList(ToDrpOrder t);


    /**
     * 将to_drp_order_info 订单池的数据同步至drp系统<br>
     * 
     * 调用drp系统接口
     */
    void ceateTransQDorderDataToDRP();

}
