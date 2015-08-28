package com.ebiz.mmt.web.struts.manager.oa;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.alibaba.fastjson.JSON;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.OaUserGroupH;
import com.ebiz.mmt.domain.OaUserGroupL;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

public class OaUserGroupAction extends BaseAction {

    @Override
    public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return this.list(mapping, form, request, response);
    }

    public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        DynaBean dynaBean = (DynaBean) form;
        String mod_id = (String) dynaBean.get("mod_id");
        String id = (String) dynaBean.get("id");// 头表主键id

        OaUserGroupH entity = new OaUserGroupH();
        entity.setId(Long.valueOf(id));
        entity = super.getFacade().getOaUserGroupHService().getOaUserGroupH(entity);
        copyProperties(form, entity);

        OaUserGroupL ol = new OaUserGroupL();
        ol.setHead_id(Long.valueOf(id));
        List<OaUserGroupL> OaUserGroupLList = super.getFacade().getOaUserGroupLService().getOaUserGroupLList(ol);


        PeProdUser userInfo = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

        KonkaDept konkaDept = new KonkaDept();
        konkaDept.setDept_type(3);
        // 多媒体事业本部 0 系统管理员
        if (userInfo.getDept_id() != 0) {
            konkaDept.setDept_id(userInfo.getDept_id());
        }
        konkaDept.getMap().put("order_by_dept_name", true);
        List<KonkaDept> deptList = super.getFacade().getKonkaDeptService().getKonkaDeptList(konkaDept);
        request.setAttribute("deptList", deptList);
        request.setAttribute("OaUserGroupLList", OaUserGroupLList);

        return mapping.findForward("input");
    }

    public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        setNaviStringToRequestScope(form, request);
        DynaBean dynaBean = (DynaBean) form;
        String mod_id = (String) dynaBean.get("mod_id");
        Pager pager = (Pager) dynaBean.get("pager");

        PeProdUser userInfo = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

        String group_name = (String) dynaBean.get("group_name");
        String dept_id = (String) dynaBean.get("dept_id");
        OaUserGroupH entity = new OaUserGroupH();
        if (dept_id != null && !"".equals(dept_id)) {
            entity.setDept_id(Integer.valueOf(dept_id));
        }
        entity.setGroup_name(group_name);
        Long recordCount = super.getFacade().getOaUserGroupHService().getOaUserGroupHCount(entity);
        pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
        entity.getRow().setFirst(pager.getFirstRow());
        entity.getRow().setCount(pager.getRowCount());

        List<OaUserGroupH> OaUserGroupHList = getFacade().getOaUserGroupHService().getOaUserGroupHPaginatedList(entity);
        request.setAttribute("OaUserGroupHList", OaUserGroupHList);

        KonkaDept konkaDept = new KonkaDept();
        konkaDept.setDept_type(3);
        // 多媒体事业本部 0 系统管理员
        if (userInfo.getDept_id() != 0) {
            konkaDept.setDept_id(userInfo.getDept_id());
        }
        konkaDept.getMap().put("order_by_dept_name", true);
        List<KonkaDept> deptList = super.getFacade().getKonkaDeptService().getKonkaDeptList(konkaDept);
        request.setAttribute("deptList", deptList);

        return mapping.findForward("list");
    }


    public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        setNaviStringToRequestScope(form, request);
        return mapping.findForward("view");
    }


    public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        setNaviStringToRequestScope(form, request);
        DynaBean dynaBean = (DynaBean) form;
        String mod_id = (String) dynaBean.get("mod_id");
        PeProdUser userInfo = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

        KonkaDept konkaDept = new KonkaDept();
        konkaDept.setDept_type(3);
        // 多媒体事业本部 0 系统管理员
        if (userInfo.getDept_id() != 0) {
            konkaDept.setDept_id(userInfo.getDept_id());
        }
        konkaDept.getMap().put("order_by_dept_name", true);
        List<KonkaDept> deptList = super.getFacade().getKonkaDeptService().getKonkaDeptList(konkaDept);
        request.setAttribute("deptList", deptList);

        return mapping.findForward("input");
    }

    /**
     * 
     * 只保存头表
     */
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        DynaBean dynaBean = (DynaBean) form;
        String mod_id = (String) dynaBean.get("mod_id");

        String group_name = (String) dynaBean.get("group_name");
        String dept_id = (String) dynaBean.get("dept_id");
        String dept_name = (String) dynaBean.get("dept_name");
        String id = (String) dynaBean.get("id");

        OaUserGroupH oh = new OaUserGroupH();
        oh.setDept_id(Integer.valueOf(dept_id));
        oh.setDept_name(dept_name);
        oh.setGroup_name(group_name);

        if (id == null || "".equals(id)) {
            // insert
            super.getFacade().getOaUserGroupHService().createOaUserGroupH(oh);

        } else {
            // update
            oh.setId(Long.valueOf(id));
            super.getFacade().getOaUserGroupHService().modifyOaUserGroupH(oh);
        }

        saveDirectlyMessage(request, "修改成功!");

        // the line below is added for pagination
        StringBuffer pathBuffer = new StringBuffer();
        pathBuffer.append(mapping.findForward("success").getPath());
        pathBuffer.append("&mod_id=" + mod_id);
        ActionForward forward = new ActionForward(pathBuffer.toString(), true);
        // end
        return forward;
    }

    /**
     * 
     * 只保存行表
     */
    public ActionForward saveL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        DynaBean dynaBean = (DynaBean) form;
        String mod_id = (String) dynaBean.get("mod_id");
        String user_ids = (String) dynaBean.get("user_ids");// 取到的用户都没有保存过的.
        String user_names = (String) dynaBean.get("user_names");
        String header_id = (String) dynaBean.get("header_id");// 头表id

        if (user_ids != null && !"".equals(user_ids)) {
            String[] ids = user_ids.split(",");
            String[] names = user_names.split(",");
            if (ids.length == names.length) {
                for (int i = 0; i < ids.length; i++) {
                    OaUserGroupL ol = new OaUserGroupL();
                    ol.setHead_id(Long.valueOf(header_id));
                    ol.setUser_name(names[i]);
                    ol.setUser_id(Long.valueOf(ids[i]));
                    super.getFacade().getOaUserGroupLService().createOaUserGroupL(ol);
                }
            }
        }
        // response.getWriter().write("<script>alert('保存成功!');</script>");
        // close;

        String json = JSON.toJSONString("恭喜,已操作成功!");
        renderJson(response, json);

        return null;
    }


    /**
     * 
     * 只删除头表数据
     */
    public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        DynaBean dynaBean = (DynaBean) form;
        String mod_id = (String) dynaBean.get("mod_id");
        String id = (String) dynaBean.get("id");// 头表id
        String is_del = (String) dynaBean.get("is_del");


        if (StringUtils.isNotEmpty(id)) {
            OaUserGroupH oh = new OaUserGroupH();
            oh.setId(Long.valueOf(id));
            oh.setIs_del(Long.valueOf(is_del));
            super.getFacade().getOaUserGroupHService().modifyOaUserGroupH(oh);
            saveDirectlyMessage(request, "修改成功!");
        } else {
            saveDirectlyError(request, "记录ID丢失,修改失败!");
        }

        return mapping.findForward("list");
    }

    /**
     * 
     * 只删除行表数据
     */
    public ActionForward deleteL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        DynaBean dynaBean = (DynaBean) form;
        String mod_id = (String) dynaBean.get("mod_id");
        String id = (String) dynaBean.get("id");// 行表id
        String header_id = (String) dynaBean.get("header_id");// 头表id

        if (StringUtils.isNotEmpty(id)) {
            OaUserGroupL ol = new OaUserGroupL();
            ol.setId(Long.valueOf(id));
            ol.setHead_id(Long.valueOf(header_id));
            super.getFacade().getOaUserGroupLService().removeOaUserGroupL(ol);
            saveDirectlyMessage(request, "修改成功!");
        } else {
            saveDirectlyError(request, "记录ID丢失,修改失败!");
        }
        // header_id转到edit方法后变成头表主键ID
        dynaBean.set("id", header_id);
        return this.edit(mapping, form, request, response);

    }


    public ActionForward showEditPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        DynaBean dynaBean = (DynaBean) form;
        String mod_id = (String) dynaBean.get("mod_id");
        String header_id = (String) dynaBean.get("header_id");//
        String dept_id = (String) dynaBean.get("dept_id");// 当前操作人所在分公司id
        dynaBean.set("dept_id", dept_id);
        dynaBean.set("mod_id", mod_id);
        dynaBean.set("headid", header_id);

        return new ActionForward("/oa/OaUserGroup/saveuser.jsp");
    }

    /**
     * 最多只获取50个,要限制用户使用关键词过滤
     */
    public void ajaxGetUsersByName(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        DynaBean dynaBean = (DynaBean) form;
        String header_id = (String) dynaBean.get("header_id");//
        String dept_id = (String) dynaBean.get("dept_id");// 当前操作人所在分公司id
        String user_name_like = (String) dynaBean.get("user_name_like");

        PeProdUser user = new PeProdUser();
        user.setDept_id(Long.valueOf(dept_id));
        user.setIs_del(0);
        user.getMap().put("name_like", user_name_like);
        user.getRow().setFirst(0);
        user.getRow().setCount(50);
        // 过滤掉,已经保存过的用户
        List<PeProdUser> userList = super.getFacade().getPeProdUserService().getPeProdUserList(user);
        CopyOnWriteArrayList<PeProdUser> copyUserList = new CopyOnWriteArrayList<PeProdUser>();
        for (PeProdUser ou : userList) {
            copyUserList.add(ou);
        }


        OaUserGroupL ol = new OaUserGroupL();
        ol.setHead_id(Long.valueOf(header_id));
        List<OaUserGroupL> olList = super.getFacade().getOaUserGroupLService().getOaUserGroupLList(ol);
        if (olList != null && olList.size() > 0) {
            //
            for (PeProdUser ou : copyUserList) {
                // 已保存的
                for (OaUserGroupL l : olList) {
                    if (l.getUser_id().equals(ou.getId())) {
                        copyUserList.remove(ou);
                        break;
                    }
                }
            }
        }


        String json = JSON.toJSONString(copyUserList);
        renderJson(response, json);

    }


}
