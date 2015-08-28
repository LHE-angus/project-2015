package com.ebiz.mmt.web.struts.manager.admin;

import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.alibaba.fastjson.JSON;
import com.ebiz.mmt.domain.Attachment;
import com.ebiz.mmt.domain.KonkaBaseTypeData;
import com.ebiz.mmt.domain.KonkaCategory;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaMobileFightReport;
import com.ebiz.mmt.domain.KonkaMobilePd;
import com.ebiz.mmt.domain.KonkaMobilePdBrand;
import com.ebiz.mmt.domain.KonkaMobileSpRelation;
import com.ebiz.mmt.domain.KonkaR3Store;
import com.ebiz.mmt.domain.MobileSelectItem;
import com.ebiz.mmt.domain.MobileSelectItemNew;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.MmtFilePathConfig;
import com.ebiz.mmt.web.struts.MobileBaseAction;
import com.ebiz.ssi.util.EncryptUtils;
import com.ebiz.ssi.web.struts.bean.Pager;
import com.ebiz.ssi.web.struts.bean.UploadFile;
import com.ibm.db2.jcc.b.ne;

public class KonkaMobileFightReportAction extends MobileBaseAction {

    @Override
    public ActionForward unspecified(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return this.list(mapping, form, request, response);
    }

    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response)
            throws Exception {
//        if (null == super.checkUserModPopeDom(form, request, "0")) {
//            return super.checkPopedomInvalid(request, response);
//        }

        setNaviStringToRequestScope(form, request);
        super.getModPopeDom(form, request);

        DynaBean dynaBean = (DynaBean) form;
        super.encodeCharacterForGetMethod(dynaBean, request);

        Pager pager = (Pager) dynaBean.get("pager");
        // every request get 20 records
        // pager.setPageSize(20);

        KonkaMobileFightReport entity = new KonkaMobileFightReport();
        // copyProperties(entity, form);

        // 分公司
        String subcomp_id = (String) dynaBean.get("subcomp_id");
        // 办事处
        // String office_name = (String) dynaBean.get("office_name");
        // 上报门店
        String dept_name_like = (String) dynaBean.get("dept_name_like");

        String report_name_like = (String) dynaBean.get("report_name_like");
        String model_id_like = (String) dynaBean.get("model_id_like");
        String date_begin = (String) dynaBean.get("date_begin");
        String date_end = (String) dynaBean.get("date_end");
        String brand_id = (String) dynaBean.get("brand_id");
        String excel_all = (String) dynaBean.get("excel_all");
        String par_type_id = (String) dynaBean.get("par_type_id");
        String e_size = (String) dynaBean.get("e_size");
        String c_comm = (String) dynaBean.get("c_comm");
        String customer_cate_id = (String) dynaBean.get("customer_cate_id");
        entity.getMap().put("report_name_like", report_name_like);
        String store_name = (String) dynaBean.get("store_name");
        
        if (StringUtils.isNotBlank(store_name)) {
            entity.getMap().put("store_name", store_name);
        }
        if (null != model_id_like && model_id_like.equals("全部")) {
            model_id_like = "1001";
        }
        entity.getMap().put("model_id_like", model_id_like);

        String l3_dept_id = (String) dynaBean.get("l3_dept_id");
        String l4_dept_id = (String) dynaBean.get("l4_dept_id");
        String l5_dept_id = (String) dynaBean.get("l5_dept_id");

        String r3_code = (String) dynaBean.get("r3_code");
        String dept_name = (String) dynaBean.get("dept_name");
        if (StringUtils.isNotBlank(r3_code)) {
            entity.getMap().put("r3_code", r3_code);
        }
        if (StringUtils.isNotBlank(dept_name)) {
            entity.setDept_name(dept_name);
        }

        if (StringUtils.isNotBlank(l5_dept_id)) {
            entity.getMap().put("dept_id_start", l5_dept_id);
        } else if (StringUtils.isNotBlank(l4_dept_id)) {
            entity.getMap().put("dept_id_start", l4_dept_id);
        } else if (StringUtils.isNotBlank(l3_dept_id)) {
            entity.getMap().put("dept_id_start", l3_dept_id);
        }
        if (StringUtils.isNotBlank(par_type_id)) {
            entity.setPar_type_id(Long.valueOf(par_type_id));
        }
        if (StringUtils.isNotBlank(e_size)) {
            entity.getMap().put("e_size", e_size);
        }

        if (StringUtils.isNotBlank(c_comm)) {
            entity.getMap().put("c_comm", c_comm);
        }
        if (StringUtils.isNotBlank(customer_cate_id)) {
            entity.getMap().put("customer_cate_id", customer_cate_id);
        }

        if (subcomp_id != null && subcomp_id.length() > 0) {
            entity.setSubcomp_id(Long.valueOf(subcomp_id));
        }
        // entity.setOffice_name(office_name);


        // 设置默认时间

        if (StringUtils.isBlank(date_begin) && StringUtils.isBlank(date_end)) {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
            String date = df.format(new Date());
            date_begin = date.substring(0, date.indexOf("-")) + "-01-01";
            date_end = date;
            dynaBean.set("date_begin", date_begin);
            dynaBean.set("date_end", date_end);
        }
        if (StringUtils.isNotBlank(date_begin)) {
            entity.getMap().put("date_begin", date_begin);
        }
        if (StringUtils.isNotBlank(date_end)) {
            entity.getMap().put("date_end", date_end);
        }
        if (StringUtils.isNotBlank(brand_id)) {
            entity.setBrand_id(Long.valueOf(brand_id));
        }
        if (StringUtils.isNotBlank(dept_name_like)) {
            entity.getMap().put("dept_name_like", dept_name_like);
        }
        PeProdUser ui = (PeProdUser) request.getSession().getAttribute(
                Constants.USER_INFO);
        PeRoleUser _peRoleUser = new PeRoleUser();
        _peRoleUser.setUser_id(ui.getId());
        List<PeRoleUser> peRoleUserList = this.getFacade()
                .getPeRoleUserService().getPeRoleUserList(_peRoleUser);

        boolean role_id_gt_30 = false;
        boolean role_id_gt_10 = false;
        for (PeRoleUser peRoleUser : peRoleUserList) {
            if (peRoleUser.getRole_id() < 30) {
                role_id_gt_30 = true;
            }
            if (peRoleUser.getRole_id() == 10) {
                role_id_gt_10 = true;
            }
        }
        Long dept_id = null;
        if (!role_id_gt_30) {
            dept_id = getKonkaDeptForFgs(ui.getDept_id()).getDept_id();
            entity.getMap().put("fgs_dept_id", dept_id);
        }
        request.setAttribute("role_id_gt_10", role_id_gt_10);
        // if (!role_id_gt_30) {
        // entity.setSubcomp_id(dept_id);
        // }
        Long recordCount = getFacade().getKonkaMobileFightReportService()
                .getKonkaMobileFightReportCount(entity);

        // 客户类型
        KonkaCategory konkaCategory = new KonkaCategory();
        konkaCategory.setC_type(10);
        konkaCategory.getMap().put("group_by_par_index", true);
        List<KonkaCategory> konkaCategoryList = super.getFacade()
                .getKonkaCategoryService()
                .getKonkaCategoryGroupByCCommList(konkaCategory);
        request.setAttribute("konkaCategoryList", konkaCategoryList);

        // 规格段下拉框
        KonkaBaseTypeData entity1 = new KonkaBaseTypeData();
        entity1.setIs_del(0);
        entity1.setIs_lock(0);
        entity1.setPar_type_id(Long.valueOf(100025));
        List<KonkaBaseTypeData> eList = super.getFacade()
                .getKonkaBaseTypeDataService()
                .getKonkaBaseTypeDataList(entity1);
        request.setAttribute("eList", eList);


        // 分公司
        KonkaDept konkaDept = new KonkaDept();
        konkaDept.setDept_type(3);
        if (!role_id_gt_30) {
            konkaDept.setDept_id(dept_id);
        }
        List<KonkaDept> baseDeptList = super.getFacade().getKonkaDeptService()
                .getKonkaDeptList(konkaDept);
        request.setAttribute("baseDeptList", baseDeptList);

        if (subcomp_id != null && subcomp_id.length() > 0) {
            KonkaDept konkaDept2 = new KonkaDept();
            konkaDept2.setPar_id(Long.valueOf(subcomp_id));
            konkaDept2.getMap().put("is_jingban", "yes");
            List<KonkaDept> konkadeptList = new ArrayList<KonkaDept>();
            konkadeptList = super.getFacade().getKonkaDeptService()
                    .getKonkaDeptList(konkaDept2);
            // 经办数据
            request.setAttribute("jbList", konkadeptList);
        }

        // 品类
        // List<BasePdClazz> basePdClazzList =
        // super.getFacade().getRetailMsBaseService()
        // .getKonkaBasePdClazzListByClsIds();
        // request.setAttribute("basePdClazzList", basePdClazzList);

        // 品牌 brandList
        // KonkaMobileCategory konkaMobileCategory = new KonkaMobileCategory();
        // konkaMobileCategory.setIs_del(0);
        // konkaMobileCategory.setC_type(14);
        // List<KonkaMobileCategory> brandList =
        // getFacade().getKonkaMobileCategoryService().getKonkaMobileCategoryList(
        // konkaMobileCategory);
        // request.setAttribute("brandList", brandList);

        KonkaMobilePdBrand pdBrand = new KonkaMobilePdBrand();
        pdBrand.setIs_del(0);
        List<KonkaMobilePdBrand> pdBrandList = getFacade()
                .getKonkaMobilePdBrandService().getKonkaMobilePdBrandList(
                        pdBrand);
        request.setAttribute("pdBrandList", pdBrandList);

        // 尺寸sizeList
        // List<MobileSelectItem> sizeList = new ArrayList<MobileSelectItem>();
        // for (String str : size_strs) {
        // MobileSelectItem t = new MobileSelectItem();
        // t.setId(str);
        // t.setName(str);
        // sizeList.add(t);
        // }
        // request.setAttribute("sizeList", sizeList);


        if (StringUtils.isNotBlank(excel_all) && "1".equals(excel_all)) {

            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename="
                    + EncryptUtils.encodingFileName("竞品信息数据") + ".xls");
            entity.getRow().setFirst(0);
            entity.getRow().setCount(recordCount.intValue());
            List<KonkaMobileFightReport> entityList1 = getFacade()
                    .getKonkaMobileFightReportService()
                    .getKonkaMobileFightReportPaginatedListForQuery(entity);

            for (KonkaMobileFightReport kmf : entityList1) {
                Attachment a = new Attachment();
                a.setLink_id(kmf.getId());
                a.setLink_tab("KONKA_MOBILE_FIGHT_REPORT");
                a.setDel_mark(Short.valueOf("0"));
                List<Attachment> attachlist = super.getFacade()
                        .getAttachmentService().getAttachmentList(a);
                kmf.getMap().put("attachlist", attachlist);
                String me_mo = kmf.getMemo();
                if (StringUtils.isNotBlank(me_mo) && me_mo.length() > 10) {
                    me_mo.substring(0, 10);
                    me_mo = me_mo + "...";
                    kmf.setMemo(me_mo);
                }

            }
            request.setAttribute("allList", entityList1);
            return mapping.findForward("view");
        }


        pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
        entity.getRow().setFirst(pager.getFirstRow());
        entity.getRow().setCount(pager.getRowCount());

        List<KonkaMobileFightReport> entityList = getFacade()
                .getKonkaMobileFightReportService()
                .getKonkaMobileFightReportPaginatedListForQuery(entity);
        for (KonkaMobileFightReport kmf : entityList) {
            Attachment a = new Attachment();
            a.setLink_id(kmf.getId());
            a.setLink_tab("KONKA_MOBILE_FIGHT_REPORT");
            a.setDel_mark(Short.valueOf("0"));
            List<Attachment> attachlist = super.getFacade()
                    .getAttachmentService().getAttachmentList(a);
            kmf.getMap().put("attachlist", attachlist);
            String me_mo = kmf.getMemo();
            if (StringUtils.isNotBlank(me_mo) && me_mo.length() > 10) {
                me_mo.substring(0, 10);
                me_mo = me_mo + "...";
            }
            kmf.setMemo(me_mo);
        }
        request.setAttribute("entityList", entityList);
        return mapping.findForward("list");
    }

    /**
     * 根据分公司获取分公司下面的所有经办 经营部和办事处
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getJBDataByFgs(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DynaBean dynaBean = (DynaBean) form;

        Long subcomp_id = 0l; // 分公司ID
        if (dynaBean.get("subcomp_id") != null
                && dynaBean.get("subcomp_id") != "") {
            subcomp_id = Long.valueOf(dynaBean.get("subcomp_id").toString());
        }
        KonkaDept konkaDept = new KonkaDept();
        konkaDept.setPar_id(subcomp_id);
        konkaDept.getMap().put("is_jingban", "yes");

        List<KonkaDept> konkadeptList = new ArrayList<KonkaDept>();
        konkadeptList = super.getFacade().getKonkaDeptService()
                .getKonkaDeptList(konkaDept);

        // 经办数据
        request.setAttribute("jbList", konkadeptList);
        super.renderJson(response, JSON.toJSONString(konkadeptList));
        return null;
    }

    public ActionForward edit(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DynaBean dynaBean = (DynaBean) form;
        String select_id = (String) dynaBean.get("select_id");
        String id = (String) dynaBean.get("id");
        String mod_id=(String) dynaBean.get("mod_id");
        PeProdUser peProdUser = (PeProdUser) request.getSession().getAttribute(
                Constants.USER_INFO);

        KonkaMobileFightReport kmf = new KonkaMobileFightReport();
        kmf.setId(Long.valueOf(id));
        List<KonkaMobileFightReport> kmfList = super.getFacade()
                .getKonkaMobileFightReportService()
                .getKonkaMobileFightReportPaginatedListForQueryNew(kmf);
        if (null != kmfList && kmfList.size() > 0) {
            kmf = kmfList.get(0);
        }
        Attachment a = new Attachment();
        a.setLink_id(kmf.getId());
        a.setLink_tab("KONKA_MOBILE_FIGHT_REPORT");
        a.setDel_mark(new Short("0"));
        List<Attachment> attachlist = super.getFacade().getAttachmentService()
                .getAttachmentList(a);
        kmf.getMap().put("attachlist", attachlist);
        kmf.getMap().put("select_id", select_id);
        request.setAttribute("kmf", kmf);
        if (StringUtils.isNotBlank(select_id) && select_id.equals("2")) {
            // 查看 加select_id传回

            return new ActionForward("/admin/KonkaMobileFightReport/form.jsp");
        }
        // 修改
        return new ActionForward("/admin/KonkaMobileFightReport/form.jsp");

    }

    public ActionForward deleteFile(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        DynaBean dynaBean = (DynaBean) form;

        String id = (String) dynaBean.get("id");

        if (StringUtils.isNotBlank(id)) {
            Attachment filesAttachment = new Attachment();
            filesAttachment.setLink_id(Long.valueOf(id));
            filesAttachment.setLink_tab("KONKA_MOBILE_FIGHT_REPORT");
            super.getFacade().getAttachmentService()
                    .removeAttachment(filesAttachment);
        }

        super.renderText(response, "success");
        return null;
    }

    public ActionForward save(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DynaBean dynaBean = (DynaBean) form;
        String id = (String) dynaBean.get("id");
        String mod_id = (String) dynaBean.get("mod_id");
        // String report_time=(String) dynaBean.get("report_time");
        // if(StringUtils.isNotEmpty(report_time))
        // {
        // Date date=new Date();
        // DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // Date time=format.parse(report_time);
        // Long leva =date.getTime()-time.getTime();
        //
        // if(leva/(3600*24*1000)>2){
        // super.renderTextOrJsonp(request, response, "48小时后不能修改数量和金额！");
        // return null;
        // }
        // }

        PeProdUser peProdUser = (PeProdUser) request.getSession().getAttribute(
                Constants.USER_INFO);

        if (null == peProdUser) {
            super.renderTextOrJsonp(request, response, "用户信息出错，请联系管理员！");
            return null;
        }

        KonkaMobileFightReport entity = new KonkaMobileFightReport();
        // id
        if (StringUtils.isNotEmpty(id)) {
            entity.setId(Long.parseLong(id));
        }
        // 总价
        String price = (String) dynaBean.get("sales_price");
        if (StringUtils.isNotEmpty(price)) {
            if (NumberUtils.isNumber(price)) {
                entity.setPrice(BigDecimal.valueOf(Double.parseDouble(price)));
                if (entity.getPrice().compareTo(BigDecimal.valueOf(0)) == 0) {
                    super.renderTextOrJsonp(request, response,
                            "金额填写有误，金额不能为0或负数！");
                    return null;
                }
            } else {
                super.renderTextOrJsonp(request, response, "金额必须为数字！");
                return null;
            }
        } else {
            super.renderTextOrJsonp(request, response, "请填写价格！");
            return null;
        }

        // 单价
        String single_price = (String) dynaBean.get("single_price");
        if (StringUtils.isNotBlank(single_price)) {
            entity.setSingle_price(new BigDecimal(single_price));
        }

        // 销量
        String count = (String) dynaBean.get("sales_count");
        if (StringUtils.isNotEmpty(count)) {
            if (NumberUtils.isNumber(count)) {

                entity.setNum(Long.parseLong(count));
                // if (entity.getNum() <= 0) {
                // super.renderTextOrJsonp(request, response,
                // "数量填写有误，数量不能为0或负数！");
                // return null;
                // }
            } else {
                super.renderTextOrJsonp(request, response, "数量必须为数字！");
                return null;
            }
        } else {
            super.renderTextOrJsonp(request, response, "请填写销量！");
            return null;
        }

        if (null != peProdUser) {
            entity.setReport_man(peProdUser.getId());
            entity.setReport_time(new java.util.Date());
            entity.setReport_name(peProdUser.getUser_name());// 上报人名

            // 分公司
            entity.setSubcomp_id(peProdUser.getDept_id());
            entity.setSubcomp_name(peProdUser.getDepartment());
        }

        // 备注
        String memo = (String) dynaBean.get("memo");
        entity.setMemo(memo);
        // 附件处理
        List<UploadFile> uploadFileList = super.uploadFile(form,
                MmtFilePathConfig.LST_PATH, true, 0, new int[]{960});
        Attachment filesAttachment = null;
        if (null != uploadFileList && uploadFileList.size() > 0) {
            for (UploadFile uploadFile : uploadFileList) {
                filesAttachment = new Attachment();
                filesAttachment.setFile_name(uploadFile.getFileName());
                filesAttachment.setFile_type(uploadFile.getContentType());
                filesAttachment.setFile_size(new Integer(uploadFile
                        .getFileSize()));
                filesAttachment.setSave_path(uploadFile.getFileSavePath());
                filesAttachment.setSave_name(uploadFile.getFileSaveName());
                filesAttachment.setDel_mark(new Short("0"));
                filesAttachment.setLink_tab("KONKA_MOBILE_FIGHT_REPORT");
                filesAttachment.setLink_id(Long.valueOf(id));
                super.getFacade().getAttachmentService()
                        .createAttachment(filesAttachment);
            }
        }
        entity.setReport_man(peProdUser.getId());
        entity.setReport_time(new java.util.Date());
        entity.setReport_name(peProdUser.getUser_name());
        super.getFacade().getKonkaMobileFightReportService()
                .modifyKonkaMobileFightReport(entity);

        StringBuffer pathBuffer = new StringBuffer();
        pathBuffer.append(mapping.findForward("success").getPath());
        pathBuffer.append("&mod_id=" + mod_id);
        pathBuffer.append("&");
        // pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
        ActionForward forward = new ActionForward(pathBuffer.toString(), true);
        // end
        return forward;

    }

    public ActionForward fightSum(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        if (null == super.checkUserModPopeDom(form, request, "0")) {
            return super.checkPopedomInvalid(request, response);
        }

        setNaviStringToRequestScope(form, request);
        super.getModPopeDom(form, request);

        DynaBean dynaBean = (DynaBean) form;
        super.encodeCharacterForGetMethod(dynaBean, request);

        Pager pager = (Pager) dynaBean.get("pager");
        // every request get 20 records
        // pager.setPageSize(20);

        KonkaMobileFightReport entity = new KonkaMobileFightReport();
        // copyProperties(entity, form);

        // 分公司
        String subcomp_id = (String) dynaBean.get("subcomp_id");
        // 办事处
        // String office_name = (String) dynaBean.get("office_name");
        // 上报门店
        String dept_name_like = (String) dynaBean.get("dept_name_like");

        String report_name_like = (String) dynaBean.get("report_name_like");
        String date_begin = (String) dynaBean.get("date_begin");
        String date_end = (String) dynaBean.get("date_end");
        String brand_id = (String) dynaBean.get("brand_id");
        String excel_all = (String) dynaBean.get("excel_all");
        String e_size = (String) dynaBean.get("e_size");
        String r3_code = (String) dynaBean.get("r3_code");

        String store_name = (String) dynaBean.get("store_name");

        entity.getMap().put("report_name_like", report_name_like);

        String l3_dept_id = (String) dynaBean.get("l3_dept_id");
        String l4_dept_id = (String) dynaBean.get("l4_dept_id");
        String l5_dept_id = (String) dynaBean.get("l5_dept_id");

        String pbList = (String) dynaBean.get("pbList");
        String dept_name = (String) dynaBean.get("dept_name");

        String c_comm = (String) dynaBean.get("c_comm");
        String customer_cate_id = (String) dynaBean.get("customer_cate_id");

        if (StringUtils.isNotBlank(c_comm)) {
            entity.getMap().put("c_comm", c_comm);
        }
        if (StringUtils.isNotBlank(customer_cate_id)) {
            entity.getMap().put("customer_cate_id", customer_cate_id);
        }

        if (subcomp_id != null && subcomp_id.length() > 0) {
            entity.setSubcomp_id(Long.valueOf(subcomp_id));
        }
        if (StringUtils.isNotBlank(dept_name)) {
            entity.setDept_name(dept_name);
        }
        if (StringUtils.isNotBlank(r3_code)) {
            entity.getMap().put("r3_code", r3_code);
        }
        if (StringUtils.isNotBlank(store_name)) {
            entity.getMap().put("store_name", store_name);
        }
        if (StringUtils.isNotBlank(l5_dept_id)) {
            entity.getMap().put("dept_id_start", l5_dept_id);
        } else if (StringUtils.isNotBlank(l4_dept_id)) {
            entity.getMap().put("dept_id_start", l4_dept_id);
        } else if (StringUtils.isNotBlank(l3_dept_id)) {
            entity.getMap().put("dept_id_start", l3_dept_id);
        }
        if (StringUtils.isNotBlank(e_size)) {
            entity.getMap().put("e_size", e_size);
        }

        if (subcomp_id != null && subcomp_id.length() > 0) {
            entity.setSubcomp_id(Long.valueOf(subcomp_id));
        }

        // 设置默认时间
        if (StringUtils.isBlank(date_begin) && StringUtils.isBlank(date_end)) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            // 获取当前月第一天：
            Calendar c = Calendar.getInstance();
            c.add(Calendar.MONTH, 0);
            c.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
            String first = format.format(c.getTime());

            // 获取当前天
            String last = format.format(new Date());
            date_begin = first;
            date_end = last;
            dynaBean.set("date_begin", date_begin);
            dynaBean.set("date_end", date_end);

        }
        if (StringUtils.isNotBlank(date_begin)) {
            entity.getMap().put("date_begin", date_begin);
        }
        if (StringUtils.isNotBlank(date_end)) {
            entity.getMap().put("date_end", date_end);
        }
        if (StringUtils.isNotBlank(brand_id)) {
            entity.setBrand_id(Long.valueOf(brand_id));
        }
        if (StringUtils.isNotBlank(dept_name_like)) {
            entity.getMap().put("dept_name_like", dept_name_like);
        }
        PeProdUser ui = (PeProdUser) request.getSession().getAttribute(
                Constants.USER_INFO);
        PeRoleUser _peRoleUser = new PeRoleUser();
        _peRoleUser.setUser_id(ui.getId());
        List<PeRoleUser> peRoleUserList = this.getFacade()
                .getPeRoleUserService().getPeRoleUserList(_peRoleUser);

        boolean role_id_gt_30 = false;
        for (PeRoleUser peRoleUser : peRoleUserList) {
            if (peRoleUser.getRole_id() < 30) {
                role_id_gt_30 = true;
            }
        }
        Long dept_id = null;
        if (!role_id_gt_30) {
            dept_id = getKonkaDeptForFgs(ui.getDept_id()).getDept_id();
            entity.getMap().put("fgs_dept_id", dept_id);
        }
        // 客户类型
        KonkaCategory konkaCategory = new KonkaCategory();
        konkaCategory.setC_type(10);
        konkaCategory.getMap().put("group_by_par_index", true);
        List<KonkaCategory> konkaCategoryList = super.getFacade()
                .getKonkaCategoryService()
                .getKonkaCategoryGroupByCCommList(konkaCategory);
        request.setAttribute("konkaCategoryList", konkaCategoryList);

        List<KonkaMobileFightReport> entityList = new ArrayList<KonkaMobileFightReport>();
        Long recordCount = getFacade().getKonkaMobileFightReportService()
                .sumKonkaMobileFightReportCount(entity);
        pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
        entity.getRow().setFirst(pager.getFirstRow());
        entity.getRow().setCount(pager.getRowCount());

        if (null != pbList && pbList.equals("1")) {
            entityList = getFacade().getKonkaMobileFightReportService()
                    .selectSumNum(entity);
        } else {
            pbList = "0";
            entityList = getFacade().getKonkaMobileFightReportService()
                    .selectSumPrice(entity);
        }
        request.setAttribute("pbList", pbList);

        request.setAttribute("entityList", entityList);

        // 规格段下拉框
        KonkaBaseTypeData entity1 = new KonkaBaseTypeData();
        entity1.setIs_del(0);
        entity1.setIs_lock(0);
        entity1.setPar_type_id(Long.valueOf(100025));
        List<KonkaBaseTypeData> eList = super.getFacade()
                .getKonkaBaseTypeDataService()
                .getKonkaBaseTypeDataList(entity1);
        request.setAttribute("eList", eList);

        // 分公司
        KonkaDept konkaDept = new KonkaDept();
        konkaDept.setDept_type(3);
        if (!role_id_gt_30) {
            konkaDept.setDept_id(dept_id);
        }
        List<KonkaDept> baseDeptList = super.getFacade().getKonkaDeptService()
                .getKonkaDeptList(konkaDept);
        request.setAttribute("baseDeptList", baseDeptList);

        if (subcomp_id != null && subcomp_id.length() > 0) {
            KonkaDept konkaDept2 = new KonkaDept();
            konkaDept2.setPar_id(Long.valueOf(subcomp_id));
            konkaDept2.getMap().put("is_jingban", "yes");
            List<KonkaDept> konkadeptList = new ArrayList<KonkaDept>();
            konkadeptList = super.getFacade().getKonkaDeptService()
                    .getKonkaDeptList(konkaDept2);
            // 经办数据
            request.setAttribute("jbList", konkadeptList);
        }
        KonkaMobilePdBrand pdBrand = new KonkaMobilePdBrand();
        pdBrand.setIs_del(0);
        List<KonkaMobilePdBrand> pdBrandList = getFacade()
                .getKonkaMobilePdBrandService().getKonkaMobilePdBrandList(
                        pdBrand);
        request.setAttribute("pdBrandList", pdBrandList);

        if (StringUtils.isNotBlank(excel_all) && "1".equals(excel_all)) {

            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename="
                    + EncryptUtils.encodingFileName("竞品统计信息数据") + ".xls");
            entity.getRow().setFirst(pager.getFirstRow());
            entity.getRow().setCount(recordCount.intValue());
            List<KonkaMobileFightReport> allList = new ArrayList<KonkaMobileFightReport>();
            if (null != pbList && pbList.equals("1")) {
                allList = getFacade().getKonkaMobileFightReportService()
                        .selectSumNum(entity);
            } else {
                pbList = "0";
                allList = getFacade().getKonkaMobileFightReportService()
                        .selectSumPrice(entity);
            }
            request.setAttribute("allList", allList);
            return new ActionForward("/admin/KonkaMobileFightReport/input.jsp");
        }
        return new ActionForward("/admin/KonkaMobileFightReport/pbList.jsp");
    }

    public ActionForward delete(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DynaBean dynaBean = (DynaBean) form;
        String id = (String) dynaBean.get("id");
        String mod_id = (String) dynaBean.get("mod_id");
        KonkaMobileFightReport entity = new KonkaMobileFightReport();
        if (StringUtils.isNotBlank(id)) {
            entity.setId(Long.valueOf(id));
            getFacade().getKonkaMobileFightReportService()
                    .removeKonkaMobileFightReport(entity);
        }
        entity.setQueryString(super.serialize(request, "visit_id", "method"));
        logger.info("++++++++++++++QueryString="
                + super.encodeSerializedQueryString(entity.getQueryString()));

        StringBuffer pathBuffer = new StringBuffer();
        pathBuffer.append(mapping.findForward("success").getPath());
        pathBuffer.append("&mod_id=" + mod_id);
        pathBuffer.append("&");
        pathBuffer.append(super.encodeSerializedQueryString(entity
                .getQueryString()));
        ActionForward forward = new ActionForward(pathBuffer.toString(), true);
        // end
        return forward;
    }
}