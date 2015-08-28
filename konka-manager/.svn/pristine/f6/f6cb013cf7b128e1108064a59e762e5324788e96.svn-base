package com.ebiz.mmt.web.struts.manager.oa;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;

public class SelectKonkaR3ShopAction extends BaseAction {

    public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return this.list(mapping, form, request, response);
    }

    public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        setNaviStringToRequestScope(form, request);
        DynaBean dynaBean = (DynaBean) form;
        super.encodeCharacterForGetMethod(dynaBean, request);

        String key_word = (String) dynaBean.get("key_word");
        String selects = (String) dynaBean.get("selects");

        PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

        PeRoleUser _peRoleUser = new PeRoleUser();
        _peRoleUser.setUser_id(ui.getId());
        List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

        KonkaR3Shop entity = new KonkaR3Shop();
        entity.setIs_del(new Long(0));
        entity.getMap().put("is_assign", "true");// 获取限定条件网点分配

        boolean role_id_ge_20 = false;
        for (PeRoleUser peRoleUser : peRoleUserList) {
            if (peRoleUser.getRole_id() >= 20L) {
                role_id_ge_20 = true;
            }
        }

        if (role_id_ge_20) { // 不是系统管理员组全部要加上网点分配
            entity.getMap().put("dept_id", super.getSuperiorForDeptType(ui.getDept_id(), 3).getDept_id());
            entity.getMap().put("user_id", this.getSessionUserInfo(request).getId());
            request.setAttribute("role_type", 1);
        }
        log.info("===============" + selects);
        log.info("-------------------" + ui.getDept_id());

        if (StringUtils.isNotBlank(selects)) {
            entity.getMap().put("selects", selects);
            request.setAttribute("selectList", super.getFacade().getKonkaR3ShopService().getKonkaR3ShopList(entity));
            entity.getMap().put("selects", null);
            entity.getMap().put("id_not_selects", selects);

        }

        entity.getMap().put("keyword_like", key_word);
        entity.getMap().put("Konka_r3_id", null);

        // 迫使用户使用过滤,最多给出100条客户信息
        entity.getRow().setFirst(0);
        entity.getRow().setCount(100);

        List<KonkaR3Shop> entityList = getFacade().getKonkaR3ShopService().getKonkaR3ShopList(entity);
        request.setAttribute("entityList", entityList);

        return mapping.findForward("list");
    }

    public ActionForward getQueryNames(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        DynaBean dynaBean = (DynaBean) form;
        String key_word = (String) dynaBean.get("key_word");
        String ids = (String) dynaBean.get("ids");

        PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

        PeRoleUser _peRoleUser = new PeRoleUser();
        _peRoleUser.setUser_id(ui.getId());
        List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

        StringBuffer sb = new StringBuffer("[");

        KonkaR3Shop entity = new KonkaR3Shop();
        entity.setIs_del(new Long(0));
        entity.getMap().put("is_assign", "true");// 获取限定条件网点分配


        boolean role_id_ge_20 = false;
        for (PeRoleUser peRoleUser : peRoleUserList) {
            if (peRoleUser.getRole_id() >= 20L) {
                role_id_ge_20 = true;
            }
        }

        if (role_id_ge_20) { // 不是系统管理员组全部要加上网点分配
            entity.getMap().put("dept_id", super.getSuperiorForDeptType(ui.getDept_id(), 3).getDept_id());
            entity.getMap().put("user_id", this.getSessionUserInfo(request).getId());
            request.setAttribute("role_type", 1);
        }
        entity.getMap().put("keyword_like", key_word);
        if (!"".equals(ids) && ids != null) {
            entity.getMap().put("id_not_selects", ids);
        }


        // 迫使用户使用过滤,最多给出100条客户信息
        entity.getRow().setFirst(0);
        entity.getRow().setCount(100);
        List<KonkaR3Shop> entityList = getFacade().getKonkaR3ShopService().getKonkaR3ShopList(entity);

        for (KonkaR3Shop t : entityList) {
            sb.append("{\"r3_shop_id\":\"" + String.valueOf(t.getId()) + "\",");
            sb.append("\"customer_name\":\"" + StringEscapeUtils.escapeJavaScript(t.getCustomer_name()) + "\"},");
        }
        sb.append("{\"end\":\"\"}]");
        log.info("sb:" + sb.toString());
        super.renderJson(response, sb.toString());

        return null;
    }
}
