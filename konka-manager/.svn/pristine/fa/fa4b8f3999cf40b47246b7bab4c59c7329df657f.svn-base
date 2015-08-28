package com.ebiz.mmt.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.QdDrpSyncLogDao;
import com.ebiz.mmt.dao.ToDrpOrderDao;
import com.ebiz.mmt.dao.ToDrpOrderDetailDao;
import com.ebiz.mmt.domain.QdDrpSyncLog;
import com.ebiz.mmt.domain.ToDrpOrder;
import com.ebiz.mmt.domain.ToDrpOrderDetail;
import com.ebiz.mmt.service.ToDrpOrderService;
import com.ebiz.mmt.web.struts.inter.form.AuditedPurchaseOrder;
import com.ebiz.mmt.web.struts.inter.form.AuditedPurchaseOrderDetail;
import com.ebiz.mmt.web.struts.inter.form.AuditedPurchaseOrderWrap;
import com.ebiz.mmt.web.util.GSONTimestampTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rock.esb.EsbClientUtil;

@Service
public class ToDrpOrderServiceImpl implements ToDrpOrderService {

    private static final Logger logger = LoggerFactory.getLogger(ToDrpOrderServiceImpl.class);

    @Resource
    private ToDrpOrderDao toDrpOrderDao;

    @Resource
    private ToDrpOrderDetailDao toDrpOrderDetailDao;

    @Resource
    private QdDrpSyncLogDao qdDrpSyncLogDao;

    public Long createToDrpOrder(ToDrpOrder t) {
        return this.toDrpOrderDao.insertEntity(t);
    }

    public ToDrpOrder getToDrpOrder(ToDrpOrder t) {
        return this.toDrpOrderDao.selectEntity(t);
    }

    public Long getToDrpOrderCount(ToDrpOrder t) {
        return this.toDrpOrderDao.selectEntityCount(t);
    }

    public List<ToDrpOrder> getToDrpOrderList(ToDrpOrder t) {
        return this.toDrpOrderDao.selectEntityList(t);
    }

    public int modifyToDrpOrder(ToDrpOrder t) {
        return this.toDrpOrderDao.updateEntity(t);
    }

    public int removeToDrpOrder(ToDrpOrder t) {
        return this.toDrpOrderDao.deleteEntity(t);
    }

    public List<ToDrpOrder> getToDrpOrderPaginatedList(ToDrpOrder t) {
        return this.toDrpOrderDao.selectEntityPaginatedList(t);
    }

    @Override
    public void ceateTransQDorderDataToDRP() {
        dosync();
    }

    // private final static String APPSECRET = "56daa22182b64121bff28415700dcdf4";
    // private final static String APPKEY = "QDG";
    // private final static String BUSINESSID = "qdg00001";
    // private final static String ESBIP = "192.168.2.192";
    // private final static String ESBPORT = "8080";
    // private final static String METHOD = "qdg.saleOrder.confirmed";

    // 正式环境
    private final static String APPSECRET = "56daa22182b64121bff28415700dcdf4";
    private final static String APPKEY = "QDG";
    private final static String BUSINESSID = "qdg00001";
    private final static String ESBIP = "192.168.2.196";
    private final static String ESBPORT = "8080";
    private final static String METHOD = "qdg.saleOrder.confirmed";

    private static String appsecret = APPSECRET;
    private static String appkey = APPKEY;
    private static String businessid = BUSINESSID;
    private static String esbip = ESBIP;
    private static String esbport = ESBPORT;
    private static String method = METHOD;

    private void dosync() {

        List<ToDrpOrder> toList = new ArrayList<ToDrpOrder>();

        toList = toDrpOrderDao.selectShouldbeSYNCEntityList();

        if (toList != null && toList.size() > 0) {
            for (ToDrpOrder tdo : toList) {

                // 订单明细集合
                List<ToDrpOrderDetail> tddList = new ArrayList<ToDrpOrderDetail>();
                ToDrpOrderDetail tdd = new ToDrpOrderDetail();
                tdd.setDs_order_fk_id(tdo.getDs_order_id());
                tddList = toDrpOrderDetailDao.selectEntityList(tdd);

                // 回传给drp系统的对象
                AuditedPurchaseOrder ao = new AuditedPurchaseOrder();
                AuditedPurchaseOrderDetail[] ado = null;

                ao.setDocType(tdo.getDoc_type());
                ao.setDsOrderId(tdo.getDs_order_id());
                ao.setGoodDiscountPrice(tdo.getDiscount_price());
                ao.setMoney(tdo.getOrder_money());
                ao.setOrderDate(tdo.getOrder_date());
                ao.setOrderNum(tdo.getOrder_num());
                ao.setR3Code(tdo.getR3_code());
                ao.setUserAddr(tdo.getUser_addr());
                ao.setUserName(tdo.getUser_name());
                ao.setUserPhone(tdo.getUser_phone());
                ao.setUserRemark(tdo.getRemark());
                ao.setUserTel(tdo.getUser_tel());
                ao.setUserZip(tdo.getUser_zip());

                if (tddList != null && tddList.size() > 0) {
                    // 调用drp接口传数据
                    ado = new AuditedPurchaseOrderDetail[tddList.size()];

                    for (int i = 0; i < tddList.size(); i++) {
                        ToDrpOrderDetail tdetail = tddList.get(i);
                        AuditedPurchaseOrderDetail aoo = new AuditedPurchaseOrderDetail();
                        aoo.setDsOrderFKid(tdetail.getDs_order_fk_id());
                        aoo.setDsOrderId(tdetail.getDs_order_id());
                        aoo.setGoodCount(Integer.valueOf(String.valueOf(tdetail.getGood_count())));
                        aoo.setGoodDiscount(tdetail.getGood_discount());
                        aoo.setGoodDiscountPrice(tdetail.getGood_discount_price());
                        aoo.setGoodPrice(tdetail.getGood_price());
                        aoo.setGoodSumPrice(tdetail.getGood_sum_price());
                        aoo.setPdName(tdetail.getPd_name());
                        aoo.setPdRemark(tdetail.getPd_remark());
                        ado[i] = aoo;
                    }
                }

                AuditedPurchaseOrderWrap wrap = new AuditedPurchaseOrderWrap();
                if (tdo.getIs_del() == 0L) {
                    wrap.setAuditFlag(true);
                } else {
                    wrap.setAuditFlag(false);
                }

                // TODO 如何定义这个值
                wrap.setUpdateFlag(true);
                wrap.setAuditedPurchaseOrder(ao);
                wrap.setAuditedPurchaseOrderDetail(ado);

                String jsonString = "";
                Gson gson = new GsonBuilder().registerTypeAdapter(Timestamp.class, new GSONTimestampTypeAdapter()).setDateFormat("yyyy-MM-dd HH:mm:ss").create();
                jsonString = gson.toJson(wrap);
                String res = "";
                try {
                    res = EsbClientUtil.callESBMethodForString(appsecret, appkey, businessid, jsonString, method, esbip, esbport);
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }

                String success = "";

                JSONObject jso = new JSONObject();
                jso = JSONObject.fromObject(res);
                success = jso.getString("success");

                long syncount = (tdo.getSync_count() == null ? 0L : tdo.getSync_count()) + 1L;

                QdDrpSyncLog ql = new QdDrpSyncLog();
                ql.setFunc("QD_2_DRP_SYNC_FUNC");
                Date thedate = new Date();

                if (success != null && "true".equals(success)) {
                    tdo.setSync_count(syncount);
                    tdo.setSync_date(thedate);
                    tdo.setSync_ok(0L);
                    ql.setHappen_time(thedate);
                    ql.setMsg(jso.getString("resultMsg"));
                    ql.setOk_flag("0");
                    ql.setBid(tdo.getDs_order_id());
                } else {
                    tdo.setSync_count(syncount);
                    tdo.setSync_date(thedate);
                    tdo.setSync_ok(-1L);
                    ql.setHappen_time(thedate);
                    ql.setMsg(jso.getString("resultMsg"));
                    ql.setOk_flag("-1");
                    ql.setBid(tdo.getDs_order_id());
                }
                toDrpOrderDao.updateEntity(tdo);
                qdDrpSyncLogDao.insertEntity(ql);
            }
        }

    }

}
