package com.ebiz.mmt.web.struts.member.center;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.EcGiftJfBuy;
import com.ebiz.mmt.domain.EcUser;
import com.ebiz.mmt.web.struts.member.BaseMemberAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author tudp
 * @version 2014-11-25
 */
public class EcGiftJfBuyAction extends BaseMemberAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		HttpSession session = request.getSession();
		EcUser ecUser = (EcUser) session.getAttribute("ecUser");
		Pager pager = (Pager) dynaBean.get("pager");
		String add_date_start=(String) dynaBean.get("add_date_start");
		String add_date_end=(String) dynaBean.get("add_date_end");
		EcGiftJfBuy entity = new EcGiftJfBuy();
		super.copyProperties(entity, form);
		String ts = (String) dynaBean.get("ts");
		String orderState = (String) dynaBean.get("orderState");
		if ("2".equals(ts)) {
			entity.getMap().put("before3months", "before3months");
		} else if ("0".equals(ts)) {
		} else {
			dynaBean.set("ts", "1");
			entity.getMap().put("last3months", "last3months");
		}

		if ("3".equals(orderState)) {// 已取消
			entity.getMap().put("state_in", new Integer[] { -10, -20, -30 });
		} else if ("2".equals(orderState)) {// 已完成
			entity.getMap().put("state_in", new Integer[] { 50, 60 });
		} else {// 进行中
			entity.getMap().put("state_in", new Integer[] { 0, 5, 10, 20, 30, 40 });
			dynaBean.set("orderState", "1");
		}
		entity.getMap().put("add_date_start", add_date_start);
		entity.getMap().put("add_date_end", add_date_end);	
		entity.setUser_id(ecUser.getId());
		entity.setOwn_sys(ecUser.getUser_type());// 订单来源 1：工卡,2触网
		Long recordCount = super.getFacade().getEcGiftJfBuyService().getEcGiftJfBuyCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<EcGiftJfBuy> entityList = super.getFacade().getEcGiftJfBuyService().getEcGiftJfBuyPaginatedList(entity);
		request.setAttribute("entityList", entityList);

		return mapping.findForward("list");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		HttpSession session = request.getSession();
		EcUser ecUser = (EcUser) session.getAttribute("ecUser");
		EcGiftJfBuy entity = new EcGiftJfBuy();
		if (StringUtils.isNotBlank(id)) {
			entity.setId(Long.valueOf(id));
		}
		entity.setUser_id(ecUser.getId());
		entity = super.getFacade().getEcGiftJfBuyService().getEcGiftJfBuy(entity);
		if (entity == null) {
			String msg = "对不起，未找到该订单信息！";
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');location.href='EcGiftJfBuy.do';}");
			return null;
		}

		entity.setQueryString(super.serialize(request, "id", "method"));
		super.copyProperties(form, entity);
 
		return mapping.findForward("view");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		HttpSession session = request.getSession();
		EcUser ecUser = (EcUser) session.getAttribute("ecUser");
		if (!StringUtils.isBlank(id) && GenericValidator.isLong(id)) {
			EcGiftJfBuy entity = new EcGiftJfBuy();
			entity.setId(new Long(id));
			entity.setState(new Integer(0));
			entity.setUser_id(ecUser.getId());
			int i = getFacade().getEcGiftJfBuyService().modifyEcGiftJfBuyForCancel(entity);
			if (i == 0) {
				String msg = "对不起，订单状态已更改，取消订单失败！";
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg
				        + "');location.href='Orders.do';}");
				return null;
			}
		}
		return mapping.findForward("success");
	}
 
}
