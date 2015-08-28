package com.ebiz.mmt.web.struts.manager.admin;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.Sheet;
import jxl.Workbook;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.CrmCustomerGroup;
import com.ebiz.mmt.domain.CrmPriceHeader;
import com.ebiz.mmt.domain.CrmPriceLines;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.PePdModel;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.struts.MmtFilePathConfig;
import com.ebiz.org.apache.commons.lang3.StringUtils;
import com.ebiz.ssi.web.struts.bean.Pager;
import com.ebiz.ssi.web.struts.bean.UploadFile;

public class CrmPriceHeaderAction extends BaseAction {
    @Override
    public ActionForward unspecified(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        return this.list(mapping, form, request, response);
    }

    public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        setNaviStringToRequestScope(form, request);
        DynaBean dynaBean = (DynaBean) form;
        Pager pager = (Pager) dynaBean.get("pager");

        // String mod_id = (String) dynaBean.get("mod_id");
        String groupcode = (String) dynaBean.get("groupcode");
        String price_code = (String) dynaBean.get("price_code");
        String price_name = (String) dynaBean.get("price_name");
        String dept_id = (String) dynaBean.get("dept_id");//
        String isdel = (String) dynaBean.get("isdel");//
        String modelcode_line = (String) dynaBean.get("modelcode_line");//
        // String to date
        // String begindate = (String) dynaBean.get("begindate");
        // String enddate = (String) dynaBean.get("enddate");

        PeProdUser userInfo = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
        if (null == userInfo) {
            String msg = super.getMessage(request, "user.session.isEmpty");
            super.renderJavaScript(response, "window.onload=function(){alert('" + msg
                    + "');history.back();}");
            return null;
        }

        CrmPriceHeader h = new CrmPriceHeader();
        // 多媒体事业本部 0

        if (StringUtils.isNotBlank(dept_id)) {
            h.setDept_id(Long.valueOf(dept_id));
        } else {
            if (userInfo.getDept_id() != null && userInfo.getDept_id() != 0) {
                h.setDept_id(Long.valueOf(userInfo.getDept_id()));
            }
        }

        if (isdel != null && !"".equals(isdel)) {
            h.setIsdel(new Integer(isdel));
        }
        if (price_code != null && !"".equals(price_code)) {
            h.setPrice_code(price_code.trim());
        }
        if (price_name != null && !"".equals(price_name)) {
            h.setPrice_name(price_name.trim());
        }
        if (groupcode != null && !"".equals(groupcode)) {
            h.setGroupcode(groupcode.trim());
        }
        if (modelcode_line != null && !"".equals(modelcode_line)) {
            h.getMap().put("modelcode_line", modelcode_line.trim());
        }

        Long recordCount = super.getFacade().getCrmPriceHeaderService().getCrmPriceHeaderCount(h);
        pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
        h.getRow().setFirst(pager.getFirstRow());
        h.getRow().setCount(pager.getRowCount());
        List<CrmPriceHeader> crmPriceHeaderList =
                super.getFacade().getCrmPriceHeaderService().getCrmPriceHeaderPaginatedList(h);

        request.setAttribute("entityList", crmPriceHeaderList);
        KonkaDept konkaDept = new KonkaDept();
        konkaDept.setDept_type(3);
        // 多媒体事业本部 0 系统管理员
        if (userInfo.getDept_id() != 0) {
            konkaDept.setDept_id(userInfo.getDept_id());
        }
        konkaDept.getMap().put("order_by_dept_name", true);
        List<KonkaDept> deptList =
                super.getFacade().getKonkaDeptService().getKonkaDeptList(konkaDept);
        request.setAttribute("deptList", deptList);
        // CrmPriceHeader
        return mapping.findForward("list");

    }

    // 新增显示form表单
    public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        setNaviStringToRequestScope(form, request);
        DynaBean dynaBean = (DynaBean) form;
        // String dept_id = (String) dynaBean.get("dept_id");
        // String mod_id = (String) dynaBean.get("mod_id");
        PeProdUser userInfo = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
        Pager pager = (Pager) dynaBean.get("pager");
        pager.init(0l, 10, "1");
        dynaBean.set("pager", pager);
        CrmPriceHeader h = new CrmPriceHeader();
        String s = super.getFacade().getCrmPriceHeaderService().getPriceCode();
        h.setPrice_code(s);
        super.copyProperties(form, h);// important

        KonkaDept konkaDept = new KonkaDept();
        konkaDept.setDept_type(3);
        // 多媒体事业本部 0 系统管理员
        if (userInfo.getDept_id() != 0) {
            konkaDept.setDept_id(userInfo.getDept_id());
        }
        konkaDept.getMap().put("order_by_dept_name", true);
        List<KonkaDept> deptList =
                super.getFacade().getKonkaDeptService().getKonkaDeptList(konkaDept);
        request.setAttribute("deptList", deptList);

        // ctmgroupList
        CrmCustomerGroup t = new CrmCustomerGroup();
        t.setIsdel(0);
        // 多媒体事业本部 0
        if (userInfo.getDept_id() != 0) {
            t.setDeptid(Long.valueOf(userInfo.getDept_id()));
        }
        List<CrmCustomerGroup> groupList = new ArrayList<CrmCustomerGroup>();
        groupList = super.getFacade().getCrmCustomerGroupService().getCrmCustomerGroupList(t);
        request.setAttribute("groupList", groupList);

        return mapping.findForward("input");
    }

    public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) {
        DynaBean dynaBean = (DynaBean) form;
        Pager pager = (Pager) dynaBean.get("pager");
        String id = (String) dynaBean.get("id");// id
        CrmPriceHeader h = new CrmPriceHeader();
        if (!GenericValidator.isLong(id)) {
            String msg = super.getMessage(request, "errors.param");
            super.renderJavaScript(response, "window.onload=function(){alert('" + msg
                    + "');history.back();}");
            return null;
        }
        h.setId(Long.valueOf(id));
        // get header
        h = super.getFacade().getCrmPriceHeaderService().getCrmPriceHeader(h);
        if (null == h) {
            String msg = super.getMessage(request, "entity.empty");
            super.renderJavaScript(response, "window.onload=function(){alert('" + msg
                    + "');history.back();}");
            return null;
        }

        KonkaDept konkaDept = new KonkaDept();
        konkaDept.setDept_id(h.getDept_id());
        konkaDept = super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
        h.getMap().put("dept_name", konkaDept.getDept_name());

        super.copyProperties(form, h);// important

        CrmCustomerGroup cg = new CrmCustomerGroup();
        cg.setGroupcode(h.getGroupcode());
        cg = super.getFacade().getCrmCustomerGroupService().getCrmCustomerGroup(cg);
        h.getMap().put("groupname", cg.getGroupname());

        // get CrmPriceLinessss
        CrmPriceLines l = new CrmPriceLines();
        l.setHeadid(Long.valueOf(id));

        Long recordCount = super.getFacade().getCrmPriceLinesService().getCrmPriceLinesCount(l);
        pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
        l.getRow().setFirst(pager.getFirstRow());
        l.getRow().setCount(pager.getRowCount());
        List<CrmPriceLines> lList =
                super.getFacade().getCrmPriceLinesService().getCrmPriceLinesPaginatedList(l);
        request.setAttribute("lineList", lList);
        return mapping.findForward("view");
    }

    /**
     * 修改数据页面
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        saveToken(request);
        setNaviStringToRequestScope(form, request);
        DynaBean dynaBean = (DynaBean) form;

        Pager pager = (Pager) dynaBean.get("pager");
        String id = (String) dynaBean.get("id");// id
        String dept_id = (String) dynaBean.get("dept_id");
        PeProdUser peProdUser =
                (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
        CrmPriceHeader h = new CrmPriceHeader();
        if (!GenericValidator.isLong(id)) {
            String msg = super.getMessage(request, "errors.param");
            super.renderJavaScript(response, "window.onload=function(){alert('" + msg
                    + "');history.back();}");
            return null;
        }
        h.setId(Long.valueOf(id));
        // get header
        h = super.getFacade().getCrmPriceHeaderService().getCrmPriceHeader(h);
        if (null == h) {
            String msg = super.getMessage(request, "entity.empty");
            super.renderJavaScript(response, "window.onload=function(){alert('" + msg
                    + "');history.back();}");
            return null;
        }
        super.copyProperties(form, h);// important

        // get CrmPriceLinessss
        CrmPriceLines l = new CrmPriceLines();
        l.setHeadid(Long.valueOf(id));

        Long recordCount = super.getFacade().getCrmPriceLinesService().getCrmPriceLinesCount(l);
        pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
        l.getRow().setFirst(pager.getFirstRow());
        l.getRow().setCount(pager.getRowCount());
        List<CrmPriceLines> lList =
                super.getFacade().getCrmPriceLinesService().getCrmPriceLinesPaginatedList(l);
        request.setAttribute("lineList", lList);

        KonkaDept konkaDept = new KonkaDept();
        konkaDept.setDept_type(3);
        // 多媒体事业本部 0 系统管理员
        if (peProdUser.getDept_id() != 0) {
            konkaDept.setDept_id(peProdUser.getDept_id());
        }
        konkaDept.getMap().put("order_by_dept_name", true);
        List<KonkaDept> deptList =
                super.getFacade().getKonkaDeptService().getKonkaDeptList(konkaDept);
        request.setAttribute("deptList", deptList);

        PeRoleUser _peRoleUser = new PeRoleUser();
        _peRoleUser.setUser_id(peProdUser.getId());
        List<PeRoleUser> peRoleUserList =
                this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);
        boolean role_id_lt_30 = false;
        boolean role_id_ge_30_lt_60 = false;
        boolean role_id_eq_60 = false;
        for (PeRoleUser peRoleUser : peRoleUserList) {
            if (peRoleUser.getRole_id() < 30) {
                // // KONKA_PE_ROLE_info 10
                // // 10 系统管理员
                // // 20 事业部管理员
                // // 21 事业部领导
                // // 22 事业部市场部管理员
                // // 28 渠道管理部人员
                // // 29 分公司-数据主管
                role_id_lt_30 = true;
                break;
            }
            if (peRoleUser.getRole_id() >= 30L && peRoleUser.getRole_id() < 60L) {
                // // 30 分公司管理员
                // // 31 分公司领导
                // // 32 分公司工作人员
                // // 34 分公司总经理
                // // 35 分公司财务部
                // // 36 分公司销管部
                // // 37 分公司市场部
                // // 38 分公司产品会计
                // // 39 分公司财务经理
                // // 40 经营部管理员
                // // 50 办事处管理员
                role_id_ge_30_lt_60 = true;
                break;
            }
            if (peRoleUser.getRole_id() == 60L) {
                // 业务员
                role_id_eq_60 = true;
                break;
            }
        }

        // ctmgroupList
        CrmCustomerGroup t = new CrmCustomerGroup();
        if (!role_id_lt_30) {
            if (StringUtils.isNotEmpty(dept_id)) {
                t.setDeptid(Long.valueOf(dept_id));
            }
        }
        t.setIsdel(0);

        List<CrmCustomerGroup> groupList = new ArrayList<CrmCustomerGroup>();
        groupList = super.getFacade().getCrmCustomerGroupService().getCrmCustomerGroupList(t);
        request.setAttribute("groupList", groupList);

        return mapping.findForward("input");
    }

    /**
     * 通过修改有效时间来失效数据
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        saveToken(request);
        setNaviStringToRequestScope(form, request);
        DynaBean dynaBean = (DynaBean) form;

        String id = (String) dynaBean.get("id");
        String mod_id = (String) dynaBean.get("mod_id");
        String isdel = (String) dynaBean.get("isdel");
        CrmPriceHeader h = new CrmPriceHeader();
        if (StringUtils.isNotEmpty(id)) {
            h.setId(Long.valueOf(id));
        } else {
            String msg = super.getMessage(request, "id丢失");
            super.renderJavaScript(response, "window.onload=function(){alert('" + msg
                    + "');history.back();}");
            return mapping.findForward("list");
        }
        if (StringUtils.isNotEmpty(isdel)) {
            h.setIsdel(Integer.valueOf(isdel));
        } else {
            return mapping.findForward("list");
        }

        super.getFacade().getCrmPriceHeaderService().modifyCrmPriceHeader(h);
        saveMessage(request, "entity.updated");
        // the line below is added for pagination
        StringBuffer pathBuffer = new StringBuffer();
        pathBuffer.append(mapping.findForward("success").getPath());
        pathBuffer.append("&mod_id=" + mod_id);
        ActionForward forward = new ActionForward(pathBuffer.toString(), true);
        // end
        return forward;
    }

    public ActionForward deleteL(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        saveToken(request);
        setNaviStringToRequestScope(form, request);
        DynaBean dynaBean = (DynaBean) form;

        String id = (String) dynaBean.get("id");
        // String mod_id = (String) dynaBean.get("mod_id");
        String lineId = (String) dynaBean.get("line_id");
        CrmPriceLines l = new CrmPriceLines();
        if (StringUtils.isNotEmpty(id)) {
            l.setHeadid(Long.valueOf(id)); // line refrence key
        } else {
            return mapping.findForward("list");
        }
        if (StringUtils.isNotEmpty(lineId)) {
            l.setId(Long.valueOf(lineId));// line key
        } else {
            return mapping.findForward("list");
        }

        super.getFacade().getCrmPriceLinesService().removeCrmPriceLines(l);
        saveMessage(request, "entity.updated");
        return this.edit(mapping, form, request, response);
    }

    /**
     * 保存头表(此处不包括行表)
     * 
     * 除了价格名称不能重复外,要求同一分公司的某个客户群组在有效时间内,允许维护唯一一份价格
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        DynaBean dynaBean = (DynaBean) form;
        String mod_id = (String) dynaBean.get("mod_id");
        String price_name = (String) dynaBean.get("price_name");
        HttpSession session = request.getSession();
        PeProdUser userInfo = new PeProdUser();
        userInfo = (PeProdUser) session.getAttribute(Constants.USER_INFO);

        CrmPriceHeader h = new CrmPriceHeader();
        super.copyProperties(h, form);
        h.setPrice_name(price_name.trim());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String beginDate = "";
        String endDate = "";

        if (h.getBegin_date() != null) {
            beginDate = sdf.format(h.getBegin_date());
        } else {
            beginDate = sdf.format(new Date());
        }
        if (h.getEnd_date() != null) {
            endDate = sdf.format(h.getEnd_date());
        } else {
            endDate = sdf.format(new Date());
        }



        // 每个公公司每次定义的价格名称都不允许重复
        boolean priceNameExit =
                super.getFacade().getCrmPriceHeaderService()
                        .isExitsPriceName(h.getDept_id(), h.getPrice_name());
        // 如果按deptId,groupCode,priceType,begindate,enddate填写会与之交叉的数据

        List<CrmPriceHeader> theList =
                super.getFacade()
                        .getCrmPriceHeaderService()
                        .getTheListOfDeptIdGroupCodePriceTypeAndTime(h.getDept_id() + "",
                                h.getGroupcode(), h.getPrice_type(), beginDate, endDate);

        Set<CrmPriceHeader> thatSet = new TreeSet<CrmPriceHeader>();
        for (CrmPriceHeader c : theList) {
            if (!c.equals(h)) {
                thatSet.add(c);
            }
        }

        String repeatPriceCodes = "";
        if (thatSet.size() > 0) {
            for (CrmPriceHeader d : thatSet) {
                repeatPriceCodes += d.getPrice_name() + ";";
            }
        }


        // 除了自己本身,与其它数据在时间内上是否有交叉
        boolean priceTypeDeptIdGroupCodeTime = false;
        if (thatSet != null && thatSet.size() > 0) {
            priceTypeDeptIdGroupCodeTime = true;
        }
        // insert or update
        if (h.getId() == null || "".equals(String.valueOf(h.getId()))) {
            // do insert 存在
            if ((priceNameExit == false) && (priceTypeDeptIdGroupCodeTime == false)) {
                h.setCreated_by(userInfo.getId());
                h.setCreated_date(new Date());
                h.setIsdel(0);// important
                this.getFacade().getCrmPriceHeaderService().createCrmPriceHeader(h);
                saveMessage(request, "entity.inserted");
            } else {
                saveDirectlyError(request,
                        "新增失败,[" + h.getPrice_name() + "]已经存在,也有可能是客户组:" + h.getGroupcode()
                        + "已维护了价格列表!");

            }
        } else {
            // 1.价格名称不能重复;dept_id + price_name + isdel(0)
            // 2.某公司+客户群组+时间段+价格类型
            if (!priceNameExit) {
                if (!priceTypeDeptIdGroupCodeTime) {
                    // do update
                    h.setUpdated_by(userInfo.getId());
                    h.setUpdated_date(new Date());
                    if (h.getId() != 0L) {
                        this.getFacade().getCrmPriceHeaderService().modifyCrmPriceHeader(h);
                    }
                    saveMessage(request, "entity.updated");
                } else {
                    // theList为如果按deptId,groupCode,priceType,begindate,enddate填写会与之交叉的数据
                    saveDirectlyError(request, "#你要维护的价格列表数据[" + h.getPrice_name()
                            + "]与其它价格数据列表数据{" + repeatPriceCodes + "}有重复,请检查!#");
                }
            } else {
                // 如果dept_id + price_name + isdel(0)存在,有可能是它自己本身.
                CrmPriceHeader t = new CrmPriceHeader();
                t.setDept_id(h.getDept_id());
                t.getMap().put("price_name_eq", h.getPrice_name().trim());
                List<CrmPriceHeader> alist =
                        getFacade().getCrmPriceHeaderService().getCrmPriceHeaderList(t);

                // 是否是自己名字本身
                boolean nameself = false;
                if (alist != null && alist.size() > 0) {
                    for (CrmPriceHeader x : alist) {
                        if (x.getId().equals(h.getId())) {
                            nameself = true;
                            break;
                        }
                    }
                }
                if (nameself && (!priceTypeDeptIdGroupCodeTime)) {
                    // do update
                    h.setUpdated_by(userInfo.getId());
                    h.setUpdated_date(new Date());
                    if (h.getId() != 0L) {
                        this.getFacade().getCrmPriceHeaderService().modifyCrmPriceHeader(h);
                    }
                    saveMessage(request, "entity.updated");
                }
                if (nameself && priceTypeDeptIdGroupCodeTime) {
                    saveDirectlyError(request, "##你要维护的价格列表数据[" + h.getPrice_name()
                            + "]与其它价格数据列表数据{" + repeatPriceCodes + "}有重复,请检查!##");
                }
                if (nameself == false) {
                    saveDirectlyError(request, "更新失败,价格列表的名称[" + h.getPrice_name()
                            + "]与其它价格列表名称重名,请检查!");
                }
            }
        }

        // the line below is added for pagination
        StringBuffer pathBuffer = new StringBuffer();
        pathBuffer.append(mapping.findForward("success").getPath());
        pathBuffer.append("&mod_id=" + mod_id);
        ActionForward forward = new ActionForward(pathBuffer.toString(), true);
        // end
        return forward;
    }

    /**
     * 复制一个价格列表(如有此需求再实现)
     * 
     * 根据某一价格ID,执行复制功能
     */
    public ActionForward copyByHeaderId(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        return null;
    }

    /**
     * 弹出机型的页面,新增行表在此页面
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward showGetModelPage(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        DynaBean dynaBean = (DynaBean) form;
        String mod_id = (String) dynaBean.get("mod_id");
        String headid = (String) dynaBean.get("headid");//
        String dept_id = (String) dynaBean.get("dept_id");// 当前操作人所在分公司id
        dynaBean.set("dept_id", dept_id);
        dynaBean.set("mod_id", mod_id);
        dynaBean.set("headid", headid);

        // Pager pager = (Pager) dynaBean.get("pager");
        // pager.init(0l, 10, "1");
        // dynaBean.set("pager", pager);

        PePdModel pm = new PePdModel();
        pm.setIs_del(0);
        pm.getMap().put("not_in_headerId", headid);// 过滤掉已经当前价格组已经保存过的机型

        List<PePdModel> entityList = super.getFacade().getPePdModelService().getPePdModelList(pm);
        List<PePdModel> pmList = new ArrayList<PePdModel>();
        for (PePdModel s : entityList) {
            PePdModel p = new PePdModel();
            p.setPd_id(s.getPd_id());
            p.setPd_desc(s.getPd_desc());
            p.setMd_name(s.getMd_name());
            pmList.add(p);
        }
        entityList = null;
        request.setAttribute("pmList", pmList);
        return new ActionForward("/admin/CrmPriceHeader/getmodel.jsp");
    }

    /**
     * 弹出机型的页面,修改行表在此页面
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward showGetModelPage2(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        DynaBean dynaBean = (DynaBean) form;
        String mod_id = (String) dynaBean.get("mod_id");
        String headid = (String) dynaBean.get("headid");// headerid
        String line_id = (String) dynaBean.get("id");
        String dept_id = (String) dynaBean.get("dept_id");// 当前操作人所在分公司id

        // Pager pager = (Pager) dynaBean.get("pager");
        // pager.init(0l, 10, "1");
        // dynaBean.set("pager", pager);

        PePdModel pm = new PePdModel();
        pm.setIs_del(0);
        pm.getMap().put("not_in_headerId", headid);// 过滤掉已经当前价格组已经保存过的机型

        List<PePdModel> entityList = super.getFacade().getPePdModelService().getPePdModelList(pm);
        List<PePdModel> pmList = new ArrayList<PePdModel>();
        for (PePdModel s : entityList) {
            PePdModel p = new PePdModel();
            p.setPd_id(s.getPd_id());
            p.setPd_desc(s.getPd_desc());
            p.setMd_name(s.getMd_name());
            pmList.add(p);
        }
        entityList = null;
        request.setAttribute("pmList", pmList);

        if (line_id != null && !"".equals(line_id)) {

            CrmPriceLines l = new CrmPriceLines();
            l.setHeadid(Long.valueOf(headid));
            l.setId(Long.valueOf(line_id));
            l = super.getFacade().getCrmPriceLinesService().getCrmPriceLines(l);
            super.copyProperties(form, l);
        }
        // super.renderJson(response, pmList.toString());
        return new ActionForward("/admin/CrmPriceHeader/getmodel.jsp");
    }

    /**
     * 保存行表(单笔新增)
     * 
     * 
     * 需要保证某公司各自管理的有效客户分组,在有效时间内只对客户分组只维护一份价格列表, 一个客户分组只能有一份有效价格列表.从而保证客户的价格管理
     * 
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward saveCrmPriceLines(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        DynaBean dynaBean = (DynaBean) form;
        String headid = (String) dynaBean.get("headid");
        String id = (String) dynaBean.get("id");// line id
        if (headid == null || "".equals(headid)) {
            // 提示错误
            response.getWriter().write("<script>alert('保存失败,先保存头表信息');window.close();</script>");
            return null;
        }

        String modelCode = (String) dynaBean.get("modelcode");
        modelCode = modelCode.replaceAll("&#40;", "(").replaceAll("&#41;", ")");

        CrmPriceLines l = new CrmPriceLines();
        copyProperties(l, form);
        l.setModelcode(modelCode);
        l.setHeadid(Long.valueOf(headid));

        // 取机型的功能和名称

        PePdModel pm = new PePdModel();
        pm.setMd_name(l.getModelcode());// 标点符号被转码
        pm.setIs_del(0);
        pm = super.getFacade().getPePdModelService().getPePdModel(pm);

        if (pm == null) {
            // 提示错误
            response.getWriter().write("<script>alert('所选择机型不存在,或已经失效');window.close();</script>");
            return null;
        }

        // 同一个机型肯定会存在其它价格列表里面,这样控制没有意义,所以只需要保证客户分组当前只维护一份价格名称就行.

        // l.setFunc(pm.get)
        if (pm != null) {
            l.setModelname(pm.getPd_desc());
            l.setFunc(getFuncsByPmModel(pm));
        }

        // 判断行表是新增还是更新
        if ((null == l.getId() || "".equals(String.valueOf(l.getId())))) {
            // validate
            CrmPriceLines templ = new CrmPriceLines();
            templ.setHeadid(Long.valueOf(headid));
            templ.setModelcode(l.getModelcode());
            long count = super.getFacade().getCrmPriceLinesService().getCrmPriceLinesCount(templ);
            if (count > 0) {
                response.getWriter()
                        .write("<script>alert('所选择机型已存在当前列表');window.close();</script>");
                return null;
            } else {
                super.getFacade().getCrmPriceLinesService().createCrmPriceLines(l);
                saveMessage(request, "entity.inserted");
            }

        } else {
            super.getFacade().getCrmPriceLinesService().modifyCrmPriceLines(l);
            saveMessage(request, "entity.updated");
        }
        try {
            response.getWriter().write("<script>window.returnValue='1';window.close();</script>");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 根据机型取出机型的功能列表
     */
    public static String getFuncsByPmModel(PePdModel p) {

        String is4k = "";
        String is3d = "";
        String isInteli = "";
        String isNetTV = "";
        if (p.getIs_4k() != null && !"".equals(String.valueOf(p.getIs_4k())) && (p.getIs_4k() == 1)) {
            is4k = "4K";
        }
        if (p.getLabel_3d() != null && !"".equals(String.valueOf(p.getLabel_3d()))
                && (p.getLabel_3d() == 1)) {
            is3d = "3D";
        }
        if (p.getLabel_www() != null && !"".equals(String.valueOf(p.getLabel_www()))
                && (p.getLabel_www() == 1)) {
            isInteli = "网络电视";
        }
        if (p.getLabel_int() != null && !"".equals(String.valueOf(p.getLabel_int()))
                && (p.getLabel_int() == 1)) {
            isNetTV = "智能电视";
        }
        // 返回的数据
        String[] str = new String[] {is4k, is3d, isInteli, isNetTV};
        ArrayList<String> str2 = new ArrayList<String>();
        for (String s : str) {
            if (!"".equals(s)) {
                str2.add(s);
            }
        }
        return StringUtils.join(str2, ",");

    }

    /**
     * 根据部门ID获取该部门所有的客户群组
     */
    public ActionForward getGroupsByDeptId(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        DynaBean dynaBean = (DynaBean) form;
        String dept_id = (String) dynaBean.get("dept_id");
        if (StringUtils.isNotEmpty(dept_id)) {
            CrmCustomerGroup t = new CrmCustomerGroup();
            t.setIsdel(0);
            t.setDeptid(Long.valueOf(dept_id));
            List<CrmCustomerGroup> glist =
                    super.getFacade().getCrmCustomerGroupService().getCrmCustomerGroupList(t);

            // 返回的数据
            List<String> rList = new ArrayList<String>();
            for (CrmCustomerGroup g : glist) {
                rList.add(StringUtils.join(new String[] {"[\"", g.getGroupcode(), "\",\"",
                        String.valueOf(g.getGroupname()), "\"]"}));
            }
            super.renderJson(response,
                    StringUtils.join(new String[] {"[", StringUtils.join(rList, ","), "]"}));
        }
        return null;
    }

    public ActionForward add_excel(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        saveToken(request);
        setNaviStringToRequestScope(form, request);
        return new ActionForward("/admin/CrmPriceHeader/form_excel.jsp");
    }

    public ActionForward save_excel(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        saveToken(request);
        setNaviStringToRequestScope(form, request);

        DynaBean dynaBean = (DynaBean) form;
        String mod_id = (String) dynaBean.get("mod_id");
        String id = (String) dynaBean.get("id");// headid

        // int isize = 0;
        // int msize = 0;

        // 上传的excel已经保存服务器某目录,现在读回内存
        List<UploadFile> uploadFilesList = super.uploadFile(form, MmtFilePathConfig.OTHERS_PATH);
        // 经过UUID文件名运算,只有一份
        if (uploadFilesList.size() == 1) {
            // files/upload/2014/04/09/659234ca-2330-45d7-9a7f-f85864b116b4.xls
            String fileSavePath = uploadFilesList.get(0).getFileSavePath();
            // D:\java\server\apache-tomcat-6.0.18\wtpwebapps\konka-wd\
            String ctxDir =
                    getServlet().getServletContext()
                            .getRealPath(String.valueOf(File.separatorChar));
            // 附件保存路径更改遗留问题
            if (StringUtils.contains(fileSavePath, MmtFilePathConfig.S_OTHERS_PATH)) {
                ctxDir = "";
                fileSavePath = "/Attachment_new/konka-files/" + fileSavePath;
            }

            Workbook workbook = Workbook.getWorkbook(new File(ctxDir + fileSavePath));
            Sheet sheet = workbook.getSheet(0);
            int rows = sheet.getRows();
            if (rows <= 1) {
                super.renderJavaScript(response,
                        "window.onload=function(){alert('Excel文件内容为空！');history.back();}");
                return null;
            }
            // 第一行为标题
            int start_row = 1;
            // excelList 去重
            List<CrmPriceLines> excelList = new ArrayList<CrmPriceLines>();
            // excel 模板里面共9列
            // 推广政策 /机型功能/ 机型编码 /供价 /市场价 /返利/ 最低限价 /提成/ 备注
            for (int i = start_row; i < rows; i++) {
                CrmPriceLines p = new CrmPriceLines();
                p.setHeadid(Long.valueOf(id));
                // 0列i行 推广政策
                p.setPolicy(sheet.getCell(0, i).getContents());
                // 1列i行 功能
                p.setFunc(sheet.getCell(1, i).getContents());
                // 2列i行 机型编码
                p.setModelcode(sheet.getCell(2, i).getContents());
                // 3列i行 供价
                if (StringUtils.isNotEmpty(sheet.getCell(3, i).getContents())) {
                    p.setForprice(Double.valueOf(sheet.getCell(3, i).getContents()));
                }
                // 4列i行 市场价
                if (StringUtils.isNotEmpty(sheet.getCell(4, i).getContents())) {
                    p.setMarketprice(Double.valueOf(sheet.getCell(4, i).getContents()));
                }
                // 5列i行 返利
                if (StringUtils.isNotEmpty(sheet.getCell(5, i).getContents())) {
                    p.setFl(Double.valueOf(sheet.getCell(5, i).getContents()));
                }
                // 6列i行 最低限价
                if (StringUtils.isNotEmpty(sheet.getCell(6, i).getContents())) {
                    p.setLowestprice(Double.valueOf(sheet.getCell(6, i).getContents()));
                }
                // 7列i行 提成
                if (StringUtils.isNotEmpty(sheet.getCell(7, i).getContents())) {
                    p.setTc(Double.valueOf(sheet.getCell(7, i).getContents()));
                }
                // 8列i行 备注
                if (StringUtils.isNotEmpty(sheet.getCell(8, i).getContents())) {
                    p.setRemark(sheet.getCell(8, i).getContents());
                }
                excelList.add(p);
            }

            excelList = removeDuplicate(excelList);// 22 33//excel导入的数据
            List<CrmPriceLines> okexcelList = new ArrayList<CrmPriceLines>();// okexcelList
            List<CrmPriceLines> insertList = new ArrayList<CrmPriceLines>();// 需要作插入的数据(可能有异常数据)
            List<CrmPriceLines> updatedList = new ArrayList<CrmPriceLines>();// 需要作更新的数据
            List<CrmPriceLines> saveList = new ArrayList<CrmPriceLines>();// 已保存的数据
            List<CrmPriceLines> errorList = new ArrayList<CrmPriceLines>();// 异常数据

            CrmPriceLines t = new CrmPriceLines();
            t.setHeadid(Long.valueOf(id));// headid
            saveList = super.getFacade().getCrmPriceLinesService().getCrmPriceLinesList(t);// 3

            for (CrmPriceLines s : excelList) {
                if (s.getModelcode() != null && !"".equals(s.getModelcode())) {
                    okexcelList.add(s);
                }
            }

            // 找到覆盖的数据
            if (saveList.size() > 0) {
                for (CrmPriceLines s : okexcelList) {
                    for (CrmPriceLines o : saveList) {
                        if (s.getModelcode() != null && !"".equals(s.getModelcode())) {
                            if (o.getModelcode().equals(s.getModelcode())) {
                                s.setId(o.getId());
                                s.setHeadid(o.getHeadid());
                                updatedList.add(s);// update data ok
                            }
                        }
                    }
                }
            }

            if (updatedList.size() > 0) {
                okexcelList.removeAll(updatedList);
            }
            insertList = okexcelList; // 得到insert数据,可能有异常数据.比如物料编码错误的

            // update data
            for (CrmPriceLines o1 : updatedList) {
                super.getFacade().getCrmPriceLinesService().modifyCrmPriceLines(o1);
            }
            // insert data
            for (CrmPriceLines o3 : insertList) {
                CrmPriceLines o4 = o3;
                if (o4.getModelcode() != null && !o4.getModelcode().equals("")) {
                    PePdModel pm1 = new PePdModel();
                    pm1.setMd_name(o4.getModelcode());
                    pm1.setIs_del(0);
                    pm1 = super.getFacade().getPePdModelService().getPePdModel(pm1);
                    if (pm1 == null) {
                        errorList.add(o3);
                    } else {
                        o4.setFunc(getFuncsByPmModel(pm1));
                        o4.setModelname(pm1.getPd_desc());
                        super.getFacade().getCrmPriceLinesService().createCrmPriceLines(o4);
                    }
                } else {
                    // do nothing
                }
            }

            if (errorList.size() > 0) {
                StringBuffer sb = new StringBuffer();
                sb.append("");
                for (CrmPriceLines l : errorList) {
                    sb.append(l.getModelcode()).append(",");
                }
                saveDirectlyMessage(request, "部分数据成功更新,但以下机型[" + sb.toString() + "]在产品库找不到或已被停用");
            } else {
                saveMessage(request, "entity.updated");
            }

            excelList = null;// excel数据
            saveList = null;// 当前价格表已经保存的数据
            updatedList = null;// 更新数据
            insertList = null;// 插入数据

        } else {
            saveDirectlyError(request, "更新失败,请检查excel数据是否符合要求");
        }
        return this.edit(mapping, form, request, response);

    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static List removeDuplicate(List list) {
        HashSet h = new HashSet(list);
        list.clear();
        list.addAll(h);
        return list;
    }

}
