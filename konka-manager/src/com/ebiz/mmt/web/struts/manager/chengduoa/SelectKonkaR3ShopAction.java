package com.ebiz.mmt.web.struts.manager.chengduoa;

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

        KonkaR3Shop entity = getKonkaR3ShopForSelect(mapping, form, request, response, ui.getDept_id());// 获取当前用户相关联的直营网点分页列表
        log.info("===============" + selects);
        log.info("-------------------" + ui.getDept_id());

        if (StringUtils.isNotBlank(selects)) {
            entity.getMap().put("selects", selects);
            request.setAttribute("selectList", super.getFacade().getKonkaR3ShopService().getKonkaR3ShopList(entity));
            entity.getMap().put("selects", null);
            entity.getMap().put("id_not_selects", selects);

        }

        entity.getMap().put("customer_name_like", key_word);
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
        String ids = (String) dynaBean.get("ids");// 已选的IDS

        PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

        StringBuffer sb = new StringBuffer("[");

        KonkaR3Shop entity = getKonkaR3ShopForSelect(mapping, form, request, response, ui.getDept_id());// 获取当前用户相关联的直营网点分页列表
        entity.getMap().put("customer_name_like", key_word);
        if (!"".equals(ids) && ids != null) {
            entity.getMap().put("id_not_selects", ids);
        }

        // 迫使用户使用过滤,最多给出100条客户信息
        entity.getRow().setFirst(0);
        entity.getRow().setCount(50);
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
