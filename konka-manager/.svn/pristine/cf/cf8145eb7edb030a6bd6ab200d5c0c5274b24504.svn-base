package com.ebiz.mmt.web.struts.manager.admin;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ebiz.mmt.domain.*;
import com.ebiz.mmt.r3.*;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.util.EncryptUtils;
import com.ebiz.ssi.web.struts.bean.Pager;
import com.opensymphony.oscache.util.StringUtil;

public class KonkaOrderSearchAction extends BaseAction {
    @Override
    public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return this.list(mapping, form, request, response);
    }
    public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 判断session是否超时
        PeProdUser userInfo = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
        if (null == userInfo) {
            String msg = super.getMessage(request, "user.session.isEmpty");
            super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
            return null;
        }
        super.getModPopeDom(form, request);// 权限判断
        setNaviStringToRequestScope(form, request);

        DynaBean dynaBean = (DynaBean) form;
        Pager pager = (Pager) dynaBean.get("pager");
        String kh_confirm_state = (String) dynaBean.get("kh_confirm_state");
        String order_date_start = (String) dynaBean.get("order_date_start");
        String order_date_end = (String) dynaBean.get("order_date_end");
        String trade_index_like = (String) dynaBean.get("trade_index_like");// 流程类型
        String user_shop_name_like = (String) dynaBean.get("user_shop_name_like");// 订单审核状态
        String process_id = (String) dynaBean.get("process_id");
        String dept_id = (String) dynaBean.get("dept_id");
        String l4_dept_id = (String) dynaBean.get("l4_dept_id");
        String l5_dept_id = (String) dynaBean.get("l5_dept_id");
        String pay_type = (String) dynaBean.get("pay_type");
        String send_type = (String) dynaBean.get("send_type");
        String my_audit_state = (String) dynaBean.get("my_audit_state");
        String or_audit_state = (String) dynaBean.get("or_audit_state");
        String pd_name_like = (String) dynaBean.get("pd_name_like");
        String is_delivered = (String) dynaBean.get("is_delivered");
        String ag_like = (String) dynaBean.get("ag_like");// 订单表里的ag 就是客户的R3编码
        String r3_id = (String) dynaBean.get("r3_id");
        String sync_state = (String) dynaBean.get("sync_state");
        String sync_date_start = (String) dynaBean.get("sync_date_start");
        String sync_date_end = (String) dynaBean.get("sync_date_end");
        String excel_all = (String) dynaBean.get("excel_all");
        String doc_type = (String) dynaBean.get("doc_type");
        String order_type = (String) dynaBean.get("order_type");//订单来源
        String return_type = (String) dynaBean.get("return_type");//退货类型

        // 退货订单的菜单参数
        String is_th = (String) dynaBean.get("is_th");// 为1 的时候是退货

        String vbedl_like = (String) dynaBean.get("vbedl_like");// 26单号
        String vbedl_null = (String) dynaBean.get("vbedl_null");
        String customer_type_index = (String) dynaBean.get("customer_type_index");
        String sync_user = (String) dynaBean.get("sync_user");

        String is_change = (String) dynaBean.get("is_change");

        PeRoleUser _peRoleUser = new PeRoleUser();
        _peRoleUser.setUser_id(userInfo.getId());
        List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

        dynaBean.set("user_id", userInfo.getId());// 用户

        boolean role_id_lt_30 = false; // 30以下,包括管理员10
        boolean role_id_eq_30 = false; // 是否为分公司管理员

        boolean role_id_eq_38 = false; // 是否为分公司产品会计
        boolean role_id_eq_41 = false; // 是否为青岛产品会计
        boolean role_id_eq_33 = false; // 是否为泉州产品会计
        boolean role_id_eq_54 = false; // 是否为乐山产品会计
        boolean role_id_eq_55 = false; // 是否为西昌产品会计
        boolean role_id_eq_56 = false; // 是否为广元产品会计
        boolean role_id_eq_43 = false; // 是否为万州产品会计
        boolean role_id_eq_44 = false; // 是否为芜湖产品会计
        boolean role_id_eq_46 = false; // 是否为温州产品会计
        boolean role_id_eq_57 = false; // 是否为分公司物流经理


        //下面这一串是同步物流的权限  34、36、38、39、40、41、47、49、50
        boolean role_id_eq_34 = false;
        boolean role_id_eq_36 = false;
        boolean role_id_eq_39 = false;
        boolean role_id_eq_40 = false;
        boolean role_id_eq_47 = false;
        boolean role_id_eq_49 = false;
        boolean role_id_eq_50 = false;

        /*
         * 1 33 分公司职务-厦门 泉州产品会计 2014-04-02 17:48:57 权限 | 人员 | 授权 | 修改 | 删除 2 38 系统职务 分公司产品会计
         * 2013-08-20 09:58:50 权限 | 人员 | 授权 | 修改 | 删除 3 41 系统职务 青岛产品会计 2013-10-31 17:54:56 权限 | 人员 |
         * 授权 | 修改 | 删除 4 54 分公司职务-成都 乐山产品会计 2014-03-28 10:42:31 权限 | 人员 | 授权 | 修改 | 删除 5 55
         * 分公司职务-成都 西昌产品会计 2014-03-28 10:42:51 权限 | 人员 | 授权 | 修改 | 删除 6 56 分公司职务-成都 广元产品会计
         */

        List<Long> role_ids = new ArrayList<Long>(); // 当前登录用户的角色列表
        if (peRoleUserList != null) {
            for (PeRoleUser peRoleUser : peRoleUserList) {
                role_ids.add(peRoleUser.getRole_id());
                if (peRoleUser.getRole_id() >= 0L && peRoleUser.getRole_id() < 30L) {
                    role_id_lt_30 = true;
                }

                if (peRoleUser.getRole_id() == 38L) {
                    role_id_eq_38 = true;
                }
                if (peRoleUser.getRole_id() == 41L) {
                    role_id_eq_41 = true;
                }

                if (peRoleUser.getRole_id() == 33L) {
                    role_id_eq_33 = true;
                }
                if (peRoleUser.getRole_id() == 54L) {
                    role_id_eq_54 = true;
                }
                if (peRoleUser.getRole_id() == 55L) {
                    role_id_eq_55 = true;
                }
                if (peRoleUser.getRole_id() == 56L) {
                    role_id_eq_56 = true;
                }
                if (peRoleUser.getRole_id() == 43L) {
                    role_id_eq_43 = true;
                }
                if (peRoleUser.getRole_id() == 44L) {
                    role_id_eq_44 = true;
                }
                if (peRoleUser.getRole_id() == 46L) {
                    role_id_eq_46 = true;
                }
                if (peRoleUser.getRole_id() == 57L) {
                    role_id_eq_57 = true;
                }
                if (peRoleUser.getRole_id() == 30L) {
                    role_id_eq_30 = true;
                }
                if (peRoleUser.getRole_id() == 34L) {
                    role_id_eq_34 = true;
                }
                if (peRoleUser.getRole_id() == 36L) {
                    role_id_eq_36 = true;
                }
                if (peRoleUser.getRole_id() == 39L) {
                    role_id_eq_39 = true;
                }
                if (peRoleUser.getRole_id() == 40L) {
                    role_id_eq_40 = true;
                }
                if (peRoleUser.getRole_id() == 47L) {
                    role_id_eq_47 = true;
                }
                if (peRoleUser.getRole_id() == 49L) {
                    role_id_eq_49 = true;
                }
                if (peRoleUser.getRole_id() == 50L) {
                    role_id_eq_50 = true;
                }
            }
        }

        request.setAttribute("role_id_eq_34", role_id_eq_34);
        request.setAttribute("role_id_eq_36", role_id_eq_36);
        request.setAttribute("role_id_eq_39", role_id_eq_39);
        request.setAttribute("role_id_eq_40", role_id_eq_40);
        request.setAttribute("role_id_eq_49", role_id_eq_49);
        request.setAttribute("role_id_eq_47", role_id_eq_47);
        request.setAttribute("role_id_eq_50", role_id_eq_50);

        request.setAttribute("role_id_eq_33", role_id_eq_33);
        request.setAttribute("role_id_eq_54", role_id_eq_54);
        request.setAttribute("role_id_eq_55", role_id_eq_55);
        request.setAttribute("role_id_eq_56", role_id_eq_56);

        request.setAttribute("role_id_eq_38", role_id_eq_38);
        request.setAttribute("role_id_eq_41", role_id_eq_41);
        request.setAttribute("role_id_eq_43", role_id_eq_43);

        request.setAttribute("role_id_eq_44", role_id_eq_44);
        request.setAttribute("role_id_eq_46", role_id_eq_46);

        request.setAttribute("role_id_eq_30", role_id_eq_30);
        request.setAttribute("role_id_eq_57", role_id_eq_57);

        // 默认显示当前1月的时间区间
        Date today = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.add(Calendar.MONTH, -1);
        String day_first = df.format(calendar.getTime());
        String day_last = df.format(today);

        calendar.add(Calendar.DATE, 15);
        String day_before_15 = df.format(calendar.getTime());
        request.setAttribute("day_before_15", day_before_15);

        if (StringUtils.isNotBlank(order_date_start) && StringUtils.isNotBlank(order_date_end)) {
            Date start_date = DateUtils.parseDate(order_date_start, new String[] {"yyyy-MM-dd"});
            Date end_date = DateUtils.parseDate(order_date_end, new String[] {"yyyy-MM-dd"});
            // 判断下单日期间隔是否小于90天
            if ((end_date.getTime() - start_date.getTime()) > 90 * 24 * 60 * 60 * 1000L) { // 超过90天
                String msg = super.getMessage(request, "order.date.over.90.day");
                super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
                return null;
            }
        } else {
            dynaBean.set("order_date_start", day_first);// 默认当前日期的前7天
            dynaBean.set("order_date_end", day_last);
        }

        KonkaOrderInfo entity = new KonkaOrderInfo();
        entity.setIs_submit(1); // 0:表示暂存；1：表示正式提交
        entity.setIs_del(0);// 0:表示未删除；1：表示删除

        if (("1").equals(vbedl_null)) {
            entity.getMap().put("vbedl_null", vbedl_null);
        }
        if (("0").equals(vbedl_null)) {
            entity.getMap().put("vbedl_not_null", vbedl_null);
        }

        if (StringUtils.isNotBlank(order_date_start)) {
            entity.getMap().put("start_date", order_date_start + " 00:00:00");
        } else {
            entity.getMap().put("start_date", day_first + " 00:00:00");
        }
        if (StringUtils.isNotBlank(order_date_end)) {
            entity.getMap().put("end_date", order_date_end + " 23:59:59");
        } else {
            entity.getMap().put("end_date", day_last + " 23:59:59");
        }
        if (StringUtils.isNotBlank(trade_index_like)) {
            entity.getMap().put("trade_index_like", trade_index_like);
        }
        if (StringUtils.isNotBlank(process_id)) {
            entity.setProcess_id(Long.valueOf(process_id));
        }
        if (StringUtils.isNotBlank(user_shop_name_like)) {
            entity.getMap().put("user_shop_name_like", user_shop_name_like);
        }
        if (StringUtils.isNotBlank(pay_type)) {
            entity.setPay_type(Integer.valueOf(pay_type));
        }
        if (StringUtils.isNotBlank(r3_id)) {
            entity.setR3_id(Long.valueOf(r3_id));
        }
        if (StringUtils.isNotBlank(sync_state)) {
            entity.setSync_state(Integer.valueOf(sync_state));
        }

        if (is_th != null && is_th.equals("1")) {
            entity.setDoc_type("ZFRE");
        } else {
            if (StringUtils.isNotBlank(doc_type)) {
                entity.setDoc_type(doc_type);
            }
        }
        /**
         * 此字段实际含义不是字面含义订单类型,现被误用作订单来源<br>
         * 0在线下单<br>
         * 1图片下单<br>
         * 2触网转单<br>
         * 4从返利转<br>
         * 5DRP转入<br>
         */
        if (StringUtils.isNotBlank(order_type)) {
            entity.setOrder_type(Integer.valueOf(order_type));
        }
        if (StringUtils.isNotBlank(return_type)) {
        	entity.setReturn_type(return_type);
        }
        if (StringUtils.isNotBlank(send_type)) {
            entity.setSend_type(Integer.valueOf(send_type));
        }
        if (StringUtils.isNotBlank(pd_name_like)) {
            entity.getMap().put("pd_name_like", pd_name_like);
        }
        if (StringUtils.isNotBlank(vbedl_like)) {
            entity.getMap().put("vbedl_like", vbedl_like);
        }
        if (StringUtils.isNotBlank(is_delivered)) {
            entity.setIs_delivered(Integer.valueOf(is_delivered));
        }
        if (StringUtils.isNotBlank(ag_like)) {
            entity.getMap().put("ag_like", ag_like);
        }
        if (GenericValidator.isInt(or_audit_state)) {
            entity.setAudit_state(Integer.valueOf(or_audit_state));
        }
        if (StringUtils.isNotBlank(l5_dept_id)) {
            entity.getMap().put("par_dept_id", l5_dept_id);
        } else if (StringUtils.isNotBlank(l4_dept_id)) {
            entity.getMap().put("par_dept_id", l4_dept_id);
        } else if (StringUtils.isNotBlank(dept_id)) {
            entity.getMap().put("par_dept_id", dept_id);
        }
        if (StringUtils.isNotEmpty(is_change) && GenericValidator.isInt(is_change)) {
            entity.setIs_change(Integer.parseInt(is_change));
        }
        // 同步时间
        if (StringUtils.isNotBlank(sync_date_start)) {
            entity.getMap().put("sync_date_start", sync_date_start + " 00:00:00");
        }
        if (StringUtils.isNotBlank(sync_date_end)) {
            entity.getMap().put("sync_date_end", sync_date_end + " 23:59:59");
        }
        if (StringUtils.isNotBlank(sync_user)) {
            entity.setSync_user(sync_user);
        }
        if (GenericValidator.isInt(my_audit_state)) {
            // 按订单状态查询
            int state = Integer.valueOf(my_audit_state);
            switch (state) {
                case 10: // 系统管理员-审核中
                    entity.getMap().put("audit_state_lt", 3);
                    break;
                case 11: // 系统管理员-已完成
                    entity.getMap().put("audit_state_eq", 3);
                    break;
                case 20: // 非系统管理员-待审核
                    entity.getMap().put("audit_state_2", my_audit_state);
                    entity.getMap().put("audit_state_eq_20", my_audit_state);
                    break;
                case 21: // 非系统管理员-已审核
                    entity.getMap().put("audit_state_2", my_audit_state);
                    entity.getMap().put("audit_state_eq_21", my_audit_state);
                    break;
            }
        }

        if (StringUtils.isNotBlank(kh_confirm_state)) {
            entity.setKh_confirm_state(Integer.valueOf(kh_confirm_state));
        }
        if (StringUtils.isNotBlank(customer_type_index)) {
            entity.getMap().put("customer_type_index", customer_type_index);
        }

        long pagecount = 0;// 当前页的商品数量
        BigDecimal pageMoney = new BigDecimal(0);// 当前页的总价
        BigDecimal pagediscount = new BigDecimal(0);// 当前页的总折扣价

        // 系统管理员
        if (role_id_lt_30) {

            dynaBean.set("dept_type", "1");

            Long recordCount = super.getFacade().getKonkaOrderInfoService().getKonkaOrderInfoAndNextRoleCount(entity);
            KonkaOrderInfo numMoney = super.getFacade().getKonkaOrderInfoService().getKonkaOrderInfoNumCountMondySum(entity);

            pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
            entity.getRow().setCount(pager.getRowCount());
            entity.getRow().setFirst(pager.getFirstRow());

            List<KonkaOrderInfo> entityList = super.getFacade().getKonkaOrderInfoService().getKonkaOrderInfoAndNextRoleResultForPaginatedList(entity);


            // 从R3得到物流发货信息--ldy
            for (KonkaOrderInfo konkaOrderInfo : entityList) {
                Long r3_order_id = konkaOrderInfo.getR3_id();// 得到订单号
                if (r3_order_id != null) {
                    KonkaR3Zsof konkaR3Zsof = new KonkaR3Zsof();
                    konkaR3Zsof.setVbeln(r3_order_id);
                    konkaR3Zsof = super.getFacade().getKonkaR3ZsofService().getKonkaR3Zsof(konkaR3Zsof);
                    if (konkaR3Zsof != null) {
                        konkaOrderInfo.getMap().put("vbedl", konkaR3Zsof.getVbedl());// 026单号
                        if (!("0000-00-00").equals(konkaR3Zsof.getLfdat())) {// 如果有发货时间,发货状态改为已发货
                            konkaOrderInfo.getMap().put("lfdat", konkaR3Zsof.getLfdat());// 发货时间
                            if (!("0000-00-00").equals(konkaR3Zsof.getShdat())) {
                                konkaOrderInfo.getMap().put("shdat", konkaR3Zsof.getShdat());// 收货时间
                            }
                        }
                    }
                }
                // 催办信息
                KonkaOrderInfoMessageSend konkaorderinfomessage = new KonkaOrderInfoMessageSend();
                konkaorderinfomessage.setOrder_id(konkaOrderInfo.getId());
                Long messagecount = super.getFacade().getKonkaOrderInfoMessageSendService().getKonkaOrderInfoMessageSendCount(konkaorderinfomessage);
                konkaOrderInfo.getMap().put("messagecount", messagecount);
                if (messagecount != 0L) {
                    List<KonkaOrderInfoMessageSend> KonkaOrderInfoMessageSendList =
                            super.getFacade().getKonkaOrderInfoMessageSendService().getKonkaOrderInfoMessageSendList(konkaorderinfomessage);
                    if (KonkaOrderInfoMessageSendList != null) {
                        konkaOrderInfo.getMap().put("KonkaOrderInfoMessageSendList", KonkaOrderInfoMessageSendList);
                    }

                }

                // 得到分公司
                if (null != konkaOrderInfo.getAdd_dept_id()) {
                    KonkaDept konkaDept = new KonkaDept();
                    konkaDept.setDept_id(konkaOrderInfo.getAdd_dept_id());
                    konkaDept.getMap().put("dept_type_eq", 3);
                    konkaDept = super.getFacade().getKonkaDeptService().getKonkaDeptSuperiorByDeptId(konkaDept);
                    if (konkaDept != null) {
                        if (null != konkaDept.getDept_name() && !"".equals(konkaDept.getDept_name())) {
                            konkaOrderInfo.getMap().put("fgsName", konkaDept.getDept_name());
                        }
                    }
                }

                // 得到经办
                if (null != konkaOrderInfo.getAdd_dept_id()) {
                    KonkaDept konkaDept2 = new KonkaDept();
                    konkaDept2.setDept_id(konkaOrderInfo.getAdd_dept_id());
                    konkaDept2.getMap().put("dept_type_eq", 4);
                    konkaDept2 = super.getFacade().getKonkaDeptService().getKonkaDeptSuperiorByDeptId(konkaDept2);
                    if (null != konkaDept2 && !("".equals(konkaDept2))) {
                        String deptName = konkaDept2.getDept_name();
                        if (null != deptName && !("".equals(deptName))) {
                            konkaOrderInfo.getMap().put("jbName", konkaDept2.getDept_name());
                        }
                    } else {
                        KonkaDept konkaDept3 = new KonkaDept();
                        konkaDept3.setDept_id(konkaOrderInfo.getAdd_dept_id());
                        konkaDept3.getMap().put("dept_type_eq", 5);
                        konkaDept3 = super.getFacade().getKonkaDeptService().getKonkaDeptSuperiorByDeptId(konkaDept3);
                        if (konkaDept3 != null && null != konkaDept3.getDept_name() && !("".equals(konkaDept3.getDept_name()))) {
                            String deptName = konkaDept3.getDept_name();
                            if (null != deptName && !("".equals(deptName))) {
                                konkaOrderInfo.getMap().put("jbName", konkaDept3.getDept_name());
                            }
                        }
                    }
                }
                // 当前页商品数量
                pagecount = pagecount + (konkaOrderInfo.getOrder_num()==null?0L:konkaOrderInfo.getOrder_num());
                // 当前页商品价格
                pageMoney = pageMoney.add(konkaOrderInfo.getMoney()==null?new BigDecimal(0):konkaOrderInfo.getMoney());
                // 当前页商品折扣价格
                pagediscount = pagediscount.add(konkaOrderInfo.getGood_discount_price()==null?new BigDecimal(0):konkaOrderInfo.getGood_discount_price());
            }

            // 系统角色可以导出50000条数据
            if (StringUtils.isNotBlank(excel_all) && "1".equals(excel_all)) {
                if (recordCount.intValue() > 50000) {
                    renderJavaScript(response, "alert('" + super.getMessage(request, "export.too.many") + "！');history.back();");
                    return null;
                }
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/octet-stream");
                response.setHeader("Content-Disposition", "attachment;filename=" + EncryptUtils.encodingFileName("订单查询数据") + ".xls");
                entity.getRow().setCount(recordCount.intValue());
                List<KonkaOrderInfo> entityList1 = getFacade().getKonkaOrderInfoService().getKonkaOrderInfoAndNextRoleResultForPaginatedList(entity);
                dynaBean.set("excel_all", excel_all);

                // 从R3得到物流发货信息--ldy
                for (KonkaOrderInfo konkaOrderInfo : entityList1) {
                    Long r3_order_id = konkaOrderInfo.getR3_id();// 得到订单号
                    if (r3_order_id != null) {
                        KonkaR3Zsof konkaR3Zsof = new KonkaR3Zsof();
                        konkaR3Zsof.setVbeln(r3_order_id);
                        konkaR3Zsof = super.getFacade().getKonkaR3ZsofService().getKonkaR3Zsof(konkaR3Zsof);
                        if (konkaR3Zsof != null) {
                            konkaOrderInfo.getMap().put("vbedl", konkaR3Zsof.getVbedl());// 026单号
                            if (!("0000-00-00").equals(konkaR3Zsof.getLfdat())) {// 如果有发货时间,发货状态改为已发货
                                konkaOrderInfo.getMap().put("lfdat", konkaR3Zsof.getLfdat());// 发货时间
                                if (!("0000-00-00").equals(konkaR3Zsof.getShdat())) {
                                    konkaOrderInfo.getMap().put("shdat", konkaR3Zsof.getShdat());// 收货时间
                                }
                            }
                        }
                    }

                    // 得到分公司
                    if (null != konkaOrderInfo.getAdd_dept_id()) {
                        KonkaDept konkaDept6 = new KonkaDept();
                        konkaDept6.setDept_id(konkaOrderInfo.getAdd_dept_id());
                        konkaDept6.getMap().put("dept_type_eq", 3);
                        konkaDept6 = super.getFacade().getKonkaDeptService().getKonkaDeptSuperiorByDeptId(konkaDept6);
                        if (null != konkaDept6 && null != konkaDept6.getDept_name() && !"".equals(konkaDept6.getDept_name())) {
                            konkaOrderInfo.getMap().put("fgsName", konkaDept6.getDept_name());
                        }
                    }

                    // 得到经办
                    if (null != konkaOrderInfo.getAdd_dept_id()) {
                        KonkaDept konkaDept4 = new KonkaDept();
                        konkaDept4.setDept_id(konkaOrderInfo.getAdd_dept_id());
                        konkaDept4.getMap().put("dept_type_eq", 4);
                        konkaDept4 = super.getFacade().getKonkaDeptService().getKonkaDeptSuperiorByDeptId(konkaDept4);
                        if (null != konkaDept4 && !("".equals(konkaDept4))) {
                            String deptName = konkaDept4.getDept_name();
                            if (null != deptName && !("".equals(deptName))) {
                                konkaOrderInfo.getMap().put("jbName", konkaDept4.getDept_name());
                            }
                        } else {
                            KonkaDept konkaDept5 = new KonkaDept();
                            konkaDept5.setDept_id(konkaOrderInfo.getAdd_dept_id());
                            konkaDept5.getMap().put("dept_type_eq", 5);
                            konkaDept5 = super.getFacade().getKonkaDeptService().getKonkaDeptSuperiorByDeptId(konkaDept5);
                            if (null != konkaDept5 && !("".equals(konkaDept5))) {
                                String deptName = konkaDept5.getDept_name();
                                if (null != deptName && !("".equals(deptName))) {
                                    konkaOrderInfo.getMap().put("jbName", konkaDept5.getDept_name());
                                }
                            }
                        }
                    }

                }
                if (null != entityList1) {
                    for (KonkaOrderInfo orderInfo : entityList1) {
                        // 0： 待审核 1: 已审核
                        orderInfo.getMap().put("states", role_ids.contains(orderInfo.getNext_audit_role_id()) ? 0 : 1);
                    }
                }
                request.setAttribute("allList", entityList1);
                if (is_th != null && is_th.equals("1")) {
                    return new ActionForward("/admin/KonkaOrderSearch/listTHForReport.jsp");
                } else {
                    return new ActionForward("/admin/KonkaOrderSearch/listForReport.jsp");
                }
            }

            request.setAttribute("entityList", entityList);
            request.setAttribute("numCount", numMoney.getMap().get("numcount"));
            request.setAttribute("moneyCount", numMoney.getMap().get("moneycount"));
            request.setAttribute("discount", numMoney.getMap().get("discount"));
        } else { // 非系统管理员登录一般为分公司管理员

            dynaBean.set("dept_type", "2");

            // 数据级别判断开始
            Long __dept_id = userInfo.getDept_id(); // 默认为当前用户所在部门
            int max_dlevel = super.getMaxDLevel(userInfo.getId()); // 获取当前用户的最高可视部门级别
            request.setAttribute("max_dlevel", max_dlevel);
            switch (max_dlevel) {
                case 9:
                    // 集团及以下部门可见
                    __dept_id = 0L; // 0表示部门根节点，即“多媒体事业本部”
                    break;
                case 8:
                    // 分公司及以下部门可见
                    KonkaDept dept_fgs = super.getKonkaDeptForFgs(Long.valueOf(__dept_id)); // 查询部门分公司
                    if (null != dept_fgs && null != dept_fgs.getDept_id()) {
                        __dept_id = dept_fgs.getDept_id(); // 分公司部门ID
                        entity.getMap().put("dept_id_start", __dept_id);
                        request.setAttribute("current_fgs_code", __dept_id);
                        request.setAttribute("show_fgs", true);
                    }
                    break;
                case 7:
                    // 我所在的部门及以下部门可见
                    __dept_id = userInfo.getDept_id(); // 默认为当前用户所在部门
                    entity.getMap().put("dept_id_start", __dept_id);
                    entity.getMap().put("par_dept_id", __dept_id);
                    request.setAttribute("current_fgs_code", __dept_id);
                    request.setAttribute("current_dept_code", __dept_id);
                    request.setAttribute("show_fgs", true);
                    request.setAttribute("show_fgs_jb", true);
                    break;
                case 0:
                    entity.getMap().put("querybycust_userid_eq", userInfo.getId());
                    request.setAttribute("show_fgs_gr", true);
                    request.setAttribute("show_fgs", true);
                    request.setAttribute("show_fgs_jb", true);
                    break;
                default:
                    // 出错
            }

            entity.getMap().put("session_user_id", userInfo.getId());// 获取当前客户所查看的数据部门
            entity.getMap().put("par_dept_id", __dept_id);
            if (StringUtils.isNotBlank(l5_dept_id)) {
                entity.getMap().put("par_dept_id", l5_dept_id);
            } else if (StringUtils.isNotBlank(l4_dept_id)) {
                entity.getMap().put("par_dept_id", l4_dept_id);
            } else if (StringUtils.isNotBlank(dept_id)) {
                entity.getMap().put("par_dept_id", dept_id);
            }

            request.setAttribute("current_fgs_code", super.getKonkaDeptForFgs(Long.valueOf(__dept_id)).getDept_id());

            // TODO 看上去有问题
            if (StringUtils.isBlank(my_audit_state) && (max_dlevel >= 7)) {
                // 这些角色可见分公司所有订单数据，加audit_state的空判断是为了设置仅在未选择“审核状态”查询条件的时候这些角色可以查看该分公司的全部数据
                // KonkaDept fgs_dept =
                // super.getSuperiorForDeptType(userInfo.getDept_id(), 3);
                // if (null != fgs_dept) {
                // entity.getMap().put("par_or_children_dept_id",
                // fgs_dept.getDept_id());
                // }
            } else {
                entity.getMap().put("no_sys_man_user_id", userInfo.getId()); // 表示需要当前用户角色审核的订单
                // entity.getMap().put("par_or_children_dept_id",
                // userInfo.getDept_id());
            }

            Long recordCount = super.getFacade().getKonkaOrderInfoService().getKonkaOrderInfoWithNoSysManCount(entity);
            KonkaOrderInfo numMoney = super.getFacade().getKonkaOrderInfoService().getKonkaOrderInfoNumCountMondySum1(entity);
            pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
            entity.getRow().setCount(pager.getRowCount());
            entity.getRow().setFirst(pager.getFirstRow());

            List<KonkaOrderInfo> entityList = super.getFacade().getKonkaOrderInfoService().getKonkaOrderInfoWithNoSysManPaginatedList(entity);

            // 从R3得到物流发货信息--ldy
            for (KonkaOrderInfo konkaOrderInfo : entityList) {
                Long r3_order_id = konkaOrderInfo.getR3_id();// 得到订单号
                if (r3_order_id != null) {
                    KonkaR3Zsof konkaR3Zsof = new KonkaR3Zsof();
                    konkaR3Zsof.setVbeln(r3_order_id);
                    konkaR3Zsof = super.getFacade().getKonkaR3ZsofService().getKonkaR3Zsof(konkaR3Zsof);
                    if (konkaR3Zsof != null) {
                        konkaOrderInfo.getMap().put("vbedl", konkaR3Zsof.getVbedl());// 026单号
                        if (!("0000-00-00").equals(konkaR3Zsof.getLfdat())) {// 如果有发货时间,发货状态改为已发货
                            konkaOrderInfo.getMap().put("lfdat", konkaR3Zsof.getLfdat());// 发货时间
                            if (!("0000-00-00").equals(konkaR3Zsof.getShdat())) {
                                konkaOrderInfo.getMap().put("shdat", konkaR3Zsof.getShdat());// 收货时间
                            }
                        }
                    }
                }

                // 得到分公司
                if (null != konkaOrderInfo.getAdd_dept_id()) {
                    KonkaDept konkaDept = new KonkaDept();
                    konkaDept.setDept_id(konkaOrderInfo.getAdd_dept_id());
                    konkaDept.getMap().put("dept_type_eq", 3);
                    konkaDept = super.getFacade().getKonkaDeptService().getKonkaDeptSuperiorByDeptId(konkaDept);
                    if (null != konkaDept && null != konkaDept.getDept_name() && !"".equals(konkaDept.getDept_name())) {
                        konkaOrderInfo.getMap().put("fgsName", konkaDept.getDept_name());
                    }
                }

                // 得到经办
                if (null != konkaOrderInfo.getAdd_dept_id()) {
                    KonkaDept konkaDept2 = new KonkaDept();
                    konkaDept2.setDept_id(konkaOrderInfo.getAdd_dept_id());
                    konkaDept2.getMap().put("dept_type_eq", 4);
                    konkaDept2 = super.getFacade().getKonkaDeptService().getKonkaDeptSuperiorByDeptId(konkaDept2);
                    if (null != konkaDept2 && !("".equals(konkaDept2))) {
                        String deptName = konkaDept2.getDept_name();
                        if (null != deptName && !("".equals(deptName))) {
                            konkaOrderInfo.getMap().put("jbName", konkaDept2.getDept_name());
                        }
                    } else {
                        KonkaDept konkaDept3 = new KonkaDept();
                        konkaDept3.setDept_id(konkaOrderInfo.getAdd_dept_id());
                        konkaDept3.getMap().put("dept_type_eq", 5);
                        konkaDept3 = super.getFacade().getKonkaDeptService().getKonkaDeptSuperiorByDeptId(konkaDept3);
                        if (null != konkaDept3 && !"".equals(konkaDept3)) {
                            String deptName = konkaDept3.getDept_name();
                            if (null != deptName && !("".equals(deptName))) {
                                konkaOrderInfo.getMap().put("jbName", konkaDept3.getDept_name());
                            }
                        }
                    }
                }
                // 0： 待审核 1: 已审核
                konkaOrderInfo.getMap().put("states", role_ids.contains(konkaOrderInfo.getNext_audit_role_id()) ? 0 : 1);
                // 当前页商品数量
                pagecount = pagecount + konkaOrderInfo.getOrder_num();
                // 当前页商品价格
                pageMoney = pageMoney.add(konkaOrderInfo.getMoney());
            }
            request.setAttribute("numCount", numMoney.getMap().get("numcount"));
            request.setAttribute("moneyCount", numMoney.getMap().get("moneycount"));
            request.setAttribute("discount", numMoney.getMap().get("discount"));
            // 非系统角色只导出5000条
            if (StringUtils.isNotBlank(excel_all) && "1".equals(excel_all)) {
                if (recordCount.intValue() > 5000) {
                    renderJavaScript(response, "alert('" + super.getMessage(request, "export.too.many") + "！');history.back();");
                    return null;
                }  
                
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/octet-stream");
                response.setHeader("Content-Disposition", "attachment;filename=" + EncryptUtils.encodingFileName("订单数据") + ".xls");
                entity.getRow().setCount(recordCount.intValue());
                List<KonkaOrderInfo> entityList1 = getFacade().getKonkaOrderInfoService().getKonkaOrderInfoWithNoSysManPaginatedList(entity);
                dynaBean.set("excel_all", excel_all);

                // 从R3得到物流发货信息--ldy
                for (KonkaOrderInfo konkaOrderInfo : entityList1) {
                    Long r3_order_id = konkaOrderInfo.getR3_id();// 得到订单号
                    if (r3_order_id != null) {
                        KonkaR3Zsof konkaR3Zsof = new KonkaR3Zsof();
                        konkaR3Zsof.setVbeln(r3_order_id);
                        konkaR3Zsof = super.getFacade().getKonkaR3ZsofService().getKonkaR3Zsof(konkaR3Zsof);
                        if (konkaR3Zsof != null) {
                            konkaOrderInfo.getMap().put("vbedl", konkaR3Zsof.getVbedl());// 026单号
                            if (!("0000-00-00").equals(konkaR3Zsof.getLfdat())) {// 如果有发货时间,发货状态改为已发货
                                konkaOrderInfo.getMap().put("lfdat", konkaR3Zsof.getLfdat());// 发货时间
                                if (!("0000-00-00").equals(konkaR3Zsof.getShdat())) {
                                    konkaOrderInfo.getMap().put("shdat", konkaR3Zsof.getShdat());// 收货时间
                                }
                            }
                        }
                    }

                    // 得到分公司
                    if (null != konkaOrderInfo.getAdd_dept_id()) {
                        KonkaDept konkaDept6 = new KonkaDept();
                        konkaDept6.setDept_id(konkaOrderInfo.getAdd_dept_id());
                        konkaDept6.getMap().put("dept_type_eq", 3);
                        konkaDept6 = super.getFacade().getKonkaDeptService().getKonkaDeptSuperiorByDeptId(konkaDept6);
                        if (null != konkaDept6 && null != konkaDept6.getDept_name() && !"".equals(konkaDept6.getDept_name())) {
                            konkaOrderInfo.getMap().put("fgsName", konkaDept6.getDept_name());
                        }
                    }

                    // 得到经办
                    if (null != konkaOrderInfo.getAdd_dept_id()) {
                        KonkaDept konkaDept4 = new KonkaDept();
                        konkaDept4.setDept_id(konkaOrderInfo.getAdd_dept_id());
                        konkaDept4.getMap().put("dept_type_eq", 4);
                        konkaDept4 = super.getFacade().getKonkaDeptService().getKonkaDeptSuperiorByDeptId(konkaDept4);
                        if (null != konkaDept4 && !("".equals(konkaDept4))) {
                            String deptName = konkaDept4.getDept_name();
                            if (null != deptName && !("".equals(deptName))) {
                                konkaOrderInfo.getMap().put("jbName", konkaDept4.getDept_name());
                            }
                        } else {
                            KonkaDept konkaDept5 = new KonkaDept();
                            konkaDept5.setDept_id(konkaOrderInfo.getAdd_dept_id());
                            konkaDept5.getMap().put("dept_type_eq", 5);
                            konkaDept5 = super.getFacade().getKonkaDeptService().getKonkaDeptSuperiorByDeptId(konkaDept5);
                            if (null != konkaDept5 && !("".equals(konkaDept5))) {
                                String deptName = konkaDept5.getDept_name();
                                if (null != deptName && !("".equals(deptName))) {
                                    konkaOrderInfo.getMap().put("jbName", konkaDept5.getDept_name());
                                }
                            }
                        }
                    }

                    // 0： 待审核 1: 已审核
                    konkaOrderInfo.getMap().put("states", role_ids.contains(konkaOrderInfo.getNext_audit_role_id()) ? 0 : 1);

                }

                request.setAttribute("allList", entityList1);
                if (is_th != null && is_th.equals("1")) {
                    return new ActionForward("/admin/KonkaOrderSearch/listTHForReport.jsp");
                } else {
                    return new ActionForward("/admin/KonkaOrderSearch/listForReport.jsp");
                }
            }

            // 获取订单数据的催办信息列表
            for (KonkaOrderInfo konkaorderinfo : entityList) {
                // 查待审核角色名称
                KonkaOrderAuditProcessNode kapn = new KonkaOrderAuditProcessNode();
                if (konkaorderinfo.getNext_node_id() != null && !"".equals(konkaorderinfo.getNext_node_id())) {
                    kapn.setId(konkaorderinfo.getNext_node_id());
                    if (konkaorderinfo.getNext_audit_role_id() != null && !"".equals(konkaorderinfo.getNext_audit_role_id())) {
                        kapn.setRole_id(konkaorderinfo.getNext_audit_role_id());
                    }

                    kapn = super.getFacade().getKonkaOrderAuditProcessNodeService().getKonkaOrderAuditProcessNode(kapn);
                    if (kapn != null) {
                        konkaorderinfo.getMap().put("next_audit_role_name", kapn.getRole_name());
                    }
                }
                KonkaOrderInfoMessageSend konkaorderinfomessage = new KonkaOrderInfoMessageSend();
                konkaorderinfomessage.setOrder_id(konkaorderinfo.getId());
                Long messagecount = super.getFacade().getKonkaOrderInfoMessageSendService().getKonkaOrderInfoMessageSendCount(konkaorderinfomessage);
                konkaorderinfo.getMap().put("messagecount", messagecount);
                if (messagecount != 0L) {
                    List<KonkaOrderInfoMessageSend> KonkaOrderInfoMessageSendList =
                            super.getFacade().getKonkaOrderInfoMessageSendService().getKonkaOrderInfoMessageSendList(konkaorderinfomessage);
                    if (KonkaOrderInfoMessageSendList != null) {
                        konkaorderinfo.getMap().put("KonkaOrderInfoMessageSendList", KonkaOrderInfoMessageSendList);
                    }
                }
            }

            request.setAttribute("entityList", entityList);
            request.setAttribute("numCount", numMoney.getMap().get("numcount"));
            request.setAttribute("moneyCount", numMoney.getMap().get("moneycount"));
            request.setAttribute("discount", numMoney.getMap().get("discount"));

        } // 按角色区别数据权限结束

        // 获取流程列表
        KonkaOrderAuditProcess process = new KonkaOrderAuditProcess();
        if (!role_id_lt_30) {
            // 分公司可见自己的流程
            KonkaDept dept = super.getSuperiorForDeptType(userInfo.getDept_id(), 3);
            process.getMap().put("par_add_dept_id", dept.getDept_id());
            process.setIs_del(0);
            List<KonkaOrderAuditProcess> processList = super.getFacade().getKonkaOrderAuditProcessService().getKonkaOrderAuditProcessList(process);

            KonkaOrderAuditProcess ap_public = new KonkaOrderAuditProcess();
            ap_public.setAdd_dept_id(0L);
            ap_public.setIs_del(0);
            List<KonkaOrderAuditProcess> ap_publicauditProcesseList = getFacade().getKonkaOrderAuditProcessService().getKonkaOrderAuditProcessList(ap_public);
            processList.addAll(ap_publicauditProcesseList);
            request.setAttribute("processList", processList);
        } else {
            // 超级管理员
            process.setIs_del(0);
            List<KonkaOrderAuditProcess> processList = super.getFacade().getKonkaOrderAuditProcessService().getKonkaOrderAuditProcessList(process);
            request.setAttribute("processList", processList);
        }

        // 客户类型
        KonkaCategory kc = new KonkaCategory();
        kc.setC_type(10);
        request.setAttribute("konkaCategoryList", super.getFacade().getKonkaCategoryService().getKonkaCategoryList(kc));
        // 当前页商品数量
        request.setAttribute("pageCount", pagecount);
        // 当前页商品价格
        request.setAttribute("pageMoney", pageMoney);
        request.setAttribute("pageDiscount", pagediscount);


        if (is_th != null && is_th.equals("1")) {
            return new ActionForward("/admin/KonkaOrderSearch/listTH.jsp");
        }


        return mapping.findForward("list");
    }

    @SuppressWarnings("unchecked")
    public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        setNaviStringToRequestScope(form, request);
        DynaBean dynaBean = (DynaBean) form;
        String order_id = (String) dynaBean.get("order_id");// 查询类型

        // 设置订单进度条
        setOrderProgress(form, request);

        KonkaOrderInfo order = new KonkaOrderInfo();
        order.setId(Long.valueOf(order_id));
        order = super.getFacade().getKonkaOrderInfoService().getKonkaOrderInfo(order);

        KonkaR3Shop konkaR3Shop = new KonkaR3Shop();
        konkaR3Shop.setId(order.getCust_id());
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
        dynaBean.set("fullName", super.getPIndexName(konkaOrderInfo.getUser_p_index(), ""));

        KonkaOrderInfoDetails konkaOrderInfoDetails = new KonkaOrderInfoDetails();
        konkaOrderInfoDetails.setOrder_id(Long.valueOf(order_id));
        List<KonkaOrderInfoDetails> konkaOrderInfoDetailsList = getFacade().getKonkaOrderInfoDetailsService().getKonkaOrderInfoDetailsList(konkaOrderInfoDetails);

        double good_price_total = 0.00;// 单价总和
        double good_sum_price_total = 0.00;// 金额总和
        double good_discount_total = 0.00;// 折让总和
        double good_discount_price_total = 0.00;// 单台折让金额总和
        double good_discount_pricr_total_all = 0.00;// 折让总金额求和
        double s_dis_price = 0.00;
        for (KonkaOrderInfoDetails ks : konkaOrderInfoDetailsList) {
            if (ks.getGood_price() != null) {
                good_price_total += ks.getGood_price().doubleValue();
            }
            if (ks.getGood_discount() != null) {
                good_discount_total += ks.getGood_discount().doubleValue();
            }
            if (ks.getGood_discount_price() != null) {
                good_discount_price_total += ks.getGood_discount_price().doubleValue();
            }
            if (ks.getGood_sum_price() != null) {
                good_sum_price_total += ks.getGood_sum_price().doubleValue();
            }
            if (ks.getGood_discount_price() != null) {
                good_discount_pricr_total_all += (ks.getGood_discount_price().doubleValue());
            }

            s_dis_price += (ks.getGood_discount().divide(new BigDecimal(100))).multiply(ks.getGood_sum_price()).doubleValue() + ks.getGood_discount_price().doubleValue();

        }
        request.setAttribute("s_dis_price", s_dis_price);
        request.setAttribute("good_price_total", good_price_total);
        request.setAttribute("good_sum_price_total", good_sum_price_total);
        request.setAttribute("good_discount_total", good_discount_total);
        request.setAttribute("good_discount_price_total", good_discount_price_total);
        request.setAttribute("good_discount_pricr_total_all", good_discount_pricr_total_all);
        request.setAttribute("konkaOrderInfoDetailsList", konkaOrderInfoDetailsList);

        // 取订单流程
        if (konkaOrderInfo != null && konkaOrderInfo.getDoc_type().equals("ZFRE")) {// 判断如果是退货订单
            KonkaDept dept = super.getKonkaDeptByCustomerId(Long.valueOf(order.getCust_id()));
            if (dept != null) {
                KonkaOrderAuditProcess process = new KonkaOrderAuditProcess();
                process.getMap().put("par_add_dept_id", dept.getDept_id());
                List<KonkaOrderAuditProcess> processList = super.getFacade().getKonkaOrderAuditProcessService().getKonkaOrderAuditProcessList(process);

                KonkaOrderAuditProcess ap_public = new KonkaOrderAuditProcess();
                ap_public.setAdd_dept_id(0L);
                List<KonkaOrderAuditProcess> ap_publicauditProcesseList = getFacade().getKonkaOrderAuditProcessService().getKonkaOrderAuditProcessList(ap_public);
                processList.addAll(ap_publicauditProcesseList);
                request.setAttribute("processList", processList);
            }
        } else {
            KonkaDept dept = super.getKonkaDeptByCustomerId(Long.valueOf(order.getCust_id()));
            if (dept != null) {
                KonkaOrderAuditProcess process = new KonkaOrderAuditProcess();
                process.getMap().put("par_add_dept_id", dept.getDept_id());
                process.setIs_del(0);
                List<KonkaOrderAuditProcess> processList = super.getFacade().getKonkaOrderAuditProcessService().getKonkaOrderAuditProcessList(process);

                KonkaOrderAuditProcess ap_public = new KonkaOrderAuditProcess();
                ap_public.setAdd_dept_id(0L);
                ap_public.setIs_del(0);
                List<KonkaOrderAuditProcess> ap_publicauditProcesseList = getFacade().getKonkaOrderAuditProcessService().getKonkaOrderAuditProcessList(ap_public);
                processList.addAll(ap_publicauditProcesseList);
                request.setAttribute("processList", processList);
            }
        }

        dynaBean.set("userName", order.getAdd_user_name());

        /** 取网点业务员 */
        BranchAssign bagn = new BranchAssign();
        bagn.setKonka_r3_id(order.getCust_id());
        bagn = getFacade().getBranchAssignService().getBranchAssign(bagn);
        if (null != bagn && null != bagn.getUser_id()) {
            PeProdUser ywy = new PeProdUser();
            ywy.setId(bagn.getUser_id());
            ywy = getFacade().getPeProdUserService().getPeProdUserResult(ywy);
            request.setAttribute("ywy_user_name", ywy.getReal_name());
        }

        if (super.isCallR3Interface(request)) {
            String sales_org = StringUtils.isBlank(fgsSN2) ? fgsSN : fgsSN2;
            List<KNVP> knvpList = new ArrayList<KNVP>();
            ReturnInfo<KNVP> info = new ReturnInfo<KNVP>();
            info = super.getFacade().getR3WebInterfaceService().getKnvpsList(sales_org, Constants.default_vtweg, Constants.default_spart, konkaR3Shop.getR3_code());
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
                    } else {
                        //
                    }
                }
            }
            request.setAttribute("agList", agList);
            request.setAttribute("reList", reList);
            request.setAttribute("rgList", rgList);
            request.setAttribute("weList", weList);
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
        request.setAttribute("call_r3_interface", super.isCallR3Interface(request));

        // 获取审核修改记录
        KonkaOrderInfoUpdateRecord updateRecord = new KonkaOrderInfoUpdateRecord();
        updateRecord.setTrade_index(order.getTrade_index());
        List<KonkaOrderInfoUpdateRecord> updateRecordGroupList = super.getFacade().getKonkaOrderInfoUpdateRecordService().getKonkaOrderInfoUpdateRecordGroupList(updateRecord);

        if (null != updateRecordGroupList && updateRecordGroupList.size() > 0) {

            for (KonkaOrderInfoUpdateRecord temp : updateRecordGroupList) {
                KonkaOrderInfoUpdateRecord _record = new KonkaOrderInfoUpdateRecord();
                _record.setAdd_date(temp.getAdd_date());
                _record.setTrade_index(order.getTrade_index());
                _record.getMap().put("order_by_pd_id_asc", true);
                List<KonkaOrderInfoUpdateRecord> recordList = super.getFacade().getKonkaOrderInfoUpdateRecordService().getKonkaOrderInfoUpdateRecordList(_record);
                for (KonkaOrderInfoUpdateRecord rr : recordList) {
                    double aa = 0f;
                    if (!this.eq_0(rr.getPrice_after().doubleValue())) {
                        aa = rr.getDiscount_after().doubleValue() / rr.getPrice_after().doubleValue() * 10000 / 100;
                    }
                    BigDecimal bb = new BigDecimal(aa);
                    rr.setGood_discount_after(bb);
                }
                temp.getMap().put("recordList", recordList);
            }

            request.setAttribute("updateRecordGroupList", updateRecordGroupList);

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
                }

                request.setAttribute("applyDetailsList", applyDetailsList);
            }

        } else {
            KonkaOrderInfoDetails details = new KonkaOrderInfoDetails();
            details.setOrder_id(Long.valueOf(order_id));
            details.getMap().put("order_by_pd_id_asc", true);
            List<KonkaOrderInfoDetails> applyDetailsList = super.getFacade().getKonkaOrderInfoDetailsService().getKonkaOrderInfoDetailsList(details);

            request.setAttribute("applyDetailsList", applyDetailsList);

        }

        request.setAttribute("weeks", check_for_stock("check_for_stock") == 0 ? 4 : check_for_stock("check_for_stock"));

        if (konkaOrderInfo != null && konkaOrderInfo.getDoc_type().equals("ZFRE")) {// 判断如果是退货订单
            return new ActionForward("/../manager/admin/KonkaOrderSearch/viewReturn.jsp");
        }

        return mapping.findForward("view");
    }

    /**
     * <p>
     * 订单已经保存，检查订单上的产品的库存<br>
     * 订单提交或者审核订单时检查
     * </p>
     * 
     */
    public ActionForward checkStockForAjax(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        DynaBean dynaBean = (DynaBean) form;
        String order_id = (String) dynaBean.get("order_id");// 查询类型
        String sales_org = (String) dynaBean.get("sales_org");// 销售组织
        KonkaOrderInfoDetails konkaOrderInfoDetails = new KonkaOrderInfoDetails();
        if (StringUtils.isBlank(order_id)) {
            return null;
        }
        konkaOrderInfoDetails.setOrder_id(Long.valueOf(order_id));
        List<KonkaOrderInfoDetails> konkaOrderInfoDetailsList = getFacade().getKonkaOrderInfoDetailsService().getKonkaOrderInfoDetailsList(konkaOrderInfoDetails);

        // add by zhou
        List<StockCheckResult> checkResult2 = new ArrayList<StockCheckResult>();
        ReturnInfo<StockCheckResult> info = new ReturnInfo<StockCheckResult>();
        for (KonkaOrderInfoDetails line : konkaOrderInfoDetailsList) {
            List<StockCheckResult> cResult = new ArrayList<StockCheckResult>();
            if (line.getStore_key() != null && !"".equals(line.getStore_key().trim())) {
                int beginIndex = line.getStore_key().indexOf("[");
                int endIndex = line.getStore_key().indexOf("]");
                String storeAndLoc = line.getStore_key().substring(beginIndex + 1, endIndex);
                String s[] = storeAndLoc.split("-");
                String store = s[0];
                String loc = s[1];
                info = getFacade().getR3WebInterfaceService().checkStock2(store, loc, line.getPd_name());
                if (info.getSuccess()) cResult = info.getDataResult();
                if (cResult.size() > 0) {
                    checkResult2.add(cResult.get(0));
                }
            } else {
                List<KonkaR3OrderLines> itemList = new ArrayList<KonkaR3OrderLines>();
                KonkaR3OrderLines konkaR3OrderLines = new KonkaR3OrderLines();
                konkaR3OrderLines.setMaterial_code(line.getPd_name());
                konkaR3OrderLines.setReview_amount(new BigDecimal(line.getGood_count()));
                itemList.add(konkaR3OrderLines);
                info = getFacade().getR3WebInterfaceService().checkStock(sales_org, itemList);
                if (info.getSuccess()) cResult = info.getDataResult();
                if (cResult.size() > 0) {
                    checkResult2.add(cResult.get(0));
                }
            }
        }
        logger.info("json string : {}", JSON.toJSONString(checkResult2));
        super.renderJson(response, JSON.toJSONString(checkResult2));
        return null;
    }

    /**
     * <p>
     * 订单已经保存，检查订单上的产品的库存<br>
     * 订单提交或者审核订单时检查
     * </p>
     * 
     */
    public ActionForward stockCheckBeforeSync2Sap(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        DynaBean dynaBean = (DynaBean) form;
        String order_id = (String) dynaBean.get("order_id");//
        String sales_org = (String) dynaBean.get("sales_org");// 销售组织
        KonkaOrderInfoDetails konkaOrderInfoDetails = new KonkaOrderInfoDetails();
        if (StringUtils.isEmpty(order_id) || StringUtils.isEmpty(sales_org)) {
            return null;
        }
        konkaOrderInfoDetails.setOrder_id(Long.valueOf(order_id));
        List<KonkaOrderInfoDetails> konkaOrderInfoDetailsList = getFacade().getKonkaOrderInfoDetailsService().getKonkaOrderInfoDetailsList(konkaOrderInfoDetails);

        // add by zhou
        List<StockCheckResult> checkResult2 = new ArrayList<StockCheckResult>();
        ReturnInfo<StockCheckResult> info = new ReturnInfo<StockCheckResult>();
        for (KonkaOrderInfoDetails line : konkaOrderInfoDetailsList) {
            List<StockCheckResult> cResult = new ArrayList<StockCheckResult>();
            if (line.getStore_key() != null && !"".equals(line.getStore_key().trim())) {
                int beginIndex = line.getStore_key().indexOf("[");
                int endIndex = line.getStore_key().indexOf("]");
                String storeAndLoc = line.getStore_key().substring(beginIndex + 1, endIndex);
                String s[] = storeAndLoc.split("-");
                String store = s[0];
                String loc = s[1];

                // 不一样的sap库存接口
                info = getFacade().getR3WebInterfaceService().checkStock2(store, loc, line.getPd_name());

                if (info.getSuccess()) cResult = info.getDataResult();
                if (cResult.size() > 0) {
                    checkResult2.add(cResult.get(0));
                }
            } else {
                List<KonkaR3OrderLines> itemList = new ArrayList<KonkaR3OrderLines>();
                KonkaR3OrderLines konkaR3OrderLines = new KonkaR3OrderLines();
                konkaR3OrderLines.setMaterial_code(line.getPd_name());
                konkaR3OrderLines.setReview_amount(new BigDecimal(line.getGood_count()));
                itemList.add(konkaR3OrderLines);
                // 不一样的sap库存接口
                info = getFacade().getR3WebInterfaceService().checkStock(sales_org, itemList);
                if (info.getSuccess()) cResult = info.getDataResult();
                if (cResult.size() > 0) {
                    checkResult2.add(cResult.get(0));
                }
            }
        }

        // isok:1,0
        // msg :机型z需求12,供给22 ;机型x需求22,供给22 ;

        HashMap<String, Object> resutlMap = new HashMap<String, Object>();
        StringBuilder sb = new StringBuilder();

        for (KonkaOrderInfoDetails d : konkaOrderInfoDetailsList) {
            for (StockCheckResult r : checkResult2) {
                if (d.getPd_name().equals(r.getMatnr())) {
                    if (d.getGood_count() > r.getLamount().intValue()) {
                        // 库存不足的机型
                        if (d.getStore_key() != null && !"".equals(d.getStore_key())) {
                            sb.append("\n").append(d.getPd_name()).append("需").append(d.getGood_count()).append("台,").append(d.getStore_key()).append("库存")
                                    .append(r.getLamount().intValue()).append(";");
                        } else {
                            sb.append("\n").append(d.getPd_name()).append("需").append(d.getGood_count()).append("台,").append("库存").append(r.getLamount().intValue()).append(";");
                        }
                    }
                }
            }
        }

        if (sb.length() > 0) {
            resutlMap.put("isok", "0");
            resutlMap.put("msg", sb.append("\n").toString());
        } else {
            resutlMap.put("isok", "1");
            resutlMap.put("msg", "订单所有机型库存充足!");
        }
        super.renderJson(response, JSON.toJSONString(resutlMap));
        return null;
    }

    public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        DynaBean dynaBean = (DynaBean) form;
        String mod_id = (String) dynaBean.get("mod_id");
        String order_id = (String) dynaBean.get("id");

        if (!GenericValidator.isLong(order_id)) {
            saveError(request, "errors.long", new String[] {order_id});
            return mapping.findForward("list");
        }

        KonkaOrderInfo entity = new KonkaOrderInfo();
        entity.setId(Long.valueOf(order_id));
        entity.setIs_del(1);
        super.getFacade().getKonkaOrderInfoService().modifyKonkaOrderInfo(entity);

        saveMessage(request, "entity.deleted");

        StringBuffer pathBuffer = new StringBuffer();
        pathBuffer.append(mapping.findForward("success").getPath());
        pathBuffer.append("&mod_id=" + mod_id);
        pathBuffer.append("&");
        pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "id", "method")));
        ActionForward forward = new ActionForward(pathBuffer.toString(), true);
        return forward;
    }

    /**
     * 
     * 删除R3订单
     * 
     * @author Wang,Kunlin
     * @author zhou
     * @date 2014-03-31
     */
    public ActionForward cancel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        DynaBean dynaBean = (DynaBean) form;
        String mod_id = (String) dynaBean.get("mod_id");
        String order_id = (String) dynaBean.get("id");
        String r3_id = (String) dynaBean.get("r3_id");
        String cust_id = (String) dynaBean.get("cust_id");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        PeProdUser userInfo = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

        if (null == userInfo) {
            super.renderJavaScript(response, "alert('抱歉session丢失,请您重新登录!');history.back();");
            return null;
        }

        if (!GenericValidator.isLong(order_id)) {
            saveError(request, "errors.long", new String[] {order_id});
            return mapping.findForward("list");
        }

        if (StringUtil.isEmpty(r3_id)) {
            KonkaOrderInfo entity = new KonkaOrderInfo();
            entity.setId(Long.valueOf(order_id));
            entity.setAudit_state(4);
            entity.setCust_id(Long.valueOf(cust_id));
            // entity.setIs_del(1);
            super.getFacade().getKonkaOrderInfoService().modifyKonkaOrderInfo(entity);
            saveMessage(request, "entity.cancel.success");
        } else {
            String opername = userInfo.getUser_name();
            if (StringUtil.isEmpty(opername)) {
                saveError(request, "errors.long", new String[] {opername});
                return mapping.findForward("list");
            }
            ReturnInfo returninfo = super.getFacade().getR3WebInterfaceService().saveKonkaOrderInfoDestory(r3_id, opername);
            if (returninfo != null) {
                if (returninfo.getSuccess()) {
                    KonkaOrderInfo entity = new KonkaOrderInfo();
                    entity.setId(Long.valueOf(order_id));
                    entity.setAudit_state(4);
                    entity.setCust_id(Long.valueOf(cust_id));

                    entity.setSync_date(sdf.format(new Date()));
                    if (userInfo != null) {
                        entity.setSync_user(userInfo.getUser_name() == null ? "system" : userInfo.getUser_name());
                    }
                    entity.setSync_state(1);
                    super.getFacade().getKonkaOrderInfoService().modifyKonkaOrderInfo(entity);
                    saveMessage(request, "entity.cancel.success");
                } else {
                    String errorMsg = "";
                    for (ExcuteMsg msg : (List<ExcuteMsg>) returninfo.getMsgList()) {
                        errorMsg += msg.getType() + ":" + msg.getMessage() + "\n";
                    }
                    saveDirectlyError(request, errorMsg);

                }
            }
        }
        StringBuffer pathBuffer = new StringBuffer();
        pathBuffer.append(mapping.findForward("success").getPath());
        pathBuffer.append("&mod_id=" + mod_id);
        pathBuffer.append("&");
        pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "id", "method")));
        ActionForward forward = new ActionForward(pathBuffer.toString(), true);
        return forward;
    }

    /**
     * @author zhouhaojie
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward odrerTb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        DynaBean dynaBean = (DynaBean) form;
        String mod_id = (String) dynaBean.get("mod_id");
        String order_id = (String) dynaBean.get("id");
        String is_th = (String) dynaBean.get("is_th");

        PeProdUser userInfo = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        if (null == userInfo) {
            super.renderJavaScript(response, "alert('抱歉session丢失,请您重新登录!');history.back();");
            return null;
        }

        if (!GenericValidator.isLong(order_id)) {
            saveError(request, "errors.long", new String[] {order_id});
            return mapping.findForward("list");
        }

        KonkaOrderInfo order = new KonkaOrderInfo();
        order.setId(Long.valueOf(order_id));
        order = super.getFacade().getKonkaOrderInfoService().getKonkaOrderInfo(order);
        // 取客户的销售组织
        if (null != order.getCust_id()) {
            KonkaR3Shop kR3Shop = new KonkaR3Shop();

            kR3Shop.setId(order.getCust_id());
            kR3Shop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(kR3Shop);
            if (kR3Shop != null) order.setSales_org(kR3Shop.getR3_sales_org_code());
        }

        KonkaOrderInfoDetails d = new KonkaOrderInfoDetails();
        d.setOrder_id(order.getId());
        List<KonkaOrderInfoDetails> konkaOrderInfoDetailsList = super.getFacade().getKonkaOrderInfoDetailsService().getKonkaOrderInfoDetailsList(d);

        List<KonkaOrderInfoDetails> konkaOrderInfoDetailsList2 = new ArrayList<KonkaOrderInfoDetails>();
        // 商品数量为0的产品行记录不同步到R3中--begin
        for (KonkaOrderInfoDetails konkaorderinfodetails : konkaOrderInfoDetailsList) {
            if (konkaorderinfodetails.getGood_count() == null || konkaorderinfodetails.getGood_count() == 0) {
                konkaOrderInfoDetailsList2.add(konkaorderinfodetails);
            }
        }
        konkaOrderInfoDetailsList.removeAll(konkaOrderInfoDetailsList2);
        // 商品数量为0的产品行记录不同步到R3中--end

        // 生成订单行项目号10,20,30
        // 订单保存入口太多.所以在此处理
        // 为订单数量不为0的订单行生成连续的项目号
        if (konkaOrderInfoDetailsList.size() > 0) {
            long i = 0;
            for (KonkaOrderInfoDetails k : konkaOrderInfoDetailsList) {
                i = i + 10;
                if (k.getItemno() == null || k.getItemno() == 0L) {
                    k.setItemno(i);
                    super.getFacade().getKonkaOrderInfoDetailsService().modifyKonkaOrderInfoDetails(k);
                }
            }
            for (KonkaOrderInfoDetails ko : konkaOrderInfoDetailsList2) {
                i = i + 10;
                if (ko.getItemno() == null || ko.getItemno() == 0L) {
                    ko.setItemno(i);
                    super.getFacade().getKonkaOrderInfoDetailsService().modifyKonkaOrderInfoDetails(ko);
                }
            }
        }

        // 重新过滤订单数量为0的数据,已生成itemNO
        konkaOrderInfoDetailsList = super.getFacade().getKonkaOrderInfoDetailsService().getKonkaOrderInfoDetailsList(d);
        List<KonkaOrderInfoDetails> konkaOrderInfoDetailsList3 = new ArrayList<KonkaOrderInfoDetails>();
        // 商品数量为0的产品行记录不同步到R3中--begin
        for (KonkaOrderInfoDetails konkaorderinfodetails : konkaOrderInfoDetailsList) {
            if (konkaorderinfodetails.getGood_count() == null || konkaorderinfodetails.getGood_count() == 0) {
                konkaOrderInfoDetailsList3.add(konkaorderinfodetails);
            }
        }
        konkaOrderInfoDetailsList.removeAll(konkaOrderInfoDetailsList3);
        order.setKonkaOrderInfoDetailsList(konkaOrderInfoDetailsList);

        // 把订单写R3订单接口
        ReturnInfo ret = new ReturnInfo();

        if (order.getR3_id() != null && order.getR3_id() != 0) {
            // 修改订单 已有61单号
            ret =
                    super.getFacade().getR3WebInterfaceService()
                            .modifyKonkaOrderInfo(order, String.valueOf(order.getR3_id()), (userInfo.getUser_name() == null ? "system" : userInfo.getUser_name()));
        } else {
            if ("ZFRE".equals(order.getDoc_type())) {
                // 新增退货订单 ZFRE
                ret = super.getFacade().getR3WebInterfaceService().saveKonkaOrderInfoReturn(order, userInfo.getUser_name());
            } else {
                // 新增订单ZFOR,ZFGC,ZFCR
                ret = super.getFacade().getR3WebInterfaceService().saveKonkaOrderInfo(order, (userInfo.getUser_name() == null ? "system" : userInfo.getUser_name()));
            }
        }

        if (ret.getSuccess()) {
            // 客户订单
            KonkaOrderInfo updated = new KonkaOrderInfo();
            updated.setId(order.getId());
            updated.setR3_id(Long.valueOf(ret.getItemNO()));
            updated.setSync_state(1);
            updated.setSync_date(sdf.format(new Date()));
            updated.setSync_user((userInfo.getUser_name() == null ? "system" : userInfo.getUser_name()));
            if (order.getR3_id() != null && order.getR3_id() != 0 && null != order.getIs_change() && order.getIs_change() == 1) {
                updated.setIs_change(2);// 订单变更并且同步过
            }
            if (order.getZmd_order_flag() == 1) {
                // 专卖店订单
                KonkaXxSellBill entity = new KonkaXxSellBill();
                entity.setSell_bill_id(order.getZmd_order_num());
                PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
                entity.setAudit_date(new Date());
                entity.setAudit_user_id(ui.getId());
                entity.setAudit_real_name(ui.getReal_name());
                entity.setDist_date(new Date());
                entity.setSell_state(20L);
                updated.setKonkaXxSellBill(entity);
            }
            super.getFacade().getKonkaOrderInfoService().modifyKonkaOrderInfoForconfirm(updated);
            saveDirectlyMessage(request, "成功同步订单:" + ret.getItemNO());

        } else {
            // 无法生成r3订单,返回异常信息!
            List<ExcuteMsg> msgList = ret.getMsgList();
            StringBuilder sb = new StringBuilder();
            int i = 0;
            if (null != msgList) {
                for (ExcuteMsg e : msgList) {
                    sb.append(++i).append(". [").append(e.getType()).append("] ").append(e.getMessage()).append("<br />");
                }
            }
            super.saveError(request, "error.order.r3.unupload", sb.toString());
        }

        // the line below is added for pagination
        StringBuffer pathBuffer = new StringBuffer();
        pathBuffer.append(mapping.findForward("success").getPath());
        if (StringUtils.isNotBlank(is_th)) {
            pathBuffer.append("&is_th=1");
        }
        pathBuffer.append("&mod_id=" + mod_id);
        pathBuffer.append("&");
        pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "id", "method")));
        ActionForward forward = new ActionForward(pathBuffer.toString(), true);
        return forward;

    }

    /**
     * 将审批完结的订单加入drp订单池
     */
    public ActionForward orderTbDRP(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        DynaBean dynaBean = (DynaBean) form;
        String mod_id = (String) dynaBean.get("mod_id");
        String index = (String) dynaBean.get("index");
        String id = (String) dynaBean.get("id");

        PeProdUser userInfo = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

        
        if (id == null || "".equals(id)) {
            super.saveDirectlyError(request, "订单ID不小心丢掉了.");
            return mapping.findForward("list");
        }
        if (index == null || "".equals(index)) {
            super.saveDirectlyError(request, "订单流水号不小心丢掉了.");
            return mapping.findForward("list");
        }
        int result = -1;
        result = super.getFacade().getFromDrpOrderService().createFromData2ToData(index);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        if (result == 0) {
            // 客户订单
            KonkaOrderInfo updated = new KonkaOrderInfo();
            updated.setId(Long.valueOf(id));// !important
            updated.setTrade_index(index);
            updated.setSync_state(1);
            updated.setSync_date(sdf.format(new Date()));
            updated.setSync_user((userInfo.getUser_name() == null ? "system" : userInfo.getUser_name()));
            super.getFacade().getKonkaOrderInfoService().modifyKonkaOrderInfoForconfirm(updated);
            super.saveDirectlyMessage(request, "订单信息已成功加入DRP订单同步队列,2分钟后将更新至DRP系统.");
        } else {
            super.saveDirectlyError(request, "由于渠道系统订单信息不完整,无法将订单转入DRP同步队列.");
            return mapping.findForward("list");
        }

        StringBuffer pathBuffer = new StringBuffer();
        pathBuffer.append(mapping.findForward("success").getPath());
        pathBuffer.append("&mod_id=" + mod_id);
        pathBuffer.append("&");
        pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "id", "method")));
        ActionForward forward = new ActionForward(pathBuffer.toString(), true);
        return forward;
    }

    private boolean eq_0(double f) {
        double EPSINON = 0.0000000001f;
        return f < EPSINON && f > -EPSINON;
    }

    // 同步所有订单物流信息
    public ActionForward ZbSync(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        DynaBean dynaBean = (DynaBean) form;
        String mod_id = (String) dynaBean.get("mod_id");
        String is_th = (String) dynaBean.get("is_th");

        PeProdUser userInfo = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
        PeRoleUser _peRoleUser = new PeRoleUser();
        _peRoleUser.setUser_id(userInfo.getId());
        List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);
        Boolean role_id_lt_30 = false;
        List<Long> role_ids = new ArrayList<Long>(); // 当前登录用户的角色列表
        for (PeRoleUser peRoleUser : peRoleUserList) {
            role_ids.add(peRoleUser.getRole_id());

            if (peRoleUser.getRole_id() >= 0L && peRoleUser.getRole_id() < 30L) {
                role_id_lt_30 = true;
                continue;
            }
        }

        // 添加记录日志
        OperLog operLog = new OperLog();
        operLog.setLink_tab("KONKA_R3_ZSOF");
        operLog.setOper_time(new Date());
        operLog.setOper_uname(userInfo.getUser_name());
        operLog.setPpdm_name("开始执行手动同步物流信息");
        operLog.setOper_type("同步物流信息 ");
        operLog.setLink_id(Long.parseLong("888887"));
        operLog.setOper_ip(userInfo.getLast_login_ip());
        operLog.setOper_uid(userInfo.getId());
        super.getFacade().getOperLogService().createOperLog(operLog);

        // 拿到需要同步的订单
        KonkaOrderInfo entity = new KonkaOrderInfo();
        List<KonkaOrderInfo> entityList = new ArrayList<KonkaOrderInfo>();
        if (!role_id_lt_30) {// 如果不是系统管理员
            int max_dlevel = super.getMaxDLevel(userInfo.getId()); // 获取当前用户的最高可视部门级别
            request.setAttribute("max_dlevel", max_dlevel);
            logger.info("Max level : {}", max_dlevel);
            switch (max_dlevel) {
                case 9:
                    // 集团及以下部门可见
                    entity.getMap().put("par_dept_id", 0L); // 0表示部门根节点，即“多媒体事业本部”
                    break;
                case 8:
                    // 分公司及以下部门可见
                    KonkaDept dept_fgs = super.getKonkaDeptForFgs(Long.valueOf(userInfo.getDept_id())); // 查询部门分公司
                    if (null != dept_fgs && null != dept_fgs.getDept_id()) {
                        entity.getMap().put("par_dept_id", dept_fgs.getDept_id()); // 分公司部门ID
                    }
                    break;
                case 7:
                    // 我所在的部门及以下部门可见
                    entity.getMap().put("par_dept_id", userInfo.getDept_id()); // 默认为当前用户所在部门
                    break;
                case 0:
                    break;
                default:
                    // 出错
            }
            entity.setIs_sh(0);
            entity.setSync_state(1);
            entity.getMap().put("r3_id_not_null", "y");
            entityList = super.getFacade().getKonkaOrderInfoService().getKonkaOrderInfoList(entity);
        } else {// 如果是系统管理员
            entity.setIs_sh(0);
            entity.setSync_state(1);
            entity.getMap().put("r3_id_not_null", "y");
            entityList = super.getFacade().getKonkaOrderInfoService().getKonkaOrderInfoList(entity);
        }

        // 从R3得到物流发货信息--ldy
        for (KonkaOrderInfo konkaOrderInfo : entityList) {
            Long r3_order_id = konkaOrderInfo.getR3_id();// 得到订单号
            if (r3_order_id != null) {
                // 得到本地ZSOF数据：
                KonkaR3Zsof konkaR3Zsof = new KonkaR3Zsof();
                konkaR3Zsof.setVbeln(r3_order_id);
                konkaR3Zsof = super.getFacade().getKonkaR3ZsofService().getKonkaR3Zsof(konkaR3Zsof);

                ZSOF zsof = new ZSOF();
                // can be null
                zsof = super.getFacade().getR3WebInterfaceService().getR3Delivery(r3_order_id);

                // 本地无记录:
                // zsof.getVBEDL() 物流号
                if (konkaR3Zsof == null && zsof != null && zsof.getVBEDL() != null) {
                    KonkaR3Zsof konkaR3Zsof2 = new KonkaR3Zsof();
                    konkaR3Zsof2.setVbedl(zsof.getVBEDL());
                    if (!("0000-00-00").equals(zsof.getLFDAT())) {
                        konkaR3Zsof2.setLfdat(zsof.getLFDAT());
                    }
                    if (!("0000-00-00").equals(zsof.getSHDAT())) {
                        konkaR3Zsof2.setShdat(zsof.getSHDAT());
                    }
                    konkaR3Zsof2.setErdat(zsof.getERDAT());
                    konkaR3Zsof2.setGjahr(zsof.getGJAHR());
                    konkaR3Zsof2.setKunnr(zsof.getKUNNR());
                    konkaR3Zsof2.setMonat(zsof.getMONAT());
                    konkaR3Zsof2.setName1(zsof.getNAME1());
                    konkaR3Zsof2.setVbeln(zsof.getVBELN());
                    konkaR3Zsof2.setVkorg(zsof.getVKORG());
                    konkaR3Zsof2.setVtext(zsof.getVTEXT());
                    super.getFacade().getKonkaR3ZsofService().createKonkaR3Zsof(konkaR3Zsof2);
                }

                // 本地已经有记录:
                if (konkaR3Zsof != null && zsof != null && zsof.getVBEDL() != null) {
                    konkaR3Zsof.setVbeln(r3_order_id);
                    konkaR3Zsof.setVbedl(zsof.getVBEDL());
                    if (!("0000-00-00").equals(zsof.getLFDAT())) {
                        konkaR3Zsof.setLfdat(zsof.getLFDAT());
                    }
                    if (!("0000-00-00").equals(zsof.getSHDAT())) {
                        konkaR3Zsof.setShdat(zsof.getSHDAT());
                    }
                    konkaR3Zsof.setErdat(zsof.getERDAT());
                    konkaR3Zsof.setGjahr(zsof.getGJAHR());
                    konkaR3Zsof.setKunnr(zsof.getKUNNR());
                    konkaR3Zsof.setMonat(zsof.getMONAT());
                    konkaR3Zsof.setName1(zsof.getNAME1());
                    konkaR3Zsof.setVbeln(zsof.getVBELN());
                    konkaR3Zsof.setVkorg(zsof.getVKORG());
                    konkaR3Zsof.setVtext(zsof.getVTEXT());
                    super.getFacade().getKonkaR3ZsofService().modifyKonkaR3Zsof(konkaR3Zsof);
                }
            }
        }

        int count = 0;
        for (KonkaOrderInfo konkaOrderInfo : entityList) {
            KonkaR3Zsof of = new KonkaR3Zsof();
            of.setVbeln(konkaOrderInfo.getR3_id());
            of = super.getFacade().getKonkaR3ZsofService().getKonkaR3Zsof(of);
            if (of != null && konkaOrderInfo.getId() != null) {
                KonkaOrderInfo k = new KonkaOrderInfo();
                k.setId(konkaOrderInfo.getId());
                // k.setR3_id(konkaOrderInfo.getR3_id());
                if (of.getLfdat() != null && !"".equals(of.getLfdat())) {
                    k.setIs_delivered(1);
                }
                if (of.getShdat() != null && !"".equals(of.getShdat())) {
                    k.setIs_sh(1);
                }
                super.getFacade().getKonkaOrderInfoService().modifyKonkaOrderInfoForconfirm(k);
                count++;
            }
        }

        // 添加记录日志
        operLog.setLink_tab("KONKA_R3_ZSOF");
        operLog.setOper_time(new Date());
        operLog.setOper_uname(userInfo.getUser_name());
        operLog.setPpdm_name("结束执行手动同步物流信息");
        operLog.setOper_type("同步物流信息 ");
        operLog.setLink_id(Long.parseLong("888887"));
        operLog.setOper_ip(userInfo.getLast_login_ip());
        operLog.setOper_uid(userInfo.getId());
        super.getFacade().getOperLogService().createOperLog(operLog);

        saveDirectlyMessage(request, "订单物流信息更新成功,条数为:" + count);
        StringBuffer pathBuffer = new StringBuffer();
        pathBuffer.append(mapping.findForward("success").getPath());
        if (StringUtils.isNotBlank(is_th)) {
            pathBuffer.append("&is_th=1");
        }
        pathBuffer.append("&mod_id=" + mod_id);
        pathBuffer.append("&");
        ActionForward forward = new ActionForward(pathBuffer.toString(), true);
        return forward;
    }

    // 单条同步物流信息
    public ActionForward orderWl(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        DynaBean dynaBean = (DynaBean) form;
        String mod_id = (String) dynaBean.get("mod_id");
        String order_id = (String) dynaBean.get("id");// 单的主键ID
        if (!GenericValidator.isLong(order_id)) {
            saveError(request, "errors.long", new String[] {order_id});
            return mapping.findForward("list");
        }

        KonkaOrderInfo konkaOrderInfo = new KonkaOrderInfo();
        konkaOrderInfo.setId(Long.valueOf(order_id));
        konkaOrderInfo = super.getFacade().getKonkaOrderInfoService().getKonkaOrderInfo(konkaOrderInfo);

        Long r3_order_id = konkaOrderInfo.getR3_id();

        // 得到本地ZSOF数据：
        KonkaR3Zsof localzsof = new KonkaR3Zsof();
        localzsof.setVbeln(r3_order_id);
        localzsof = super.getFacade().getKonkaR3ZsofService().getKonkaR3Zsof(localzsof);

        // 得到R3物流数据
        ZSOF zsof = new ZSOF();
        zsof = super.getFacade().getR3WebInterfaceService().getR3Delivery(r3_order_id);
        // 本地无记录:
        // zsof.getVBEDL() 物流号
        if (localzsof == null && zsof != null && zsof.getVBEDL() != null) {
            KonkaR3Zsof konkaR3Zsof2 = new KonkaR3Zsof();
            konkaR3Zsof2.setVbedl(zsof.getVBEDL());
            if (!("0000-00-00").equals(zsof.getLFDAT())) {
                konkaR3Zsof2.setLfdat(zsof.getLFDAT());
            }
            if (!("0000-00-00").equals(zsof.getSHDAT())) {
                konkaR3Zsof2.setShdat(zsof.getSHDAT());
            }
            konkaR3Zsof2.setErdat(zsof.getERDAT());
            konkaR3Zsof2.setGjahr(zsof.getGJAHR());
            konkaR3Zsof2.setKunnr(zsof.getKUNNR());
            konkaR3Zsof2.setMonat(zsof.getMONAT());
            konkaR3Zsof2.setName1(zsof.getNAME1());
            konkaR3Zsof2.setVbeln(zsof.getVBELN());
            konkaR3Zsof2.setVkorg(zsof.getVKORG());
            konkaR3Zsof2.setVtext(zsof.getVTEXT());
            super.getFacade().getKonkaR3ZsofService().createKonkaR3Zsof(konkaR3Zsof2);
        }

        // 本地已经有记录,则更新信息
        if (localzsof != null && zsof != null) {
            if (zsof.getVBEDL() != null || !"".equals(zsof.getVBEDL())) {
                localzsof.setVbeln(r3_order_id);
                localzsof.setVbedl(zsof.getVBEDL());
                if (!("0000-00-00").equals(zsof.getLFDAT())) {
                    localzsof.setLfdat(zsof.getLFDAT());
                }
                if (!("0000-00-00").equals(zsof.getSHDAT())) {
                    localzsof.setShdat(zsof.getSHDAT());
                }
                localzsof.setErdat(zsof.getERDAT());
                localzsof.setGjahr(zsof.getGJAHR());
                localzsof.setKunnr(zsof.getKUNNR());
                localzsof.setMonat(zsof.getMONAT());
                localzsof.setName1(zsof.getNAME1());
                localzsof.setVbeln(zsof.getVBELN());
                localzsof.setVkorg(zsof.getVKORG());
                localzsof.setVtext(zsof.getVTEXT());
                super.getFacade().getKonkaR3ZsofService().modifyKonkaR3Zsof(localzsof);
            }
        } else if (localzsof != null && zsof == null) {
            if (localzsof.getVbedl() != null) {
                super.getFacade().getKonkaR3ZsofService().removeKonkaR3Zsof(localzsof);
            }
        } else {
            // do nothing
        }

        // 重新取物流数据
        KonkaR3Zsof of = new KonkaR3Zsof();
        of.setVbeln(konkaOrderInfo.getR3_id());
        of = super.getFacade().getKonkaR3ZsofService().getKonkaR3Zsof(of);
        int count = 0;
        if (of != null && konkaOrderInfo.getId() != null) {
            KonkaOrderInfo k = new KonkaOrderInfo();
            k.setId(konkaOrderInfo.getId());
            if (of.getLfdat() != null && !"".equals(of.getLfdat())) {
                k.setIs_delivered(1);
            } else {
                k.setIs_delivered(0);
            }
            if (of.getShdat() != null && !"".equals(of.getShdat())) {
                k.setIs_sh(1);
            } else {
                k.setIs_sh(0);
            }
            super.getFacade().getKonkaOrderInfoService().modifyKonkaOrderInfoForconfirm(k);
            count++;
        }
        saveDirectlyMessage(request, "订单物流信息更新成功,条数为:" + count);

        StringBuffer pathBuffer = new StringBuffer();
        pathBuffer.append(mapping.findForward("success").getPath());
        pathBuffer.append("&mod_id=" + mod_id);
        pathBuffer.append("&");
        pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "id", "method")));
        ActionForward forward = new ActionForward(pathBuffer.toString(), true);
        return forward;
    }

    public ActionForward sendmsg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        DynaBean dynaBean = (DynaBean) form;
        String order_id = (String) dynaBean.get("order_id");

        if (StringUtil.isEmpty(order_id)) {
            return null;
        }
        // 判断是否要发短信
        KonkaOrderInfo order1 = new KonkaOrderInfo();
        order1.setId(Long.valueOf(order_id));
        order1 = super.getFacade().getKonkaOrderInfoService().getKonkaOrderInfo(order1);
        /** 取网点业务员 */

        KonkaOrderInfoMessageSend t = new KonkaOrderInfoMessageSend();
        BranchAssign bagn = new BranchAssign();
        bagn.setKonka_r3_id(order1.getCust_id());
        bagn = getFacade().getBranchAssignService().getBranchAssign(bagn);
        if (null != bagn && null != bagn.getUser_id()) {
            PeProdUser sender = new PeProdUser();
            sender.setId(bagn.getUser_id());
            sender = getFacade().getPeProdUserService().getPeProdUserResult(sender);
            t.setSender_name(sender.getReal_name());
            t.setSender_code(sender.getId().toString());
            t.setSender_phone(sender.getLink_phone());
        }
        t.setOrder_id(Long.valueOf(order_id));
        PeProdUser receiver = new PeProdUser();
        receiver.setId(order1.getAdd_user_id());
        receiver = super.getFacade().getPeProdUserService().getPeProdUserResult(receiver);
        t.setReceiver_name(receiver.getReal_name());
        t.setReceiver_code(receiver.getId().toString());
        if (null != receiver.getLink_phone()) {
            t.setReceiver_phone(receiver.getLink_phone().trim());
        }
        t.setSend_date(new Date());
        if (null != receiver.getLink_phone()) {
            SimpleDateFormat dateformat2 = new SimpleDateFormat("yyyy年MM月dd日");
            String date = dateformat2.format(new Date());
            String msg =
                    receiver.getReal_name() + "您好,您的订单[" + order1.getTrade_index() + "]已于[" + date + " ]进行内容变更，请使用您的账号登陆查询![http://vip.konka.com]。若有疑问，请及时联系康佳业务员（"
                            + t.getSender_name() + "）[" + t.getSender_phone() + "]";
            if (getSendMessageResult(receiver.getLink_phone(), msg)) {
                super.getFacade().getKonkaOrderInfoMessageSendService().createKonkaOrderInfoMessageSend(t);
                order1.setKh_confirm_state(1);
                super.getFacade().getKonkaOrderInfoService().modifyKonkaOrderInfo(order1);
                EcMessage ecm = new EcMessage();
                ecm.setAdd_date(new Date());
                ecm.setContent(msg);
                ecm.setMobile(receiver.getLink_phone());
                ecm.setOrder_id(order1.getId().toString());
                ecm.setSend_date(new Date());
                ecm.setOrder_state(1);
                ecm.setSend_state(0);
                super.getFacade().getEcMessageService().createEcMessage(ecm);
            }
        }

        JSONObject jonsObject = new JSONObject();
        KonkaOrderInfoMessageSend konkaorderinfomessage = new KonkaOrderInfoMessageSend();
        konkaorderinfomessage.setOrder_id(Long.valueOf(order_id));
        Long messagecount = super.getFacade().getKonkaOrderInfoMessageSendService().getKonkaOrderInfoMessageSendCount(konkaorderinfomessage);
        if (messagecount != null) {
            jonsObject.put("messagecount", messagecount);
            List<KonkaOrderInfoMessageSend> KonkaOrderInfoMessageSendList =
                    super.getFacade().getKonkaOrderInfoMessageSendService().getKonkaOrderInfoMessageSendList(konkaorderinfomessage);
            if (KonkaOrderInfoMessageSendList != null) {
                jonsObject.put("konkaorderinfomessagesendlist", KonkaOrderInfoMessageSendList);

            }
        }
        super.renderJson(response, jonsObject.toString());
        return null;

    }

    public ActionForward listdetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        setNaviStringToRequestScope(form, request);

        DynaBean dynaBean = (DynaBean) form;
        Pager pager = (Pager) dynaBean.get("pager");
        String kh_confirm_state = (String) dynaBean.get("kh_confirm_state");
        String order_date_start = (String) dynaBean.get("order_date_start");
        String order_date_end = (String) dynaBean.get("order_date_end");
        String trade_index_like = (String) dynaBean.get("trade_index_like");// 流程类型
        String user_shop_name_like = (String) dynaBean.get("user_shop_name_like");// 订单审核状态
        String process_id = (String) dynaBean.get("process_id");
        String dept_id = (String) dynaBean.get("dept_id");
        String l4_dept_id = (String) dynaBean.get("l4_dept_id");
        String l5_dept_id = (String) dynaBean.get("l5_dept_id");
        String pay_type = (String) dynaBean.get("pay_type");
        String send_type = (String) dynaBean.get("send_type");
        String audit_state = (String) dynaBean.get("audit_state");
        String pd_name_like = (String) dynaBean.get("pd_name_like");
        String is_delivered = (String) dynaBean.get("is_delivered");
        String ag_like = (String) dynaBean.get("ag_like");// 订单表里的ag 就是客户的R3编码
        String r3_id = (String) dynaBean.get("r3_id");
        String sync_state = (String) dynaBean.get("sync_state");
        String excel_all = (String) dynaBean.get("excel_all");
        String doc_type = (String) dynaBean.get("doc_type");
        String order_type = (String) dynaBean.get("order_type");
        String is_th = (String) dynaBean.get("is_th");// 为1 的时候是退货
        String vbedl_like = (String) dynaBean.get("vbedl_like");// 26单号
        String vbedl_null = (String) dynaBean.get("vbedl_null");
        // 同步人、同步时间
        String sync_user = (String) dynaBean.get("sync_user");
        String sync_date_start = (String) dynaBean.get("sync_date_start");
        String sync_date_end = (String) dynaBean.get("sync_date_end");

        PeProdUser userInfo = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
        PeRoleUser _peRoleUser = new PeRoleUser();
        _peRoleUser.setUser_id(userInfo.getId());
        List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

        dynaBean.set("user_id", userInfo.getId());// 用户

        boolean role_id_lt_30 = false; // 是否为系统管理员
        boolean role_id_eq_38 = false; // 是否为分公司产品会计
        boolean role_id_eq_41 = false; // 是否为青岛产品会计
        boolean role_id_eq_33 = false; // 是否为泉州产品会计
        boolean role_id_eq_54 = false; // 是否为乐山产品会计
        boolean role_id_eq_55 = false; // 是否为西昌产品会计
        boolean role_id_eq_56 = false; // 是否为广元产品会计
        boolean role_id_eq_43 = false; // 是否为万州产品会计
        boolean role_id_eq_44 = false; // 是否为芜湖产品会计
        boolean role_id_eq_46 = false; // 是否为温州产品会计

        boolean role_id_eq_57 = false; // 是否为分公司物流经理
        boolean role_id_eq_30 = false; // 是否为分公司管理员

        /*
         * 1 33 分公司职务-厦门 泉州产品会计 2014-04-0 17:48:57 权限 | 人员 | 授权 | 修改 | 删除 38 系统职务 分公司产品会计 013-08-0
         * 09:58:50 权限 | 人员 | 授权 | 修改 | 删除 3 41 系统职务 青岛产品会计 013-10-31 17:54:56 权限 | 人员 | 授权 | 修改 |
         * 删除 4 54 分公司职务-成都 乐山产品会计 014-03-8 10:4:31 权限 | 人员 | 授权 | 修改 | 删除 5 55 分公司职务-成都 西昌产品会计
         * 014-03-8 10:4:51 权限 | 人员 | 授权 | 修改 | 删除 6 56 分公司职务-成都 广元产品会计
         */

        List<Long> role_ids = new ArrayList<Long>(); // 当前登录用户的角色列表
        for (PeRoleUser peRoleUser : peRoleUserList) {
            role_ids.add(peRoleUser.getRole_id());

            if (peRoleUser.getRole_id() >= 0L && peRoleUser.getRole_id() < 30L) {
                role_id_lt_30 = true;
            }
            if (peRoleUser.getRole_id() == 38L) {
                role_id_eq_38 = true;
            }
            if (peRoleUser.getRole_id() == 41L) {
                role_id_eq_41 = true;
            }

            if (peRoleUser.getRole_id() == 33L) {
                role_id_eq_33 = true;
            }
            if (peRoleUser.getRole_id() == 54L) {
                role_id_eq_54 = true;
            }
            if (peRoleUser.getRole_id() == 55L) {
                role_id_eq_55 = true;
            }
            if (peRoleUser.getRole_id() == 56L) {
                role_id_eq_56 = true;
            }
            if (peRoleUser.getRole_id() == 43L) {
                role_id_eq_43 = true;
            }
            if (peRoleUser.getRole_id() == 44L) {
                role_id_eq_44 = true;
            }
            if (peRoleUser.getRole_id() == 46L) {
                role_id_eq_46 = true;
            }
            if (peRoleUser.getRole_id() == 57L) {
                role_id_eq_57 = true;
            }
            if (peRoleUser.getRole_id() == 30L) {
                role_id_eq_30 = true;
            }

        }

        request.setAttribute("role_id_eq_33", role_id_eq_33);
        request.setAttribute("role_id_eq_54", role_id_eq_54);
        request.setAttribute("role_id_eq_55", role_id_eq_55);
        request.setAttribute("role_id_eq_56", role_id_eq_56);

        request.setAttribute("role_id_eq_38", role_id_eq_38);
        request.setAttribute("role_id_eq_41", role_id_eq_41);
        request.setAttribute("role_id_eq_43", role_id_eq_43);

        request.setAttribute("role_id_eq_44", role_id_eq_44);
        request.setAttribute("role_id_eq_46", role_id_eq_46);

        request.setAttribute("role_id_eq_30", role_id_eq_30);
        request.setAttribute("role_id_eq_57", role_id_eq_57);

        // 默认显示当前1月的时间区间
        Date today = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.add(Calendar.MONTH, -1);
        String day_first = df.format(calendar.getTime());
        String day_last = df.format(today);

        if (StringUtils.isNotBlank(order_date_start) && StringUtils.isNotBlank(order_date_end)) {
            Date start_date = DateUtils.parseDate(order_date_start, new String[] {"yyyy-MM-dd"});
            Date end_date = DateUtils.parseDate(order_date_end, new String[] {"yyyy-MM-dd"});
            // 判断下单日期间隔是否小于90天
            if ((end_date.getTime() - start_date.getTime()) > 90 * 24 * 60 * 60 * 1000L) { // 超过90天
                String msg = super.getMessage(request, "order.date.over.90.day");
                super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
                return null;
            }
        } else {
            dynaBean.set("order_date_start", day_first);// 默认当前日期的前7天
            dynaBean.set("order_date_end", day_last);
        }

        KonkaOrderInfo entity = new KonkaOrderInfo();
        entity.setIs_submit(1); // 0:表示暂存；1：表示正式提交
        entity.setIs_del(0);// 0:表示未删除；1：表示删除

        if (("1").equals(vbedl_null)) {
            entity.getMap().put("vbedl_null", vbedl_null);
        }
        if (("0").equals(vbedl_null)) {
            entity.getMap().put("vbedl_not_null", vbedl_null);
        }

        if (StringUtils.isNotBlank(order_date_start)) {
            entity.getMap().put("start_date", order_date_start + " 00:00:00");
        } else {
            entity.getMap().put("start_date", day_first + " 00:00:00");
        }
        if (StringUtils.isNotBlank(order_date_end)) {
            entity.getMap().put("end_date", order_date_end + " 23:59:59");
        } else {
            entity.getMap().put("end_date", day_last + " 23:59:59");
        }
        if (StringUtils.isNotBlank(trade_index_like)) {
            entity.getMap().put("trade_index_like", trade_index_like);
        }
        if (StringUtils.isNotBlank(process_id)) {
            entity.setProcess_id(Long.valueOf(process_id));
        }
        if (StringUtils.isNotBlank(user_shop_name_like)) {
            entity.getMap().put("user_shop_name_like", user_shop_name_like);
        }
        if (StringUtils.isNotBlank(pay_type)) {
            entity.setPay_type(Integer.valueOf(pay_type));
        }
        if (StringUtils.isNotBlank(r3_id)) {
            entity.setR3_id(Long.valueOf(r3_id));
        }
        if (StringUtils.isNotBlank(sync_state)) {
            entity.setSync_state(Integer.valueOf(sync_state));
        }
        if (is_th != null && is_th.equals("1")) {
            entity.setDoc_type("ZFRE");
        } else {
            if (StringUtils.isNotBlank(doc_type)) {
                entity.setDoc_type(doc_type);
            }
        }

        /**
         * 此字段实际含义不是字面含义订单类型,现被误用作订单来源<br>
         * 0在线下单<br>
         * 1图片下单<br>
         * 2触网转单<br>
         * 4从返利转<br>
         * 5DRP转入<br>
         */
        if (StringUtils.isNotBlank(order_type)) {
            entity.setOrder_type(Integer.valueOf(order_type));
        }
        if (StringUtils.isNotBlank(send_type)) {
            entity.setSend_type(Integer.valueOf(send_type));
        }
        if (StringUtils.isNotBlank(pd_name_like)) {
            entity.getMap().put("pd_name_like", pd_name_like);
        }
        if (StringUtils.isNotBlank(vbedl_like)) {
            entity.getMap().put("vbedl_like", vbedl_like);
        }
        if (StringUtils.isNotBlank(is_delivered)) {
            entity.setIs_delivered(Integer.valueOf(is_delivered));
        }
        if (StringUtils.isNotBlank(ag_like)) {
            entity.getMap().put("ag_like", ag_like);
        }
        if (StringUtils.isNotBlank(l5_dept_id)) {
            entity.getMap().put("par_dept_id", l5_dept_id);
        } else if (StringUtils.isNotBlank(l4_dept_id)) {
            entity.getMap().put("par_dept_id", l4_dept_id);
        } else if (StringUtils.isNotBlank(dept_id)) {
            entity.getMap().put("par_dept_id", dept_id);
        }
        // 同步人，同步日期
        if (StringUtils.isNotBlank(sync_date_start)) {
            entity.getMap().put("sync_date_start", sync_date_start + " 00:00:00");
        }
        if (StringUtils.isNotBlank(sync_date_end)) {
            entity.getMap().put("sync_date_end", sync_date_end + " 23:59:59");
        }
        if (StringUtils.isNotBlank(sync_user)) {
            entity.setSync_user(sync_user);
        }

        if (GenericValidator.isInt(audit_state)) {
            // 按订单状态查询
            int state = Integer.valueOf(audit_state);
            switch (state) {
                case 10: // 系统管理员-审核中
                    entity.getMap().put("audit_state_lt", 3);
                    break;
                case 11: // 系统管理员-已完成
                    entity.getMap().put("audit_state_eq", 3);
                    break;
                case 0: // 非系统管理员-待审核
                    entity.getMap().put("audit_state_", audit_state);
                    entity.getMap().put("audit_state_eq_0", audit_state);
                    break;
                case 1: // 非系统管理员-已审核
                    entity.getMap().put("audit_state_", audit_state);
                    entity.getMap().put("audit_state_eq_1", audit_state);
                    break;
                case 31: // 系统管理员-已作废
                    entity.getMap().put("audit_state_eq", 4);
                    break;
            }
        }

        if (StringUtils.isNotBlank(kh_confirm_state)) {
            entity.setKh_confirm_state(Integer.valueOf(kh_confirm_state));
        }

        Integer pagecount = 0;// 当前页的商品数量
        BigDecimal pageMoney = new BigDecimal(0);// 当前页的总价
        Double pagediscount = 0.0;// 当前页的总折扣价
        // role小于30
        if (role_id_lt_30) {
            dynaBean.set("dept_type", "1");
            Long recordCount = super.getFacade().getKonkaOrderInfoService().getKonkaOrderInfoAndNextRoleResultForPaginatedListcount1(entity);
            KonkaOrderInfo numMoney = super.getFacade().getKonkaOrderInfoService().getKonkaOrderInfoNumCountMondySum2(entity);
            pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
            entity.getRow().setCount(pager.getRowCount());
            entity.getRow().setFirst(pager.getFirstRow());

            List<KonkaOrderInfo> entityList = super.getFacade().getKonkaOrderInfoService().getKonkaOrderInfoAndNextRoleResultForPaginatedList1(entity);
            for (KonkaOrderInfo konkaOrderInfo : entityList) {
                BranchAssign bagn = new BranchAssign();
                bagn.setKonka_r3_id(konkaOrderInfo.getCust_id());
                bagn = getFacade().getBranchAssignService().getBranchAssign(bagn);
                if (null != bagn && null != bagn.getUser_id()) {
                    PeProdUser ywy = new PeProdUser();
                    ywy.setId(bagn.getUser_id());
                    ywy = getFacade().getPeProdUserService().getPeProdUserResult(ywy);
                    konkaOrderInfo.getMap().put("ywy", ywy.getReal_name());
                }

            }

            // 从R3得到物流发货信息--ldy
            for (KonkaOrderInfo konkaOrderInfo : entityList) {
                Long r3_order_id = konkaOrderInfo.getR3_id();// 得到订单号
                if (r3_order_id != null) {
                    KonkaR3Zsof konkaR3Zsof = new KonkaR3Zsof();
                    konkaR3Zsof.setVbeln(r3_order_id);
                    konkaR3Zsof = super.getFacade().getKonkaR3ZsofService().getKonkaR3Zsof(konkaR3Zsof);
                    if (konkaR3Zsof != null) {
                        konkaOrderInfo.getMap().put("vbedl", konkaR3Zsof.getVbedl());// 06单号
                        if (!("0000-00-00").equals(konkaR3Zsof.getLfdat())) {// 如果有发货时间,发货状态改为已发货
                            konkaOrderInfo.getMap().put("lfdat", konkaR3Zsof.getLfdat());// 发货时间
                            if (!("0000-00-00").equals(konkaR3Zsof.getShdat())) {
                                konkaOrderInfo.getMap().put("shdat", konkaR3Zsof.getShdat());// 收货时间
                            }
                        }
                    }
                }
            }

            // 得到分公司
            for (KonkaOrderInfo konkaOrderInfo : entityList) {
                if (null != konkaOrderInfo.getAdd_dept_id()) {
                    KonkaDept konkaDept = new KonkaDept();
                    konkaDept.setDept_id(konkaOrderInfo.getAdd_dept_id());
                    konkaDept.getMap().put("dept_type_eq", 3);
                    konkaDept = super.getFacade().getKonkaDeptService().getKonkaDeptSuperiorByDeptId(konkaDept);
                    if (konkaDept != null) {
                        if (null != konkaDept.getDept_name() && !"".equals(konkaDept.getDept_name())) {
                            konkaOrderInfo.getMap().put("fgsName", konkaDept.getDept_name());
                        }
                    }
                }
            }

            // 得到经办
            for (KonkaOrderInfo konkaOrderInfo : entityList) {
                if (null != konkaOrderInfo.getAdd_dept_id()) {
                    KonkaDept konkaDept = new KonkaDept();
                    konkaDept.setDept_id(konkaOrderInfo.getAdd_dept_id());
                    konkaDept.getMap().put("dept_type_eq", 4);
                    konkaDept = super.getFacade().getKonkaDeptService().getKonkaDeptSuperiorByDeptId(konkaDept);
                    if (null != konkaDept && !("".equals(konkaDept))) {
                        String deptName = konkaDept.getDept_name();
                        if (null != deptName && !("".equals(deptName))) {
                            konkaOrderInfo.getMap().put("jbName", konkaDept.getDept_name());
                        }
                    } else {
                        KonkaDept konkaDept3 = new KonkaDept();
                        konkaDept3.setDept_id(konkaOrderInfo.getAdd_dept_id());
                        konkaDept3.getMap().put("dept_type_eq", 5);
                        konkaDept3 = super.getFacade().getKonkaDeptService().getKonkaDeptSuperiorByDeptId(konkaDept3);
                        if (konkaDept3 != null && null != konkaDept3.getDept_name() && !("".equals(konkaDept3.getDept_name()))) {
                            String deptName = konkaDept3.getDept_name();
                            if (null != deptName && !("".equals(deptName))) {
                                konkaOrderInfo.getMap().put("jbName", konkaDept3.getDept_name());
                            }
                        }
                    }
                }

                // 0： 待审核 1: 已审核
                konkaOrderInfo.getMap().put("states", role_ids.contains(konkaOrderInfo.getNext_audit_role_id()) ? 0 : 1);
                // 当前页商品数量
                pagecount = pagecount + (Integer) konkaOrderInfo.getMap().get("good_count");
                // 当前页商品价格
                pageMoney = pageMoney.add(((BigDecimal) konkaOrderInfo.getMap().get("good_price")).multiply(new BigDecimal((Integer) konkaOrderInfo.getMap().get("good_count"))));

                pagediscount +=
                        (((BigDecimal) (konkaOrderInfo.getMap().get("good_discount") == null ? new BigDecimal(0) : konkaOrderInfo.getMap().get("good_discount")))
                                .divide(new BigDecimal(100))).multiply((BigDecimal) konkaOrderInfo.getMap().get("good_sum_price")).doubleValue()
                                + ((BigDecimal) konkaOrderInfo.getMap().get("d_good_discount_price")).doubleValue();

            }

            // 导出
            if (StringUtils.isNotBlank(excel_all) && "1".equals(excel_all)) {
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/octet-stream");
                response.setHeader("Content-Disposition", "attachment;filename=" + EncryptUtils.encodingFileName("订单详细查询数据") + ".xls");
                entity.getRow().setCount(recordCount.intValue());
                List<KonkaOrderInfo> entityList1 = getFacade().getKonkaOrderInfoService().getKonkaOrderInfoAndNextRoleResultForPaginatedList1(entity);
                dynaBean.set("excel_all", excel_all);
                // 查找业务员相关
                for (KonkaOrderInfo konkaOrderInfo : entityList1) {
                    BranchAssign bagn = new BranchAssign();
                    bagn.setKonka_r3_id(konkaOrderInfo.getCust_id());
                    bagn = getFacade().getBranchAssignService().getBranchAssign(bagn);
                    if (null != bagn && null != bagn.getUser_id()) {
                        PeProdUser ywy = new PeProdUser();
                        ywy.setId(bagn.getUser_id());
                        ywy = getFacade().getPeProdUserService().getPeProdUserResult(ywy);
                        konkaOrderInfo.getMap().put("ywy", ywy.getReal_name());
                    }

                }

                // 从R3得到物流发货信息--ldy
                for (KonkaOrderInfo konkaOrderInfo : entityList) {
                    Long r3_order_id = konkaOrderInfo.getR3_id();// 得到订单号
                    if (r3_order_id != null) {
                        KonkaR3Zsof konkaR3Zsof = new KonkaR3Zsof();
                        konkaR3Zsof.setVbeln(r3_order_id);
                        konkaR3Zsof = super.getFacade().getKonkaR3ZsofService().getKonkaR3Zsof(konkaR3Zsof);
                        if (konkaR3Zsof != null) {
                            konkaOrderInfo.getMap().put("vbedl", konkaR3Zsof.getVbedl());// 06单号
                            if (!("0000-00-00").equals(konkaR3Zsof.getLfdat())) {// 如果有发货时间,发货状态改为已发货
                                konkaOrderInfo.getMap().put("lfdat", konkaR3Zsof.getLfdat());// 发货时间
                                if (!("0000-00-00").equals(konkaR3Zsof.getShdat())) {
                                    konkaOrderInfo.getMap().put("shdat", konkaR3Zsof.getShdat());// 收货时间
                                }
                            }
                        }
                    }
                }

                // 得到分公司
                for (KonkaOrderInfo konkaOrderInfo4 : entityList1) {
                    if (null != konkaOrderInfo4.getAdd_dept_id()) {
                        KonkaDept konkaDept6 = new KonkaDept();
                        konkaDept6.setDept_id(konkaOrderInfo4.getAdd_dept_id());
                        konkaDept6.getMap().put("dept_type_eq", 3);
                        konkaDept6 = super.getFacade().getKonkaDeptService().getKonkaDeptSuperiorByDeptId(konkaDept6);
                        if (null != konkaDept6 && null != konkaDept6.getDept_name() && !"".equals(konkaDept6.getDept_name())) {
                            konkaOrderInfo4.getMap().put("fgsName", konkaDept6.getDept_name());
                        }
                    }
                }

                // 得到经办
                for (KonkaOrderInfo konkaOrderInfo4 : entityList1) {
                    if (null != konkaOrderInfo4.getAdd_dept_id()) {
                        KonkaDept konkaDept4 = new KonkaDept();
                        konkaDept4.setDept_id(konkaOrderInfo4.getAdd_dept_id());
                        konkaDept4.getMap().put("dept_type_eq", 4);
                        konkaDept4 = super.getFacade().getKonkaDeptService().getKonkaDeptSuperiorByDeptId(konkaDept4);
                        if (null != konkaDept4 && !("".equals(konkaDept4))) {
                            String deptName = konkaDept4.getDept_name();
                            if (null != deptName && !("".equals(deptName))) {
                                konkaOrderInfo4.getMap().put("jbName", konkaDept4.getDept_name());
                            }
                        } else {
                            KonkaDept konkaDept5 = new KonkaDept();
                            konkaDept5.setDept_id(konkaOrderInfo4.getAdd_dept_id());
                            konkaDept5.getMap().put("dept_type_eq", 5);
                            konkaDept5 = super.getFacade().getKonkaDeptService().getKonkaDeptSuperiorByDeptId(konkaDept5);
                            if (null != konkaDept5 && !("".equals(konkaDept5))) {
                                String deptName = konkaDept5.getDept_name();
                                if (null != deptName && !("".equals(deptName))) {
                                    konkaOrderInfo4.getMap().put("jbName", konkaDept5.getDept_name());
                                }
                            }
                        }
                    }
                }
                if (null != entityList1) {
                    for (KonkaOrderInfo orderInfo : entityList1) {
                        // 0： 待审核 1: 已审核
                        orderInfo.getMap().put("states", role_ids.contains(orderInfo.getNext_audit_role_id()) ? 0 : 1);
                    }
                }
                request.setAttribute("allList", entityList1);

                if (is_th != null && is_th.equals("1")) {
                    return new ActionForward("/admin/KonkaOrderSearch/listTHForReport.jsp");
                } else {
                    return new ActionForward("/admin/KonkaOrderSearch/listForReport1.jsp");
                }
            }
            request.setAttribute("entityList", entityList);
            request.setAttribute("numCount", numMoney.getMap().get("numcount"));
            request.setAttribute("moneyCount", numMoney.getMap().get("moneycount"));
            request.setAttribute("disCount", numMoney.getMap().get("discount"));
        } else { // 非系统管理员登录
            dynaBean.set("dept_type", "");

            // 数据级别判断开始
            Long __dept_id = userInfo.getDept_id(); // 默认为当前用户所在部门
            int max_dlevel = super.getMaxDLevel(userInfo.getId()); // 获取当前用户的最高可视部门级别
            request.setAttribute("max_dlevel", max_dlevel);
            logger.info("Max level : {}", max_dlevel);
            switch (max_dlevel) {
                case 9:
                    // 集团及以下部门可见
                    __dept_id = 0L; // 0表示部门根节点，即“多媒体事业本部”
                    break;
                case 8:
                    // 分公司及以下部门可见
                    KonkaDept dept_fgs = super.getKonkaDeptForFgs(Long.valueOf(__dept_id)); // 查询部门分公司
                    if (null != dept_fgs && null != dept_fgs.getDept_id()) {
                        __dept_id = dept_fgs.getDept_id(); // 分公司部门ID
                    }
                    break;
                case 7:
                    // 我所在的部门及以下部门可见
                    __dept_id = userInfo.getDept_id(); // 默认为当前用户所在部门
                    break;
                case 0:
                    entity.getMap().put("querycxbycust_userid_eq", userInfo.getId());
                    break;
                default:
                    // 出错
            }
            // 数据级别判断结束

            entity.getMap().put("par_or_children_dept_id", __dept_id);

            if (StringUtils.isBlank(audit_state) && (max_dlevel >= 7)) {
                // 这些角色可见分公司所有订单数据，加audit_state的空判断是为了设置仅在未选择“审核状态”查询条件的时候这些角色可以查看该分公司的全部数据
                // KonkaDept fgs_dept =
                // super.getSuperiorForDeptType(userInfo.getDept_id(), 3);
                // if (null != fgs_dept) {
                // entity.getMap().put("par_or_children_dept_id",
                // fgs_dept.getDept_id());
                // }
            } else {
                entity.getMap().put("no_sys_man_user_id", userInfo.getId()); // 表示需要当前用户角色审核的订单
                // entity.getMap().put("par_or_children_dept_id",
                // userInfo.getDept_id());
            }

            Long recordCount = super.getFacade().getKonkaOrderInfoService().getKonkaOrderInfoAndNextRoleResultForPaginatedListcount1(entity);
            KonkaOrderInfo numMoney = super.getFacade().getKonkaOrderInfoService().getKonkaOrderInfoNumCountMondySum2(entity);
            pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
            entity.getRow().setCount(pager.getRowCount());
            entity.getRow().setFirst(pager.getFirstRow());

            List<KonkaOrderInfo> entityList = super.getFacade().getKonkaOrderInfoService().getKonkaOrderInfoAndNextRoleResultForPaginatedList1(entity);

            for (KonkaOrderInfo konkaOrderInfo : entityList) {
                BranchAssign bagn = new BranchAssign();
                bagn.setKonka_r3_id(konkaOrderInfo.getCust_id());
                bagn = getFacade().getBranchAssignService().getBranchAssign(bagn);
                if (null != bagn && null != bagn.getUser_id()) {
                    PeProdUser ywy = new PeProdUser();
                    ywy.setId(bagn.getUser_id());
                    ywy = getFacade().getPeProdUserService().getPeProdUserResult(ywy);
                    konkaOrderInfo.getMap().put("ywy", ywy.getReal_name());
                }

            }

            // 从R3得到物流发货信息--ldy
            for (KonkaOrderInfo konkaOrderInfo : entityList) {
                Long r3_order_id = konkaOrderInfo.getR3_id();// 得到订单号
                if (r3_order_id != null) {
                    KonkaR3Zsof konkaR3Zsof = new KonkaR3Zsof();
                    konkaR3Zsof.setVbeln(r3_order_id);
                    konkaR3Zsof = super.getFacade().getKonkaR3ZsofService().getKonkaR3Zsof(konkaR3Zsof);
                    if (konkaR3Zsof != null) {
                        konkaOrderInfo.getMap().put("vbedl", konkaR3Zsof.getVbedl());// 06单号
                        if (!("0000-00-00").equals(konkaR3Zsof.getLfdat())) {// 如果有发货时间,发货状态改为已发货
                            konkaOrderInfo.getMap().put("lfdat", konkaR3Zsof.getLfdat());// 发货时间
                            if (!("0000-00-00").equals(konkaR3Zsof.getShdat())) {
                                konkaOrderInfo.getMap().put("shdat", konkaR3Zsof.getShdat());// 收货时间
                            }
                        }
                    }
                }
            }

            // 得到分公司
            for (KonkaOrderInfo konkaOrderInfo : entityList) {
                if (null != konkaOrderInfo.getAdd_dept_id()) {
                    KonkaDept konkaDept = new KonkaDept();
                    konkaDept.setDept_id(konkaOrderInfo.getAdd_dept_id());
                    konkaDept.getMap().put("dept_type_eq", 3);
                    konkaDept = super.getFacade().getKonkaDeptService().getKonkaDeptSuperiorByDeptId(konkaDept);
                    if (null != konkaDept && null != konkaDept.getDept_name() && !"".equals(konkaDept.getDept_name())) {
                        konkaOrderInfo.getMap().put("fgsName", konkaDept.getDept_name());
                    }
                }
            }

            // 得到经办
            for (KonkaOrderInfo konkaOrderInfo : entityList) {
                if (null != konkaOrderInfo.getAdd_dept_id()) {
                    KonkaDept konkaDept = new KonkaDept();
                    konkaDept.setDept_id(konkaOrderInfo.getAdd_dept_id());
                    konkaDept.getMap().put("dept_type_eq", 4);
                    konkaDept = super.getFacade().getKonkaDeptService().getKonkaDeptSuperiorByDeptId(konkaDept);
                    if (null != konkaDept && !("".equals(konkaDept))) {
                        String deptName = konkaDept.getDept_name();
                        if (null != deptName && !("".equals(deptName))) {
                            konkaOrderInfo.getMap().put("jbName", konkaDept.getDept_name());
                        }
                    } else {
                        KonkaDept konkaDept3 = new KonkaDept();
                        konkaDept3.setDept_id(konkaOrderInfo.getAdd_dept_id());
                        konkaDept3.getMap().put("dept_type_eq", 5);
                        konkaDept3 = super.getFacade().getKonkaDeptService().getKonkaDeptSuperiorByDeptId(konkaDept3);
                        if (null != konkaDept3 && !"".equals(konkaDept3)) {
                            String deptName = konkaDept3.getDept_name();
                            if (null != deptName && !("".equals(deptName))) {
                                konkaOrderInfo.getMap().put("jbName", konkaDept3.getDept_name());
                            }
                        }
                    }
                }

                // 0： 待审核 1: 已审核
                konkaOrderInfo.getMap().put("states", role_ids.contains(konkaOrderInfo.getNext_audit_role_id()) ? 0 : 1);
                // 当前页商品数量
                pagecount = pagecount + (Integer) konkaOrderInfo.getMap().get("good_count");
                // 当前页商品价格

                pageMoney = pageMoney.add(((BigDecimal) konkaOrderInfo.getMap().get("good_price")).multiply(new BigDecimal((Integer) konkaOrderInfo.getMap().get("good_count"))));

                pagediscount +=
                        (((BigDecimal) (konkaOrderInfo.getMap().get("good_discount") == null ? new BigDecimal(0) : konkaOrderInfo.getMap().get("good_discount")))
                                .divide(new BigDecimal(100))).multiply((BigDecimal) konkaOrderInfo.getMap().get("good_sum_price")).doubleValue()
                                + ((BigDecimal) konkaOrderInfo.getMap().get("d_good_discount_price")).doubleValue();
            }

            if (null != entityList) {
                for (KonkaOrderInfo orderInfo : entityList) {
                    // 0： 待审核 1: 已审核
                    orderInfo.getMap().put("states", role_ids.contains(orderInfo.getNext_audit_role_id()) ? 0 : 1);
                }
            }

            // 导出
            if (StringUtils.isNotBlank(excel_all) && "1".equals(excel_all)) {
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/octet-stream");
                response.setHeader("Content-Disposition", "attachment;filename=" + EncryptUtils.encodingFileName("订单详细查询数据") + ".xls");
                entity.getRow().setCount(recordCount.intValue());
                List<KonkaOrderInfo> entityList1 = getFacade().getKonkaOrderInfoService().getKonkaOrderInfoAndNextRoleResultForPaginatedList1(entity);
                dynaBean.set("excel_all", excel_all);

                // 查找业务员相关
                for (KonkaOrderInfo konkaOrderInfo : entityList1) {
                    BranchAssign bagn = new BranchAssign();
                    bagn.setKonka_r3_id(konkaOrderInfo.getCust_id());
                    bagn = getFacade().getBranchAssignService().getBranchAssign(bagn);
                    if (null != bagn && null != bagn.getUser_id()) {
                        PeProdUser ywy = new PeProdUser();
                        ywy.setId(bagn.getUser_id());
                        ywy = getFacade().getPeProdUserService().getPeProdUserResult(ywy);
                        konkaOrderInfo.getMap().put("ywy", ywy.getReal_name());
                    }

                }

                // 从R3得到物流发货信息--ldy
                for (KonkaOrderInfo konkaOrderInfo : entityList) {
                    Long r3_order_id = konkaOrderInfo.getR3_id();// 得到订单号
                    if (r3_order_id != null) {
                        KonkaR3Zsof konkaR3Zsof = new KonkaR3Zsof();
                        konkaR3Zsof.setVbeln(r3_order_id);
                        konkaR3Zsof = super.getFacade().getKonkaR3ZsofService().getKonkaR3Zsof(konkaR3Zsof);
                        if (konkaR3Zsof != null) {
                            konkaOrderInfo.getMap().put("vbedl", konkaR3Zsof.getVbedl());// 06单号
                            if (!("0000-00-00").equals(konkaR3Zsof.getLfdat())) {// 如果有发货时间,发货状态改为已发货
                                konkaOrderInfo.getMap().put("lfdat", konkaR3Zsof.getLfdat());// 发货时间
                                if (!("0000-00-00").equals(konkaR3Zsof.getShdat())) {
                                    konkaOrderInfo.getMap().put("shdat", konkaR3Zsof.getShdat());// 收货时间
                                }
                            }
                        }
                    }
                }

                // 得到分公司
                for (KonkaOrderInfo konkaOrderInfo4 : entityList1) {
                    if (null != konkaOrderInfo4.getAdd_dept_id()) {
                        KonkaDept konkaDept6 = new KonkaDept();
                        konkaDept6.setDept_id(konkaOrderInfo4.getAdd_dept_id());
                        konkaDept6.getMap().put("dept_type_eq", 3);
                        konkaDept6 = super.getFacade().getKonkaDeptService().getKonkaDeptSuperiorByDeptId(konkaDept6);
                        if (null != konkaDept6 && null != konkaDept6.getDept_name() && !"".equals(konkaDept6.getDept_name())) {
                            konkaOrderInfo4.getMap().put("fgsName", konkaDept6.getDept_name());
                        }
                    }
                }

                // 得到经办
                for (KonkaOrderInfo konkaOrderInfo4 : entityList1) {
                    if (null != konkaOrderInfo4.getAdd_dept_id()) {
                        KonkaDept konkaDept4 = new KonkaDept();
                        konkaDept4.setDept_id(konkaOrderInfo4.getAdd_dept_id());
                        konkaDept4.getMap().put("dept_type_eq", 4);
                        konkaDept4 = super.getFacade().getKonkaDeptService().getKonkaDeptSuperiorByDeptId(konkaDept4);
                        if (null != konkaDept4 && !("".equals(konkaDept4))) {
                            String deptName = konkaDept4.getDept_name();
                            if (null != deptName && !("".equals(deptName))) {
                                konkaOrderInfo4.getMap().put("jbName", konkaDept4.getDept_name());
                            }
                        } else {
                            KonkaDept konkaDept5 = new KonkaDept();
                            konkaDept5.setDept_id(konkaOrderInfo4.getAdd_dept_id());
                            konkaDept5.getMap().put("dept_type_eq", 5);
                            konkaDept5 = super.getFacade().getKonkaDeptService().getKonkaDeptSuperiorByDeptId(konkaDept5);
                            if (null != konkaDept5 && !("".equals(konkaDept5))) {
                                String deptName = konkaDept5.getDept_name();
                                if (null != deptName && !("".equals(deptName))) {
                                    konkaOrderInfo4.getMap().put("jbName", konkaDept5.getDept_name());
                                }
                            }
                        }
                    }
                }
                if (null != entityList1) {
                    for (KonkaOrderInfo orderInfo : entityList1) {
                        // 0： 待审核 1: 已审核
                        orderInfo.getMap().put("states", role_ids.contains(orderInfo.getNext_audit_role_id()) ? 0 : 1);
                    }
                }
                request.setAttribute("allList", entityList1);
                if (is_th != null && is_th.equals("1")) {
                    return new ActionForward("/admin/KonkaOrderSearch/listTHForReport.jsp");
                } else {
                    return new ActionForward("/admin/KonkaOrderSearch/listForReport1.jsp");
                }
            }

            request.setAttribute("entityList", entityList);
            request.setAttribute("numCount", numMoney.getMap().get("numcount"));
            request.setAttribute("moneyCount", numMoney.getMap().get("moneycount"));
            request.setAttribute("disCount", numMoney.getMap().get("discount"));
        }

        // 获取流程列表
        KonkaOrderAuditProcess process = new KonkaOrderAuditProcess();
        if (!role_id_lt_30) {

            // 分公司之可见自己的流程
            KonkaDept dept = super.getSuperiorForDeptType(userInfo.getDept_id(), 3);
            process.getMap().put("par_add_dept_id", dept.getDept_id());
            process.setIs_del(0);
            List<KonkaOrderAuditProcess> processList = super.getFacade().getKonkaOrderAuditProcessService().getKonkaOrderAuditProcessList(process);

            // 系统自带的统一流程
            KonkaOrderAuditProcess ap_public = new KonkaOrderAuditProcess();
            ap_public.setAdd_dept_id(0L);
            ap_public.setIs_del(0);
            // ap_public.setIs_stop(0); // 非停用
            List<KonkaOrderAuditProcess> ap_publicauditProcesseList = getFacade().getKonkaOrderAuditProcessService().getKonkaOrderAuditProcessList(ap_public);
            processList.addAll(ap_publicauditProcesseList);

            request.setAttribute("processList", processList);
        } else {
            // 超级管理员
            process.setIs_del(0);
            List<KonkaOrderAuditProcess> processList = super.getFacade().getKonkaOrderAuditProcessService().getKonkaOrderAuditProcessList(process);
            request.setAttribute("processList", processList);

        }

        if (is_th != null && is_th.equals("1")) {
            return new ActionForward("/admin/KonkaOrderSearch/listTH.jsp");
        }

        // 当前页商品数量
        request.setAttribute("pageCount", pagecount);
        // 当前页商品价格
        request.setAttribute("pageMoney", pageMoney);
        // 当前页商品折扣
        request.setAttribute("pageDiscount", pagediscount);
        return new ActionForward("/admin/KonkaOrderSearch/listdetails.jsp");
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
        request.setAttribute("feed_id", kf.getId());
        dynaBean.set("f_content", kf.getContent());
        dynaBean.set("f_tel", kf.getTel());
        dynaBean.set("f_add_date", kf.getAdd_date());
        dynaBean.set("f_person", kf.getQuestion_person());

        return new ActionForward("/admin/KonkaOrderSearch/iframe.jsp");
    }

    public ActionForward ajaxmessage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        DynaBean dynaBean = (DynaBean) form;
        String feed_id = (String) dynaBean.get("feed_id");
        String f_id = (String) dynaBean.get("f_id");
        if (StringUtils.isBlank(feed_id)) {
            return null;
        }
        KonkaMobileTerminalFbBack entity = new KonkaMobileTerminalFbBack();
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


    // TODO ? 为毛代码放这里?
    public ActionForward ajaxSave(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        DynaBean dynaBean = (DynaBean) form;
        String content = (String) dynaBean.get("content");
        String feed_id = (String) dynaBean.get("feed_id");

        HttpSession session = request.getSession();
        PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
        if (null == user) {
            return null;
        }

        if (StringUtils.isEmpty(feed_id)) {
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
}
