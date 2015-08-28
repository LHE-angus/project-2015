package com.ebiz.mmt.web.struts.jxc;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaR3SellImpInvalidData;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Zhang,Xufeng
 * @version 2011-11-16
 */
@Deprecated
public class KonkaJxcR3SellImpInvalidDateAction extends BaseJxcAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

    public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        if (!isSessionLoginForKonkaJxc(request)) {
            return forwardNoSessionForKonkaJxc(request, response);
        }
        if (null == super.checkUserModPopeDom(form, request, "0")) {
            return super.checkPopedomInvalid(request, response);
        }

        setNaviStringToRequestScope(form, request);
        DynaBean dynaBean = (DynaBean) form;

        Pager pager = (Pager) dynaBean.get("pager");
        String start_date = (String) dynaBean.get("start_date");
        String end_date = (String) dynaBean.get("end_date");
        // 初始化列表页面的起始时间
        Date now = new Date();
        if (StringUtils.isBlank(start_date)) {
            start_date = DateFormatUtils.format(now, "yyyy-MM-") + "01";
        }
        if (StringUtils.isBlank(end_date)) {
            end_date = DateFormatUtils.format(now, "yyyy-MM-dd");
        }
        KonkaR3SellImpInvalidData entity = new KonkaR3SellImpInvalidData();
        super.copyProperties(entity, form);
        if (StringUtils.isNotBlank(start_date)) {
            entity.getMap().put("start_date", start_date);
        }
        if (StringUtils.isNotBlank(end_date)) {
            entity.getMap().put("end_date", end_date);
        }

        Long count =
                getFacade().getKonkaR3SellImpInvalidDataService()
                        .getKonkaR3SellImpInvalidDataCount(entity);
        pager.init(count, 10, pager.getRequestPage());
        entity.getRow().setFirst(pager.getFirstRow());
        entity.getRow().setCount(pager.getRowCount());
        List<KonkaR3SellImpInvalidData> konkaR3SellImpInvalidDataList =
                getFacade().getKonkaR3SellImpInvalidDataService()
                        .getKonkaR3SellImpInvalidDataPaginatedList(entity);
        dynaBean.set("start_date", start_date);
        dynaBean.set("end_date", end_date);
        request.setAttribute("konkaR3SellImpInvalidDataList", konkaR3SellImpInvalidDataList);
        return mapping.findForward("list");
    }

    public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        if (!isSessionLoginForKonkaJxc(request)) {
            return forwardNoSessionForKonkaJxc(request, response);
        }
        setNaviStringToRequestScope(form, request);
        DynaBean dynaBean = (DynaBean) form;
        String id = (String) dynaBean.get("id");

        if (GenericValidator.isLong(id)) {
            KonkaR3SellImpInvalidData entity = new KonkaR3SellImpInvalidData();
            entity.setId(Long.valueOf(id));
            entity =
                    getFacade().getKonkaR3SellImpInvalidDataService().getKonkaR3SellImpInvalidData(
                            entity);
            if (null == entity) {
                saveMessage(request, "entity.missing");
                return mapping.findForward("list");
            }
            entity.setQueryString(super.serialize(request, "id", "method"));
            super.copyProperties(form, entity);
            return mapping.findForward("view");
        } else {
            this.saveError(request, "errors.long", new String[] {id});
            return mapping.findForward("list");
        }
    }
}
