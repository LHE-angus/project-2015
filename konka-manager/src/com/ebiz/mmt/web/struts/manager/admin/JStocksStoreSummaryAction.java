package com.ebiz.mmt.web.struts.manager.admin;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.alibaba.fastjson.JSON;
import com.ebiz.mmt.domain.CustomerInventoryDetailVO;
import com.ebiz.mmt.domain.JStocksStoreSummary;
import com.ebiz.mmt.domain.KonkaCategory;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaOrderInfo;
import com.ebiz.mmt.domain.KonkaOrderInfoDetails;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.util.DateUtilsForMonth;
import com.ebiz.ssi.util.EncryptUtils;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author ChenXiaoqi
 * @version 2014-08-15
 */
public class JStocksStoreSummaryAction extends BaseAction {
    @Override
    public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return this.list(mapping, form, request, response);
    }

    public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        setNaviStringToRequestScope(form, request);
        super.getModPopeDom(form, request);

        PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

        DynaBean dynaBean = (DynaBean) form;
        Pager pager = (Pager) dynaBean.get("pager");

        String customer_name_like = (String) dynaBean.get("customer_name_like");
        String handle_name_like = (String) dynaBean.get("handle_name_like");// 经办名称
        String r3_code_like = (String) dynaBean.get("r3_code_like");
        String goods_name_like = (String) dynaBean.get("goods_name_like");
        String dept_sn = (String) dynaBean.get("dept_sn");
        String excel_to_all = (String) dynaBean.get("excel_to_all");
        String cur_min_num = (String) dynaBean.get("cur_min_num");
        String cur_max_num = (String) dynaBean.get("cur_max_num");
        // 仓库名称、编码、送达方编码
        String store_name_like = (String) dynaBean.get("store_name_like");
        String store_sn_like = (String) dynaBean.get("store_sn_like");
        String sale_r3_code_like = (String) dynaBean.get("sale_r3_code_like");
        // 一级客户类型
        String customer_type1 = (String) dynaBean.get("v_customer_type1");
        String is_allow_back = (String) dynaBean.get("is_allow_back");
        request.setAttribute("is_allow_back", is_allow_back);
        // 二级客户类型
        String customer_type2 = (String) dynaBean.get("v_customer_type2");
        JStocksStoreSummary entity = new JStocksStoreSummary();

        if (StringUtils.isNotBlank(customer_name_like)) {
            entity.getMap().put("customer_name_like", customer_name_like.trim());
        }
        if (StringUtils.isNotBlank(r3_code_like)) {
            entity.getMap().put("r3_code_like", r3_code_like.trim());
        }
        if (StringUtils.isNotBlank(cur_min_num)) {
            entity.getMap().put("cur_min_num", Integer.valueOf(cur_min_num));
            dynaBean.set("cur_min_num", cur_min_num);
        }
        if (StringUtils.isNotBlank(cur_max_num)) {
            entity.getMap().put("cur_max_num", Integer.valueOf(cur_max_num));
            dynaBean.set("cur_max_num", cur_max_num);
        }
        if (StringUtils.isNotBlank(store_name_like)) {
            entity.getMap().put("store_name_like", store_name_like.trim());
        }
        if (StringUtils.isNotBlank(store_sn_like)) {
            entity.getMap().put("store_sn_like", store_sn_like.trim());
        }
        if (StringUtils.isNotBlank(sale_r3_code_like)) {
            entity.getMap().put("sale_r3_code_like", sale_r3_code_like.trim());
        }
        // 添加客户类型筛选条件
        if (StringUtils.isNotBlank(customer_type2)) {
            entity.getMap().put("cus_type2", customer_type2);
            dynaBean.set("customer_type2", customer_type2);
        } else {
            if (StringUtils.isNotBlank(customer_type1)) {
                entity.getMap().put("cus_type1", customer_type1);
                dynaBean.set("customer_type1", customer_type1);
            }
        }
        if (StringUtils.isNotBlank(goods_name_like)) entity.getMap().put("goods_name_like", goods_name_like.trim());

        if (StringUtils.isNotBlank(dept_sn)) entity.getMap().put("dept_sn", dept_sn);

        if (StringUtils.isNotBlank(handle_name_like)) {
            entity.getMap().put("handle_name_like_1", handle_name_like.trim());
            dynaBean.set("handle_name_like_1", handle_name_like.trim());
        }
        // 数据级别判断开始
        Long __dept_id = ui.getDept_id(); // 默认为当前用户所在部门
        int max_dlevel = super.getMaxDLevel(ui.getId()); // 获取当前用户的最高可视部门级别
        logger.info("Max level : {}", max_dlevel);
        request.setAttribute("max_dlevel", max_dlevel);
        switch (max_dlevel) {
            case 9:
                // 集团及以下部门可见
                __dept_id = 0L; // 0表示部门根节点，即“多媒体事业本部”

                // 分公司列表
                KonkaDept kd = new KonkaDept();
                kd.setDept_type(3);
                List<KonkaDept> kdList = super.getFacade().getKonkaDeptService().getKonkaDeptList(kd);
                request.setAttribute("kdList", kdList);

                break;
            case 8:
                // 分公司及以下部门可见
                KonkaDept dept_fgs = super.getKonkaDeptForFgs(ui.getDept_id()); // 查询部门分公司
                if (null != dept_fgs && null != dept_fgs.getDept_sn()) {
                    entity.getMap().put("dept_sn", dept_fgs.getDept_sn());
                }
                break;
            case 7:
                // 我所在的部门及以下部门可见
                __dept_id = ui.getDept_id(); // 默认为当前用户所在部门
                entity.getMap().put("dept_id_start", __dept_id);
                break;
            case 0:
                __dept_id = ui.getDept_id(); // 默认为当前用户所在部门
                entity.getMap().put("filter_by_ywy_id_eq", ui.getId());
                break;
            default:
                // 出错
        }
        entity.getMap().put("session_user_id", ui.getId());

        Long recordCount = super.getFacade().getJStocksStoreSummaryService().getJStocksStoreSummaryForR3ShopCount(entity);
        pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
        entity.getRow().setFirst(pager.getFirstRow());
        entity.getRow().setCount(pager.getRowCount());

        if (StringUtils.isNotBlank(excel_to_all)) {

            // 为“总部财务部”角色开放导出记录数限制
            PeRoleUser roleUser = new PeRoleUser();
            roleUser.setUser_id(ui.getId());
            List<PeRoleUser> roleUserList = getFacade().getPeRoleUserService().getPeRoleUserList(roleUser);

            boolean is_exit = true;
            for (PeRoleUser t : roleUserList) {
                if (t.getRole_id() == 29 || t.getRole_id() == 10) {
                    is_exit = false;
                    break;
                }
            }
            if (is_exit) {
                if (recordCount > 30000) {// 导出数据不能超过30000条
                    super.renderJavaScript(response, "alert('导出记录不能超过30000条，请缩小查找范围！');history.back();");
                    return null;
                }
            }

            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" + EncryptUtils.encodingFileName("客户分仓库存数据") + ".xls");

            entity.getRow().setCount(recordCount.intValue());
            Date today = new Date();
            Date fistDayOfMonth = getFistDayOfMonth();
            int zzts = daysBetween(fistDayOfMonth == null ? today : fistDayOfMonth, today);
            // 算周转天数,这个天数一定不能缺少
            entity.getMap().put("zzts", zzts);
            List<JStocksStoreSummary> entityList1 = getFacade().getJStocksStoreSummaryService().getJStocksStoreSummaryForR3ShopList(entity);
            request.setAttribute("allList", entityList1);
            return mapping.findForward("view");
        }
        // 客户类型
        KonkaCategory kc = new KonkaCategory();
        kc.setC_type(10);
        kc.setIs_del(0);
        request.setAttribute("konkaCategoryList", super.getFacade().getKonkaCategoryService().getKonkaCategoryList(kc));

        Date today = new Date();
        Date fistDayOfMonth = getFistDayOfMonth();
        int zzts = daysBetween(fistDayOfMonth == null ? today : fistDayOfMonth, today);
        // 算周转天数,这个天数一定不能缺少
		entity.getMap().put("zzts_IN", zzts);
        List<JStocksStoreSummary> entityList = super.getFacade().getJStocksStoreSummaryService().getJStocksStoreSummaryForR3ShopPaginatedList(entity);

        Object totlePageNum = super.getFacade().getJStocksStoreSummaryService().getJStocksStoreSummaryForR3ShopSumCount(entity);// 求库存合计,根据查询条件变化

        if (null != totlePageNum) {
            dynaBean.set("totlePageNum", totlePageNum);
        } else {
            dynaBean.set("totlePageNum", "0");
        }
        if (entityList != null && entityList.size() > 0) {
            if (entityList.get(0) != null && entityList.get(0).getAdd_date() != null) {
                request.setAttribute("add_date", entityList.get(0).getAdd_date());
            }
        }
        request.setAttribute("entityList", entityList);

        List<KonkaDept> konkaDeptList = super.getDeptInfoListWithOutLimit(mapping, form, request, response);
        request.setAttribute("konkaDeptList", konkaDeptList);

        return mapping.findForward("list");
    }


    public static Date getFistDayOfMonth() {
        String str = DateUtilsForMonth.getFirstDay();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date day = null;
        try {
            day = sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return day;
    }


    /**
     * 计算年月的最大天数
     * 
     * @param year
     * @param month
     * @return
     */
    public int getMaxDay(int year, int month) {
        int maxDay = 0;
        int day = 1;
        /**
         * 与其他语言环境敏感类一样，Calendar 提供了一个类方法 getInstance， 以获得此类型的一个通用的对象。Calendar 的 getInstance 方法返回一 个
         * Calendar 对象，其日历字段已由当前日期和时间初始化：
         */
        Calendar calendar = Calendar.getInstance();
        /**
         * 实例化日历各个字段,这里的day为实例化使用
         */
        calendar.set(year, month - 1, day);
        /**
         * Calendar.Date:表示一个月中的某天 calendar.getActualMaximum(int field):返回指定日历字段可能拥有的最大值
         */
        maxDay = calendar.getActualMaximum(Calendar.DATE);
        return maxDay;
    }

    /**
     * 字符串的日期格式的计算
     */
    public static int daysBetween(String smdate, String bdate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(smdate));
        long time1 = cal.getTimeInMillis();
        cal.setTime(sdf.parse(bdate));
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);
        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 计算两个日期之间相差的天数
     * 
     * @param smdate 较小的时间
     * @param bdate 较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int daysBetween(Date smdate, Date bdate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        smdate = sdf.parse(sdf.format(smdate));
        bdate = sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);
        return Integer.parseInt(String.valueOf(between_days));
    }

    // 取得分公司
    public ActionForward getJBDataBydeptCode(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        DynaBean dynaBean = (DynaBean) form;
        String dept_sn = (String) dynaBean.get("dept_sn"); // 分公司
        List<String> jbList = new ArrayList<String>();

        if (dept_sn != null && dept_sn.length() > 0) {

            List<KonkaDept> kklist = new ArrayList<KonkaDept>();

            KonkaDept kk2 = new KonkaDept();

            KonkaDept kk = new KonkaDept();
            kk.setIs_del(0);
            kk.setDept_sn(dept_sn);
            kklist = super.getFacade().getKonkaDeptService().getKonkaDeptList(kk);
            if (kklist != null && kklist.size() == 1) {
                kk = kklist.get(0);
                kk2.getMap().put("fgs_dept_id", kk.getDept_id());
                kk2.setIs_del(0);
                kklist.clear();
                kklist = super.getFacade().getKonkaDeptService().getKonkaDeptAndJbNameList(kk2);

                request.setAttribute("kkList", kklist);
            }

            for (KonkaDept x : kklist) {
                jbList.add(x.getDept_name());
            }
            super.renderJson(response, JSON.toJSONString(jbList));
        } else {
            super.renderJson(response, JSON.toJSONString(jbList));
        }

        return null;
    }

    /**
     * DRP审批单据时,需要查询客户子仓周转天数
     * 
     * 数据来源JStocksStoreSummary
     */
    public ActionForward getCustomerInventoryDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 1.客户编码(客户可能对应多个子仓)
        // 2.机型数据(有可能是多条)
        // 3.时间范围是从1号到当天
        // 4.返回结果也是多条,有可能多于传过来的条数

        DynaBean dynaBean = (DynaBean) form;
        String tradeIndex = (String) dynaBean.get("tradeIndex");

        String customerCode = "";
        KonkaOrderInfo ko = new KonkaOrderInfo();
        ko.setTrade_index(tradeIndex);
        ko = super.getFacade().getKonkaOrderInfoService().getKonkaOrderInfo(ko);
        customerCode = ko.getAg();

        String[] goods_name_array = null;
        List<KonkaOrderInfoDetails> dList = new ArrayList<KonkaOrderInfoDetails>();

        KonkaOrderInfoDetails kd = new KonkaOrderInfoDetails();
        kd.setOrder_id(ko.getId());
        dList = super.getFacade().getKonkaOrderInfoDetailsService().getKonkaOrderInfoDetailsList(kd);
        if (dList != null && dList.size() > 0) {
            int i = 0;
            goods_name_array = new String[dList.size()];
            for (KonkaOrderInfoDetails detail : dList) {
                goods_name_array[i] = detail.getPd_name();
                i++;
            }
        }

        // PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
        // entity.getMap().put("session_user_id", ui.getId());


        JStocksStoreSummary entity = new JStocksStoreSummary();

        // 售达方
        entity.setR3_code(customerCode);
        // 送达方
        String sale_r3_code_like = ko.getWe();
        if (StringUtils.isNotBlank(sale_r3_code_like)) {
            entity.getMap().put("sale_r3_code_like", sale_r3_code_like.trim());
        }

        // 算周转天数,这个天数一定不能缺少
        Date today = new Date();
        Date fistDayOfMonth = getFistDayOfMonth();
        int zzts = daysBetween(fistDayOfMonth == null ? today : fistDayOfMonth, today);
		entity.getMap().put("zzts_IN", zzts);
        // 机型数据
        entity.getMap().put("goods_name_map", goods_name_array);
        List<JStocksStoreSummary> entityList = null;
        if (goods_name_array.length > 0) {
            entity.getRow().setCount(10000 * 900);// 有数百万记录?如果有应该换系统了. 由于没有其它sql.暂时只这样处理
            entityList = super.getFacade().getJStocksStoreSummaryService().getJStocksStoreSummaryForR3ShopList(entity);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        List<CustomerInventoryDetailVO> objList = new ArrayList<CustomerInventoryDetailVO>();
        if (entityList != null) {
            for (JStocksStoreSummary js : entityList) {
                CustomerInventoryDetailVO cd = new CustomerInventoryDetailVO();
                cd.setFgs((String) js.getMap().get("dept_name"));
                cd.setInitnum(js.getInit_num());
                cd.setInnum(js.getInit_num());
                cd.setInventoryCode((String) js.getMap().get("store_sn"));
                cd.setInventoryName((String) js.getMap().get("store_name"));
                cd.setModel_code(js.getGoods_name());
                cd.setPc_date(sdf.format(js.getOpr_date()));
                cd.setR3code(js.getR3_code());
                cd.setSalenum(js.getOut_num());
                cd.setSdf((String) js.getMap().get("sale_r3_code"));
                cd.setSync_date(sdf.format(js.getAdd_date()));
                cd.setXynum(Long.valueOf(js.getMap().get("cur_num").toString()));
                cd.setZzts(new BigDecimal(js.getMap().get("zzts").toString()).intValue());
                objList.add(cd);
            }
        }
        JSONArray json = JSONArray.fromObject(objList);

        super.renderJson(response, json.toString());
        return null;

    }
}
