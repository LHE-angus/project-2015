package com.ebiz.mmt.web.struts.manager.admin;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.Attachment;
import com.ebiz.mmt.domain.JBaseStore;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaMobileSpRelation;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.KonkaR3Store;
import com.ebiz.mmt.domain.KonkaR3StoreShow;
import com.ebiz.mmt.domain.KonkaStoreUserCInfo;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.domain.SysModule;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.struts.MmtFilePathConfig;
import com.ebiz.mmt.web.util.StringHelper;
import com.ebiz.ssi.util.EncryptUtils;
import com.ebiz.ssi.web.struts.bean.Pager;
import com.ebiz.ssi.web.struts.bean.UploadFile;

/**
 * @author Xing,XiuDong
 * @version 2013-05-17
 */
public class KonkaR3StoreAction extends BaseAction {
    @Override
    public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return this.list(mapping, form, request, response);
    }

    public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 判断session是否超时
        PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
        if (null == ui) {
            String msg = super.getMessage(request, "user.session.isEmpty");
            super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
            return null;
        }

        DynaBean dynaBean = (DynaBean) form;
        if (null == super.checkUserModPopeDom(form, request, "0")) {
            return super.checkPopedomInvalid(request, response);
        }

        setNaviStringToRequestScope(form, request);
        super.getModPopeDom(form, request);

        super.encodeCharacterForGetMethod(dynaBean, request);

        PeRoleUser roleUser = new PeRoleUser();
        roleUser.setUser_id(ui.getId());
        List<PeRoleUser> roleUserList = super.getFacade().getPeRoleUserService().getPeRoleUserList(roleUser);

        boolean role_id_eq_10 = false; // 系统管理员
        // boolean role_id_eq_30 = false; // 分公司管理员

        // boolean role_id_eq_188 = false; // 促销员

        boolean role_id_eq_188 = false; // 促销员

        request.setAttribute("user_role", "1");
        for (PeRoleUser peRoleUser : roleUserList) {
            if (peRoleUser.getRole_id() < 30L || (peRoleUser.getRole_id() >= 200L && peRoleUser.getRole_id() < 300L)) {
                role_id_eq_10 = true;
            }
            if (peRoleUser.getRole_id() == 188L) {
                role_id_eq_188 = true;
            }
            if (peRoleUser.getRole_id() == 30) {// 分公司管理员
                request.setAttribute("user_role", "0");
            }
        }


        Pager pager = (Pager) dynaBean.get("pager");
        String store_name_like = (String) dynaBean.get("store_name_like");

        // 分公司
        String dept_id = (String) dynaBean.get("dept_id");
        // 经办ID
        String jb_job_id = (String) dynaBean.get("jb_job_id");

        String r3_code_like = (String) dynaBean.get("r3_code_like");
        String is_del = (String) dynaBean.get("is_del");
        String store_id = (String) dynaBean.get("store_id");
        String excel_to_all = (String) dynaBean.get("excel_to_all");
        String zxy_name_like = (String) dynaBean.get("zxy_name_like");
        String ywy_name_like = (String) dynaBean.get("ywy_name_like");
        String kh_name_like = (String) dynaBean.get("kh_name_like");

        // 一级客户类型
        String customer_type1 = (String) dynaBean.get("v_customer_type1");

        // 二级客户类型
        String customer_type2 = (String) dynaBean.get("v_customer_type2");

        // 经办名称
        String jing_ban_like = (String) dynaBean.get("jing_ban_like");

        // R3客户合并转入
        String merge_r3_code = (String) dynaBean.get("merge_r3_code");

        String is_sure = (String) dynaBean.get("is_sure");

        KonkaR3Store entity = new KonkaR3Store();
        super.copyProperties(entity, form);
        dynaBean.set("r3_code", entity.getR3_code());

        if (StringUtils.isNotBlank(merge_r3_code)) {
            entity.setR3_code(merge_r3_code);
            dynaBean.set("r3_code_like", merge_r3_code);
        }

        if (StringUtils.isNotBlank(kh_name_like)) {
            entity.getMap().put("kh_name_like", kh_name_like);
        }

        if (StringUtils.isNotBlank(is_sure)) {
            if ("1".equals(is_sure)) {
                entity.getMap().put("is_sure1", "1");
            }
            if ("2".equals(is_sure)) {
                entity.getMap().put("is_sure2", "1");
            }
        }


        if (StringUtils.isNotBlank(store_id) && GenericValidator.isLong(store_id)) {
            entity.setStore_id(Long.valueOf(store_id));
        }

        if (StringUtils.isNotBlank(is_del)) {
            entity.setIs_del(Integer.valueOf(is_del));
        } else {
            if (null == is_del) {
                entity.setIs_del(0);
                dynaBean.set("is_del", 0);
            }
        }
        if (StringUtils.isNotBlank(store_name_like)) {
            store_name_like = store_name_like.replaceAll("&#40;", "（").replaceAll("&#41;", "）");
            entity.getMap().put("store_name_like", store_name_like);
        }
        // 促销员，业务员模糊查询条件
        if (StringUtils.isNotBlank(zxy_name_like)) {
            entity.getMap().put("zxy_name_like", zxy_name_like);
        }
        if (StringUtils.isNotBlank(ywy_name_like)) {
            entity.getMap().put("ywy_user_name_like", ywy_name_like);
        }
        entity.getMap().put("r3_code_like", r3_code_like);

        if (StringUtils.isNotBlank(jing_ban_like)) {
            entity.setJb_name(jing_ban_like);
        }

        // 添加客户类型筛选条件
        if (customer_type1 != null && !"".equals(customer_type1)) {
            entity.getMap().put("customer_type1", customer_type1);
        }
        if (customer_type2 != null && !"".equals(customer_type2)) {
            entity.getMap().put("customer_type2", customer_type2);
        }

        // 数据级别判断开始
        Long __dept_id = StringUtils.isNotBlank(dept_id) ? Long.valueOf(dept_id) : ui.getDept_id(); // 默认为当前用户所在部门
        int max_dlevel = super.getMaxDLevel(ui.getId()); // 获取当前用户的最高可视部门级别
        logger.info("Max level : {}", max_dlevel);
        request.setAttribute("max_dlevel", max_dlevel);
        switch (max_dlevel) {
            case 9:
                // 集团及以下部门可见
                __dept_id = 0L; // 0表示部门根节点，即“多媒体事业本部”
                break;
            case 8:
                // 分公司及以下部门可见
                KonkaDept dept_fgs = super.getKonkaDeptForFgs(ui.getDept_id()); // 查询部门分公司
                if (null != dept_fgs && null != dept_fgs.getDept_id()) {
                    __dept_id = dept_fgs.getDept_id(); // 分公司部门ID
                    entity.getMap().put("dept_id_start", __dept_id);
                    request.setAttribute("current_fgs_code", __dept_id);
                    request.setAttribute("show_fgs", true);
                }
                break;
            case 7:
                // 我所在的部门及以下部门可见
                __dept_id = ui.getDept_id(); // 默认为当前用户所在部门
                entity.getMap().put("dept_id_start", __dept_id);
                request.setAttribute("current_fgs_code", __dept_id);
                request.setAttribute("show_fgs", true);
                break;
            case 0:
                __dept_id = ui.getDept_id(); // 默认为当前用户所在部门
                // entity.getMap().put("dept_id_start", __dept_id);
                entity.getMap().put("filter_by_ywy_job_id_eq", ui.getJob_id());
                request.setAttribute("current_fgs_code", __dept_id);
                request.setAttribute("show_fgs", true);
                entity.getMap().put("only_cxy", ui.getId());// 不管什么角色，都穿树下only_cxy，以便查询所包含的门店信息
                break;
            default:
                entity.getMap().put("only_cxy", ui.getId());// 不管什么角色，都穿树下only_cxy，以便查询所包含的门店信息
                entity.getMap().put("filter_by_job_id_eq", ui.getJob_id());

                // 出错
        }
        // 数据级别判断结束
        entity.getMap().put("session_user_id", ui.getId());

        if (role_id_eq_10) {
            // 查询分公司基础数据
            KonkaDept kd = new KonkaDept();
            kd.setDept_id(ui.getDept_id());
            kd = super.getFacade().getKonkaDeptService().getKonkaDept(kd);
            entity.getMap().put("dept_name", kd.getDept_name());
            entity.getMap().put("order_by_dept_name", true);
            request.setAttribute("is_admin", "1");
        } else {
            dynaBean.set("dept_name", getKonkaDeptById(ui.getDept_id()).getDept_name());
        }

        // 设置标识，区别电脑端与手机端的请求
        entity.getMap().put("is_pc", "true");

        //
        if (StringUtils.isNotEmpty(jb_job_id)) {
            entity.setJb_job_id(jb_job_id);
        }

        Long recordCount = 0L;

        recordCount = getFacade().getKonkaR3StoreService().getKonkaR3StoreAndYwyNameCount(entity);

        pager.init(recordCount, 10, pager.getRequestPage());
        entity.getRow().setFirst(pager.getFirstRow());
        entity.getRow().setCount(pager.getRowCount());
        // entity.getMap().put("only_cxy", ui.getId()); //不管什么角色，都穿树下only_cxy，以便查询所包含的门店信息
        // entity.getMap().put("filter_by_job_id_eq", ui.getJob_id());
        List<HashMap> entityList = getFacade().getKonkaR3StoreService().getKonkaR3StoreAndYwyNamePaginatedList(entity);
        request.setAttribute("entityList", entityList);

        if (StringUtils.isNotBlank(r3_code_like)) {
            KonkaR3Shop shop = new KonkaR3Shop();
            shop.setR3_code(r3_code_like);
            shop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(shop);
            request.setAttribute("konkaR3Shop", shop);
        }

        // 导出excel方法
        if (StringUtils.isNotBlank(excel_to_all)) {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" + EncryptUtils.encodingFileName("门店列表") + ".xls");

            entity.getRow().setCount(recordCount.intValue());
            List<HashMap> entityList1 = getFacade().getKonkaR3StoreService().getKonkaR3StoreAndYwyNamePaginatedList(entity);
            request.setAttribute("allList", entityList1);
            return new ActionForward("/admin/KonkaR3Store/listForReport.jsp");
        }

        return mapping.findForward("list");
    }

    public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (null == super.checkUserModPopeDom(form, request, "1")) {
            return super.checkPopedomInvalid(request, response);
        }

        DynaBean dynaBean = (DynaBean) form;
        String r3_code = (String) dynaBean.get("r3_code");

        PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
        PeRoleUser roleUser = new PeRoleUser();
        roleUser.setUser_id(ui.getId());
        List<PeRoleUser> roleUserList = super.getFacade().getPeRoleUserService().getPeRoleUserList(roleUser);

        Boolean role_id_gt_30_btw_200_300 = false;
        if (roleUserList.size() > 0) {
            for (PeRoleUser temp : roleUserList) {
                if (temp.getRole_id() < 30 || (temp.getRole_id() > 200 && temp.getRole_id() < 300)) {
                    role_id_gt_30_btw_200_300 = true;
                }
            }
        }

        if (role_id_gt_30_btw_200_300) {
            // 部门列表
            KonkaDept konka_dept = new KonkaDept();
            konka_dept.setPar_id(0L);
            konka_dept.getMap().put("order_by_dept_name", true);
            List<KonkaDept> deptInfoList = super.getFacade().getKonkaDeptService().getKonkaDeptList(konka_dept);
            request.setAttribute("peDeptList", deptInfoList);
        } else {
            KonkaDept peDept = new KonkaDept();
            peDept.setDept_type(3);
            peDept.setDept_id(ui.getDept_id());
            peDept.getMap().put("order_by_dept_name", true);
            List<KonkaDept> deptInfoList = super.getFacade().getKonkaDeptService().getKonkaDeptByDeptId(peDept);
            request.setAttribute("peDeptList", deptInfoList);
        }

        if (StringUtils.isNotBlank(r3_code)) {
            KonkaR3Shop shop = new KonkaR3Shop();
            shop.setR3_code(r3_code);
            shop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(shop);
            request.setAttribute("konkaR3Shop", shop);
        }

        saveToken(request);
        setNaviStringToRequestScope(form, request);

        return mapping.findForward("input");
    }

    public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (null == super.checkUserModPopeDom(form, request, "2")) {
            return super.checkPopedomInvalid(request, response);
        }

        setNaviStringToRequestScope(form, request);
        DynaBean dynaBean = (DynaBean) form;
        String store_id = (String) dynaBean.get("store_id");

        if (!GenericValidator.isLong(store_id)) {
            this.saveError(request, "errors.long", new String[] {store_id});
            return mapping.findForward("list");
        }

        KonkaR3Store entity = new KonkaR3Store();
        entity.setStore_id(Long.valueOf(store_id));
        entity = super.getFacade().getKonkaR3StoreService().getKonkaR3Store(entity);

        if (null == entity) {
            saveError(request, "errors.long", new String[] {store_id});
            return mapping.findForward("list");
        }

        // the line below is added for pagination
        entity.setQueryString(super.serialize(request, "store_id", "method"));
        // end

        super.copyProperties(form, entity);

        // 查询创建、维护人
        if (entity.getCreate_user_id() != null) {
            PeProdUser user = new PeProdUser();
            user.setId(entity.getCreate_user_id());
            user = super.getFacade().getPeProdUserService().getPeProdUser(user);
            request.setAttribute("create_man", user.getReal_name());
        }

        if (entity.getModify_user_id() != null) {
            PeProdUser user = new PeProdUser();
            user.setId(entity.getModify_user_id());
            user = super.getFacade().getPeProdUserService().getPeProdUser(user);
            request.setAttribute("modify_man", user.getReal_name());
        }

        // 附件
        Attachment attachment = new Attachment();
        attachment.setLink_id(new Long(store_id));
        attachment.setLink_tab("KONKA_R3_STORE");
        attachment.setDel_mark(new Short("0"));
        request.setAttribute("attachmentList", getFacade().getAttachmentService().getAttachmentList(attachment));

        PeProdUser pp1 = new PeProdUser();
        pp1.getMap().put("role_id", "188"); // 定死为促销员
        pp1.getMap().put("is_position_dept", true);
        pp1.getMap().put("is_del", 0);

        PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
        PeRoleUser roleUser = new PeRoleUser();
        roleUser.setUser_id(ui.getId());
        List<PeRoleUser> roleUserList = super.getFacade().getPeRoleUserService().getPeRoleUserList(roleUser);

        PeRoleUser _peRoleUser = new PeRoleUser();
        _peRoleUser.setUser_id(ui.getId());
        List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

        boolean role_id_eq_10 = false;// 总部
        boolean role_id_ge_10_le_60 = false;// 管理人员
        boolean role_id_eq_60 = false;// 业务员
        boolean role_id_ge_200_le_600 = false;
        boolean role_id_ge_1000_le_1100 = false;
        for (PeRoleUser peRoleUser : peRoleUserList) {
            if (peRoleUser.getRole_id() == 10L) {
                role_id_eq_10 = true;
            }
            if (peRoleUser.getRole_id() > 10L && peRoleUser.getRole_id() < 60L) {
                role_id_ge_10_le_60 = true;
            }
            if (peRoleUser.getRole_id() == 60L) {
                role_id_eq_60 = true;
            }
            if (peRoleUser.getRole_id() >= 200L && peRoleUser.getRole_id() <= 600L) {
                role_id_ge_200_le_600 = true;
            }
            if (peRoleUser.getRole_id() >= 1000 && peRoleUser.getRole_id() <= 1100) {
                role_id_ge_1000_le_1100 = true;
            }
        }

        if (role_id_eq_10) {
            request.setAttribute("role_id_eq_10", true);
        }

        if (role_id_ge_10_le_60) { // 非系统管理员只能看到自己部门下的用户，管理员能看到所有用户
            pp1.getMap().put("dept_id_in", ui.getDept_id());
            pp1.getMap().put("user_id", ui.getId());
        }
        if (role_id_eq_60) {// 业务员
            pp1.getMap().put("cxy_id_in", ui.getId());
        }
        if (role_id_ge_200_le_600) {
            pp1.getMap().put("is_pe_prod_user", "");// 非康佳专版中人员管理中查询总数中将专卖店的去掉所加的条件
        } else if (role_id_ge_1000_le_1100) {
            pp1.getMap().put("is_pec_user", "true");// 完美终端用户
        } else {
            pp1.getMap().put("is_pe_prod_user", "true");// 康佳专版中人员管理中查询总数中将非专卖店的去掉所加的条件
        }

        // 取当前门店所在分公司
        // pp1.getMap().put("dept_id", entity.getDept_id());
        pp1.getMap().put("dept_id_all_prod_user", entity.getDept_id());
        entity.getMap().put("order_by_name", "true");// 按照姓名排序

        // 回显门店关联的促销员
        KonkaMobileSpRelation konkaMobileSpRelation = new KonkaMobileSpRelation();
        konkaMobileSpRelation.getMap().put("shop_id_eq", store_id);
        konkaMobileSpRelation.getMap().put("is_del_eq", true);
        List<KonkaMobileSpRelation> konkaMobileSpRelationList = super.getFacade().getKonkaMobileSpRelationService().getKonkaMobileSpRelationForCxyList(konkaMobileSpRelation);
        List<String> ids = new ArrayList<String>();
        if (konkaMobileSpRelationList.size() > 0) {
            // String cxy_ids = ",";
            for (KonkaMobileSpRelation konkaMobileSpRelation2 : konkaMobileSpRelationList) {
                PeProdUser pp = new PeProdUser();
                pp.setIs_del(0);
                pp.setId(konkaMobileSpRelation2.getSeller_id());
                pp = super.getFacade().getPeProdUserService().getCXYPeProdUser(pp);
                if (null != pp && !"".equals(pp.getReal_name()) && null != pp.getReal_name()) {
                    String type = "";
                    if (null != pp.getMap().get("sales_type")) {
                        type = pp.getMap().get("sales_type").toString();
                    }
                    if ("1".equals(type)) {
                        type = "兼职";
                    }
                    if ("2".equals(type)) {
                        type = "全职";
                    }
                    konkaMobileSpRelation2.getMap().put("cxy_name", pp.getUser_name() + "(" + type + ")");
                    konkaMobileSpRelation2.getMap().put("cxy_id", pp.getId());
                    ids.add(pp.getId().toString());
                }
                // cxy_ids = cxy_ids + pp.getId() + ",";
            }
        }
        request.setAttribute("konkaMobileSpRelationList", konkaMobileSpRelationList);

        if (null != ids && ids.size() > 0) {
            pp1.getMap().put("yxy_id_not_in", ids);
        }
        pp1.getRow().setFirst(0);
        pp1.getRow().setCount(100000);
        pp1.getMap().put("order_by_name", true);

        // 全部促销员
        List<PeProdUser> entityList2 = super.getFacade().getPeProdUserService().getpeProdUserWithPositionNameAndFullDeptNamePaginatedList(pp1);
        if (entityList2.size() > 0) {
            for (PeProdUser ppu : entityList2) {
                String type = "";
                if (null != ppu.getMap().get("sales_type")) {
                    type = ppu.getMap().get("sales_type").toString();
                }
                if ("1".equals(type)) {
                    type = "兼职";
                }
                if ("2".equals(type)) {
                    type = "全职";
                }
                ppu.getMap().put("cxy_name", ppu.getUser_name() + "(" + type + ")");
                ppu.getMap().put("cxy_id", ppu.getId());
            }
        }
        request.setAttribute("entityList2", entityList2);

        // 回显该门店所在分公司所有的促销员，不包括已经拥有的

        Boolean role_id_gt_30_btw_200_300 = false;
        // Boolean role_id_eq_10 = false;
        if (roleUserList.size() > 0) {
            for (PeRoleUser temp : roleUserList) {
                if (temp.getRole_id() < 30 || (temp.getRole_id() > 200 && temp.getRole_id() < 300)) {
                    role_id_gt_30_btw_200_300 = true;
                }
                // if (temp.getRole_id() == 10L) {
                // role_id_eq_10 = true;
                // request.setAttribute("is_admin", "1");
                // }
            }

        }

        if (role_id_gt_30_btw_200_300) {
            // 部门列表
            KonkaDept konka_dept = new KonkaDept();
            konka_dept.setPar_id(0L);
            List<KonkaDept> deptInfoList = super.getFacade().getKonkaDeptService().getKonkaDeptList(konka_dept);
            request.setAttribute("is_admin", "1");
            request.setAttribute("peDeptList", deptInfoList);
        } else {
            KonkaDept peDept = new KonkaDept();
            peDept.setDept_type(3);
            peDept.setDept_id(ui.getDept_id());
            List<KonkaDept> deptInfoList = super.getFacade().getKonkaDeptService().getKonkaDeptByDeptId(peDept);
            request.setAttribute("peDeptList", deptInfoList);
        }

        // 回显地区信息
        if (entity.getP_index() != null && entity.getP_index() != -1) {
            String p_index = entity.getP_index().toString();
            if (StringUtils.isNotBlank(p_index)) {
                if (!p_index.endsWith("00")) {
                    if (p_index.length() == 6) {
                        request.setAttribute("_province", StringUtils.substring(p_index, 0, 2) + "0000");
                        request.setAttribute("_city", StringUtils.substring(p_index, 0, 4) + "00");
                        request.setAttribute("_country", p_index);
                    } else if (p_index.length() == 8) {
                        request.setAttribute("_province", StringUtils.substring(p_index, 0, 2) + "0000");
                        request.setAttribute("_city", StringUtils.substring(p_index, 0, 4) + "00");
                        request.setAttribute("_country", StringUtils.substring(p_index, 0, 6));
                        request.setAttribute("_town", p_index);
                    }
                } else if (p_index.endsWith("0000")) {
                    request.setAttribute("_province", p_index);
                } else if (p_index.endsWith("00")) {
                    request.setAttribute("_province", StringUtils.substring(p_index, 0, 2) + "0000");
                    request.setAttribute("_city", p_index);
                }
            }
        }

        // 回显门店展台展柜
        KonkaR3StoreShow ks = new KonkaR3StoreShow();
        ks.setStore_id(Long.valueOf(store_id));
        // ks.setTask_type(0);
        // List<KonkaR3StoreShow> ksList1 =
        // super.getFacade().getKonkaR3StoreShowService().getKonkaR3StoreShowList(ks);
        // request.setAttribute("ksList1", ksList1);
        //
        // // 回显门店展台展柜
        // KonkaR3StoreShow ks1 = new KonkaR3StoreShow();
        // ks1.setStore_id(Long.valueOf(store_id));
        // ks1.setTask_type(1);

        ks.getRow().setFirst(0);
        ks.getRow().setCount(50);
        List<KonkaR3StoreShow> ksList = super.getFacade().getKonkaR3StoreShowService().getKonkaR3StoreShowLBPaginatedList(ks);
        request.setAttribute("ksList", ksList);

        return mapping.findForward("input");
    }

    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        setNaviStringToRequestScope(form, request);

        if (null == super.checkUserModPopeDom(form, request, "1") && null == super.checkUserModPopeDom(form, request, "2")) {
            return super.checkPopedomInvalid(request, response);
        }

        DynaBean dynaBean = (DynaBean) form;
        String store_id = (String) dynaBean.get("store_id");
        String dept_id = (String) dynaBean.get("dept_id");
        String dept_name = (String) dynaBean.get("dept_name");
        String mod_id = (String) dynaBean.get("mod_id");
        String province = (String) dynaBean.get("_province");
        String city = (String) dynaBean.get("_city");
        String country = (String) dynaBean.get("_country");
        String town = (String) dynaBean.get("_town");
        // String kh_name = (String) dynaBean.get("kh_name");
        // String ck_id = (String) dynaBean.get("ck_id");
        // String ck_name = (String) dynaBean.get("ck_name");
        String[] select1 = request.getParameterValues("select1");
        logger.info("select1-------------{}" + select1);

        String[] compition = request.getParameterValues("competition");
        String compitions = "";
        if (null != compition && compition.length > 0) {
            for (String string : compition) {
                compitions = compitions + string + ",";
            }
        }

        KonkaR3Store entity = new KonkaR3Store();
        super.copyProperties(entity, form);

        PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

        if (null == dept_name || "".equals(dept_name)) {
            KonkaDept konkaDept = new KonkaDept();
            konkaDept.setDept_id(Long.valueOf(dept_id));
            konkaDept = super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
            entity.setDept_name(konkaDept.getDept_name());
        }

        if (null != entity.getStore_name() && !"".equals(entity.getStore_name())) {

            String store_name = entity.getStore_name();
            store_name = store_name.replaceAll("&#40;", "（").replaceAll("&#41;", "）");

            entity.setStore_name(store_name);
        }
        entity.setCompetitions(compitions);// 这是竞争品牌
        // 人员居住地设定
        if (GenericValidator.isLong(province) && !GenericValidator.isLong(city)) {
            entity.setP_index(Long.valueOf(province));
        }
        if (GenericValidator.isLong(province) && GenericValidator.isLong(city) && !GenericValidator.isLong(country)) {
            entity.setP_index(Long.valueOf(city));
        }
        if (GenericValidator.isLong(province) && GenericValidator.isLong(city) && GenericValidator.isLong(country) && !GenericValidator.isLong(town)) {
            entity.setP_index(Long.valueOf(country));
        }
        if (GenericValidator.isLong(province) && GenericValidator.isLong(city) && GenericValidator.isLong(country) && GenericValidator.isLong(town)) {
            entity.setP_index(Long.valueOf(town));
        }

        String ids = "";
        if (null != select1 && select1.length > 0) {
            for (String string : select1) {
                ids = ids + string + ",";
            }
        }

        String[] file_desc = request.getParameterValues("file_desc");
        // 附件处理
        List<UploadFile> uploadFileList = super.uploadFile(form, MmtFilePathConfig.MD_PATH, true, 0, new int[] {240});
        List<Attachment> filesAttachmentList = new ArrayList<Attachment>();
        Attachment filesAttachment = null;
        if (null != file_desc && file_desc.length > 0) {
            for (int i = 1; i < file_desc.length; i++) {
                if (null != uploadFileList && uploadFileList.size() > 0) {
                    for (UploadFile uploadFile : uploadFileList) {
                        if (("file_show_" + i).endsWith(uploadFile.getFormName())) {
                            filesAttachment = new Attachment();
                            filesAttachment.setFile_name(uploadFile.getFileName());
                            filesAttachment.setFile_type(uploadFile.getContentType());
                            filesAttachment.setFile_size(new Integer(uploadFile.getFileSize()));
                            filesAttachment.setSave_path(uploadFile.getFileSavePath());
                            filesAttachment.setSave_name(uploadFile.getFileSaveName());
                            filesAttachment.setDel_mark(new Short("0"));
                            filesAttachment.setFile_desc(file_desc[i]);
                            filesAttachmentList.add(filesAttachment);
                        }
                    }
                }
            }
            entity.setAttachmentList(filesAttachmentList);
        }

        // if(null!= uploadFileList &&uploadFileList.size()>0){
        // // int file_desc_index=1;
        // int file_desc_index=uploadFileList.size();
        // for (UploadFile uploadFile : uploadFileList) {
        // filesAttachment = new Attachment();
        // filesAttachment.setFile_name(uploadFile.getFileName());
        // filesAttachment.setFile_type(uploadFile.getContentType());
        // filesAttachment.setFile_size(new Integer(uploadFile.getFileSize()));
        // filesAttachment.setSave_path(uploadFile.getFileSavePath());
        // filesAttachment.setSave_name(uploadFile.getFileSaveName());
        // filesAttachment.setDel_mark(new Short("0"));
        // filesAttachment.setFile_desc(file_desc[file_desc_index]);//图片加一个附件标题
        // filesAttachmentList.add(filesAttachment);
        // file_desc_index--;
        // }
        // entity.setAttachmentList(filesAttachmentList);
        // }
        if (StringUtils.isBlank(store_id)) {// insert
            // 2014-11-26 添加创建人ID
            entity.setCreate_user_id(ui.getId());
            // 2014-11-27 添加创建时间
            entity.setAdd_date(new Date());
            entity.setIs_del(0);
            super.getFacade().getKonkaR3StoreService().createKonkaR3Store(entity);
            saveMessage(request, "entity.inserted");
        } else {// update
            if (null == super.checkUserModPopeDom(form, request, "2")) {
                return super.checkPopedomInvalid(request, response);
            }
            // entity.getMap().put("store_id", entity.getStore_id());

            KonkaMobileSpRelation konkaMobileSpRelation = new KonkaMobileSpRelation();
            konkaMobileSpRelation.setShop_id(Long.valueOf(store_id));
            List<KonkaMobileSpRelation> konkaMobileSpRelationList = super.getFacade().getKonkaMobileSpRelationService().getKonkaMobileSpRelationList(konkaMobileSpRelation);
            List<String> old_ids = new ArrayList<String>();
            if (konkaMobileSpRelationList.size() > 0) {
                // String cxy_ids = ",";
                for (KonkaMobileSpRelation konkaMobileSpRelation2 : konkaMobileSpRelationList) {
                    PeProdUser pp = new PeProdUser();
                    pp.setIs_del(0);
                    pp.setId(konkaMobileSpRelation2.getSeller_id());
                    pp = super.getFacade().getPeProdUserService().getPeProdUser(pp);
                    if (null != pp && !"".equals(pp.getReal_name()) && null != pp.getReal_name()) {
                        konkaMobileSpRelation2.getMap().put("cxy_name", pp.getUser_name());
                        konkaMobileSpRelation2.getMap().put("cxy_id", pp.getId());
                        old_ids.add(pp.getId().toString());
                    }
                    // cxy_ids = cxy_ids + pp.getId() + ",";
                }
            }

            entity.getMap().put("old_ids", old_ids);

            entity.setStore_id(Long.valueOf(store_id));
            /*
             * if (StringUtils.isNotBlank(kh_name)) { entity.setKh_name(kh_name); }
             */
            entity.getMap().put("ids", ids);
            // 2014-11-26 添加修改人ID
            entity.setModify_user_id(ui.getId());
            // 2014-11-27 添加维护时间
            entity.setModify_date(new Date());
            getFacade().getKonkaR3StoreService().modifyKonkaR3Store(entity);

            // 同步修改门店展台/演示设备
            List<KonkaR3StoreShow> storeshowlist = new ArrayList<KonkaR3StoreShow>();
            KonkaR3StoreShow storeshow = new KonkaR3StoreShow();
            storeshow.setStore_id(entity.getStore_id());
            storeshowlist = super.getFacade().getKonkaR3StoreShowService().getKonkaR3StoreShowList(storeshow);
            for (int i = 0; i < storeshowlist.size(); i++) {
                Long updateid = storeshowlist.get(i).getId();
                KonkaR3StoreShow updateEntity = new KonkaR3StoreShow();
                if (null != updateid) {
                    updateEntity.setId(updateid);
                    updateEntity.setStore_name(entity.getStore_name());
                    super.getFacade().getKonkaR3StoreShowService().modifyKonkaR3StoreShow(updateEntity);
                }
            }
            saveMessage(request, "entity.updated");
        }

        // the line below is added for pagination
        StringBuffer pathBuffer = new StringBuffer();
        pathBuffer.append(mapping.findForward("success").getPath());
        pathBuffer.append("&mod_id=" + mod_id);
        pathBuffer.append("&");
        logger.info(entity.getQueryString());
        pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
        ActionForward forward = new ActionForward(pathBuffer.toString(), true);
        // end
        return forward;
    }

    public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (null == super.checkUserModPopeDom(form, request, "0")) {
            return super.checkPopedomInvalid(request, response);
        }
        super.getModPopeDom(form, request);
        setNaviStringToRequestScope(form, request);

        DynaBean dynaBean = (DynaBean) form;
        String store_id = (String) dynaBean.get("store_id");

        if (!GenericValidator.isLong(store_id)) {
            saveError(request, "errors.long", new String[] {store_id});
            return mapping.findForward("list");
        }

        KonkaR3Store entity = new KonkaR3Store();
        entity.setStore_id(Long.valueOf(store_id));
        entity = super.getFacade().getKonkaR3StoreService().getKonkaR3Store(entity);

        if (null == entity) {
            saveError(request, "errors.long", new String[] {store_id});
            return mapping.findForward("list");
        }
        entity.setQueryString(super.serialize(request, "store_id", "mod_id"));
        // request.setAttribute("entity", entity);
        super.copyProperties(form, entity);

        // 查询创建、维护人
        if (entity.getCreate_user_id() != null) {
            PeProdUser user = new PeProdUser();
            user.setId(entity.getCreate_user_id());
            user = super.getFacade().getPeProdUserService().getPeProdUser(user);
            request.setAttribute("create_man", user.getReal_name());
        }

        if (entity.getModify_user_id() != null) {
            PeProdUser user = new PeProdUser();
            user.setId(entity.getModify_user_id());
            user = super.getFacade().getPeProdUserService().getPeProdUser(user);
            request.setAttribute("modify_man", user.getReal_name());
        }

        // 附件
        Attachment attachment = new Attachment();
        attachment.setLink_id(new Long(store_id));
        attachment.setLink_tab("KONKA_R3_STORE");
        attachment.setDel_mark(new Short("0"));
        request.setAttribute("attachmentList", getFacade().getAttachmentService().getAttachmentList(attachment));

        // 部门列表
        KonkaDept konka_dept = new KonkaDept();
        konka_dept.setPar_id(0L);
        List<KonkaDept> deptInfoList = super.getFacade().getKonkaDeptService().getKonkaDeptListWithTreeNameForProdUser(konka_dept);
        request.setAttribute("peDeptList", deptInfoList);

        // 回显地区信息
        if (entity.getP_index() != null && entity.getP_index() != -1) {
            String p_index = entity.getP_index().toString();
            if (StringUtils.isNotBlank(p_index)) {
                if (!p_index.endsWith("00")) {
                    if (p_index.length() == 6) {
                        request.setAttribute("_province", StringUtils.substring(p_index, 0, 2) + "0000");
                        request.setAttribute("_city", StringUtils.substring(p_index, 0, 4) + "00");
                        request.setAttribute("_country", p_index);
                    } else if (p_index.length() == 8) {
                        request.setAttribute("_province", StringUtils.substring(p_index, 0, 2) + "0000");
                        request.setAttribute("_city", StringUtils.substring(p_index, 0, 4) + "00");
                        request.setAttribute("_country", StringUtils.substring(p_index, 0, 6));
                        request.setAttribute("_town", p_index);
                    }
                } else if (p_index.endsWith("0000")) {
                    request.setAttribute("_province", p_index);
                } else if (p_index.endsWith("00")) {
                    request.setAttribute("_province", StringUtils.substring(p_index, 0, 2) + "0000");
                    request.setAttribute("_city", p_index);
                }
            }
        }

        // 回显已有促销员 Lianghouen 2014-06-18
        KonkaMobileSpRelation konkaMobileSpRelation = new KonkaMobileSpRelation();
        konkaMobileSpRelation.getMap().put("shop_id_eq", store_id);
        konkaMobileSpRelation.getMap().put("is_del_eq", true);
        List<KonkaMobileSpRelation> konkaMobileSpRelationList = super.getFacade().getKonkaMobileSpRelationService().getKonkaMobileSpRelationForCxyList(konkaMobileSpRelation);
        List<String> ids = new ArrayList<String>();
        StringBuffer temp = new StringBuffer("");
        if (konkaMobileSpRelationList.size() > 0) {
            for (KonkaMobileSpRelation konkaMobileSpRelation2 : konkaMobileSpRelationList) {
                PeProdUser pp = new PeProdUser();
                pp.setIs_del(0);
                pp.setId(konkaMobileSpRelation2.getSeller_id());
                pp = super.getFacade().getPeProdUserService().getCXYPeProdUser(pp);
                if (null != pp && !"".equals(pp.getReal_name()) && null != pp.getReal_name()) {
                    String type = "";
                    if (null != pp.getMap().get("sales_type")) {
                        type = pp.getMap().get("sales_type").toString();
                    }
                    if ("1".equals(type)) {
                        type = "兼职";
                    }
                    if ("2".equals(type)) {
                        type = "全职";
                    }
                    temp.append(pp.getUser_name() + "(" + type + ")" + "，");
                }
            }
        }

        // 回显门店展台展柜
        KonkaR3StoreShow ks = new KonkaR3StoreShow();
        ks.setStore_id(Long.valueOf(store_id));
        // ks.setTask_type(0);
        // List<KonkaR3StoreShow> ksList1 =
        // super.getFacade().getKonkaR3StoreShowService().getKonkaR3StoreShowList(ks);
        // request.setAttribute("ksList1", ksList1);
        //
        // // 回显门店展台展柜
        // KonkaR3StoreShow ks1 = new KonkaR3StoreShow();
        // ks1.setStore_id(Long.valueOf(store_id));
        // ks1.setTask_type(1);

        ks.getRow().setFirst(0);
        ks.getRow().setCount(50);
        List<KonkaR3StoreShow> ksList = super.getFacade().getKonkaR3StoreShowService().getKonkaR3StoreShowLBPaginatedList(ks);
        request.setAttribute("ksList", ksList);

        String exCXY = temp.toString();
        if (exCXY.length() > 0) {
            exCXY = temp.substring(0, temp.length() - 1);
        }
        request.setAttribute("exCXY", exCXY);

        return mapping.findForward("view");
    }

    public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (null == super.checkUserModPopeDom(form, request, "4")) {
            return super.checkPopedomInvalid(request, response);
        }

        setNaviStringToRequestScope(form, request);

        DynaBean dynaBean = (DynaBean) form;

        String store_id = (String) dynaBean.get("store_id");
        // String[] pks = (String[]) dynaBean.get("pks");
        String mod_id = (String) dynaBean.get("mod_id");

        if (!StringUtils.isBlank(store_id) && GenericValidator.isLong(store_id)) {

            PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

            // 插入停用记录表
            KonkaR3Store kStore = new KonkaR3Store();
            kStore.setStore_id(Long.valueOf(store_id));
            kStore = super.getFacade().getKonkaR3StoreService().getKonkaR3Store(kStore);

            if (null != kStore) {
                KonkaStoreUserCInfo k = new KonkaStoreUserCInfo();
                k.setAdd_date(new Date());
                k.setAdd_user_id(ui.getId());
                k.setAdd_user_job_id(ui.getJob_id());
                k.setAdd_user_name(ui.getUser_name());
                k.setC_type(30);
                k.setChange_info("该门店被停用!");
                k.setSs_id(kStore.getStore_id());
                k.setSs_name(kStore.getStore_name());
                super.getFacade().getKonkaStoreUserCInfoService().createKonkaStoreUserCInfo(k);
            }

            KonkaR3Store entity = new KonkaR3Store();
            entity.setStore_id(Long.valueOf(store_id));
            entity.setIs_del(1);
            // 2014-11-27 添加维护时间
            entity.setModify_date(new Date());
            // 2014-11-26 添加修改人ID
            entity.setModify_user_id(ui.getId());
            super.getFacade().getKonkaR3StoreService().modifyKonkaR3Store(entity);

            saveMessage(request, "konka.close.success");
        }
        // else if (!ArrayUtils.isEmpty(pks)) {
        // KonkaR3Store entity = new KonkaR3Store();
        // entity.getMap().put("pks", pks);
        // super.getFacade().getKonkaR3StoreService().modifyKonkaR3Store(entity);
        // saveMessage(request, "konka.close.success");
        // }

        // the line below is added for pagination
        StringBuffer pathBuffer = new StringBuffer();
        pathBuffer.append(mapping.findForward("success").getPath());
        pathBuffer.append("&mod_id=").append(mod_id);
        pathBuffer.append("&");
        pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "store_id", "method")));
        ActionForward forward = new ActionForward(pathBuffer.toString(), true);
        // end

        return forward;
    }

    public ActionForward reStart(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (null == super.checkUserModPopeDom(form, request, "4")) {
            return super.checkPopedomInvalid(request, response);
        }
        PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
        setNaviStringToRequestScope(form, request);

        DynaBean dynaBean = (DynaBean) form;

        String store_id = (String) dynaBean.get("store_id");
        // String[] pks = (String[]) dynaBean.get("pks");
        String mod_id = (String) dynaBean.get("mod_id");

        if (!StringUtils.isBlank(store_id) && GenericValidator.isLong(store_id)) {

            // 插入停用记录表
            KonkaR3Store kStore = new KonkaR3Store();
            kStore.setStore_id(Long.valueOf(store_id));
            kStore = super.getFacade().getKonkaR3StoreService().getKonkaR3Store(kStore);

            if (null != kStore) {
                KonkaStoreUserCInfo k = new KonkaStoreUserCInfo();
                k.setAdd_date(new Date());
                k.setAdd_user_id(ui.getId());
                k.setAdd_user_job_id(ui.getJob_id());
                k.setAdd_user_name(ui.getUser_name());
                k.setC_type(30);
                k.setChange_info("该门店被启用!");
                k.setSs_id(kStore.getStore_id());
                k.setSs_name(kStore.getStore_name());
                super.getFacade().getKonkaStoreUserCInfoService().createKonkaStoreUserCInfo(k);
            }

            KonkaR3Store entity = new KonkaR3Store();
            entity.setStore_id(Long.valueOf(store_id));
            entity.setIs_del(0);
            // 2014-11-27 添加维护时间
            entity.setModify_date(new Date());
            // 2014-11-26 添加修改人ID
            entity.setModify_user_id(ui.getId());
            super.getFacade().getKonkaR3StoreService().modifyKonkaR3Store(entity);
            saveMessage(request, "konka.restart.success");
        }
        // else if (!ArrayUtils.isEmpty(pks)) {
        // KonkaR3Store entity = new KonkaR3Store();
        // entity.getMap().put("pks", pks);
        // super.getFacade().getKonkaR3StoreService().modifyKonkaR3Store(entity);
        // saveMessage(request, "konka.restart.success");
        // }

        // the line below is added for pagination
        StringBuffer pathBuffer = new StringBuffer();
        pathBuffer.append(mapping.findForward("success").getPath());
        pathBuffer.append("&mod_id=").append(mod_id);
        pathBuffer.append("&");
        pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "store_id", "method")));
        ActionForward forward = new ActionForward(pathBuffer.toString(), true);
        // end

        return forward;
    }

    public ActionForward listCustomer(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        setNaviStringToRequestScope(form, request);

        DynaBean dynaBean = (DynaBean) form;

        String fgs_id = (String) dynaBean.get("fgs_id");
        String is_xx = (String) dynaBean.get("is_xx");// 根据页面传的值，判断是否需要显示停用的R3客户
        String customer_name_like = (String) dynaBean.get("customer_name_like");
        String r3_code = (String) dynaBean.get("r3_code");
        String type = (String) dynaBean.get("type");

        if (!GenericValidator.isLong(fgs_id)) {
            return null;
        }

        KonkaDept t = new KonkaDept();
        t.setDept_id(Long.valueOf(fgs_id));
        t = super.getFacade().getKonkaDeptService().getKonkaDept(t);

        if (null == t || t.getDept_sn() == null) {
            return null;
        }

        KonkaR3Shop konkaR3Shop = new KonkaR3Shop();
        konkaR3Shop.setBranch_area_name_2(t.getDept_sn());
        konkaR3Shop.getMap().put("customer_name_like", customer_name_like);
        if (null != is_xx && "0".equals(is_xx)) {
            konkaR3Shop.setIs_del(0L);
        }
        if (StringUtils.isNotBlank(r3_code)) {
            konkaR3Shop.setR3_code(r3_code);
        }
        List<KonkaR3Shop> entityList = super.getFacade().getKonkaR3ShopService().getKonkaR3ShopList(konkaR3Shop);
        request.setAttribute("entityList", entityList);
        request.setAttribute("type", type);

        return new ActionForward("/admin/KonkaR3Store/list-customer.jsp");
    }

    /**
     * 获取出货仓信息
     */
    public ActionForward listchc(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        setNaviStringToRequestScope(form, request);

        DynaBean dynaBean = (DynaBean) form;

        String r3_code = (String) dynaBean.get("r3_code");

        List<JBaseStore> jbaseStorelist = new ArrayList<JBaseStore>();

        KonkaR3Shop konkaR3shop = new KonkaR3Shop();

        if (StringUtils.isNotBlank(r3_code)) {
            konkaR3shop.setR3_code(r3_code);
            List<KonkaR3Shop> entityList = super.getFacade().getKonkaR3ShopService().getKonkaR3ShopList(konkaR3shop);
            if (null != entityList && entityList.size() > 0) {
                KonkaR3Shop entity = entityList.get(0);
                JBaseStore jbaseStore = new JBaseStore();
                jbaseStore.setC_id(entity.getId());
                jbaseStore.setIs_del(0);
                jbaseStorelist = super.getFacade().getJBaseStoreService().getJBaseStoreForR3WithSdfNullOrNotList(jbaseStore, true);
            }
        }
        request.setAttribute("entityList", jbaseStorelist);
        return new ActionForward("/admin/KonkaR3Store/list_jbaseStore.jsp");
    }

    public ActionForward view2(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (null == super.checkUserModPopeDom(form, request, "0")) {
            return super.checkPopedomInvalid(request, response);
        }
        super.getModPopeDom(form, request);
        setNaviStringToRequestScope(form, request);

        DynaBean dynaBean = (DynaBean) form;
        String id = (String) dynaBean.get("id");

        if (!GenericValidator.isLong(id)) {
            saveError(request, "errors.long", new String[] {id});
            return mapping.findForward("list");
        }

        KonkaR3StoreShow entity = new KonkaR3StoreShow();
        entity.setId(Long.valueOf(id));
        entity = super.getFacade().getKonkaR3StoreShowService().getKonkaR3StoreShow(entity);

        if (null == entity) {
            saveError(request, "errors.long", new String[] {id});
            return mapping.findForward("list");
        }
        entity.setQueryString(super.serialize(request, "id", "mod_id"));
        // request.setAttribute("entity", entity);
        super.copyProperties(form, entity);

        return new ActionForward("/admin/KonkaR3Store/view2.jsp");
    }

    public ActionForward deleteFile(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        DynaBean dynaBean = (DynaBean) form;

        String id = (String) dynaBean.get("id");

        if (StringUtils.isNotBlank(id) && GenericValidator.isLong(id)) {
            Attachment entity = new Attachment();
            entity.setId(new Long(id));
            super.getFacade().getAttachmentService().removeAttachment(entity);
        }

        super.renderText(response, "success");
        return null;
    }

    public ActionForward validateName(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        DynaBean dynaBean = (DynaBean) form;

        StringBuffer oper = new StringBuffer("{\"result\":");
        String store_name = (String) dynaBean.get("v_store_name");
        KonkaR3Store entity = new KonkaR3Store();
        entity.getMap().put("eq_store_name", store_name);
        // entity.setStore_name(store_name);

        // entity.setIs_del(0);
        Long count = super.getFacade().getKonkaR3StoreService().getKonkaR3StoreCount(entity);
        if ("0".equals(count.toString())) {
            oper.append(false);
        } else {
            oper.append(true);
        }
        oper.append("}");
        super.render(response, oper.toString(), "text/x-json;charset=UTF-8");
        return null;
    }

    /************************* 更改UI后新方法 ***********************************/
    /**
     * 初始化
     * 
     * @author Angus
     * @date 2014-7-25
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        HashMap allmap = new HashMap();
        // 位置信息
        DynaBean dynaBean = (DynaBean) form;
        String mod_id = (String) dynaBean.get("mod_id");

        String naviString = "";
        if (StringUtils.isNotBlank(mod_id)) {
            SysModule sysModule = new SysModule();
            sysModule.getMap().put("mod_id", mod_id);
            List<SysModule> sysModuleList = getFacade().getSysModuleService().getSysModuleList(sysModule);
            naviString = StringHelper.getNaviString(sysModuleList, " > ");
        }
        allmap.put("local_str", naviString);

        // 当前用户信息
        PeProdUser ui = new PeProdUser();
        ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
        int max_dlevel = super.getMaxDLevel(ui.getId());
        if (max_dlevel == 9) {
            allmap.put("dept_id", "");
        } else {
            allmap.put("dept_id", ui.getDept_id());
        }

        // 转换为json数据
        JSONArray jsonArray = JSONArray.fromObject(allmap);

        int start = jsonArray.toString().indexOf("[");
        int end = jsonArray.toString().lastIndexOf("}");

        response.setContentType("application/json;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter out = response.getWriter();
        out.print(jsonArray.toString().substring(start + 1, end + 1));
        out.flush();
        out.close();
        return null;
    }

    /**
     * 初始化是否停用列表
     * 
     * @author Angus
     * @date 2014-7-25
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getIsStop(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 统计维度
        ArrayList<HashMap> typeList = new ArrayList<HashMap>();
        HashMap m = new HashMap();
        m.put("code_value", "0");
        m.put("text_value", "未停用");
        typeList.add(m);
        m = new HashMap();
        m.put("code_value", "1");
        m.put("text_value", "已停用");
        typeList.add(m);

        // 转换为json数据
        JSONArray jsonArray = JSONArray.fromObject(typeList);
        response.setContentType("application/json;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter out = response.getWriter();
        out.print(jsonArray.toString());
        out.flush();
        out.close();
        return null;
    }

    public ActionForward queryList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        KonkaR3Store entity = getEntiy(mapping, form, request);

        Long recordCount = getFacade().getKonkaR3StoreService().getKonkaR3StoreAndYwyNameCount(entity);
        DynaBean dynaBean = (DynaBean) form;
        Pager pager = (Pager) dynaBean.get("pager");
        pager.init(recordCount, pager.getPageSize(), (String) entity.getMap().get("page"));
        entity.getRow().setFirst(pager.getFirstRow());
        entity.getRow().setCount(pager.getRowCount());

        List<HashMap> entityList = null;
        HashMap allmap = null;
        if (recordCount > 0) {
            entityList = super.getFacade().getKonkaR3StoreService().getKonkaR3StoreAndYwyNamePaginatedNew(entity);
        }

        // 封装成JSON字符串
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("total", recordCount);
        if (entityList == null) {
            String[] str = {};
            m.put("rows", str);
        } else {
            m.put("rows", entityList);
        }
        JSONArray jsonArray = JSONArray.fromObject(m);
        int start = jsonArray.toString().indexOf("[");
        int end = jsonArray.toString().lastIndexOf("}");
        String jsonStr = jsonArray.toString().substring(start + 1, end + 1);
        response.setContentType("application/json;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter out = response.getWriter();
        out.print(jsonStr);
        out.flush();
        out.close();
        return null;
    }

    /**
     * 导出数据
     * 
     * @author Angus
     * @date 2014-7-24
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward exportData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=" + EncryptUtils.encodingFileName("门店管理") + ".xls");
        KonkaR3Store entity = getEntiy(mapping, form, request);
        Long recordCount = getFacade().getKonkaR3StoreService().getKonkaR3StoreAndYwyNameCount(entity);
        entity.getRow().setFirst(0);
        entity.getRow().setCount(recordCount.intValue());
        List<HashMap> entityList = super.getFacade().getKonkaR3StoreService().getKonkaR3StoreAndYwyNamePaginatedNew(entity);
        request.setAttribute("allList", entityList);
        return new ActionForward("/admin/KonkaR3Store_new/view.jsp");
    }

    /**
     * 封装数据
     * 
     * @author Angus
     * @date 2014-7-24
     * @param mapping
     * @param form
     * @param request
     * @return
     * @throws Exception
     */
    public KonkaR3Store getEntiy(ActionMapping mapping, ActionForm form, HttpServletRequest request) throws Exception {

        String page = request.getParameter("page");
        // 分公司
        String dept_id = request.getParameter("dept_id");
        // 门店名称
        String store_name = request.getParameter("store_name");
        // 门店编码
        String store_id = request.getParameter("store_id");
        // R3编码
        String r3_code = request.getParameter("r3_code");
        // 业务员
        String ywy_name = request.getParameter("ywy_name");
        // 促销员
        String cxy_name = request.getParameter("cxy_name");
        // 一级客户类型
        String cus_type1 = request.getParameter("cus_type1");
        // 二级客户类型
        String cus_type2 = request.getParameter("cus_type2");
        // 经办名称
        String jb_name = request.getParameter("jb_name");
        // 是否停用
        String is_stop = request.getParameter("is_stop");

        KonkaR3Store entity = new KonkaR3Store();

        if (StringUtils.isNotBlank(dept_id)) {
            entity.setDept_id(Long.valueOf(dept_id));
        }
        if (StringUtils.isNotBlank(store_name)) {
            store_name = store_name.replaceAll("&#40;", "（").replaceAll("&#41;", "）");
            entity.getMap().put("store_name_like", store_name);
        }
        if (StringUtils.isNotBlank(store_id) && GenericValidator.isLong(store_id)) {
            entity.setStore_id(Long.valueOf(store_id));
        }
        if (StringUtils.isNotBlank(is_stop)) {
            entity.setIs_del(Integer.valueOf(is_stop));
        }
        // 促销员，业务员模糊查询条件
        if (StringUtils.isNotBlank(cxy_name)) {
            entity.getMap().put("zxy_name_like", cxy_name);
        }
        if (StringUtils.isNotBlank(ywy_name)) {
            entity.getMap().put("ywy_name_like", ywy_name);
        }
        entity.getMap().put("r3_code_like", r3_code);

        if (StringUtils.isNotBlank(jb_name)) {
            entity.setJb_name(jb_name);
        }

        // 添加客户类型筛选条件
        if (cus_type1 != null && !"".equals(cus_type1)) {
            entity.getMap().put("customer_type1", cus_type1);
        }
        if (cus_type2 != null && !"".equals(cus_type2)) {
            entity.getMap().put("customer_type2", cus_type2);
        }

        PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

        PeRoleUser roleUser = new PeRoleUser();
        roleUser.setUser_id(ui.getId());
        List<PeRoleUser> roleUserList = super.getFacade().getPeRoleUserService().getPeRoleUserList(roleUser);

        boolean role_id_eq_10 = false; // 系统管理员

        for (PeRoleUser peRoleUser : roleUserList) {
            if (peRoleUser.getRole_id() < 30L || (peRoleUser.getRole_id() >= 200L && peRoleUser.getRole_id() < 300L)) {
                role_id_eq_10 = true;
            }
        }

        // 数据级别判断开始
        Long __dept_id = StringUtils.isNotBlank(dept_id) ? Long.valueOf(dept_id) : ui.getDept_id(); // 默认为当前用户所在部门
        int max_dlevel = super.getMaxDLevel(ui.getId()); // 获取当前用户的最高可视部门级别
        logger.info("Max level : {}", max_dlevel);
        request.setAttribute("max_dlevel", max_dlevel);
        switch (max_dlevel) {
            case 9:
                // 集团及以下部门可见
                __dept_id = 0L; // 0表示部门根节点，即“多媒体事业本部”
                break;
            case 8:
                // 分公司及以下部门可见
                KonkaDept dept_fgs = super.getKonkaDeptForFgs(ui.getDept_id()); // 查询部门分公司
                if (null != dept_fgs && null != dept_fgs.getDept_id()) {
                    __dept_id = dept_fgs.getDept_id(); // 分公司部门ID
                    entity.getMap().put("dept_id_start", __dept_id);
                }
                break;
            case 7:
                // 我所在的部门及以下部门可见
                __dept_id = ui.getDept_id(); // 默认为当前用户所在部门
                entity.getMap().put("dept_id_start", __dept_id);
                break;
            case 0:
                __dept_id = ui.getDept_id(); // 默认为当前用户所在部门
                entity.getMap().put("filter_by_ywy_job_id_eq", ui.getJob_id());
                break;
            default:
                // 出错
        }
        // 数据级别判断结束
        entity.getMap().put("session_user_id", ui.getId());
        entity.getMap().put("filter_by_job_id_eq", ui.getJob_id());

        if (role_id_eq_10) {
            // 查询分公司基础数据
            KonkaDept kd = new KonkaDept();
            kd.setDept_id(ui.getDept_id());
            kd = super.getFacade().getKonkaDeptService().getKonkaDept(kd);
            entity.getMap().put("dept_name", kd.getDept_name());
            entity.getMap().put("order_by_dept_name", true);
        }
        entity.getMap().put("page", page);
        return entity;
    }

    /**
     * 停用门店
     * 
     * @author Angus
     * @date 2014-7-28
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward stopStore(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (null == super.checkUserModPopeDom(form, request, "4")) {
            return super.noPersission(request, response);
        }

        DynaBean dynaBean = (DynaBean) form;

        String store_id = (String) dynaBean.get("store_id");
        String mod_id = (String) dynaBean.get("mod_id");

        int result = 0;

        if (!StringUtils.isBlank(store_id) && GenericValidator.isLong(store_id)) {
            PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

            // 插入停用记录表
            KonkaR3Store kStore = new KonkaR3Store();
            kStore.setStore_id(Long.valueOf(store_id));
            kStore = super.getFacade().getKonkaR3StoreService().getKonkaR3Store(kStore);

            if (null != kStore) {
                KonkaStoreUserCInfo k = new KonkaStoreUserCInfo();
                k.setAdd_date(new Date());
                k.setAdd_user_id(ui.getId());
                k.setAdd_user_job_id(ui.getJob_id());
                k.setAdd_user_name(ui.getUser_name());
                k.setC_type(30);
                k.setChange_info("该门店被停用!");
                k.setSs_id(kStore.getStore_id());
                k.setSs_name(kStore.getStore_name());
                super.getFacade().getKonkaStoreUserCInfoService().createKonkaStoreUserCInfo(k);
            }

            KonkaR3Store entity = new KonkaR3Store();
            entity.setStore_id(Long.valueOf(store_id));
            entity.setIs_del(1);
            entity.setStop_date(new Date());
            result = super.getFacade().getKonkaR3StoreService().modifyKonkaR3Store(entity);
        }

        // 封装成JSON字符串
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("success", result);
        JSONArray jsonArray = JSONArray.fromObject(m);
        int start = jsonArray.toString().indexOf("[");
        int end = jsonArray.toString().lastIndexOf("}");
        String jsonStr = jsonArray.toString().substring(start + 1, end + 1);
        response.setContentType("application/json;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter out = response.getWriter();
        out.print(jsonStr);
        out.flush();
        out.close();
        return null;
    }

    /**
     * 启用门店
     * 
     * @author Angus
     * @date 2014-7-25
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward startStore(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        int suc = 0;
        PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

        DynaBean dynaBean = (DynaBean) form;

        String store_id = request.getParameter("store_id");
        String mod_id = request.getParameter("mod_id");

        if (!StringUtils.isBlank(store_id) && GenericValidator.isLong(store_id)) {
            // 插入停用记录表
            KonkaR3Store kStore = new KonkaR3Store();
            kStore.setStore_id(Long.valueOf(store_id));
            kStore = super.getFacade().getKonkaR3StoreService().getKonkaR3Store(kStore);

            if (null != kStore) {
                KonkaStoreUserCInfo k = new KonkaStoreUserCInfo();
                k.setAdd_date(new Date());
                k.setAdd_user_id(ui.getId());
                k.setAdd_user_job_id(ui.getJob_id());
                k.setAdd_user_name(ui.getUser_name());
                k.setC_type(30);
                k.setChange_info("该门店被启用!");
                k.setSs_id(kStore.getStore_id());
                k.setSs_name(kStore.getStore_name());
                super.getFacade().getKonkaStoreUserCInfoService().createKonkaStoreUserCInfo(k);
            }

            KonkaR3Store entity = new KonkaR3Store();
            entity.setStore_id(Long.valueOf(store_id));
            entity.setIs_del(0);

            suc = super.getFacade().getKonkaR3StoreService().modifyKonkaR3Store(entity);
        }

        // 封装成JSON字符串
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("success", suc);
        JSONArray jsonArray = JSONArray.fromObject(m);
        int start = jsonArray.toString().indexOf("[");
        int end = jsonArray.toString().lastIndexOf("}");
        String jsonStr = jsonArray.toString().substring(start + 1, end + 1);
        response.setContentType("application/json;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter out = response.getWriter();
        out.print(jsonStr);
        out.flush();
        out.close();
        return null;
    }
}
