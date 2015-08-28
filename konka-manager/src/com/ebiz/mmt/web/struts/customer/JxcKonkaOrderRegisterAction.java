package com.ebiz.mmt.web.struts.customer;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ebiz.mmt.domain.Attachment;
import com.ebiz.mmt.domain.BranchAssign;
import com.ebiz.mmt.domain.GiftInfo;
import com.ebiz.mmt.domain.JBaseGoods;
import com.ebiz.mmt.domain.JBasePartner;
import com.ebiz.mmt.domain.JBaseStore;
import com.ebiz.mmt.domain.JBaseStoreR3;
import com.ebiz.mmt.domain.JBaseType;
import com.ebiz.mmt.domain.JBill;
import com.ebiz.mmt.domain.JBillDetails;
import com.ebiz.mmt.domain.JSubSellRec;
import com.ebiz.mmt.domain.KonkaBcompPd;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaJxcBaseAddr;
import com.ebiz.mmt.domain.KonkaMobileImpStore;
import com.ebiz.mmt.domain.KonkaMobileSailData;
import com.ebiz.mmt.domain.KonkaMobileTerminalFbBack;
import com.ebiz.mmt.domain.KonkaMobileTerminalFeedback;
import com.ebiz.mmt.domain.KonkaOrderAuditProcess;
import com.ebiz.mmt.domain.KonkaOrderAuditProcessNode;
import com.ebiz.mmt.domain.KonkaOrderInfo;
import com.ebiz.mmt.domain.KonkaOrderInfoAudit;
import com.ebiz.mmt.domain.KonkaOrderInfoDetails;
import com.ebiz.mmt.domain.KonkaOrderInfoUpdateRecord;
import com.ebiz.mmt.domain.KonkaPePdModelLowestPrice;
import com.ebiz.mmt.domain.KonkaR3MmtMatch;
import com.ebiz.mmt.domain.KonkaR3OrderLines;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.KonkaR3Store;
import com.ebiz.mmt.domain.KonkaXxSellBill;
import com.ebiz.mmt.domain.PePdModel;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleInfo;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.domain.PshowOrdeDetails;
import com.ebiz.mmt.domain.PshowOrder;
import com.ebiz.mmt.r3.KNVP;
import com.ebiz.mmt.r3.ReturnInfo;
import com.ebiz.mmt.r3.StockCheckResult;
import com.ebiz.mmt.r3.ZSOF;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.MmtFilePathConfig;
import com.ebiz.ssi.web.struts.bean.Pager;
import com.ebiz.ssi.web.struts.bean.UploadFile;
import com.taobao.api.sync.DateUtils;

/**
 * 客户下单
 * 
 * 
 */
@SuppressWarnings("unused")
public class JxcKonkaOrderRegisterAction extends BaseClientJxcAction {

    @Override
    public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return this.list(mapping, form, request, response);
    }

    public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        setNaviStringToRequestScope(form, request);
        DynaBean dynaBean = (DynaBean) form;
        super.encodeCharacterForGetMethod(dynaBean, request);
        Pager pager = (Pager) dynaBean.get("pager");
        String cust_id = (String) dynaBean.get("cust_id");
        String order_type = (String) dynaBean.get("order_type");
        String is_th = (String) dynaBean.get("is_th");// 为1 的时候是退货
        String is_all = (String) dynaBean.get("is_all");// 查看所有的订单
        String FROMSALESMAN = (String) dynaBean.get("FROMSALESMAN"); // 状态位，标识是否是业务员提交
        String CUSTID = (String) dynaBean.get("CUSTID"); // 业务提交请求的客户ID
        String we_like = (String) dynaBean.get("we_like"); // 送达方
        if (StringUtils.isNotBlank(FROMSALESMAN)) {
            request.setAttribute("FROMSALESMAN", FROMSALESMAN);
        }
        if (StringUtils.isNotBlank(CUSTID)) {
            request.setAttribute("CUSTID", CUSTID);
        }
        
        // 如果是业务员查看明细则添加客户名称在导航菜单中
        if ("1".equals(FROMSALESMAN)) {
            KonkaR3Shop krs = new KonkaR3Shop();
            krs.setId(Long.valueOf(CUSTID));
            krs = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(krs);
            if (null != krs) {
                request.setAttribute("naviString", request.getAttribute("naviString") + " &gt; 【" + krs.getCustomer_name() + "】");
            }
        }
        PeProdUser user = null;
        HttpSession session = request.getSession();
        // 业务员处理客户订单信息
        if (GenericValidator.isLong(CUSTID)) {
            user = new PeProdUser();
            user.getRow().setCount(1);
            user.setCust_id(Long.valueOf(CUSTID));
            List<PeProdUser> userList = super.getFacade().getPeProdUserService().getPeProdUserList(user);
            if (null != userList && userList.size() > 0) {
                user = userList.get(0);
                session.setAttribute(Constants.CUSTOMER_USER_INFO, user);
                request.setAttribute("SESSION_U_ID", ((PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO)).getId());
            }
        } else {
            user = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);
            request.setAttribute("SESSION_U_ID", user.getId());
        }


        if (null == user) {
            return null;
        }

        Calendar cal = Calendar.getInstance();
        Calendar f = (Calendar) cal.clone();
        f.clear();
        f.set(Calendar.YEAR, cal.get(Calendar.YEAR));
        f.set(Calendar.MONTH, cal.get(Calendar.MONTH));
        String firstday = new SimpleDateFormat("yyyy-MM-dd").format(f.getTime());
        String today = DateFormatUtils.format(new Date(), "yyyy-MM-dd");

        String add_date_gt = (String) dynaBean.get("add_date_gt");
        String add_date_lt = (String) dynaBean.get("add_date_lt");
        String trade_index_like = (String) dynaBean.get("trade_index_like");
        String pay_type = (String) dynaBean.get("pay_type");
        String send_type = (String) dynaBean.get("send_type");
        String audit_state_x = (String) dynaBean.get("audit_state_x");
        String pd_name_like = (String) dynaBean.get("pd_name_like");
        String is_delivered = (String) dynaBean.get("is_delivered");
        String doc_type = (String) dynaBean.get("doc_type");

        if (StringUtils.isBlank(add_date_gt)) {
            add_date_gt = firstday;
        }
        if (StringUtils.isBlank(add_date_lt)) {
            add_date_lt = today;
        }

        dynaBean.set("add_date_gt", add_date_gt);
        dynaBean.set("add_date_lt", add_date_lt);

        KonkaOrderInfo konkaOrderInfo = new KonkaOrderInfo();

        /**
         * 此字段实际含义不是字面含义订单类型,现被误用作订单来源<br>
         * 0在线下单<br>
         * 1图片下单<br>
         * 2触网转单<br>
         * 4从返利转<br>
         * 5DRP转入<br>
         */
        if (StringUtils.isNotBlank(order_type)) {
            konkaOrderInfo.setOrder_type(Integer.valueOf(order_type));
        }
        super.copyProperties(konkaOrderInfo, form);
        konkaOrderInfo.setIs_del(0);
        if (StringUtils.isNotBlank(trade_index_like)) {
            konkaOrderInfo.getMap().put("trade_index_like", trade_index_like);
        }
        if (StringUtils.isNotBlank(cust_id)) {
            konkaOrderInfo.setCust_id(Long.valueOf(cust_id)); // 业务员，直销员登录查看
        } else {
            konkaOrderInfo.setCust_id(user.getCust_id()); //
        }
        if (StringUtils.isNotBlank(pay_type)) {
            konkaOrderInfo.setPay_type(Integer.valueOf(pay_type));
        }
        if (StringUtils.isNotBlank(send_type)) {
            konkaOrderInfo.setSend_type(Integer.valueOf(send_type));
        }
        if (StringUtils.isNotBlank(audit_state_x)) {
            int audit_state = Integer.valueOf(audit_state_x);
            switch (audit_state) {
                case 10:// 未提交
                    konkaOrderInfo.setIs_submit(0);
                    konkaOrderInfo.setAudit_state(null);
                    break;
                case 20:// 审核中
                    konkaOrderInfo.setIs_submit(1);
                    konkaOrderInfo.getMap().put("audit_state_lt", 3);
                    break;
                case 30:// 已审核
                    konkaOrderInfo.setIs_submit(1);
                    konkaOrderInfo.getMap().put("audit_state_eq", 3);
                    break;
            }
        }
        if (StringUtils.isNotBlank(pd_name_like)) {
            konkaOrderInfo.getMap().put("pd_name_like", pd_name_like);
        }
        if (StringUtils.isNotBlank(is_delivered)) {
            konkaOrderInfo.setIs_delivered(Integer.valueOf(is_delivered));
        }
        if (StringUtils.isNotBlank(is_all)||StringUtils.isEmpty(is_th)) {
            // 查看所有的订单，什么限制条件都不加
            konkaOrderInfo.getMap().put("is_all", "is_all");
        } else {
            if (is_th != null && is_th.equals("1")) {
                konkaOrderInfo.setDoc_type("ZFRE");
            } else {
                if (StringUtils.isNotBlank(doc_type)) {
                    konkaOrderInfo.setDoc_type(doc_type);
                }
            }
        }
        if (StringUtils.isNotBlank(we_like)) {
        	konkaOrderInfo.getMap().put("we_like", we_like);
        }
        konkaOrderInfo.getMap().put("start_date", add_date_gt + " 00:00:00");
        konkaOrderInfo.getMap().put("end_date", add_date_lt + " 23:59:59");
        Long recordCount = super.getFacade().getKonkaOrderInfoService().getKonkaOrderInfoSearchforPdNameCount(konkaOrderInfo);
        pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
        konkaOrderInfo.getRow().setFirst(pager.getFirstRow());
        konkaOrderInfo.getRow().setCount(pager.getRowCount());

        List<KonkaOrderInfo> konkaOrderInfoList = super.getFacade().getKonkaOrderInfoService().getKonkaOrderInfoResultForPaginatedList(konkaOrderInfo);

        // 确定每个订单当前审核角色
        // 1、有审核记录（记录中最高等级的后一步审核角色，要判断是否在最后一步【根据audit_state】）
        // 2、无审核记录（该网点分配的用户角色）
        // ...................

        // 从R3得到物流发货信息--ldy
        for (KonkaOrderInfo konkaOrderInfo2 : konkaOrderInfoList) {
            Long r3_order_id = konkaOrderInfo2.getR3_id();// 得到订单号
            if (r3_order_id != null) {
                ZSOF zsof = new ZSOF();
                zsof = super.getFacade().getR3WebInterfaceService().getR3Delivery(r3_order_id);
                if (zsof != null && zsof.getVBEDL() != null) {
                    if (!zsof.getLFDAT().equals("0000-00-00")) {// 如果有发货时间,发货状态改为已发货
                        konkaOrderInfo2.getMap().put("lfdat", zsof.getLFDAT());// 发货时间
                        if (konkaOrderInfo2.getIs_delivered() == 0) {
                            konkaOrderInfo2.setIs_delivered(1);
                            super.getFacade().getKonkaOrderInfoService().modifyKonkaOrderInfoForconfirm(konkaOrderInfo2);
                        }
                        if (!zsof.getSHDAT().equals("0000-00-00")) {
                            konkaOrderInfo2.getMap().put("shdat", zsof.getSHDAT());// 收货时间
                        }
                    }
                }
            }
        }

        request.setAttribute("konkaOrderInfoList", konkaOrderInfoList);
        if (is_th != null && is_th.equals("1")) {
            return new ActionForward("/../customer/JxcKonkaOrderRegister/listTH.jsp");
        }
        return mapping.findForward("list");
    }

    public ActionForward listpdselect(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        setNaviStringToRequestScope(form, request);
        DynaBean dynaBean = (DynaBean) form;
        super.encodeCharacterForGetMethod(dynaBean, request);
        Pager pager = (Pager) dynaBean.get("pager");
        String md_name_like = (String) dynaBean.get("md_name_like");
        String pd_ids = (String) dynaBean.get("pd_ids");

        PePdModel entity = new PePdModel();
        entity.setIs_del(0);
        entity.getMap().put("md_name_like", md_name_like);
        entity.getMap().put("not_selects", StringUtils.substring(pd_ids, 1, -1));
        Long recordCount = super.getFacade().getPePdModelService().getPePdModelCount(entity);
        pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
        entity.getRow().setFirst(pager.getFirstRow());
        entity.getRow().setCount(pager.getRowCount());

        List<PePdModel> entityList = super.getFacade().getPePdModelService().getPePdModelPaginatedList(entity);
        request.setAttribute("entityList", entityList);

        // 确定每个订单当前审核角色
        // 1、有审核记录（记录中最高等级的后一步审核角色，要判断是否在最后一步【根据audit_state】）
        // 2、无审核记录（该网点分配的用户角色）
        // ...................

        if (StringUtils.isNotBlank(pd_ids) && StringUtils.isNotBlank(StringUtils.substring(pd_ids, 1, -1))) {
            PePdModel entity2 = new PePdModel();
            entity2.setIs_del(0);
            entity2.getMap().put("selects", StringUtils.substring(pd_ids, 1, -1));
            List<PePdModel> entity2List = super.getFacade().getPePdModelService().getPePdModelList(entity2);
            request.setAttribute("entity2List", entity2List);
        }

        return new ActionForward("/../customer/JxcKonkaOrderRegister/list_pd_select.jsp");
    }

    public ActionForward listTHpdselect(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        setNaviStringToRequestScope(form, request);
        DynaBean dynaBean = (DynaBean) form;
        super.encodeCharacterForGetMethod(dynaBean, request);
        Pager pager = (Pager) dynaBean.get("pager");
        String md_name_like = (String) dynaBean.get("md_name_like");
        String pd_ids = (String) dynaBean.get("pd_ids");

        PePdModel entity = new PePdModel();
        entity.setIs_del(0);
        entity.getMap().put("md_name_like", md_name_like);
        entity.getMap().put("not_selects", StringUtils.substring(pd_ids, 1, -1));
        Long recordCount = super.getFacade().getPePdModelService().getPePdModelCount(entity);
        pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
        entity.getRow().setFirst(pager.getFirstRow());
        entity.getRow().setCount(pager.getRowCount());

        List<PePdModel> entityList = super.getFacade().getPePdModelService().getPePdModelPaginatedList(entity);
        request.setAttribute("entityList", entityList);

        // 确定每个订单当前审核角色
        // 1、有审核记录（记录中最高等级的后一步审核角色，要判断是否在最后一步【根据audit_state】）
        // 2、无审核记录（该网点分配的用户角色）
        // ...................

        if (StringUtils.isNotBlank(pd_ids) && StringUtils.isNotBlank(StringUtils.substring(pd_ids, 1, -1))) {
            PePdModel entity2 = new PePdModel();
            entity2.setIs_del(0);
            entity2.getMap().put("selects", StringUtils.substring(pd_ids, 1, -1));
            List<PePdModel> entity2List = super.getFacade().getPePdModelService().getPePdModelList(entity2);
            request.setAttribute("entity2List", entity2List);
        }

        return new ActionForward("/../customer/JxcKonkaOrderRegister/list_TH_pd_select.jsp");
    }

    public ActionForward saveOrderOnMobile(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        DynaBean dynaBean = (DynaBean) form;
        String forward_type = (String) dynaBean.get("forward_type");
        String doc_type = (String) dynaBean.get("doc_type");

        this.saveOrder(mapping, form, request, response);

        StringBuffer pathBuffer = new StringBuffer();
        if ("2".equals(forward_type)) { // 保存并新建
            pathBuffer.append("/../customer/manager/JxcKonkaOrderRegister.do?method=listpdselect");
        } else {
            if ("ZFRE".equals(doc_type)) {
                pathBuffer.append("/../customer/manager/JxcKonkaOrderRegister.do?method=listInMobile&is_th=1");
            } else {
                pathBuffer.append("/../customer/manager/JxcKonkaOrderRegister.do?method=listInMobile");
            }
        }
        pathBuffer.append("&");
        ActionForward forward = new ActionForward(pathBuffer.toString(), true);
        // end
        return forward;
    }

    public ActionForward addInMobile(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        DynaBean dynaBean = (DynaBean) form;
        String __username = (String) dynaBean.get("__username");
        String __password = (String) dynaBean.get("__password");

        String pd_ids = request.getParameter("pd_ids");
        if (StringUtils.isNotBlank(pd_ids)) {
            pd_ids = StringUtils.substring(pd_ids, 1, -1); // 去头尾的逗号
        }

        // if (!StringUtils.equalsIgnoreCase(request.getMethod(), "POST")) {
        // super.renderHtml(response, "Error: It is not post request.",
        // "Error.");
        // return null;
        // }

        // if (StringUtils.isBlank(__username) ||
        // StringUtils.isBlank(__username)) {
        // super.renderHtml(response, "Error: Request parameter is empty.",
        // "Error.");
        // return null;
        // }
        //
        // logger.info("User_name:{}, Pass_word:{}", __username, __password);
        //
        // PeProdUser user = new PeProdUser();
        // user.setUser_name(__username.trim());
        // user.setIs_del(0);
        // user.setPass_word(new DESPlus().encrypt(__password));
        // user = getFacade().getPeProdUserService().getPeProdUser(user);
        //
        // if (null == user || user.getCust_id() == null) {
        // super.renderHtml(response,
        // "Error: username or password is not valid.", "Error.");
        // return null;
        // }
        //
        // request.getSession().setAttribute(Constants.CUSTOMER_USER_INFO,
        // user);

        if (null == pd_ids) {
            return new ActionForward("/../customer/manager/JxcKonkaOrderRegister.do?method=listpdselect", true);
        } else {
            PePdModel model = new PePdModel();
            model.getMap().put("selects", pd_ids);
            model.setIs_del(0);
            List<PePdModel> pePdModelList = super.getFacade().getPePdModelService().getPePdModelList(model);

            request.setAttribute("pePdModelList", pePdModelList);
        }

        this.add(mapping, form, request, response);

        return new ActionForward("/../customer/JxcKonkaOrderRegister/input_mobile.jsp");
    }

    public ActionForward viewInMobile(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        DynaBean dynaBean = (DynaBean) form;
        String __username = (String) dynaBean.get("__username");
        String __password = (String) dynaBean.get("__password");

        // if (!StringUtils.equalsIgnoreCase(request.getMethod(), "POST")) {
        // super.renderHtml(response, "Error: It is not post request.",
        // "Error.");
        // return null;
        // }

        // if (StringUtils.isBlank(__username) ||
        // StringUtils.isBlank(__username)) {
        // super.renderHtml(response, "Error: Request parameter is empty.",
        // "Error.");
        // return null;
        // }
        //
        // logger.info("User_name:{}, Pass_word:{}", __username, __password);
        //
        // PeProdUser user = new PeProdUser();
        // user.setUser_name(__username.trim());
        // user.setIs_del(0);
        // user.setPass_word(new DESPlus().encrypt(__password));
        // user = getFacade().getPeProdUserService().getPeProdUser(user);
        //
        // if (null == user || user.getCust_id() == null) {
        // super.renderHtml(response,
        // "Error: username or password is not valid.", "Error.");
        // return null;
        // }
        //
        // request.getSession().setAttribute(Constants.CUSTOMER_USER_INFO,
        // user);

        this.viewOrder(mapping, form, request, response);

        return new ActionForward("/../customer/JxcKonkaOrderRegister/view_mobile.jsp");
    }

    public ActionForward listInMobile(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        DynaBean dynaBean = (DynaBean) form;
        String __username = (String) dynaBean.get("__username");
        String __password = (String) dynaBean.get("__password");

        // if (!StringUtils.equalsIgnoreCase(request.getMethod(), "POST")) {
        // super.renderHtml(response, "Error: It is not post request.",
        // "Error.");
        // return null;
        // }

        // if (StringUtils.isBlank(__username) ||
        // StringUtils.isBlank(__username)) {
        // super.renderHtml(response, "Error: Request parameter is empty.",
        // "Error.");
        // return null;
        // }
        //
        // logger.info("User_name:{}, Pass_word:{}", __username, __password);
        //
        // PeProdUser user = new PeProdUser();
        // user.setUser_name(__username.trim());
        // user.setIs_del(0);
        // user.setPass_word(new DESPlus().encrypt(__password));
        // user = getFacade().getPeProdUserService().getPeProdUser(user);
        //
        // if (null == user || user.getCust_id() == null) {
        // super.renderHtml(response,
        // "Error: username or password is not valid.", "Error.");
        // return null;
        // }
        //
        // request.getSession().setAttribute(Constants.CUSTOMER_USER_INFO,
        // user);

        this.list(mapping, form, request, response);

        return new ActionForward("/../customer/JxcKonkaOrderRegister/list_mobile.jsp");
    }

    public ActionForward addTHInMobile(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        DynaBean dynaBean = (DynaBean) form;
        String __username = (String) dynaBean.get("__username");
        String __password = (String) dynaBean.get("__password");

        String pd_ids = request.getParameter("pd_ids");
        if (StringUtils.isNotBlank(pd_ids)) {
            pd_ids = StringUtils.substring(pd_ids, 1, -1); // 鍘诲ご灏剧殑閫楀彿
        }

        // if (!StringUtils.equalsIgnoreCase(request.getMethod(), "POST")) {
        // super.renderHtml(response, "Error: It is not post request.",
        // "Error.");
        // return null;
        // }

        // if (StringUtils.isBlank(__username) ||
        // StringUtils.isBlank(__username)) {
        // super.renderHtml(response, "Error: Request parameter is empty.",
        // "Error.");
        // return null;
        // }
        //
        // logger.info("User_name:{}, Pass_word:{}", __username, __password);
        //
        // PeProdUser user = new PeProdUser();
        // user.setUser_name(__username.trim());
        // user.setIs_del(0);
        // user.setPass_word(new DESPlus().encrypt(__password));
        // user = getFacade().getPeProdUserService().getPeProdUser(user);
        //
        // if (null == user || user.getCust_id() == null) {
        // super.renderHtml(response,
        // "Error: username or password is not valid.", "Error.");
        // return null;
        // }
        //
        // request.getSession().setAttribute(Constants.CUSTOMER_USER_INFO,
        // user);

        if (null == pd_ids) {
            return new ActionForward("/../customer/manager/JxcKonkaOrderRegister.do?method=listTHpdselect", true);
        } else {
            PePdModel model = new PePdModel();
            model.getMap().put("selects", pd_ids);
            model.setIs_del(0);
            List<PePdModel> pePdModelList = super.getFacade().getPePdModelService().getPePdModelList(model);

            request.setAttribute("pePdModelList", pePdModelList);
        }

        this.addTH(mapping, form, request, response);

        return new ActionForward("/../customer/JxcKonkaOrderRegister/inputTH_mobile.jsp");
    }

    /** 订单登记只进康佳品牌的型号 */
    public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        setNaviStringToRequestScope(form, request);
        DynaBean dynaBean = (DynaBean) form;
        String cust_id = (String) dynaBean.get("cust_id");

        /**
         * @ADD BY Jiang,JiaYong
         * @version 2013-08-24
         */
        String FROMSALESMAN = (String) dynaBean.get("FROMSALESMAN"); // 状态位，标识是否是业务员提交
        String CUSTID = (String) dynaBean.get("CUSTID"); // 业务提交请求的客户ID
        if (StringUtils.isNotBlank(FROMSALESMAN)) {
            request.setAttribute("FROMSALESMAN", FROMSALESMAN);
        }
        if (StringUtils.isNotBlank(CUSTID)) {
            request.setAttribute("CUSTID", CUSTID);
        }

        PeProdUser user = null;
        HttpSession session = request.getSession();
        if (GenericValidator.isLong(CUSTID)) {
            user = new PeProdUser();
            user.getRow().setCount(1);
            user.setCust_id(Long.valueOf(CUSTID));
            List<PeProdUser> userList = super.getFacade().getPeProdUserService().getPeProdUserList(user);
            if (null != userList && userList.size() > 0) {
                user = userList.get(0);
                session.setAttribute(Constants.CUSTOMER_USER_INFO, user);
            }
        } else {
            user = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);
        }
        if (null == user) {
            return null;
        }

        // 取收货地址
        KonkaJxcBaseAddr konkaJxcBaseAddr = new KonkaJxcBaseAddr();
        konkaJxcBaseAddr.setIs_del(0);

        KonkaR3Shop r3shop = new KonkaR3Shop();
        r3shop.setId(user.getCust_id());
        r3shop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(r3shop);
        if (null != r3shop) {
            konkaJxcBaseAddr.setR3_id(r3shop.getId());
            konkaJxcBaseAddr.setR3_code(r3shop.getR3_code());
        }

        List<KonkaJxcBaseAddr> konkaJxcBaseAddrList = super.getFacade().getKonkaJxcBaseAddrService().getKonkaJxcBaseAddrList(konkaJxcBaseAddr);
        request.setAttribute("konkaJxcBaseAddrList", konkaJxcBaseAddrList);

        if (null != konkaJxcBaseAddrList && konkaJxcBaseAddrList.size() > 0) {
            for (KonkaJxcBaseAddr kba : konkaJxcBaseAddrList) {
                if (kba.getIs_default() == 0) {
                    dynaBean.set("send_type", 2);
                    dynaBean.set("user_name", kba.getUser_name());
                    dynaBean.set("user_tel", kba.getUser_tel());
                    dynaBean.set("user_phone", kba.getUser_phone());
                    if (StringUtils.isNotBlank(kba.getUser_p_index().toString())) {
                        String p_index = kba.getUser_p_index().toString();
                        if (!p_index.endsWith("00")) {
                            if (p_index.length() == 6) {
                                dynaBean.set("province", StringUtils.substring(p_index, 0, 2) + "0000");
                                dynaBean.set("city", StringUtils.substring(p_index, 0, 4) + "00");
                                dynaBean.set("country", p_index);
                            } else if (p_index.length() == 8) {
                                dynaBean.set("province", StringUtils.substring(p_index, 0, 2) + "0000");
                                dynaBean.set("city", StringUtils.substring(p_index, 0, 4) + "00");
                                dynaBean.set("country", StringUtils.substring(p_index, 0, 6));
                                dynaBean.set("town", p_index);
                            }
                        } else if (p_index.endsWith("0000")) {
                            dynaBean.set("province", p_index);
                        } else if (p_index.endsWith("00")) {
                            dynaBean.set("province", StringUtils.substring(p_index, 0, 2) + "0000");
                            dynaBean.set("city", p_index);
                        }
                    }
                    dynaBean.set("user_addr", kba.getUser_addr());
                    dynaBean.set("user_remark", kba.getUser_remark());
                }
            }

        }
        Long current_cust_id = null;

        if (null != user.getCust_id()) {
            // 当前登录人是客户
            current_cust_id = user.getCust_id();
        } else {
            // 当前登录人不是客户
            PeRoleUser _peRoleUser = new PeRoleUser();
            _peRoleUser.setUser_id(user.getId());
            List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

            boolean role_id_eq_60 = false;
            for (PeRoleUser peRoleUser : peRoleUserList) {
                if (peRoleUser.getRole_id() == 60L) {
                    role_id_eq_60 = true;
                }
            }
            if (user.getCust_id() == null && role_id_eq_60) {
                if (!GenericValidator.isLong(cust_id)) {
                    return null;
                }
                current_cust_id = Long.valueOf(cust_id);
            }
        }

        if (null == current_cust_id) {
            return null;
        }

        request.setAttribute("cust_id", current_cust_id);

        KonkaR3Shop konkaR3Shop = new KonkaR3Shop();
        konkaR3Shop.setId(current_cust_id);
        konkaR3Shop = getFacade().getKonkaR3ShopService().getKonkaR3Shop(konkaR3Shop);
        if (null == konkaR3Shop) {
            super.renderJavaScript(response, "alert('" + super.getMessage(request, "konka.r3.customer.none") + "');history.back();");
            return null;
        }

        // //System.out.println("current_cust_id============" + current_cust_id);

        // 获取流程列表
        KonkaDept kd = super.getKonkaDeptByCustomerId(current_cust_id);
        KonkaDept dept = null;
        if (kd != null) {
            dept = super.getSuperiorForDeptType(kd.getDept_id(), 3);
        }
        List<KonkaOrderAuditProcess> processList = new ArrayList<KonkaOrderAuditProcess>();
        // Boolean flag = null;
        if (dept != null) {

            KonkaR3Shop konkaR3Shop_ = new KonkaR3Shop();
            konkaR3Shop_.setId(current_cust_id);
            konkaR3Shop_.setIs_del(0l);
            konkaR3Shop_ = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(konkaR3Shop_);
            if (null != konkaR3Shop_) {
                if (StringUtils.isNotBlank(konkaR3Shop_.getCustomer_type())) {// 判断是否是有客户类型
                    KonkaOrderAuditProcess process = new KonkaOrderAuditProcess();
                    process.getMap().put("par_add_dept_id", dept.getDept_id());
                    process.setCustomer_type(konkaR3Shop_.getCustomer_type());
                    process.setIs_del(0);
                    process.setIs_stop(0);
                    process.setUsed_field(0);
                    processList = super.getFacade().getKonkaOrderAuditProcessService().getKonkaOrderAuditProcessList(process);
                    if (null == processList || processList.size() == 0) {
                        // 没有分公司自定义的流程取统一流程，即分公司自定义流程
                        KonkaOrderAuditProcess ap_public = new KonkaOrderAuditProcess();
                        ap_public.setCustomer_type(konkaR3Shop_.getCustomer_type());
                        ap_public.setAdd_dept_id(0L);
                        ap_public.setIs_del(0);
                        ap_public.setIs_stop(0);
                        ap_public.setUsed_field(0);
                        List<KonkaOrderAuditProcess> ap_publicauditProcesseList = getFacade().getKonkaOrderAuditProcessService().getKonkaOrderAuditProcessList(ap_public);
                        processList.addAll(ap_publicauditProcesseList);
                    }
                    if (null == processList || processList.size() == 0) {
                        // 没有分公司自定义的流程取统一流程，即分公司流程
                        KonkaOrderAuditProcess ap_public = new KonkaOrderAuditProcess();
                        ap_public.setCustomer_type(konkaR3Shop_.getCustomer_type());
                        ap_public.setAdd_dept_id(0L);
                        ap_public.setIs_del(0);
                        ap_public.setIs_stop(0);
                        ap_public.setUsed_field(0);
                        List<KonkaOrderAuditProcess> ap_publicauditProcesseList = getFacade().getKonkaOrderAuditProcessService().getKonkaOrderAuditProcessList(ap_public);
                        processList.addAll(ap_publicauditProcesseList);
                    }
                    if (null == processList || processList.size() == 0) {
                        // 没有分公司流程取统一流程，即总公司流程
                        KonkaOrderAuditProcess ap_public = new KonkaOrderAuditProcess();
                        ap_public.getMap().put("par_add_dept_id", dept.getDept_id());
                        ap_public.setIs_del(0);
                        ap_public.setIs_stop(0);
                        ap_public.setUsed_field(0);
                        ap_public.getMap().put("customer_type_is_null", true);
                        List<KonkaOrderAuditProcess> ap_publicauditProcesseList = getFacade().getKonkaOrderAuditProcessService().getKonkaOrderAuditProcessList(ap_public);
                        processList.addAll(ap_publicauditProcesseList);
                    }

                    if (null == processList || processList.size() == 0) {
                        // 没有分公司自定义的流程取统一流程，即总公司自定义流程
                        KonkaOrderAuditProcess ap_public = new KonkaOrderAuditProcess();
                        ap_public.setAdd_dept_id(0L);
                        ap_public.setIs_del(0);
                        ap_public.setIs_stop(0);
                        ap_public.setUsed_field(0);
                        ap_public.getMap().put("customer_type_is_null", true);
                        List<KonkaOrderAuditProcess> ap_publicauditProcesseList = getFacade().getKonkaOrderAuditProcessService().getKonkaOrderAuditProcessList(ap_public);
                        processList.addAll(ap_publicauditProcesseList);
                    }

                    // flag = true;
                    request.setAttribute("customer_type", konkaR3Shop_.getCustomer_type());
                } else {
                    KonkaOrderAuditProcess process = new KonkaOrderAuditProcess();
                    process.getMap().put("par_add_dept_id", dept.getDept_id());
                    process.getMap().put("customer_type_is_null", true);
                    process.setIs_del(0);
                    process.setIs_stop(0);
                    process.setUsed_field(0);
                    processList = super.getFacade().getKonkaOrderAuditProcessService().getKonkaOrderAuditProcessList(process);
                    if (null == processList || processList.size() == 0) {
                        // 没有分公司统一流程，即分公司优先级高
                        KonkaOrderAuditProcess ap_public = new KonkaOrderAuditProcess();
                        ap_public.getMap().put("customer_type_is_null", true);
                        ap_public.setAdd_dept_id(0L);
                        ap_public.setIs_del(0);
                        ap_public.setIs_stop(0);
                        ap_public.setUsed_field(0);
                        List<KonkaOrderAuditProcess> ap_publicauditProcesseList = getFacade().getKonkaOrderAuditProcessService().getKonkaOrderAuditProcessList(ap_public);
                        processList.addAll(ap_publicauditProcesseList);
                    }
                    // flag = false;
                }
            }
            request.setAttribute("processList", processList);
            // request.setAttribute("flag", flag);
        }

        // 取订单流程
        // KonkaDept dept = super.getKonkaDeptByCustomerId(current_cust_id);
        // KonkaOrderAuditProcess ap = new KonkaOrderAuditProcess();
        // ap.getMap().put("par_add_dept_id", dept.getDept_id());
        // ap.setIs_del(0);
        // List<KonkaOrderAuditProcess> auditProcesseList =
        // getFacade().getKonkaOrderAuditProcessService()
        // .getKonkaOrderAuditProcessList(ap);
        //
        // if (null == auditProcesseList || auditProcesseList.size() == 0) {
        // // 没有分公司自定义的流程取统一流程，即分公司优先级高
        // KonkaOrderAuditProcess ap_public = new KonkaOrderAuditProcess();
        // ap_public.setAdd_dept_id(0L);
        // ap_public.setIs_del(0);
        // List<KonkaOrderAuditProcess> ap_publicauditProcesseList =
        // getFacade().getKonkaOrderAuditProcessService()
        // .getKonkaOrderAuditProcessList(ap_public);
        // auditProcesseList.addAll(ap_publicauditProcesseList);
        // }
        //
        // request.setAttribute("processList", auditProcesseList);

        // 查询客户用户信息
        // PeProdUser u = new PeProdUser();
        // u.setCust_id(current_cust_id);
        // u = super.getFacade().getPeProdUserService().getPeProdUser(u);
        //
        // if (null != u) {
        // request.setAttribute("u", u);
        // if (null != u.getP_index()) {
        // super.setprovinceAndcityAndcountryToFrom(dynaBean, u.getP_index());
        // }
        // }

        dynaBean.set("brandId", Constants.KONKA_BRAND_ID);
        // dynaBean.set("trade_index", super.generateTradeIndex());// 交易流水号
        dynaBean.set("today", DateFormatUtils.format(new Date(), "yyyy-MM-dd hh:mm:ss"));// 订单日期
        dynaBean.set("cg_order_date", new Date());// 订单日期
        dynaBean.set("user_shop_name", konkaR3Shop.getCustomer_name());
        dynaBean.set("r3_code", konkaR3Shop.getR3_code());

        dynaBean.set("userName", user.getUser_name());
        /**
         * 从订单登记功能过来的制单人 from_manager_user_id
         */
        String from_manager_user_id = (String) dynaBean.get("from_manager_user_id");
        if (StringUtils.isNotBlank(from_manager_user_id) && GenericValidator.isLong(from_manager_user_id)) {
            PeProdUser zdruser = new PeProdUser();
            zdruser.setId(Long.valueOf(from_manager_user_id));
            zdruser.setIs_del(0);
            zdruser = super.getFacade().getPeProdUserService().getPeProdUser(zdruser);
            if (null != zdruser.getReal_name()) {
                dynaBean.set("userName", zdruser.getUser_name());
            }
        }

        // dynaBean.set("userAddr", user.getLink_addr());

        // dynaBean.set("userZip", entp.getPostcode());
        // dynaBean.set("userAddr", entp.getAddr());
        // dynaBean.set("userTel", entp.getTel());
        // dynaBean.set("userPIndex", user.getStdEntpMain().getP_index());

        String fgsSN = konkaR3Shop.getBranch_area_name_2();
        String fgsSN2 = konkaR3Shop.getR3_sales_org_code();
        request.setAttribute("sales_org", StringUtils.isBlank(fgsSN2) ? fgsSN : fgsSN2);

        /** 取网点业务员 */
        BranchAssign bagn = new BranchAssign();
        bagn.setKonka_r3_id(current_cust_id);
        bagn = getFacade().getBranchAssignService().getBranchAssign(bagn);
        if (null != bagn && null != bagn.getUser_id()) {
            PeProdUser ywy = new PeProdUser();
            ywy.setId(bagn.getUser_id());
            ywy = getFacade().getPeProdUserService().getPeProdUserResult(ywy);
            if (null != ywy) {
                request.setAttribute("ywy_user_name", ywy.getReal_name());
            }
        }
        if (super.isCallR3Interface(request)) {
            String sales_org = StringUtils.isBlank(fgsSN2) ? fgsSN : fgsSN2;
            ReturnInfo<KNVP> info = new ReturnInfo<KNVP>();
            info = super.getFacade().getR3WebInterfaceService().getKnvpsList(sales_org, Constants.default_vtweg, Constants.default_spart, konkaR3Shop.getR3_code());
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

            request.setAttribute("agList", agList);
            request.setAttribute("reList", reList);
            request.setAttribute("rgList", rgList);
            request.setAttribute("weList", weList);

            if (agList.size() == 0) {
                request.setAttribute("ag", konkaR3Shop.getR3_code());
            }
            if (reList.size() == 0) {
                request.setAttribute("re", konkaR3Shop.getR3_code());
            }
            if (rgList.size() == 0) {
                request.setAttribute("rg", konkaR3Shop.getR3_code());
            }
            if (weList.size() == 0) {
                KonkaMobileImpStore s = new KonkaMobileImpStore();
                s.setR3_shdf_sn(konkaR3Shop.getR3_code());
                List<KonkaMobileImpStore> sList = super.getFacade().getKonkaMobileImpStoreService().getKonkaMobileImpStoreListForDistinctSdf(s);

                List<KNVP> __weList = new ArrayList<KNVP>();
                for (KonkaMobileImpStore cur : sList) {
                    KNVP k = new KNVP();
                    k.setKUNN2(cur.getR3_sdf_sn());
                    __weList.add(k);
                }
                request.setAttribute("weList", __weList);
                if (__weList.size() == 0) {
                    request.setAttribute("we", konkaR3Shop.getR3_code());
                }
            }
        } else {
            request.setAttribute("ag", konkaR3Shop.getR3_code());
            request.setAttribute("re", konkaR3Shop.getR3_code());
            request.setAttribute("rg", konkaR3Shop.getR3_code());

            KonkaMobileImpStore s = new KonkaMobileImpStore();
            s.setR3_shdf_sn(konkaR3Shop.getR3_code());
            List<KonkaMobileImpStore> sList = super.getFacade().getKonkaMobileImpStoreService().getKonkaMobileImpStoreListForDistinctSdf(s);

            List<KNVP> weList = new ArrayList<KNVP>();
            for (KonkaMobileImpStore cur : sList) {
                KNVP k = new KNVP();
                k.setKUNN2(cur.getR3_sdf_sn());
                weList.add(k);
            }
            request.setAttribute("weList", weList);
        }

        request.setAttribute("call_r3_interface", super.isCallR3Interface(request));

        // 从在线商城传过来的产品
        String pd_id = (String) dynaBean.get("pd_id");
        String num = (String) dynaBean.get("num");
        if (GenericValidator.isLong(pd_id)) {
            PePdModel ppm = new PePdModel();
            ppm.setPd_id(Long.valueOf(pd_id));
            ppm = super.getFacade().getPePdModelService().getPePdModel(ppm);
            if (null != ppm) {
                List<KonkaOrderInfoDetails> konkaOrderInfoDetailsList = new ArrayList<KonkaOrderInfoDetails>();
                KonkaOrderInfoDetails t = new KonkaOrderInfoDetails();
                t.setPd_id(ppm.getPd_id());
                t.setPd_name(ppm.getMd_name());
                t.setGood_count(Integer.valueOf(num));
                konkaOrderInfoDetailsList.add(t);
                request.setAttribute("konkaOrderInfoDetailsList", konkaOrderInfoDetailsList);
            }
        }

        return new ActionForward("/../customer/JxcKonkaOrderRegister/input.jsp");
    }

    /** 退货订单 */
    public ActionForward addTH(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        setNaviStringToRequestScope(form, request);
        DynaBean dynaBean = (DynaBean) form;
        String cust_id = (String) dynaBean.get("cust_id");

        /**
         * @ADD BY Xiao,GuoJian
         * @version 2014-03-21
         */
        String FROMSALESMAN = (String) dynaBean.get("FROMSALESMAN"); // 状态位，标识是否是业务员提交
        String CUSTID = (String) dynaBean.get("CUSTID"); // 业务提交请求的客户ID

        PeProdUser user = null;
        HttpSession session = request.getSession();
        if (GenericValidator.isLong(CUSTID)) {
            user = new PeProdUser();
            user.getRow().setCount(1);
            user.setCust_id(Long.valueOf(CUSTID));
            List<PeProdUser> userList = super.getFacade().getPeProdUserService().getPeProdUserList(user);
            if (null == userList || 0 == userList.size()) {} else {
                user = userList.get(0);
                session.setAttribute(Constants.CUSTOMER_USER_INFO, user);
            }
        } else {
            user = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);
        }
        if (null == user) {
            return null;
        }

        Long current_cust_id = null;

        if (null != user.getCust_id()) {
            // 当前登录人是客户
            current_cust_id = user.getCust_id();
        } else {
            // 当前登录人不是客户
            PeRoleUser _peRoleUser = new PeRoleUser();
            _peRoleUser.setUser_id(user.getId());
            List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

            boolean role_id_eq_60 = false;
            for (PeRoleUser peRoleUser : peRoleUserList) {
                if (peRoleUser.getRole_id() == 60L) {
                    role_id_eq_60 = true;
                }
            }
            if (user.getCust_id() == null && role_id_eq_60) {
                if (!GenericValidator.isLong(cust_id)) {
                    return null;
                }
                current_cust_id = Long.valueOf(cust_id);
            }
        }

        if (null == current_cust_id) {
            return null;
        }

        request.setAttribute("cust_id", current_cust_id);

        KonkaR3Shop konkaR3Shop = new KonkaR3Shop();
        konkaR3Shop.setId(current_cust_id);
        konkaR3Shop = getFacade().getKonkaR3ShopService().getKonkaR3Shop(konkaR3Shop);
        if (null == konkaR3Shop) {
            super.renderJavaScript(response, "alert('" + super.getMessage(request, "konka.r3.customer.none") + "');history.back();");
            return null;
        }

        // 取订单流程
        // 退货的
        // 获取流程列表
        KonkaDept kd = super.getKonkaDeptByCustomerId(current_cust_id);
        KonkaDept dept = null;
        if (kd != null) {
            dept = super.getSuperiorForDeptType(kd.getDept_id(), 3);
        }
        List<KonkaOrderAuditProcess> processList = new ArrayList<KonkaOrderAuditProcess>();
        // Boolean flag = null;

        if (dept != null) {
            KonkaR3Shop konkaR3Shop_ = new KonkaR3Shop();
            konkaR3Shop_.setId(current_cust_id);
            konkaR3Shop_.setIs_del(0l);
            konkaR3Shop_ = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(konkaR3Shop_);
            if (null != konkaR3Shop_) {
                if (StringUtils.isNotBlank(konkaR3Shop_.getCustomer_type())) {// 判断是否是有客户类型
                    KonkaOrderAuditProcess process = new KonkaOrderAuditProcess();
                    process.getMap().put("par_add_dept_id", dept.getDept_id());
                    process.setCustomer_type(konkaR3Shop_.getCustomer_type());
                    process.setIs_del(0);
                    process.setIs_stop(0);
                    process.setUsed_field(2);
                    processList = super.getFacade().getKonkaOrderAuditProcessService().getKonkaOrderAuditProcessList(process);
                    if (null == processList || processList.size() == 0) {
                        // 没有分公司自定义的流程取统一流程，即分公司自定义流程
                        KonkaOrderAuditProcess ap_public = new KonkaOrderAuditProcess();
                        ap_public.setCustomer_type(konkaR3Shop_.getCustomer_type());
                        ap_public.setAdd_dept_id(0L);
                        ap_public.setIs_del(0);
                        ap_public.setIs_stop(0);
                        ap_public.setUsed_field(2);
                        List<KonkaOrderAuditProcess> ap_publicauditProcesseList = getFacade().getKonkaOrderAuditProcessService().getKonkaOrderAuditProcessList(ap_public);
                        processList.addAll(ap_publicauditProcesseList);
                    }
                    if (null == processList || processList.size() == 0) {
                        // 没有分公司自定义的流程取统一流程，即分公司流程
                        KonkaOrderAuditProcess ap_public = new KonkaOrderAuditProcess();
                        ap_public.setCustomer_type(konkaR3Shop_.getCustomer_type());
                        ap_public.setAdd_dept_id(0L);
                        ap_public.setIs_del(0);
                        ap_public.setIs_stop(0);
                        ap_public.setUsed_field(2);
                        List<KonkaOrderAuditProcess> ap_publicauditProcesseList = getFacade().getKonkaOrderAuditProcessService().getKonkaOrderAuditProcessList(ap_public);
                        processList.addAll(ap_publicauditProcesseList);
                    }
                    if (null == processList || processList.size() == 0) {
                        // 没有分公司流程取统一流程，即总公司流程
                        KonkaOrderAuditProcess ap_public = new KonkaOrderAuditProcess();
                        ap_public.getMap().put("par_add_dept_id", dept.getDept_id());
                        ap_public.setIs_del(0);
                        ap_public.setIs_stop(0);
                        ap_public.setUsed_field(2);

                        List<KonkaOrderAuditProcess> ap_publicauditProcesseList = getFacade().getKonkaOrderAuditProcessService().getKonkaOrderAuditProcessList(ap_public);
                        processList.addAll(ap_publicauditProcesseList);
                    }

                    if (null == processList || processList.size() == 0) {
                        // 没有分公司自定义的流程取统一流程，即总公司自定义流程
                        KonkaOrderAuditProcess ap_public = new KonkaOrderAuditProcess();
                        ap_public.setAdd_dept_id(0L);
                        ap_public.setIs_del(0);
                        ap_public.setIs_stop(0);
                        ap_public.setUsed_field(2);

                        List<KonkaOrderAuditProcess> ap_publicauditProcesseList = getFacade().getKonkaOrderAuditProcessService().getKonkaOrderAuditProcessList(ap_public);
                        processList.addAll(ap_publicauditProcesseList);
                    }

                } else {
                    KonkaOrderAuditProcess process = new KonkaOrderAuditProcess();
                    process.getMap().put("par_add_dept_id", dept.getDept_id());

                    process.setIs_del(0);
                    process.setIs_stop(0);
                    process.setUsed_field(2);
                    processList = super.getFacade().getKonkaOrderAuditProcessService().getKonkaOrderAuditProcessList(process);
                    if (null == processList || processList.size() == 0) {
                        // 没有分公司统一流程，即分公司优先级高
                        KonkaOrderAuditProcess ap_public = new KonkaOrderAuditProcess();

                        ap_public.setAdd_dept_id(0L);
                        ap_public.setIs_del(0);
                        ap_public.setIs_stop(0);
                        ap_public.setUsed_field(2);
                        List<KonkaOrderAuditProcess> ap_publicauditProcesseList = getFacade().getKonkaOrderAuditProcessService().getKonkaOrderAuditProcessList(ap_public);
                        processList.addAll(ap_publicauditProcesseList);
                    }
                }
            }
            request.setAttribute("processList", processList);
        }


        /*
         * KonkaDept dept = super.getKonkaDeptByCustomerId(current_cust_id); KonkaOrderAuditProcess
         * ap = new KonkaOrderAuditProcess(); ap.setAdd_dept_id(dept.getDept_id()); ap.setIs_del(0);
         * List<KonkaOrderAuditProcess> auditProcesseList = getFacade()
         * .getKonkaOrderAuditProcessService() .getKonkaOrderAuditProcessList(ap);
         * 
         * if (null == auditProcesseList || auditProcesseList.size() == 0) { //
         * 没有分公司自定义的流程取统一流程，即分公司优先级高 KonkaOrderAuditProcess ap_public = new
         * KonkaOrderAuditProcess(); ap_public.setAdd_dept_id(0L); ap_public.setIs_del(0);
         * List<KonkaOrderAuditProcess> ap_publicauditProcesseList = getFacade()
         * .getKonkaOrderAuditProcessService() .getKonkaOrderAuditProcessList(ap_public);
         * auditProcesseList.addAll(ap_publicauditProcesseList); }
         * 
         * request.setAttribute("processList", auditProcesseList);
         */
        // 查询客户用户信息
        // PeProdUser u = new PeProdUser();
        // u.setCust_id(current_cust_id);
        // u = super.getFacade().getPeProdUserService().getPeProdUser(u);
        //
        // if (null != u) {
        // request.setAttribute("u", u);
        // if (null != u.getP_index()) {
        // super.setprovinceAndcityAndcountryToFrom(dynaBean, u.getP_index());
        // }
        // }

        dynaBean.set("brandId", Constants.KONKA_BRAND_ID);
        // 交易流水号
        dynaBean.set("today", DateFormatUtils.format(new Date(), "yyyy-MM-dd hh:mm:ss"));// 订单日期
        dynaBean.set("cg_order_date", new Date());// 订单日期
        dynaBean.set("user_shop_name", konkaR3Shop.getCustomer_name());
        dynaBean.set("r3_code", konkaR3Shop.getR3_code());

        dynaBean.set("userName", user.getReal_name());

        /**
         * 从订单登记功能过来的制单人 from_manager_user_id
         */
        String from_manager_user_id = (String) dynaBean.get("from_manager_user_id");
        if (StringUtils.isNotBlank(from_manager_user_id) && GenericValidator.isLong(from_manager_user_id)) {
            PeProdUser zdruser = new PeProdUser();
            zdruser.setId(Long.valueOf(from_manager_user_id));
            zdruser.setIs_del(0);
            zdruser = super.getFacade().getPeProdUserService().getPeProdUser(zdruser);
            if (null != zdruser.getReal_name()) {
                dynaBean.set("userName", zdruser.getReal_name());
            }
        }

        String fgsSN = konkaR3Shop.getBranch_area_name_2();
        String fgsSN2 = konkaR3Shop.getR3_sales_org_code();
        request.setAttribute("sales_org", StringUtils.isBlank(fgsSN2) ? fgsSN : fgsSN2);

        /** 取网点业务员 */
        BranchAssign bagn = new BranchAssign();
        bagn.setKonka_r3_id(current_cust_id);
        bagn = getFacade().getBranchAssignService().getBranchAssign(bagn);
        if (null != bagn && null != bagn.getUser_id()) {
            PeProdUser ywy = new PeProdUser();
            ywy.setId(bagn.getUser_id());
            ywy = getFacade().getPeProdUserService().getPeProdUserResult(ywy);
            request.setAttribute("ywy_user_name", ywy.getReal_name());
        }

        if (true) {
            String sales_org = StringUtils.isBlank(fgsSN2) ? fgsSN : fgsSN2;
            ReturnInfo<KNVP> info = new ReturnInfo<KNVP>();
            info = super.getFacade().getR3WebInterfaceService().getKnvpsList(sales_org, Constants.default_vtweg, Constants.default_spart, konkaR3Shop.getR3_code());
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

            request.setAttribute("agList", agList);
            request.setAttribute("reList", reList);
            request.setAttribute("rgList", rgList);
            request.setAttribute("weList", weList);

            if (agList.size() == 0) {
                request.setAttribute("ag", konkaR3Shop.getR3_code());
            }
            if (reList.size() == 0) {
                request.setAttribute("re", konkaR3Shop.getR3_code());
            }
            if (rgList.size() == 0) {
                request.setAttribute("rg", konkaR3Shop.getR3_code());
            }
            if (weList.size() == 0) {
                KonkaMobileImpStore s = new KonkaMobileImpStore();
                s.setR3_shdf_sn(konkaR3Shop.getR3_code());
                List<KonkaMobileImpStore> sList = super.getFacade().getKonkaMobileImpStoreService().getKonkaMobileImpStoreListForDistinctSdf(s);

                List<KNVP> __weList = new ArrayList<KNVP>();
                for (KonkaMobileImpStore cur : sList) {
                    KNVP k = new KNVP();
                    k.setKUNN2(cur.getR3_sdf_sn());
                    __weList.add(k);
                }
                request.setAttribute("weList", __weList);
                if (__weList.size() == 0) {
                    request.setAttribute("we", konkaR3Shop.getR3_code());
                }
            }
        } else {
            request.setAttribute("ag", konkaR3Shop.getR3_code());
            request.setAttribute("re", konkaR3Shop.getR3_code());
            request.setAttribute("rg", konkaR3Shop.getR3_code());

            KonkaMobileImpStore s = new KonkaMobileImpStore();
            s.setR3_shdf_sn(konkaR3Shop.getR3_code());
            List<KonkaMobileImpStore> sList = super.getFacade().getKonkaMobileImpStoreService().getKonkaMobileImpStoreListForDistinctSdf(s);

            List<KNVP> weList = new ArrayList<KNVP>();
            for (KonkaMobileImpStore cur : sList) {
                KNVP k = new KNVP();
                k.setKUNN2(cur.getR3_sdf_sn());
                weList.add(k);
            }
            request.setAttribute("weList", weList);
        }

        request.setAttribute("call_r3_interface", super.isCallR3Interface(request));

        // 从在线商城传过来的产品
        String pd_id = (String) dynaBean.get("pd_id");
        String num = (String) dynaBean.get("num");
        if (GenericValidator.isLong(pd_id)) {
            PePdModel ppm = new PePdModel();
            ppm.setPd_id(Long.valueOf(pd_id));
            ppm = super.getFacade().getPePdModelService().getPePdModel(ppm);
            if (null != ppm) {
                List<KonkaOrderInfoDetails> konkaOrderInfoDetailsList = new ArrayList<KonkaOrderInfoDetails>();
                KonkaOrderInfoDetails t = new KonkaOrderInfoDetails();
                t.setPd_id(ppm.getPd_id());
                t.setPd_name(ppm.getMd_name());
                t.setGood_count(Integer.valueOf(num));
                konkaOrderInfoDetailsList.add(t);
                request.setAttribute("konkaOrderInfoDetailsList", konkaOrderInfoDetailsList);
            }
        }

        return new ActionForward("/../customer/JxcKonkaOrderRegister/inputTH.jsp");
    }

    /** 保存订单 */
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        DynaBean dynaBean = (DynaBean) form;
        String mod_id = (String) dynaBean.get("mod_id");
        String cust_id = (String) dynaBean.get("cust_id");
        String order_id = (String) dynaBean.get("order_id");
        String zbukrs = (String) dynaBean.get("sales_org");
        String doc_type = (String) dynaBean.get("doc_type");
        String not_redirect_to_list = (String) dynaBean.get("not_redirect_to_list");
        HttpSession session = request.getSession();
        PeProdUser user = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);
        if (null == user || StringUtils.isBlank(cust_id)) {
            super.renderJavaScript(response, "alert('登录超时请重新登录。');history.back();");
            return null;
        }
        String[] pd_ids = request.getParameterValues("pd_id");// 型号
        String[] md_names = request.getParameterValues("md_name");// 产品编码
        String[] good_counts = request.getParameterValues("good_count");// 数量
        String[] good_units = request.getParameterValues("good_unit");// 单位
        String[] good_prices = request.getParameterValues("good_price");// 单价
        String[] good_discounts = request.getParameterValues("good_discount");// 折让率
        String[] pd_remark = request.getParameterValues("pd_remark");// 产品备注
        KonkaOrderInfo konkaOrderInfo = new KonkaOrderInfo();
        super.copyProperties(konkaOrderInfo, form);

        if (null != order_id) {
            konkaOrderInfo.setId(new Long(order_id));
        }
        // 检查库存 1 通过,0 不通过
        // super.isCallR3Interface(request)
        if (true) {
            String[] md_names2 = new String[pd_ids.length - 1];
            String[] good_counts2 = new String[pd_ids.length - 1];
            // 开始. 取过来的值是{,xxx,yyy}
            for (int i = 1; i < md_names.length; i++) {
                md_names2[i - 1] = md_names[i];
            }
            for (int i = 1; i < good_counts.length; i++) {
                good_counts2[i - 1] = good_counts[i];
            }
            int isPass = 0;
            List<StockCheckResult> checkResult = this.checkStock(zbukrs, md_names2, good_counts2);
            for (StockCheckResult sr : checkResult) {
                isPass = isPass * sr.getIsOk();
            }
            if (isPass != 1) {
                // 库存检查没有通过,显示检查结果
                request.setAttribute("checkResult", checkResult);
                return null;
            }
        }

        /** add 20120305===== */
        konkaOrderInfo.setIs_del(0);// 未删除
        konkaOrderInfo.setIs_submit(1);// 0:暂存，1：已提交
        konkaOrderInfo.setIs_end(0);// 未完结
        /** add 20120305===== */

        konkaOrderInfo.setCust_id(Long.valueOf(cust_id));
        // konkaOrderInfo.setShop_id(shop_id); //客户端：康佳客户不保存
        konkaOrderInfo.setOrder_state(0);
        konkaOrderInfo.setAdd_user_id(user.getId());
        konkaOrderInfo.setAdd_user_name(user.getUser_name());
        konkaOrderInfo.setProcess_state(1);// 普通流程
        String YM = DateFormatUtils.format(konkaOrderInfo.getOrder_date(), "yyyyMMdd");
        int year = Integer.valueOf(YM.substring(0, 4));// 订单年份
        int month = Integer.valueOf(YM.substring(4, 6));// 订单月份

        KonkaDept fgsOfCurrentCustmer = super.getKonkaDeptByCustomerId(Long.valueOf(cust_id));
        if (null == fgsOfCurrentCustmer) {
            super.renderJavaScript(response, "alert('可能是您不是分公司管理员，或者您的客户信息不完整，没有权限操作。');history.back();");
            return null;
        }
        // 取该分公司定义的特殊流程
        KonkaOrderAuditProcess konkaOrderAuditProcess = super.getSpecialProcessByFgsDeptId(fgsOfCurrentCustmer.getDept_id());

        // 产品信息
        List<KonkaOrderInfoDetails> konkaOrderInfoDetailsList = new ArrayList<KonkaOrderInfoDetails>();
        if (ArrayUtils.isEmpty(pd_ids)) {
            super.renderJavaScript(response, "alert('未选择产品。');history.back();");
            return null;
        }

        Long orderNum = 0L;
        BigDecimal orderMoney = new BigDecimal("0.00");
        BigDecimal orderDiscountPrice = new BigDecimal("0.00");
        for (int i = 1; i < pd_ids.length; i++) {
            KonkaOrderInfoDetails konkaOrderInfoDetails = new KonkaOrderInfoDetails();
            konkaOrderInfoDetails.setPd_id(Long.valueOf(pd_ids[i]));
            // 取型号、大类信息
            PePdModel pePdModel = new PePdModel();
            pePdModel.setBrand_id(Constants.KONKA_BRAND_ID);
            pePdModel.setIs_del(0);
            pePdModel.setPd_id(Long.valueOf(pd_ids[i]));
            pePdModel = getFacade().getPePdModelService().getPePdModel(pePdModel);
            if (null != pePdModel) {
                konkaOrderInfoDetails.setPd_name(pePdModel.getMd_name());
                konkaOrderInfoDetails.setBrand_id(pePdModel.getBrand_id());
                konkaOrderInfoDetails.setBrand_name(Constants.KONKA_BRAND_NAME);
                konkaOrderInfoDetails.setPd_type_id(pePdModel.getCls_id());
                konkaOrderInfoDetails.setPd_type_name(super.getBasePdClazz(pePdModel.getCls_id()).getCls_name());
            }

            // 判断是否定义特殊流程，如果没定义则不改变订单状态为特殊流程状态
            // 该分公司定义了特殊流程，即产品低于限价需要走特殊流程
            // 此行为优化作用，若该订单已被设置需要走特殊流程（n[n>1]个产品需要走特殊流程，仅作1次查询）
            if (null != konkaOrderAuditProcess && konkaOrderInfo.getProcess_state() != 2) {
                // 获取分公司的产品限价
                KonkaPePdModelLowestPrice konkaPePdModelLowestPrice = new KonkaPePdModelLowestPrice();
                konkaPePdModelLowestPrice.setAdd_dept_id(fgsOfCurrentCustmer.getDept_id()); // 分公司
                konkaPePdModelLowestPrice.setPd_id(new Long(pd_ids[i])); // 产品
                konkaPePdModelLowestPrice.setSet_year(year); // 年
                konkaPePdModelLowestPrice.setSet_month(month); // 月
                konkaPePdModelLowestPrice.setIs_del(0);
                konkaPePdModelLowestPrice = getFacade().getKonkaPePdModelLowestPriceService().getKonkaPePdModelLowestPrice(konkaPePdModelLowestPrice);
                if (null != konkaPePdModelLowestPrice // 设置了产品限价
                        && new Long(good_prices[i]) < konkaPePdModelLowestPrice.getLowest_price().longValue()) {
                    konkaOrderInfo.setProcess_state(2);// 特殊流程（产品价格低于了部门产品的最低限价）
                }
            }

            konkaOrderInfoDetails.setGood_count(Integer.valueOf(good_counts[i]));
            orderNum += Long.valueOf(good_counts[i]);

            konkaOrderInfoDetails.setGood_price(new BigDecimal(good_prices[i]));
            konkaOrderInfoDetails.setGood_unit(good_units[i]);
            konkaOrderInfoDetails.setGood_sum_price(new BigDecimal(good_prices[i]).multiply(new BigDecimal(good_counts[i])));
            orderMoney = orderMoney.add(konkaOrderInfoDetails.getGood_sum_price());

            konkaOrderInfoDetails.setGood_discount(new BigDecimal(good_discounts[i]));

            // 单台折让金额
            konkaOrderInfoDetails.setGood_discount_price(konkaOrderInfoDetails.getGood_sum_price().multiply(new BigDecimal(good_discounts[i])).divide(new BigDecimal("100")));

            konkaOrderInfoDetails.setPd_remark(pd_remark[i]);

            // orderDiscountPrice =
            // orderDiscountPrice.add(konkaOrderInfoDetails.getGood_discount_price());
            // 折让总金额
            orderDiscountPrice = orderDiscountPrice.add(konkaOrderInfoDetails.getGood_discount_price().multiply(new BigDecimal(good_counts[i])));

            konkaOrderInfoDetailsList.add(konkaOrderInfoDetails);
        }

        konkaOrderInfo.setOrder_num(orderNum);// 订单总数
        // konkaOrderInfo.setMoney(new BigDecimal(money));//订单总金额
        // konkaOrderInfo.setGood_discount_price(new
        // BigDecimal(discount_price_sum));//订单折让总金额
        konkaOrderInfo.setMoney(orderMoney);// 订单总金额
        konkaOrderInfo.setGood_discount_price(orderDiscountPrice);// 订单折让总金额
        konkaOrderInfo.setKonkaOrderInfoDetailsList(konkaOrderInfoDetailsList);
        konkaOrderInfo.setIs_need_to_manager(0);// 财务意见
        konkaOrderInfo.setSale_count_01_add(new BigDecimal(0));// 业务意见
        // 判断匹配或者
        // 作为经销商被开拓到代理商下时，对应的R3用户有没有被分配到业务员：如果有，则业务员角色自动审核、如果没有则不处理
        PeProdUser ywyOfCustomer = super.getYwyOfCustomerByCustomerId(Long.valueOf(cust_id));
        if (null != ywyOfCustomer) {
            // 如果有业务员则作业务员自动审核操作
            konkaOrderInfo.setAudit_state(1);// 更新为审核中
            List<KonkaOrderInfoAudit> konkaOrderInfoAuditList = new ArrayList<KonkaOrderInfoAudit>();
            KonkaOrderInfoAudit konkaOrderInfoAudit = new KonkaOrderInfoAudit();// 业务员自动审核的信息
            konkaOrderInfoAudit.setAudit_type(0);
            konkaOrderInfoAudit.setAudit_level(1);// 业务员是第一级审核
            konkaOrderInfoAudit.setAudit_user_id(ywyOfCustomer.getId());
            konkaOrderInfoAudit.setAudit_user(ywyOfCustomer.getUser_name());
            konkaOrderInfoAudit.setAudit_comment("同意（业务员角色：系统自动审核通过）");
            konkaOrderInfoAudit.setAudit_result(1);
            konkaOrderInfoAudit.setAudit_dept_name(ywyOfCustomer.getDepartment());
            konkaOrderInfoAudit.setAudit_dept_id(ywyOfCustomer.getDept_id());
            konkaOrderInfoAuditList.add(konkaOrderInfoAudit);

            konkaOrderInfo.setKonkaOrderInfoAuditList(konkaOrderInfoAuditList);
        }

        if (null != order_id) {
            // 暂存后提交
            konkaOrderInfo.getMap().put("is_temp_save", "true");
            KonkaOrderInfoDetails konkaOrderInfoDetails = new KonkaOrderInfoDetails();
            konkaOrderInfoDetails.setOrder_id(new Long(order_id));
            List<KonkaOrderInfoDetails> konkaOrderInfoDetailsListForDel = super.getFacade().getKonkaOrderInfoDetailsService().getKonkaOrderInfoDetailsList(konkaOrderInfoDetails);
            konkaOrderInfo.setKonkaOrderInfoDetailsListForDel(konkaOrderInfoDetailsListForDel);// 需要删除的详细信息
        }

        // //System.out.println("add_date" + konkaOrderInfo.getAdd_date());
        // //System.out.println("order_date" + konkaOrderInfo.getOrder_date());

        HashMap<String, String> result = getFacade().getKonkaOrderInfoService().createKonkaOrderInfo(konkaOrderInfo);// 保存订单与短信接口返回值
        String inputline = result.get("sendResult");

        if (null != inputline) {
            // 返回值对应表
            if ("0".equals(inputline)) {// 发送成功success;
                saveMessage(request, "konka.jxc.inserted.sended.0");
            } else if ("-1".equals(inputline)) {// 参数为空params is null;
                saveMessage(request, "konka.jxc.inserted.sended.-1");
            } else if ("-2".equals(inputline)) {// 手机号码不合法mp is illegal;
                saveMessage(request, "konka.jxc.inserted.sended.-2");
            } else if ("-3".equals(inputline)) {// IOException;
                saveMessage(request, "konka.jxc.inserted.sended.-99999");// 帐号或者密码错误
            } else if ("-4".equals(inputline)) {// upload exception;
                saveMessage(request, "konka.jxc.inserted.sended.-99999");// 上传出现异常
            } else if ("-41".equals(inputline)) {// upload file is too
                // big(size :2M);
                saveMessage(request, "konka.jxc.inserted.sended.-99999");// 上传文件太大
            } else if ("-5".equals(inputline)) {// ParseException;
                saveMessage(request, "konka.jxc.inserted.sended.-99999");// 通过异常
            } else if ("-11".equals(inputline)) {// 保留remain;
                saveMessage(request, "konka.jxc.inserted.sended.-99999");// 定时发送时间不是有效的时间格式
            } else if ("-12".equals(inputline)) {// 校验不合法key illegal;
                saveMessage(request, "konka.jxc.inserted.sended.-12");
            } else if ("-13".equals(inputline)) {// ip illegal;
                saveMessage(request, "konka.jxc.inserted.sended.-13");
            } else if ("-14".equals(inputline)) {// unknown error;
                saveMessage(request, "konka.jxc.inserted.sended.-99999");// 未知错误
            } else if ("-101".equals(inputline)) {// 商户的参数为空mc param is
                // null;
                saveMessage(request, "konka.jxc.inserted.sended.-101");
            } else if ("-102".equals(inputline)) {// 商户不存在mc is not
                // exist;
                saveMessage(request, "konka.jxc.inserted.sended.-102");
            } else if ("-103".equals(inputline)) { // 商户密钥校验错误mc key is
                // error;
                saveMessage(request, "konka.jxc.inserted.sended.-103");
            } else if ("-104".equals(inputline)) {// 商户IP地址不是合同指定的mc ip
                // is
                // error;
                saveMessage(request, "konka.jxc.inserted.sended.-104");
            } else if ("-105,**".equals(inputline)) {// 短信内容超过最大长度，**为数字表示最大长度
                saveMessage(request, "konka.jxc.inserted.sended.-105");
            } else if ("-106,**".equals(inputline)) {// 群发短信超过最大限制，**为数字表示最大群发数
                saveMessage(request, "konka.jxc.inserted.sended.-106");
            }
        }
        if (null == inputline) {// 没有任何返回值
            saveMessage(request, "entity.inserted");
        }

        // 兼容手机端
        if (StringUtils.isNotBlank(not_redirect_to_list)) {
            super.renderHtml(response, "Success. No." + konkaOrderInfo.getTrade_index() + " is saved.");
            return null;
        }

        // the line below is added for pagination
        StringBuffer pathBuffer = new StringBuffer();
        pathBuffer.append(mapping.findForward("success").getPath());
        pathBuffer.append("&mod_id=" + mod_id);
        pathBuffer.append("&");
        // pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request,
        // "id", "pks", "method")));
        ActionForward forward = new ActionForward(pathBuffer.toString(), true);
        // end
        return forward;

    }

    public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        setNaviStringToRequestScope(form, request);
        DynaBean dynaBean = (DynaBean) form;
        HttpSession session = request.getSession();
        PeProdUser user = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);
        if (null == user || null == user.getCust_id()) {
            return null;
        }

        // 进货登记单
        String order_id = (String) dynaBean.get("order_id");
        KonkaOrderInfo konkaOrderInfo = new KonkaOrderInfo();
        konkaOrderInfo.setId(Long.valueOf(order_id));
        konkaOrderInfo = getFacade().getKonkaOrderInfoService().getKonkaOrderInfo(konkaOrderInfo);

        if (null == konkaOrderInfo) {
            super.renderJavaScript(response, "alert('指定定单不存在。');history.back();");
            return null;
        }

        // dynaBean.set("fullName",
        // super.getPIndexFullName(konkaOrderInfo.getUser_p_index()));
        dynaBean.set("fullName", super.getPIndexName(konkaOrderInfo.getUser_p_index(), ""));

        KonkaOrderInfoDetails konkaOrderInfoDetails = new KonkaOrderInfoDetails();
        konkaOrderInfoDetails.setOrder_id(Long.valueOf(order_id));
        List<KonkaOrderInfoDetails> konkaOrderInfoDetailsList = getFacade().getKonkaOrderInfoDetailsService().getKonkaOrderInfoDetailsList(konkaOrderInfoDetails);
        request.setAttribute("konkaOrderInfoDetailsList", konkaOrderInfoDetailsList);

        /* 订单审核信息 start */
        // 取订单当前所处的审核流程(随时可能变动)

        // 根据订单id，取所对应的分公司
        KonkaDept konkaDeptForFGS = super.getKonkaFGSByOrderId(konkaOrderInfo.getId());
        // 取订单当前所处的审核流程(随时可能变动)
        KonkaOrderAuditProcess konkaOrderAuditProcess = new KonkaOrderAuditProcess();
        konkaOrderAuditProcess.setAdd_dept_id(konkaDeptForFGS.getDept_id());//
        konkaOrderAuditProcess.setProcess_type(konkaOrderInfo.getProcess_state());
        konkaOrderAuditProcess.setIs_del(0);
        konkaOrderAuditProcess = getFacade().getKonkaOrderAuditProcessService().getKonkaOrderAuditProcess(konkaOrderAuditProcess);

        if (null == konkaOrderAuditProcess) {
            // 审核流程还没定义
            konkaOrderInfo.getMap().put("audit_role_name", "等待审核");
        }

        // 审核状态：0 未审核 ，1 审核中 ，2 审核未通过 ， 3 审核通过 （2、3两种状态为审核完成的最终状态
        // 即标示为2、3的订单已完成审核）
        if (konkaOrderInfo.getAudit_state() != 0) {
            // 有审核
            List<KonkaOrderInfoAudit> list = super.getKonkaOrderInfoAuditWithRoleInfoList(konkaOrderInfo.getId());// 订单审核信息列表

            if (konkaOrderInfo.getAudit_state() == 1) {
                // 审核中，显示当前审核角色的 下一步审核角色

                KonkaOrderAuditProcessNode entity = new KonkaOrderAuditProcessNode();
                entity.setAudit_proces_id(konkaOrderAuditProcess.getId());
                // 审核等级，最后一级
                Long process_max_level = super.getKonkaOrderAuditProcessNodeMaxLevel(konkaDeptForFGS.getDept_id(), new Long(konkaOrderInfo.getId()));// 流程最高级别
                if (process_max_level == list.get(0).getAudit_level().longValue()) {

                    entity.setAudit_level(list.get(0).getAudit_level());
                } else {
                    entity.setAudit_level(list.get(0).getAudit_level() + 1);
                }
                List<KonkaOrderAuditProcessNode> nextKonkaOrderAuditProcessNodeList = getFacade().getKonkaOrderAuditProcessNodeService().getKonkaOrderAuditProcessNodeList(entity);
                String[] nextRoleNames = new String[nextKonkaOrderAuditProcessNodeList.size()];
                for (int i = 0; i < nextKonkaOrderAuditProcessNodeList.size(); i++) {
                    nextRoleNames[i] = nextKonkaOrderAuditProcessNodeList.get(i).getRole_name();
                }
                konkaOrderInfo.getMap().put("audit_role_name", StringUtils.join(nextRoleNames, ","));

            } else if (konkaOrderInfo.getAudit_state() == 2 || konkaOrderInfo.getAudit_state() == 3) {
                // 审核通过或者未通过，显示最后审核角色

                konkaOrderInfo.getMap().put("audit_role_name", list.get(0).getMap().get("role_name"));
                logger.info("audit_role_name1============{}", list.get(0).getMap().get("role_name"));
            }
            request.setAttribute("konkaOrderAuditList", list);
        } else {
            // 未审核,显示当前审核角色 即该网点分配的用户角色

            Long r3_shop_id = null;
            if (null != konkaOrderInfo.getShop_id()) {
                // 根据买卖提商铺ID 获取SHOP_ID
                KonkaR3MmtMatch konkaR3MmtMatch = new KonkaR3MmtMatch();
                konkaR3MmtMatch.setMmt_shop_id(konkaOrderInfo.getShop_id());
                konkaR3MmtMatch = getFacade().getKonkaR3MmtMatchService().getKonkaR3MmtMatch(konkaR3MmtMatch);

                r3_shop_id = konkaR3MmtMatch.getR3_shop_id();
            } else if (null != konkaOrderInfo.getCust_id()) {
                r3_shop_id = konkaOrderInfo.getCust_id();
            }

            BranchAssign branchAssign = new BranchAssign();
            branchAssign.setBranch_type(1);
            branchAssign.setKonka_r3_id(r3_shop_id);
            branchAssign = getFacade().getBranchAssignService().getBranchAssign(branchAssign);
            if (null != branchAssign) {
                if (null != branchAssign.getUser_id()) {
                    // 已分配到具体的人员
                    PeRoleUser peRoleUser = new PeRoleUser();
                    // peRoleUser.setUser_id(branchAssign.getUser_id());
                    // peRoleUser =
                    // getFacade().getPeRoleUserService().getPeRoleUser(peRoleUser);
                    logger.info("user_id============={}", branchAssign.getUser_id());
                    peRoleUser = super.getRoleInfoByUserId(branchAssign.getUser_id());
                    if (null != peRoleUser) {// 查找角色名称
                        PeRoleInfo peRoleInfo = new PeRoleInfo();
                        peRoleInfo.setRole_id(peRoleUser.getRole_id());
                        peRoleInfo = getFacade().getPeRoleInfoService().getPeRoleInfo(peRoleInfo);
                        if (null != peRoleInfo) {
                            konkaOrderInfo.getMap().put("audit_role_name", peRoleInfo.getRole_name());
                        }
                    }

                } else {
                    // 未分配到具体的人员
                    if (null != branchAssign.getBsc_id() || null != branchAssign.getJyb_id() || null != branchAssign.getFgs_id()) {
                        // 办事处、经营部、分公司
                        konkaOrderInfo.getMap().put("audit_role_name", "暂无");
                    }
                }
            }
        }

        super.copyProperties(form, konkaOrderInfo);
        return mapping.findForward("view");
    }

    /** 暂存订单 */
    public ActionForward tempSave(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ParseException {
        DynaBean dynaBean = (DynaBean) form;
        String mod_id = (String) dynaBean.get("mod_id");
        String cust_id = (String) dynaBean.get("cust_id");
        String order_id = (String) dynaBean.get("order_id");

        HttpSession session = request.getSession();
        PeProdUser user = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);
        if (null == user) {
            return null;
        } else if (null == user.getCust_id()) {
            return null;
        }

        if (StringUtils.isBlank(cust_id)) {
            return null;
        }

        String[] pd_ids = request.getParameterValues("pd_id");// 型号
        // String[] pd_type_names =
        // request.getParameterValues("pd_type_name");//隐藏域大类
        String[] good_counts = request.getParameterValues("good_count");// 数量
        String[] good_units = request.getParameterValues("good_unit");// 单位
        String[] good_prices = request.getParameterValues("good_price");// 单价
        // String[] sum_prices = request.getParameterValues("sum_price");//单个金额
        String[] good_discounts = request.getParameterValues("good_discount");// 折让
        String[] pd_remark = request.getParameterValues("pd_remark");// 产品备注
        // String[] discount_prices =
        // request.getParameterValues("discount_price");// 单个折让金额
        // String order_num = (String) dynaBean.get("order_num");// 订单总数
        // String money = (String) dynaBean.get("money_sum");// 订单总金额
        // String discount_price_sum = (String)
        // dynaBean.get("discount_price_sum");// 订单折让总金额

        KonkaOrderInfo konkaOrderInfo = new KonkaOrderInfo();
        super.copyProperties(konkaOrderInfo, form);
        if (null != order_id) {
            konkaOrderInfo.setId(new Long(order_id));
        }

        /** add 20120305===== */
        konkaOrderInfo.setIs_del(0);// 未删除
        konkaOrderInfo.setIs_submit(0);// 0:暂存，1：已提交
        konkaOrderInfo.setIs_end(0);// 未完结
        /** add 20120305===== */

        konkaOrderInfo.setCust_id(Long.valueOf(cust_id));
        // konkaOrderInfo.setShop_id(shop_id); //客户端：康佳客户不保存
        konkaOrderInfo.setOrder_state(0);
        konkaOrderInfo.setAdd_user_id(user.getId());
        konkaOrderInfo.setAdd_user_name(user.getUser_name());
        konkaOrderInfo.setProcess_state(1);// 普通流程
        String YM = DateFormatUtils.format(konkaOrderInfo.getOrder_date(), "yyyyMMdd");
        int year = Integer.valueOf(YM.substring(0, 4));// 订单年份
        int month = Integer.valueOf(YM.substring(4, 6));// 订单月份

        List<KonkaOrderInfoDetails> konkaOrderInfoDetailsList = new ArrayList<KonkaOrderInfoDetails>();
        if (ArrayUtils.isNotEmpty(pd_ids)) {
            Long orderNum = 0L;
            BigDecimal orderMoney = new BigDecimal("0.00");
            BigDecimal orderDiscountPrice = new BigDecimal("0.00");
            for (int i = 1; i < pd_ids.length; i++) {
                KonkaOrderInfoDetails konkaOrderInfoDetails = new KonkaOrderInfoDetails();
                konkaOrderInfoDetails.setPd_id(Long.valueOf(pd_ids[i]));
                // 取型号、大类信息
                PePdModel pePdModel = new PePdModel();
                pePdModel.setBrand_id(Constants.KONKA_BRAND_ID);
                pePdModel.setIs_del(0);
                pePdModel.setPd_id(Long.valueOf(pd_ids[i]));
                pePdModel = getFacade().getPePdModelService().getPePdModel(pePdModel);
                if (null != pePdModel) {
                    konkaOrderInfoDetails.setPd_name(pePdModel.getMd_name());
                    konkaOrderInfoDetails.setBrand_id(pePdModel.getBrand_id());
                    konkaOrderInfoDetails.setBrand_name(Constants.KONKA_BRAND_NAME);
                    konkaOrderInfoDetails.setPd_type_id(pePdModel.getCls_id());
                    konkaOrderInfoDetails.setPd_type_name(super.getBasePdClazz(pePdModel.getCls_id()).getCls_name());
                }
                // 取R3网点分公司ID
                KonkaR3Shop krs = new KonkaR3Shop();
                krs.setId(Long.valueOf(cust_id));
                krs = getFacade().getKonkaR3ShopService().getKonkaR3ShopWithBranchAssign(krs);
                if (null != krs) {
                    BigDecimal deptId = new BigDecimal(krs.getMap().get("fgs_id").toString());
                    if (null != deptId && deptId.intValue() != -1) {// deptId=-1l,说明没有找到上级分公司（暂时没做限制添加订单的处理）

                        // 判断是否定义特殊流程，如果没定义则不改变订单状态为特殊流程状态
                        KonkaOrderAuditProcess konkaOrderAuditProcess = super.getSpecialProcessByFgsDeptId(deptId.longValue());
                        if (null != konkaOrderAuditProcess) {
                            // 取分公司的产品限价
                            KonkaPePdModelLowestPrice konkaPePdModelLowestPrice = new KonkaPePdModelLowestPrice();
                            konkaPePdModelLowestPrice.setAdd_dept_id(deptId.longValue());
                            konkaPePdModelLowestPrice.setPd_id(new Long(pd_ids[i]));
                            konkaPePdModelLowestPrice.setSet_year(year);
                            konkaPePdModelLowestPrice.setSet_month(month);
                            konkaPePdModelLowestPrice.setIs_del(0);
                            konkaPePdModelLowestPrice = getFacade().getKonkaPePdModelLowestPriceService().getKonkaPePdModelLowestPrice(konkaPePdModelLowestPrice);
                            if (null != konkaPePdModelLowestPrice) {
                                if (new BigDecimal(good_prices[i]).longValue() < konkaPePdModelLowestPrice.getLowest_price().longValue()) {
                                    konkaOrderInfo.setProcess_state(2);// 特殊流程（产品价格低于了部门产品的最低限价）
                                }
                            }
                        }
                    }
                }

                konkaOrderInfoDetails.setGood_count(Integer.valueOf(good_counts[i]));
                orderNum += Long.valueOf(good_counts[i]);

                konkaOrderInfoDetails.setGood_price(new BigDecimal(good_prices[i]));
                konkaOrderInfoDetails.setGood_unit(good_units[i]);
                // konkaOrderInfoDetails.setGood_sum_price(new
                // BigDecimal(sum_prices[i]));
                konkaOrderInfoDetails.setGood_sum_price(new BigDecimal(good_prices[i]).multiply(new BigDecimal(good_counts[i])));
                orderMoney = orderMoney.add(konkaOrderInfoDetails.getGood_sum_price());

                konkaOrderInfoDetails.setGood_discount(new BigDecimal(good_discounts[i]));
                // konkaOrderInfoDetails.setGood_discount_price(new
                // BigDecimal(discount_prices[i]));

                // 单台折让金额
                konkaOrderInfoDetails.setGood_discount_price(konkaOrderInfoDetails.getGood_sum_price().multiply(new BigDecimal(good_discounts[i])).divide(new BigDecimal("100")));

                konkaOrderInfoDetails.setPd_remark(pd_remark[i]);

                orderDiscountPrice = orderDiscountPrice.add(konkaOrderInfoDetails.getGood_discount_price());
                konkaOrderInfoDetailsList.add(konkaOrderInfoDetails);
            }

            konkaOrderInfo.setOrder_num(orderNum);// 订单总数
            konkaOrderInfo.setMoney(orderMoney);// 订单总金额
            konkaOrderInfo.setGood_discount_price(orderDiscountPrice);// 订单折让总金额
            konkaOrderInfo.setKonkaOrderInfoDetailsList(konkaOrderInfoDetailsList);

            if (null != order_id) {
                // 暂存后又暂存,修改订单主表，先删除后添加详细信息
                konkaOrderInfo.getMap().put("is_temp_save", "true");// 判断是暂存，还是订单审核时的修改

                KonkaOrderInfoDetails konkaOrderInfoDetails = new KonkaOrderInfoDetails();
                konkaOrderInfoDetails.setOrder_id(new Long(order_id));
                List<KonkaOrderInfoDetails> konkaOrderInfoDetailsListForDel =
                        super.getFacade().getKonkaOrderInfoDetailsService().getKonkaOrderInfoDetailsList(konkaOrderInfoDetails);
                konkaOrderInfo.setKonkaOrderInfoDetailsListForDel(konkaOrderInfoDetailsListForDel);// 需要删除的详细信息
            }
            super.getFacade().getKonkaOrderInfoService().createKonkaOrderInfo(konkaOrderInfo);// 保存订单，业务员不自动审核、不发送短信
            saveMessage(request, "entity.temp.inserted");
        }

        // the line below is added for pagination
        StringBuffer pathBuffer = new StringBuffer();
        pathBuffer.append(mapping.findForward("success").getPath());
        pathBuffer.append("&mod_id=" + mod_id);
        pathBuffer.append("&");
        // pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request,
        // "id", "pks", "method")));
        ActionForward forward = new ActionForward(pathBuffer.toString(), true);
        // end
        return forward;
    }

    /** 修改订单 */
    public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        setNaviStringToRequestScope(form, request);
        DynaBean dynaBean = (DynaBean) form;
        String cust_id = (String) dynaBean.get("cust_id");

        HttpSession session = request.getSession();
        PeProdUser user = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);
        if (null == user) {
            return null;
        } else if (null == user.getCust_id()) {
            return null;
        }

        if (StringUtils.isBlank(cust_id)) {
            return null;
        }

        KonkaR3Shop konkaR3Shop = new KonkaR3Shop();
        konkaR3Shop.setId(user.getCust_id());
        konkaR3Shop = getFacade().getKonkaR3ShopService().getKonkaR3Shop(konkaR3Shop);
        if (null == konkaR3Shop) {
            return null;
        }

        dynaBean.set("brandId", Constants.KONKA_BRAND_ID);
        Date today = new Date();
        dynaBean.set("today", DateFormatUtils.format(today, "yyyy-MM-dd"));// 订单日期

        // dynaBean.set("userPIndex", user.getStdEntpMain().getP_index());

        // super.setprovinceAndcityAndcountryToFrom(dynaBean,
        // user.getEntp_shop().getP_index().longValue());

        // 取R3网点分公司ID
        KonkaR3Shop krs = new KonkaR3Shop();
        krs.setId(Long.valueOf(cust_id));
        krs = getFacade().getKonkaR3ShopService().getKonkaR3ShopWithBranchAssign(krs);
        if (null != krs) {
            BigDecimal deptId = new BigDecimal(krs.getMap().get("fgs_id").toString());
            if (null != deptId && deptId.intValue() == -1) {// deptId=-1l,说明没有找到上级分公司
                request.setAttribute("confirm", 1);
            }
        }

        // 全部康佳产品
        PePdModel pePdModel = new PePdModel();
        pePdModel.setBrand_id(Constants.KONKA_BRAND_ID);
        pePdModel.setIs_del(0);
        List<PePdModel> pdList = getFacade().getPePdModelService().getPePdModelWithClsNameBrandNameList(pePdModel);

        request.setAttribute("pdList", pdList);

        // 进货登记单
        String order_id = (String) dynaBean.get("order_id");
        KonkaOrderInfo konkaOrderInfo = new KonkaOrderInfo();
        konkaOrderInfo.setId(Long.valueOf(order_id));
        konkaOrderInfo = getFacade().getKonkaOrderInfoService().getKonkaOrderInfo(konkaOrderInfo);
        // dynaBean.set("fullName",
        // super.getPIndexFullName(konkaOrderInfo.getUser_p_index()));
        dynaBean.set("fullName", super.getPIndexName(konkaOrderInfo.getUser_p_index(), ""));
        dynaBean.set("order_id", order_id);
        dynaBean.set("userName", konkaOrderInfo.getUser_name());
        dynaBean.set("userShopName", konkaOrderInfo.getUser_shop_name());
        dynaBean.set("userZip", konkaOrderInfo.getUser_zip());
        dynaBean.set("userAddr", konkaOrderInfo.getUser_addr());
        dynaBean.set("userTel", konkaOrderInfo.getUser_tel());
        dynaBean.set("tradeIndex", konkaOrderInfo.getTrade_index());// 交易流水号
        super.setprovinceAndcityAndcountryToFrom(dynaBean, konkaOrderInfo.getUser_p_index());

        KonkaOrderInfoDetails konkaOrderInfoDetails = new KonkaOrderInfoDetails();
        konkaOrderInfoDetails.setOrder_id(Long.valueOf(order_id));
        List<KonkaOrderInfoDetails> konkaOrderInfoDetailsList = getFacade().getKonkaOrderInfoDetailsService().getKonkaOrderInfoDetailsList(konkaOrderInfoDetails);
        request.setAttribute("konkaOrderInfoDetailsList", konkaOrderInfoDetailsList);

        String[] pd_ids = new String[konkaOrderInfoDetailsList.size()];
        for (int i = 0; i < konkaOrderInfoDetailsList.size(); i++) {
            pd_ids[i] = konkaOrderInfoDetailsList.get(i).getPd_id().toString();
        }
        dynaBean.set("pd_ids", StringUtils.join(pd_ids, ","));

        String fgsSN = konkaR3Shop.getBranch_area_name_2();
        request.setAttribute("sales_org", fgsSN);

        if (super.isCallR3Interface(request)) {
            ReturnInfo<KNVP> info = new ReturnInfo<KNVP>();
            List<KNVP> knvpList = new ArrayList<KNVP>();
            info = super.getFacade().getR3WebInterfaceService().getKnvpsList(fgsSN, Constants.default_vtweg, Constants.default_spart, konkaR3Shop.getR3_code());
            if (info.getSuccess() == true) {
                knvpList = info.getDataResult();
            }

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

            request.setAttribute("agList", agList);
            request.setAttribute("reList", reList);
            request.setAttribute("rgList", rgList);
            request.setAttribute("weList", weList);

            if (agList.size() == 0) {
                request.setAttribute("ag", konkaR3Shop.getR3_code());
            }
            if (reList.size() == 0) {
                request.setAttribute("re", konkaR3Shop.getR3_code());
            }
            if (rgList.size() == 0) {
                request.setAttribute("rg", konkaR3Shop.getR3_code());
            }
            if (weList.size() == 0) {
                KonkaMobileImpStore s = new KonkaMobileImpStore();
                s.setR3_shdf_sn(konkaR3Shop.getR3_code());
                List<KonkaMobileImpStore> sList = super.getFacade().getKonkaMobileImpStoreService().getKonkaMobileImpStoreListForDistinctSdf(s);

                List<KNVP> __weList = new ArrayList<KNVP>();
                for (KonkaMobileImpStore cur : sList) {
                    KNVP k = new KNVP();
                    k.setKUNN2(cur.getR3_sdf_sn());
                    __weList.add(k);
                }
                request.setAttribute("weList", __weList);
                if (__weList.size() == 0) {
                    request.setAttribute("we", konkaR3Shop.getR3_code());
                }
            }

            request.setAttribute("knvpList", knvpList);
        }

        super.copyProperties(form, konkaOrderInfo);
        return new ActionForward("/../customer/JxcKonkaOrderRegister/form_edit.jsp");
    }

    // 检查订单上的产品的库存
    private List<StockCheckResult> checkStock(String zbukrs, String[] md_names, String[] good_counts) {
        List<KonkaR3OrderLines> itemList = new ArrayList<KonkaR3OrderLines>();
        if (md_names.length != 0 && md_names.length == good_counts.length) {
            for (int i = 0; i < md_names.length; i++) {
                KonkaR3OrderLines konkaR3OrderLines = new KonkaR3OrderLines();
                konkaR3OrderLines.setReview_amount(new BigDecimal(good_counts[i].trim() + ""));
                konkaR3OrderLines.setMaterial_code(md_names[i]);
                itemList.add(konkaR3OrderLines);
            }
        }

        List<StockCheckResult> checkResult = null;
        ReturnInfo<StockCheckResult> info = new ReturnInfo<StockCheckResult>();
        // checkResult =
        // getFacade().getR3WebInterfaceService().checkStock(zbukrs, itemList);
        info = getFacade().getR3WebInterfaceService().checkStock(zbukrs, itemList);
        if (info.getSuccess() == true) {
            checkResult = info.getDataResult();
        }

        return checkResult;
    }

    // 检查订单上的产品的库存 wkl
    private List<StockCheckResult> checkStock(String zbukrs, String[] md_names, String[] good_counts, String[] span_index) {
        List<KonkaR3OrderLines> itemList = new ArrayList<KonkaR3OrderLines>();
        if (md_names.length != 0 && md_names.length == good_counts.length && md_names.length == span_index.length) {
            for (int i = 0; i < md_names.length; i++) {
                KonkaR3OrderLines konkaR3OrderLines = new KonkaR3OrderLines();
                konkaR3OrderLines.setReview_amount(new BigDecimal(good_counts[i].trim() + ""));
                konkaR3OrderLines.setMaterial_code(md_names[i]);
                itemList.add(konkaR3OrderLines);
            }
        }

        List<StockCheckResult> checkResult = new ArrayList<StockCheckResult>();
        ReturnInfo<StockCheckResult> info = new ReturnInfo<StockCheckResult>();
        // checkResult =
        // getFacade().getR3WebInterfaceService().checkStock(zbukrs, itemList);
        info = getFacade().getR3WebInterfaceService().checkStock(zbukrs, itemList);
        if (info.getSuccess() == true) {
            checkResult = info.getDataResult();
        }

        if (null != checkResult && checkResult.size() > 0) {
            for (int i = 0; i < checkResult.size(); i++) {
                checkResult.get(i).setMatnr(span_index[i]);
            }
        }

        return checkResult;
    }

    // public ActionForward checkStockForAjax1(ActionMapping mapping, ActionForm
    // form, HttpServletRequest request,
    // HttpServletResponse response) throws Exception {
    // if (!super.isCallR3Interface(request)) {
    // return null;
    // }
    //
    // DynaBean dynaBean = (DynaBean) form;
    // String zbukrs = (String) dynaBean.get("sales_org"); // 销售组织
    // String md_names = (String) dynaBean.get("md_name");// 产品编码
    // String good_counts = (String) dynaBean.get("good_count");// 数量
    //
    //
    // String[] md_names_array = md_names.split(",");
    // String[] good_counts_array = good_counts.split(",");
    //
    //
    // List<StockCheckResult> checkResult = this.checkStock(zbukrs,
    // md_names_array, good_counts_array);
    //
    // logger.info("json string : {}", JSON.toJSONString(checkResult));
    //
    // super.renderJson(response, JSON.toJSONString(checkResult));
    // return null;
    // }
    /**
     * 检查订单上的产品的库存,ajax
     */
    public ActionForward checkStockForAjax(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (!super.isCallR3Interface(request)) {
            return null;
        }

        DynaBean dynaBean = (DynaBean) form;
        String zbukrs = (String) dynaBean.get("sales_org"); // 销售组织
        String md_names = (String) dynaBean.get("md_name");// 产品编码
        String good_counts = (String) dynaBean.get("good_count");// 数量
        String span_index = (String) dynaBean.get("span_index");// 数量
        // //System.out.println(span_index);
        String[] md_names_array = md_names.split(",");
        String[] good_counts_array = good_counts.split(",");

        List<StockCheckResult> checkResult = new ArrayList<StockCheckResult>();
        if (StringUtils.isBlank(span_index)) {
            checkResult = this.checkStock(zbukrs, md_names_array, good_counts_array);
        } else {
            String[] span_index_array = span_index.split(",");
            checkResult = this.checkStock(zbukrs, md_names_array, good_counts_array, span_index_array);
        }

        logger.info("json string : {}", JSON.toJSONString(checkResult));

        super.renderJson(response, JSON.toJSONString(checkResult));
        return null;
    }

    /**
     * 保存订单
     **/
    public ActionForward saveOrder(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        DynaBean dynaBean = (DynaBean) form;
        String mod_id = (String) dynaBean.get("mod_id");
        String cust_id = (String) dynaBean.get("cust_id");
        String order_id = (String) dynaBean.get("order_id");
        String zbukrs = (String) dynaBean.get("sales_org");// 销售组织
        String process_id = (String) dynaBean.get("process_id");// 流程ID
        String[] pd_ids = request.getParameterValues("pd_id");// 型号
        String[] md_names = request.getParameterValues("md_name");// 产品编码

        String sellids = (String) dynaBean.get("sellids");
        String sell_bill_id = (String) dynaBean.get("sell_bill_id");
        String[] good_counts = request.getParameterValues("good_count");// 数量
        String[] good_units = request.getParameterValues("good_unit");// 单位
        String[] good_prices = request.getParameterValues("good_price");// 单价
        String[] good_discounts = request.getParameterValues("good_discount");// 折让
        String[] good_discount_price = request.getParameterValues("good_discount_price");// 折让金额
        String[] pd_remark = request.getParameterValues("pd_remark");// 产品备注
        String forward_type = (String) dynaBean.get("forward_type");
        String province = (String) dynaBean.get("province");
        String city = (String) dynaBean.get("city");
        String country = (String) dynaBean.get("country");
        String town = (String) dynaBean.get("town");
        String pay_type_value = (String) dynaBean.get("pay_type_value");
        String user_p_index = "";
        String discount_price_sum = (String) dynaBean.get("discount_price_sum");
        String order_date = (String) dynaBean.get("order_date");

        String FROMSALESMAN = (String) dynaBean.get("FROMSALESMAN"); // 状态位，标识是否是业务员提交
        String CUSTID = (String) dynaBean.get("CUSTID"); // 业务提交请求的客户ID

        String content1 = (String) dynaBean.get("content1");// 处理客户下单意见反馈字段
        String tel1 = (String) dynaBean.get("tel1");// 处理客户下单意见反馈电话

        HttpSession session = request.getSession();
        PeProdUser user = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);
        if (null == user || StringUtils.isBlank(cust_id)) {
            super.renderJavaScript(response, "alert('登录超时请重新登录。');history.back();");
            return null;
        }

        // add by Jiang,JiaYong 2013-08-26
        // 如果是业务员帮忙添加订单则在会话中保存状态为[1]
        // 连接地址为：struts.manager.admin.KonkaR3ShopNewAction
        Object from_manager_is_salesman = session.getAttribute("from_manager_is_salesman");

        KonkaDept fgsOfCurrentCustmer = super.getKonkaDeptByCustomerId(Long.valueOf(cust_id));
        if (null == fgsOfCurrentCustmer) {
            super.renderJavaScript(response, "alert('可能是您不是分公司管理员，或者您的客户信息不完整，没有权限操作。');history.back();");
            return null;
        }

        if (ArrayUtils.isEmpty(pd_ids)) {
            super.renderJavaScript(response, "alert('未选择产品。');history.back();");
            return null;
        }

        if (null != from_manager_is_salesman && "1".equals(from_manager_is_salesman.toString()) && StringUtils.isBlank(process_id)) {
            super.renderJavaScript(response, "alert('未选择订单类型！');history.back();");
            return null;
        }

        // 检查库存 1 通过,0 不通过
        if (super.isCallR3Interface(request)) {
            String[] md_names2 = new String[pd_ids.length - 1];
            String[] good_counts2 = new String[pd_ids.length - 1];
            // 开始. 取过来的值是{,xxx,yyy}
            for (int i = 1; i < md_names.length; i++) {
                md_names2[i - 1] = md_names[i];
            }
            for (int i = 1; i < good_counts.length; i++) {
                good_counts2[i - 1] = good_counts[i];
            }

            int isPass = 0;
            List<StockCheckResult> checkResult = this.checkStock(zbukrs, md_names2, good_counts2);
            for (StockCheckResult sr : checkResult) {
                isPass = isPass * sr.getIsOk();
            }

            if (isPass != 1) {
                // 库存检查没有通过,显示检查结果
                // request.setAttribute("checkResult", checkResult);
                // return null;
            }
        }

        if (StringUtils.isNotBlank(town)) {
            user_p_index = town;
        } else if (StringUtils.isNotBlank(country)) {
            user_p_index = country;
        } else if (StringUtils.isNotBlank(city)) {
            user_p_index = city;
        } else if (StringUtils.isNotBlank(province)) {
            user_p_index = province;
        }

        /** 订单处理............................................ */
        KonkaOrderInfo konkaOrderInfo = new KonkaOrderInfo();
        super.copyProperties(konkaOrderInfo, form);

        // 付款信息
        if (StringUtils.isNotBlank(pay_type_value)) {
            konkaOrderInfo.setPay_type(Integer.valueOf(pay_type_value));
        }

        konkaOrderInfo.setCust_id(Long.valueOf(cust_id));
        konkaOrderInfo.setOrder_state(0);
        if (StringUtils.isNotBlank(user_p_index)) {
            konkaOrderInfo.setUser_p_index(Long.valueOf(user_p_index));
        }

        // 判断是否是业务员下单，如果是，下单人则保存业务员自己的ID
        if ("1".equals(from_manager_is_salesman)) {
            PeProdUser ui = (PeProdUser) session.getAttribute(Constants.USER_INFO);
            konkaOrderInfo.setAdd_user_id(ui.getId());
            konkaOrderInfo.setAdd_user_name(ui.getUser_name());
        } else {
            konkaOrderInfo.setAdd_user_id(user.getId());
            konkaOrderInfo.setAdd_user_name(user.getUser_name());
        }

        konkaOrderInfo.setIs_del(0);// 未删除
        konkaOrderInfo.setIs_end(0);// 未完结
        String YM = DateFormatUtils.format(konkaOrderInfo.getOrder_date(), "yyyyMMdd");
        int year = Integer.valueOf(YM.substring(0, 4));// 订单年份
        int month = Integer.valueOf(YM.substring(4, 6));// 订单月份
        // 产品信息
        List<KonkaOrderInfoDetails> konkaOrderInfoDetailsList = new ArrayList<KonkaOrderInfoDetails>();
        Long orderNum = 0L;
        BigDecimal orderMoney = new BigDecimal("0.00");
        BigDecimal orderDiscountPrice = new BigDecimal("0.000000");
        for (int i = 1; i < pd_ids.length; i++) {
            KonkaOrderInfoDetails konkaOrderInfoDetails = new KonkaOrderInfoDetails();
            konkaOrderInfoDetails.setPd_id(Long.valueOf(pd_ids[i]));
            // 取型号、大类信息
            PePdModel pePdModel = new PePdModel();
            pePdModel.setBrand_id(Constants.KONKA_BRAND_ID);
            pePdModel.setIs_del(0);
            pePdModel.setPd_id(Long.valueOf(pd_ids[i]));
            pePdModel = getFacade().getPePdModelService().getPePdModel(pePdModel);
            if (null != pePdModel) {
                konkaOrderInfoDetails.setPd_name(pePdModel.getMd_name());
                konkaOrderInfoDetails.setBrand_id(pePdModel.getBrand_id());
                konkaOrderInfoDetails.setBrand_name(Constants.KONKA_BRAND_NAME);
                konkaOrderInfoDetails.setPd_type_id(pePdModel.getCls_id());
                if (null != pePdModel.getCls_id() && "".equals(pePdModel.getCls_id()) && null != super.getBasePdClazz(pePdModel.getCls_id()))
                    konkaOrderInfoDetails.setPd_type_name(super.getBasePdClazz(pePdModel.getCls_id()).getCls_name());
            }
            konkaOrderInfoDetails.setGood_count(Integer.valueOf(good_counts[i]));
            konkaOrderInfoDetails.setGood_price(new BigDecimal(good_prices[i]));
            konkaOrderInfoDetails.setGood_unit(good_units[i]);
            konkaOrderInfoDetails.setGood_sum_price(new BigDecimal(good_prices[i]).multiply(new BigDecimal(good_counts[i])));
            if (null != good_discounts && null != good_discounts[i]) konkaOrderInfoDetails.setGood_discount(new BigDecimal(good_discounts[i]));
            // konkaOrderInfoDetails.setGood_discount_price(konkaOrderInfoDetails.getGood_sum_price().multiply(new
            // BigDecimal(good_discounts[i])).divide(new BigDecimal("100")));
            if (null != good_discount_price && null != good_discount_price[i] && StringUtils.isNotBlank(good_discount_price[i])) {
                konkaOrderInfoDetails.setGood_discount_price(new BigDecimal(good_discount_price[i]));
            }
            konkaOrderInfoDetails.setPd_remark(pd_remark[i]);

            orderNum += Long.valueOf(good_counts[i]);
            orderMoney = orderMoney.add(konkaOrderInfoDetails.getGood_sum_price());
            // orderDiscountPrice =
            // orderDiscountPrice.add(konkaOrderInfoDetails.getGood_discount_price());
            konkaOrderInfoDetailsList.add(konkaOrderInfoDetails);

            orderDiscountPrice =
                    orderDiscountPrice.add((new BigDecimal(good_counts[i]).multiply(new BigDecimal(good_prices[i])).multiply(new BigDecimal(good_discounts[i])
                            .divide(new BigDecimal(100)))).add(new BigDecimal(good_discount_price[i])));

            // //System.out.println(orderDiscountPrice + "折扣价格价格");
        }
        konkaOrderInfo.setOrder_num(orderNum);// 订单总数
        konkaOrderInfo.setMoney(orderMoney);// 订单总金额
        // konkaOrderInfo.setGood_discount_price(new
        // BigDecimal(discount_price_sum));// 订单折让总金额---旧的逻辑
        konkaOrderInfo.setGood_discount_price(orderDiscountPrice);// 订单折让总金额---新的逻辑
        konkaOrderInfo.setKonkaOrderInfoDetailsList(konkaOrderInfoDetailsList);

        // sell_bill_id插入到konka_order_info

        if (StringUtils.isNotBlank(sell_bill_id)) {
            konkaOrderInfo.setZmd_order_num(Long.valueOf(sell_bill_id));
            konkaOrderInfo.setZmd_order_flag(1);
        }

        PeProdUser ywyOfThisCust = super.getYwyOfCustomerByCustomerId(Long.valueOf(cust_id));
        if (null != ywyOfThisCust) konkaOrderInfo.setAdd_dept_id(ywyOfThisCust.getDept_id()); // 取业务员所在部门的ID

        // 取审核流程
        if (null != from_manager_is_salesman && "1".equals(from_manager_is_salesman.toString())) {
            KonkaOrderAuditProcessNode node = super.getNextProcessNode(Long.valueOf(process_id), null);
            konkaOrderInfo.setNext_audit_role_id(node.getRole_id());
            konkaOrderInfo.setNext_node_id(node.getId());
        }

        /** 上传附件 */
        String[] id_ = request.getParameterValues("_id");
        String[] link_id_ = request.getParameterValues("_link_id");
        String[] link_tab_ = request.getParameterValues("_link_tab");
        String[] file_name_ = request.getParameterValues("_file_name");
        String[] file_type_ = request.getParameterValues("_file_type");
        String[] file_size_ = request.getParameterValues("_file_size");
        String[] save_path_ = request.getParameterValues("_save_path");
        String[] save_name_ = request.getParameterValues("_save_name");
        String[] file_desc_ = request.getParameterValues("_file_desc");
        String[] del_mark_ = request.getParameterValues("_del_mark");

        String[] file_descs = request.getParameterValues("file_desc");
        List<Attachment> attachmentList = new ArrayList<Attachment>();
        if (ArrayUtils.isNotEmpty(id_)) {
            for (int i = 0; i < id_.length; i++) {
                Attachment at = new Attachment();
                at.setLink_id(Long.valueOf(link_id_[i]));
                at.setLink_tab(link_tab_[i]);
                at.setFile_name(file_name_[i]);
                at.setFile_type(file_type_[i]);
                at.setFile_size(Integer.valueOf(file_size_[i]));
                at.setSave_path(save_path_[i]);
                at.setSave_name(save_name_[i]);
                at.setFile_desc(file_desc_[i]);
                at.setDel_mark(Short.valueOf(del_mark_[i]));
                attachmentList.add(at);
            }
        }
        Integer n = attachmentList.size();

        // List<UploadFile> uploadFileList = super.uploadFile(form, true, 0, new
        // int[] { 60, 120, 240, 350, 400, 800 });
        List<UploadFile> uploadFileList = super.uploadFile(form, MmtFilePathConfig.OTHERS_PATH, true, 0, new int[] {60, 120, 240, 350, 400, 800});

        if (null != uploadFileList && uploadFileList.size() > 0) {
            for (int i = 0; i < uploadFileList.size(); i++) {
                UploadFile file = uploadFileList.get(i);
                if (file.getFormName().startsWith("pic_file_")) {
                    Integer j = Integer.valueOf(StringUtils.splitByWholeSeparator(file.getFormName(), "_file_")[1]);

                    Attachment att = new Attachment();
                    att.setLink_tab("KONKA_ORDER_INFO");
                    att.setFile_name(file.getFileName());
                    // alter table ATTACHMENT alter FILE_TYPE set data type
                    // varchar(255)
                    att.setFile_type(file.getContentType());
                    att.setFile_size(file.getFileSize());
                    att.setSave_path(file.getFileSavePath());
                    att.setSave_name(file.getFileSaveName());
                    att.setFile_desc(file_descs[j - n - 1]);
                    att.setDel_mark(Short.valueOf("0"));
                    attachmentList.add(att);
                }
            }
        }

        if (StringUtils.isBlank(order_id)) { // 新增
            if ("ZFRE".equals(konkaOrderInfo.getDoc_type())) {
                konkaOrderInfo.setTrade_index(super.generateReturnTradeIndex());
            } else {
                konkaOrderInfo.setTrade_index(super.generateTradeIndex());
            }
            konkaOrderInfo.getMap().put("save_type", "insert");
        } else { // 修改
            KonkaOrderInfo o = new KonkaOrderInfo();
            o.setId(Long.valueOf(order_id));
            o = super.getFacade().getKonkaOrderInfoService().getKonkaOrderInfo(o);
            if (null != o && null != o.getIs_submit() && o.getProcess_id() != null && o.getIs_submit() == 0 && konkaOrderInfo.getIs_submit() == 1) {
                // 订单含有流程后再次提交，初始化下一个审核角色和审核节点
                KonkaOrderAuditProcessNode firstNode = super.getNextProcessNode(o, null);
                konkaOrderInfo.setNext_audit_role_id(firstNode.getRole_id());
                konkaOrderInfo.setNext_node_id(firstNode.getId());
            }

            konkaOrderInfo.setId(Long.valueOf(order_id));

            konkaOrderInfo.getMap().put("save_type", "update");
        }
        konkaOrderInfo.setAttachmentList(attachmentList);

        konkaOrderInfo.getMap().put("content1", content1);// 处理客户意见反馈
        konkaOrderInfo.getMap().put("tel1", tel1);// 处理客户意见反馈电话

        getFacade().getKonkaOrderInfoService().createKonkaOrderInfoOrder(konkaOrderInfo);
        if (konkaOrderInfo.getIs_submit() == 0) {
            if ("1".equals(forward_type)) {
                saveMessage(request, "entity.temp.inserted");
            } else if ("2".equals(forward_type)) {
                saveMessage(request, "entity.temp.inserted.to.add");
            }
        } else {
            saveMessage(request, "entity.inserted");
        }

        // 处理pshoworder的转单状态

        // //System.out.println(dynaBean.get("order_type") + "order_type");

        if ("2".equals(dynaBean.get("order_type"))) {
            // dynaBean.get("pshow_id");
            String pshow_id = (String) dynaBean.get("pshow_id");
            String pshowids = (String) dynaBean.get("pshowids");
            String[] pks2 = pshowids.split(",");
            if (StringUtils.isNotBlank(pshow_id)) {
                PshowOrder p = new PshowOrder();
                p.setId(Long.valueOf(pshow_id));
                // /p.setState(70);//70为已经转R3
                super.getFacade().getPshowOrderService().modifyPshowOrderForSwitchR3(p);
                // 生成销售记录开始
                PshowOrder t = new PshowOrder();
                t.setId(Long.valueOf(pshow_id));
                t = super.getFacade().getPshowOrderService().getPshowOrder(t);

                PshowOrdeDetails psd1 = new PshowOrdeDetails();
                psd1.setOrder_id(Long.valueOf(pshow_id));
                List<PshowOrdeDetails> pshowordedetails1 = super.getFacade().getPshowOrdeDetailsService().getPshowOrdeDetailsList(psd1);

                JBill jbill = new JBill();
                jbill.setBill_type(20);
                Date today = new Date();
                String bill_sn = "20" + "-" + DateFormatUtils.format(today, "yyyyMMdd") + "-";
                Long seq_jbill_id = super.getFacade().getJBillService().getSeqJBillId();
                bill_sn = bill_sn + StringUtils.leftPad(seq_jbill_id.toString(), 8, "0");
                jbill.setBill_sn(bill_sn);
                jbill.setRec_money(t.getPay_price());
                jbill.setDiscount(new BigDecimal("100"));
                jbill.setMoney(t.getPay_price());
                jbill.setOpr_date(today);
                if (null != user.getCust_id()) {
                    jbill.setC_id(user.getCust_id());
                }
                jbill.setPlan_hand_time(today);

                // 插入销量明细
                List<JBillDetails> jBillDetailsList = new ArrayList<JBillDetails>();
                if (null != pshowordedetails1 && pshowordedetails1.size() > 0) {
                    for (PshowOrdeDetails dd : pshowordedetails1) {
                        JBillDetails jBillDetails = new JBillDetails();
                        jBillDetails.setStore_id(182L);// 默认取总库

                        KonkaBcompPd kb = new KonkaBcompPd();
                        kb.setId(dd.getPd_id());
                        List<KonkaBcompPd> kbList = super.getFacade().getKonkaBcompPdService().getKonkaBcompPdList(kb);
                        if (null != kbList && kbList.size() > 0) {
                            kb = kbList.get(0);
                        }
                        PePdModel ppm = new PePdModel();
                        ppm.setPd_id(Long.valueOf(kb.getPd_spec()));
                        ppm = super.getFacade().getPePdModelService().getPePdModel(ppm);

                        JBaseGoods jb = new JBaseGoods();
                        jb.setC_id(user.getCust_id());
                        jb.setGoods_name(ppm.getMd_name());
                        // jb.setGoods_stutes(0);
                        jb = super.getFacade().getJBaseGoodsService().getJBaseGoods(jb);

                        if (null != jb) {

                            jBillDetails.setGoods_id(jb.getGoods_id());

                            jBillDetails.setNum(dd.getNum());
                            jBillDetails.setPrice(dd.getPrice());
                            jBillDetails.setMoney(dd.getTotal_price());
                            jBillDetailsList.add(jBillDetails);
                            // super.renderJavaScript(response,
                            // "window.onload=function(){alert('对不起！请去维护型号" +
                            // ppm.getMd_name()
                            // + "的库存');history.back();}");
                        }

                        jbill.setjBillDetailsList(jBillDetailsList);

                        // 新增往来单位
                        JBasePartner jBasePartner = new JBasePartner();
                        if (null != t.getBuyer_mp()) {
                            jBasePartner.setLink_tel(t.getBuyer_mp());
                        }
                        if (null != t.getBuyer_name()) {
                            jBasePartner.setPartner_name(t.getBuyer_name()); // 往来单位名称
                        }
                        if (null != user.getCust_id()) {
                            jBasePartner.setC_id(user.getCust_id()); // 客户ID
                        }
                        if (null != t.getBuyer_name()) {
                            jBasePartner.setLink_name(t.getBuyer_name());
                        }
                        if (null != t.getBuyer_mp()) {
                            jBasePartner.setLink_mobile(t.getBuyer_mp());
                        }
                        if (null != t.getBuyer_mp()) {
                            jBasePartner.setConsignee_tel(t.getBuyer_mp());
                        }
                        if (null != t.getBuyer_name()) {
                            jBasePartner.setConsignee_name(t.getBuyer_name());
                        }
                        if (null != t.getBuyer_p_index()) {
                            jBasePartner.setConsignee_p_index(t.getBuyer_p_index().toString());
                        }
                        if (null != t.getBuyer_addr()) {
                            jBasePartner.setConsignee_street(t.getBuyer_addr());
                        }
                        jBasePartner.setPartner_type(1); // 伙伴类型: 0-供应商 1-客户,
                        // 10,供应商和客户
                        jBasePartner.setPartner_obj(0); // 伙伴对象:0-个人 1-组织/单位

                        if (null != user.getCust_id()) {
                            long time = new Date().getTime();
                            String ss = String.valueOf(time);
                            String partner_sn = "WLDW-KH-GR-" + user.getCust_id() + "-" + ss.substring(ss.length() - 6, ss.length());
                            jBasePartner.setPartner_sn(partner_sn);
                        }
                        jbill.setjBasePartner(jBasePartner);
                    }
                    super.getFacade().getJBillService().createJBillAndDeatails(jbill);
                }
            }
            if (null != pks2 && pks2.length > 0) {
                for (String pd : pks2) {
                    PshowOrder p = new PshowOrder();
                    p.setId(Long.valueOf(pd));
                    // /p.setState(70);//70为已经转R3
                    super.getFacade().getPshowOrderService().modifyPshowOrderForSwitchR3(p);
                }
                // 生成销售记录开始
                PshowOrder t = new PshowOrder();
                t.setId(Long.valueOf(pks2[0]));
                t = super.getFacade().getPshowOrderService().getPshowOrder(t);
                JBill jbill = new JBill();
                jbill.setBill_type(20);
                Date today = new Date();
                String bill_sn = "20" + "-" + DateFormatUtils.format(today, "yyyyMMdd") + "-";
                Long seq_jbill_id = super.getFacade().getJBillService().getSeqJBillId();
                bill_sn = bill_sn + StringUtils.leftPad(seq_jbill_id.toString(), 8, "0");
                jbill.setBill_sn(bill_sn);
                jbill.setRec_money(t.getPay_price());
                jbill.setDiscount(new BigDecimal("100"));
                jbill.setMoney(t.getPay_price());
                jbill.setOpr_date(today);
                if (null != user.getCust_id()) {
                    jbill.setC_id(user.getCust_id());
                }
                jbill.setPlan_hand_time(today);
                BigDecimal recmoney = new BigDecimal("0");
                BigDecimal money = new BigDecimal("0");
                List<JBillDetails> jBillDetailsList = new ArrayList<JBillDetails>();

                for (String pd : pks2) {
                    PshowOrder t1 = new PshowOrder();
                    t1.setId(Long.valueOf(pks2[0]));
                    t1 = super.getFacade().getPshowOrderService().getPshowOrder(t1);
                    recmoney = recmoney.add(t1.getPay_price());
                    money = money.add(t1.getPay_price());
                    PshowOrdeDetails psd1 = new PshowOrdeDetails();
                    psd1.setOrder_id(Long.valueOf(pd));
                    List<PshowOrdeDetails> pshowordedetails1 = super.getFacade().getPshowOrdeDetailsService().getPshowOrdeDetailsList(psd1);
                    if (null != pshowordedetails1 && pshowordedetails1.size() > 0) {
                        for (PshowOrdeDetails dd : pshowordedetails1) {
                            JBillDetails jBillDetails = new JBillDetails();
                            jBillDetails.setStore_id(182L);// 默认取总库

                            KonkaBcompPd kb = new KonkaBcompPd();
                            kb.setId(dd.getPd_id());
                            List<KonkaBcompPd> kbList = super.getFacade().getKonkaBcompPdService().getKonkaBcompPdList(kb);
                            if (null != kbList && kbList.size() > 0) {
                                kb = kbList.get(0);
                            }
                            PePdModel ppm = new PePdModel();
                            ppm.setPd_id(Long.valueOf(kb.getPd_spec()));

                            ppm = super.getFacade().getPePdModelService().getPePdModel(ppm);
                            if (ppm != null) {
                                JBaseGoods jb = new JBaseGoods();
                                jb.setC_id(user.getCust_id());
                                jb.setGoods_name(ppm.getMd_name());
                                // jb.setGoods_stutes(0);
                                jb = super.getFacade().getJBaseGoodsService().getJBaseGoods(jb);

                                if (null != jb) {

                                    jBillDetails.setGoods_id(jb.getGoods_id());

                                    jBillDetails.setNum(dd.getNum());
                                    jBillDetails.setPrice(dd.getPrice());
                                    jBillDetails.setMoney(dd.getTotal_price());
                                    jBillDetailsList.add(jBillDetails);
                                    // super.renderJavaScript(response,
                                    // "window.onload=function(){alert('对不起！请去维护型号"
                                    // + ppm.getMd_name()
                                    // + "的库存');history.back();}");

                                    //
                                    // }

                                }

                            }
                        }
                        jbill.setjBillDetailsList(jBillDetailsList);
                        // 新增往来单位
                        JBasePartner jBasePartner = new JBasePartner();
                        if (null != t.getBuyer_mp()) {
                            jBasePartner.setLink_tel(t.getBuyer_mp());
                        }
                        if (null != t.getBuyer_name()) {
                            jBasePartner.setPartner_name(t.getBuyer_name()); // 往来单位名称
                        }
                        if (null != user.getCust_id()) {
                            jBasePartner.setC_id(user.getCust_id()); // 客户ID
                        }
                        if (null != t.getBuyer_name()) {
                            jBasePartner.setLink_name(t.getBuyer_name());
                        }
                        if (null != t.getBuyer_mp()) {
                            jBasePartner.setLink_mobile(t.getBuyer_mp());
                        }
                        if (null != t.getBuyer_mp()) {
                            jBasePartner.setConsignee_tel(t.getBuyer_mp());
                        }
                        if (null != t.getBuyer_name()) {
                            jBasePartner.setConsignee_name(t.getBuyer_name());
                        }
                        if (null != t.getBuyer_p_index()) {
                            jBasePartner.setConsignee_p_index(t.getBuyer_p_index().toString());
                        }
                        if (null != t.getBuyer_addr()) {
                            jBasePartner.setConsignee_street(t.getBuyer_addr());
                        }
                        jBasePartner.setPartner_type(1); // 伙伴类型: 0-供应商 1-客户,
                        // 10,供应商和客户
                        jBasePartner.setPartner_obj(0); // 伙伴对象:0-个人 1-组织/单位

                        if (null != user.getCust_id()) {
                            long time = new Date().getTime();
                            String ss = String.valueOf(time);
                            String partner_sn = "WLDW-KH-GR-" + user.getCust_id() + "-" + ss.substring(ss.length() - 6, ss.length());
                            jBasePartner.setPartner_sn(partner_sn);
                        }

                        jbill.setjBasePartner(jBasePartner);

                    }
                    super.getFacade().getJBillService().createJBillAndDeatails(jbill);
                }
            }

        }
        if ("4".equals(dynaBean.get("order_type"))) {
            // String sell_bill_id=(String)dynaBean.get("sell_bill_id");
            String[] pks1 = sellids.split(",");
            if (StringUtils.isNotBlank(sell_bill_id)) {
                KonkaXxSellBill k = new KonkaXxSellBill();
                k.setSell_bill_id(Long.valueOf(sell_bill_id));
                k.setIs_switch(1);
                super.getFacade().getKonkaXxSellBillService().modifyKonkaXxSellBill(k);
            }
            if (null != pks1 && pks1.length > 0 && StringUtils.isNotEmpty(sellids)) {
                for (String pd : pks1) {
                    KonkaXxSellBill k = new KonkaXxSellBill();
                    k.setSell_bill_id(Long.valueOf(pd));
                    k.setIs_switch(1);
                    super.getFacade().getKonkaXxSellBillService().modifyKonkaXxSellBill(k);
                }
            }

        }

        // the line below is added for pagination
        StringBuffer pathBuffer = new StringBuffer();
        if ("2".equals(forward_type)) { // 保存并新建
            pathBuffer.append(mapping.findForward("success_to_add").getPath());
        } else {
            pathBuffer.append(mapping.findForward("success").getPath());
        }
        if (StringUtils.isBlank(mod_id)) {
            mod_id = "105020400";
        }
        pathBuffer.append("&mod_id=" + mod_id);
        if (StringUtils.isNotBlank(FROMSALESMAN)) {
            pathBuffer.append("&FROMSALESMAN=" + FROMSALESMAN);
        }
        if (StringUtils.isNotBlank(CUSTID)) {
            pathBuffer.append("&CUSTID=" + CUSTID);
        }
        pathBuffer.append("&");
        // pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request,
        // "id", "pks", "method")));
        ActionForward forward = new ActionForward(pathBuffer.toString(), true);
        // end
        return forward;
    }

    /**
     * 保存退货订单
     **/
    public ActionForward saveReturnOrder(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        DynaBean dynaBean = (DynaBean) form;
        String mod_id = (String) dynaBean.get("mod_id");
        String cust_id = (String) dynaBean.get("cust_id");
        String order_id = (String) dynaBean.get("order_id");
        String zbukrs = (String) dynaBean.get("sales_org");// 销售组织
        String process_id = (String) dynaBean.get("process_id");// 流程ID
        String[] pd_ids = request.getParameterValues("pd_id");// 型号
        String[] md_names = request.getParameterValues("md_name");// 产品编码
        String[] good_counts = request.getParameterValues("good_count");// 数量
        String[] good_units = request.getParameterValues("good_unit");// 单位
        String[] good_prices = request.getParameterValues("good_price");// 单价
        String[] good_discounts = request.getParameterValues("good_discount");// 折让
        String[] good_discount_price = request.getParameterValues("good_discount_price");// 折让金额
        String[] pd_remark = request.getParameterValues("pd_remark");// 产品备注
        String[] pd_trade_index = request.getParameterValues("pd_trade_index");// 产品对应订单号
        String forward_type = (String) dynaBean.get("forward_type");
        String user_p_index = "";
        String discount_price_sum = (String) dynaBean.get("discount_price_sum");
        String user_shop_name = (String) dynaBean.get("user_shop_name");

        String content1 = (String) dynaBean.get("content1");// 处理客户下单意见反馈字段
        String tel1 = (String) dynaBean.get("tel1");// 处理客户下单意见反馈字段
        logger.info("content------>" + content1);

        HttpSession session = request.getSession();
        PeProdUser user = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);
        if (null == user || StringUtils.isBlank(cust_id)) {
            super.renderJavaScript(response, "alert('登录超时请重新登录。');history.back();");
            return null;
        }

        // add by Jiang,JiaYong 2013-08-26
        // 如果是业务员帮忙添加订单则在会话中保存状态为[1]
        // 连接地址为：struts.manager.admin.KonkaR3ShopNewAction
        Object from_manager_is_salesman = session.getAttribute("from_manager_is_salesman");

        KonkaDept fgsOfCurrentCustmer = super.getKonkaDeptByCustomerId(Long.valueOf(cust_id));
        if (null == fgsOfCurrentCustmer) {
            super.renderJavaScript(response, "alert('可能是您不是分公司管理员，或者您的客户信息不完整，没有权限操作。');history.back();");
            return null;
        }

        if (ArrayUtils.isEmpty(pd_ids)) {
            super.renderJavaScript(response, "alert('未选择产品。');history.back();");
            return null;
        }

        if (null != from_manager_is_salesman && "1".equals(from_manager_is_salesman.toString()) && StringUtils.isBlank(process_id)) {
            super.renderJavaScript(response, "alert('未选择订单类型！');history.back();");
            return null;
        }

        // 检查库存 1 通过,0 不通过
        if (super.isCallR3Interface(request)) {
            String[] md_names2 = new String[pd_ids.length - 1];
            String[] good_counts2 = new String[pd_ids.length - 1];
            // 开始. 取过来的值是{,xxx,yyy}
            for (int i = 1; i < md_names.length; i++) {
                md_names2[i - 1] = md_names[i];
            }
            for (int i = 1; i < good_counts.length; i++) {
                good_counts2[i - 1] = good_counts[i];
            }

            int isPass = 0;
            List<StockCheckResult> checkResult = this.checkStock(zbukrs, md_names2, good_counts2);
            for (StockCheckResult sr : checkResult) {
                isPass = isPass * sr.getIsOk();
            }

            if (isPass != 1) {
                // 库存检查没有通过,显示检查结果
                // request.setAttribute("checkResult", checkResult);
                // return null;
            }
        }

        /** 订单处理............................................ */
        KonkaOrderInfo konkaOrderInfo = new KonkaOrderInfo();
        super.copyProperties(konkaOrderInfo, form);
        if (StringUtils.isNotBlank(user_shop_name)) {
            konkaOrderInfo.setUser_shop_name(user_shop_name);// 修改客户名称
        }
        konkaOrderInfo.setCust_id(Long.valueOf(cust_id));
        konkaOrderInfo.setOrder_state(0);
        if (StringUtils.isNotBlank(user_p_index)) {
            konkaOrderInfo.setUser_p_index(Long.valueOf(user_p_index));
        }

        // 判断是否是业务员下单，如果是，下单人则保存业务员自己的ID
        if ("1".equals(from_manager_is_salesman)) {
            PeProdUser ui = (PeProdUser) session.getAttribute(Constants.USER_INFO);
            konkaOrderInfo.setAdd_user_id(ui.getId());
            konkaOrderInfo.setAdd_user_name(ui.getUser_name());
        } else {
            konkaOrderInfo.setAdd_user_id(user.getId());
            konkaOrderInfo.setAdd_user_name(user.getUser_name());
        }

        konkaOrderInfo.setIs_del(0);// 未删除
        konkaOrderInfo.setIs_end(0);// 未完结
        String YM = DateFormatUtils.format(konkaOrderInfo.getOrder_date(), "yyyyMMdd");
        int year = Integer.valueOf(YM.substring(0, 4));// 订单年份
        int month = Integer.valueOf(YM.substring(4, 6));// 订单月份
        // 产品信息
        List<KonkaOrderInfoDetails> konkaOrderInfoDetailsList = new ArrayList<KonkaOrderInfoDetails>();
        Long orderNum = 0L;
        BigDecimal orderMoney = new BigDecimal("0.00");
        BigDecimal orderDiscountPrice = new BigDecimal("0.000000");
        for (int i = 1; i < pd_ids.length; i++) {
            KonkaOrderInfoDetails konkaOrderInfoDetails = new KonkaOrderInfoDetails();
            konkaOrderInfoDetails.setPd_id(Long.valueOf(pd_ids[i]));
            // 取型号、大类信息
            PePdModel pePdModel = new PePdModel();
            pePdModel.setBrand_id(Constants.KONKA_BRAND_ID);
            pePdModel.setIs_del(0);
            pePdModel.setPd_id(Long.valueOf(pd_ids[i]));
            pePdModel = getFacade().getPePdModelService().getPePdModel(pePdModel);
            if (null != pePdModel) {
                konkaOrderInfoDetails.setPd_name(pePdModel.getMd_name());
                konkaOrderInfoDetails.setBrand_id(pePdModel.getBrand_id());
                konkaOrderInfoDetails.setBrand_name(Constants.KONKA_BRAND_NAME);
                konkaOrderInfoDetails.setPd_type_id(pePdModel.getCls_id());
                if (null != pePdModel.getCls_id() && "".equals(String.valueOf(pePdModel.getCls_id())) && null != super.getBasePdClazz(pePdModel.getCls_id()))
                    konkaOrderInfoDetails.setPd_type_name(super.getBasePdClazz(pePdModel.getCls_id()).getCls_name());
            }
            konkaOrderInfoDetails.setGood_count(Integer.valueOf(good_counts[i]));
            konkaOrderInfoDetails.setGood_price(new BigDecimal(good_prices[i]));
            konkaOrderInfoDetails.setGood_unit(good_units[i]);
            konkaOrderInfoDetails.setGood_sum_price(new BigDecimal(good_prices[i]).multiply(new BigDecimal(good_counts[i])));
            if (null != good_discounts && null != good_discounts[i]) konkaOrderInfoDetails.setGood_discount(new BigDecimal(good_discounts[i]));
            // konkaOrderInfoDetails.setGood_discount_price(konkaOrderInfoDetails.getGood_sum_price().multiply(new
            // BigDecimal(good_discounts[i])).divide(new BigDecimal("100")));
            if (null != good_discount_price && null != good_discount_price[i] && StringUtils.isNotBlank(good_discount_price[i])) {
                konkaOrderInfoDetails.setGood_discount_price(new BigDecimal(good_discount_price[i]));
            }
            konkaOrderInfoDetails.setPd_remark(pd_remark[i]);
            konkaOrderInfoDetails.setPd_trade_index(pd_trade_index[i]);

            orderNum += Long.valueOf(good_counts[i]);
            orderMoney = orderMoney.add(konkaOrderInfoDetails.getGood_sum_price());
            // orderDiscountPrice =
            // orderDiscountPrice.add(konkaOrderInfoDetails.getGood_discount_price());
            konkaOrderInfoDetailsList.add(konkaOrderInfoDetails);
        }
        konkaOrderInfo.setOrder_num(orderNum);// 订单总数
        konkaOrderInfo.setMoney(orderMoney);// 订单总金额
        if (null != discount_price_sum && null != discount_price_sum && StringUtils.isNotBlank(discount_price_sum)) {
            konkaOrderInfo.setGood_discount_price(new BigDecimal(discount_price_sum));// 订单折让总金额
        }
        konkaOrderInfo.setKonkaOrderInfoDetailsList(konkaOrderInfoDetailsList);

        PeProdUser ywyOfThisCust = super.getYwyOfCustomerByCustomerId(Long.valueOf(cust_id));
        if (null != ywyOfThisCust) konkaOrderInfo.setAdd_dept_id(ywyOfThisCust.getDept_id()); // 取业务员所在部门的ID

        // 取审核流程
        if (null != from_manager_is_salesman && "1".equals(from_manager_is_salesman.toString())) {
            KonkaOrderAuditProcessNode node = super.getNextProcessNode(Long.valueOf(process_id), null);
            konkaOrderInfo.setNext_audit_role_id(node.getRole_id());
            konkaOrderInfo.setNext_node_id(node.getId());
        }

        /** 上传附件 */
        String[] id_ = request.getParameterValues("_id");
        String[] link_id_ = request.getParameterValues("_link_id");
        String[] link_tab_ = request.getParameterValues("_link_tab");
        String[] file_name_ = request.getParameterValues("_file_name");
        String[] file_type_ = request.getParameterValues("_file_type");
        String[] file_size_ = request.getParameterValues("_file_size");
        String[] save_path_ = request.getParameterValues("_save_path");
        String[] save_name_ = request.getParameterValues("_save_name");
        String[] file_desc_ = request.getParameterValues("_file_desc");
        String[] del_mark_ = request.getParameterValues("_del_mark");

        String[] file_descs = request.getParameterValues("file_desc");
        List<Attachment> attachmentList = new ArrayList<Attachment>();
        if (ArrayUtils.isNotEmpty(id_)) {
            for (int i = 0; i < id_.length; i++) {
                Attachment at = new Attachment();
                at.setLink_id(Long.valueOf(link_id_[i]));
                at.setLink_tab(link_tab_[i]);
                at.setFile_name(file_name_[i]);
                at.setFile_type(file_type_[i]);
                at.setFile_size(Integer.valueOf(file_size_[i]));
                at.setSave_path(save_path_[i]);
                at.setSave_name(save_name_[i]);
                at.setFile_desc(file_desc_[i]);
                at.setDel_mark(Short.valueOf(del_mark_[i]));
                attachmentList.add(at);
            }
        }
        Integer n = attachmentList.size();

        // List<UploadFile> uploadFileList = super.uploadFile(form, true, 0, new
        // int[] { 60, 120, 240, 350, 400, 800 });
        List<UploadFile> uploadFileList = super.uploadFile(form, MmtFilePathConfig.OTHERS_PATH, true, 0, new int[] {60, 120, 240, 350, 400, 800});
        if (null != uploadFileList && uploadFileList.size() > 0) {
            for (int i = 0; i < uploadFileList.size(); i++) {
                UploadFile file = uploadFileList.get(i);
                if (file.getFormName().startsWith("pic_file_")) {
                    Integer j = Integer.valueOf(StringUtils.splitByWholeSeparator(file.getFormName(), "_file_")[1]);

                    Attachment att = new Attachment();
                    att.setLink_tab("KONKA_ORDER_INFO");
                    att.setFile_name(file.getFileName());
                    // alter table ATTACHMENT alter FILE_TYPE set data type
                    // varchar(255)
                    att.setFile_type(file.getContentType());
                    att.setFile_size(file.getFileSize());
                    att.setSave_path(file.getFileSavePath());
                    att.setSave_name(file.getFileSaveName());
                    att.setFile_desc(file_descs[j - n - 1]);
                    att.setDel_mark(Short.valueOf("0"));
                    attachmentList.add(att);
                }
            }
        }

        konkaOrderInfo.getMap().put("content1", content1);// 处理客户意见反馈
        konkaOrderInfo.getMap().put("tel1", tel1);// 处理客户意见反馈

        if (StringUtils.isBlank(order_id)) { // 新增
            konkaOrderInfo.setTrade_index(super.generateReturnTradeIndex());
            konkaOrderInfo.getMap().put("save_type", "insert");
        } else { // 修改
            KonkaOrderInfo o = new KonkaOrderInfo();
            o.setId(Long.valueOf(order_id));
            o = super.getFacade().getKonkaOrderInfoService().getKonkaOrderInfo(o);
            if (null != o && null != o.getIs_submit() && o.getProcess_id() != null && o.getIs_submit() == 0 && konkaOrderInfo.getIs_submit() == 1) {
                // 订单含有流程后再次提交，初始化下一个审核角色和审核节点
                KonkaOrderAuditProcessNode firstNode = super.getNextProcessNode(o, null);
                konkaOrderInfo.setNext_audit_role_id(firstNode.getRole_id());
                konkaOrderInfo.setNext_node_id(firstNode.getId());
            }

            konkaOrderInfo.setId(Long.valueOf(order_id));
            konkaOrderInfo.getMap().put("save_type", "update");
        }

        konkaOrderInfo.setAttachmentList(attachmentList);

        getFacade().getKonkaOrderInfoService().createKonkaOrderInfoOrder(konkaOrderInfo);
        // //System.out.println("user.getReal_name()===" + user.getUser_name());
        // getFacade().getR3WebInterfaceService().saveKonkaOrderInfoReturn(konkaOrderInfo,
        // user.getUser_name());
        if (konkaOrderInfo.getIs_submit() == 0) {
            if ("1".equals(forward_type)) {
                saveMessage(request, "entity.temp.inserted");
            } else if ("2".equals(forward_type)) {
                saveMessage(request, "entity.temp.inserted.to.add");
            }
        } else {
            saveMessage(request, "entity.inserted");
        }

        // the line below is added for pagination
        StringBuffer pathBuffer = new StringBuffer();
        if ("2".equals(forward_type)) { // 保存并新建
            pathBuffer.append("/../customer/manager/JxcKonkaOrderRegister.do?method=addTH");
        } else {
            pathBuffer.append(mapping.findForward("success").getPath());
        }
        pathBuffer.append("&mod_id=" + mod_id);
        pathBuffer.append("&is_th=1");
        ActionForward forward = new ActionForward(pathBuffer.toString(), true);
        // end
        return forward;
    }

    public ActionForward editOrder(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        setNaviStringToRequestScope(form, request);
        DynaBean dynaBean = (DynaBean) form;
        String cust_id = (String) dynaBean.get("cust_id");
        String FROMSALESMAN = (String) dynaBean.get("FROMSALESMAN"); // 状态位，标识是否是业务员提交
        String CUSTID = (String) dynaBean.get("CUSTID"); // 业务提交请求的客户ID
        if (StringUtils.isNotBlank(FROMSALESMAN)) {
            request.setAttribute("FROMSALESMAN", FROMSALESMAN);
        }
        if (StringUtils.isNotBlank(CUSTID)) {
            request.setAttribute("CUSTID", CUSTID);
        }

        HttpSession session = request.getSession();
        PeProdUser user = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);
        if (null == user || StringUtils.isBlank(cust_id)) {
            super.renderJavaScript(response, "alert('登录超时请重新登录。');history.back();");
            return null;
        } else if (null == user.getCust_id()) {
            return null;
        }

        KonkaR3Shop konkaR3Shop = new KonkaR3Shop();
        konkaR3Shop.setId(user.getCust_id());
        konkaR3Shop = getFacade().getKonkaR3ShopService().getKonkaR3Shop(konkaR3Shop);
        if (null == konkaR3Shop) {
            return null;
        }

        // 取收货地址
        KonkaJxcBaseAddr konkaJxcBaseAddr = new KonkaJxcBaseAddr();
        konkaJxcBaseAddr.setIs_del(0);

        KonkaR3Shop r3shop = new KonkaR3Shop();
        r3shop.setId(user.getCust_id());
        r3shop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(r3shop);
        if (null != r3shop) {
            konkaJxcBaseAddr.setR3_id(r3shop.getId());
            konkaJxcBaseAddr.setR3_code(r3shop.getR3_code());
        }

        List<KonkaJxcBaseAddr> konkaJxcBaseAddrList = super.getFacade().getKonkaJxcBaseAddrService().getKonkaJxcBaseAddrList(konkaJxcBaseAddr);
        request.setAttribute("konkaJxcBaseAddrList", konkaJxcBaseAddrList);

        dynaBean.set("brandId", Constants.KONKA_BRAND_ID);
        Date today = new Date();
        dynaBean.set("today", DateFormatUtils.format(today, "yyyy-MM-dd"));// 订单日期
        dynaBean.set("r3_code", konkaR3Shop.getR3_code());

        // dynaBean.set("userPIndex", user.getStdEntpMain().getP_index());

        // 取R3网点分公司ID
        KonkaR3Shop krs = new KonkaR3Shop();
        krs.setId(Long.valueOf(cust_id));
        krs = getFacade().getKonkaR3ShopService().getKonkaR3ShopWithBranchAssign(krs);
        if (null != krs) {
            BigDecimal deptId = new BigDecimal(krs.getMap().get("fgs_id").toString());
            if (null != deptId && deptId.intValue() == -1) {// deptId=-1l,说明没有找到上级分公司
                request.setAttribute("confirm", 1);
            }
        }

        // 取订单流程
        // KonkaDept dept =
        // super.getKonkaDeptByCustomerId(Long.valueOf(cust_id));
        // KonkaOrderAuditProcess ap = new KonkaOrderAuditProcess();
        // ap.setAdd_dept_id(dept.getDept_id());
        // ap.setIs_del(0);
        // List<KonkaOrderAuditProcess> auditProcesseList =
        // getFacade().getKonkaOrderAuditProcessService()
        // .getKonkaOrderAuditProcessList(ap);
        //
        // KonkaOrderAuditProcess ap_public = new KonkaOrderAuditProcess();
        // ap_public.setAdd_dept_id(0L);
        // ap_public.setIs_del(0);
        // List<KonkaOrderAuditProcess> ap_publicauditProcesseList =
        // getFacade().getKonkaOrderAuditProcessService()
        // .getKonkaOrderAuditProcessList(ap_public);
        // auditProcesseList.addAll(ap_publicauditProcesseList);
        // request.setAttribute("processList", auditProcesseList);

        // 获取流程列表
        KonkaDept kd = super.getKonkaDeptByCustomerId(Long.valueOf(cust_id));
        KonkaDept dept = null;
        if (kd != null) {
            dept = super.getSuperiorForDeptType(kd.getDept_id(), 3);
        }
        List<KonkaOrderAuditProcess> processList = new ArrayList<KonkaOrderAuditProcess>();
        if (dept != null) {

            KonkaR3Shop konkaR3Shop_ = new KonkaR3Shop();
            konkaR3Shop_.setId(Long.valueOf(cust_id));
            konkaR3Shop_.setIs_del(0l);
            konkaR3Shop_ = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(konkaR3Shop_);
            if (null != konkaR3Shop_) {
                if (StringUtils.isNotBlank(konkaR3Shop_.getCustomer_type())) {// 判断是否是有客户类型
                    KonkaOrderAuditProcess process = new KonkaOrderAuditProcess();
                    process.getMap().put("par_add_dept_id", dept.getDept_id());
                    process.setCustomer_type(konkaR3Shop_.getCustomer_type());
                    process.setIs_del(0);
                    process.setIs_stop(0);
                    processList = super.getFacade().getKonkaOrderAuditProcessService().getKonkaOrderAuditProcessList(process);
                    if (null == processList || processList.size() == 0) {
                        // 没有分公司自定义的流程取统一流程，即分公司自定义流程
                        KonkaOrderAuditProcess ap_public = new KonkaOrderAuditProcess();
                        ap_public.setCustomer_type(konkaR3Shop_.getCustomer_type());
                        ap_public.setAdd_dept_id(0L);
                        ap_public.setIs_del(0);
                        ap_public.setIs_stop(0);
                        List<KonkaOrderAuditProcess> ap_publicauditProcesseList = getFacade().getKonkaOrderAuditProcessService().getKonkaOrderAuditProcessList(ap_public);
                        processList.addAll(ap_publicauditProcesseList);
                    }
                    if (null == processList || processList.size() == 0) {
                        // 没有分公司自定义的流程取统一流程，即分公司流程
                        KonkaOrderAuditProcess ap_public = new KonkaOrderAuditProcess();
                        ap_public.setCustomer_type(konkaR3Shop_.getCustomer_type());
                        ap_public.setAdd_dept_id(0L);
                        ap_public.setIs_del(0);
                        ap_public.setIs_stop(0);
                        List<KonkaOrderAuditProcess> ap_publicauditProcesseList = getFacade().getKonkaOrderAuditProcessService().getKonkaOrderAuditProcessList(ap_public);
                        processList.addAll(ap_publicauditProcesseList);
                    }
                    if (null == processList || processList.size() == 0) {
                        // 没有分公司流程取统一流程，即总公司流程
                        KonkaOrderAuditProcess ap_public = new KonkaOrderAuditProcess();
                        ap_public.getMap().put("par_add_dept_id", dept.getDept_id());
                        ap_public.setIs_del(0);
                        ap_public.setIs_stop(0);
                        ap_public.getMap().put("customer_type_is_null", true);
                        List<KonkaOrderAuditProcess> ap_publicauditProcesseList = getFacade().getKonkaOrderAuditProcessService().getKonkaOrderAuditProcessList(ap_public);
                        processList.addAll(ap_publicauditProcesseList);
                    }

                    if (null == processList || processList.size() == 0) {
                        // 没有分公司自定义的流程取统一流程，即总公司自定义流程
                        KonkaOrderAuditProcess ap_public = new KonkaOrderAuditProcess();
                        ap_public.setAdd_dept_id(0L);
                        ap_public.setIs_del(0);
                        ap_public.setIs_stop(0);
                        ap_public.getMap().put("customer_type_is_null", true);
                        List<KonkaOrderAuditProcess> ap_publicauditProcesseList = getFacade().getKonkaOrderAuditProcessService().getKonkaOrderAuditProcessList(ap_public);
                        processList.addAll(ap_publicauditProcesseList);
                    }

                    request.setAttribute("customer_type", konkaR3Shop_.getCustomer_type());
                } else {
                    KonkaOrderAuditProcess process = new KonkaOrderAuditProcess();
                    process.getMap().put("par_add_dept_id", dept.getDept_id());
                    process.getMap().put("customer_type_is_null", true);
                    process.setIs_del(0);
                    process.setIs_stop(0);
                    processList = super.getFacade().getKonkaOrderAuditProcessService().getKonkaOrderAuditProcessList(process);
                    if (null == processList || processList.size() == 0) {
                        // 没有分公司统一流程，即分公司优先级高
                        KonkaOrderAuditProcess ap_public = new KonkaOrderAuditProcess();
                        ap_public.getMap().put("customer_type_is_null", true);
                        ap_public.setAdd_dept_id(0L);
                        ap_public.setIs_del(0);
                        ap_public.setIs_stop(0);
                        List<KonkaOrderAuditProcess> ap_publicauditProcesseList = getFacade().getKonkaOrderAuditProcessService().getKonkaOrderAuditProcessList(ap_public);
                        processList.addAll(ap_publicauditProcesseList);
                    }
                }
            }
            request.setAttribute("processList", processList);
        }

        // 全部康佳产品
        PePdModel pePdModel = new PePdModel();
        pePdModel.setBrand_id(Constants.KONKA_BRAND_ID);
        pePdModel.setIs_del(0);
        List<PePdModel> pdList = getFacade().getPePdModelService().getPePdModelWithClsNameBrandNameList(pePdModel);

        request.setAttribute("pdList", pdList);

        dynaBean.set("userName", user.getReal_name());
        /** 取网点业务员 */
        BranchAssign bagn = new BranchAssign();
        bagn.setKonka_r3_id(Long.valueOf(cust_id));
        bagn = getFacade().getBranchAssignService().getBranchAssign(bagn);
        if (null != bagn && null != bagn.getUser_id()) {
            PeProdUser ywy = new PeProdUser();
            ywy.setId(bagn.getUser_id());
            ywy = getFacade().getPeProdUserService().getPeProdUserResult(ywy);
            request.setAttribute("ywy_user_name", ywy.getReal_name());
        }

        // 进货登记单
        String order_id = (String) dynaBean.get("order_id");
        KonkaOrderInfo konkaOrderInfo = new KonkaOrderInfo();
        konkaOrderInfo.setId(Long.valueOf(order_id));
        konkaOrderInfo = getFacade().getKonkaOrderInfoService().getKonkaOrderInfo(konkaOrderInfo);

        KonkaMobileTerminalFeedback kf = new KonkaMobileTerminalFeedback();
        kf.setTrade_index(konkaOrderInfo.getTrade_index());
        kf = super.getFacade().getKonkaMobileTerminalFeedbackService().getKonkaMobileTerminalFeedback(kf);
        if (kf != null) {
            dynaBean.set("content1", kf.getContent());
            dynaBean.set("tel1", kf.getTel());
            if (kf.getContent() != null) {
                dynaBean.set("show", true);
                dynaBean.set("t_id", kf.getId());
            }
        }

        // dynaBean.set("fullName",
        // super.getPIndexFullName(konkaOrderInfo.getUser_p_index()));
        dynaBean.set("fullName", super.getPIndexName(konkaOrderInfo.getUser_p_index(), ""));
        // dynaBean.set("order_id", order_id);
        // dynaBean.set("userName", konkaOrderInfo.getUser_name());
        // dynaBean.set("userShopName", konkaOrderInfo.getUser_shop_name());
        // dynaBean.set("userZip", konkaOrderInfo.getUser_zip());
        // dynaBean.set("userAddr", konkaOrderInfo.getUser_addr());
        // dynaBean.set("userTel", konkaOrderInfo.getUser_tel());
        // dynaBean.set("tradeIndex", konkaOrderInfo.getTrade_index());// 交易流水号
        // super.setprovinceAndcityAndcountryToFrom(dynaBean,
        // konkaOrderInfo.getUser_p_index());

        if (null != konkaOrderInfo.getUser_p_index()) {
            String user_index = konkaOrderInfo.getUser_p_index().toString();
            String province = StringUtils.substring(user_index, 0, 2).concat("0000");
            String city = StringUtils.substring(user_index, 0, 4).concat("00");
            String country = StringUtils.substring(user_index, 0, 6);
            String town = "";
            if (StringUtils.length(user_index) == 8) {
                town = user_index;
            }
            dynaBean.set("province", province);
            dynaBean.set("city", city);
            dynaBean.set("country", country);
            dynaBean.set("town", town);
        }

        KonkaOrderInfoDetails konkaOrderInfoDetails = new KonkaOrderInfoDetails();
        konkaOrderInfoDetails.setOrder_id(Long.valueOf(order_id));
        List<KonkaOrderInfoDetails> konkaOrderInfoDetailsList = getFacade().getKonkaOrderInfoDetailsService().getKonkaOrderInfoDetailsList(konkaOrderInfoDetails);
        request.setAttribute("konkaOrderInfoDetailsList", konkaOrderInfoDetailsList);

        String[] pd_ids = new String[konkaOrderInfoDetailsList.size()];
        for (int i = 0; i < konkaOrderInfoDetailsList.size(); i++) {
            pd_ids[i] = konkaOrderInfoDetailsList.get(i).getPd_id().toString();
        }
        dynaBean.set("pd_ids", StringUtils.join(pd_ids, ","));

        String fgsSN = konkaR3Shop.getBranch_area_name_2();
        String fgsSN2 = konkaR3Shop.getR3_sales_org_code();
        request.setAttribute("sales_org", StringUtils.isBlank(fgsSN2) ? fgsSN : fgsSN2);

        if (super.isCallR3Interface(request)) {
            // List<KNVP> knvpList =
            // super.getFacade().getR3WebInterfaceService().getKnvpsList(fgsSN,
            // Constants.default_vtweg, Constants.default_spart,
            // konkaR3Shop.getR3_code());
            //
            List<KNVP> knvpList = new ArrayList<KNVP>();
            ReturnInfo<KNVP> info = new ReturnInfo<KNVP>();
            info = super.getFacade().getR3WebInterfaceService().getKnvpsList(fgsSN, Constants.default_vtweg, Constants.default_spart, konkaR3Shop.getR3_code());
            if (info.getSuccess() == true) {
                knvpList = info.getDataResult();
            }

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

            request.setAttribute("agList", agList);
            request.setAttribute("reList", reList);
            request.setAttribute("rgList", rgList);
            request.setAttribute("weList", weList);

            if (agList.size() == 0) {
                request.setAttribute("ag", konkaR3Shop.getR3_code());
            }
            if (reList.size() == 0) {
                request.setAttribute("re", konkaR3Shop.getR3_code());
            }
            if (rgList.size() == 0) {
                request.setAttribute("rg", konkaR3Shop.getR3_code());
            }
            if (weList.size() == 0) {
                KonkaMobileImpStore s = new KonkaMobileImpStore();
                s.setR3_shdf_sn(konkaR3Shop.getR3_code());
                List<KonkaMobileImpStore> sList = super.getFacade().getKonkaMobileImpStoreService().getKonkaMobileImpStoreListForDistinctSdf(s);

                List<KNVP> __weList = new ArrayList<KNVP>();
                for (KonkaMobileImpStore cur : sList) {
                    KNVP k = new KNVP();
                    k.setKUNN2(cur.getR3_sdf_sn());
                    __weList.add(k);
                }
                request.setAttribute("weList", __weList);
                if (__weList.size() == 0) {
                    request.setAttribute("we", konkaR3Shop.getR3_code());
                }
            }
        } else {
            request.setAttribute("ag", konkaR3Shop.getR3_code());
            request.setAttribute("re", konkaR3Shop.getR3_code());
            request.setAttribute("rg", konkaR3Shop.getR3_code());

            KonkaMobileImpStore s = new KonkaMobileImpStore();
            s.setR3_shdf_sn(konkaR3Shop.getR3_code());
            List<KonkaMobileImpStore> sList = super.getFacade().getKonkaMobileImpStoreService().getKonkaMobileImpStoreListForDistinctSdf(s);

            List<KNVP> weList = new ArrayList<KNVP>();
            for (KonkaMobileImpStore cur : sList) {
                KNVP k = new KNVP();
                k.setKUNN2(cur.getR3_sdf_sn());
                weList.add(k);
            }
            request.setAttribute("weList", weList);
        }
        super.copyProperties(form, konkaOrderInfo);

        Attachment attachment = new Attachment();
        attachment.setLink_id(Long.valueOf(order_id));
        attachment.setDel_mark(Short.valueOf("0"));
        List<Attachment> attachmentList = getFacade().getAttachmentService().getAttachmentList(attachment);
        request.setAttribute("attachmentList", attachmentList);

        request.setAttribute("call_r3_interface", super.isCallR3Interface(request));

        request.setAttribute("cust_id", cust_id);
        request.setAttribute("order_id", order_id);
        if (konkaOrderInfo != null && konkaOrderInfo.getDoc_type().equals("ZFRE")) {// 判断如果是退货订单
            return new ActionForward("/../customer/JxcKonkaOrderRegister/inputTH.jsp");
        }
        return new ActionForward("/../customer/JxcKonkaOrderRegister/input.jsp");
    }

    @SuppressWarnings("unchecked")
    public ActionForward viewOrder(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        setNaviStringToRequestScope(form, request);
        // 设置订单进度条
        setOrderProgress(form, request);
        DynaBean dynaBean = (DynaBean) form;
        String order_id = (String) dynaBean.get("order_id");

        HttpSession session = request.getSession();
        PeProdUser user = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);
        if (null == user || StringUtils.isBlank(order_id)) {
            super.renderJavaScript(response, "alert('登录超时请重新登录。');history.back();");
            return null;
        }

        KonkaR3Shop konkaR3Shop = new KonkaR3Shop();
        konkaR3Shop.setId(user.getCust_id());
        konkaR3Shop = getFacade().getKonkaR3ShopService().getKonkaR3Shop(konkaR3Shop);
        if (null == konkaR3Shop) {
            super.renderJavaScript(response, "alert('未查询到网点！');history.back();");
            return null;
        }
        dynaBean.set("r3_code", konkaR3Shop.getR3_code());

        String fgsSN = konkaR3Shop.getBranch_area_name_2();
        String fgsSN2 = konkaR3Shop.getR3_sales_org_code();
        request.setAttribute("sales_org", StringUtils.isBlank(fgsSN2) ? fgsSN : fgsSN2);

        KonkaOrderInfo konkaOrderInfo = new KonkaOrderInfo();
        konkaOrderInfo.setId(Long.valueOf(order_id));
        konkaOrderInfo = getFacade().getKonkaOrderInfoService().getKonkaOrderInfo(konkaOrderInfo);
        if (null == konkaOrderInfo) {
            super.renderJavaScript(response, "alert('未查询到订单！');history.back();");
            return null;
        }

        KonkaMobileTerminalFeedback kf = new KonkaMobileTerminalFeedback();
        kf.setTrade_index(konkaOrderInfo.getTrade_index());
        kf = super.getFacade().getKonkaMobileTerminalFeedbackService().getKonkaMobileTerminalFeedback(kf);
        if (kf != null) {
            dynaBean.set("f_content", kf.getContent());
            dynaBean.set("f_tel", kf.getTel());
            dynaBean.set("f_add_date", kf.getAdd_date());
            dynaBean.set("f_person", kf.getQuestion_person());
            request.setAttribute("feed_id", kf.getId());
            if (kf.getContent() != null) {
                dynaBean.set("show", true);
                dynaBean.set("t_id", kf.getId());
            }
        }

        KonkaOrderInfoAudit konkaOrderInfoAudit = new KonkaOrderInfoAudit();
        konkaOrderInfoAudit.setLink_id(Long.valueOf(order_id));
        List<KonkaOrderInfoAudit> konkaOrderInfoAuditWithRoleInfoList = getFacade().getKonkaOrderInfoAuditService().getKonkaOrderInfoAuditAndRoleList(konkaOrderInfoAudit);
        if (null != konkaOrderInfoAuditWithRoleInfoList && konkaOrderInfoAuditWithRoleInfoList.size() > 0) {
            konkaOrderInfo.setKonkaOrderInfoAuditList(konkaOrderInfoAuditWithRoleInfoList);
        }
        // 审核流程列表
        KonkaOrderAuditProcessNode node = new KonkaOrderAuditProcessNode();
        node.setAudit_proces_id(konkaOrderInfo.getProcess_id());
        List<KonkaOrderAuditProcessNode> nodeList = super.getFacade().getKonkaOrderAuditProcessNodeService().getKonkaOrderAuditProcessNodeList(node);
        request.setAttribute("nodeList", nodeList);

        super.copyProperties(form, konkaOrderInfo);

        // dynaBean.set("fullName",
        // super.getPIndexFullName(konkaOrderInfo.getUser_p_index()));
        dynaBean.set("fullName", super.getPIndexName(konkaOrderInfo.getUser_p_index(), ""));
        KonkaOrderInfoDetails konkaOrderInfoDetails = new KonkaOrderInfoDetails();
        konkaOrderInfoDetails.setOrder_id(Long.valueOf(order_id));
        List<KonkaOrderInfoDetails> konkaOrderInfoDetailsList = getFacade().getKonkaOrderInfoDetailsService().getKonkaOrderInfoDetailsList(konkaOrderInfoDetails);
        request.setAttribute("konkaOrderInfoDetailsList", konkaOrderInfoDetailsList);

        // 取订单流程
        KonkaDept dept = super.getKonkaDeptByCustomerId(Long.valueOf(user.getCust_id()));
        KonkaOrderAuditProcess ap = new KonkaOrderAuditProcess();
        ap.setAdd_dept_id(dept.getDept_id());
        ap.setIs_del(0);
        List<KonkaOrderAuditProcess> auditProcesseList = getFacade().getKonkaOrderAuditProcessService().getKonkaOrderAuditProcessList(ap);

        if (konkaOrderInfo != null && konkaOrderInfo.getDoc_type().equals("ZFRE")) {// 判断如果是退货订单
            KonkaOrderAuditProcess ap_public = new KonkaOrderAuditProcess();
            ap_public.setAdd_dept_id(0L);
            List<KonkaOrderAuditProcess> ap_publicauditProcesseList = getFacade().getKonkaOrderAuditProcessService().getKonkaOrderAuditProcessList(ap_public);
            auditProcesseList.addAll(ap_publicauditProcesseList);
            request.setAttribute("processList", auditProcesseList);
        } else {
            KonkaOrderAuditProcess ap_public = new KonkaOrderAuditProcess();
            ap_public.setAdd_dept_id(0L);
            ap_public.setIs_del(0);
            List<KonkaOrderAuditProcess> ap_publicauditProcesseList = getFacade().getKonkaOrderAuditProcessService().getKonkaOrderAuditProcessList(ap_public);
            auditProcesseList.addAll(ap_publicauditProcesseList);
            request.setAttribute("processList", auditProcesseList);
        }

        dynaBean.set("userName", user.getReal_name());
        /** 取网点业务员 */
        BranchAssign bagn = new BranchAssign();
        bagn.setKonka_r3_id(user.getCust_id());
        bagn = getFacade().getBranchAssignService().getBranchAssign(bagn);
        if (null != bagn && null != bagn.getUser_id()) {
            PeProdUser ywy = new PeProdUser();
            ywy.setId(bagn.getUser_id());
            ywy = getFacade().getPeProdUserService().getPeProdUserResult(ywy);
            request.setAttribute("ywy_user_name", ywy.getReal_name());
        }

        if (super.isCallR3Interface(request)) {
            List<KNVP> knvpList = new ArrayList<KNVP>();
            ReturnInfo<KNVP> info = new ReturnInfo<KNVP>();
            info = super.getFacade().getR3WebInterfaceService().getKnvpsList(fgsSN, Constants.default_vtweg, Constants.default_spart, konkaR3Shop.getR3_code());
            if (info.getSuccess() == true) {
                knvpList = info.getDataResult();
            }

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

            request.setAttribute("agList", agList);
            request.setAttribute("reList", reList);
            request.setAttribute("rgList", rgList);
            request.setAttribute("weList", weList);

            if (agList.size() == 0) {
                request.setAttribute("ag", konkaR3Shop.getR3_code());
            }
            if (reList.size() == 0) {
                request.setAttribute("re", konkaR3Shop.getR3_code());
            }
            if (rgList.size() == 0) {
                request.setAttribute("rg", konkaR3Shop.getR3_code());
            }
            if (weList.size() == 0) {
                KonkaMobileImpStore s = new KonkaMobileImpStore();
                s.setR3_shdf_sn(konkaR3Shop.getR3_code());
                List<KonkaMobileImpStore> sList = super.getFacade().getKonkaMobileImpStoreService().getKonkaMobileImpStoreListForDistinctSdf(s);

                List<KNVP> __weList = new ArrayList<KNVP>();
                for (KonkaMobileImpStore cur : sList) {
                    KNVP k = new KNVP();
                    k.setKUNN2(cur.getR3_sdf_sn());
                    __weList.add(k);
                }
                request.setAttribute("weList", __weList);
                if (__weList.size() == 0) {
                    request.setAttribute("we", konkaR3Shop.getR3_code());
                }
            }
        } else {
            request.setAttribute("ag", konkaR3Shop.getR3_code());
            request.setAttribute("re", konkaR3Shop.getR3_code());
            request.setAttribute("rg", konkaR3Shop.getR3_code());

            KonkaMobileImpStore s = new KonkaMobileImpStore();
            s.setR3_shdf_sn(konkaR3Shop.getR3_code());
            List<KonkaMobileImpStore> sList = super.getFacade().getKonkaMobileImpStoreService().getKonkaMobileImpStoreListForDistinctSdf(s);

            List<KNVP> weList = new ArrayList<KNVP>();
            for (KonkaMobileImpStore cur : sList) {
                KNVP k = new KNVP();
                k.setKUNN2(cur.getR3_sdf_sn());
                weList.add(k);
            }
            request.setAttribute("weList", weList);
        }

        Attachment attachment = new Attachment();
        attachment.setLink_id(Long.valueOf(order_id));
        attachment.setDel_mark(Short.valueOf("0"));
        List<Attachment> attachmentList = getFacade().getAttachmentService().getAttachmentList(attachment);
        request.setAttribute("attachmentList", attachmentList);

        KonkaOrderInfoUpdateRecord updateRecord = new KonkaOrderInfoUpdateRecord();
        updateRecord.setTrade_index(konkaOrderInfo.getTrade_index());
        List<KonkaOrderInfoUpdateRecord> updateRecordGroupList = super.getFacade().getKonkaOrderInfoUpdateRecordService().getKonkaOrderInfoUpdateRecordGroupList(updateRecord);

        if (null != updateRecordGroupList && updateRecordGroupList.size() > 0) {

            for (KonkaOrderInfoUpdateRecord temp : updateRecordGroupList) {
                KonkaOrderInfoUpdateRecord _record = new KonkaOrderInfoUpdateRecord();
                _record.setAdd_date(temp.getAdd_date());
                _record.setTrade_index(konkaOrderInfo.getTrade_index());
                _record.getMap().put("order_by_pd_id_asc", true);
                List<KonkaOrderInfoUpdateRecord> recordList = super.getFacade().getKonkaOrderInfoUpdateRecordService().getKonkaOrderInfoUpdateRecordList(_record);
                for (KonkaOrderInfoUpdateRecord rr : recordList) {
                    Double aa = rr.getDiscount_after().doubleValue() / rr.getPrice_after().doubleValue() * 10000 / 100;
                    if (aa.isNaN()) {
                        aa = 0.0;
                    }
                    BigDecimal bb = new BigDecimal(aa);
                    rr.setGood_discount_after(bb);

                }
                temp.getMap().put("recordList", recordList);
            }

            request.setAttribute("updateRecordGroupList", updateRecordGroupList);

            long good_counts = 0;
            double good_total_price = 0.00;
            if (null != updateRecordGroupList.get(0).getMap().get("recordList")) {
                List<KonkaOrderInfoUpdateRecord> applyRecordList = (ArrayList<KonkaOrderInfoUpdateRecord>) updateRecordGroupList.get(0).getMap().get("recordList");
                List<KonkaOrderInfoDetails> applyDetailsList = new ArrayList<KonkaOrderInfoDetails>();
                for (KonkaOrderInfoUpdateRecord temp : applyRecordList) {
                    KonkaOrderInfoDetails details = new KonkaOrderInfoDetails();
                    details.setPd_name(temp.getPd_name());
                    details.setGood_count(Integer.valueOf(temp.getNum_before().toString()));
                    details.setGood_price(temp.getPrice_before());
                    details.setGood_discount_price(temp.getDiscount_before());
                    applyDetailsList.add(details);
                    good_counts += temp.getNum_before();
                    good_total_price += (temp.getPrice_before().doubleValue()) * temp.getNum_before();
                }
                request.setAttribute("good_counts", good_counts);
                request.setAttribute("good_total_price", good_total_price);
                request.setAttribute("applyDetailsList", applyDetailsList);
            }

        } else {
            KonkaOrderInfoDetails details = new KonkaOrderInfoDetails();
            details.setOrder_id(Long.valueOf(order_id));
            details.getMap().put("order_by_pd_id_asc", true);
            List<KonkaOrderInfoDetails> applyDetailsList = super.getFacade().getKonkaOrderInfoDetailsService().getKonkaOrderInfoDetailsList(details);

            int good_counts = 0;
            double good_total_price = 0.00;
            for (KonkaOrderInfoDetails konkaOrderInfoDetails2 : applyDetailsList) {
                good_counts += konkaOrderInfoDetails2.getGood_count();
                good_total_price += (konkaOrderInfoDetails2.getGood_price().doubleValue()) * konkaOrderInfoDetails2.getGood_count();
            }
            request.setAttribute("good_counts", good_counts);
            request.setAttribute("good_total_price", good_total_price);
            request.setAttribute("applyDetailsList", applyDetailsList);

        }
        if (konkaOrderInfo != null && konkaOrderInfo.getDoc_type().equals("ZFRE")) {
            return new ActionForward("/../customer/JxcKonkaOrderRegister/viewReturnOrder.jsp");
        }

        return new ActionForward("/../customer/JxcKonkaOrderRegister/viewOrder.jsp");
    }

    public ActionForward openiframe(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        setNaviStringToRequestScope(form, request);
        DynaBean dynaBean = (DynaBean) form;
        String t_id = (String) dynaBean.get("t_id");

        KonkaMobileTerminalFeedback kf = new KonkaMobileTerminalFeedback();
        kf.setId(Long.valueOf(t_id));
        kf = super.getFacade().getKonkaMobileTerminalFeedbackService().getKonkaMobileTerminalFeedback(kf);
        if (null == kf) {
            super.renderJavaScript(response, "window.onload=function(){alert('数据异常！');history.back();}");
            return null;
        }
        // dynaBean.set("feed_id", kf.getId());
        // super.renderText(response, "1");
        request.setAttribute("feed_id", kf.getId());
        dynaBean.set("f_content", kf.getContent());
        dynaBean.set("f_tel", kf.getTel());
        dynaBean.set("f_add_date", kf.getAdd_date());
        dynaBean.set("f_person", kf.getQuestion_person());

        return new ActionForward("/../customer/JxcKonkaOrderRegister/iframe.jsp");
    }

    public ActionForward ajaxmessage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        DynaBean dynaBean = (DynaBean) form;
        String feed_id = (String) dynaBean.get("feed_id");
        String f_id = (String) dynaBean.get("f_id");

        logger.info("feed_id--->" + feed_id);
        logger.info("f_id--->" + f_id);

        KonkaMobileTerminalFbBack entity = new KonkaMobileTerminalFbBack();
        if (StringUtils.isBlank(feed_id) || !GenericValidator.isLong(feed_id)) {
            super.renderJavaScript(response, "window.onload=function(){alert('数据异常！');history.back();}");
            return null;
        }
        entity.setFeed_id(Long.valueOf(feed_id));
        entity.getMap().put("order_by_id_asc", true);

        List<KonkaMobileTerminalFbBack> entityList = new ArrayList<KonkaMobileTerminalFbBack>();
        List<KonkaMobileTerminalFbBack> entityList1 = super.getFacade().getKonkaMobileTerminalFbBackService().getKonkaMobileTerminalFbBackList(entity);
        for (KonkaMobileTerminalFbBack konkaMobileTerminalFbBack : entityList1) {
            if (konkaMobileTerminalFbBack.getId() > Long.valueOf(f_id)) {
                entityList.add(konkaMobileTerminalFbBack);
            }
        }

        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 封装成JSON字符串
        StringBuffer json = new StringBuffer("{");

        StringBuffer jsonBuffer = new StringBuffer();
        if (null != entityList && entityList.size() > 0) {
            for (KonkaMobileTerminalFbBack er : entityList) {
                jsonBuffer.append("{");
                jsonBuffer.append("\"add_date\":\"").append(sf.format(er.getAdd_date()));
                jsonBuffer.append("\",\"content\":\"").append(er.getContent());
                jsonBuffer.append("\",\"question_person\":\"").append(er.getQuestion_person());
                jsonBuffer.append("\",\"id\":\"").append(er.getId());
                jsonBuffer.append("\"},");
            }
            String listStr = StringUtils.removeEnd(jsonBuffer.toString(), ",");
            json.append("\"list\" : [").append(listStr).append("]");
        }
        json.append("}");

        logger.info("josn---->{}" + json);
        response.setContentType("application/json;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
        out.close();
        return null;
    }

    public ActionForward ajaxSave(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        DynaBean dynaBean = (DynaBean) form;
        String content = (String) dynaBean.get("content");
        String feed_id = (String) dynaBean.get("feed_id");

        HttpSession session = request.getSession();
        PeProdUser user = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);
        if (null == user) {
            return null;
        }

        KonkaMobileTerminalFbBack entity = new KonkaMobileTerminalFbBack();
        entity.setAdd_date(new Date());
        entity.setFeed_id(Long.valueOf(feed_id));
        entity.setContent(content);
        entity.setQuestion_person(user.getReal_name());
        entity.setQuestion_id(user.getId());
        super.getFacade().getKonkaMobileTerminalFbBackService().createKonkaMobileTerminalFbBack(entity);
        super.renderText(response, "1");
        return null;
    }

    /** 撤回订单 */
    public ActionForward withdrawals(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ParseException {
        DynaBean dynaBean = (DynaBean) form;
        String mod_id = (String) dynaBean.get("mod_id");
        String cust_id = (String) dynaBean.get("cust_id");
        String order_id = (String) dynaBean.get("order_id");
        String process_id = (String) dynaBean.get("process_id");
        String FROMSALESMAN = (String) dynaBean.get("FROMSALESMAN"); // 状态位，标识是否是业务员提交
        String CUSTID = (String) dynaBean.get("CUSTID"); // 业务提交请求的客户ID

        // System.out.println("FROMSALESMAN==" + FROMSALESMAN + "     CUSTID===" + CUSTID);

        if (!GenericValidator.isLong(order_id) || !GenericValidator.isLong(cust_id)) {
            super.renderJavaScript(response, "alert('" + super.getMessage(request, "errors.parm") + "');history.back();");
            return null;
        }

        HttpSession session = request.getSession();
        PeProdUser user = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);
        if (null == user || StringUtils.isBlank(order_id)) {
            super.renderJavaScript(response, "alert('登录超时请重新登录。');history.back();");
            return null;
        }

        // 处理数据
        KonkaOrderInfo konkaOrderInfo = new KonkaOrderInfo();
        konkaOrderInfo.setId(Long.valueOf(order_id));
        konkaOrderInfo.setCust_id(Long.valueOf(cust_id));
        konkaOrderInfo.setAudit_state(0);
        konkaOrderInfo.setIs_submit(0);

        // 下面的这段代码是用来重置订单的“是否被修改”状态，这个工作在管理端“驳回”到客户的操作也需要做
        // 审批结果：1同意，-1驳回，0-驳回（到客户，重新制单）， -9 撤回（客户在客户端操作）
        // 当订单被驳回到重新制单后，重置“订单修改标识”
        konkaOrderInfo.setKh_confirm_state(0);

        if (GenericValidator.isLong(process_id)) {
            KonkaOrderAuditProcessNode node = super.getNextProcessNode(Long.valueOf(process_id), null);
            konkaOrderInfo.setNext_audit_role_id(node.getRole_id());
            konkaOrderInfo.setNext_node_id(node.getId());
        }
        // konkaOrderInfo.getMap().put("withdrawals", "true");
        super.getFacade().getKonkaOrderInfoService().modifyKonkaOrderInfo(konkaOrderInfo);
        saveMessage(request, "withdrawals.success");

        // 添加撤回信息
        KonkaOrderInfoAudit entity = new KonkaOrderInfoAudit();
        entity.setLink_id(Long.valueOf(order_id));
        entity.setAudit_level(-9);
        entity.setAudit_type(0);// 审批类别：0审批，1会签
        entity.setAudit_user_id(user.getId());
        entity.setAudit_user(user.getUser_name());
        entity.setAudit_dept_id(user.getDept_id());
        entity.setNext_node_id(konkaOrderInfo.getNext_node_id());
        entity.setAudit_result(-9);
        entity.setCur_node_id(null);
        entity.setAudit_datetime(new Date());
        super.getFacade().getKonkaOrderInfoAuditService().createKonkaOrderInfoAudit(entity);

        // the line below is added for pagination
        StringBuffer pathBuffer = new StringBuffer();
        pathBuffer.append(mapping.findForward("success").getPath());
        pathBuffer.append("&mod_id=" + mod_id);
        if (StringUtils.isNotBlank(FROMSALESMAN)) {
            pathBuffer.append("&FROMSALESMAN=" + FROMSALESMAN);
        }
        if (StringUtils.isNotBlank(CUSTID)) {
            pathBuffer.append("&CUSTID=" + CUSTID);
        }
        pathBuffer.append("&");
        // System.out.println("buffer===" + pathBuffer.toString());
        ActionForward forward = new ActionForward(pathBuffer.toString(), true);
        // end
        return forward;
    }

    /** 删除订单 */
    public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info(1);
        DynaBean dynaBean = (DynaBean) form;
        String mod_id = (String) dynaBean.get("mod_id");
        String cust_id = (String) dynaBean.get("cust_id");
        String order_id = (String) dynaBean.get("order_id");
        String is_th = (String) dynaBean.get("is_th");// 为1 的时候是退货
        String FROMSALESMAN = (String) dynaBean.get("FROMSALESMAN"); // 状态位，标识是否是业务员提交
        String CUSTID = (String) dynaBean.get("CUSTID"); // 业务提交请求的客户ID

        if (!GenericValidator.isLong(order_id) || !GenericValidator.isLong(cust_id)) {
            super.renderJavaScript(response, "alert('" + super.getMessage(request, "errors.parm") + "');history.back();");
            return null;
        }

        HttpSession session = request.getSession();
        PeProdUser user = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);
        if (null == user || StringUtils.isBlank(order_id)) {
            super.renderJavaScript(response, "alert('登录超时请重新登录。');history.back();");
            return null;
        }

        // 处理数据
        KonkaOrderInfo konkaOrderInfo = new KonkaOrderInfo();
        konkaOrderInfo.setId(Long.valueOf(order_id));
        konkaOrderInfo.setCust_id(Long.valueOf(cust_id));
        konkaOrderInfo.setIs_del(1);
        super.getFacade().getKonkaOrderInfoService().modifyKonkaOrderInfo(konkaOrderInfo);
        saveMessage(request, "entity.deleted");

        // the line below is added for pagination
        StringBuffer pathBuffer = new StringBuffer();
        pathBuffer.append(mapping.findForward("success").getPath());

        if (is_th != null && is_th.equals("1")) {
            pathBuffer.append("&is_th=1");
        }
        if (StringUtils.isNotBlank(FROMSALESMAN)) {
            pathBuffer.append("&FROMSALESMAN=" + FROMSALESMAN);
        }
        if (StringUtils.isNotBlank(CUSTID)) {
            pathBuffer.append("&CUSTID=" + CUSTID);
        }
        pathBuffer.append("&");
        pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "order_id", "method")));
        ActionForward forward = new ActionForward(pathBuffer.toString(), true);
        // end
        log.info(forward.getPath());
        return forward;
    }

    /** 确认订单 */
    public ActionForward confirmOrder(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ParseException {
        DynaBean dynaBean = (DynaBean) form;
        String mod_id = (String) dynaBean.get("mod_id");
        String order_id = (String) dynaBean.get("order_id");
        String cust_id = (String) dynaBean.get("cust_id");
        String is_th = (String) dynaBean.get("is_th");// 为1 的时候是退货
        String FROMSALESMAN = (String) dynaBean.get("FROMSALESMAN"); // 状态位，标识是否是业务员提交
        String CUSTID = (String) dynaBean.get("CUSTID"); // 业务提交请求的客户ID

        if (!GenericValidator.isLong(order_id)) {
            super.renderJavaScript(response, "alert('" + super.getMessage(request, "errors.parm") + "');history.back();");
            return null;
        }

        HttpSession session = request.getSession();
        PeProdUser user = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);
        if (null == user || StringUtils.isBlank(order_id)) {
            super.renderJavaScript(response, "alert('登录超时请重新登录。');history.back();");
            return null;
        }

        // 处理数据
        KonkaOrderInfo konkaOrderInfo = new KonkaOrderInfo();
        konkaOrderInfo.setId(Long.valueOf(order_id));
        // -1订单已被修改（等待客户确认），
        // 0订单未被修改（初始状态），
        // 1客户已确认.此状态在客户撤回后需要重置

        konkaOrderInfo.setKh_confirm_state(1);
        konkaOrderInfo.setKh_confirm_date(new Date());
        if (StringUtils.isNotBlank(cust_id)) {
            konkaOrderInfo.setCust_id(Long.valueOf(cust_id));
        }
        super.getFacade().getKonkaOrderInfoService().modifyKonkaOrderInfo(konkaOrderInfo);
        saveMessage(request, "entity.updated");

        // the line below is added for pagination
        StringBuffer pathBuffer = new StringBuffer();
        pathBuffer.append(mapping.findForward("success").getPath());
        pathBuffer.append("&mod_id=" + mod_id);
        if (StringUtils.isNotBlank(FROMSALESMAN)) {
            pathBuffer.append("&FROMSALESMAN=" + FROMSALESMAN);
        }
        if (StringUtils.isNotBlank(CUSTID)) {
            pathBuffer.append("&CUSTID=" + CUSTID);
        }
        if (is_th != null && is_th.equals("1")) {
            pathBuffer.append("&is_th=1");
        }
        pathBuffer.append("&");
        ActionForward forward = new ActionForward(pathBuffer.toString(), true);
        // end
        return forward;
    }

    public ActionForward confirmOrderOnMobile(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ParseException {
        DynaBean dynaBean = (DynaBean) form;
        String mod_id = (String) dynaBean.get("mod_id");
        String order_id = (String) dynaBean.get("order_id");
        String cust_id = (String) dynaBean.get("cust_id");

        if (!GenericValidator.isLong(order_id)) {
            super.renderJavaScript(response, "alert('" + super.getMessage(request, "errors.parm") + "');history.back();");
            return null;
        }

        HttpSession session = request.getSession();
        PeProdUser user = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);
        if (null == user || StringUtils.isBlank(order_id)) {
            super.renderJavaScript(response, "alert('登录超时请重新登录。');history.back();");
            return null;
        }

        // 处理数据
        KonkaOrderInfo konkaOrderInfo = new KonkaOrderInfo();
        konkaOrderInfo.setId(Long.valueOf(order_id));
        // -1订单已被修改（等待客户确认），
        // 0订单未被修改（初始状态），
        // 1客户已确认.此状态在客户撤回后需要重置
        konkaOrderInfo.setKh_confirm_state(1);
        konkaOrderInfo.setKh_confirm_date(new Date());
        if (StringUtils.isNotBlank(cust_id)) {
            konkaOrderInfo.setCust_id(Long.valueOf(cust_id));
        }
        super.getFacade().getKonkaOrderInfoService().modifyKonkaOrderInfo(konkaOrderInfo);
        saveMessage(request, "entity.updated");

        // the line below is added for pagination
        StringBuffer pathBuffer = new StringBuffer();
        pathBuffer.append("/../customer/manager/JxcKonkaOrderRegister.do?method=listInMobile");
        pathBuffer.append("&mod_id=" + mod_id);
        pathBuffer.append("&");
        ActionForward forward = new ActionForward(pathBuffer.toString(), true);
        // end
        return forward;
    }

    /** 复制订单 */
    public ActionForward copy(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        setNaviStringToRequestScope(form, request);
        DynaBean dynaBean = (DynaBean) form;
        String cust_id = (String) dynaBean.get("cust_id");

        String FROMSALESMAN = (String) dynaBean.get("FROMSALESMAN"); // 状态位，标识是否是业务员提交
        String CUSTID = (String) dynaBean.get("CUSTID"); // 业务提交请求的客户ID
        if (StringUtils.isNotBlank(FROMSALESMAN)) {
            request.setAttribute("FROMSALESMAN", FROMSALESMAN);
        }
        if (StringUtils.isNotBlank(CUSTID)) {
            request.setAttribute("CUSTID", CUSTID);
        }

        HttpSession session = request.getSession();
        PeProdUser user = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);
        if (null == user || StringUtils.isBlank(cust_id)) {
            super.renderJavaScript(response, "alert('登录超时请重新登录。');history.back();");
            return null;
        } else if (null == user.getCust_id()) {
            return null;
        }

        KonkaR3Shop konkaR3Shop = new KonkaR3Shop();
        konkaR3Shop.setId(user.getCust_id());
        konkaR3Shop = getFacade().getKonkaR3ShopService().getKonkaR3Shop(konkaR3Shop);
        if (null == konkaR3Shop) {
            return null;
        }

        // 取收货地址
        KonkaJxcBaseAddr konkaJxcBaseAddr = new KonkaJxcBaseAddr();
        konkaJxcBaseAddr.setIs_del(0);

        KonkaR3Shop r3shop = new KonkaR3Shop();
        r3shop.setId(user.getCust_id());
        r3shop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(r3shop);
        if (null != r3shop) {
            konkaJxcBaseAddr.setR3_id(r3shop.getId());
            konkaJxcBaseAddr.setR3_code(r3shop.getR3_code());
        }

        List<KonkaJxcBaseAddr> konkaJxcBaseAddrList = super.getFacade().getKonkaJxcBaseAddrService().getKonkaJxcBaseAddrList(konkaJxcBaseAddr);
        request.setAttribute("konkaJxcBaseAddrList", konkaJxcBaseAddrList);

        dynaBean.set("brandId", Constants.KONKA_BRAND_ID);
        Date today = new Date();
        dynaBean.set("today", DateFormatUtils.format(today, "yyyy-MM-dd hh:mm:ss"));// 订单日期
        dynaBean.set("r3_code", konkaR3Shop.getR3_code());

        // 取R3网点分公司ID
        KonkaR3Shop krs = new KonkaR3Shop();
        krs.setId(Long.valueOf(cust_id));
        krs = getFacade().getKonkaR3ShopService().getKonkaR3ShopWithBranchAssign(krs);
        if (null != krs) {
            BigDecimal deptId = new BigDecimal(krs.getMap().get("fgs_id").toString());
            if (null != deptId && deptId.intValue() == -1) {// deptId=-1l,说明没有找到上级分公司
                request.setAttribute("confirm", 1);
            }
        }

        // 取订单流程
        // KonkaDept dept =
        // super.getKonkaDeptByCustomerId(Long.valueOf(cust_id));
        // KonkaOrderAuditProcess ap = new KonkaOrderAuditProcess();
        // ap.setAdd_dept_id(dept.getDept_id());
        // ap.setIs_del(0);
        // List<KonkaOrderAuditProcess> auditProcesseList =
        // getFacade().getKonkaOrderAuditProcessService()
        // .getKonkaOrderAuditProcessList(ap);
        //
        // KonkaOrderAuditProcess ap_public = new KonkaOrderAuditProcess();
        // ap_public.setAdd_dept_id(0L);
        // ap_public.setIs_del(0);
        // List<KonkaOrderAuditProcess> ap_publicauditProcesseList =
        // getFacade().getKonkaOrderAuditProcessService()
        // .getKonkaOrderAuditProcessList(ap_public);
        // auditProcesseList.addAll(ap_publicauditProcesseList);
        // request.setAttribute("processList", auditProcesseList);

        // 获取流程列表
        KonkaDept kd = super.getKonkaDeptByCustomerId(Long.valueOf(cust_id));
        KonkaDept dept = null;
        if (kd != null) {
            dept = super.getSuperiorForDeptType(kd.getDept_id(), 3);
        }
        List<KonkaOrderAuditProcess> processList = new ArrayList<KonkaOrderAuditProcess>();
        if (dept != null) {

            KonkaR3Shop konkaR3Shop_ = new KonkaR3Shop();
            konkaR3Shop_.setId(Long.valueOf(cust_id));
            konkaR3Shop_.setIs_del(0l);
            konkaR3Shop_ = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(konkaR3Shop_);
            if (null != konkaR3Shop_) {
                if (StringUtils.isNotBlank(konkaR3Shop_.getCustomer_type())) {// 判断是否是有客户类型
                    KonkaOrderAuditProcess process = new KonkaOrderAuditProcess();
                    process.getMap().put("par_add_dept_id", dept.getDept_id());
                    process.setCustomer_type(konkaR3Shop_.getCustomer_type());
                    process.setIs_del(0);
                    process.setIs_stop(0);
                    processList = super.getFacade().getKonkaOrderAuditProcessService().getKonkaOrderAuditProcessList(process);
                    if (null == processList || processList.size() == 0) {
                        // 没有分公司自定义的流程取统一流程，即分公司自定义流程
                        KonkaOrderAuditProcess ap_public = new KonkaOrderAuditProcess();
                        ap_public.setCustomer_type(konkaR3Shop_.getCustomer_type());
                        ap_public.setAdd_dept_id(0L);
                        ap_public.setIs_del(0);
                        ap_public.setIs_stop(0);
                        List<KonkaOrderAuditProcess> ap_publicauditProcesseList = getFacade().getKonkaOrderAuditProcessService().getKonkaOrderAuditProcessList(ap_public);
                        processList.addAll(ap_publicauditProcesseList);
                    }
                    if (null == processList || processList.size() == 0) {
                        // 没有分公司自定义的流程取统一流程，即分公司流程
                        KonkaOrderAuditProcess ap_public = new KonkaOrderAuditProcess();
                        ap_public.setCustomer_type(konkaR3Shop_.getCustomer_type());
                        ap_public.setAdd_dept_id(0L);
                        ap_public.setIs_del(0);
                        ap_public.setIs_stop(0);
                        List<KonkaOrderAuditProcess> ap_publicauditProcesseList = getFacade().getKonkaOrderAuditProcessService().getKonkaOrderAuditProcessList(ap_public);
                        processList.addAll(ap_publicauditProcesseList);
                    }
                    if (null == processList || processList.size() == 0) {
                        // 没有分公司流程取统一流程，即总公司流程
                        KonkaOrderAuditProcess ap_public = new KonkaOrderAuditProcess();
                        ap_public.getMap().put("par_add_dept_id", dept.getDept_id());
                        ap_public.setIs_del(0);
                        ap_public.setIs_stop(0);
                        ap_public.getMap().put("customer_type_is_null", true);
                        List<KonkaOrderAuditProcess> ap_publicauditProcesseList = getFacade().getKonkaOrderAuditProcessService().getKonkaOrderAuditProcessList(ap_public);
                        processList.addAll(ap_publicauditProcesseList);
                    }

                    if (null == processList || processList.size() == 0) {
                        // 没有分公司自定义的流程取统一流程，即总公司自定义流程
                        KonkaOrderAuditProcess ap_public = new KonkaOrderAuditProcess();
                        ap_public.setAdd_dept_id(0L);
                        ap_public.setIs_del(0);
                        ap_public.setIs_stop(0);
                        ap_public.getMap().put("customer_type_is_null", true);
                        List<KonkaOrderAuditProcess> ap_publicauditProcesseList = getFacade().getKonkaOrderAuditProcessService().getKonkaOrderAuditProcessList(ap_public);
                        processList.addAll(ap_publicauditProcesseList);
                    }

                    request.setAttribute("customer_type", konkaR3Shop_.getCustomer_type());
                } else {
                    KonkaOrderAuditProcess process = new KonkaOrderAuditProcess();
                    process.getMap().put("par_add_dept_id", dept.getDept_id());
                    process.getMap().put("customer_type_is_null", true);
                    process.setIs_del(0);
                    process.setIs_stop(0);
                    processList = super.getFacade().getKonkaOrderAuditProcessService().getKonkaOrderAuditProcessList(process);
                    if (null == processList || processList.size() == 0) {
                        // 没有分公司统一流程，即分公司优先级高
                        KonkaOrderAuditProcess ap_public = new KonkaOrderAuditProcess();
                        ap_public.getMap().put("customer_type_is_null", true);
                        ap_public.setAdd_dept_id(0L);
                        ap_public.setIs_del(0);
                        ap_public.setIs_stop(0);
                        List<KonkaOrderAuditProcess> ap_publicauditProcesseList = getFacade().getKonkaOrderAuditProcessService().getKonkaOrderAuditProcessList(ap_public);
                        processList.addAll(ap_publicauditProcesseList);
                    }
                }
            }
            request.setAttribute("processList", processList);
        }

        // 全部康佳产品
        PePdModel pePdModel = new PePdModel();
        pePdModel.setBrand_id(Constants.KONKA_BRAND_ID);
        pePdModel.setIs_del(0);
        List<PePdModel> pdList = getFacade().getPePdModelService().getPePdModelWithClsNameBrandNameList(pePdModel);

        request.setAttribute("pdList", pdList);

        dynaBean.set("userName", user.getReal_name());
        /** 取网点业务员 */
        BranchAssign bagn = new BranchAssign();
        bagn.setKonka_r3_id(Long.valueOf(cust_id));
        bagn = getFacade().getBranchAssignService().getBranchAssign(bagn);
        if (null != bagn && null != bagn.getUser_id()) {
            PeProdUser ywy = new PeProdUser();
            ywy.setId(bagn.getUser_id());
            ywy = getFacade().getPeProdUserService().getPeProdUserResult(ywy);
            request.setAttribute("ywy_user_name", ywy.getReal_name());
        }

        // 进货登记单
        String order_id = (String) dynaBean.get("order_id");
        KonkaOrderInfo konkaOrderInfo = new KonkaOrderInfo();
        konkaOrderInfo.setId(Long.valueOf(order_id));
        konkaOrderInfo = getFacade().getKonkaOrderInfoService().getKonkaOrderInfo(konkaOrderInfo);
        konkaOrderInfo.setIs_submit(null);

        // dynaBean.set("fullName",
        // super.getPIndexFullName(konkaOrderInfo.getUser_p_index()));
        dynaBean.set("fullName", super.getPIndexName(konkaOrderInfo.getUser_p_index(), ""));

        // super.setprovinceAndcityAndcountryToFrom(dynaBean,
        // konkaOrderInfo.getUser_p_index());

        Long __user_index = konkaOrderInfo.getUser_p_index();
        if (null != __user_index) {
            String user_index = __user_index.toString();
            String province = StringUtils.substring(user_index, 0, 2).concat("0000");
            String city = StringUtils.substring(user_index, 0, 4).concat("00");
            String country = StringUtils.substring(user_index, 0, 6);
            String town = "";
            if (StringUtils.length(user_index) == 8) {
                town = user_index;
            }
            dynaBean.set("province", province);
            dynaBean.set("city", city);
            dynaBean.set("country", country);
            dynaBean.set("town", town);
        }

        KonkaOrderInfoDetails konkaOrderInfoDetails = new KonkaOrderInfoDetails();
        konkaOrderInfoDetails.setOrder_id(Long.valueOf(order_id));
        List<KonkaOrderInfoDetails> konkaOrderInfoDetailsList = getFacade().getKonkaOrderInfoDetailsService().getKonkaOrderInfoDetailsList(konkaOrderInfoDetails);
        request.setAttribute("konkaOrderInfoDetailsList", konkaOrderInfoDetailsList);

        String[] pd_ids = new String[konkaOrderInfoDetailsList.size()];
        for (int i = 0; i < konkaOrderInfoDetailsList.size(); i++) {
            pd_ids[i] = konkaOrderInfoDetailsList.get(i).getPd_id().toString();
        }
        dynaBean.set("pd_ids", StringUtils.join(pd_ids, ","));

        String fgsSN = konkaR3Shop.getBranch_area_name_2();
        request.setAttribute("sales_org", fgsSN);

        if (super.isCallR3Interface(request)) {
            List<KNVP> knvpList = new ArrayList<KNVP>();
            ReturnInfo<KNVP> info = new ReturnInfo<KNVP>();
            info = super.getFacade().getR3WebInterfaceService().getKnvpsList(fgsSN, Constants.default_vtweg, Constants.default_spart, konkaR3Shop.getR3_code());
            if (info.getSuccess() == true) {
                knvpList = info.getDataResult();
            }

            // AG:售达方
            // RE:出票方
            // RG:付款方
            // WE:送达方

            List<KNVP> agList = new ArrayList<KNVP>();
            List<KNVP> reList = new ArrayList<KNVP>();
            List<KNVP> rgList = new ArrayList<KNVP>();
            List<KNVP> weList = new ArrayList<KNVP>();

            if (null != knvpList) {
                // System.out.println("knvpList===" + knvpList.size());
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
            request.setAttribute("agList", agList);
            request.setAttribute("reList", reList);
            request.setAttribute("rgList", rgList);
            request.setAttribute("weList", weList);

            if (agList.size() == 0) {
                request.setAttribute("ag", konkaR3Shop.getR3_code());
            }
            if (reList.size() == 0) {
                request.setAttribute("re", konkaR3Shop.getR3_code());
            }
            if (rgList.size() == 0) {
                request.setAttribute("rg", konkaR3Shop.getR3_code());
            }
            if (weList.size() == 0) {
                KonkaMobileImpStore s = new KonkaMobileImpStore();
                s.setR3_shdf_sn(konkaR3Shop.getR3_code());
                List<KonkaMobileImpStore> sList = super.getFacade().getKonkaMobileImpStoreService().getKonkaMobileImpStoreListForDistinctSdf(s);

                List<KNVP> __weList = new ArrayList<KNVP>();
                for (KonkaMobileImpStore cur : sList) {
                    KNVP k = new KNVP();
                    k.setKUNN2(cur.getR3_sdf_sn());
                    __weList.add(k);
                }
                request.setAttribute("weList", __weList);
                if (__weList.size() == 0) {
                    request.setAttribute("we", konkaR3Shop.getR3_code());
                }
            }
        } else {
            request.setAttribute("ag", konkaR3Shop.getR3_code());
            request.setAttribute("re", konkaR3Shop.getR3_code());
            request.setAttribute("rg", konkaR3Shop.getR3_code());

            KonkaMobileImpStore s = new KonkaMobileImpStore();
            s.setR3_shdf_sn(konkaR3Shop.getR3_code());
            List<KonkaMobileImpStore> sList = super.getFacade().getKonkaMobileImpStoreService().getKonkaMobileImpStoreListForDistinctSdf(s);

            List<KNVP> weList = new ArrayList<KNVP>();
            for (KonkaMobileImpStore cur : sList) {
                KNVP k = new KNVP();
                k.setKUNN2(cur.getR3_sdf_sn());
                weList.add(k);
            }
            request.setAttribute("weList", weList);
        }
        super.copyProperties(form, konkaOrderInfo);

        Attachment attachment = new Attachment();
        attachment.setLink_id(Long.valueOf(order_id));
        attachment.setDel_mark(Short.valueOf("0"));
        List<Attachment> attachmentList = getFacade().getAttachmentService().getAttachmentList(attachment);
        request.setAttribute("attachmentList", attachmentList);

        request.setAttribute("call_r3_interface", super.isCallR3Interface(request));

        request.setAttribute("cust_id", cust_id);
        dynaBean.set("trade_index", "");
        return new ActionForward("/../customer/JxcKonkaOrderRegister/input.jsp");
    }

    public ActionForward copyTH(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        setNaviStringToRequestScope(form, request);
        DynaBean dynaBean = (DynaBean) form;
        String cust_id = (String) dynaBean.get("cust_id");

        String FROMSALESMAN = (String) dynaBean.get("FROMSALESMAN"); // 鐘舵�浣嶏紝鏍囪瘑鏄惁鏄笟鍔″憳鎻愪氦
        String CUSTID = (String) dynaBean.get("CUSTID"); // 涓氬姟鎻愪氦璇锋眰鐨勫鎴稩D
        if (StringUtils.isNotBlank(FROMSALESMAN)) {
            request.setAttribute("FROMSALESMAN", FROMSALESMAN);
        }
        if (StringUtils.isNotBlank(CUSTID)) {
            request.setAttribute("CUSTID", CUSTID);
        }

        HttpSession session = request.getSession();
        PeProdUser user = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);
        if (null == user || StringUtils.isBlank(cust_id)) {
            super.renderJavaScript(response, "alert('登录超时请重新登录。');history.back();");
            return null;
        } else if (null == user.getCust_id()) {
            return null;
        }

        KonkaR3Shop konkaR3Shop = new KonkaR3Shop();
        konkaR3Shop.setId(user.getCust_id());
        konkaR3Shop = getFacade().getKonkaR3ShopService().getKonkaR3Shop(konkaR3Shop);
        if (null == konkaR3Shop) {
            return null;
        }

        // 鍙栨敹璐у湴鍧�
        KonkaJxcBaseAddr konkaJxcBaseAddr = new KonkaJxcBaseAddr();
        konkaJxcBaseAddr.setIs_del(0);

        KonkaR3Shop r3shop = new KonkaR3Shop();
        r3shop.setId(user.getCust_id());
        r3shop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(r3shop);
        if (null != r3shop) {
            konkaJxcBaseAddr.setR3_id(r3shop.getId());
            konkaJxcBaseAddr.setR3_code(r3shop.getR3_code());
        }

        List<KonkaJxcBaseAddr> konkaJxcBaseAddrList = super.getFacade().getKonkaJxcBaseAddrService().getKonkaJxcBaseAddrList(konkaJxcBaseAddr);
        request.setAttribute("konkaJxcBaseAddrList", konkaJxcBaseAddrList);

        dynaBean.set("brandId", Constants.KONKA_BRAND_ID);
        Date today = new Date();
        dynaBean.set("today", DateFormatUtils.format(today, "yyyy-MM-dd"));// 璁㈠崟鏃ユ湡
        dynaBean.set("r3_code", konkaR3Shop.getR3_code());

        // 鍙朢3缃戠偣鍒嗗叕鍙窱D
        KonkaR3Shop krs = new KonkaR3Shop();
        krs.setId(Long.valueOf(cust_id));
        krs = getFacade().getKonkaR3ShopService().getKonkaR3ShopWithBranchAssign(krs);
        if (null != krs) {
            BigDecimal deptId = new BigDecimal(krs.getMap().get("fgs_id").toString());
            if (null != deptId && deptId.intValue() == -1) {// deptId=-1l,璇存槑娌℃湁鎵惧埌涓婄骇鍒嗗叕鍙�
                request.setAttribute("confirm", 1);
            }
        }

        // 鍙栬鍗曟祦绋�
        // KonkaDept dept =
        // super.getKonkaDeptByCustomerId(Long.valueOf(cust_id));
        // KonkaOrderAuditProcess ap = new KonkaOrderAuditProcess();
        // ap.setAdd_dept_id(dept.getDept_id());
        // ap.setIs_del(0);
        // List<KonkaOrderAuditProcess> auditProcesseList =
        // getFacade().getKonkaOrderAuditProcessService()
        // .getKonkaOrderAuditProcessList(ap);
        //
        // KonkaOrderAuditProcess ap_public = new KonkaOrderAuditProcess();
        // ap_public.setAdd_dept_id(0L);
        // ap_public.setIs_del(0);
        // List<KonkaOrderAuditProcess> ap_publicauditProcesseList =
        // getFacade().getKonkaOrderAuditProcessService()
        // .getKonkaOrderAuditProcessList(ap_public);
        // auditProcesseList.addAll(ap_publicauditProcesseList);
        // request.setAttribute("processList", auditProcesseList);

        // 鑾峰彇娴佺▼鍒楄〃
        KonkaDept kd = super.getKonkaDeptByCustomerId(Long.valueOf(cust_id));
        KonkaDept dept = null;
        if (kd != null) {
            dept = super.getSuperiorForDeptType(kd.getDept_id(), 3);
        }
        List<KonkaOrderAuditProcess> processList = new ArrayList<KonkaOrderAuditProcess>();
        if (dept != null) {

            KonkaR3Shop konkaR3Shop_ = new KonkaR3Shop();
            konkaR3Shop_.setId(Long.valueOf(cust_id));
            konkaR3Shop_.setIs_del(0l);
            konkaR3Shop_ = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(konkaR3Shop_);
            if (null != konkaR3Shop_) {
                if (StringUtils.isNotBlank(konkaR3Shop_.getCustomer_type())) {// 鍒ゆ柇鏄惁鏄湁瀹㈡埛绫诲瀷
                    KonkaOrderAuditProcess process = new KonkaOrderAuditProcess();
                    process.getMap().put("par_add_dept_id", dept.getDept_id());
                    process.setCustomer_type(konkaR3Shop_.getCustomer_type());
                    process.setIs_del(0);
                    process.setIs_stop(0);
                    processList = super.getFacade().getKonkaOrderAuditProcessService().getKonkaOrderAuditProcessList(process);
                    if (null == processList || processList.size() == 0) {
                        // 娌℃湁鍒嗗叕鍙歌嚜瀹氫箟鐨勬祦绋嬪彇缁熶竴娴佺▼锛屽嵆鍒嗗叕鍙歌嚜瀹氫箟娴佺▼
                        KonkaOrderAuditProcess ap_public = new KonkaOrderAuditProcess();
                        ap_public.setCustomer_type(konkaR3Shop_.getCustomer_type());
                        ap_public.setAdd_dept_id(0L);
                        ap_public.setIs_del(0);
                        ap_public.setIs_stop(0);
                        List<KonkaOrderAuditProcess> ap_publicauditProcesseList = getFacade().getKonkaOrderAuditProcessService().getKonkaOrderAuditProcessList(ap_public);
                        processList.addAll(ap_publicauditProcesseList);
                    }
                    if (null == processList || processList.size() == 0) {
                        // 娌℃湁鍒嗗叕鍙歌嚜瀹氫箟鐨勬祦绋嬪彇缁熶竴娴佺▼锛屽嵆鍒嗗叕鍙告祦绋�
                        KonkaOrderAuditProcess ap_public = new KonkaOrderAuditProcess();
                        ap_public.setCustomer_type(konkaR3Shop_.getCustomer_type());
                        ap_public.setAdd_dept_id(0L);
                        ap_public.setIs_del(0);
                        ap_public.setIs_stop(0);
                        List<KonkaOrderAuditProcess> ap_publicauditProcesseList = getFacade().getKonkaOrderAuditProcessService().getKonkaOrderAuditProcessList(ap_public);
                        processList.addAll(ap_publicauditProcesseList);
                    }
                    if (null == processList || processList.size() == 0) {
                        // 娌℃湁鍒嗗叕鍙告祦绋嬪彇缁熶竴娴佺▼锛屽嵆鎬诲叕鍙告祦绋�
                        KonkaOrderAuditProcess ap_public = new KonkaOrderAuditProcess();
                        ap_public.getMap().put("par_add_dept_id", dept.getDept_id());
                        ap_public.setIs_del(0);
                        ap_public.setIs_stop(0);
                        ap_public.getMap().put("customer_type_is_null", true);
                        List<KonkaOrderAuditProcess> ap_publicauditProcesseList = getFacade().getKonkaOrderAuditProcessService().getKonkaOrderAuditProcessList(ap_public);
                        processList.addAll(ap_publicauditProcesseList);
                    }

                    if (null == processList || processList.size() == 0) {
                        // 娌℃湁鍒嗗叕鍙歌嚜瀹氫箟鐨勬祦绋嬪彇缁熶竴娴佺▼锛屽嵆鎬诲叕鍙歌嚜瀹氫箟娴佺▼
                        KonkaOrderAuditProcess ap_public = new KonkaOrderAuditProcess();
                        ap_public.setAdd_dept_id(0L);
                        ap_public.setIs_del(0);
                        ap_public.setIs_stop(0);
                        ap_public.getMap().put("customer_type_is_null", true);
                        List<KonkaOrderAuditProcess> ap_publicauditProcesseList = getFacade().getKonkaOrderAuditProcessService().getKonkaOrderAuditProcessList(ap_public);
                        processList.addAll(ap_publicauditProcesseList);
                    }

                    request.setAttribute("customer_type", konkaR3Shop_.getCustomer_type());
                } else {
                    KonkaOrderAuditProcess process = new KonkaOrderAuditProcess();
                    process.getMap().put("par_add_dept_id", dept.getDept_id());
                    process.getMap().put("customer_type_is_null", true);
                    process.setIs_del(0);
                    process.setIs_stop(0);
                    processList = super.getFacade().getKonkaOrderAuditProcessService().getKonkaOrderAuditProcessList(process);
                    if (null == processList || processList.size() == 0) {
                        // 娌℃湁鍒嗗叕鍙哥粺涓�祦绋嬶紝鍗冲垎鍏徃浼樺厛绾ч珮
                        KonkaOrderAuditProcess ap_public = new KonkaOrderAuditProcess();
                        ap_public.getMap().put("customer_type_is_null", true);
                        ap_public.setAdd_dept_id(0L);
                        ap_public.setIs_del(0);
                        ap_public.setIs_stop(0);
                        List<KonkaOrderAuditProcess> ap_publicauditProcesseList = getFacade().getKonkaOrderAuditProcessService().getKonkaOrderAuditProcessList(ap_public);
                        processList.addAll(ap_publicauditProcesseList);
                    }
                }
            }
            request.setAttribute("processList", processList);
        }

        // 鍏ㄩ儴搴蜂匠浜у搧
        PePdModel pePdModel = new PePdModel();
        pePdModel.setBrand_id(Constants.KONKA_BRAND_ID);
        pePdModel.setIs_del(0);
        List<PePdModel> pdList = getFacade().getPePdModelService().getPePdModelWithClsNameBrandNameList(pePdModel);

        request.setAttribute("pdList", pdList);

        dynaBean.set("userName", user.getReal_name());
        /** 鍙栫綉鐐逛笟鍔″憳 */
        BranchAssign bagn = new BranchAssign();
        bagn.setKonka_r3_id(Long.valueOf(cust_id));
        bagn = getFacade().getBranchAssignService().getBranchAssign(bagn);
        if (null != bagn && null != bagn.getUser_id()) {
            PeProdUser ywy = new PeProdUser();
            ywy.setId(bagn.getUser_id());
            ywy = getFacade().getPeProdUserService().getPeProdUserResult(ywy);
            request.setAttribute("ywy_user_name", ywy.getReal_name());
        }

        // 杩涜揣鐧昏鍗�
        String order_id = (String) dynaBean.get("order_id");
        KonkaOrderInfo konkaOrderInfo = new KonkaOrderInfo();
        konkaOrderInfo.setId(Long.valueOf(order_id));
        konkaOrderInfo = getFacade().getKonkaOrderInfoService().getKonkaOrderInfo(konkaOrderInfo);
        konkaOrderInfo.setIs_submit(null);

        // dynaBean.set("fullName",
        // super.getPIndexFullName(konkaOrderInfo.getUser_p_index()));
        dynaBean.set("fullName", super.getPIndexName(konkaOrderInfo.getUser_p_index(), ""));

        // super.setprovinceAndcityAndcountryToFrom(dynaBean,
        // konkaOrderInfo.getUser_p_index());

        Long __user_index = konkaOrderInfo.getUser_p_index();
        if (null != __user_index) {
            String user_index = __user_index.toString();
            String province = StringUtils.substring(user_index, 0, 2).concat("0000");
            String city = StringUtils.substring(user_index, 0, 4).concat("00");
            String country = StringUtils.substring(user_index, 0, 6);
            String town = "";
            if (StringUtils.length(user_index) == 8) {
                town = user_index;
            }
            dynaBean.set("province", province);
            dynaBean.set("city", city);
            dynaBean.set("country", country);
            dynaBean.set("town", town);
        }

        KonkaOrderInfoDetails konkaOrderInfoDetails = new KonkaOrderInfoDetails();
        konkaOrderInfoDetails.setOrder_id(Long.valueOf(order_id));
        List<KonkaOrderInfoDetails> konkaOrderInfoDetailsList = getFacade().getKonkaOrderInfoDetailsService().getKonkaOrderInfoDetailsList(konkaOrderInfoDetails);
        request.setAttribute("konkaOrderInfoDetailsList", konkaOrderInfoDetailsList);

        String[] pd_ids = new String[konkaOrderInfoDetailsList.size()];
        for (int i = 0; i < konkaOrderInfoDetailsList.size(); i++) {
            pd_ids[i] = konkaOrderInfoDetailsList.get(i).getPd_id().toString();
        }
        dynaBean.set("pd_ids", StringUtils.join(pd_ids, ","));

        String fgsSN = konkaR3Shop.getBranch_area_name_2();
        request.setAttribute("sales_org", fgsSN);

        if (super.isCallR3Interface(request)) {
            List<KNVP> knvpList = new ArrayList<KNVP>();
            ReturnInfo<KNVP> info = new ReturnInfo<KNVP>();
            info = super.getFacade().getR3WebInterfaceService().getKnvpsList(fgsSN, Constants.default_vtweg, Constants.default_spart, konkaR3Shop.getR3_code());
            if (info.getSuccess() == true) {
                knvpList = info.getDataResult();
            }

            // AG:鍞揪鏂�
            // RE:鍑虹エ鏂�
            // RG:浠樻鏂�
            // WE:閫佽揪鏂�

            List<KNVP> agList = new ArrayList<KNVP>();
            List<KNVP> reList = new ArrayList<KNVP>();
            List<KNVP> rgList = new ArrayList<KNVP>();
            List<KNVP> weList = new ArrayList<KNVP>();

            if (null != knvpList) {
                // System.out.println("knvpList===" + knvpList.size());
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
            request.setAttribute("agList", agList);
            request.setAttribute("reList", reList);
            request.setAttribute("rgList", rgList);
            request.setAttribute("weList", weList);

            if (agList.size() == 0) {
                request.setAttribute("ag", konkaR3Shop.getR3_code());
            }
            if (reList.size() == 0) {
                request.setAttribute("re", konkaR3Shop.getR3_code());
            }
            if (rgList.size() == 0) {
                request.setAttribute("rg", konkaR3Shop.getR3_code());
            }
            if (weList.size() == 0) {
                KonkaMobileImpStore s = new KonkaMobileImpStore();
                s.setR3_shdf_sn(konkaR3Shop.getR3_code());
                List<KonkaMobileImpStore> sList = super.getFacade().getKonkaMobileImpStoreService().getKonkaMobileImpStoreListForDistinctSdf(s);

                List<KNVP> __weList = new ArrayList<KNVP>();
                for (KonkaMobileImpStore cur : sList) {
                    KNVP k = new KNVP();
                    k.setKUNN2(cur.getR3_sdf_sn());
                    __weList.add(k);
                }
                request.setAttribute("weList", __weList);
                if (__weList.size() == 0) {
                    request.setAttribute("we", konkaR3Shop.getR3_code());
                }
            }
        } else {
            request.setAttribute("ag", konkaR3Shop.getR3_code());
            request.setAttribute("re", konkaR3Shop.getR3_code());
            request.setAttribute("rg", konkaR3Shop.getR3_code());

            KonkaMobileImpStore s = new KonkaMobileImpStore();
            s.setR3_shdf_sn(konkaR3Shop.getR3_code());
            List<KonkaMobileImpStore> sList = super.getFacade().getKonkaMobileImpStoreService().getKonkaMobileImpStoreListForDistinctSdf(s);

            List<KNVP> weList = new ArrayList<KNVP>();
            for (KonkaMobileImpStore cur : sList) {
                KNVP k = new KNVP();
                k.setKUNN2(cur.getR3_sdf_sn());
                weList.add(k);
            }
            request.setAttribute("weList", weList);
        }
        super.copyProperties(form, konkaOrderInfo);

        Attachment attachment = new Attachment();
        attachment.setLink_id(Long.valueOf(order_id));
        attachment.setDel_mark(Short.valueOf("0"));
        List<Attachment> attachmentList = getFacade().getAttachmentService().getAttachmentList(attachment);
        request.setAttribute("attachmentList", attachmentList);

        request.setAttribute("call_r3_interface", super.isCallR3Interface(request));

        request.setAttribute("cust_id", cust_id);
        dynaBean.set("trade_index", "");

        return new ActionForward("/../customer/JxcKonkaOrderRegister/inputTH.jsp");
    }

    public Long getWeekSaleAmount(KonkaR3Shop r3shop, String md_name, String cust_id, int week) {

        Long order_fourweek_count = 0l;
        if (null != r3shop) {
            Date today = new Date();
            // 默认是检查前四周的
            // 找系统设置里面相关的维护记录
            int weeksAmouts = week == 0 ? 4 : week;
            Date beginTime = DateUtils.addDays(today, weeksAmouts * -7);
            JBaseGoods jBaseGoods = new JBaseGoods();
            jBaseGoods.setIs_konka(1);
            jBaseGoods.setGoods_stutes(0);
            jBaseGoods.setGoods_name(md_name);
            jBaseGoods.setC_id(Long.valueOf(cust_id));
            List<JBaseGoods> jBaseGoodsList = super.getFacade().getJBaseGoodsService().getJBaseGoodsList(jBaseGoods);
            Long Jbill_tuiHuo_count = 0l;
            Long Jbill_sale_count = 0l;
            Long order_info_count = 0l;
            if (null != jBaseGoodsList && jBaseGoodsList.size() > 0) {
                // 获取销售退货数量
                JBill Jbill = new JBill();
                Jbill.setBill_type(21);
                Jbill.getMap().put("goods_id", jBaseGoodsList.get(0).getGoods_id());
                Jbill.getMap().put("r3_id", r3shop.getId());
                Jbill.getMap().put("opr_date_gt", DateUtils.formatDay(beginTime));
                Jbill.getMap().put("opr_date_lt", DateUtils.formatDay(today));
                Jbill_tuiHuo_count = super.getFacade().getJBillService().getJBillCountForFourWeek(Jbill);
                // 获取销售销售数量
                JBill Jbill2 = new JBill();
                Jbill2.setBill_type(20);
                Jbill2.getMap().put("goods_id", jBaseGoodsList.get(0).getGoods_id());
                Jbill2.getMap().put("r3_id", r3shop.getId());
                Jbill2.getMap().put("opr_date_gt", DateUtils.formatDay(beginTime));
                Jbill2.getMap().put("opr_date_lt", DateUtils.formatDay(today));
                Jbill_sale_count = super.getFacade().getJBillService().getJBillCountForFourWeek(Jbill2);
                // 查询前N周的零售量
                JSubSellRec jSubSellRec = new JSubSellRec();
                jSubSellRec.setStatus(1);
                jSubSellRec.getMap().put("r3_id", r3shop.getId());
                jSubSellRec.getMap().put("add_date_gt", DateUtils.formatDay(beginTime));
                jSubSellRec.getMap().put("add_date_lt", DateUtils.formatDay(today));
                jSubSellRec.getMap().put("goods_id", jBaseGoodsList.get(0).getGoods_id());
                order_info_count = super.getFacade().getJSubSellRecService().getJSubSellRecCountForFourWeek(jSubSellRec);
            }
            // 获得手机端销售数量
            KonkaMobileSailData konkaMobileSailData = new KonkaMobileSailData();
            konkaMobileSailData.getMap().put("opr_date_gt", DateUtils.formatDay(beginTime));
            konkaMobileSailData.getMap().put("opr_date_lt", DateUtils.formatDay(today));
            konkaMobileSailData.setIs_del(0);
            konkaMobileSailData.setCustomer_r3_code(r3shop.getR3_code());
            konkaMobileSailData.setModel_name(md_name);
            Long mobile_count = super.getFacade().getKonkaMobileSailDataService().getKonkaMobileSailDataCountForFourWeek(konkaMobileSailData);
            // 查询前四周的专卖店零售量
            KonkaOrderInfo order3 = new KonkaOrderInfo();
            order3.setIs_del(0);
            order3.setZmd_order_flag(1);
            order3.setR3_id(r3shop.getId());
            order3.setCust_id(Long.valueOf(cust_id));
            // order3.getMap().put("pd_id", odt.getPd_id());
            order3.getMap().put("start_date", DateUtils.formatDay(beginTime));
            order3.getMap().put("end_date", DateUtils.formatDay(today));
            Long order_info_zmd_count = super.getFacade().getKonkaOrderInfoService().getKonkaOrderInfoWithNoForFourWeekCount(order3);

            if (null == mobile_count) {
                mobile_count = 0l;
            }
            order_fourweek_count = order_info_zmd_count + order_info_count + mobile_count + Jbill_sale_count - Jbill_tuiHuo_count;



            // 获得库存
        }
        return order_fourweek_count;
    }

    public ActionForward AjaxgetFourCountAndStoreCount(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String flag = "0";
        DynaBean dynaBean = (DynaBean) form;
        String zbukrs = (String) dynaBean.get("sales_org"); // 销售组织
        String md_names = (String) dynaBean.get("md_name");// 产品编码
        String cust_id = (String) dynaBean.get("cust_id");// 产品编码

        String[] md_names_array = md_names.split(",");

        JSONObject jonsObject = new JSONObject();
        JSONArray list = new JSONArray();

        // 获取过来的md_names_array多一个 i从1开始
        for (int i = 1; i < md_names_array.length; i++) {
            // 获得库存
            KonkaR3Shop r3shop = new KonkaR3Shop();
            r3shop.setId(Long.valueOf(cust_id));
            r3shop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(r3shop);
            if (null != r3shop) {
                Date today = new Date();
                // 默认是检查前四周的
                // 找系统设置里面相关的维护记录
                int weeksAmouts = check_for_stock("check_for_stock") == 0 ? 4 : check_for_stock("check_for_stock");
                Date beginTime = DateUtils.addDays(today, weeksAmouts * -7);
                JBaseGoods jBaseGoods = new JBaseGoods();
                jBaseGoods.setIs_konka(1);
                jBaseGoods.setGoods_stutes(0);
                jBaseGoods.setGoods_name(md_names_array[i]);
                jBaseGoods.setC_id(Long.valueOf(cust_id));
                List<JBaseGoods> jBaseGoodsList = super.getFacade().getJBaseGoodsService().getJBaseGoodsList(jBaseGoods);
                Long Jbill_tuiHuo_count = 0l;
                Long Jbill_sale_count = 0l;
                Long order_info_count = 0l;
                if (null != jBaseGoodsList && jBaseGoodsList.size() > 0) {
                    // 获取销售退货数量
                    JBill Jbill = new JBill();
                    Jbill.setBill_type(21);
                    Jbill.getMap().put("goods_id", jBaseGoodsList.get(0).getGoods_id());
                    Jbill.getMap().put("r3_id", r3shop.getId());
                    Jbill.getMap().put("opr_date_gt", DateUtils.formatDay(beginTime));
                    Jbill.getMap().put("opr_date_lt", DateUtils.formatDay(today));
                    Jbill_tuiHuo_count = super.getFacade().getJBillService().getJBillCountForFourWeek(Jbill);
                    // 获取销售销售数量
                    JBill Jbill2 = new JBill();
                    Jbill2.setBill_type(20);
                    Jbill2.getMap().put("goods_id", jBaseGoodsList.get(0).getGoods_id());
                    Jbill2.getMap().put("r3_id", r3shop.getId());
                    Jbill2.getMap().put("opr_date_gt", DateUtils.formatDay(beginTime));
                    Jbill2.getMap().put("opr_date_lt", DateUtils.formatDay(today));
                    Jbill_sale_count = super.getFacade().getJBillService().getJBillCountForFourWeek(Jbill2);
                    // 查询前N周的零售量
                    JSubSellRec jSubSellRec = new JSubSellRec();
                    jSubSellRec.setStatus(1);
                    jSubSellRec.getMap().put("r3_id", r3shop.getId());
                    jSubSellRec.getMap().put("add_date_gt", DateUtils.formatDay(beginTime));
                    jSubSellRec.getMap().put("add_date_lt", DateUtils.formatDay(today));
                    jSubSellRec.getMap().put("goods_id", jBaseGoodsList.get(0).getGoods_id());
                    order_info_count = super.getFacade().getJSubSellRecService().getJSubSellRecCountForFourWeek(jSubSellRec);
                }
                // 获得手机端销售数量
                KonkaMobileSailData konkaMobileSailData = new KonkaMobileSailData();
                konkaMobileSailData.getMap().put("opr_date_gt", DateUtils.formatDay(beginTime));
                konkaMobileSailData.getMap().put("opr_date_lt", DateUtils.formatDay(today));
                konkaMobileSailData.setIs_del(0);
                konkaMobileSailData.setCustomer_r3_code(r3shop.getR3_code());
                konkaMobileSailData.setModel_name(md_names_array[i]);
                Long mobile_count = super.getFacade().getKonkaMobileSailDataService().getKonkaMobileSailDataCountForFourWeek(konkaMobileSailData);
                // 查询前四周的专卖店零售量
                KonkaOrderInfo order3 = new KonkaOrderInfo();
                order3.setIs_del(0);
                order3.setZmd_order_flag(1);
                order3.setR3_id(r3shop.getId());
                order3.setCust_id(Long.valueOf(cust_id));
                // order3.getMap().put("pd_id", odt.getPd_id());
                order3.getMap().put("start_date", DateUtils.formatDay(beginTime));
                order3.getMap().put("end_date", DateUtils.formatDay(today));
                Long order_info_zmd_count = super.getFacade().getKonkaOrderInfoService().getKonkaOrderInfoWithNoForFourWeekCount(order3);

                if (null == mobile_count) {
                    mobile_count = 0l;
                }
                Long order_fourweek_count = order_info_zmd_count + order_info_count + mobile_count + Jbill_sale_count - Jbill_tuiHuo_count;

                // 获取前4周的销量
                Long order_fourweek_count_4 = getWeekSaleAmount(r3shop, md_names_array[i], cust_id, 4);

                // 获取前6周的销量
                Long order_fourweek_count_6 = this.getWeekSaleAmount(r3shop, md_names_array[i], cust_id, 6);

                // 获取前8周的销量
                Long order_fourweek_count_8 = this.getWeekSaleAmount(r3shop, md_names_array[i], cust_id, 8);


                // 获取前10周的销量
                Long order_fourweek_count_10 = this.getWeekSaleAmount(r3shop, md_names_array[i], cust_id, 10);

                // 获得库存
                Long curr_ku_count = super.getKhStocks(r3shop.getR3_code(), md_names_array[i], null);

                JSONObject obj = new JSONObject();
                obj.put("md_name", md_names_array[i]);
                obj.put("curr_ku_count", curr_ku_count);
                obj.put("order_fourweek_count", order_fourweek_count);
                obj.put("order_fourweek_count_4", order_fourweek_count_4);
                obj.put("order_fourweek_count_6", order_fourweek_count_6);
                obj.put("order_fourweek_count_8", order_fourweek_count_8);
                obj.put("order_fourweek_count_10", order_fourweek_count_10);
                obj.put("weeks", weeksAmouts);
                list.add(obj);
            }
        }
        jonsObject.put("list", list);
        super.renderJson(response, jonsObject.toString());
        return null;
    }

    public ActionForward AjaxgetAddr(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        DynaBean dynaBean = (DynaBean) form;
        String id = (String) dynaBean.get("id");

        JSONObject jonsObject = new JSONObject();
        JSONArray list = new JSONArray();

        KonkaJxcBaseAddr entity = new KonkaJxcBaseAddr();
        entity.setId(Long.valueOf(id));
        entity.setIs_del(0);
        entity = super.getFacade().getKonkaJxcBaseAddrService().getKonkaJxcBaseAddr(entity);
        if (null != entity) {
            JSONObject obj = new JSONObject();
            obj.put("user_name", entity.getUser_name());
            obj.put("user_tel", entity.getUser_tel());
            obj.put("user_phone", entity.getUser_phone());

            if (StringUtils.isNotBlank(entity.getUser_p_index().toString())) {
                String p_index = entity.getUser_p_index().toString();
                if (!p_index.endsWith("00")) {
                    if (p_index.length() == 6) {
                        obj.put("province", StringUtils.substring(p_index, 0, 2) + "0000");
                        obj.put("city", StringUtils.substring(p_index, 0, 4) + "00");
                        obj.put("country", p_index);
                    } else if (p_index.length() == 8) {
                        obj.put("province", StringUtils.substring(p_index, 0, 2) + "0000");
                        obj.put("city", StringUtils.substring(p_index, 0, 4) + "00");
                        obj.put("country", StringUtils.substring(p_index, 0, 6));
                        obj.put("town", p_index);
                    }
                } else if (p_index.endsWith("0000")) {
                    obj.put("province", p_index);
                } else if (p_index.endsWith("00")) {
                    obj.put("province", StringUtils.substring(p_index, 0, 2) + "0000");
                    obj.put("city", p_index);
                }
            }
            obj.put("user_addr", entity.getUser_addr());
            obj.put("user_remark", entity.getUser_remark());
            list.add(obj);

            jonsObject.put("list", list);
            super.renderJson(response, jonsObject.toString());
            return null;
        }
        return null;
    }

    public ActionForward chooseTradeIndex(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        DynaBean dynaBean = (DynaBean) form;
        Pager pager = (Pager) dynaBean.get("pager");
        String trade_index_like = (String) dynaBean.get("trade_index_like");
        String add_date_gt = (String) dynaBean.get("add_date_gt");
        String add_date_lt = (String) dynaBean.get("add_date_lt");
        PeProdUser user = (PeProdUser) request.getSession().getAttribute(Constants.CUSTOMER_USER_INFO);

        KonkaOrderInfo entity = new KonkaOrderInfo();
        if (StringUtils.isNotBlank(trade_index_like)) {
            entity.getMap().put("trade_index_like", trade_index_like);
        }
        Calendar cal = Calendar.getInstance();
        Calendar f = (Calendar) cal.clone();
        f.clear();
        f.set(Calendar.YEAR, cal.get(Calendar.YEAR));
        f.set(Calendar.MONTH, cal.get(Calendar.MONTH));
        String firstday = new SimpleDateFormat("yyyy-MM-dd").format(f.getTime());
        String today = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
        if (StringUtils.isBlank(add_date_gt)) {
            add_date_gt = firstday;
        }
        if (StringUtils.isBlank(add_date_lt)) {
            add_date_lt = today;
        }

        dynaBean.set("add_date_gt", add_date_gt);
        dynaBean.set("add_date_lt", add_date_lt);
        entity.getMap().put("start_date", add_date_gt + " 00:00:00");
        entity.getMap().put("end_date", add_date_lt + " 23:59:59");
        /**
         * 此字段实际含义不是字面含义订单类型,现被误用作订单来源<br>
         * 0在线下单<br>
         * 1图片下单<br>
         * 2触网转单<br>
         * 4从返利转<br>
         * 5DRP转入<br>
         */
        entity.setOrder_type(0);
        entity.setIs_del(0);
        entity.setCust_id(user.getCust_id());
        entity.setAudit_state(3);

        // Long recordCount =
        // getFacade().getKonkaOrderInfoService().getKonkaOrderInfoCount(entity);
        Long recordCount = super.getFacade().getKonkaOrderInfoService().getKonkaOrderInfoSearchforPdNameCount(entity);
        pager.init(recordCount, 45, pager.getRequestPage());
        entity.getRow().setFirst(pager.getFirstRow());
        entity.getRow().setCount(45);
        // List<KonkaOrderInfo> list =
        // getFacade().getKonkaOrderInfoService().getKonkaOrderInfoPaginatedList(entity);
        List<KonkaOrderInfo> list = super.getFacade().getKonkaOrderInfoService().getKonkaOrderInfoResultForPaginatedList(entity);

        request.setAttribute("entityList", list);
        return new ActionForward("/../customer/JxcKonkaOrderRegister/choose_trade_index.jsp");
    }

    public ActionForward ajaxSetRTradeIndexDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        DynaBean dynaBean = (DynaBean) form;
        String r_trade_index = (String) dynaBean.get("r_trade_index");
        StringBuffer sb = new StringBuffer("{");
        if (StringUtils.isBlank(r_trade_index)) {
            sb = sb.append("}");
            super.renderJson(response, sb.toString());
            return null;
        }
        PeProdUser user = (PeProdUser) request.getSession().getAttribute(Constants.CUSTOMER_USER_INFO);

        KonkaOrderInfo konkaOrderInfo = new KonkaOrderInfo();
        konkaOrderInfo.setTrade_index(r_trade_index);
        konkaOrderInfo = super.getFacade().getKonkaOrderInfoService().getKonkaOrderInfo(konkaOrderInfo);

        sb = sb.append("\"list\":[");
        KonkaOrderInfoDetails konkaOrderInfoDetails = new KonkaOrderInfoDetails();
        konkaOrderInfoDetails.setOrder_id(konkaOrderInfo.getId());
        List<KonkaOrderInfoDetails> konkaOrderInfoDetailsList = super.getFacade().getKonkaOrderInfoDetailsService().getKonkaOrderInfoDetailsList(konkaOrderInfoDetails);
        if (null != konkaOrderInfoDetailsList && konkaOrderInfoDetailsList.size() > 0) {
            for (KonkaOrderInfoDetails temp : konkaOrderInfoDetailsList) {
                sb = sb.append("{");
                sb = sb.append("\"pd_name\":\"").append(temp.getPd_name()).append("\",");
                sb = sb.append("\"pd_id\":\"").append(temp.getPd_id()).append("\",");
                sb = sb.append("\"good_count\":\"").append(temp.getGood_count()).append("\",");
                sb = sb.append("\"good_unit\":\"").append(temp.getGood_unit()).append("\",");
                sb = sb.append("\"good_price\":\"").append(temp.getGood_price()).append("\",");
                sb = sb.append("\"good_sum_price\":\"").append(temp.getGood_sum_price()).append("\",");

                sb = sb.append("\"pd_trade_index\":\"").append(r_trade_index).append("\"");
                sb = sb.append("},");
            }
        }
        String sb_str = StringUtils.removeEnd(sb.toString(), ",") + "]}";
        logger.info("sb_str {}", sb_str);
        super.renderJson(response, sb_str);
        return null;
    }
    
    
    public ActionForward print(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String order_id = (String) dynaBean.get("order_id");

		PeProdUser user = (PeProdUser) getSessionAttribute(request, Constants.CUSTOMER_USER_INFO);
		KonkaR3Shop shop = new KonkaR3Shop();
		shop.setId(user.getCust_id());
		shop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(shop);
		request.setAttribute("shop", shop);
		
		KonkaOrderInfo order = new KonkaOrderInfo();
		order.setId(Long.parseLong(order_id));
		order = super.getFacade().getKonkaOrderInfoService().getKonkaOrderInfo(order);
		
		request.setAttribute("order", order);
		
		//送达方仓位
		JBaseStoreR3 storer3 = new JBaseStoreR3();
		storer3.setSale_r3_code(order.getWe());
		HashMap map = super.getFacade().getJBaseStoreR3Service().getSDFStore(storer3);
		request.setAttribute("store_name", map.get("STORE_NAME"));
		
		//订单明细
		KonkaOrderInfoDetails details = new KonkaOrderInfoDetails();
		details.setOrder_id(order.getId());
		List<KonkaOrderInfoDetails> dlist = super.getFacade().getKonkaOrderInfoDetailsService().getKonkaOrderInfoDetailsList(details);
		request.setAttribute("detailsList", dlist);
		request.setAttribute("nowDate", new Date());

		return new ActionForward("/../customer/JxcKonkaOrderRegister/print.jsp");
	}
}
