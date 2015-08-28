package com.ebiz.mmt.web.struts.manager.admin;

import com.ebiz.mmt.domain.*;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.MmtFilePathConfig;
import com.ebiz.mmt.web.struts.MobileBaseAction;
import com.ebiz.ssi.util.EncryptUtils;
import com.ebiz.ssi.web.struts.bean.Pager;
import com.ebiz.ssi.web.struts.bean.UploadFile;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

public class KonkaMobileSailDataAction extends MobileBaseAction {

    @Override
    public ActionForward unspecified(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        return this.list(mapping, form, request, response);
    }

    public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        if (null == super.checkUserModPopeDom(form, request, "0")) {
            return super.checkPopedomInvalid(request, response);
        }

        setNaviStringToRequestScope(form, request);
        // super.getModPopeDom(form, request); //先屏蔽，查询的数据太多，影响效率

        PeProdUser peProdUser =
                (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

        DynaBean dynaBean = (DynaBean) form;



        super.encodeCharacterForGetMethod(dynaBean, request);

        Pager pager = (Pager) dynaBean.get("pager");

        String report_name_like = (String) dynaBean.get("report_name_like");
        String date_begin = (String) dynaBean.get("date_begin");
        String date_end = (String) dynaBean.get("date_end");
        String customer_name_like = (String) dynaBean.get("customer_name_like");
        String is_del = (String) dynaBean.get("is_del");
        String excel_all = (String) dynaBean.get("excel_all");
        String dept_name_like = (String) dynaBean.get("dept_name_like");
        String single_price_end = (String) dynaBean.get("single_price_end");
        String single_price_begin = (String) dynaBean.get("single_price_begin");

        String l3_dept_id = (String) dynaBean.get("l3_dept_id");
        String l4_dept_id = (String) dynaBean.get("l4_dept_id");
        String l5_dept_id = (String) dynaBean.get("l5_dept_id");
        String c_comm = (String) dynaBean.get("c_comm");
        String excel_to_all = (String) dynaBean.get("excel_to_all");

        // 是否显示0销量,0显示，1不显示
        String isShow = (String) dynaBean.get("isShow");
        String r3_code = (String) dynaBean.get("r3_code");

        // 型号模糊搜索
        String model_like = (String) dynaBean.get("model_like");

        KonkaMobileSailData entity = new KonkaMobileSailData();
        super.copyProperties(entity, form);

        if (StringUtils.isNotBlank(single_price_end)) {
            entity.getMap().put("single_price_end", single_price_end);
        }
        if (StringUtils.isNotBlank(single_price_begin)) {
            entity.getMap().put("single_price_begin", single_price_begin);
        }
        if (StringUtils.isNotBlank(model_like)) {
            entity.getMap().put("model_like", model_like);
        }

        PeRoleUser _peRoleUser = new PeRoleUser();
        _peRoleUser.setUser_id(peProdUser.getId());
        List<PeRoleUser> peRoleUserList =
                this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

        boolean role_id_eq_10 = false; // 非系统管理员
        boolean role_id_eq_60 = false; // 业务员
        // boolean role_id_eq_30 = false; // 分公司管理员
        // boolean role_id_eq_60 = false;
        String role_ids = "";
        for (PeRoleUser peRoleUser : peRoleUserList) {
            role_ids = role_ids + "," + peRoleUser.getRole_id();
            if (peRoleUser.getRole_id() == 10L) {
                role_id_eq_10 = true;
            }
            if (peRoleUser.getRole_id() == 60L) {
                role_id_eq_60 = true;
            }
        }

        entity.getMap().put("filter_by_job_id_eq", peProdUser.getJob_id());
        entity.getMap().put("session_user_id", peProdUser.getId());
        entity.getMap().put("is_not_gz", "yes");// 不是广州零售通，（不带发票的）
        if (!role_id_eq_10) {
            // 非系统管理员
            // 分公司用户 未指定分公司查询条件 取当前登录用户部门ID
            KonkaDept kd = super.getSuperiorForDeptType(peProdUser.getDept_id(), 3);

            if (null != kd) {
                // entity.setSubcomp_id(kd.getDept_id());
                if (null == entity.getMap().get("dept_id_start")) {
                    entity.getMap().put("dept_id_start", peProdUser.getDept_id());
                }
            }

            // if (!role_id_eq_30 && !role_id_eq_10) {
            // entity.getMap().put("filter_by_job_id_eq",
            // peProdUser.getJob_id());
            // entity.getMap().remove("dept_id_start");
            // }

            // 业务员
            if (role_id_eq_60) {
                entity.getMap().put("ye_name", peProdUser.getUser_name());

                if (null != peRoleUserList && peRoleUserList.size() == 1) {
                    entity.getMap().put("filter_by_ywy", "true");
                    entity.getMap().remove("dept_id_start");
                }
            }

        } else {
            // 系统管理员
            request.setAttribute("isFgsUser", "true");
        }

        // 数据级别判断开始
        Long __dept_id = peProdUser.getDept_id(); // 默认为当前用户所在部门
        int max_dlevel = super.getMaxDLevel(peProdUser.getId()); // 获取当前用户的最高可视部门级别
        logger.info("Max level : {}", max_dlevel);
        switch (max_dlevel) {
            case 9:
                // 集团及以下部门可见
                __dept_id = 0L; // 0表示部门根节点，即“多媒体事业本部”
                break;
            case 8:
                // 分公司及以下部门可见
                KonkaDept dept_fgs = super.getKonkaDeptForFgs(peProdUser.getDept_id()); // 查询部门分公司
                if (null != dept_fgs && null != dept_fgs.getDept_id()) {
                    __dept_id = dept_fgs.getDept_id(); // 分公司部门ID
                    entity.getMap().put("dept_id_start", __dept_id);
                    entity.getMap().put("filter_by_job_id", peProdUser.getJob_id());
                }
                break;
            case 7:
                // 我所在的部门及以下部门可见
                __dept_id = peProdUser.getDept_id(); // 默认为当前用户所在部门
                entity.getMap().put("dept_id_start", __dept_id);
                entity.getMap().put("filter_by_job_id_", peProdUser.getJob_id());
                break;
            case 0:
                __dept_id = peProdUser.getDept_id(); // 默认为当前用户所在部门
                entity.getMap().put("filter_by_ywy_id_eq", peProdUser.getId());
                entity.getMap().put("filter_by_job_id_eq", peProdUser.getJob_id());
                break;
            default:
                // 出错
        }

        if (StringUtils.isNotBlank(l5_dept_id)) {
            entity.getMap().put("dept_id_start_value", l5_dept_id);
        } else if (StringUtils.isNotBlank(l4_dept_id)) {
            entity.getMap().put("dept_id_start_value", l4_dept_id);
        } else if (StringUtils.isNotBlank(l3_dept_id)) {
            entity.getMap().put("dept_id_start_value", l3_dept_id);
        }

        entity.getMap().put("customer_name_like", customer_name_like);
        entity.getMap().put("report_name_like", report_name_like);
        entity.getMap().put("dept_name_like", dept_name_like);
        entity.getMap().put("c_comm", c_comm);

        // 是否显示0销量
        if (GenericValidator.isBlankOrNull(isShow)) {

        } else {
            if ("1".equals(isShow)) {// 有销量的
                entity.getMap().put("not_Show", "and a.num <> 0");
            } else {// 零销量的
                entity.getMap().put("not_Show", "and a.num = 0");
            }
        }

        // 是否有效
        entity.setIs_del(StringUtils.isNotBlank(is_del) ? Integer.valueOf(is_del) : 0);

        if (StringUtils.isNotBlank(r3_code)) {
            entity.getMap().put("r3_id", r3_code);
        }

        SimpleDateFormat mm_fmt = new SimpleDateFormat("yyyy-MM");
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat fmt1 = new SimpleDateFormat("yyyy-MM-dd");

        if (StringUtils.isNotBlank(date_begin)) {
            entity.getMap().put("report_date_begin", date_begin + " 00:00:00");
        } else {
            // 判断是否有时间没有的话取当前一个月的时间
            entity.getMap().put("report_date_begin", mm_fmt.format(new Date()) + "-01 00:00:00");
            dynaBean.set("date_begin", mm_fmt.format(new Date()) + "-01");
        }
        if (StringUtils.isNotBlank(date_end)) {
            entity.getMap().put("report_date_end", date_end + " 23:59:59");
        } else {
            entity.getMap().put("report_date_end", fmt.format(new Date()));
            dynaBean.set("date_end", fmt1.format(new Date()));
        }
        Long recordCount =
                getFacade().getKonkaMobileSailDataService().getKonkaMobileSailDataCount(entity);

        pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
        entity.getRow().setFirst(pager.getFirstRow());
        entity.getRow().setCount(pager.getRowCount());

        if (StringUtils.isNotBlank(excel_all) && "1".equals(excel_all)) {
            if (recordCount.intValue() > 8000) {
                renderJavaScript(response, "alert('" + super.getMessage(request, "export.too.many")
                        + "！');history.back();");
                return null;
            }
            entity.getRow().setCount(recordCount.intValue());
            List<KonkaMobileSailData> entityList1 =
                    getFacade().getKonkaMobileSailDataService()
                            .getKonkaMobileSailDataInR3InfoPaginatedList(entity);

            dynaBean.set("excel_all", excel_all);
            request.setAttribute("allList", entityList1);

        }

        if (StringUtils.isNotBlank(excel_to_all) && "2".equals(excel_to_all)) {

            if (recordCount.intValue() > 100000) {
                renderJavaScript(response, "alert('" + super.getMessage(request, "export.under10w")
                        + "！');history.back();");
                return null;
            }

            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + EncryptUtils.encodingFileName("销售明细") + ".xls");

            entity.getRow().setCount(recordCount.intValue());
            List<KonkaMobileSailData> entityList2 =
                    getFacade().getKonkaMobileSailDataService()
                            .getKonkaMobileSailDataInR3InfoPaginatedList(entity);
            request.setAttribute("allList", entityList2);
            return mapping.findForward("view");
        }

        // 尺寸sizeList
        List<MobileSelectItem> sizeList = new ArrayList<MobileSelectItem>();
        for (String str : size_strs) {
            MobileSelectItem t = new MobileSelectItem();
            t.setId(str);
            t.setName(str);
            sizeList.add(t);
        }
        request.setAttribute("sizeList", sizeList);

        // 是否显示0销量
        ArrayList<HashMap<String, String>> showList = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> map = new HashMap<String, String>();
        HashMap<String, String> map2 = new HashMap<String, String>();
        map.put("val", "0");
        map.put("name", "是");
        showList.add(map);

        map2 = new HashMap<String, String>();
        map2.put("val", "1");
        map2.put("name", "否");
        showList.add(map2);
        request.setAttribute("showList", showList);

        // 所属客户
        KonkaCategory konkaCategory = new KonkaCategory();
        // konkaCategory.setC_type(10);
        // konkaCategory.getMap().put("group_by_par_index", true);
        List<KonkaCategory> konkaCategoryList =
                super.getFacade().getKonkaCategoryService()
                        .getKonkaCategoryGroupByCCommList(konkaCategory);
        request.setAttribute("konkaCategoryList", konkaCategoryList);

        Date now = new Date();
        Date yesterday = DateUtils.addDays(now, -1);
        Date two_days_ago = DateUtils.addDays(now, -2);
        request.setAttribute("now", now);
        request.setAttribute("yesterday", yesterday);
        request.setAttribute("two_days_ago", two_days_ago);

        KonkaDeptTree t = new KonkaDeptTree();
        t.setDept_id(peProdUser.getDept_id());
        t = super.getFacade().getKonkaDeptTreeService().getKonkaDeptTree(t);
        if (null != t && t.getIs_leaf() == 1) {
            request.setAttribute("current_dept", t);
        }

        request.setAttribute("cs_par_id", peProdUser.getDept_id());


        List<KonkaMobileSailData> entityList =
                getFacade().getKonkaMobileSailDataService()
                        .getKonkaMobileSailDataInR3InfoPaginatedList(entity);
        request.setAttribute("entityList", entityList);

        if (GenericValidator.isBlankOrNull(isShow) && "0".equals(isShow)) {} else {
            List<KonkaMobileSailData> numMoneyentityList =
                    getFacade().getKonkaMobileSailDataService()
                            .getKonkaMobileSailDataNumAndMoneyCount(entity);
            if (null != numMoneyentityList && numMoneyentityList.size() > 0) {
                KonkaMobileSailData numMoney = numMoneyentityList.get(0);

                request.setAttribute("allCount", null == numMoney.getMap().get("allnum")
                        ? 0
                        : numMoney.getMap().get("allnum"));
                request.setAttribute("allMoney", null == numMoney.getMap().get("allprice")
                        ? 0
                        : numMoney.getMap().get("allprice"));
            }
        }
        return mapping.findForward("list");
    }



    public ActionForward exportExcel(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        PeProdUser peProdUser =
                (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

        DynaBean dynaBean = (DynaBean) form;
        String report_name_like = (String) dynaBean.get("report_name_like");
        String date_begin = (String) dynaBean.get("date_begin");
        String date_end = (String) dynaBean.get("date_end");
        String customer_name_like = (String) dynaBean.get("customer_name_like");
        String yw_name = (String) dynaBean.get("yw_name");
        String is_del = (String) dynaBean.get("is_del");
        String dept_name_like = (String) dynaBean.get("dept_name_like");

        String l3_dept_id = (String) dynaBean.get("l3_dept_id");
        String l4_dept_id = (String) dynaBean.get("l4_dept_id");
        String l5_dept_id = (String) dynaBean.get("l5_dept_id");
        String c_comm = (String) dynaBean.get("c_comm");

        KonkaMobileSailData entity = new KonkaMobileSailData();
        super.copyProperties(entity, form);

        PeRoleUser _peRoleUser = new PeRoleUser();
        _peRoleUser.setUser_id(peProdUser.getId());
        List<PeRoleUser> peRoleUserList =
                this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

        boolean role_id_eq_10 = false; // 非系统管理员
        boolean role_id_eq_60 = false; // 业务员
        boolean role_id_eq_30 = false; // 分公司管理员
        // boolean role_id_eq_60 = false;
        for (PeRoleUser peRoleUser : peRoleUserList) {
            if (peRoleUser.getRole_id() == 10L) {
                role_id_eq_10 = true;
            }
            if (peRoleUser.getRole_id() == 60L) {
                role_id_eq_60 = true;
            }
            if (peRoleUser.getRole_id() == 30L) {
                role_id_eq_30 = true;
            }
        }

        if (StringUtils.isNotBlank(l5_dept_id)) {
            entity.getMap().put("dept_id_start", l5_dept_id);
        } else if (StringUtils.isNotBlank(l4_dept_id)) {
            entity.getMap().put("dept_id_start", l4_dept_id);
        } else if (StringUtils.isNotBlank(l3_dept_id)) {
            entity.getMap().put("dept_id_start", l3_dept_id);
        }
        entity.getMap().put("session_user_id", peProdUser.getId());

        if (!role_id_eq_10) {
            // 非系统管理员
            // 分公司用户 未指定分公司查询条件 取当前登录用户部门ID
            KonkaDept kd = super.getSuperiorForDeptType(peProdUser.getDept_id(), 3);
            if (null != kd) {
                // entity.setSubcomp_id(kd.getDept_id());
                if (null == entity.getMap().get("dept_id_start")) {
                    entity.getMap().put("dept_id_start", peProdUser.getDept_id());
                }
            }

            if (!role_id_eq_30 && !role_id_eq_10) {
                entity.getMap().put("filter_by_job_id_eq", peProdUser.getJob_id());
                entity.getMap().remove("dept_id_start");
            }

            // 业务员
            if (role_id_eq_60) {
                entity.getMap().put("ye_name", peProdUser.getUser_name());

                if (null != peRoleUserList && peRoleUserList.size() == 1) {
                    entity.getMap().put("filter_by_ywy", "true");
                }
            } else {
                if (StringUtils.isNotBlank(yw_name)) {
                    entity.getMap().put("ye_name", yw_name);
                }
            }

        } else {
            // 系统管理员
            request.setAttribute("isFgsUser", "true");
        }

        entity.getMap().put("customer_name_like", customer_name_like);
        entity.getMap().put("report_name_like", report_name_like);
        entity.getMap().put("dept_name_like", dept_name_like);
        entity.getMap().put("c_comm", c_comm);

        // 是否有效
        entity.setIs_del(StringUtils.isNotBlank(is_del) ? Integer.valueOf(is_del) : 0);

        if (StringUtils.isNotBlank(date_begin)) {
            entity.getMap().put("report_date_begin", date_begin + " 00:00:00");
        }
        if (StringUtils.isNotBlank(date_end)) {
            entity.getMap().put("report_date_end", date_end + " 23:59:59");
        }

        List<KonkaMobileSailData> entityList =
                getFacade().getKonkaMobileSailDataService()
                        .getKonkaMobileSailDataInR3InfoPaginatedList(entity);
        request.setAttribute("entityList", entityList);

        // 类别 下拉选项
        List<BasePdClazz> basePdClazzList =
                super.getFacade().getRetailMsBaseService().getKonkaBasePdClazzListByClsIds();
        request.setAttribute("basePdClazzList", basePdClazzList);

        // 尺寸sizeList
        List<MobileSelectItem> sizeList = new ArrayList<MobileSelectItem>();
        for (String str : size_strs) {
            MobileSelectItem t = new MobileSelectItem();
            t.setId(str);
            t.setName(str);
            sizeList.add(t);
        }
        request.setAttribute("sizeList", sizeList);
        request.setAttribute("date", DateFormatUtils.format(new Date(), "yyyyMMddHHmmss"));
        return mapping.findForward("excel");
    }

    /*
     * 办事处
     */
    public ActionForward getDept(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        // subcomp_id
        DynaBean dynaBean = (DynaBean) form;
        String subcomp_id = (String) dynaBean.get("subcomp_id");
        logger.info("-------subcomp_id--->{}", subcomp_id);
        if (StringUtils.isNotEmpty(subcomp_id)) {
            KonkaDept konkaDept = new KonkaDept();
            konkaDept.setPar_id(new Long(subcomp_id));

            List<KonkaDept> baseDeptList =
                    super.getFacade().getKonkaDeptService().getKonkaDeptList(konkaDept);

            List<String> dataList = new ArrayList<String>();
            for (KonkaDept _t : baseDeptList) {
                dataList.add(StringUtils.join(new String[] {"[\"", _t.getDept_name(), "\",\"",
                        String.valueOf(_t.getDept_id()), "\"]"}));
            }
            super.renderJson(response,
                    StringUtils.join(new String[] {"[", StringUtils.join(dataList, ","), "]"}));
        }
        return null;
    }

    /*
     * 产品型号
     */
    public ActionForward getModel(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        // subcomp_id
        DynaBean dynaBean = (DynaBean) form;
        String size_id = (String) dynaBean.get("size_id");
        if (StringUtils.isNotEmpty(size_id)) {

            // String[] cls_ids = { category_id };
            // List<PePdModel> pePdModelList = super.getFacade()
            // .getRetailMsBaseService().getKonkaPePdModelListByClsIds(
            // cls_ids);
            //
            List<String> dataList = new ArrayList<String>();
            PePdModel t = new PePdModel();
            t.getMap().put("led_name_like", size_id);
            t.getMap().put("OrderByMd", true);
            List<PePdModel> tList = new ArrayList<PePdModel>();
            tList = super.getFacade().getPePdModelService().getPePdModelList(t);
            for (PePdModel _t : tList) {
                dataList.add(StringUtils.join(new String[] {"[\"", _t.getMd_name(), "\",\"",
                        String.valueOf(_t.getPd_id()), "\"]"}));
            }
            super.renderJson(response,
                    StringUtils.join(new String[] {"[", StringUtils.join(dataList, ","), "]"}));
        }
        return null;
    }

    /*
     * 产品型号
     */
    public ActionForward getCType(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        // subcomp_id
        DynaBean dynaBean = (DynaBean) form;
        String c_comm = (String) dynaBean.get("c_comm");
        if (StringUtils.isNotEmpty(c_comm)) {

            List<String> dataList = new ArrayList<String>();
            KonkaCategory t = new KonkaCategory();
            t.setPar_index(c_comm);
            List<KonkaCategory> tList =
                    super.getFacade().getKonkaCategoryService().getKonkaCategoryList(t);
            for (KonkaCategory _t : tList) {
                dataList.add(StringUtils.join(new String[] {"[\"", _t.getC_name(), "\",\"",
                        String.valueOf(_t.getC_index()), "\"]"}));
            }
            super.renderJson(response,
                    StringUtils.join(new String[] {"[", StringUtils.join(dataList, ","), "]"}));
        }
        return null;
    }

    public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        if (null == super.checkUserModPopeDom(form, request, "2")) {
            return super.checkPopedomInvalid(request, response);
        }

        super.setNaviStringToRequestScope(form, request);

        setNaviStringToRequestScope(form, request);
        DynaBean dynaBean = (DynaBean) form;
        String id = (String) dynaBean.get("id");

        if (!GenericValidator.isLong(id)) {
            this.saveError(request, "errors.long", new String[] {id});
            return mapping.findForward("list");
        }

        KonkaMobileSailData entity = new KonkaMobileSailData();
        entity.setId(Long.valueOf(id));
        entity = super.getFacade().getKonkaMobileSailDataService().getKonkaMobileSailData(entity);

        if (null == entity) {
            saveError(request, "errors.long", new String[] {id});
            return mapping.findForward("list");
        }

        // the line below is added for pagination
        entity.setQueryString(super.serialize(request, "id", "mod_id"));
        // end
        super.copyProperties(form, entity);

        return mapping.findForward("input");
    }

    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        setNaviStringToRequestScope(form, request);

        DynaBean dynaBean = (DynaBean) form;
        String id = (String) dynaBean.get("id");
        String mod_id = (String) dynaBean.get("mod_id");
        String pd_id = (String) dynaBean.get("pd_id");

        KonkaMobileSailData entity = new KonkaMobileSailData();
        super.copyProperties(entity, form);

        if (StringUtils.isNotBlank(pd_id)) {
            entity.setModel_id(Long.valueOf(pd_id));
        }
        if (StringUtils.isNotBlank(id)) {// insert
            getFacade().getKonkaMobileSailDataService().modifyKonkaMobileSailData(entity);
            saveMessage(request, "entity.updated");
        }



        // the line below is added for pagination
        StringBuffer pathBuffer = new StringBuffer();
        pathBuffer.append(mapping.findForward("success").getPath());
        pathBuffer.append("&mod_id=" + mod_id);
        pathBuffer.append("&");
        pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
        ActionForward forward = new ActionForward(pathBuffer.toString(), true);
        // dynaBean.set("id",null);
        // ActionForward forward = new ActionForward(pathBuffer.toString(), true);
        return forward;

        // return this.list(mapping, form, request, response);
    }

    public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        if (null == super.checkUserModPopeDom(form, request, "4")) {
            return super.checkPopedomInvalid(request, response);
        }

        setNaviStringToRequestScope(form, request);
        super.getModPopeDom(form, request);

        DynaBean dynaBean = (DynaBean) form;
        String id = (String) dynaBean.get("id");
        String mod_id = (String) dynaBean.get("mod_id");

        if (!GenericValidator.isLong(id)) {
            String msg = super.getMessage(request, "errors.parm");
            super.renderJavaScript(response, "window.onload=function(){alert('" + msg
                    + "');history.back();}");
            return null;
        }

        KonkaMobileSailData entity = new KonkaMobileSailData();
        super.copyProperties(entity, dynaBean);
        entity.setId(Long.valueOf(id));
        entity.setIs_del(1);
        super.getFacade().getKonkaMobileSailDataService().modifyKonkaMobileSailData(entity);

        KonkaMobileSailDataBill Bill = new KonkaMobileSailDataBill();
        Bill.setSail_id(Long.valueOf(id));
        Bill.setIs_valid(1);
        super.getFacade().getKonkaMobileSailDataBillService().modifyKonkaMobileSailDataBill(Bill);

		// 先进先出
		entity = super.getFacade().getKonkaMobileSailDataService().getKonkaMobileSailData(entity);
		if (null != entity) {
			JBaseStore jBaseStore = new JBaseStore();
			jBaseStore.getMap().put("sale_r3_code", entity.getCustomer_r3_code());
			jBaseStore.getMap().put("r3_code", entity.getCustomer_r3_code());
			jBaseStore.setIs_del(0);
			List<JBaseStore> storeList = super.getFacade().getJBaseStoreService().getJBaseStoreForR3List(jBaseStore);
			if (entity.getNum() > 0) {// 销售无效，也就是当进货了

				if (null != storeList && storeList.size() > 0) {
					jBaseStore = storeList.get(0);
				}
				super.getFacade()
						.getJxcFifoStocksService()
						.stock_in(jBaseStore.getStore_id(), entity.getModel_id(), entity.getSingle_price(),
								entity.getNum().intValue(), new Date(), 70);

			} else if (entity.getNum() < 0) {

				// 销售退货的无效，当销售
				super.getFacade()
						.getJxcFifoStocksService()
						.stock_out(entity.getDept_id(), entity.getModel_id(), entity.getModel_name(),
								entity.getNum() * -1, entity.getSingle_price(), entity.getCustomer_r3_code(),
								jBaseStore.getStore_id(), 630, entity.getReport_id());

			}

		}
		// 先进先出
        saveMessage(request, "konka.sail.data.update");

        StringBuffer pathBuffer = new StringBuffer();
        pathBuffer.append(mapping.findForward("success").getPath());
        pathBuffer.append("&mod_id=" + mod_id);
        pathBuffer.append("&");
        // dynaBean.set("id",null);
        //

        pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "id", "pks",
                "method")));

        ActionForward forward = new ActionForward(pathBuffer.toString(), true);
        return forward;

        // return this.list(mapping, form, request, response);
    }

    public ActionForward imp(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        super.setNaviStringToRequestScope(form, request);

        return new ActionForward("/../manager/admin/KonkaMobileSailData/impform.jsp");
        // return mapping.findForward("input");
    }

    public ActionForward impSave(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        DynaBean dynaBean = (DynaBean) form;
        String mod_id = (String) dynaBean.get("mod_id");

        FileInputStream isFile = null;
        String fileSavePath = null;
        String ctxDir = "";


        List<UploadFile> uploadFileList = super.uploadFile(form, MmtFilePathConfig.LST_PATH);
        for (UploadFile uploadFile : uploadFileList) {
            if ("up_load_file".equals(uploadFile.getFormName())) {
                fileSavePath = uploadFile.getFileSavePath();
                break;
            }
        }

        if (fileSavePath == null && "".equals(fileSavePath)) {
            super.renderJavaScript(response, "alert('文件没有保存成功,数据不能导入！');history.back();");
            return null;
        }

        // 附件保存路径更改遗留问题
        if (StringUtils.contains(fileSavePath, MmtFilePathConfig.S_LST_PATH)) {
            ctxDir = "";
            fileSavePath = "/Attachment_new/konka-files/" + fileSavePath;
        }
        String filetype = fileSavePath.substring(fileSavePath.lastIndexOf(".") + 1);
        if (!filetype.equalsIgnoreCase("xls")) {
            super.renderJavaScript(response, "alert('上传数据格式不正确！');history.back();");
            return null;
        }
        fileSavePath = ctxDir + fileSavePath;
        isFile = new FileInputStream(fileSavePath);

        List<KonkaMobileImpSailData> entityList = new ArrayList<KonkaMobileImpSailData>();
        List<KonkaMobileImpSailData> errorList = new ArrayList<KonkaMobileImpSailData>();
        boolean excel_is_ok = true;// 验证通过标识位 是否是数字
        String regEx = "^-?\\d{1,10}(\\.\\d+)?$";
        String regDate = "^[0-9]{4}-[0-9]{2}-[0-9]{2}$";
        Pattern vali_dig = Pattern.compile(regEx);
        Pattern vali_date = Pattern.compile(regDate);

        int startRow = 2;
        int startCol = 0;
        HSSFWorkbook workbook = new HSSFWorkbook(isFile);
        HSSFSheet sheet = null;

        // 验证数据完整性BEGIN
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            sheet = workbook.getSheetAt(i);
            int rowCounts = sheet.getLastRowNum();// 行数

            for (int j = startRow; j <= rowCounts; j++) {// 获取每行
                HSSFRow row = sheet.getRow(j);
                KonkaMobileImpSailData data = new KonkaMobileImpSailData();

                String c0 = getCellText(row, startCol);
                String c1 = getCellText(row, startCol + 1);
                String c2 = getCellText(row, startCol + 2);
                String c3 = getCellText(row, startCol + 3);
                String c4 = getCellText(row, startCol + 4);
                String c5 = getCellText(row, startCol + 5);
                String c6 = getCellText(row, startCol + 6);
                String c7 = getCellText(row, startCol + 7);
                String c8 = getCellText(row, startCol + 8);
                String c9 = getCellText(row, startCol + 9);
                String c10 = getCellText(row, startCol + 10);
                String c11 = getCellText(row, startCol + 11);
                String c12 = getCellText(row, startCol + 12);
                String c13 = getCellText(row, startCol + 13);
                String c14 = getCellText(row, startCol + 14);

                // 门店编号、直销员岗位id、上报时间不能为空
                if (StringUtils.isBlank(c2) && StringUtils.isBlank(c3) && StringUtils.isBlank(c5)) {
                    break;
                }

                // 门店名称
                if (StringUtils.isNotBlank(c0)) {
                    data.setStore_name(StringUtils.trim(c0));
                } else {
                    excel_is_ok = false;
                    data.getMap().put("msg0", "【*门店名称】不能为空！");
                }
                data.getMap().put("c0", c0);
                // 全称
                data.setR3_shop_name(StringUtils.trim(c1));

                // 2.门店编号
                if (StringUtils.isNotBlank(c2) && GenericValidator.isLong(StringUtils.trim(c2))) {
                    KonkaR3Store store = new KonkaR3Store();
                    store.setStore_id(Long.valueOf(StringUtils.trim(c2)));
                    List<KonkaR3Store> storeList =
                            super.getFacade().getKonkaR3StoreService().getKonkaR3StoreList(store);
                    if (storeList.size() == 0) {
                        excel_is_ok = false;
                        data.getMap().put("msg2", "门店不存在！");
                    } else {
                        data.setStore_id(StringUtils.trim(c2));
                    }

                } else {
                    excel_is_ok = false;
                    data.getMap().put("msg2", "【*门店编号】不能为空，且必须为正整数！");
                }
                data.getMap().put("c2", c2);

                // 3. 岗位ID
                if (StringUtils.isNotBlank(c3)) {
                    data.setZxy_job_id(StringUtils.trim(c3));
                } else {
                    excel_is_ok = false;
                    data.getMap().put("msg3", "【*岗位ID】不能为空！");
                }
                data.getMap().put("c3", c3);

                // 直销员姓名
                data.getMap().put("c4", c4);

                // 6. 上报日期
                if (StringUtils.isNotBlank(c5) && vali_date.matcher(c5).matches()) {
                    data.setReport_date(c5);
                } else {
                    excel_is_ok = false;
                    data.getMap().put("msg5", "【*上报日期】不能为空或日期格式错误，请填写正确的日期格式: yyyy-MM-dd！");
                }
                data.getMap().put("c5", c5);

                // 型号名称
                if (StringUtils.isNotBlank(c6)) {
                  
                	//检查机型是否是无效机型或者是错误的机型
                   PePdModel pepdmodel=new PePdModel();
                   pepdmodel.setMd_name(c6);
                   pepdmodel.setIs_del(0);
                   List<PePdModel> modlist=super.getFacade().getPePdModelService().getPePdModelList(pepdmodel);
                   if(null!=modlist && modlist.size()==1){
                	   //机型存在并且是没停用的即可导入
                	   data.setMd_name(c6);  
                   }else{
                	   // 否则不可导入
                	   excel_is_ok = false;
                       data.getMap().put("msg6", "【*型号名称】为错误机型或无效机型");
                   }
                } else {
                    excel_is_ok = false;
                    data.getMap().put("msg6", "【*型号名称】不能为空");
                }
                data.getMap().put("c6", c6);

                // 数量
                if (StringUtils.isNotBlank(c7)) {
                    if (GenericValidator.isLong(c7)) {
                        data.setNum(c7);
                    } else {
                        excel_is_ok = false;
                        data.getMap().put("msg7", "【数量】不合法，必须填写数字。");
                    }
                } else {
                    excel_is_ok = false;
                    data.getMap().put("msg7", "【数量】不合法，不能为空。");
                }
                data.getMap().put("c7", subZeroAndDot(c7));

                // 单价
                if (StringUtils.isNotBlank(c8)) {
                    String c8v = StringUtils.replace(StringUtils.replace(c8, ",", ""), "，", "");

                    if (StringUtils.isNotBlank(c8v) && vali_dig.matcher(c8v).matches()) {
                        data.setPrice(c8v);
                    } else {
                        excel_is_ok = false;
                        data.getMap().put("msg8", "【单价】不合法，必须填写数字。");
                    }
                    data.getMap().put("c8", subZeroAndDot(c8v));
                } else {
                    excel_is_ok = false;
                    data.getMap().put("msg8", "【单价】不合法，不能为空。");
                    data.getMap().put("c8", c8);
                }

                // 总金额
                if (StringUtils.isNotBlank(c9)) {
                    String c9v = StringUtils.replace(StringUtils.replace(c9, ",", ""), "，", "");

                    if (StringUtils.isNotBlank(c9v) && vali_dig.matcher(c9v).matches()) {
                        data.setAll_price(c9v);
                    } else {
                        excel_is_ok = false;
                        data.getMap().put("msg9", "【总金额】不合法，必须填写数字且不能包含公式。");
                    }
                    data.getMap().put("c9", subZeroAndDot(c9v));
                } else {
                    excel_is_ok = false;
                    data.getMap().put("msg9", "【总金额】不合法，不能为空且不能包含公式。");
                    data.getMap().put("c9", c9);
                }

                // 消费者姓名
                data.setCustomer_name(c10);
                data.getMap().put("c10", c10);

                // 消费者电话
                data.setCustomer_tel(c11);
                data.getMap().put("c11", c11);

                // 消费者身份证
                data.setCustomer_id_card(c12);
                data.getMap().put("c12", c12);

                // 地址
                data.setCustomer_addr(c13);
                data.getMap().put("c13", c13);

                // 备注
                data.setMemo(c14);
                data.getMap().put("c14", c14);

                if (excel_is_ok) {// 如果excel_is_ok为true,则保存，否则将错误信息返回到页面中
                    entityList.add(data);
                } else {
                    errorList.add(data);
                }
                excel_is_ok = true;
            }
        }

        // if (!excel_is_ok) {
        // request.setAttribute("demoEntityList", entityList);
        // return new
        // ActionForward("/../manager/admin/KonkaMobileSailData/impview.jsp");
        // }

        Long exsit_count =
                super.getFacade().getKonkaMobileImpSailDataService()
                        .createKonkaMobileImpSailDataToView(entityList);
        // 如果错误列表不为空，将列表返回
        if (null != errorList && errorList.size() > 0) {
            request.setAttribute("demoEntityList", errorList);
            return new ActionForward("/../manager/admin/KonkaMobileSailData/impview.jsp");
        }

        String msg = "成功导入 " + (entityList.size()) + " 条数据！";
        if (0 != (entityList.size() - exsit_count)) {
            super.saveError(request, "message", msg);
        } else {
            super.saveMessage(request, "message", msg);
        }

        StringBuffer pathBuffer = new StringBuffer();
        pathBuffer.append("/../manager/admin/KonkaMobileSailData.do?method=list");
        pathBuffer.append("&mod_id=").append(mod_id).append("&");
        ActionForward forward = new ActionForward(pathBuffer.toString(), true);
        // end
        return forward;
    }

    private String getCellText(HSSFRow row, int col) {
        if (row.getCell(col) == null) {
            return "";
        }
        DecimalFormat df = new DecimalFormat("#0");
        DecimalFormat df1 = new DecimalFormat("#0.00");
        HSSFCell cell = row.getCell(col);
        String data = "";
        if (null != cell) {
            if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                if (col != 7 && col != 2) {
                    data = df1.format(cell.getNumericCellValue());
                } else {
                    data = df.format(cell.getNumericCellValue());
                }
            } else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
                data = cell.getRichStringCellValue().toString();
            }
        }
        return data.trim();
    }

    /**
     * 使用java正则表达式去掉多余的.与0
     * 
     * @param s
     * @return
     */
    public static String subZeroAndDot(String s) {
        if (null != s && s.indexOf(".") > 0) {
            s = s.replaceAll("0+?$", "");// 去掉多余的0
            s = s.replaceAll("[.]$", "");// 如最后一位是.则去掉
        }
        return s;
    }

    public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        if (null == super.checkUserModPopeDom(form, request, "2")) {
            return super.checkPopedomInvalid(request, response);
        }

        super.setNaviStringToRequestScope(form, request);

        setNaviStringToRequestScope(form, request);
        DynaBean dynaBean = (DynaBean) form;
        String id = (String) dynaBean.get("id");

        if (!GenericValidator.isLong(id)) {
            this.saveError(request, "errors.long", new String[] {id});
            return mapping.findForward("list");
        }

        KonkaMobileSailData entity = new KonkaMobileSailData();
        entity.setId(Long.valueOf(id));
        entity = super.getFacade().getKonkaMobileSailDataService().getKonkaMobileSailData(entity);

        if (null == entity) {
            saveError(request, "errors.long", new String[] {id});
            return mapping.findForward("list");
        }

        // the line below is added for pagination
        entity.setQueryString(super.serialize(request, "id", "mod_id"));
        // end
        super.copyProperties(form, entity);

        return mapping.findForward("view");
    }
}
