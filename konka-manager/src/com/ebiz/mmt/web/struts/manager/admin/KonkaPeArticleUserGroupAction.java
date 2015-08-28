package com.ebiz.mmt.web.struts.manager.admin;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.alibaba.fastjson.JSON;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaPeArticleGUsers;
import com.ebiz.mmt.domain.KonkaPeArticleUserGroup;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleInfo;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Hu,Hao
 * @version 2013-07-11
 */
public class KonkaPeArticleUserGroupAction extends BaseAction {
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
        super.getModPopeDom(form, request);

        setNaviStringToRequestScope(form, request);

        DynaBean dynaBean = (DynaBean) form;

        String group_name_like = (String) dynaBean.get("group_name_like");

        Pager pager = (Pager) dynaBean.get("pager");

        KonkaPeArticleUserGroup entity = new KonkaPeArticleUserGroup();

        if (StringUtils.isNotBlank(group_name_like)) {
            entity.getMap().put("group_name_like", group_name_like);
        }

        PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

        entity.setAdd_user_id(ui.getId());

        entity.setIs_del(0);
        Long recordCount =
                getFacade().getKonkaPeArticleUserGroupService().getKonkaPeArticleUserGroupCount(
                        entity);
        pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
        entity.getRow().setFirst(pager.getFirstRow());
        entity.getRow().setCount(pager.getRowCount());

        List<KonkaPeArticleUserGroup> entityList =
                getFacade().getKonkaPeArticleUserGroupService()
                        .getKonkaPeArticleUserGroupPaginatedList(entity);

        if (entityList.size() > 0) {
            for (KonkaPeArticleUserGroup temp : entityList) {
                KonkaPeArticleGUsers konkaPeArticleGUsers = new KonkaPeArticleGUsers();
                konkaPeArticleGUsers.setGroup_id(temp.getGroup_id());
                List<KonkaPeArticleGUsers> konkaPeArticleGUsersList =
                        super.getFacade().getKonkaPeArticleGUsersService()
                                .getKonkaPeArticleGUsersList(konkaPeArticleGUsers);
                if (konkaPeArticleGUsersList.size() > 0) ;
                String group_users = "";
                for (KonkaPeArticleGUsers temp1 : konkaPeArticleGUsersList) {
                    group_users = group_users + "," + temp1.getGroup_user_name();
                }
                temp.getMap().put("group_users", group_users.substring(1, group_users.length()));
            }
        }

        request.setAttribute("entityList", entityList);

        return mapping.findForward("list");
    }

    public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        if (null == super.checkUserModPopeDom(form, request, "0")) {
            return super.checkPopedomInvalid(request, response);
        }
        super.getModPopeDom(form, request);

        setNaviStringToRequestScope(form, request);

        PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

        if (null == ui) {
            String msg = super.getMessage(request, "popedom.check.invalid");
            renderJavaScript(response, "window.onload=function(){alert('" + msg
                    + "');history.back();}");
            return null;
        }

        PeRoleInfo peRoleInfo = new PeRoleInfo();// 角色信息
        peRoleInfo.setIs_del(0);
        PeRoleUser peRoleUser = new PeRoleUser();// 用户对应的角色
        peRoleUser.setUser_id(ui.getId());
        List<PeRoleUser> peRoleUserList =
                super.getFacade().getPeRoleUserService().getPeRoleUserList(peRoleUser);
        Boolean role_id_lt_30 = false;// 总部
        Boolean role_id_lt_100 = false;// 分公司人员
        if (peRoleUserList.size() > 0) {
            for (PeRoleUser temp : peRoleUserList) {
                if (temp.getRole_id() < 30) {
                    role_id_lt_30 = true;
                }
                if (temp.getRole_id() >= 30 && temp.getRole_id() < 100) {
                    role_id_lt_100 = true;
                }
            }
        }

        PeProdUser peProdUser = new PeProdUser();// 回显用户
        peProdUser.getMap().put("user_name_is_not_null", true);
        // peProdUser.getMap().put("user_role_is_not_188", true);

        Long dept_id = null;
        if (role_id_lt_30) {
            dept_id = 0L;
        }
        if (role_id_lt_100 && !role_id_lt_30) {
            dept_id = getKonkaDeptForFgs(ui.getDept_id()).getDept_id();
            peRoleInfo.setDept_id(dept_id);
            peProdUser.getMap().put("dept_id_in", dept_id);
            peRoleInfo.getMap().put("role_id_60_32_187_188", true);
        }

        request.setAttribute("peProdUserList", super.getFacade().getPeProdUserService()
                .getPeProdUserListForGroup(peProdUser));

        List<PeRoleInfo> peRoleUserList1 =
                super.getFacade().getPeRoleInfoService().getPeRoleInfoList(peRoleInfo);
        // 客户
        PeRoleInfo pr = new PeRoleInfo();
        pr.setRole_name("客户");
        pr.setRole_id(-10L);
        peRoleUserList1.add(pr);

        request.setAttribute("peRoleUserList", peRoleUserList1);
        request.setAttribute("dept_value", dept_id);
        return mapping.findForward("input");
    }

    public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        setNaviStringToRequestScope(form, request);

        DynaBean dynaBean = (DynaBean) form;
        String group_id = (String) dynaBean.get("group_id");

        if (!GenericValidator.isLong(group_id)) {
            String msg = super.getMessage(request, "errors.parm");
            renderJavaScript(response, "window.onload=function(){alert('" + msg
                    + "');history.back();}");
            return null;
        }

        KonkaPeArticleUserGroup entity = new KonkaPeArticleUserGroup();
        entity.setGroup_id(Long.valueOf(group_id));
        entity =
                super.getFacade().getKonkaPeArticleUserGroupService()
                        .getKonkaPeArticleUserGroup(entity);

        if (entity == null) {
            String msg = super.getMessage(request, "entity.empty");
            renderJavaScript(response, "window.onload=function(){alert('" + msg
                    + "');history.back();}");
            return null;
        }
        super.copyProperties(form, entity);

        PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

        if (null == ui) {
            String msg = super.getMessage(request, "popedom.check.invalid");
            renderJavaScript(response, "window.onload=function(){alert('" + msg
                    + "');history.back();}");
            return null;
        }

        PeRoleInfo peRoleInfo = new PeRoleInfo();// 角色信息
        peRoleInfo.setIs_del(0);
        // peRoleInfo.getMap().put("role_id_is_not_188", true);
        entity.getMap().put("dept_id_in", ui.getDept_id());

        PeRoleUser peRoleUser = new PeRoleUser();// 用户对应的角色
        peRoleUser.setUser_id(ui.getId());
        List<PeRoleUser> peRoleUserList =
                super.getFacade().getPeRoleUserService().getPeRoleUserList(peRoleUser);
        Boolean role_id_lt_30 = false;// 总部
        Boolean role_id_lt_100 = false;// 分公司人员
        if (peRoleUserList.size() > 0) {
            for (PeRoleUser temp : peRoleUserList) {
                if (temp.getRole_id() < 30) {
                    role_id_lt_30 = true;
                }
                if (temp.getRole_id() >= 30 && temp.getRole_id() < 100) {
                    role_id_lt_100 = true;
                }
            }
        }
        Long dept_id = null;

        PeProdUser peProdUser = new PeProdUser();// 回显用户
        peProdUser.getMap().put("user_name_is_not_null", true);
        // peProdUser.getMap().put("user_role_is_not_188", true);
        if (role_id_lt_30) {
            dept_id = 0L;
        }
        if (role_id_lt_100) {
            dept_id = getKonkaDeptForFgs(ui.getDept_id()).getDept_id();
            peRoleInfo.setDept_id(dept_id);
            peProdUser.setDept_id(dept_id);
            peRoleInfo.getMap().put("role_id_60_32_187_188", true);
        }

        KonkaPeArticleGUsers konkaPeArticleGUsers = new KonkaPeArticleGUsers();
        konkaPeArticleGUsers.setGroup_id(Long.valueOf(group_id));
        List<KonkaPeArticleGUsers> konkaPeArticleGUsersList =
                super.getFacade().getKonkaPeArticleGUsersService()
                        .getKonkaPeArticleGUsersList(konkaPeArticleGUsers);

        request.setAttribute("konkaPeArticleGUsersList", konkaPeArticleGUsersList);

        request.setAttribute("peProdUserList", super.getFacade().getPeProdUserService()
                .getPeProdUserListForGroup(peProdUser));

        List<PeRoleInfo> peRoleUserList1 =
                super.getFacade().getPeRoleInfoService().getPeRoleInfoList(peRoleInfo);

        // 客户
        PeRoleInfo pr = new PeRoleInfo();
        pr.setRole_name("客户");
        pr.setRole_id(-10L);
        peRoleUserList1.add(pr);

        request.setAttribute("peRoleUserList", peRoleUserList1);
        request.setAttribute("dept_value", dept_id);
        return mapping.findForward("input");
    }

    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        if (null == super.checkUserModPopeDom(form, request, "0")) {
            return super.checkPopedomInvalid(request, response);
        }
        super.getModPopeDom(form, request);

        DynaBean dynaBean = (DynaBean) form;
        String mod_id = (String) dynaBean.get("mod_id");
        String group_id = (String) dynaBean.get("group_id");
        String group_user_ids = (String) dynaBean.get("group_user_ids");
        String group_desc = (String) dynaBean.get("group_desc");
        String group_name = (String) dynaBean.get("group_name");
        // String dept_value= (String) dynaBean.get("dept_value");

        KonkaPeArticleUserGroup entity = new KonkaPeArticleUserGroup();
        if (StringUtils.isNotBlank(group_id)) {
            entity.setGroup_id(Long.valueOf(group_id));
        }
        if (StringUtils.isNotBlank(group_desc)) {
            entity.setGroup_desc(group_desc);
        }
        if (StringUtils.isNotBlank(group_name)) {
            entity.setGroup_name(group_name);
        }
        if (StringUtils.isNotBlank(group_user_ids)) {
            entity.getMap().put("group_user_ids", group_user_ids);
        }

        if (GenericValidator.isLong(group_id)) {
            entity.setGroup_id(Long.valueOf(group_id));
            super.getFacade().getKonkaPeArticleUserGroupService()
                    .modifyKonkaPeArticleUserGroupForUsers(entity);
            super.saveMessage(request, "entity.updated");
        } else {
            PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

            if (null == ui) {
                String msg = super.getMessage(request, "popedom.check.invalid");
                renderJavaScript(response, "window.onload=function(){alert('" + msg
                        + "');history.back();}");
                return null;
            }

            KonkaDept kd = getKonkaDeptForFgs(ui.getDept_id());
            if (null != kd) {
                entity.setDept_id(kd.getDept_id());
            } else {
                entity.setDept_id(0L);
            }
            entity.setGroup_user_type(0);
            entity.setAdd_user_id(ui.getId());
            entity.setAdd_user_name(ui.getReal_name());
            entity.setAdd_date(new Date());
            super.getFacade().getKonkaPeArticleUserGroupService()
                    .createKonkaPeArticleUserGroupForUsers(entity);

            super.saveMessage(request, "entity.inserted");
        }

        // the line below is added for pagination
        StringBuffer pathBuffer = new StringBuffer();
        pathBuffer.append(mapping.findForward("success").getPath());
        pathBuffer.append("&").append("mod_id=" + mod_id);
        ActionForward forward = new ActionForward(pathBuffer.toString(), true);
        // end
        return forward;
    }

    public ActionForward getQueryUserNames(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        DynaBean dynaBean = (DynaBean) form;
        String dept_value = (String) dynaBean.get("dept_value");
        String user_name_like = (String) dynaBean.get("user_name_like");
        String role_id = (String) dynaBean.get("role_id");
        PeProdUser userInfo = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
        if (null == userInfo || null == userInfo.getDept_id()
                || "".equals(String.valueOf(userInfo.getDept_id()))) {
            String msg = super.getMessage(request, "popedom.check.invalid");
            renderJavaScript(response, "window.onload=function(){alert('" + msg
                    + "');history.back();}");
            return null;
        }

        PeProdUser entity = new PeProdUser();
        entity.setIs_del(0);
        entity.getMap().put("user_name_is_not_null", true);
        // entity.getMap().put("user_role_is_not_188", true);
        entity.getMap().put("dept_id_in", userInfo.getDept_id());

        if (StringUtils.isNotBlank(user_name_like)) {
            entity.getMap().put("user_name_like", user_name_like);
        }
        if (StringUtils.isNotBlank(role_id) && !"null".equals(role_id)) {
            entity.getMap().put("role_id", role_id);
        }
        if (StringUtils.isNotBlank(dept_value) && StringUtils.isNotBlank(role_id)) {

            // 判断是否是客户
            if ("-10".equals(role_id)) {

                entity.getMap().put("dept_id_in", null);
                entity.getMap().put("role_id", null);
                if ("0".equals(dept_value)) {
                    entity.getMap().put("kh_all_ids", true);
                } else {
                    KonkaDept kd = getKonkaDeptForFgs(userInfo.getDept_id());
                    if (kd != null) {
                        entity.getMap().put("kh_fgs_ids", kd.getDept_sn());
                    } else {
                        entity.getMap().put("kh_fgs_ids", "-10");
                    }
                }
            }
        }
        List<PeProdUser> entityList =
                super.getFacade().getPeProdUserService().getPeProdUserListForGroup(entity);

        super.renderTextJsonOrJsonp(request, response, JSON.toJSONString(entityList));
        return null;
    }

    public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        if (null == super.checkUserModPopeDom(form, request, "0")) {
            return super.checkPopedomInvalid(request, response);
        }
        super.getModPopeDom(form, request);

        DynaBean dynaBean = (DynaBean) form;
        String mod_id = (String) dynaBean.get("mod_id");
        String group_id = (String) dynaBean.get("group_id");

        if (!GenericValidator.isLong(group_id)) {
            String msg = super.getMessage(request, "errors.parm");
            renderJavaScript(response, "window.onload=function(){alert('" + msg
                    + "');history.back();}");
            return null;
        }

        KonkaPeArticleUserGroup entity = new KonkaPeArticleUserGroup();
        entity.setGroup_id(Long.valueOf(group_id));
        entity.setIs_del(1);
        super.getFacade().getKonkaPeArticleUserGroupService().modifyKonkaPeArticleUserGroup(entity);

        super.saveMessage(request, "entity.deleted");

        // the line below is added for pagination
        StringBuffer pathBuffer = new StringBuffer();
        pathBuffer.append(mapping.findForward("success").getPath());
        pathBuffer.append("&").append("mod_id=" + mod_id);
        ActionForward forward = new ActionForward(pathBuffer.toString(), true);
        // end
        return forward;
    }

}
