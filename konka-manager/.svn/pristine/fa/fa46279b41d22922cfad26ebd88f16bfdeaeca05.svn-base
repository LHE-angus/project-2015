package com.ebiz.mmt.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.FromDrpOrderDao;
import com.ebiz.mmt.dao.ToDrpOrderDao;
import com.ebiz.mmt.dao.ToDrpOrderDetailDao;
import com.ebiz.mmt.domain.FromDrpOrder;
import com.ebiz.mmt.domain.FromDrpOrderDetail;
import com.ebiz.mmt.domain.ToDrpOrder;
import com.ebiz.mmt.domain.ToDrpOrderDetail;
import com.ebiz.mmt.service.FromDrpOrderDetailService;
import com.ebiz.mmt.service.FromDrpOrderService;

@Service
public class FromDrpOrderServiceImpl implements FromDrpOrderService {

    @Resource
    private FromDrpOrderDao fromDrpOrderDao;

    @Resource
    private FromDrpOrderDetailService fddservice;

    @Resource
    private ToDrpOrderDao tdoservice;

    @Resource
    private ToDrpOrderDetailDao tddservice;


    public Long createFromDrpOrder(FromDrpOrder t) {
        return this.fromDrpOrderDao.insertEntity(t);
    }

    public FromDrpOrder getFromDrpOrder(FromDrpOrder t) {
        return this.fromDrpOrderDao.selectEntity(t);
    }

    public Long getFromDrpOrderCount(FromDrpOrder t) {
        return this.fromDrpOrderDao.selectEntityCount(t);
    }

    public List<FromDrpOrder> getFromDrpOrderList(FromDrpOrder t) {
        return this.fromDrpOrderDao.selectEntityList(t);
    }

    public int modifyFromDrpOrder(FromDrpOrder t) {
        return this.fromDrpOrderDao.updateEntity(t);
    }

    public int removeFromDrpOrder(FromDrpOrder t) {
        return this.fromDrpOrderDao.deleteEntity(t);
    }

    public List<FromDrpOrder> getFromDrpOrderPaginatedList(FromDrpOrder t) {
        return this.fromDrpOrderDao.selectFDrpAndQdOrderList(t);
    }

    @Override
    public List<FromDrpOrder> getFDrpAndQdOrderList(FromDrpOrder t) {
        return this.fromDrpOrderDao.selectFDrpAndQdOrderList(t);
    }

    @Override
    public void createAllFromData2ToData() {
        createFromData2ToData(null);
    }

    @Override
    public int createFromData2ToData(String index) {

        List<FromDrpOrder> orderList = new ArrayList<FromDrpOrder>();
        FromDrpOrder fo = new FromDrpOrder();
        if (index != null && !"".equals(index)) {
            fo.setQd_tran_index(index);
        }
        orderList = getFDrpAndQdOrderList(fo);//

        if (orderList != null) {
            for (FromDrpOrder fdo : orderList) {
                // 处理明细头
                ToDrpOrder tdo = new ToDrpOrder();
                tdo.setDiscount_price(fdo.getDiscount_price());
                tdo.setDoc_type(fdo.getDoc_type());
                tdo.setDs_order_id(fdo.getDs_order_id());// important
                tdo.setFrom_date(fdo.getFrom_date());

                String isdel = String.valueOf(fdo.getMap().get("is_del"));
                tdo.setIs_del(Long.valueOf(isdel));

                tdo.setOrder_date(fdo.getOrder_date());
                tdo.setOrder_money(fdo.getOrder_money());
                tdo.setOrder_num(fdo.getOrder_num());
                tdo.setQd_tran_index(fdo.getQd_tran_index());
                tdo.setR3_code(fdo.getR3_code());
                tdo.setRemark(fdo.getRemark());

                tdo.setSync_count(0L);
                tdo.setSync_date(null);
                tdo.setSync_ok(null);

                tdo.setUser_addr(fdo.getUser_addr());
                tdo.setUser_name(fdo.getUser_name());
                tdo.setUser_phone(fdo.getUser_phone());
                tdo.setUser_tel(fdo.getUser_tel());
                tdo.setUser_zip(fdo.getUser_zip());

                tdoservice.insertEntity(tdo);

                // 处理明细行
                List<FromDrpOrderDetail> orderDetailList = new ArrayList<FromDrpOrderDetail>();
                orderDetailList = fddservice.getFDrpOrderDetailAndQddata(fdo.getDs_order_id());// important

                if (orderDetailList != null) {
                    for (FromDrpOrderDetail fdd : orderDetailList) {
                        ToDrpOrderDetail tdd = new ToDrpOrderDetail();
                        tdd.setDs_order_id(fdd.getDs_order_id());
                        tdd.setDs_order_fk_id(fdd.getDs_order_fk_id());
                        tdd.setGood_count(fdd.getGood_count());
                        tdd.setGood_discount(fdd.getGood_discount());;
                        tdd.setGood_discount_price(fdd.getGood_discount_price());
                        tdd.setGood_price(fdd.getGood_price());
                        tdd.setGood_sum_price(fdd.getGood_sum_price());
                        tdd.setPd_name(fdd.getPd_name());;
                        tdd.setPd_remark(fdd.getPd_remark());
                        tddservice.insertEntity(tdd);
                    }
                }
            }
            return 0;
        } else {

            // 查不到信息
            return -1;
        }

    }

}
