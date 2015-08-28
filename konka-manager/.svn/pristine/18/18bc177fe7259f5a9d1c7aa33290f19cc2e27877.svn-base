package com.ebiz.mmt.web.struts.manager.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ebiz.mmt.domain.KonkaR3Shop;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.JxcFifoStocks;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;


public class JxcFifoStocksAction extends BaseAction {
    public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                     HttpServletResponse response) throws Exception {
        return this.list(mapping, form, request, response);
    }

    public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                              HttpServletResponse response) {
        // TODO Auto-generated method stub
        return this.listDetails(mapping, form, request, response);
    }

    public ActionForward listDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                     HttpServletResponse response) {
        // TODO Auto-generated method stub
        DynaBean dynaBean = (DynaBean) form;
        String r3_code = (String) dynaBean.get("r3_code");
        String dept_sn = (String) dynaBean.get("dept_sn");
        String customer_name_like = (String) dynaBean.get("customer_name_like");
        String goods_name_like = (String) dynaBean.get("goods_name_like");

        String start_date = (String) dynaBean.get("start_date");
        String end_date = (String) dynaBean.get("end_date");
        String mod_name = (String) dynaBean.get("mod_name");
        String excel_to_all = (String) dynaBean.get("excel_to_all");

        String stock_state = (String) dynaBean.get("stock_state");

        String dept_id = (String) dynaBean.get("dept_id");
        String l4_dept_id = (String) dynaBean.get("l4_dept_id");
        String l5_dept_id = (String) dynaBean.get("l5_dept_id");
        Pager pager = (Pager) dynaBean.get("pager");


        //判断session是否超时
        PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
        if (null == ui) {
            String msg = super.getMessage(request, "user.session.isEmpty");
            super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
            return null;
        }


        JxcFifoStocks entity = new JxcFifoStocks();
        if (StringUtils.isNotBlank(dept_sn) && GenericValidator.isLong(dept_sn)) {
            entity.setSubcomp_id(Long.valueOf(dept_sn));
        }
        if (StringUtils.isNotBlank(r3_code)) {
            entity.setR3_code(r3_code);
        }
        if (StringUtils.isNotBlank(stock_state) && GenericValidator.isInt(stock_state)) {
            entity.setStock_state(Integer.parseInt(stock_state));
        }
        if (StringUtils.isNotBlank(customer_name_like)) {
            entity.getMap().put("customer_name_like", customer_name_like);
        }
        if (StringUtils.isNotBlank(goods_name_like)) {
            entity.getMap().put("goods_name_like", goods_name_like);
        }
        if (StringUtils.isNotBlank(mod_name)) {
            entity.setGoods_model(mod_name);
        }
        if (StringUtils.isNotBlank(start_date)) {
            entity.getMap().put("in_start_date", start_date);
        }
        if (StringUtils.isNotBlank(end_date)) {
            entity.getMap().put("in_end_date", end_date);
        }
        if (StringUtils.isNotBlank(l5_dept_id)) {
            entity.getMap().put("par_dept_id", l5_dept_id);
        } else if (StringUtils.isNotBlank(l4_dept_id)) {
            entity.getMap().put("par_dept_id", l4_dept_id);
        } else if (StringUtils.isNotBlank(dept_id)) {
            entity.getMap().put("par_dept_id", dept_id);
            entity.setSubcomp_id(Long.valueOf(dept_id));
        }
        //检查权限
        // 数据级别判断开始
        Long __dept_id = StringUtils.isNotBlank(dept_id) ? Long.valueOf(dept_id) : ui.getDept_id(); // 默认为当前用户所在部门
        int max_dlevel = super.getMaxDLevel(ui.getId()); // 获取当前用户的最高可视部门级别
        logger.info("Max level : {}", max_dlevel);
        request.setAttribute("max_dlevel", max_dlevel);
        switch (max_dlevel) {
            case 9:
                // 集团及以下部门可见
                __dept_id = 0L; // 0表示部门根节点，即“多媒体事业本部”
                if (StringUtils.isNotBlank(dept_id)) {
                    entity.getMap().put("dept_id", null);
                    entity.getMap().put("fgs_dept_value", dept_id);
                }

                break;
            case 8:
                // 分公司及以下部门可见
                KonkaDept dept_fgs = super.getKonkaDeptForFgs(ui.getDept_id()); // 查询部门分公司
                if (null != dept_fgs && null != dept_fgs.getDept_id()) {
                    __dept_id = dept_fgs.getDept_id(); // 分公司部门ID
                    // entity.getMap().put("dept_id_start", __dept_id);
                    entity.getMap().put("fgs_dept_value", __dept_id);
                    entity.setSubcomp_id(Long.valueOf(dept_id));
                }
                break;
            case 7:
                // 我所在的部门及以下部门可见
                __dept_id = ui.getDept_id(); // 默认为当前用户所在部门
                entity.getMap().put("dept_id_start", __dept_id);
                break;
            case 0:
                __dept_id = ui.getDept_id(); // 默认为当前用户所在部门
                // entity.getMap().put("dept_id_start", __dept_id);
                entity.getMap().put("filter_by_ywy_id_eq", ui.getId());
                break;
            default:
                // 出错
        }
        entity.getMap().put("session_user_id", ui.getId());

        Long recordCount = super.getFacade().getJxcFifoStocksService().getJxcFifoStocksManagerDetailsCount(entity);
        pager.setPageSize(10);
        pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
        entity.getRow().setFirst(pager.getFirstRow());
        entity.getRow().setCount(pager.getRowCount());
        List<JxcFifoStocks> entityList = super.getFacade().getJxcFifoStocksService()
                .getJxcFifoStocksManagerDetailsPaginatedList(entity);

        request.setAttribute("entityList", entityList);
        List<KonkaDept> konkaDeptList = super.getDeptInfoListWithOutLimit(mapping, form, request, response);
        request.setAttribute("konkaDeptList", konkaDeptList);
        return new ActionForward("/../manager/admin/JxcFifoStocks/list_details.jsp");
    }

    public ActionForward listAccount(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                     HttpServletResponse response) {
        // TODO Auto-generated method stub
        DynaBean dynaBean = (DynaBean) form;
        String r3_code = (String) dynaBean.get("r3_code");
        String dept_sn = (String) dynaBean.get("dept_sn");
        String customer_name_like = (String) dynaBean.get("customer_name_like");
        String goods_name_like = (String) dynaBean.get("goods_name_like");

        String start_date = (String) dynaBean.get("start_date");
        String end_date = (String) dynaBean.get("end_date");
        String mod_name = (String) dynaBean.get("mod_name");
        String excel_to_all = (String) dynaBean.get("excel_to_all");

        String stock_state = (String) dynaBean.get("stock_state");

        String dept_id = (String) dynaBean.get("dept_id");
        String l4_dept_id = (String) dynaBean.get("l4_dept_id");
        String l5_dept_id = (String) dynaBean.get("l5_dept_id");
        Pager pager = (Pager) dynaBean.get("pager");
        String group_by_store = (String) dynaBean.get("group_by_store");

        //判断session是否超时
        PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
        if (null == ui) {
            String msg = super.getMessage(request, "user.session.isEmpty");
            super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
            return null;
        }


        JxcFifoStocks entity = new JxcFifoStocks();
        if (StringUtils.isNotBlank(dept_sn) && GenericValidator.isLong(dept_sn)) {
            entity.setSubcomp_id(Long.valueOf(dept_sn));
        }
        if (StringUtils.isNotBlank(r3_code)) {
            entity.setR3_code(r3_code);
        }
        if (StringUtils.isNotBlank(stock_state) && GenericValidator.isInt(stock_state)) {
            entity.setStock_state(Integer.parseInt(stock_state));
        }
        if (StringUtils.isNotBlank(customer_name_like)) {
            entity.getMap().put("customer_name_like", customer_name_like);
        }
        if (StringUtils.isNotBlank(goods_name_like)) {
            entity.getMap().put("goods_name_like", goods_name_like);
        }
        if (StringUtils.isNotBlank(mod_name)) {
            entity.setGoods_model(mod_name);
        }
        if (StringUtils.isNotBlank(start_date)) {
            entity.getMap().put("in_start_date", start_date);
        }
        if (StringUtils.isNotBlank(end_date)) {
            entity.getMap().put("in_end_date", end_date);
        }

        if (StringUtils.isNotBlank(group_by_store)) {
            entity.getMap().put("group_by_store", group_by_store);
            System.out.println(group_by_store);
            request.setAttribute("group_by_store", group_by_store);
        }
        if (StringUtils.isNotBlank(l5_dept_id)) {
            entity.getMap().put("par_dept_id", l5_dept_id);
        } else if (StringUtils.isNotBlank(l4_dept_id)) {
            entity.getMap().put("par_dept_id", l4_dept_id);
        } else if (StringUtils.isNotBlank(dept_id)) {
            entity.getMap().put("par_dept_id", dept_id);
            entity.setSubcomp_id(Long.valueOf(dept_id));
        }
        //检查权限
        // 数据级别判断开始
        Long __dept_id = StringUtils.isNotBlank(dept_id) ? Long.valueOf(dept_id) : ui.getDept_id(); // 默认为当前用户所在部门
        int max_dlevel = super.getMaxDLevel(ui.getId()); // 获取当前用户的最高可视部门级别
        logger.info("Max level : {}", max_dlevel);
        request.setAttribute("max_dlevel", max_dlevel);
        switch (max_dlevel) {
            case 9:
                // 集团及以下部门可见
                __dept_id = 0L; // 0表示部门根节点，即“多媒体事业本部”
                if (StringUtils.isNotBlank(dept_id)) {
                    entity.getMap().put("dept_id", null);
                    entity.getMap().put("fgs_dept_value", dept_id);
                }

                break;
            case 8:
                // 分公司及以下部门可见
                KonkaDept dept_fgs = super.getKonkaDeptForFgs(ui.getDept_id()); // 查询部门分公司
                if (null != dept_fgs && null != dept_fgs.getDept_id()) {
                    __dept_id = dept_fgs.getDept_id(); // 分公司部门ID
                    // entity.getMap().put("dept_id_start", __dept_id);
                    entity.getMap().put("fgs_dept_value", __dept_id);
                    entity.setSubcomp_id(Long.valueOf(dept_id));
                }
                break;
            case 7:
                // 我所在的部门及以下部门可见
                __dept_id = ui.getDept_id(); // 默认为当前用户所在部门
                entity.getMap().put("dept_id_start", __dept_id);
                break;
            case 0:
                __dept_id = ui.getDept_id(); // 默认为当前用户所在部门
                // entity.getMap().put("dept_id_start", __dept_id);
                entity.getMap().put("filter_by_ywy_id_eq", ui.getId());
                break;
            default:
                // 出错
        }
        entity.getMap().put("session_user_id", ui.getId());

        Long recordCount = super.getFacade().getJxcFifoStocksService().getJxcFifoStocksManagerAccountCount(entity);
        pager.setPageSize(10);
        pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
        entity.getRow().setFirst(pager.getFirstRow());
        entity.getRow().setCount(pager.getRowCount());
        List<JxcFifoStocks> entityList = super.getFacade().getJxcFifoStocksService()
                .getJxcFifoStocksManagerAccountPaginatedList(entity);

        request.setAttribute("entityList", entityList);
        List<KonkaDept> konkaDeptList = super.getDeptInfoListWithOutLimit(mapping, form, request, response);
        request.setAttribute("konkaDeptList", konkaDeptList);
        return new ActionForward("/../manager/admin/JxcFifoStocks/list_count.jsp");
    }


    public ActionForward viewDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                     HttpServletResponse response) {
        // TODO Auto-generated method stub
        setNaviStringToRequestScope(form, request);
        DynaBean dynaBean = (DynaBean) form;


        String goods_name_like = (String) dynaBean.get("goods_name_like");
        String store_id = (String) dynaBean.get("store_id");
        String stock_state = (String) dynaBean.get("stock_state");
        String r3_code = (String) dynaBean.get("r3_code");
        // 判断session是否超时
        PeProdUser ui = (PeProdUser) getSessionAttribute(request, Constants.USER_INFO);
        if (null == ui) {
            String msg = super.getMessage(request, "user.session.isEmpty");
            super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
            return null;
        }
        KonkaR3Shop shop = new KonkaR3Shop();
        shop.setR3_code(r3_code);

        shop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(shop);
        if (null != shop) {
            request.setAttribute("konkaR3Shop", shop);
        }

        JxcFifoStocks entity = new JxcFifoStocks();

        if (StringUtils.isNotBlank(stock_state) && GenericValidator.isInt(stock_state)) {

            if ("10".endsWith(stock_state))
                entity.getMap().put("this_is_in", "this_is_in");
            else
                entity.getMap().put("this_is_out", "this_is_out");
            request.setAttribute("stock_state", stock_state);
        } else {
            return null;
        }

        if (StringUtils.isNotBlank(r3_code)) {
            entity.setR3_code(r3_code);
        }
        if (StringUtils.isNotBlank(store_id)) {
            if ("10".endsWith(stock_state))
                entity.setStock_in_store(Long.valueOf(store_id));
            else
                entity.setStock_out_store(Long.valueOf(store_id));
        }
        if (StringUtils.isNotBlank(goods_name_like)) {
            entity.getMap().put("goods_name_like", goods_name_like);
        }

        // 查询详细信息
        List<JxcFifoStocks> entityList = super.getFacade().getJxcFifoStocksService()
                .getJxcFifoStocksViewDetailsList(entity);

        request.setAttribute("entityList", entityList);

        return new ActionForward("/admin/JxcFifoStocks/view_details.jsp");
    }

}
