package com.ebiz.mmt.web.struts.manager.admin;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.JBaseGoods;
import com.ebiz.mmt.domain.JBasePartner;
import com.ebiz.mmt.domain.JBill;
import com.ebiz.mmt.domain.JBillDetails;
import com.ebiz.mmt.domain.JSubSellRec;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.PePdModel;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.customer.BaseClientJxcAction;
import com.ebiz.ssi.util.EncryptUtils;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * 
 * @author Xing,XiuDong
 * @version 2013-9-25
 */
public class JSubSellRecListOfDetailsAction extends BaseClientJxcAction {

    public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        DynaBean dynaBean = (DynaBean) form;
        // 获取当月的时间
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        // 获取当前月第一天：
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
        String first = format.format(c.getTime());
        // 获取当前天
        String last = format.format(new Date());

        dynaBean.set("opr_date_gt", first);
        dynaBean.set("opr_date_lt", last);
        return this.list(mapping, form, request, response);
    }

    public ActionForward list1(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        super.setNaviStringToRequestScope(form, request);

        DynaBean dynaBean = (DynaBean) form;
        Pager pager = (Pager) dynaBean.get("pager");

        String add_date_ge = (String) dynaBean.get("add_date_ge");
        String add_date_le = (String) dynaBean.get("add_date_le");
        String buy_bill_sn_like = (String) dynaBean.get("buy_bill_sn_like");
        String sell_bill_sn_like = (String) dynaBean.get("sell_bill_sn_like");

        String map_sell_cust_name_like = (String) dynaBean.get("map_sell_cust_name_like");
        String map_sell_r3_code_like = (String) dynaBean.get("map_sell_r3_code_like");
        String map_buy_cust_name_like = (String) dynaBean.get("map_buy_cust_name_like");
        String map_buy_r3_code_like = (String) dynaBean.get("map_buy_r3_code_like");
        String map_pd_name_like = (String) dynaBean.get("map_pd_name_like");

        String l3_dept_id = (String) dynaBean.get("l3_dept_id");
        String l4_dept_id = (String) dynaBean.get("l4_dept_id");
        String l5_dept_id = (String) dynaBean.get("l5_dept_id");

        String excel_to_all = (String) dynaBean.get("excel_to_all");

        PeProdUser userInfo = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

        JSubSellRec entity = new JSubSellRec();
        super.copyProperties(entity, form);

        if(StringUtils.isNotBlank(map_sell_cust_name_like)){
            entity.getMap().put("map_sell_cust_name_like", map_sell_cust_name_like);
        }
        if(StringUtils.isNotBlank(map_sell_r3_code_like)){
            entity.getMap().put("map_sell_r3_code_like", map_sell_r3_code_like);
        }
        if(StringUtils.isNotBlank(map_buy_cust_name_like)){
            entity.getMap().put("map_buy_cust_name_like", map_buy_cust_name_like);
        }
        if(StringUtils.isNotBlank(map_buy_r3_code_like)){
            entity.getMap().put("map_buy_r3_code_like", map_buy_r3_code_like);
        }
        if(StringUtils.isNotBlank(map_pd_name_like)){
            entity.getMap().put("map_pd_name_like", map_pd_name_like);
        }
        if(StringUtils.isNotBlank(add_date_ge)){
            entity.getMap().put("add_date_ge", add_date_ge);
        }
        if(StringUtils.isNotBlank(add_date_le)){
            entity.getMap().put("add_date_le", add_date_le);
        }
        if(StringUtils.isNotBlank(sell_bill_sn_like)){
            entity.getMap().put("sell_bill_sn_like", sell_bill_sn_like);
        }
        if(StringUtils.isNotBlank(buy_bill_sn_like)){
            entity.getMap().put("buy_bill_sn_like", buy_bill_sn_like);
        }

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
                KonkaDept dept_fgs = super.getKonkaDeptForFgs(userInfo.getDept_id()); // 查询部门分公司
                if (null != dept_fgs && null != dept_fgs.getDept_id()) {
                    __dept_id = dept_fgs.getDept_id(); // 分公司部门ID
                    entity.getMap().put("dept_id_start", __dept_id);
                }
                break;
            case 7:
                // 我所在的部门及以下部门可见
                __dept_id = userInfo.getDept_id(); // 默认为当前用户所在部门
                entity.getMap().put("dept_id_start", __dept_id);
                break;
            case 0:
                __dept_id = userInfo.getDept_id(); // 默认为当前用户所在部门
                // entity.getMap().put("dept_id_start", __dept_id);
                entity.getMap().put("filter_by_ywy_id_eq", userInfo.getId());
                break;
            default:
                // 出错
        }
        entity.getMap().put("session_user_id", userInfo.getId());

        if (StringUtils.isNotBlank(l5_dept_id)) {
            entity.getMap().put("dept_id_starts", l5_dept_id);
        } else if (StringUtils.isNotBlank(l4_dept_id)) {
            entity.getMap().put("dept_id_starts", l4_dept_id);
        } else if (StringUtils.isNotBlank(l3_dept_id)) {
            entity.getMap().put("dept_id_starts", l3_dept_id);
        }
        Long recordCount = getFacade().getJSubSellRecService().getJSubSellRecCountOfDetails(entity);
        pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
        entity.getRow().setFirst(pager.getFirstRow());
        entity.getRow().setCount(pager.getRowCount());
        List<JSubSellRec> entityList = getFacade().getJSubSellRecService().getJSubSellRecPaginatedListOfDetails(entity);

        // 导出
        if (StringUtils.isNotBlank(excel_to_all)) {
            entity.getRow().setCount(recordCount.intValue());
            List<JSubSellRec> entityList1 = getFacade().getJSubSellRecService().getJSubSellRecPaginatedListOfDetails(entity);
            request.setAttribute("allList", entityList1);

            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" + EncryptUtils.encodingFileName("分销数据") + ".xls");

            return new ActionForward("/../manager/admin/JSubSellRecListOfDetails/excel.jsp");
        }

        request.setAttribute("entityList", entityList);
        return mapping.findForward("list");
    }

    public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {// 获取分销历史记录
        setNaviStringToRequestScope(form, request);
        DynaBean dynaBean = (DynaBean) form;
        String export = (String) dynaBean.get("export");
        Pager pager = (Pager) dynaBean.get("pager");
        pager.setPageSize(10);
        String mod_id = (String) dynaBean.get("mod_id");
        String bill_type = (String) dynaBean.get("bill_type");
        String bill_sn_like = (String) dynaBean.get("bill_sn_like");// 销售单号
        String type_id = (String) dynaBean.get("type_id");// 商品类型
        String goods_id = (String) dynaBean.get("goods_id");// 机型
        String opr_date_gt = (String) dynaBean.get("opr_date_gt");// 起始时间
        String opr_date_lt = (String) dynaBean.get("opr_date_lt");// 结束时间
        String status = (String) dynaBean.get("status");// 财务确认
        String wd_status = (String) dynaBean.get("wd_status");// 网点确认
        String partner_name = (String) dynaBean.get("partner_name");// 网店名称
        String link_name = (String) dynaBean.get("link_name");// 联系人
        String md_size = (String) dynaBean.get("md_size");// 尺寸
        String sell_r3_code = (String) dynaBean.get("sell_r3_code");// R3_code
        String map_pd_name_like = (String) dynaBean.get("map_pd_name_like");// 类型名称
        String l3_dept_id = (String) dynaBean.get("l3_dept_id");
        String l4_dept_id = (String) dynaBean.get("l4_dept_id");
        String l5_dept_id = (String) dynaBean.get("l5_dept_id");
        String chc_store_name_like = (String) dynaBean.get("chc_store_name_like");

        PeProdUser userInfo = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
        JBillDetails entity = new JBillDetails();

        if (StringUtils.isNotBlank(opr_date_gt)) {
            entity.getMap().put("opr_date_gt", opr_date_gt + " 00:00:00");
        }
        if (StringUtils.isNotBlank(opr_date_lt)) {
            entity.getMap().put("opr_date_lt", opr_date_lt + " 23:59:59");
        }
        if (StringUtils.isNotBlank(bill_sn_like)) {
            entity.getMap().put("bill_sn_like", bill_sn_like);
        }
        if (StringUtils.isNotBlank(type_id)) {
            entity.getMap().put("type_id", type_id);
        }
        if (StringUtils.isNotBlank(goods_id)) {
            entity.getMap().put("goods_id", goods_id);
        }
        if (StringUtils.isNotBlank(status)) {
            entity.getMap().put("bill_state", status);
        }
        if (StringUtils.isNotBlank(wd_status)) {
            entity.getMap().put("status", wd_status);
        }
        if (StringUtils.isNotBlank(partner_name)) {
            entity.getMap().put("partner_name_like", partner_name);
        }
        if (StringUtils.isNotBlank(link_name)) {
            entity.getMap().put("link_name", link_name);
        }
        if (StringUtils.isNotBlank(md_size)) {
            entity.getMap().put("md_size", md_size);
        }
        if (StringUtils.isNotBlank(sell_r3_code)) {
            entity.getMap().put("sell_r3_code", sell_r3_code);
        }
        if (StringUtils.isNotBlank(map_pd_name_like)) {
            entity.getMap().put("map_pd_name_like", map_pd_name_like);
        }
        if (StringUtils.isNotBlank(bill_type)) {
            entity.getMap().put("bill_type", bill_type);
        }
        if (StringUtils.isNotBlank(chc_store_name_like)) {
            entity.getMap().put("chc_store_name_like", chc_store_name_like);
        }

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
                KonkaDept dept_fgs = super.getKonkaDeptForFgs(userInfo.getDept_id()); // 查询部门分公司
                if (null != dept_fgs && null != dept_fgs.getDept_id()) {
                    __dept_id = dept_fgs.getDept_id(); // 分公司部门ID
                    entity.getMap().put("dept_id_start", __dept_id);
                }
                break;
            case 7:
                // 我所在的部门及以下部门可见
                __dept_id = userInfo.getDept_id(); // 默认为当前用户所在部门
                entity.getMap().put("dept_id_start", __dept_id);
                break;
            case 0:
                __dept_id = userInfo.getDept_id(); // 默认为当前用户所在部门
                entity.getMap().put("filter_by_ywy_id_eq", userInfo.getId());
                break;
            default:
                // 出错
        }
        entity.getMap().put("session_user_id", userInfo.getId());

        if (StringUtils.isNotBlank(l5_dept_id)) {
            entity.getMap().put("dept_id_starts", l5_dept_id);
        } else if (StringUtils.isNotBlank(l4_dept_id)) {
            entity.getMap().put("dept_id_starts", l4_dept_id);
        } else if (StringUtils.isNotBlank(l3_dept_id)) {
            entity.getMap().put("dept_id_starts", l3_dept_id);
        }

        Long recordCount = super.getFacade().getJBillDetailsService().getJBillDetailsForJSubSellRecCount(entity);
        pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
        entity.getRow().setCount(pager.getRowCount());
        entity.getRow().setFirst(pager.getFirstRow());
        List<JBillDetails> entityList = super.getFacade().getJBillDetailsService().getJBillDetailsForJSubSellRecPaginatedList(entity);

        request.setAttribute("entityList", entityList);
        request.setAttribute("jBaseTypes", super.getJBaseTypes(10001L, userInfo.getDept_id())); // 商品类型
        request.setAttribute("jBaseGoodsList", super.getJBaseGoodsList(userInfo.getDept_id())); // 机型

        // 尺寸段下拉数据
        List<HashMap> pepdList = super.getFacade().getPePdModelService().getPePdModelListForMdSize(new PePdModel());// 查询尺寸
        request.setAttribute("pepdList", pepdList);

        if (StringUtils.isNotBlank(export)) {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" + EncryptUtils.encodingFileName("分销数据") + ".xls");
            entity.getRow().setCount(recordCount.intValue());
            dynaBean.set("export", export);
            List<JBillDetails> entityList1 = getFacade().getJBillDetailsService().getJBillDetailsForJSubSellRecPaginatedList(entity);

            request.setAttribute("entityList1", entityList1);
            return new ActionForward("/../manager/admin/JSubSellRecListOfDetails/excel.jsp");
        }
        return mapping.findForward("list");
    }

    public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        super.setNaviStringToRequestScope(form, request);

        DynaBean dynaBean = (DynaBean) form;
        String bill_id = (String) dynaBean.get("bill_id");
        String bill_sn = (String) dynaBean.get("bill_sn");

        if (!GenericValidator.isLong(bill_id) && StringUtils.isBlank(bill_sn)) {
            return null;
        }

        JBill entity = new JBill();
        if (GenericValidator.isLong(bill_id)) {
            entity.setBill_id(Long.valueOf(bill_id));
        }
        entity.setBill_sn(bill_sn);
        entity = getFacade().getJBillService().getJBill(entity);

        if (null == entity) {
            return null;
        }

        // 供货商
        if (null != entity.getC_id()) {
            KonkaR3Shop konkaR3Shop = new KonkaR3Shop();
            konkaR3Shop.setId(entity.getC_id());
            konkaR3Shop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(konkaR3Shop);
            request.setAttribute("konkaR3Shop", konkaR3Shop);
        }

        // 往来单位
        if (null != entity.getPartner_id()) {
            JBasePartner jBasePartner = new JBasePartner();
            jBasePartner.setPartner_id(entity.getPartner_id());
            jBasePartner = super.getFacade().getJBasePartnerService().getJBasePartner(jBasePartner);
            entity.setjBasePartner(jBasePartner);
        }

        JBillDetails jBillDetails = new JBillDetails();
        jBillDetails.setBill_id(entity.getBill_id());
        List<JBillDetails> jBillDetailsList = super.getFacade().getJBillDetailsService().getJBillDetailsList(jBillDetails);
        for (JBillDetails cur : jBillDetailsList) {
            JBaseGoods goods = new JBaseGoods();
            goods.setGoods_id(cur.getGoods_id());
            goods = super.getFacade().getJBaseGoodsService().getJBaseGoods(goods);
            cur.setjBaseGoods(goods);
        }

        entity.setjBillDetailsList(jBillDetailsList);

        super.copyProperties(form, entity);

        return mapping.findForward("view");
    }
}
