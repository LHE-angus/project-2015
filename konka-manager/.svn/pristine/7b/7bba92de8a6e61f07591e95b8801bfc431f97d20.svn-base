package com.ebiz.mmt.web.struts.inter.action;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.BranchAssign;
import com.ebiz.mmt.domain.FromDrpOrder;
import com.ebiz.mmt.domain.FromDrpOrderDetail;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaOrderInfo;
import com.ebiz.mmt.domain.KonkaOrderInfoDetails;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.PePdModel;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.r3.KNVP;
import com.ebiz.mmt.r3.ReturnInfo;
import com.ebiz.mmt.web.struts.inter.form.DrpExcuteMsg;
import com.ebiz.mmt.web.struts.inter.form.PurchaseOrder;
import com.ebiz.mmt.web.struts.inter.form.PurchaseOrderDetail;

/**
 * 
 * 目前开放给DRP系统使用
 * 
 * @author zhouhaojie
 * 
 */
public class QdOrderCreateAction extends BaseInterAction {
    public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        return this.addOrder(mapping, form, request, response);
    }

    public ActionForward addOrder(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ParseException {
        DrpExcuteMsg drm = new DrpExcuteMsg();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String data = request.getParameter("data");

        if (data == null || "".equals(data)) {
            drm.setSuccessflag(false);
            drm.getReturnMsg().add("*丢失重要数据!");
        }

        // 检查重要的几个参数
        if (data.indexOf("purchaseOrderDetail") == -1 || data.indexOf("dsOrderId") == -1) {
            drm.setSuccessflag(false);
            drm.getReturnMsg().add("*json数据格式非法!");
        }

        JSONObject alljsonObject;
        // JSONObject purchaseOrderObject;
        JSONArray purchaseOrderDetail;


        alljsonObject = JSONObject.fromObject(data);


        // 订单头数据
        // purchaseOrderObject = alljsonObject.getJSONObject("purchaseOrder");
        // 订单行数据
        purchaseOrderDetail = alljsonObject.getJSONArray("purchaseOrderDetail");

        // 取出传过来的订单的信息
        String dsOrderId = alljsonObject.getString("dsOrderId");
        String r3Code = alljsonObject.getString("r3Code");
        String docType = alljsonObject.getString("docType");
        Long orderNum = alljsonObject.getLong("orderNum");
        BigDecimal money = new BigDecimal(alljsonObject.getString("money"));
        BigDecimal goodDiscountPrice = new BigDecimal(alljsonObject.getString("goodDiscountPrice"));
        String userName = alljsonObject.getString("userName");
        String userTel = alljsonObject.getString("userTel");
        String userPhone = alljsonObject.getString("userPhone");
        String userAddr = alljsonObject.getString("userAddr");
        String userZip = alljsonObject.getString("userZip");
        String userRemark = alljsonObject.getString("userRemark");
        Date orderDate;
        try {
            orderDate = sdf.parse(alljsonObject.getString("orderDate"));
        } catch (ParseException e) {
            throw e;
        }
        // 订单明细数组
        PurchaseOrderDetail[] orderDetail = new PurchaseOrderDetail[purchaseOrderDetail.size()];
        for (int i = 0; i < purchaseOrderDetail.size(); i++) {

            JSONObject js = (JSONObject) purchaseOrderDetail.get(i);
            PurchaseOrderDetail d = new PurchaseOrderDetail();

            d.setGoodCount(Integer.valueOf(js.getString("goodCount")));
            d.setGoodDiscount(new BigDecimal(js.getString("goodDiscount")));
            d.setGoodDiscountPrice(new BigDecimal(js.getString("goodDiscountPrice")));
            d.setGoodPrice(new BigDecimal(js.getString("goodPrice")));
            d.setGoodSumPrice(new BigDecimal(js.getString("goodSumPrice")));
            d.setPdName(js.getString("pdName"));
            d.setPdRemark(js.getString("pdRemark"));
            d.setDsOrderFKid(dsOrderId);// 头表的主键,明细表的外键
            d.setDsOrderId(js.getString("dsOrderId"));// 明细表的key

            orderDetail[i] = d;
        }

        // 对传过来的订单信息进行必要的校验
        // r3code,docType,dsOrderId ;
        drm = OrderInfoValidate(r3Code, docType, dsOrderId);

        // 如果验证通过
        if (drm.isSuccessflag()) {
            drm.getReturnMsg().clear();

            // 调用渠道生成订单的接口生成订单,并持久化原始订单信息
            PurchaseOrder orderinfo = new PurchaseOrder();
            orderinfo.addOrderInfo(dsOrderId, r3Code, docType, orderNum, money, goodDiscountPrice, orderDate);
            orderinfo.addUserInfo(userName, userTel, userPhone, userAddr, userZip, userRemark);

            // 生成渠道系统订单
            String index = createOrder(orderinfo, orderDetail);
            drm.setSuccessflag(true);
            drm.getReturnMsg().add("*执行成功,渠道系统流水号:" + index + "");
        }

        JSONObject text = JSONObject.fromObject(drm);

        super.renderJson(response, text.toString());

        return null;
    }



    /**
     * 渠道系统订单流水号
     * 
     * @param orderinfo
     * @param orderDetail
     * @return
     */
    private String createOrder(PurchaseOrder orderinfo, PurchaseOrderDetail[] orderDetail) {

        final Date nowDate = new Date();

        // 客户信息,客户账号(模拟客户下单功能)

        final String r3code = orderinfo.getR3Code();
        Long custId = null;
        String salesOrg = "";
        String ag = "";
        String re = "";
        String rg = "";
        String we = "";

        KonkaR3Shop ks = new KonkaR3Shop();
        ks.setR3_code(r3code);
        ks.setIs_del(0L);
        ks = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(ks);
        // 在此之前有方法已经校验
        if (ks != null) {
            custId = ks.getId();
            salesOrg = ks.getR3_sales_org_code();
        }

        ReturnInfo<KNVP> info = new ReturnInfo<KNVP>();
        info = super.getFacade().getR3WebInterfaceService().getKnvpsList(salesOrg, "10", "10", r3code);
        if (info.getSuccess()) {
            List<KNVP> knvpList = info.getDataResult();
            // AG:售达方
            // RE:出票方
            // RG:付款方
            // WE:送达方
            List<KNVP> agList = new ArrayList<KNVP>();
            List<KNVP> reList = new ArrayList<KNVP>();
            List<KNVP> rgList = new ArrayList<KNVP>();
            List<KNVP> weList = new ArrayList<KNVP>();
            if (null != knvpList) {
                for (KNVP cur : knvpList) {
                    if ("AG".equalsIgnoreCase(cur.getPARVW())) {
                        agList.add(cur);
                    } else if ("RE".equalsIgnoreCase(cur.getPARVW())) {
                        reList.add(cur);
                    } else if ("RG".equalsIgnoreCase(cur.getPARVW())) {
                        rgList.add(cur);
                    } else if ("WE".equalsIgnoreCase(cur.getPARVW())) {
                        weList.add(cur);
                    }
                }
            }

            if (agList.size() >= 1) {
                ag = agList.get(0).getKUNNR();
            } else {
                ag = r3code;
            }
            if (reList.size() >= 1) {
                re = agList.get(0).getKUNNR();
            } else {
                re = r3code;
            }
            if (rgList.size() >= 1) {
                rg = agList.get(0).getKUNNR();
            } else {
                rg = r3code;
            }
            if (weList.size() >= 1) {
                we = agList.get(0).getKUNNR();
            } else {
                we = r3code;
            }

        }

        PeProdUser userAccout = new PeProdUser();
        userAccout.setIs_del(0);
        userAccout.setUser_type(2);// 取客户账号
        userAccout.setCust_id(custId);
        userAccout.getMap().put("cust_id_not_null", "true");
        List<PeProdUser> userList = new ArrayList<PeProdUser>();
        userList = super.getFacade().getPeProdUserService().getPeProdUserList(userAccout);
        // 客户没有在渠道系统维护账号
        if (userList != null && userList.size() > 0) {
            userAccout = userList.get(0);
        }

        KonkaOrderInfo ko = new KonkaOrderInfo();
        ko.setAdd_date(nowDate);

        // 0未审核 1审核中 3已完结 4已作废
        ko.setAudit_state(0);

        ko.setCust_id(custId);

        ko.setFreight(null);
        ko.setGood_discount_price(orderinfo.getGoodDiscountPrice());


        // ko.setInvoice_status(invoice_status);
        // ko.setInvoice_type(invoice_type);
        // ko.setInvoices_payable(invoices_payable);

        // 是否变更
        ko.setIs_change(0);
        ko.setIs_del(0);
        // 是否送达
        ko.setIs_delivered(0);
        // 是否结束
        ko.setIs_end(0);
        // 是否需要高层审核
        ko.setIs_need_to_manager(0);
        // 是否收货
        ko.setIs_sh(0);
        // 0 1
        ko.setIs_submit(1);

        // ko.setKh_confirm_date(null);

        // 订单数量汇总
        ko.setOrder_num(orderinfo.getOrderNum());
        ko.setOrder_date(orderinfo.getOrderDate());
        ko.setOrder_state(0);

        /**
         * 此字段实际含义不是字面含义订单类型,现被误用作订单来源<br>
         * 0在线下单<br>
         * 1图片下单<br>
         * 2触网转单<br>
         * 4从返利转<br>
         * 5DRP转入<br>
         */
        ko.setOrder_type(5);
        ko.setMoney(orderinfo.getMoney());
        ko.setPay_date(nowDate);
        // 账期
        ko.setPay_type(56);
        ko.setPrintCount(0);

        // 要求必须为每个客户指定业务员
        // ko.setProcess_id(processId);
        // ko.setProcess_state(processState);
        // ko.setNext_audit_role_id(nextAuditRoleId);
        // ko.setNext_node_id(nextNodeId);

        // sap返回的订单ID
        // ko.setR3_id(r3_id);

        // ko.setReceiving_id(receiving_id);
        // ko.setReceiving_date(receiving_date);
        //
        // ko.setReturn_reason(return_reason);
        // ko.setReturn_type(return_type);
        // ko.setReturn_type_remark(return_type_remark);

        // ko.setSend_type(sendType);
        // ko.setShipping_date(shipping_date);
        // ko.setShipping_id(shipping_id);


        ko.setRemark(orderinfo.getUserRemark());
        ko.setCg_order_date(nowDate);
        ko.setThird_cg_order_num("");

        if ("ZFRE".equals(orderinfo.getDocType().toUpperCase())) {
            ko.setTrade_index(super.generateReturnTradeIndex());
        } else {
            ko.setTrade_index(super.generateTradeIndex());
        }

        // 收货信息
        ko.setUser_addr(orderinfo.getUserAddr());
        ko.setUser_name(orderinfo.getUserName());
        ko.setUser_p_index(null);
        ko.setUser_phone(orderinfo.getUserPhone());
        ko.setUser_remark(orderinfo.getUserRemark());


        // 取当前客户被分配到哪个分公司哪个业务身上
        BranchAssign ba = new BranchAssign();
        ba.setKonka_r3_id(custId);
        ba = super.getFacade().getBranchAssignService().getBranchAssign(ba);

        // 需要手工维护(一个客户可能有多个账号,取其中一个就可以)
        // 如果这个账号没有维护部门信息,取客户所绑定的业务员所在部门具体在客户分配那里维护
        if (userAccout.getDept_id() == null || "".equals(userAccout.getDept_id())) {
            ko.setAdd_dept_id(ba.getFgs_id());
        } else {
            ko.setAdd_dept_name(userAccout.getDepartment());
        }

        if (userAccout.getDepartment() == null || "".equals(userAccout.getDepartment())) {
            KonkaDept kd = new KonkaDept();
            kd.setDept_id(ba.getFgs_id());
            kd.setIs_del(0);
            kd = super.getFacade().getKonkaDeptService().getFgsByDeptId(kd);
            if (kd != null) {
                ko.setAdd_dept_name(kd.getDept_name());
            }
        } else {
            ko.setAdd_dept_name(userAccout.getDepartment());
        }

        ko.setAdd_user_id(userAccout.getId());
        ko.setAdd_user_name(userAccout.getUser_name());
        ko.setAttachmentList(null);


        // r3客户名称
        ko.setUser_shop_name(ks.getCustomer_name());

        ko.setUser_tel(orderinfo.getUserTel());
        ko.setUser_zip(orderinfo.getUserZip());

        ko.setSales_org(salesOrg);
        ko.setAg(ag);
        ko.setWe(we);
        ko.setRg(rg);
        ko.setRe(re);
        ko.setDivision("10");
        ko.setDoc_type(orderinfo.getDocType());


        ko.getMap().put("save_type", "insert");
        ko.getMap().put("content1", "");// 留言与附件
        ko.getMap().put("tel1", "");

        List<KonkaOrderInfoDetails> orderDetailsList = new ArrayList<KonkaOrderInfoDetails>();

        for (PurchaseOrderDetail p : orderDetail) {

            PePdModel pd = new PePdModel();
            pd.setMd_name(p.getPdName());
            pd = super.getFacade().getPePdModelService().getPePdModel(pd);

            //
            KonkaOrderInfoDetails dd = new KonkaOrderInfoDetails();
            dd.setBrand_id(114L);
            dd.setBrand_name("康佳");
            dd.setGood_count(p.getGoodCount());
            dd.setGood_discount(p.getGoodDiscount());
            dd.setGood_discount_price(p.getGoodDiscountPrice());
            dd.setGood_price(p.getGoodPrice());
            dd.setGood_state(null);
            dd.setGood_sum_price(p.getGoodSumPrice());
            dd.setGood_unit("台");
            dd.setPd_id(pd.getPd_id());
            dd.setPd_name(p.getPdName());
            dd.setPd_remark(p.getPdRemark());
            dd.setPd_type_id(pd.getCls_id());
            // dd.setPd_type_name(pd_type_name);
            orderDetailsList.add(dd);
        }
        ko.setKonkaOrderInfoDetailsList(orderDetailsList);

        FromDrpOrder fo = buildDrpOrder(orderinfo, orderDetail, ko.getTrade_index());

        // konkaorderinfo ko ,fromdrpOrderinfo fo;两者信息同时传入,使之处于同一事务中
        super.getFacade().getKonkaOrderInfoService().createDrpOrderInfo(ko, fo);

        return ko.getTrade_index();

    }

    private DrpExcuteMsg OrderInfoValidate(final String r3Code, final String docType, final String dsOrderId) {
        DrpExcuteMsg drm = new DrpExcuteMsg();
        // 1.
        KonkaR3Shop ks = new KonkaR3Shop();
        ks.setR3_code(r3Code);
        ks = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(ks);
        if (ks == null) {
            // 客户在渠道系统不存在,不允许在渠道系统执行订单业务
            drm.setSuccessflag(false);
            drm.getReturnMsg().add("*客户" + r3Code + "在渠道系统不存在,不允许在渠道系统执行订单业务");
        } else {
            if (ks.getIs_del() == 1) {
                // 客户已经被停用,不允许在渠道系统执行订单业务
                drm.setSuccessflag(false);
                drm.getReturnMsg().add("*客户" + r3Code + "已经被停用,不允许在渠道系统执行订单业务");
            }
        }

        // 2.客户是否有账号
        if (ks != null) {
            PeProdUser userAccout = new PeProdUser();
            userAccout.setIs_del(0);
            userAccout.setCust_id(ks.getId());
            List<PeProdUser> userList = new ArrayList<PeProdUser>();
            userList = super.getFacade().getPeProdUserService().getPeProdUserList(userAccout);

            if (userList == null || userList.size() == 0) {
                // 客户在渠道系统没有维护账号
                drm.setSuccessflag(false);
                drm.getReturnMsg().add("*客户" + r3Code + "在渠道系统没有维护账号,不允许在渠道系统执行订单业务");
            }

        }

        // 3.类型
        final String[] doctypes = new String[] {"ZFOR", "ZFRE", "ZFCR", "ZFGC"};
        Arrays.sort(doctypes);
        int index = Arrays.binarySearch(doctypes, docType);
        if (index == -1) {
            // doctype 错误类型
            drm.setSuccessflag(false);
            drm.getReturnMsg().add("*订单类型只支持\"ZFOR\", \"ZFRE\", \"ZFCR\", \"ZFGC\"");
        }

        // 4.订单是否重复传到渠道系统
        FromDrpOrder fo = new FromDrpOrder();
        fo.setDs_order_id(dsOrderId);
        long ordersize = super.getFacade().getFromDrpOrderService().getFromDrpOrderCount(fo);
        if (ordersize > 0) {
            // 重复
            drm.setSuccessflag(false);
            drm.getReturnMsg().add("*DRP订单ID:" + dsOrderId + "在渠道系统已存在");
        }
        return drm;
    }


    // PurchaseOrder and PurchaseOrderDetail 为json数据结构
    // fromdrporder todrporder 为渠道系统保存记录的数据表结构
    private FromDrpOrder buildDrpOrder(PurchaseOrder order, PurchaseOrderDetail[] detail, String index) {

        final Date date = new Date();

        FromDrpOrder fo = new FromDrpOrder();
        fo.setDiscount_price(order.getGoodDiscountPrice());
        fo.setDoc_type(order.getDocType());
        fo.setDs_order_id(order.getDsOrderId());
        fo.setFrom_date(date);
        fo.setOrder_date(order.getOrderDate());
        fo.setOrder_money(order.getMoney());
        fo.setOrder_num(order.getOrderNum());
        fo.setQd_tran_index(index);// important

        fo.setR3_code(order.getR3Code());
        fo.setRemark(order.getUserRemark());
        fo.setUser_addr(order.getUserAddr());
        fo.setUser_name(order.getUserName());
        fo.setUser_phone(order.getUserPhone());
        fo.setUser_tel(order.getUserTel());
        fo.setUser_zip(order.getUserZip());


        List<FromDrpOrderDetail> dList = new ArrayList<FromDrpOrderDetail>();

        for (PurchaseOrderDetail d : detail) {
            FromDrpOrderDetail fdo = new FromDrpOrderDetail();
            //
            fdo.setDs_order_id(d.getDsOrderId());
            fdo.setDs_order_fk_id(d.getDsOrderFKid());

            //
            fdo.setGood_count((long) d.getGoodCount());
            fdo.setGood_discount_price(d.getGoodDiscountPrice());
            fdo.setGood_discount(d.getGoodDiscount());
            fdo.setGood_price(d.getGoodPrice());
            fdo.setGood_sum_price(d.getGoodSumPrice());
            fdo.setPd_name(d.getPdName());
            fdo.setPd_remark(d.getPdRemark());
            dList.add(fdo);
        }
        fo.setDlist(dList);
        return fo;
    }

}
